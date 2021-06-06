package fi.liike.rest.util;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;

import fi.liike.rest.Model.JarjestelmaHenkiloRooli;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import fi.liike.rest.Service.HenkiloService;
import fi.liike.rest.Service.JarjestelmaService;
import fi.liike.rest.api.dto.FetchHenkiloRooliDto;
import fi.liike.rest.api.dto.JarjestelmaDto;
import rst.pdfbox.layout.elements.Document;
import rst.pdfbox.layout.elements.ImageElement;
import rst.pdfbox.layout.elements.Paragraph;
import rst.pdfbox.layout.elements.render.VerticalLayoutHint;
import rst.pdfbox.layout.text.Alignment;
import rst.pdfbox.layout.text.Constants;
import rst.pdfbox.layout.text.Position;

import javax.imageio.ImageIO;
import javax.servlet.ServletContext;

import static java.lang.String.format;

public class DocumentCreator {
	private static final String logoName = "vayla_v_cmyk.png";
	private final Logger LOG = LoggerFactory.getLogger(DocumentCreator.class);

	public boolean createPdfDocument(JarjestelmaService jarjestelmaService, HenkiloService henkiloService, int id, String path) {
		return create(jarjestelmaService, henkiloService, id, path, null);
	}

	public ByteArrayOutputStream createOutputStream(JarjestelmaService jarjestelmaService, HenkiloService henkiloService, String name, int id,
													ServletContext servletContext) {
		ByteArrayOutputStream output = new ByteArrayOutputStream();
		boolean result = create(jarjestelmaService, henkiloService, id, output, servletContext);
		if (result)
			return output;
		else
			return null;
	}

	private boolean create(JarjestelmaService jarjestelmaService, HenkiloService henkiloService, int id, Object saveSource,
						   ServletContext servletContext) {
		Set<FetchHenkiloRooliDto> fetchHenkiloRooliDtoList = henkiloService.getFetchHenkiloRooliListBySystemId(id,
				false, JarjestelmaHenkiloRooli.class, "JARJESTELMA_ID");
		
		JarjestelmaDto jarjestelma = (JarjestelmaDto) jarjestelmaService.get(id);
		if (jarjestelma == null)
			return false;
		Document document = new Document(Constants.A4, 60, 60, 30, 60);

		try {
			InputStream url = null;

			LOG.info("Started to create the document.");
			try{
				url = servletContext.getResourceAsStream(logoName);
			}catch(Exception e){
				LOG.error("Couldn't load " + logoName + ". Url was not found.", e);
			}
			if (url != null) {
				ImageElement image;
				image = new ImageElement(ImageIO.read(url));
				image.setWidth(100);
				image.setHeight(100);
				image.setAbsolutePosition(new Position(10, 840));
				document.add(image);
			}
			
			setHeader(document, "Väylävirasto     " + new SimpleDateFormat("dd.MM.yyyy").format(new Date()));
			setHeader(document, "Julkisuuslain mukainen tietojärjestelmäseloste", 10);

			document.add(new Paragraph(), new VerticalLayoutHint(Alignment.Left, 0, 0,
					50, 0, false));

			setTextH2(document, "1. Tietojärjestelmän nimi");

			String nimi = (jarjestelma.getNimi() == null) ? "" :
					jarjestelma.getNimi();
			
			String jarjestelmaOmistaja = "";
			String jarjestelmaVastaava = "";
			for (FetchHenkiloRooliDto henkiloRooliDto : fetchHenkiloRooliDtoList) {
				if(henkiloRooliDto.getRooli().getTunnus() == 1) {
					jarjestelmaOmistaja = henkiloRooliDto.getHenkilo().getSukunimi() + " " + henkiloRooliDto.getHenkilo().getEtunimi();
				}else if(henkiloRooliDto.getRooli().getTunnus() == 2) {
					jarjestelmaVastaava = henkiloRooliDto.getHenkilo().getSukunimi() + " " + henkiloRooliDto.getHenkilo().getEtunimi();
				}
			}
			
			String tietoLahteet = (jarjestelma.getTietolahteet() == null) ? "" :
				jarjestelma.getTietolahteet();
			String jarjestelmaKuvaus = (jarjestelma.getKuvaus() == null) ? "" :
					jarjestelma.getKuvaus();
			String tietojenJulkisuus = (jarjestelma.getTietojen_julkisuus() == null) ? "" :
					jarjestelma.getTietojen_julkisuus();
			String tiedotRyhmittain = (jarjestelma.getJulkiset_tiedot_ryhmittain() == null) ? "" :
					jarjestelma.getJulkiset_tiedot_ryhmittain();
			String tiedotSalassa = (jarjestelma.getSalassa_pidettavat_tiedot() == null) ? "" :
					jarjestelma.getSalassa_pidettavat_tiedot();
			String tietojenInternetOsoite = (jarjestelma.getTietoj_internet_osoite() == null) ? "" :
					jarjestelma.getTietoj_internet_osoite();

			setText(document, nimi, 4, 18);

			setTextH2(document, "2. Tietojärjestelmän vastuutaho");

			setText(document, jarjestelmaOmistaja, 4, 0);
			setText(document, "Väylävirasto");
			setText(document, "Opastinsilta 12 A");
			setText(document, "00520 Helsinki");
			setTextH2(document, "3. Tietojärjestelmän vastuuyksikkö ja -henkilö", 18, 4);

			setText(document, jarjestelmaVastaava, 0, 18);

			setTextH2(document, "4. Tietojärjestelmän käyttötarkoitus");
			setText(document, jarjestelmaKuvaus, 4, 18);

			setTextH2(document, "5. Tietolähteet");
			setText(document, tietoLahteet, 4, 18);

			setTextH2(document, "6. Tietojen julkisuus/salassa pidettävyys");
			setText(document, tietojenJulkisuus, 4, 18);

			setTextH2(document, "7. Julkiset tiedot");

			setText(document, tiedotRyhmittain, 4, 18);

			setTextH2(document, "8. Salassa pidettävät tiedot");
			setText(document, tiedotSalassa, 4, 18);

			setTextH2(document, "9. Tietojärjestelmän julkinen osoite");
			setText(document, tietojenInternetOsoite, 4, 18);
				
			if (saveSource instanceof ByteArrayOutputStream) {
				document.save((ByteArrayOutputStream) saveSource);
			}

			if (saveSource instanceof String) {
				document.save(new FileOutputStream((String) saveSource));
			}
		} catch (IOException e) {
			LOG.error(format("Could not generate PDF of JarjestelmaDto with ID %s", id));
		}
		LOG.info("Document creating finished.");
		return true;
	}

	private static void setHeader(Document document, String string) throws IOException {
		setHeader(document, string, 0);
	}

	private static void setHeader(Document document, String string, float marginTop) throws IOException {
		Paragraph para = new Paragraph();
		para.addText(string, 14, PDType1Font.HELVETICA);
		document.add(para, new VerticalLayoutHint(Alignment.Left, 50, 20,
				marginTop, 0));
	}

	private static void setTextH2(Document document, String string, float marginTop, float marginBottom) throws IOException {
		Paragraph para = new Paragraph();
		para.addText(string, 12, PDType1Font.HELVETICA);
		document.add(para, new VerticalLayoutHint(Alignment.Left, 10, 20,
				marginTop, marginBottom));
	}

	private static void setTextH2(Document document, String string) throws IOException {
		setTextH2(document, string, 0, 0);
	}


	private static void setText(Document document, String string) throws IOException {
		setText(document, string,0, 0);
	}

	private static void setText(Document document, String string, float marginTop, float marginBottom) throws IOException {
		Paragraph para = new Paragraph();
		para.addText(string, 10, PDType1Font.HELVETICA);
		document.add(para, new VerticalLayoutHint(Alignment.Left, 25, 20,
				marginTop, marginBottom));
	}


}
