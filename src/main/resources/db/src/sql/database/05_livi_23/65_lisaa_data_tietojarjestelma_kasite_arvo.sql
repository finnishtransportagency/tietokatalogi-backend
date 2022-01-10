-- ANALPK-1397 Uusi Elinkaari-alasvetovalikko Järjestelmälinkkaus-osioon

-- Add previously missing kasite_arvo values.
-- These rows were in test and prod DBs but they were not included in DB scripts before.

INSERT INTO TIETOJARJESTELMA_KASITE_ARVO (KASITE_WID, KASITE, ARVO) VALUES(1, 'TURVALLISUUSLUOKKA', 'Ei turvallisuuskriittisyyttä');
INSERT INTO TIETOJARJESTELMA_KASITE_ARVO (KASITE_WID, KASITE, ARVO) VALUES(2, 'TURVALLISUUSLUOKKA', 'Turvallisuuskriittinen');
INSERT INTO TIETOJARJESTELMA_KASITE_ARVO (KASITE_WID, KASITE, ARVO) VALUES(3, 'PALVELUAIKA', 'virka-aika (8-16)');
INSERT INTO TIETOJARJESTELMA_KASITE_ARVO (KASITE_WID, KASITE, ARVO) VALUES(4, 'PALVELUAIKA', 'jatkuva 24h (24/7)');
INSERT INTO TIETOJARJESTELMA_KASITE_ARVO (KASITE_WID, KASITE, ARVO) VALUES(5, 'PALVELUAIKA', 'pidennetty virka-aika (7-20)');
INSERT INTO TIETOJARJESTELMA_KASITE_ARVO (KASITE_WID, KASITE, ARVO) VALUES(9, 'ICT_VARAUTUMISEN_LUOKITUS', 'Perus');
INSERT INTO TIETOJARJESTELMA_KASITE_ARVO (KASITE_WID, KASITE, ARVO) VALUES(10, 'ICT_VARAUTUMISEN_LUOKITUS', 'Korotettu');
INSERT INTO TIETOJARJESTELMA_KASITE_ARVO (KASITE_WID, KASITE, ARVO) VALUES(11, 'ICT_VARAUTUMISEN_LUOKITUS', 'Korkea');
INSERT INTO TIETOJARJESTELMA_KASITE_ARVO (KASITE_WID, KASITE, ARVO) VALUES(12, 'SLA_LUOKITUS_KAYTTOPALVELU', 'Normaali');
INSERT INTO TIETOJARJESTELMA_KASITE_ARVO (KASITE_WID, KASITE, ARVO) VALUES(13, 'SLA_LUOKITUS_KAYTTOPALVELU', 'Laajennettu');
INSERT INTO TIETOJARJESTELMA_KASITE_ARVO (KASITE_WID, KASITE, ARVO) VALUES(14, 'SLA_LUOKITUS_KAYTTOPALVELU', 'Kriittinen');
INSERT INTO TIETOJARJESTELMA_KASITE_ARVO (KASITE_WID, KASITE, ARVO) VALUES(15, 'SLA_LUOKITUS_KAYTTOPALVELU', 'Erittäin kriittinen');
INSERT INTO TIETOJARJESTELMA_KASITE_ARVO (KASITE_WID, KASITE, ARVO) VALUES(16, 'Toteutusteknologia', 'Weblogic');
INSERT INTO TIETOJARJESTELMA_KASITE_ARVO (KASITE_WID, KASITE, ARVO) VALUES(17, 'Toteutusteknologia', 'OC4J');
INSERT INTO TIETOJARJESTELMA_KASITE_ARVO (KASITE_WID, KASITE, ARVO) VALUES(18, 'Toteutusteknologia', 'JBOSS');
INSERT INTO TIETOJARJESTELMA_KASITE_ARVO (KASITE_WID, KASITE, ARVO) VALUES(19, 'Toteutusteknologia', '.NET');
INSERT INTO TIETOJARJESTELMA_KASITE_ARVO (KASITE_WID, KASITE, ARVO) VALUES(20, 'PORTAALIPALVELU', 'Extranet');
INSERT INTO TIETOJARJESTELMA_KASITE_ARVO (KASITE_WID, KASITE, ARVO) VALUES(21, 'PORTAALIPALVELU', 'Intranet');
INSERT INTO TIETOJARJESTELMA_KASITE_ARVO (KASITE_WID, KASITE, ARVO) VALUES(22, 'PORTAALIPALVELU', 'Järjestelmän sisäinen');
INSERT INTO TIETOJARJESTELMA_KASITE_ARVO (KASITE_WID, KASITE, ARVO) VALUES(23, 'TEKNINEN_ELINKAARI', 'normaali');
INSERT INTO TIETOJARJESTELMA_KASITE_ARVO (KASITE_WID, KASITE, ARVO) VALUES(24, 'TEKNINEN_ELINKAARI', 'hyvä');
INSERT INTO TIETOJARJESTELMA_KASITE_ARVO (KASITE_WID, KASITE, ARVO) VALUES(25, 'TEKNINEN_ELINKAARI', 'erittäin hyvä');
INSERT INTO TIETOJARJESTELMA_KASITE_ARVO (KASITE_WID, KASITE, ARVO) VALUES(26, 'TEKNINEN_ELINKAARI', 'erittäin huono');
INSERT INTO TIETOJARJESTELMA_KASITE_ARVO (KASITE_WID, KASITE, ARVO) VALUES(27, 'TEKNINEN_ELINKAARI', 'huono');
INSERT INTO TIETOJARJESTELMA_KASITE_ARVO (KASITE_WID, KASITE, ARVO) VALUES(28, 'KAYTTOOIKEUKSIEN_HALLINTA', 'järjestelmän oma hallinta');
INSERT INTO TIETOJARJESTELMA_KASITE_ARVO (KASITE_WID, KASITE, ARVO) VALUES(29, 'KAYTTOOIKEUKSIEN_HALLINTA', 'käyttöpalvelu');
INSERT INTO TIETOJARJESTELMA_KASITE_ARVO (KASITE_WID, KASITE, ARVO) VALUES(30, 'SLA_LUOKITUS_JHS', 'Normaali');
INSERT INTO TIETOJARJESTELMA_KASITE_ARVO (KASITE_WID, KASITE, ARVO) VALUES(31, 'SLA_LUOKITUS_JHS', 'Laajennettu');
INSERT INTO TIETOJARJESTELMA_KASITE_ARVO (KASITE_WID, KASITE, ARVO) VALUES(32, 'SLA_LUOKITUS_JHS', 'Kriittinen');
INSERT INTO TIETOJARJESTELMA_KASITE_ARVO (KASITE_WID, KASITE, ARVO) VALUES(33, 'SLA_LUOKITUS_JHS', 'Erittäin kriittinen');
INSERT INTO TIETOJARJESTELMA_KASITE_ARVO (KASITE_WID, KASITE, ARVO) VALUES(34, 'KRIITTISYYS', 'Normaali');
INSERT INTO TIETOJARJESTELMA_KASITE_ARVO (KASITE_WID, KASITE, ARVO) VALUES(35, 'KRIITTISYYS', 'Korkea');
INSERT INTO TIETOJARJESTELMA_KASITE_ARVO (KASITE_WID, KASITE, ARVO) VALUES(36, 'KRIITTISYYS', 'Matala');
INSERT INTO TIETOJARJESTELMA_KASITE_ARVO (KASITE_WID, KASITE, ARVO) VALUES(37, 'KRIITTISYYS', 'Erittäin korkea');
INSERT INTO TIETOJARJESTELMA_KASITE_ARVO (KASITE_WID, KASITE, ARVO) VALUES(38, 'KRIITTISYYS', 'Erittäin matala');
INSERT INTO TIETOJARJESTELMA_KASITE_ARVO (KASITE_WID, KASITE, ARVO) VALUES(39, 'TOIMINNALLINEN_ELINKAARI', 'vakiintunut käyttö');
INSERT INTO TIETOJARJESTELMA_KASITE_ARVO (KASITE_WID, KASITE, ARVO) VALUES(40, 'TOIMINNALLINEN_ELINKAARI', 'poistunut');
INSERT INTO TIETOJARJESTELMA_KASITE_ARVO (KASITE_WID, KASITE, ARVO) VALUES(41, 'TOIMINNALLINEN_ELINKAARI', 'kehitteillä');
INSERT INTO TIETOJARJESTELMA_KASITE_ARVO (KASITE_WID, KASITE, ARVO) VALUES(42, 'TOIMINNALLINEN_ELINKAARI', 'poistossa');
INSERT INTO TIETOJARJESTELMA_KASITE_ARVO (KASITE_WID, KASITE, ARVO) VALUES(43, 'TOIMINNALLINEN_ELINKAARI', 'rajoitettu käyttö');
INSERT INTO TIETOJARJESTELMA_KASITE_ARVO (KASITE_WID, KASITE, ARVO) VALUES(45, 'JARJESTELMAN_TARKEYS_LIVILLE', 'Jonkin verran tärkeä');
INSERT INTO TIETOJARJESTELMA_KASITE_ARVO (KASITE_WID, KASITE, ARVO) VALUES(46, 'JARJESTELMAN_TARKEYS_LIVILLE', 'Tärkeä');
INSERT INTO TIETOJARJESTELMA_KASITE_ARVO (KASITE_WID, KASITE, ARVO) VALUES(49, 'JARJ_TARKEYS_YHTEISTYOKUMP', 'Jonkin verran tärkeä');
INSERT INTO TIETOJARJESTELMA_KASITE_ARVO (KASITE_WID, KASITE, ARVO) VALUES(50, 'JARJ_TARKEYS_YHTEISTYOKUMP', 'Tärkeä');
INSERT INTO TIETOJARJESTELMA_KASITE_ARVO (KASITE_WID, KASITE, ARVO) VALUES(52, 'Pääsynhallinta', 'AD');
INSERT INTO TIETOJARJESTELMA_KASITE_ARVO (KASITE_WID, KASITE, ARVO) VALUES(53, 'Pääsynhallinta', 'OID');
INSERT INTO TIETOJARJESTELMA_KASITE_ARVO (KASITE_WID, KASITE, ARVO) VALUES(54, 'Pääsynhallinta', 'Järjestelmän sisäinen');
INSERT INTO TIETOJARJESTELMA_KASITE_ARVO (KASITE_WID, KASITE, ARVO) VALUES(55, 'TARKEYS_YK', 'Vähäinen');
INSERT INTO TIETOJARJESTELMA_KASITE_ARVO (KASITE_WID, KASITE, ARVO) VALUES(56, 'TARKEYS_YK', 'Jonkin verran tärkeä');
INSERT INTO TIETOJARJESTELMA_KASITE_ARVO (KASITE_WID, KASITE, ARVO) VALUES(57, 'TARKEYS_YK', 'Tärkeä');
INSERT INTO TIETOJARJESTELMA_KASITE_ARVO (KASITE_WID, KASITE, ARVO) VALUES(58, 'TARKEYS_YK', 'Elintärkeä');
INSERT INTO TIETOJARJESTELMA_KASITE_ARVO (KASITE_WID, KASITE, ARVO) VALUES(59, 'TIETOJEN_JULKISUUS', 'Julkinen');
INSERT INTO TIETOJARJESTELMA_KASITE_ARVO (KASITE_WID, KASITE, ARVO) VALUES(64, 'Palvelin', 'Windows 2003 Server');
INSERT INTO TIETOJARJESTELMA_KASITE_ARVO (KASITE_WID, KASITE, ARVO) VALUES(65, 'Palvelin', 'Windows 2008R2');
INSERT INTO TIETOJARJESTELMA_KASITE_ARVO (KASITE_WID, KASITE, ARVO) VALUES(66, 'Palvelin', 'Windows 2012R2');
INSERT INTO TIETOJARJESTELMA_KASITE_ARVO (KASITE_WID, KASITE, ARVO) VALUES(67, 'Jarjestelmatyyppi', 'lisenssi');
INSERT INTO TIETOJARJESTELMA_KASITE_ARVO (KASITE_WID, KASITE, ARVO) VALUES(68, 'Jarjestelmatyyppi', 'palvelu');
INSERT INTO TIETOJARJESTELMA_KASITE_ARVO (KASITE_WID, KASITE, ARVO) VALUES(69, 'Jarjestelmatyyppi', 'sovellus');
INSERT INTO TIETOJARJESTELMA_KASITE_ARVO (KASITE_WID, KASITE, ARVO) VALUES(70, 'Jarjestelmatyyppi', 'sulautettu järjestelmä');
INSERT INTO TIETOJARJESTELMA_KASITE_ARVO (KASITE_WID, KASITE, ARVO) VALUES(71, 'Jarjestelmatyyppi', 'tietojärjestelmä');
INSERT INTO TIETOJARJESTELMA_KASITE_ARVO (KASITE_WID, KASITE, ARVO) VALUES(72, 'Elinkaaritila', 'Suunnitteilla');
INSERT INTO TIETOJARJESTELMA_KASITE_ARVO (KASITE_WID, KASITE, ARVO) VALUES(73, 'Elinkaaritila', 'Kehityksessä');
INSERT INTO TIETOJARJESTELMA_KASITE_ARVO (KASITE_WID, KASITE, ARVO) VALUES(74, 'Elinkaaritila', 'Tuotannossa');
INSERT INTO TIETOJARJESTELMA_KASITE_ARVO (KASITE_WID, KASITE, ARVO) VALUES(75, 'Elinkaaritila', 'Poistumassa');
INSERT INTO TIETOJARJESTELMA_KASITE_ARVO (KASITE_WID, KASITE, ARVO) VALUES(76, 'Elinkaaritila', 'Poistunut');
INSERT INTO TIETOJARJESTELMA_KASITE_ARVO (KASITE_WID, KASITE, ARVO) VALUES(77, 'Jarjestelmaalue', 'LiHa - Meri');
INSERT INTO TIETOJARJESTELMA_KASITE_ARVO (KASITE_WID, KASITE, ARVO) VALUES(78, 'Jarjestelmaalue', 'LiHa - Rata');
INSERT INTO TIETOJARJESTELMA_KASITE_ARVO (KASITE_WID, KASITE, ARVO) VALUES(79, 'Jarjestelmaalue', 'LiHa - Tie');
INSERT INTO TIETOJARJESTELMA_KASITE_ARVO (KASITE_WID, KASITE, ARVO) VALUES(80, 'Jarjestelmaalue', 'Liikenteen palvelut');
INSERT INTO TIETOJARJESTELMA_KASITE_ARVO (KASITE_WID, KASITE, ARVO) VALUES(81, 'Jarjestelmaalue', 'VTJ - Meri');
INSERT INTO TIETOJARJESTELMA_KASITE_ARVO (KASITE_WID, KASITE, ARVO) VALUES(82, 'Jarjestelmaalue', 'VTJ - Rata');
INSERT INTO TIETOJARJESTELMA_KASITE_ARVO (KASITE_WID, KASITE, ARVO) VALUES(83, 'Jarjestelmaalue', 'VTJ - Tie');
INSERT INTO TIETOJARJESTELMA_KASITE_ARVO (KASITE_WID, KASITE, ARVO) VALUES(84, 'Jarjestelmaalue', 'Yhteinen ICT');
