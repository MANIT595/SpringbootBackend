# Use the official Java image as a parent image
FROM openjdk:17-jdk-slim

# Set the working directory in the container
WORKDIR /app

# Copy the JAR file (assuming it's in the target directory)
COPY target/Gateway-0.0.1-SNAPSHOT.jar app.jar

# Expose the port the app runs on (replace with your app's port)
EXPOSE 8085

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
