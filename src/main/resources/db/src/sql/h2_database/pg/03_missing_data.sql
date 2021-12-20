
-- Helper view that shows tietolaji and tietoryhma values related to jarjestelma entities.

  CREATE OR REPLACE FORCE VIEW "TIETOK"."JARJESTELMA_TIETOARKKITEHTUURI" ("JARJESTELMAN_NIMI", "TIETOJARJESTELMATUNNUS", "TIETONIMI", "TIETOTUNNUS", "TIETORYHMANIMI", "TIETORYHMATUNNUS") AS
  SELECT
    tietojarjestelmasalkku.jarjestelman_nimi, tietojarjestelmasalkku.tietojarjestelmatunnus, tieto.nimi AS tietonimi, tieto.tietotunnus, tietoryhma.nimi AS tietoryhmanimi, tietoryhma.tietoryhmatunnus
FROM
    tietojarjestelmasalkku
JOIN looginentietovaranto ON
    tietojarjestelmasalkku.tietojarjestelmatunnus = looginentietovaranto.koodi
JOIN tieto ON
    looginentietovaranto.looginentietovarantotunnus = tieto.looginentietovarantotunnus
LEFT JOIN tietoryhma ON
    tieto.tietoryhmatunnus = tietoryhma.tietoryhmatunnus
;


-- TKYP-124 Tieto-omaisuus -lomake
CREATE VIEW TIETOOMAISUUS_JARJESTELMA_NIMI
AS select tietoomaisuus.*, tietojarjestelmasalkku.jarjestelman_nimi
from tietoomaisuus
join tietojarjestelmasalkku on (tietoomaisuus.tietojarjestelma_id = tietojarjestelmasalkku.tietojarjestelmatunnus);
