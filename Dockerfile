# Multi-stage Dockerfile para TheGamersPlace-Backend
# Build stage: usa una imagen con Gradle + JDK 17
FROM gradle:8.4.1-jdk17 AS builder
WORKDIR /home/gradle/project

# Copiamos los archivos del proyecto al contenedor
COPY --chown=gradle:gradle . .

# Construimos el jar ejecutable de Spring Boot
# -x test para omitir tests en el build de la imagen (quita si quieres ejecutar tests)
RUN gradle bootJar --no-daemon -x test

# Runtime stage: imagen más pequeña con JRE 17
FROM eclipse-temurin:17-jre-jammy
WORKDIR /app

# Copiamos el JAR construido desde la etapa anterior
COPY --from=builder /home/gradle/project/build/libs/*.jar app.jar

# Variables opcionales
ENV JAVA_OPTS=""

# Exponer puerto (documentativo); Render proporciona $PORT automáticamente
EXPOSE 8080

# CMD que respeta la variable de entorno PORT usada por Render
CMD ["sh", "-c", "java $JAVA_OPTS -Dserver.port=$PORT -jar /app/app.jar"]

