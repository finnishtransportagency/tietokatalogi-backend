package fi.liike.rest.api.dto;

import fi.liike.rest.Model.Haettava;
import fi.liike.rest.Model.Paatietoryhma;
import fi.liike.rest.api.ContentDto;

public class PaatietoryhmaConverter extends BasicConverter implements Converter {

	public PaatietoryhmaConverter() {
	}

	@Override
	public Haettava dtoToDomain(ContentDto dtoContent) {
		PaatietoryhmaDto data = (PaatietoryhmaDto) dtoContent;
		Paatietoryhma result = new Paatietoryhma();
		result.setOmistaja(data.getOmistaja());
		result.setTila(data.getTila());
		result.setSynonyymi(data.getSynonyymi());
		result.setTietosuojataso(data.getTietosuojataso());
		result.setJulkaisutieto(data.getJulkaisutieto());
		result.setJulkaisuhuomio(data.getJulkaisuhuomio());
		result.setTunnus(data.getTunnus());
		result.setNimi(data.getNimi());
		result.setKoodi(data.getKoodi());
		result.setKuvaus(data.getKuvaus());
		result.setLahde(data.getLahde());
		result.setRivimuokkaajatunnus(data.getRivimuokkaajatunnus());
		return result;
	}

	@Override
	public ContentDto modelToDto(Haettava modelObject, Integer... parentId) {
		if (modelObject == null)
			return null;
		Paatietoryhma data = (Paatietoryhma) modelObject;
		PaatietoryhmaDto result = new PaatietoryhmaDto();
		super.convert(data, result);
		return result;
	}
}
