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
-- Data for Name: looginen_kasite_arvo; Type: TABLE DATA; Schema: tietok; Owner: tietokt
--

INSERT INTO tietok.looginen_kasite_arvo VALUES (1, 'Alue', 'Tie', 'T');
INSERT INTO tietok.looginen_kasite_arvo VALUES (2, 'Alue', 'Rata', 'R');
INSERT INTO tietok.looginen_kasite_arvo VALUES (3, 'Alue', 'Vesi', 'V');
INSERT INTO tietok.looginen_kasite_arvo VALUES (4, 'Alue', 'Yhteiset', 'Y');


--
-- Data for Name: palvelu_kasite_arvo; Type: TABLE DATA; Schema: tietok; Owner: tietokt
--

INSERT INTO tietok.palvelu_kasite_arvo VALUES (1, 'Asiointipalvelut', 'Asiointipalvelut');
INSERT INTO tietok.palvelu_kasite_arvo VALUES (2, 'ICT -infrapalvelut', 'Infran elinkaaren hallintapalvelut');
INSERT INTO tietok.palvelu_kasite_arvo VALUES (3, 'ICT -infrapalvelut', 'Palveluiden elinkaaren hallinta (SOA -toimisto)');
INSERT INTO tietok.palvelu_kasite_arvo VALUES (4, 'ICT -infrapalvelut', 'Tietoliikenteen elinkaaren hallintapalvelut');
INSERT INTO tietok.palvelu_kasite_arvo VALUES (5, 'ICT -infrapalvelut', 'Pääsy- ja käyttövaltuuksien elinkaaren hallintapalvelut (IAM)');
INSERT INTO tietok.palvelu_kasite_arvo VALUES (6, 'ICT -käyttäjälle palvelut', 'Käyttäjien tukipalvelut');
INSERT INTO tietok.palvelu_kasite_arvo VALUES (7, 'ICT -käyttäjälle palvelut', 'Käyttäjien etäyhteyspalvelut');
INSERT INTO tietok.palvelu_kasite_arvo VALUES (8, 'ICT -käyttäjälle palvelut', 'Käyttäjä- ja käyttövaltuudet');
INSERT INTO tietok.palvelu_kasite_arvo VALUES (9, 'ICT -käyttäjälle palvelut', 'Käyttäjien päätelaitepalvelut');
INSERT INTO tietok.palvelu_kasite_arvo VALUES (10, 'ICT -käyttäjälle palvelut', 'Käyttäjien viestintätekniset palvelut');
INSERT INTO tietok.palvelu_kasite_arvo VALUES (11, 'ICT hallinta- ja ylläpitopalvelut', 'Palvelutuottajien ohjaus');
INSERT INTO tietok.palvelu_kasite_arvo VALUES (12, 'ICT hallinta- ja ylläpitopalvelut', 'Sopimusten ja lisenssien hallintapalvelu');
INSERT INTO tietok.palvelu_kasite_arvo VALUES (13, 'Kehittämisen ohjauspalvelut', 'Kehittämisohjelman johto- ja hallintapalvelu');
INSERT INTO tietok.palvelu_kasite_arvo VALUES (14, 'Kehittämisen ohjauspalvelut', 'Arkkitehtuuripalvelu');
INSERT INTO tietok.palvelu_kasite_arvo VALUES (15, 'Kehittämisprojektien toteuttaminen', 'Kehittämisprojektin ohjaus');
INSERT INTO tietok.palvelu_kasite_arvo VALUES (16, 'Kehittämisprojektien toteuttaminen', 'Kehittämisprojektin toteutus');
INSERT INTO tietok.palvelu_kasite_arvo VALUES (17, 'Projektien tuki- ja ohjauspalvelut', 'Yhteisten palveluiden asiantuntijatuki');
INSERT INTO tietok.palvelu_kasite_arvo VALUES (18, 'Projektien tuki- ja ohjauspalvelut', 'SOA -asiantuntijatuki');
INSERT INTO tietok.palvelu_kasite_arvo VALUES (19, 'Projektien tuki- ja ohjauspalvelut', 'Käyttäjä- ja käyttövaltuushallinnan tuki');
INSERT INTO tietok.palvelu_kasite_arvo VALUES (20, 'Tieto-omaisuuden hallintapalvelut', 'Väylätietojen hallintapalvelu');
INSERT INTO tietok.palvelu_kasite_arvo VALUES (21, 'Tieto-omaisuuden hallintapalvelut', 'Asiakirja- ja asianhallintapalvelut');
INSERT INTO tietok.palvelu_kasite_arvo VALUES (22, 'Tieto-omaisuuden hallintapalvelut', 'Merenmittaustietojen hallintapalvelut');
INSERT INTO tietok.palvelu_kasite_arvo VALUES (23, 'Tieto-omaisuuden hallintapalvelut', 'Tietojärjestelmien ylläpito ja elinkaaren hallinta');
INSERT INTO tietok.palvelu_kasite_arvo VALUES (24, 'Tietohallinto', 'Tietojärjestelmien salasanat ja käyttöoikeudet');
INSERT INTO tietok.palvelu_kasite_arvo VALUES (25, 'Tietohallinto', 'Verkot ja yhteydet');
INSERT INTO tietok.palvelu_kasite_arvo VALUES (26, 'Tietohallinto', 'Ulkoisten käyttäjien käyttöoikeudet');
INSERT INTO tietok.palvelu_kasite_arvo VALUES (27, 'Tietohallinto', 'Palvelut IT-projekteille ja järjestelmävastaaville');
INSERT INTO tietok.palvelu_kasite_arvo VALUES (28, 'Tietohallinto', 'Tuki ja opastus (käyttäjätukipalvelu)');
INSERT INTO tietok.palvelu_kasite_arvo VALUES (29, 'Tietohallinto', 'Asiakirjahallinto- ja kirjastopalvelut');
INSERT INTO tietok.palvelu_kasite_arvo VALUES (30, 'Tietohallinto', 'Laitteet ja tarvikkeet');
INSERT INTO tietok.palvelu_kasite_arvo VALUES (31, 'Tietohallinto', 'Videoneuvottelulaitteet');
INSERT INTO tietok.palvelu_kasite_arvo VALUES (32, 'Tietopalvelut', 'Avoin data -tietopalvelut');
INSERT INTO tietok.palvelu_kasite_arvo VALUES (33, 'Tietopalvelut', 'Avoin data -asiantuntijatukipalvelu');
INSERT INTO tietok.palvelu_kasite_arvo VALUES (34, 'Tietopalvelut', 'Arkisto- ja kirjastotietopalvelut');
INSERT INTO tietok.palvelu_kasite_arvo VALUES (35, 'Tietopalvelut', 'Tilastopalvelut');
INSERT INTO tietok.palvelu_kasite_arvo VALUES (36, 'Tietopalvelut', 'Tietoaineistopalvelut');
INSERT INTO tietok.palvelu_kasite_arvo VALUES (37, 'Tietopalvelut', 'Analytiikkapalvelut');
INSERT INTO tietok.palvelu_kasite_arvo VALUES (38, 'Tietoturva ja mahdollistavat palvelut', 'Tietoturvan palvelut');
INSERT INTO tietok.palvelu_kasite_arvo VALUES (39, 'Tietoturva ja mahdollistavat palvelut', 'Muut mahdollistavat palvelut');
INSERT INTO tietok.palvelu_kasite_arvo VALUES (40, 'Ylätaso', 'Asiointipalvelut');
INSERT INTO tietok.palvelu_kasite_arvo VALUES (41, 'Ylätaso', 'Kehittämisen ohjauspalvelut');
INSERT INTO tietok.palvelu_kasite_arvo VALUES (42, 'Ylätaso', 'Projektien tuki- ja ohjauspalvelut');
INSERT INTO tietok.palvelu_kasite_arvo VALUES (43, 'Ylätaso', 'ICT -käyttäjälle palvelut');
INSERT INTO tietok.palvelu_kasite_arvo VALUES (44, 'Ylätaso', 'ICT -infrapalvelut');
INSERT INTO tietok.palvelu_kasite_arvo VALUES (45, 'Ylätaso', 'Tietoturva ja mahdollistavat palvelut');
INSERT INTO tietok.palvelu_kasite_arvo VALUES (46, 'Ylätaso', 'ICT hallinta- ja ylläpitopalvelut');
INSERT INTO tietok.palvelu_kasite_arvo VALUES (47, 'Ylätaso', 'Kehittämisprojektien toteuttaminen');
INSERT INTO tietok.palvelu_kasite_arvo VALUES (48, 'Ylätaso', 'Tietopalvelut');
INSERT INTO tietok.palvelu_kasite_arvo VALUES (49, 'Ylätaso', 'Tieto-omaisuuden hallintapalvelut');


--
-- Data for Name: palvelukatalogi_kasite_arvo; Type: TABLE DATA; Schema: tietok; Owner: tietokt
--

INSERT INTO tietok.palvelukatalogi_kasite_arvo VALUES (1, 'Otsikko', 'Salasanan nollaaminen', 'Tietohallinto');
INSERT INTO tietok.palvelukatalogi_kasite_arvo VALUES (2, 'Otsikko', 'Tietojärjestelmien salasanat ja käyttöoikeudet', 'Tietohallinto');
INSERT INTO tietok.palvelukatalogi_kasite_arvo VALUES (3, 'Otsikko', 'Tuki ja opastus (käyttäjätukipalvelu)', 'Tietohallinto');
INSERT INTO tietok.palvelukatalogi_kasite_arvo VALUES (4, 'Otsikko', 'Laitteet ja tarvikkeet henkilökunnalle', 'Tietohallinto');
INSERT INTO tietok.palvelukatalogi_kasite_arvo VALUES (5, 'Otsikko', 'Verkot ja yhteydet', 'Tietohallinto');
INSERT INTO tietok.palvelukatalogi_kasite_arvo VALUES (6, 'Otsikko', 'Videoneuvottelulaitteet', 'Tietohallinto');
INSERT INTO tietok.palvelukatalogi_kasite_arvo VALUES (7, 'Otsikko', 'Tulostus- ja monitoimilaitteet (kopiokone ja skannaus)', 'Tietohallinto');
INSERT INTO tietok.palvelukatalogi_kasite_arvo VALUES (8, 'Otsikko', 'Asiakirjahallinto- ja kirjastopalvelut', 'Tietohallinto');
INSERT INTO tietok.palvelukatalogi_kasite_arvo VALUES (9, 'Otsikko', 'Palvelut IT-projekteille ja järjestelmävastaaville', 'Tietohallinto');
INSERT INTO tietok.palvelukatalogi_kasite_arvo VALUES (10, 'Otsikko', 'Ulkoisten käyttäjien käyttöoikeudet', 'Tietohallinto');
INSERT INTO tietok.palvelukatalogi_kasite_arvo VALUES (11, 'Ylätaso', 'Tietohallinto', NULL);


--
-- Data for Name: tieto_kasite_arvo; Type: TABLE DATA; Schema: tietok; Owner: tietokt
--

INSERT INTO tietok.tieto_kasite_arvo VALUES (1, 'Tila', 'Suunnitteilla');
INSERT INTO tietok.tieto_kasite_arvo VALUES (2, 'Tila', 'Kehityksessä');
INSERT INTO tietok.tieto_kasite_arvo VALUES (3, 'Tila', 'Tuotannossa');
INSERT INTO tietok.tieto_kasite_arvo VALUES (4, 'Tila', 'Poistumassa');
INSERT INTO tietok.tieto_kasite_arvo VALUES (5, 'Tila', 'Poistunut');


--
-- Data for Name: tietojarjestelma_kasite_arvo; Type: TABLE DATA; Schema: tietok; Owner: tietokt
--

INSERT INTO tietok.tietojarjestelma_kasite_arvo VALUES (1, 'TURVALLISUUSLUOKKA', 'Ei turvallisuuskriittisyyttä');
INSERT INTO tietok.tietojarjestelma_kasite_arvo VALUES (2, 'TURVALLISUUSLUOKKA', 'Turvallisuuskriittinen');
INSERT INTO tietok.tietojarjestelma_kasite_arvo VALUES (3, 'PALVELUAIKA', 'virka-aika (8-16)');
INSERT INTO tietok.tietojarjestelma_kasite_arvo VALUES (4, 'PALVELUAIKA', 'jatkuva 24h (24/7)');
INSERT INTO tietok.tietojarjestelma_kasite_arvo VALUES (5, 'PALVELUAIKA', 'pidennetty virka-aika (7-20)');
INSERT INTO tietok.tietojarjestelma_kasite_arvo VALUES (9, 'ICT_VARAUTUMISEN_LUOKITUS', 'Perus');
INSERT INTO tietok.tietojarjestelma_kasite_arvo VALUES (10, 'ICT_VARAUTUMISEN_LUOKITUS', 'Korotettu');
INSERT INTO tietok.tietojarjestelma_kasite_arvo VALUES (11, 'ICT_VARAUTUMISEN_LUOKITUS', 'Korkea');
INSERT INTO tietok.tietojarjestelma_kasite_arvo VALUES (12, 'SLA_LUOKITUS_KAYTTOPALVELU', 'Normaali');
INSERT INTO tietok.tietojarjestelma_kasite_arvo VALUES (13, 'SLA_LUOKITUS_KAYTTOPALVELU', 'Laajennettu');
INSERT INTO tietok.tietojarjestelma_kasite_arvo VALUES (14, 'SLA_LUOKITUS_KAYTTOPALVELU', 'Kriittinen');
INSERT INTO tietok.tietojarjestelma_kasite_arvo VALUES (15, 'SLA_LUOKITUS_KAYTTOPALVELU', 'Erittäin kriittinen');
INSERT INTO tietok.tietojarjestelma_kasite_arvo VALUES (16, 'Toteutusteknologia', 'Weblogic');
INSERT INTO tietok.tietojarjestelma_kasite_arvo VALUES (17, 'Toteutusteknologia', 'OC4J');
INSERT INTO tietok.tietojarjestelma_kasite_arvo VALUES (18, 'Toteutusteknologia', 'JBOSS');
INSERT INTO tietok.tietojarjestelma_kasite_arvo VALUES (19, 'Toteutusteknologia', '.NET');
INSERT INTO tietok.tietojarjestelma_kasite_arvo VALUES (20, 'PORTAALIPALVELU', 'Extranet');
INSERT INTO tietok.tietojarjestelma_kasite_arvo VALUES (21, 'PORTAALIPALVELU', 'Intranet');
INSERT INTO tietok.tietojarjestelma_kasite_arvo VALUES (22, 'PORTAALIPALVELU', 'Järjestelmän sisäinen');
INSERT INTO tietok.tietojarjestelma_kasite_arvo VALUES (23, 'TEKNINEN_ELINKAARI', 'normaali');
INSERT INTO tietok.tietojarjestelma_kasite_arvo VALUES (24, 'TEKNINEN_ELINKAARI', 'hyvä');
INSERT INTO tietok.tietojarjestelma_kasite_arvo VALUES (25, 'TEKNINEN_ELINKAARI', 'erittäin hyvä');
INSERT INTO tietok.tietojarjestelma_kasite_arvo VALUES (26, 'TEKNINEN_ELINKAARI', 'erittäin huono');
INSERT INTO tietok.tietojarjestelma_kasite_arvo VALUES (27, 'TEKNINEN_ELINKAARI', 'huono');
INSERT INTO tietok.tietojarjestelma_kasite_arvo VALUES (28, 'KAYTTOOIKEUKSIEN_HALLINTA', 'järjestelmän oma hallinta');
INSERT INTO tietok.tietojarjestelma_kasite_arvo VALUES (29, 'KAYTTOOIKEUKSIEN_HALLINTA', 'käyttöpalvelu');
INSERT INTO tietok.tietojarjestelma_kasite_arvo VALUES (30, 'SLA_LUOKITUS_JHS', 'Normaali');
INSERT INTO tietok.tietojarjestelma_kasite_arvo VALUES (31, 'SLA_LUOKITUS_JHS', 'Laajennettu');
INSERT INTO tietok.tietojarjestelma_kasite_arvo VALUES (32, 'SLA_LUOKITUS_JHS', 'Kriittinen');
INSERT INTO tietok.tietojarjestelma_kasite_arvo VALUES (33, 'SLA_LUOKITUS_JHS', 'Erittäin kriittinen');
INSERT INTO tietok.tietojarjestelma_kasite_arvo VALUES (34, 'KRIITTISYYS', 'Normaali');
INSERT INTO tietok.tietojarjestelma_kasite_arvo VALUES (35, 'KRIITTISYYS', 'Korkea');
INSERT INTO tietok.tietojarjestelma_kasite_arvo VALUES (36, 'KRIITTISYYS', 'Matala');
INSERT INTO tietok.tietojarjestelma_kasite_arvo VALUES (37, 'KRIITTISYYS', 'Erittäin korkea');
INSERT INTO tietok.tietojarjestelma_kasite_arvo VALUES (38, 'KRIITTISYYS', 'Erittäin matala');
INSERT INTO tietok.tietojarjestelma_kasite_arvo VALUES (39, 'TOIMINNALLINEN_ELINKAARI', 'vakiintunut käyttö');
INSERT INTO tietok.tietojarjestelma_kasite_arvo VALUES (40, 'TOIMINNALLINEN_ELINKAARI', 'poistunut');
INSERT INTO tietok.tietojarjestelma_kasite_arvo VALUES (41, 'TOIMINNALLINEN_ELINKAARI', 'kehitteillä');
INSERT INTO tietok.tietojarjestelma_kasite_arvo VALUES (42, 'TOIMINNALLINEN_ELINKAARI', 'poistossa');
INSERT INTO tietok.tietojarjestelma_kasite_arvo VALUES (43, 'TOIMINNALLINEN_ELINKAARI', 'rajoitettu käyttö');
INSERT INTO tietok.tietojarjestelma_kasite_arvo VALUES (45, 'JARJESTELMAN_TARKEYS_LIVILLE', 'Jonkin verran tärkeä');
INSERT INTO tietok.tietojarjestelma_kasite_arvo VALUES (46, 'JARJESTELMAN_TARKEYS_LIVILLE', 'Tärkeä');
INSERT INTO tietok.tietojarjestelma_kasite_arvo VALUES (49, 'JARJ_TARKEYS_YHTEISTYOKUMP', 'Jonkin verran tärkeä');
INSERT INTO tietok.tietojarjestelma_kasite_arvo VALUES (50, 'JARJ_TARKEYS_YHTEISTYOKUMP', 'Tärkeä');
INSERT INTO tietok.tietojarjestelma_kasite_arvo VALUES (52, 'Pääsynhallinta', 'AD');
INSERT INTO tietok.tietojarjestelma_kasite_arvo VALUES (53, 'Pääsynhallinta', 'OID');
INSERT INTO tietok.tietojarjestelma_kasite_arvo VALUES (54, 'Pääsynhallinta', 'Järjestelmän sisäinen');
INSERT INTO tietok.tietojarjestelma_kasite_arvo VALUES (55, 'TARKEYS_YK', 'Vähäinen');
INSERT INTO tietok.tietojarjestelma_kasite_arvo VALUES (56, 'TARKEYS_YK', 'Jonkin verran tärkeä');
INSERT INTO tietok.tietojarjestelma_kasite_arvo VALUES (57, 'TARKEYS_YK', 'Tärkeä');
INSERT INTO tietok.tietojarjestelma_kasite_arvo VALUES (58, 'TARKEYS_YK', 'Elintärkeä');
INSERT INTO tietok.tietojarjestelma_kasite_arvo VALUES (59, 'TIETOJEN_JULKISUUS', 'Julkinen');
INSERT INTO tietok.tietojarjestelma_kasite_arvo VALUES (64, 'Palvelin', 'Windows 2003 Server');
INSERT INTO tietok.tietojarjestelma_kasite_arvo VALUES (65, 'Palvelin', 'Windows 2008R2');
INSERT INTO tietok.tietojarjestelma_kasite_arvo VALUES (66, 'Palvelin', 'Windows 2012R2');
INSERT INTO tietok.tietojarjestelma_kasite_arvo VALUES (67, 'Jarjestelmatyyppi', 'lisenssi');
INSERT INTO tietok.tietojarjestelma_kasite_arvo VALUES (68, 'Jarjestelmatyyppi', 'palvelu');
INSERT INTO tietok.tietojarjestelma_kasite_arvo VALUES (69, 'Jarjestelmatyyppi', 'sovellus');
INSERT INTO tietok.tietojarjestelma_kasite_arvo VALUES (70, 'Jarjestelmatyyppi', 'sulautettu järjestelmä');
INSERT INTO tietok.tietojarjestelma_kasite_arvo VALUES (71, 'Jarjestelmatyyppi', 'tietojärjestelmä');
INSERT INTO tietok.tietojarjestelma_kasite_arvo VALUES (72, 'Elinkaaritila', 'Suunnitteilla');
INSERT INTO tietok.tietojarjestelma_kasite_arvo VALUES (73, 'Elinkaaritila', 'Kehityksessä');
INSERT INTO tietok.tietojarjestelma_kasite_arvo VALUES (74, 'Elinkaaritila', 'Tuotannossa');
INSERT INTO tietok.tietojarjestelma_kasite_arvo VALUES (75, 'Elinkaaritila', 'Poistumassa');
INSERT INTO tietok.tietojarjestelma_kasite_arvo VALUES (76, 'Elinkaaritila', 'Poistunut');
INSERT INTO tietok.tietojarjestelma_kasite_arvo VALUES (77, 'Jarjestelmaalue', 'LiHa - Meri');
INSERT INTO tietok.tietojarjestelma_kasite_arvo VALUES (78, 'Jarjestelmaalue', 'LiHa - Rata');
INSERT INTO tietok.tietojarjestelma_kasite_arvo VALUES (79, 'Jarjestelmaalue', 'LiHa - Tie');
INSERT INTO tietok.tietojarjestelma_kasite_arvo VALUES (80, 'Jarjestelmaalue', 'Liikenteen palvelut');
INSERT INTO tietok.tietojarjestelma_kasite_arvo VALUES (81, 'Jarjestelmaalue', 'VTJ - Meri');
INSERT INTO tietok.tietojarjestelma_kasite_arvo VALUES (82, 'Jarjestelmaalue', 'VTJ - Rata');
INSERT INTO tietok.tietojarjestelma_kasite_arvo VALUES (83, 'Jarjestelmaalue', 'VTJ - Tie');
INSERT INTO tietok.tietojarjestelma_kasite_arvo VALUES (84, 'Jarjestelmaalue', 'Yhteinen ICT');
INSERT INTO tietok.tietojarjestelma_kasite_arvo VALUES (100, 'Budjetti', 'Järjestelmäbudjetti');
INSERT INTO tietok.tietojarjestelma_kasite_arvo VALUES (101, 'Budjetti', 'Yksikön oma');
INSERT INTO tietok.tietojarjestelma_kasite_arvo VALUES (102, 'Rahoitusmomentti', 'Toimintamenot');
INSERT INTO tietok.tietojarjestelma_kasite_arvo VALUES (103, 'Rahoitusmomentti', 'Radanpito');
INSERT INTO tietok.tietojarjestelma_kasite_arvo VALUES (104, 'Rahoitusmomentti', 'Tienpito');
INSERT INTO tietok.tietojarjestelma_kasite_arvo VALUES (105, 'Rahoitusmomentti', 'Vesiväylänpito');
INSERT INTO tietok.tietojarjestelma_kasite_arvo VALUES (93, 'OMISTAVA_ORGANISAATIO', 'Väylävirasto');
INSERT INTO tietok.tietojarjestelma_kasite_arvo VALUES (94, 'OMISTAVA_ORGANISAATIO', 'Traficom');
INSERT INTO tietok.tietojarjestelma_kasite_arvo VALUES (95, 'OMISTAVA_ORGANISAATIO', 'Fintraffic');
INSERT INTO tietok.tietojarjestelma_kasite_arvo VALUES (96, 'OMISTAVA_ORGANISAATIO', 'VR Tracks');
INSERT INTO tietok.tietojarjestelma_kasite_arvo VALUES (97, 'OMISTAVA_ORGANISAATIO', 'VR');
INSERT INTO tietok.tietojarjestelma_kasite_arvo VALUES (98, 'OMISTAVA_ORGANISAATIO', 'ELY-keskus');
INSERT INTO tietok.tietojarjestelma_kasite_arvo VALUES (99, 'OMISTAVA_ORGANISAATIO', 'Palkeet');
INSERT INTO tietok.tietojarjestelma_kasite_arvo VALUES (89, 'Tyyppi', 'Sovellus');
INSERT INTO tietok.tietojarjestelma_kasite_arvo VALUES (90, 'Tyyppi', 'Järjestelmä');
INSERT INTO tietok.tietojarjestelma_kasite_arvo VALUES (91, 'Suunta', 'Luku');
INSERT INTO tietok.tietojarjestelma_kasite_arvo VALUES (92, 'Suunta', 'Kirjoitus');
INSERT INTO tietok.tietojarjestelma_kasite_arvo VALUES (107, 'TURVALLISUUSLUOKITUS', 'Turvallisuusluokka I');
INSERT INTO tietok.tietojarjestelma_kasite_arvo VALUES (108, 'TURVALLISUUSLUOKITUS', 'Turvallisuusluokka II');
INSERT INTO tietok.tietojarjestelma_kasite_arvo VALUES (109, 'TURVALLISUUSLUOKITUS', 'Turvallisuusluokka III');
INSERT INTO tietok.tietojarjestelma_kasite_arvo VALUES (110, 'TURVALLISUUSLUOKITUS', 'Turvallisuusluokka IV');
INSERT INTO tietok.tietojarjestelma_kasite_arvo VALUES (111, 'TARKEINTA', 'Luottamuksellisuus');
INSERT INTO tietok.tietojarjestelma_kasite_arvo VALUES (112, 'TARKEINTA', 'Eheys');
INSERT INTO tietok.tietojarjestelma_kasite_arvo VALUES (113, 'TARKEINTA', 'Saatavuus');
INSERT INTO tietok.tietojarjestelma_kasite_arvo VALUES (114, 'TIEDONLUOTTAMUKSELLISUUS', 'Ei lainkaan tärkeä');
INSERT INTO tietok.tietojarjestelma_kasite_arvo VALUES (115, 'TIEDONLUOTTAMUKSELLISUUS', 'Jonkin verran tärkeä');
INSERT INTO tietok.tietojarjestelma_kasite_arvo VALUES (116, 'TIEDONLUOTTAMUKSELLISUUS', 'Tärkeä');
INSERT INTO tietok.tietojarjestelma_kasite_arvo VALUES (117, 'TIEDONLUOTTAMUKSELLISUUS', 'Erittäin tärkeä');
INSERT INTO tietok.tietojarjestelma_kasite_arvo VALUES (118, 'TIEDON_SAATAVUUS', 'Jonkin verran tärkeä');
INSERT INTO tietok.tietojarjestelma_kasite_arvo VALUES (119, 'TIEDON_SAATAVUUS', 'Tärkeä');
INSERT INTO tietok.tietojarjestelma_kasite_arvo VALUES (120, 'TIEDON_SAATAVUUS', 'Erittäin tärkeä');
INSERT INTO tietok.tietojarjestelma_kasite_arvo VALUES (121, 'TIEDON_EHEYS', 'Jonkin verran tärkeä');
INSERT INTO tietok.tietojarjestelma_kasite_arvo VALUES (122, 'TIEDON_EHEYS', 'Tärkeä');
INSERT INTO tietok.tietojarjestelma_kasite_arvo VALUES (123, 'TIEDON_EHEYS', 'Erittäin tärkeä');
INSERT INTO tietok.tietojarjestelma_kasite_arvo VALUES (124, 'TIETOTURVATASOLUOKITUS', 'Jonkin verran tärkeä');
INSERT INTO tietok.tietojarjestelma_kasite_arvo VALUES (125, 'TIETOTURVATASOLUOKITUS', 'Tärkeä');
INSERT INTO tietok.tietojarjestelma_kasite_arvo VALUES (126, 'TIETOTURVATASOLUOKITUS', 'Erittäin tärkeä');
INSERT INTO tietok.tietojarjestelma_kasite_arvo VALUES (127, 'TIETOJEN_JULKISUUS', 'Turvallisuusluokiteltu');
INSERT INTO tietok.tietojarjestelma_kasite_arvo VALUES (128, 'TIETOJEN_JULKISUUS', 'Salassa pidettävä');
INSERT INTO tietok.tietojarjestelma_kasite_arvo VALUES (129, 'TIETOJEN_JULKISUUS', 'Harkinnanvaraisesti annettava');
INSERT INTO tietok.tietojarjestelma_kasite_arvo VALUES (130, 'JARJESTELMAN_TARKEYS_LIVILLE', 'Erittäin tärkeä');
INSERT INTO tietok.tietojarjestelma_kasite_arvo VALUES (131, 'JARJ_TARKEYS_YHTEISTYOKUMP', 'Erittäin tärkeä');
INSERT INTO tietok.tietojarjestelma_kasite_arvo VALUES (132, 'JARJ_TARKEYS_YK', 'Vähäinen merkitys');
INSERT INTO tietok.tietojarjestelma_kasite_arvo VALUES (133, 'JARJ_TARKEYS_YK', 'Jonkin verran tärkeä');
INSERT INTO tietok.tietojarjestelma_kasite_arvo VALUES (134, 'JARJ_TARKEYS_YK', 'Tärkeä');
INSERT INTO tietok.tietojarjestelma_kasite_arvo VALUES (135, 'JARJ_TARKEYS_YK', 'Erittäin tärkeä');
INSERT INTO tietok.tietojarjestelma_kasite_arvo VALUES (106, 'OMISTAVA_ORGANISAATIO', 'Muut ulkoiset organisaatiot');


--
-- Data for Name: tietojarjpalvelu_kasite_arvo; Type: TABLE DATA; Schema: tietok; Owner: tietokt
--

INSERT INTO tietok.tietojarjpalvelu_kasite_arvo VALUES (1.0000000000, 'Elinkaaritila', 'Suunnitteilla');
INSERT INTO tietok.tietojarjpalvelu_kasite_arvo VALUES (2.0000000000, 'Elinkaaritila', 'Kehityksessä');
INSERT INTO tietok.tietojarjpalvelu_kasite_arvo VALUES (3.0000000000, 'Elinkaaritila', 'Tuotannossa');
INSERT INTO tietok.tietojarjpalvelu_kasite_arvo VALUES (4.0000000000, 'Elinkaaritila', 'Poistumassa');
INSERT INTO tietok.tietojarjpalvelu_kasite_arvo VALUES (5.0000000000, 'Elinkaaritila', 'Poistunut');


--
-- Data for Name: tietoomaisuus_kasite_arvo; Type: TABLE DATA; Schema: tietok; Owner: tietokt
--

INSERT INTO tietok.tietoomaisuus_kasite_arvo VALUES (1, 'PRIMAARIKAYTTOTARVE', 'Tiedon ensisijaista käyttötarvetta ei tiedetä.', 0.0000000000);
INSERT INTO tietok.tietoomaisuus_kasite_arvo VALUES (2, 'PRIMAARIKAYTTOTARVE', 'Tiedon ensisijainen käyttötarve on tunnistettu.', 1.0000000000);
INSERT INTO tietok.tietoomaisuus_kasite_arvo VALUES (3, 'SEKUNDAAR_KAYTTOTARPEET', 'Tiedon toissijaisia käyttötarpeita ei tiedetä.', 0.0000000000);
INSERT INTO tietok.tietoomaisuus_kasite_arvo VALUES (4, 'SEKUNDAAR_KAYTTOTARPEET', 'Tiedon tärkeimmät toissijaiset käyttötarpeet tiedetään tai tiedetään, ettei sellaisia ole.', 1.0000000000);
INSERT INTO tietok.tietoomaisuus_kasite_arvo VALUES (5, 'SEKUNDAAR_KAYTTOTARPEET', 'Tieto vastaa tärkeimpien toissijaisten käyttötarpeiden vaatimuksiin.', 2.0000000000);
INSERT INTO tietok.tietoomaisuus_kasite_arvo VALUES (6, 'KAYTTAJAT', 'Asiakkaita ja käyttäjiä ei ole tunnistettu', 0.0000000000);
INSERT INTO tietok.tietoomaisuus_kasite_arvo VALUES (7, 'KAYTTAJAT', 'Tiedon asiakkaat ja käyttäjät on tunnistettu', 1.0000000000);
INSERT INTO tietok.tietoomaisuus_kasite_arvo VALUES (8, 'KAYTTAJAT', 'Tiedon asiakkaisiin ja käyttäjiin pystytään ottamaan yhteyttä', 2.0000000000);
INSERT INTO tietok.tietoomaisuus_kasite_arvo VALUES (9, 'MALLINNUSTAPA', 'Tietosisältöä ei ole mallinnettu tieto- tai käsitemallien avulla.', 0.0000000000);
INSERT INTO tietok.tietoomaisuus_kasite_arvo VALUES (10, 'MALLINNUSTAPA', 'Tietosisältö on mallinnettu tieto- ja/tai käsitemallien avulla.', 1.0000000000);
INSERT INTO tietok.tietoomaisuus_kasite_arvo VALUES (11, 'MALLINNUSTAPA', ' Mallinnustapa on standardi eli Väylän linjausta noudattava.', 2.0000000000);
INSERT INTO tietok.tietoomaisuus_kasite_arvo VALUES (12, 'KAYTTOFORMAATIT', 'Tiedon käyttöformaatit eivät ole tiedossa.', 0.0000000000);
INSERT INTO tietok.tietoomaisuus_kasite_arvo VALUES (13, 'KAYTTOFORMAATIT', 'Tiedon käyttöformaatit ovat tiedossa ja dokumentoitu.', 1.0000000000);
INSERT INTO tietok.tietoomaisuus_kasite_arvo VALUES (14, 'KAYTTOFORMAATIT', 'Käyttöformaatit ovat jonkin alalla yleisesti hyväksytyn standardin mukaisia.', 2.0000000000);
INSERT INTO tietok.tietoomaisuus_kasite_arvo VALUES (15, 'HISTORIATIEDOT', 'Tiedon historioinnin tarpeellisuutta ei ole pohdittu.', 0.0000000000);
INSERT INTO tietok.tietoomaisuus_kasite_arvo VALUES (16, 'HISTORIATIEDOT', 'Tarve historioinnille on tunnistettu ja tapa historiatiedon hallintaan on kehitetty. Jos historiatietoja ei hallita, peruste on kirjattu.', 1.0000000000);
INSERT INTO tietok.tietoomaisuus_kasite_arvo VALUES (17, 'HISTORIATIEDOT', 'Historiatiedon käyttäminen on suunniteltu siten, että hyödyntäjät saavat käyttöliittymien kautta myös historiatietoja.', 2.0000000000);
INSERT INTO tietok.tietoomaisuus_kasite_arvo VALUES (18, 'KATTAVUUS_JA_LAATU', 'Tiedon kattavuutta ja laatua ei tunneta', 0.0000000000);
INSERT INTO tietok.tietoomaisuus_kasite_arvo VALUES (19, 'KATTAVUUS_JA_LAATU', 'Tiedon kattavuutta ja laatua on osittain kuvattu.', 1.0000000000);
INSERT INTO tietok.tietoomaisuus_kasite_arvo VALUES (20, 'KATTAVUUS_JA_LAATU', 'Laadun valvonta on kattavaa ja sitä on automatisoitu analytiikan avulla.', 2.0000000000);
INSERT INTO tietok.tietoomaisuus_kasite_arvo VALUES (21, 'METATIEDOT', 'Tiedon metatietoja ei ole kuvattu', 0.0000000000);
INSERT INTO tietok.tietoomaisuus_kasite_arvo VALUES (22, 'METATIEDOT', 'Tiedon metatiedot on kuvattu.', 1.0000000000);
INSERT INTO tietok.tietoomaisuus_kasite_arvo VALUES (23, 'METATIEDOT', 'Metatiedot kattavat koko tietoaineiston ja ovat avoimesti saatavilla ', 2.0000000000);
INSERT INTO tietok.tietoomaisuus_kasite_arvo VALUES (24, 'METATIEDOT', 'Metatiedot on tallennettu paikaan, josta niiden hyödyntäminen onnistuu myös rajapinnan kautta.', 3.0000000000);
INSERT INTO tietok.tietoomaisuus_kasite_arvo VALUES (25, 'MUUTOSTIEDOT', 'Tiedon muutoksia ei pystytä tunnistamaan', 0.0000000000);
INSERT INTO tietok.tietoomaisuus_kasite_arvo VALUES (26, 'MUUTOSTIEDOT', 'Tiedon muuttuessa muutokset pystytään tunnistamaan ja yksilöimään', 1.0000000000);
INSERT INTO tietok.tietoomaisuus_kasite_arvo VALUES (27, 'MUUTOSTIEDOT', 'Aineiston hyödyntäminen on mahdollista muutossanomien avulla', 2.0000000000);
INSERT INTO tietok.tietoomaisuus_kasite_arvo VALUES (28, 'SAATAVUUS', 'Tiedon saatavuuden vaatimuksia ei ole tunnistettu', 0.0000000000);
INSERT INTO tietok.tietoomaisuus_kasite_arvo VALUES (29, 'SAATAVUUS', 'Tiedon saatavuuden vaatimukset on tunnistettu sisäisien käyttäjien suhteen.', 1.0000000000);
INSERT INTO tietok.tietoomaisuus_kasite_arvo VALUES (30, 'SAATAVUUS', ' Vaatimuksia on tunnistettu myös ulkoisten käyttäjien suhteen.', 2.0000000000);
INSERT INTO tietok.tietoomaisuus_kasite_arvo VALUES (31, 'HENKILOTIEDOT', 'Ei tiedetä, onko järjestelmässä henkilötietoja.', 0.0000000000);
INSERT INTO tietok.tietoomaisuus_kasite_arvo VALUES (32, 'HENKILOTIEDOT', 'Tiedetään, että järjestelmässä on / ei ole henkilötietoja.', 1.0000000000);
INSERT INTO tietok.tietoomaisuus_kasite_arvo VALUES (33, 'HENKILOTIEDOT', 'Jos henkilötietoja on, niiden olemassaolo on harkittu ja perusteet on kirjattu.', 2.0000000000);
INSERT INTO tietok.tietoomaisuus_kasite_arvo VALUES (34, 'EHEYS', 'Datan eheyttä ei seurata', 0.0000000000);
INSERT INTO tietok.tietoomaisuus_kasite_arvo VALUES (35, 'EHEYS', 'Datan hallitsemattomat tai tahalliset muutokset pystytään havaitsemaan tai on voitu varmistaa, ettei sellaisia voi tapahtua', 1.0000000000);
INSERT INTO tietok.tietoomaisuus_kasite_arvo VALUES (36, 'EHEYS', 'Muutosten aiheuttaja pystytään selvittämään', 2.0000000000);
INSERT INTO tietok.tietoomaisuus_kasite_arvo VALUES (37, 'AVOIMUUS', 'Datan avoimuutta tai ei-avoimuutta ei ole tietolajitasolla päätetty.', 0.0000000000);
INSERT INTO tietok.tietoomaisuus_kasite_arvo VALUES (38, 'AVOIMUUS', 'Data on avointa tai peruste ei-avoimuudelle on kirjattu.', 1.0000000000);
INSERT INTO tietok.tietoomaisuus_kasite_arvo VALUES (39, 'IMMATERIAALIOIKEUDET', 'Tiedon käyttöoikeutta ja lisenssiä ei ole määritelty', 0.0000000000);
INSERT INTO tietok.tietoomaisuus_kasite_arvo VALUES (40, 'IMMATERIAALIOIKEUDET', 'Tiedon käyttöoikeudet ja lisenssit on määritelty', 1.0000000000);
INSERT INTO tietok.tietoomaisuus_kasite_arvo VALUES (41, 'YKSILOIVAT_TUNNISTEET', 'Ei yksilöiviä id:tä', 0.0000000000);
INSERT INTO tietok.tietoomaisuus_kasite_arvo VALUES (42, 'YKSILOIVAT_TUNNISTEET', 'Yksilöivät id:t ovat käytössä jollekin "päätietolajille" tai muulle rajatulle kohdejoukolle', 1.0000000000);
INSERT INTO tietok.tietoomaisuus_kasite_arvo VALUES (43, 'YKSILOIVAT_TUNNISTEET', 'Yksilöivät id:t ovat käytössä järjestelmän kaikille tiedoille', 2.0000000000);
INSERT INTO tietok.tietoomaisuus_kasite_arvo VALUES (44, 'PYSYVYYS', 'Datan pysyvyyttä ei ole tunnistettu', 0.0000000000);
INSERT INTO tietok.tietoomaisuus_kasite_arvo VALUES (45, 'PYSYVYYS', 'On määritelty, onko data staattista vai dynaamista', 1.0000000000);
INSERT INTO tietok.tietoomaisuus_kasite_arvo VALUES (46, 'DOKUMENTAATIO', 'Tietojärjestelmää ei ole dokumentoitu', 0.0000000000);
INSERT INTO tietok.tietoomaisuus_kasite_arvo VALUES (47, 'DOKUMENTAATIO', 'Ei ajantasainen dokumentaatio on olemassa', 1.0000000000);
INSERT INTO tietok.tietoomaisuus_kasite_arvo VALUES (48, 'DOKUMENTAATIO', 'Ajantasainen dokumentaatio on olemassa', 2.0000000000);
INSERT INTO tietok.tietoomaisuus_kasite_arvo VALUES (49, 'ELINKAARI', 'Tietojärjestelmän elinkaarivaihetta ei ole tunnistettu', 0.0000000000);
INSERT INTO tietok.tietoomaisuus_kasite_arvo VALUES (50, 'ELINKAARI', 'Tietojärjestelmän elinkaarivaihe on tunnistettu', 1.0000000000);
INSERT INTO tietok.tietoomaisuus_kasite_arvo VALUES (51, 'ELINKAARI', 'Tietojärjestelmän elinkaarivaiheen seuraava muuttuminen on ajoitettu', 2.0000000000);
INSERT INTO tietok.tietoomaisuus_kasite_arvo VALUES (52, 'ALUSTOJEN_TYYP_JA_SIJ', 'Tietojärjestelmän käyttämien alustojen tyyppiä (konesalit vai pilvipalvelut) ei tiedetä.', 0.0000000000);
INSERT INTO tietok.tietoomaisuus_kasite_arvo VALUES (53, 'ALUSTOJEN_TYYP_JA_SIJ', 'Tietojärjestelmän käyttämien alustojen tyyppi (konesalit vai pilvipalvelut) tiedetään, mutta sijainti (valtiotasolla) ei ole tiedossa.', 1.0000000000);
INSERT INTO tietok.tietoomaisuus_kasite_arvo VALUES (54, 'ALUSTOJEN_TYYP_JA_SIJ', 'Tietojärjestelmän käyttämien alustojen tyyppi (konesalit vai pilvipalvelut) ja sijainti valtiotasolla tiedetään.', 2.0000000000);


--
-- Data for Name: tietovaranto_kasite_arvo; Type: TABLE DATA; Schema: tietok; Owner: tietokt
--

INSERT INTO tietok.tietovaranto_kasite_arvo VALUES (1, 'KASITTELYN_PERUSTEET', 'Lakisääteinen velvoite', NULL);
INSERT INTO tietok.tietovaranto_kasite_arvo VALUES (2, 'KASITTELYN_PERUSTEET', 'Yleinen etu tai julkinen valta', NULL);
INSERT INTO tietok.tietovaranto_kasite_arvo VALUES (3, 'KASITTELYN_PERUSTEET', 'Rekisteröidyn kanssa tehdyn sopimuksen täyttämiseksi', NULL);
INSERT INTO tietok.tietovaranto_kasite_arvo VALUES (4, 'KASITTELYN_PERUSTEET', 'Suostumus', NULL);
INSERT INTO tietok.tietovaranto_kasite_arvo VALUES (5, 'KASITTELYN_PERUSTEET', 'Rekisteröidyn tai toisen luonnollisen henkilön elintärkeiden etujen suojaamiseksi', NULL);
INSERT INTO tietok.tietovaranto_kasite_arvo VALUES (10, 'TIEDONOHJAUSSUUNNITELMAT', '00', 'Johtaminen ja ohjaus');
INSERT INTO tietok.tietovaranto_kasite_arvo VALUES (11, 'TIEDONOHJAUSSUUNNITELMAT', '00.00', 'Strateginen ohjaus');
INSERT INTO tietok.tietovaranto_kasite_arvo VALUES (12, 'TIEDONOHJAUSSUUNNITELMAT', '00.00.00', 'Strategian, arvojen ja toimintalinjojen laatiminen');
INSERT INTO tietok.tietovaranto_kasite_arvo VALUES (13, 'TIEDONOHJAUSSUUNNITELMAT', '00.00.01', 'Valtioneuvoston ja ministeriön strateginen ohjaus');
INSERT INTO tietok.tietovaranto_kasite_arvo VALUES (14, 'TIEDONOHJAUSSUUNNITELMAT', '00.01', 'Johtaminen');
INSERT INTO tietok.tietovaranto_kasite_arvo VALUES (15, 'TIEDONOHJAUSSUUNNITELMAT', '00.01.00', 'Yleinen johtaminen');
INSERT INTO tietok.tietovaranto_kasite_arvo VALUES (16, 'TIEDONOHJAUSSUUNNITELMAT', '00.01.01', 'Laatu- ja turvallisuusjohtamisjärjestelmät');
INSERT INTO tietok.tietovaranto_kasite_arvo VALUES (17, 'TIEDONOHJAUSSUUNNITELMAT', '00.01.02', 'Sisäinen tarkastus');
INSERT INTO tietok.tietovaranto_kasite_arvo VALUES (18, 'TIEDONOHJAUSSUUNNITELMAT', '00.01.03', 'Ulkoinen valvonta, tarkastus ja auditoinnit');
INSERT INTO tietok.tietovaranto_kasite_arvo VALUES (19, 'TIEDONOHJAUSSUUNNITELMAT', '00.01.04', 'Viestintä');
INSERT INTO tietok.tietovaranto_kasite_arvo VALUES (20, 'TIEDONOHJAUSSUUNNITELMAT', '00.01.05', 'Varautuminen');
INSERT INTO tietok.tietovaranto_kasite_arvo VALUES (21, 'TIEDONOHJAUSSUUNNITELMAT', '00.02', 'Tulosohjaus ja toiminnan kehittämisen ohjaus');
INSERT INTO tietok.tietovaranto_kasite_arvo VALUES (22, 'TIEDONOHJAUSSUUNNITELMAT', '00.02.00', 'Tulosohjaus');
INSERT INTO tietok.tietovaranto_kasite_arvo VALUES (23, 'TIEDONOHJAUSSUUNNITELMAT', '00.02.01', 'ELY-ohjaus');
INSERT INTO tietok.tietovaranto_kasite_arvo VALUES (24, 'TIEDONOHJAUSSUUNNITELMAT', '00.02.02', 'Toiminnan kehittämisen ohjaus');
INSERT INTO tietok.tietovaranto_kasite_arvo VALUES (25, 'TIEDONOHJAUSSUUNNITELMAT', '00.03', 'Sidosryhmäyhteistyö');
INSERT INTO tietok.tietovaranto_kasite_arvo VALUES (26, 'TIEDONOHJAUSSUUNNITELMAT', '00.03.00', 'Asiakkuuden hallinta');
INSERT INTO tietok.tietovaranto_kasite_arvo VALUES (27, 'TIEDONOHJAUSSUUNNITELMAT', '00.03.01', 'Kansainvälinen yhteistyö');
INSERT INTO tietok.tietovaranto_kasite_arvo VALUES (28, 'TIEDONOHJAUSSUUNNITELMAT', '00.03.02', 'Ministeriöiden, kansanedustajien ja muiden viranomaisten lausuntopyynnöt ja kyselyt');
INSERT INTO tietok.tietovaranto_kasite_arvo VALUES (29, 'TIEDONOHJAUSSUUNNITELMAT', '00.03.03', 'Säädösvalmisteluun vaikuttaminen');
INSERT INTO tietok.tietovaranto_kasite_arvo VALUES (30, 'TIEDONOHJAUSSUUNNITELMAT', '01', 'Hallinto');
INSERT INTO tietok.tietovaranto_kasite_arvo VALUES (31, 'TIEDONOHJAUSSUUNNITELMAT', '01.00', 'Yleinen hallintoa koskeva ohjaus');
INSERT INTO tietok.tietovaranto_kasite_arvo VALUES (32, 'TIEDONOHJAUSSUUNNITELMAT', '01.01', 'Henkilöstö');
INSERT INTO tietok.tietovaranto_kasite_arvo VALUES (33, 'TIEDONOHJAUSSUUNNITELMAT', '01.01.00', 'Henkilöstöhallinnon ohjaus ja kehittäminen');
INSERT INTO tietok.tietovaranto_kasite_arvo VALUES (34, 'TIEDONOHJAUSSUUNNITELMAT', '01.01.01', 'Palvelusuhdeasiat');
INSERT INTO tietok.tietovaranto_kasite_arvo VALUES (35, 'TIEDONOHJAUSSUUNNITELMAT', '01.01.02', 'Palkkaus ja palkitseminen');
INSERT INTO tietok.tietovaranto_kasite_arvo VALUES (36, 'TIEDONOHJAUSSUUNNITELMAT', '01.01.03', 'Osaamisen kehittäminen');
INSERT INTO tietok.tietovaranto_kasite_arvo VALUES (37, 'TIEDONOHJAUSSUUNNITELMAT', '01.01.04', 'Työhyvinvointi ja työsuojelu');
INSERT INTO tietok.tietovaranto_kasite_arvo VALUES (38, 'TIEDONOHJAUSSUUNNITELMAT', '01.01.05', 'Työnantaja ja henkilöstöjärjestöjen suhteet');
INSERT INTO tietok.tietovaranto_kasite_arvo VALUES (39, 'TIEDONOHJAUSSUUNNITELMAT', '01.02', 'Talous');
INSERT INTO tietok.tietovaranto_kasite_arvo VALUES (40, 'TIEDONOHJAUSSUUNNITELMAT', '01.02.00', 'Talouden suunnittelu ja ohjaaminen');
INSERT INTO tietok.tietovaranto_kasite_arvo VALUES (41, 'TIEDONOHJAUSSUUNNITELMAT', '01.02.01', 'Talouden seuranta ja raportointi');
INSERT INTO tietok.tietovaranto_kasite_arvo VALUES (42, 'TIEDONOHJAUSSUUNNITELMAT', '01.02.02', 'Kirjanpito ja maksuliikenne');
INSERT INTO tietok.tietovaranto_kasite_arvo VALUES (43, 'TIEDONOHJAUSSUUNNITELMAT', '01.02.03', 'EU-rakennerahastojen hallinnointi ja EU-varojen valvonta');
INSERT INTO tietok.tietovaranto_kasite_arvo VALUES (44, 'TIEDONOHJAUSSUUNNITELMAT', '01.03', 'Tieto');
INSERT INTO tietok.tietovaranto_kasite_arvo VALUES (45, 'TIEDONOHJAUSSUUNNITELMAT', '01.03.00', 'Tiedonhallinnan kehittäminen ja ohjaaminen');
INSERT INTO tietok.tietovaranto_kasite_arvo VALUES (46, 'TIEDONOHJAUSSUUNNITELMAT', '01.03.01', 'Tietopalvelut (asiakirja-, julkaisu- ja kirjastopalvelut, tilastot)');
INSERT INTO tietok.tietovaranto_kasite_arvo VALUES (47, 'TIEDONOHJAUSSUUNNITELMAT', '01.03.02', 'yhteiskäyttöiset ICT-palvelut');
INSERT INTO tietok.tietovaranto_kasite_arvo VALUES (48, 'TIEDONOHJAUSSUUNNITELMAT', '01.04', 'Muut hallintopalvelut');
INSERT INTO tietok.tietovaranto_kasite_arvo VALUES (49, 'TIEDONOHJAUSSUUNNITELMAT', '01.04.00', 'Toimitilat ja niihin liittyvät palvelut');
INSERT INTO tietok.tietovaranto_kasite_arvo VALUES (50, 'TIEDONOHJAUSSUUNNITELMAT', '01.04.01', 'Muu turvallisuus');
INSERT INTO tietok.tietovaranto_kasite_arvo VALUES (51, 'TIEDONOHJAUSSUUNNITELMAT', '01.04.02', 'Matkustuspalvelut');
INSERT INTO tietok.tietovaranto_kasite_arvo VALUES (52, 'TIEDONOHJAUSSUUNNITELMAT', '01.04.03', 'Muut tukipalvelut');
INSERT INTO tietok.tietovaranto_kasite_arvo VALUES (53, 'TIEDONOHJAUSSUUNNITELMAT', '01.04.04', 'Museo- ja perinneasiat');
INSERT INTO tietok.tietovaranto_kasite_arvo VALUES (54, 'TIEDONOHJAUSSUUNNITELMAT', '02', 'Hankinta');
INSERT INTO tietok.tietovaranto_kasite_arvo VALUES (55, 'TIEDONOHJAUSSUUNNITELMAT', '02.00', 'Hankinnan ohjaus');
INSERT INTO tietok.tietovaranto_kasite_arvo VALUES (56, 'TIEDONOHJAUSSUUNNITELMAT', '02.01', 'Liikenneväylien ja -järjestelmien hankinnat');
INSERT INTO tietok.tietovaranto_kasite_arvo VALUES (57, 'TIEDONOHJAUSSUUNNITELMAT', '02.01.00', 'Suunnittelu, hankintakategoria');
INSERT INTO tietok.tietovaranto_kasite_arvo VALUES (58, 'TIEDONOHJAUSSUUNNITELMAT', '02.01.01', 'Investointien toteutus, hankintakategoria');
INSERT INTO tietok.tietovaranto_kasite_arvo VALUES (59, 'TIEDONOHJAUSSUUNNITELMAT', '02.01.02', 'Hoito ja käyttö, hankintakategoria');
INSERT INTO tietok.tietovaranto_kasite_arvo VALUES (60, 'TIEDONOHJAUSSUUNNITELMAT', '02.01.03', 'Maantielauttaliikenne, hankintakategoria');
INSERT INTO tietok.tietovaranto_kasite_arvo VALUES (61, 'TIEDONOHJAUSSUUNNITELMAT', '02.01.04', 'Meriliikenteen avustuspalvelut, hankintakategoria');
INSERT INTO tietok.tietovaranto_kasite_arvo VALUES (62, 'TIEDONOHJAUSSUUNNITELMAT', '02.01.05', 'Liikenteenohjaus, hankintakategoria');
INSERT INTO tietok.tietovaranto_kasite_arvo VALUES (63, 'TIEDONOHJAUSSUUNNITELMAT', '02.01.06', 'Korjaus, hankintakategoria');
INSERT INTO tietok.tietovaranto_kasite_arvo VALUES (64, 'TIEDONOHJAUSSUUNNITELMAT', '02.01.07', 'Teettämis- ja hankintapalvelut, hankintakategoria');
INSERT INTO tietok.tietovaranto_kasite_arvo VALUES (65, 'TIEDONOHJAUSSUUNNITELMAT', '02.01.08', 'Tekniset järjestelmät ja laitteet, hankintakategoria');
INSERT INTO tietok.tietovaranto_kasite_arvo VALUES (66, 'TIEDONOHJAUSSUUNNITELMAT', '02.01.09', 'T&K, hankintakategoria');
INSERT INTO tietok.tietovaranto_kasite_arvo VALUES (67, 'TIEDONOHJAUSSUUNNITELMAT', '02.01.10', 'Tiedot ja mittauspalvelut, hankintakategoria');
INSERT INTO tietok.tietovaranto_kasite_arvo VALUES (68, 'TIEDONOHJAUSSUUNNITELMAT', '02.01.11', 'Tietojärjestelmät ja -palvelut, hankintakategoria');
INSERT INTO tietok.tietovaranto_kasite_arvo VALUES (69, 'TIEDONOHJAUSSUUNNITELMAT', '02.01.12', 'Asiantuntijapalvelut, hankintakategoria');
INSERT INTO tietok.tietovaranto_kasite_arvo VALUES (70, 'TIEDONOHJAUSSUUNNITELMAT', '02.01.13', 'Radanpidon materiaalit, hankintakategoria');
INSERT INTO tietok.tietovaranto_kasite_arvo VALUES (71, 'TIEDONOHJAUSSUUNNITELMAT', '02.02', 'Oman toiminnan hankinnat');
INSERT INTO tietok.tietovaranto_kasite_arvo VALUES (72, 'TIEDONOHJAUSSUUNNITELMAT', '02.02.00', 'Hallintopalvelut, hankintakategoria');
INSERT INTO tietok.tietovaranto_kasite_arvo VALUES (73, 'TIEDONOHJAUSSUUNNITELMAT', '02.02.01', 'ICT-palvelut, hankintakategoria');
INSERT INTO tietok.tietovaranto_kasite_arvo VALUES (74, 'TIEDONOHJAUSSUUNNITELMAT', '03', 'Palvelutason määrittely ja toteutuksen suunnittelu');
INSERT INTO tietok.tietovaranto_kasite_arvo VALUES (75, 'TIEDONOHJAUSSUUNNITELMAT', '03.00', 'Palvelutason ohjaus ja kehittäminen');
INSERT INTO tietok.tietovaranto_kasite_arvo VALUES (76, 'TIEDONOHJAUSSUUNNITELMAT', '03.00.00', 'Liikennejärjestelmäsuunnittelun ohjaus ja kehittäminen');
INSERT INTO tietok.tietovaranto_kasite_arvo VALUES (77, 'TIEDONOHJAUSSUUNNITELMAT', '03.00.01', 'Liikenne- ja toimintaympäristötutkimukset ja -analyysit');
INSERT INTO tietok.tietovaranto_kasite_arvo VALUES (78, 'TIEDONOHJAUSSUUNNITELMAT', '03.00.02', 'Liikenneturvallisuus');
INSERT INTO tietok.tietovaranto_kasite_arvo VALUES (79, 'TIEDONOHJAUSSUUNNITELMAT', '03.01', 'Palvelutason määrittely');
INSERT INTO tietok.tietovaranto_kasite_arvo VALUES (80, 'TIEDONOHJAUSSUUNNITELMAT', '03.01.00', 'Pitkän aikavälin vaihtoehtotarkastelut');
INSERT INTO tietok.tietovaranto_kasite_arvo VALUES (81, 'TIEDONOHJAUSSUUNNITELMAT', '03.01.01', 'Liikkumisen ja kuljettamisen palvelutasosuunnittelu');
INSERT INTO tietok.tietovaranto_kasite_arvo VALUES (82, 'TIEDONOHJAUSSUUNNITELMAT', '03.01.02', 'Maankäyttöasiat');
INSERT INTO tietok.tietovaranto_kasite_arvo VALUES (83, 'TIEDONOHJAUSSUUNNITELMAT', '03.02', 'Palvelutason toteutuksen suunnittelu');
INSERT INTO tietok.tietovaranto_kasite_arvo VALUES (84, 'TIEDONOHJAUSSUUNNITELMAT', '03.02.00', 'Esisuunnittelu ja tarveselvitys');
INSERT INTO tietok.tietovaranto_kasite_arvo VALUES (85, 'TIEDONOHJAUSSUUNNITELMAT', '03.02.01', 'Tarve- ja suunnitteluohjelmien valmistelu');
INSERT INTO tietok.tietovaranto_kasite_arvo VALUES (86, 'TIEDONOHJAUSSUUNNITELMAT', '04', 'Väyläverkon kehittäminen');
INSERT INTO tietok.tietovaranto_kasite_arvo VALUES (87, 'TIEDONOHJAUSSUUNNITELMAT', '04.00', 'Väylähankesuunnittelun ja toteutuksen ohjaus ja kehittäminen');
INSERT INTO tietok.tietovaranto_kasite_arvo VALUES (88, 'TIEDONOHJAUSSUUNNITELMAT', '04.01', 'Väylähankesuunnittelu');
INSERT INTO tietok.tietovaranto_kasite_arvo VALUES (89, 'TIEDONOHJAUSSUUNNITELMAT', '04.01.00', 'Yleissuunnittelu');
INSERT INTO tietok.tietovaranto_kasite_arvo VALUES (90, 'TIEDONOHJAUSSUUNNITELMAT', '04.01.01', 'Tie- ja ratasuunnittelu');
INSERT INTO tietok.tietovaranto_kasite_arvo VALUES (91, 'TIEDONOHJAUSSUUNNITELMAT', '04.01.02', 'Vesiliikennehankkeen suunnittelu ja vesilupa');
INSERT INTO tietok.tietovaranto_kasite_arvo VALUES (92, 'TIEDONOHJAUSSUUNNITELMAT', '04.01.03', 'Hanketason esisuunnittelu');
INSERT INTO tietok.tietovaranto_kasite_arvo VALUES (93, 'TIEDONOHJAUSSUUNNITELMAT', '04.02', 'Väylähankehallinta ja hankkeiden toteutus');
INSERT INTO tietok.tietovaranto_kasite_arvo VALUES (94, 'TIEDONOHJAUSSUUNNITELMAT', '04.02.00', 'Toteutussalkun hallinta');
INSERT INTO tietok.tietovaranto_kasite_arvo VALUES (95, 'TIEDONOHJAUSSUUNNITELMAT', '04.02.01', 'Toteutushankkeen läpivienti');
INSERT INTO tietok.tietovaranto_kasite_arvo VALUES (96, 'TIEDONOHJAUSSUUNNITELMAT', '04.02.02', 'Väylähankkeiden toteuttamiseen tarvittava maanhankinta');
INSERT INTO tietok.tietovaranto_kasite_arvo VALUES (97, 'TIEDONOHJAUSSUUNNITELMAT', '05', 'Väyläverkon kunnossapito');
INSERT INTO tietok.tietovaranto_kasite_arvo VALUES (98, 'TIEDONOHJAUSSUUNNITELMAT', '05.00', 'Kunnon hallinan ohjaus ja kehittäminen');
INSERT INTO tietok.tietovaranto_kasite_arvo VALUES (99, 'TIEDONOHJAUSSUUNNITELMAT', '05.01', 'Hoito ja käyttö');
INSERT INTO tietok.tietovaranto_kasite_arvo VALUES (100, 'TIEDONOHJAUSSUUNNITELMAT', '05.01.00', 'Rata, hoidon ja käytön suunnittelu ja toteutus');
INSERT INTO tietok.tietovaranto_kasite_arvo VALUES (101, 'TIEDONOHJAUSSUUNNITELMAT', '05.01.01', 'Tie, hoidon ja käytön suunnittelu ja toteutus');
INSERT INTO tietok.tietovaranto_kasite_arvo VALUES (102, 'TIEDONOHJAUSSUUNNITELMAT', '05.01.02', 'Vesiväylät ja kanavat, hoidon ja käytön suunnittelu ja toteutus');
INSERT INTO tietok.tietovaranto_kasite_arvo VALUES (103, 'TIEDONOHJAUSSUUNNITELMAT', '05.02', 'Ylläpito');
INSERT INTO tietok.tietovaranto_kasite_arvo VALUES (104, 'TIEDONOHJAUSSUUNNITELMAT', '05.02.00', 'Rata, ylläpidon ja kohteiden suunnittelu ja toteutus');
INSERT INTO tietok.tietovaranto_kasite_arvo VALUES (105, 'TIEDONOHJAUSSUUNNITELMAT', '05.02.01', 'Tie, ylläpidon ja kohteiden suunnittelu ja toteutus');
INSERT INTO tietok.tietovaranto_kasite_arvo VALUES (106, 'TIEDONOHJAUSSUUNNITELMAT', '05.02.02', 'Vesiväylät ja kanavat, ylläpidon ja kohteiden suunnittelu ja toteutus');
INSERT INTO tietok.tietovaranto_kasite_arvo VALUES (107, 'TIEDONOHJAUSSUUNNITELMAT', '06', 'Väylänpidon tukitehtävät');
INSERT INTO tietok.tietovaranto_kasite_arvo VALUES (108, 'TIEDONOHJAUSSUUNNITELMAT', '06.00', 'Kiinteistö- ja ympäristöasiat');
INSERT INTO tietok.tietovaranto_kasite_arvo VALUES (109, 'TIEDONOHJAUSSUUNNITELMAT', '06.00.00', 'Kiinteistö- ja ympäristöasioiden ohjaus ja kehittäminen');
INSERT INTO tietok.tietovaranto_kasite_arvo VALUES (110, 'TIEDONOHJAUSSUUNNITELMAT', '06.00.01', 'Kiinteistöjen, rakennusten ja muun omaisuuden hallinta');
INSERT INTO tietok.tietovaranto_kasite_arvo VALUES (111, 'TIEDONOHJAUSSUUNNITELMAT', '06.00.02', 'Maanmittaustoimitukset');
INSERT INTO tietok.tietovaranto_kasite_arvo VALUES (112, 'TIEDONOHJAUSSUUNNITELMAT', '06.00.03', 'Ympäristöasiat');
INSERT INTO tietok.tietovaranto_kasite_arvo VALUES (113, 'TIEDONOHJAUSSUUNNITELMAT', '06.01', 'T&K');
INSERT INTO tietok.tietovaranto_kasite_arvo VALUES (114, 'TIEDONOHJAUSSUUNNITELMAT', '06.01.00', 'T&K hankkeet');
INSERT INTO tietok.tietovaranto_kasite_arvo VALUES (115, 'TIEDONOHJAUSSUUNNITELMAT', '06.01.01', 'Väyläalan koulutuksen järjestäminen ja osaamisen kehittäminen');
INSERT INTO tietok.tietovaranto_kasite_arvo VALUES (116, 'TIEDONOHJAUSSUUNNITELMAT', '06.02', 'Väylänpidon viranomaistehtävät');
INSERT INTO tietok.tietovaranto_kasite_arvo VALUES (117, 'TIEDONOHJAUSSUUNNITELMAT', '06.02.00', 'Viranomaistehtävien ohjaus ja kehittäminen');
INSERT INTO tietok.tietovaranto_kasite_arvo VALUES (118, 'TIEDONOHJAUSSUUNNITELMAT', '06.02.01', 'Radanpidon viranomaistehtävät');
INSERT INTO tietok.tietovaranto_kasite_arvo VALUES (119, 'TIEDONOHJAUSSUUNNITELMAT', '06.02.02', 'Vesiväylänpidon viranomaistehtävät');
INSERT INTO tietok.tietovaranto_kasite_arvo VALUES (120, 'TIEDONOHJAUSSUUNNITELMAT', '06.02.03', 'Oikeudelliset asiat');
INSERT INTO tietok.tietovaranto_kasite_arvo VALUES (121, 'TIEDONOHJAUSSUUNNITELMAT', '06.02.04', 'Liikenneonnettomuudet ja poikkeamatilanteet, onnettomuustutkinta');
INSERT INTO tietok.tietovaranto_kasite_arvo VALUES (122, 'TIEDONOHJAUSSUUNNITELMAT', '06.03', 'Väylätietojen hallinta');
INSERT INTO tietok.tietovaranto_kasite_arvo VALUES (123, 'TIEDONOHJAUSSUUNNITELMAT', '06.03.00', 'Väylä- ja liikennetietojen hallinta ja tietopalvelut');
INSERT INTO tietok.tietovaranto_kasite_arvo VALUES (124, 'TIEDONOHJAUSSUUNNITELMAT', '06.04', 'Väylätekniset asiat');
INSERT INTO tietok.tietovaranto_kasite_arvo VALUES (125, 'TIEDONOHJAUSSUUNNITELMAT', '06.04.00', 'Teknisten asioiden ohjaus ja kehittäminen');
INSERT INTO tietok.tietovaranto_kasite_arvo VALUES (126, 'TIEDONOHJAUSSUUNNITELMAT', '06.04.01', 'Tekniset ohjeet ja laatuvaatimukset');
INSERT INTO tietok.tietovaranto_kasite_arvo VALUES (127, 'TIEDONOHJAUSSUUNNITELMAT', '06.04.02', 'Tekniset hyväksynnät ja tyyppihyväksynnät');
INSERT INTO tietok.tietovaranto_kasite_arvo VALUES (128, 'TIEDONOHJAUSSUUNNITELMAT', '06.04.03', 'Liikkuvan kaluston ja väylien yhteentoimivuus');
INSERT INTO tietok.tietovaranto_kasite_arvo VALUES (129, 'TIEDONOHJAUSSUUNNITELMAT', '07', 'Liikenteen palvelut');
INSERT INTO tietok.tietovaranto_kasite_arvo VALUES (130, 'TIEDONOHJAUSSUUNNITELMAT', '07.00', 'Joukkoliikenne');
INSERT INTO tietok.tietovaranto_kasite_arvo VALUES (131, 'TIEDONOHJAUSSUUNNITELMAT', '07.01', 'Liikenneverkon hallinta');
INSERT INTO tietok.tietovaranto_kasite_arvo VALUES (132, 'TIEDONOHJAUSSUUNNITELMAT', '07.01.00', 'Liikenneverkon hallinnan ohjaus ja kehittäminen');
INSERT INTO tietok.tietovaranto_kasite_arvo VALUES (133, 'TIEDONOHJAUSSUUNNITELMAT', '07.01.01', 'Rataverkon käytön hallinta');
INSERT INTO tietok.tietovaranto_kasite_arvo VALUES (134, 'TIEDONOHJAUSSUUNNITELMAT', '07.01.02', 'Talvimerenkulku');
INSERT INTO tietok.tietovaranto_kasite_arvo VALUES (135, 'TIEDONOHJAUSSUUNNITELMAT', '07.02', 'Liikenteen ohjaus');
INSERT INTO tietok.tietovaranto_kasite_arvo VALUES (136, 'TIEDONOHJAUSSUUNNITELMAT', '07.02.00', 'Liikenteenohjauksen ohjaus ja kehittäminen');
INSERT INTO tietok.tietovaranto_kasite_arvo VALUES (137, 'TIEDONOHJAUSSUUNNITELMAT', '07.02.01', 'Meriliikenteenohjaus');
INSERT INTO tietok.tietovaranto_kasite_arvo VALUES (138, 'TIEDONOHJAUSSUUNNITELMAT', '07.02.02', 'Rataliikenteenohjaus');
INSERT INTO tietok.tietovaranto_kasite_arvo VALUES (139, 'TIEDONOHJAUSSUUNNITELMAT', '07.02.03', 'Tieliikenteenohjaus');


--
-- Data for Name: toimintaprosessi_kasite_arvo; Type: TABLE DATA; Schema: tietok; Owner: tietokt
--



--
-- PostgreSQL database dump complete
--

