# Use an official OpenJDK runtime as a parent image
FROM adoptopenjdk:17-jre-hotspot

# Set the working directory in the container
WORKDIR /app

# Copy the application JAR file into the container at /app
COPY ManagerXpressBack/target/ManagerXpressBack-0.0.1-SNAPSHOT.jar /app/

# Expose the port that your application will run on
EXPOSE 8080

# Define environment variables
ENV JAVA_OPTS=""
ENV SPRING_PROFILES_ACTIVE=""

# Run the application when the container starts
CMD ["java", "-jar", "your-application.jar"]
