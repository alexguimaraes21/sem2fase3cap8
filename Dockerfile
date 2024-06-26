FROM openjdk:17
RUN adduser spring
USER spring:spring
COPY ./target/app.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]