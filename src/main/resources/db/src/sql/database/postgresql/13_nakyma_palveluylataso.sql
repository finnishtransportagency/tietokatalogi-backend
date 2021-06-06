create or replace view tietok.palveluylataso ("nimi", "n_nimi")
as (
    select
        ak.nimi,
        coalesce(concat(upper(substr(ak.nimi, 1, 1)), lower(substr(ak.nimi, 2, length(ak.nimi)))), 'kaikki') as n_nimi
    from ( select distinct
            (ylataso) as nimi
        from
            tietok.palvelu) ak)
order by
    n_nimi asc;

