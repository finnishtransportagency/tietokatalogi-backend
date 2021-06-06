# Start docker-machine
docker-machine start tietokatalogi

# Get machines env-variables
& docker-machine env tietokatalogi | Invoke-Expression

# Start Oracle container
docker start oracle-xe

# Start Tomcat container
docker start tomcat
