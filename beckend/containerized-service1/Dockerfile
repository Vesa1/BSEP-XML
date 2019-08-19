FROM anapsix/alpine-java
MAINTAINER Stefan Colic <stefan.colic4@gmail.com>
ADD target/containerized-service1-0.0.1-SNAPSHOT.jar containerized-service1.jar
ENTRYPOINT ["java", "-jar", "/containerized-service1.jar"]
EXPOSE 2222