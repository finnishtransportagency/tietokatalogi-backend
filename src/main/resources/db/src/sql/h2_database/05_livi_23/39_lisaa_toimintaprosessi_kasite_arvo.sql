-- TKYP-156 Lisää osio: Toimintaprosessit
CREATE TABLE TOIMINTAPROSESSI_KASITE_ARVO (
   "KASITE_WID" NUMBER NOT NULL,
   "KASITE" VARCHAR2(100),
   "ARVO" VARCHAR2(200),
   PRIMARY KEY ("KASITE_WID")
);

--Edit: The following values are incorrect, and also fixed values are not needed for 'VASTAAVA_ORGANISAATIO', so no need to insert rows here
--Instead the 'VASTAAVA_ORGANISAATIO' values come from the TOIMINTAPROSESSI table
--INSERT INTO TOIMINTAPROSESSI_KASITE_ARVO (KASITE_WID, KASITE, ARVO) VALUES (1, 'VASTAAVA_ORGANISAATIO', 'Väylävirasto');
--INSERT INTO TOIMINTAPROSESSI_KASITE_ARVO (KASITE_WID, KASITE, ARVO) VALUES (2, 'VASTAAVA_ORGANISAATIO', 'Traficom');
--INSERT INTO TOIMINTAPROSESSI_KASITE_ARVO (KASITE_WID, KASITE, ARVO) VALUES (3, 'VASTAAVA_ORGANISAATIO', 'TMFG');
--INSERT INTO TOIMINTAPROSESSI_KASITE_ARVO (KASITE_WID, KASITE, ARVO) VALUES (4, 'VASTAAVA_ORGANISAATIO', 'VR Tracks');
--INSERT INTO TOIMINTAPROSESSI_KASITE_ARVO (KASITE_WID, KASITE, ARVO) VALUES (5, 'VASTAAVA_ORGANISAATIO', 'VR');
--INSERT INTO TOIMINTAPROSESSI_KASITE_ARVO (KASITE_WID, KASITE, ARVO) VALUES (6, 'VASTAAVA_ORGANISAATIO', 'ELY-keskus');
--INSERT INTO TOIMINTAPROSESSI_KASITE_ARVO (KASITE_WID, KASITE, ARVO) VALUES (7, 'VASTAAVA_ORGANISAATIO', 'Palkeet');
--INSERT INTO TOIMINTAPROSESSI_KASITE_ARVO (KASITE_WID, KASITE, ARVO) VALUES (8, 'VASTAAVA_ORGANISAATIO', 'Muut ulkoiset organisaatiot');
