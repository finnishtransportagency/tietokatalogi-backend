CREATE OR REPLACE FORCE VIEW APU_TIETOVIRRAT ("S_ID", "SOURCE", "T_ID", "TARGET", "LINKKI", "TIETOSISALTO", "JARJESTELMAN_NIMI", "TIETOVIRTA_ID") AS
select j.tietojarjestelmatunnus as s_id,j.jarjestelman_nimi as source, tj.tietojarjestelmatunnus as t_id, tj.jarjestelman_nimi as target, t.linkki, t.tietosisalto, j.jarjestelman_nimi ,t.tietovirta_id  from TIETOVIRRAT t
inner join tietojarjestelmasalkku j on(t.s_jarjestelma=j.tietojarjestelmatunnus)
inner join tietojarjestelmasalkku tj on(t.t_jarjestelma=tj.tietojarjestelmatunnus)
group by j.tietojarjestelmatunnus,j.jarjestelman_nimi,tj.tietojarjestelmatunnus,tj.jarjestelman_nimi,t.linkki, t.tietosisalto,t.tietovirta_id;
