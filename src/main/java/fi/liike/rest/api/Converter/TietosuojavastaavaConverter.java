package fi.liike.rest.api.Converter;

import fi.liike.rest.Model.Haettava;
import fi.liike.rest.Model.Tietosuojavastaava;
import fi.liike.rest.api.ContentDto;
import fi.liike.rest.api.dto.TietosuojavastaavaDto;

public class TietosuojavastaavaConverter extends BasicConverter implements Converter {

    @Override
    public Haettava dtoToDomain(ContentDto dtoContent) {
        TietosuojavastaavaDto tietosuojavastaavaDto = (TietosuojavastaavaDto) dtoContent;
        Tietosuojavastaava tietosuojavastaava = new Tietosuojavastaava();
        super.convert(tietosuojavastaavaDto, tietosuojavastaava);
        return tietosuojavastaava;
    }

    @Override
    public ContentDto modelToDto(Haettava modelObject, Integer... parentId) {
        if (modelObject == null)
            return null;
        Tietosuojavastaava tietosuojavastaava = (Tietosuojavastaava) modelObject;
        TietosuojavastaavaDto tietosuojavastaavaDto = new TietosuojavastaavaDto();
        super.convert(tietosuojavastaava, tietosuojavastaavaDto);
        return tietosuojavastaavaDto;
    }
}
