CREATE TABLE TIETOHISTORIA (
"RIVI_ID" NUMBER NOT NULL,
"TIETOTUNNUS" NUMBER NOT NULL,
"HISTORIATYYPPI" VARCHAR2(6) NOT NULL,
"LOOGINENTIETOVARANTOTUNNUS" NUMBER,
"TIETORYHMATUNNUS" NUMBER,
"NIMI" VARCHAR2(150),
"KOODI" VARCHAR2(50),
"KUVAUS" VARCHAR2(4000),
"OMISTAJA" VARCHAR2(150),
"LAHDE" VARCHAR2(150),
"TILA" VARCHAR2(50),
"SYNONYYMI" VARCHAR2(150),
"TIETOSUOJATASO" VARCHAR2(50),
"YLEMPITASO" NUMBER,
"RIVITUNNUS" NUMBER,
"RIVITILA" VARCHAR2(15),
"RIVIMUOKKAAJATUNNUS" VARCHAR2(150),
"RIVILUOTUPVM" TIMESTAMP (6),
"DOCUMENT_ID" VARCHAR2(200),
CONSTRAINT "TIETOHISTORIA_PK" PRIMARY KEY ("RIVI_ID"));
