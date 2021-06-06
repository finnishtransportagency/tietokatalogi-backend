-- Rename sovellus import table so that the table and its sequence
-- are named the same way, since some existing code assumes that
-- sequence name := table name + "_id_seq"
ALTER TABLE SOVELLUS_TUONNIT RENAME TO SOVELLUS_TUONTI;
