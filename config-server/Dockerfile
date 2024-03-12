FROM openjdk:17-jdk-alpine
ARG JAR_FILE=config-server-0.0.1.jar
COPY ${JAR_FILE} app_config.jar
ENTRYPOINT ["java", "-jar", "app_config.jar"]