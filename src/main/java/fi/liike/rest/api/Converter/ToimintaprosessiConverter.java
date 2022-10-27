package fi.liike.rest.api.Converter;

import fi.liike.rest.Model.Haettava;
import fi.liike.rest.Model.Toimintaprosessi;
import fi.liike.rest.api.ContentDto;
import fi.liike.rest.api.KasiteArvoContent;
import fi.liike.rest.api.dto.ToimintaprosessiDto;

public class ToimintaprosessiConverter extends BasicConverter implements Converter {
    private final String VASTAAVA_ORGANISAATIO = "VASTAAVA_ORGANISAATIO";

    @Override
    public Haettava dtoToDomain(ContentDto dtoContent) {
        ToimintaprosessiDto toimintaprosessiDto = (ToimintaprosessiDto) dtoContent;
        Toimintaprosessi toimintaprosessi = new Toimintaprosessi();
        super.convert(toimintaprosessiDto, toimintaprosessi);
        return toimintaprosessi;
    }

    @Override
    public ContentDto modelToDto(Haettava modelObject, Integer... parentId) {
        if (modelObject == null)
            return null;
        Toimintaprosessi toimintaprosessi = (Toimintaprosessi) modelObject;
        ToimintaprosessiDto toimintaprosessiDto = new ToimintaprosessiDto();
        super.convert(toimintaprosessi, toimintaprosessiDto);
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

}
