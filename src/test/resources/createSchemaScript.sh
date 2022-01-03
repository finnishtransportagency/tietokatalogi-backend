# Concatenate dbmaintain sql scripts to a single file.
# This provides a starting point for creating a schema for h2 database.
# However, parts of the resulting script need to be manually edited to
# be compatible with h2.
find ../../main/resources/db/src/sql/h2_database/pg/ -type f | sort -n | xargs cat > createH2Schema.sql
