-- TKYP-156 Lisää osio: Toimintaprosessit
CREATE TABLE "TOIMINTAPROSESSI" (
    "TOIMINTAPROSESSITUNNUS" NUMBER PRIMARY KEY,
    "NIMI" VARCHAR2(225),
    "VASTAAVA_ORGANISAATIO" VARCHAR2(255), 
    "TARKOITUS" VARCHAR2(255),
    "RIVIMUOKKAAJATUNNUS" VARCHAR2(150),
    "RIVILUOTUPVM" TIMESTAMP(6),
    "RIVIMUOKATTUPVM" TIMESTAMP(6)
);

CREATE SEQUENCE "TOIMINTAPROSESSI_ID_SEQ" START WITH 1;