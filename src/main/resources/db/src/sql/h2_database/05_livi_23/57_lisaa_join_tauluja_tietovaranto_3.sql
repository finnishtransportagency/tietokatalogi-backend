-- ANALPK-1246: Lisäys tietovaranto-formiin
CREATE TABLE TIETOVARANTOTIEDONOHJAUS (
    RIVITUNNUS NUMBER,
    TIETOVARANTOTUNNUS NUMBER,
    TIEDONOHJAUSSUUNNITELMA VARCHAR2(255),
    CONSTRAINT TIETOVARANTOTIEDONOHJAUS_PK PRIMARY KEY (RIVITUNNUS),
    CONSTRAINT TIETOVARANTOTIEDONOHJAUS_FK FOREIGN KEY (TIETOVARANTOTUNNUS) REFERENCES TIETOVARANTO(TIETOVARANTOTUNNUS)
);

CREATE SEQUENCE TIETOVARTIEDONOHJAUS_ID_SEQ START WITH 1;

CREATE TABLE TIETOVARATIEDONOHJAUSHISTORIA (
    RIVI_ID NUMBER,
    TIETOVARANTOTUNNUS NUMBER,
    TIEDONOHJAUSSUUNNITELMA VARCHAR2(255),
    RIVILUOTUPVM TIMESTAMP (6),
    HISTORIATYYPPI VARCHAR2(6),
    RIVIMUOKKAAJATUNNUS VARCHAR2(150)
);

CREATE SEQUENCE TIETOVARATIEDONOHJSHIST_ID_SEQ START WITH 1;