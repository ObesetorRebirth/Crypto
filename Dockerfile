
FROM openjdk:21-jdk-slim


WORKDIR /app


COPY target/Crypto-0.0.1-SNAPSHOT.jar /app/crypto-app.jar


EXPOSE 8080


ENTRYPOINT ["java", "-jar", "/app/crypto-app.jar"]