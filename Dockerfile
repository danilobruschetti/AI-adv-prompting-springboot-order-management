FROM openjdk:18

WORKDIR /app

COPY target/*.jar app.jar

EXPOSE 8280

CMD ["java", "-jar", "app.jar"]
