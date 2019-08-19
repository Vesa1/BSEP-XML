FROM anapsix/alpine-java
MAINTAINER Stefan Colic <stefan.colic4@gmail.com>
ADD target/containerized-service2-0.0.1-SNAPSHOT.jar containerized-service2.jar
ENTRYPOINT ["java", "-jar", "/containerized-service2.jar"]
EXPOSE 2222
