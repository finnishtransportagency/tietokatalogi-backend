-- TIET-152 Lisää kentät omistaja ja sijainti
alter table FYYSINENTIETOVARANTO add (OMISTAJA VARCHAR2(150));
alter table FYYSINENTIETOVARANTO add (SIJAINTI VARCHAR2(2000));
