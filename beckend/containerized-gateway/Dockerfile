FROM anapsix/alpine-java
MAINTAINER Stefan Colic <stefan.colic4@gmail.com>
ADD target/containerized-gateway-0.0.1-SNAPSHOT.jar containerized-gateway.jar
ENTRYPOINT ["java", "-jar", "/containerized-gateway.jar"]
EXPOSE 8761
