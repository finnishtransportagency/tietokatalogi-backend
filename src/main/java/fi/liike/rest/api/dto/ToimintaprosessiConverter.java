package fi.liike.rest.api.dto;

import fi.liike.rest.Model.Haettava;
import fi.liike.rest.Model.Toimintaprosessi;
import fi.liike.rest.api.ContentDto;
import fi.liike.rest.api.KasiteArvoContent;
import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationTargetException;

public class ToimintaprosessiConverter implements Converter {
    private final String VASTAAVA_ORGANISAATIO = "VASTAAVA_ORGANISAATIO";

    private final Logger LOG = LoggerFactory.getLogger(ToimintaprosessiConverter.class);

    @Override
    public Haettava dtoToDomain(ContentDto dtoContent) {
        ToimintaprosessiDto toimintaprosessiDto = (ToimintaprosessiDto) dtoContent;
        Toimintaprosessi toimintaprosessi = new Toimintaprosessi();
        convert(toimintaprosessiDto, toimintaprosessi, true);
        return toimintaprosessi;
    }

    @Override
    public ContentDto modelToDto(Haettava modelObject, Integer... parentId) {
        if (modelObject == null)
            return null;
        Toimintaprosessi toimintaprosessi = (Toimintaprosessi) modelObject;
        ToimintaprosessiDto toimintaprosessiDto = new ToimintaprosessiDto();
        convert(toimintaprosessiDto, toimintaprosessi, false);
        return toimintaprosessiDto;
    }

    public KasiteArvoContent modelToVastaavaOrganisaatioDto(Haettava modelObject) {
        if (modelObject == null)
            return null;
        Toimintaprosessi toimintaprosessi = (Toimintaprosessi) modelObject;
        if (toimintaprosessi.getVastaava_organisaatio() != null && toimintaprosessi.getVastaava_organisaatio().length() > 0) {
            return new KasiteArvoContent(null, VASTAAVA_ORGANISAATIO, toimintaprosessi.getVastaava_organisaatio());
        }
        return null;
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
