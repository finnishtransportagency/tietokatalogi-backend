
ALTER TABLE tietojarjpalvliittyvajarj
    ADD CONSTRAINT fk_tietojarjpalvliittyvajarj_1 FOREIGN KEY (tietojarjpalvelutunnus)
            REFERENCES tietojarjestelmapalvelu(tunnus);

ALTER TABLE tietojarjpalvliittyvajarj
    ADD CONSTRAINT fk_tietojarjpalvliittyvajarj_2 FOREIGN KEY (liittyvajarjestelmatunnus)
            REFERENCES tietojarjestelmasalkku(tietojarjestelmatunnus);


-- ANALPK-1397 Uusi Elinkaari-alasvetovalikko Järjestelmälinkkaus-osioon

ALTER TABLE jarjestelmalinkkaus ADD elinkaaritila character varying(255);
ALTER TABLE jarjestelmalinkkaushistoria ADD elinkaaritila character varying(255);

UPDATE jarjestelmalinkkaus
SET elinkaaritila = 'Tuotannossa'
WHERE elinkaaritila IS NULL;


-- ANALPK-1483 Tietokatalogi: Liittyvä järjestelmä järjestelmälinkkauksen nimeen
-- Refactoring to add liittyva jarjestelma information to db
-- (was previously inferred from other data).
ALTER TABLE tietojarjpalvelu_tietolaji ADD liittyva_jarjestelma NUMERIC(38,10)
    CONSTRAINT tietojarjpalvelu_tietolaji_fk3 REFERENCES tietojarjestelmasalkku(tietojarjestelmatunnus);

ALTER TABLE tietojarjpalvelutietohistoria ADD liittyva_jarjestelma NUMERIC(38,10);


INSERT INTO tietojarjestelma_kasite_arvo (kasite_wid, kasite, arvo) VALUES(136, 'Rahoitusmomentti', 'Jaettu tienpidolle, radanpidolle ja vesiväylänpidolle');


-- ANALPA-1783 Sanasto Pdf-muodossa - lisäys Sanastolomakkeeseen
ALTER TABLE termilomake ADD pdf_linkki VARCHAR(4000);
ALTER TABLE termilomakehistoria ADD pdf_linkki VARCHAR(4000);
ALTER TABLE termilomake ADD pdf_linkki_nimi VARCHAR(4000);
ALTER TABLE termilomakehistoria ADD pdf_linkki_nimi VARCHAR(4000);

ALTER TABLE termilomake ADD kasitekaavio_linkki VARCHAR(4000);
ALTER TABLE termilomakehistoria ADD kasitekaavio_linkki VARCHAR(4000);
ALTER TABLE termilomake ADD kasitekaavio_linkki_nimi VARCHAR(4000);
ALTER TABLE termilomakehistoria ADD kasitekaavio_linkki_nimi VARCHAR(4000);

ALTER TABLE termilomake ADD muokattava_tunnus NUMERIC(38,10);
ALTER TABLE termilomakehistoria ADD muokattava_tunnus NUMERIC(38,10);

UPDATE termilomake SET muokattava_tunnus = termilomaketunnus WHERE muokattava_tunnus IS NULL;
