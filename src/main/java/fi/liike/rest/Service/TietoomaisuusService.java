package fi.liike.rest.Service;

import fi.liike.rest.Dao.Hibernate.TietoomaisuusDaoImpl;
import fi.liike.rest.Model.Haettava;
import fi.liike.rest.Model.Tietoomaisuus;
import fi.liike.rest.Model.TietoomaisuusFetch;
import fi.liike.rest.api.*;
import fi.liike.rest.api.Converter.TietoomaisuusConverter;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TietoomaisuusService extends MainService implements Service {
    private TietoomaisuusConverter converter;
    private TietoomaisuusDaoImpl dao;
    private final Logger LOG = LoggerFactory.getLogger(TietoomaisuusService.class);


    public TietoomaisuusService() {
        this.converter = new TietoomaisuusConverter();
        this.dao = new TietoomaisuusDaoImpl();
    }

    @Override
    public DtoResults getFiltered(SearchContent searchContent) {
        ModelResults filtered = dao.getFiltered(searchContent);
        ArrayList<ContentDto> dtoResults = new ArrayList<ContentDto>();
        List<Haettava> modelResults = filtered.getHaettavat();
        for (Haettava haettava : modelResults) {
            dtoResults.add(converter.fetchModelToDto((TietoomaisuusFetch) haettava));
        }
        return new DtoResults(dtoResults, filtered.getTotalCount());
    }

    @Override
    public List<ContentDto> getAll() {
        return null;
    }

    @Override
    public ContentDto save(ContentDto content) throws SQLException {
        DaoContent daoContent = new DaoContent();
        Tietoomaisuus tietoomaisuus = (Tietoomaisuus) converter.dtoToDomain(content);
        daoContent.setHaettava(tietoomaisuus);
        Tietoomaisuus savedTietoomaisuus = (Tietoomaisuus) dao.save(null, daoContent);
        return converter.modelToDto(savedTietoomaisuus);
    }

    @Override
    public ContentDto save(Session session, ContentDto content) throws SQLException, IOException {
        return null;
    }

    @Override
    public ContentDto update(ContentDto content) throws IOException, SQLException {
        DaoContent daoContent = new DaoContent();
        Tietoomaisuus tietoomaisuus = (Tietoomaisuus) converter.dtoToDomain(content);
        daoContent.setHaettava(tietoomaisuus);
        Tietoomaisuus updatedTietoomaisuus = (Tietoomaisuus) dao.update(null, daoContent);
        if (updatedTietoomaisuus == null) return null;
        return converter.modelToDto(updatedTietoomaisuus);
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
        } catch (SQLException e) {
            LOG.error("Unable to delete content. Error message: " + e.getMessage());
            return null;
        }
    }

    @Override
    public ContentDto get(int id) {
        return super.get(converter, dao, id);
    }

    @Override
    public List<KasiteArvoContent> getResources() {
        return null;
    }

    public List<KasiteArvoContent> getScoredResources() {
        return dao.getScoredResources();
    }
}
