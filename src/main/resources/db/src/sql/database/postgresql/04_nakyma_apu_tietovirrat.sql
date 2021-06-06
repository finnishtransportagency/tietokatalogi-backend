create or replace view tietok.apu_tietovirrat ("s_id", "source", "t_id", "target", "linkki", "tietosisalto", "jarjestelman_nimi", "tietovirta_id") as
select j.tietojarjestelmatunnus as s_id,j.jarjestelman_nimi as source, tj.tietojarjestelmatunnus as t_id, tj.jarjestelman_nimi as target, t.linkki, t.tietosisalto, j.jarjestelman_nimi ,t.tietovirta_id  from tietok.tietovirrat t
inner join tietok.tietojarjestelmasalkku j on(t.s_jarjestelma=j.tietojarjestelmatunnus)
inner join tietok.tietojarjestelmasalkku tj on(t.t_jarjestelma=tj.tietojarjestelmatunnus)
group by j.tietojarjestelmatunnus,j.jarjestelman_nimi,tj.tietojarjestelmatunnus,tj.jarjestelman_nimi,t.linkki, t.tietosisalto,t.tietovirta_id;
