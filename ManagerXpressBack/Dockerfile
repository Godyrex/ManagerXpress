# Stage 1: Build the application
FROM maven:3.8.3-openjdk-17 as build
WORKDIR /app
COPY . /app
RUN mvn -B clean package

# Stage 2: Create a minimal image with only the JAR file
FROM openjdk:17
WORKDIR /app
COPY --from=build /app/target/ManagerXpressBack-0.0.1-SNAPSHOT.jar /app.jar
EXPOSE 8080

CMD ["java", "-jar", "app.jar"]
