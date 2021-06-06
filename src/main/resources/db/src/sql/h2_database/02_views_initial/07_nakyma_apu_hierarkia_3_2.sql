CREATE OR REPLACE FORCE VIEW APU_HIERARKIA_3_2 ("LIITTYY_1", "TUNNUS_1", "VARANTO_1", "LIITTYY_2", "TUNNUS_2", "VARANTO_2", "TYYPPI", "LINKKI", "TIETOSISALTO", "TIETOVIRTA_ID") AS
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
to_char(ft.fyysinentietovarantotunnus)||'|'||to_char(lt.looginentietovarantotunnus)||'|lft' as tietovirta_id
from LOOGINENTIETOVARANTOFYYSINENTI t
inner join looginentietovaranto lt on
(
t.looginentietovarantotunnus = lt.looginentietovarantotunnus
)
inner join fyysinentietovaranto ft on
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
to_char(tr.tietoryhmatunnus)||'|'||to_char(ptr.paatietoryhmatunnus)||'|ptr' as tietovirta_id
from TIETORYHMA trl
inner join tietoryhma tr on (trl.tietoryhmatunnus = tr.tietoryhmatunnus)
inner join paatietoryhma ptr on (trl.paatietoryhmatunnus = ptr.paatietoryhmatunnus)
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
to_char(tr.tietoryhmatunnus)||'|'||to_char(lt.looginentietovarantotunnus)||'|ltv' as tietovirta_id
from TIETORYHMALOOGINENTIETOVARANTO trl
inner join tietoryhma tr on (trl.tietoryhmatunnus = tr.tietoryhmatunnus)
inner join looginentietovaranto lt on
(
trl.looginentietovarantotunnus = lt.looginentietovarantotunnus
)
union
Select
tr.nimi liittyy_1,
tr.tietoryhmatunnus tunnus_1,
'tietoryhma' varanto_1,
lt.nimi liittyy_2,
lt.looginentietovarantotunnus tunnus_2,
'looginentietovaranto' varanto_2,
'tietoarkkitehtuuri' as tyyppi,
'' as linkki,
'' as tietosisalto,
to_char(tr.tietoryhmatunnus)||'|'||to_char(lt.looginentietovarantotunnus)||'|tr' as tietovirta_id
from TIETO trl
inner join tietoryhma tr on
(
trl.tietoryhmatunnus = tr.tietoryhmatunnus
)
inner join looginentietovaranto lt on
(
trl.looginentietovarantotunnus = lt.looginentietovarantotunnus
)
union
Select
trl.nimi liittyy_1,
trl.tietotunnus tunnus_1,
'tietolaji' varanto_1,
lt.nimi liittyy_2,
lt.looginentietovarantotunnus tunnus_2,
'looginentietovaranto' varanto_2,
'tietoarkkitehtuuri' as tyyppi,
'' as linkki,
'' as tietosisalto,
to_char(trl.tietotunnus)||'|'||to_char(lt.looginentietovarantotunnus)||'|tl' as tietovirta_id
from TIETO trl
inner join looginentietovaranto lt on
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
from apu_tietovirrat_2 ap;
