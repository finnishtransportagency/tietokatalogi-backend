package fi.liike.rest.Service;

import fi.liike.rest.Dao.Hibernate.OrganisaatioDaoImpl;
import fi.liike.rest.Dao.MainDao;
import fi.liike.rest.api.ContentDto;
import fi.liike.rest.api.DaoContent;
import fi.liike.rest.api.DtoResults;
import fi.liike.rest.api.KasiteArvoContent;
import fi.liike.rest.api.SearchContent;
import fi.liike.rest.api.Converter.OrganisaatioConverter;
import fi.liike.rest.auth.InvalidTietokatalogiDataException;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class OrganisaatioService extends MainService implements Service {
    private MainDao dao;
    private OrganisaatioConverter converter;

    private final Logger LOG = LoggerFactory.getLogger(OrganisaatioService.class);

    public OrganisaatioService() {
        this.dao = new OrganisaatioDaoImpl();
        this.converter = new OrganisaatioConverter();
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
        return dao.getResources();
    }
}
