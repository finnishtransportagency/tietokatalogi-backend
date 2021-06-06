create or replace view tietok.tjs_sov ("id", "nimi", "taulu") as
(
select
t.tietojarjestelmatunnus as id,
t.jarjestelman_nimi as nimi,
'tietojarjestelmasalkku' as taulu
from tietok.tietojarjestelmasalkku t
union
select
s.tunnus+1000000, s.nimi || ' ' || s.versio as nimi, 'sovellus' as taulu
from tietok.sovellus s
);
