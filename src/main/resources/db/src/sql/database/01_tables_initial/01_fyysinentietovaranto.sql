CREATE TABLE FYYSINENTIETOVARANTO (
"FYYSINENTIETOVARANTOTUNNUS" NUMBER NOT NULL,
"ID" VARCHAR2(50),
"NIMI" VARCHAR2(150) NOT NULL,
"KOODI" VARCHAR2(50),
"SISLT" VARCHAR2(4000),
"TIETOKANTATEKNOLOGIA" VARCHAR2(50),
"PALVELUTASO" VARCHAR2(50),
"KOKO" NUMBER(15,5),
"TIETUEMAARA" NUMBER(15,5),
"LINKKI" VARCHAR2(50),
"MUUTA" VARCHAR2(4000),
"TEKNOLOGIA" VARCHAR2(150),
"LINKKI_1" VARCHAR2(150),
"YLEMPITASO" NUMBER,
"RIVITUNNUS" NUMBER,
"RIVITILA" VARCHAR2(15),
"RIVIMUOKATTUPVM" TIMESTAMP (6),
"RIVIMUOKKAAJATUNNUS" VARCHAR2(150),
"RIVILUOTUPVM" TIMESTAMP (6),
"DOCUMENT_ID" VARCHAR2(255),
CONSTRAINT "FYYSINENTIETOVARANTO_PK" PRIMARY KEY ("FYYSINENTIETOVARANTOTUNNUS"));
