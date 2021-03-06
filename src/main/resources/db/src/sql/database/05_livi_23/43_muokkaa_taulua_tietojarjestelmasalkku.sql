-- TKYP-157 Päivityksiä Järjestelmäsalkun tietoturvakenttiin

-- Backup table
-- CREATE TABLE TIETOJARJESTELMASALKK_28092020 AS SELECT * FROM TIETOJARJESTELMASALKKU;

ALTER TABLE TIETOJARJESTELMASALKKU ADD salassapidon_peruste VARCHAR2(255);
ALTER TABLE TIETOJARJESTELMASALKKU ADD turvallisuusluokitus VARCHAR2(255);
ALTER TABLE TIETOJARJESTELMASALKKU ADD tarkeinta VARCHAR2(255);
ALTER TABLE TIETOJARJESTELMASALKKU ADD tiedonluottamuksellisuus VARCHAR2(255);
ALTER TABLE TIETOJARJESTELMASALKKU ADD tiedon_saatavuus VARCHAR2(255);
ALTER TABLE TIETOJARJESTELMASALKKU ADD tiedon_eheys VARCHAR2(255);
ALTER TABLE TIETOJARJESTELMASALKKU ADD tarvitaan_viikonloppuna VARCHAR2(255);
ALTER TABLE TIETOJARJESTELMASALKKU ADD tarvitaan_audit_trail VARCHAR2(255);
ALTER TABLE TIETOJARJESTELMASALKKU ADD integraatioita_muihin VARCHAR2(255);

ALTER TABLE TIETOJARJESTELMASALKKUHISTORIA ADD salassapidon_peruste VARCHAR2(255);
ALTER TABLE TIETOJARJESTELMASALKKUHISTORIA ADD turvallisuusluokitus VARCHAR2(255);
ALTER TABLE TIETOJARJESTELMASALKKUHISTORIA ADD tarkeinta VARCHAR2(255);
ALTER TABLE TIETOJARJESTELMASALKKUHISTORIA ADD tiedonluottamuksellisuus VARCHAR2(255);
ALTER TABLE TIETOJARJESTELMASALKKUHISTORIA ADD tiedon_saatavuus VARCHAR2(255);
ALTER TABLE TIETOJARJESTELMASALKKUHISTORIA ADD tiedon_eheys VARCHAR2(255);
ALTER TABLE TIETOJARJESTELMASALKKUHISTORIA ADD tarvitaan_viikonloppuna VARCHAR2(255);
ALTER TABLE TIETOJARJESTELMASALKKUHISTORIA ADD tarvitaan_audit_trail VARCHAR2(255);
ALTER TABLE TIETOJARJESTELMASALKKUHISTORIA ADD integraatioita_muihin VARCHAR2(255);
