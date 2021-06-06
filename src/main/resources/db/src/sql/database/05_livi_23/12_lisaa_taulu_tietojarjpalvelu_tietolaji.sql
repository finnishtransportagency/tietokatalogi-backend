CREATE TABLE "TIETOK"."TIETOJARJPALVELU_TIETOLAJI"
    (	"TIETOJARJESTELMAPALVELUTUNNUS" NUMBER NOT NULL,
    "TIETOLAJITUNNUS" NUMBER NOT NULL,
    "RIVITUNNUS"  NUMBER PRIMARY KEY,
    "RIVITILA" VARCHAR2(15),

    CONSTRAINT TIETOJARJPALVELU_TIETOLAJI_FK1 FOREIGN KEY (TIETOJARJESTELMAPALVELUTUNNUS)
        REFERENCES TIETOJARJESTELMAPALVELU(TUNNUS),
    CONSTRAINT TIETOJARJPALVELU_TIETOLAJI_FK2 FOREIGN KEY (TIETOLAJITUNNUS)
        REFERENCES TIETO(TIETOTUNNUS)
);

CREATE SEQUENCE "TIETOJARJPALVELUTIETO_ID_SEQ" START WITH 1;