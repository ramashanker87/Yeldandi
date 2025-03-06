FROM eclipse-temurin:21-jdk
WORKDIR /app
COPY target/springboot-hello-world-0.0.1-SNAPSHOT.jar /app/springboot-hello-world.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app/springboot-hello-world.jar"]
