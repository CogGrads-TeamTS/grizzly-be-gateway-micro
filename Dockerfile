FROM openjdk:8-jre-alpine
MAINTAINER Piotr Minkowski <piotr.minkowski@gmail.com>
ADD target/gateway-service.jar gateway-service.jar
ENTRYPOINT ["java", "-Xms32m", "-Xmx128m", "-Dspring.profiles.active=prod", "-jar", "/gateway-service.jar"]
EXPOSE 8765