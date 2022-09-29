
-- ANALPA-1530 Tietokatalogiin muokattava etusivu
CREATE TABLE etusivu (
    id integer,
    paateksti text,
    sivuteksti text
);

-- ANALPA-1932 Tietokatalogin kehitystä
ALTER TABLE toimintaprosessi
ADD COLUMN tyotila character varying(4000);

ALTER TABLE toimintaprosessihistoria
ADD COLUMN tyotila character varying(4000);