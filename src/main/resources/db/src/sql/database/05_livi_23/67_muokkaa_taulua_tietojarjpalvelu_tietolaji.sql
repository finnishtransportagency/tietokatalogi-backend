
-- ANALPK-1483 Tietokatalogi: Liittyvä järjestelmä järjestelmälinkkauksen nimeen
-- Refactoring to add liittyva jarjestelma information to db
-- (was previously inferred from other data).
ALTER TABLE TIETOJARJPALVELU_TIETOLAJI ADD liittyva_jarjestelma NUMBER CONSTRAINT TIETOJARJPALVELU_TIETOLAJI_FK3
REFERENCES TIETOJARJESTELMASALKKU(tietojarjestelmatunnus);

ALTER TABLE TIETOJARJPALVELUTIETOHISTORIA ADD liittyva_jarjestelma NUMBER;
