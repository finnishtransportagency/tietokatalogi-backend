CREATE OR REPLACE FORCE VIEW APU_HIERARKIA ("FT_NIMI", "LT_NIMI", "TR_NIMI", "PTR_NIMI") AS
select ft.nimi ft_nimi, lt.nimi lt_nimi,tr.nimi tr_nimi, ptr.nimi ptr_nimi from LOOGINENTIETOVARANTOFYYSINENTI t
inner join looginentietovaranto lt on (t.looginentietovarantotunnus = lt.looginentietovarantotunnus)
inner join fyysinentietovaranto ft on (t.fyysinentietovarantotunnus = ft.fyysinentietovarantotunnus)
left outer join TIETORYHMALOOGINENTIETOVARANTO trl on(lt.looginentietovarantotunnus = trl.looginentietovarantotunnus)
left outer join tietoryhma tr on (trl.tietoryhmatunnus = tr.tietoryhmatunnus)
left outer join paatietoryhma ptr on (trl.paatietoryhmatunnus = ptr.paatietoryhmatunnus);
