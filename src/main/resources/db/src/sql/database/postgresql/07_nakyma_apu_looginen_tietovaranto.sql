create or replace view tietok.apu_looginentietovaranto ("lt_nimi", "ft_nimi") as
select lt.nimi as lt_nimi, ft.nimi as ft_nimi from tietok.looginentietovarantofyysinenti t
inner join tietok.looginentietovaranto lt on (t.looginentietovarantotunnus = lt.looginentietovarantotunnus)
inner join tietok.fyysinentietovaranto ft on (t.fyysinentietovarantotunnus = ft.fyysinentietovarantotunnus);
