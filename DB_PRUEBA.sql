CREATE DATABASE db_prueba;
CREATE TABLE db_prueba.history_city(
  `id` bigint NOT NULL AUTO_INCREMENT,
  `resultado` varchar(1000) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci
