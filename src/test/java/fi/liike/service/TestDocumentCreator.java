package fi.liike.service;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import fi.liike.rest.Service.HenkiloService;
import fi.liike.rest.Service.JarjestelmaService;
import fi.liike.rest.api.dto.JarjestelmaDto;
import fi.liike.rest.util.DocumentCreator;

import java.net.URL;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

public class TestDocumentCreator {

	private JarjestelmaService service;
	private HenkiloService henkiloService;
	private DocumentCreator documentCreator;

	@Before
	public void setUp() throws Exception {
		documentCreator = new DocumentCreator();
		JarjestelmaDto jarjestelma = createNewTestJarjestelmaDto();
		service = Mockito.mock(JarjestelmaService.class);
		henkiloService = Mockito.mock(HenkiloService.class);

		Mockito.when(service.get(Mockito.anyInt())).thenReturn(jarjestelma);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testCreatePdfDocument() {
		URL url = getClass().getClassLoader().getResource("");
		assertNotNull(url);
		assertThat(documentCreator.createPdfDocument(service, henkiloService, 100,url.getPath() + "test.pdf"), is(true));
	}

	private JarjestelmaDto createNewTestJarjestelmaDto() {
		JarjestelmaDto jarjestelma = new JarjestelmaDto();
		jarjestelma.setNimi("Agfa Apogee Power Converter");
		jarjestelma.setKuvaus("Merikartoitus prosessin painetun kartan tiedostojen värimäärittely ja konvertointi määritettyyn formaattiin lopullisen tuotteen (Painettu merikartta tai -sarja) toimittajalle");
		jarjestelma.setTietolahteet("www.osoite.com/polku/polku");
		jarjestelma.setTietojen_julkisuus("Julkinen");
		jarjestelma.setJulkiset_tiedot_ryhmittain("ryhmä1");
		jarjestelma.setSalassa_pidettavat_tiedot("salassa pidettävät tiedot salassa pidettävät tiedot");
		jarjestelma.setTietoj_internet_osoite("www.osoite.com/polku/polku");
		return jarjestelma;
	}

}
