-- This file has been edited to work with h2 database (postgresql mode)

--
-- PostgreSQL database dump
--

-- Dumped from database version 14.1 (Debian 14.1-1.pgdg110+1)
-- Dumped by pg_dump version 14.1 (Debian 14.1-1.pgdg110+1)

DROP ALL OBJECTS;

--
---- Name: fyysinentietovaranto; Type: TABLE; Schema: tietok; Owner: tietokt(.*)
--

CREATE TABLE fyysinentietovaranto (
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


--ALTER TABLE fyysinentietovaranto OWNER TO tietokt(.*);

--
-- Name: looginentietovaranto; Type: TABLE; Schema: tietok; Owner: tietokt
--

CREATE TABLE looginentietovaranto (
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


--ALTER TABLE looginentietovaranto OWNER TO tietokt(.*);

--
-- Name: looginentietovarantofyysinenti; Type: TABLE; Schema: tietok; Owner: tietokt
--

CREATE TABLE looginentietovarantofyysinenti (
    rivitunnus numeric(38,10) NOT NULL,
    fyysinentietovarantotunnus numeric(38,10) NOT NULL,
    looginentietovarantotunnus numeric(38,10) NOT NULL,
    rivitila character varying(15) NOT NULL
);


--ALTER TABLE looginentietovarantofyysinenti OWNER TO tietokt(.*);

--
-- Name: paatietoryhma; Type: TABLE; Schema: tietok; Owner: tietokt
--

CREATE TABLE paatietoryhma (
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


--ALTER TABLE paatietoryhma OWNER TO tietokt(.*);

--
-- Name: tietoryhma; Type: TABLE; Schema: tietok; Owner: tietokt
--

CREATE TABLE tietoryhma (
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


--ALTER TABLE tietoryhma OWNER TO tietokt(.*);

--
-- Name: tietoryhmalooginentietovaranto; Type: TABLE; Schema: tietok; Owner: tietokt
--

CREATE TABLE tietoryhmalooginentietovaranto (
    rivitunnus numeric(38,10) NOT NULL,
    paatietoryhmatunnus numeric(38,10),
    tietoryhmatunnus numeric(38,10),
    looginentietovarantotunnus numeric(38,10),
    rivitila character varying(15)
);


--ALTER TABLE tietoryhmalooginentietovaranto OWNER TO tietokt(.*);

--
-- Name: apu_hierarkia; Type: VIEW; Schema: tietok; Owner: tietokt
--

CREATE VIEW apu_hierarkia AS
 SELECT ft.nimi AS ft_nimi,
    lt.nimi AS lt_nimi,
    tr.nimi AS tr_nimi,
    ptr.nimi AS ptr_nimi
   FROM (((((looginentietovarantofyysinenti t
     JOIN looginentietovaranto lt ON ((t.looginentietovarantotunnus = lt.looginentietovarantotunnus)))
     JOIN fyysinentietovaranto ft ON ((t.fyysinentietovarantotunnus = ft.fyysinentietovarantotunnus)))
     LEFT JOIN tietoryhmalooginentietovaranto trl ON ((lt.looginentietovarantotunnus = trl.looginentietovarantotunnus)))
     LEFT JOIN tietoryhma tr ON ((trl.tietoryhmatunnus = tr.tietoryhmatunnus)))
     LEFT JOIN paatietoryhma ptr ON ((trl.paatietoryhmatunnus = ptr.paatietoryhmatunnus)));


--ALTER TABLE apu_hierarkia OWNER TO tietokt(.*);

--
-- Name: apu_hierarkia_2; Type: VIEW; Schema: tietok; Owner: tietokt
--

CREATE VIEW apu_hierarkia_2 AS
 SELECT ft.nimi AS liittyy_1,
    ft.fyysinentietovarantotunnus AS tunnus_1,
    'fyysinentietovaranto'::text AS varanto_1,
    lt.nimi AS liittyy_2,
    lt.looginentietovarantotunnus AS tunnus_2,
    'looginentietovaranto'::text AS varanto_2
   FROM ((looginentietovarantofyysinenti t
     JOIN looginentietovaranto lt ON ((t.looginentietovarantotunnus = lt.looginentietovarantotunnus)))
     JOIN fyysinentietovaranto ft ON ((t.fyysinentietovarantotunnus = ft.fyysinentietovarantotunnus)))
UNION
 SELECT tr.nimi AS liittyy_1,
    tr.tietoryhmatunnus AS tunnus_1,
    'tietoryhma'::text AS varanto_1,
    ptr.nimi AS liittyy_2,
    ptr.paatietoryhmatunnus AS tunnus_2,
    'paatietoryhma'::text AS varanto_2
   FROM ((tietoryhmalooginentietovaranto trl
     JOIN tietoryhma tr ON ((trl.tietoryhmatunnus = tr.tietoryhmatunnus)))
     JOIN paatietoryhma ptr ON ((trl.paatietoryhmatunnus = ptr.paatietoryhmatunnus)))
UNION
 SELECT tr.nimi AS liittyy_1,
    tr.tietoryhmatunnus AS tunnus_1,
    'tietoryhma'::text AS varanto_1,
    lt.nimi AS liittyy_2,
    lt.looginentietovarantotunnus AS tunnus_2,
    'looginentietovaranto'::text AS varanto_2
   FROM ((tietoryhmalooginentietovaranto trl
     JOIN tietoryhma tr ON ((trl.tietoryhmatunnus = tr.tietoryhmatunnus)))
     JOIN looginentietovaranto lt ON ((trl.looginentietovarantotunnus = lt.looginentietovarantotunnus)));


--ALTER TABLE apu_hierarkia_2 OWNER TO tietokt(.*);

--
-- Name: tietojarjestelmasalkku; Type: TABLE; Schema: tietok; Owner: tietokt
--

CREATE TABLE tietojarjestelmasalkku (
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


--ALTER TABLE tietojarjestelmasalkku OWNER TO tietokt(.*);

--
-- Name: tietovirrat; Type: TABLE; Schema: tietok; Owner: tietokt
--

CREATE TABLE tietovirrat (
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


--ALTER TABLE tietovirrat OWNER TO tietokt(.*);

--
-- Name: apu_tietovirrat; Type: VIEW; Schema: tietok; Owner: tietokt
--

CREATE VIEW apu_tietovirrat AS
 SELECT j.tietojarjestelmatunnus AS s_id,
    j.jarjestelman_nimi AS source,
    tj.tietojarjestelmatunnus AS t_id,
    tj.jarjestelman_nimi AS target,
    t.linkki,
    t.tietosisalto,
    j.jarjestelman_nimi,
    t.tietovirta_id
   FROM ((tietovirrat t
     JOIN tietojarjestelmasalkku j ON ((t.s_jarjestelma = j.tietojarjestelmatunnus)))
     JOIN tietojarjestelmasalkku tj ON ((t.t_jarjestelma = tj.tietojarjestelmatunnus)))
  GROUP BY j.tietojarjestelmatunnus, j.jarjestelman_nimi, tj.tietojarjestelmatunnus, tj.jarjestelman_nimi, t.linkki, t.tietosisalto, t.tietovirta_id;


--ALTER TABLE apu_tietovirrat OWNER TO tietokt(.*);

--
-- Name: apu_hierarkia_3; Type: VIEW; Schema: tietok; Owner: tietokt
--

CREATE VIEW apu_hierarkia_3 AS
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
   FROM ((looginentietovarantofyysinenti t
     JOIN looginentietovaranto lt ON ((t.looginentietovarantotunnus = lt.looginentietovarantotunnus)))
     JOIN fyysinentietovaranto ft ON ((t.fyysinentietovarantotunnus = ft.fyysinentietovarantotunnus)))
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
   FROM ((tietoryhmalooginentietovaranto trl
     JOIN tietoryhma tr ON ((trl.tietoryhmatunnus = tr.tietoryhmatunnus)))
     JOIN paatietoryhma ptr ON ((trl.paatietoryhmatunnus = ptr.paatietoryhmatunnus)))
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
   FROM ((tietoryhmalooginentietovaranto trl
     JOIN tietoryhma tr ON ((trl.tietoryhmatunnus = tr.tietoryhmatunnus)))
     JOIN looginentietovaranto lt ON ((trl.looginentietovarantotunnus = lt.looginentietovarantotunnus)))
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
   FROM apu_tietovirrat ap;


--ALTER TABLE apu_hierarkia_3 OWNER TO tietokt(.*);

--
-- Name: sovellus; Type: TABLE; Schema: tietok; Owner: tietokt
--

CREATE TABLE sovellus (
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
    poistunut numeric(38,10) NOT NULL,
    tuotekoodi character varying(255),
    nimi character varying(255) NOT NULL
);


--ALTER TABLE sovellus OWNER TO tietokt(.*);

--
-- Name: tjs_sov; Type: VIEW; Schema: tietok; Owner: tietokt
--

CREATE VIEW tjs_sov AS
 SELECT t.tietojarjestelmatunnus AS id,
    t.jarjestelman_nimi AS nimi,
    'tietojarjestelmasalkku'::text AS taulu
   FROM tietojarjestelmasalkku t
UNION
 SELECT (s.tunnus + (1000000)::numeric) AS id,
    (((s.nimi)::text || ' '::text) || (s.versio)::text) AS nimi,
    'sovellus'::text AS taulu
   FROM sovellus s;


--ALTER TABLE tjs_sov OWNER TO tietokt(.*);

--
-- Name: apu_tietovirrat_2; Type: VIEW; Schema: tietok; Owner: tietokt
--

CREATE VIEW apu_tietovirrat_2 AS
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
   FROM ((tietovirrat t
     JOIN tjs_sov j ON ((t.s_jarjestelma = j.id)))
     JOIN tjs_sov tj ON ((t.t_jarjestelma = tj.id)))
  GROUP BY j.id, j.nimi, j.taulu, tj.id, tj.nimi, tj.taulu, t.linkki, t.tietosisalto, t.tietovirta_id;


--ALTER TABLE apu_tietovirrat_2 OWNER TO tietokt(.*);

--
-- Name: tieto; Type: TABLE; Schema: tietok; Owner: tietokt
--

CREATE TABLE tieto (
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


--ALTER TABLE tieto OWNER TO tietokt(.*);

--
-- Name: apu_hierarkia_3_2; Type: VIEW; Schema: tietok; Owner: tietokt
--

CREATE VIEW apu_hierarkia_3_2 AS
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
   FROM ((looginentietovarantofyysinenti t
     JOIN looginentietovaranto lt ON ((t.looginentietovarantotunnus = lt.looginentietovarantotunnus)))
     JOIN fyysinentietovaranto ft ON ((t.fyysinentietovarantotunnus = ft.fyysinentietovarantotunnus)))
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
   FROM ((tietoryhma trl
     JOIN tietoryhma tr ON ((trl.tietoryhmatunnus = tr.tietoryhmatunnus)))
     JOIN paatietoryhma ptr ON ((trl.paatietoryhmatunnus = ptr.paatietoryhmatunnus)))
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
   FROM ((tietoryhmalooginentietovaranto trl
     JOIN tietoryhma tr ON ((trl.tietoryhmatunnus = tr.tietoryhmatunnus)))
     JOIN looginentietovaranto lt ON ((trl.looginentietovarantotunnus = lt.looginentietovarantotunnus)))
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
   FROM ((tieto trl
     JOIN tietoryhma tr ON ((trl.tietoryhmatunnus = tr.tietoryhmatunnus)))
     JOIN looginentietovaranto lt ON ((trl.looginentietovarantotunnus = lt.looginentietovarantotunnus)))
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
   FROM (tieto trl
     JOIN looginentietovaranto lt ON ((trl.looginentietovarantotunnus = lt.looginentietovarantotunnus)))
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
   FROM apu_tietovirrat_2 ap;


--ALTER TABLE apu_hierarkia_3_2 OWNER TO tietokt(.*);

--
-- Name: apu_looginentietovaranto; Type: VIEW; Schema: tietok; Owner: tietokt
--

CREATE VIEW apu_looginentietovaranto AS
 SELECT lt.nimi AS lt_nimi,
    ft.nimi AS ft_nimi
   FROM ((looginentietovarantofyysinenti t
     JOIN looginentietovaranto lt ON ((t.looginentietovarantotunnus = lt.looginentietovarantotunnus)))
     JOIN fyysinentietovaranto ft ON ((t.fyysinentietovarantotunnus = ft.fyysinentietovarantotunnus)));


--ALTER TABLE apu_looginentietovaranto OWNER TO tietokt(.*);

--
-- Name: apu_tietoryhma; Type: VIEW; Schema: tietok; Owner: tietokt
--

CREATE VIEW apu_tietoryhma AS
 SELECT p.nimi AS ptr_nimi,
    lv.nimi AS ltv_nimi,
    s.nimi AS tietoryhma_nimi
   FROM (((tietoryhmalooginentietovaranto t
     JOIN tietoryhma s ON ((s.tietoryhmatunnus = t.tietoryhmatunnus)))
     JOIN looginentietovaranto lv ON ((lv.looginentietovarantotunnus = t.looginentietovarantotunnus)))
     JOIN paatietoryhma p ON ((p.paatietoryhmatunnus = t.paatietoryhmatunnus)));


--ALTER TABLE apu_tietoryhma OWNER TO tietokt(.*);

--
-- Name: dbmaintain_scripts; Type: TABLE; Schema: tietok; Owner: tietokt
--

-- Removed so that this file can be run with dbmaintain -Alvar

--CREATE TABLE dbmaintain_scripts (
--    file_name character varying(150),
--    file_last_modified_at numeric(38,0),
--    checksum character varying(50),
--    executed_at character varying(20),
--    succeeded numeric(38,0)
--);


----ALTER TABLE dbmaintain_scripts OWNER TO tietokt(.*);

--
-- Name: depr_jt_nak_bkp; Type: TABLE; Schema: tietok; Owner: tietokt
--

CREATE TABLE depr_jt_nak_bkp (
    id numeric(38,10),
    sarakkeen_nimi character varying(255),
    riviluotupvm timestamp without time zone,
    rivimuokattupvm timestamp without time zone,
    rivimuokkaajatunnus character varying(255),
    document_id character varying(255),
    nimi character varying(255),
    jarjestys numeric(38,10)
);


--ALTER TABLE depr_jt_nak_bkp OWNER TO tietokt(.*);

--
-- Name: depr_jt_nakyvyys; Type: TABLE; Schema: tietok; Owner: tietokt
--

CREATE TABLE depr_jt_nakyvyys (
    id numeric(38,10) NOT NULL,
    sarakkeen_nimi character varying(255),
    riviluotupvm timestamp without time zone,
    rivimuokattupvm timestamp without time zone,
    rivimuokkaajatunnus character varying(255),
    document_id character varying(255),
    nimi character varying(255),
    jarjestys numeric(38,10)
);


--ALTER TABLE depr_jt_nakyvyys OWNER TO tietokt(.*);

--
-- Name: depr_looginentietovarantotieto; Type: TABLE; Schema: tietok; Owner: tietokt
--

CREATE TABLE depr_looginentietovarantotieto (
    rivitunnus numeric(38,10) NOT NULL,
    paatietoryhmatunnus numeric(38,10) NOT NULL,
    looginentietovarantotunnus numeric(38,10) NOT NULL,
    tietoryhmatunnus numeric(38,10) NOT NULL,
    tietotunnus numeric(38,10) NOT NULL,
    rivitila character varying(15) NOT NULL
);


--ALTER TABLE depr_looginentietovarantotieto OWNER TO tietokt(.*);

--
-- Name: depr_looginentvtoimitus; Type: TABLE; Schema: tietok; Owner: tietokt
--

CREATE TABLE depr_looginentvtoimitus (
    rivitunnus numeric(38,10) NOT NULL,
    looginentietovarantotunnus numeric(38,10) NOT NULL,
    toimitustapa character varying(150) NOT NULL,
    rivitila character varying(15) NOT NULL
);


--ALTER TABLE depr_looginentvtoimitus OWNER TO tietokt(.*);

--
-- Name: depr_paatietoryhmalahde; Type: TABLE; Schema: tietok; Owner: tietokt
--

CREATE TABLE depr_paatietoryhmalahde (
    rivitunnus numeric(38,10) NOT NULL,
    paatietoryhmatunnus numeric(38,10) NOT NULL,
    tietolahde character varying(150) NOT NULL,
    rivitila character varying(15) NOT NULL
);


--ALTER TABLE depr_paatietoryhmalahde OWNER TO tietokt(.*);

--
-- Name: depr_paatietoryhmasynonyymi; Type: TABLE; Schema: tietok; Owner: tietokt
--

CREATE TABLE depr_paatietoryhmasynonyymi (
    rivitunnus numeric(38,10) NOT NULL,
    paatietoryhmatunnus numeric(38,10) NOT NULL,
    sana character varying(255) NOT NULL,
    rivitila character varying(15) NOT NULL
);


--ALTER TABLE depr_paatietoryhmasynonyymi OWNER TO tietokt(.*);

--
-- Name: depr_par_bkp; Type: TABLE; Schema: tietok; Owner: tietokt
--

CREATE TABLE depr_par_bkp (
    avain character varying(240),
    arvo character varying(240),
    riviluotupvm timestamp without time zone,
    rivimuokattupvm timestamp without time zone,
    rivimuokkaajatunnus character varying(240),
    kuvaus character varying(240),
    otsikko character varying(240)
);


--ALTER TABLE depr_par_bkp OWNER TO tietokt(.*);

--
-- Name: depr_parametrit; Type: TABLE; Schema: tietok; Owner: tietokt
--

CREATE TABLE depr_parametrit (
    avain character varying(240) NOT NULL,
    arvo character varying(240),
    riviluotupvm timestamp without time zone,
    rivimuokattupvm timestamp without time zone,
    rivimuokkaajatunnus character varying(240),
    kuvaus character varying(240),
    otsikko character varying(240)
);


--ALTER TABLE depr_parametrit OWNER TO tietokt(.*);

--
-- Name: depr_rekisterijulkaisu; Type: TABLE; Schema: tietok; Owner: tietokt
--

CREATE TABLE depr_rekisterijulkaisu (
    julkaisutieto_rivimuokkaajatun character varying(150) NOT NULL,
    rivimuokkaajatunnus character varying(150) NOT NULL,
    rivitunnus numeric(38,10) NOT NULL,
    sahkoposti character varying(50),
    rivitila character varying(15) NOT NULL,
    riviluotupvm timestamp without time zone NOT NULL,
    rivimuokattupvm timestamp without time zone NOT NULL
);


--ALTER TABLE depr_rekisterijulkaisu OWNER TO tietokt(.*);

--
-- Name: depr_rekisterikasitemalli; Type: TABLE; Schema: tietok; Owner: tietokt
--

CREATE TABLE depr_rekisterikasitemalli (
    kasitemalli character varying(150) NOT NULL,
    kuvaus character varying(4000) NOT NULL,
    rivitunnus numeric(38,10) NOT NULL,
    rivitila character varying(15) NOT NULL,
    riviluotupvm timestamp without time zone NOT NULL,
    rivimuokkaajatunnus character varying(150) NOT NULL,
    rivimuokattupvm timestamp without time zone NOT NULL
);


--ALTER TABLE depr_rekisterikasitemalli OWNER TO tietokt(.*);

--
-- Name: depr_rekisterikayttotiheys; Type: TABLE; Schema: tietok; Owner: tietokt
--

CREATE TABLE depr_rekisterikayttotiheys (
    kayttotiheys character varying(50) NOT NULL,
    kuvaus character varying(4000) NOT NULL,
    rivitunnus numeric(38,10) NOT NULL,
    rivitila character varying(15) NOT NULL,
    riviluotupvm timestamp without time zone NOT NULL,
    rivimuokkaajatunnus character varying(150) NOT NULL,
    rivimuokattupvm timestamp without time zone NOT NULL
);


--ALTER TABLE depr_rekisterikayttotiheys OWNER TO tietokt(.*);

--
-- Name: depr_rekisterilinkki; Type: TABLE; Schema: tietok; Owner: tietokt
--

CREATE TABLE depr_rekisterilinkki (
    linkki character varying(50) NOT NULL,
    kuvaus character varying(4000) NOT NULL,
    rivitunnus numeric(38,10) NOT NULL,
    rivitila character varying(15) NOT NULL,
    riviluotupvm timestamp without time zone NOT NULL,
    rivimuokkaajatunnus character varying(150) NOT NULL,
    rivimuokattupvm timestamp without time zone NOT NULL
);


--ALTER TABLE depr_rekisterilinkki OWNER TO tietokt(.*);

--
-- Name: depr_rekisterimuokkaaja; Type: TABLE; Schema: tietok; Owner: tietokt
--

CREATE TABLE depr_rekisterimuokkaaja (
    rivimuokkaajatunnus character varying(150) NOT NULL,
    rivitunnus numeric(38,10) NOT NULL,
    sahkoposti character varying(50),
    rivitila character varying(15) NOT NULL,
    riviluotupvm timestamp without time zone NOT NULL,
    rivimuokattupvm timestamp without time zone NOT NULL,
    rivimuokkaajatunnus_1 character varying(150) NOT NULL
);


--ALTER TABLE depr_rekisterimuokkaaja OWNER TO tietokt(.*);

--
-- Name: depr_rekisteripaivitystiheys; Type: TABLE; Schema: tietok; Owner: tietokt
--

CREATE TABLE depr_rekisteripaivitystiheys (
    paivitystiheys character varying(50) NOT NULL,
    kuvaus character varying(4000) NOT NULL,
    rivitunnus numeric(38,10) NOT NULL,
    rivitila character varying(15) NOT NULL,
    riviluotupvm timestamp without time zone NOT NULL,
    rivimuokkaajatunnus character varying(150) NOT NULL,
    rivimuokattupvm timestamp without time zone NOT NULL
);


--ALTER TABLE depr_rekisteripaivitystiheys OWNER TO tietokt(.*);

--
-- Name: depr_rekisteripalvelutaso; Type: TABLE; Schema: tietok; Owner: tietokt
--

CREATE TABLE depr_rekisteripalvelutaso (
    palvelutaso character varying(50) NOT NULL,
    kuvaus character varying(4000) NOT NULL,
    rivitunnus numeric(38,10) NOT NULL,
    rivitila character varying(15) NOT NULL,
    riviluotupvm timestamp without time zone NOT NULL,
    rivimuokkaajatunnus character varying(150) NOT NULL,
    rivimuokattupvm timestamp without time zone NOT NULL
);


--ALTER TABLE depr_rekisteripalvelutaso OWNER TO tietokt(.*);

--
-- Name: depr_rekisterisanasto; Type: TABLE; Schema: tietok; Owner: tietokt
--

CREATE TABLE depr_rekisterisanasto (
    sana character varying(255) NOT NULL,
    kuvaus character varying(4000) NOT NULL,
    rivitila character varying(15) NOT NULL,
    rivitunnus numeric(38,10) NOT NULL,
    riviluotupvm timestamp without time zone NOT NULL,
    rivimuokattupvm timestamp without time zone NOT NULL,
    rivimuokkaajatunnus character varying(150) NOT NULL
);


--ALTER TABLE depr_rekisterisanasto OWNER TO tietokt(.*);

--
-- Name: depr_rekisteritietolahde; Type: TABLE; Schema: tietok; Owner: tietokt
--

CREATE TABLE depr_rekisteritietolahde (
    tietolahde character varying(150) NOT NULL,
    kuvaus character varying(4000),
    rivitunnus numeric(38,10) NOT NULL,
    rivitila character varying(15) NOT NULL,
    riviluotupvm timestamp without time zone NOT NULL,
    rivimuokattupvm timestamp without time zone NOT NULL,
    rivimuokkaajatunnus character varying(150) NOT NULL
);


--ALTER TABLE depr_rekisteritietolahde OWNER TO tietokt(.*);

--
-- Name: depr_rekisteritila; Type: TABLE; Schema: tietok; Owner: tietokt
--

CREATE TABLE depr_rekisteritila (
    tila character varying(50) NOT NULL,
    rivimuokkaajatunnus character varying(150) NOT NULL,
    kuvaus character varying(150) NOT NULL,
    rivitunnus numeric(38,10) NOT NULL,
    rivitila character varying(15) NOT NULL,
    riviluotupvm timestamp without time zone NOT NULL,
    rivimuokattupvm timestamp without time zone NOT NULL
);


--ALTER TABLE depr_rekisteritila OWNER TO tietokt(.*);

--
-- Name: depr_reklooginentietovarastoo; Type: TABLE; Schema: tietok; Owner: tietokt
--

CREATE TABLE depr_reklooginentietovarastoo (
    omistaja character varying(150) NOT NULL,
    rivitunnus numeric(38,10) NOT NULL,
    sahkoposti character varying(50),
    rivitila character varying(15) NOT NULL,
    riviluotupvm timestamp without time zone NOT NULL,
    rivimuokattupvm timestamp without time zone NOT NULL,
    rivimuokkaajatunnus character varying(150) NOT NULL
);


--ALTER TABLE depr_reklooginentietovarastoo OWNER TO tietokt(.*);

--
-- Name: depr_reklooginentietovarastos; Type: TABLE; Schema: tietok; Owner: tietokt
--

CREATE TABLE depr_reklooginentietovarastos (
    kayttaja character varying(150) NOT NULL,
    rivitunnus numeric(38,10) NOT NULL,
    sahkoposti character varying(50),
    rivitila character varying(15) NOT NULL,
    riviluotupvm timestamp without time zone NOT NULL,
    rivimuokattupvm timestamp without time zone NOT NULL,
    rivimuokkaajatunnus character varying(150) NOT NULL
);


--ALTER TABLE depr_reklooginentietovarastos OWNER TO tietokt(.*);

--
-- Name: depr_rektiedontoimistustapa; Type: TABLE; Schema: tietok; Owner: tietokt
--

CREATE TABLE depr_rektiedontoimistustapa (
    toimitustapa character varying(150) NOT NULL,
    kuvaus character varying(4000) NOT NULL,
    rivitunnus numeric(38,10) NOT NULL,
    rivitila character varying(15) NOT NULL,
    riviluotupvm timestamp without time zone NOT NULL,
    rivimuokattupvm timestamp without time zone NOT NULL,
    rivimuokkaajatunnus character varying(150) NOT NULL
);


--ALTER TABLE depr_rektiedontoimistustapa OWNER TO tietokt(.*);

--
-- Name: depr_rektietojarjestelmatila; Type: TABLE; Schema: tietok; Owner: tietokt
--

CREATE TABLE depr_rektietojarjestelmatila (
    tila character varying(50) NOT NULL,
    kuvaus character varying(4000) NOT NULL,
    rivitunnus numeric(38,10) NOT NULL,
    rivitila character varying(15) NOT NULL,
    riviluotupvm timestamp without time zone NOT NULL,
    rivimuokkaajatunnus character varying(150) NOT NULL,
    rivimuokattupvm timestamp without time zone NOT NULL
);


--ALTER TABLE depr_rektietojarjestelmatila OWNER TO tietokt(.*);

--
-- Name: depr_rektietokantateknologia; Type: TABLE; Schema: tietok; Owner: tietokt
--

CREATE TABLE depr_rektietokantateknologia (
    teknologia character varying(150) NOT NULL,
    kuvaus character varying(4000) NOT NULL,
    rivitunnus numeric(38,10) NOT NULL,
    rivitila character varying(15) NOT NULL,
    riviluotupvm timestamp without time zone NOT NULL,
    rivimuokkaajatunnus character varying(150) NOT NULL,
    rivimuokattupvm timestamp without time zone NOT NULL
);


--ALTER TABLE depr_rektietokantateknologia OWNER TO tietokt(.*);

--
-- Name: depr_rektietoryhmaomistaja; Type: TABLE; Schema: tietok; Owner: tietokt
--

CREATE TABLE depr_rektietoryhmaomistaja (
    omistaja character varying(150) NOT NULL,
    rivitunnus numeric(38,10) NOT NULL,
    sahkoposti character varying(50),
    rivitila character varying(15) NOT NULL,
    riviluotupvm timestamp without time zone NOT NULL,
    rivimuokattupvm timestamp without time zone NOT NULL,
    rivimuokkaajatunnus character varying(150) NOT NULL
);


--ALTER TABLE depr_rektietoryhmaomistaja OWNER TO tietokt(.*);

--
-- Name: depr_rektietosuojataso; Type: TABLE; Schema: tietok; Owner: tietokt
--

CREATE TABLE depr_rektietosuojataso (
    tietosuojataso character varying(50) NOT NULL,
    rivimuokkaajatunnus character varying(150) NOT NULL,
    rivitunnus numeric(38,10) NOT NULL,
    rivitila character varying(15) NOT NULL,
    riviluotupvm timestamp without time zone NOT NULL,
    rivimuokattupvm timestamp without time zone NOT NULL
);


--ALTER TABLE depr_rektietosuojataso OWNER TO tietokt(.*);

--
-- Name: depr_rektietovarastoomistaja; Type: TABLE; Schema: tietok; Owner: tietokt
--

CREATE TABLE depr_rektietovarastoomistaja (
    kayttaja character varying(150) NOT NULL,
    rivitunnus numeric(38,10) NOT NULL,
    sahkoposti character varying(50),
    rivitila character varying(15) NOT NULL,
    riviluotupvm timestamp without time zone NOT NULL,
    rivimuokattupvm timestamp without time zone NOT NULL,
    rivimuokkaajatunnus character varying(150) NOT NULL
);


--ALTER TABLE depr_rektietovarastoomistaja OWNER TO tietokt(.*);

--
-- Name: depr_tietojarjestelmapalvelu; Type: TABLE; Schema: tietok; Owner: tietokt
--

CREATE TABLE depr_tietojarjestelmapalvelu (
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


--ALTER TABLE depr_tietojarjestelmapalvelu OWNER TO tietokt(.*);

--
-- Name: depr_tietolahde; Type: TABLE; Schema: tietok; Owner: tietokt
--

CREATE TABLE depr_tietolahde (
    rivitunnus numeric(38,10) NOT NULL,
    tietolahde character varying(150) NOT NULL,
    paatietoryhmatunnus numeric(38,10) NOT NULL,
    tietoryhmatunnus numeric(38,10) NOT NULL,
    rivitila character varying(15) NOT NULL,
    tietotunnus numeric(38,10) NOT NULL
);


--ALTER TABLE depr_tietolahde OWNER TO tietokt(.*);

--
-- Name: depr_tietoryhmalahde; Type: TABLE; Schema: tietok; Owner: tietokt
--

CREATE TABLE depr_tietoryhmalahde (
    rivitunnus numeric(38,10) NOT NULL,
    tietolahde character varying(150) NOT NULL,
    paatietoryhmatunnus numeric(38,10) NOT NULL,
    tietoryhmatunnus numeric(38,10) NOT NULL,
    rivitila character varying(15) NOT NULL
);


--ALTER TABLE depr_tietoryhmalahde OWNER TO tietokt(.*);

--
-- Name: depr_tjsalkkufyysinen; Type: TABLE; Schema: tietok; Owner: tietokt
--

CREATE TABLE depr_tjsalkkufyysinen (
    rivitunnus numeric(38,10) NOT NULL,
    fyysinentietovarantotunnus numeric(38,10) NOT NULL,
    tietojarjestelmatunnus numeric(38,10) NOT NULL,
    rivitila character varying(15) NOT NULL
);


--ALTER TABLE depr_tjsalkkufyysinen OWNER TO tietokt(.*);

--
-- Name: depr_valintalista; Type: TABLE; Schema: tietok; Owner: tietokt
--

CREATE TABLE depr_valintalista (
    code numeric(38,10),
    name character varying(255)
);


--ALTER TABLE depr_valintalista OWNER TO tietokt(.*);

--
-- Name: fyysinentietovaranto_fyysin62; Type: SEQUENCE; Schema: tietok; Owner: tietokt
--

CREATE SEQUENCE fyysinentietovaranto_fyysin62
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--ALTER TABLE fyysinentietovaranto_fyysin62 OWNER TO tietokt(.*);

--
-- Name: fyysinentietovarantohistoria; Type: TABLE; Schema: tietok; Owner: tietokt
--

CREATE TABLE fyysinentietovarantohistoria (
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


--ALTER TABLE fyysinentietovarantohistoria OWNER TO tietokt(.*);

--
-- Name: fyysinentv_id_seq; Type: SEQUENCE; Schema: tietok; Owner: tietokt
--

CREATE SEQUENCE fyysinentv_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--ALTER TABLE fyysinentv_id_seq OWNER TO tietokt(.*);

--
-- Name: fyysinentv_seq; Type: SEQUENCE; Schema: tietok; Owner: tietokt
--

CREATE SEQUENCE fyysinentv_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--ALTER TABLE fyysinentv_seq OWNER TO tietokt(.*);

--
-- Name: fyysinentvhist_id_seq; Type: SEQUENCE; Schema: tietok; Owner: tietokt
--

CREATE SEQUENCE fyysinentvhist_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--ALTER TABLE fyysinentvhist_id_seq OWNER TO tietokt(.*);

--
-- Name: fyysinentvhist_seq; Type: SEQUENCE; Schema: tietok; Owner: tietokt
--

CREATE SEQUENCE fyysinentvhist_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--ALTER TABLE fyysinentvhist_seq OWNER TO tietokt(.*);

--
-- Name: henkilo; Type: TABLE; Schema: tietok; Owner: tietokt
--

CREATE TABLE henkilo (
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


--ALTER TABLE henkilo OWNER TO tietokt(.*);

--
-- Name: henkilo_id_seq; Type: SEQUENCE; Schema: tietok; Owner: tietokt
--

CREATE SEQUENCE henkilo_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--ALTER TABLE henkilo_id_seq OWNER TO tietokt(.*);

--
-- Name: henkilo_seq; Type: SEQUENCE; Schema: tietok; Owner: tietokt
--

CREATE SEQUENCE henkilo_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--ALTER TABLE henkilo_seq OWNER TO tietokt(.*);

--
-- Name: henkilo_temp; Type: TABLE; Schema: tietok; Owner: tietokt
--

CREATE TABLE henkilo_temp (
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


--ALTER TABLE henkilo_temp OWNER TO tietokt(.*);

--
-- Name: henkilo_temp_id_seq; Type: SEQUENCE; Schema: tietok; Owner: tietokt
--

CREATE SEQUENCE henkilo_temp_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--ALTER TABLE henkilo_temp_id_seq OWNER TO tietokt(.*);

--
-- Name: henkilo_temp_seq; Type: SEQUENCE; Schema: tietok; Owner: tietokt
--

CREATE SEQUENCE henkilo_temp_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--ALTER TABLE henkilo_temp_seq OWNER TO tietokt(.*);

--
-- Name: jarjestelma_henkilo_rooli; Type: TABLE; Schema: tietok; Owner: tietokt
--

CREATE TABLE jarjestelma_henkilo_rooli (
    henkilo_id numeric(38,10) NOT NULL,
    rooli_id numeric(38,10) NOT NULL,
    jarjestelma_id numeric(38,10) NOT NULL,
    rivitunnus numeric(38,10) NOT NULL
);


--ALTER TABLE jarjestelma_henkilo_rooli OWNER TO tietokt(.*);

--
-- Name: jarjestelma_henkilo_rooli_hist; Type: TABLE; Schema: tietok; Owner: tietokt
--

CREATE TABLE jarjestelma_henkilo_rooli_hist (
    rivitunnus numeric(38,10) NOT NULL,
    henkilo_id numeric(38,10) NOT NULL,
    rooli_id numeric(38,10) NOT NULL,
    jarjestelma_id numeric(38,10) NOT NULL,
    historiatyyppi character varying(6) NOT NULL,
    riviluotupvm timestamp without time zone,
    rivimuokkaajatunnus character varying(150)
);


--ALTER TABLE jarjestelma_henkilo_rooli_hist OWNER TO tietokt(.*);

--
-- Name: jarjestelma_id_seq; Type: SEQUENCE; Schema: tietok; Owner: tietokt
--

CREATE SEQUENCE jarjestelma_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--ALTER TABLE jarjestelma_id_seq OWNER TO tietokt(.*);

--
-- Name: jarjestelma_seq; Type: SEQUENCE; Schema: tietok; Owner: tietokt
--

CREATE SEQUENCE jarjestelma_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--ALTER TABLE jarjestelma_seq OWNER TO tietokt(.*);

--
-- Name: jarjestelmaalue; Type: VIEW; Schema: tietok; Owner: tietokt
--

CREATE VIEW jarjestelmaalue AS
 SELECT ak.nimi,
    COALESCE(concat(upper(substr((ak.nimi)::text, 1, 1)), lower(substr((ak.nimi)::text, 2, length((ak.nimi)::text)))), 'kaikki'::text) AS n_nimi
   FROM ( SELECT DISTINCT tietojarjestelmasalkku.jarjestelmaalue AS nimi
           FROM tietojarjestelmasalkku) ak;


--ALTER TABLE jarjestelmaalue OWNER TO tietokt(.*);

--
-- Name: jarjestelmahist_id_seq; Type: SEQUENCE; Schema: tietok; Owner: tietokt
--

CREATE SEQUENCE jarjestelmahist_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--ALTER TABLE jarjestelmahist_id_seq OWNER TO tietokt(.*);

--
-- Name: jarjestelmahist_seq; Type: SEQUENCE; Schema: tietok; Owner: tietokt
--

CREATE SEQUENCE jarjestelmahist_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--ALTER TABLE jarjestelmahist_seq OWNER TO tietokt(.*);

--
-- Name: jarjestelmalinkkaus; Type: TABLE; Schema: tietok; Owner: tietokt
--

CREATE TABLE jarjestelmalinkkaus (
    rivitunnus numeric(38,10) NOT NULL,
    tietojarjestelmatunnus numeric(38,10) NOT NULL,
    linkattava_id numeric(38,10) NOT NULL,
    suunta character varying(255),
    tyyppi character varying(255),
    kuvaus character varying(255),
    rivitila character varying(15) NOT NULL,
    tietojarjestelmapalvelutunnus numeric(38,10)
);


--ALTER TABLE jarjestelmalinkkaus OWNER TO tietokt(.*);

--
-- Name: jarjestelmalinkkaushistoria; Type: TABLE; Schema: tietok; Owner: tietokt
--

CREATE TABLE jarjestelmalinkkaushistoria (
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


--ALTER TABLE jarjestelmalinkkaushistoria OWNER TO tietokt(.*);

--
-- Name: jarjestelmalooginen; Type: TABLE; Schema: tietok; Owner: tietokt
--

CREATE TABLE jarjestelmalooginen (
    rivitunnus numeric(38,10) NOT NULL,
    jarjestelmatunnus numeric(38,10),
    looginentunnus numeric(38,10)
);


--ALTER TABLE jarjestelmalooginen OWNER TO tietokt(.*);

--
-- Name: jarjestelmalooginen_id_seq; Type: SEQUENCE; Schema: tietok; Owner: tietokt
--

CREATE SEQUENCE jarjestelmalooginen_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--ALTER TABLE jarjestelmalooginen_id_seq OWNER TO tietokt(.*);

--
-- Name: jarjestelmalooginenhist_id_seq; Type: SEQUENCE; Schema: tietok; Owner: tietokt
--

CREATE SEQUENCE jarjestelmalooginenhist_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--ALTER TABLE jarjestelmalooginenhist_id_seq OWNER TO tietokt(.*);

--
-- Name: jarjestelmalooginenhistoria; Type: TABLE; Schema: tietok; Owner: tietokt
--

CREATE TABLE jarjestelmalooginenhistoria (
    rivi_id numeric(38,10) NOT NULL,
    jarjestelmatunnus numeric(38,10) NOT NULL,
    looginentunnus character varying(6),
    riviluotupvm timestamp without time zone,
    historiatyyppi character varying(6),
    rivimuokkaajatunnus character varying(150)
);


--ALTER TABLE jarjestelmalooginenhistoria OWNER TO tietokt(.*);

--
-- Name: jarjestelmatieto_k_bkp; Type: TABLE; Schema: tietok; Owner: tietokt
--

CREATE TABLE jarjestelmatieto_k_bkp (
    nimi character varying(255),
    sarakkeen_nimi character varying(255)
);


--ALTER TABLE jarjestelmatieto_k_bkp OWNER TO tietokt(.*);

--
-- Name: jarjestelmatieto_kentat; Type: TABLE; Schema: tietok; Owner: tietokt
--

CREATE TABLE jarjestelmatieto_kentat (
    nimi character varying(255) NOT NULL,
    sarakkeen_nimi character varying(255),
    jarjestys numeric(38,10)
);


--ALTER TABLE jarjestelmatieto_kentat OWNER TO tietokt(.*);

--
-- Name: jarjestelmatyyppi; Type: VIEW; Schema: tietok; Owner: tietokt
--

CREATE VIEW jarjestelmatyyppi AS
 SELECT ak.nimi,
    COALESCE(concat(upper(substr((ak.nimi)::text, 1, 1)), lower(substr((ak.nimi)::text, 2, length((ak.nimi)::text)))), 'kaikki'::text) AS n_nimi
   FROM ( SELECT DISTINCT tietojarjestelmasalkku.jarjestelmatyyppi AS nimi
           FROM tietojarjestelmasalkku) ak;


--ALTER TABLE jarjestelmatyyppi OWNER TO tietokt(.*);

--
-- Name: jarjhenkroolihist_id_seq; Type: SEQUENCE; Schema: tietok; Owner: tietokt
--

CREATE SEQUENCE jarjhenkroolihist_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--ALTER TABLE jarjhenkroolihist_id_seq OWNER TO tietokt(.*);

--
-- Name: jarjhenkroolihist_seq; Type: SEQUENCE; Schema: tietok; Owner: tietokt
--

CREATE SEQUENCE jarjhenkroolihist_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--ALTER TABLE jarjhenkroolihist_seq OWNER TO tietokt(.*);

--
-- Name: joinhuomautus; Type: TABLE; Schema: tietok; Owner: tietokt
--

CREATE TABLE joinhuomautus (
    rivitunnus numeric(38,10) NOT NULL,
    termilomaketunnus numeric(38,10),
    huomautus character varying(255),
    rivitila character varying(6)
);


--ALTER TABLE joinhuomautus OWNER TO tietokt(.*);

--
-- Name: joinhuomautus_id_seq; Type: SEQUENCE; Schema: tietok; Owner: tietokt
--

CREATE SEQUENCE joinhuomautus_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--ALTER TABLE joinhuomautus_id_seq OWNER TO tietokt(.*);

--
-- Name: joinhuomautushist_id_seq; Type: SEQUENCE; Schema: tietok; Owner: tietokt
--

CREATE SEQUENCE joinhuomautushist_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--ALTER TABLE joinhuomautushist_id_seq OWNER TO tietokt(.*);

--
-- Name: joinhuomautushistoria; Type: TABLE; Schema: tietok; Owner: tietokt
--

CREATE TABLE joinhuomautushistoria (
    rivi_id numeric(38,10) NOT NULL,
    termilomaketunnus numeric(38,10),
    huomautus character varying(255),
    historiatyyppi character varying(6),
    riviluotupvm timestamp without time zone,
    rivimuokkaajatunnus character varying(150)
);


--ALTER TABLE joinhuomautushistoria OWNER TO tietokt(.*);

--
-- Name: joinjarjestelmalinkkaus_id_seq; Type: SEQUENCE; Schema: tietok; Owner: tietokt
--

CREATE SEQUENCE joinjarjestelmalinkkaus_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--ALTER TABLE joinjarjestelmalinkkaus_id_seq OWNER TO tietokt(.*);

--
-- Name: joinjarjestelmalinkkaus_seq; Type: SEQUENCE; Schema: tietok; Owner: tietokt
--

CREATE SEQUENCE joinjarjestelmalinkkaus_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--ALTER TABLE joinjarjestelmalinkkaus_seq OWNER TO tietokt(.*);

--
-- Name: joinjarjhenkrooli_id_seq; Type: SEQUENCE; Schema: tietok; Owner: tietokt
--

CREATE SEQUENCE joinjarjhenkrooli_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--ALTER TABLE joinjarjhenkrooli_id_seq OWNER TO tietokt(.*);

--
-- Name: joinjarjhenkrooli_seq; Type: SEQUENCE; Schema: tietok; Owner: tietokt
--

CREATE SEQUENCE joinjarjhenkrooli_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--ALTER TABLE joinjarjhenkrooli_seq OWNER TO tietokt(.*);

--
-- Name: joinjlinkhist_id_seq; Type: SEQUENCE; Schema: tietok; Owner: tietokt
--

CREATE SEQUENCE joinjlinkhist_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--ALTER TABLE joinjlinkhist_id_seq OWNER TO tietokt(.*);

--
-- Name: joinjlinkhist_seq; Type: SEQUENCE; Schema: tietok; Owner: tietokt
--

CREATE SEQUENCE joinjlinkhist_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--ALTER TABLE joinjlinkhist_seq OWNER TO tietokt(.*);

--
-- Name: joinloogfyyshistoria_id_seq; Type: SEQUENCE; Schema: tietok; Owner: tietokt
--

CREATE SEQUENCE joinloogfyyshistoria_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--ALTER TABLE joinloogfyyshistoria_id_seq OWNER TO tietokt(.*);

--
-- Name: joinloogfyyshistoria_seq; Type: SEQUENCE; Schema: tietok; Owner: tietokt
--

CREATE SEQUENCE joinloogfyyshistoria_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--ALTER TABLE joinloogfyyshistoria_seq OWNER TO tietokt(.*);

--
-- Name: joinlooginenfyysinen_id_seq; Type: SEQUENCE; Schema: tietok; Owner: tietokt
--

CREATE SEQUENCE joinlooginenfyysinen_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--ALTER TABLE joinlooginenfyysinen_id_seq OWNER TO tietokt(.*);

--
-- Name: joinlooginenfyysinen_seq; Type: SEQUENCE; Schema: tietok; Owner: tietokt
--

CREATE SEQUENCE joinlooginenfyysinen_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--ALTER TABLE joinlooginenfyysinen_seq OWNER TO tietokt(.*);

--
-- Name: joinsovellushenkrooli_id_seq; Type: SEQUENCE; Schema: tietok; Owner: tietokt
--

CREATE SEQUENCE joinsovellushenkrooli_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--ALTER TABLE joinsovellushenkrooli_id_seq OWNER TO tietokt(.*);

--
-- Name: joinsovellushenkrooli_seq; Type: SEQUENCE; Schema: tietok; Owner: tietokt
--

CREATE SEQUENCE joinsovellushenkrooli_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--ALTER TABLE joinsovellushenkrooli_seq OWNER TO tietokt(.*);

--
-- Name: jointietolajilooginen_id_seq; Type: SEQUENCE; Schema: tietok; Owner: tietokt
--

CREATE SEQUENCE jointietolajilooginen_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--ALTER TABLE jointietolajilooginen_id_seq OWNER TO tietokt(.*);

--
-- Name: jointietolajilooginen_seq; Type: SEQUENCE; Schema: tietok; Owner: tietokt
--

CREATE SEQUENCE jointietolajilooginen_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--ALTER TABLE jointietolajilooginen_seq OWNER TO tietokt(.*);

--
-- Name: jointietolajitietoryhma_id_seq; Type: SEQUENCE; Schema: tietok; Owner: tietokt
--

CREATE SEQUENCE jointietolajitietoryhma_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--ALTER TABLE jointietolajitietoryhma_id_seq OWNER TO tietokt(.*);

--
-- Name: jointietolajitietoryhma_seq; Type: SEQUENCE; Schema: tietok; Owner: tietokt
--

CREATE SEQUENCE jointietolajitietoryhma_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--ALTER TABLE jointietolajitietoryhma_seq OWNER TO tietokt(.*);

--
-- Name: jointietolooghistoria_id_seq; Type: SEQUENCE; Schema: tietok; Owner: tietokt
--

CREATE SEQUENCE jointietolooghistoria_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--ALTER TABLE jointietolooghistoria_id_seq OWNER TO tietokt(.*);

--
-- Name: jointietolooghistoria_seq; Type: SEQUENCE; Schema: tietok; Owner: tietokt
--

CREATE SEQUENCE jointietolooghistoria_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--ALTER TABLE jointietolooghistoria_seq OWNER TO tietokt(.*);

--
-- Name: jointietorpaatietor_id_seq; Type: SEQUENCE; Schema: tietok; Owner: tietokt
--

CREATE SEQUENCE jointietorpaatietor_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--ALTER TABLE jointietorpaatietor_id_seq OWNER TO tietokt(.*);

--
-- Name: jointietorpaatietor_seq; Type: SEQUENCE; Schema: tietok; Owner: tietokt
--

CREATE SEQUENCE jointietorpaatietor_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--ALTER TABLE jointietorpaatietor_seq OWNER TO tietokt(.*);

--
-- Name: jtietorpaatietorhist_id_seq; Type: SEQUENCE; Schema: tietok; Owner: tietokt
--

CREATE SEQUENCE jtietorpaatietorhist_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--ALTER TABLE jtietorpaatietorhist_id_seq OWNER TO tietokt(.*);

--
-- Name: jtietorpaatietorhist_seq; Type: SEQUENCE; Schema: tietok; Owner: tietokt
--

CREATE SEQUENCE jtietorpaatietorhist_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--ALTER TABLE jtietorpaatietorhist_seq OWNER TO tietokt(.*);

--
-- Name: jtietotietoryhmahist_id_seq; Type: SEQUENCE; Schema: tietok; Owner: tietokt
--

CREATE SEQUENCE jtietotietoryhmahist_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--ALTER TABLE jtietotietoryhmahist_id_seq OWNER TO tietokt(.*);

--
-- Name: jtietotietoryhmahist_seq; Type: SEQUENCE; Schema: tietok; Owner: tietokt
--

CREATE SEQUENCE jtietotietoryhmahist_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--ALTER TABLE jtietotietoryhmahist_seq OWNER TO tietokt(.*);

--
-- Name: looginen_kasite_arvo; Type: TABLE; Schema: tietok; Owner: tietokt
--

CREATE TABLE looginen_kasite_arvo (
    kasite_wid numeric(38,0) NOT NULL,
    kasite character varying(100),
    arvo character varying(200),
    aluekoodi character varying(6)
);


--ALTER TABLE looginen_kasite_arvo OWNER TO tietokt(.*);

--
-- Name: looginenfyysinenhistoria; Type: TABLE; Schema: tietok; Owner: tietokt
--

CREATE TABLE looginenfyysinenhistoria (
    rivitunnus numeric(38,10) NOT NULL,
    fyysinentietovarantotunnus numeric(38,10) NOT NULL,
    looginentietovarantotunnus numeric(38,10) NOT NULL,
    historiatyyppi character varying(6) NOT NULL,
    rivitila character varying(15) NOT NULL,
    riviluotupvm timestamp without time zone,
    rivimuokkaajatunnus character varying(150)
);


--ALTER TABLE looginenfyysinenhistoria OWNER TO tietokt(.*);

--
-- Name: looginentietovaranto_loogin742; Type: SEQUENCE; Schema: tietok; Owner: tietokt
--

CREATE SEQUENCE looginentietovaranto_loogin742
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--ALTER TABLE looginentietovaranto_loogin742 OWNER TO tietokt(.*);

--
-- Name: looginentietovarantofyysine427; Type: SEQUENCE; Schema: tietok; Owner: tietokt
--

CREATE SEQUENCE looginentietovarantofyysine427
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--ALTER TABLE looginentietovarantofyysine427 OWNER TO tietokt(.*);

--
-- Name: looginentietovarantohistoria; Type: TABLE; Schema: tietok; Owner: tietokt
--

CREATE TABLE looginentietovarantohistoria (
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


--ALTER TABLE looginentietovarantohistoria OWNER TO tietokt(.*);

--
-- Name: looginentv_id_seq; Type: SEQUENCE; Schema: tietok; Owner: tietokt
--

CREATE SEQUENCE looginentv_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--ALTER TABLE looginentv_id_seq OWNER TO tietokt(.*);

--
-- Name: looginentv_seq; Type: SEQUENCE; Schema: tietok; Owner: tietokt
--

CREATE SEQUENCE looginentv_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--ALTER TABLE looginentv_seq OWNER TO tietokt(.*);

--
-- Name: looginentvhist_id_seq; Type: SEQUENCE; Schema: tietok; Owner: tietokt
--

CREATE SEQUENCE looginentvhist_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--ALTER TABLE looginentvhist_id_seq OWNER TO tietokt(.*);

--
-- Name: looginentvhist_seq; Type: SEQUENCE; Schema: tietok; Owner: tietokt
--

CREATE SEQUENCE looginentvhist_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--ALTER TABLE looginentvhist_seq OWNER TO tietokt(.*);

--
-- Name: organisaatio; Type: TABLE; Schema: tietok; Owner: tietokt
--

CREATE TABLE organisaatio (
    organisaatiotunnus numeric(38,10) NOT NULL,
    nimi character varying(225),
    osoite character varying(255),
    sahkoposti character varying(255),
    puhelinnumero character varying(255),
    rivimuokkaajatunnus character varying(150),
    riviluotupvm timestamp without time zone,
    rivimuokattupvm timestamp without time zone
);


--ALTER TABLE organisaatio OWNER TO tietokt(.*);

--
-- Name: organisaatio_id_seq; Type: SEQUENCE; Schema: tietok; Owner: tietokt
--

CREATE SEQUENCE organisaatio_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--ALTER TABLE organisaatio_id_seq OWNER TO tietokt(.*);

--
-- Name: organisaatiohistoria; Type: TABLE; Schema: tietok; Owner: tietokt
--

CREATE TABLE organisaatiohistoria (
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


--ALTER TABLE organisaatiohistoria OWNER TO tietokt(.*);

--
-- Name: organisaatiohistoria_id_seq; Type: SEQUENCE; Schema: tietok; Owner: tietokt
--

CREATE SEQUENCE organisaatiohistoria_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--ALTER TABLE organisaatiohistoria_id_seq OWNER TO tietokt(.*);

--
-- Name: paatietoryhma_id_seq; Type: SEQUENCE; Schema: tietok; Owner: tietokt
--

CREATE SEQUENCE paatietoryhma_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--ALTER TABLE paatietoryhma_id_seq OWNER TO tietokt(.*);

--
-- Name: paatietoryhma_paatietoryhma612; Type: SEQUENCE; Schema: tietok; Owner: tietokt
--

CREATE SEQUENCE paatietoryhma_paatietoryhma612
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--ALTER TABLE paatietoryhma_paatietoryhma612 OWNER TO tietokt(.*);

--
-- Name: paatietoryhma_seq; Type: SEQUENCE; Schema: tietok; Owner: tietokt
--

CREATE SEQUENCE paatietoryhma_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--ALTER TABLE paatietoryhma_seq OWNER TO tietokt(.*);

--
-- Name: paatietoryhmahist_id_seq; Type: SEQUENCE; Schema: tietok; Owner: tietokt
--

CREATE SEQUENCE paatietoryhmahist_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--ALTER TABLE paatietoryhmahist_id_seq OWNER TO tietokt(.*);

--
-- Name: paatietoryhmahist_seq; Type: SEQUENCE; Schema: tietok; Owner: tietokt
--

CREATE SEQUENCE paatietoryhmahist_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--ALTER TABLE paatietoryhmahist_seq OWNER TO tietokt(.*);

--
-- Name: paatietoryhmahistoria; Type: TABLE; Schema: tietok; Owner: tietokt
--

CREATE TABLE paatietoryhmahistoria (
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


--ALTER TABLE paatietoryhmahistoria OWNER TO tietokt(.*);

--
-- Name: paatietoryhmalahde_rivitunn376; Type: SEQUENCE; Schema: tietok; Owner: tietokt
--

CREATE SEQUENCE paatietoryhmalahde_rivitunn376
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--ALTER TABLE paatietoryhmalahde_rivitunn376 OWNER TO tietokt(.*);

--
-- Name: paatietoryhmalahde_rivitunn377; Type: SEQUENCE; Schema: tietok; Owner: tietokt
--

CREATE SEQUENCE paatietoryhmalahde_rivitunn377
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--ALTER TABLE paatietoryhmalahde_rivitunn377 OWNER TO tietokt(.*);

--
-- Name: paatietoryhmalahde_rivitunn379; Type: SEQUENCE; Schema: tietok; Owner: tietokt
--

CREATE SEQUENCE paatietoryhmalahde_rivitunn379
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--ALTER TABLE paatietoryhmalahde_rivitunn379 OWNER TO tietokt(.*);

--
-- Name: paatietoryhmalahde_rivitunn381; Type: SEQUENCE; Schema: tietok; Owner: tietokt
--

CREATE SEQUENCE paatietoryhmalahde_rivitunn381
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--ALTER TABLE paatietoryhmalahde_rivitunn381 OWNER TO tietokt(.*);

--
-- Name: paatietoryhmalahde_rivitunn383; Type: SEQUENCE; Schema: tietok; Owner: tietokt
--

CREATE SEQUENCE paatietoryhmalahde_rivitunn383
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--ALTER TABLE paatietoryhmalahde_rivitunn383 OWNER TO tietokt(.*);

--
-- Name: paatietoryhmalahde_rivitunn574; Type: SEQUENCE; Schema: tietok; Owner: tietokt
--

CREATE SEQUENCE paatietoryhmalahde_rivitunn574
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--ALTER TABLE paatietoryhmalahde_rivitunn574 OWNER TO tietokt(.*);

--
-- Name: palvelu; Type: TABLE; Schema: tietok; Owner: tietokt
--

CREATE TABLE palvelu (
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


--ALTER TABLE palvelu OWNER TO tietokt(.*);

--
-- Name: palvelu_id_seq; Type: SEQUENCE; Schema: tietok; Owner: tietokt
--

CREATE SEQUENCE palvelu_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--ALTER TABLE palvelu_id_seq OWNER TO tietokt(.*);

--
-- Name: palvelu_kasite_arvo; Type: TABLE; Schema: tietok; Owner: tietokt
--

CREATE TABLE palvelu_kasite_arvo (
    kasite_id numeric(38,0) NOT NULL,
    kasite character varying(100),
    arvo character varying(200)
);


--ALTER TABLE palvelu_kasite_arvo OWNER TO tietokt(.*);

--
-- Name: palvelu_seq; Type: SEQUENCE; Schema: tietok; Owner: tietokt
--

CREATE SEQUENCE palvelu_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--ALTER TABLE palvelu_seq OWNER TO tietokt(.*);

--
-- Name: palveluhist_id_seq; Type: SEQUENCE; Schema: tietok; Owner: tietokt
--

CREATE SEQUENCE palveluhist_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--ALTER TABLE palveluhist_id_seq OWNER TO tietokt(.*);

--
-- Name: palveluhist_seq; Type: SEQUENCE; Schema: tietok; Owner: tietokt
--

CREATE SEQUENCE palveluhist_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--ALTER TABLE palveluhist_seq OWNER TO tietokt(.*);

--
-- Name: palveluhistoria; Type: TABLE; Schema: tietok; Owner: tietokt
--

CREATE TABLE palveluhistoria (
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


--ALTER TABLE palveluhistoria OWNER TO tietokt(.*);

--
-- Name: palvelukatalogi_kasite_arvo; Type: TABLE; Schema: tietok; Owner: tietokt
--

CREATE TABLE palvelukatalogi_kasite_arvo (
    kasite_id numeric(38,0) NOT NULL,
    kasite character varying(100),
    arvo character varying(200),
    ylataso character varying(200)
);


--ALTER TABLE palvelukatalogi_kasite_arvo OWNER TO tietokt(.*);

--
-- Name: palvelukatalogi_kentat; Type: TABLE; Schema: tietok; Owner: tietokt
--

CREATE TABLE palvelukatalogi_kentat (
    nimi character varying(255) NOT NULL,
    sarakkeen_nimi character varying(255),
    jarjestys numeric(38,10)
);


--ALTER TABLE palvelukatalogi_kentat OWNER TO tietokt(.*);

--
-- Name: palveluotsikko; Type: VIEW; Schema: tietok; Owner: tietokt
--

CREATE VIEW palveluotsikko AS
 SELECT ak.nimi,
    COALESCE(concat(upper(substr((ak.nimi)::text, 1, 1)), lower(substr((ak.nimi)::text, 2, length((ak.nimi)::text)))), 'kaikki'::text) AS n_nimi
   FROM ( SELECT DISTINCT palvelu.otsikko AS nimi
           FROM palvelu) ak
  ORDER BY COALESCE(concat(upper(substr((ak.nimi)::text, 1, 1)), lower(substr((ak.nimi)::text, 2, length((ak.nimi)::text)))), 'kaikki'::text);


--ALTER TABLE palveluotsikko OWNER TO tietokt(.*);

--
-- Name: palveluylataso; Type: VIEW; Schema: tietok; Owner: tietokt
--

CREATE VIEW palveluylataso AS
 SELECT ak.nimi,
    COALESCE(concat(upper(substr((ak.nimi)::text, 1, 1)), lower(substr((ak.nimi)::text, 2, length((ak.nimi)::text)))), 'kaikki'::text) AS n_nimi
   FROM ( SELECT DISTINCT palvelu.ylataso AS nimi
           FROM palvelu) ak
  ORDER BY COALESCE(concat(upper(substr((ak.nimi)::text, 1, 1)), lower(substr((ak.nimi)::text, 2, length((ak.nimi)::text)))), 'kaikki'::text);


--ALTER TABLE palveluylataso OWNER TO tietokt(.*);

--
-- Name: rekisterijulkaisu_rivitunnu106; Type: SEQUENCE; Schema: tietok; Owner: tietokt
--

CREATE SEQUENCE rekisterijulkaisu_rivitunnu106
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--ALTER TABLE rekisterijulkaisu_rivitunnu106 OWNER TO tietokt(.*);

--
-- Name: rekisterikayttotiheys_rivit326; Type: SEQUENCE; Schema: tietok; Owner: tietokt
--

CREATE SEQUENCE rekisterikayttotiheys_rivit326
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--ALTER TABLE rekisterikayttotiheys_rivit326 OWNER TO tietokt(.*);

--
-- Name: rekisterilinkki_rivitunnus_seq; Type: SEQUENCE; Schema: tietok; Owner: tietokt
--

CREATE SEQUENCE rekisterilinkki_rivitunnus_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--ALTER TABLE rekisterilinkki_rivitunnus_seq OWNER TO tietokt(.*);

--
-- Name: rekisterilooginentietovaras113; Type: SEQUENCE; Schema: tietok; Owner: tietokt
--

CREATE SEQUENCE rekisterilooginentietovaras113
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--ALTER TABLE rekisterilooginentietovaras113 OWNER TO tietokt(.*);

--
-- Name: rekisterilooginentietovaras525; Type: SEQUENCE; Schema: tietok; Owner: tietokt
--

CREATE SEQUENCE rekisterilooginentietovaras525
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--ALTER TABLE rekisterilooginentietovaras525 OWNER TO tietokt(.*);

--
-- Name: rekisteripalvelutaso_rivitu222; Type: SEQUENCE; Schema: tietok; Owner: tietokt
--

CREATE SEQUENCE rekisteripalvelutaso_rivitu222
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--ALTER TABLE rekisteripalvelutaso_rivitu222 OWNER TO tietokt(.*);

--
-- Name: rekisteritietojarjestelmati301; Type: SEQUENCE; Schema: tietok; Owner: tietokt
--

CREATE SEQUENCE rekisteritietojarjestelmati301
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--ALTER TABLE rekisteritietojarjestelmati301 OWNER TO tietokt(.*);

--
-- Name: rekisteritietokantateknolog539; Type: SEQUENCE; Schema: tietok; Owner: tietokt
--

CREATE SEQUENCE rekisteritietokantateknolog539
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--ALTER TABLE rekisteritietokantateknolog539 OWNER TO tietokt(.*);

--
-- Name: rekisteritietolahde_rivitun386; Type: SEQUENCE; Schema: tietok; Owner: tietokt
--

CREATE SEQUENCE rekisteritietolahde_rivitun386
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--ALTER TABLE rekisteritietolahde_rivitun386 OWNER TO tietokt(.*);

--
-- Name: rekisteritietolahde_rivitun388; Type: SEQUENCE; Schema: tietok; Owner: tietokt
--

CREATE SEQUENCE rekisteritietolahde_rivitun388
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--ALTER TABLE rekisteritietolahde_rivitun388 OWNER TO tietokt(.*);

--
-- Name: rekisteritietolahde_rivitun389; Type: SEQUENCE; Schema: tietok; Owner: tietokt
--

CREATE SEQUENCE rekisteritietolahde_rivitun389
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--ALTER TABLE rekisteritietolahde_rivitun389 OWNER TO tietokt(.*);

--
-- Name: rekisteritietoryhmaomistaja952; Type: SEQUENCE; Schema: tietok; Owner: tietokt
--

CREATE SEQUENCE rekisteritietoryhmaomistaja952
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--ALTER TABLE rekisteritietoryhmaomistaja952 OWNER TO tietokt(.*);

--
-- Name: rekisteritietovarastoomista937; Type: SEQUENCE; Schema: tietok; Owner: tietokt
--

CREATE SEQUENCE rekisteritietovarastoomista937
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--ALTER TABLE rekisteritietovarastoomista937 OWNER TO tietokt(.*);

--
-- Name: rekisteritila_rivitunnus_seq; Type: SEQUENCE; Schema: tietok; Owner: tietokt
--

CREATE SEQUENCE rekisteritila_rivitunnus_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--ALTER TABLE rekisteritila_rivitunnus_seq OWNER TO tietokt(.*);

--
-- Name: rooli; Type: TABLE; Schema: tietok; Owner: tietokt
--

CREATE TABLE rooli (
    tunnus numeric(38,10) NOT NULL,
    nimi character varying(255) NOT NULL
);


--ALTER TABLE rooli OWNER TO tietokt(.*);

--
-- Name: rooli_id_seq; Type: SEQUENCE; Schema: tietok; Owner: tietokt
--

CREATE SEQUENCE rooli_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--ALTER TABLE rooli_id_seq OWNER TO tietokt(.*);

--
-- Name: rooli_seq; Type: SEQUENCE; Schema: tietok; Owner: tietokt
--

CREATE SEQUENCE rooli_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--ALTER TABLE rooli_seq OWNER TO tietokt(.*);

--
-- Name: sovellus_henkilo_rooli; Type: TABLE; Schema: tietok; Owner: tietokt
--

CREATE TABLE sovellus_henkilo_rooli (
    henkilo_id numeric(38,10) NOT NULL,
    rooli_id numeric(38,10) NOT NULL,
    sovellus_id numeric(38,10) NOT NULL,
    rivitunnus numeric(38,10) NOT NULL
);


--ALTER TABLE sovellus_henkilo_rooli OWNER TO tietokt(.*);

--
-- Name: sovellus_henkilo_rooli_hist; Type: TABLE; Schema: tietok; Owner: tietokt
--

CREATE TABLE sovellus_henkilo_rooli_hist (
    rivitunnus numeric(38,10) NOT NULL,
    henkilo_id numeric(38,10) NOT NULL,
    rooli_id numeric(38,10) NOT NULL,
    sovellus_id numeric(38,10) NOT NULL,
    historiatyyppi character varying(6) NOT NULL,
    riviluotupvm timestamp without time zone,
    rivimuokkaajatunnus character varying(150)
);


--ALTER TABLE sovellus_henkilo_rooli_hist OWNER TO tietokt(.*);

--
-- Name: sovellus_hist_id_seq; Type: SEQUENCE; Schema: tietok; Owner: tietokt
--

CREATE SEQUENCE sovellus_hist_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--ALTER TABLE sovellus_hist_id_seq OWNER TO tietokt(.*);

--
-- Name: sovellus_hist_seq; Type: SEQUENCE; Schema: tietok; Owner: tietokt
--

CREATE SEQUENCE sovellus_hist_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--ALTER TABLE sovellus_hist_seq OWNER TO tietokt(.*);

--
-- Name: sovellus_history; Type: TABLE; Schema: tietok; Owner: tietokt
--

CREATE TABLE sovellus_history (
    rivitunnus numeric(38,10) NOT NULL,
    tunnus numeric(38,10) NOT NULL,
    elinkaaritieto character varying(4000),
    poistunut numeric(38,10) NOT NULL,
    historiatyyppi character varying(6) NOT NULL,
    rivimuokkaajatunnus character varying(150)
);


--ALTER TABLE sovellus_history OWNER TO tietokt(.*);

--
-- Name: sovellus_id_seq; Type: SEQUENCE; Schema: tietok; Owner: tietokt
--

CREATE SEQUENCE sovellus_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--ALTER TABLE sovellus_id_seq OWNER TO tietokt(.*);

--
-- Name: sovellus_seq; Type: SEQUENCE; Schema: tietok; Owner: tietokt
--

CREATE SEQUENCE sovellus_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--ALTER TABLE sovellus_seq OWNER TO tietokt(.*);

--
-- Name: sovellus_temp; Type: TABLE; Schema: tietok; Owner: tietokt
--

CREATE TABLE sovellus_temp (
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


--ALTER TABLE sovellus_temp OWNER TO tietokt(.*);

--
-- Name: sovellus_tuonti; Type: TABLE; Schema: tietok; Owner: tietokt
--

CREATE TABLE sovellus_tuonti (
    tuontiaika timestamp without time zone NOT NULL,
    onnistunut numeric(38,10) NOT NULL,
    tuonti_id numeric(38,10) NOT NULL
);


--ALTER TABLE sovellus_tuonti OWNER TO tietokt(.*);

--
-- Name: sovellus_tuonti_id_seq; Type: SEQUENCE; Schema: tietok; Owner: tietokt
--

CREATE SEQUENCE sovellus_tuonti_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--ALTER TABLE sovellus_tuonti_id_seq OWNER TO tietokt(.*);

--
-- Name: sovhenkroolihist_id_seq; Type: SEQUENCE; Schema: tietok; Owner: tietokt
--

CREATE SEQUENCE sovhenkroolihist_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--ALTER TABLE sovhenkroolihist_id_seq OWNER TO tietokt(.*);

--
-- Name: sovhenkroolihist_seq; Type: SEQUENCE; Schema: tietok; Owner: tietokt
--

CREATE SEQUENCE sovhenkroolihist_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--ALTER TABLE sovhenkroolihist_seq OWNER TO tietokt(.*);

--
-- Name: termilomake; Type: TABLE; Schema: tietok; Owner: tietokt
--

CREATE TABLE termilomake (
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


--ALTER TABLE termilomake OWNER TO tietokt(.*);

--
-- Name: termilomake_id_seq; Type: SEQUENCE; Schema: tietok; Owner: tietokt
--

CREATE SEQUENCE termilomake_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--ALTER TABLE termilomake_id_seq OWNER TO tietokt(.*);

--
-- Name: termilomakeaskahistoria_id_seq; Type: SEQUENCE; Schema: tietok; Owner: tietokt
--

CREATE SEQUENCE termilomakeaskahistoria_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--ALTER TABLE termilomakeaskahistoria_id_seq OWNER TO tietokt(.*);

--
-- Name: termilomakeaskasihistoria; Type: TABLE; Schema: tietok; Owner: tietokt
--

CREATE TABLE termilomakeaskasihistoria (
    rivi_id numeric(38,10) NOT NULL,
    assosiatiivinen_ylakasite numeric(38,10),
    assosiatiivinen_alakasite numeric(38,10),
    historiatyyppi character varying(6),
    riviluotupvm timestamp without time zone,
    rivimuokkaajatunnus character varying(150)
);


--ALTER TABLE termilomakeaskasihistoria OWNER TO tietokt(.*);

--
-- Name: termilomakeassoskasite; Type: TABLE; Schema: tietok; Owner: tietokt
--

CREATE TABLE termilomakeassoskasite (
    rivitunnus numeric(38,10) NOT NULL,
    assosiatiivinen_ylakasite numeric(38,10),
    assosiatiivinen_alakasite numeric(38,10),
    rivitila character varying(6)
);


--ALTER TABLE termilomakeassoskasite OWNER TO tietokt(.*);

--
-- Name: termilomakeassoskasite_id_seq; Type: SEQUENCE; Schema: tietok; Owner: tietokt
--

CREATE SEQUENCE termilomakeassoskasite_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--ALTER TABLE termilomakeassoskasite_id_seq OWNER TO tietokt(.*);

--
-- Name: termilomakehierarkkasite; Type: TABLE; Schema: tietok; Owner: tietokt
--

CREATE TABLE termilomakehierarkkasite (
    rivitunnus numeric(38,10) NOT NULL,
    hierarkkinen_ylakasite numeric(38,10),
    hierarkkinen_alakasite numeric(38,10),
    rivitila character varying(6)
);


--ALTER TABLE termilomakehierarkkasite OWNER TO tietokt(.*);

--
-- Name: termilomakehierkasite_id_seq; Type: SEQUENCE; Schema: tietok; Owner: tietokt
--

CREATE SEQUENCE termilomakehierkasite_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--ALTER TABLE termilomakehierkasite_id_seq OWNER TO tietokt(.*);

--
-- Name: termilomakehierkhist_id_seq; Type: SEQUENCE; Schema: tietok; Owner: tietokt
--

CREATE SEQUENCE termilomakehierkhist_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--ALTER TABLE termilomakehierkhist_id_seq OWNER TO tietokt(.*);

--
-- Name: termilomakehist_id_seq; Type: SEQUENCE; Schema: tietok; Owner: tietokt
--

CREATE SEQUENCE termilomakehist_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--ALTER TABLE termilomakehist_id_seq OWNER TO tietokt(.*);

--
-- Name: termilomakehistoria; Type: TABLE; Schema: tietok; Owner: tietokt
--

CREATE TABLE termilomakehistoria (
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


--ALTER TABLE termilomakehistoria OWNER TO tietokt(.*);

--
-- Name: termilomakehkasitehistoria; Type: TABLE; Schema: tietok; Owner: tietokt
--

CREATE TABLE termilomakehkasitehistoria (
    rivi_id numeric(38,10) NOT NULL,
    hierarkkinen_alakasite numeric(38,10),
    hierarkkinen_ylakasite numeric(38,10),
    historiatyyppi character varying(6),
    riviluotupvm timestamp without time zone,
    rivimuokkaajatunnus character varying(150)
);


--ALTER TABLE termilomakehkasitehistoria OWNER TO tietokt(.*);

--
-- Name: termilomakekoostkashist_id_seq; Type: SEQUENCE; Schema: tietok; Owner: tietokt
--

CREATE SEQUENCE termilomakekoostkashist_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--ALTER TABLE termilomakekoostkashist_id_seq OWNER TO tietokt(.*);

--
-- Name: termilomakekoostkasihistoria; Type: TABLE; Schema: tietok; Owner: tietokt
--

CREATE TABLE termilomakekoostkasihistoria (
    rivi_id numeric(38,10) NOT NULL,
    koostumussuhteinen_ylakasite numeric(38,10),
    koostumussuhteinen_alakasite numeric(38,10),
    historiatyyppi character varying(6),
    riviluotupvm timestamp without time zone,
    rivimuokkaajatunnus character varying(150)
);


--ALTER TABLE termilomakekoostkasihistoria OWNER TO tietokt(.*);

--
-- Name: termilomakekoostkasite; Type: TABLE; Schema: tietok; Owner: tietokt
--

CREATE TABLE termilomakekoostkasite (
    rivitunnus numeric(38,10) NOT NULL,
    koostumussuhteinen_ylakasite numeric(38,10),
    koostumussuhteinen_alakasite numeric(38,10),
    rivitila character varying(6)
);


--ALTER TABLE termilomakekoostkasite OWNER TO tietokt(.*);

--
-- Name: termilomakekoostkasite_id_seq; Type: SEQUENCE; Schema: tietok; Owner: tietokt
--

CREATE SEQUENCE termilomakekoostkasite_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--ALTER TABLE termilomakekoostkasite_id_seq OWNER TO tietokt(.*);

--
-- Name: tiesuunnitelma; Type: TABLE; Schema: tietok; Owner: tietokt
--

CREATE TABLE tiesuunnitelma (
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


--ALTER TABLE tiesuunnitelma OWNER TO tietokt(.*);

--
-- Name: tiesuunnitelmahistoria; Type: TABLE; Schema: tietok; Owner: tietokt
--

CREATE TABLE tiesuunnitelmahistoria (
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


--ALTER TABLE tiesuunnitelmahistoria OWNER TO tietokt(.*);

--
-- Name: tieto_kasite_arvo; Type: TABLE; Schema: tietok; Owner: tietokt
--

CREATE TABLE tieto_kasite_arvo (
    kasite_wid numeric(38,0) NOT NULL,
    kasite character varying(100),
    arvo character varying(200)
);


--ALTER TABLE tieto_kasite_arvo OWNER TO tietokt(.*);

--
-- Name: tieto_tietotunnus_seq_1; Type: SEQUENCE; Schema: tietok; Owner: tietokt
--

CREATE SEQUENCE tieto_tietotunnus_seq_1
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--ALTER TABLE tieto_tietotunnus_seq_1 OWNER TO tietokt(.*);

--
-- Name: tietohistoria; Type: TABLE; Schema: tietok; Owner: tietokt
--

CREATE TABLE tietohistoria (
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


--ALTER TABLE tietohistoria OWNER TO tietokt(.*);

--
-- Name: tietojarj_kasite_arvo_28092020; Type: TABLE; Schema: tietok; Owner: tietokt
--

CREATE TABLE tietojarj_kasite_arvo_28092020 (
    kasite_wid numeric(38,0) NOT NULL,
    kasite character varying(100),
    arvo character varying(200)
);


--ALTER TABLE tietojarj_kasite_arvo_28092020 OWNER TO tietokt(.*);

--
-- Name: tietojarjestelma_kasite_arvo; Type: TABLE; Schema: tietok; Owner: tietokt
--

CREATE TABLE tietojarjestelma_kasite_arvo (
    kasite_wid numeric(38,0) NOT NULL,
    kasite character varying(100),
    arvo character varying(200)
);


--ALTER TABLE tietojarjestelma_kasite_arvo OWNER TO tietokt(.*);

--
-- Name: tietojarjestelmapalvelu; Type: TABLE; Schema: tietok; Owner: tietokt
--

CREATE TABLE tietojarjestelmapalvelu (
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


--ALTER TABLE tietojarjestelmapalvelu OWNER TO tietokt(.*);

--
-- Name: tietojarjestelmapalvelu_id_seq; Type: SEQUENCE; Schema: tietok; Owner: tietokt
--

CREATE SEQUENCE tietojarjestelmapalvelu_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--ALTER TABLE tietojarjestelmapalvelu_id_seq OWNER TO tietokt(.*);

--
-- Name: tietojarjestelmapalvelu_tie872; Type: SEQUENCE; Schema: tietok; Owner: tietokt
--

CREATE SEQUENCE tietojarjestelmapalvelu_tie872
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--ALTER TABLE tietojarjestelmapalvelu_tie872 OWNER TO tietokt(.*);

--
-- Name: tietojarjestelmasalkk_28092020; Type: TABLE; Schema: tietok; Owner: tietokt
--

CREATE TABLE tietojarjestelmasalkk_28092020 (
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


--ALTER TABLE tietojarjestelmasalkk_28092020 OWNER TO tietokt(.*);

--
-- Name: tietojarjestelmasalkku_tiet311; Type: SEQUENCE; Schema: tietok; Owner: tietokt
--

CREATE SEQUENCE tietojarjestelmasalkku_tiet311
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--ALTER TABLE tietojarjestelmasalkku_tiet311 OWNER TO tietokt(.*);

--
-- Name: tietojarjestelmasalkkufyysi263; Type: SEQUENCE; Schema: tietok; Owner: tietokt
--

CREATE SEQUENCE tietojarjestelmasalkkufyysi263
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--ALTER TABLE tietojarjestelmasalkkufyysi263 OWNER TO tietokt(.*);

--
-- Name: tietojarjestelmasalkkuhistoria; Type: TABLE; Schema: tietok; Owner: tietokt
--

CREATE TABLE tietojarjestelmasalkkuhistoria (
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


--ALTER TABLE tietojarjestelmasalkkuhistoria OWNER TO tietokt(.*);

--
-- Name: tietojarjpalvelu_kasite_arvo; Type: TABLE; Schema: tietok; Owner: tietokt
--

CREATE TABLE tietojarjpalvelu_kasite_arvo (
    kasite_id numeric(38,10) NOT NULL,
    kasite character varying(100) NOT NULL,
    arvo character varying(200) NOT NULL
);


--ALTER TABLE tietojarjpalvelu_kasite_arvo OWNER TO tietokt(.*);

--
-- Name: tietojarjpalvelu_tietolaji; Type: TABLE; Schema: tietok; Owner: tietokt
--

CREATE TABLE tietojarjpalvelu_tietolaji (
    tietojarjestelmapalvelutunnus numeric(38,10) NOT NULL,
    tietolajitunnus numeric(38,10) NOT NULL,
    rivitunnus numeric(38,10) NOT NULL,
    rivitila character varying(15)
);


--ALTER TABLE tietojarjpalvelu_tietolaji OWNER TO tietokt(.*);

--
-- Name: tietojarjpalveluhist_id_seq; Type: SEQUENCE; Schema: tietok; Owner: tietokt
--

CREATE SEQUENCE tietojarjpalveluhist_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--ALTER TABLE tietojarjpalveluhist_id_seq OWNER TO tietokt(.*);

--
-- Name: tietojarjpalveluhistoria; Type: TABLE; Schema: tietok; Owner: tietokt
--

CREATE TABLE tietojarjpalveluhistoria (
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


--ALTER TABLE tietojarjpalveluhistoria OWNER TO tietokt(.*);

--
-- Name: tietojarjpalvelutieto_id_seq; Type: SEQUENCE; Schema: tietok; Owner: tietokt
--

CREATE SEQUENCE tietojarjpalvelutieto_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--ALTER TABLE tietojarjpalvelutieto_id_seq OWNER TO tietokt(.*);

--
-- Name: tietojarjpalvelutietohistoria; Type: TABLE; Schema: tietok; Owner: tietokt
--

CREATE TABLE tietojarjpalvelutietohistoria (
    rivitunnus numeric(38,10) NOT NULL,
    tietojarjestelmapalvelutunnus numeric(38,10) NOT NULL,
    tietolajitunnus numeric(38,10) NOT NULL,
    rivitila character varying(6),
    historiatyyppi character varying(6),
    riviluotupvm timestamp without time zone,
    rivimuokattupvm timestamp without time zone,
    rivimuokkaajatunnus character varying(150)
);


--ALTER TABLE tietojarjpalvelutietohistoria OWNER TO tietokt(.*);

--
-- Name: tietojarjpalvliitjarj_id_seq; Type: SEQUENCE; Schema: tietok; Owner: tietokt
--

CREATE SEQUENCE tietojarjpalvliitjarj_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--ALTER TABLE tietojarjpalvliitjarj_id_seq OWNER TO tietokt(.*);

--
-- Name: tietojarjpalvliitjarjhistoria; Type: TABLE; Schema: tietok; Owner: tietokt
--

CREATE TABLE tietojarjpalvliitjarjhistoria (
    rivi_id numeric(38,10),
    tietojarjpalvelutunnus numeric(38,10),
    liittyvajarjestelmatunnus numeric(38,10),
    riviluotupvm timestamp without time zone,
    historiatyyppi character varying(6),
    rivimuokkaajatunnus character varying(150)
);


--ALTER TABLE tietojarjpalvliitjarjhistoria OWNER TO tietokt(.*);

--
-- Name: tietojarjpalvliittyvajarj; Type: TABLE; Schema: tietok; Owner: tietokt
--

CREATE TABLE tietojarjpalvliittyvajarj (
    rivitunnus numeric(38,10) NOT NULL,
    tietojarjpalvelutunnus numeric(38,10),
    liittyvajarjestelmatunnus numeric(38,10)
);


--ALTER TABLE tietojarjpalvliittyvajarj OWNER TO tietokt(.*);

--
-- Name: tietojarjpalvtietohist_id_seq; Type: SEQUENCE; Schema: tietok; Owner: tietokt
--

CREATE SEQUENCE tietojarjpalvtietohist_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--ALTER TABLE tietojarjpalvtietohist_id_seq OWNER TO tietokt(.*);

--
-- Name: tietolahde_rivitunnus_seq; Type: SEQUENCE; Schema: tietok; Owner: tietokt
--

CREATE SEQUENCE tietolahde_rivitunnus_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--ALTER TABLE tietolahde_rivitunnus_seq OWNER TO tietokt(.*);

--
-- Name: tietolaji_id_seq; Type: SEQUENCE; Schema: tietok; Owner: tietokt
--

CREATE SEQUENCE tietolaji_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--ALTER TABLE tietolaji_id_seq OWNER TO tietokt(.*);

--
-- Name: tietolaji_seq; Type: SEQUENCE; Schema: tietok; Owner: tietokt
--

CREATE SEQUENCE tietolaji_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--ALTER TABLE tietolaji_seq OWNER TO tietokt(.*);

--
-- Name: tietolajihist_id_seq; Type: SEQUENCE; Schema: tietok; Owner: tietokt
--

CREATE SEQUENCE tietolajihist_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--ALTER TABLE tietolajihist_id_seq OWNER TO tietokt(.*);

--
-- Name: tietolajihist_seq; Type: SEQUENCE; Schema: tietok; Owner: tietokt
--

CREATE SEQUENCE tietolajihist_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--ALTER TABLE tietolajihist_seq OWNER TO tietokt(.*);

--
-- Name: tietolooginenhistoria; Type: TABLE; Schema: tietok; Owner: tietokt
--

CREATE TABLE tietolooginenhistoria (
    rivitunnus numeric(38,10) NOT NULL,
    looginentietovarantotunnus numeric(38,10) NOT NULL,
    tietotunnus numeric(38,10) NOT NULL,
    historiatyyppi character varying(6) NOT NULL,
    rivitila character varying(15) NOT NULL,
    riviluotupvm timestamp without time zone,
    rivimuokkaajatunnus character varying(150)
);


--ALTER TABLE tietolooginenhistoria OWNER TO tietokt(.*);

--
-- Name: tietolooginentietovaranto; Type: TABLE; Schema: tietok; Owner: tietokt
--

CREATE TABLE tietolooginentietovaranto (
    rivitunnus numeric(38,10) NOT NULL,
    looginentietovarantotunnus numeric(38,10) NOT NULL,
    tietotunnus numeric(38,10) NOT NULL,
    rivitila character varying(15) NOT NULL
);


--ALTER TABLE tietolooginentietovaranto OWNER TO tietokt(.*);

--
-- Name: tietoomaisuus; Type: TABLE; Schema: tietok; Owner: tietokt
--

CREATE TABLE tietoomaisuus (
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


--ALTER TABLE tietoomaisuus OWNER TO tietokt(.*);

--
-- Name: tietoomaisuus_id_seq; Type: SEQUENCE; Schema: tietok; Owner: tietokt
--

CREATE SEQUENCE tietoomaisuus_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--ALTER TABLE tietoomaisuus_id_seq OWNER TO tietokt(.*);

--
-- Name: tietoomaisuus_kasite_arvo; Type: TABLE; Schema: tietok; Owner: tietokt
--

CREATE TABLE tietoomaisuus_kasite_arvo (
    kasite_wid numeric(38,0) NOT NULL,
    kasite character varying(100),
    arvo character varying(200),
    pisteytys numeric(38,10)
);


--ALTER TABLE tietoomaisuus_kasite_arvo OWNER TO tietokt(.*);

--
-- Name: tietoomaisuushist_id_seq; Type: SEQUENCE; Schema: tietok; Owner: tietokt
--

CREATE SEQUENCE tietoomaisuushist_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--ALTER TABLE tietoomaisuushist_id_seq OWNER TO tietokt(.*);

--
-- Name: tietoomaisuushistoria; Type: TABLE; Schema: tietok; Owner: tietokt
--

CREATE TABLE tietoomaisuushistoria (
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


--ALTER TABLE tietoomaisuushistoria OWNER TO tietokt(.*);

--
-- Name: tietoryhma_id_seq; Type: SEQUENCE; Schema: tietok; Owner: tietokt
--

CREATE SEQUENCE tietoryhma_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--ALTER TABLE tietoryhma_id_seq OWNER TO tietokt(.*);

--
-- Name: tietoryhma_seq; Type: SEQUENCE; Schema: tietok; Owner: tietokt
--

CREATE SEQUENCE tietoryhma_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--ALTER TABLE tietoryhma_seq OWNER TO tietokt(.*);

--
-- Name: tietoryhma_tietoryhmatunnus830; Type: SEQUENCE; Schema: tietok; Owner: tietokt
--

CREATE SEQUENCE tietoryhma_tietoryhmatunnus830
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--ALTER TABLE tietoryhma_tietoryhmatunnus830 OWNER TO tietokt(.*);

--
-- Name: tietoryhmahist_id_seq; Type: SEQUENCE; Schema: tietok; Owner: tietokt
--

CREATE SEQUENCE tietoryhmahist_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--ALTER TABLE tietoryhmahist_id_seq OWNER TO tietokt(.*);

--
-- Name: tietoryhmahist_seq; Type: SEQUENCE; Schema: tietok; Owner: tietokt
--

CREATE SEQUENCE tietoryhmahist_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--ALTER TABLE tietoryhmahist_seq OWNER TO tietokt(.*);

--
-- Name: tietoryhmahistoria; Type: TABLE; Schema: tietok; Owner: tietokt
--

CREATE TABLE tietoryhmahistoria (
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


--ALTER TABLE tietoryhmahistoria OWNER TO tietokt(.*);

--
-- Name: tietoryhmalooginentietovara529; Type: SEQUENCE; Schema: tietok; Owner: tietokt
--

CREATE SEQUENCE tietoryhmalooginentietovara529
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--ALTER TABLE tietoryhmalooginentietovara529 OWNER TO tietokt(.*);

--
-- Name: tietoryhmapaatietohistoria; Type: TABLE; Schema: tietok; Owner: tietokt
--

CREATE TABLE tietoryhmapaatietohistoria (
    rivitunnus numeric(38,10) NOT NULL,
    paatietoryhmatunnus numeric(38,10) NOT NULL,
    historiatyyppi character varying(6) NOT NULL,
    tietoryhmatunnus numeric(38,10) NOT NULL,
    rivitila character varying(15) NOT NULL,
    riviluotupvm timestamp without time zone,
    rivimuokkaajatunnus character varying(150)
);


--ALTER TABLE tietoryhmapaatietohistoria OWNER TO tietokt(.*);

--
-- Name: tietoryhmapaatietoryhma; Type: TABLE; Schema: tietok; Owner: tietokt
--

CREATE TABLE tietoryhmapaatietoryhma (
    rivitunnus numeric(38,10) NOT NULL,
    paatietoryhmatunnus numeric(38,10) NOT NULL,
    tietoryhmatunnus numeric(38,10) NOT NULL,
    rivitila character varying(15) NOT NULL
);


--ALTER TABLE tietoryhmapaatietoryhma OWNER TO tietokt(.*);

--
-- Name: tietoryhmatietovarahist_id_seq; Type: SEQUENCE; Schema: tietok; Owner: tietokt
--

CREATE SEQUENCE tietoryhmatietovarahist_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--ALTER TABLE tietoryhmatietovarahist_id_seq OWNER TO tietokt(.*);

--
-- Name: tietoryhmatietovaranto; Type: TABLE; Schema: tietok; Owner: tietokt
--

CREATE TABLE tietoryhmatietovaranto (
    rivitunnus numeric(38,10) NOT NULL,
    tietovarantotunnus numeric(38,10),
    tietoryhmatunnus numeric(38,10)
);


--ALTER TABLE tietoryhmatietovaranto OWNER TO tietokt(.*);

--
-- Name: tietoryhmatietovaranto_id_seq; Type: SEQUENCE; Schema: tietok; Owner: tietokt
--

CREATE SEQUENCE tietoryhmatietovaranto_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--ALTER TABLE tietoryhmatietovaranto_id_seq OWNER TO tietokt(.*);

--
-- Name: tietoryhmatietovarantohistoria; Type: TABLE; Schema: tietok; Owner: tietokt
--

CREATE TABLE tietoryhmatietovarantohistoria (
    rivi_id numeric(38,10) NOT NULL,
    tietovarantotunnus numeric(38,10),
    tietoryhmatunnus numeric(38,10),
    riviluotupvm timestamp without time zone,
    historiatyyppi character varying(6),
    rivimuokkaajatunnus character varying(150)
);


--ALTER TABLE tietoryhmatietovarantohistoria OWNER TO tietokt(.*);

--
-- Name: tietosuojavastaava; Type: TABLE; Schema: tietok; Owner: tietokt
--

CREATE TABLE tietosuojavastaava (
    tietosuojavastaavatunnus numeric(38,10) NOT NULL,
    nimi character varying(225),
    osoite character varying(255),
    sahkoposti character varying(255),
    puhelinnumero character varying(255),
    rivimuokkaajatunnus character varying(150),
    riviluotupvm timestamp without time zone,
    rivimuokattupvm timestamp without time zone
);


--ALTER TABLE tietosuojavastaava OWNER TO tietokt(.*);

--
-- Name: tietosuojavastaava_id_seq; Type: SEQUENCE; Schema: tietok; Owner: tietokt
--

CREATE SEQUENCE tietosuojavastaava_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--ALTER TABLE tietosuojavastaava_id_seq OWNER TO tietokt(.*);

--
-- Name: tietosuojavastaavahist_id_seq; Type: SEQUENCE; Schema: tietok; Owner: tietokt
--

CREATE SEQUENCE tietosuojavastaavahist_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--ALTER TABLE tietosuojavastaavahist_id_seq OWNER TO tietokt(.*);

--
-- Name: tietosuojavastaavahistoria; Type: TABLE; Schema: tietok; Owner: tietokt
--

CREATE TABLE tietosuojavastaavahistoria (
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


--ALTER TABLE tietosuojavastaavahistoria OWNER TO tietokt(.*);

--
-- Name: tietotietoryhma; Type: TABLE; Schema: tietok; Owner: tietokt
--

CREATE TABLE tietotietoryhma (
    rivitunnus numeric(38,10) NOT NULL,
    tietoryhmatunnus numeric(38,10) NOT NULL,
    tietotunnus numeric(38,10) NOT NULL,
    rivitila character varying(15) NOT NULL
);


--ALTER TABLE tietotietoryhma OWNER TO tietokt(.*);

--
-- Name: tietotietoryhmahistoria; Type: TABLE; Schema: tietok; Owner: tietokt
--

CREATE TABLE tietotietoryhmahistoria (
    rivitunnus numeric(38,10) NOT NULL,
    tietoryhmatunnus numeric(38,10) NOT NULL,
    tietotunnus numeric(38,10) NOT NULL,
    historiatyyppi character varying(6) NOT NULL,
    rivitila character varying(15) NOT NULL,
    riviluotupvm timestamp without time zone,
    rivimuokkaajatunnus character varying(150)
);


--ALTER TABLE tietotietoryhmahistoria OWNER TO tietokt(.*);

--
-- Name: tietovarahenktietoryhma_id_seq; Type: SEQUENCE; Schema: tietok; Owner: tietokt
--

CREATE SEQUENCE tietovarahenktietoryhma_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--ALTER TABLE tietovarahenktietoryhma_id_seq OWNER TO tietokt(.*);

--
-- Name: tietovarakasitperushist_id_seq; Type: SEQUENCE; Schema: tietok; Owner: tietokt
--

CREATE SEQUENCE tietovarakasitperushist_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--ALTER TABLE tietovarakasitperushist_id_seq OWNER TO tietokt(.*);

--
-- Name: tietovarakasittperustehistoria; Type: TABLE; Schema: tietok; Owner: tietokt
--

CREATE TABLE tietovarakasittperustehistoria (
    rivi_id numeric(38,10),
    tietovarantotunnus numeric(38,10),
    kasittelyn_peruste character varying(255),
    riviluotupvm timestamp without time zone,
    historiatyyppi character varying(6),
    rivimuokkaajatunnus character varying(150)
);


--ALTER TABLE tietovarakasittperustehistoria OWNER TO tietokt(.*);

--
-- Name: tietovaranto; Type: TABLE; Schema: tietok; Owner: tietokt
--

CREATE TABLE tietovaranto (
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


--ALTER TABLE tietovaranto OWNER TO tietokt(.*);

--
-- Name: tietovaranto_id_seq; Type: SEQUENCE; Schema: tietok; Owner: tietokt
--

CREATE SEQUENCE tietovaranto_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--ALTER TABLE tietovaranto_id_seq OWNER TO tietokt(.*);

--
-- Name: tietovaranto_kasite_arvo; Type: TABLE; Schema: tietok; Owner: tietokt
--

CREATE TABLE tietovaranto_kasite_arvo (
    kasite_wid numeric(38,0) NOT NULL,
    kasite character varying(100),
    arvo character varying(200),
    selitys character varying(200)
);


--ALTER TABLE tietovaranto_kasite_arvo OWNER TO tietokt(.*);

--
-- Name: tietovarantohenkilotietoryhma; Type: TABLE; Schema: tietok; Owner: tietokt
--

CREATE TABLE tietovarantohenkilotietoryhma (
    rivitunnus numeric(38,10) NOT NULL,
    tietovarantotunnus numeric(38,10),
    henkilotietoryhma character varying(255)
);


--ALTER TABLE tietovarantohenkilotietoryhma OWNER TO tietokt(.*);

--
-- Name: tietovarantohist_id_seq; Type: SEQUENCE; Schema: tietok; Owner: tietokt
--

CREATE SEQUENCE tietovarantohist_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--ALTER TABLE tietovarantohist_id_seq OWNER TO tietokt(.*);

--
-- Name: tietovarantohistoria; Type: TABLE; Schema: tietok; Owner: tietokt
--

CREATE TABLE tietovarantohistoria (
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


--ALTER TABLE tietovarantohistoria OWNER TO tietokt(.*);

--
-- Name: tietovarantokasittelynperuste; Type: TABLE; Schema: tietok; Owner: tietokt
--

CREATE TABLE tietovarantokasittelynperuste (
    rivitunnus numeric(38,10) NOT NULL,
    tietovarantotunnus numeric(38,10),
    kasittelyn_peruste character varying(255)
);


--ALTER TABLE tietovarantokasittelynperuste OWNER TO tietokt(.*);

--
-- Name: tietovarantorekisryhmahistoria; Type: TABLE; Schema: tietok; Owner: tietokt
--

CREATE TABLE tietovarantorekisryhmahistoria (
    rivi_id numeric(38,10),
    tietovarantotunnus numeric(38,10),
    rekisteroityryhma character varying(255),
    riviluotupvm timestamp without time zone,
    historiatyyppi character varying(6),
    rivimuokkaajatunnus character varying(150)
);


--ALTER TABLE tietovarantorekisryhmahistoria OWNER TO tietokt(.*);

--
-- Name: tietovarantorekisteroityryhma; Type: TABLE; Schema: tietok; Owner: tietokt
--

CREATE TABLE tietovarantorekisteroityryhma (
    rivitunnus numeric(38,10) NOT NULL,
    tietovarantotunnus numeric(38,10),
    rekisteroityryhma character varying(255)
);


--ALTER TABLE tietovarantorekisteroityryhma OWNER TO tietokt(.*);

--
-- Name: tietovarantorekistryhma_id_seq; Type: SEQUENCE; Schema: tietok; Owner: tietokt
--

CREATE SEQUENCE tietovarantorekistryhma_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--ALTER TABLE tietovarantorekistryhma_id_seq OWNER TO tietokt(.*);

--
-- Name: tietovarantotiedonohjaus; Type: TABLE; Schema: tietok; Owner: tietokt
--

CREATE TABLE tietovarantotiedonohjaus (
    rivitunnus numeric(38,10) NOT NULL,
    tietovarantotunnus numeric(38,10),
    tiedonohjaussuunnitelma character varying(255)
);


--ALTER TABLE tietovarantotiedonohjaus OWNER TO tietokt(.*);

--
-- Name: tietovarantoturvatoimenpide; Type: TABLE; Schema: tietok; Owner: tietokt
--

CREATE TABLE tietovarantoturvatoimenpide (
    rivitunnus numeric(38,10) NOT NULL,
    tietovarantotunnus numeric(38,10),
    turvatoimenpide character varying(255)
);


--ALTER TABLE tietovarantoturvatoimenpide OWNER TO tietokt(.*);

--
-- Name: tietovarantovastaanottajaryhma; Type: TABLE; Schema: tietok; Owner: tietokt
--

CREATE TABLE tietovarantovastaanottajaryhma (
    rivitunnus numeric(38,10) NOT NULL,
    tietovarantotunnus numeric(38,10),
    vastaanottajaryhma character varying(255)
);


--ALTER TABLE tietovarantovastaanottajaryhma OWNER TO tietokt(.*);

--
-- Name: tietovarantoyhteisrekistpitaja; Type: TABLE; Schema: tietok; Owner: tietokt
--

CREATE TABLE tietovarantoyhteisrekistpitaja (
    rivitunnus numeric(38,10) NOT NULL,
    tietovarantotunnus numeric(38,10),
    yhteisrekisterinpitaja character varying(255)
);


--ALTER TABLE tietovarantoyhteisrekistpitaja OWNER TO tietokt(.*);

--
-- Name: tietovarantoyllapitomuutaho; Type: TABLE; Schema: tietok; Owner: tietokt
--

CREATE TABLE tietovarantoyllapitomuutaho (
    rivitunnus numeric(38,10) NOT NULL,
    tietovarantotunnus numeric(38,10),
    yllapito_muu_taho character varying(255)
);


--ALTER TABLE tietovarantoyllapitomuutaho OWNER TO tietokt(.*);

--
-- Name: tietovarantrekryhmahist_id_seq; Type: SEQUENCE; Schema: tietok; Owner: tietokt
--

CREATE SEQUENCE tietovarantrekryhmahist_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--ALTER TABLE tietovarantrekryhmahist_id_seq OWNER TO tietokt(.*);

--
-- Name: tietovaratiedonohjaushistoria; Type: TABLE; Schema: tietok; Owner: tietokt
--

CREATE TABLE tietovaratiedonohjaushistoria (
    rivi_id numeric(38,10),
    tietovarantotunnus numeric(38,10),
    tiedonohjaussuunnitelma character varying(255),
    riviluotupvm timestamp without time zone,
    historiatyyppi character varying(6),
    rivimuokkaajatunnus character varying(150)
);


--ALTER TABLE tietovaratiedonohjaushistoria OWNER TO tietokt(.*);

--
-- Name: tietovaratiedonohjshist_id_seq; Type: SEQUENCE; Schema: tietok; Owner: tietokt
--

CREATE SEQUENCE tietovaratiedonohjshist_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--ALTER TABLE tietovaratiedonohjshist_id_seq OWNER TO tietokt(.*);

--
-- Name: tietovaraturvatoimpidehistoria; Type: TABLE; Schema: tietok; Owner: tietokt
--

CREATE TABLE tietovaraturvatoimpidehistoria (
    rivi_id numeric(38,10),
    tietovarantotunnus numeric(38,10),
    turvatoimenpide character varying(255),
    riviluotupvm timestamp without time zone,
    historiatyyppi character varying(6),
    rivimuokkaajatunnus character varying(150)
);


--ALTER TABLE tietovaraturvatoimpidehistoria OWNER TO tietokt(.*);

--
-- Name: tietovaravastaanoryhmahistoria; Type: TABLE; Schema: tietok; Owner: tietokt
--

CREATE TABLE tietovaravastaanoryhmahistoria (
    rivi_id numeric(38,10),
    tietovarantotunnus numeric(38,10),
    vastaanottajaryhma character varying(255),
    riviluotupvm timestamp without time zone,
    historiatyyppi character varying(6),
    rivimuokkaajatunnus character varying(150)
);


--ALTER TABLE tietovaravastaanoryhmahistoria OWNER TO tietokt(.*);

--
-- Name: tietovaravastaryhmahist_id_seq; Type: SEQUENCE; Schema: tietok; Owner: tietokt
--

CREATE SEQUENCE tietovaravastaryhmahist_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--ALTER TABLE tietovaravastaryhmahist_id_seq OWNER TO tietokt(.*);

--
-- Name: tietovarayhtrekpitahist_id_seq; Type: SEQUENCE; Schema: tietok; Owner: tietokt
--

CREATE SEQUENCE tietovarayhtrekpitahist_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--ALTER TABLE tietovarayhtrekpitahist_id_seq OWNER TO tietokt(.*);

--
-- Name: tietovarayhtrekpitajahistoria; Type: TABLE; Schema: tietok; Owner: tietokt
--

CREATE TABLE tietovarayhtrekpitajahistoria (
    rivi_id numeric(38,10),
    tietovarantotunnus numeric(38,10),
    yhteisrekisterinpitaja character varying(255),
    riviluotupvm timestamp without time zone,
    historiatyyppi character varying(6),
    rivimuokkaajatunnus character varying(150)
);


--ALTER TABLE tietovarayhtrekpitajahistoria OWNER TO tietokt(.*);

--
-- Name: tietovarayllapitotahohistoria; Type: TABLE; Schema: tietok; Owner: tietokt
--

CREATE TABLE tietovarayllapitotahohistoria (
    rivi_id numeric(38,10),
    tietovarantotunnus numeric(38,10),
    yllapito_muu_taho character varying(255),
    riviluotupvm timestamp without time zone,
    historiatyyppi character varying(6),
    rivimuokkaajatunnus character varying(150)
);


--ALTER TABLE tietovarayllapitotahohistoria OWNER TO tietokt(.*);

--
-- Name: tietovarayllapttahohist_id_seq; Type: SEQUENCE; Schema: tietok; Owner: tietokt
--

CREATE SEQUENCE tietovarayllapttahohist_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--ALTER TABLE tietovarayllapttahohist_id_seq OWNER TO tietokt(.*);

--
-- Name: tietovarhenktietoryhmahistoria; Type: TABLE; Schema: tietok; Owner: tietokt
--

CREATE TABLE tietovarhenktietoryhmahistoria (
    rivi_id numeric(38,10),
    tietovarantotunnus numeric(38,10),
    henkilotietoryhma character varying(255),
    riviluotupvm timestamp without time zone,
    historiatyyppi character varying(6),
    rivimuokkaajatunnus character varying(150)
);


--ALTER TABLE tietovarhenktietoryhmahistoria OWNER TO tietokt(.*);

--
-- Name: tietovarhentietryhmhist_id_seq; Type: SEQUENCE; Schema: tietok; Owner: tietokt
--

CREATE SEQUENCE tietovarhentietryhmhist_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--ALTER TABLE tietovarhentietryhmhist_id_seq OWNER TO tietokt(.*);

--
-- Name: tietovarkasittelperuste_id_seq; Type: SEQUENCE; Schema: tietok; Owner: tietokt
--

CREATE SEQUENCE tietovarkasittelperuste_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--ALTER TABLE tietovarkasittelperuste_id_seq OWNER TO tietokt(.*);

--
-- Name: tietovartiedonohjaus_id_seq; Type: SEQUENCE; Schema: tietok; Owner: tietokt
--

CREATE SEQUENCE tietovartiedonohjaus_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--ALTER TABLE tietovartiedonohjaus_id_seq OWNER TO tietokt(.*);

--
-- Name: tietovarturvatoimenpide_id_seq; Type: SEQUENCE; Schema: tietok; Owner: tietokt
--

CREATE SEQUENCE tietovarturvatoimenpide_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--ALTER TABLE tietovarturvatoimenpide_id_seq OWNER TO tietokt(.*);

--
-- Name: tietovarturvtoimpidhist_id_seq; Type: SEQUENCE; Schema: tietok; Owner: tietokt
--

CREATE SEQUENCE tietovarturvtoimpidhist_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--ALTER TABLE tietovarturvtoimpidhist_id_seq OWNER TO tietokt(.*);

--
-- Name: tietovarvastaanottryhma_id_seq; Type: SEQUENCE; Schema: tietok; Owner: tietokt
--

CREATE SEQUENCE tietovarvastaanottryhma_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--ALTER TABLE tietovarvastaanottryhma_id_seq OWNER TO tietokt(.*);

--
-- Name: tietovaryhteisrekpitaja_id_seq; Type: SEQUENCE; Schema: tietok; Owner: tietokt
--

CREATE SEQUENCE tietovaryhteisrekpitaja_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--ALTER TABLE tietovaryhteisrekpitaja_id_seq OWNER TO tietokt(.*);

--
-- Name: tietovaryllapitomuutaho_id_seq; Type: SEQUENCE; Schema: tietok; Owner: tietokt
--

CREATE SEQUENCE tietovaryllapitomuutaho_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--ALTER TABLE tietovaryllapitomuutaho_id_seq OWNER TO tietokt(.*);

--
-- Name: tjpliittyvajarjhist_id_seq; Type: SEQUENCE; Schema: tietok; Owner: tietokt
--

CREATE SEQUENCE tjpliittyvajarjhist_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--ALTER TABLE tjpliittyvajarjhist_id_seq OWNER TO tietokt(.*);

--
-- Name: toimintaprosessi; Type: TABLE; Schema: tietok; Owner: tietokt
--

CREATE TABLE toimintaprosessi (
    toimintaprosessitunnus numeric(38,10) NOT NULL,
    nimi character varying(225),
    vastaava_organisaatio character varying(255),
    tarkoitus character varying(4000),
    rivimuokkaajatunnus character varying(150),
    riviluotupvm timestamp without time zone,
    rivimuokattupvm timestamp without time zone
);


--ALTER TABLE toimintaprosessi OWNER TO tietokt(.*);

--
-- Name: toimintaprosessi_id_seq; Type: SEQUENCE; Schema: tietok; Owner: tietokt
--

CREATE SEQUENCE toimintaprosessi_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--ALTER TABLE toimintaprosessi_id_seq OWNER TO tietokt(.*);

--
-- Name: toimintaprosessi_kasite_arvo; Type: TABLE; Schema: tietok; Owner: tietokt
--

CREATE TABLE toimintaprosessi_kasite_arvo (
    kasite_wid numeric(38,0) NOT NULL,
    kasite character varying(100),
    arvo character varying(200)
);


--ALTER TABLE toimintaprosessi_kasite_arvo OWNER TO tietokt(.*);

--
-- Name: toimintaprosessihist_id_seq; Type: SEQUENCE; Schema: tietok; Owner: tietokt
--

CREATE SEQUENCE toimintaprosessihist_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--ALTER TABLE toimintaprosessihist_id_seq OWNER TO tietokt(.*);

--
-- Name: toimintaprosessihistoria; Type: TABLE; Schema: tietok; Owner: tietokt
--

CREATE TABLE toimintaprosessihistoria (
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


--ALTER TABLE toimintaprosessihistoria OWNER TO tietokt(.*);

--
-- Name: toimintaprosessitietovaranto; Type: TABLE; Schema: tietok; Owner: tietokt
--

CREATE TABLE toimintaprosessitietovaranto (
    rivitunnus numeric(38,10) NOT NULL,
    tietovarantotunnus numeric(38,10),
    toimintaprosessitunnus numeric(38,10)
);


--ALTER TABLE toimintaprosessitietovaranto OWNER TO tietokt(.*);

--
-- Name: toimintaprostietovahist_id_seq; Type: SEQUENCE; Schema: tietok; Owner: tietokt
--

CREATE SEQUENCE toimintaprostietovahist_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--ALTER TABLE toimintaprostietovahist_id_seq OWNER TO tietokt(.*);

--
-- Name: toimintaprostietovarahistoria; Type: TABLE; Schema: tietok; Owner: tietokt
--

CREATE TABLE toimintaprostietovarahistoria (
    rivi_id numeric(38,10) NOT NULL,
    tietovarantotunnus numeric(38,10),
    toimintaprosessitunnus numeric(38,10),
    riviluotupvm timestamp without time zone,
    historiatyyppi character varying(6),
    rivimuokkaajatunnus character varying(150)
);


--ALTER TABLE toimintaprostietovarahistoria OWNER TO tietokt(.*);

--
-- Name: toimintaprostietovarant_id_seq; Type: SEQUENCE; Schema: tietok; Owner: tietokt
--

CREATE SEQUENCE toimintaprostietovarant_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--ALTER TABLE toimintaprostietovarant_id_seq OWNER TO tietokt(.*);

--
-- Name: yleissuunnitelma; Type: TABLE; Schema: tietok; Owner: tietokt
--

CREATE TABLE yleissuunnitelma (
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


--ALTER TABLE yleissuunnitelma OWNER TO tietokt(.*);

--
-- Name: yleissuunnitelmahistoria; Type: TABLE; Schema: tietok; Owner: tietokt
--

CREATE TABLE yleissuunnitelmahistoria (
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


--ALTER TABLE yleissuunnitelmahistoria OWNER TO tietokt(.*);

--
-- Name: depr_jt_nakyvyys depr_jt_nakyvyys_pkey; Type: CONSTRAINT; Schema: tietok; Owner: tietokt
--

alter table  depr_jt_nakyvyys
    ADD CONSTRAINT depr_jt_nakyvyys_pkey PRIMARY KEY (id);


--
-- Name: depr_looginentietovarantotieto depr_looginentietovarantotieto_pkey; Type: CONSTRAINT; Schema: tietok; Owner: tietokt
--

alter table  depr_looginentietovarantotieto
    ADD CONSTRAINT depr_looginentietovarantotieto_pkey PRIMARY KEY (rivitunnus);


--
-- Name: depr_looginentvtoimitus depr_looginentvtoimitus_pkey; Type: CONSTRAINT; Schema: tietok; Owner: tietokt
--

alter table  depr_looginentvtoimitus
    ADD CONSTRAINT depr_looginentvtoimitus_pkey PRIMARY KEY (rivitunnus);


--
-- Name: depr_paatietoryhmalahde depr_paatietoryhmalahde_pkey; Type: CONSTRAINT; Schema: tietok; Owner: tietokt
--

alter table  depr_paatietoryhmalahde
    ADD CONSTRAINT depr_paatietoryhmalahde_pkey PRIMARY KEY (rivitunnus);


--
-- Name: depr_paatietoryhmasynonyymi depr_paatietoryhmasynonyymi_pkey; Type: CONSTRAINT; Schema: tietok; Owner: tietokt
--

alter table  depr_paatietoryhmasynonyymi
    ADD CONSTRAINT depr_paatietoryhmasynonyymi_pkey PRIMARY KEY (rivitunnus);


--
-- Name: depr_parametrit depr_parametrit_pkey; Type: CONSTRAINT; Schema: tietok; Owner: tietokt
--

alter table  depr_parametrit
    ADD CONSTRAINT depr_parametrit_pkey PRIMARY KEY (avain);


--
-- Name: depr_rekisterijulkaisu depr_rekisterijulkaisu_pkey; Type: CONSTRAINT; Schema: tietok; Owner: tietokt
--

alter table  depr_rekisterijulkaisu
    ADD CONSTRAINT depr_rekisterijulkaisu_pkey PRIMARY KEY (julkaisutieto_rivimuokkaajatun, rivimuokkaajatunnus);


--
-- Name: depr_rekisterikasitemalli depr_rekisterikasitemalli_pkey; Type: CONSTRAINT; Schema: tietok; Owner: tietokt
--

alter table  depr_rekisterikasitemalli
    ADD CONSTRAINT depr_rekisterikasitemalli_pkey PRIMARY KEY (kasitemalli);


--
-- Name: depr_rekisterikayttotiheys depr_rekisterikayttotiheys_pkey; Type: CONSTRAINT; Schema: tietok; Owner: tietokt
--

alter table  depr_rekisterikayttotiheys
    ADD CONSTRAINT depr_rekisterikayttotiheys_pkey PRIMARY KEY (kayttotiheys);


--
-- Name: depr_rekisterilinkki depr_rekisterilinkki_pkey; Type: CONSTRAINT; Schema: tietok; Owner: tietokt
--

alter table  depr_rekisterilinkki
    ADD CONSTRAINT depr_rekisterilinkki_pkey PRIMARY KEY (linkki);


--
-- Name: depr_rekisterimuokkaaja depr_rekisterimuokkaaja_pkey; Type: CONSTRAINT; Schema: tietok; Owner: tietokt
--

alter table  depr_rekisterimuokkaaja
    ADD CONSTRAINT depr_rekisterimuokkaaja_pkey PRIMARY KEY (rivimuokkaajatunnus);


--
-- Name: depr_rekisteripaivitystiheys depr_rekisteripaivitystiheys_pkey; Type: CONSTRAINT; Schema: tietok; Owner: tietokt
--

alter table  depr_rekisteripaivitystiheys
    ADD CONSTRAINT depr_rekisteripaivitystiheys_pkey PRIMARY KEY (paivitystiheys);


--
-- Name: depr_rekisteripalvelutaso depr_rekisteripalvelutaso_pkey; Type: CONSTRAINT; Schema: tietok; Owner: tietokt
--

alter table  depr_rekisteripalvelutaso
    ADD CONSTRAINT depr_rekisteripalvelutaso_pkey PRIMARY KEY (palvelutaso);


--
-- Name: depr_rekisterisanasto depr_rekisterisanasto_pkey; Type: CONSTRAINT; Schema: tietok; Owner: tietokt
--

alter table  depr_rekisterisanasto
    ADD CONSTRAINT depr_rekisterisanasto_pkey PRIMARY KEY (sana);


--
-- Name: depr_rekisteritietolahde depr_rekisteritietolahde_pkey; Type: CONSTRAINT; Schema: tietok; Owner: tietokt
--

alter table  depr_rekisteritietolahde
    ADD CONSTRAINT depr_rekisteritietolahde_pkey PRIMARY KEY (tietolahde);


--
-- Name: depr_rekisteritila depr_rekisteritila_pkey; Type: CONSTRAINT; Schema: tietok; Owner: tietokt
--

alter table  depr_rekisteritila
    ADD CONSTRAINT depr_rekisteritila_pkey PRIMARY KEY (tila, rivimuokkaajatunnus);


--
-- Name: depr_reklooginentietovarastoo depr_reklooginentietovarastoo_pkey; Type: CONSTRAINT; Schema: tietok; Owner: tietokt
--

alter table  depr_reklooginentietovarastoo
    ADD CONSTRAINT depr_reklooginentietovarastoo_pkey PRIMARY KEY (omistaja);


--
-- Name: depr_reklooginentietovarastos depr_reklooginentietovarastos_pkey; Type: CONSTRAINT; Schema: tietok; Owner: tietokt
--

alter table  depr_reklooginentietovarastos
    ADD CONSTRAINT depr_reklooginentietovarastos_pkey PRIMARY KEY (kayttaja);


--
-- Name: depr_rektiedontoimistustapa depr_rektiedontoimistustapa_pkey; Type: CONSTRAINT; Schema: tietok; Owner: tietokt
--

alter table  depr_rektiedontoimistustapa
    ADD CONSTRAINT depr_rektiedontoimistustapa_pkey PRIMARY KEY (toimitustapa);


--
-- Name: depr_rektietojarjestelmatila depr_rektietojarjestelmatila_pkey; Type: CONSTRAINT; Schema: tietok; Owner: tietokt
--

alter table  depr_rektietojarjestelmatila
    ADD CONSTRAINT depr_rektietojarjestelmatila_pkey PRIMARY KEY (tila);


--
-- Name: depr_rektietokantateknologia depr_rektietokantateknologia_pkey; Type: CONSTRAINT; Schema: tietok; Owner: tietokt
--

alter table  depr_rektietokantateknologia
    ADD CONSTRAINT depr_rektietokantateknologia_pkey PRIMARY KEY (teknologia);


--
-- Name: depr_rektietoryhmaomistaja depr_rektietoryhmaomistaja_pkey; Type: CONSTRAINT; Schema: tietok; Owner: tietokt
--

alter table  depr_rektietoryhmaomistaja
    ADD CONSTRAINT depr_rektietoryhmaomistaja_pkey PRIMARY KEY (omistaja);


--
-- Name: depr_rektietosuojataso depr_rektietosuojataso_pkey; Type: CONSTRAINT; Schema: tietok; Owner: tietokt
--

alter table  depr_rektietosuojataso
    ADD CONSTRAINT depr_rektietosuojataso_pkey PRIMARY KEY (tietosuojataso, rivimuokkaajatunnus);


--
-- Name: depr_rektietovarastoomistaja depr_rektietovarastoomistaja_pkey; Type: CONSTRAINT; Schema: tietok; Owner: tietokt
--

alter table  depr_rektietovarastoomistaja
    ADD CONSTRAINT depr_rektietovarastoomistaja_pkey PRIMARY KEY (kayttaja);


--
-- Name: depr_tietojarjestelmapalvelu depr_tietojarjestelmapalvelu_pkey; Type: CONSTRAINT; Schema: tietok; Owner: tietokt
--

alter table  depr_tietojarjestelmapalvelu
    ADD CONSTRAINT depr_tietojarjestelmapalvelu_pkey PRIMARY KEY (tietojarjestelmapalvelutunnus);


--
-- Name: depr_tietolahde depr_tietolahde_pkey; Type: CONSTRAINT; Schema: tietok; Owner: tietokt
--

alter table  depr_tietolahde
    ADD CONSTRAINT depr_tietolahde_pkey PRIMARY KEY (rivitunnus);


--
-- Name: depr_tietoryhmalahde depr_tietoryhmalahde_pkey; Type: CONSTRAINT; Schema: tietok; Owner: tietokt
--

alter table  depr_tietoryhmalahde
    ADD CONSTRAINT depr_tietoryhmalahde_pkey PRIMARY KEY (rivitunnus);


--
-- Name: depr_tjsalkkufyysinen depr_tjsalkkufyysinen_pkey; Type: CONSTRAINT; Schema: tietok; Owner: tietokt
--

alter table  depr_tjsalkkufyysinen
    ADD CONSTRAINT depr_tjsalkkufyysinen_pkey PRIMARY KEY (rivitunnus);


--
-- Name: fyysinentietovaranto fyysinentietovaranto_pkey; Type: CONSTRAINT; Schema: tietok; Owner: tietokt
--

alter table  fyysinentietovaranto
    ADD CONSTRAINT fyysinentietovaranto_pkey PRIMARY KEY (fyysinentietovarantotunnus);


--
-- Name: fyysinentietovarantohistoria fyysinentietovarantohistoria_pkey; Type: CONSTRAINT; Schema: tietok; Owner: tietokt
--

alter table  fyysinentietovarantohistoria
    ADD CONSTRAINT fyysinentietovarantohistoria_pkey PRIMARY KEY (rivi_id);


--
-- Name: henkilo henkilo_pkey; Type: CONSTRAINT; Schema: tietok; Owner: tietokt
--

alter table  henkilo
    ADD CONSTRAINT henkilo_pkey PRIMARY KEY (tunnus);


--
-- Name: henkilo_temp henkilo_temp_pkey; Type: CONSTRAINT; Schema: tietok; Owner: tietokt
--

alter table  henkilo_temp
    ADD CONSTRAINT henkilo_temp_pkey PRIMARY KEY (tunnus);


--
-- Name: jarjestelma_henkilo_rooli_hist jarjestelma_henkilo_rooli_hist_pkey; Type: CONSTRAINT; Schema: tietok; Owner: tietokt
--

alter table  jarjestelma_henkilo_rooli_hist
    ADD CONSTRAINT jarjestelma_henkilo_rooli_hist_pkey PRIMARY KEY (rivitunnus);


--
-- Name: jarjestelma_henkilo_rooli jarjestelma_henkilo_rooli_pkey; Type: CONSTRAINT; Schema: tietok; Owner: tietokt
--

alter table  jarjestelma_henkilo_rooli
    ADD CONSTRAINT jarjestelma_henkilo_rooli_pkey PRIMARY KEY (henkilo_id, rooli_id, jarjestelma_id);


--
-- Name: jarjestelmalinkkaus jarjestelmalinkkaus_pkey; Type: CONSTRAINT; Schema: tietok; Owner: tietokt
--

alter table  jarjestelmalinkkaus
    ADD CONSTRAINT jarjestelmalinkkaus_pkey PRIMARY KEY (rivitunnus);


--
-- Name: jarjestelmalinkkaushistoria jarjestelmalinkkaushistoria_pkey; Type: CONSTRAINT; Schema: tietok; Owner: tietokt
--

alter table  jarjestelmalinkkaushistoria
    ADD CONSTRAINT jarjestelmalinkkaushistoria_pkey PRIMARY KEY (rivitunnus);


--
-- Name: jarjestelmalooginen jarjestelmalooginen_pkey; Type: CONSTRAINT; Schema: tietok; Owner: tietokt
--

alter table  jarjestelmalooginen
    ADD CONSTRAINT jarjestelmalooginen_pkey PRIMARY KEY (rivitunnus);


--
-- Name: jarjestelmalooginenhistoria jarjestelmalooginenhistoria_pkey; Type: CONSTRAINT; Schema: tietok; Owner: tietokt
--

alter table  jarjestelmalooginenhistoria
    ADD CONSTRAINT jarjestelmalooginenhistoria_pkey PRIMARY KEY (rivi_id);


--
-- Name: jarjestelmatieto_kentat jarjestelmatieto_kentat_pkey; Type: CONSTRAINT; Schema: tietok; Owner: tietokt
--

alter table  jarjestelmatieto_kentat
    ADD CONSTRAINT jarjestelmatieto_kentat_pkey PRIMARY KEY (nimi);


--
-- Name: joinhuomautus joinhuomautus_pkey; Type: CONSTRAINT; Schema: tietok; Owner: tietokt
--

alter table  joinhuomautus
    ADD CONSTRAINT joinhuomautus_pkey PRIMARY KEY (rivitunnus);


--
-- Name: joinhuomautushistoria joinhuomautushistoria_pkey; Type: CONSTRAINT; Schema: tietok; Owner: tietokt
--

alter table  joinhuomautushistoria
    ADD CONSTRAINT joinhuomautushistoria_pkey PRIMARY KEY (rivi_id);


--
-- Name: looginen_kasite_arvo looginen_kasite_arvo_pkey; Type: CONSTRAINT; Schema: tietok; Owner: tietokt
--

alter table  looginen_kasite_arvo
    ADD CONSTRAINT looginen_kasite_arvo_pkey PRIMARY KEY (kasite_wid);


--
-- Name: looginenfyysinenhistoria looginenfyysinenhistoria_pkey; Type: CONSTRAINT; Schema: tietok; Owner: tietokt
--

alter table  looginenfyysinenhistoria
    ADD CONSTRAINT looginenfyysinenhistoria_pkey PRIMARY KEY (rivitunnus);


--
-- Name: looginentietovaranto looginentietovaranto_pkey; Type: CONSTRAINT; Schema: tietok; Owner: tietokt
--

alter table  looginentietovaranto
    ADD CONSTRAINT looginentietovaranto_pkey PRIMARY KEY (looginentietovarantotunnus);


--
-- Name: looginentietovarantofyysinenti looginentietovarantofyysinenti_pkey; Type: CONSTRAINT; Schema: tietok; Owner: tietokt
--

alter table  looginentietovarantofyysinenti
    ADD CONSTRAINT looginentietovarantofyysinenti_pkey PRIMARY KEY (rivitunnus);


--
-- Name: looginentietovarantohistoria looginentietovarantohistoria_pkey; Type: CONSTRAINT; Schema: tietok; Owner: tietokt
--

alter table  looginentietovarantohistoria
    ADD CONSTRAINT looginentietovarantohistoria_pkey PRIMARY KEY (rivi_id);


--
-- Name: organisaatio organisaatio_pkey; Type: CONSTRAINT; Schema: tietok; Owner: tietokt
--

alter table  organisaatio
    ADD CONSTRAINT organisaatio_pkey PRIMARY KEY (organisaatiotunnus);


--
-- Name: organisaatiohistoria organisaatiohistoria_pkey; Type: CONSTRAINT; Schema: tietok; Owner: tietokt
--

alter table  organisaatiohistoria
    ADD CONSTRAINT organisaatiohistoria_pkey PRIMARY KEY (rivi_id);


--
-- Name: paatietoryhma paatietoryhma_pkey; Type: CONSTRAINT; Schema: tietok; Owner: tietokt
--

alter table  paatietoryhma
    ADD CONSTRAINT paatietoryhma_pkey PRIMARY KEY (paatietoryhmatunnus);


--
-- Name: paatietoryhmahistoria paatietoryhmahistoria_pkey; Type: CONSTRAINT; Schema: tietok; Owner: tietokt
--

alter table  paatietoryhmahistoria
    ADD CONSTRAINT paatietoryhmahistoria_pkey PRIMARY KEY (rivi_id);


--
-- Name: palvelu_kasite_arvo palvelu_kasite_arvo_pkey; Type: CONSTRAINT; Schema: tietok; Owner: tietokt
--

alter table  palvelu_kasite_arvo
    ADD CONSTRAINT palvelu_kasite_arvo_pkey PRIMARY KEY (kasite_id);


--
-- Name: palvelu palvelu_pkey; Type: CONSTRAINT; Schema: tietok; Owner: tietokt
--

alter table  palvelu
    ADD CONSTRAINT palvelu_pkey PRIMARY KEY (palvelutunnus);


--
-- Name: palveluhistoria palveluhistoria_pkey; Type: CONSTRAINT; Schema: tietok; Owner: tietokt
--

alter table  palveluhistoria
    ADD CONSTRAINT palveluhistoria_pkey PRIMARY KEY (rivi_id);


--
-- Name: palvelukatalogi_kasite_arvo palvelukatalogi_kasite_arvo_pkey; Type: CONSTRAINT; Schema: tietok; Owner: tietokt
--

alter table  palvelukatalogi_kasite_arvo
    ADD CONSTRAINT palvelukatalogi_kasite_arvo_pkey PRIMARY KEY (kasite_id);


--
-- Name: rooli rooli_pkey; Type: CONSTRAINT; Schema: tietok; Owner: tietokt
--

alter table  rooli
    ADD CONSTRAINT rooli_pkey PRIMARY KEY (tunnus);


--
-- Name: sovellus_henkilo_rooli_hist sovellus_henkilo_rooli_hist_pkey; Type: CONSTRAINT; Schema: tietok; Owner: tietokt
--

alter table  sovellus_henkilo_rooli_hist
    ADD CONSTRAINT sovellus_henkilo_rooli_hist_pkey PRIMARY KEY (rivitunnus);


--
-- Name: sovellus_henkilo_rooli sovellus_henkilo_rooli_pkey; Type: CONSTRAINT; Schema: tietok; Owner: tietokt
--

alter table  sovellus_henkilo_rooli
    ADD CONSTRAINT sovellus_henkilo_rooli_pkey PRIMARY KEY (henkilo_id, rooli_id, sovellus_id);


--
-- Name: sovellus_history sovellus_history_pkey; Type: CONSTRAINT; Schema: tietok; Owner: tietokt
--

alter table  sovellus_history
    ADD CONSTRAINT sovellus_history_pkey PRIMARY KEY (rivitunnus);


--
-- Name: sovellus sovellus_pkey; Type: CONSTRAINT; Schema: tietok; Owner: tietokt
--

alter table  sovellus
    ADD CONSTRAINT sovellus_pkey PRIMARY KEY (tunnus);


--
-- Name: sovellus_tuonti sovellus_tuonti_pkey; Type: CONSTRAINT; Schema: tietok; Owner: tietokt
--

alter table  sovellus_tuonti
    ADD CONSTRAINT sovellus_tuonti_pkey PRIMARY KEY (tuonti_id);


--
-- Name: termilomake termilomake_pkey; Type: CONSTRAINT; Schema: tietok; Owner: tietokt
--

alter table  termilomake
    ADD CONSTRAINT termilomake_pkey PRIMARY KEY (termilomaketunnus);


--
-- Name: termilomakeaskasihistoria termilomakeaskasihistoria_pkey; Type: CONSTRAINT; Schema: tietok; Owner: tietokt
--

alter table  termilomakeaskasihistoria
    ADD CONSTRAINT termilomakeaskasihistoria_pkey PRIMARY KEY (rivi_id);


--
-- Name: termilomakeassoskasite termilomakeassoskasite_pkey; Type: CONSTRAINT; Schema: tietok; Owner: tietokt
--

alter table  termilomakeassoskasite
    ADD CONSTRAINT termilomakeassoskasite_pkey PRIMARY KEY (rivitunnus);


--
-- Name: termilomakehierarkkasite termilomakehierarkkasite_pkey; Type: CONSTRAINT; Schema: tietok; Owner: tietokt
--

alter table  termilomakehierarkkasite
    ADD CONSTRAINT termilomakehierarkkasite_pkey PRIMARY KEY (rivitunnus);


--
-- Name: termilomakehistoria termilomakehistoria_pkey; Type: CONSTRAINT; Schema: tietok; Owner: tietokt
--

alter table  termilomakehistoria
    ADD CONSTRAINT termilomakehistoria_pkey PRIMARY KEY (rivi_id);


--
-- Name: termilomakehkasitehistoria termilomakehkasitehistoria_pkey; Type: CONSTRAINT; Schema: tietok; Owner: tietokt
--

alter table  termilomakehkasitehistoria
    ADD CONSTRAINT termilomakehkasitehistoria_pkey PRIMARY KEY (rivi_id);


--
-- Name: termilomakekoostkasihistoria termilomakekoostkasihistoria_pkey; Type: CONSTRAINT; Schema: tietok; Owner: tietokt
--

alter table  termilomakekoostkasihistoria
    ADD CONSTRAINT termilomakekoostkasihistoria_pkey PRIMARY KEY (rivi_id);


--
-- Name: termilomakekoostkasite termilomakekoostkasite_pkey; Type: CONSTRAINT; Schema: tietok; Owner: tietokt
--

alter table  termilomakekoostkasite
    ADD CONSTRAINT termilomakekoostkasite_pkey PRIMARY KEY (rivitunnus);


--
-- Name: tiesuunnitelma tiesuunnitelma_pkey; Type: CONSTRAINT; Schema: tietok; Owner: tietokt
--

alter table  tiesuunnitelma
    ADD CONSTRAINT tiesuunnitelma_pkey PRIMARY KEY (tiesuunnitelmatunnus);


--
-- Name: tiesuunnitelmahistoria tiesuunnitelmahistoria_pkey; Type: CONSTRAINT; Schema: tietok; Owner: tietokt
--

alter table  tiesuunnitelmahistoria
    ADD CONSTRAINT tiesuunnitelmahistoria_pkey PRIMARY KEY (rivi_id);


--
-- Name: tieto_kasite_arvo tieto_kasite_arvo_pkey; Type: CONSTRAINT; Schema: tietok; Owner: tietokt
--

alter table  tieto_kasite_arvo
    ADD CONSTRAINT tieto_kasite_arvo_pkey PRIMARY KEY (kasite_wid);


--
-- Name: tieto tieto_pkey; Type: CONSTRAINT; Schema: tietok; Owner: tietokt
--

alter table  tieto
    ADD CONSTRAINT tieto_pkey PRIMARY KEY (tietotunnus);


--
-- Name: tietohistoria tietohistoria_pkey; Type: CONSTRAINT; Schema: tietok; Owner: tietokt
--

alter table  tietohistoria
    ADD CONSTRAINT tietohistoria_pkey PRIMARY KEY (rivi_id);


--
-- Name: tietojarjestelma_kasite_arvo tietojarjestelma_kasite_arvo_pkey; Type: CONSTRAINT; Schema: tietok; Owner: tietokt
--

alter table  tietojarjestelma_kasite_arvo
    ADD CONSTRAINT tietojarjestelma_kasite_arvo_pkey PRIMARY KEY (kasite_wid);


--
-- Name: tietojarjestelmapalvelu tietojarjestelmapalvelu_pkey; Type: CONSTRAINT; Schema: tietok; Owner: tietokt
--

alter table  tietojarjestelmapalvelu
    ADD CONSTRAINT tietojarjestelmapalvelu_pkey PRIMARY KEY (tunnus);


--
-- Name: tietojarjestelmasalkku tietojarjestelmasalkku_pkey; Type: CONSTRAINT; Schema: tietok; Owner: tietokt
--

alter table  tietojarjestelmasalkku
    ADD CONSTRAINT tietojarjestelmasalkku_pkey PRIMARY KEY (tietojarjestelmatunnus);


--
-- Name: tietojarjestelmasalkkuhistoria tietojarjestelmasalkkuhistoria_pkey; Type: CONSTRAINT; Schema: tietok; Owner: tietokt
--

alter table  tietojarjestelmasalkkuhistoria
    ADD CONSTRAINT tietojarjestelmasalkkuhistoria_pkey PRIMARY KEY (rivi_id);


--
-- Name: tietojarjpalvelu_kasite_arvo tietojarjpalvelu_kasite_arvo_pkey; Type: CONSTRAINT; Schema: tietok; Owner: tietokt
--

alter table  tietojarjpalvelu_kasite_arvo
    ADD CONSTRAINT tietojarjpalvelu_kasite_arvo_pkey PRIMARY KEY (kasite_id);


--
-- Name: tietojarjpalvelu_tietolaji tietojarjpalvelu_tietolaji_pkey; Type: CONSTRAINT; Schema: tietok; Owner: tietokt
--

alter table  tietojarjpalvelu_tietolaji
    ADD CONSTRAINT tietojarjpalvelu_tietolaji_pkey PRIMARY KEY (rivitunnus);


--
-- Name: tietojarjpalveluhistoria tietojarjpalveluhistoria_pkey; Type: CONSTRAINT; Schema: tietok; Owner: tietokt
--

alter table  tietojarjpalveluhistoria
    ADD CONSTRAINT tietojarjpalveluhistoria_pkey PRIMARY KEY (rivi_id);


--
-- Name: tietojarjpalvelutietohistoria tietojarjpalvelutietohistoria_pkey; Type: CONSTRAINT; Schema: tietok; Owner: tietokt
--

alter table  tietojarjpalvelutietohistoria
    ADD CONSTRAINT tietojarjpalvelutietohistoria_pkey PRIMARY KEY (rivitunnus);


--
-- Name: tietojarjpalvliittyvajarj tietojarjpalvliittyvajarj_pkey; Type: CONSTRAINT; Schema: tietok; Owner: tietokt
--

alter table  tietojarjpalvliittyvajarj
    ADD CONSTRAINT tietojarjpalvliittyvajarj_pkey PRIMARY KEY (rivitunnus);


--
-- Name: tietolooginenhistoria tietolooginenhistoria_pkey; Type: CONSTRAINT; Schema: tietok; Owner: tietokt
--

alter table  tietolooginenhistoria
    ADD CONSTRAINT tietolooginenhistoria_pkey PRIMARY KEY (rivitunnus);


--
-- Name: tietolooginentietovaranto tietolooginentietovaranto_pkey; Type: CONSTRAINT; Schema: tietok; Owner: tietokt
--

alter table  tietolooginentietovaranto
    ADD CONSTRAINT tietolooginentietovaranto_pkey PRIMARY KEY (rivitunnus);


--
-- Name: tietoomaisuus_kasite_arvo tietoomaisuus_kasite_arvo_pkey; Type: CONSTRAINT; Schema: tietok; Owner: tietokt
--

alter table  tietoomaisuus_kasite_arvo
    ADD CONSTRAINT tietoomaisuus_kasite_arvo_pkey PRIMARY KEY (kasite_wid);


--
-- Name: tietoomaisuus tietoomaisuus_pkey; Type: CONSTRAINT; Schema: tietok; Owner: tietokt
--

alter table  tietoomaisuus
    ADD CONSTRAINT tietoomaisuus_pkey PRIMARY KEY (id);


--
-- Name: tietoomaisuushistoria tietoomaisuushistoria_pkey; Type: CONSTRAINT; Schema: tietok; Owner: tietokt
--

alter table  tietoomaisuushistoria
    ADD CONSTRAINT tietoomaisuushistoria_pkey PRIMARY KEY (rivi_id);


--
-- Name: tietoryhma tietoryhma_pkey; Type: CONSTRAINT; Schema: tietok; Owner: tietokt
--

alter table  tietoryhma
    ADD CONSTRAINT tietoryhma_pkey PRIMARY KEY (paatietoryhmatunnus, tietoryhmatunnus);


--
-- Name: tietoryhmahistoria tietoryhmahistoria_pkey; Type: CONSTRAINT; Schema: tietok; Owner: tietokt
--

alter table  tietoryhmahistoria
    ADD CONSTRAINT tietoryhmahistoria_pkey PRIMARY KEY (rivi_id);


--
-- Name: tietoryhmalooginentietovaranto tietoryhmalooginentietovaranto_pkey; Type: CONSTRAINT; Schema: tietok; Owner: tietokt
--

alter table  tietoryhmalooginentietovaranto
    ADD CONSTRAINT tietoryhmalooginentietovaranto_pkey PRIMARY KEY (rivitunnus);


--
-- Name: tietoryhmapaatietohistoria tietoryhmapaatietohistoria_pkey; Type: CONSTRAINT; Schema: tietok; Owner: tietokt
--

alter table  tietoryhmapaatietohistoria
    ADD CONSTRAINT tietoryhmapaatietohistoria_pkey PRIMARY KEY (rivitunnus);


--
-- Name: tietoryhmapaatietoryhma tietoryhmapaatietoryhma_pkey; Type: CONSTRAINT; Schema: tietok; Owner: tietokt
--

alter table  tietoryhmapaatietoryhma
    ADD CONSTRAINT tietoryhmapaatietoryhma_pkey PRIMARY KEY (rivitunnus);


--
-- Name: tietoryhmatietovaranto tietoryhmatietovaranto_pkey; Type: CONSTRAINT; Schema: tietok; Owner: tietokt
--

alter table  tietoryhmatietovaranto
    ADD CONSTRAINT tietoryhmatietovaranto_pkey PRIMARY KEY (rivitunnus);


--
-- Name: tietoryhmatietovarantohistoria tietoryhmatietovarantohistoria_pkey; Type: CONSTRAINT; Schema: tietok; Owner: tietokt
--

alter table  tietoryhmatietovarantohistoria
    ADD CONSTRAINT tietoryhmatietovarantohistoria_pkey PRIMARY KEY (rivi_id);


--
-- Name: tietosuojavastaava tietosuojavastaava_pkey; Type: CONSTRAINT; Schema: tietok; Owner: tietokt
--

alter table  tietosuojavastaava
    ADD CONSTRAINT tietosuojavastaava_pkey PRIMARY KEY (tietosuojavastaavatunnus);


--
-- Name: tietosuojavastaavahistoria tietosuojavastaavahistoria_pkey; Type: CONSTRAINT; Schema: tietok; Owner: tietokt
--

alter table  tietosuojavastaavahistoria
    ADD CONSTRAINT tietosuojavastaavahistoria_pkey PRIMARY KEY (rivi_id);


--
-- Name: tietotietoryhma tietotietoryhma_pkey; Type: CONSTRAINT; Schema: tietok; Owner: tietokt
--

alter table  tietotietoryhma
    ADD CONSTRAINT tietotietoryhma_pkey PRIMARY KEY (rivitunnus);


--
-- Name: tietotietoryhmahistoria tietotietoryhmahistoria_pkey; Type: CONSTRAINT; Schema: tietok; Owner: tietokt
--

alter table  tietotietoryhmahistoria
    ADD CONSTRAINT tietotietoryhmahistoria_pkey PRIMARY KEY (rivitunnus);


--
-- Name: tietovaranto_kasite_arvo tietovaranto_kasite_arvo_pkey; Type: CONSTRAINT; Schema: tietok; Owner: tietokt
--

alter table  tietovaranto_kasite_arvo
    ADD CONSTRAINT tietovaranto_kasite_arvo_pkey PRIMARY KEY (kasite_wid);


--
-- Name: tietovaranto tietovaranto_pkey; Type: CONSTRAINT; Schema: tietok; Owner: tietokt
--

alter table  tietovaranto
    ADD CONSTRAINT tietovaranto_pkey PRIMARY KEY (tietovarantotunnus);


--
-- Name: tietovarantohenkilotietoryhma tietovarantohenkilotietoryhma_pkey; Type: CONSTRAINT; Schema: tietok; Owner: tietokt
--

alter table  tietovarantohenkilotietoryhma
    ADD CONSTRAINT tietovarantohenkilotietoryhma_pkey PRIMARY KEY (rivitunnus);


--
-- Name: tietovarantohistoria tietovarantohistoria_pkey; Type: CONSTRAINT; Schema: tietok; Owner: tietokt
--

alter table  tietovarantohistoria
    ADD CONSTRAINT tietovarantohistoria_pkey PRIMARY KEY (rivi_id);


--
-- Name: tietovarantokasittelynperuste tietovarantokasittelynperuste_pkey; Type: CONSTRAINT; Schema: tietok; Owner: tietokt
--

alter table  tietovarantokasittelynperuste
    ADD CONSTRAINT tietovarantokasittelynperuste_pkey PRIMARY KEY (rivitunnus);


--
-- Name: tietovarantorekisteroityryhma tietovarantorekisteroityryhma_pkey; Type: CONSTRAINT; Schema: tietok; Owner: tietokt
--

alter table  tietovarantorekisteroityryhma
    ADD CONSTRAINT tietovarantorekisteroityryhma_pkey PRIMARY KEY (rivitunnus);


--
-- Name: tietovarantotiedonohjaus tietovarantotiedonohjaus_pkey; Type: CONSTRAINT; Schema: tietok; Owner: tietokt
--

alter table  tietovarantotiedonohjaus
    ADD CONSTRAINT tietovarantotiedonohjaus_pkey PRIMARY KEY (rivitunnus);


--
-- Name: tietovarantoturvatoimenpide tietovarantoturvatoimenpide_pkey; Type: CONSTRAINT; Schema: tietok; Owner: tietokt
--

alter table  tietovarantoturvatoimenpide
    ADD CONSTRAINT tietovarantoturvatoimenpide_pkey PRIMARY KEY (rivitunnus);


--
-- Name: tietovarantovastaanottajaryhma tietovarantovastaanottajaryhma_pkey; Type: CONSTRAINT; Schema: tietok; Owner: tietokt
--

alter table  tietovarantovastaanottajaryhma
    ADD CONSTRAINT tietovarantovastaanottajaryhma_pkey PRIMARY KEY (rivitunnus);


--
-- Name: tietovarantoyhteisrekistpitaja tietovarantoyhteisrekistpitaja_pkey; Type: CONSTRAINT; Schema: tietok; Owner: tietokt
--

alter table  tietovarantoyhteisrekistpitaja
    ADD CONSTRAINT tietovarantoyhteisrekistpitaja_pkey PRIMARY KEY (rivitunnus);


--
-- Name: tietovarantoyllapitomuutaho tietovarantoyllapitomuutaho_pkey; Type: CONSTRAINT; Schema: tietok; Owner: tietokt
--

alter table  tietovarantoyllapitomuutaho
    ADD CONSTRAINT tietovarantoyllapitomuutaho_pkey PRIMARY KEY (rivitunnus);


--
-- Name: toimintaprosessi_kasite_arvo toimintaprosessi_kasite_arvo_pkey; Type: CONSTRAINT; Schema: tietok; Owner: tietokt
--

alter table  toimintaprosessi_kasite_arvo
    ADD CONSTRAINT toimintaprosessi_kasite_arvo_pkey PRIMARY KEY (kasite_wid);


--
-- Name: toimintaprosessi toimintaprosessi_pkey; Type: CONSTRAINT; Schema: tietok; Owner: tietokt
--

alter table  toimintaprosessi
    ADD CONSTRAINT toimintaprosessi_pkey PRIMARY KEY (toimintaprosessitunnus);


--
-- Name: toimintaprosessihistoria toimintaprosessihistoria_pkey; Type: CONSTRAINT; Schema: tietok; Owner: tietokt
--

alter table  toimintaprosessihistoria
    ADD CONSTRAINT toimintaprosessihistoria_pkey PRIMARY KEY (rivi_id);


--
-- Name: toimintaprosessitietovaranto toimintaprosessitietovaranto_pkey; Type: CONSTRAINT; Schema: tietok; Owner: tietokt
--

alter table  toimintaprosessitietovaranto
    ADD CONSTRAINT toimintaprosessitietovaranto_pkey PRIMARY KEY (rivitunnus);


--
-- Name: toimintaprostietovarahistoria toimintaprostietovarahistoria_pkey; Type: CONSTRAINT; Schema: tietok; Owner: tietokt
--

alter table  toimintaprostietovarahistoria
    ADD CONSTRAINT toimintaprostietovarahistoria_pkey PRIMARY KEY (rivi_id);


--
-- Name: yleissuunnitelma yleissuunnitelma_pkey; Type: CONSTRAINT; Schema: tietok; Owner: tietokt
--

alter table  yleissuunnitelma
    ADD CONSTRAINT yleissuunnitelma_pkey PRIMARY KEY (yleissuunnitelmatunnus);


--
-- Name: yleissuunnitelmahistoria yleissuunnitelmahistoria_pkey; Type: CONSTRAINT; Schema: tietok; Owner: tietokt
--

alter table  yleissuunnitelmahistoria
    ADD CONSTRAINT yleissuunnitelmahistoria_pkey PRIMARY KEY (rivi_id);


--
-- Name: sovellus_temp_SOVELLUS_TEMP_UNIQUE; Type: INDEX; Schema: tietok; Owner: tietokt
--

--create unique index(.*);


--
-- PostgreSQL database dump complete
--



