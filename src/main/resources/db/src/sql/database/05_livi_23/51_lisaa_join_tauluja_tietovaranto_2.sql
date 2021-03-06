-- ANALPK-1182: Tietokatalogi-muutokset
CREATE TABLE TIETOVARANTOKASITTELYNPERUSTE (
    RIVITUNNUS NUMBER,
    TIETOVARANTOTUNNUS NUMBER,
    KASITTELYN_PERUSTE VARCHAR2(255),
    CONSTRAINT TIETOVARANTOKASITTELPERUSTE_PK PRIMARY KEY (RIVITUNNUS),
    CONSTRAINT TIETOVARANTOKASITTELPERUSTE_FK FOREIGN KEY (TIETOVARANTOTUNNUS) REFERENCES TIETOVARANTO(TIETOVARANTOTUNNUS)
);

CREATE TABLE TIETOVARANTOYLLAPITOMUUTAHO (
    RIVITUNNUS NUMBER,
    TIETOVARANTOTUNNUS NUMBER,
    YLLAPITO_MUU_TAHO VARCHAR2(255),
    CONSTRAINT TIETOVARANTOYLLAPITOMUUTAHO_PK PRIMARY KEY (RIVITUNNUS),
    CONSTRAINT TIETOVARANTOYLLAPITOMUUTAHO_FK FOREIGN KEY (TIETOVARANTOTUNNUS) REFERENCES TIETOVARANTO(TIETOVARANTOTUNNUS)
);

CREATE TABLE TIETOVARANTOVASTAANOTTAJARYHMA (
    RIVITUNNUS NUMBER,
    TIETOVARANTOTUNNUS NUMBER,
    VASTAANOTTAJARYHMA VARCHAR2(255),
    CONSTRAINT TIETOVARANTOVASTAANOTTRYHMA_PK PRIMARY KEY (RIVITUNNUS),
    CONSTRAINT TIETOVARANTOVASTAANOTTRYHMA_FK FOREIGN KEY (TIETOVARANTOTUNNUS) REFERENCES TIETOVARANTO(TIETOVARANTOTUNNUS)
);

CREATE SEQUENCE TIETOVARKASITTELPERUSTE_ID_SEQ START WITH 1;
CREATE SEQUENCE TIETOVARYLLAPITOMUUTAHO_ID_SEQ START WITH 1;
CREATE SEQUENCE TIETOVARVASTAANOTTRYHMA_ID_SEQ START WITH 1;

CREATE TABLE TIETOVARAKASITTPERUSTEHISTORIA (
    RIVI_ID NUMBER,
    TIETOVARANTOTUNNUS NUMBER,
    KASITTELYN_PERUSTE VARCHAR2(255),
    RIVILUOTUPVM TIMESTAMP (6),
    HISTORIATYYPPI VARCHAR2(6),
    RIVIMUOKKAAJATUNNUS VARCHAR2(150)
);

CREATE TABLE TIETOVARAYLLAPITOTAHOHISTORIA (
    RIVI_ID NUMBER,
    TIETOVARANTOTUNNUS NUMBER,
    YLLAPITO_MUU_TAHO VARCHAR2(255),
    RIVILUOTUPVM TIMESTAMP (6),
    HISTORIATYYPPI VARCHAR2(6),
    RIVIMUOKKAAJATUNNUS VARCHAR2(150)
);

CREATE TABLE TIETOVARAVASTAANORYHMAHISTORIA (
    RIVI_ID NUMBER,
    TIETOVARANTOTUNNUS NUMBER,
    VASTAANOTTAJARYHMA VARCHAR2(255),
    RIVILUOTUPVM TIMESTAMP (6),
    HISTORIATYYPPI VARCHAR2(6),
    RIVIMUOKKAAJATUNNUS VARCHAR2(150)
);

CREATE SEQUENCE TIETOVARAKASITPERUSHIST_ID_SEQ START WITH 1;
CREATE SEQUENCE TIETOVARAYLLAPTTAHOHIST_ID_SEQ START WITH 1;
CREATE SEQUENCE TIETOVARAVASTARYHMAHIST_ID_SEQ START WITH 1;

INSERT INTO TIETOVARANTOKASITTELYNPERUSTE (RIVITUNNUS, TIETOVARANTOTUNNUS, KASITTELYN_PERUSTE)
SELECT TIETOVARKASITTELPERUSTE_ID_SEQ.nextval, TIETOVARANTOTUNNUS, KASITTELYN_PERUSTE FROM TIETOVARANTO WHERE KASITTELYN_PERUSTE IS NOT NULL; 

INSERT INTO TIETOVARANTOYLLAPITOMUUTAHO (RIVITUNNUS, TIETOVARANTOTUNNUS, YLLAPITO_MUU_TAHO)
SELECT TIETOVARYLLAPITOMUUTAHO_ID_SEQ.nextval, TIETOVARANTOTUNNUS, YLLAPITO_MUUT_TAHOT FROM TIETOVARANTO WHERE YLLAPITO_MUUT_TAHOT IS NOT NULL; 

INSERT INTO TIETOVARANTOVASTAANOTTAJARYHMA (RIVITUNNUS, TIETOVARANTOTUNNUS, VASTAANOTTAJARYHMA)
SELECT TIETOVARVASTAANOTTRYHMA_ID_SEQ.nextval, TIETOVARANTOTUNNUS, VASTAANOTTAJARYHMAT FROM TIETOVARANTO WHERE VASTAANOTTAJARYHMAT IS NOT NULL; 

INSERT INTO TIETOVARAKASITTPERUSTEHISTORIA (RIVI_ID, TIETOVARANTOTUNNUS, KASITTELYN_PERUSTE, RIVILUOTUPVM, HISTORIATYYPPI, RIVIMUOKKAAJATUNNUS)
SELECT TIETOVARAKASITPERUSHIST_ID_SEQ.nextval, TIETOVARANTOTUNNUS, KASITTELYN_PERUSTE, SYSDATE, 'ADD', RIVIMUOKKAAJATUNNUS FROM TIETOVARANTO WHERE KASITTELYN_PERUSTE IS NOT NULL;

INSERT INTO TIETOVARAYLLAPITOTAHOHISTORIA (RIVI_ID, TIETOVARANTOTUNNUS, YLLAPITO_MUU_TAHO, RIVILUOTUPVM, HISTORIATYYPPI, RIVIMUOKKAAJATUNNUS)
SELECT TIETOVARAYLLAPTTAHOHIST_ID_SEQ.nextval, TIETOVARANTOTUNNUS, YLLAPITO_MUUT_TAHOT, SYSDATE, 'ADD', RIVIMUOKKAAJATUNNUS FROM TIETOVARANTO WHERE YLLAPITO_MUUT_TAHOT IS NOT NULL;

INSERT INTO TIETOVARAVASTAANORYHMAHISTORIA (RIVI_ID, TIETOVARANTOTUNNUS, VASTAANOTTAJARYHMA, RIVILUOTUPVM, HISTORIATYYPPI, RIVIMUOKKAAJATUNNUS)
SELECT TIETOVARAVASTARYHMAHIST_ID_SEQ.nextval, TIETOVARANTOTUNNUS, VASTAANOTTAJARYHMAT, SYSDATE, 'ADD', RIVIMUOKKAAJATUNNUS FROM TIETOVARANTO WHERE VASTAANOTTAJARYHMAT IS NOT NULL;

ALTER TABLE TIETOVARANTO MODIFY TEKNISET_ORG_TURVATOIMENPITEET VARCHAR2(4000);
ALTER TABLE TIETOVARANTOHISTORIA MODIFY TEKNISET_ORG_TURVATOIMENPITEET VARCHAR2(4000);

ALTER TABLE TIETOVARANTO DROP COLUMN KASITTELYN_PERUSTE;
ALTER TABLE TIETOVARANTOHISTORIA DROP COLUMN KASITTELYN_PERUSTE;

ALTER TABLE TIETOVARANTO DROP COLUMN YLLAPITO_MUUT_TAHOT;
ALTER TABLE TIETOVARANTOHISTORIA DROP COLUMN YLLAPITO_MUUT_TAHOT;

ALTER TABLE TIETOVARANTO DROP COLUMN VASTAANOTTAJARYHMAT;
ALTER TABLE TIETOVARANTOHISTORIA DROP COLUMN VASTAANOTTAJARYHMAT;
