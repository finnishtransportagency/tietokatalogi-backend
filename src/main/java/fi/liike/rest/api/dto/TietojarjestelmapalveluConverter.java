package fi.liike.rest.api.dto;

import fi.liike.rest.Model.*;
import fi.liike.rest.api.ContentDto;

import java.util.List;

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
        Tietojarjestelmapalvelu data = (Tietojarjestelmapalvelu) modelObject;
        TietojarjestelmapalveluDto result = new TietojarjestelmapalveluDto();
        result.setTunnus(data.getTunnus());
        result.setNimi(data.getNimi());
        result.setKuvaus(data.getKuvaus());
        result.setElinkaaritila(data.getElinkaari());
        result.setKayttajaroolit(data.getKayttajaroolit());
        result.setJarjestelma(data.getTietojarjestelmatunnus());
        result.setRivimuokkaajatunnus(data.getRivimuokkaajatunnus());
        return result;
    }

    public ContentDto modelToDto(Haettava modelObject, List<Integer> relatedJarjestelmaIds) {
        if (modelObject == null)
            return null;
        TietojarjestelmapalveluDto dtoContent = (TietojarjestelmapalveluDto) modelToDto(modelObject);
        dtoContent.setRelatedJarjestelmaIds(relatedJarjestelmaIds);
        return dtoContent;
    }

    public AnnotatedTietolajiDto tietolajiModelToAnnotatedDto(Tietolaji tietolaji) {
        AnnotatedTietolajiDto result = new AnnotatedTietolajiDto();
        result.setNimi(tietolaji.getNimi());
        result.setTunnus(tietolaji.getTunnus());
        return result;
    }

    public ContentDto modelToMinimalDto(Haettava modelObject) {
        Tietojarjestelmapalvelu data = (Tietojarjestelmapalvelu) modelObject;
        TietojarjestelmapalveluFetchMinimalDto result = new TietojarjestelmapalveluFetchMinimalDto();
        result.setTunnus(data.getTunnus());
        result.setNimi(data.getNimi());
        result.setKuvaus(data.getKuvaus());
        return result;
    }

    public JoinTietojarjestelmapalveluTietolaji linkDtoToDomain(AnnotatedTietolajiDto data) {
        JoinTietojarjestelmapalveluTietolaji result = new JoinTietojarjestelmapalveluTietolaji();
        result.setParentNode(data.getTunnus());
        // TODO: legacy feature, consider removing
        result.setRivitila("A");
        result.setLiittyvaJarjestelma(data.getLiittyvaJarjestelmaTunnus());
        return result;
    }

}
