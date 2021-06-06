CREATE TABLE REKISTERIPALVELUTASO (
"PALVELUTASO" VARCHAR2(50) NOT NULL,
"KUVAUS" VARCHAR2(4000) NOT NULL,
"RIVITUNNUS" NUMBER NOT NULL,
"RIVITILA" VARCHAR2(15) NOT NULL,
"RIVILUOTUPVM" TIMESTAMP (6) NOT NULL,
"RIVIMUOKKAAJATUNNUS" VARCHAR2(150) NOT NULL,
"RIVIMUOKATTUPVM" TIMESTAMP (6) NOT NULL,
CONSTRAINT "REKISTERIPALVELUTASO_PK" PRIMARY KEY ("PALVELUTASO"));
