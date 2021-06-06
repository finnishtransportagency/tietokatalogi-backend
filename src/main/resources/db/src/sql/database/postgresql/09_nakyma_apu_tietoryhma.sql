create or replace view tietok.apu_tietoryhma ("ptr_nimi", "ltv_nimi", "tietoryhma_nimi") as
select p.nimi as ptr_nimi, lv.nimi as ltv_nimi, s.nimi as tietoryhma_nimi from tietok.tietoryhmalooginentietovaranto t
inner join tietok.tietoryhma s on (s.tietoryhmatunnus = t.tietoryhmatunnus)
inner join tietok.looginentietovaranto lv on(lv.looginentietovarantotunnus = t.looginentietovarantotunnus)
inner join tietok.paatietoryhma p on (p.paatietoryhmatunnus = t.paatietoryhmatunnus);
