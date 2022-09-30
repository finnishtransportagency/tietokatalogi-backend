package fi.liike.rest.api.Converter;

import fi.liike.rest.Model.Haettava;
import fi.liike.rest.api.ContentDto;

public interface MinimalConverter extends Converter {
    ContentDto modelToMinimalDto(Haettava modelObject);
}
