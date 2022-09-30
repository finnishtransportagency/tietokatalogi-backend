package fi.liike.rest.api.Converter;

import fi.liike.rest.Model.Haettava;
import fi.liike.rest.Model.Tietoomaisuus;
import fi.liike.rest.Model.TietoomaisuusFetch;
import fi.liike.rest.api.ContentDto;
import fi.liike.rest.api.dto.TietoomaisuusDto;

public class TietoomaisuusConverter implements Converter {
    @Override
    public Haettava dtoToDomain(ContentDto dtoContent) {
        Tietoomaisuus tietoomaisuus = new Tietoomaisuus();
        TietoomaisuusDto dto = (TietoomaisuusDto) dtoContent;
        tietoomaisuus.setAlustojenTyypJaSij(dto.getAlustojen_tyyp_ja_sij());
        tietoomaisuus.setAvoimuus(dto.getAvoimuus());
        tietoomaisuus.setDokumentaatio(dto.getDokumentaatio());
        tietoomaisuus.setEheys(dto.getEheys());
        tietoomaisuus.setElinkaari(dto.getElinkaari());
        tietoomaisuus.setHenkilotiedot(dto.getHenkilotiedot());
        tietoomaisuus.setHistoriatiedot(dto.getHistoriatiedot());
        tietoomaisuus.setImmateriaalioikeudet(dto.getImmateriaalioikeudet());
        tietoomaisuus.setKayttoformaatit(dto.getKayttoformaatit());
        tietoomaisuus.setKattavuusJaLaatu(dto.getKattavuus_ja_laatu());
        tietoomaisuus.setKayttajat(dto.getKayttajat());
        tietoomaisuus.setMallinnustapa(dto.getMallinnustapa());
        tietoomaisuus.setMetatiedot(dto.getMetatiedot());
        tietoomaisuus.setMuutostiedot(dto.getMuutostiedot());
        tietoomaisuus.setPrimaarikayttotarve(dto.getPrimaarikayttotarve());
        tietoomaisuus.setPysyvyys(dto.getPysyvyys());
        tietoomaisuus.setRivimuokkaajatunnus(dto.getRivimuokkaajatunnus());
        tietoomaisuus.setSaatavuus(dto.getSaatavuus());
        tietoomaisuus.setSekundaar_kayttotarpeet(dto.getSekundaar_kayttotarpeet());
        tietoomaisuus.setTietojarjestelmaTunnus(dto.getTietojarjestelma_tunnus());
        tietoomaisuus.setTunnus(dto.getTunnus());
        tietoomaisuus.setYksiloivatTunnisteet(dto.getYksiloivat_tunnisteet());
        return tietoomaisuus;
    }

    @Override
    public ContentDto modelToDto(Haettava modelObject, Integer... parentId) {
        Tietoomaisuus tietoomaisuus = (Tietoomaisuus) modelObject;
        TietoomaisuusDto dto = new TietoomaisuusDto();
        dto.setAlustojen_tyyp_ja_sij(tietoomaisuus.getAlustojenTyypJaSij());
        dto.setAvoimuus(tietoomaisuus.getAvoimuus());
        dto.setDokumentaatio(tietoomaisuus.getDokumentaatio());
        dto.setEheys(tietoomaisuus.getEheys());
        dto.setElinkaari(tietoomaisuus.getElinkaari());
        dto.setHenkilotiedot(tietoomaisuus.getHenkilotiedot());
        dto.setHistoriatiedot(tietoomaisuus.getHistoriatiedot());
        dto.setImmateriaalioikeudet(tietoomaisuus.getImmateriaalioikeudet());
        dto.setKayttoformaatit(tietoomaisuus.getKayttoformaatit());
        dto.setKattavuus_ja_laatu(tietoomaisuus.getKattavuusJaLaatu());
        dto.setKayttajat(tietoomaisuus.getKayttajat());
        dto.setMallinnustapa(tietoomaisuus.getMallinnustapa());
        dto.setMetatiedot(tietoomaisuus.getMetatiedot());
        dto.setMuutostiedot(tietoomaisuus.getMuutostiedot());
        dto.setPrimaarikayttotarve(tietoomaisuus.getPrimaarikayttotarve());
        dto.setPysyvyys(tietoomaisuus.getPysyvyys());
        dto.setRivimuokkaajatunnus(tietoomaisuus.getRivimuokkaajatunnus());
        dto.setSaatavuus(tietoomaisuus.getSaatavuus());
        dto.setSekundaar_kayttotarpeet(tietoomaisuus.getSekundaar_kayttotarpeet());
        dto.setTietojarjestelma_tunnus(tietoomaisuus.getTietojarjestelmaTunnus());
        dto.setTunnus(tietoomaisuus.getTunnus());
        dto.setYksiloivat_tunnisteet(tietoomaisuus.getYksiloivatTunnisteet());
        return dto;
    }

    public TietoomaisuusDto fetchModelToDto(TietoomaisuusFetch tietoomaisuus) {
        TietoomaisuusDto dto = (TietoomaisuusDto) modelToDto(tietoomaisuus);
        dto.setTietojarjestelma_nimi(tietoomaisuus.getJarjestelman_nimi());
        return dto;
    }
}
