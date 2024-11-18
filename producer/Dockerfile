FROM openjdk:17-jdk-slim
ARG JAR_FILE=./build/libs/producer.jar
COPY ${JAR_FILE} app/producer.jar
ENTRYPOINT ["java", "-jar", "/app/producer.jar"]