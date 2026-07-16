# Agenda de Contactos - API Backend

Este es el backend de la aplicación de Agenda de Contactos, desarrollado con **Spring Boot 4.1.0** y **Java 21**.

## Tecnologías utilizadas
*   **Java 21** & **Spring Boot**
*   **Spring Data JPA** (Persistencia)
*   **PostgreSQL** (Base de datos relacional)
*   **Lombok** & **Spring Validation**

## Requisitos previos
*   JDK 21 instalado.
*   Servidor PostgreSQL corriendo localmente.

## Instalación y ejecución
1. Crea una base de datos en PostgreSQL llamada `agenda_db`.
2. Configura tus credenciales de base de datos en `src/main/resources/application.properties`.
3. Ejecuta la aplicación desde tu IDE o mediante terminal:
   ```bash
   ./mvnw spring-boot:run