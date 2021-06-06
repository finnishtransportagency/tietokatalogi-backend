create or replace view tietok.apu_hierarkia_2 ("liittyy_1", "tunnus_1", "varanto_1", "liittyy_2", "tunnus_2", "varanto_2") as
select ft.nimi liittyy_1,ft.fyysinentietovarantotunnus tunnus_1,'fyysinentietovaranto' varanto_1, lt.nimi liittyy_2, lt.looginentietovarantotunnus tunnus_2, 'looginentietovaranto' varanto_2 from tietok.looginentietovarantofyysinenti t
inner join tietok.looginentietovaranto lt on (t.looginentietovarantotunnus = lt.looginentietovarantotunnus)
inner join tietok.fyysinentietovaranto ft on (t.fyysinentietovarantotunnus = ft.fyysinentietovarantotunnus)
union
select tr.nimi liittyy_1,tr.tietoryhmatunnus tunnus_1,'tietoryhma' varanto_1, ptr.nimi liittyy_2,ptr.paatietoryhmatunnus tunnus_2,'paatietoryhma' varanto_2 from tietok.tietoryhmalooginentietovaranto trl
inner join tietok.tietoryhma tr on (trl.tietoryhmatunnus = tr.tietoryhmatunnus)
inner join tietok.paatietoryhma ptr on (trl.paatietoryhmatunnus = ptr.paatietoryhmatunnus)
union
select tr.nimi liittyy_1,tr.tietoryhmatunnus tunnus_1,'tietoryhma' varanto_1, lt.nimi liittyy_2,lt.looginentietovarantotunnus tunnus_2,'looginentietovaranto' varanto_2 from tietok.tietoryhmalooginentietovaranto trl
inner join tietok.tietoryhma tr on (trl.tietoryhmatunnus = tr.tietoryhmatunnus)
inner join tietok.looginentietovaranto lt on (trl.looginentietovarantotunnus = lt.looginentietovarantotunnus);
