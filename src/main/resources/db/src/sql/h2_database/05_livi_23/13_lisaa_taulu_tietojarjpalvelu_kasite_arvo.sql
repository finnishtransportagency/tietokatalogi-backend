
CREATE TABLE "TIETOJARJPALVELU_KASITE_ARVO" (
   "KASITE_ID" NUMBER PRIMARY KEY,
   "KASITE" VARCHAR2(100) NOT NULL,
   "ARVO" VARCHAR2(200) NOT NULL
);

INSERT INTO TIETOJARJPALVELU_KASITE_ARVO (KASITE_ID, KASITE, ARVO) VALUES (1, 'Elinkaaritila', 'Suunnitteilla');
INSERT INTO TIETOJARJPALVELU_KASITE_ARVO (KASITE_ID, KASITE, ARVO) VALUES (2, 'Elinkaaritila', 'Kehityksessä');
INSERT INTO TIETOJARJPALVELU_KASITE_ARVO (KASITE_ID, KASITE, ARVO) VALUES (3, 'Elinkaaritila', 'Tuotannossa');
INSERT INTO TIETOJARJPALVELU_KASITE_ARVO (KASITE_ID, KASITE, ARVO) VALUES (4, 'Elinkaaritila', 'Poistumassa');
INSERT INTO TIETOJARJPALVELU_KASITE_ARVO (KASITE_ID, KASITE, ARVO) VALUES (5, 'Elinkaaritila', 'Poistunut');