# Edutech

Plataforma educativa construida con **arquitectura de microservicios**. El proyecto está compuesto por **17 microservicios independientes** desarrollados con Spring Boot, cada uno con su propia lógica, controladores y configuración, que en conjunto cubren la gestión de cursos, contenido educativo, inscripciones, evaluaciones, progreso de estudiantes y datos de apoyo (empresas, proveedores y ubicaciones).

## Tecnologías

- Java
- Spring Boot (Spring Web, Spring Data JPA, Spring HATEOAS)
- Maven (cada microservicio incluye su propio `mvnw`)
- MySQL
- Visual Studio Code

## Microservicios

| # | Microservicio | Carpeta |
|---|---------------|---------|
| 1 | Contenido educativo | `ContenidoEducativo` |
| 2 | Curso | `Curso` |
| 3 | Evaluación | `Evaluacion` |
| 4 | Incidencia | `Incidencia` |
| 5 | Inscripción | `Inscripcion` |
| 6 | Plataforma educativa | `PlataformaEducativa` |
| 7 | Progreso del estudiante | `ProgresoEstudiante` |
| 8 | Registro de rendimiento | `RegistroRendimiento` |
| 9 | Reporte | `Reporte` |
| 10 | Usuario | `Usuario` |
| 11 | Ciudad | `ciudad` |
| 12 | Comuna | `comuna` |
| 13 | Dirección | `direccion` |
| 14 | Empresa | `empresa` |
| 15 | Oficina | `oficina` |
| 16 | País (con HATEOAS) | `pais_hateoas` |
| 17 | Proveedores | `proveedores` |

## Estructura del proyecto

```
Edutech/
├── ContenidoEducativo/
├── Curso/
├── Evaluacion/
├── ...
└── proveedores/
    ├── src/main/java/...
    ├── src/main/resources/application.properties
    └── pom.xml
```

## Requisitos previos

- JDK 17 o superior
- Maven (o usar el `mvnw` incluido en cada microservicio)
- MySQL en ejecución (por defecto en `localhost:3306`)

## Configuración

Por seguridad, los archivos `application.properties` **no se suben al repositorio**. Para ejecutar un microservicio:

1. Entra a la carpeta del microservicio, por ejemplo `ciudad/`.
2. Crea el archivo `src/main/resources/application.properties` (si existe una copia `application.properties.example`, úsala como base).
3. Completa tus propios datos de conexión:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/NOMBRE_BASE_DE_DATOS?useSSL=false&serverTimezone=UTC
spring.datasource.username=TU_USUARIO
spring.datasource.password=TU_PASSWORD
spring.jpa.hibernate.ddl-auto=update
server.port=8080
```

> Cada microservicio debe usar un **puerto distinto** (`server.port`) para poder ejecutarlos al mismo tiempo.

## Ejecución

Desde la carpeta de cada microservicio:

```bash
./mvnw spring-boot:run
```

En Windows (PowerShell):

```powershell
.\mvnw.cmd spring-boot:run
```

## Pruebas de los endpoints

Los endpoints se pueden probar con archivos `.rest` (extensión REST Client de VS Code), Postman o Thunder Client.

## Autor

**Diego Muñoz Hormazábal**
Proyecto académico — Duoc UC
