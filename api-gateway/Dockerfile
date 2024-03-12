FROM openjdk:17-jdk-slim
ARG JAR_FILE=api-gateway-0.0.1.jar
COPY ${JAR_FILE} app_apiGateWay.jar
ENTRYPOINT ["java", "-jar", "app_apiGateWay.jar"]