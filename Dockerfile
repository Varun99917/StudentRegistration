
FROM eclipse-temurin:25-jre

WORKDIR /app

COPY target/student-registration-1.0.jar app.jar

EXPOSE 8080

CMD ["java", "-cp", "app.jar", "com.student.App"]
