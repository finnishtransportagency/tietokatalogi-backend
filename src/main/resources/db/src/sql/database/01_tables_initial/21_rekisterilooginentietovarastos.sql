CREATE TABLE REKISTERILOOGINENTIETOVARASTOS (
"KAYTTAJA" VARCHAR2(150) NOT NULL,
"RIVITUNNUS" NUMBER NOT NULL,
"SAHKOPOSTI" VARCHAR2(50),
"RIVITILA" VARCHAR2(15) NOT NULL,
"RIVILUOTUPVM" TIMESTAMP (6) NOT NULL,
"RIVIMUOKATTUPVM" TIMESTAMP (6) NOT NULL,
"RIVIMUOKKAAJATUNNUS" VARCHAR2(150) NOT NULL,
CONSTRAINT "REKISTERILOOGINENTIETOVARAS27" PRIMARY KEY ("KAYTTAJA"));
