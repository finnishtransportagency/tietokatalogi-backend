package fi.liike.rest.api.Converter;

import fi.liike.rest.Model.Haettava;
import fi.liike.rest.Model.Organisaatio;
import fi.liike.rest.api.ContentDto;
import fi.liike.rest.api.dto.OrganisaatioDto;

public class OrganisaatioConverter extends BasicConverter implements Converter {

    @Override
    public Haettava dtoToDomain(ContentDto dtoContent) {
        OrganisaatioDto organisaatioDto = (OrganisaatioDto) dtoContent;
        Organisaatio organisaatio = new Organisaatio();
        super.convert(organisaatioDto, organisaatio);
        return organisaatio;
    }

    @Override
    public ContentDto modelToDto(Haettava modelObject, Integer... parentId) {
        if (modelObject == null)
            return null;
        Organisaatio organisaatio = (Organisaatio) modelObject;
        OrganisaatioDto organisaatioDto = new OrganisaatioDto();
        super.convert(organisaatio, organisaatioDto);
        return organisaatioDto;
    }
}
