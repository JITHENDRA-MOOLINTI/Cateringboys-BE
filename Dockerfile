# ------------ STAGE 1: Build the Spring Boot JAR ------------
FROM eclipse-temurin:17-jdk-alpine as builder
WORKDIR /app

# Copy project files
COPY . .

# Build the jar using Maven
RUN ./mvnw -B -DskipTests package || mvn -B -DskipTests package

# ------------ STAGE 2: Run the Application ------------------
FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app

# Copy the built JAR from builder stage
COPY --from=builder /app/target/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["sh", "-c", "java -Dserver.port=$PORT -jar app.jar"]

