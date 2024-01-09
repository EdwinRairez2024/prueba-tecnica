# Prueba técnica del clima

El ejercicio cuenta con realizar el consumo de una APi externa del clima, donde presentara los resultados de la ciudad consultada. Se expone dos servicios del clima. Uno encargado de realizar la consulta a la API externa y el otro el historial de las ciudades consultadas. Adicionalmente se tiene otro servicio el cual se encarga de realizar una multiplicación de matrices en forma concurrente.

## Creación del contenedor
El primer ejercicio es la creación de la base de datos.
En primera instancia se debe tener instalada docker

- Se debe enviar el siguiente comando en una consola (CMD)

docker run -d --name mysql-db -v mysql-db-data:/var/lib/mysql -e MYSQL_ROOT_PASSWORD=edwin2024 mysql:latest

Este comando permite descargar un contenedor de mysql el cual será usado para persistir la información en la base de datos.

## Ingresar a la consola de MySQL del contenedor

En esta sección se debe conectar a docker y crear la base de datos desde la consola: los pasos son los siguientes:
- Ejecutar en la consola el siguiente comando: docker exec -it mysql-db mysql -p
- Debe ingresar la contraseña del usuario root que fue configurado en la creación del contenedor MYSQL_ROOT_PASSWORD 
- Al ingresar a la consola de mysql ejecutar el siguiente comando para crear la base de datos
	create databases db_prueba;
- Una vez creada la base de datos ejecutar la siguiente sentencia sql para crear la tabla donde se almacenara la información de las consultas del clima:
	CREATE TABLE db_prueba.history_city(
	`id` bigint NOT NULL AUTO_INCREMENT,
	`resultado` varchar(1000) NOT NULL,
	PRIMARY KEY (`id`)
	) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
- Al realizar los pasos anteriores, esta listo para lanzar el proyecto
- Tener en cuenta que en el repositorio se encuentra el sql DB_PRUEBA.sql donde estan las sentencias para crear la base de datos y la tabla correspondiente
## Lanzar el proyecto

- Se debe cargar el proyecto en el IDE Spring boot.
- Tener en cuenta que si al momento de la creación del contenedor cambian la información de la contraseña y del --name, se debe modificar el archivo aplication.properties dichos datos segun correspondan
- Se debe realizar el lanzamiento de la aplicación. 

## Endpoint's

Para consultar un clima en la ciudad especifica usamos el siguiente link, eso lo debes realizar por medio de postman http://localhost:8090/api/clima/bogota con metodo get

Ese traera la información correspondiente al clima de bogotá

Para consultar el historial de los datos consultados usar el siguiente endpoint http://localhost:8090/api/clima/history con metodo get

Con este endpoint podra consultar los últimos 10 climas consultados.

Para realizar la multiplicación de matrices se usa el siguiente endpoint http://localhost:8090/api/clima/multiplicar-matrices

Para el correcto funcionamiento se debe usar postman, donde se debe seleccionar metodo post y luego body, seguidamente raw tipo json y para un ejemplo debe colocar la siguiente información:

{
    
    "matriz1":[
        [11,22,43],
        [33,43,40]
    ],

    "matriz2":[
        [55,66],
        [77,88],
        [34,66]
    ]
}

De esta forma podra conseguir realizar la multiplicación de matrices.
