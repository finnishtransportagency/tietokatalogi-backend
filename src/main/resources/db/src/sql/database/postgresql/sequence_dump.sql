--
-- PostgreSQL database dump
--

-- Dumped from database version 12.4
-- Dumped by pg_dump version 12.4

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
-- Name: fyysinentietovaranto_fyysin62; Type: SEQUENCE; Schema: tietok; Owner: -
--

CREATE SEQUENCE tietok.fyysinentietovaranto_fyysin62
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: fyysinentv_id_seq; Type: SEQUENCE; Schema: tietok; Owner: -
--

CREATE SEQUENCE tietok.fyysinentv_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: fyysinentv_seq; Type: SEQUENCE; Schema: tietok; Owner: -
--

CREATE SEQUENCE tietok.fyysinentv_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: fyysinentvhist_id_seq; Type: SEQUENCE; Schema: tietok; Owner: -
--

CREATE SEQUENCE tietok.fyysinentvhist_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: fyysinentvhist_seq; Type: SEQUENCE; Schema: tietok; Owner: -
--

CREATE SEQUENCE tietok.fyysinentvhist_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: henkilo_id_seq; Type: SEQUENCE; Schema: tietok; Owner: -
--

CREATE SEQUENCE tietok.henkilo_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: henkilo_seq; Type: SEQUENCE; Schema: tietok; Owner: -
--

CREATE SEQUENCE tietok.henkilo_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: henkilo_temp_id_seq; Type: SEQUENCE; Schema: tietok; Owner: -
--

CREATE SEQUENCE tietok.henkilo_temp_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: henkilo_temp_seq; Type: SEQUENCE; Schema: tietok; Owner: -
--

CREATE SEQUENCE tietok.henkilo_temp_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: jarjestelma_id_seq; Type: SEQUENCE; Schema: tietok; Owner: -
--

CREATE SEQUENCE tietok.jarjestelma_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: jarjestelma_seq; Type: SEQUENCE; Schema: tietok; Owner: -
--

CREATE SEQUENCE tietok.jarjestelma_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: jarjestelmahist_id_seq; Type: SEQUENCE; Schema: tietok; Owner: -
--

CREATE SEQUENCE tietok.jarjestelmahist_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: jarjestelmahist_seq; Type: SEQUENCE; Schema: tietok; Owner: -
--

CREATE SEQUENCE tietok.jarjestelmahist_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: jarjestelmalooginen_id_seq; Type: SEQUENCE; Schema: tietok; Owner: -
--

CREATE SEQUENCE tietok.jarjestelmalooginen_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: jarjestelmalooginenhist_id_seq; Type: SEQUENCE; Schema: tietok; Owner: -
--

CREATE SEQUENCE tietok.jarjestelmalooginenhist_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: jarjhenkroolihist_id_seq; Type: SEQUENCE; Schema: tietok; Owner: -
--

CREATE SEQUENCE tietok.jarjhenkroolihist_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: jarjhenkroolihist_seq; Type: SEQUENCE; Schema: tietok; Owner: -
--

CREATE SEQUENCE tietok.jarjhenkroolihist_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: joinhuomautus_id_seq; Type: SEQUENCE; Schema: tietok; Owner: -
--

CREATE SEQUENCE tietok.joinhuomautus_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: joinhuomautushist_id_seq; Type: SEQUENCE; Schema: tietok; Owner: -
--

CREATE SEQUENCE tietok.joinhuomautushist_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: joinjarjestelmalinkkaus_id_seq; Type: SEQUENCE; Schema: tietok; Owner: -
--

CREATE SEQUENCE tietok.joinjarjestelmalinkkaus_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: joinjarjestelmalinkkaus_seq; Type: SEQUENCE; Schema: tietok; Owner: -
--

CREATE SEQUENCE tietok.joinjarjestelmalinkkaus_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: joinjarjhenkrooli_id_seq; Type: SEQUENCE; Schema: tietok; Owner: -
--

CREATE SEQUENCE tietok.joinjarjhenkrooli_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: joinjarjhenkrooli_seq; Type: SEQUENCE; Schema: tietok; Owner: -
--

CREATE SEQUENCE tietok.joinjarjhenkrooli_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: joinjlinkhist_id_seq; Type: SEQUENCE; Schema: tietok; Owner: -
--

CREATE SEQUENCE tietok.joinjlinkhist_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: joinjlinkhist_seq; Type: SEQUENCE; Schema: tietok; Owner: -
--

CREATE SEQUENCE tietok.joinjlinkhist_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: joinloogfyyshistoria_id_seq; Type: SEQUENCE; Schema: tietok; Owner: -
--

CREATE SEQUENCE tietok.joinloogfyyshistoria_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: joinloogfyyshistoria_seq; Type: SEQUENCE; Schema: tietok; Owner: -
--

CREATE SEQUENCE tietok.joinloogfyyshistoria_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: joinlooginenfyysinen_id_seq; Type: SEQUENCE; Schema: tietok; Owner: -
--

CREATE SEQUENCE tietok.joinlooginenfyysinen_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: joinlooginenfyysinen_seq; Type: SEQUENCE; Schema: tietok; Owner: -
--

CREATE SEQUENCE tietok.joinlooginenfyysinen_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: joinsovellushenkrooli_id_seq; Type: SEQUENCE; Schema: tietok; Owner: -
--

CREATE SEQUENCE tietok.joinsovellushenkrooli_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: joinsovellushenkrooli_seq; Type: SEQUENCE; Schema: tietok; Owner: -
--

CREATE SEQUENCE tietok.joinsovellushenkrooli_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: jointietolajilooginen_id_seq; Type: SEQUENCE; Schema: tietok; Owner: -
--

CREATE SEQUENCE tietok.jointietolajilooginen_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: jointietolajilooginen_seq; Type: SEQUENCE; Schema: tietok; Owner: -
--

CREATE SEQUENCE tietok.jointietolajilooginen_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: jointietolajitietoryhma_id_seq; Type: SEQUENCE; Schema: tietok; Owner: -
--

CREATE SEQUENCE tietok.jointietolajitietoryhma_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: jointietolajitietoryhma_seq; Type: SEQUENCE; Schema: tietok; Owner: -
--

CREATE SEQUENCE tietok.jointietolajitietoryhma_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: jointietolooghistoria_id_seq; Type: SEQUENCE; Schema: tietok; Owner: -
--

CREATE SEQUENCE tietok.jointietolooghistoria_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: jointietolooghistoria_seq; Type: SEQUENCE; Schema: tietok; Owner: -
--

CREATE SEQUENCE tietok.jointietolooghistoria_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: jointietorpaatietor_id_seq; Type: SEQUENCE; Schema: tietok; Owner: -
--

CREATE SEQUENCE tietok.jointietorpaatietor_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: jointietorpaatietor_seq; Type: SEQUENCE; Schema: tietok; Owner: -
--

CREATE SEQUENCE tietok.jointietorpaatietor_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: jtietorpaatietorhist_id_seq; Type: SEQUENCE; Schema: tietok; Owner: -
--

CREATE SEQUENCE tietok.jtietorpaatietorhist_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: jtietorpaatietorhist_seq; Type: SEQUENCE; Schema: tietok; Owner: -
--

CREATE SEQUENCE tietok.jtietorpaatietorhist_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: jtietotietoryhmahist_id_seq; Type: SEQUENCE; Schema: tietok; Owner: -
--

CREATE SEQUENCE tietok.jtietotietoryhmahist_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: jtietotietoryhmahist_seq; Type: SEQUENCE; Schema: tietok; Owner: -
--

CREATE SEQUENCE tietok.jtietotietoryhmahist_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: looginentietovaranto_loogin742; Type: SEQUENCE; Schema: tietok; Owner: -
--

CREATE SEQUENCE tietok.looginentietovaranto_loogin742
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: looginentietovarantofyysine427; Type: SEQUENCE; Schema: tietok; Owner: -
--

CREATE SEQUENCE tietok.looginentietovarantofyysine427
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: looginentv_id_seq; Type: SEQUENCE; Schema: tietok; Owner: -
--

CREATE SEQUENCE tietok.looginentv_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: looginentv_seq; Type: SEQUENCE; Schema: tietok; Owner: -
--

CREATE SEQUENCE tietok.looginentv_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: looginentvhist_id_seq; Type: SEQUENCE; Schema: tietok; Owner: -
--

CREATE SEQUENCE tietok.looginentvhist_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: looginentvhist_seq; Type: SEQUENCE; Schema: tietok; Owner: -
--

CREATE SEQUENCE tietok.looginentvhist_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: organisaatio_id_seq; Type: SEQUENCE; Schema: tietok; Owner: -
--

CREATE SEQUENCE tietok.organisaatio_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: organisaatiohistoria_id_seq; Type: SEQUENCE; Schema: tietok; Owner: -
--

CREATE SEQUENCE tietok.organisaatiohistoria_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: paatietoryhma_id_seq; Type: SEQUENCE; Schema: tietok; Owner: -
--

CREATE SEQUENCE tietok.paatietoryhma_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: paatietoryhma_paatietoryhma612; Type: SEQUENCE; Schema: tietok; Owner: -
--

CREATE SEQUENCE tietok.paatietoryhma_paatietoryhma612
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: paatietoryhma_seq; Type: SEQUENCE; Schema: tietok; Owner: -
--

CREATE SEQUENCE tietok.paatietoryhma_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: paatietoryhmahist_id_seq; Type: SEQUENCE; Schema: tietok; Owner: -
--

CREATE SEQUENCE tietok.paatietoryhmahist_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: paatietoryhmahist_seq; Type: SEQUENCE; Schema: tietok; Owner: -
--

CREATE SEQUENCE tietok.paatietoryhmahist_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: paatietoryhmalahde_rivitunn376; Type: SEQUENCE; Schema: tietok; Owner: -
--

CREATE SEQUENCE tietok.paatietoryhmalahde_rivitunn376
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: paatietoryhmalahde_rivitunn377; Type: SEQUENCE; Schema: tietok; Owner: -
--

CREATE SEQUENCE tietok.paatietoryhmalahde_rivitunn377
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: paatietoryhmalahde_rivitunn379; Type: SEQUENCE; Schema: tietok; Owner: -
--

CREATE SEQUENCE tietok.paatietoryhmalahde_rivitunn379
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: paatietoryhmalahde_rivitunn381; Type: SEQUENCE; Schema: tietok; Owner: -
--

CREATE SEQUENCE tietok.paatietoryhmalahde_rivitunn381
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: paatietoryhmalahde_rivitunn383; Type: SEQUENCE; Schema: tietok; Owner: -
--

CREATE SEQUENCE tietok.paatietoryhmalahde_rivitunn383
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: paatietoryhmalahde_rivitunn574; Type: SEQUENCE; Schema: tietok; Owner: -
--

CREATE SEQUENCE tietok.paatietoryhmalahde_rivitunn574
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: palvelu_id_seq; Type: SEQUENCE; Schema: tietok; Owner: -
--

CREATE SEQUENCE tietok.palvelu_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: palvelu_seq; Type: SEQUENCE; Schema: tietok; Owner: -
--

CREATE SEQUENCE tietok.palvelu_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: palveluhist_id_seq; Type: SEQUENCE; Schema: tietok; Owner: -
--

CREATE SEQUENCE tietok.palveluhist_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: palveluhist_seq; Type: SEQUENCE; Schema: tietok; Owner: -
--

CREATE SEQUENCE tietok.palveluhist_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: rekisterijulkaisu_rivitunnu106; Type: SEQUENCE; Schema: tietok; Owner: -
--

CREATE SEQUENCE tietok.rekisterijulkaisu_rivitunnu106
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: rekisterikayttotiheys_rivit326; Type: SEQUENCE; Schema: tietok; Owner: -
--

CREATE SEQUENCE tietok.rekisterikayttotiheys_rivit326
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: rekisterilinkki_rivitunnus_seq; Type: SEQUENCE; Schema: tietok; Owner: -
--

CREATE SEQUENCE tietok.rekisterilinkki_rivitunnus_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: rekisterilooginentietovaras113; Type: SEQUENCE; Schema: tietok; Owner: -
--

CREATE SEQUENCE tietok.rekisterilooginentietovaras113
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: rekisterilooginentietovaras525; Type: SEQUENCE; Schema: tietok; Owner: -
--

CREATE SEQUENCE tietok.rekisterilooginentietovaras525
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: rekisteripalvelutaso_rivitu222; Type: SEQUENCE; Schema: tietok; Owner: -
--

CREATE SEQUENCE tietok.rekisteripalvelutaso_rivitu222
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: rekisteritietojarjestelmati301; Type: SEQUENCE; Schema: tietok; Owner: -
--

CREATE SEQUENCE tietok.rekisteritietojarjestelmati301
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: rekisteritietokantateknolog539; Type: SEQUENCE; Schema: tietok; Owner: -
--

CREATE SEQUENCE tietok.rekisteritietokantateknolog539
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: rekisteritietolahde_rivitun386; Type: SEQUENCE; Schema: tietok; Owner: -
--

CREATE SEQUENCE tietok.rekisteritietolahde_rivitun386
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: rekisteritietolahde_rivitun388; Type: SEQUENCE; Schema: tietok; Owner: -
--

CREATE SEQUENCE tietok.rekisteritietolahde_rivitun388
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: rekisteritietolahde_rivitun389; Type: SEQUENCE; Schema: tietok; Owner: -
--

CREATE SEQUENCE tietok.rekisteritietolahde_rivitun389
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: rekisteritietoryhmaomistaja952; Type: SEQUENCE; Schema: tietok; Owner: -
--

CREATE SEQUENCE tietok.rekisteritietoryhmaomistaja952
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: rekisteritietovarastoomista937; Type: SEQUENCE; Schema: tietok; Owner: -
--

CREATE SEQUENCE tietok.rekisteritietovarastoomista937
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: rekisteritila_rivitunnus_seq; Type: SEQUENCE; Schema: tietok; Owner: -
--

CREATE SEQUENCE tietok.rekisteritila_rivitunnus_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: rooli_id_seq; Type: SEQUENCE; Schema: tietok; Owner: -
--

CREATE SEQUENCE tietok.rooli_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: rooli_seq; Type: SEQUENCE; Schema: tietok; Owner: -
--

CREATE SEQUENCE tietok.rooli_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: sovellus_hist_id_seq; Type: SEQUENCE; Schema: tietok; Owner: -
--

CREATE SEQUENCE tietok.sovellus_hist_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: sovellus_hist_seq; Type: SEQUENCE; Schema: tietok; Owner: -
--

CREATE SEQUENCE tietok.sovellus_hist_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: sovellus_id_seq; Type: SEQUENCE; Schema: tietok; Owner: -
--

CREATE SEQUENCE tietok.sovellus_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: sovellus_seq; Type: SEQUENCE; Schema: tietok; Owner: -
--

CREATE SEQUENCE tietok.sovellus_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: sovellus_tuonti_id_seq; Type: SEQUENCE; Schema: tietok; Owner: -
--

CREATE SEQUENCE tietok.sovellus_tuonti_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: sovhenkroolihist_id_seq; Type: SEQUENCE; Schema: tietok; Owner: -
--

CREATE SEQUENCE tietok.sovhenkroolihist_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: sovhenkroolihist_seq; Type: SEQUENCE; Schema: tietok; Owner: -
--

CREATE SEQUENCE tietok.sovhenkroolihist_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: termilomake_id_seq; Type: SEQUENCE; Schema: tietok; Owner: -
--

CREATE SEQUENCE tietok.termilomake_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: termilomakeaskahistoria_id_seq; Type: SEQUENCE; Schema: tietok; Owner: -
--

CREATE SEQUENCE tietok.termilomakeaskahistoria_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: termilomakeassoskasite_id_seq; Type: SEQUENCE; Schema: tietok; Owner: -
--

CREATE SEQUENCE tietok.termilomakeassoskasite_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: termilomakehierkasite_id_seq; Type: SEQUENCE; Schema: tietok; Owner: -
--

CREATE SEQUENCE tietok.termilomakehierkasite_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: termilomakehierkhist_id_seq; Type: SEQUENCE; Schema: tietok; Owner: -
--

CREATE SEQUENCE tietok.termilomakehierkhist_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: termilomakehist_id_seq; Type: SEQUENCE; Schema: tietok; Owner: -
--

CREATE SEQUENCE tietok.termilomakehist_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: termilomakekoostkashist_id_seq; Type: SEQUENCE; Schema: tietok; Owner: -
--

CREATE SEQUENCE tietok.termilomakekoostkashist_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: termilomakekoostkasite_id_seq; Type: SEQUENCE; Schema: tietok; Owner: -
--

CREATE SEQUENCE tietok.termilomakekoostkasite_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: tieto_tietotunnus_seq_1; Type: SEQUENCE; Schema: tietok; Owner: -
--

CREATE SEQUENCE tietok.tieto_tietotunnus_seq_1
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: tietojarjestelmapalvelu_id_seq; Type: SEQUENCE; Schema: tietok; Owner: -
--

CREATE SEQUENCE tietok.tietojarjestelmapalvelu_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: tietojarjestelmapalvelu_tie872; Type: SEQUENCE; Schema: tietok; Owner: -
--

CREATE SEQUENCE tietok.tietojarjestelmapalvelu_tie872
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: tietojarjestelmasalkku_tiet311; Type: SEQUENCE; Schema: tietok; Owner: -
--

CREATE SEQUENCE tietok.tietojarjestelmasalkku_tiet311
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: tietojarjestelmasalkkufyysi263; Type: SEQUENCE; Schema: tietok; Owner: -
--

CREATE SEQUENCE tietok.tietojarjestelmasalkkufyysi263
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: tietojarjpalveluhist_id_seq; Type: SEQUENCE; Schema: tietok; Owner: -
--

CREATE SEQUENCE tietok.tietojarjpalveluhist_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: tietojarjpalvelutieto_id_seq; Type: SEQUENCE; Schema: tietok; Owner: -
--

CREATE SEQUENCE tietok.tietojarjpalvelutieto_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: tietojarjpalvliitjarj_id_seq; Type: SEQUENCE; Schema: tietok; Owner: -
--

CREATE SEQUENCE tietok.tietojarjpalvliitjarj_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: tietojarjpalvtietohist_id_seq; Type: SEQUENCE; Schema: tietok; Owner: -
--

CREATE SEQUENCE tietok.tietojarjpalvtietohist_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: tietolahde_rivitunnus_seq; Type: SEQUENCE; Schema: tietok; Owner: -
--

CREATE SEQUENCE tietok.tietolahde_rivitunnus_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: tietolaji_id_seq; Type: SEQUENCE; Schema: tietok; Owner: -
--

CREATE SEQUENCE tietok.tietolaji_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: tietolaji_seq; Type: SEQUENCE; Schema: tietok; Owner: -
--

CREATE SEQUENCE tietok.tietolaji_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: tietolajihist_id_seq; Type: SEQUENCE; Schema: tietok; Owner: -
--

CREATE SEQUENCE tietok.tietolajihist_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: tietolajihist_seq; Type: SEQUENCE; Schema: tietok; Owner: -
--

CREATE SEQUENCE tietok.tietolajihist_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: tietoomaisuus_id_seq; Type: SEQUENCE; Schema: tietok; Owner: -
--

CREATE SEQUENCE tietok.tietoomaisuus_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: tietoomaisuushist_id_seq; Type: SEQUENCE; Schema: tietok; Owner: -
--

CREATE SEQUENCE tietok.tietoomaisuushist_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: tietoryhma_id_seq; Type: SEQUENCE; Schema: tietok; Owner: -
--

CREATE SEQUENCE tietok.tietoryhma_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: tietoryhma_seq; Type: SEQUENCE; Schema: tietok; Owner: -
--

CREATE SEQUENCE tietok.tietoryhma_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: tietoryhma_tietoryhmatunnus830; Type: SEQUENCE; Schema: tietok; Owner: -
--

CREATE SEQUENCE tietok.tietoryhma_tietoryhmatunnus830
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: tietoryhmahist_id_seq; Type: SEQUENCE; Schema: tietok; Owner: -
--

CREATE SEQUENCE tietok.tietoryhmahist_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: tietoryhmahist_seq; Type: SEQUENCE; Schema: tietok; Owner: -
--

CREATE SEQUENCE tietok.tietoryhmahist_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: tietoryhmalooginentietovara529; Type: SEQUENCE; Schema: tietok; Owner: -
--

CREATE SEQUENCE tietok.tietoryhmalooginentietovara529
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: tietoryhmatietovarahist_id_seq; Type: SEQUENCE; Schema: tietok; Owner: -
--

CREATE SEQUENCE tietok.tietoryhmatietovarahist_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: tietoryhmatietovaranto_id_seq; Type: SEQUENCE; Schema: tietok; Owner: -
--

CREATE SEQUENCE tietok.tietoryhmatietovaranto_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: tietosuojavastaava_id_seq; Type: SEQUENCE; Schema: tietok; Owner: -
--

CREATE SEQUENCE tietok.tietosuojavastaava_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: tietosuojavastaavahist_id_seq; Type: SEQUENCE; Schema: tietok; Owner: -
--

CREATE SEQUENCE tietok.tietosuojavastaavahist_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: tietovarahenktietoryhma_id_seq; Type: SEQUENCE; Schema: tietok; Owner: -
--

CREATE SEQUENCE tietok.tietovarahenktietoryhma_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: tietovarakasitperushist_id_seq; Type: SEQUENCE; Schema: tietok; Owner: -
--

CREATE SEQUENCE tietok.tietovarakasitperushist_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: tietovaranto_id_seq; Type: SEQUENCE; Schema: tietok; Owner: -
--

CREATE SEQUENCE tietok.tietovaranto_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: tietovarantohist_id_seq; Type: SEQUENCE; Schema: tietok; Owner: -
--

CREATE SEQUENCE tietok.tietovarantohist_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: tietovarantorekistryhma_id_seq; Type: SEQUENCE; Schema: tietok; Owner: -
--

CREATE SEQUENCE tietok.tietovarantorekistryhma_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: tietovarantrekryhmahist_id_seq; Type: SEQUENCE; Schema: tietok; Owner: -
--

CREATE SEQUENCE tietok.tietovarantrekryhmahist_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: tietovaratiedonohjshist_id_seq; Type: SEQUENCE; Schema: tietok; Owner: -
--

CREATE SEQUENCE tietok.tietovaratiedonohjshist_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: tietovaravastaryhmahist_id_seq; Type: SEQUENCE; Schema: tietok; Owner: -
--

CREATE SEQUENCE tietok.tietovaravastaryhmahist_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: tietovarayhtrekpitahist_id_seq; Type: SEQUENCE; Schema: tietok; Owner: -
--

CREATE SEQUENCE tietok.tietovarayhtrekpitahist_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: tietovarayllapttahohist_id_seq; Type: SEQUENCE; Schema: tietok; Owner: -
--

CREATE SEQUENCE tietok.tietovarayllapttahohist_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: tietovarhentietryhmhist_id_seq; Type: SEQUENCE; Schema: tietok; Owner: -
--

CREATE SEQUENCE tietok.tietovarhentietryhmhist_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: tietovarkasittelperuste_id_seq; Type: SEQUENCE; Schema: tietok; Owner: -
--

CREATE SEQUENCE tietok.tietovarkasittelperuste_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: tietovartiedonohjaus_id_seq; Type: SEQUENCE; Schema: tietok; Owner: -
--

CREATE SEQUENCE tietok.tietovartiedonohjaus_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: tietovarturvatoimenpide_id_seq; Type: SEQUENCE; Schema: tietok; Owner: -
--

CREATE SEQUENCE tietok.tietovarturvatoimenpide_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: tietovarturvtoimpidhist_id_seq; Type: SEQUENCE; Schema: tietok; Owner: -
--

CREATE SEQUENCE tietok.tietovarturvtoimpidhist_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: tietovarvastaanottryhma_id_seq; Type: SEQUENCE; Schema: tietok; Owner: -
--

CREATE SEQUENCE tietok.tietovarvastaanottryhma_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: tietovaryhteisrekpitaja_id_seq; Type: SEQUENCE; Schema: tietok; Owner: -
--

CREATE SEQUENCE tietok.tietovaryhteisrekpitaja_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: tietovaryllapitomuutaho_id_seq; Type: SEQUENCE; Schema: tietok; Owner: -
--

CREATE SEQUENCE tietok.tietovaryllapitomuutaho_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: tjpliittyvajarjhist_id_seq; Type: SEQUENCE; Schema: tietok; Owner: -
--

CREATE SEQUENCE tietok.tjpliittyvajarjhist_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: toimintaprosessi_id_seq; Type: SEQUENCE; Schema: tietok; Owner: -
--

CREATE SEQUENCE tietok.toimintaprosessi_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: toimintaprosessihist_id_seq; Type: SEQUENCE; Schema: tietok; Owner: -
--

CREATE SEQUENCE tietok.toimintaprosessihist_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: toimintaprostietovahist_id_seq; Type: SEQUENCE; Schema: tietok; Owner: -
--

CREATE SEQUENCE tietok.toimintaprostietovahist_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: toimintaprostietovarant_id_seq; Type: SEQUENCE; Schema: tietok; Owner: -
--

CREATE SEQUENCE tietok.toimintaprostietovarant_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: fyysinentietovaranto_fyysin62; Type: SEQUENCE SET; Schema: tietok; Owner: -
--

SELECT pg_catalog.setval('tietok.fyysinentietovaranto_fyysin62', 1, false);


--
-- Name: fyysinentv_id_seq; Type: SEQUENCE SET; Schema: tietok; Owner: -
--

SELECT pg_catalog.setval('tietok.fyysinentv_id_seq', 1, false);


--
-- Name: fyysinentv_seq; Type: SEQUENCE SET; Schema: tietok; Owner: -
--

SELECT pg_catalog.setval('tietok.fyysinentv_seq', 1, false);


--
-- Name: fyysinentvhist_id_seq; Type: SEQUENCE SET; Schema: tietok; Owner: -
--

SELECT pg_catalog.setval('tietok.fyysinentvhist_id_seq', 1, false);


--
-- Name: fyysinentvhist_seq; Type: SEQUENCE SET; Schema: tietok; Owner: -
--

SELECT pg_catalog.setval('tietok.fyysinentvhist_seq', 1, false);


--
-- Name: henkilo_id_seq; Type: SEQUENCE SET; Schema: tietok; Owner: -
--

SELECT pg_catalog.setval('tietok.henkilo_id_seq', 1, false);


--
-- Name: henkilo_seq; Type: SEQUENCE SET; Schema: tietok; Owner: -
--

SELECT pg_catalog.setval('tietok.henkilo_seq', 1, false);


--
-- Name: henkilo_temp_id_seq; Type: SEQUENCE SET; Schema: tietok; Owner: -
--

SELECT pg_catalog.setval('tietok.henkilo_temp_id_seq', 1, false);


--
-- Name: henkilo_temp_seq; Type: SEQUENCE SET; Schema: tietok; Owner: -
--

SELECT pg_catalog.setval('tietok.henkilo_temp_seq', 1, false);


--
-- Name: jarjestelma_id_seq; Type: SEQUENCE SET; Schema: tietok; Owner: -
--

SELECT pg_catalog.setval('tietok.jarjestelma_id_seq', 1, false);


--
-- Name: jarjestelma_seq; Type: SEQUENCE SET; Schema: tietok; Owner: -
--

SELECT pg_catalog.setval('tietok.jarjestelma_seq', 1, false);


--
-- Name: jarjestelmahist_id_seq; Type: SEQUENCE SET; Schema: tietok; Owner: -
--

SELECT pg_catalog.setval('tietok.jarjestelmahist_id_seq', 1, false);


--
-- Name: jarjestelmahist_seq; Type: SEQUENCE SET; Schema: tietok; Owner: -
--

SELECT pg_catalog.setval('tietok.jarjestelmahist_seq', 1, false);


--
-- Name: jarjestelmalooginen_id_seq; Type: SEQUENCE SET; Schema: tietok; Owner: -
--

SELECT pg_catalog.setval('tietok.jarjestelmalooginen_id_seq', 1, false);


--
-- Name: jarjestelmalooginenhist_id_seq; Type: SEQUENCE SET; Schema: tietok; Owner: -
--

SELECT pg_catalog.setval('tietok.jarjestelmalooginenhist_id_seq', 1, false);


--
-- Name: jarjhenkroolihist_id_seq; Type: SEQUENCE SET; Schema: tietok; Owner: -
--

SELECT pg_catalog.setval('tietok.jarjhenkroolihist_id_seq', 1, false);


--
-- Name: jarjhenkroolihist_seq; Type: SEQUENCE SET; Schema: tietok; Owner: -
--

SELECT pg_catalog.setval('tietok.jarjhenkroolihist_seq', 1, false);


--
-- Name: joinhuomautus_id_seq; Type: SEQUENCE SET; Schema: tietok; Owner: -
--

SELECT pg_catalog.setval('tietok.joinhuomautus_id_seq', 1, false);


--
-- Name: joinhuomautushist_id_seq; Type: SEQUENCE SET; Schema: tietok; Owner: -
--

SELECT pg_catalog.setval('tietok.joinhuomautushist_id_seq', 1, false);


--
-- Name: joinjarjestelmalinkkaus_id_seq; Type: SEQUENCE SET; Schema: tietok; Owner: -
--

SELECT pg_catalog.setval('tietok.joinjarjestelmalinkkaus_id_seq', 1, false);


--
-- Name: joinjarjestelmalinkkaus_seq; Type: SEQUENCE SET; Schema: tietok; Owner: -
--

SELECT pg_catalog.setval('tietok.joinjarjestelmalinkkaus_seq', 1, false);


--
-- Name: joinjarjhenkrooli_id_seq; Type: SEQUENCE SET; Schema: tietok; Owner: -
--

SELECT pg_catalog.setval('tietok.joinjarjhenkrooli_id_seq', 1, false);


--
-- Name: joinjarjhenkrooli_seq; Type: SEQUENCE SET; Schema: tietok; Owner: -
--

SELECT pg_catalog.setval('tietok.joinjarjhenkrooli_seq', 1, false);


--
-- Name: joinjlinkhist_id_seq; Type: SEQUENCE SET; Schema: tietok; Owner: -
--

SELECT pg_catalog.setval('tietok.joinjlinkhist_id_seq', 1, false);


--
-- Name: joinjlinkhist_seq; Type: SEQUENCE SET; Schema: tietok; Owner: -
--

SELECT pg_catalog.setval('tietok.joinjlinkhist_seq', 1, false);


--
-- Name: joinloogfyyshistoria_id_seq; Type: SEQUENCE SET; Schema: tietok; Owner: -
--

SELECT pg_catalog.setval('tietok.joinloogfyyshistoria_id_seq', 1, false);


--
-- Name: joinloogfyyshistoria_seq; Type: SEQUENCE SET; Schema: tietok; Owner: -
--

SELECT pg_catalog.setval('tietok.joinloogfyyshistoria_seq', 1, false);


--
-- Name: joinlooginenfyysinen_id_seq; Type: SEQUENCE SET; Schema: tietok; Owner: -
--

SELECT pg_catalog.setval('tietok.joinlooginenfyysinen_id_seq', 1, false);


--
-- Name: joinlooginenfyysinen_seq; Type: SEQUENCE SET; Schema: tietok; Owner: -
--

SELECT pg_catalog.setval('tietok.joinlooginenfyysinen_seq', 1, false);


--
-- Name: joinsovellushenkrooli_id_seq; Type: SEQUENCE SET; Schema: tietok; Owner: -
--

SELECT pg_catalog.setval('tietok.joinsovellushenkrooli_id_seq', 1, false);


--
-- Name: joinsovellushenkrooli_seq; Type: SEQUENCE SET; Schema: tietok; Owner: -
--

SELECT pg_catalog.setval('tietok.joinsovellushenkrooli_seq', 1, false);


--
-- Name: jointietolajilooginen_id_seq; Type: SEQUENCE SET; Schema: tietok; Owner: -
--

SELECT pg_catalog.setval('tietok.jointietolajilooginen_id_seq', 1, false);


--
-- Name: jointietolajilooginen_seq; Type: SEQUENCE SET; Schema: tietok; Owner: -
--

SELECT pg_catalog.setval('tietok.jointietolajilooginen_seq', 1, false);


--
-- Name: jointietolajitietoryhma_id_seq; Type: SEQUENCE SET; Schema: tietok; Owner: -
--

SELECT pg_catalog.setval('tietok.jointietolajitietoryhma_id_seq', 1, false);


--
-- Name: jointietolajitietoryhma_seq; Type: SEQUENCE SET; Schema: tietok; Owner: -
--

SELECT pg_catalog.setval('tietok.jointietolajitietoryhma_seq', 1, false);


--
-- Name: jointietolooghistoria_id_seq; Type: SEQUENCE SET; Schema: tietok; Owner: -
--

SELECT pg_catalog.setval('tietok.jointietolooghistoria_id_seq', 1, false);


--
-- Name: jointietolooghistoria_seq; Type: SEQUENCE SET; Schema: tietok; Owner: -
--

SELECT pg_catalog.setval('tietok.jointietolooghistoria_seq', 1, false);


--
-- Name: jointietorpaatietor_id_seq; Type: SEQUENCE SET; Schema: tietok; Owner: -
--

SELECT pg_catalog.setval('tietok.jointietorpaatietor_id_seq', 1, false);


--
-- Name: jointietorpaatietor_seq; Type: SEQUENCE SET; Schema: tietok; Owner: -
--

SELECT pg_catalog.setval('tietok.jointietorpaatietor_seq', 1, false);


--
-- Name: jtietorpaatietorhist_id_seq; Type: SEQUENCE SET; Schema: tietok; Owner: -
--

SELECT pg_catalog.setval('tietok.jtietorpaatietorhist_id_seq', 1, false);


--
-- Name: jtietorpaatietorhist_seq; Type: SEQUENCE SET; Schema: tietok; Owner: -
--

SELECT pg_catalog.setval('tietok.jtietorpaatietorhist_seq', 1, false);


--
-- Name: jtietotietoryhmahist_id_seq; Type: SEQUENCE SET; Schema: tietok; Owner: -
--

SELECT pg_catalog.setval('tietok.jtietotietoryhmahist_id_seq', 1, false);


--
-- Name: jtietotietoryhmahist_seq; Type: SEQUENCE SET; Schema: tietok; Owner: -
--

SELECT pg_catalog.setval('tietok.jtietotietoryhmahist_seq', 1, false);


--
-- Name: looginentietovaranto_loogin742; Type: SEQUENCE SET; Schema: tietok; Owner: -
--

SELECT pg_catalog.setval('tietok.looginentietovaranto_loogin742', 1, false);


--
-- Name: looginentietovarantofyysine427; Type: SEQUENCE SET; Schema: tietok; Owner: -
--

SELECT pg_catalog.setval('tietok.looginentietovarantofyysine427', 1, false);


--
-- Name: looginentv_id_seq; Type: SEQUENCE SET; Schema: tietok; Owner: -
--

SELECT pg_catalog.setval('tietok.looginentv_id_seq', 1, false);


--
-- Name: looginentv_seq; Type: SEQUENCE SET; Schema: tietok; Owner: -
--

SELECT pg_catalog.setval('tietok.looginentv_seq', 1, false);


--
-- Name: looginentvhist_id_seq; Type: SEQUENCE SET; Schema: tietok; Owner: -
--

SELECT pg_catalog.setval('tietok.looginentvhist_id_seq', 1, false);


--
-- Name: looginentvhist_seq; Type: SEQUENCE SET; Schema: tietok; Owner: -
--

SELECT pg_catalog.setval('tietok.looginentvhist_seq', 1, false);


--
-- Name: organisaatio_id_seq; Type: SEQUENCE SET; Schema: tietok; Owner: -
--

SELECT pg_catalog.setval('tietok.organisaatio_id_seq', 1, false);


--
-- Name: organisaatiohistoria_id_seq; Type: SEQUENCE SET; Schema: tietok; Owner: -
--

SELECT pg_catalog.setval('tietok.organisaatiohistoria_id_seq', 1, false);


--
-- Name: paatietoryhma_id_seq; Type: SEQUENCE SET; Schema: tietok; Owner: -
--

SELECT pg_catalog.setval('tietok.paatietoryhma_id_seq', 1, false);


--
-- Name: paatietoryhma_paatietoryhma612; Type: SEQUENCE SET; Schema: tietok; Owner: -
--

SELECT pg_catalog.setval('tietok.paatietoryhma_paatietoryhma612', 1, false);


--
-- Name: paatietoryhma_seq; Type: SEQUENCE SET; Schema: tietok; Owner: -
--

SELECT pg_catalog.setval('tietok.paatietoryhma_seq', 1, false);


--
-- Name: paatietoryhmahist_id_seq; Type: SEQUENCE SET; Schema: tietok; Owner: -
--

SELECT pg_catalog.setval('tietok.paatietoryhmahist_id_seq', 1, false);


--
-- Name: paatietoryhmahist_seq; Type: SEQUENCE SET; Schema: tietok; Owner: -
--

SELECT pg_catalog.setval('tietok.paatietoryhmahist_seq', 1, false);


--
-- Name: paatietoryhmalahde_rivitunn376; Type: SEQUENCE SET; Schema: tietok; Owner: -
--

SELECT pg_catalog.setval('tietok.paatietoryhmalahde_rivitunn376', 1, false);


--
-- Name: paatietoryhmalahde_rivitunn377; Type: SEQUENCE SET; Schema: tietok; Owner: -
--

SELECT pg_catalog.setval('tietok.paatietoryhmalahde_rivitunn377', 1, false);


--
-- Name: paatietoryhmalahde_rivitunn379; Type: SEQUENCE SET; Schema: tietok; Owner: -
--

SELECT pg_catalog.setval('tietok.paatietoryhmalahde_rivitunn379', 1, false);


--
-- Name: paatietoryhmalahde_rivitunn381; Type: SEQUENCE SET; Schema: tietok; Owner: -
--

SELECT pg_catalog.setval('tietok.paatietoryhmalahde_rivitunn381', 1, false);


--
-- Name: paatietoryhmalahde_rivitunn383; Type: SEQUENCE SET; Schema: tietok; Owner: -
--

SELECT pg_catalog.setval('tietok.paatietoryhmalahde_rivitunn383', 1, false);


--
-- Name: paatietoryhmalahde_rivitunn574; Type: SEQUENCE SET; Schema: tietok; Owner: -
--

SELECT pg_catalog.setval('tietok.paatietoryhmalahde_rivitunn574', 1, false);


--
-- Name: palvelu_id_seq; Type: SEQUENCE SET; Schema: tietok; Owner: -
--

SELECT pg_catalog.setval('tietok.palvelu_id_seq', 1, false);


--
-- Name: palvelu_seq; Type: SEQUENCE SET; Schema: tietok; Owner: -
--

SELECT pg_catalog.setval('tietok.palvelu_seq', 1, false);


--
-- Name: palveluhist_id_seq; Type: SEQUENCE SET; Schema: tietok; Owner: -
--

SELECT pg_catalog.setval('tietok.palveluhist_id_seq', 1, false);


--
-- Name: palveluhist_seq; Type: SEQUENCE SET; Schema: tietok; Owner: -
--

SELECT pg_catalog.setval('tietok.palveluhist_seq', 1, false);


--
-- Name: rekisterijulkaisu_rivitunnu106; Type: SEQUENCE SET; Schema: tietok; Owner: -
--

SELECT pg_catalog.setval('tietok.rekisterijulkaisu_rivitunnu106', 1, false);


--
-- Name: rekisterikayttotiheys_rivit326; Type: SEQUENCE SET; Schema: tietok; Owner: -
--

SELECT pg_catalog.setval('tietok.rekisterikayttotiheys_rivit326', 1, false);


--
-- Name: rekisterilinkki_rivitunnus_seq; Type: SEQUENCE SET; Schema: tietok; Owner: -
--

SELECT pg_catalog.setval('tietok.rekisterilinkki_rivitunnus_seq', 1, false);


--
-- Name: rekisterilooginentietovaras113; Type: SEQUENCE SET; Schema: tietok; Owner: -
--

SELECT pg_catalog.setval('tietok.rekisterilooginentietovaras113', 1, false);


--
-- Name: rekisterilooginentietovaras525; Type: SEQUENCE SET; Schema: tietok; Owner: -
--

SELECT pg_catalog.setval('tietok.rekisterilooginentietovaras525', 1, false);


--
-- Name: rekisteripalvelutaso_rivitu222; Type: SEQUENCE SET; Schema: tietok; Owner: -
--

SELECT pg_catalog.setval('tietok.rekisteripalvelutaso_rivitu222', 1, false);


--
-- Name: rekisteritietojarjestelmati301; Type: SEQUENCE SET; Schema: tietok; Owner: -
--

SELECT pg_catalog.setval('tietok.rekisteritietojarjestelmati301', 1, false);


--
-- Name: rekisteritietokantateknolog539; Type: SEQUENCE SET; Schema: tietok; Owner: -
--

SELECT pg_catalog.setval('tietok.rekisteritietokantateknolog539', 1, false);


--
-- Name: rekisteritietolahde_rivitun386; Type: SEQUENCE SET; Schema: tietok; Owner: -
--

SELECT pg_catalog.setval('tietok.rekisteritietolahde_rivitun386', 1, false);


--
-- Name: rekisteritietolahde_rivitun388; Type: SEQUENCE SET; Schema: tietok; Owner: -
--

SELECT pg_catalog.setval('tietok.rekisteritietolahde_rivitun388', 1, false);


--
-- Name: rekisteritietolahde_rivitun389; Type: SEQUENCE SET; Schema: tietok; Owner: -
--

SELECT pg_catalog.setval('tietok.rekisteritietolahde_rivitun389', 1, false);


--
-- Name: rekisteritietoryhmaomistaja952; Type: SEQUENCE SET; Schema: tietok; Owner: -
--

SELECT pg_catalog.setval('tietok.rekisteritietoryhmaomistaja952', 1, false);


--
-- Name: rekisteritietovarastoomista937; Type: SEQUENCE SET; Schema: tietok; Owner: -
--

SELECT pg_catalog.setval('tietok.rekisteritietovarastoomista937', 1, false);


--
-- Name: rekisteritila_rivitunnus_seq; Type: SEQUENCE SET; Schema: tietok; Owner: -
--

SELECT pg_catalog.setval('tietok.rekisteritila_rivitunnus_seq', 1, false);


--
-- Name: rooli_id_seq; Type: SEQUENCE SET; Schema: tietok; Owner: -
--

SELECT pg_catalog.setval('tietok.rooli_id_seq', 1, false);


--
-- Name: rooli_seq; Type: SEQUENCE SET; Schema: tietok; Owner: -
--

SELECT pg_catalog.setval('tietok.rooli_seq', 1, false);


--
-- Name: sovellus_hist_id_seq; Type: SEQUENCE SET; Schema: tietok; Owner: -
--

SELECT pg_catalog.setval('tietok.sovellus_hist_id_seq', 1, false);


--
-- Name: sovellus_hist_seq; Type: SEQUENCE SET; Schema: tietok; Owner: -
--

SELECT pg_catalog.setval('tietok.sovellus_hist_seq', 1, false);


--
-- Name: sovellus_id_seq; Type: SEQUENCE SET; Schema: tietok; Owner: -
--

SELECT pg_catalog.setval('tietok.sovellus_id_seq', 1, false);


--
-- Name: sovellus_seq; Type: SEQUENCE SET; Schema: tietok; Owner: -
--

SELECT pg_catalog.setval('tietok.sovellus_seq', 1, false);


--
-- Name: sovellus_tuonti_id_seq; Type: SEQUENCE SET; Schema: tietok; Owner: -
--

SELECT pg_catalog.setval('tietok.sovellus_tuonti_id_seq', 1, false);


--
-- Name: sovhenkroolihist_id_seq; Type: SEQUENCE SET; Schema: tietok; Owner: -
--

SELECT pg_catalog.setval('tietok.sovhenkroolihist_id_seq', 1, false);


--
-- Name: sovhenkroolihist_seq; Type: SEQUENCE SET; Schema: tietok; Owner: -
--

SELECT pg_catalog.setval('tietok.sovhenkroolihist_seq', 1, false);


--
-- Name: termilomake_id_seq; Type: SEQUENCE SET; Schema: tietok; Owner: -
--

SELECT pg_catalog.setval('tietok.termilomake_id_seq', 1, false);


--
-- Name: termilomakeaskahistoria_id_seq; Type: SEQUENCE SET; Schema: tietok; Owner: -
--

SELECT pg_catalog.setval('tietok.termilomakeaskahistoria_id_seq', 1, false);


--
-- Name: termilomakeassoskasite_id_seq; Type: SEQUENCE SET; Schema: tietok; Owner: -
--

SELECT pg_catalog.setval('tietok.termilomakeassoskasite_id_seq', 1, false);


--
-- Name: termilomakehierkasite_id_seq; Type: SEQUENCE SET; Schema: tietok; Owner: -
--

SELECT pg_catalog.setval('tietok.termilomakehierkasite_id_seq', 1, false);


--
-- Name: termilomakehierkhist_id_seq; Type: SEQUENCE SET; Schema: tietok; Owner: -
--

SELECT pg_catalog.setval('tietok.termilomakehierkhist_id_seq', 1, false);


--
-- Name: termilomakehist_id_seq; Type: SEQUENCE SET; Schema: tietok; Owner: -
--

SELECT pg_catalog.setval('tietok.termilomakehist_id_seq', 1, false);


--
-- Name: termilomakekoostkashist_id_seq; Type: SEQUENCE SET; Schema: tietok; Owner: -
--

SELECT pg_catalog.setval('tietok.termilomakekoostkashist_id_seq', 1, false);


--
-- Name: termilomakekoostkasite_id_seq; Type: SEQUENCE SET; Schema: tietok; Owner: -
--

SELECT pg_catalog.setval('tietok.termilomakekoostkasite_id_seq', 1, false);


--
-- Name: tieto_tietotunnus_seq_1; Type: SEQUENCE SET; Schema: tietok; Owner: -
--

SELECT pg_catalog.setval('tietok.tieto_tietotunnus_seq_1', 1, false);


--
-- Name: tietojarjestelmapalvelu_id_seq; Type: SEQUENCE SET; Schema: tietok; Owner: -
--

SELECT pg_catalog.setval('tietok.tietojarjestelmapalvelu_id_seq', 1, false);


--
-- Name: tietojarjestelmapalvelu_tie872; Type: SEQUENCE SET; Schema: tietok; Owner: -
--

SELECT pg_catalog.setval('tietok.tietojarjestelmapalvelu_tie872', 1, false);


--
-- Name: tietojarjestelmasalkku_tiet311; Type: SEQUENCE SET; Schema: tietok; Owner: -
--

SELECT pg_catalog.setval('tietok.tietojarjestelmasalkku_tiet311', 1, false);


--
-- Name: tietojarjestelmasalkkufyysi263; Type: SEQUENCE SET; Schema: tietok; Owner: -
--

SELECT pg_catalog.setval('tietok.tietojarjestelmasalkkufyysi263', 1, false);


--
-- Name: tietojarjpalveluhist_id_seq; Type: SEQUENCE SET; Schema: tietok; Owner: -
--

SELECT pg_catalog.setval('tietok.tietojarjpalveluhist_id_seq', 1, false);


--
-- Name: tietojarjpalvelutieto_id_seq; Type: SEQUENCE SET; Schema: tietok; Owner: -
--

SELECT pg_catalog.setval('tietok.tietojarjpalvelutieto_id_seq', 1, false);


--
-- Name: tietojarjpalvliitjarj_id_seq; Type: SEQUENCE SET; Schema: tietok; Owner: -
--

SELECT pg_catalog.setval('tietok.tietojarjpalvliitjarj_id_seq', 1, false);


--
-- Name: tietojarjpalvtietohist_id_seq; Type: SEQUENCE SET; Schema: tietok; Owner: -
--

SELECT pg_catalog.setval('tietok.tietojarjpalvtietohist_id_seq', 1, false);


--
-- Name: tietolahde_rivitunnus_seq; Type: SEQUENCE SET; Schema: tietok; Owner: -
--

SELECT pg_catalog.setval('tietok.tietolahde_rivitunnus_seq', 1, false);


--
-- Name: tietolaji_id_seq; Type: SEQUENCE SET; Schema: tietok; Owner: -
--

SELECT pg_catalog.setval('tietok.tietolaji_id_seq', 1, false);


--
-- Name: tietolaji_seq; Type: SEQUENCE SET; Schema: tietok; Owner: -
--

SELECT pg_catalog.setval('tietok.tietolaji_seq', 1, false);


--
-- Name: tietolajihist_id_seq; Type: SEQUENCE SET; Schema: tietok; Owner: -
--

SELECT pg_catalog.setval('tietok.tietolajihist_id_seq', 1, false);


--
-- Name: tietolajihist_seq; Type: SEQUENCE SET; Schema: tietok; Owner: -
--

SELECT pg_catalog.setval('tietok.tietolajihist_seq', 1, false);


--
-- Name: tietoomaisuus_id_seq; Type: SEQUENCE SET; Schema: tietok; Owner: -
--

SELECT pg_catalog.setval('tietok.tietoomaisuus_id_seq', 1, false);


--
-- Name: tietoomaisuushist_id_seq; Type: SEQUENCE SET; Schema: tietok; Owner: -
--

SELECT pg_catalog.setval('tietok.tietoomaisuushist_id_seq', 1, false);


--
-- Name: tietoryhma_id_seq; Type: SEQUENCE SET; Schema: tietok; Owner: -
--

SELECT pg_catalog.setval('tietok.tietoryhma_id_seq', 1, false);


--
-- Name: tietoryhma_seq; Type: SEQUENCE SET; Schema: tietok; Owner: -
--

SELECT pg_catalog.setval('tietok.tietoryhma_seq', 1, false);


--
-- Name: tietoryhma_tietoryhmatunnus830; Type: SEQUENCE SET; Schema: tietok; Owner: -
--

SELECT pg_catalog.setval('tietok.tietoryhma_tietoryhmatunnus830', 1, false);


--
-- Name: tietoryhmahist_id_seq; Type: SEQUENCE SET; Schema: tietok; Owner: -
--

SELECT pg_catalog.setval('tietok.tietoryhmahist_id_seq', 1, false);


--
-- Name: tietoryhmahist_seq; Type: SEQUENCE SET; Schema: tietok; Owner: -
--

SELECT pg_catalog.setval('tietok.tietoryhmahist_seq', 1, false);


--
-- Name: tietoryhmalooginentietovara529; Type: SEQUENCE SET; Schema: tietok; Owner: -
--

SELECT pg_catalog.setval('tietok.tietoryhmalooginentietovara529', 1, false);


--
-- Name: tietoryhmatietovarahist_id_seq; Type: SEQUENCE SET; Schema: tietok; Owner: -
--

SELECT pg_catalog.setval('tietok.tietoryhmatietovarahist_id_seq', 1, false);


--
-- Name: tietoryhmatietovaranto_id_seq; Type: SEQUENCE SET; Schema: tietok; Owner: -
--

SELECT pg_catalog.setval('tietok.tietoryhmatietovaranto_id_seq', 1, false);


--
-- Name: tietosuojavastaava_id_seq; Type: SEQUENCE SET; Schema: tietok; Owner: -
--

SELECT pg_catalog.setval('tietok.tietosuojavastaava_id_seq', 1, false);


--
-- Name: tietosuojavastaavahist_id_seq; Type: SEQUENCE SET; Schema: tietok; Owner: -
--

SELECT pg_catalog.setval('tietok.tietosuojavastaavahist_id_seq', 1, false);


--
-- Name: tietovarahenktietoryhma_id_seq; Type: SEQUENCE SET; Schema: tietok; Owner: -
--

SELECT pg_catalog.setval('tietok.tietovarahenktietoryhma_id_seq', 1, false);


--
-- Name: tietovarakasitperushist_id_seq; Type: SEQUENCE SET; Schema: tietok; Owner: -
--

SELECT pg_catalog.setval('tietok.tietovarakasitperushist_id_seq', 1, false);


--
-- Name: tietovaranto_id_seq; Type: SEQUENCE SET; Schema: tietok; Owner: -
--

SELECT pg_catalog.setval('tietok.tietovaranto_id_seq', 1, false);


--
-- Name: tietovarantohist_id_seq; Type: SEQUENCE SET; Schema: tietok; Owner: -
--

SELECT pg_catalog.setval('tietok.tietovarantohist_id_seq', 1, false);


--
-- Name: tietovarantorekistryhma_id_seq; Type: SEQUENCE SET; Schema: tietok; Owner: -
--

SELECT pg_catalog.setval('tietok.tietovarantorekistryhma_id_seq', 1, false);


--
-- Name: tietovarantrekryhmahist_id_seq; Type: SEQUENCE SET; Schema: tietok; Owner: -
--

SELECT pg_catalog.setval('tietok.tietovarantrekryhmahist_id_seq', 1, false);


--
-- Name: tietovaratiedonohjshist_id_seq; Type: SEQUENCE SET; Schema: tietok; Owner: -
--

SELECT pg_catalog.setval('tietok.tietovaratiedonohjshist_id_seq', 1, false);


--
-- Name: tietovaravastaryhmahist_id_seq; Type: SEQUENCE SET; Schema: tietok; Owner: -
--

SELECT pg_catalog.setval('tietok.tietovaravastaryhmahist_id_seq', 1, false);


--
-- Name: tietovarayhtrekpitahist_id_seq; Type: SEQUENCE SET; Schema: tietok; Owner: -
--

SELECT pg_catalog.setval('tietok.tietovarayhtrekpitahist_id_seq', 1, false);


--
-- Name: tietovarayllapttahohist_id_seq; Type: SEQUENCE SET; Schema: tietok; Owner: -
--

SELECT pg_catalog.setval('tietok.tietovarayllapttahohist_id_seq', 1, false);


--
-- Name: tietovarhentietryhmhist_id_seq; Type: SEQUENCE SET; Schema: tietok; Owner: -
--

SELECT pg_catalog.setval('tietok.tietovarhentietryhmhist_id_seq', 1, false);


--
-- Name: tietovarkasittelperuste_id_seq; Type: SEQUENCE SET; Schema: tietok; Owner: -
--

SELECT pg_catalog.setval('tietok.tietovarkasittelperuste_id_seq', 1, false);


--
-- Name: tietovartiedonohjaus_id_seq; Type: SEQUENCE SET; Schema: tietok; Owner: -
--

SELECT pg_catalog.setval('tietok.tietovartiedonohjaus_id_seq', 1, false);


--
-- Name: tietovarturvatoimenpide_id_seq; Type: SEQUENCE SET; Schema: tietok; Owner: -
--

SELECT pg_catalog.setval('tietok.tietovarturvatoimenpide_id_seq', 1, false);


--
-- Name: tietovarturvtoimpidhist_id_seq; Type: SEQUENCE SET; Schema: tietok; Owner: -
--

SELECT pg_catalog.setval('tietok.tietovarturvtoimpidhist_id_seq', 1, false);


--
-- Name: tietovarvastaanottryhma_id_seq; Type: SEQUENCE SET; Schema: tietok; Owner: -
--

SELECT pg_catalog.setval('tietok.tietovarvastaanottryhma_id_seq', 1, false);


--
-- Name: tietovaryhteisrekpitaja_id_seq; Type: SEQUENCE SET; Schema: tietok; Owner: -
--

SELECT pg_catalog.setval('tietok.tietovaryhteisrekpitaja_id_seq', 1, false);


--
-- Name: tietovaryllapitomuutaho_id_seq; Type: SEQUENCE SET; Schema: tietok; Owner: -
--

SELECT pg_catalog.setval('tietok.tietovaryllapitomuutaho_id_seq', 1, false);


--
-- Name: tjpliittyvajarjhist_id_seq; Type: SEQUENCE SET; Schema: tietok; Owner: -
--

SELECT pg_catalog.setval('tietok.tjpliittyvajarjhist_id_seq', 1, false);


--
-- Name: toimintaprosessi_id_seq; Type: SEQUENCE SET; Schema: tietok; Owner: -
--

SELECT pg_catalog.setval('tietok.toimintaprosessi_id_seq', 1, false);


--
-- Name: toimintaprosessihist_id_seq; Type: SEQUENCE SET; Schema: tietok; Owner: -
--

SELECT pg_catalog.setval('tietok.toimintaprosessihist_id_seq', 1, false);


--
-- Name: toimintaprostietovahist_id_seq; Type: SEQUENCE SET; Schema: tietok; Owner: -
--

SELECT pg_catalog.setval('tietok.toimintaprostietovahist_id_seq', 1, false);


--
-- Name: toimintaprostietovarant_id_seq; Type: SEQUENCE SET; Schema: tietok; Owner: -
--

SELECT pg_catalog.setval('tietok.toimintaprostietovarant_id_seq', 1, false);


--
-- PostgreSQL database dump complete
--

