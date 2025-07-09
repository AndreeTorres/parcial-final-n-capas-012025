# Pasos para levantar la app con Docker

## Paso 1: Compilar el JAR

Primero, asegurate de que `mvnw` tenga permisos:

```bash
chmod +x ./mvnw
```

Luego compila el proyecto para generar el archivo `.jar`:

```bash
./mvnw clean package -DskipTests
```

Esto generará el archivo `.jar` en `target/`.

---

## Paso 2: Levantar la app y la base de datos

Ejecutá el siguiente comando para construir la imagen y levantar los servicios:

```bash
docker-compose up --build
```
---

##  Configuración de base de datos

La app se conecta automáticamente al contenedor PostgreSQL usando estas variables (definidas en `docker-compose.yml`):

```yaml
SPRING_DATASOURCE_URL=jdbc:postgresql://db:5432/supportdb
SPRING_DATASOURCE_USERNAME=postgres
SPRING_DATASOURCE_PASSWORD=admin1234
```
---
