FROM anapsix/alpine-java
MAINTAINER Stefan Colic <stefan.colic4@gmail.com>
ADD target/containerized-discovery-0.0.1-SNAPSHOT.jar containerized-discovery.jar
ENTRYPOINT ["java", "-jar", "/containerized-discovery.jar"]
EXPOSE 8761
