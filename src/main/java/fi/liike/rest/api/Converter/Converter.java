package fi.liike.rest.api.Converter;

import fi.liike.rest.Model.Haettava;
import fi.liike.rest.api.ContentDto;

public interface Converter {

	Haettava dtoToDomain(ContentDto dtoContent);

	ContentDto modelToDto(Haettava modelObject, Integer... parentId);

}
