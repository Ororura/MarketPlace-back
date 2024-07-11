# Second stage: create a minimal image to run the application
FROM openjdk:17-jdk-slim

# Set the working directory inside the container
WORKDIR /app

# Copy the compiled JAR file from the previous stage
COPY  ./build/libs/audiomarket-0.0.1-SNAPSHOT.jar ./app.jar

# Expose the port that the application uses
EXPOSE 8080

# Command to run the application
CMD ["java", "-jar", "app.jar"]
