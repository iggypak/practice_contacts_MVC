FROM openjdk:17-jdk-slim
WORKDIR /app
COPY target/practice_contact_MVC.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]
EXPOSE 8080
