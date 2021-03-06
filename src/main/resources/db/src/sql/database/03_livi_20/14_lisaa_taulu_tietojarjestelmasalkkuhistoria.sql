CREATE TABLE TIETOJARJESTELMASALKKUHISTORIA (
"RIVI_ID" NUMBER NOT NULL,
"TIETOJARJESTELMATUNNUS" NUMBER,
"HISTORIATYYPPI" VARCHAR2(6) NOT NULL,
"JARJESTELMAN_NIMI" VARCHAR2(255),
"KUVAUS_MAARITELMA" VARCHAR2(4000),
"LYHENNETTY_NIMI" VARCHAR2(255),
"JARJESTELMATYYPPI" VARCHAR2(255),
"KAYTTAJARYHMAT" VARCHAR2(1000),
"ELINKAARITILA" VARCHAR2(255),
"TOIMITTAJA" VARCHAR2(255),
"TOIMITTAJAN_YHTEYSHENKILO" VARCHAR2(255),
"JARJESTELMAVASTAAVA_LIVI" VARCHAR2(255),
"JARJESTELMAVASTAAVA_LIVI_ID" VARCHAR2(50),
"SISAISET_KAYTTAJAT" VARCHAR2(255),
"LISENSSITIETO" VARCHAR2(255),
"ALFRESCO_LINKKI" VARCHAR2(255),
"KRIITTISYYS" VARCHAR2(255),
"MUUT_TOIMITTAJAT" VARCHAR2(255),
"VERSIO" VARCHAR2(255),
"TIETOJ_KRIITTISYYS_TOIMINNALLE" VARCHAR2(255),
"TIETOVARASTOT" VARCHAR2(255),
"PAASYNHALLINTA" VARCHAR2(255),
"PORTAALIPALVELU" VARCHAR2(255),
"TUNNISTETUT_KEHITYSTARPEET" VARCHAR2(255),
"TOTEUTUSTEKNOLOGIA" VARCHAR2(255),
"SISAISTEN_KAYTTAJIEN_MAARA" VARCHAR2(255),
"PALVELIN" VARCHAR2(255),
"TEKNINEN_ELINKAARI" VARCHAR2(255),
"KAYTTOOIKEUKSIEN_HALLINTA" VARCHAR2(255),
"KAYTTOONOTTOVUOSI" VARCHAR2(255),
"HUOMAUTUS_ESIMERKKI" VARCHAR2(1000),
"KEHITYSPANOS_ARVIOITU" VARCHAR2(255),
"KEHITYSKULUT_ARVIOIDUT" VARCHAR2(255),
"PALVELUAIKA" VARCHAR2(255),
"LIIK_TURVALLISUUSLUOKKA" VARCHAR2(255),
"TUNNISTETUT_RISKIT" VARCHAR2(255),
"LUOKITUKSEN_TARKASTUSPVM" DATE,
"TIETOTURVATASOLUOKITUS" VARCHAR2(255),
"ICT_VARAUTUMISEN_LUOKITUS" VARCHAR2(255),
"SLA_LUOKITUS_JHS" VARCHAR2(255),
"SLA_LUOKITUS_KAYTTOPALVELU" VARCHAR2(255),
"JARJESTELMAN_TARKEYS_LIVILLE" VARCHAR2(255),
"JARJ_TARKEYS_YHTEISTYOKUMP" VARCHAR2(255),
"TURVALLISUUSKUVAUS_LAADITTU" VARCHAR2(255),
"TOIPUMISSUUNNITELMA_LAADITTU" VARCHAR2(255),
"KEHITYSTARVE" VARCHAR2(255),
"KAYTTOTIHEYS" VARCHAR2(255),
"ULKOISET_KAYTTAJAT" VARCHAR2(255),
"POISTOVUOSI" VARCHAR2(20),
"TIETOLAHTEET" VARCHAR2(255),
"SALASSA_PIDETTAVAT_TIEDOT" VARCHAR2(255),
"JULKISET_TIEDOT_RYHMITTAIN" VARCHAR2(255),
"TIETOJ_INTERNET_OSOITE" VARCHAR2(255),
"TIETOJEN_JULKISUUS" VARCHAR2(255),
"JARJ_OMISTAJA" VARCHAR2(255),
"JARJ_OMISTAJA_ID" VARCHAR2(50),
"REKISTERISELOSTE" VARCHAR2(255),
"RIVILUOTUPVM" TIMESTAMP (6),
"RIVIMUOKKAAJATUNNUS" VARCHAR2(150),
"DOCUMENT_ID" VARCHAR2(255),
"TOIMITTAJAN_TEKN_YH" VARCHAR2(255),
"YHTEISK_KRIIT_JARJ" VARCHAR2(255),
"JARJ_TARKEYS_YK" VARCHAR2(255),
"JARJESTELMAALUE" VARCHAR2(255),
"TIETOTURVASOPIMUS" NUMBER(1),
CONSTRAINT "TIETOJARJESTELMASALKKUHIST_PK" PRIMARY KEY ("RIVI_ID"));
