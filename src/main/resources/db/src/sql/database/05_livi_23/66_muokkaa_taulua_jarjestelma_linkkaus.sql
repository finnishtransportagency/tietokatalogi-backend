-- ANALPK-1397 Uusi Elinkaari-alasvetovalikko Järjestelmälinkkaus-osioon

ALTER TABLE JARJESTELMALINKKAUS ADD ELINKAARITILA VARCHAR2(255);
ALTER TABLE JARJESTELMALINKKAUSHISTORIA ADD ELINKAARITILA VARCHAR2(255);

UPDATE JARJESTELMALINKKAUS
SET ELINKAARITILA = 'Tuotannossa'
WHERE ELINKAARITILA IS NULL;
