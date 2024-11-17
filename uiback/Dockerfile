FROM openjdk:17-jdk-slim
ARG JAR_FILE=./build/libs/uiback.jar
COPY ${JAR_FILE} app/uiback.jar
ENTRYPOINT ["java", "-jar", "/app/uiback.jar"]