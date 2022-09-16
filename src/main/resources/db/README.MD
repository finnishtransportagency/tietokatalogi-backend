# Database management

Database is updated using [dbmaintain-maven-plugin ](http://www.dbmaintain.org/maven-goals.html), when managing the database container separately.
All files placed in src/sql/database/postgres_scripts are executed in sql-server.
All updates are incremental - only new files are executed. Therefore, you can insert files to folder with biggest ordernumber.

Create new folder for new development iteration (for example folder/version).  Sql-filename must begin with ordernumber, you can skip numbers, but you cannot later add files with lower number (for example 03-add-user.sql after 05-add-group.sql is not allowed - next file must be 06 or bigger).

## New, docker-compose based solution
Start backend and database by `docker-compose up`.
The database is automatically created and initialized based on the scripts in the `./src/sql/[...]` directories relative to this file.

In order to connect to the database using e.g. dbeaver, do the following:
  1. Start the database and backend
  2. Find the host of the database container using `docker network inspect {created network name}`. The network name will include the string `tietokatalogi`.
  3. Use same connection settings in dbeaver as in `dbmaintain_local.properties` or `hibernate.cfg.xml`, except substitute `localhost` with the host found in previous step. The database name is the same as username: `tietokt`.

The other steps below describe managing the database container separately (not using docker-compose).

## Initialize database
Uncomment the hibernate.connection.url line from `hibernate.cfg.xml` that corresponds to the separate container solution (as shown in comment in the file).

Start the docker container by running `start-local-db.sh`
Connect to db with
  1. `docker exec -it tk-pgsql bash`
  2. `psql -U tietokt`
Copy-paste and run contents of src/main/resources/db/src/sql/init_users/01_globals.sql.
Run `./updateDatabase.sh`

## Testing against the real postgres database
Some tests only work when run against a real pg database (instead of h2's pg mode). These are ignored in normal (h2) tests.
Run them separately as follows:
    - change host.docker.internal to localhost in hibernate.cfg.xml
    - run ./clearDatabase.sh && ./createDatabase.sh (needs to be run before every individual test)
    - run a single test such that -Ddb=h2 is absent from the run configuration

Note that these CLEAR the local database! Ensure that any important testing data in the local database is backed up!

## Updating database
Execute all files added after last update.

Run command `./updateDatabase.sh`

## Deleting all data from local database
**WARNING: THIS DELETES ALL YOUR TABLES AND DATA**

Run command `./clearDatabase.sh`

## Inserting test-data to database
Execute **insert_testdata.sql** using SQL Developer (or any other sql-client)


## Problemsolving
You tried to execute malformed sql? Execution failed because constraints? etc. Remove failed file from logs running command `./markErrorReverted.sh`

Want run last 10 files again?
Remove lines from DBMAINTAIN_SCRIPTS -table.
Note that you have still revert those changes manually (for example remove created tables).

You tried update database and got **ORA-00054: resource busy and acquire with NOWAIT specified or timeout expired** -error?
Disconnect your Sql-clint and try again.
