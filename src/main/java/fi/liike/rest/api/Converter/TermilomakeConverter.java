package fi.liike.rest.api.Converter;

import fi.liike.rest.Model.Haettava;
import fi.liike.rest.Model.Termilomake;
import fi.liike.rest.Model.TermilomakeJoinHierarkkinenKasite;
import fi.liike.rest.api.ContentDto;
import fi.liike.rest.api.dto.TermilomakeDto;
import fi.liike.rest.api.dto.TermilomakeHierarkkinenKasiteDto;
import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

public class TermilomakeConverter implements Converter {
    private final Logger LOG = LoggerFactory.getLogger(TermilomakeConverter.class);

    @Override
    public Haettava dtoToDomain(ContentDto dtoContent) {
        TermilomakeDto termilomakeDto = (TermilomakeDto) dtoContent;
        Termilomake termilomake = new Termilomake();
        convert(termilomakeDto, termilomake, true);
        return termilomake;
    }

    @Override
    public ContentDto modelToDto(Haettava modelObject, Integer... parentId) {
        if (modelObject == null)
            return null;
        Termilomake termilomake = (Termilomake) modelObject;
        TermilomakeDto termilomakeDto = new TermilomakeDto();
        convert(termilomakeDto, termilomake, false);
        return termilomakeDto;
    }

    public ContentDto modelToDto(Haettava modelObject,
                                 List<Integer> hierarkIds,
                                 List<Integer> assosIds,
                                 List<Integer> koostIds,
                                 List<String> huomautusList) {
        if (modelObject == null) return null;

        TermilomakeDto dtoContent = (TermilomakeDto) modelToDto(modelObject);
        dtoContent.setHierarkk_ylakasite(hierarkIds);
        dtoContent.setKoostumussuht_ylakasite(koostIds);
        dtoContent.setAssosiatiiv_kasite(assosIds);
        dtoContent.sethuomautusList(huomautusList);
        return dtoContent;
    }

    // TODO: same as in tietovaranto converter! Refactor to a shared location
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

    public TermilomakeJoinHierarkkinenKasite joinHierarkkinenDtoToDomain(TermilomakeHierarkkinenKasiteDto dto) {
        TermilomakeJoinHierarkkinenKasite ylaKasiteModel = new TermilomakeJoinHierarkkinenKasite();
        ylaKasiteModel.setRivitunnus(dto.getId());
        ylaKasiteModel.setChildNode(dto.getHierarkkinenAlakasite());
        ylaKasiteModel.setParentNode(dto.getHierarkkinenYlakasite());
        ylaKasiteModel.setRivitila("A");
        return ylaKasiteModel;
    }
}
