# Buses API
API para gestionar autobuses y marcas en una empresa de transporte.

## Tabla de Contenidos
- [Instalación](#instalación)
- [Configuración](#configuración)
- [Endpoints](#endpoints)
- [Modelo de Datos](#modelo-de-datos)
- [Documentación Swagger](#documentación-swagger)

## Instalación
1. Clona el repositorio:
```bash
   git clone https://github.com/AngelOU20/civa-buses-api.git
```

2. Accede al proyecto:
```bash
   cd busesapi
```

## Requisitos
- Java 17
- Spring Boot 3
- Maven
- MySQL 8 
- IDE recomendado: IntelliJ

## Configuración
Edita application.properties con tu configuración de MySQL:
```bash
  spring.datasource.url=jdbc:mysql://localhost:3306/tu-base-de-datos?useSSL=false
  spring.datasource.username=tu-username
  spring.datasource.password=tu-contraseña
```

## Estructura de la API
### EndPoints de Marcas

- `GET /api/v1/brands`: Obtener todas las marcas.
- `POST /api/v1/brands`: Crear una nueva marca.
- `DELETE /api/v1/brands/{id}`: Eliminar una marca por ID.

### EndPoints de Buses

- `POST /api/v1/bus`: Crear un nuevo autobús.
- `GET /api/v1/bus`: Obtener una lista paginada de autobuses.
- `GET /api/v1/bus/{id}`: Obtener un autobús por su ID.
- `PUT /api/v1/bus/{id}`: Actualizar los detalles de un autobús por ID.
- `DELETE /api/v1/bus/{id}`: Eliminar un autobús por ID.

## Documentación Swagger
Para acceder a la documentación completa de la API, dirígete a [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html) después de iniciar la aplicación.