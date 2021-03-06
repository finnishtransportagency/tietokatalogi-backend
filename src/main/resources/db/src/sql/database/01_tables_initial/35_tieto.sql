CREATE TABLE TIETO (
"TIETOTUNNUS" NUMBER NOT NULL,
"LOOGINENTIETOVARANTOTUNNUS" NUMBER NOT NULL,
"TIETORYHMATUNNUS" NUMBER NOT NULL,
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
"RIVIMUOKATTUPVM" TIMESTAMP (6),
"RIVIMUOKKAAJATUNNUS" VARCHAR2(150),
"RIVILUOTUPVM" TIMESTAMP (6),
"DOCUMENT_ID" VARCHAR2(200),
CONSTRAINT "TIETO_PK" PRIMARY KEY ("TIETOTUNNUS"));
