package fi.liike.service;

import fi.liike.rest.MainRestTester;
import fi.liike.rest.Service.*;
import fi.liike.rest.api.Catalogue;
import fi.liike.rest.api.ContentDto;
import fi.liike.rest.api.dto.*;
import org.hibernate.Session;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static java.lang.String.format;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.mockito.internal.verification.VerificationModeFactory.times;

public class TietoarkkitehtuuriServiceTest {
    private MainRestTester mainTester;
    private TietoarkkitehtuuriService mockedTietoarkkitehtuuriService;
    private TietoarkkitehtuuriService tietoarkkitehtuuriService;
    private FyysinenService fyysinenService;
    private LooginenService looginenService;
    private TietolajiService tietolajiService;
    private TietoryhmaService tietoryhmaService;
    private PaatietoryhmaService paatietoryhmaService;

    @Before
    public void setUp() {
        this.mainTester = new MainRestTester(Catalogue.TIETOARKKITEHTUURI);
        this.fyysinenService = mock(FyysinenService.class);
        this.looginenService = mock(LooginenService.class);
        this.tietolajiService = mock(TietolajiService.class);
        this.tietoryhmaService = mock(TietoryhmaService.class);
        this.paatietoryhmaService = mock(PaatietoryhmaService.class);
        this.mockedTietoarkkitehtuuriService = new TietoarkkitehtuuriService(fyysinenService, looginenService,
                tietolajiService, tietoryhmaService, paatietoryhmaService);
        this.tietoarkkitehtuuriService = new TietoarkkitehtuuriService();
    }

    @After
    public void tearDown() {
        mainTester.clear();
    }

    private void mockContentDto(ContentDto passed, ContentDto expected, Integer expectedTunnus, Service service) throws IOException, SQLException {
        if (passed.getTunnus() == null) {
            expected.setTunnus(expectedTunnus);
            when(service.save(any(Session.class), eq(passed))).thenReturn(expected);
        } else {
            expected.setTunnus(expectedTunnus);
            when(service.update(any(Session.class), eq(passed))).thenReturn(expected);
        }
    }

    private TietoarkkitehtuuriDto mockTietoarkkitehtuuri(List<Integer> subCatCountList, TietoarkkitehtuuriDto tietoarkkitehtuuri) throws SQLException, IOException {
        TietoarkkitehtuuriDto expectedTietoarkkitehtuuri = createNewTietoarkkitehtuuri(subCatCountList);
        FyysinenTietovarantoDto expectedFyysinen = expectedTietoarkkitehtuuri.getFyysinen();
        mockContentDto(tietoarkkitehtuuri.getFyysinen(), expectedFyysinen, 1, fyysinenService);

        List<LooginenPaaTietoryhmaTietolaji> looginenSubCatList = tietoarkkitehtuuri.getLooginenPaaTietoryhmaTietolajiList();
        List<LooginenPaaTietoryhmaTietolaji> expectedLooginenSubCatList = expectedTietoarkkitehtuuri.getLooginenPaaTietoryhmaTietolajiList();
        assertThat(looginenSubCatList.size(), is(subCatCountList.size()));
        assertThat(expectedLooginenSubCatList.size(), is(subCatCountList.size()));

        Integer currLooginenTunnus = 1;
        Integer currTietolajiTunnus = 1;
        Integer currTietoryhmaTunnus = 1;
        Integer currPaatietoTunnus = 1;
        for (int looginenCount = 0; looginenCount < subCatCountList.size(); looginenCount++) {
            LooginenPaaTietoryhmaTietolaji looginenSubCat = looginenSubCatList.get(looginenCount);
            LooginenPaaTietoryhmaTietolaji expectedLooginenSubCat = expectedLooginenSubCatList.get(looginenCount);
            LooginenTietovarantoDto looginen = looginenSubCat.getLooginen();
            LooginenTietovarantoDto expectedLooginen = expectedLooginenSubCat.getLooginen();

            expectedLooginen.setFyysinenTietovaranto(expectedFyysinen.getTunnus());
            looginen.setFyysinenTietovaranto(expectedFyysinen.getTunnus());

            mockContentDto(looginen, expectedLooginen, currLooginenTunnus++, looginenService);

            List<PaatietoTietoryhmaTietolaji> tietolajiSubCatList = looginenSubCat.getPaatietoTietoryhmaTietolajiList();
            List<PaatietoTietoryhmaTietolaji> expectedTietolajiSubCatList = expectedLooginenSubCat.getPaatietoTietoryhmaTietolajiList();
            Integer tietolajiSubCatCount = subCatCountList.get(looginenCount);
            assertThat(tietolajiSubCatList.size(), is(tietolajiSubCatCount));

            for (int tietolajiCount = 0; tietolajiCount < tietolajiSubCatCount; tietolajiCount++) {
                PaatietoTietoryhmaTietolaji tietolajiSubCat = tietolajiSubCatList.get(tietolajiCount);
                PaatietoTietoryhmaTietolaji expectedTietolajiSubCat = expectedTietolajiSubCatList.get(tietolajiCount);

                PaatietoryhmaDto paatieto = tietolajiSubCat.getPaatieto();
                PaatietoryhmaDto expectedPaatieto = expectedTietolajiSubCat.getPaatieto();

                mockContentDto(paatieto, expectedPaatieto, currPaatietoTunnus++, paatietoryhmaService);

                TietoryhmaDto tietoryhma = tietolajiSubCat.getTietoryhma();
                TietoryhmaDto expectedTietoryhma = expectedTietolajiSubCat.getTietoryhma();
                tietoryhma.setPaatietoryhma(expectedPaatieto.getTunnus());
                expectedTietoryhma.setPaatietoryhma(expectedPaatieto.getTunnus());

                mockContentDto(tietoryhma, expectedTietoryhma, currTietoryhmaTunnus++, tietoryhmaService);

                TietolajiDto tietolaji = tietolajiSubCat.getTietolaji();
                TietolajiDto expectedTietolaji = expectedTietolajiSubCat.getTietolaji();
                expectedTietolaji.setLooginenTietovarantoTunnus(expectedLooginen.getTunnus());
                expectedTietolaji.setTietoryhmatunnus(expectedTietoryhma.getTunnus());
                tietolaji.setLooginenTietovarantoTunnus(expectedLooginen.getTunnus());
                tietolaji.setTietoryhmatunnus(expectedTietoryhma.getTunnus());

                mockContentDto(tietolaji, expectedTietolaji, currTietolajiTunnus++, tietolajiService);
            }
        }

        return expectedTietoarkkitehtuuri;
    }

    @Test
    public void createTietoarkkitehtuuriTest() throws IOException, SQLException {
        //Tietoarkkitehtuuri contains one looginen row with one tietolaji row
        List<Integer> subCatCountList = Collections.singletonList(1);
        TietoarkkitehtuuriDto tietoarkkitehtuuri = createNewTietoarkkitehtuuri(subCatCountList);
        FyysinenTietovarantoDto fyysinen = tietoarkkitehtuuri.getFyysinen();
        List<LooginenPaaTietoryhmaTietolaji> looginenSubCatList = tietoarkkitehtuuri.getLooginenPaaTietoryhmaTietolajiList();
        assertThat(looginenSubCatList.size(), is(1));
        LooginenPaaTietoryhmaTietolaji looginenSubCat = looginenSubCatList.get(0);
        LooginenTietovarantoDto looginen = looginenSubCat.getLooginen();
        List<PaatietoTietoryhmaTietolaji> tietolajiSubCatList = looginenSubCat.getPaatietoTietoryhmaTietolajiList();
        assertThat(tietolajiSubCatList.size(), is(1));
        PaatietoTietoryhmaTietolaji tietolajiSubCat = tietolajiSubCatList.get(0);
        TietolajiDto tietolaji = tietolajiSubCat.getTietolaji();
        TietoryhmaDto tietoryhma = tietolajiSubCat.getTietoryhma();
        PaatietoryhmaDto paatieto = tietolajiSubCat.getPaatieto();

        TietoarkkitehtuuriDto expectedTietoarkkitehtuuri = mockTietoarkkitehtuuri(subCatCountList, tietoarkkitehtuuri);
        TietoarkkitehtuuriDto fetchedTietoarkkitehtuuri = mockedTietoarkkitehtuuriService.create(tietoarkkitehtuuri);

        verify(fyysinenService, times(1)).save(any(Session.class), eq(fyysinen));
        verify(looginenService, times(1)).save(any(Session.class), eq(looginen));
        verify(paatietoryhmaService, times(1)).save(any(Session.class), eq(paatieto));
        verify(tietoryhmaService, times(1)).save(any(Session.class), eq(tietoryhma));
        verify(tietolajiService, times(1)).save(any(Session.class), eq(tietolaji));

        assertThat(fetchedTietoarkkitehtuuri, is(expectedTietoarkkitehtuuri));
    }

    @Test
    public void createTietoarkkitehtuuriTest2() throws IOException, SQLException {
        //Tietoarkkitehtuuri contains two looginen rows with one tietolaji row each
        List<Integer> subCatCountList = Arrays.asList(1, 1);
        TietoarkkitehtuuriDto tietoarkkitehtuuri = createNewTietoarkkitehtuuri(subCatCountList);
        FyysinenTietovarantoDto fyysinen = tietoarkkitehtuuri.getFyysinen();
        List<LooginenPaaTietoryhmaTietolaji> looginenSubCatList = tietoarkkitehtuuri.getLooginenPaaTietoryhmaTietolajiList();
        assertThat(looginenSubCatList.size(), is(2));

        LooginenPaaTietoryhmaTietolaji looginenSubCat1 = looginenSubCatList.get(0);
        LooginenTietovarantoDto looginen1 = looginenSubCat1.getLooginen();
        List<PaatietoTietoryhmaTietolaji> tietolajiSubCatList1 = looginenSubCat1.getPaatietoTietoryhmaTietolajiList();
        assertThat(tietolajiSubCatList1.size(), is(1));
        PaatietoTietoryhmaTietolaji tietolajiSubCat1 = tietolajiSubCatList1.get(0);
        TietolajiDto tietolaji1 = tietolajiSubCat1.getTietolaji();
        TietoryhmaDto tietoryhma1 = tietolajiSubCat1.getTietoryhma();
        PaatietoryhmaDto paatieto1 = tietolajiSubCat1.getPaatieto();

        LooginenPaaTietoryhmaTietolaji looginenSubCat2 = looginenSubCatList.get(1);
        LooginenTietovarantoDto looginen2 = looginenSubCat2.getLooginen();
        List<PaatietoTietoryhmaTietolaji> tietolajiSubCatList2 = looginenSubCat2.getPaatietoTietoryhmaTietolajiList();
        assertThat(tietolajiSubCatList2.size(), is(1));
        PaatietoTietoryhmaTietolaji tietolajiSubCat2 = tietolajiSubCatList2.get(0);
        TietolajiDto tietolaji2 = tietolajiSubCat2.getTietolaji();
        TietoryhmaDto tietoryhma2 = tietolajiSubCat2.getTietoryhma();
        PaatietoryhmaDto paatieto2 = tietolajiSubCat2.getPaatieto();

        TietoarkkitehtuuriDto expectedTietoarkkitehtuuri = mockTietoarkkitehtuuri(subCatCountList, tietoarkkitehtuuri);
        TietoarkkitehtuuriDto fetchedTietoarkkitehtuuri = mockedTietoarkkitehtuuriService.create(tietoarkkitehtuuri);

        verify(fyysinenService, times(1)).save(any(Session.class), eq(fyysinen));

        verify(looginenService, times(1)).save(any(Session.class), eq(looginen1));
        verify(looginenService, times(1)).save(any(Session.class), eq(looginen2));

        verify(paatietoryhmaService, times(1)).save(any(Session.class), eq(paatieto1));
        verify(paatietoryhmaService, times(1)).save(any(Session.class), eq(paatieto2));

        verify(tietoryhmaService, times(1)).save(any(Session.class), eq(tietoryhma1));
        verify(tietoryhmaService, times(1)).save(any(Session.class), eq(tietoryhma2));

        verify(tietolajiService, times(1)).save(any(Session.class), eq(tietolaji1));
        verify(tietolajiService, times(1)).save(any(Session.class), eq(tietolaji2));

        assertThat(fetchedTietoarkkitehtuuri, is(expectedTietoarkkitehtuuri));
    }

    @Test
    public void createTietoarkkitehtuuriTest3() throws IOException, SQLException {
        List<Integer> subCatCountList = Collections.singletonList(1);
        TietoarkkitehtuuriDto tietoarkkitehtuuri = createNewTietoarkkitehtuuri(subCatCountList);
        //Fyysinen exists
        FyysinenTietovarantoDto fyysinen = tietoarkkitehtuuri.getFyysinen();
        fyysinen.setTunnus(1);
        List<LooginenPaaTietoryhmaTietolaji> looginenSubCatList = tietoarkkitehtuuri.getLooginenPaaTietoryhmaTietolajiList();
        assertThat(looginenSubCatList.size(), is(1));
        LooginenPaaTietoryhmaTietolaji looginenSubCat = looginenSubCatList.get(0);
        //Looginen is new
        LooginenTietovarantoDto looginen = looginenSubCat.getLooginen();
        List<PaatietoTietoryhmaTietolaji> tietolajiSubCatList = looginenSubCat.getPaatietoTietoryhmaTietolajiList();
        assertThat(tietolajiSubCatList.size(), is(1));
        PaatietoTietoryhmaTietolaji tietolajiSubCat = tietolajiSubCatList.get(0);

        //Tietolaji exists
        TietolajiDto tietolaji = tietolajiSubCat.getTietolaji();
        tietolaji.setTunnus(1);
        //Tietoryhma is new
        TietoryhmaDto tietoryhma = tietolajiSubCat.getTietoryhma();
        //Paatieto exists
        PaatietoryhmaDto paatieto = tietolajiSubCat.getPaatieto();
        paatieto.setTunnus(1);

        TietoarkkitehtuuriDto expectedTietoarkkitehtuuri = mockTietoarkkitehtuuri(subCatCountList, tietoarkkitehtuuri);
        TietoarkkitehtuuriDto fetchedTietoarkkitehtuuri = mockedTietoarkkitehtuuriService.create(tietoarkkitehtuuri);

        verify(fyysinenService, times(0)).save(any(Session.class), any(FyysinenTietovarantoDto.class));
        verify(fyysinenService, times(1)).update(any(Session.class), eq(fyysinen));

        verify(looginenService, times(1)).save(any(Session.class), eq(looginen));

        verify(paatietoryhmaService, times(0)).save(any(Session.class), any(PaatietoryhmaDto.class));
        verify(paatietoryhmaService, times(1)).update(any(Session.class), eq(paatieto));

        verify(tietoryhmaService, times(1)).save(any(Session.class), eq(tietoryhma));

        verify(tietolajiService, times(0)).save(any(Session.class), any(TietolajiDto.class));
        verify(tietolajiService, times(1)).update(any(Session.class), eq(tietolaji));

        assertThat(fetchedTietoarkkitehtuuri, is(expectedTietoarkkitehtuuri));
    }

    @Test
    public void createTietoarkkitehtuuriWithConflictingTietolajiAndExpectFullRollback() throws IOException, SQLException {
        //TODO: make me when there is something to fail... Now you can e.g. create tietolaji without name...
    }

    private TietoarkkitehtuuriDto createNewTietoarkkitehtuuri(List<Integer> subCatCountList) {
        FyysinenTietovarantoDto fyysinen = (FyysinenTietovarantoDto) createTestContent(new FyysinenTietovarantoDto(), 0, 0);
        List<LooginenPaaTietoryhmaTietolaji> looginenSubCatList = new ArrayList<>();
        for (int i = 0; i < subCatCountList.size(); i++) {
            int tietolajiSubCatCount = subCatCountList.get(i);
            List<PaatietoTietoryhmaTietolaji> paatietoTietoryhmaTietolajiList = new ArrayList<>();
            for (int j = 0; j < tietolajiSubCatCount; j++) {
                TietolajiDto tietolaji = (TietolajiDto) createTestContent(new TietolajiDto(), i, j);
                TietoryhmaDto tietoryhma = (TietoryhmaDto) createTestContent(new TietoryhmaDto(), i, j);
                PaatietoryhmaDto paatieto = (PaatietoryhmaDto) createTestContent(new PaatietoryhmaDto(), i, j);
                paatietoTietoryhmaTietolajiList.add(new PaatietoTietoryhmaTietolaji(tietolaji, tietoryhma, paatieto));
            }
            LooginenTietovarantoDto looginen = (LooginenTietovarantoDto) createTestContent(new LooginenTietovarantoDto(), i, 0);
            looginenSubCatList.add(new LooginenPaaTietoryhmaTietolaji(looginen, paatietoTietoryhmaTietolajiList));
        }

        return new TietoarkkitehtuuriDto(fyysinen, looginenSubCatList);
    }

    private ContentDto createTestContent(ContentDto content, int i, int j) {
        content.setNimi(format("%s-%s-%s", content.getClass().getSimpleName(), i + 1, j + 1));
        return content;
    }
}
