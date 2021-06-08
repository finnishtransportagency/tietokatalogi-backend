Deployment instructions in tietokatalogi/deployment/README

Instructions for setting up local environment using docker in tietokatalogi/local_dev_env/README

Instructions for database management are in tietokatalogi/db/README

The api is documented in tietokatalogi/documentation/documentation.html

More complete documentation is in project wiki in extranet

## Create a complete deployable package
In tietokatalogi/ folder run 
```
sh build.sh
```


## Backend
To build the maven project, run:
```
mvn clean install -Dmaven.test.skip=true
```


Run the backend with
```
mvn tomcat7:run -Denv=local
```


Change the backend server port (e.g. if the local db tries to use the same port):
Run with the added option
```
-Dmaven.tomcat.port=8081
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

The backend requires ojdbc6 manual maven installation because of Oracle's licensing policy.
```
mvn install:install-file -Dfile=tietokatalogi/lib/ojdbc6.jar -DgroupId=com.oracle -DartifactId=ojdbc6 -Dversion=11.2.0.3 -Dpackaging=jar
```
