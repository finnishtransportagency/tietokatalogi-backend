create or replace view tietok.apu_hierarkia ("ft_nimi", "lt_nimi", "tr_nimi", "ptr_nimi") as
select ft.nimi ft_nimi, lt.nimi lt_nimi,tr.nimi tr_nimi, ptr.nimi ptr_nimi from tietok.looginentietovarantofyysinenti t
inner join tietok.looginentietovaranto lt on (t.looginentietovarantotunnus = lt.looginentietovarantotunnus)
inner join tietok.fyysinentietovaranto ft on (t.fyysinentietovarantotunnus = ft.fyysinentietovarantotunnus)
left outer join tietok.tietoryhmalooginentietovaranto trl on(lt.looginentietovarantotunnus = trl.looginentietovarantotunnus)
left outer join tietok.tietoryhma tr on (trl.tietoryhmatunnus = tr.tietoryhmatunnus)
left outer join tietok.paatietoryhma ptr on (trl.paatietoryhmatunnus = ptr.paatietoryhmatunnus);
