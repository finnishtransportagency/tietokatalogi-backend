package fi.liike.rest.api.Converter;

import fi.liike.rest.Model.Haettava;
import fi.liike.rest.Model.Organisaatio;
import fi.liike.rest.api.ContentDto;
import fi.liike.rest.api.dto.OrganisaatioDto;
import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationTargetException;

public class OrganisaatioConverter implements Converter {
    private final Logger LOG = LoggerFactory.getLogger(OrganisaatioConverter.class);

    @Override
    public Haettava dtoToDomain(ContentDto dtoContent) {
        OrganisaatioDto organisaatioDto = (OrganisaatioDto) dtoContent;
        Organisaatio organisaatio = new Organisaatio();
        convert(organisaatioDto, organisaatio, true);
        return organisaatio;
    }

    @Override
    public ContentDto modelToDto(Haettava modelObject, Integer... parentId) {
        if (modelObject == null)
            return null;
        Organisaatio organisaatio = (Organisaatio) modelObject;
        OrganisaatioDto organisaatioDto = new OrganisaatioDto();
        convert(organisaatioDto, organisaatio, false);
        return organisaatioDto;
    }

    private <T, U> void convert(T dto, U model, Boolean toModel) {
        try {
            if (toModel)
                BeanUtils.copyProperties(model, dto);
            else
                BeanUtils.copyProperties(dto, model);

        } catch (IllegalAccessException | InvocationTargetException e) {
            LOG.error("Could not copy properties to BeanUtils! Error Message: " + e.getMessage());
        }
    }
}
