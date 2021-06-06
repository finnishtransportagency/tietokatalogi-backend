# Get machines env-variables
docker-machine env tietokatalogi
eval $(docker-machine env tietokatalogi)

# Copy war
docker cp ../../target/TietokatalogiUI.war tomcat:/usr/local/tomcat/webapps/tietokatalogi.war

# Wait 5s (Give tomcat time to unpack war)
sleep 5s

# Copy configs
docker cp config.properties tomcat:/usr/local/tomcat/webapps/tietokatalogi/WEB-INF/classes/
docker cp hibernate.cfg.xml tomcat:/usr/local/tomcat/webapps/tietokatalogi/WEB-INF/classes/

# Restart tomcat container
docker restart tomcat