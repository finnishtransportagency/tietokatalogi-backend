package fi.liike.rest.Service;

import fi.liike.rest.Dao.HibernateSession;
import fi.liike.rest.api.ContentDto;
import fi.liike.rest.api.dto.*;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.sql.SQLException;
import java.util.*;

public class TietoarkkitehtuuriService extends HibernateSession {
    private final Logger LOG = LoggerFactory.getLogger(TietoarkkitehtuuriService.class);
    private FyysinenService fyysinenService;
    private LooginenService looginenService;
    private TietolajiService tietolajiService;
    private TietoryhmaService tietoryhmaService;
    private PaatietoryhmaService paatietoryhmaService;
    private HashMap<TietoryhmaDto, TietoryhmaDto> addedTietoryhmaMap;
    private HashMap<PaatietoryhmaDto, PaatietoryhmaDto> addedPaatietoryhmaMap;

    public TietoarkkitehtuuriService() {
        this.fyysinenService = new FyysinenService();
        this.looginenService = new LooginenService();
        this.tietolajiService = new TietolajiService();
        this.tietoryhmaService = new TietoryhmaService();
        this.paatietoryhmaService = new PaatietoryhmaService();
        this.addedTietoryhmaMap = new HashMap<>();
        this.addedPaatietoryhmaMap = new HashMap<>();
    }

    public TietoarkkitehtuuriService(FyysinenService fyysinenService, LooginenService looginenService,
                                     TietolajiService tietolajiService, TietoryhmaService tietoryhmaService,
                                     PaatietoryhmaService paatietoryhmaService) {
        this.fyysinenService = fyysinenService;
        this.looginenService = looginenService;
        this.tietolajiService = tietolajiService;
        this.tietoryhmaService = tietoryhmaService;
        this.paatietoryhmaService = paatietoryhmaService;
        this.addedTietoryhmaMap = new HashMap<>();
        this.addedPaatietoryhmaMap = new HashMap<>();
    }

    public TietoarkkitehtuuriDto create(TietoarkkitehtuuriDto content) throws IOException, SQLException {
        Session session = getSession();
        Transaction transaction = null;
        TietoarkkitehtuuriDto created;
        try {
            transaction = session.beginTransaction();
            created = save(session, content);
            transaction.commit();
        } catch (RuntimeException e) {
            try {
                LOG.error("Unable to do a transaction. Error message: " + e.getMessage());
                transaction.rollback();
                //TODO throw custom http exception
                return null;
            } catch (RuntimeException ex) {
                LOG.error("Cannot roll back transaction! Error Message: " + ex.getMessage());
                //TODO throw custom http exception
                return null;
            }
        } finally {
            if (session != null) {
                closeSession();
            }
        }
        return created;
    }

    private TietoarkkitehtuuriDto save(Session session, TietoarkkitehtuuriDto content) throws IOException, SQLException {
        this.addedTietoryhmaMap.clear();
        this.addedPaatietoryhmaMap.clear();

        TietoarkkitehtuuriDto created = new TietoarkkitehtuuriDto();
        FyysinenTietovarantoDto createdFyysinen = (FyysinenTietovarantoDto) saveContent(session, content.getFyysinen(), fyysinenService);
        created.setFyysinen(createdFyysinen);

        List<LooginenPaaTietoryhmaTietolaji> createdLooginenTietoList = new ArrayList<>();
        for (LooginenPaaTietoryhmaTietolaji contentLooginenTieto : content.getLooginenPaaTietoryhmaTietolajiList()) {
            LooginenPaaTietoryhmaTietolaji createdLooginenTieto = new LooginenPaaTietoryhmaTietolaji();

            LooginenTietovarantoDto contentLooginen = contentLooginenTieto.getLooginen();
            if (contentLooginen == null) {
                return created;
            }

            contentLooginen.setFyysinenTietovaranto(createdFyysinen.getTunnus());
            LooginenTietovarantoDto createdLooginen = (LooginenTietovarantoDto) saveContent(session, contentLooginen, looginenService);
            createdLooginenTieto.setLooginen(createdLooginen);

            List<PaatietoTietoryhmaTietolaji> createdSubCategoriesList = new ArrayList<>();
            for (PaatietoTietoryhmaTietolaji subCategories : contentLooginenTieto.getPaatietoTietoryhmaTietolajiList()) {
                PaatietoTietoryhmaTietolaji createdSubCategories = new PaatietoTietoryhmaTietolaji();

                //Create/update paatieto
                PaatietoryhmaDto createdPaatieto = this.saveContentOnce(
                        session, subCategories.getPaatieto(), this.addedPaatietoryhmaMap, this.paatietoryhmaService
                );
                TietoryhmaDto tietoryhmaContent = subCategories.getTietoryhma();
                tietoryhmaContent.setPaatietoryhma(createdPaatieto.getTunnus());

                //Create/update tietoryhma
                TietoryhmaDto createdTietoryhma = this.saveContentOnce(
                        session, subCategories.getTietoryhma(), this.addedTietoryhmaMap, this.tietoryhmaService
                );
                TietolajiDto tietolajiContent = subCategories.getTietolaji();
                tietolajiContent.setLooginenTietovarantoTunnus(createdLooginen.getTunnus());
                tietolajiContent.setTietoryhmatunnus(createdTietoryhma.getTunnus());

                //Create/update tietolaji
                TietolajiDto createdTietolaji = (TietolajiDto) saveContent(session, tietolajiContent, tietolajiService);

                createdSubCategories.setPaatieto(createdPaatieto);
                createdSubCategories.setTietoryhma(createdTietoryhma);
                createdSubCategories.setTietolaji(createdTietolaji);

                createdSubCategoriesList.add(createdSubCategories);
            }
            createdLooginenTieto.setPaatietoTietoryhmaTietolajiList(createdSubCategoriesList);
            createdLooginenTietoList.add(createdLooginenTieto);
        }
        created.setLooginenPaaTietoryhmaTietolajiList(createdLooginenTietoList);
        return created;
    }

    /**
     * Saves a DTO unless an identical object has been saved before in the same call to this.save().
     * The comparisons are based on the DTO's equals() methods.
     *
     * This works for small amounts of catalogue items being added. With more added content, this logic could be
     * replaced with a MERGE in the database instead.
     * @param session
     * @param dtoWithNoId A DTO object with no id, i.e. a DTO describing a potentially new, unsaved catalogue item.
     * @param addedContentMap A map of DTOs with no ID mapped to similar full DTOs with database IDs.
     * @param service
     * @param <A>
     * @return Returns a DTO of the saved object. In particular, the DTO includes the database ID.
     * @throws IOException
     * @throws SQLException
     */
    private <A extends ContentDto> A saveContentOnce(Session session, A dtoWithNoId, Map<A, A>addedContentMap, Service service)
            throws IOException, SQLException {
        if (!addedContentMap.containsKey(dtoWithNoId)) {
            A dtoWithDatabaseId = (A) saveContent(session, dtoWithNoId, service);
            addedContentMap.put(dtoWithNoId, dtoWithDatabaseId);
        }
        return addedContentMap.get(dtoWithNoId);
    }

    private ContentDto saveContent(Session session, ContentDto content, Service service) throws IOException, SQLException {
        Integer tunnus = content.getTunnus();
        if (tunnus != null) {
            //Update links
            return service.update(session, content);
        }
        return service.save(session, content);
    }
}
