# Get machines env-variables
docker-machine env tietokatalogi
eval $(docker-machine env tietokatalogi)

# Ssh to tomcat
docker exec -t -i tomcat /bin/bash
