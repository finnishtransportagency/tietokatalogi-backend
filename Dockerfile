#Tomcat 7 poimittu projektin pom:sta ja openjdk8 confluencesta tuotannon ajoympariston kuvauksesta
FROM tomcat:7.0.109-jdk8-adoptopenjdk-openj9

RUN apt update && apt upgrade --quiet --yes

# Remove default apps
RUN rm -rf {$CATALINA_HOME}/webapps/*

# Copy and extract
ADD target/TietokatalogiUI.war ${CATALINA_HOME}/webapps/tietokatalogi.war
