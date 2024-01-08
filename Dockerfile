FROM maven:3.9-amazoncorretto-21 AS build
MAINTAINER ouharrioutman@gmail.com
VOLUME /tmp
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

FROM openjdk:21
WORKDIR /app
COPY --from=build app/target/jobinow-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8084
ENTRYPOINT ["java", "-jar", "/app/app.jar"]