package fi.liike.rest.api.dto;

import fi.liike.rest.Model.Henkilo;
import fi.liike.rest.Model.HenkiloTemp;
import fi.liike.rest.Model.JoinHenkiloRooliTable;
import fi.liike.rest.Model.Rooli;

import java.util.ArrayList;
import java.util.List;

public class HenkiloConverter {

    public HenkiloConverter() {}

    public Henkilo henkiloDtoToDomain(HenkiloDto henkiloDto) {
        Henkilo henkilo = new Henkilo();
        henkilo.setTunnus(henkiloDto.getTunnus());
        henkilo.setObjectID(henkiloDto.getObjectID());
        henkilo.setNayttonimi(henkiloDto.getNayttonimi());
        henkilo.setTunnustyyppi(henkiloDto.getTunnustyyppi());
        if (henkiloDto.getPoistunut() == null) {
            henkilo.setPoistunut(null);
        } else {
            henkilo.setPoistunut((henkiloDto.getPoistunut()) ? 1 : 0);
        }
        henkilo.setKayttajatunnus(henkiloDto.getKayttajatunnus());
        henkilo.setYritys(henkiloDto.getYritys());
        henkilo.setYritystunnus(henkiloDto.getYritystunnus());
        henkilo.setEtunimi(henkiloDto.getEtunimi());
        henkilo.setSukunimi(henkiloDto.getSukunimi());
        henkilo.setSahkoposti(henkiloDto.getSahkoposti());
        henkilo.setMatkapuhelin(henkiloDto.getMatkapuhelin());
        return henkilo;
    }

    public HenkiloTemp henkiloDtoToHenkiloTemp(HenkiloDto henkiloDto) {
        HenkiloTemp henkilo = new HenkiloTemp();
        henkilo.setTunnus(henkiloDto.getTunnus());
        henkilo.setObjectID(henkiloDto.getObjectID());
        henkilo.setNayttonimi(henkiloDto.getNayttonimi());
        henkilo.setTunnustyyppi(henkiloDto.getTunnustyyppi());
        if (henkiloDto.getPoistunut() == null) {
            henkilo.setPoistunut(null);
        } else {
            henkilo.setPoistunut((henkiloDto.getPoistunut()) ? 1 : 0);
        }
        henkilo.setKayttajatunnus(henkiloDto.getKayttajatunnus());
        henkilo.setYritys(henkiloDto.getYritys());
        henkilo.setYritystunnus(henkiloDto.getYritystunnus());
        henkilo.setEtunimi(henkiloDto.getEtunimi());
        henkilo.setSukunimi(henkiloDto.getSukunimi());
        henkilo.setSahkoposti(henkiloDto.getSahkoposti());
        henkilo.setMatkapuhelin(henkiloDto.getMatkapuhelin());
        return henkilo;
    }

    public HenkiloDto henkiloModelToDto(Henkilo henkilo) {
        HenkiloDto henkiloDto = new HenkiloDto();
        henkiloDto.setTunnus(henkilo.getTunnus());
        henkiloDto.setObjectID(henkilo.getObjectID());
        henkiloDto.setNayttonimi(henkilo.getNayttonimi());
        henkiloDto.setTunnustyyppi(henkilo.getTunnustyyppi());
        if (henkilo.getPoistunut() == null) {
            henkiloDto.setPoistunut(null);
        } else {
            henkiloDto.setPoistunut(henkilo.getPoistunut().equals(1));
        }
        henkiloDto.setKayttajatunnus(henkilo.getKayttajatunnus());
        henkiloDto.setYritys(henkilo.getYritys());
        henkiloDto.setYritystunnus(henkilo.getYritystunnus());
        henkiloDto.setEtunimi(henkilo.getEtunimi());
        henkiloDto.setSukunimi(henkilo.getSukunimi());
        henkiloDto.setSahkoposti(henkilo.getSahkoposti());
        henkiloDto.setMatkapuhelin(henkilo.getMatkapuhelin());
        return henkiloDto;
    }

    public List<HenkiloDto> henkiloModelListToDtoList(List<Henkilo> henkiloList) {
        List<HenkiloDto> henkiloDtoList = new ArrayList<HenkiloDto>();
        for (Henkilo henkilo : henkiloList) {
            henkiloDtoList.add(henkiloModelToDto(henkilo));
        }
        return henkiloDtoList;
    }

    public List<Henkilo> henkiloDtoListToModelList(List<HenkiloDto> henkiloDtoList) {
        List<Henkilo> henkiloList = new ArrayList<Henkilo>();
        for (HenkiloDto henkilo : henkiloDtoList) {
            henkiloList.add(henkiloDtoToDomain(henkilo));
        }
        return henkiloList;
    }

    public RooliDto rooliModelToDto(Rooli rooli) {
        return new RooliDto(rooli.getTunnus(), rooli.getNimi());
    }


    public Henkilo convertHenkiloTempToHenkilo(HenkiloTemp henkiloTemp) {
        return new Henkilo(henkiloTemp.getTunnus(), henkiloTemp.getObjectID(), henkiloTemp. getNayttonimi(),
                henkiloTemp.getTunnustyyppi(), henkiloTemp.getPoistunut(), henkiloTemp.getKayttajatunnus(),
                henkiloTemp.getYritys(), henkiloTemp.getYritystunnus(), henkiloTemp.getEtunimi(),
                henkiloTemp.getSukunimi(), henkiloTemp.getSahkoposti(), henkiloTemp.getMatkapuhelin());
    }

    public HenkiloTemp convertHenkiloToHenkiloTemp(Henkilo henkilo) {
        return new HenkiloTemp(henkilo.getTunnus(), henkilo.getObjectID(), henkilo. getNayttonimi(),
                henkilo.getTunnustyyppi(), henkilo.getPoistunut(), henkilo.getKayttajatunnus(),
                henkilo.getYritys(), henkilo.getYritystunnus(), henkilo.getEtunimi(),
                henkilo.getSukunimi(), henkilo.getSahkoposti(), henkilo.getMatkapuhelin());
    }

    public List<HenkiloTemp> convertHenkiloListToHenkiloTempList(List<Henkilo> henkiloList) {
        List<HenkiloTemp> henkiloTempList = new ArrayList<HenkiloTemp>();
        for (Henkilo henkilo : henkiloList) {
            henkiloTempList.add(convertHenkiloToHenkiloTemp(henkilo));
        }
        return henkiloTempList;
    }

    public List<Henkilo> convertHenkiloTempListToHenkiloList(List<HenkiloTemp> henkiloTempList) {
        List<Henkilo> henkiloList = new ArrayList<Henkilo>();
        for (HenkiloTemp henkilo : henkiloTempList) {
            henkiloList.add(convertHenkiloTempToHenkilo(henkilo));
        }
        return henkiloList;
    }

    public HenkiloRooliDto convertJoinHenkiloRooliTableToHenkiloRooliDto(
            JoinHenkiloRooliTable henkiloRooliTable) {

        HenkiloRooliDto henkiloRooli = new HenkiloRooliDto(henkiloRooliTable.getRooliId(), henkiloRooliTable.getHenkiloId());
        return henkiloRooli;
    }

    public List<HenkiloRooliDto> convertJoinHenkiloRooliTableListToHenkiloRooliDtoList(
            List<? extends JoinHenkiloRooliTable> henkiloRooliTableList) {

        List<HenkiloRooliDto> dtoList = new ArrayList<>();
        for (JoinHenkiloRooliTable joinHenkiloRooliTable : henkiloRooliTableList) {
            dtoList.add(convertJoinHenkiloRooliTableToHenkiloRooliDto(joinHenkiloRooliTable));
        }

        return dtoList;
    }

}
