CREATE OR REPLACE FORCE VIEW APU_LOOGINENTIETOVARANTO ("LT_NIMI", "FT_NIMI") AS
select lt.nimi as lt_nimi, ft.nimi as ft_nimi from LOOGINENTIETOVARANTOFYYSINENTI t
inner join looginentietovaranto lt on (t.looginentietovarantotunnus = lt.looginentietovarantotunnus)
inner join fyysinentietovaranto ft on (t.fyysinentietovarantotunnus = ft.fyysinentietovarantotunnus);
