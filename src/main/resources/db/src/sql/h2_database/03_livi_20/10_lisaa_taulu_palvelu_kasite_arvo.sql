CREATE TABLE PALVELU_KASITE_ARVO (
"KASITE_ID" NUMBER NOT NULL,
"KASITE" VARCHAR2(100),
"ARVO" VARCHAR2(200),
PRIMARY KEY ("KASITE_ID"));

-- * Data
-- ** Palvelukatalogi
--    - PALVELU_KASITE_ARVO
insert into palvelu_kasite_arvo (KASITE_ID, KASITE, ARVO) values(1, 'Asiointipalvelut', 'Asiointipalvelut');
insert into palvelu_kasite_arvo (KASITE_ID, KASITE, ARVO) values(2, 'ICT -infrapalvelut', 'Infran elinkaaren hallintapalvelut');
insert into palvelu_kasite_arvo (KASITE_ID, KASITE, ARVO) values(3, 'ICT -infrapalvelut', 'Palveluiden elinkaaren hallinta (SOA -toimisto)');
insert into palvelu_kasite_arvo (KASITE_ID, KASITE, ARVO) values(4, 'ICT -infrapalvelut', 'Tietoliikenteen elinkaaren hallintapalvelut');
insert into palvelu_kasite_arvo (KASITE_ID, KASITE, ARVO) values(5, 'ICT -infrapalvelut', 'Pääsy- ja käyttövaltuuksien elinkaaren hallintapalvelut (IAM)');
insert into palvelu_kasite_arvo (KASITE_ID, KASITE, ARVO) values(6, 'ICT -käyttäjälle palvelut', 'Käyttäjien tukipalvelut');
insert into palvelu_kasite_arvo (KASITE_ID, KASITE, ARVO) values(7, 'ICT -käyttäjälle palvelut', 'Käyttäjien etäyhteyspalvelut');
insert into palvelu_kasite_arvo (KASITE_ID, KASITE, ARVO) values(8, 'ICT -käyttäjälle palvelut', 'Käyttäjä- ja käyttövaltuudet');
insert into palvelu_kasite_arvo (KASITE_ID, KASITE, ARVO) values(9, 'ICT -käyttäjälle palvelut', 'Käyttäjien päätelaitepalvelut');
insert into palvelu_kasite_arvo (KASITE_ID, KASITE, ARVO) values(10, 'ICT -käyttäjälle palvelut', 'Käyttäjien viestintätekniset palvelut');
insert into palvelu_kasite_arvo (KASITE_ID, KASITE, ARVO) values(11, 'ICT hallinta- ja ylläpitopalvelut', 'Palvelutuottajien ohjaus');
insert into palvelu_kasite_arvo (KASITE_ID, KASITE, ARVO) values(12, 'ICT hallinta- ja ylläpitopalvelut', 'Sopimusten ja lisenssien hallintapalvelu');
insert into palvelu_kasite_arvo (KASITE_ID, KASITE, ARVO) values(13, 'Kehittämisen ohjauspalvelut', 'Kehittämisohjelman johto- ja hallintapalvelu');
insert into palvelu_kasite_arvo (KASITE_ID, KASITE, ARVO) values(14, 'Kehittämisen ohjauspalvelut', 'Arkkitehtuuripalvelu');
insert into palvelu_kasite_arvo (KASITE_ID, KASITE, ARVO) values(15, 'Kehittämisprojektien toteuttaminen', 'Kehittämisprojektin ohjaus');
insert into palvelu_kasite_arvo (KASITE_ID, KASITE, ARVO) values(16, 'Kehittämisprojektien toteuttaminen', 'Kehittämisprojektin toteutus');
insert into palvelu_kasite_arvo (KASITE_ID, KASITE, ARVO) values(17, 'Projektien tuki- ja ohjauspalvelut', 'Yhteisten palveluiden asiantuntijatuki');
insert into palvelu_kasite_arvo (KASITE_ID, KASITE, ARVO) values(18, 'Projektien tuki- ja ohjauspalvelut', 'SOA -asiantuntijatuki');
insert into palvelu_kasite_arvo (KASITE_ID, KASITE, ARVO) values(19, 'Projektien tuki- ja ohjauspalvelut', 'Käyttäjä- ja käyttövaltuushallinnan tuki');
insert into palvelu_kasite_arvo (KASITE_ID, KASITE, ARVO) values(20, 'Tieto-omaisuuden hallintapalvelut', 'Väylätietojen hallintapalvelu');
insert into palvelu_kasite_arvo (KASITE_ID, KASITE, ARVO) values(21, 'Tieto-omaisuuden hallintapalvelut', 'Asiakirja- ja asianhallintapalvelut');
insert into palvelu_kasite_arvo (KASITE_ID, KASITE, ARVO) values(22, 'Tieto-omaisuuden hallintapalvelut', 'Merenmittaustietojen hallintapalvelut');
insert into palvelu_kasite_arvo (KASITE_ID, KASITE, ARVO) values(23, 'Tieto-omaisuuden hallintapalvelut', 'Tietojärjestelmien ylläpito ja elinkaaren hallinta');
insert into palvelu_kasite_arvo (KASITE_ID, KASITE, ARVO) values(24, 'Tietohallinto', 'Tietojärjestelmien salasanat ja käyttöoikeudet');
insert into palvelu_kasite_arvo (KASITE_ID, KASITE, ARVO) values(25, 'Tietohallinto', 'Verkot ja yhteydet');
insert into palvelu_kasite_arvo (KASITE_ID, KASITE, ARVO) values(26, 'Tietohallinto', 'Ulkoisten käyttäjien käyttöoikeudet');
insert into palvelu_kasite_arvo (KASITE_ID, KASITE, ARVO) values(27, 'Tietohallinto', 'Palvelut IT-projekteille ja järjestelmävastaaville');
insert into palvelu_kasite_arvo (KASITE_ID, KASITE, ARVO) values(28, 'Tietohallinto', 'Tuki ja opastus (käyttäjätukipalvelu)');
insert into palvelu_kasite_arvo (KASITE_ID, KASITE, ARVO) values(29, 'Tietohallinto', 'Asiakirjahallinto- ja kirjastopalvelut');
insert into palvelu_kasite_arvo (KASITE_ID, KASITE, ARVO) values(30, 'Tietohallinto', 'Laitteet ja tarvikkeet');
insert into palvelu_kasite_arvo (KASITE_ID, KASITE, ARVO) values(31, 'Tietohallinto', 'Videoneuvottelulaitteet');
insert into palvelu_kasite_arvo (KASITE_ID, KASITE, ARVO) values(32, 'Tietopalvelut', 'Avoin data -tietopalvelut');
insert into palvelu_kasite_arvo (KASITE_ID, KASITE, ARVO) values(33, 'Tietopalvelut', 'Avoin data -asiantuntijatukipalvelu');
insert into palvelu_kasite_arvo (KASITE_ID, KASITE, ARVO) values(34, 'Tietopalvelut', 'Arkisto- ja kirjastotietopalvelut');
insert into palvelu_kasite_arvo (KASITE_ID, KASITE, ARVO) values(35, 'Tietopalvelut', 'Tilastopalvelut');
insert into palvelu_kasite_arvo (KASITE_ID, KASITE, ARVO) values(36, 'Tietopalvelut', 'Tietoaineistopalvelut');
insert into palvelu_kasite_arvo (KASITE_ID, KASITE, ARVO) values(37, 'Tietopalvelut', 'Analytiikkapalvelut');
insert into palvelu_kasite_arvo (KASITE_ID, KASITE, ARVO) values(38, 'Tietoturva ja mahdollistavat palvelut', 'Tietoturvan palvelut');
insert into palvelu_kasite_arvo (KASITE_ID, KASITE, ARVO) values(39, 'Tietoturva ja mahdollistavat palvelut', 'Muut mahdollistavat palvelut');
insert into palvelu_kasite_arvo (KASITE_ID, KASITE, ARVO) values(40, 'Ylätaso', 'Asiointipalvelut');
insert into palvelu_kasite_arvo (KASITE_ID, KASITE, ARVO) values(41, 'Ylätaso', 'Kehittämisen ohjauspalvelut');
insert into palvelu_kasite_arvo (KASITE_ID, KASITE, ARVO) values(42, 'Ylätaso', 'Projektien tuki- ja ohjauspalvelut');
insert into palvelu_kasite_arvo (KASITE_ID, KASITE, ARVO) values(43, 'Ylätaso', 'ICT -käyttäjälle palvelut');
insert into palvelu_kasite_arvo (KASITE_ID, KASITE, ARVO) values(44, 'Ylätaso', 'ICT -infrapalvelut');
insert into palvelu_kasite_arvo (KASITE_ID, KASITE, ARVO) values(45, 'Ylätaso', 'Tietoturva ja mahdollistavat palvelut');
insert into palvelu_kasite_arvo (KASITE_ID, KASITE, ARVO) values(46, 'Ylätaso', 'ICT hallinta- ja ylläpitopalvelut');
insert into palvelu_kasite_arvo (KASITE_ID, KASITE, ARVO) values(47, 'Ylätaso', 'Kehittämisprojektien toteuttaminen');
insert into palvelu_kasite_arvo (KASITE_ID, KASITE, ARVO) values(48, 'Ylätaso', 'Tietopalvelut');
insert into palvelu_kasite_arvo (KASITE_ID, KASITE, ARVO) values(49, 'Ylätaso', 'Tieto-omaisuuden hallintapalvelut');
