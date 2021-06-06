CREATE OR REPLACE FORCE VIEW APU_HIERARKIA_2 ("LIITTYY_1", "TUNNUS_1", "VARANTO_1", "LIITTYY_2", "TUNNUS_2", "VARANTO_2") AS
select ft.nimi liittyy_1,ft.fyysinentietovarantotunnus tunnus_1,'fyysinentietovaranto' varanto_1, lt.nimi liittyy_2, lt.looginentietovarantotunnus tunnus_2, 'looginentietovaranto' varanto_2 from LOOGINENTIETOVARANTOFYYSINENTI t
inner join looginentietovaranto lt on (t.looginentietovarantotunnus = lt.looginentietovarantotunnus)
inner join fyysinentietovaranto ft on (t.fyysinentietovarantotunnus = ft.fyysinentietovarantotunnus)
union
select tr.nimi liittyy_1,tr.tietoryhmatunnus tunnus_1,'tietoryhma' varanto_1, ptr.nimi liittyy_2,ptr.paatietoryhmatunnus tunnus_2,'paatietoryhma' varanto_2 from TIETORYHMALOOGINENTIETOVARANTO trl
inner join tietoryhma tr on (trl.tietoryhmatunnus = tr.tietoryhmatunnus)
inner join paatietoryhma ptr on (trl.paatietoryhmatunnus = ptr.paatietoryhmatunnus)
union
select tr.nimi liittyy_1,tr.tietoryhmatunnus tunnus_1,'tietoryhma' varanto_1, lt.nimi liittyy_2,lt.looginentietovarantotunnus tunnus_2,'looginentietovaranto' varanto_2 from TIETORYHMALOOGINENTIETOVARANTO trl
inner join tietoryhma tr on (trl.tietoryhmatunnus = tr.tietoryhmatunnus)
inner join looginentietovaranto lt on (trl.looginentietovarantotunnus = lt.looginentietovarantotunnus);
