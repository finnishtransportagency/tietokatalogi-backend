package fi.liike.rest.api.Converter;

import fi.liike.rest.Model.Haettava;
import fi.liike.rest.Model.Tietosuojavastaava;
import fi.liike.rest.api.ContentDto;
import fi.liike.rest.api.dto.TietosuojavastaavaDto;
import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationTargetException;

public class TietosuojavastaavaConverter implements Converter {
    private final Logger LOG = LoggerFactory.getLogger(TietosuojavastaavaConverter.class);

    @Override
    public Haettava dtoToDomain(ContentDto dtoContent) {
        TietosuojavastaavaDto tietosuojavastaavaDto = (TietosuojavastaavaDto) dtoContent;
        Tietosuojavastaava tietosuojavastaava = new Tietosuojavastaava();
        convert(tietosuojavastaavaDto, tietosuojavastaava, true);
        return tietosuojavastaava;
    }

    @Override
    public ContentDto modelToDto(Haettava modelObject, Integer... parentId) {
        if (modelObject == null)
            return null;
        Tietosuojavastaava tietosuojavastaava = (Tietosuojavastaava) modelObject;
        TietosuojavastaavaDto tietosuojavastaavaDto = new TietosuojavastaavaDto();
        convert(tietosuojavastaavaDto, tietosuojavastaava, false);
        return tietosuojavastaavaDto;
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
