# Use an official OpenJDK runtime as a parent image
FROM openjdk:17

# Set the working directory in the container
WORKDIR /app

COPY --from=build ManagerXpressBack/target/my-application.jar /app.jar

# Expose the port that your application will run on
EXPOSE 8080

# Define environment variables
ENV JAVA_OPTS=""
ENV SPRING_PROFILES_ACTIVE=""

# Run the application when the container starts
CMD ["java", "-jar", "your-application.jar"]
