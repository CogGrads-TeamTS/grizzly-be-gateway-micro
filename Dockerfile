FROM openjdk
MAINTAINER Piotr Minkowski <piotr.minkowski@gmail.com>
ADD target/gateway-service.jar gateway-service.jar
ENTRYPOINT ["java", "-Xms32m", "-Xmx128m", "-Xss32m", "-Xmn64m", "-jar", "/gateway-service.jar"]
EXPOSE 8765