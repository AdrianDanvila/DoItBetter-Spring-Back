FROM amazoncorretto:17-alpine-jdk

COPY target/app-0.0.1-SNAPSHOT app.java

ENTRYPOINT [ "java","-jar","/app.jar" ]