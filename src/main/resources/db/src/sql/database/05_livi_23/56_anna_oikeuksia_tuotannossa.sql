-- Commented out since this fails in maven dbmaintain plugin

--BEGIN
--  FOR r IN (
--    SELECT owner, table_name
--    FROM all_tables
--    WHERE owner = 'TIETOK'
--  )
--  LOOP
--    EXECUTE IMMEDIATE 'GRANT SELECT ON ' || r.owner || '.' || r.table_name || ' to ' || 'TK_LUKU_ROLE';
--  END LOOP;
--END;

--The rights can be checked with this:
--SELECT * FROM table_privileges WHERE owner = 'TIETOK' ORDER BY table_name;
