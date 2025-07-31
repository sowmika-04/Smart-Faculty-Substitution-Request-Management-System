# Use official OpenJDK image
FROM eclipse-temurin:17-jdk-alpine

# Set working directory
WORKDIR /app

# Copy all files
COPY . .

# Package the application using Maven wrapper
RUN ./mvnw clean package -DskipTests

# Expose port 8080 (Spring Boot default)
EXPOSE 8080

# Run the jar file
git add DockerfileCMD ["java", "-jar", "target/faculty-substitution-0.0.1-SNAPSHOT.jar"]
