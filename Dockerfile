FROM maven:3.6.0-jdk-8-alpine as builder
COPY . /usr/src/app
WORKDIR /usr/src/app
RUN mvn clean package

FROM openjdk:8-jre-alpine
COPY --from=0 /usr/src/app/target/koo-swimming-0.0.1-SNAPSHOT.jar /usr/src/app/app.jar
WORKDIR /usr/src/app
RUN mkdir db
EXPOSE 8080
CMD ["java", " -jar", "app.jar"]