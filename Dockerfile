FROM openjdk:8
EXPOSE 8080
ADD target/TrophoniusRestServer-0.0.1-SNAPSHOT.jar TrophoniusRestServer-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/TrophoniusRestServer-0.0.1-SNAPSHOT.jar"]
