FROM openjdk:latest
COPY ./target/world_population-0.1.0.3-jar-with-dependencies.jar /tmp
# working directory of a Docker container
WORKDIR /tmp
# tells docker to run application, first value is command and the last two are parameters
ENTRYPOINT ["java", "-jar", "world_population-0.1.0.3-jar-with-dependencies.jar"]
