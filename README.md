# Proyecto "Pizzeberus" para HUniversity Microservicios
Alexis Valentin Richter

# Guía de uso
1. ```git clone https://gitlabdes.hiberus.com/varichter/introduccion-microservicios.git```.
2. ``mvn clean install`` para instalar las dependencias del proyecto.
3. ``docker-compose up --build`` para levantar Rabbitmq, Zipkin, Postgres (Usuario & Pizzas), Grafana y Prometheus.
4. Arrancar desde el IDE el servicio Config Server.
5. Arrancar desde el IDE el servicio Eureka Server.
6. Arrancar desde el IDE el servicio Gateway Server.
7. Arrancar desde el IDE los servicios Usuarios y Prendas.
8. ``docker compose down -v`` para eliminar los containers
9. ``docker compose up -d`` para levantar los containers si ya se hizo la build.

# Documentación
- Config server: http://localhost:8888/<nombre_servicio>/default
- Eureka server: http://localhost:8761/
- Gateway: http://localhost:9000/
- Usuarios: http://localhost:8080/swagger-ui.html
- Pizza-Command: http://localhost:8081/swagger-ui.html
- Pizza-Query: http://localhost:8082/swagger-ui.html
- Zipking: http://localhost:9411/zipkin/
- Grafana: `http://localhost:3000/login`
- Prometheus: `http://localhost:9090/targets`

##### Credenciales
- Postgres_usuarios:
  - POSTGRES_USER: postgres
  - POSTGRES_PASSWORD: secret_usuarios
  - POSTGRES_DB: usuarios
- Postgres_pizzas:
  - POSTGRES_USER: postgres
  - POSTGRES_PASSWORD: secret_pizza
  - POSTGRES_DB: pizzas

- Grafana:
    - Usuario: `admin`
    - Contrasenia: `admin`

### Para configurar obserbailidad con Prometheus & Grafana
1. En el archivo `prometheus/prometheus.yml` y en `grafana/grafana.yml` debera poner la ip local de 
   su docker. Ej: `172.17.0.1`
    - Si estas usando linux ejecutar: `ip addr show docker0`
    - Si estas usando windows ejecutar: `docker network inspect bridge`
2. En el archivo `prometheus/prometheus.yml` debera poner la ip de su maquina local. Ej: `192.168.1.33`
    - Si estas usando linux ejecutar: `hostname -I`
    - Si estas usando windows ejecutar: `ipconfig`
3. Configurar Grafana:
    - Ingresar a `http://localhost:3000`
    - Usuario: `admin`
    - Contrasenia: `admin`
    - Skip "Update your password"
    - Dashboards -> New -> Import -> dashboard URL or ID "`19004`" -> Load -> Select Prometheus data source -> "`Prometheus`" -> Import

# Enunciado
Pizzeberus es una Pizzeria que necesita desarrollar un back-end para su negocio.  
Necesitan crear dos microservicios para la gestion de las pizzas, uno con permisos de escritura en la base de datos, 
y otro microservicio que tenga permisos de lectura (patron CQRS).
Por otro lado, necesitan un tercer microservicio para gestionar los usuarios.  
El objetivo de este proyecto es aplicar los conocimientos adquiridos en el curso, se utilizaran las siguiente 
herramientas de Sprin Cloud:
- Cloud config
- Spring Cloud Eureka
- Spring Cloud Gateway
- Spring Cloud Feing Client
- Spring Cloud Circuit Breaker
- Spring Cloud Sleuth

PIZZEBERUS

----- Pizzas (CQRS) -----
- Id
- Nombre

Escritura
- RF1: crear pizzas
- RF2: modificar pizzas

Lectura
- RF3: listar pizzas existentes
- RF4: recuperar pizza por id

----- Usuarios -----
- Id
- Nombre
- Pizzas favoritas

--- RF ---

- RF5: crear usuario
- RF6: modificar usuario
- RF7: borrar usuario
- RF8: listar usuarios
- RF9: marcar pizzas favoritas
- RF10: desmarcar pizzas favoritas

--- RNF ---

- RNF1: Desarrollados en Spring Boot
- RNF2: Documentación de sus APIs en Swagger
- RNF3: Gestionados con Maven
- RNF4: Configuración del sistema centralizada con Spring Cloud
- RNF5: Registro y descubrimiento de servicios con Eureka
- RNF6: Ser accesibles mediante Spring Cloud Gateway
- RNF7: Comunicación entre servicios mediante Spring Cloud Feing
- RNF8: Servicios con circuit brakers usando Spring Cloud Circuit Breaker
- RNF9: Seguimiento y trazabilidad con Spring Cloud Sleuth y Zipking
- RNF10: Servicios conectados a PostgreSQL

Link: https://gitlabdes.hiberus.com/varichter/introduccion-microservicios