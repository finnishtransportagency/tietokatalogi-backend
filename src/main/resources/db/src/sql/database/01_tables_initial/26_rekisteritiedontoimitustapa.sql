CREATE TABLE REKISTERITIEDONTOIMISTUSTAPA (
"TOIMITUSTAPA" VARCHAR2(150) NOT NULL,
"KUVAUS" VARCHAR2(4000) NOT NULL,
"RIVITUNNUS" NUMBER NOT NULL,
"RIVITILA" VARCHAR2(15) NOT NULL,
"RIVILUOTUPVM" TIMESTAMP (6) NOT NULL,
"RIVIMUOKATTUPVM" TIMESTAMP (6) NOT NULL,
"RIVIMUOKKAAJATUNNUS" VARCHAR2(150) NOT NULL,
CONSTRAINT "REKISTERITIEDONTOIMISTUSTAP264" PRIMARY KEY ("TOIMITUSTAPA"));
