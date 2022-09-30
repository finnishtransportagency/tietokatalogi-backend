package fi.liike.rest.Service;

import fi.liike.rest.Dao.Hibernate.FrontpageDao;
import fi.liike.rest.Model.Frontpage;
import fi.liike.rest.api.Converter.FrontpageConverter;
import fi.liike.rest.api.dto.FrontpageDto;

import java.util.Optional;

public class FrontpageService extends MainService {
    private FrontpageDao dao;
    private FrontpageConverter converter;

    public FrontpageService() {
        this.dao = new FrontpageDao();
        this.converter = new FrontpageConverter();
    }

    public void save(FrontpageDto dto) {
        dao.save(converter.dtoToDomain(dto));
    }

    public Optional<FrontpageDto> get() {
        Optional<Frontpage> frontpage = dao.getSingle();
        return frontpage.map(converter::modelToDto);
    }
}
