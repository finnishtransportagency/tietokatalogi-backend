package fi.liike.service;

import com.google.common.collect.ImmutableSet;
import fi.liike.rest.Dao.Hibernate.SovellusDaoImpl;
import fi.liike.rest.Model.*;
import fi.liike.rest.Service.HenkiloService;
import fi.liike.rest.Service.SovellusService;
import fi.liike.rest.api.dto.ExternalSovellusCSVDto;
import fi.liike.rest.api.dto.HenkiloRooliDto;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import static fi.liike.rest.api.dto.ExternalSovellusCSVDto.ACCEPTED_BY;
import static fi.liike.rest.api.dto.ExternalSovellusCSVDto.INSTALLATION_APPROVAL_NAME;
import static org.mockito.Mockito.*;

public class SovellusServiceTest {
    private SovellusService sovellusService;
    private SovellusDaoImpl sovellusDao;
    private HenkiloService henkiloService;

    @Before
    public void setUp() {
        this.sovellusDao = mock(SovellusDaoImpl.class);
        this.henkiloService = mock(HenkiloService.class);
        this.sovellusService = new SovellusService(sovellusDao, henkiloService);
    }

    @Test
    public void importExternalSovellusCSVListTest() throws IOException, SQLException {
        String loginName = "L123456";
        String lirsLoginName = "lirs." + loginName;
        Integer henkiloId = 100;
        Henkilo henkilo = new Henkilo(); henkilo.setTunnus(henkiloId);
        String sovellusName1 = "test1"; String signature1 = "123"; String versio = "1.0.0";
        Sovellus sovellus1 = new Sovellus(); sovellus1.setTunnus(1);

        when(henkiloService.getHenkiloByLoginName(loginName)).thenReturn(henkilo);

        ExternalSovellusCSVDto sovellusCSV1a = createTestSovellus(sovellusName1, signature1, versio,
                ACCEPTED_BY, lirsLoginName);
        ExternalSovellusCSVDto sovellusCSV1b = createTestSovellus(sovellusName1, signature1, versio,
                INSTALLATION_APPROVAL_NAME, lirsLoginName);

        when(sovellusDao.getByExternalSovellusCSVDto(sovellusCSV1a)).thenReturn(sovellus1);

        Set<PersonRole> includeRoles = ImmutableSet.of(PersonRole.TUOTANTOON_HYVAKSYNYT, PersonRole.ASENNUKSEN_HYVAKSYNYT);
        sovellusService.importExternalSovellusCSVList(Arrays.asList(sovellusCSV1a, sovellusCSV1b), includeRoles);

        List<SovellusTemp> sovellusTempList = Collections.singletonList(sovellusTemp(sovellusName1, signature1, versio));
        verify(sovellusDao).mergeExternalSovellusList(sovellusTempList);

        List<HenkiloRooliDto> henkiloRooliList1 = Arrays.asList(
                henkiloRooli(PersonRole.TUOTANTOON_HYVAKSYNYT.getId(), henkiloId),
                henkiloRooli(PersonRole.ASENNUKSEN_HYVAKSYNYT.getId(), henkiloId)
        );

        verify(henkiloService).addAndDeleteSystemHenkiloRoolis(SovellusHenkiloRooli.class, SovellusHenkiloRooliHistory.class,
                henkiloRooliList1, sovellus1.getTunnus(), "csv-import", "sovellusId",
                ImmutableSet.of(PersonRole.TUOTANTOON_HYVAKSYNYT, PersonRole.ASENNUKSEN_HYVAKSYNYT));

    }

    private HenkiloRooliDto henkiloRooli(Integer rooliId, Integer henkiloId) {
        return new HenkiloRooliDto(rooliId, henkiloId);
    }

    private ExternalSovellusCSVDto createTestSovellus(String adgroup, String signature, String versio, String personRole, String loginName) {
        return new ExternalSovellusCSVDto(adgroup, signature, versio, personRole, loginName);
    }

    private SovellusTemp sovellusTemp(String adgroup, String signature, String versio) {
        return new SovellusTemp(adgroup, versio, signature);
    }

}
