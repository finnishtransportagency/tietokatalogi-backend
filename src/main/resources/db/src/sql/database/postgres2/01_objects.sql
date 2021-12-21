--
-- PostgreSQL database dump
--

-- Dumped from database version 14.1 (Debian 14.1-1.pgdg110+1)
-- Dumped by pg_dump version 14.1 (Debian 14.1-1.pgdg110+1)

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;


--
-- Name: fyysinentietovaranto; Type: TABLE; Schema: tietok; Owner: tietokt
--

CREATE TABLE tietok.fyysinentietovaranto (
    fyysinentietovarantotunnus numeric(38,10) NOT NULL,
    id character varying(50),
    nimi character varying(150) NOT NULL,
    koodi character varying(50),
    sislt character varying(4000),
    tietokantateknologia character varying(50),
    palvelutaso character varying(50),
    koko numeric(15,5),
    tietuemaara numeric(15,5),
    linkki character varying(50),
    muuta character varying(4000),
    teknologia character varying(150),
    linkki_1 character varying(150),
    ylempitaso numeric(38,10),
    rivitunnus numeric(38,10),
    rivitila character varying(15),
    rivimuokattupvm timestamp without time zone,
    rivimuokkaajatunnus character varying(150),
    riviluotupvm timestamp without time zone,
    document_id character varying(255),
    omistaja character varying(150),
    sijainti character varying(2000)
);


ALTER TABLE tietok.fyysinentietovaranto OWNER TO tietokt;

--
-- Name: looginentietovaranto; Type: TABLE; Schema: tietok; Owner: tietokt
--

CREATE TABLE tietok.looginentietovaranto (
    looginentietovarantotunnus numeric(38,10) NOT NULL,
    nimi character varying(255) NOT NULL,
    omistaja character varying(255),
    kuvaus character varying(4000),
    kuvauskeskeinentieto character varying(4000),
    kuvausvarastointifyysinen character varying(2000),
    paivitystiheys character varying(255),
    kuvauskayttaja character varying(2000),
    kayttaja character varying(150),
    kuvaustoimitus character varying(4000),
    kasitemalli character varying(150),
    ylempitaso numeric(38,10),
    rivitunnus numeric(38,10),
    rivitila character varying(15),
    rivimuokattupvm timestamp without time zone,
    rivimuokkaajatunnus character varying(150),
    riviluotupvm timestamp without time zone,
    document_id character varying(255),
    alue character varying(2000)
);


ALTER TABLE tietok.looginentietovaranto OWNER TO tietokt;

--
-- Name: looginentietovarantofyysinenti; Type: TABLE; Schema: tietok; Owner: tietokt
--

CREATE TABLE tietok.looginentietovarantofyysinenti (
    rivitunnus numeric(38,10) NOT NULL,
    fyysinentietovarantotunnus numeric(38,10) NOT NULL,
    looginentietovarantotunnus numeric(38,10) NOT NULL,
    rivitila character varying(15) NOT NULL
);


ALTER TABLE tietok.looginentietovarantofyysinenti OWNER TO tietokt;

--
-- Name: paatietoryhma; Type: TABLE; Schema: tietok; Owner: tietokt
--

CREATE TABLE tietok.paatietoryhma (
    paatietoryhmatunnus numeric(38,10) NOT NULL,
    nimi character varying(255),
    koodi character varying(50),
    kuvaus character varying(4000),
    omistaja character varying(150),
    lahde character varying(150),
    tila character varying(50),
    synonyymi character varying(150),
    tietosuojataso character varying(50),
    julkaisutieto character varying(150),
    julkaisuhuomio character varying(2000),
    ylempitaso numeric(38,10),
    rivitunnus numeric(38,10),
    rivitila character varying(15),
    riviluotupvm timestamp without time zone,
    rivimuokattupvm timestamp without time zone,
    rivimuokkaajatunnus character varying(150),
    document_id character varying(255)
);


ALTER TABLE tietok.paatietoryhma OWNER TO tietokt;

--
-- Name: tietoryhma; Type: TABLE; Schema: tietok; Owner: tietokt
--

CREATE TABLE tietok.tietoryhma (
    paatietoryhmatunnus numeric(38,10) NOT NULL,
    tietoryhmatunnus numeric(38,10) NOT NULL,
    nimi character varying(255) NOT NULL,
    koodi character varying(50),
    kuvaus character varying(4000),
    omistaja character varying(150),
    lahde character varying(150),
    tila character varying(50),
    synonyymi character varying(150),
    tietosuojataso character varying(50),
    kayttaja character varying(150),
    ylempitaso numeric(38,10),
    rivitunnus numeric(38,10),
    rivitila character varying(15),
    riviluotupvm timestamp without time zone,
    rivimuokattupvm timestamp without time zone,
    rivimuokkaajatunnus character varying(150),
    document_id character varying(255),
    tietovarantotunnus numeric(38,10)
);


ALTER TABLE tietok.tietoryhma OWNER TO tietokt;

--
-- Name: tietoryhmalooginentietovaranto; Type: TABLE; Schema: tietok; Owner: tietokt
--

CREATE TABLE tietok.tietoryhmalooginentietovaranto (
    rivitunnus numeric(38,10) NOT NULL,
    paatietoryhmatunnus numeric(38,10),
    tietoryhmatunnus numeric(38,10),
    looginentietovarantotunnus numeric(38,10),
    rivitila character varying(15)
);


ALTER TABLE tietok.tietoryhmalooginentietovaranto OWNER TO tietokt;

--
-- Name: apu_hierarkia; Type: VIEW; Schema: tietok; Owner: tietokt
--

CREATE VIEW tietok.apu_hierarkia AS
 SELECT ft.nimi AS ft_nimi,
    lt.nimi AS lt_nimi,
    tr.nimi AS tr_nimi,
    ptr.nimi AS ptr_nimi
   FROM (((((tietok.looginentietovarantofyysinenti t
     JOIN tietok.looginentietovaranto lt ON ((t.looginentietovarantotunnus = lt.looginentietovarantotunnus)))
     JOIN tietok.fyysinentietovaranto ft ON ((t.fyysinentietovarantotunnus = ft.fyysinentietovarantotunnus)))
     LEFT JOIN tietok.tietoryhmalooginentietovaranto trl ON ((lt.looginentietovarantotunnus = trl.looginentietovarantotunnus)))
     LEFT JOIN tietok.tietoryhma tr ON ((trl.tietoryhmatunnus = tr.tietoryhmatunnus)))
     LEFT JOIN tietok.paatietoryhma ptr ON ((trl.paatietoryhmatunnus = ptr.paatietoryhmatunnus)));


ALTER TABLE tietok.apu_hierarkia OWNER TO tietokt;

--
-- Name: apu_hierarkia_2; Type: VIEW; Schema: tietok; Owner: tietokt
--

CREATE VIEW tietok.apu_hierarkia_2 AS
 SELECT ft.nimi AS liittyy_1,
    ft.fyysinentietovarantotunnus AS tunnus_1,
    'fyysinentietovaranto'::text AS varanto_1,
    lt.nimi AS liittyy_2,
    lt.looginentietovarantotunnus AS tunnus_2,
    'looginentietovaranto'::text AS varanto_2
   FROM ((tietok.looginentietovarantofyysinenti t
     JOIN tietok.looginentietovaranto lt ON ((t.looginentietovarantotunnus = lt.looginentietovarantotunnus)))
     JOIN tietok.fyysinentietovaranto ft ON ((t.fyysinentietovarantotunnus = ft.fyysinentietovarantotunnus)))
UNION
 SELECT tr.nimi AS liittyy_1,
    tr.tietoryhmatunnus AS tunnus_1,
    'tietoryhma'::text AS varanto_1,
    ptr.nimi AS liittyy_2,
    ptr.paatietoryhmatunnus AS tunnus_2,
    'paatietoryhma'::text AS varanto_2
   FROM ((tietok.tietoryhmalooginentietovaranto trl
     JOIN tietok.tietoryhma tr ON ((trl.tietoryhmatunnus = tr.tietoryhmatunnus)))
     JOIN tietok.paatietoryhma ptr ON ((trl.paatietoryhmatunnus = ptr.paatietoryhmatunnus)))
UNION
 SELECT tr.nimi AS liittyy_1,
    tr.tietoryhmatunnus AS tunnus_1,
    'tietoryhma'::text AS varanto_1,
    lt.nimi AS liittyy_2,
    lt.looginentietovarantotunnus AS tunnus_2,
    'looginentietovaranto'::text AS varanto_2
   FROM ((tietok.tietoryhmalooginentietovaranto trl
     JOIN tietok.tietoryhma tr ON ((trl.tietoryhmatunnus = tr.tietoryhmatunnus)))
     JOIN tietok.looginentietovaranto lt ON ((trl.looginentietovarantotunnus = lt.looginentietovarantotunnus)));


ALTER TABLE tietok.apu_hierarkia_2 OWNER TO tietokt;

--
-- Name: tietojarjestelmasalkku; Type: TABLE; Schema: tietok; Owner: tietokt
--

CREATE TABLE tietok.tietojarjestelmasalkku (
    tietojarjestelmatunnus numeric(38,10) NOT NULL,
    jarjestelman_nimi character varying(255),
    kuvaus_maaritelma character varying(4000),
    lyhennetty_nimi character varying(255),
    jarjestelmatyyppi character varying(255),
    kayttajaryhmat character varying(1000),
    elinkaaritila character varying(255),
    toimittaja character varying(255),
    toimittajan_yhteyshenkilo character varying(255),
    jarjestelmavastaava_livi character varying(255),
    sisaiset_kayttajat character varying(255),
    lisenssitieto character varying(255),
    alfresco_linkki character varying(1000),
    kriittisyys character varying(255),
    muut_toimittajat character varying(255),
    versio character varying(255),
    tietoj_kriittisyys_toiminnalle character varying(255),
    tietovarastot character varying(255),
    paasynhallinta character varying(255),
    portaalipalvelu character varying(255),
    tunnistetut_kehitystarpeet character varying(255),
    toteutusteknologia character varying(255),
    sisaisten_kayttajien_maara character varying(255),
    palvelin character varying(255),
    tekninen_elinkaari character varying(255),
    kayttooikeuksien_hallinta character varying(255),
    kayttoonottovuosi character varying(255),
    huomautus_esimerkki character varying(1000),
    kehityspanos_arvioitu character varying(255),
    kehityskulut_arvioidut character varying(255),
    palveluaika character varying(255),
    liik_turvallisuusluokka character varying(255),
    tunnistetut_riskit character varying(255),
    luokituksen_tarkastuspvm timestamp without time zone,
    tietoturvatasoluokitus character varying(255),
    ict_varautumisen_luokitus character varying(255),
    sla_luokitus_jhs character varying(255),
    sla_luokitus_kayttopalvelu character varying(255),
    jarjestelman_tarkeys_liville character varying(255),
    jarj_tarkeys_yhteistyokump character varying(255),
    turvallisuuskuvaus_laadittu character varying(255),
    toipumissuunnitelma_laadittu character varying(255),
    kehitystarve character varying(255),
    kayttotiheys character varying(255),
    ulkoiset_kayttajat character varying(255),
    poistovuosi character varying(20),
    tietolahteet character varying(255),
    salassa_pidettavat_tiedot character varying(255),
    julkiset_tiedot_ryhmittain character varying(255),
    tietoj_internet_osoite character varying(255),
    tietojen_julkisuus character varying(255),
    jarj_omistaja character varying(255),
    rekisteriseloste character varying(255),
    riviluotupvm timestamp without time zone,
    rivimuokattupvm timestamp without time zone,
    rivimuokkaajatunnus character varying(150),
    document_id character varying(255),
    toimittajan_tekn_yh character varying(255),
    yhteisk_kriit_jarj character varying(255),
    jarj_tarkeys_yk character varying(255),
    jarjestelmaalue character varying(255),
    tietoturvasopimus smallint,
    confluence_linkki character varying(1000),
    kayttopalvelun_toimittaja character varying(255),
    omistava_organisaatio character varying(255),
    budjetti character varying(255),
    rahoitusmomentti character varying(255),
    salassapidon_peruste character varying(255),
    turvallisuusluokitus character varying(255),
    tarkeinta character varying(255),
    tiedonluottamuksellisuus character varying(255),
    tiedon_saatavuus character varying(255),
    tiedon_eheys character varying(255),
    tarvitaan_viikonloppuna character varying(255),
    tarvitaan_audit_trail character varying(255),
    integraatioita_muihin character varying(255)
);


ALTER TABLE tietok.tietojarjestelmasalkku OWNER TO tietokt;

--
-- Name: tietovirrat; Type: TABLE; Schema: tietok; Owner: tietokt
--

CREATE TABLE tietok.tietovirrat (
    s_jarjestelma numeric(38,10),
    t_jarjestelma numeric(38,10),
    luotu timestamp without time zone,
    muokattu timestamp without time zone,
    muokkaaja character varying(240),
    document_id character varying(255),
    linkki character varying(255),
    tietosisalto character varying(4000),
    s_jarjestelma_nimi character varying(4000),
    t_jarjestelma_nimi character varying(4000),
    tietovirta_id character varying(240)
);


ALTER TABLE tietok.tietovirrat OWNER TO tietokt;

--
-- Name: apu_tietovirrat; Type: VIEW; Schema: tietok; Owner: tietokt
--

CREATE VIEW tietok.apu_tietovirrat AS
 SELECT j.tietojarjestelmatunnus AS s_id,
    j.jarjestelman_nimi AS source,
    tj.tietojarjestelmatunnus AS t_id,
    tj.jarjestelman_nimi AS target,
    t.linkki,
    t.tietosisalto,
    j.jarjestelman_nimi,
    t.tietovirta_id
   FROM ((tietok.tietovirrat t
     JOIN tietok.tietojarjestelmasalkku j ON ((t.s_jarjestelma = j.tietojarjestelmatunnus)))
     JOIN tietok.tietojarjestelmasalkku tj ON ((t.t_jarjestelma = tj.tietojarjestelmatunnus)))
  GROUP BY j.tietojarjestelmatunnus, j.jarjestelman_nimi, tj.tietojarjestelmatunnus, tj.jarjestelman_nimi, t.linkki, t.tietosisalto, t.tietovirta_id;


ALTER TABLE tietok.apu_tietovirrat OWNER TO tietokt;

--
-- Name: apu_hierarkia_3; Type: VIEW; Schema: tietok; Owner: tietokt
--

CREATE VIEW tietok.apu_hierarkia_3 AS
 SELECT ft.nimi AS liittyy_1,
    ft.fyysinentietovarantotunnus AS tunnus_1,
    'fyysinentietovaranto'::text AS varanto_1,
    lt.nimi AS liittyy_2,
    lt.looginentietovarantotunnus AS tunnus_2,
    'looginentietovaranto'::text AS varanto_2,
    'tietoarkkitehtuuri'::text AS tyyppi,
    ''::text AS linkki,
    ''::text AS tietosisalto,
    ''::text AS tietovirta_id
   FROM ((tietok.looginentietovarantofyysinenti t
     JOIN tietok.looginentietovaranto lt ON ((t.looginentietovarantotunnus = lt.looginentietovarantotunnus)))
     JOIN tietok.fyysinentietovaranto ft ON ((t.fyysinentietovarantotunnus = ft.fyysinentietovarantotunnus)))
UNION
 SELECT tr.nimi AS liittyy_1,
    tr.tietoryhmatunnus AS tunnus_1,
    'tietoryhma'::text AS varanto_1,
    ptr.nimi AS liittyy_2,
    ptr.paatietoryhmatunnus AS tunnus_2,
    'paatietoryhma'::text AS varanto_2,
    'tietoarkkitehtuuri'::text AS tyyppi,
    ''::text AS linkki,
    ''::text AS tietosisalto,
    ''::text AS tietovirta_id
   FROM ((tietok.tietoryhmalooginentietovaranto trl
     JOIN tietok.tietoryhma tr ON ((trl.tietoryhmatunnus = tr.tietoryhmatunnus)))
     JOIN tietok.paatietoryhma ptr ON ((trl.paatietoryhmatunnus = ptr.paatietoryhmatunnus)))
UNION
 SELECT tr.nimi AS liittyy_1,
    tr.tietoryhmatunnus AS tunnus_1,
    'tietoryhma'::text AS varanto_1,
    lt.nimi AS liittyy_2,
    lt.looginentietovarantotunnus AS tunnus_2,
    'looginentietovaranto'::text AS varanto_2,
    'tietoarkkitehtuuri'::text AS tyyppi,
    ''::text AS linkki,
    ''::text AS tietosisalto,
    ''::text AS tietovirta_id
   FROM ((tietok.tietoryhmalooginentietovaranto trl
     JOIN tietok.tietoryhma tr ON ((trl.tietoryhmatunnus = tr.tietoryhmatunnus)))
     JOIN tietok.looginentietovaranto lt ON ((trl.looginentietovarantotunnus = lt.looginentietovarantotunnus)))
UNION
 SELECT ap.source AS liittyy_1,
    ap.s_id AS tunnus_1,
    'tietojarjestelmasalkku'::text AS varanto_1,
    ap.target AS liittyy_2,
    ap.t_id AS tunnus_2,
    'tietojarjestelmasalkku'::text AS varanto_2,
    'jarjestelma'::text AS tyyppi,
    ap.linkki,
    ap.tietosisalto,
    ap.tietovirta_id
   FROM tietok.apu_tietovirrat ap;


ALTER TABLE tietok.apu_hierarkia_3 OWNER TO tietokt;

--
-- Name: sovellus; Type: TABLE; Schema: tietok; Owner: tietokt
--

CREATE TABLE tietok.sovellus (
    tunnus numeric(38,10) NOT NULL,
    valmistaja character varying(255),
    alias_nimet character varying(255),
    versio character varying(255),
    konfiguraatio_versio character varying(255),
    lisatietoja character varying(4000),
    sovellus_tyyppi character varying(255),
    kielisyys character varying(255),
    kayttojarjestelmavaatimus character varying(255),
    arkkitehtuuri character varying(255),
    alusta character varying(255),
    riippuvuustieto character varying(255),
    liittymat_jarjestelmiin character varying(255),
    tuotantoon_hyvaksymispaiva timestamp without time zone,
    kriittisyys character varying(4000),
    elinkaaritieto character varying(4000),
    poistunut numeric(38,10) DEFAULT 0 NOT NULL,
    tuotekoodi character varying(255),
    nimi character varying(255) NOT NULL
);


ALTER TABLE tietok.sovellus OWNER TO tietokt;

--
-- Name: tjs_sov; Type: VIEW; Schema: tietok; Owner: tietokt
--

CREATE VIEW tietok.tjs_sov AS
 SELECT t.tietojarjestelmatunnus AS id,
    t.jarjestelman_nimi AS nimi,
    'tietojarjestelmasalkku'::text AS taulu
   FROM tietok.tietojarjestelmasalkku t
UNION
 SELECT (s.tunnus + (1000000)::numeric) AS id,
    (((s.nimi)::text || ' '::text) || (s.versio)::text) AS nimi,
    'sovellus'::text AS taulu
   FROM tietok.sovellus s;


ALTER TABLE tietok.tjs_sov OWNER TO tietokt;

--
-- Name: apu_tietovirrat_2; Type: VIEW; Schema: tietok; Owner: tietokt
--

CREATE VIEW tietok.apu_tietovirrat_2 AS
 SELECT j.id AS s_id,
    j.nimi AS source,
    j.taulu AS s_type,
    tj.id AS t_id,
    tj.nimi AS target,
    tj.taulu AS t_type,
    t.linkki,
    t.tietosisalto,
    j.nimi,
    t.tietovirta_id
   FROM ((tietok.tietovirrat t
     JOIN tietok.tjs_sov j ON ((t.s_jarjestelma = j.id)))
     JOIN tietok.tjs_sov tj ON ((t.t_jarjestelma = tj.id)))
  GROUP BY j.id, j.nimi, j.taulu, tj.id, tj.nimi, tj.taulu, t.linkki, t.tietosisalto, t.tietovirta_id;


ALTER TABLE tietok.apu_tietovirrat_2 OWNER TO tietokt;

--
-- Name: tieto; Type: TABLE; Schema: tietok; Owner: tietokt
--

CREATE TABLE tietok.tieto (
    tietotunnus numeric(38,10) NOT NULL,
    looginentietovarantotunnus numeric(38,10),
    tietoryhmatunnus numeric(38,10),
    nimi character varying(150),
    koodi character varying(50),
    kuvaus character varying(4000),
    omistaja character varying(150),
    lahde character varying(150),
    tila character varying(50),
    synonyymi character varying(150),
    tietosuojataso character varying(50),
    ylempitaso numeric(38,10),
    rivitunnus numeric(38,10),
    rivitila character varying(15),
    rivimuokattupvm timestamp without time zone,
    rivimuokkaajatunnus character varying(150),
    riviluotupvm timestamp without time zone,
    document_id character varying(200)
);


ALTER TABLE tietok.tieto OWNER TO tietokt;

--
-- Name: apu_hierarkia_3_2; Type: VIEW; Schema: tietok; Owner: tietokt
--

CREATE VIEW tietok.apu_hierarkia_3_2 AS
 SELECT ft.nimi AS liittyy_1,
    ft.fyysinentietovarantotunnus AS tunnus_1,
    'fyysinentietovaranto'::text AS varanto_1,
    lt.nimi AS liittyy_2,
    lt.looginentietovarantotunnus AS tunnus_2,
    'looginentietovaranto'::text AS varanto_2,
    'tietoarkkitehtuuri'::text AS tyyppi,
    ''::text AS linkki,
    ''::text AS tietosisalto,
    ((((trunc(ft.fyysinentietovarantotunnus, 0))::text || '|'::text) || (trunc(lt.looginentietovarantotunnus, 0))::text) || '|lft'::text) AS tietovirta_id
   FROM ((tietok.looginentietovarantofyysinenti t
     JOIN tietok.looginentietovaranto lt ON ((t.looginentietovarantotunnus = lt.looginentietovarantotunnus)))
     JOIN tietok.fyysinentietovaranto ft ON ((t.fyysinentietovarantotunnus = ft.fyysinentietovarantotunnus)))
UNION
 SELECT tr.nimi AS liittyy_1,
    tr.tietoryhmatunnus AS tunnus_1,
    'tietoryhma'::text AS varanto_1,
    ptr.nimi AS liittyy_2,
    ptr.paatietoryhmatunnus AS tunnus_2,
    'paatietoryhma'::text AS varanto_2,
    'tietoarkkitehtuuri'::text AS tyyppi,
    ''::text AS linkki,
    ''::text AS tietosisalto,
    ((((trunc(tr.tietoryhmatunnus, 0))::text || '|'::text) || (trunc(ptr.paatietoryhmatunnus, 0))::text) || '|ptr'::text) AS tietovirta_id
   FROM ((tietok.tietoryhma trl
     JOIN tietok.tietoryhma tr ON ((trl.tietoryhmatunnus = tr.tietoryhmatunnus)))
     JOIN tietok.paatietoryhma ptr ON ((trl.paatietoryhmatunnus = ptr.paatietoryhmatunnus)))
UNION
 SELECT tr.nimi AS liittyy_1,
    tr.tietoryhmatunnus AS tunnus_1,
    'tietoryhma'::text AS varanto_1,
    lt.nimi AS liittyy_2,
    lt.looginentietovarantotunnus AS tunnus_2,
    'looginentietovaranto'::text AS varanto_2,
    'tietoarkkitehtuuri'::text AS tyyppi,
    ''::text AS linkki,
    ''::text AS tietosisalto,
    ((((trunc(tr.tietoryhmatunnus, 0))::text || '|'::text) || (trunc(lt.looginentietovarantotunnus, 0))::text) || '|ltv'::text) AS tietovirta_id
   FROM ((tietok.tietoryhmalooginentietovaranto trl
     JOIN tietok.tietoryhma tr ON ((trl.tietoryhmatunnus = tr.tietoryhmatunnus)))
     JOIN tietok.looginentietovaranto lt ON ((trl.looginentietovarantotunnus = lt.looginentietovarantotunnus)))
UNION
 SELECT tr.nimi AS liittyy_1,
    tr.tietoryhmatunnus AS tunnus_1,
    'tietoryhma'::text AS varanto_1,
    lt.nimi AS liittyy_2,
    lt.looginentietovarantotunnus AS tunnus_2,
    'looginentietovaranto'::text AS varanto_2,
    'tietoarkkitehtuuri'::text AS tyyppi,
    ''::text AS linkki,
    ''::text AS tietosisalto,
    ((((trunc(tr.tietoryhmatunnus, 0))::text || '|'::text) || (trunc(lt.looginentietovarantotunnus, 0))::text) || '|tr'::text) AS tietovirta_id
   FROM ((tietok.tieto trl
     JOIN tietok.tietoryhma tr ON ((trl.tietoryhmatunnus = tr.tietoryhmatunnus)))
     JOIN tietok.looginentietovaranto lt ON ((trl.looginentietovarantotunnus = lt.looginentietovarantotunnus)))
UNION
 SELECT trl.nimi AS liittyy_1,
    trl.tietotunnus AS tunnus_1,
    'tietolaji'::text AS varanto_1,
    lt.nimi AS liittyy_2,
    lt.looginentietovarantotunnus AS tunnus_2,
    'looginentietovaranto'::text AS varanto_2,
    'tietoarkkitehtuuri'::text AS tyyppi,
    ''::text AS linkki,
    ''::text AS tietosisalto,
    ((((trunc(trl.tietotunnus, 0))::text || '|'::text) || (trunc(lt.looginentietovarantotunnus, 0))::text) || '|tl'::text) AS tietovirta_id
   FROM (tietok.tieto trl
     JOIN tietok.looginentietovaranto lt ON ((trl.looginentietovarantotunnus = lt.looginentietovarantotunnus)))
UNION
 SELECT ap.source AS liittyy_1,
    ap.s_id AS tunnus_1,
    ap.s_type AS varanto_1,
    ap.target AS liittyy_2,
    ap.t_id AS tunnus_2,
    ap.t_type AS varanto_2,
    'jarjestelma'::text AS tyyppi,
    ap.linkki,
    ap.tietosisalto,
    ((ap.tietovirta_id)::text || '|ap'::text) AS tietovirta_id
   FROM tietok.apu_tietovirrat_2 ap;


ALTER TABLE tietok.apu_hierarkia_3_2 OWNER TO tietokt;

--
-- Name: apu_looginentietovaranto; Type: VIEW; Schema: tietok; Owner: tietokt
--

CREATE VIEW tietok.apu_looginentietovaranto AS
 SELECT lt.nimi AS lt_nimi,
    ft.nimi AS ft_nimi
   FROM ((tietok.looginentietovarantofyysinenti t
     JOIN tietok.looginentietovaranto lt ON ((t.looginentietovarantotunnus = lt.looginentietovarantotunnus)))
     JOIN tietok.fyysinentietovaranto ft ON ((t.fyysinentietovarantotunnus = ft.fyysinentietovarantotunnus)));


ALTER TABLE tietok.apu_looginentietovaranto OWNER TO tietokt;

--
-- Name: apu_tietoryhma; Type: VIEW; Schema: tietok; Owner: tietokt
--

CREATE VIEW tietok.apu_tietoryhma AS
 SELECT p.nimi AS ptr_nimi,
    lv.nimi AS ltv_nimi,
    s.nimi AS tietoryhma_nimi
   FROM (((tietok.tietoryhmalooginentietovaranto t
     JOIN tietok.tietoryhma s ON ((s.tietoryhmatunnus = t.tietoryhmatunnus)))
     JOIN tietok.looginentietovaranto lv ON ((lv.looginentietovarantotunnus = t.looginentietovarantotunnus)))
     JOIN tietok.paatietoryhma p ON ((p.paatietoryhmatunnus = t.paatietoryhmatunnus)));


ALTER TABLE tietok.apu_tietoryhma OWNER TO tietokt;

--
-- Name: dbmaintain_scripts; Type: TABLE; Schema: tietok; Owner: tietokt
--

-- Removed so that this file can be run with dbmaintain -Alvar

--CREATE TABLE tietok.dbmaintain_scripts (
--    file_name character varying(150),
--    file_last_modified_at numeric(38,0),
--    checksum character varying(50),
--    executed_at character varying(20),
--    succeeded numeric(38,0)
--);


--ALTER TABLE tietok.dbmaintain_scripts OWNER TO tietokt;

--
-- Name: depr_jt_nak_bkp; Type: TABLE; Schema: tietok; Owner: tietokt
--

CREATE TABLE tietok.depr_jt_nak_bkp (
    id numeric(38,10),
    sarakkeen_nimi character varying(255),
    riviluotupvm timestamp without time zone,
    rivimuokattupvm timestamp without time zone,
    rivimuokkaajatunnus character varying(255),
    document_id character varying(255),
    nimi character varying(255),
    jarjestys numeric(38,10)
);


ALTER TABLE tietok.depr_jt_nak_bkp OWNER TO tietokt;

--
-- Name: depr_jt_nakyvyys; Type: TABLE; Schema: tietok; Owner: tietokt
--

CREATE TABLE tietok.depr_jt_nakyvyys (
    id numeric(38,10) NOT NULL,
    sarakkeen_nimi character varying(255),
    riviluotupvm timestamp without time zone,
    rivimuokattupvm timestamp without time zone,
    rivimuokkaajatunnus character varying(255),
    document_id character varying(255),
    nimi character varying(255),
    jarjestys numeric(38,10)
);


ALTER TABLE tietok.depr_jt_nakyvyys OWNER TO tietokt;

--
-- Name: depr_looginentietovarantotieto; Type: TABLE; Schema: tietok; Owner: tietokt
--

CREATE TABLE tietok.depr_looginentietovarantotieto (
    rivitunnus numeric(38,10) NOT NULL,
    paatietoryhmatunnus numeric(38,10) NOT NULL,
    looginentietovarantotunnus numeric(38,10) NOT NULL,
    tietoryhmatunnus numeric(38,10) NOT NULL,
    tietotunnus numeric(38,10) NOT NULL,
    rivitila character varying(15) NOT NULL
);


ALTER TABLE tietok.depr_looginentietovarantotieto OWNER TO tietokt;

--
-- Name: depr_looginentvtoimitus; Type: TABLE; Schema: tietok; Owner: tietokt
--

CREATE TABLE tietok.depr_looginentvtoimitus (
    rivitunnus numeric(38,10) NOT NULL,
    looginentietovarantotunnus numeric(38,10) NOT NULL,
    toimitustapa character varying(150) NOT NULL,
    rivitila character varying(15) NOT NULL
);


ALTER TABLE tietok.depr_looginentvtoimitus OWNER TO tietokt;

--
-- Name: depr_paatietoryhmalahde; Type: TABLE; Schema: tietok; Owner: tietokt
--

CREATE TABLE tietok.depr_paatietoryhmalahde (
    rivitunnus numeric(38,10) NOT NULL,
    paatietoryhmatunnus numeric(38,10) NOT NULL,
    tietolahde character varying(150) NOT NULL,
    rivitila character varying(15) NOT NULL
);


ALTER TABLE tietok.depr_paatietoryhmalahde OWNER TO tietokt;

--
-- Name: depr_paatietoryhmasynonyymi; Type: TABLE; Schema: tietok; Owner: tietokt
--

CREATE TABLE tietok.depr_paatietoryhmasynonyymi (
    rivitunnus numeric(38,10) NOT NULL,
    paatietoryhmatunnus numeric(38,10) NOT NULL,
    sana character varying(255) NOT NULL,
    rivitila character varying(15) NOT NULL
);


ALTER TABLE tietok.depr_paatietoryhmasynonyymi OWNER TO tietokt;

--
-- Name: depr_par_bkp; Type: TABLE; Schema: tietok; Owner: tietokt
--

CREATE TABLE tietok.depr_par_bkp (
    avain character varying(240),
    arvo character varying(240),
    riviluotupvm timestamp without time zone,
    rivimuokattupvm timestamp without time zone,
    rivimuokkaajatunnus character varying(240),
    kuvaus character varying(240),
    otsikko character varying(240)
);


ALTER TABLE tietok.depr_par_bkp OWNER TO tietokt;

--
-- Name: depr_parametrit; Type: TABLE; Schema: tietok; Owner: tietokt
--

CREATE TABLE tietok.depr_parametrit (
    avain character varying(240) NOT NULL,
    arvo character varying(240),
    riviluotupvm timestamp without time zone,
    rivimuokattupvm timestamp without time zone,
    rivimuokkaajatunnus character varying(240),
    kuvaus character varying(240),
    otsikko character varying(240)
);


ALTER TABLE tietok.depr_parametrit OWNER TO tietokt;

--
-- Name: depr_rekisterijulkaisu; Type: TABLE; Schema: tietok; Owner: tietokt
--

CREATE TABLE tietok.depr_rekisterijulkaisu (
    julkaisutieto_rivimuokkaajatun character varying(150) NOT NULL,
    rivimuokkaajatunnus character varying(150) NOT NULL,
    rivitunnus numeric(38,10) NOT NULL,
    sahkoposti character varying(50),
    rivitila character varying(15) NOT NULL,
    riviluotupvm timestamp without time zone NOT NULL,
    rivimuokattupvm timestamp without time zone NOT NULL
);


ALTER TABLE tietok.depr_rekisterijulkaisu OWNER TO tietokt;

--
-- Name: depr_rekisterikasitemalli; Type: TABLE; Schema: tietok; Owner: tietokt
--

CREATE TABLE tietok.depr_rekisterikasitemalli (
    kasitemalli character varying(150) NOT NULL,
    kuvaus character varying(4000) NOT NULL,
    rivitunnus numeric(38,10) NOT NULL,
    rivitila character varying(15) NOT NULL,
    riviluotupvm timestamp without time zone NOT NULL,
    rivimuokkaajatunnus character varying(150) NOT NULL,
    rivimuokattupvm timestamp without time zone NOT NULL
);


ALTER TABLE tietok.depr_rekisterikasitemalli OWNER TO tietokt;

--
-- Name: depr_rekisterikayttotiheys; Type: TABLE; Schema: tietok; Owner: tietokt
--

CREATE TABLE tietok.depr_rekisterikayttotiheys (
    kayttotiheys character varying(50) NOT NULL,
    kuvaus character varying(4000) NOT NULL,
    rivitunnus numeric(38,10) NOT NULL,
    rivitila character varying(15) NOT NULL,
    riviluotupvm timestamp without time zone NOT NULL,
    rivimuokkaajatunnus character varying(150) NOT NULL,
    rivimuokattupvm timestamp without time zone NOT NULL
);


ALTER TABLE tietok.depr_rekisterikayttotiheys OWNER TO tietokt;

--
-- Name: depr_rekisterilinkki; Type: TABLE; Schema: tietok; Owner: tietokt
--

CREATE TABLE tietok.depr_rekisterilinkki (
    linkki character varying(50) NOT NULL,
    kuvaus character varying(4000) NOT NULL,
    rivitunnus numeric(38,10) NOT NULL,
    rivitila character varying(15) NOT NULL,
    riviluotupvm timestamp without time zone NOT NULL,
    rivimuokkaajatunnus character varying(150) NOT NULL,
    rivimuokattupvm timestamp without time zone NOT NULL
);


ALTER TABLE tietok.depr_rekisterilinkki OWNER TO tietokt;

--
-- Name: depr_rekisterimuokkaaja; Type: TABLE; Schema: tietok; Owner: tietokt
--

CREATE TABLE tietok.depr_rekisterimuokkaaja (
    rivimuokkaajatunnus character varying(150) NOT NULL,
    rivitunnus numeric(38,10) NOT NULL,
    sahkoposti character varying(50),
    rivitila character varying(15) NOT NULL,
    riviluotupvm timestamp without time zone NOT NULL,
    rivimuokattupvm timestamp without time zone NOT NULL,
    rivimuokkaajatunnus_1 character varying(150) NOT NULL
);


ALTER TABLE tietok.depr_rekisterimuokkaaja OWNER TO tietokt;

--
-- Name: depr_rekisteripaivitystiheys; Type: TABLE; Schema: tietok; Owner: tietokt
--

CREATE TABLE tietok.depr_rekisteripaivitystiheys (
    paivitystiheys character varying(50) NOT NULL,
    kuvaus character varying(4000) NOT NULL,
    rivitunnus numeric(38,10) NOT NULL,
    rivitila character varying(15) NOT NULL,
    riviluotupvm timestamp without time zone NOT NULL,
    rivimuokkaajatunnus character varying(150) NOT NULL,
    rivimuokattupvm timestamp without time zone NOT NULL
);


ALTER TABLE tietok.depr_rekisteripaivitystiheys OWNER TO tietokt;

--
-- Name: depr_rekisteripalvelutaso; Type: TABLE; Schema: tietok; Owner: tietokt
--

CREATE TABLE tietok.depr_rekisteripalvelutaso (
    palvelutaso character varying(50) NOT NULL,
    kuvaus character varying(4000) NOT NULL,
    rivitunnus numeric(38,10) NOT NULL,
    rivitila character varying(15) NOT NULL,
    riviluotupvm timestamp without time zone NOT NULL,
    rivimuokkaajatunnus character varying(150) NOT NULL,
    rivimuokattupvm timestamp without time zone NOT NULL
);


ALTER TABLE tietok.depr_rekisteripalvelutaso OWNER TO tietokt;

--
-- Name: depr_rekisterisanasto; Type: TABLE; Schema: tietok; Owner: tietokt
--

CREATE TABLE tietok.depr_rekisterisanasto (
    sana character varying(255) NOT NULL,
    kuvaus character varying(4000) NOT NULL,
    rivitila character varying(15) NOT NULL,
    rivitunnus numeric(38,10) NOT NULL,
    riviluotupvm timestamp without time zone NOT NULL,
    rivimuokattupvm timestamp without time zone NOT NULL,
    rivimuokkaajatunnus character varying(150) NOT NULL
);


ALTER TABLE tietok.depr_rekisterisanasto OWNER TO tietokt;

--
-- Name: depr_rekisteritietolahde; Type: TABLE; Schema: tietok; Owner: tietokt
--

CREATE TABLE tietok.depr_rekisteritietolahde (
    tietolahde character varying(150) NOT NULL,
    kuvaus character varying(4000),
    rivitunnus numeric(38,10) NOT NULL,
    rivitila character varying(15) NOT NULL,
    riviluotupvm timestamp without time zone NOT NULL,
    rivimuokattupvm timestamp without time zone NOT NULL,
    rivimuokkaajatunnus character varying(150) NOT NULL
);


ALTER TABLE tietok.depr_rekisteritietolahde OWNER TO tietokt;

--
-- Name: depr_rekisteritila; Type: TABLE; Schema: tietok; Owner: tietokt
--

CREATE TABLE tietok.depr_rekisteritila (
    tila character varying(50) NOT NULL,
    rivimuokkaajatunnus character varying(150) NOT NULL,
    kuvaus character varying(150) NOT NULL,
    rivitunnus numeric(38,10) NOT NULL,
    rivitila character varying(15) NOT NULL,
    riviluotupvm timestamp without time zone NOT NULL,
    rivimuokattupvm timestamp without time zone NOT NULL
);


ALTER TABLE tietok.depr_rekisteritila OWNER TO tietokt;

--
-- Name: depr_reklooginentietovarastoo; Type: TABLE; Schema: tietok; Owner: tietokt
--

CREATE TABLE tietok.depr_reklooginentietovarastoo (
    omistaja character varying(150) NOT NULL,
    rivitunnus numeric(38,10) NOT NULL,
    sahkoposti character varying(50),
    rivitila character varying(15) NOT NULL,
    riviluotupvm timestamp without time zone NOT NULL,
    rivimuokattupvm timestamp without time zone NOT NULL,
    rivimuokkaajatunnus character varying(150) NOT NULL
);


ALTER TABLE tietok.depr_reklooginentietovarastoo OWNER TO tietokt;

--
-- Name: depr_reklooginentietovarastos; Type: TABLE; Schema: tietok; Owner: tietokt
--

CREATE TABLE tietok.depr_reklooginentietovarastos (
    kayttaja character varying(150) NOT NULL,
    rivitunnus numeric(38,10) NOT NULL,
    sahkoposti character varying(50),
    rivitila character varying(15) NOT NULL,
    riviluotupvm timestamp without time zone NOT NULL,
    rivimuokattupvm timestamp without time zone NOT NULL,
    rivimuokkaajatunnus character varying(150) NOT NULL
);


ALTER TABLE tietok.depr_reklooginentietovarastos OWNER TO tietokt;

--
-- Name: depr_rektiedontoimistustapa; Type: TABLE; Schema: tietok; Owner: tietokt
--

CREATE TABLE tietok.depr_rektiedontoimistustapa (
    toimitustapa character varying(150) NOT NULL,
    kuvaus character varying(4000) NOT NULL,
    rivitunnus numeric(38,10) NOT NULL,
    rivitila character varying(15) NOT NULL,
    riviluotupvm timestamp without time zone NOT NULL,
    rivimuokattupvm timestamp without time zone NOT NULL,
    rivimuokkaajatunnus character varying(150) NOT NULL
);


ALTER TABLE tietok.depr_rektiedontoimistustapa OWNER TO tietokt;

--
-- Name: depr_rektietojarjestelmatila; Type: TABLE; Schema: tietok; Owner: tietokt
--

CREATE TABLE tietok.depr_rektietojarjestelmatila (
    tila character varying(50) NOT NULL,
    kuvaus character varying(4000) NOT NULL,
    rivitunnus numeric(38,10) NOT NULL,
    rivitila character varying(15) NOT NULL,
    riviluotupvm timestamp without time zone NOT NULL,
    rivimuokkaajatunnus character varying(150) NOT NULL,
    rivimuokattupvm timestamp without time zone NOT NULL
);


ALTER TABLE tietok.depr_rektietojarjestelmatila OWNER TO tietokt;

--
-- Name: depr_rektietokantateknologia; Type: TABLE; Schema: tietok; Owner: tietokt
--

CREATE TABLE tietok.depr_rektietokantateknologia (
    teknologia character varying(150) NOT NULL,
    kuvaus character varying(4000) NOT NULL,
    rivitunnus numeric(38,10) NOT NULL,
    rivitila character varying(15) NOT NULL,
    riviluotupvm timestamp without time zone NOT NULL,
    rivimuokkaajatunnus character varying(150) NOT NULL,
    rivimuokattupvm timestamp without time zone NOT NULL
);


ALTER TABLE tietok.depr_rektietokantateknologia OWNER TO tietokt;

--
-- Name: depr_rektietoryhmaomistaja; Type: TABLE; Schema: tietok; Owner: tietokt
--

CREATE TABLE tietok.depr_rektietoryhmaomistaja (
    omistaja character varying(150) NOT NULL,
    rivitunnus numeric(38,10) NOT NULL,
    sahkoposti character varying(50),
    rivitila character varying(15) NOT NULL,
    riviluotupvm timestamp without time zone NOT NULL,
    rivimuokattupvm timestamp without time zone NOT NULL,
    rivimuokkaajatunnus character varying(150) NOT NULL
);


ALTER TABLE tietok.depr_rektietoryhmaomistaja OWNER TO tietokt;

--
-- Name: depr_rektietosuojataso; Type: TABLE; Schema: tietok; Owner: tietokt
--

CREATE TABLE tietok.depr_rektietosuojataso (
    tietosuojataso character varying(50) NOT NULL,
    rivimuokkaajatunnus character varying(150) NOT NULL,
    rivitunnus numeric(38,10) NOT NULL,
    rivitila character varying(15) NOT NULL,
    riviluotupvm timestamp without time zone NOT NULL,
    rivimuokattupvm timestamp without time zone NOT NULL
);


ALTER TABLE tietok.depr_rektietosuojataso OWNER TO tietokt;

--
-- Name: depr_rektietovarastoomistaja; Type: TABLE; Schema: tietok; Owner: tietokt
--

CREATE TABLE tietok.depr_rektietovarastoomistaja (
    kayttaja character varying(150) NOT NULL,
    rivitunnus numeric(38,10) NOT NULL,
    sahkoposti character varying(50),
    rivitila character varying(15) NOT NULL,
    riviluotupvm timestamp without time zone NOT NULL,
    rivimuokattupvm timestamp without time zone NOT NULL,
    rivimuokkaajatunnus character varying(150) NOT NULL
);


ALTER TABLE tietok.depr_rektietovarastoomistaja OWNER TO tietokt;

--
-- Name: depr_tietojarjestelmapalvelu; Type: TABLE; Schema: tietok; Owner: tietokt
--

CREATE TABLE tietok.depr_tietojarjestelmapalvelu (
    tietojarjestelmapalvelutunnus numeric(38,10) NOT NULL,
    nimi character varying(150) NOT NULL,
    koodi character varying(50) NOT NULL,
    kuvaus character varying(4000) NOT NULL,
    keskeinentoiminnallisuus character varying(255) NOT NULL,
    toteuttavatietojarjestelma character varying(150) NOT NULL,
    tietojarjestelmatunnus numeric(38,10) NOT NULL,
    omistajayksikko character varying(150) NOT NULL,
    vastuuhenkilo character varying(150) NOT NULL,
    strateginenmerkitys character varying(50) NOT NULL,
    kriittisyys character varying(50) NOT NULL,
    elinkaaritila character varying(50) NOT NULL,
    palvelutaso character varying(50) NOT NULL,
    muuta character varying(2000) NOT NULL,
    ylempitaso numeric(38,10) NOT NULL,
    rivimuokattupvm timestamp without time zone NOT NULL,
    rivimuokkaajatunnus character varying(150) NOT NULL,
    riviluotupvm timestamp without time zone NOT NULL,
    rivitunnus numeric(38,10) NOT NULL,
    rivitila character varying(15) NOT NULL
);


ALTER TABLE tietok.depr_tietojarjestelmapalvelu OWNER TO tietokt;

--
-- Name: depr_tietolahde; Type: TABLE; Schema: tietok; Owner: tietokt
--

CREATE TABLE tietok.depr_tietolahde (
    rivitunnus numeric(38,10) NOT NULL,
    tietolahde character varying(150) NOT NULL,
    paatietoryhmatunnus numeric(38,10) NOT NULL,
    tietoryhmatunnus numeric(38,10) NOT NULL,
    rivitila character varying(15) NOT NULL,
    tietotunnus numeric(38,10) NOT NULL
);


ALTER TABLE tietok.depr_tietolahde OWNER TO tietokt;

--
-- Name: depr_tietoryhmalahde; Type: TABLE; Schema: tietok; Owner: tietokt
--

CREATE TABLE tietok.depr_tietoryhmalahde (
    rivitunnus numeric(38,10) NOT NULL,
    tietolahde character varying(150) NOT NULL,
    paatietoryhmatunnus numeric(38,10) NOT NULL,
    tietoryhmatunnus numeric(38,10) NOT NULL,
    rivitila character varying(15) NOT NULL
);


ALTER TABLE tietok.depr_tietoryhmalahde OWNER TO tietokt;

--
-- Name: depr_tjsalkkufyysinen; Type: TABLE; Schema: tietok; Owner: tietokt
--

CREATE TABLE tietok.depr_tjsalkkufyysinen (
    rivitunnus numeric(38,10) NOT NULL,
    fyysinentietovarantotunnus numeric(38,10) NOT NULL,
    tietojarjestelmatunnus numeric(38,10) NOT NULL,
    rivitila character varying(15) NOT NULL
);


ALTER TABLE tietok.depr_tjsalkkufyysinen OWNER TO tietokt;

--
-- Name: depr_valintalista; Type: TABLE; Schema: tietok; Owner: tietokt
--

CREATE TABLE tietok.depr_valintalista (
    code numeric(38,10),
    name character varying(255)
);


ALTER TABLE tietok.depr_valintalista OWNER TO tietokt;

--
-- Name: fyysinentietovaranto_fyysin62; Type: SEQUENCE; Schema: tietok; Owner: tietokt
--

CREATE SEQUENCE tietok.fyysinentietovaranto_fyysin62
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE tietok.fyysinentietovaranto_fyysin62 OWNER TO tietokt;

--
-- Name: fyysinentietovarantohistoria; Type: TABLE; Schema: tietok; Owner: tietokt
--

CREATE TABLE tietok.fyysinentietovarantohistoria (
    rivi_id numeric(38,10) NOT NULL,
    fyysinentietovarantotunnus numeric(38,10) NOT NULL,
    historiatyyppi character varying(6) NOT NULL,
    id character varying(50),
    nimi character varying(150) NOT NULL,
    koodi character varying(50),
    sislt character varying(4000),
    tietokantateknologia character varying(50),
    palvelutaso character varying(50),
    koko numeric(15,5),
    tietuemaara numeric(15,5),
    linkki character varying(50),
    muuta character varying(4000),
    teknologia character varying(150),
    linkki_1 character varying(150),
    ylempitaso numeric(38,10),
    rivitunnus numeric(38,10),
    rivitila character varying(15),
    rivimuokkaajatunnus character varying(150),
    riviluotupvm timestamp without time zone,
    document_id character varying(255),
    omistaja character varying(150),
    sijainti character varying(2000)
);


ALTER TABLE tietok.fyysinentietovarantohistoria OWNER TO tietokt;

--
-- Name: fyysinentv_id_seq; Type: SEQUENCE; Schema: tietok; Owner: tietokt
--

CREATE SEQUENCE tietok.fyysinentv_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE tietok.fyysinentv_id_seq OWNER TO tietokt;

--
-- Name: fyysinentv_seq; Type: SEQUENCE; Schema: tietok; Owner: tietokt
--

CREATE SEQUENCE tietok.fyysinentv_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE tietok.fyysinentv_seq OWNER TO tietokt;

--
-- Name: fyysinentvhist_id_seq; Type: SEQUENCE; Schema: tietok; Owner: tietokt
--

CREATE SEQUENCE tietok.fyysinentvhist_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE tietok.fyysinentvhist_id_seq OWNER TO tietokt;

--
-- Name: fyysinentvhist_seq; Type: SEQUENCE; Schema: tietok; Owner: tietokt
--

CREATE SEQUENCE tietok.fyysinentvhist_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE tietok.fyysinentvhist_seq OWNER TO tietokt;

--
-- Name: henkilo; Type: TABLE; Schema: tietok; Owner: tietokt
--

CREATE TABLE tietok.henkilo (
    tunnus numeric(38,10) NOT NULL,
    object_id character varying(36) NOT NULL,
    nayttonimi character varying(255),
    tunnustyyppi character varying(255),
    poistunut smallint,
    kayttajatunnus character varying(255),
    yritys character varying(255),
    yritystunnus character varying(255),
    etunimi character varying(255),
    sukunimi character varying(255),
    sahkoposti character varying(255),
    matkapuhelin character varying(255)
);


ALTER TABLE tietok.henkilo OWNER TO tietokt;

--
-- Name: henkilo_id_seq; Type: SEQUENCE; Schema: tietok; Owner: tietokt
--

CREATE SEQUENCE tietok.henkilo_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE tietok.henkilo_id_seq OWNER TO tietokt;

--
-- Name: henkilo_seq; Type: SEQUENCE; Schema: tietok; Owner: tietokt
--

CREATE SEQUENCE tietok.henkilo_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE tietok.henkilo_seq OWNER TO tietokt;

--
-- Name: henkilo_temp; Type: TABLE; Schema: tietok; Owner: tietokt
--

CREATE TABLE tietok.henkilo_temp (
    tunnus numeric(38,10) NOT NULL,
    object_id character varying(36) NOT NULL,
    nayttonimi character varying(255),
    tunnustyyppi character varying(255),
    poistunut smallint,
    kayttajatunnus character varying(255),
    yritys character varying(255),
    yritystunnus character varying(255),
    etunimi character varying(255),
    sukunimi character varying(255),
    sahkoposti character varying(255),
    matkapuhelin character varying(255)
);


ALTER TABLE tietok.henkilo_temp OWNER TO tietokt;

--
-- Name: henkilo_temp_id_seq; Type: SEQUENCE; Schema: tietok; Owner: tietokt
--

CREATE SEQUENCE tietok.henkilo_temp_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE tietok.henkilo_temp_id_seq OWNER TO tietokt;

--
-- Name: henkilo_temp_seq; Type: SEQUENCE; Schema: tietok; Owner: tietokt
--

CREATE SEQUENCE tietok.henkilo_temp_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE tietok.henkilo_temp_seq OWNER TO tietokt;

--
-- Name: jarjestelma_henkilo_rooli; Type: TABLE; Schema: tietok; Owner: tietokt
--

CREATE TABLE tietok.jarjestelma_henkilo_rooli (
    henkilo_id numeric(38,10) NOT NULL,
    rooli_id numeric(38,10) NOT NULL,
    jarjestelma_id numeric(38,10) NOT NULL,
    rivitunnus numeric(38,10) NOT NULL
);


ALTER TABLE tietok.jarjestelma_henkilo_rooli OWNER TO tietokt;

--
-- Name: jarjestelma_henkilo_rooli_hist; Type: TABLE; Schema: tietok; Owner: tietokt
--

CREATE TABLE tietok.jarjestelma_henkilo_rooli_hist (
    rivitunnus numeric(38,10) NOT NULL,
    henkilo_id numeric(38,10) NOT NULL,
    rooli_id numeric(38,10) NOT NULL,
    jarjestelma_id numeric(38,10) NOT NULL,
    historiatyyppi character varying(6) NOT NULL,
    riviluotupvm timestamp without time zone,
    rivimuokkaajatunnus character varying(150)
);


ALTER TABLE tietok.jarjestelma_henkilo_rooli_hist OWNER TO tietokt;

--
-- Name: jarjestelma_id_seq; Type: SEQUENCE; Schema: tietok; Owner: tietokt
--

CREATE SEQUENCE tietok.jarjestelma_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE tietok.jarjestelma_id_seq OWNER TO tietokt;

--
-- Name: jarjestelma_seq; Type: SEQUENCE; Schema: tietok; Owner: tietokt
--

CREATE SEQUENCE tietok.jarjestelma_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE tietok.jarjestelma_seq OWNER TO tietokt;

--
-- Name: jarjestelmaalue; Type: VIEW; Schema: tietok; Owner: tietokt
--

CREATE VIEW tietok.jarjestelmaalue AS
 SELECT ak.nimi,
    COALESCE(concat(upper(substr((ak.nimi)::text, 1, 1)), lower(substr((ak.nimi)::text, 2, length((ak.nimi)::text)))), 'kaikki'::text) AS n_nimi
   FROM ( SELECT DISTINCT tietojarjestelmasalkku.jarjestelmaalue AS nimi
           FROM tietok.tietojarjestelmasalkku) ak;


ALTER TABLE tietok.jarjestelmaalue OWNER TO tietokt;

--
-- Name: jarjestelmahist_id_seq; Type: SEQUENCE; Schema: tietok; Owner: tietokt
--

CREATE SEQUENCE tietok.jarjestelmahist_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE tietok.jarjestelmahist_id_seq OWNER TO tietokt;

--
-- Name: jarjestelmahist_seq; Type: SEQUENCE; Schema: tietok; Owner: tietokt
--

CREATE SEQUENCE tietok.jarjestelmahist_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE tietok.jarjestelmahist_seq OWNER TO tietokt;

--
-- Name: jarjestelmalinkkaus; Type: TABLE; Schema: tietok; Owner: tietokt
--

CREATE TABLE tietok.jarjestelmalinkkaus (
    rivitunnus numeric(38,10) NOT NULL,
    tietojarjestelmatunnus numeric(38,10) NOT NULL,
    linkattava_id numeric(38,10) NOT NULL,
    suunta character varying(255),
    tyyppi character varying(255),
    kuvaus character varying(255),
    rivitila character varying(15) NOT NULL,
    tietojarjestelmapalvelutunnus numeric(38,10)
);


ALTER TABLE tietok.jarjestelmalinkkaus OWNER TO tietokt;

--
-- Name: jarjestelmalinkkaushistoria; Type: TABLE; Schema: tietok; Owner: tietokt
--

CREATE TABLE tietok.jarjestelmalinkkaushistoria (
    rivitunnus numeric(38,10) NOT NULL,
    tietojarjestelmatunnus numeric(38,10) NOT NULL,
    linkattava_id numeric(38,10) NOT NULL,
    historiatyyppi character varying(6) NOT NULL,
    rivitila character varying(15) NOT NULL,
    riviluotupvm timestamp without time zone,
    rivimuokkaajatunnus character varying(150),
    suunta character varying(255),
    tyyppi character varying(255),
    kuvaus character varying(255),
    tietojarjestelmapalvelutunnus numeric(38,10)
);


ALTER TABLE tietok.jarjestelmalinkkaushistoria OWNER TO tietokt;

--
-- Name: jarjestelmalooginen; Type: TABLE; Schema: tietok; Owner: tietokt
--

CREATE TABLE tietok.jarjestelmalooginen (
    rivitunnus numeric(38,10) NOT NULL,
    jarjestelmatunnus numeric(38,10),
    looginentunnus numeric(38,10)
);


ALTER TABLE tietok.jarjestelmalooginen OWNER TO tietokt;

--
-- Name: jarjestelmalooginen_id_seq; Type: SEQUENCE; Schema: tietok; Owner: tietokt
--

CREATE SEQUENCE tietok.jarjestelmalooginen_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE tietok.jarjestelmalooginen_id_seq OWNER TO tietokt;

--
-- Name: jarjestelmalooginenhist_id_seq; Type: SEQUENCE; Schema: tietok; Owner: tietokt
--

CREATE SEQUENCE tietok.jarjestelmalooginenhist_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE tietok.jarjestelmalooginenhist_id_seq OWNER TO tietokt;

--
-- Name: jarjestelmalooginenhistoria; Type: TABLE; Schema: tietok; Owner: tietokt
--

CREATE TABLE tietok.jarjestelmalooginenhistoria (
    rivi_id numeric(38,10) NOT NULL,
    jarjestelmatunnus numeric(38,10) NOT NULL,
    looginentunnus character varying(6),
    riviluotupvm timestamp without time zone,
    historiatyyppi character varying(6),
    rivimuokkaajatunnus character varying(150)
);


ALTER TABLE tietok.jarjestelmalooginenhistoria OWNER TO tietokt;

--
-- Name: jarjestelmatieto_k_bkp; Type: TABLE; Schema: tietok; Owner: tietokt
--

CREATE TABLE tietok.jarjestelmatieto_k_bkp (
    nimi character varying(255),
    sarakkeen_nimi character varying(255)
);


ALTER TABLE tietok.jarjestelmatieto_k_bkp OWNER TO tietokt;

--
-- Name: jarjestelmatieto_kentat; Type: TABLE; Schema: tietok; Owner: tietokt
--

CREATE TABLE tietok.jarjestelmatieto_kentat (
    nimi character varying(255) NOT NULL,
    sarakkeen_nimi character varying(255),
    jarjestys numeric(38,10)
);


ALTER TABLE tietok.jarjestelmatieto_kentat OWNER TO tietokt;

--
-- Name: jarjestelmatyyppi; Type: VIEW; Schema: tietok; Owner: tietokt
--

CREATE VIEW tietok.jarjestelmatyyppi AS
 SELECT ak.nimi,
    COALESCE(concat(upper(substr((ak.nimi)::text, 1, 1)), lower(substr((ak.nimi)::text, 2, length((ak.nimi)::text)))), 'kaikki'::text) AS n_nimi
   FROM ( SELECT DISTINCT tietojarjestelmasalkku.jarjestelmatyyppi AS nimi
           FROM tietok.tietojarjestelmasalkku) ak;


ALTER TABLE tietok.jarjestelmatyyppi OWNER TO tietokt;

--
-- Name: jarjhenkroolihist_id_seq; Type: SEQUENCE; Schema: tietok; Owner: tietokt
--

CREATE SEQUENCE tietok.jarjhenkroolihist_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE tietok.jarjhenkroolihist_id_seq OWNER TO tietokt;

--
-- Name: jarjhenkroolihist_seq; Type: SEQUENCE; Schema: tietok; Owner: tietokt
--

CREATE SEQUENCE tietok.jarjhenkroolihist_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE tietok.jarjhenkroolihist_seq OWNER TO tietokt;

--
-- Name: joinhuomautus; Type: TABLE; Schema: tietok; Owner: tietokt
--

CREATE TABLE tietok.joinhuomautus (
    rivitunnus numeric(38,10) NOT NULL,
    termilomaketunnus numeric(38,10),
    huomautus character varying(255),
    rivitila character varying(6)
);


ALTER TABLE tietok.joinhuomautus OWNER TO tietokt;

--
-- Name: joinhuomautus_id_seq; Type: SEQUENCE; Schema: tietok; Owner: tietokt
--

CREATE SEQUENCE tietok.joinhuomautus_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE tietok.joinhuomautus_id_seq OWNER TO tietokt;

--
-- Name: joinhuomautushist_id_seq; Type: SEQUENCE; Schema: tietok; Owner: tietokt
--

CREATE SEQUENCE tietok.joinhuomautushist_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE tietok.joinhuomautushist_id_seq OWNER TO tietokt;

--
-- Name: joinhuomautushistoria; Type: TABLE; Schema: tietok; Owner: tietokt
--

CREATE TABLE tietok.joinhuomautushistoria (
    rivi_id numeric(38,10) NOT NULL,
    termilomaketunnus numeric(38,10),
    huomautus character varying(255),
    historiatyyppi character varying(6),
    riviluotupvm timestamp without time zone,
    rivimuokkaajatunnus character varying(150)
);


ALTER TABLE tietok.joinhuomautushistoria OWNER TO tietokt;

--
-- Name: joinjarjestelmalinkkaus_id_seq; Type: SEQUENCE; Schema: tietok; Owner: tietokt
--

CREATE SEQUENCE tietok.joinjarjestelmalinkkaus_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE tietok.joinjarjestelmalinkkaus_id_seq OWNER TO tietokt;

--
-- Name: joinjarjestelmalinkkaus_seq; Type: SEQUENCE; Schema: tietok; Owner: tietokt
--

CREATE SEQUENCE tietok.joinjarjestelmalinkkaus_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE tietok.joinjarjestelmalinkkaus_seq OWNER TO tietokt;

--
-- Name: joinjarjhenkrooli_id_seq; Type: SEQUENCE; Schema: tietok; Owner: tietokt
--

CREATE SEQUENCE tietok.joinjarjhenkrooli_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE tietok.joinjarjhenkrooli_id_seq OWNER TO tietokt;

--
-- Name: joinjarjhenkrooli_seq; Type: SEQUENCE; Schema: tietok; Owner: tietokt
--

CREATE SEQUENCE tietok.joinjarjhenkrooli_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE tietok.joinjarjhenkrooli_seq OWNER TO tietokt;

--
-- Name: joinjlinkhist_id_seq; Type: SEQUENCE; Schema: tietok; Owner: tietokt
--

CREATE SEQUENCE tietok.joinjlinkhist_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE tietok.joinjlinkhist_id_seq OWNER TO tietokt;

--
-- Name: joinjlinkhist_seq; Type: SEQUENCE; Schema: tietok; Owner: tietokt
--

CREATE SEQUENCE tietok.joinjlinkhist_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE tietok.joinjlinkhist_seq OWNER TO tietokt;

--
-- Name: joinloogfyyshistoria_id_seq; Type: SEQUENCE; Schema: tietok; Owner: tietokt
--

CREATE SEQUENCE tietok.joinloogfyyshistoria_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE tietok.joinloogfyyshistoria_id_seq OWNER TO tietokt;

--
-- Name: joinloogfyyshistoria_seq; Type: SEQUENCE; Schema: tietok; Owner: tietokt
--

CREATE SEQUENCE tietok.joinloogfyyshistoria_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE tietok.joinloogfyyshistoria_seq OWNER TO tietokt;

--
-- Name: joinlooginenfyysinen_id_seq; Type: SEQUENCE; Schema: tietok; Owner: tietokt
--

CREATE SEQUENCE tietok.joinlooginenfyysinen_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE tietok.joinlooginenfyysinen_id_seq OWNER TO tietokt;

--
-- Name: joinlooginenfyysinen_seq; Type: SEQUENCE; Schema: tietok; Owner: tietokt
--

CREATE SEQUENCE tietok.joinlooginenfyysinen_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE tietok.joinlooginenfyysinen_seq OWNER TO tietokt;

--
-- Name: joinsovellushenkrooli_id_seq; Type: SEQUENCE; Schema: tietok; Owner: tietokt
--

CREATE SEQUENCE tietok.joinsovellushenkrooli_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE tietok.joinsovellushenkrooli_id_seq OWNER TO tietokt;

--
-- Name: joinsovellushenkrooli_seq; Type: SEQUENCE; Schema: tietok; Owner: tietokt
--

CREATE SEQUENCE tietok.joinsovellushenkrooli_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE tietok.joinsovellushenkrooli_seq OWNER TO tietokt;

--
-- Name: jointietolajilooginen_id_seq; Type: SEQUENCE; Schema: tietok; Owner: tietokt
--

CREATE SEQUENCE tietok.jointietolajilooginen_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE tietok.jointietolajilooginen_id_seq OWNER TO tietokt;

--
-- Name: jointietolajilooginen_seq; Type: SEQUENCE; Schema: tietok; Owner: tietokt
--

CREATE SEQUENCE tietok.jointietolajilooginen_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE tietok.jointietolajilooginen_seq OWNER TO tietokt;

--
-- Name: jointietolajitietoryhma_id_seq; Type: SEQUENCE; Schema: tietok; Owner: tietokt
--

CREATE SEQUENCE tietok.jointietolajitietoryhma_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE tietok.jointietolajitietoryhma_id_seq OWNER TO tietokt;

--
-- Name: jointietolajitietoryhma_seq; Type: SEQUENCE; Schema: tietok; Owner: tietokt
--

CREATE SEQUENCE tietok.jointietolajitietoryhma_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE tietok.jointietolajitietoryhma_seq OWNER TO tietokt;

--
-- Name: jointietolooghistoria_id_seq; Type: SEQUENCE; Schema: tietok; Owner: tietokt
--

CREATE SEQUENCE tietok.jointietolooghistoria_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE tietok.jointietolooghistoria_id_seq OWNER TO tietokt;

--
-- Name: jointietolooghistoria_seq; Type: SEQUENCE; Schema: tietok; Owner: tietokt
--

CREATE SEQUENCE tietok.jointietolooghistoria_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE tietok.jointietolooghistoria_seq OWNER TO tietokt;

--
-- Name: jointietorpaatietor_id_seq; Type: SEQUENCE; Schema: tietok; Owner: tietokt
--

CREATE SEQUENCE tietok.jointietorpaatietor_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE tietok.jointietorpaatietor_id_seq OWNER TO tietokt;

--
-- Name: jointietorpaatietor_seq; Type: SEQUENCE; Schema: tietok; Owner: tietokt
--

CREATE SEQUENCE tietok.jointietorpaatietor_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE tietok.jointietorpaatietor_seq OWNER TO tietokt;

--
-- Name: jtietorpaatietorhist_id_seq; Type: SEQUENCE; Schema: tietok; Owner: tietokt
--

CREATE SEQUENCE tietok.jtietorpaatietorhist_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE tietok.jtietorpaatietorhist_id_seq OWNER TO tietokt;

--
-- Name: jtietorpaatietorhist_seq; Type: SEQUENCE; Schema: tietok; Owner: tietokt
--

CREATE SEQUENCE tietok.jtietorpaatietorhist_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE tietok.jtietorpaatietorhist_seq OWNER TO tietokt;

--
-- Name: jtietotietoryhmahist_id_seq; Type: SEQUENCE; Schema: tietok; Owner: tietokt
--

CREATE SEQUENCE tietok.jtietotietoryhmahist_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE tietok.jtietotietoryhmahist_id_seq OWNER TO tietokt;

--
-- Name: jtietotietoryhmahist_seq; Type: SEQUENCE; Schema: tietok; Owner: tietokt
--

CREATE SEQUENCE tietok.jtietotietoryhmahist_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE tietok.jtietotietoryhmahist_seq OWNER TO tietokt;

--
-- Name: looginen_kasite_arvo; Type: TABLE; Schema: tietok; Owner: tietokt
--

CREATE TABLE tietok.looginen_kasite_arvo (
    kasite_wid numeric(38,0) NOT NULL,
    kasite character varying(100),
    arvo character varying(200),
    aluekoodi character varying(6)
);


ALTER TABLE tietok.looginen_kasite_arvo OWNER TO tietokt;

--
-- Name: looginenfyysinenhistoria; Type: TABLE; Schema: tietok; Owner: tietokt
--

CREATE TABLE tietok.looginenfyysinenhistoria (
    rivitunnus numeric(38,10) NOT NULL,
    fyysinentietovarantotunnus numeric(38,10) NOT NULL,
    looginentietovarantotunnus numeric(38,10) NOT NULL,
    historiatyyppi character varying(6) NOT NULL,
    rivitila character varying(15) NOT NULL,
    riviluotupvm timestamp without time zone,
    rivimuokkaajatunnus character varying(150)
);


ALTER TABLE tietok.looginenfyysinenhistoria OWNER TO tietokt;

--
-- Name: looginentietovaranto_loogin742; Type: SEQUENCE; Schema: tietok; Owner: tietokt
--

CREATE SEQUENCE tietok.looginentietovaranto_loogin742
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE tietok.looginentietovaranto_loogin742 OWNER TO tietokt;

--
-- Name: looginentietovarantofyysine427; Type: SEQUENCE; Schema: tietok; Owner: tietokt
--

CREATE SEQUENCE tietok.looginentietovarantofyysine427
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE tietok.looginentietovarantofyysine427 OWNER TO tietokt;

--
-- Name: looginentietovarantohistoria; Type: TABLE; Schema: tietok; Owner: tietokt
--

CREATE TABLE tietok.looginentietovarantohistoria (
    rivi_id numeric(38,10) NOT NULL,
    looginentietovarantotunnus numeric(38,10) NOT NULL,
    historiatyyppi character varying(6) NOT NULL,
    nimi character varying(255),
    koodi character varying(50),
    omistaja character varying(255),
    kuvaus character varying(4000),
    kuvauskeskeinentieto character varying(4000),
    kuvausvarastointifyysinen character varying(2000),
    paivitystiheys character varying(255),
    kuvauskayttaja character varying(2000),
    kayttaja character varying(150),
    kuvaustoimitus character varying(4000),
    kasitemalli character varying(150),
    ylempitaso numeric(38,10),
    rivitunnus numeric(38,10),
    rivitila character varying(15),
    rivimuokkaajatunnus character varying(150),
    riviluotupvm timestamp without time zone,
    document_id character varying(255),
    alue character varying(2000)
);


ALTER TABLE tietok.looginentietovarantohistoria OWNER TO tietokt;

--
-- Name: looginentv_id_seq; Type: SEQUENCE; Schema: tietok; Owner: tietokt
--

CREATE SEQUENCE tietok.looginentv_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE tietok.looginentv_id_seq OWNER TO tietokt;

--
-- Name: looginentv_seq; Type: SEQUENCE; Schema: tietok; Owner: tietokt
--

CREATE SEQUENCE tietok.looginentv_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE tietok.looginentv_seq OWNER TO tietokt;

--
-- Name: looginentvhist_id_seq; Type: SEQUENCE; Schema: tietok; Owner: tietokt
--

CREATE SEQUENCE tietok.looginentvhist_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE tietok.looginentvhist_id_seq OWNER TO tietokt;

--
-- Name: looginentvhist_seq; Type: SEQUENCE; Schema: tietok; Owner: tietokt
--

CREATE SEQUENCE tietok.looginentvhist_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE tietok.looginentvhist_seq OWNER TO tietokt;

--
-- Name: organisaatio; Type: TABLE; Schema: tietok; Owner: tietokt
--

CREATE TABLE tietok.organisaatio (
    organisaatiotunnus numeric(38,10) NOT NULL,
    nimi character varying(225),
    osoite character varying(255),
    sahkoposti character varying(255),
    puhelinnumero character varying(255),
    rivimuokkaajatunnus character varying(150),
    riviluotupvm timestamp without time zone,
    rivimuokattupvm timestamp without time zone
);


ALTER TABLE tietok.organisaatio OWNER TO tietokt;

--
-- Name: organisaatio_id_seq; Type: SEQUENCE; Schema: tietok; Owner: tietokt
--

CREATE SEQUENCE tietok.organisaatio_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE tietok.organisaatio_id_seq OWNER TO tietokt;

--
-- Name: organisaatiohistoria; Type: TABLE; Schema: tietok; Owner: tietokt
--

CREATE TABLE tietok.organisaatiohistoria (
    rivi_id numeric(38,10) NOT NULL,
    historiatyyppi character varying(6),
    organisaatiotunnus numeric(38,10),
    nimi character varying(225),
    osoite character varying(255),
    sahkoposti character varying(255),
    puhelinnumero character varying(255),
    rivimuokkaajatunnus character varying(150),
    riviluotupvm timestamp without time zone,
    rivimuokattupvm timestamp without time zone
);


ALTER TABLE tietok.organisaatiohistoria OWNER TO tietokt;

--
-- Name: organisaatiohistoria_id_seq; Type: SEQUENCE; Schema: tietok; Owner: tietokt
--

CREATE SEQUENCE tietok.organisaatiohistoria_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE tietok.organisaatiohistoria_id_seq OWNER TO tietokt;

--
-- Name: paatietoryhma_id_seq; Type: SEQUENCE; Schema: tietok; Owner: tietokt
--

CREATE SEQUENCE tietok.paatietoryhma_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE tietok.paatietoryhma_id_seq OWNER TO tietokt;

--
-- Name: paatietoryhma_paatietoryhma612; Type: SEQUENCE; Schema: tietok; Owner: tietokt
--

CREATE SEQUENCE tietok.paatietoryhma_paatietoryhma612
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE tietok.paatietoryhma_paatietoryhma612 OWNER TO tietokt;

--
-- Name: paatietoryhma_seq; Type: SEQUENCE; Schema: tietok; Owner: tietokt
--

CREATE SEQUENCE tietok.paatietoryhma_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE tietok.paatietoryhma_seq OWNER TO tietokt;

--
-- Name: paatietoryhmahist_id_seq; Type: SEQUENCE; Schema: tietok; Owner: tietokt
--

CREATE SEQUENCE tietok.paatietoryhmahist_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE tietok.paatietoryhmahist_id_seq OWNER TO tietokt;

--
-- Name: paatietoryhmahist_seq; Type: SEQUENCE; Schema: tietok; Owner: tietokt
--

CREATE SEQUENCE tietok.paatietoryhmahist_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE tietok.paatietoryhmahist_seq OWNER TO tietokt;

--
-- Name: paatietoryhmahistoria; Type: TABLE; Schema: tietok; Owner: tietokt
--

CREATE TABLE tietok.paatietoryhmahistoria (
    rivi_id numeric(38,10) NOT NULL,
    paatietoryhmatunnus numeric(38,10) NOT NULL,
    historiatyyppi character varying(6) NOT NULL,
    nimi character varying(255) NOT NULL,
    koodi character varying(50),
    kuvaus character varying(4000),
    omistaja character varying(150),
    lahde character varying(150),
    tila character varying(50),
    synonyymi character varying(150),
    tietosuojataso character varying(50),
    julkaisutieto character varying(150),
    julkaisuhuomio character varying(2000),
    ylempitaso numeric(38,10),
    rivitunnus numeric(38,10),
    rivitila character varying(15),
    riviluotupvm timestamp without time zone,
    rivimuokkaajatunnus character varying(150),
    document_id character varying(255)
);


ALTER TABLE tietok.paatietoryhmahistoria OWNER TO tietokt;

--
-- Name: paatietoryhmalahde_rivitunn376; Type: SEQUENCE; Schema: tietok; Owner: tietokt
--

CREATE SEQUENCE tietok.paatietoryhmalahde_rivitunn376
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE tietok.paatietoryhmalahde_rivitunn376 OWNER TO tietokt;

--
-- Name: paatietoryhmalahde_rivitunn377; Type: SEQUENCE; Schema: tietok; Owner: tietokt
--

CREATE SEQUENCE tietok.paatietoryhmalahde_rivitunn377
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE tietok.paatietoryhmalahde_rivitunn377 OWNER TO tietokt;

--
-- Name: paatietoryhmalahde_rivitunn379; Type: SEQUENCE; Schema: tietok; Owner: tietokt
--

CREATE SEQUENCE tietok.paatietoryhmalahde_rivitunn379
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE tietok.paatietoryhmalahde_rivitunn379 OWNER TO tietokt;

--
-- Name: paatietoryhmalahde_rivitunn381; Type: SEQUENCE; Schema: tietok; Owner: tietokt
--

CREATE SEQUENCE tietok.paatietoryhmalahde_rivitunn381
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE tietok.paatietoryhmalahde_rivitunn381 OWNER TO tietokt;

--
-- Name: paatietoryhmalahde_rivitunn383; Type: SEQUENCE; Schema: tietok; Owner: tietokt
--

CREATE SEQUENCE tietok.paatietoryhmalahde_rivitunn383
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE tietok.paatietoryhmalahde_rivitunn383 OWNER TO tietokt;

--
-- Name: paatietoryhmalahde_rivitunn574; Type: SEQUENCE; Schema: tietok; Owner: tietokt
--

CREATE SEQUENCE tietok.paatietoryhmalahde_rivitunn574
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE tietok.paatietoryhmalahde_rivitunn574 OWNER TO tietokt;

--
-- Name: palvelu; Type: TABLE; Schema: tietok; Owner: tietokt
--

CREATE TABLE tietok.palvelu (
    palvelutunnus numeric(38,10) NOT NULL,
    ylataso character varying(255),
    otsikko character varying(255),
    nimi character varying(4000),
    kuvaus character varying(4000),
    vastuuhenkilo character varying(4000),
    asiakkaat character varying(4000),
    saatavuus character varying(4000),
    vasteajat character varying(4000),
    ohje_pt character varying(4000),
    ohjeistus character varying(4000),
    ohjesaannot character varying(4000),
    riviluotupvm timestamp without time zone,
    rivimuokattupvm timestamp without time zone,
    rivimuokkaajatunnus character varying(150),
    document_id character varying(255)
);


ALTER TABLE tietok.palvelu OWNER TO tietokt;

--
-- Name: palvelu_id_seq; Type: SEQUENCE; Schema: tietok; Owner: tietokt
--

CREATE SEQUENCE tietok.palvelu_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE tietok.palvelu_id_seq OWNER TO tietokt;

--
-- Name: palvelu_kasite_arvo; Type: TABLE; Schema: tietok; Owner: tietokt
--

CREATE TABLE tietok.palvelu_kasite_arvo (
    kasite_id numeric(38,0) NOT NULL,
    kasite character varying(100),
    arvo character varying(200)
);


ALTER TABLE tietok.palvelu_kasite_arvo OWNER TO tietokt;

--
-- Name: palvelu_seq; Type: SEQUENCE; Schema: tietok; Owner: tietokt
--

CREATE SEQUENCE tietok.palvelu_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE tietok.palvelu_seq OWNER TO tietokt;

--
-- Name: palveluhist_id_seq; Type: SEQUENCE; Schema: tietok; Owner: tietokt
--

CREATE SEQUENCE tietok.palveluhist_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE tietok.palveluhist_id_seq OWNER TO tietokt;

--
-- Name: palveluhist_seq; Type: SEQUENCE; Schema: tietok; Owner: tietokt
--

CREATE SEQUENCE tietok.palveluhist_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE tietok.palveluhist_seq OWNER TO tietokt;

--
-- Name: palveluhistoria; Type: TABLE; Schema: tietok; Owner: tietokt
--

CREATE TABLE tietok.palveluhistoria (
    rivi_id numeric(38,10) NOT NULL,
    palvelutunnus numeric(38,10) NOT NULL,
    historiatyyppi character varying(6) NOT NULL,
    ylataso character varying(255),
    otsikko character varying(255),
    nimi character varying(4000),
    kuvaus character varying(4000),
    vastuuhenkilo character varying(4000),
    asiakkaat character varying(4000),
    saatavuus character varying(4000),
    vasteajat character varying(4000),
    ohje_pt character varying(4000),
    ohjeistus character varying(4000),
    ohjesaannot character varying(4000),
    riviluotupvm timestamp without time zone NOT NULL,
    rivimuokkaajatunnus character varying(150),
    document_id character varying(255)
);


ALTER TABLE tietok.palveluhistoria OWNER TO tietokt;

--
-- Name: palvelukatalogi_kasite_arvo; Type: TABLE; Schema: tietok; Owner: tietokt
--

CREATE TABLE tietok.palvelukatalogi_kasite_arvo (
    kasite_id numeric(38,0) NOT NULL,
    kasite character varying(100),
    arvo character varying(200),
    ylataso character varying(200)
);


ALTER TABLE tietok.palvelukatalogi_kasite_arvo OWNER TO tietokt;

--
-- Name: palvelukatalogi_kentat; Type: TABLE; Schema: tietok; Owner: tietokt
--

CREATE TABLE tietok.palvelukatalogi_kentat (
    nimi character varying(255) NOT NULL,
    sarakkeen_nimi character varying(255),
    jarjestys numeric(38,10)
);


ALTER TABLE tietok.palvelukatalogi_kentat OWNER TO tietokt;

--
-- Name: palveluotsikko; Type: VIEW; Schema: tietok; Owner: tietokt
--

CREATE VIEW tietok.palveluotsikko AS
 SELECT ak.nimi,
    COALESCE(concat(upper(substr((ak.nimi)::text, 1, 1)), lower(substr((ak.nimi)::text, 2, length((ak.nimi)::text)))), 'kaikki'::text) AS n_nimi
   FROM ( SELECT DISTINCT palvelu.otsikko AS nimi
           FROM tietok.palvelu) ak
  ORDER BY COALESCE(concat(upper(substr((ak.nimi)::text, 1, 1)), lower(substr((ak.nimi)::text, 2, length((ak.nimi)::text)))), 'kaikki'::text);


ALTER TABLE tietok.palveluotsikko OWNER TO tietokt;

--
-- Name: palveluylataso; Type: VIEW; Schema: tietok; Owner: tietokt
--

CREATE VIEW tietok.palveluylataso AS
 SELECT ak.nimi,
    COALESCE(concat(upper(substr((ak.nimi)::text, 1, 1)), lower(substr((ak.nimi)::text, 2, length((ak.nimi)::text)))), 'kaikki'::text) AS n_nimi
   FROM ( SELECT DISTINCT palvelu.ylataso AS nimi
           FROM tietok.palvelu) ak
  ORDER BY COALESCE(concat(upper(substr((ak.nimi)::text, 1, 1)), lower(substr((ak.nimi)::text, 2, length((ak.nimi)::text)))), 'kaikki'::text);


ALTER TABLE tietok.palveluylataso OWNER TO tietokt;

--
-- Name: rekisterijulkaisu_rivitunnu106; Type: SEQUENCE; Schema: tietok; Owner: tietokt
--

CREATE SEQUENCE tietok.rekisterijulkaisu_rivitunnu106
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE tietok.rekisterijulkaisu_rivitunnu106 OWNER TO tietokt;

--
-- Name: rekisterikayttotiheys_rivit326; Type: SEQUENCE; Schema: tietok; Owner: tietokt
--

CREATE SEQUENCE tietok.rekisterikayttotiheys_rivit326
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE tietok.rekisterikayttotiheys_rivit326 OWNER TO tietokt;

--
-- Name: rekisterilinkki_rivitunnus_seq; Type: SEQUENCE; Schema: tietok; Owner: tietokt
--

CREATE SEQUENCE tietok.rekisterilinkki_rivitunnus_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE tietok.rekisterilinkki_rivitunnus_seq OWNER TO tietokt;

--
-- Name: rekisterilooginentietovaras113; Type: SEQUENCE; Schema: tietok; Owner: tietokt
--

CREATE SEQUENCE tietok.rekisterilooginentietovaras113
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE tietok.rekisterilooginentietovaras113 OWNER TO tietokt;

--
-- Name: rekisterilooginentietovaras525; Type: SEQUENCE; Schema: tietok; Owner: tietokt
--

CREATE SEQUENCE tietok.rekisterilooginentietovaras525
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE tietok.rekisterilooginentietovaras525 OWNER TO tietokt;

--
-- Name: rekisteripalvelutaso_rivitu222; Type: SEQUENCE; Schema: tietok; Owner: tietokt
--

CREATE SEQUENCE tietok.rekisteripalvelutaso_rivitu222
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE tietok.rekisteripalvelutaso_rivitu222 OWNER TO tietokt;

--
-- Name: rekisteritietojarjestelmati301; Type: SEQUENCE; Schema: tietok; Owner: tietokt
--

CREATE SEQUENCE tietok.rekisteritietojarjestelmati301
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE tietok.rekisteritietojarjestelmati301 OWNER TO tietokt;

--
-- Name: rekisteritietokantateknolog539; Type: SEQUENCE; Schema: tietok; Owner: tietokt
--

CREATE SEQUENCE tietok.rekisteritietokantateknolog539
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE tietok.rekisteritietokantateknolog539 OWNER TO tietokt;

--
-- Name: rekisteritietolahde_rivitun386; Type: SEQUENCE; Schema: tietok; Owner: tietokt
--

CREATE SEQUENCE tietok.rekisteritietolahde_rivitun386
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE tietok.rekisteritietolahde_rivitun386 OWNER TO tietokt;

--
-- Name: rekisteritietolahde_rivitun388; Type: SEQUENCE; Schema: tietok; Owner: tietokt
--

CREATE SEQUENCE tietok.rekisteritietolahde_rivitun388
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE tietok.rekisteritietolahde_rivitun388 OWNER TO tietokt;

--
-- Name: rekisteritietolahde_rivitun389; Type: SEQUENCE; Schema: tietok; Owner: tietokt
--

CREATE SEQUENCE tietok.rekisteritietolahde_rivitun389
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE tietok.rekisteritietolahde_rivitun389 OWNER TO tietokt;

--
-- Name: rekisteritietoryhmaomistaja952; Type: SEQUENCE; Schema: tietok; Owner: tietokt
--

CREATE SEQUENCE tietok.rekisteritietoryhmaomistaja952
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE tietok.rekisteritietoryhmaomistaja952 OWNER TO tietokt;

--
-- Name: rekisteritietovarastoomista937; Type: SEQUENCE; Schema: tietok; Owner: tietokt
--

CREATE SEQUENCE tietok.rekisteritietovarastoomista937
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE tietok.rekisteritietovarastoomista937 OWNER TO tietokt;

--
-- Name: rekisteritila_rivitunnus_seq; Type: SEQUENCE; Schema: tietok; Owner: tietokt
--

CREATE SEQUENCE tietok.rekisteritila_rivitunnus_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE tietok.rekisteritila_rivitunnus_seq OWNER TO tietokt;

--
-- Name: rooli; Type: TABLE; Schema: tietok; Owner: tietokt
--

CREATE TABLE tietok.rooli (
    tunnus numeric(38,10) NOT NULL,
    nimi character varying(255) NOT NULL
);


ALTER TABLE tietok.rooli OWNER TO tietokt;

--
-- Name: rooli_id_seq; Type: SEQUENCE; Schema: tietok; Owner: tietokt
--

CREATE SEQUENCE tietok.rooli_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE tietok.rooli_id_seq OWNER TO tietokt;

--
-- Name: rooli_seq; Type: SEQUENCE; Schema: tietok; Owner: tietokt
--

CREATE SEQUENCE tietok.rooli_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE tietok.rooli_seq OWNER TO tietokt;

--
-- Name: sovellus_henkilo_rooli; Type: TABLE; Schema: tietok; Owner: tietokt
--

CREATE TABLE tietok.sovellus_henkilo_rooli (
    henkilo_id numeric(38,10) NOT NULL,
    rooli_id numeric(38,10) NOT NULL,
    sovellus_id numeric(38,10) NOT NULL,
    rivitunnus numeric(38,10) NOT NULL
);


ALTER TABLE tietok.sovellus_henkilo_rooli OWNER TO tietokt;

--
-- Name: sovellus_henkilo_rooli_hist; Type: TABLE; Schema: tietok; Owner: tietokt
--

CREATE TABLE tietok.sovellus_henkilo_rooli_hist (
    rivitunnus numeric(38,10) NOT NULL,
    henkilo_id numeric(38,10) NOT NULL,
    rooli_id numeric(38,10) NOT NULL,
    sovellus_id numeric(38,10) NOT NULL,
    historiatyyppi character varying(6) NOT NULL,
    riviluotupvm timestamp without time zone,
    rivimuokkaajatunnus character varying(150)
);


ALTER TABLE tietok.sovellus_henkilo_rooli_hist OWNER TO tietokt;

--
-- Name: sovellus_hist_id_seq; Type: SEQUENCE; Schema: tietok; Owner: tietokt
--

CREATE SEQUENCE tietok.sovellus_hist_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE tietok.sovellus_hist_id_seq OWNER TO tietokt;

--
-- Name: sovellus_hist_seq; Type: SEQUENCE; Schema: tietok; Owner: tietokt
--

CREATE SEQUENCE tietok.sovellus_hist_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE tietok.sovellus_hist_seq OWNER TO tietokt;

--
-- Name: sovellus_history; Type: TABLE; Schema: tietok; Owner: tietokt
--

CREATE TABLE tietok.sovellus_history (
    rivitunnus numeric(38,10) NOT NULL,
    tunnus numeric(38,10) NOT NULL,
    elinkaaritieto character varying(4000),
    poistunut numeric(38,10) NOT NULL,
    historiatyyppi character varying(6) NOT NULL,
    rivimuokkaajatunnus character varying(150)
);


ALTER TABLE tietok.sovellus_history OWNER TO tietokt;

--
-- Name: sovellus_id_seq; Type: SEQUENCE; Schema: tietok; Owner: tietokt
--

CREATE SEQUENCE tietok.sovellus_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE tietok.sovellus_id_seq OWNER TO tietokt;

--
-- Name: sovellus_seq; Type: SEQUENCE; Schema: tietok; Owner: tietokt
--

CREATE SEQUENCE tietok.sovellus_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE tietok.sovellus_seq OWNER TO tietokt;

--
-- Name: sovellus_temp; Type: TABLE; Schema: tietok; Owner: tietokt
--

CREATE TABLE tietok.sovellus_temp (
    valmistaja character varying(255),
    alias_nimet character varying(255),
    versio character varying(255),
    konfiguraatio_versio character varying(255),
    lisatietoja character varying(4000),
    sovellus_tyyppi character varying(255),
    kielisyys character varying(255),
    kayttojarjestelmavaatimus character varying(255),
    arkkitehtuuri character varying(255),
    alusta character varying(255),
    riippuvuustieto character varying(255),
    liittymat_jarjestelmiin character varying(255),
    tuotantoon_hyvaksymispaiva timestamp without time zone,
    kriittisyys character varying(4000),
    tuotekoodi character varying(255),
    nimi character varying(255) NOT NULL
);


ALTER TABLE tietok.sovellus_temp OWNER TO tietokt;

--
-- Name: sovellus_tuonti; Type: TABLE; Schema: tietok; Owner: tietokt
--

CREATE TABLE tietok.sovellus_tuonti (
    tuontiaika timestamp without time zone NOT NULL,
    onnistunut numeric(38,10) NOT NULL,
    tuonti_id numeric(38,10) NOT NULL
);


ALTER TABLE tietok.sovellus_tuonti OWNER TO tietokt;

--
-- Name: sovellus_tuonti_id_seq; Type: SEQUENCE; Schema: tietok; Owner: tietokt
--

CREATE SEQUENCE tietok.sovellus_tuonti_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE tietok.sovellus_tuonti_id_seq OWNER TO tietokt;

--
-- Name: sovhenkroolihist_id_seq; Type: SEQUENCE; Schema: tietok; Owner: tietokt
--

CREATE SEQUENCE tietok.sovhenkroolihist_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE tietok.sovhenkroolihist_id_seq OWNER TO tietokt;

--
-- Name: sovhenkroolihist_seq; Type: SEQUENCE; Schema: tietok; Owner: tietokt
--

CREATE SEQUENCE tietok.sovhenkroolihist_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE tietok.sovhenkroolihist_seq OWNER TO tietokt;

--
-- Name: termilomake; Type: TABLE; Schema: tietok; Owner: tietokt
--

CREATE TABLE tietok.termilomake (
    termilomaketunnus numeric(38,10) NOT NULL,
    nimi character varying(255) NOT NULL,
    ensisij_termi character varying(255),
    synonyymit character varying(255),
    ei_suosit_termi character varying(255),
    kayttoaluerajaus character varying(255),
    maaritelma character varying(255),
    huomautus_1 character varying(255),
    huomautus_2 character varying(255),
    kommentit character varying(255),
    valmis character varying(255),
    lahde character varying(255),
    omistaja character varying(255),
    ydinkasite character varying(255),
    hakutermit character varying(255),
    riviluotupvm timestamp without time zone,
    rivimuokattupvm timestamp without time zone,
    rivimuokkaajatunnus character varying(150)
);


ALTER TABLE tietok.termilomake OWNER TO tietokt;

--
-- Name: termilomake_id_seq; Type: SEQUENCE; Schema: tietok; Owner: tietokt
--

CREATE SEQUENCE tietok.termilomake_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE tietok.termilomake_id_seq OWNER TO tietokt;

--
-- Name: termilomakeaskahistoria_id_seq; Type: SEQUENCE; Schema: tietok; Owner: tietokt
--

CREATE SEQUENCE tietok.termilomakeaskahistoria_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE tietok.termilomakeaskahistoria_id_seq OWNER TO tietokt;

--
-- Name: termilomakeaskasihistoria; Type: TABLE; Schema: tietok; Owner: tietokt
--

CREATE TABLE tietok.termilomakeaskasihistoria (
    rivi_id numeric(38,10) NOT NULL,
    assosiatiivinen_ylakasite numeric(38,10),
    assosiatiivinen_alakasite numeric(38,10),
    historiatyyppi character varying(6),
    riviluotupvm timestamp without time zone,
    rivimuokkaajatunnus character varying(150)
);


ALTER TABLE tietok.termilomakeaskasihistoria OWNER TO tietokt;

--
-- Name: termilomakeassoskasite; Type: TABLE; Schema: tietok; Owner: tietokt
--

CREATE TABLE tietok.termilomakeassoskasite (
    rivitunnus numeric(38,10) NOT NULL,
    assosiatiivinen_ylakasite numeric(38,10),
    assosiatiivinen_alakasite numeric(38,10),
    rivitila character varying(6)
);


ALTER TABLE tietok.termilomakeassoskasite OWNER TO tietokt;

--
-- Name: termilomakeassoskasite_id_seq; Type: SEQUENCE; Schema: tietok; Owner: tietokt
--

CREATE SEQUENCE tietok.termilomakeassoskasite_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE tietok.termilomakeassoskasite_id_seq OWNER TO tietokt;

--
-- Name: termilomakehierarkkasite; Type: TABLE; Schema: tietok; Owner: tietokt
--

CREATE TABLE tietok.termilomakehierarkkasite (
    rivitunnus numeric(38,10) NOT NULL,
    hierarkkinen_ylakasite numeric(38,10),
    hierarkkinen_alakasite numeric(38,10),
    rivitila character varying(6)
);


ALTER TABLE tietok.termilomakehierarkkasite OWNER TO tietokt;

--
-- Name: termilomakehierkasite_id_seq; Type: SEQUENCE; Schema: tietok; Owner: tietokt
--

CREATE SEQUENCE tietok.termilomakehierkasite_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE tietok.termilomakehierkasite_id_seq OWNER TO tietokt;

--
-- Name: termilomakehierkhist_id_seq; Type: SEQUENCE; Schema: tietok; Owner: tietokt
--

CREATE SEQUENCE tietok.termilomakehierkhist_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE tietok.termilomakehierkhist_id_seq OWNER TO tietokt;

--
-- Name: termilomakehist_id_seq; Type: SEQUENCE; Schema: tietok; Owner: tietokt
--

CREATE SEQUENCE tietok.termilomakehist_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE tietok.termilomakehist_id_seq OWNER TO tietokt;

--
-- Name: termilomakehistoria; Type: TABLE; Schema: tietok; Owner: tietokt
--

CREATE TABLE tietok.termilomakehistoria (
    rivi_id numeric(38,10) NOT NULL,
    termilomaketunnus numeric(38,10) NOT NULL,
    nimi character varying(255) NOT NULL,
    ensisij_termi character varying(255),
    synonyymit character varying(255),
    ei_suosit_termi character varying(255),
    kayttoaluerajaus character varying(255),
    maaritelma character varying(255),
    huomautus_1 character varying(255),
    huomautus_2 character varying(255),
    kommentit character varying(255),
    valmis character varying(255),
    lahde character varying(255),
    omistaja character varying(255),
    ydinkasite character varying(255),
    hakutermit character varying(255),
    historiatyyppi character varying(6),
    riviluotupvm timestamp without time zone,
    rivimuokattupvm timestamp without time zone,
    rivimuokkaajatunnus character varying(150)
);


ALTER TABLE tietok.termilomakehistoria OWNER TO tietokt;

--
-- Name: termilomakehkasitehistoria; Type: TABLE; Schema: tietok; Owner: tietokt
--

CREATE TABLE tietok.termilomakehkasitehistoria (
    rivi_id numeric(38,10) NOT NULL,
    hierarkkinen_alakasite numeric(38,10),
    hierarkkinen_ylakasite numeric(38,10),
    historiatyyppi character varying(6),
    riviluotupvm timestamp without time zone,
    rivimuokkaajatunnus character varying(150)
);


ALTER TABLE tietok.termilomakehkasitehistoria OWNER TO tietokt;

--
-- Name: termilomakekoostkashist_id_seq; Type: SEQUENCE; Schema: tietok; Owner: tietokt
--

CREATE SEQUENCE tietok.termilomakekoostkashist_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE tietok.termilomakekoostkashist_id_seq OWNER TO tietokt;

--
-- Name: termilomakekoostkasihistoria; Type: TABLE; Schema: tietok; Owner: tietokt
--

CREATE TABLE tietok.termilomakekoostkasihistoria (
    rivi_id numeric(38,10) NOT NULL,
    koostumussuhteinen_ylakasite numeric(38,10),
    koostumussuhteinen_alakasite numeric(38,10),
    historiatyyppi character varying(6),
    riviluotupvm timestamp without time zone,
    rivimuokkaajatunnus character varying(150)
);


ALTER TABLE tietok.termilomakekoostkasihistoria OWNER TO tietokt;

--
-- Name: termilomakekoostkasite; Type: TABLE; Schema: tietok; Owner: tietokt
--

CREATE TABLE tietok.termilomakekoostkasite (
    rivitunnus numeric(38,10) NOT NULL,
    koostumussuhteinen_ylakasite numeric(38,10),
    koostumussuhteinen_alakasite numeric(38,10),
    rivitila character varying(6)
);


ALTER TABLE tietok.termilomakekoostkasite OWNER TO tietokt;

--
-- Name: termilomakekoostkasite_id_seq; Type: SEQUENCE; Schema: tietok; Owner: tietokt
--

CREATE SEQUENCE tietok.termilomakekoostkasite_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE tietok.termilomakekoostkasite_id_seq OWNER TO tietokt;

--
-- Name: tiesuunnitelma; Type: TABLE; Schema: tietok; Owner: tietokt
--

CREATE TABLE tietok.tiesuunnitelma (
    tiesuunnitelmatunnus numeric(38,10) NOT NULL,
    nimi character varying(4000),
    koodi character varying(4000),
    kasittelija character varying(4000),
    kuvaus character varying(4000),
    diaariteksti character varying(4000),
    elykeskus character varying(4000),
    suunntelmanstatus character varying(4000),
    kohde1 character varying(4000),
    kohde2 character varying(4000),
    selitys character varying(4000),
    paatoshaosisalto character varying(4000),
    paatoskhosisalto character varying(4000),
    kokonaiskustannukset numeric(38,10),
    kustannuksetvaltio numeric(38,10),
    kustannuksetkunta numeric(38,10),
    kustannuksetsahko numeric(38,10),
    kustannuksetviestinta numeric(38,10),
    kustannuksetvesi numeric(38,10),
    indeksivuosi numeric(38,10),
    maarakennusindeksi numeric(38,10),
    muistutuksia numeric(38,10),
    valituksia numeric(38,10),
    sisaltaamaantienlakkaamisen character varying(255),
    kiireellinenkasittelypyydetty character varying(255),
    yvahanke character varying(255),
    hyvaksymisesityslaadittu timestamp without time zone,
    hyvaksymisesityssaapunut timestamp without time zone,
    aloituskuulutus timestamp without time zone,
    hyvaksymispaatosannettu timestamp without time zone,
    jatkopaatosannettu1 timestamp without time zone,
    jatkopaatosannettu2 timestamp without time zone,
    palautettuelylle timestamp without time zone,
    hyvaksymisesituslvmlaadittu timestamp without time zone,
    hyvaksymispaatoslvmannettu timestamp without time zone,
    jatkopaatoslvmannettu timestamp without time zone,
    osapaatosannettu timestamp without time zone,
    valipaatoshao timestamp without time zone,
    hyvaksymispaatoshaokumottu timestamp without time zone,
    paatoshao timestamp without time zone,
    valipaatoskho timestamp without time zone,
    hyvaksymispaatoskhokumottu timestamp without time zone,
    paatoskho timestamp without time zone,
    lainvoimaisuus timestamp without time zone,
    lainvoimaisuudenpaattyminen timestamp without time zone,
    liikenteeseenosaluovutus timestamp without time zone,
    liikenteeseenkokoluovutus timestamp without time zone,
    riviluotupvm timestamp without time zone,
    rivimuokattupvm timestamp without time zone,
    rivimuokkaajatunnus character varying(150),
    document_id character varying(255)
);


ALTER TABLE tietok.tiesuunnitelma OWNER TO tietokt;

--
-- Name: tiesuunnitelmahistoria; Type: TABLE; Schema: tietok; Owner: tietokt
--

CREATE TABLE tietok.tiesuunnitelmahistoria (
    rivi_id numeric(38,10) NOT NULL,
    historiatyyppi character varying(6) NOT NULL,
    tiesuunnitelmatunnus numeric(38,10) NOT NULL,
    nimi character varying(4000),
    koodi character varying(4000),
    kasittelija character varying(4000),
    kuvaus character varying(4000),
    diaariteksti character varying(4000),
    elykeskus character varying(4000),
    suunntelmanstatus character varying(4000),
    kohde1 character varying(4000),
    kohde2 character varying(4000),
    selitys character varying(4000),
    paatoshaosisalto character varying(4000),
    paatoskhosisalto character varying(4000),
    kokonaiskustannukset numeric(38,10),
    kustannuksetvaltio numeric(38,10),
    kustannuksetkunta numeric(38,10),
    kustannuksetsahko numeric(38,10),
    kustannuksetviestinta numeric(38,10),
    kustannuksetvesi numeric(38,10),
    indeksivuosi numeric(38,10),
    maarakennusindeksi numeric(38,10),
    muistutuksia numeric(38,10),
    valituksia numeric(38,10),
    sisaltaamaantienlakkaamisen character varying(255),
    kiireellinenkasittelypyydetty character varying(255),
    yvahanke character varying(255),
    hyvaksymisesityslaadittu timestamp without time zone,
    hyvaksymisesityssaapunut timestamp without time zone,
    aloituskuulutus timestamp without time zone,
    hyvaksymispaatosannettu timestamp without time zone,
    jatkopaatosannettu1 timestamp without time zone,
    jatkopaatosannettu2 timestamp without time zone,
    palautettuelylle timestamp without time zone,
    hyvaksymisesituslvmlaadittu timestamp without time zone,
    hyvaksymispaatoslvmannettu timestamp without time zone,
    jatkopaatoslvmannettu timestamp without time zone,
    osapaatosannettu timestamp without time zone,
    valipaatoshao timestamp without time zone,
    hyvaksymispaatoshaokumottu timestamp without time zone,
    paatoshao timestamp without time zone,
    valipaatoskho timestamp without time zone,
    hyvaksymispaatoskhokumottu timestamp without time zone,
    paatoskho timestamp without time zone,
    lainvoimaisuus timestamp without time zone,
    lainvoimaisuudenpaattyminen timestamp without time zone,
    liikenteeseenosaluovutus timestamp without time zone,
    liikenteeseenkokoluovutus timestamp without time zone,
    rivitunnus numeric(38,10),
    rivitila character varying(15),
    riviluotupvm timestamp without time zone,
    rivimuokattupvm timestamp without time zone,
    rivimuokkaajatunnus character varying(150),
    document_id character varying(255)
);


ALTER TABLE tietok.tiesuunnitelmahistoria OWNER TO tietokt;

--
-- Name: tieto_kasite_arvo; Type: TABLE; Schema: tietok; Owner: tietokt
--

CREATE TABLE tietok.tieto_kasite_arvo (
    kasite_wid numeric(38,0) NOT NULL,
    kasite character varying(100),
    arvo character varying(200)
);


ALTER TABLE tietok.tieto_kasite_arvo OWNER TO tietokt;

--
-- Name: tieto_tietotunnus_seq_1; Type: SEQUENCE; Schema: tietok; Owner: tietokt
--

CREATE SEQUENCE tietok.tieto_tietotunnus_seq_1
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE tietok.tieto_tietotunnus_seq_1 OWNER TO tietokt;

--
-- Name: tietohistoria; Type: TABLE; Schema: tietok; Owner: tietokt
--

CREATE TABLE tietok.tietohistoria (
    rivi_id numeric(38,10) NOT NULL,
    tietotunnus numeric(38,10) NOT NULL,
    historiatyyppi character varying(6) NOT NULL,
    looginentietovarantotunnus numeric(38,10),
    tietoryhmatunnus numeric(38,10),
    nimi character varying(150),
    koodi character varying(50),
    kuvaus character varying(4000),
    omistaja character varying(150),
    lahde character varying(150),
    tila character varying(50),
    synonyymi character varying(150),
    tietosuojataso character varying(50),
    ylempitaso numeric(38,10),
    rivitunnus numeric(38,10),
    rivitila character varying(15),
    rivimuokkaajatunnus character varying(150),
    riviluotupvm timestamp without time zone,
    document_id character varying(200)
);


ALTER TABLE tietok.tietohistoria OWNER TO tietokt;

--
-- Name: tietojarj_kasite_arvo_28092020; Type: TABLE; Schema: tietok; Owner: tietokt
--

CREATE TABLE tietok.tietojarj_kasite_arvo_28092020 (
    kasite_wid numeric(38,0) NOT NULL,
    kasite character varying(100),
    arvo character varying(200)
);


ALTER TABLE tietok.tietojarj_kasite_arvo_28092020 OWNER TO tietokt;

--
-- Name: tietojarjestelma_kasite_arvo; Type: TABLE; Schema: tietok; Owner: tietokt
--

CREATE TABLE tietok.tietojarjestelma_kasite_arvo (
    kasite_wid numeric(38,0) NOT NULL,
    kasite character varying(100),
    arvo character varying(200)
);


ALTER TABLE tietok.tietojarjestelma_kasite_arvo OWNER TO tietokt;

--
-- Name: tietojarjestelmapalvelu; Type: TABLE; Schema: tietok; Owner: tietokt
--

CREATE TABLE tietok.tietojarjestelmapalvelu (
    tunnus numeric(38,10) NOT NULL,
    nimi character varying(225) NOT NULL,
    kuvaus character varying(4000),
    kayttajaroolit character varying(225),
    tietojarjestelmatunnus numeric(38,10),
    elinkaari character varying(20),
    riviluotupvm timestamp without time zone,
    rivimuokattupvm timestamp without time zone,
    rivimuokkaajatunnus character varying(150)
);


ALTER TABLE tietok.tietojarjestelmapalvelu OWNER TO tietokt;

--
-- Name: tietojarjestelmapalvelu_id_seq; Type: SEQUENCE; Schema: tietok; Owner: tietokt
--

CREATE SEQUENCE tietok.tietojarjestelmapalvelu_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE tietok.tietojarjestelmapalvelu_id_seq OWNER TO tietokt;

--
-- Name: tietojarjestelmapalvelu_tie872; Type: SEQUENCE; Schema: tietok; Owner: tietokt
--

CREATE SEQUENCE tietok.tietojarjestelmapalvelu_tie872
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE tietok.tietojarjestelmapalvelu_tie872 OWNER TO tietokt;

--
-- Name: tietojarjestelmasalkk_28092020; Type: TABLE; Schema: tietok; Owner: tietokt
--

CREATE TABLE tietok.tietojarjestelmasalkk_28092020 (
    tietojarjestelmatunnus numeric(38,10) NOT NULL,
    jarjestelman_nimi character varying(255),
    kuvaus_maaritelma character varying(4000),
    lyhennetty_nimi character varying(255),
    jarjestelmatyyppi character varying(255),
    kayttajaryhmat character varying(1000),
    elinkaaritila character varying(255),
    toimittaja character varying(255),
    toimittajan_yhteyshenkilo character varying(255),
    jarjestelmavastaava_livi character varying(255),
    sisaiset_kayttajat character varying(255),
    lisenssitieto character varying(255),
    alfresco_linkki character varying(1000),
    kriittisyys character varying(255),
    muut_toimittajat character varying(255),
    versio character varying(255),
    tietoj_kriittisyys_toiminnalle character varying(255),
    tietovarastot character varying(255),
    paasynhallinta character varying(255),
    portaalipalvelu character varying(255),
    tunnistetut_kehitystarpeet character varying(255),
    toteutusteknologia character varying(255),
    sisaisten_kayttajien_maara character varying(255),
    palvelin character varying(255),
    tekninen_elinkaari character varying(255),
    kayttooikeuksien_hallinta character varying(255),
    kayttoonottovuosi character varying(255),
    huomautus_esimerkki character varying(1000),
    kehityspanos_arvioitu character varying(255),
    kehityskulut_arvioidut character varying(255),
    palveluaika character varying(255),
    liik_turvallisuusluokka character varying(255),
    tunnistetut_riskit character varying(255),
    luokituksen_tarkastuspvm timestamp without time zone,
    tietoturvatasoluokitus character varying(255),
    ict_varautumisen_luokitus character varying(255),
    sla_luokitus_jhs character varying(255),
    sla_luokitus_kayttopalvelu character varying(255),
    jarjestelman_tarkeys_liville character varying(255),
    jarj_tarkeys_yhteistyokump character varying(255),
    turvallisuuskuvaus_laadittu character varying(255),
    toipumissuunnitelma_laadittu character varying(255),
    kehitystarve character varying(255),
    kayttotiheys character varying(255),
    ulkoiset_kayttajat character varying(255),
    poistovuosi character varying(20),
    tietolahteet character varying(255),
    salassa_pidettavat_tiedot character varying(255),
    julkiset_tiedot_ryhmittain character varying(255),
    tietoj_internet_osoite character varying(255),
    tietojen_julkisuus character varying(255),
    jarj_omistaja character varying(255),
    rekisteriseloste character varying(255),
    riviluotupvm timestamp without time zone,
    rivimuokattupvm timestamp without time zone,
    rivimuokkaajatunnus character varying(150),
    document_id character varying(255),
    toimittajan_tekn_yh character varying(255),
    yhteisk_kriit_jarj character varying(255),
    jarj_tarkeys_yk character varying(255),
    jarjestelmaalue character varying(255),
    tietoturvasopimus smallint,
    confluence_linkki character varying(1000),
    kayttopalvelun_toimittaja character varying(255),
    omistava_organisaatio character varying(255),
    budjetti character varying(255),
    rahoitusmomentti character varying(255)
);


ALTER TABLE tietok.tietojarjestelmasalkk_28092020 OWNER TO tietokt;

--
-- Name: tietojarjestelmasalkku_tiet311; Type: SEQUENCE; Schema: tietok; Owner: tietokt
--

CREATE SEQUENCE tietok.tietojarjestelmasalkku_tiet311
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE tietok.tietojarjestelmasalkku_tiet311 OWNER TO tietokt;

--
-- Name: tietojarjestelmasalkkufyysi263; Type: SEQUENCE; Schema: tietok; Owner: tietokt
--

CREATE SEQUENCE tietok.tietojarjestelmasalkkufyysi263
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE tietok.tietojarjestelmasalkkufyysi263 OWNER TO tietokt;

--
-- Name: tietojarjestelmasalkkuhistoria; Type: TABLE; Schema: tietok; Owner: tietokt
--

CREATE TABLE tietok.tietojarjestelmasalkkuhistoria (
    rivi_id numeric(38,10) NOT NULL,
    tietojarjestelmatunnus numeric(38,10),
    historiatyyppi character varying(6) NOT NULL,
    jarjestelman_nimi character varying(255),
    kuvaus_maaritelma character varying(4000),
    lyhennetty_nimi character varying(255),
    jarjestelmatyyppi character varying(255),
    kayttajaryhmat character varying(1000),
    elinkaaritila character varying(255),
    toimittaja character varying(255),
    toimittajan_yhteyshenkilo character varying(255),
    jarjestelmavastaava_livi character varying(255),
    sisaiset_kayttajat character varying(255),
    lisenssitieto character varying(255),
    alfresco_linkki character varying(255),
    kriittisyys character varying(255),
    muut_toimittajat character varying(255),
    versio character varying(255),
    tietoj_kriittisyys_toiminnalle character varying(255),
    tietovarastot character varying(255),
    paasynhallinta character varying(255),
    portaalipalvelu character varying(255),
    tunnistetut_kehitystarpeet character varying(255),
    toteutusteknologia character varying(255),
    sisaisten_kayttajien_maara character varying(255),
    palvelin character varying(255),
    tekninen_elinkaari character varying(255),
    kayttooikeuksien_hallinta character varying(255),
    kayttoonottovuosi character varying(255),
    huomautus_esimerkki character varying(1000),
    kehityspanos_arvioitu character varying(255),
    kehityskulut_arvioidut character varying(255),
    palveluaika character varying(255),
    liik_turvallisuusluokka character varying(255),
    tunnistetut_riskit character varying(255),
    luokituksen_tarkastuspvm timestamp without time zone,
    tietoturvatasoluokitus character varying(255),
    ict_varautumisen_luokitus character varying(255),
    sla_luokitus_jhs character varying(255),
    sla_luokitus_kayttopalvelu character varying(255),
    jarjestelman_tarkeys_liville character varying(255),
    jarj_tarkeys_yhteistyokump character varying(255),
    turvallisuuskuvaus_laadittu character varying(255),
    toipumissuunnitelma_laadittu character varying(255),
    kehitystarve character varying(255),
    kayttotiheys character varying(255),
    ulkoiset_kayttajat character varying(255),
    poistovuosi character varying(20),
    tietolahteet character varying(255),
    salassa_pidettavat_tiedot character varying(255),
    julkiset_tiedot_ryhmittain character varying(255),
    tietoj_internet_osoite character varying(255),
    tietojen_julkisuus character varying(255),
    jarj_omistaja character varying(255),
    rekisteriseloste character varying(255),
    riviluotupvm timestamp without time zone,
    rivimuokkaajatunnus character varying(150),
    document_id character varying(255),
    toimittajan_tekn_yh character varying(255),
    yhteisk_kriit_jarj character varying(255),
    jarj_tarkeys_yk character varying(255),
    jarjestelmaalue character varying(255),
    tietoturvasopimus smallint,
    confluence_linkki character varying(1000),
    kayttopalvelun_toimittaja character varying(255),
    omistava_organisaatio character varying(255),
    budjetti character varying(255),
    rahoitusmomentti character varying(255),
    salassapidon_peruste character varying(255),
    turvallisuusluokitus character varying(255),
    tarkeinta character varying(255),
    tiedonluottamuksellisuus character varying(255),
    tiedon_saatavuus character varying(255),
    tiedon_eheys character varying(255),
    tarvitaan_viikonloppuna character varying(255),
    tarvitaan_audit_trail character varying(255),
    integraatioita_muihin character varying(255)
);


ALTER TABLE tietok.tietojarjestelmasalkkuhistoria OWNER TO tietokt;

--
-- Name: tietojarjpalvelu_kasite_arvo; Type: TABLE; Schema: tietok; Owner: tietokt
--

CREATE TABLE tietok.tietojarjpalvelu_kasite_arvo (
    kasite_id numeric(38,10) NOT NULL,
    kasite character varying(100) NOT NULL,
    arvo character varying(200) NOT NULL
);


ALTER TABLE tietok.tietojarjpalvelu_kasite_arvo OWNER TO tietokt;

--
-- Name: tietojarjpalvelu_tietolaji; Type: TABLE; Schema: tietok; Owner: tietokt
--

CREATE TABLE tietok.tietojarjpalvelu_tietolaji (
    tietojarjestelmapalvelutunnus numeric(38,10) NOT NULL,
    tietolajitunnus numeric(38,10) NOT NULL,
    rivitunnus numeric(38,10) NOT NULL,
    rivitila character varying(15)
);


ALTER TABLE tietok.tietojarjpalvelu_tietolaji OWNER TO tietokt;

--
-- Name: tietojarjpalveluhist_id_seq; Type: SEQUENCE; Schema: tietok; Owner: tietokt
--

CREATE SEQUENCE tietok.tietojarjpalveluhist_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE tietok.tietojarjpalveluhist_id_seq OWNER TO tietokt;

--
-- Name: tietojarjpalveluhistoria; Type: TABLE; Schema: tietok; Owner: tietokt
--

CREATE TABLE tietok.tietojarjpalveluhistoria (
    rivi_id numeric(38,10) NOT NULL,
    historiatyyppi character varying(6),
    tunnus numeric(38,10),
    nimi character varying(225) NOT NULL,
    kuvaus character varying(4000),
    kayttajaroolit character varying(225),
    tietojarjestelmatunnus numeric(38,10),
    elinkaari character varying(20),
    riviluotupvm timestamp without time zone,
    rivimuokattupvm timestamp without time zone,
    rivimuokkaajatunnus character varying(150)
);


ALTER TABLE tietok.tietojarjpalveluhistoria OWNER TO tietokt;

--
-- Name: tietojarjpalvelutieto_id_seq; Type: SEQUENCE; Schema: tietok; Owner: tietokt
--

CREATE SEQUENCE tietok.tietojarjpalvelutieto_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE tietok.tietojarjpalvelutieto_id_seq OWNER TO tietokt;

--
-- Name: tietojarjpalvelutietohistoria; Type: TABLE; Schema: tietok; Owner: tietokt
--

CREATE TABLE tietok.tietojarjpalvelutietohistoria (
    rivitunnus numeric(38,10) NOT NULL,
    tietojarjestelmapalvelutunnus numeric(38,10) NOT NULL,
    tietolajitunnus numeric(38,10) NOT NULL,
    rivitila character varying(6),
    historiatyyppi character varying(6),
    riviluotupvm timestamp without time zone,
    rivimuokattupvm timestamp without time zone,
    rivimuokkaajatunnus character varying(150)
);


ALTER TABLE tietok.tietojarjpalvelutietohistoria OWNER TO tietokt;

--
-- Name: tietojarjpalvliitjarj_id_seq; Type: SEQUENCE; Schema: tietok; Owner: tietokt
--

CREATE SEQUENCE tietok.tietojarjpalvliitjarj_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE tietok.tietojarjpalvliitjarj_id_seq OWNER TO tietokt;

--
-- Name: tietojarjpalvliitjarjhistoria; Type: TABLE; Schema: tietok; Owner: tietokt
--

CREATE TABLE tietok.tietojarjpalvliitjarjhistoria (
    rivi_id numeric(38,10),
    tietojarjpalvelutunnus numeric(38,10),
    liittyvajarjestelmatunnus numeric(38,10),
    riviluotupvm timestamp without time zone,
    historiatyyppi character varying(6),
    rivimuokkaajatunnus character varying(150)
);


ALTER TABLE tietok.tietojarjpalvliitjarjhistoria OWNER TO tietokt;

--
-- Name: tietojarjpalvliittyvajarj; Type: TABLE; Schema: tietok; Owner: tietokt
--

CREATE TABLE tietok.tietojarjpalvliittyvajarj (
    rivitunnus numeric(38,10) NOT NULL,
    tietojarjpalvelutunnus numeric(38,10),
    liittyvajarjestelmatunnus numeric(38,10)
);


ALTER TABLE tietok.tietojarjpalvliittyvajarj OWNER TO tietokt;

--
-- Name: tietojarjpalvtietohist_id_seq; Type: SEQUENCE; Schema: tietok; Owner: tietokt
--

CREATE SEQUENCE tietok.tietojarjpalvtietohist_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE tietok.tietojarjpalvtietohist_id_seq OWNER TO tietokt;

--
-- Name: tietolahde_rivitunnus_seq; Type: SEQUENCE; Schema: tietok; Owner: tietokt
--

CREATE SEQUENCE tietok.tietolahde_rivitunnus_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE tietok.tietolahde_rivitunnus_seq OWNER TO tietokt;

--
-- Name: tietolaji_id_seq; Type: SEQUENCE; Schema: tietok; Owner: tietokt
--

CREATE SEQUENCE tietok.tietolaji_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE tietok.tietolaji_id_seq OWNER TO tietokt;

--
-- Name: tietolaji_seq; Type: SEQUENCE; Schema: tietok; Owner: tietokt
--

CREATE SEQUENCE tietok.tietolaji_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE tietok.tietolaji_seq OWNER TO tietokt;

--
-- Name: tietolajihist_id_seq; Type: SEQUENCE; Schema: tietok; Owner: tietokt
--

CREATE SEQUENCE tietok.tietolajihist_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE tietok.tietolajihist_id_seq OWNER TO tietokt;

--
-- Name: tietolajihist_seq; Type: SEQUENCE; Schema: tietok; Owner: tietokt
--

CREATE SEQUENCE tietok.tietolajihist_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE tietok.tietolajihist_seq OWNER TO tietokt;

--
-- Name: tietolooginenhistoria; Type: TABLE; Schema: tietok; Owner: tietokt
--

CREATE TABLE tietok.tietolooginenhistoria (
    rivitunnus numeric(38,10) NOT NULL,
    looginentietovarantotunnus numeric(38,10) NOT NULL,
    tietotunnus numeric(38,10) NOT NULL,
    historiatyyppi character varying(6) NOT NULL,
    rivitila character varying(15) NOT NULL,
    riviluotupvm timestamp without time zone,
    rivimuokkaajatunnus character varying(150)
);


ALTER TABLE tietok.tietolooginenhistoria OWNER TO tietokt;

--
-- Name: tietolooginentietovaranto; Type: TABLE; Schema: tietok; Owner: tietokt
--

CREATE TABLE tietok.tietolooginentietovaranto (
    rivitunnus numeric(38,10) NOT NULL,
    looginentietovarantotunnus numeric(38,10) NOT NULL,
    tietotunnus numeric(38,10) NOT NULL,
    rivitila character varying(15) NOT NULL
);


ALTER TABLE tietok.tietolooginentietovaranto OWNER TO tietokt;

--
-- Name: tietoomaisuus; Type: TABLE; Schema: tietok; Owner: tietokt
--

CREATE TABLE tietok.tietoomaisuus (
    id numeric(38,10) NOT NULL,
    tietojarjestelma_id numeric(38,10) NOT NULL,
    primaarikayttotarve numeric(38,10),
    sekundaar_kayttotarpeet numeric(38,10),
    kayttajat numeric(38,10),
    mallinnustapa numeric(38,10),
    kayttoformaatit numeric(38,10),
    historiatiedot numeric(38,10),
    kattavuus_ja_laatu numeric(38,10),
    metatiedot numeric(38,10),
    muutostiedot numeric(38,10),
    saatavuus numeric(38,10),
    henkilotiedot numeric(38,10),
    eheys numeric(38,10),
    avoimuus numeric(38,10),
    immateriaalioikeudet numeric(38,10),
    yksiloivat_tunnisteet numeric(38,10),
    pysyvyys numeric(38,10),
    dokumentaatio numeric(38,10),
    elinkaari numeric(38,10),
    riviluotupvm timestamp without time zone,
    rivimuokattupvm timestamp without time zone,
    rivimuokkaajatunnus character varying(150),
    nimi character varying(225),
    kuvaus character varying(4000),
    alustojen_tyyp_ja_sij numeric(38,10)
);


ALTER TABLE tietok.tietoomaisuus OWNER TO tietokt;

--
-- Name: tietoomaisuus_id_seq; Type: SEQUENCE; Schema: tietok; Owner: tietokt
--

CREATE SEQUENCE tietok.tietoomaisuus_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE tietok.tietoomaisuus_id_seq OWNER TO tietokt;

--
-- Name: tietoomaisuus_kasite_arvo; Type: TABLE; Schema: tietok; Owner: tietokt
--

CREATE TABLE tietok.tietoomaisuus_kasite_arvo (
    kasite_wid numeric(38,0) NOT NULL,
    kasite character varying(100),
    arvo character varying(200),
    pisteytys numeric(38,10)
);


ALTER TABLE tietok.tietoomaisuus_kasite_arvo OWNER TO tietokt;

--
-- Name: tietoomaisuushist_id_seq; Type: SEQUENCE; Schema: tietok; Owner: tietokt
--

CREATE SEQUENCE tietok.tietoomaisuushist_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE tietok.tietoomaisuushist_id_seq OWNER TO tietokt;

--
-- Name: tietoomaisuushistoria; Type: TABLE; Schema: tietok; Owner: tietokt
--

CREATE TABLE tietok.tietoomaisuushistoria (
    rivi_id numeric(38,10) NOT NULL,
    tunnus numeric(38,10) NOT NULL,
    tietojarjestelma_id numeric(38,10) NOT NULL,
    historiatyyppi character varying(6),
    primaarikayttotarve numeric(38,10),
    sekundaar_kayttotarpeet numeric(38,10),
    kayttajat numeric(38,10),
    modulaarisuus numeric(38,10),
    yleistystasot numeric(38,10),
    mallinnustapa numeric(38,10),
    kayttoformaatit numeric(38,10),
    historiatiedot numeric(38,10),
    kattavuus_ja_laatu numeric(38,10),
    metatiedot numeric(38,10),
    muutostiedot numeric(38,10),
    saatavuus numeric(38,10),
    henkilotiedot numeric(38,10),
    eheys numeric(38,10),
    avoimuus numeric(38,10),
    immateriaalioikeudet numeric(38,10),
    yksiloivat_tunnisteet numeric(38,10),
    pysyvyys numeric(38,10),
    dokumentaatio numeric(38,10),
    elinkaari numeric(38,10),
    riviluotupvm timestamp without time zone,
    rivimuokattupvm timestamp without time zone,
    rivimuokkaajatunnus character varying(150),
    nimi character varying(225),
    kuvaus character varying(4000),
    alustojen_tyyp_ja_sij numeric(38,10)
);


ALTER TABLE tietok.tietoomaisuushistoria OWNER TO tietokt;

--
-- Name: tietoryhma_id_seq; Type: SEQUENCE; Schema: tietok; Owner: tietokt
--

CREATE SEQUENCE tietok.tietoryhma_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE tietok.tietoryhma_id_seq OWNER TO tietokt;

--
-- Name: tietoryhma_seq; Type: SEQUENCE; Schema: tietok; Owner: tietokt
--

CREATE SEQUENCE tietok.tietoryhma_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE tietok.tietoryhma_seq OWNER TO tietokt;

--
-- Name: tietoryhma_tietoryhmatunnus830; Type: SEQUENCE; Schema: tietok; Owner: tietokt
--

CREATE SEQUENCE tietok.tietoryhma_tietoryhmatunnus830
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE tietok.tietoryhma_tietoryhmatunnus830 OWNER TO tietokt;

--
-- Name: tietoryhmahist_id_seq; Type: SEQUENCE; Schema: tietok; Owner: tietokt
--

CREATE SEQUENCE tietok.tietoryhmahist_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE tietok.tietoryhmahist_id_seq OWNER TO tietokt;

--
-- Name: tietoryhmahist_seq; Type: SEQUENCE; Schema: tietok; Owner: tietokt
--

CREATE SEQUENCE tietok.tietoryhmahist_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE tietok.tietoryhmahist_seq OWNER TO tietokt;

--
-- Name: tietoryhmahistoria; Type: TABLE; Schema: tietok; Owner: tietokt
--

CREATE TABLE tietok.tietoryhmahistoria (
    rivi_id numeric(38,10) NOT NULL,
    tietoryhmatunnus numeric(38,10) NOT NULL,
    historiatyyppi character varying(6) NOT NULL,
    paatietoryhmatunnus numeric(38,10),
    nimi character varying(255) NOT NULL,
    koodi character varying(50),
    kuvaus character varying(4000),
    omistaja character varying(150),
    lahde character varying(150),
    tila character varying(50),
    synonyymi character varying(150),
    tietosuojataso character varying(50),
    kayttaja character varying(150),
    ylempitaso numeric(38,10),
    rivitunnus numeric(38,10),
    rivitila character varying(15),
    riviluotupvm timestamp without time zone,
    rivimuokkaajatunnus character varying(150),
    document_id character varying(255),
    tietovarantotunnus numeric(38,10)
);


ALTER TABLE tietok.tietoryhmahistoria OWNER TO tietokt;

--
-- Name: tietoryhmalooginentietovara529; Type: SEQUENCE; Schema: tietok; Owner: tietokt
--

CREATE SEQUENCE tietok.tietoryhmalooginentietovara529
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE tietok.tietoryhmalooginentietovara529 OWNER TO tietokt;

--
-- Name: tietoryhmapaatietohistoria; Type: TABLE; Schema: tietok; Owner: tietokt
--

CREATE TABLE tietok.tietoryhmapaatietohistoria (
    rivitunnus numeric(38,10) NOT NULL,
    paatietoryhmatunnus numeric(38,10) NOT NULL,
    historiatyyppi character varying(6) NOT NULL,
    tietoryhmatunnus numeric(38,10) NOT NULL,
    rivitila character varying(15) NOT NULL,
    riviluotupvm timestamp without time zone,
    rivimuokkaajatunnus character varying(150)
);


ALTER TABLE tietok.tietoryhmapaatietohistoria OWNER TO tietokt;

--
-- Name: tietoryhmapaatietoryhma; Type: TABLE; Schema: tietok; Owner: tietokt
--

CREATE TABLE tietok.tietoryhmapaatietoryhma (
    rivitunnus numeric(38,10) NOT NULL,
    paatietoryhmatunnus numeric(38,10) NOT NULL,
    tietoryhmatunnus numeric(38,10) NOT NULL,
    rivitila character varying(15) NOT NULL
);


ALTER TABLE tietok.tietoryhmapaatietoryhma OWNER TO tietokt;

--
-- Name: tietoryhmatietovarahist_id_seq; Type: SEQUENCE; Schema: tietok; Owner: tietokt
--

CREATE SEQUENCE tietok.tietoryhmatietovarahist_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE tietok.tietoryhmatietovarahist_id_seq OWNER TO tietokt;

--
-- Name: tietoryhmatietovaranto; Type: TABLE; Schema: tietok; Owner: tietokt
--

CREATE TABLE tietok.tietoryhmatietovaranto (
    rivitunnus numeric(38,10) NOT NULL,
    tietovarantotunnus numeric(38,10),
    tietoryhmatunnus numeric(38,10)
);


ALTER TABLE tietok.tietoryhmatietovaranto OWNER TO tietokt;

--
-- Name: tietoryhmatietovaranto_id_seq; Type: SEQUENCE; Schema: tietok; Owner: tietokt
--

CREATE SEQUENCE tietok.tietoryhmatietovaranto_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE tietok.tietoryhmatietovaranto_id_seq OWNER TO tietokt;

--
-- Name: tietoryhmatietovarantohistoria; Type: TABLE; Schema: tietok; Owner: tietokt
--

CREATE TABLE tietok.tietoryhmatietovarantohistoria (
    rivi_id numeric(38,10) NOT NULL,
    tietovarantotunnus numeric(38,10),
    tietoryhmatunnus numeric(38,10),
    riviluotupvm timestamp without time zone,
    historiatyyppi character varying(6),
    rivimuokkaajatunnus character varying(150)
);


ALTER TABLE tietok.tietoryhmatietovarantohistoria OWNER TO tietokt;

--
-- Name: tietosuojavastaava; Type: TABLE; Schema: tietok; Owner: tietokt
--

CREATE TABLE tietok.tietosuojavastaava (
    tietosuojavastaavatunnus numeric(38,10) NOT NULL,
    nimi character varying(225),
    osoite character varying(255),
    sahkoposti character varying(255),
    puhelinnumero character varying(255),
    rivimuokkaajatunnus character varying(150),
    riviluotupvm timestamp without time zone,
    rivimuokattupvm timestamp without time zone
);


ALTER TABLE tietok.tietosuojavastaava OWNER TO tietokt;

--
-- Name: tietosuojavastaava_id_seq; Type: SEQUENCE; Schema: tietok; Owner: tietokt
--

CREATE SEQUENCE tietok.tietosuojavastaava_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE tietok.tietosuojavastaava_id_seq OWNER TO tietokt;

--
-- Name: tietosuojavastaavahist_id_seq; Type: SEQUENCE; Schema: tietok; Owner: tietokt
--

CREATE SEQUENCE tietok.tietosuojavastaavahist_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE tietok.tietosuojavastaavahist_id_seq OWNER TO tietokt;

--
-- Name: tietosuojavastaavahistoria; Type: TABLE; Schema: tietok; Owner: tietokt
--

CREATE TABLE tietok.tietosuojavastaavahistoria (
    rivi_id numeric(38,10) NOT NULL,
    historiatyyppi character varying(6),
    tietosuojavastaavatunnus numeric(38,10),
    nimi character varying(225),
    osoite character varying(255),
    sahkoposti character varying(255),
    puhelinnumero character varying(255),
    rivimuokkaajatunnus character varying(150),
    riviluotupvm timestamp without time zone,
    rivimuokattupvm timestamp without time zone
);


ALTER TABLE tietok.tietosuojavastaavahistoria OWNER TO tietokt;

--
-- Name: tietotietoryhma; Type: TABLE; Schema: tietok; Owner: tietokt
--

CREATE TABLE tietok.tietotietoryhma (
    rivitunnus numeric(38,10) NOT NULL,
    tietoryhmatunnus numeric(38,10) NOT NULL,
    tietotunnus numeric(38,10) NOT NULL,
    rivitila character varying(15) NOT NULL
);


ALTER TABLE tietok.tietotietoryhma OWNER TO tietokt;

--
-- Name: tietotietoryhmahistoria; Type: TABLE; Schema: tietok; Owner: tietokt
--

CREATE TABLE tietok.tietotietoryhmahistoria (
    rivitunnus numeric(38,10) NOT NULL,
    tietoryhmatunnus numeric(38,10) NOT NULL,
    tietotunnus numeric(38,10) NOT NULL,
    historiatyyppi character varying(6) NOT NULL,
    rivitila character varying(15) NOT NULL,
    riviluotupvm timestamp without time zone,
    rivimuokkaajatunnus character varying(150)
);


ALTER TABLE tietok.tietotietoryhmahistoria OWNER TO tietokt;

--
-- Name: tietovarahenktietoryhma_id_seq; Type: SEQUENCE; Schema: tietok; Owner: tietokt
--

CREATE SEQUENCE tietok.tietovarahenktietoryhma_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE tietok.tietovarahenktietoryhma_id_seq OWNER TO tietokt;

--
-- Name: tietovarakasitperushist_id_seq; Type: SEQUENCE; Schema: tietok; Owner: tietokt
--

CREATE SEQUENCE tietok.tietovarakasitperushist_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE tietok.tietovarakasitperushist_id_seq OWNER TO tietokt;

--
-- Name: tietovarakasittperustehistoria; Type: TABLE; Schema: tietok; Owner: tietokt
--

CREATE TABLE tietok.tietovarakasittperustehistoria (
    rivi_id numeric(38,10),
    tietovarantotunnus numeric(38,10),
    kasittelyn_peruste character varying(255),
    riviluotupvm timestamp without time zone,
    historiatyyppi character varying(6),
    rivimuokkaajatunnus character varying(150)
);


ALTER TABLE tietok.tietovarakasittperustehistoria OWNER TO tietokt;

--
-- Name: tietovaranto; Type: TABLE; Schema: tietok; Owner: tietokt
--

CREATE TABLE tietok.tietovaranto (
    tietovarantotunnus numeric(38,10) NOT NULL,
    nimi character varying(225),
    kuvaus character varying(4000),
    vastaava character varying(225),
    lisatieto character varying(4000),
    rivimuokkaajatunnus character varying(150),
    riviluotupvm timestamp without time zone,
    rivimuokattupvm timestamp without time zone,
    kuvaus_sidoksesta character varying(4000),
    rekisterinpitaja character varying(255),
    kasittelyn_tarkoitus character varying(4000),
    viittaus_henktieto_sopimukseen character varying(255),
    kolmannet_maat_ja_jarjestot character varying(255),
    suojatoimia_dokumentaatio character varying(255),
    tekniset_org_turvatoimenpiteet character varying(4000)
);


ALTER TABLE tietok.tietovaranto OWNER TO tietokt;

--
-- Name: tietovaranto_id_seq; Type: SEQUENCE; Schema: tietok; Owner: tietokt
--

CREATE SEQUENCE tietok.tietovaranto_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE tietok.tietovaranto_id_seq OWNER TO tietokt;

--
-- Name: tietovaranto_kasite_arvo; Type: TABLE; Schema: tietok; Owner: tietokt
--

CREATE TABLE tietok.tietovaranto_kasite_arvo (
    kasite_wid numeric(38,0) NOT NULL,
    kasite character varying(100),
    arvo character varying(200),
    selitys character varying(200)
);


ALTER TABLE tietok.tietovaranto_kasite_arvo OWNER TO tietokt;

--
-- Name: tietovarantohenkilotietoryhma; Type: TABLE; Schema: tietok; Owner: tietokt
--

CREATE TABLE tietok.tietovarantohenkilotietoryhma (
    rivitunnus numeric(38,10) NOT NULL,
    tietovarantotunnus numeric(38,10),
    henkilotietoryhma character varying(255)
);


ALTER TABLE tietok.tietovarantohenkilotietoryhma OWNER TO tietokt;

--
-- Name: tietovarantohist_id_seq; Type: SEQUENCE; Schema: tietok; Owner: tietokt
--

CREATE SEQUENCE tietok.tietovarantohist_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE tietok.tietovarantohist_id_seq OWNER TO tietokt;

--
-- Name: tietovarantohistoria; Type: TABLE; Schema: tietok; Owner: tietokt
--

CREATE TABLE tietok.tietovarantohistoria (
    rivi_id numeric(38,10) NOT NULL,
    historiatyyppi character varying(6),
    tietovarantotunnus numeric(38,10),
    nimi character varying(225),
    kuvaus character varying(4000),
    vastaava character varying(225),
    lisatieto character varying(4000),
    rivimuokkaajatunnus character varying(150),
    riviluotupvm timestamp without time zone,
    rivimuokattupvm timestamp without time zone,
    kuvaus_sidoksesta character varying(4000),
    rekisterinpitaja character varying(255),
    kasittelyn_tarkoitus character varying(4000),
    viittaus_henktieto_sopimukseen character varying(255),
    kolmannet_maat_ja_jarjestot character varying(255),
    suojatoimia_dokumentaatio character varying(255),
    tekniset_org_turvatoimenpiteet character varying(4000)
);


ALTER TABLE tietok.tietovarantohistoria OWNER TO tietokt;

--
-- Name: tietovarantokasittelynperuste; Type: TABLE; Schema: tietok; Owner: tietokt
--

CREATE TABLE tietok.tietovarantokasittelynperuste (
    rivitunnus numeric(38,10) NOT NULL,
    tietovarantotunnus numeric(38,10),
    kasittelyn_peruste character varying(255)
);


ALTER TABLE tietok.tietovarantokasittelynperuste OWNER TO tietokt;

--
-- Name: tietovarantorekisryhmahistoria; Type: TABLE; Schema: tietok; Owner: tietokt
--

CREATE TABLE tietok.tietovarantorekisryhmahistoria (
    rivi_id numeric(38,10),
    tietovarantotunnus numeric(38,10),
    rekisteroityryhma character varying(255),
    riviluotupvm timestamp without time zone,
    historiatyyppi character varying(6),
    rivimuokkaajatunnus character varying(150)
);


ALTER TABLE tietok.tietovarantorekisryhmahistoria OWNER TO tietokt;

--
-- Name: tietovarantorekisteroityryhma; Type: TABLE; Schema: tietok; Owner: tietokt
--

CREATE TABLE tietok.tietovarantorekisteroityryhma (
    rivitunnus numeric(38,10) NOT NULL,
    tietovarantotunnus numeric(38,10),
    rekisteroityryhma character varying(255)
);


ALTER TABLE tietok.tietovarantorekisteroityryhma OWNER TO tietokt;

--
-- Name: tietovarantorekistryhma_id_seq; Type: SEQUENCE; Schema: tietok; Owner: tietokt
--

CREATE SEQUENCE tietok.tietovarantorekistryhma_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE tietok.tietovarantorekistryhma_id_seq OWNER TO tietokt;

--
-- Name: tietovarantotiedonohjaus; Type: TABLE; Schema: tietok; Owner: tietokt
--

CREATE TABLE tietok.tietovarantotiedonohjaus (
    rivitunnus numeric(38,10) NOT NULL,
    tietovarantotunnus numeric(38,10),
    tiedonohjaussuunnitelma character varying(255)
);


ALTER TABLE tietok.tietovarantotiedonohjaus OWNER TO tietokt;

--
-- Name: tietovarantoturvatoimenpide; Type: TABLE; Schema: tietok; Owner: tietokt
--

CREATE TABLE tietok.tietovarantoturvatoimenpide (
    rivitunnus numeric(38,10) NOT NULL,
    tietovarantotunnus numeric(38,10),
    turvatoimenpide character varying(255)
);


ALTER TABLE tietok.tietovarantoturvatoimenpide OWNER TO tietokt;

--
-- Name: tietovarantovastaanottajaryhma; Type: TABLE; Schema: tietok; Owner: tietokt
--

CREATE TABLE tietok.tietovarantovastaanottajaryhma (
    rivitunnus numeric(38,10) NOT NULL,
    tietovarantotunnus numeric(38,10),
    vastaanottajaryhma character varying(255)
);


ALTER TABLE tietok.tietovarantovastaanottajaryhma OWNER TO tietokt;

--
-- Name: tietovarantoyhteisrekistpitaja; Type: TABLE; Schema: tietok; Owner: tietokt
--

CREATE TABLE tietok.tietovarantoyhteisrekistpitaja (
    rivitunnus numeric(38,10) NOT NULL,
    tietovarantotunnus numeric(38,10),
    yhteisrekisterinpitaja character varying(255)
);


ALTER TABLE tietok.tietovarantoyhteisrekistpitaja OWNER TO tietokt;

--
-- Name: tietovarantoyllapitomuutaho; Type: TABLE; Schema: tietok; Owner: tietokt
--

CREATE TABLE tietok.tietovarantoyllapitomuutaho (
    rivitunnus numeric(38,10) NOT NULL,
    tietovarantotunnus numeric(38,10),
    yllapito_muu_taho character varying(255)
);


ALTER TABLE tietok.tietovarantoyllapitomuutaho OWNER TO tietokt;

--
-- Name: tietovarantrekryhmahist_id_seq; Type: SEQUENCE; Schema: tietok; Owner: tietokt
--

CREATE SEQUENCE tietok.tietovarantrekryhmahist_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE tietok.tietovarantrekryhmahist_id_seq OWNER TO tietokt;

--
-- Name: tietovaratiedonohjaushistoria; Type: TABLE; Schema: tietok; Owner: tietokt
--

CREATE TABLE tietok.tietovaratiedonohjaushistoria (
    rivi_id numeric(38,10),
    tietovarantotunnus numeric(38,10),
    tiedonohjaussuunnitelma character varying(255),
    riviluotupvm timestamp without time zone,
    historiatyyppi character varying(6),
    rivimuokkaajatunnus character varying(150)
);


ALTER TABLE tietok.tietovaratiedonohjaushistoria OWNER TO tietokt;

--
-- Name: tietovaratiedonohjshist_id_seq; Type: SEQUENCE; Schema: tietok; Owner: tietokt
--

CREATE SEQUENCE tietok.tietovaratiedonohjshist_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE tietok.tietovaratiedonohjshist_id_seq OWNER TO tietokt;

--
-- Name: tietovaraturvatoimpidehistoria; Type: TABLE; Schema: tietok; Owner: tietokt
--

CREATE TABLE tietok.tietovaraturvatoimpidehistoria (
    rivi_id numeric(38,10),
    tietovarantotunnus numeric(38,10),
    turvatoimenpide character varying(255),
    riviluotupvm timestamp without time zone,
    historiatyyppi character varying(6),
    rivimuokkaajatunnus character varying(150)
);


ALTER TABLE tietok.tietovaraturvatoimpidehistoria OWNER TO tietokt;

--
-- Name: tietovaravastaanoryhmahistoria; Type: TABLE; Schema: tietok; Owner: tietokt
--

CREATE TABLE tietok.tietovaravastaanoryhmahistoria (
    rivi_id numeric(38,10),
    tietovarantotunnus numeric(38,10),
    vastaanottajaryhma character varying(255),
    riviluotupvm timestamp without time zone,
    historiatyyppi character varying(6),
    rivimuokkaajatunnus character varying(150)
);


ALTER TABLE tietok.tietovaravastaanoryhmahistoria OWNER TO tietokt;

--
-- Name: tietovaravastaryhmahist_id_seq; Type: SEQUENCE; Schema: tietok; Owner: tietokt
--

CREATE SEQUENCE tietok.tietovaravastaryhmahist_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE tietok.tietovaravastaryhmahist_id_seq OWNER TO tietokt;

--
-- Name: tietovarayhtrekpitahist_id_seq; Type: SEQUENCE; Schema: tietok; Owner: tietokt
--

CREATE SEQUENCE tietok.tietovarayhtrekpitahist_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE tietok.tietovarayhtrekpitahist_id_seq OWNER TO tietokt;

--
-- Name: tietovarayhtrekpitajahistoria; Type: TABLE; Schema: tietok; Owner: tietokt
--

CREATE TABLE tietok.tietovarayhtrekpitajahistoria (
    rivi_id numeric(38,10),
    tietovarantotunnus numeric(38,10),
    yhteisrekisterinpitaja character varying(255),
    riviluotupvm timestamp without time zone,
    historiatyyppi character varying(6),
    rivimuokkaajatunnus character varying(150)
);


ALTER TABLE tietok.tietovarayhtrekpitajahistoria OWNER TO tietokt;

--
-- Name: tietovarayllapitotahohistoria; Type: TABLE; Schema: tietok; Owner: tietokt
--

CREATE TABLE tietok.tietovarayllapitotahohistoria (
    rivi_id numeric(38,10),
    tietovarantotunnus numeric(38,10),
    yllapito_muu_taho character varying(255),
    riviluotupvm timestamp without time zone,
    historiatyyppi character varying(6),
    rivimuokkaajatunnus character varying(150)
);


ALTER TABLE tietok.tietovarayllapitotahohistoria OWNER TO tietokt;

--
-- Name: tietovarayllapttahohist_id_seq; Type: SEQUENCE; Schema: tietok; Owner: tietokt
--

CREATE SEQUENCE tietok.tietovarayllapttahohist_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE tietok.tietovarayllapttahohist_id_seq OWNER TO tietokt;

--
-- Name: tietovarhenktietoryhmahistoria; Type: TABLE; Schema: tietok; Owner: tietokt
--

CREATE TABLE tietok.tietovarhenktietoryhmahistoria (
    rivi_id numeric(38,10),
    tietovarantotunnus numeric(38,10),
    henkilotietoryhma character varying(255),
    riviluotupvm timestamp without time zone,
    historiatyyppi character varying(6),
    rivimuokkaajatunnus character varying(150)
);


ALTER TABLE tietok.tietovarhenktietoryhmahistoria OWNER TO tietokt;

--
-- Name: tietovarhentietryhmhist_id_seq; Type: SEQUENCE; Schema: tietok; Owner: tietokt
--

CREATE SEQUENCE tietok.tietovarhentietryhmhist_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE tietok.tietovarhentietryhmhist_id_seq OWNER TO tietokt;

--
-- Name: tietovarkasittelperuste_id_seq; Type: SEQUENCE; Schema: tietok; Owner: tietokt
--

CREATE SEQUENCE tietok.tietovarkasittelperuste_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE tietok.tietovarkasittelperuste_id_seq OWNER TO tietokt;

--
-- Name: tietovartiedonohjaus_id_seq; Type: SEQUENCE; Schema: tietok; Owner: tietokt
--

CREATE SEQUENCE tietok.tietovartiedonohjaus_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE tietok.tietovartiedonohjaus_id_seq OWNER TO tietokt;

--
-- Name: tietovarturvatoimenpide_id_seq; Type: SEQUENCE; Schema: tietok; Owner: tietokt
--

CREATE SEQUENCE tietok.tietovarturvatoimenpide_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE tietok.tietovarturvatoimenpide_id_seq OWNER TO tietokt;

--
-- Name: tietovarturvtoimpidhist_id_seq; Type: SEQUENCE; Schema: tietok; Owner: tietokt
--

CREATE SEQUENCE tietok.tietovarturvtoimpidhist_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE tietok.tietovarturvtoimpidhist_id_seq OWNER TO tietokt;

--
-- Name: tietovarvastaanottryhma_id_seq; Type: SEQUENCE; Schema: tietok; Owner: tietokt
--

CREATE SEQUENCE tietok.tietovarvastaanottryhma_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE tietok.tietovarvastaanottryhma_id_seq OWNER TO tietokt;

--
-- Name: tietovaryhteisrekpitaja_id_seq; Type: SEQUENCE; Schema: tietok; Owner: tietokt
--

CREATE SEQUENCE tietok.tietovaryhteisrekpitaja_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE tietok.tietovaryhteisrekpitaja_id_seq OWNER TO tietokt;

--
-- Name: tietovaryllapitomuutaho_id_seq; Type: SEQUENCE; Schema: tietok; Owner: tietokt
--

CREATE SEQUENCE tietok.tietovaryllapitomuutaho_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE tietok.tietovaryllapitomuutaho_id_seq OWNER TO tietokt;

--
-- Name: tjpliittyvajarjhist_id_seq; Type: SEQUENCE; Schema: tietok; Owner: tietokt
--

CREATE SEQUENCE tietok.tjpliittyvajarjhist_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE tietok.tjpliittyvajarjhist_id_seq OWNER TO tietokt;

--
-- Name: toimintaprosessi; Type: TABLE; Schema: tietok; Owner: tietokt
--

CREATE TABLE tietok.toimintaprosessi (
    toimintaprosessitunnus numeric(38,10) NOT NULL,
    nimi character varying(225),
    vastaava_organisaatio character varying(255),
    tarkoitus character varying(4000),
    rivimuokkaajatunnus character varying(150),
    riviluotupvm timestamp without time zone,
    rivimuokattupvm timestamp without time zone
);


ALTER TABLE tietok.toimintaprosessi OWNER TO tietokt;

--
-- Name: toimintaprosessi_id_seq; Type: SEQUENCE; Schema: tietok; Owner: tietokt
--

CREATE SEQUENCE tietok.toimintaprosessi_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE tietok.toimintaprosessi_id_seq OWNER TO tietokt;

--
-- Name: toimintaprosessi_kasite_arvo; Type: TABLE; Schema: tietok; Owner: tietokt
--

CREATE TABLE tietok.toimintaprosessi_kasite_arvo (
    kasite_wid numeric(38,0) NOT NULL,
    kasite character varying(100),
    arvo character varying(200)
);


ALTER TABLE tietok.toimintaprosessi_kasite_arvo OWNER TO tietokt;

--
-- Name: toimintaprosessihist_id_seq; Type: SEQUENCE; Schema: tietok; Owner: tietokt
--

CREATE SEQUENCE tietok.toimintaprosessihist_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE tietok.toimintaprosessihist_id_seq OWNER TO tietokt;

--
-- Name: toimintaprosessihistoria; Type: TABLE; Schema: tietok; Owner: tietokt
--

CREATE TABLE tietok.toimintaprosessihistoria (
    rivi_id numeric(38,10) NOT NULL,
    historiatyyppi character varying(6),
    toimintaprosessitunnus numeric(38,10),
    nimi character varying(225),
    vastaava_organisaatio character varying(255),
    tarkoitus character varying(4000),
    rivimuokkaajatunnus character varying(150),
    riviluotupvm timestamp without time zone,
    rivimuokattupvm timestamp without time zone
);


ALTER TABLE tietok.toimintaprosessihistoria OWNER TO tietokt;

--
-- Name: toimintaprosessitietovaranto; Type: TABLE; Schema: tietok; Owner: tietokt
--

CREATE TABLE tietok.toimintaprosessitietovaranto (
    rivitunnus numeric(38,10) NOT NULL,
    tietovarantotunnus numeric(38,10),
    toimintaprosessitunnus numeric(38,10)
);


ALTER TABLE tietok.toimintaprosessitietovaranto OWNER TO tietokt;

--
-- Name: toimintaprostietovahist_id_seq; Type: SEQUENCE; Schema: tietok; Owner: tietokt
--

CREATE SEQUENCE tietok.toimintaprostietovahist_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE tietok.toimintaprostietovahist_id_seq OWNER TO tietokt;

--
-- Name: toimintaprostietovarahistoria; Type: TABLE; Schema: tietok; Owner: tietokt
--

CREATE TABLE tietok.toimintaprostietovarahistoria (
    rivi_id numeric(38,10) NOT NULL,
    tietovarantotunnus numeric(38,10),
    toimintaprosessitunnus numeric(38,10),
    riviluotupvm timestamp without time zone,
    historiatyyppi character varying(6),
    rivimuokkaajatunnus character varying(150)
);


ALTER TABLE tietok.toimintaprostietovarahistoria OWNER TO tietokt;

--
-- Name: toimintaprostietovarant_id_seq; Type: SEQUENCE; Schema: tietok; Owner: tietokt
--

CREATE SEQUENCE tietok.toimintaprostietovarant_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE tietok.toimintaprostietovarant_id_seq OWNER TO tietokt;

--
-- Name: yleissuunnitelma; Type: TABLE; Schema: tietok; Owner: tietokt
--

CREATE TABLE tietok.yleissuunnitelma (
    yleissuunnitelmatunnus numeric(38,10) NOT NULL,
    nimi character varying(4000),
    koodi character varying(4000),
    kasittelija character varying(4000),
    kuvaus character varying(4000),
    diaariteksti character varying(4000),
    elykeskus character varying(4000),
    suunntelmanstatus character varying(4000),
    kohde1 character varying(4000),
    kohde2 character varying(4000),
    selitys character varying(4000),
    paatoshaosisalto character varying(4000),
    paatoskhosisalto character varying(4000),
    tiesuunnitelmialaadittu character varying(4000),
    kokonaiskustannukset numeric(38,10),
    kustannuksetvaltio numeric(38,10),
    kustannuksetkunta numeric(38,10),
    kustannuksetsahko numeric(38,10),
    kustannuksetviestinta numeric(38,10),
    kustannuksetvesi numeric(38,10),
    indeksivuosi numeric(38,10),
    maarakennusindeksi numeric(38,10),
    muistutuksia numeric(38,10),
    valituksia numeric(38,10),
    sisaltaamaantienlakkaamisen character varying(255),
    kiireellinenkasittelypyydetty character varying(255),
    yvahanke character varying(255),
    hyvaksymisesityslaadittu timestamp without time zone,
    hyvaksymisesityssaapunut timestamp without time zone,
    aloituskuulutus timestamp without time zone,
    hyvaksymispaatosannettu timestamp without time zone,
    jatkopaatosannettu1 timestamp without time zone,
    jatkopaatosannettu2 timestamp without time zone,
    palautettuelylle timestamp without time zone,
    hyvaksymisesituslvmlaadittu timestamp without time zone,
    hyvaksymispaatoslvmannettu timestamp without time zone,
    jatkopaatoslvmannettu timestamp without time zone,
    osapaatosannettu timestamp without time zone,
    valipaatoshao timestamp without time zone,
    hyvaksymispaatoshaokumottu timestamp without time zone,
    paatoshao timestamp without time zone,
    valipaatoskho timestamp without time zone,
    hyvaksymispaatoskhokumottu timestamp without time zone,
    paatoskho timestamp without time zone,
    lainvoimaisuus timestamp without time zone,
    lainvoimaisuudenpaattyminen timestamp without time zone,
    rauennut timestamp without time zone,
    riviluotupvm timestamp without time zone,
    rivimuokattupvm timestamp without time zone,
    rivimuokkaajatunnus character varying(150),
    document_id character varying(255)
);


ALTER TABLE tietok.yleissuunnitelma OWNER TO tietokt;

--
-- Name: yleissuunnitelmahistoria; Type: TABLE; Schema: tietok; Owner: tietokt
--

CREATE TABLE tietok.yleissuunnitelmahistoria (
    rivi_id numeric(38,10) NOT NULL,
    historiatyyppi character varying(6) NOT NULL,
    yleissuunnitelmatunnus numeric(38,10) NOT NULL,
    nimi character varying(4000),
    koodi character varying(4000),
    kasittelija character varying(4000),
    kuvaus character varying(4000),
    diaariteksti character varying(4000),
    elykeskus character varying(4000),
    suunntelmanstatus character varying(4000),
    kohde1 character varying(4000),
    kohde2 character varying(4000),
    selitys character varying(4000),
    paatoshaosisalto character varying(4000),
    paatoskhosisalto character varying(4000),
    tiesuunnitelmialaadittu character varying(4000),
    kokonaiskustannukset numeric(38,10),
    kustannuksetvaltio numeric(38,10),
    kustannuksetkunta numeric(38,10),
    kustannuksetsahko numeric(38,10),
    kustannuksetviestinta numeric(38,10),
    kustannuksetvesi numeric(38,10),
    indeksivuosi numeric(38,10),
    maarakennusindeksi numeric(38,10),
    muistutuksia numeric(38,10),
    valituksia numeric(38,10),
    sisaltaamaantienlakkaamisen character varying(255),
    kiireellinenkasittelypyydetty character varying(255),
    yvahanke character varying(255),
    hyvaksymisesityslaadittu timestamp without time zone,
    hyvaksymisesityssaapunut timestamp without time zone,
    aloituskuulutus timestamp without time zone,
    hyvaksymispaatosannettu timestamp without time zone,
    jatkopaatosannettu1 timestamp without time zone,
    jatkopaatosannettu2 timestamp without time zone,
    palautettuelylle timestamp without time zone,
    hyvaksymisesituslvmlaadittu timestamp without time zone,
    hyvaksymispaatoslvmannettu timestamp without time zone,
    jatkopaatoslvmannettu timestamp without time zone,
    osapaatosannettu timestamp without time zone,
    valipaatoshao timestamp without time zone,
    hyvaksymispaatoshaokumottu timestamp without time zone,
    paatoshao timestamp without time zone,
    valipaatoskho timestamp without time zone,
    hyvaksymispaatoskhokumottu timestamp without time zone,
    paatoskho timestamp without time zone,
    lainvoimaisuus timestamp without time zone,
    lainvoimaisuudenpaattyminen timestamp without time zone,
    rauennut timestamp without time zone,
    rivitunnus numeric(38,10),
    rivitila character varying(15),
    riviluotupvm timestamp without time zone,
    rivimuokattupvm timestamp without time zone,
    rivimuokkaajatunnus character varying(150),
    document_id character varying(255)
);


ALTER TABLE tietok.yleissuunnitelmahistoria OWNER TO tietokt;

--
-- Name: depr_jt_nakyvyys depr_jt_nakyvyys_pkey; Type: CONSTRAINT; Schema: tietok; Owner: tietokt
--

ALTER TABLE ONLY tietok.depr_jt_nakyvyys
    ADD CONSTRAINT depr_jt_nakyvyys_pkey PRIMARY KEY (id);


--
-- Name: depr_looginentietovarantotieto depr_looginentietovarantotieto_pkey; Type: CONSTRAINT; Schema: tietok; Owner: tietokt
--

ALTER TABLE ONLY tietok.depr_looginentietovarantotieto
    ADD CONSTRAINT depr_looginentietovarantotieto_pkey PRIMARY KEY (rivitunnus);


--
-- Name: depr_looginentvtoimitus depr_looginentvtoimitus_pkey; Type: CONSTRAINT; Schema: tietok; Owner: tietokt
--

ALTER TABLE ONLY tietok.depr_looginentvtoimitus
    ADD CONSTRAINT depr_looginentvtoimitus_pkey PRIMARY KEY (rivitunnus);


--
-- Name: depr_paatietoryhmalahde depr_paatietoryhmalahde_pkey; Type: CONSTRAINT; Schema: tietok; Owner: tietokt
--

ALTER TABLE ONLY tietok.depr_paatietoryhmalahde
    ADD CONSTRAINT depr_paatietoryhmalahde_pkey PRIMARY KEY (rivitunnus);


--
-- Name: depr_paatietoryhmasynonyymi depr_paatietoryhmasynonyymi_pkey; Type: CONSTRAINT; Schema: tietok; Owner: tietokt
--

ALTER TABLE ONLY tietok.depr_paatietoryhmasynonyymi
    ADD CONSTRAINT depr_paatietoryhmasynonyymi_pkey PRIMARY KEY (rivitunnus);


--
-- Name: depr_parametrit depr_parametrit_pkey; Type: CONSTRAINT; Schema: tietok; Owner: tietokt
--

ALTER TABLE ONLY tietok.depr_parametrit
    ADD CONSTRAINT depr_parametrit_pkey PRIMARY KEY (avain);


--
-- Name: depr_rekisterijulkaisu depr_rekisterijulkaisu_pkey; Type: CONSTRAINT; Schema: tietok; Owner: tietokt
--

ALTER TABLE ONLY tietok.depr_rekisterijulkaisu
    ADD CONSTRAINT depr_rekisterijulkaisu_pkey PRIMARY KEY (julkaisutieto_rivimuokkaajatun, rivimuokkaajatunnus);


--
-- Name: depr_rekisterikasitemalli depr_rekisterikasitemalli_pkey; Type: CONSTRAINT; Schema: tietok; Owner: tietokt
--

ALTER TABLE ONLY tietok.depr_rekisterikasitemalli
    ADD CONSTRAINT depr_rekisterikasitemalli_pkey PRIMARY KEY (kasitemalli);


--
-- Name: depr_rekisterikayttotiheys depr_rekisterikayttotiheys_pkey; Type: CONSTRAINT; Schema: tietok; Owner: tietokt
--

ALTER TABLE ONLY tietok.depr_rekisterikayttotiheys
    ADD CONSTRAINT depr_rekisterikayttotiheys_pkey PRIMARY KEY (kayttotiheys);


--
-- Name: depr_rekisterilinkki depr_rekisterilinkki_pkey; Type: CONSTRAINT; Schema: tietok; Owner: tietokt
--

ALTER TABLE ONLY tietok.depr_rekisterilinkki
    ADD CONSTRAINT depr_rekisterilinkki_pkey PRIMARY KEY (linkki);


--
-- Name: depr_rekisterimuokkaaja depr_rekisterimuokkaaja_pkey; Type: CONSTRAINT; Schema: tietok; Owner: tietokt
--

ALTER TABLE ONLY tietok.depr_rekisterimuokkaaja
    ADD CONSTRAINT depr_rekisterimuokkaaja_pkey PRIMARY KEY (rivimuokkaajatunnus);


--
-- Name: depr_rekisteripaivitystiheys depr_rekisteripaivitystiheys_pkey; Type: CONSTRAINT; Schema: tietok; Owner: tietokt
--

ALTER TABLE ONLY tietok.depr_rekisteripaivitystiheys
    ADD CONSTRAINT depr_rekisteripaivitystiheys_pkey PRIMARY KEY (paivitystiheys);


--
-- Name: depr_rekisteripalvelutaso depr_rekisteripalvelutaso_pkey; Type: CONSTRAINT; Schema: tietok; Owner: tietokt
--

ALTER TABLE ONLY tietok.depr_rekisteripalvelutaso
    ADD CONSTRAINT depr_rekisteripalvelutaso_pkey PRIMARY KEY (palvelutaso);


--
-- Name: depr_rekisterisanasto depr_rekisterisanasto_pkey; Type: CONSTRAINT; Schema: tietok; Owner: tietokt
--

ALTER TABLE ONLY tietok.depr_rekisterisanasto
    ADD CONSTRAINT depr_rekisterisanasto_pkey PRIMARY KEY (sana);


--
-- Name: depr_rekisteritietolahde depr_rekisteritietolahde_pkey; Type: CONSTRAINT; Schema: tietok; Owner: tietokt
--

ALTER TABLE ONLY tietok.depr_rekisteritietolahde
    ADD CONSTRAINT depr_rekisteritietolahde_pkey PRIMARY KEY (tietolahde);


--
-- Name: depr_rekisteritila depr_rekisteritila_pkey; Type: CONSTRAINT; Schema: tietok; Owner: tietokt
--

ALTER TABLE ONLY tietok.depr_rekisteritila
    ADD CONSTRAINT depr_rekisteritila_pkey PRIMARY KEY (tila, rivimuokkaajatunnus);


--
-- Name: depr_reklooginentietovarastoo depr_reklooginentietovarastoo_pkey; Type: CONSTRAINT; Schema: tietok; Owner: tietokt
--

ALTER TABLE ONLY tietok.depr_reklooginentietovarastoo
    ADD CONSTRAINT depr_reklooginentietovarastoo_pkey PRIMARY KEY (omistaja);


--
-- Name: depr_reklooginentietovarastos depr_reklooginentietovarastos_pkey; Type: CONSTRAINT; Schema: tietok; Owner: tietokt
--

ALTER TABLE ONLY tietok.depr_reklooginentietovarastos
    ADD CONSTRAINT depr_reklooginentietovarastos_pkey PRIMARY KEY (kayttaja);


--
-- Name: depr_rektiedontoimistustapa depr_rektiedontoimistustapa_pkey; Type: CONSTRAINT; Schema: tietok; Owner: tietokt
--

ALTER TABLE ONLY tietok.depr_rektiedontoimistustapa
    ADD CONSTRAINT depr_rektiedontoimistustapa_pkey PRIMARY KEY (toimitustapa);


--
-- Name: depr_rektietojarjestelmatila depr_rektietojarjestelmatila_pkey; Type: CONSTRAINT; Schema: tietok; Owner: tietokt
--

ALTER TABLE ONLY tietok.depr_rektietojarjestelmatila
    ADD CONSTRAINT depr_rektietojarjestelmatila_pkey PRIMARY KEY (tila);


--
-- Name: depr_rektietokantateknologia depr_rektietokantateknologia_pkey; Type: CONSTRAINT; Schema: tietok; Owner: tietokt
--

ALTER TABLE ONLY tietok.depr_rektietokantateknologia
    ADD CONSTRAINT depr_rektietokantateknologia_pkey PRIMARY KEY (teknologia);


--
-- Name: depr_rektietoryhmaomistaja depr_rektietoryhmaomistaja_pkey; Type: CONSTRAINT; Schema: tietok; Owner: tietokt
--

ALTER TABLE ONLY tietok.depr_rektietoryhmaomistaja
    ADD CONSTRAINT depr_rektietoryhmaomistaja_pkey PRIMARY KEY (omistaja);


--
-- Name: depr_rektietosuojataso depr_rektietosuojataso_pkey; Type: CONSTRAINT; Schema: tietok; Owner: tietokt
--

ALTER TABLE ONLY tietok.depr_rektietosuojataso
    ADD CONSTRAINT depr_rektietosuojataso_pkey PRIMARY KEY (tietosuojataso, rivimuokkaajatunnus);


--
-- Name: depr_rektietovarastoomistaja depr_rektietovarastoomistaja_pkey; Type: CONSTRAINT; Schema: tietok; Owner: tietokt
--

ALTER TABLE ONLY tietok.depr_rektietovarastoomistaja
    ADD CONSTRAINT depr_rektietovarastoomistaja_pkey PRIMARY KEY (kayttaja);


--
-- Name: depr_tietojarjestelmapalvelu depr_tietojarjestelmapalvelu_pkey; Type: CONSTRAINT; Schema: tietok; Owner: tietokt
--

ALTER TABLE ONLY tietok.depr_tietojarjestelmapalvelu
    ADD CONSTRAINT depr_tietojarjestelmapalvelu_pkey PRIMARY KEY (tietojarjestelmapalvelutunnus);


--
-- Name: depr_tietolahde depr_tietolahde_pkey; Type: CONSTRAINT; Schema: tietok; Owner: tietokt
--

ALTER TABLE ONLY tietok.depr_tietolahde
    ADD CONSTRAINT depr_tietolahde_pkey PRIMARY KEY (rivitunnus);


--
-- Name: depr_tietoryhmalahde depr_tietoryhmalahde_pkey; Type: CONSTRAINT; Schema: tietok; Owner: tietokt
--

ALTER TABLE ONLY tietok.depr_tietoryhmalahde
    ADD CONSTRAINT depr_tietoryhmalahde_pkey PRIMARY KEY (rivitunnus);


--
-- Name: depr_tjsalkkufyysinen depr_tjsalkkufyysinen_pkey; Type: CONSTRAINT; Schema: tietok; Owner: tietokt
--

ALTER TABLE ONLY tietok.depr_tjsalkkufyysinen
    ADD CONSTRAINT depr_tjsalkkufyysinen_pkey PRIMARY KEY (rivitunnus);


--
-- Name: fyysinentietovaranto fyysinentietovaranto_pkey; Type: CONSTRAINT; Schema: tietok; Owner: tietokt
--

ALTER TABLE ONLY tietok.fyysinentietovaranto
    ADD CONSTRAINT fyysinentietovaranto_pkey PRIMARY KEY (fyysinentietovarantotunnus);


--
-- Name: fyysinentietovarantohistoria fyysinentietovarantohistoria_pkey; Type: CONSTRAINT; Schema: tietok; Owner: tietokt
--

ALTER TABLE ONLY tietok.fyysinentietovarantohistoria
    ADD CONSTRAINT fyysinentietovarantohistoria_pkey PRIMARY KEY (rivi_id);


--
-- Name: henkilo henkilo_pkey; Type: CONSTRAINT; Schema: tietok; Owner: tietokt
--

ALTER TABLE ONLY tietok.henkilo
    ADD CONSTRAINT henkilo_pkey PRIMARY KEY (tunnus);


--
-- Name: henkilo_temp henkilo_temp_pkey; Type: CONSTRAINT; Schema: tietok; Owner: tietokt
--

ALTER TABLE ONLY tietok.henkilo_temp
    ADD CONSTRAINT henkilo_temp_pkey PRIMARY KEY (tunnus);


--
-- Name: jarjestelma_henkilo_rooli_hist jarjestelma_henkilo_rooli_hist_pkey; Type: CONSTRAINT; Schema: tietok; Owner: tietokt
--

ALTER TABLE ONLY tietok.jarjestelma_henkilo_rooli_hist
    ADD CONSTRAINT jarjestelma_henkilo_rooli_hist_pkey PRIMARY KEY (rivitunnus);


--
-- Name: jarjestelma_henkilo_rooli jarjestelma_henkilo_rooli_pkey; Type: CONSTRAINT; Schema: tietok; Owner: tietokt
--

ALTER TABLE ONLY tietok.jarjestelma_henkilo_rooli
    ADD CONSTRAINT jarjestelma_henkilo_rooli_pkey PRIMARY KEY (henkilo_id, rooli_id, jarjestelma_id);


--
-- Name: jarjestelmalinkkaus jarjestelmalinkkaus_pkey; Type: CONSTRAINT; Schema: tietok; Owner: tietokt
--

ALTER TABLE ONLY tietok.jarjestelmalinkkaus
    ADD CONSTRAINT jarjestelmalinkkaus_pkey PRIMARY KEY (rivitunnus);


--
-- Name: jarjestelmalinkkaushistoria jarjestelmalinkkaushistoria_pkey; Type: CONSTRAINT; Schema: tietok; Owner: tietokt
--

ALTER TABLE ONLY tietok.jarjestelmalinkkaushistoria
    ADD CONSTRAINT jarjestelmalinkkaushistoria_pkey PRIMARY KEY (rivitunnus);


--
-- Name: jarjestelmalooginen jarjestelmalooginen_pkey; Type: CONSTRAINT; Schema: tietok; Owner: tietokt
--

ALTER TABLE ONLY tietok.jarjestelmalooginen
    ADD CONSTRAINT jarjestelmalooginen_pkey PRIMARY KEY (rivitunnus);


--
-- Name: jarjestelmalooginenhistoria jarjestelmalooginenhistoria_pkey; Type: CONSTRAINT; Schema: tietok; Owner: tietokt
--

ALTER TABLE ONLY tietok.jarjestelmalooginenhistoria
    ADD CONSTRAINT jarjestelmalooginenhistoria_pkey PRIMARY KEY (rivi_id);


--
-- Name: jarjestelmatieto_kentat jarjestelmatieto_kentat_pkey; Type: CONSTRAINT; Schema: tietok; Owner: tietokt
--

ALTER TABLE ONLY tietok.jarjestelmatieto_kentat
    ADD CONSTRAINT jarjestelmatieto_kentat_pkey PRIMARY KEY (nimi);


--
-- Name: joinhuomautus joinhuomautus_pkey; Type: CONSTRAINT; Schema: tietok; Owner: tietokt
--

ALTER TABLE ONLY tietok.joinhuomautus
    ADD CONSTRAINT joinhuomautus_pkey PRIMARY KEY (rivitunnus);


--
-- Name: joinhuomautushistoria joinhuomautushistoria_pkey; Type: CONSTRAINT; Schema: tietok; Owner: tietokt
--

ALTER TABLE ONLY tietok.joinhuomautushistoria
    ADD CONSTRAINT joinhuomautushistoria_pkey PRIMARY KEY (rivi_id);


--
-- Name: looginen_kasite_arvo looginen_kasite_arvo_pkey; Type: CONSTRAINT; Schema: tietok; Owner: tietokt
--

ALTER TABLE ONLY tietok.looginen_kasite_arvo
    ADD CONSTRAINT looginen_kasite_arvo_pkey PRIMARY KEY (kasite_wid);


--
-- Name: looginenfyysinenhistoria looginenfyysinenhistoria_pkey; Type: CONSTRAINT; Schema: tietok; Owner: tietokt
--

ALTER TABLE ONLY tietok.looginenfyysinenhistoria
    ADD CONSTRAINT looginenfyysinenhistoria_pkey PRIMARY KEY (rivitunnus);


--
-- Name: looginentietovaranto looginentietovaranto_pkey; Type: CONSTRAINT; Schema: tietok; Owner: tietokt
--

ALTER TABLE ONLY tietok.looginentietovaranto
    ADD CONSTRAINT looginentietovaranto_pkey PRIMARY KEY (looginentietovarantotunnus);


--
-- Name: looginentietovarantofyysinenti looginentietovarantofyysinenti_pkey; Type: CONSTRAINT; Schema: tietok; Owner: tietokt
--

ALTER TABLE ONLY tietok.looginentietovarantofyysinenti
    ADD CONSTRAINT looginentietovarantofyysinenti_pkey PRIMARY KEY (rivitunnus);


--
-- Name: looginentietovarantohistoria looginentietovarantohistoria_pkey; Type: CONSTRAINT; Schema: tietok; Owner: tietokt
--

ALTER TABLE ONLY tietok.looginentietovarantohistoria
    ADD CONSTRAINT looginentietovarantohistoria_pkey PRIMARY KEY (rivi_id);


--
-- Name: organisaatio organisaatio_pkey; Type: CONSTRAINT; Schema: tietok; Owner: tietokt
--

ALTER TABLE ONLY tietok.organisaatio
    ADD CONSTRAINT organisaatio_pkey PRIMARY KEY (organisaatiotunnus);


--
-- Name: organisaatiohistoria organisaatiohistoria_pkey; Type: CONSTRAINT; Schema: tietok; Owner: tietokt
--

ALTER TABLE ONLY tietok.organisaatiohistoria
    ADD CONSTRAINT organisaatiohistoria_pkey PRIMARY KEY (rivi_id);


--
-- Name: paatietoryhma paatietoryhma_pkey; Type: CONSTRAINT; Schema: tietok; Owner: tietokt
--

ALTER TABLE ONLY tietok.paatietoryhma
    ADD CONSTRAINT paatietoryhma_pkey PRIMARY KEY (paatietoryhmatunnus);


--
-- Name: paatietoryhmahistoria paatietoryhmahistoria_pkey; Type: CONSTRAINT; Schema: tietok; Owner: tietokt
--

ALTER TABLE ONLY tietok.paatietoryhmahistoria
    ADD CONSTRAINT paatietoryhmahistoria_pkey PRIMARY KEY (rivi_id);


--
-- Name: palvelu_kasite_arvo palvelu_kasite_arvo_pkey; Type: CONSTRAINT; Schema: tietok; Owner: tietokt
--

ALTER TABLE ONLY tietok.palvelu_kasite_arvo
    ADD CONSTRAINT palvelu_kasite_arvo_pkey PRIMARY KEY (kasite_id);


--
-- Name: palvelu palvelu_pkey; Type: CONSTRAINT; Schema: tietok; Owner: tietokt
--

ALTER TABLE ONLY tietok.palvelu
    ADD CONSTRAINT palvelu_pkey PRIMARY KEY (palvelutunnus);


--
-- Name: palveluhistoria palveluhistoria_pkey; Type: CONSTRAINT; Schema: tietok; Owner: tietokt
--

ALTER TABLE ONLY tietok.palveluhistoria
    ADD CONSTRAINT palveluhistoria_pkey PRIMARY KEY (rivi_id);


--
-- Name: palvelukatalogi_kasite_arvo palvelukatalogi_kasite_arvo_pkey; Type: CONSTRAINT; Schema: tietok; Owner: tietokt
--

ALTER TABLE ONLY tietok.palvelukatalogi_kasite_arvo
    ADD CONSTRAINT palvelukatalogi_kasite_arvo_pkey PRIMARY KEY (kasite_id);


--
-- Name: rooli rooli_pkey; Type: CONSTRAINT; Schema: tietok; Owner: tietokt
--

ALTER TABLE ONLY tietok.rooli
    ADD CONSTRAINT rooli_pkey PRIMARY KEY (tunnus);


--
-- Name: sovellus_henkilo_rooli_hist sovellus_henkilo_rooli_hist_pkey; Type: CONSTRAINT; Schema: tietok; Owner: tietokt
--

ALTER TABLE ONLY tietok.sovellus_henkilo_rooli_hist
    ADD CONSTRAINT sovellus_henkilo_rooli_hist_pkey PRIMARY KEY (rivitunnus);


--
-- Name: sovellus_henkilo_rooli sovellus_henkilo_rooli_pkey; Type: CONSTRAINT; Schema: tietok; Owner: tietokt
--

ALTER TABLE ONLY tietok.sovellus_henkilo_rooli
    ADD CONSTRAINT sovellus_henkilo_rooli_pkey PRIMARY KEY (henkilo_id, rooli_id, sovellus_id);


--
-- Name: sovellus_history sovellus_history_pkey; Type: CONSTRAINT; Schema: tietok; Owner: tietokt
--

ALTER TABLE ONLY tietok.sovellus_history
    ADD CONSTRAINT sovellus_history_pkey PRIMARY KEY (rivitunnus);


--
-- Name: sovellus sovellus_pkey; Type: CONSTRAINT; Schema: tietok; Owner: tietokt
--

ALTER TABLE ONLY tietok.sovellus
    ADD CONSTRAINT sovellus_pkey PRIMARY KEY (tunnus);


--
-- Name: sovellus_tuonti sovellus_tuonti_pkey; Type: CONSTRAINT; Schema: tietok; Owner: tietokt
--

ALTER TABLE ONLY tietok.sovellus_tuonti
    ADD CONSTRAINT sovellus_tuonti_pkey PRIMARY KEY (tuonti_id);


--
-- Name: termilomake termilomake_pkey; Type: CONSTRAINT; Schema: tietok; Owner: tietokt
--

ALTER TABLE ONLY tietok.termilomake
    ADD CONSTRAINT termilomake_pkey PRIMARY KEY (termilomaketunnus);


--
-- Name: termilomakeaskasihistoria termilomakeaskasihistoria_pkey; Type: CONSTRAINT; Schema: tietok; Owner: tietokt
--

ALTER TABLE ONLY tietok.termilomakeaskasihistoria
    ADD CONSTRAINT termilomakeaskasihistoria_pkey PRIMARY KEY (rivi_id);


--
-- Name: termilomakeassoskasite termilomakeassoskasite_pkey; Type: CONSTRAINT; Schema: tietok; Owner: tietokt
--

ALTER TABLE ONLY tietok.termilomakeassoskasite
    ADD CONSTRAINT termilomakeassoskasite_pkey PRIMARY KEY (rivitunnus);


--
-- Name: termilomakehierarkkasite termilomakehierarkkasite_pkey; Type: CONSTRAINT; Schema: tietok; Owner: tietokt
--

ALTER TABLE ONLY tietok.termilomakehierarkkasite
    ADD CONSTRAINT termilomakehierarkkasite_pkey PRIMARY KEY (rivitunnus);


--
-- Name: termilomakehistoria termilomakehistoria_pkey; Type: CONSTRAINT; Schema: tietok; Owner: tietokt
--

ALTER TABLE ONLY tietok.termilomakehistoria
    ADD CONSTRAINT termilomakehistoria_pkey PRIMARY KEY (rivi_id);


--
-- Name: termilomakehkasitehistoria termilomakehkasitehistoria_pkey; Type: CONSTRAINT; Schema: tietok; Owner: tietokt
--

ALTER TABLE ONLY tietok.termilomakehkasitehistoria
    ADD CONSTRAINT termilomakehkasitehistoria_pkey PRIMARY KEY (rivi_id);


--
-- Name: termilomakekoostkasihistoria termilomakekoostkasihistoria_pkey; Type: CONSTRAINT; Schema: tietok; Owner: tietokt
--

ALTER TABLE ONLY tietok.termilomakekoostkasihistoria
    ADD CONSTRAINT termilomakekoostkasihistoria_pkey PRIMARY KEY (rivi_id);


--
-- Name: termilomakekoostkasite termilomakekoostkasite_pkey; Type: CONSTRAINT; Schema: tietok; Owner: tietokt
--

ALTER TABLE ONLY tietok.termilomakekoostkasite
    ADD CONSTRAINT termilomakekoostkasite_pkey PRIMARY KEY (rivitunnus);


--
-- Name: tiesuunnitelma tiesuunnitelma_pkey; Type: CONSTRAINT; Schema: tietok; Owner: tietokt
--

ALTER TABLE ONLY tietok.tiesuunnitelma
    ADD CONSTRAINT tiesuunnitelma_pkey PRIMARY KEY (tiesuunnitelmatunnus);


--
-- Name: tiesuunnitelmahistoria tiesuunnitelmahistoria_pkey; Type: CONSTRAINT; Schema: tietok; Owner: tietokt
--

ALTER TABLE ONLY tietok.tiesuunnitelmahistoria
    ADD CONSTRAINT tiesuunnitelmahistoria_pkey PRIMARY KEY (rivi_id);


--
-- Name: tieto_kasite_arvo tieto_kasite_arvo_pkey; Type: CONSTRAINT; Schema: tietok; Owner: tietokt
--

ALTER TABLE ONLY tietok.tieto_kasite_arvo
    ADD CONSTRAINT tieto_kasite_arvo_pkey PRIMARY KEY (kasite_wid);


--
-- Name: tieto tieto_pkey; Type: CONSTRAINT; Schema: tietok; Owner: tietokt
--

ALTER TABLE ONLY tietok.tieto
    ADD CONSTRAINT tieto_pkey PRIMARY KEY (tietotunnus);


--
-- Name: tietohistoria tietohistoria_pkey; Type: CONSTRAINT; Schema: tietok; Owner: tietokt
--

ALTER TABLE ONLY tietok.tietohistoria
    ADD CONSTRAINT tietohistoria_pkey PRIMARY KEY (rivi_id);


--
-- Name: tietojarjestelma_kasite_arvo tietojarjestelma_kasite_arvo_pkey; Type: CONSTRAINT; Schema: tietok; Owner: tietokt
--

ALTER TABLE ONLY tietok.tietojarjestelma_kasite_arvo
    ADD CONSTRAINT tietojarjestelma_kasite_arvo_pkey PRIMARY KEY (kasite_wid);


--
-- Name: tietojarjestelmapalvelu tietojarjestelmapalvelu_pkey; Type: CONSTRAINT; Schema: tietok; Owner: tietokt
--

ALTER TABLE ONLY tietok.tietojarjestelmapalvelu
    ADD CONSTRAINT tietojarjestelmapalvelu_pkey PRIMARY KEY (tunnus);


--
-- Name: tietojarjestelmasalkku tietojarjestelmasalkku_pkey; Type: CONSTRAINT; Schema: tietok; Owner: tietokt
--

ALTER TABLE ONLY tietok.tietojarjestelmasalkku
    ADD CONSTRAINT tietojarjestelmasalkku_pkey PRIMARY KEY (tietojarjestelmatunnus);


--
-- Name: tietojarjestelmasalkkuhistoria tietojarjestelmasalkkuhistoria_pkey; Type: CONSTRAINT; Schema: tietok; Owner: tietokt
--

ALTER TABLE ONLY tietok.tietojarjestelmasalkkuhistoria
    ADD CONSTRAINT tietojarjestelmasalkkuhistoria_pkey PRIMARY KEY (rivi_id);


--
-- Name: tietojarjpalvelu_kasite_arvo tietojarjpalvelu_kasite_arvo_pkey; Type: CONSTRAINT; Schema: tietok; Owner: tietokt
--

ALTER TABLE ONLY tietok.tietojarjpalvelu_kasite_arvo
    ADD CONSTRAINT tietojarjpalvelu_kasite_arvo_pkey PRIMARY KEY (kasite_id);


--
-- Name: tietojarjpalvelu_tietolaji tietojarjpalvelu_tietolaji_pkey; Type: CONSTRAINT; Schema: tietok; Owner: tietokt
--

ALTER TABLE ONLY tietok.tietojarjpalvelu_tietolaji
    ADD CONSTRAINT tietojarjpalvelu_tietolaji_pkey PRIMARY KEY (rivitunnus);


--
-- Name: tietojarjpalveluhistoria tietojarjpalveluhistoria_pkey; Type: CONSTRAINT; Schema: tietok; Owner: tietokt
--

ALTER TABLE ONLY tietok.tietojarjpalveluhistoria
    ADD CONSTRAINT tietojarjpalveluhistoria_pkey PRIMARY KEY (rivi_id);


--
-- Name: tietojarjpalvelutietohistoria tietojarjpalvelutietohistoria_pkey; Type: CONSTRAINT; Schema: tietok; Owner: tietokt
--

ALTER TABLE ONLY tietok.tietojarjpalvelutietohistoria
    ADD CONSTRAINT tietojarjpalvelutietohistoria_pkey PRIMARY KEY (rivitunnus);


--
-- Name: tietojarjpalvliittyvajarj tietojarjpalvliittyvajarj_pkey; Type: CONSTRAINT; Schema: tietok; Owner: tietokt
--

ALTER TABLE ONLY tietok.tietojarjpalvliittyvajarj
    ADD CONSTRAINT tietojarjpalvliittyvajarj_pkey PRIMARY KEY (rivitunnus);


--
-- Name: tietolooginenhistoria tietolooginenhistoria_pkey; Type: CONSTRAINT; Schema: tietok; Owner: tietokt
--

ALTER TABLE ONLY tietok.tietolooginenhistoria
    ADD CONSTRAINT tietolooginenhistoria_pkey PRIMARY KEY (rivitunnus);


--
-- Name: tietolooginentietovaranto tietolooginentietovaranto_pkey; Type: CONSTRAINT; Schema: tietok; Owner: tietokt
--

ALTER TABLE ONLY tietok.tietolooginentietovaranto
    ADD CONSTRAINT tietolooginentietovaranto_pkey PRIMARY KEY (rivitunnus);


--
-- Name: tietoomaisuus_kasite_arvo tietoomaisuus_kasite_arvo_pkey; Type: CONSTRAINT; Schema: tietok; Owner: tietokt
--

ALTER TABLE ONLY tietok.tietoomaisuus_kasite_arvo
    ADD CONSTRAINT tietoomaisuus_kasite_arvo_pkey PRIMARY KEY (kasite_wid);


--
-- Name: tietoomaisuus tietoomaisuus_pkey; Type: CONSTRAINT; Schema: tietok; Owner: tietokt
--

ALTER TABLE ONLY tietok.tietoomaisuus
    ADD CONSTRAINT tietoomaisuus_pkey PRIMARY KEY (id);


--
-- Name: tietoomaisuushistoria tietoomaisuushistoria_pkey; Type: CONSTRAINT; Schema: tietok; Owner: tietokt
--

ALTER TABLE ONLY tietok.tietoomaisuushistoria
    ADD CONSTRAINT tietoomaisuushistoria_pkey PRIMARY KEY (rivi_id);


--
-- Name: tietoryhma tietoryhma_pkey; Type: CONSTRAINT; Schema: tietok; Owner: tietokt
--

ALTER TABLE ONLY tietok.tietoryhma
    ADD CONSTRAINT tietoryhma_pkey PRIMARY KEY (paatietoryhmatunnus, tietoryhmatunnus);


--
-- Name: tietoryhmahistoria tietoryhmahistoria_pkey; Type: CONSTRAINT; Schema: tietok; Owner: tietokt
--

ALTER TABLE ONLY tietok.tietoryhmahistoria
    ADD CONSTRAINT tietoryhmahistoria_pkey PRIMARY KEY (rivi_id);


--
-- Name: tietoryhmalooginentietovaranto tietoryhmalooginentietovaranto_pkey; Type: CONSTRAINT; Schema: tietok; Owner: tietokt
--

ALTER TABLE ONLY tietok.tietoryhmalooginentietovaranto
    ADD CONSTRAINT tietoryhmalooginentietovaranto_pkey PRIMARY KEY (rivitunnus);


--
-- Name: tietoryhmapaatietohistoria tietoryhmapaatietohistoria_pkey; Type: CONSTRAINT; Schema: tietok; Owner: tietokt
--

ALTER TABLE ONLY tietok.tietoryhmapaatietohistoria
    ADD CONSTRAINT tietoryhmapaatietohistoria_pkey PRIMARY KEY (rivitunnus);


--
-- Name: tietoryhmapaatietoryhma tietoryhmapaatietoryhma_pkey; Type: CONSTRAINT; Schema: tietok; Owner: tietokt
--

ALTER TABLE ONLY tietok.tietoryhmapaatietoryhma
    ADD CONSTRAINT tietoryhmapaatietoryhma_pkey PRIMARY KEY (rivitunnus);


--
-- Name: tietoryhmatietovaranto tietoryhmatietovaranto_pkey; Type: CONSTRAINT; Schema: tietok; Owner: tietokt
--

ALTER TABLE ONLY tietok.tietoryhmatietovaranto
    ADD CONSTRAINT tietoryhmatietovaranto_pkey PRIMARY KEY (rivitunnus);


--
-- Name: tietoryhmatietovarantohistoria tietoryhmatietovarantohistoria_pkey; Type: CONSTRAINT; Schema: tietok; Owner: tietokt
--

ALTER TABLE ONLY tietok.tietoryhmatietovarantohistoria
    ADD CONSTRAINT tietoryhmatietovarantohistoria_pkey PRIMARY KEY (rivi_id);


--
-- Name: tietosuojavastaava tietosuojavastaava_pkey; Type: CONSTRAINT; Schema: tietok; Owner: tietokt
--

ALTER TABLE ONLY tietok.tietosuojavastaava
    ADD CONSTRAINT tietosuojavastaava_pkey PRIMARY KEY (tietosuojavastaavatunnus);


--
-- Name: tietosuojavastaavahistoria tietosuojavastaavahistoria_pkey; Type: CONSTRAINT; Schema: tietok; Owner: tietokt
--

ALTER TABLE ONLY tietok.tietosuojavastaavahistoria
    ADD CONSTRAINT tietosuojavastaavahistoria_pkey PRIMARY KEY (rivi_id);


--
-- Name: tietotietoryhma tietotietoryhma_pkey; Type: CONSTRAINT; Schema: tietok; Owner: tietokt
--

ALTER TABLE ONLY tietok.tietotietoryhma
    ADD CONSTRAINT tietotietoryhma_pkey PRIMARY KEY (rivitunnus);


--
-- Name: tietotietoryhmahistoria tietotietoryhmahistoria_pkey; Type: CONSTRAINT; Schema: tietok; Owner: tietokt
--

ALTER TABLE ONLY tietok.tietotietoryhmahistoria
    ADD CONSTRAINT tietotietoryhmahistoria_pkey PRIMARY KEY (rivitunnus);


--
-- Name: tietovaranto_kasite_arvo tietovaranto_kasite_arvo_pkey; Type: CONSTRAINT; Schema: tietok; Owner: tietokt
--

ALTER TABLE ONLY tietok.tietovaranto_kasite_arvo
    ADD CONSTRAINT tietovaranto_kasite_arvo_pkey PRIMARY KEY (kasite_wid);


--
-- Name: tietovaranto tietovaranto_pkey; Type: CONSTRAINT; Schema: tietok; Owner: tietokt
--

ALTER TABLE ONLY tietok.tietovaranto
    ADD CONSTRAINT tietovaranto_pkey PRIMARY KEY (tietovarantotunnus);


--
-- Name: tietovarantohenkilotietoryhma tietovarantohenkilotietoryhma_pkey; Type: CONSTRAINT; Schema: tietok; Owner: tietokt
--

ALTER TABLE ONLY tietok.tietovarantohenkilotietoryhma
    ADD CONSTRAINT tietovarantohenkilotietoryhma_pkey PRIMARY KEY (rivitunnus);


--
-- Name: tietovarantohistoria tietovarantohistoria_pkey; Type: CONSTRAINT; Schema: tietok; Owner: tietokt
--

ALTER TABLE ONLY tietok.tietovarantohistoria
    ADD CONSTRAINT tietovarantohistoria_pkey PRIMARY KEY (rivi_id);


--
-- Name: tietovarantokasittelynperuste tietovarantokasittelynperuste_pkey; Type: CONSTRAINT; Schema: tietok; Owner: tietokt
--

ALTER TABLE ONLY tietok.tietovarantokasittelynperuste
    ADD CONSTRAINT tietovarantokasittelynperuste_pkey PRIMARY KEY (rivitunnus);


--
-- Name: tietovarantorekisteroityryhma tietovarantorekisteroityryhma_pkey; Type: CONSTRAINT; Schema: tietok; Owner: tietokt
--

ALTER TABLE ONLY tietok.tietovarantorekisteroityryhma
    ADD CONSTRAINT tietovarantorekisteroityryhma_pkey PRIMARY KEY (rivitunnus);


--
-- Name: tietovarantotiedonohjaus tietovarantotiedonohjaus_pkey; Type: CONSTRAINT; Schema: tietok; Owner: tietokt
--

ALTER TABLE ONLY tietok.tietovarantotiedonohjaus
    ADD CONSTRAINT tietovarantotiedonohjaus_pkey PRIMARY KEY (rivitunnus);


--
-- Name: tietovarantoturvatoimenpide tietovarantoturvatoimenpide_pkey; Type: CONSTRAINT; Schema: tietok; Owner: tietokt
--

ALTER TABLE ONLY tietok.tietovarantoturvatoimenpide
    ADD CONSTRAINT tietovarantoturvatoimenpide_pkey PRIMARY KEY (rivitunnus);


--
-- Name: tietovarantovastaanottajaryhma tietovarantovastaanottajaryhma_pkey; Type: CONSTRAINT; Schema: tietok; Owner: tietokt
--

ALTER TABLE ONLY tietok.tietovarantovastaanottajaryhma
    ADD CONSTRAINT tietovarantovastaanottajaryhma_pkey PRIMARY KEY (rivitunnus);


--
-- Name: tietovarantoyhteisrekistpitaja tietovarantoyhteisrekistpitaja_pkey; Type: CONSTRAINT; Schema: tietok; Owner: tietokt
--

ALTER TABLE ONLY tietok.tietovarantoyhteisrekistpitaja
    ADD CONSTRAINT tietovarantoyhteisrekistpitaja_pkey PRIMARY KEY (rivitunnus);


--
-- Name: tietovarantoyllapitomuutaho tietovarantoyllapitomuutaho_pkey; Type: CONSTRAINT; Schema: tietok; Owner: tietokt
--

ALTER TABLE ONLY tietok.tietovarantoyllapitomuutaho
    ADD CONSTRAINT tietovarantoyllapitomuutaho_pkey PRIMARY KEY (rivitunnus);


--
-- Name: toimintaprosessi_kasite_arvo toimintaprosessi_kasite_arvo_pkey; Type: CONSTRAINT; Schema: tietok; Owner: tietokt
--

ALTER TABLE ONLY tietok.toimintaprosessi_kasite_arvo
    ADD CONSTRAINT toimintaprosessi_kasite_arvo_pkey PRIMARY KEY (kasite_wid);


--
-- Name: toimintaprosessi toimintaprosessi_pkey; Type: CONSTRAINT; Schema: tietok; Owner: tietokt
--

ALTER TABLE ONLY tietok.toimintaprosessi
    ADD CONSTRAINT toimintaprosessi_pkey PRIMARY KEY (toimintaprosessitunnus);


--
-- Name: toimintaprosessihistoria toimintaprosessihistoria_pkey; Type: CONSTRAINT; Schema: tietok; Owner: tietokt
--

ALTER TABLE ONLY tietok.toimintaprosessihistoria
    ADD CONSTRAINT toimintaprosessihistoria_pkey PRIMARY KEY (rivi_id);


--
-- Name: toimintaprosessitietovaranto toimintaprosessitietovaranto_pkey; Type: CONSTRAINT; Schema: tietok; Owner: tietokt
--

ALTER TABLE ONLY tietok.toimintaprosessitietovaranto
    ADD CONSTRAINT toimintaprosessitietovaranto_pkey PRIMARY KEY (rivitunnus);


--
-- Name: toimintaprostietovarahistoria toimintaprostietovarahistoria_pkey; Type: CONSTRAINT; Schema: tietok; Owner: tietokt
--

ALTER TABLE ONLY tietok.toimintaprostietovarahistoria
    ADD CONSTRAINT toimintaprostietovarahistoria_pkey PRIMARY KEY (rivi_id);


--
-- Name: yleissuunnitelma yleissuunnitelma_pkey; Type: CONSTRAINT; Schema: tietok; Owner: tietokt
--

ALTER TABLE ONLY tietok.yleissuunnitelma
    ADD CONSTRAINT yleissuunnitelma_pkey PRIMARY KEY (yleissuunnitelmatunnus);


--
-- Name: yleissuunnitelmahistoria yleissuunnitelmahistoria_pkey; Type: CONSTRAINT; Schema: tietok; Owner: tietokt
--

ALTER TABLE ONLY tietok.yleissuunnitelmahistoria
    ADD CONSTRAINT yleissuunnitelmahistoria_pkey PRIMARY KEY (rivi_id);


--
-- Name: sovellus_temp_SOVELLUS_TEMP_UNIQUE; Type: INDEX; Schema: tietok; Owner: tietokt
--

CREATE UNIQUE INDEX "sovellus_temp_SOVELLUS_TEMP_UNIQUE" ON tietok.sovellus_temp USING btree (nimi, tuotekoodi, versio);


--
-- PostgreSQL database dump complete
--

