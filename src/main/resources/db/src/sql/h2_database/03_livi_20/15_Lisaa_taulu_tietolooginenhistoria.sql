CREATE TABLE TIETOLOOGINENHISTORIA (
"RIVITUNNUS" NUMBER NOT NULL,
"LOOGINENTIETOVARANTOTUNNUS" NUMBER NOT NULL,
"TIETOTUNNUS" NUMBER NOT NULL,
"HISTORIATYYPPI" VARCHAR2(6) NOT NULL,
"RIVITILA" VARCHAR2(15) NOT NULL,
"RIVILUOTUPVM" TIMESTAMP (6),
"RIVIMUOKKAAJATUNNUS" VARCHAR2(150),
CONSTRAINT "PK_TIETOLOOGINENHISTORIA" PRIMARY KEY ("RIVITUNNUS"));