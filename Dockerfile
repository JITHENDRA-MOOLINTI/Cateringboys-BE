# ---------- STAGE 1: build with Maven ----------
FROM maven:3.9.6-eclipse-temurin-17 as builder
WORKDIR /app

# Copy pom and source
COPY pom.xml .
COPY src ./src

# If you have other files (properties, etc) copy them too:
# COPY mvnw .
# COPY .mvn .mvn
# COPY settings.xml .

# Build the project
RUN mvn -B -DskipTests package

# ---------- STAGE 2: runtime ----------
FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app

# Copy the jar built in the builder stage
COPY --from=builder /app/target/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["sh", "-c", "java -Dserver.port=$PORT -jar app.jar"]


