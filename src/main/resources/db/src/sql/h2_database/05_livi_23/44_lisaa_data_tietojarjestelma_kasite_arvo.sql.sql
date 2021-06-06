-- TKYP-157 Päivityksiä Järjestelmäsalkun tietoturvakenttiin

-- Backup table
-- CREATE TABLE TIETOJARJ_KASITE_ARVO_28092020 AS SELECT * FROM TIETOJARJESTELMA_KASITE_ARVO;

INSERT INTO TIETOJARJESTELMA_KASITE_ARVO (KASITE_WID, KASITE, ARVO) VALUES (107, 'TURVALLISUUSLUOKITUS', 'Turvallisuusluokka I');
INSERT INTO TIETOJARJESTELMA_KASITE_ARVO (KASITE_WID, KASITE, ARVO) VALUES (108, 'TURVALLISUUSLUOKITUS', 'Turvallisuusluokka II');
INSERT INTO TIETOJARJESTELMA_KASITE_ARVO (KASITE_WID, KASITE, ARVO) VALUES (109, 'TURVALLISUUSLUOKITUS', 'Turvallisuusluokka III');
INSERT INTO TIETOJARJESTELMA_KASITE_ARVO (KASITE_WID, KASITE, ARVO) VALUES (110, 'TURVALLISUUSLUOKITUS', 'Turvallisuusluokka IV');
INSERT INTO TIETOJARJESTELMA_KASITE_ARVO (KASITE_WID, KASITE, ARVO) VALUES (111, 'TARKEINTA', 'Luottamuksellisuus');
INSERT INTO TIETOJARJESTELMA_KASITE_ARVO (KASITE_WID, KASITE, ARVO) VALUES (112, 'TARKEINTA', 'Eheys');
INSERT INTO TIETOJARJESTELMA_KASITE_ARVO (KASITE_WID, KASITE, ARVO) VALUES (113, 'TARKEINTA', 'Saatavuus');
INSERT INTO TIETOJARJESTELMA_KASITE_ARVO (KASITE_WID, KASITE, ARVO) VALUES (114, 'TIEDONLUOTTAMUKSELLISUUS', 'Ei lainkaan tärkeä');
INSERT INTO TIETOJARJESTELMA_KASITE_ARVO (KASITE_WID, KASITE, ARVO) VALUES (115, 'TIEDONLUOTTAMUKSELLISUUS', 'Jonkin verran tärkeä');
INSERT INTO TIETOJARJESTELMA_KASITE_ARVO (KASITE_WID, KASITE, ARVO) VALUES (116, 'TIEDONLUOTTAMUKSELLISUUS', 'Tärkeä');
INSERT INTO TIETOJARJESTELMA_KASITE_ARVO (KASITE_WID, KASITE, ARVO) VALUES (117, 'TIEDONLUOTTAMUKSELLISUUS', 'Erittäin tärkeä');
INSERT INTO TIETOJARJESTELMA_KASITE_ARVO (KASITE_WID, KASITE, ARVO) VALUES (118, 'TIEDON_SAATAVUUS', 'Jonkin verran tärkeä');
INSERT INTO TIETOJARJESTELMA_KASITE_ARVO (KASITE_WID, KASITE, ARVO) VALUES (119, 'TIEDON_SAATAVUUS', 'Tärkeä');
INSERT INTO TIETOJARJESTELMA_KASITE_ARVO (KASITE_WID, KASITE, ARVO) VALUES (120, 'TIEDON_SAATAVUUS', 'Erittäin tärkeä');
INSERT INTO TIETOJARJESTELMA_KASITE_ARVO (KASITE_WID, KASITE, ARVO) VALUES (121, 'TIEDON_EHEYS', 'Jonkin verran tärkeä');
INSERT INTO TIETOJARJESTELMA_KASITE_ARVO (KASITE_WID, KASITE, ARVO) VALUES (122, 'TIEDON_EHEYS', 'Tärkeä');
INSERT INTO TIETOJARJESTELMA_KASITE_ARVO (KASITE_WID, KASITE, ARVO) VALUES (123, 'TIEDON_EHEYS', 'Erittäin tärkeä');
