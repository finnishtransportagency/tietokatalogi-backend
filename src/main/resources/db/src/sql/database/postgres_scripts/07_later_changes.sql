
-- ANALPA-1530 Tietokatalogiin muokattava etusivu
CREATE TABLE tietok.etusivu (
    id integer,
    paateksti text,
    sivuteksti text
);

-- ANALPA-1932 Tietokatalogin kehitystä
ALTER TABLE tietok.toimintaprosessi
ADD COLUMN tyotila character varying(4000);

ALTER TABLE tietok.toimintaprosessihistoria
ADD COLUMN tyotila character varying(4000);