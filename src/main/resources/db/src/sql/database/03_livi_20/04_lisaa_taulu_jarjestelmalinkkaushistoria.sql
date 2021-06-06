CREATE TABLE JARJESTELMALINKKAUSHISTORIA (
"RIVITUNNUS" NUMBER NOT NULL,
"TIETOJARJESTELMATUNNUS" NUMBER NOT NULL,
"LINKATTAVA_ID" NUMBER NOT NULL,
"HISTORIATYYPPI" VARCHAR2(6) NOT NULL,
"RIVITILA" VARCHAR2(15) NOT NULL,
"RIVILUOTUPVM" TIMESTAMP (6),
"RIVIMUOKKAAJATUNNUS" VARCHAR2(150),
"SUUNTA" VARCHAR(255),
"TYYPPI" VARCHAR(255),
"KUVAUS" VARCHAR(255),
CONSTRAINT "PK_JARJESTELMALINKHISTORY" PRIMARY KEY ("RIVITUNNUS"));

create sequence joinjarjestelmalinkkaus_seq start with 1;
create sequence joinjarjestelmalinkkaus_id_seq start with 1;
create sequence joinjlinkhist_seq start with 1;
create sequence joinjlinkhist_id_seq start with 1;
