# Database management

Database is updated using [dbmaintain-maven-plugin ](http://www.dbmaintain.org/maven-goals.html). All files placed in src/sql/database/postgres_scripts are executed in sql-server.
All updates are incremental - only new files are executed. Therefore you can insert files to folder with biggest ordernumber.

Create new folder for new development iteration (for example folder/version).  Sql-filename must begin with ordernumber, you can skip numbers but you cannot later add files with lower number (for example 03-add-user.sql after 05-add-group.sql is not allowed - next file must be 06 or bigger).

TODO: reorganize/remove old (non-postgres) scripts after migration is complete!

## Initialize database
Start the docker container by running `start-local-db.sh`
Connect to db with `sudo docker exec -it tk-pgsql bash`
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

## Updating test environments database
* Fill database username & password to dbmaintain\_livi\_testi.properties (if you haven't already done that). **DO NOT STORE PASSWORDS IN GIT**. Password is stored in [CRED-811](https://jira.solita.fi/browse/CRED-811).
* Open VPN connection [https://etayhteys.vayla.fi/](https://etayhteys.vayla.fi/)
* Open ssh-tunnel to test-env
* `ssh -L1530:10.129.46.171:1521 <yourKaccount>@172.17.204.73`
* Run updatescript `updateDatabaseLiviTesti.sh`

## Updating production environments database
* Fill database username & password to dbmaintain\_livi\_tuotanto.properties (if you haven't already done that). **DO NOT STORE PASSWORDS IN GIT**. Password is stored in [CRED-811](https://jira.solita.fi/browse/CRED-811).
* Open VPN connection [https://etayhteys.vayla.fi/](https://etayhteys.vayla.fi/)
* Open ssh-tunnel to test-env
* `ssh -L1535:10.129.47.76:1521 <yourKaccount>@172.17.201.21`
* Run updatescript `updateDatabaseLiviTuotanto.sh`


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