package fi.liike.rest.api.Converter;

import fi.liike.rest.Model.Frontpage;
import fi.liike.rest.api.dto.FrontpageDto;

/**
 * Converter for the frontpage dto and model.
 * Does not implement `Converter` since Haettava and ContentDto
 * are not used with frontpage.
 */
public class FrontpageConverter extends BasicConverter {

    public Frontpage dtoToDomain(FrontpageDto dto) {
        Frontpage model = new Frontpage();
        super.convert(dto, model);
        return model;
    }

    public FrontpageDto modelToDto(Frontpage model) {
        FrontpageDto dto = new FrontpageDto();
        super.convert(model, dto);
        return dto;
    }
}
