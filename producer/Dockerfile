FROM openjdk:17-jdk-slim

WORKDIR /app

COPY build/libs/producer.jar /app/producer.jar

CMD ["java", "-jar", "producer.jar"]

EXPOSE 8081
