
ALTER TABLE ONLY tietok.tietojarjpalvliittyvajarj
    ADD CONSTRAINT fk_tietojarjpalvliittyvajarj_1 FOREIGN KEY (tietojarjpalvelutunnus)
            REFERENCES tietok.tietojarjestelmapalvelu(tunnus);

ALTER TABLE ONLY tietok.tietojarjpalvliittyvajarj
    ADD CONSTRAINT fk_tietojarjpalvliittyvajarj_2 FOREIGN KEY (liittyvajarjestelmatunnus)
            REFERENCES tietok.tietojarjestelmasalkku(tietojarjestelmatunnus);


-- ANALPK-1397 Uusi Elinkaari-alasvetovalikko Järjestelmälinkkaus-osioon

ALTER TABLE tietok.jarjestelmalinkkaus ADD elinkaaritila character varying(255);
ALTER TABLE tietok.jarjestelmalinkkaushistoria ADD elinkaaritila character varying(255);

UPDATE tietok.jarjestelmalinkkaus
SET elinkaaritila = 'Tuotannossa'
WHERE elinkaaritila IS NULL;


-- ANALPK-1483 Tietokatalogi: Liittyvä järjestelmä järjestelmälinkkauksen nimeen
-- Refactoring to add liittyva jarjestelma information to db
-- (was previously inferred from other data).
ALTER TABLE tietok.tietojarjpalvelu_tietolaji ADD liittyva_jarjestelma NUMERIC(38,10)
    CONSTRAINT tietojarjpalvelu_tietolaji_fk3 REFERENCES tietok.tietojarjestelmasalkku(tietojarjestelmatunnus);

ALTER TABLE tietok.tietojarjpalvelutietohistoria ADD liittyva_jarjestelma NUMERIC(38,10);


INSERT INTO tietok.tietojarjestelma_kasite_arvo (kasite_wid, kasite, arvo) VALUES(136, 'Rahoitusmomentti', 'Jaettu tienpidolle, radanpidolle ja vesiväylänpidolle');


-- ANALPA-1783 Sanasto Pdf-muodossa - lisäys Sanastolomakkeeseen
ALTER TABLE tietok.termilomake ADD pdf_linkki VARCHAR(4000);
ALTER TABLE tietok.termilomakehistoria ADD pdf_linkki VARCHAR(4000);
ALTER TABLE tietok.termilomake ADD pdf_linkki_nimi VARCHAR(4000);
ALTER TABLE tietok.termilomakehistoria ADD pdf_linkki_nimi VARCHAR(4000);

ALTER TABLE tietok.termilomake ADD kasitekaavio_linkki VARCHAR(4000);
ALTER TABLE tietok.termilomakehistoria ADD kasitekaavio_linkki VARCHAR(4000);
ALTER TABLE tietok.termilomake ADD kasitekaavio_linkki_nimi VARCHAR(4000);
ALTER TABLE tietok.termilomakehistoria ADD kasitekaavio_linkki_nimi VARCHAR(4000);

ALTER TABLE tietok.termilomake ADD muokattava_tunnus NUMERIC(38,10);
ALTER TABLE tietok.termilomakehistoria ADD muokattava_tunnus NUMERIC(38,10);

UPDATE tietok.termilomake SET muokattava_tunnus = termilomaketunnus WHERE muokattava_tunnus IS NULL;
