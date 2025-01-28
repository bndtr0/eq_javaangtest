# eq_javaangtest
Equ hiring test  (28/01/2025)
=============================

1. Para la aplicación se instalaron:
* Java SE 17.0.12
* Spring Boot 3.4.2
* Maven
* Libraries: JDBC API,  PostgreSQL driver, Jersey, Spring Web (with embedded Apache Tomcat server).
* Install Eclipse IDE for Java EE developers.
* PostgreSQL 17 for Windows.
* npm
* Node.JS 22.13.1
* Angular 17


2. En el proyecto van: el script de base de datos (en SQL) para PostgreSQL 17, el proyecto de Spring Boot (usando el servidor de Apache Tomcat incrustado) con algunos Web Services de tipo REST y el proyecto de Angular 17 con vistas simples. También se agrega un archivo de Excel con datos de prueba.


3. PostgreSQL debe ser configurado con los siguientes datos:
- URL: jdbc:postgresql://localhost:5432/postgres
- Username:postgres
- Password:sup59832_


4. Por ahora en esta versión sólo funcionan:
- La aplicación de Angular 17 (en http://localhost:4200).
- La vista de Angular /home (en http://localhost:4200/home).
- La vista de Angular /login (en http://localhost:4200/login, pero falta conectarla al servicio Web).

- La aplicación de Spring Boot (en http://localhost:8080)
- El servicio Web REST para listar usuarios (GET http://localhost:8080/user).
- El servicio Web REST para enviar registros desde un archivo Excel (POST http://localhost:8080/user).


5. Continuaré trabajando en la seguridad (login, JWT y Docker) durante el día.

Saludos y muchas gracias.