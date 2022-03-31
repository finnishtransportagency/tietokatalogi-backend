-- EDIT 2022-03-23: The huomautus fields appear unused, but there's (little) data in them in production.
-- Leave the columns in dev and prod postgresql databases for now, but consider deletion later.

--alter table tietok.termilomake drop column "huomautus_1";
--alter table tietok.termilomake drop column "huomautus_2";
alter table tietok.termilomake alter "maaritelma" type character varying(4000);

--alter table tietok.termilomakehistoria drop column "huomautus_1";
--alter table tietok.termilomakehistoria drop column "huomautus_2";
alter table tietok.termilomakehistoria alter "maaritelma" type character varying(4000);
