package fi.liike.rest.api.dto;

import fi.liike.rest.Model.*;
import fi.liike.rest.api.ContentDto;

import java.util.HashSet;
import java.util.Set;

public class TietojarjestelmapalveluConverter implements MinimalConverter {
    @Override
    public Haettava dtoToDomain(ContentDto dtoContent) {
        TietojarjestelmapalveluDto data = (TietojarjestelmapalveluDto) dtoContent;
        Tietojarjestelmapalvelu result = new Tietojarjestelmapalvelu();
        result.setTunnus(data.getTunnus());
        result.setNimi(data.getNimi());
        result.setKuvaus(data.getKuvaus());
        result.setKayttajaroolit(data.getKayttajaroolit());
        result.setTietojarjestelmatunnus(data.getJarjestelma());
        result.setElinkaari(data.getElinkaaritila());
        result.setRivimuokkaajatunnus(data.getRivimuokkaajatunnus());
        return result;
    }



    @Override
    public ContentDto modelToDto(Haettava modelObject, Integer... parentId) {
        if (modelObject == null)
            return null;
        TietojarjestelmapalveluFetch data = (TietojarjestelmapalveluFetch) modelObject;
        TietojarjestelmapalveluDto result = new TietojarjestelmapalveluDto();
        result.setTunnus(data.getTunnus());
        result.setNimi(data.getNimi());
        result.setKuvaus(data.getKuvaus());
        result.setElinkaaritila(data.getElinkaari());
        result.setKayttajaroolit(data.getKayttajaroolit());
        result.setJarjestelma(data.getTietojarjestelmatunnus());
        result.setRivimuokkaajatunnus(data.getRivimuokkaajatunnus());
        Set<Tietolaji> tietolajit = data.getTietolajit();
        Set<TietolajiMinimalDto> dtoTietolajit = new HashSet<>();
        for (Tietolaji t : tietolajit) {
            dtoTietolajit.add(tietolajiModelToMinimalDto(t));
        }
        result.setTietolajit(dtoTietolajit);
        return result;
    }

    public TietolajiMinimalDto tietolajiModelToMinimalDto(Tietolaji tietolaji) {
        TietolajiMinimalDto result = new TietolajiMinimalDto();
        result.setNimi(tietolaji.getNimi());
        result.setTunnus(tietolaji.getTunnus());
        return result;
    }

    public ContentDto modelToMinimalDto(Haettava modelObject) {
        TietojarjestelmapalveluFetch data = (TietojarjestelmapalveluFetch) modelObject;
        TietojarjestelmapalveluFetchMinimalDto result = new TietojarjestelmapalveluFetchMinimalDto();
        result.setTunnus(data.getTunnus());
        result.setNimi(data.getNimi());
        result.setKuvaus(data.getKuvaus());
        return result;
    }

    public JoinTietojarjestelmapalveluTietolaji linkDtoToDomain(TietolajiMinimalDto data) {
        JoinTietojarjestelmapalveluTietolaji result = new JoinTietojarjestelmapalveluTietolaji();
        result.setParentNode(data.getTunnus());
        // TODO: legacy feature, consider removing
        result.setRivitila("A");
        return result;
    }

}
