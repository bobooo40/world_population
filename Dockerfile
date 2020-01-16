FROM openjdk:latest
COPY ./target/world_population-0.1.0.3-jar-with-dependencies.jar /tmp
WORKDIR /tmp
ENTRYPOINT ["java", "-jar", "world_population-0.1.0.3-jar-with-dependencies.jar"]
