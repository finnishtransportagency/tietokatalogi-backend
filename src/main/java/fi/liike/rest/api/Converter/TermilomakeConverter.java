package fi.liike.rest.api.Converter;

import fi.liike.rest.Model.Haettava;
import fi.liike.rest.Model.Termilomake;
import fi.liike.rest.api.ContentDto;
import fi.liike.rest.api.dto.TermilomakeDto;

import java.util.List;

public class TermilomakeConverter extends BasicConverter implements Converter {

    @Override
    public Haettava dtoToDomain(ContentDto dtoContent) {
        TermilomakeDto termilomakeDto = (TermilomakeDto) dtoContent;
        Termilomake termilomake = new Termilomake();
        super.convert(termilomakeDto, termilomake);
        return termilomake;
    }

    @Override
    public ContentDto modelToDto(Haettava modelObject, Integer... parentId) {
        if (modelObject == null)
            return null;
        Termilomake termilomake = (Termilomake) modelObject;
        TermilomakeDto termilomakeDto = new TermilomakeDto();
        super.convert(termilomake, termilomakeDto);
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

}
