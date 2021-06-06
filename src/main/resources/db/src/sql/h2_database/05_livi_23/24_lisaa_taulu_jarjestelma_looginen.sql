-- TKYP-133 Loogisen tietovarannon koodi-kentän muutos

CREATE TABLE JARJESTELMALOOGINEN (
    RIVITUNNUS NUMBER,
    JARJESTELMATUNNUS NUMBER,
	LOOGINENTUNNUS NUMBER,
	CONSTRAINT JARJESTELMALOOGINEN_PK PRIMARY KEY (RIVITUNNUS)
);

CREATE SEQUENCE "JARJESTELMALOOGINEN_ID_SEQ" START WITH 1;
