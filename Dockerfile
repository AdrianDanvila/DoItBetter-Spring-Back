# Fase 1: Construcción con Maven
FROM maven:3.8.5-openjdk-17 AS build
WORKDIR /app
COPY . . 
RUN mvn clean package -DskipTests

# Fase 2: Ejecución con OpenJDK
FROM openjdk:17.0.1-jdk-slim
WORKDIR /app
COPY --from=build /app/target/app-0.0.1-SNAPSHOT.jar app.jar
# Copia toda la carpeta 'uploads' al contenedor
COPY uploads uploads/

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]

