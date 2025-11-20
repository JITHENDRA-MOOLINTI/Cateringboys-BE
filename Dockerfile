# ---------- STAGE 1: build with Maven (JDK 21) ----------
FROM maven:3.9.6-eclipse-temurin-21 as builder
WORKDIR /app

# Copy only what is needed first for caching
COPY pom.xml .
COPY src ./src

# Build the project (skip tests to speed up)
RUN mvn -B -DskipTests package

# ---------- STAGE 2: runtime ----------
FROM eclipse-temurin:21-jdk-alpine
WORKDIR /app

# Copy the jar built in the builder stage
COPY --from=builder /app/target/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["sh", "-c", "java -Dserver.port=$PORT -jar app.jar"]



