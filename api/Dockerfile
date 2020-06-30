FROM maven:3.6.3-jdk-11-slim AS build
COPY /src /usr/src/movie-store/src
RUN rm -rf /usr/src/movie-store/src/main/resources/application.yml
RUN mv /usr/src/movie-store/src/main/resources/application-docker.yml /usr/src/movie-store/src/main/resources/application.yml
COPY pom.xml /usr/src/movie-store
COPY Dockerfile /usr/src/movie-store
RUN mvn -f /usr/src/movie-store/pom.xml -DskipTests=true clean install

FROM openjdk:11-jre-slim
RUN apt-get update
COPY --from=build /usr/src/movie-store/target/movie-store-0.0.1.jar app.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]
