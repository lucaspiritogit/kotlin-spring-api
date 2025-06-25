FROM gradle:8.7.0-jdk17 AS builder
WORKDIR /app
COPY . .
RUN ./gradlew clean build -x test
FROM openjdk:17-slim
COPY --from=builder /app/build/libs/*.jar ./app.jar
EXPOSE 8080
CMD ["java", "-jar", "app.jar"]