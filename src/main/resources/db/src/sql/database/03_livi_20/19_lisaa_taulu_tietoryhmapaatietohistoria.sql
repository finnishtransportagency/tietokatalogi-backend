CREATE TABLE TIETORYHMAPAATIETOHISTORIA (
"RIVITUNNUS" NUMBER NOT NULL,
"PAATIETORYHMATUNNUS" NUMBER NOT NULL,
"HISTORIATYYPPI" VARCHAR2(6) NOT NULL,
"TIETORYHMATUNNUS" NUMBER NOT NULL,
"RIVITILA" VARCHAR2(15) NOT NULL,
"RIVILUOTUPVM" TIMESTAMP (6),
"RIVIMUOKKAAJATUNNUS" VARCHAR2(150),
CONSTRAINT "PK_TIETORYHMAPAATIETOHISTORIA" PRIMARY KEY ("RIVITUNNUS"));
