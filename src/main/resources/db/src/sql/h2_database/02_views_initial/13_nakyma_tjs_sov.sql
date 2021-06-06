CREATE OR REPLACE FORCE VIEW TJS_SOV ("ID", "NIMI", "TAULU") AS
(
select
t.TIETOJARJESTELMATUNNUS as id,
t.jarjestelman_nimi as nimi,
'tietojarjestelmasalkku' as taulu
from tietojarjestelmasalkku t
union
select
s.id+1000000, s.application_name || ' ' || s.VER as nimi, 'sovellus' as taulu
from sovellus s
);
