-- TKYP-138 Muuta vanha linkkausdata uuden taulun mukaiseksi

-- Move jarjestelma-looginen links in the database from
-- LOOGINENTIETOVARANTO.KOODI into JARJESTELMALOOGINEN join table.

-- The trailing slash is needed so that the maven dbmaintain plugin is able to execute this script.
-- See: http://www.dbmaintain.org/tutorial.html#PLSQL_support
--  "Oracle, DB2 and MySQL PL-SQL syntax is supported
--  (eg. functions and stored procedures). Blocks of PL/SQL code must always end with a separate line containing
--  a single forward slash."

-- CREATE OR REPLACE TRIGGER JARJESTELMALOOGINEN_PK_INCR
--     BEFORE INSERT ON JARJESTELMALOOGINEN
--     FOR EACH ROW
-- BEGIN
--     :NEW.RIVITUNNUS := JARJESTELMALOOGINEN_ID_SEQ.nextval;
-- END;
-- /
