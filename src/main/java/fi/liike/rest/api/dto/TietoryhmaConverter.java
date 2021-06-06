package fi.liike.rest.api.dto;

import fi.liike.rest.Model.Haettava;
import fi.liike.rest.Model.Tietoryhma;
import fi.liike.rest.api.ContentDto;

public class TietoryhmaConverter implements Converter {

	public TietoryhmaConverter() {
	}

	@Override
	public Haettava dtoToDomain(ContentDto dtoContent) {
		TietoryhmaDto data = (TietoryhmaDto) dtoContent;
		Tietoryhma result = new Tietoryhma();
		result.setLahde(data.getLahde());
		result.setOmistaja(data.getOmistaja());
		result.setTila(data.getTila());
		result.setSynonyymi(data.getSynonyymi());
		result.setTietosuojataso(data.getTietosuojataso());
		result.setPaatietoryhma(data.getPaatietoryhma());
		result.setTunnus(data.getTunnus());
		result.setNimi(data.getNimi());
		result.setKoodi(data.getKoodi());
		result.setKuvaus(data.getKuvaus());
		result.setRivimuokkaajatunnus(data.getRivimuokkaajatunnus());
		result.setTietovaranto(data.getTietovaranto());
		return result;
	}

	@Override
	public ContentDto modelToDto(Haettava modelObject, Integer... parentId) {
		if (modelObject == null)
			return null;
		Tietoryhma data = (Tietoryhma) modelObject;
		TietoryhmaDto result = new TietoryhmaDto();
		result.setLahde(data.getLahde());
		result.setOmistaja(data.getOmistaja());
		result.setTila(data.getTila());
		result.setSynonyymi(data.getSynonyymi());
		result.setTietosuojataso(data.getTietosuojataso());
		result.setPaatietoryhma(data.getPaatietoryhma());
		result.setTunnus(data.getTunnus());
		result.setNimi(data.getNimi());
		result.setKoodi(data.getKoodi());
		result.setKuvaus(data.getKuvaus());
		result.setTietovaranto(data.getTietovaranto());
		return result;
	}
}
