FROM openjdk:8
ARG JAR_FILE=target/*.jar
COPY ./target/YourWindow-YourSoul-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8089
ENTRYPOINT ["java", "-jar", "/app.jar"]