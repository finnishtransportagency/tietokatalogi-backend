alter table tietok.termilomake drop column "huomautus_1";
alter table tietok.termilomake drop column "huomautus_2";
alter table tietok.termilomake alter "maaritelma" type character varying(4000);

alter table tietok.termilomakehistoria drop column "huomautus_1";
alter table tietok.termilomakehistoria drop column "huomautus_2";
alter table tietok.termilomakehistoria alter "maaritelma" type character varying(4000);
