# TheGamersPlace Backend

Spring Boot 3.1.3 API con MongoDB, GraphQL y JWT authentication.

## Stack

- **Spring Boot 3.1.3**
- **MongoDB** (Atlas)
- **GraphQL** para queries
- **JWT** para autenticación
- **Docker** para deployment en Railway

## Desarrollo Local

```bash
cd TheGamersPlace-Backend
./gradlew bootRun
```

Luego accede a `http://localhost:8080`

## Deployment en Railway

1. Crea un repositorio en GitHub: `TheGamersPlace-Backend`
2. En Railway, crea un nuevo proyecto desde este repositorio
3. Railway detectará automáticamente que es un proyecto Gradle/Spring Boot
4. Configura variables de entorno:
   - `MONGODB_URI` — URI de MongoDB Atlas
   - `MONGODB_DATABASE` — nombre de BD (ej: `the-gamers-place`)
   - `CLIENT_HOST` — URL del frontend en Vercel
   - `JWT_SECRET_KEY` — clave secreta larga
   - `JWT_VALIDITY` — duración del token (ej: `7200`)
   - `GRAPHIQL_ENABLED` — `false` en producción
   - `SPRING_PROFILES_ACTIVE` — `prod`

5. Haz deploy y railway construirá automáticamente

## API Endpoints

- `POST /auth/authenticate` — Login
- `GET /graphql` — GraphQL playground
- `POST /graphql` — GraphQL queries

## Notas

- El Dockerfile construye automáticamente con Gradle
- Railway inyecta el puerto a través de la variable `$PORT`
- CORS está configurado para aceptar solo desde el frontend en `CLIENT_HOST`

