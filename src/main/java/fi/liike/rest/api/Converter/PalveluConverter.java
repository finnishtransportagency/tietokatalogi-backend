package fi.liike.rest.api.Converter;

import fi.liike.rest.Model.Haettava;
import fi.liike.rest.Model.Palvelu;
import fi.liike.rest.api.ContentDto;
import fi.liike.rest.api.dto.PalveluDto;

public class PalveluConverter implements Converter {

	public PalveluConverter() {
	}

	@Override
	public Haettava dtoToDomain(ContentDto dtoContent) {
		PalveluDto data = (PalveluDto) dtoContent;
		Palvelu result = new Palvelu();
		result.setTunnus(data.getTunnus());
		result.setYlataso(data.getYlataso());
		result.setOtsikko(data.getOtsikko());
		result.setNimi(data.getNimi());
		result.setKuvaus(data.getKuvaus());
		result.setVastuuhenkilo(data.getVastuuhenkilo());
		result.setAsiakkaat(data.getAsiakkaat());
		result.setSaatavuus(data.getSaatavuus());
		result.setVasteajat(data.getVasteajat());
		result.setOhje_pt(data.getOhje_pt());
		result.setOhjeistus(data.getOhjeistus());
		result.setOhjesaannot(data.getOhjesaannot());
		result.setRivimuokkaajatunnus(data.getRivimuokkaajatunnus());
		return result;
	}

	@Override
	public ContentDto modelToDto(Haettava modelObject, Integer... parentId) {
		if (modelObject == null)
			return null;
		Palvelu data = (Palvelu) modelObject;
		PalveluDto result = new PalveluDto();
		result.setTunnus(data.getTunnus());
		result.setYlataso(data.getYlataso());
		result.setOtsikko(data.getOtsikko());
		result.setNimi(data.getNimi());
		result.setKuvaus(data.getKuvaus());
		result.setVastuuhenkilo(data.getVastuuhenkilo());
		result.setAsiakkaat(data.getAsiakkaat());
		result.setSaatavuus(data.getSaatavuus());
		result.setVasteajat(data.getVasteajat());
		result.setOhje_pt(data.getOhje_pt());
		result.setOhjeistus(data.getOhjeistus());
		result.setOhjesaannot(data.getOhjesaannot());
		return result;
	}
}
