FROM eclipse-temurin:21-jdk

WORKDIR /app
COPY . .

RUN sh gradlew clean bootJar -x test

EXPOSE 8080
CMD ["java", "-Dserver.port=8080", "-jar", "build/libs/server-app.jar"]

