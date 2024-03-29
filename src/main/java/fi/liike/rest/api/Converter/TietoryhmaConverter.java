package fi.liike.rest.api.Converter;

import fi.liike.rest.Model.Haettava;
import fi.liike.rest.Model.Tietoryhma;
import fi.liike.rest.api.ContentDto;
import fi.liike.rest.api.dto.TietoryhmaDto;

public class TietoryhmaConverter extends BasicConverter implements Converter {

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
		super.convert(data, result);
		return result;
	}
}
