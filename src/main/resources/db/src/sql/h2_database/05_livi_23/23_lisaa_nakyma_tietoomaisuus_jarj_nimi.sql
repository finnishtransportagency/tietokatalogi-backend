-- TKYP-124 Tieto-omaisuus -lomake
CREATE VIEW TIETOOMAISUUS_JARJESTELMA_NIMI
AS select tietoomaisuus.*, tietojarjestelmasalkku.jarjestelman_nimi
from tietoomaisuus
join tietojarjestelmasalkku on (tietoomaisuus.tietojarjestelma_id = tietojarjestelmasalkku.tietojarjestelmatunnus);
