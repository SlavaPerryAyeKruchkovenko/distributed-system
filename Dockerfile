FROM openjdk:8-jdk-alpine
RUN mkdir /app
COPY target/your-app.jar /app/app.jar
WORKDIR /app
CMD ["java", "-jar", "app.jar"]