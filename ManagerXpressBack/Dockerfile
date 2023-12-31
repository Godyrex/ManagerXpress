# Stage 1: Build the application
FROM maven:3.8.3-openjdk-17 as build
WORKDIR /app
COPY . /app
RUN mvn -B clean package

# Stage 2: Create a minimal image with only the JAR file and Redis
FROM openjdk:17
WORKDIR /app
COPY --from=build /app/target/ManagerXpressBack-0.0.1-SNAPSHOT.jar /app.jar

# Install Redis
RUN apt update && \
    apt install -y redis-server

# Expose ports
EXPOSE 8080
EXPOSE 6379

# CMD
CMD ["java", "-jar", "app.jar"]
