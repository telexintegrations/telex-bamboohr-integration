# Use an official OpenJDK runtime with Java 21 as the base image
FROM eclipse-temurin:21-jdk-jammy as builder

# Set the working directory inside the container
WORKDIR /app

# Copy the Maven project files
COPY pom.xml .
COPY src ./src

# Build the application using Maven
RUN ./mvnw clean package -DskipTests

# Use a smaller base image for the final stage
FROM eclipse-temurin:21-jre-jammy

# Set the working directory
WORKDIR /app

# Copy the built JAR file from the builder stage
COPY --from=builder /app/target/telex-bamboohr-integration-*.jar app.jar

# Expose the port your app runs on (change 8080 if your app uses a different port)
EXPOSE 8080

# Define the command to run the application
CMD ["java", "-jar", "app.jar"]