package fi.liike.rest.api.dto;

import fi.liike.rest.Model.Haettava;
import fi.liike.rest.Model.LooginenTietovaranto;
import fi.liike.rest.api.ContentDto;

import java.util.List;

public class LooginenTietovarantoConverter extends BasicConverter implements Converter {

	public LooginenTietovarantoConverter() {
	}

	@Override
	public Haettava dtoToDomain(ContentDto dtoContent) {
		LooginenTietovarantoDto content = (LooginenTietovarantoDto) dtoContent;
		LooginenTietovaranto model = new LooginenTietovaranto();
		model.setTunnus(content.getTunnus());
		model.setNimi(content.getNimi());
		model.setKuvaus(content.getKuvaus());
		model.setOmistaja(content.getOmistaja());
		model.setPaivitystiheys(content.getPaivitystiheys());
		model.setKayttaja(content.getKayttaja());
		model.setKuvauskayttaja(content.getKuvauskayttaja());
		model.setKasitemalli(content.getTietomalli());
		model.setRivimuokkaajatunnus(content.getRivimuokkaajatunnus());
		model.setAlue(content.getAlue());
		return model;
	}



	@Override
	public ContentDto modelToDto(Haettava modelObject, Integer... parentId) {
		if (modelObject == null)
			return null;
		LooginenTietovaranto model = (LooginenTietovaranto) modelObject;
		LooginenTietovarantoDto dtoContent = new LooginenTietovarantoDto();
		super.convert(model, dtoContent);
		dtoContent.setTietomalli(model.getKasitemalli());
		if (parentId.length > 0)
			dtoContent.setFyysinenTietovaranto(parentId[0]);
		return dtoContent;
	}

	public ContentDto modelToDto(Haettava modelObject, Integer fyysinenId, List<Integer> jarjestelmaIds) {
		if (modelObject == null)
			return null;
		LooginenTietovarantoDto dtoContent = (LooginenTietovarantoDto) modelToDto(modelObject, fyysinenId);
		dtoContent.setJarjestelmaIds(jarjestelmaIds);
		return dtoContent;
	}

}
