FROM openjdk:17-jdk-slim
ARG JAR_FILE=./build/libs/consumer.jar
COPY ${JAR_FILE} app/consumer.jar
ENTRYPOINT ["java", "-jar", "/app/consumer.jar"]