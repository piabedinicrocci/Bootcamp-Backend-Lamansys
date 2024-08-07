FROM openjdk:17-jdk-slim

LABEL org.opencontainers.image.authors="dev@lamansys.com.ar"

WORKDIR /app
COPY mensajes/target/mensajes-1.0-SNAPSHOT.jar /app/bootcamp.jar

EXPOSE 8080

ENTRYPOINT ["java","-jar","/app/bootcamp.jar"]

