package fi.liike.rest.Service;

import fi.liike.rest.Dao.Hibernate.FrontpageDao;
import fi.liike.rest.api.dto.FrontpageConverter;
import fi.liike.rest.api.dto.FrontpageDto;

public class FrontpageService extends MainService {
    private final FrontpageDao dao;
    private final FrontpageConverter converter;

    public FrontpageService(FrontpageDao dao) {
        this.dao = dao;
        this.converter = new FrontpageConverter();
    }

    public void save(FrontpageDto dto) {
        dao.save(converter.dtoToDomain(dto));
        // TODO: response?
    }
}
