-- TKYP-139 Tietojärjestelmäpalvelun muutokset
-- Modify the view to use JARJESTELMALOOGINEN join table instead of LOOGINENTIETOVARANTO and its KOODI field.
CREATE OR REPLACE VIEW JARJESTELMA_TIETOARKKITEHTUURI
            (JARJESTELMAN_NIMI,TIETOJARJESTELMATUNNUS,TIETONIMI,TIETOTUNNUS,TIETORYHMANIMI,TIETORYHMATUNNUS)
AS
SELECT
    tietojarjestelmasalkku.jarjestelman_nimi, tietojarjestelmasalkku.tietojarjestelmatunnus, tieto.nimi AS tietonimi, tieto.tietotunnus, tietoryhma.nimi AS tietoryhmanimi, tietoryhma.tietoryhmatunnus
FROM
    tietojarjestelmasalkku
        JOIN jarjestelmalooginen ON
            tietojarjestelmasalkku.tietojarjestelmatunnus = jarjestelmalooginen.jarjestelmatunnus
        JOIN looginentietovaranto ON
            jarjestelmalooginen.looginentunnus = looginentietovaranto.looginentietovarantotunnus
        JOIN tieto ON
            looginentietovaranto.looginentietovarantotunnus = tieto.looginentietovarantotunnus
        LEFT JOIN tietoryhma ON
            tieto.tietoryhmatunnus = tietoryhma.tietoryhmatunnus;