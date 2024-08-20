FROM maven:3.8.5-openjdk-17 AS build
COPY . .
RUN mvc clean package

FROM openjdk:17.0.1-jdk-slim
COPY --from=build /target/app-0.0.1-SNAPSHOT.jar app.java
EXPOSE 8080
ENTRYPOINT [ "java","-jar","app.jar" ]