-- TKYP-156 Lisää osio: Toimintaprosessit
CREATE TABLE "TOIMINTAPROSTIETOVARAHISTORIA" (
    "RIVI_ID" NUMBER PRIMARY KEY,
    "TIETOVARANTOTUNNUS" NUMBER,
    "TOIMINTAPROSESSITUNNUS" NUMBER,
    "RIVILUOTUPVM" TIMESTAMP (6),
    "HISTORIATYYPPI" VARCHAR2(6),
    "RIVIMUOKKAAJATUNNUS" VARCHAR2(150)
);

CREATE SEQUENCE "TOIMINTAPROSTIETOVAHIST_ID_SEQ" START WITH 1;
