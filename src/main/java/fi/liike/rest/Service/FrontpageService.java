package fi.liike.rest.Service;

import fi.liike.rest.Dao.Hibernate.FrontpageDao;
import fi.liike.rest.api.dto.FrontpageConverter;
import fi.liike.rest.api.dto.FrontpageDto;

public class FrontpageService extends MainService {
    private FrontpageDao dao;
    private FrontpageConverter converter;

    public FrontpageService() {
        this.dao = new FrontpageDao();
        this.converter = new FrontpageConverter();
    }

    public void save(FrontpageDto dto) {
        dao.save(converter.dtoToDomain(dto));
        // TODO: response?
    }
}
