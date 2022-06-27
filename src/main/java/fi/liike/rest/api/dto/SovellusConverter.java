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
}
