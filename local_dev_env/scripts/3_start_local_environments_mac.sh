# Start docker-machine
docker-machine start tietokatalogi

# Get machines env-variables
docker-machine env tietokatalogi
eval $(docker-machine env tietokatalogi)

# Start Oracle container
docker start oracle-xe

# Start Tomcat container
docker start tomcat
