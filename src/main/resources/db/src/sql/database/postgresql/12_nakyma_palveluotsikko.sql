CREATE OR REPLACE VIEW tietok.palveluotsikko ("nimi", "n_nimi")
AS (
    SELECT
        ak.nimi,
        coalesce(concat(upper(substr(ak.nimi, 1, 1)), lower(substr(ak.nimi, 2, length(ak.nimi)))), 'kaikki') AS n_nimi
    FROM ( SELECT DISTINCT
            (otsikko) AS nimi
        FROM
            tietok.palvelu) ak)
ORDER BY
    n_nimi ASC;

