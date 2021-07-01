#Tomcat 7 poimittu projektin pom:sta ja openjdk8 confluencesta tuotannon ajoympariston kuvauksesta
#AWS ecr osoite vain pilvikayttoon, devissa voi poistaa (tai tehda toisen Dockerfilen)
FROM 894932018761.dkr.ecr.eu-west-1.amazonaws.com/tomcat:7-jdk8-openjdk

# Remove default apps
RUN rm -rf {$CATALINA_HOME}/webapps/*

# Copy and extract
ADD target/TietokatalogiUI.war ${CATALINA_HOME}/webapps/tietokatalogi.war
