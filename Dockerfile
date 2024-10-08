# COMPOSE
# BASE IMAGE FOR BUILD
FROM maven:3.9.8-eclipse-temurin-17 AS build

# CONFIGURE TEMPORARY VOLUME
WORKDIR /opt/app
COPY . /opt/app
RUN mvn clean package

# BASE IMAGE FOR RUNTIME
FROM eclipse-temurin:17-jre-alpine

# CONFIGURE FINAL IMAGE
WORKDIR /opt/app
COPY --from=build /opt/app/target/app.jar /opt/app/app.jar

ENV PROFILE=test

# PORT
EXPOSE 8080

# INIT
ENTRYPOINT ["sh", "-c", "java -Dspring.profioles.active=${PROFILE} -jar /opt/app/app.jar"]