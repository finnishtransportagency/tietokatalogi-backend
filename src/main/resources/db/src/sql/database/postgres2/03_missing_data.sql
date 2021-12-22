-- TKYP-139 Tietojärjestelmäpalvelun muutokset
-- Modify the view to use JARJESTELMALOOGINEN join table instead of LOOGINENTIETOVARANTO and its KOODI field.
CREATE OR REPLACE VIEW TIETOK.JARJESTELMA_TIETOARKKITEHTUURI
            (JARJESTELMAN_NIMI,TIETOJARJESTELMATUNNUS,TIETONIMI,TIETOTUNNUS,TIETORYHMANIMI,TIETORYHMATUNNUS)
AS
SELECT
    tietojarjestelmasalkku.jarjestelman_nimi, tietojarjestelmasalkku.tietojarjestelmatunnus, tieto.nimi AS tietonimi, tieto.tietotunnus, tietoryhma.nimi AS tietoryhmanimi, tietoryhma.tietoryhmatunnus
FROM
    tietok.tietojarjestelmasalkku
        JOIN tietok.jarjestelmalooginen ON
            tietojarjestelmasalkku.tietojarjestelmatunnus = jarjestelmalooginen.jarjestelmatunnus
        JOIN tietok.looginentietovaranto ON
            jarjestelmalooginen.looginentunnus = looginentietovaranto.looginentietovarantotunnus
        JOIN tietok.tieto ON
            tietok.looginentietovaranto.looginentietovarantotunnus = tieto.looginentietovarantotunnus
        LEFT JOIN tietok.tietoryhma ON
            tieto.tietoryhmatunnus = tietoryhma.tietoryhmatunnus;

-- TKYP-124 Tieto-omaisuus -lomake
CREATE VIEW tietok.TIETOOMAISUUS_JARJESTELMA_NIMI
AS select tietoomaisuus.*, tietojarjestelmasalkku.jarjestelman_nimi
from tietok.tietoomaisuus
join tietok.tietojarjestelmasalkku on (tietoomaisuus.tietojarjestelma_id = tietojarjestelmasalkku.tietojarjestelmatunnus);
