create or replace view tietok.apu_tietovirrat_2 ("s_id", "source", "s_type", "t_id", "target", "t_type", "linkki", "tietosisalto", "nimi", "tietovirta_id") as
select
j.id as s_id,
j.nimi as source,
j.taulu as s_type,
tj.id as t_id,
tj.nimi as target,
tj.taulu as t_type,
t.linkki,
t.tietosisalto,
j.nimi,
t.tietovirta_id
from tietok.tietovirrat t
inner join tietok.tjs_sov j on(t.s_jarjestelma=j.id)
inner join tietok.tjs_sov tj on ( t.t_jarjestelma=tj.id )
group by j.id,
j.nimi,
j.taulu,
tj.id,
tj.nimi,
tj.taulu,
t.linkki,
t.tietosisalto,
t.tietovirta_id;
