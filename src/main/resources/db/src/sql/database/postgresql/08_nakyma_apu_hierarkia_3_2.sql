create or replace view tietok.apu_hierarkia_3_2 ("liittyy_1", "tunnus_1", "varanto_1", "liittyy_2", "tunnus_2", "varanto_2", "tyyppi", "linkki", "tietosisalto", "tietovirta_id") as
select
ft.nimi liittyy_1,
ft.fyysinentietovarantotunnus tunnus_1,
'fyysinentietovaranto' varanto_1,
lt.nimi liittyy_2,
lt.looginentietovarantotunnus tunnus_2,
'looginentietovaranto' varanto_2,
'tietoarkkitehtuuri' as tyyppi,
'' as linkki,
'' as tietosisalto,
trunc(ft.fyysinentietovarantotunnus, 0)::text||'|'||trunc(lt.looginentietovarantotunnus, 0)::text||'|lft' as tietovirta_id
from tietok.looginentietovarantofyysinenti t
inner join tietok.looginentietovaranto lt on
(
t.looginentietovarantotunnus = lt.looginentietovarantotunnus
)
inner join tietok.fyysinentietovaranto ft on
(
t.fyysinentietovarantotunnus = ft.fyysinentietovarantotunnus
)
union
select
tr.nimi liittyy_1,
tr.tietoryhmatunnus tunnus_1,
'tietoryhma' varanto_1,
ptr.nimi liittyy_2,
ptr.paatietoryhmatunnus tunnus_2,
'paatietoryhma' varanto_2,
'tietoarkkitehtuuri' as tyyppi,
'' as linkki,
'' as tietosisalto,
trunc(tr.tietoryhmatunnus, 0)::text||'|'||trunc(ptr.paatietoryhmatunnus, 0)::text||'|ptr' as tietovirta_id
from tietok.tietoryhma trl
inner join tietok.tietoryhma tr on (trl.tietoryhmatunnus = tr.tietoryhmatunnus)
inner join tietok.paatietoryhma ptr on (trl.paatietoryhmatunnus = ptr.paatietoryhmatunnus)
union
select
tr.nimi liittyy_1,
tr.tietoryhmatunnus tunnus_1,
'tietoryhma' varanto_1,
lt.nimi liittyy_2,
lt.looginentietovarantotunnus tunnus_2,
'looginentietovaranto' varanto_2,
'tietoarkkitehtuuri' as tyyppi,
'' as linkki,
'' as tietosisalto,
trunc(tr.tietoryhmatunnus, 0)::text||'|'||trunc(lt.looginentietovarantotunnus, 0)::text||'|ltv' as tietovirta_id
from tietok.tietoryhmalooginentietovaranto trl
inner join tietok.tietoryhma tr on (trl.tietoryhmatunnus = tr.tietoryhmatunnus)
inner join tietok.looginentietovaranto lt on
(
trl.looginentietovarantotunnus = lt.looginentietovarantotunnus
)
union
select
tr.nimi liittyy_1,
tr.tietoryhmatunnus tunnus_1,
'tietoryhma' varanto_1,
lt.nimi liittyy_2,
lt.looginentietovarantotunnus tunnus_2,
'looginentietovaranto' varanto_2,
'tietoarkkitehtuuri' as tyyppi,
'' as linkki,
'' as tietosisalto,
trunc(tr.tietoryhmatunnus, 0)::text||'|'||trunc(lt.looginentietovarantotunnus, 0)::text||'|tr' as tietovirta_id
from tietok.tieto trl
inner join tietok.tietoryhma tr on
(
trl.tietoryhmatunnus = tr.tietoryhmatunnus
)
inner join tietok.looginentietovaranto lt on
(
trl.looginentietovarantotunnus = lt.looginentietovarantotunnus
)
union
select
trl.nimi liittyy_1,
trl.tietotunnus tunnus_1,
'tietolaji' varanto_1,
lt.nimi liittyy_2,
lt.looginentietovarantotunnus tunnus_2,
'looginentietovaranto' varanto_2,
'tietoarkkitehtuuri' as tyyppi,
'' as linkki,
'' as tietosisalto,
trunc(trl.tietotunnus, 0)::text||'|'||trunc(lt.looginentietovarantotunnus, 0)::text||'|tl' as tietovirta_id
from tietok.tieto trl
inner join tietok.looginentietovaranto lt on
(
trl.looginentietovarantotunnus = lt.looginentietovarantotunnus
)
union
select
ap.source liittyy_1,
ap.s_id tunnus_1,
ap.s_type varanto_1,
ap.target liittyy_2,
ap.t_id tunnus_2,
ap.t_type varanto_2,
'jarjestelma' as tyyppi,
ap.linkki,
ap.tietosisalto,
ap.tietovirta_id||'|ap'
from tietok.apu_tietovirrat_2 ap;
