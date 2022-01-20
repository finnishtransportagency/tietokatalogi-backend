Instructions for database management are in tietokatalogi/db/README

More complete documentation is in project wiki in extranet


## Backend
To build the maven project, run:
```
mvn clean install -Dmaven.test.skip=true
```

Run the backend with
```
mvn tomcat7:run -Denv=local
```

or in a container with
```
docker-compose up --build
```

When finished, run (from another terminal)
```
docker-compose down
```

### Testing
Run JUnit tests with
```
mvn test -Ddb=h2
```
The tests are meant to be run against the h2 in-memory database, which is selected with the -Ddb=h2 option.

The test database is defined based on the files located in `src/main/resources/db/src/sql/h2_database`.
After changes to these files, run 
```
./createSchemaScript.sh
```
to update the init script for the h2 database.

The test suite has some tests that must be run against the real postgresql database. These tests are skipped by default.
See `src/main/resources/db/README.md` for instructions on running these tests manually.