FROM openjdk:8-jdk-alpine
WORKDIR /app
COPY target/utp-test-backend-1.0.5.jar app.jar
EXPOSE 8080
CMD ["java", "-jar", "app.jar"]