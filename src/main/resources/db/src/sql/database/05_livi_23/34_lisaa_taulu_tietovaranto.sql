-- TKYP-150 Lisää Tietovaranto-osio
CREATE TABLE "TIETOVARANTO" (
    "TIETOVARANTOTUNNUS" NUMBER PRIMARY KEY,
    "NIMI" VARCHAR2(225),
    "KUVAUS" VARCHAR2(4000),
    "VASTAAVA" VARCHAR2(225),
    "LISATIETO" VARCHAR2(4000),
    "RIVIMUOKKAAJATUNNUS" VARCHAR2(150),
    "RIVILUOTUPVM" TIMESTAMP(6),
    "RIVIMUOKATTUPVM" TIMESTAMP(6)
);

CREATE SEQUENCE "TIETOVARANTO_ID_SEQ" START WITH 1;