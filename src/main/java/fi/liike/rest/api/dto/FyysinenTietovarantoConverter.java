package fi.liike.rest.api.dto;

import fi.liike.rest.Model.FyysinenTietovaranto;
import fi.liike.rest.Model.Haettava;
import fi.liike.rest.api.ContentDto;

public class FyysinenTietovarantoConverter implements Converter {

	public FyysinenTietovarantoConverter() {
	}

	@Override
	public Haettava dtoToDomain(ContentDto dtoContent) {
		FyysinenTietovarantoDto data = (FyysinenTietovarantoDto) dtoContent;
		FyysinenTietovaranto result = new FyysinenTietovaranto();
		result.setTunnus(data.getTunnus());
		result.setNimi(data.getNimi());
		result.setKoodi(data.getKoodi());
		result.setKuvaus(data.getKuvaus());
		result.setId(data.getId());
		result.setTietokantateknologia(data.getTietokantateknologia());
		result.setPalvelutaso(data.getPalvelutaso());
		result.setKoko(data.getKoko());
		result.setTietuemaara(data.getTietuemaara());
		result.setLinkki(data.getLinkki());
		result.setMuuta(data.getMuuta());
		result.setTeknologia(data.getTeknologia());
		result.setRivimuokkaajatunnus(data.getRivimuokkaajatunnus());
		result.setOmistaja(data.getOmistaja());
		result.setSijainti(data.getSijainti());
		return result;
	}

	@Override
	public ContentDto modelToDto(Haettava modelObject, Integer... parentId) {
		if (modelObject == null)
			return null;
		FyysinenTietovaranto data = (FyysinenTietovaranto) modelObject;
		FyysinenTietovarantoDto result = new FyysinenTietovarantoDto();
		result.setTunnus(data.getTunnus());
		result.setNimi(data.getNimi());
		result.setKoodi(data.getKoodi());
		result.setKuvaus(data.getKuvaus());
		result.setId(data.getId());
		result.setTietokantateknologia(data.getTietokantateknologia());
		result.setPalvelutaso(data.getPalvelutaso());
		result.setKoko(data.getKoko());
		result.setTietuemaara(data.getTietuemaara());
		result.setLinkki(data.getLinkki());
		result.setMuuta(data.getMuuta());
		result.setTeknologia(data.getTeknologia());
		result.setOmistaja(data.getOmistaja());
		result.setSijainti(data.getSijainti());
		return result;
	}
}
