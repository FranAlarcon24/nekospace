# Despliegue en Render — nekospace

Esta guía explica cómo desplegar `nekospace` en Render de forma equivalente a `demo-spring-main`.

Resumen rápido
- Usar una base de datos PostgreSQL administrada en Render (recomendado).
- Configurar las environment variables en el servicio Web de Render.
- Deploy usando `Dockerfile` (recomendado) o usando Buildpack/Maven.

Pasos detallados

1) Empuja tu rama al repo remoto

- Asegúrate de haber commit/pusheado todos los cambios a la rama que conectarás en Render (ej. `master`).

2) Provisiona la base de datos en Render

- En Render: New → PostgreSQL → crea una base (ej. `nekospace-db`).
- Anota la connection string que Render te muestre (usualmente en formato `postgres://user:pass@host:port/dbname`).

3) Convertir la connection string a JDBC

- Si Render te da `postgres://user:pass@host:port/dbname` conviértela a:

```
jdbc:postgresql://host:port/dbname?sslmode=require
```

Ejemplo (ya convertido):
```
jdbc:postgresql://dpg-d4ho18mr433s73erk0j0-a:5432/nekospace_db?sslmode=require
```

4) Crear el Web Service en Render

- New → Web Service → conecta tu repo y selecciona la rama.
- Selecciona el método de deploy:
  - `Dockerfile` (recomendado): Render construirá la imagen usando el `Dockerfile` del repo.
  - Build (Maven): Build Command: ``./mvnw -DskipTests package`` y Start Command: ``java -jar target/nekospace-0.0.1-SNAPSHOT.jar``

5) Environment variables (Render → Service → Environment)

- Añade estas variables (sustituye los valores por los de tu DB):

```
SPRING_DATASOURCE_URL=jdbc:postgresql://<HOST>:<PORT>/<DBNAME>?sslmode=require
DATABASE_USERNAME=<db_user>
DATABASE_PASSWORD=<db_password>
DB_SSLMODE=require
```

- Nota: puedes también usar `DATABASE_URL`, pero si usas el valor en formato `postgres://...` necesitarás convertirlo a JDBC o añadir el conversor en el código (opcional).

6) Desplegar y verificar

- Haz clic en Deploy. Revisa los logs.
- Líneas clave en los logs que indican éxito:
  - `HikariPool-1 - Start completed.`
  - `Initialized JPA EntityManagerFactory for persistence unit 'default'`
  - `Tomcat started on port ...`
- Accede a `https://<tu-servicio>.onrender.com/doc/swagger-ui.html` para abrir Swagger.

Problemas comunes y soluciones

- UnknownHostException `db`: significa la URL apunta a `db` (nombre de servicio docker). En Render usa el host que te da la DB administrada.
- Driver/dialect incorrecto: asegúrate de que `SPRING_DATASOURCE_URL` coincide con Postgres si usas Postgres; `pom.xml` ya incluye `org.postgresql:postgresql`.
- DB tarda en estar lista: Render reinicia el servicio en caso de fallo; opcionalmente puedes añadir lógica de espera (wait-for) antes de inicializar JPA.

Seguridad

- No commitees credenciales ni el archivo `.env` con secrets. Usa las Environment Variables en la UI (secreto).

Opcionales (yo puedo añadirlos si quieres)

- Añadir un `EnvironmentPostProcessor` para aceptar `DATABASE_URL=postgres://...` y convertirlo automáticamente a `spring.datasource.url`.
- Añadir un pequeño `wait-for-db` que haga retry antes de que JPA intente la inicialización.

Checklist antes del primer deploy

- [ ] Repo push a la rama seleccionada
- [ ] Provisionar Postgres en Render y copiar JDBC
- [ ] Añadir `SPRING_DATASOURCE_URL`, `DATABASE_USERNAME`, `DATABASE_PASSWORD` en Environment
- [ ] Seleccionar Dockerfile o Buildpack en la creación del Web Service
- [ ] Deploy y revisar logs

Contacto

Si el despliegue falla pega aquí los logs de Render (las primeras 200 líneas) y lo reviso.
