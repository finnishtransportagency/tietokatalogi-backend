package fi.liike.rest.Service;

import fi.liike.rest.Dao.Hibernate.TietovarantoDaoImpl;
import fi.liike.rest.Dao.JoinPublicDao;
import fi.liike.rest.Dao.MainDao;
import fi.liike.rest.Model.Haettava;
import fi.liike.rest.Model.JoinTietovarantoAttribute;
import fi.liike.rest.Model.JoinTietovarantoHenkilotietoRyhma;
import fi.liike.rest.Model.JoinTietovarantoKasittelynPeruste;
import fi.liike.rest.Model.JoinTietovarantoRekisteroityRyhma;
import fi.liike.rest.Model.JoinTietovarantoTiedonohjaus;
import fi.liike.rest.Model.JoinTietovarantoTurvatoimenpide;
import fi.liike.rest.Model.JoinTietovarantoVastaanottajaRyhma;
import fi.liike.rest.Model.JoinTietovarantoYhteisrekisterinpitaja;
import fi.liike.rest.Model.JoinTietovarantoYllapitoMuuTaho;
import fi.liike.rest.Model.JoinToimintaprosessiTietovaranto;
import fi.liike.rest.api.*;
import fi.liike.rest.api.dto.TietovarantoConverter;
import fi.liike.rest.api.dto.TietovarantoDto;
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

public class TietovarantoService extends MainService implements Service {
    private MainDao dao;
    private JoinToimintaprosessiTietovarantoService joinToimintaprosessiTietovarantoService;
    private JoinTietovarantoAttributeService joinTietovarantoYhteisrekisterinpitajaService;
    private JoinTietovarantoAttributeService joinTietovarantoRekisteroityRyhmaService;
    private JoinTietovarantoAttributeService joinTietovarantoHenkilotietoRyhmaService;
    private JoinTietovarantoAttributeService joinTietovarantoTurvatoimenpideService;
    private JoinTietovarantoAttributeService joinTietovarantoKasittelynPerusteService;
    private JoinTietovarantoAttributeService joinTietovarantoYllapitoMuuTahoService;
    private JoinTietovarantoAttributeService joinTietovarantoVastaanottajaRyhmaService;
    private JoinTietovarantoAttributeService joinTietovarantoTiedonohjausService;
    private TietovarantoConverter converter;

    private final String YHTEISREKISTERINPITAJAT = "YHTEISREKISTERINPITAJAT";
    private final String REKISTEROITYJENRYHMAT = "REKISTEROITYJENRYHMAT";
    private final String HENKILOTIETOJENRYHMAT = "HENKILOTIETOJENRYHMAT";
    private final String TURVATOIMENPITEET = "TURVATOIMENPITEET";
    // private final String KASITTELYN_PERUSTEET = "KASITTELYN_PERUSTEET";
    private final String YLLAPITO_MUUT_TAHOT = "YLLAPITO_MUUT_TAHOT";
    private final String VASTAANOTTAJARYHMAT = "VASTAANOTTAJARYHMAT";

    private final Logger LOG = LoggerFactory.getLogger(TietovarantoService.class);

    public TietovarantoService() {
        this.dao = new TietovarantoDaoImpl();
        this.joinToimintaprosessiTietovarantoService = new JoinToimintaprosessiTietovarantoService();
        this.joinTietovarantoYhteisrekisterinpitajaService = new JoinTietovarantoAttributeService();
        this.joinTietovarantoRekisteroityRyhmaService = new JoinTietovarantoAttributeService();
        this.joinTietovarantoHenkilotietoRyhmaService = new JoinTietovarantoAttributeService();
        this.joinTietovarantoTurvatoimenpideService = new JoinTietovarantoAttributeService();
        this.joinTietovarantoKasittelynPerusteService = new JoinTietovarantoAttributeService();
        this.joinTietovarantoYllapitoMuuTahoService = new JoinTietovarantoAttributeService();
        this.joinTietovarantoVastaanottajaRyhmaService = new JoinTietovarantoAttributeService();
        this.joinTietovarantoTiedonohjausService = new JoinTietovarantoAttributeService();
        this.converter = new TietovarantoConverter();
    }

    @Override
    public DtoResults getFiltered(SearchContent searchContent) {
        ModelResults filtered = dao.getFiltered(searchContent);
        ArrayList<ContentDto> dtoResults = new ArrayList<ContentDto>();
        List<Haettava> modelResults = filtered.getHaettavat();
        for (Haettava haettava : modelResults) {
            dtoResults.add(addLinksAndConvert(haettava));
        }
        return new DtoResults(dtoResults, filtered.getTotalCount());
    }

    @Override
    public List<ContentDto> getAll() {
        return super.getAll(converter, dao);
    }

    @Override
    public ContentDto save(ContentDto content) throws SQLException, IOException {
        return save(null, content);
    }

    @Override
    public ContentDto save(Session session, ContentDto content) throws SQLException, IOException {
        DaoContent dataToSave = prepareDaoContent(content);
        Haettava savedTietovaranto = dao.save(session, dataToSave);
        if (savedTietovaranto != null) {
            return get(savedTietovaranto.getTunnus());
        }
        return null;
    }

    @Override
    public ContentDto update(ContentDto content) throws IOException, SQLException, InvalidTietokatalogiDataException {
        return update(null, content);
    }

    @Override
    public ContentDto update(Session session, ContentDto content) throws IOException, SQLException {
        DaoContent dataToSave = prepareDaoContent(content);
        Haettava savedTietovaranto = dao.update(session, dataToSave);
        if (savedTietovaranto != null) {
            return get(savedTietovaranto.getTunnus());
        }
        return null;
    }

    @Override
    public Integer delete(int id, String remoteUser) {
        try {
            // Fetch the existing tietovaranto content including joins
            ContentDto existingContent = this.get(id);
            if (existingContent != null) {
                DaoContent deleteContent = prepareDaoContent(existingContent);

                dao.delete(id, deleteContent, remoteUser);
                return id;
            }
            return null;
        }
        catch (SQLException e) {
            LOG.error("Unable to delete content");
            LOG.error(e.toString());
            return null;
        }
    }

    @Override
    public ContentDto get(int id) {
        Haettava haettava = dao.get(id);
        return addLinksAndConvert(haettava);
    }

    @Override
    public List<KasiteArvoContent> getResources() {
        ResourceTool resourceTool = new ResourceTool();
        List<KasiteArvoContent> resources = dao.getResources();
        resources.addAll(resourceTool.createResources(Catalogue.TOIMINTAPROSESSI));
        resources.addAll(resourceTool.createResources(Catalogue.ORGANISAATIO));

        // Each creatable select dropdown should contain a list of previously added free text values
        // Use a set to get distinct values
        Set<KasiteArvoContent> creatableSelectValueSet = new HashSet<>();
        for (Haettava haettava : dao.getAll()) {
            KasiteArvoContent rekisterinpitajaDto = converter.modelToRekisterinpitajaDto(haettava);
            if (rekisterinpitajaDto != null) {
                creatableSelectValueSet.add(rekisterinpitajaDto);
            }
        }

        List<String> yhteisrekisterinpitajaList = joinTietovarantoYhteisrekisterinpitajaService.getAllTietovarantoAttributes(JoinTietovarantoYhteisrekisterinpitaja.class);
        for (String yhteisrekisterinpitaja : yhteisrekisterinpitajaList) {
            if (yhteisrekisterinpitaja != null && yhteisrekisterinpitaja.length() > 0) {
                creatableSelectValueSet.add(new KasiteArvoContent(null, YHTEISREKISTERINPITAJAT, yhteisrekisterinpitaja));
            }
        }

        List<String> rekisteroityjenryhmaList = joinTietovarantoRekisteroityRyhmaService.getAllTietovarantoAttributes(JoinTietovarantoRekisteroityRyhma.class);
        for (String rekisteroityjenryhma : rekisteroityjenryhmaList) {
            if (rekisteroityjenryhma != null && rekisteroityjenryhma.length() > 0) {
                creatableSelectValueSet.add(new KasiteArvoContent(null, REKISTEROITYJENRYHMAT, rekisteroityjenryhma));
            }
        }

        List<String> henkilotietojenryhmaList = joinTietovarantoHenkilotietoRyhmaService.getAllTietovarantoAttributes(JoinTietovarantoHenkilotietoRyhma.class);
        for (String henkilotietojenryhma : henkilotietojenryhmaList) {
            if (henkilotietojenryhma != null && henkilotietojenryhma.length() > 0) {
                creatableSelectValueSet.add(new KasiteArvoContent(null, HENKILOTIETOJENRYHMAT, henkilotietojenryhma));
            }
        }

        List<String> turvatoimenpideList = joinTietovarantoTurvatoimenpideService.getAllTietovarantoAttributes(JoinTietovarantoTurvatoimenpide.class);
        for (String turvatoimenpide : turvatoimenpideList) {
            if (turvatoimenpide != null && turvatoimenpide.length() > 0) {
                creatableSelectValueSet.add(new KasiteArvoContent(null, TURVATOIMENPITEET, turvatoimenpide));
            }
        }

        // Note: the KASITTELYN_PERUSTEET values have been replaced by static options from the TIETOVARANTO_KASITE_ARVO table
        /*
        List<String> kasittelynPerusteList = joinTietovarantoKasittelynPerusteService.getAllTietovarantoAttributes(JoinTietovarantoKasittelynPeruste.class);
        for (String kasittelynPeruste : kasittelynPerusteList) {
            if (kasittelynPeruste != null && kasittelynPeruste.length() > 0) {
                creatableSelectValueSet.add(new KasiteArvoContent(null, KASITTELYN_PERUSTEET, kasittelynPeruste));
            }
        }
        */

        List<String> yllapitoMuuTahoList = joinTietovarantoYllapitoMuuTahoService.getAllTietovarantoAttributes(JoinTietovarantoYllapitoMuuTaho.class);
        for (String yllapitoMuuTaho : yllapitoMuuTahoList) {
            if (yllapitoMuuTaho != null && yllapitoMuuTaho.length() > 0) {
                creatableSelectValueSet.add(new KasiteArvoContent(null, YLLAPITO_MUUT_TAHOT, yllapitoMuuTaho));
            }
        }

        List<String> vastaanottajaRyhmaList = joinTietovarantoVastaanottajaRyhmaService.getAllTietovarantoAttributes(JoinTietovarantoVastaanottajaRyhma.class);
        for (String vastaanottajaRyhma : vastaanottajaRyhmaList) {
            if (vastaanottajaRyhma != null && vastaanottajaRyhma.length() > 0) {
                creatableSelectValueSet.add(new KasiteArvoContent(null, VASTAANOTTAJARYHMAT, vastaanottajaRyhma));
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

    private DaoContent prepareDaoContent(ContentDto content) {
        DaoContent saveContent = new DaoContent();
        saveContent.setHaettava(converter.dtoToDomain(content));

        // Create joinDao for toimintaprosessi-tietovaranto joins
        List<JoinToimintaprosessiTietovaranto> joinToimintaprosessiTietovarantoList = new ArrayList<>();
        List<Integer> toimintaprosessiIds = ((TietovarantoDto) content).getToimintaprosessiIds();
        if (toimintaprosessiIds != null) {
            for (Integer toimintaprosessiId : toimintaprosessiIds) {
                joinToimintaprosessiTietovarantoList.add(new JoinToimintaprosessiTietovaranto(toimintaprosessiId, content.getTunnus()));
            }
            JoinPublicDao joinToimintaprosessiTietovarantoDao = joinToimintaprosessiTietovarantoService.getDao(
                    joinToimintaprosessiTietovarantoList, content.getRivimuokkaajatunnus());
            saveContent.addJoinDao(joinToimintaprosessiTietovarantoDao);
        }

        // Create joinDao for tietovaranto-attribute joins for multiselect boxes
        List<JoinTietovarantoAttribute> joinTietovarantoYhteisrekisterinpitajaList = new ArrayList<>();
        List<String> yhteisrekisterinpitajat = ((TietovarantoDto) content).getYhteisrekisterinpitajat();
        if (yhteisrekisterinpitajat != null) {
            for (String yhteisrekisterinpitaja : yhteisrekisterinpitajat) {
                joinTietovarantoYhteisrekisterinpitajaList.add(new JoinTietovarantoYhteisrekisterinpitaja(content.getTunnus(), yhteisrekisterinpitaja));
            }
            JoinPublicDao joinTietovarantoYhteisrekisterinpitajaDao = joinTietovarantoYhteisrekisterinpitajaService.getDao(
                    joinTietovarantoYhteisrekisterinpitajaList, JoinTietovarantoYhteisrekisterinpitaja.class, content.getRivimuokkaajatunnus());
            saveContent.addJoinDao(joinTietovarantoYhteisrekisterinpitajaDao);
        }

        List<JoinTietovarantoAttribute> joinTietovarantoRekisteroityRyhmaList = new ArrayList<>();
        List<String> rekisteroityjenryhmat = ((TietovarantoDto) content).getRekisteroityjenryhmat();
        if (rekisteroityjenryhmat != null) {
            for (String rekisteroityjenryhma : rekisteroityjenryhmat) {
                joinTietovarantoRekisteroityRyhmaList.add(new JoinTietovarantoRekisteroityRyhma(content.getTunnus(), rekisteroityjenryhma));
            }
            JoinPublicDao joinTietovarantoRekisteroityRyhmaDao = joinTietovarantoRekisteroityRyhmaService.getDao(
                    joinTietovarantoRekisteroityRyhmaList, JoinTietovarantoRekisteroityRyhma.class, content.getRivimuokkaajatunnus());
            saveContent.addJoinDao(joinTietovarantoRekisteroityRyhmaDao);
        }

        List<JoinTietovarantoAttribute> joinTietovarantoHenkilotietoRyhmaList = new ArrayList<>();
        List<String> henkilotietojenryhmat = ((TietovarantoDto) content).getHenkilotietojenryhmat();
        if (henkilotietojenryhmat != null) {
            for (String henkilotietojenryhma : henkilotietojenryhmat) {
                joinTietovarantoHenkilotietoRyhmaList.add(new JoinTietovarantoHenkilotietoRyhma(content.getTunnus(), henkilotietojenryhma));
            }
            JoinPublicDao joinTietovarantoHenkilotietoRyhmaDao = joinTietovarantoHenkilotietoRyhmaService.getDao(
                    joinTietovarantoHenkilotietoRyhmaList, JoinTietovarantoHenkilotietoRyhma.class, content.getRivimuokkaajatunnus());
            saveContent.addJoinDao(joinTietovarantoHenkilotietoRyhmaDao);
        }

        List<JoinTietovarantoAttribute> joinTietovarantoTurvatoimenpideList = new ArrayList<>();
        List<String> turvatoimenpiteet = ((TietovarantoDto) content).getTurvatoimenpiteet();
        if (turvatoimenpiteet != null) {
            for (String turvatoimenpide : turvatoimenpiteet) {
                joinTietovarantoTurvatoimenpideList.add(new JoinTietovarantoTurvatoimenpide(content.getTunnus(), turvatoimenpide));
            }
            JoinPublicDao joinTietovarantoTurvatoimenpideDao = joinTietovarantoTurvatoimenpideService.getDao(
                    joinTietovarantoTurvatoimenpideList, JoinTietovarantoTurvatoimenpide.class, content.getRivimuokkaajatunnus());
            saveContent.addJoinDao(joinTietovarantoTurvatoimenpideDao);
        }

        List<JoinTietovarantoAttribute> joinTietovarantoKasittelynPerusteList = new ArrayList<>();
        List<String> kasittelynPerusteet = ((TietovarantoDto) content).getKasittelyn_perusteet();
        if (kasittelynPerusteet != null) {
            for (String kasittelynPeruste : kasittelynPerusteet) {
                joinTietovarantoKasittelynPerusteList.add(new JoinTietovarantoKasittelynPeruste(content.getTunnus(), kasittelynPeruste));
            }
            JoinPublicDao joinTietovarantoKasittelynPerusteDao = joinTietovarantoKasittelynPerusteService.getDao(
                    joinTietovarantoKasittelynPerusteList, JoinTietovarantoKasittelynPeruste.class, content.getRivimuokkaajatunnus());
            saveContent.addJoinDao(joinTietovarantoKasittelynPerusteDao);
        }

        List<JoinTietovarantoAttribute> joinTietovarantoYllapitoMuuTahoList = new ArrayList<>();
        List<String> yllapitoMuutTahot = ((TietovarantoDto) content).getYllapito_muut_tahot();
        if (yllapitoMuutTahot != null) {
            for (String yllapitoMuuTaho : yllapitoMuutTahot) {
                joinTietovarantoYllapitoMuuTahoList.add(new JoinTietovarantoYllapitoMuuTaho(content.getTunnus(), yllapitoMuuTaho));
            }
            JoinPublicDao joinTietovarantoYllapitoMuuTahoDao = joinTietovarantoYllapitoMuuTahoService.getDao(
                    joinTietovarantoYllapitoMuuTahoList, JoinTietovarantoYllapitoMuuTaho.class, content.getRivimuokkaajatunnus());
            saveContent.addJoinDao(joinTietovarantoYllapitoMuuTahoDao);
        }

        List<JoinTietovarantoAttribute> joinTietovarantoVastaanottajaRyhmaList = new ArrayList<>();
        List<String> vastaanottajaRyhmat = ((TietovarantoDto) content).getVastaanottajaryhmat();
        if (vastaanottajaRyhmat != null) {
            for (String vastaanottajaRyhma : vastaanottajaRyhmat) {
                joinTietovarantoVastaanottajaRyhmaList.add(new JoinTietovarantoVastaanottajaRyhma(content.getTunnus(), vastaanottajaRyhma));
            }
            JoinPublicDao joinTietovarantoVastaanottajaRyhmaDao = joinTietovarantoVastaanottajaRyhmaService.getDao(
                    joinTietovarantoVastaanottajaRyhmaList, JoinTietovarantoVastaanottajaRyhma.class, content.getRivimuokkaajatunnus());
            saveContent.addJoinDao(joinTietovarantoVastaanottajaRyhmaDao);
        }

        List<JoinTietovarantoAttribute> joinTietovarantoTiedonohjausList = new ArrayList<>();
        List<String> tiedonohjaussuunnitelmat = ((TietovarantoDto) content).getTiedonohjaussuunnitelmat();
        if (tiedonohjaussuunnitelmat != null) {
            for (String tiedonohjaussuunnitelma : tiedonohjaussuunnitelmat) {
                joinTietovarantoTiedonohjausList.add(new JoinTietovarantoTiedonohjaus(content.getTunnus(), tiedonohjaussuunnitelma));
            }
            JoinPublicDao joinTietovarantoTiedonohjausDao = joinTietovarantoTiedonohjausService.getDao(
                    joinTietovarantoTiedonohjausList, JoinTietovarantoTiedonohjaus.class, content.getRivimuokkaajatunnus());
            saveContent.addJoinDao(joinTietovarantoTiedonohjausDao);
        }

        return saveContent;
    }

    private ContentDto addLinksAndConvert(Haettava haettava) {
        if (haettava != null) {
            Integer tietovarantoId = haettava.getTunnus();
            List<Integer> toimintaprosessiIds = joinToimintaprosessiTietovarantoService.getToimintaprosessiIdsOfTietovaranto(tietovarantoId);
            List<String> yhteisrekisterinpitajat = joinTietovarantoYhteisrekisterinpitajaService.getTietovarantoAttributeList(tietovarantoId, JoinTietovarantoYhteisrekisterinpitaja.class);
            List<String> rekisteroityjenRyhmat = joinTietovarantoRekisteroityRyhmaService.getTietovarantoAttributeList(tietovarantoId, JoinTietovarantoRekisteroityRyhma.class);
            List<String> henkilotietojenRyhmat = joinTietovarantoHenkilotietoRyhmaService.getTietovarantoAttributeList(tietovarantoId, JoinTietovarantoHenkilotietoRyhma.class);
            List<String> turvatoimenpiteet = joinTietovarantoTurvatoimenpideService.getTietovarantoAttributeList(tietovarantoId, JoinTietovarantoTurvatoimenpide.class);
            List<String> kasittelynPerusteet = joinTietovarantoKasittelynPerusteService.getTietovarantoAttributeList(tietovarantoId, JoinTietovarantoKasittelynPeruste.class);
            List<String> yllapitoMuutTahot = joinTietovarantoYllapitoMuuTahoService.getTietovarantoAttributeList(tietovarantoId, JoinTietovarantoYllapitoMuuTaho.class);
            List<String> vastaanottajaRyhmat = joinTietovarantoVastaanottajaRyhmaService.getTietovarantoAttributeList(tietovarantoId, JoinTietovarantoVastaanottajaRyhma.class);
            List<String> tiedonohjaussuunnitelmat = joinTietovarantoTiedonohjausService.getTietovarantoAttributeList(tietovarantoId, JoinTietovarantoTiedonohjaus.class);
            return converter.modelToDto(haettava, toimintaprosessiIds, yhteisrekisterinpitajat, rekisteroityjenRyhmat, henkilotietojenRyhmat,
                    turvatoimenpiteet, kasittelynPerusteet, yllapitoMuutTahot, vastaanottajaRyhmat, tiedonohjaussuunnitelmat);
        }
        return null;
    }
}
