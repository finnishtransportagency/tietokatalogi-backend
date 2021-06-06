package fi.liike.rest.Service;

import java.util.ArrayList;
import java.util.List;

import fi.liike.rest.api.Catalogue;
import fi.liike.rest.api.ContentDto;
import fi.liike.rest.api.KasiteArvoContent;

public class ResourceTool {

	public List<KasiteArvoContent> createResources(Catalogue catalogue) {

		List<ContentDto> entries = getService(catalogue).getAll();
		List<KasiteArvoContent> content = new ArrayList<KasiteArvoContent>();
		if (entries == null)
			return content;
		for (ContentDto entry : entries) {
			content.add(new KasiteArvoContent(entry.getTunnus(), getResourceName(catalogue), entry.getNimi()));
		}
		return content;
	}

	private Service getService(Catalogue catalogue) {
		switch (catalogue) {
		case JARJESTELMA:
			return new JarjestelmaService();
		case PALVELU:
			return new PalveluService();
		case LOOGINEN:
			return new LooginenService();
		case FYYSINEN:
			return new FyysinenService();
		case PAATIETORYHMA:
			return new PaatietoryhmaService();
		case TIETORYHMA:
			return new TietoryhmaService();
		case TIETOLAJI:
			return new TietolajiService();
		case TIETOVARANTO:
			return new TietovarantoService();
		case TOIMINTAPROSESSI:
			return new ToimintaprosessiService();
		case ORGANISAATIO:
			return new OrganisaatioService();
		case TERMILOMAKE_HIERARKKINEN_KASITE:
		case TERMILOMAKE_KOOSTUMUSSUHT_KASITE:
		case TERMILOMAKE_ASSOS_KASITE:
			return new TermilomakeService();
		default:
			return null;
		}
	}

	private String getResourceName(Catalogue catalogue) {
		switch (catalogue) {
		case JARJESTELMA:
			return "JARJESTELMA";
		case PALVELU:
			return "PALVELU";
		case LOOGINEN:
			return "LOOGINEN";
		case FYYSINEN:
			return "FYYSINEN";
		case PAATIETORYHMA:
			return "PAATIETORYHMA";
		case TIETORYHMA:
			return "TIETORYHMA";
		case TIETOLAJI:
			return "TIETOLAJI";
		case TIETOVARANTO:
			return "TIETOVARANTO";
		case TOIMINTAPROSESSI:
			return "TOIMINTAPROSESSI";
		case ORGANISAATIO:
			return "ORGANISAATIO";
		case TERMILOMAKE:
			return "TERMILOMAKE";
		case TERMILOMAKE_KOOSTUMUSSUHT_KASITE:
			return "koostumussuht_ylakasite";
		case TERMILOMAKE_HIERARKKINEN_KASITE:
			return "hierarkk_ylakasite";
		case TERMILOMAKE_ASSOS_KASITE:
			return "assosiatiiv_kasite";
		default:
			return null;
		}
	}

}
