CREATE OR REPLACE FORCE VIEW APU_TIETOVIRRAT_2 ("S_ID", "SOURCE", "S_TYPE", "T_ID", "TARGET", "T_TYPE", "LINKKI", "TIETOSISALTO", "NIMI", "TIETOVIRTA_ID") AS
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
from TIETOVIRRAT t
inner join tjs_sov j on(t.s_jarjestelma=j.id)
inner join tjs_sov tj on ( t.t_jarjestelma=tj.id )
group by j.id,
j.nimi,
j.taulu,
tj.id,
tj.nimi,
tj.taulu,
t.linkki,
t.tietosisalto,
t.tietovirta_id;
