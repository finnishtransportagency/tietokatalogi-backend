package fi.liike.rest.api.dto;

import fi.liike.rest.Model.Haettava;
import fi.liike.rest.Model.Sovellus;
import fi.liike.rest.Model.SovellusTemp;
import fi.liike.rest.Model.SovellusUpdate;
import fi.liike.rest.api.ContentDto;


import java.util.*;

import static fi.liike.rest.util.Utils.dateToStr;
import static fi.liike.rest.util.Utils.strToDate;

public class SovellusConverter implements Converter {

    @Override
    public Haettava dtoToDomain(ContentDto dtoContent) {
        return null;
    }

    public Haettava updateDtoToUpdateDomain(SovellusUpdateDto sovellusUpdateDto) {
        SovellusUpdate sovellus = new SovellusUpdate();
        sovellus.setTunnus(sovellusUpdateDto.getTunnus());
        sovellus.setElinkaaritieto(sovellusUpdateDto.getElinkaaritieto());
        Boolean poistunut = sovellusUpdateDto.getPoistunut();
        if (poistunut != null) {
            sovellus.setPoistunut((sovellusUpdateDto.getPoistunut()) ? 1 : 0);
        } else {
            sovellus.setPoistunut(0);
        }
        return sovellus;
    }

    @Override
    public ContentDto modelToDto(Haettava modelObject, Integer... parentId) {
        SovellusDto sovellusDto = new SovellusDto();
        Sovellus sovellus = (Sovellus) modelObject;

        sovellusDto.setTunnus(sovellus.getTunnus());
        sovellusDto.setValmistaja(sovellus.getValmistaja());
        sovellusDto.setNimi(sovellus.getNimi());
        sovellusDto.setAliasNimet(sovellus.getAliasNimet());
        sovellusDto.setVersio(sovellus.getVersio());
        sovellusDto.setTuotekoodi(sovellus.getTuotekoodi());
        sovellusDto.setKonfiguraatioVersio(sovellus.getKonfiguraatioVersio());
        sovellusDto.setLisatietoja(sovellus.getLisatietoja());
        sovellusDto.setSovelluksenTyyppi(sovellus.getSovellusTyyppi());
        sovellusDto.setKielisyys(sovellus.getKielisyys());
        sovellusDto.setKayttojarjestelmavaatimus(sovellus.getKayttojarjestelmaVaatimus());
        sovellusDto.setArkkitehtuuri(sovellus.getArkkitehtuuri());
        sovellusDto.setAlusta(sovellus.getAlusta());
        sovellusDto.setRiippuvuustieto(sovellus.getRiippuvuustieto());
        sovellusDto.setLiittymatJarjestelmiin(sovellus.getLiittymatJarjestelmiin());
        sovellusDto.setTuotantoonHyvaksymispaiva(dateToStr(sovellus.getTuotantoonHyvaksymisPaiva()));
        sovellusDto.setKriittisyys(sovellus.getKriittisyys());
        sovellusDto.setElinkaaritieto(sovellus.getElinkaaritieto());
        sovellusDto.setPoistunut(sovellus.getPoistunut());
        return sovellusDto;
    }

    public List<SovellusTemp> convertExternalSovellusCSVDtosToSovellusTempList(List<ExternalSovellusCSVDto> csvList) {
        List<SovellusTemp> sovellusTempList = new ArrayList<>();
        for (ExternalSovellusCSVDto externalSovellus : csvList) {
            sovellusTempList.add(convertExternalSovellusCSVDDtoToSovellusTemp(externalSovellus));
        }

        return sovellusTempList;
    }

    private SovellusTemp convertExternalSovellusCSVDDtoToSovellusTemp(ExternalSovellusCSVDto csvDto) {
        SovellusTemp sovellus = new SovellusTemp();

        sovellus.setValmistaja(convertNull(csvDto.getManufacturer()));
        sovellus.setAliasNimet(convertNull(csvDto.getModel()));
        sovellus.setNimi(convertNull(csvDto.getAdGroup()));
        sovellus.setVersio(convertNull(csvDto.getVersion()));
        sovellus.setTuotekoodi(convertNull(csvDto.getSignature0()));
        sovellus.setKonfiguraatioVersio(convertNull(csvDto.getConfigVersion()));
        sovellus.setLisatietoja(convertNull(csvDto.getMisc()));
        sovellus.setSovellusTyyppi(convertNull(csvDto.getConfigurationBasicNumber()));
        sovellus.setKielisyys(convertNull(csvDto.getLanguage()));
        sovellus.setKayttojarjestelmaVaatimus(convertNull(csvDto.getForOS()));
        sovellus.setArkkitehtuuri(convertNull(csvDto.getForArchitecture()));
        sovellus.setAlusta(convertNull(csvDto.getPlatform()));
        sovellus.setRiippuvuustieto(convertNull(csvDto.getDependencies()));
        sovellus.setLiittymatJarjestelmiin(convertNull(csvDto.getRelations()));
        sovellus.setTuotantoonHyvaksymisPaiva(strToDate(convertNull(csvDto.getAcceptanceDate())));
        sovellus.setKriittisyys(convertNull(csvDto.getCriticality()));

        return sovellus;
    }

    private String convertNull(String val) {
        if (val == null || val.equals("NULL") || val.equals("null") || val.equals("N/A")) {
            return null;
        }
        return val;
    }

}
