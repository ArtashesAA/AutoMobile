# Compilación
FROM maven:3.8.6-eclipse-temurin-17 AS builder
WORKDIR /usr/src/project
COPY . .
RUN mvn package -DskipTests

# Ejecución
FROM openjdk:17-jdk-alpine
VOLUME /tmp
COPY --from=builder /usr/src/project/target/*.jar /app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]
