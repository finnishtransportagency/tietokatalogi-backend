CREATE TABLE REKISTERITIETOSUOJATASO (
"TIETOSUOJATASO" VARCHAR2(50) NOT NULL,
"RIVIMUOKKAAJATUNNUS" VARCHAR2(150) NOT NULL,
"RIVITUNNUS" NUMBER NOT NULL,
"RIVITILA" VARCHAR2(15) NOT NULL,
"RIVILUOTUPVM" TIMESTAMP (6) NOT NULL,
"RIVIMUOKATTUPVM" TIMESTAMP (6) NOT NULL,
CONSTRAINT "REKISTERITIETOSUOJATASO_PK" PRIMARY KEY ("TIETOSUOJATASO", "RIVIMUOKKAAJATUNNUS"));
