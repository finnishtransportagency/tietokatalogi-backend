#Tomcat 7 poimittu projektin pom:sta ja openjdk8 confluencesta tuotannon ajoympariston kuvauksesta
FROM tomcat:7-jdk8-openjdk

# Remove default apps
RUN rm -rf {$CATALINA_HOME}/webapps/*

# Copy and extract
ADD target/TietokatalogiUI.war ${CATALINA_HOME}/webapps/tietokatalogi.war
