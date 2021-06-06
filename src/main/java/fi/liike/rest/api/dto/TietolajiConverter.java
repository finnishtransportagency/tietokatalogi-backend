package fi.liike.rest.api.dto;

import fi.liike.rest.Model.Haettava;
import fi.liike.rest.Model.Tietolaji;
import fi.liike.rest.api.ContentDto;

public class TietolajiConverter implements Converter {

	public TietolajiConverter() {
	}

	@Override
	public Haettava dtoToDomain(ContentDto dtoContent) {
		TietolajiDto data = (TietolajiDto) dtoContent;
		Tietolaji result = new Tietolaji();
		result.setTunnus(data.getTunnus());
		result.setLooginenTietovarantoTunnus(data.getLooginenTietovarantoTunnus());
		result.setTietoryhmatunnus(data.getTietoryhmatunnus());
		result.setNimi(data.getNimi());
		result.setKoodi(data.getKoodi());
		result.setKuvaus(data.getKuvaus());
		result.setOmistaja(data.getOmistaja());
		result.setLahde(data.getLahde());
		result.setTila(data.getTila());
		result.setSynonyymi(data.getSynonyymi());
		result.setTietosuojataso(data.getTietosuojataso());
		result.setYlempitaso(data.getYlempitaso());
		result.setRivitunnus(data.getRivitunnus());
		result.setRivitila(data.getRivitila());
		result.setRivimuokkaajatunnus(data.getRivimuokkaajatunnus());
		return result;
	}

	@Override
	public ContentDto modelToDto(Haettava modelObject, Integer... parentId) {
		if (modelObject == null)
			return null;
		Tietolaji data = (Tietolaji) modelObject;
		TietolajiDto result = new TietolajiDto();
		result.setTunnus(data.getTunnus());
		if (parentId.length > 0 && parentId[0] != null)
			result.setLooginenTietovarantoTunnus(parentId[0]);
		if (parentId.length > 1 && parentId[1] != null)
			result.setTietoryhmatunnus(parentId[1]);
		result.setNimi(data.getNimi());
		result.setKoodi(data.getKoodi());
		result.setKuvaus(data.getKuvaus());
		result.setOmistaja(data.getOmistaja());
		result.setLahde(data.getLahde());
		result.setTila(data.getTila());
		result.setSynonyymi(data.getSynonyymi());
		result.setTietosuojataso(data.getTietosuojataso());
		result.setYlempitaso(data.getYlempitaso());
		result.setRivitunnus(data.getRivitunnus());
		result.setRivitila(data.getRivitila());
		return result;
	}
}
