package fi.liike.rest.Service;

import fi.liike.rest.Dao.Hibernate.ToimintaprosessiDaoImpl;
import fi.liike.rest.Dao.MainDao;
import fi.liike.rest.Model.Haettava;
import fi.liike.rest.api.ContentDto;
import fi.liike.rest.api.DaoContent;
import fi.liike.rest.api.DtoResults;
import fi.liike.rest.api.KasiteArvoContent;
import fi.liike.rest.api.SearchContent;
import fi.liike.rest.api.Converter.ToimintaprosessiConverter;
import fi.liike.rest.auth.InvalidTietokatalogiDataException;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.ListIterator;
import java.util.Set;

public class ToimintaprosessiService extends MainService implements Service {
    private MainDao dao;
    private ToimintaprosessiConverter converter;

    private final Logger LOG = LoggerFactory.getLogger(ToimintaprosessiService.class);

    public ToimintaprosessiService() {
        this.dao = new ToimintaprosessiDaoImpl();
        this.converter = new ToimintaprosessiConverter();
    }

    @Override
    public DtoResults getFiltered(SearchContent searchContent) {
        return super.getFiltered(converter, dao, searchContent);
    }

    @Override
    public List<ContentDto> getAll() {
        return super.getAll(converter, dao);
    }

    @Override
    public ContentDto save(ContentDto content) throws SQLException, IOException {
        DaoContent daoContent = new DaoContent();
        daoContent.setHaettava(converter.dtoToDomain(content));
        return converter.modelToDto(dao.save(null, daoContent));
    }

    @Override
    public ContentDto save(Session session, ContentDto content) throws SQLException, IOException {
        return null;
    }

    @Override
    public ContentDto update(ContentDto content) throws IOException, SQLException, InvalidTietokatalogiDataException {
        DaoContent daoContent = new DaoContent();
        daoContent.setHaettava(converter.dtoToDomain(content));
        return converter.modelToDto(dao.update(null, daoContent));
    }

    @Override
    public ContentDto update(Session session, ContentDto content) throws IOException, SQLException {
        return null;
    }

    @Override
    public Integer delete(int id, String remoteUser) {
        try {
            dao.delete(id, null, remoteUser);
            return id;
        }
        catch (SQLException e) {
            LOG.error("Unable to delete content");
            LOG.error(e.toString());
            return null;
        }
    }

    @Override
    public ContentDto get(int id) {
        return super.get(this.converter, this.dao, id);
    }

    @Override
    public List<KasiteArvoContent> getResources() {
        List<KasiteArvoContent> resources = dao.getResources();

        // Each creatable select dropdown should contain a list of previously added free text values
        // Use a set to get distinct values
        Set<KasiteArvoContent> creatableSelectValueSet = new HashSet<>();
        for (Haettava haettava : dao.getAll()) {
            KasiteArvoContent vastaavaOrganisaatioDto = converter.modelToVastaavaOrganisaatioDto(haettava);
            if (vastaavaOrganisaatioDto != null) {
                creatableSelectValueSet.add(vastaavaOrganisaatioDto);
            }
        }

        // There must be an id value for the dropdown to work, so convert the set to a list and iterate
        // Use a negative index value to indicate this is not a real database id value
        for (ListIterator<KasiteArvoContent> iter = new ArrayList<>(creatableSelectValueSet).listIterator(); iter.hasNext();) {
            KasiteArvoContent org = iter.next();
            org.setId(-iter.nextIndex());
            resources.add(org);
        }

        return resources;
    }
}
