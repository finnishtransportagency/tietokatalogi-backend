create or replace view tietok.jarjestelmatyyppi ("nimi", "n_nimi") as
(select ak.nimi,coalesce(concat(upper(substr(ak.nimi,1,1)),lower(substr(ak.nimi,2,length(ak.nimi)))),'kaikki') as n_nimi
from
(select distinct(jarjestelmatyyppi) as nimi from tietok.tietojarjestelmasalkku)ak);
