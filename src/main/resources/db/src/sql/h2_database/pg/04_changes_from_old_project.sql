alter table termilomake drop column huomautus_1;
alter table termilomake drop column huomautus_2;
alter table termilomake alter maaritelma type character varying(4000);

alter table termilomakehistoria drop column huomautus_1;
alter table termilomakehistoria drop column huomautus_2;
alter table termilomakehistoria alter maaritelma type character varying(4000);
