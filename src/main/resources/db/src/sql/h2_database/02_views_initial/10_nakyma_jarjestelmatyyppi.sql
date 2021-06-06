CREATE OR REPLACE FORCE VIEW JARJESTELMATYYPPI ("NIMI", "N_NIMI") AS
(select ak.nimi,nvl(concat(UPPER(SUBSTR(ak.nimi,1,1)),LOWER(SUBSTR(ak.nimi,2,LENGTH(ak.nimi)))),'Kaikki') as n_nimi
from
(select distinct(jarjestelmatyyppi) as nimi from tietojarjestelmasalkku)ak);
