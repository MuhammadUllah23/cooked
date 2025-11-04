# Use official OpenJDK 17 slim image
FROM openjdk:17.0.1-jdk-slim

# Optional: Set a working directory inside container
WORKDIR /app

# Copy your built jar file into the container
# Make sure you build your project before docker build
COPY target/cooked-backend-0.0.1-SNAPSHOT.jar app.jar

# Expose port 8080 (Spring Boot default)
EXPOSE 8080

# Run the jar
ENTRYPOINT ["java","-jar","/app/app.jar"]
