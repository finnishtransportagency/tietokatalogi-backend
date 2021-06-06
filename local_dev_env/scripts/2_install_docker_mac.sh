# Install Cask
brew tap caskroom/cask

# Install docker toolbox
brew cask install docker-toolbox

# Create docker-machine
docker-machine create --driver "virtualbox" tietokatalogi

# Stop docker-machine (needed for setting changes)
docker-machine stop tietokatalogi

# Allow port forwarding to port 1521 (Oracle)
VBoxManage modifyvm "tietokatalogi" --natpf1 "oracle-xe,tcp,,1521,,1521"

# Allow port forwarding to port 8080 (Tomcat)
VBoxManage modifyvm "tietokatalogi" --natpf1 "tomcat,tcp,,8080,,8080"

# Start docker-machine
docker-machine start tietokatalogi

# Get machines env-variables
docker-machine env tietokatalogi
eval $(docker-machine env tietokatalogi)

# Create internal network for containers
docker network create tietokatalogiNetwork

# Create Oracle container
docker run -d --shm-size=2g --network=tietokatalogiNetwork --name=oracle-xe -p 1521:1521 alexeiled/docker-oracle-xe-11g

# Create Tomcat container
docker run -d --network=tietokatalogiNetwork --name=tomcat -p 8080:8080 tomcat:7

# Create required user for tietokatalogi
cd ../../db/
mvn properties:read-project-properties dbmaintain:updateDatabase -DconfigFile=dbmaintain_local_init.properties

# Stop docker-machine (start only when needed)
docker-machine stop tietokatalogi
