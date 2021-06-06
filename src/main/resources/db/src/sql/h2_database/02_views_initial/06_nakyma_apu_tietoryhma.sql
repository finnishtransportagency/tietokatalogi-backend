CREATE OR REPLACE FORCE VIEW APU_TIETORYHMA ("PTR_NIMI", "LTV_NIMI", "TIETORYHMA_NIMI") AS
select p.nimi as ptr_nimi, lv.nimi as ltv_nimi, s.nimi as tietoryhma_nimi from TIETORYHMALOOGINENTIETOVARANTO t
inner join tietoryhma s on (s.tietoryhmatunnus = t.tietoryhmatunnus)
inner join looginentietovaranto lv on(lv.looginentietovarantotunnus = t.looginentietovarantotunnus)
inner join paatietoryhma p on (p.paatietoryhmatunnus = t.paatietoryhmatunnus);
