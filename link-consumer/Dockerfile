FROM openjdk:17-oracle
EXPOSE 4000
WORKDIR /opt/app
COPY build/libs/*.jar app.jar
CMD ["java", "-jar", "app.jar"]