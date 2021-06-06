-- Rename sovellus import table so that the table and its sequence
-- are named the same way, since some existing code assumes that
-- sequence name := table name + "_id_seq"
RENAME SOVELLUS_TUONNIT TO SOVELLUS_TUONTI;