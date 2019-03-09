FROM binayakd86/koo-swimming-builder as builder
COPY src /usr/src/app/src
WORKDIR /usr/src/app
RUN mkdir db && mvn package

FROM openjdk:8-jre-alpine
COPY --from=0 /usr/src/app/target/koo-swimming-0.0.1-SNAPSHOT.jar /usr/src/app/app.jar
WORKDIR /usr/src/app
RUN mkdir db
EXPOSE 8080
CMD ["java", "-jar", "app.jar"]