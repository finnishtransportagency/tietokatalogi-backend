package fi.liike.rest.api.Converter;

import fi.liike.rest.Model.Haettava;
import fi.liike.rest.Model.Tietovaranto;
import fi.liike.rest.api.ContentDto;
import fi.liike.rest.api.KasiteArvoContent;
import fi.liike.rest.api.dto.TietovarantoDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class TietovarantoConverter extends BasicConverter implements Converter {
    private final String REKISTERINPITAJA = "REKISTERINPITAJA";

    private final Logger LOG = LoggerFactory.getLogger(TietovarantoConverter.class);

    @Override
    public Haettava dtoToDomain(ContentDto dtoContent) {
        TietovarantoDto tietovarantoDto = (TietovarantoDto) dtoContent;
        Tietovaranto tietovaranto = new Tietovaranto();
        super.convert(tietovarantoDto, tietovaranto);
        return tietovaranto;
    }

    @Override
    public ContentDto modelToDto(Haettava modelObject, Integer... parentId) {
        if (modelObject == null)
            return null;
        Tietovaranto tietovaranto = (Tietovaranto) modelObject;
        TietovarantoDto tietovarantoDto = new TietovarantoDto();
        super.convert(tietovaranto, tietovarantoDto);
        return tietovarantoDto;
    }

    public ContentDto modelToDto(Haettava modelObject, List<Integer> toimintaprosessiIds, List<String> yhteisrekisterinpitajat,
                                 List<String> rekisteroityjenryhmat, List<String> henkilotietojenryhmat,
                                 List<String> turvatoimenpiteet, List<String> kasittelynPerusteet,
                                 List<String> yllapitoMuutTahot, List<String> vastaanottajaRyhmat,
                                 List<String> tiedonohjaussuunnitelmat) {
        if (modelObject == null)
            return null;
        TietovarantoDto dtoContent = (TietovarantoDto) modelToDto(modelObject);
        dtoContent.setToimintaprosessiIds(toimintaprosessiIds);
        dtoContent.setYhteisrekisterinpitajat(yhteisrekisterinpitajat);
        dtoContent.setRekisteroityjenryhmat(rekisteroityjenryhmat);
        dtoContent.setHenkilotietojenryhmat(henkilotietojenryhmat);
        dtoContent.setTurvatoimenpiteet(turvatoimenpiteet);
        dtoContent.setKasittelyn_perusteet(kasittelynPerusteet);
        dtoContent.setYllapito_muut_tahot(yllapitoMuutTahot);
        dtoContent.setVastaanottajaryhmat(vastaanottajaRyhmat);
        dtoContent.setTiedonohjaussuunnitelmat(tiedonohjaussuunnitelmat);
        return dtoContent;
    }

    public KasiteArvoContent modelToRekisterinpitajaDto(Haettava modelObject) {
        if (modelObject == null)
            return null;
        Tietovaranto tietovaranto = (Tietovaranto) modelObject;
        if (tietovaranto.getRekisterinpitaja() != null && tietovaranto.getRekisterinpitaja().length() > 0) {
            return new KasiteArvoContent(null, REKISTERINPITAJA, tietovaranto.getRekisterinpitaja());
        }
        return null;
    }

}
