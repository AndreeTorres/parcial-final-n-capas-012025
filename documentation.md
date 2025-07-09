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

Esto hará lo siguiente:

- Compilar la imagen de la app con el `Dockerfile`
- Levantar PostgreSQL con los datos definidos
- Exponer la app en `http://localhost:8080`

---

##  Configuración de base de datos

La app se conecta automáticamente al contenedor PostgreSQL usando estas variables (definidas en `docker-compose.yml`):

```yaml
SPRING_DATASOURCE_URL=jdbc:postgresql://db:5432/supportdb
SPRING_DATASOURCE_USERNAME=postgres
SPRING_DATASOURCE_PASSWORD=admin1234
```

El archivo `application.yml` está configurado para leer estas variables de entorno.

---

## Notas finales

- Asegurate de que ningún otro proceso esté usando los puertos `8080` o `5432`.
- Los datos de PostgreSQL se almacenan en un volumen llamado `pgdata`.
- Si necesitás reiniciar con base de datos limpia: `docker-compose down -v`.

---
