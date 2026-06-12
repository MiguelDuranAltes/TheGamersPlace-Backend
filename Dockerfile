# Multi-stage Dockerfile para TheGamersPlace-Backend
# Build stage: usa Temurin JDK17 y el Gradle Wrapper (más fiable en mirrors donde no existe la imagen gradle)
FROM eclipse-temurin:17-jdk-jammy AS builder
WORKDIR /home/gradle/project

# Copiamos los archivos del proyecto al contenedor
COPY . .

# Instalar herramientas necesarias para que el Gradle Wrapper pueda descargar y descomprimir Gradle
RUN apt-get update \
	&& apt-get install -y --no-install-recommends unzip bash \
	&& rm -rf /var/lib/apt/lists/*

# Hacer el wrapper ejecutable y construir el jar ejecutable de Spring Boot
# -x test para omitir tests en el build de la imagen (quita si quieres ejecutar tests)
RUN chmod +x ./gradlew && ./gradlew bootJar --no-daemon -x test

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

