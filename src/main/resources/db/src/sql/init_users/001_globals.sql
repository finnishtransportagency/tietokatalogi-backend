--
-- PostgreSQL database cluster dump
--

SET default_transaction_read_only = off;

SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;

--
-- Roles
--

--CREATE ROLE tietokt;
ALTER ROLE tietokt WITH SUPERUSER INHERIT CREATEROLE CREATEDB LOGIN REPLICATION BYPASSRLS PASSWORD 'SCRAM-SHA-256$4096:4l7Pklh7YRDbeJXFpMj0Dg==$6jtuMLafvIvDhoKTeXk9PRGpMJXMNmQdPZXJjhUiWzU=:Za6bpe2zcoJbfm5AKCqvsHVbWHwGXydYCpg1U4TwN5E=';






--
-- PostgreSQL database cluster dump complete
--

-- Schema creation moved here from objects dump  -Alvar
--
-- Name: tietok; Type: SCHEMA; Schema: -; Owner: tietokt
--

CREATE SCHEMA tietok;


ALTER SCHEMA tietok OWNER TO tietokt;

SET default_tablespace = '';

SET default_table_access_method = heap;
