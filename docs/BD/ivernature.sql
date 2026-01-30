CREATE DATABASE  IF NOT EXISTS `ivernnature` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `ivernnature`;
-- MySQL dump 10.13  Distrib 8.0.34, for Win64 (x86_64)
--
-- Host: localhost    Database: ivernnature
-- ------------------------------------------------------
-- Server version	8.0.34

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `contenidoseducativos`
--

DROP TABLE IF EXISTS `contenidoseducativos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `contenidoseducativos` (
  `idcontenidoeducativo` int NOT NULL AUTO_INCREMENT,
  `tipo_archivo` varchar(255) DEFAULT NULL,
  `titulo` varchar(255) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`idcontenidoeducativo`),
  UNIQUE KEY `UK9tnp2lwbqkrh9tawqhhnx0lj4` (`url`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `contenidoseducativos`
--

LOCK TABLES `contenidoseducativos` WRITE;
/*!40000 ALTER TABLE `contenidoseducativos` DISABLE KEYS */;
INSERT INTO `contenidoseducativos` VALUES (1,'application/vnd.openxmlformats-officedocument.wordprocessingml.document','Documento Ganadería','Ejemplo documento Gnaderia.docx'),(2,'application/pdf','Pdf Ganaderia','Ejemplo documento Gnaderia.pdf'),(3,'image/png','Imagen Ganaderia','ganaderia.png'),(4,'application/vnd.openxmlformats-officedocument.wordprocessingml.document','Ganaderia en la Economia Agricola','Ganaderia_en_la_Economia_Agricola.docx'),(5,'image/jpeg','Imagen Garanja','GanaderiaEnseña.jpg'),(6,'application/vnd.openxmlformats-officedocument.wordprocessingml.document','Rol de la Tecnologia en la Agricultura','Rol_de_la_Tecnologia_en_la_Agricultura.docx'),(7,'image/png','Ganaderia Tecnica','GanaderiaEnseña2.png'),(8,'image/png','Tractor Agricola','agricultura.png'),(9,'image/png','Agricultura Tecnologica','agricultura2.png'),(10,'image/png','Campo de Trigo','agricultura3.png'),(11,'application/vnd.openxmlformats-officedocument.wordprocessingml.document','Documento Agricultura','Ejemplo documento Agricultura.docx'),(12,'application/pdf','Documento Agricola','Ejemplo documento Agricultura.pdf'),(13,'image/jpeg','ImgAgric','ImgAgricultura.jpg'),(17,'application/vnd.openxmlformats-officedocument.wordprocessingml.document','pp','NewwEducational_Document_Livestock.docx'),(18,'application/vnd.openxmlformats-officedocument.wordprocessingml.document','prueba word','NewEducational_Document_Agriculture.docx');
/*!40000 ALTER TABLE `contenidoseducativos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `contiene`
--

DROP TABLE IF EXISTS `contiene`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `contiene` (
  `idcontenido_educativo` int DEFAULT NULL,
  `idcontiene` int NOT NULL AUTO_INCREMENT,
  `idcurso` int DEFAULT NULL,
  PRIMARY KEY (`idcontiene`),
  UNIQUE KEY `UKi05bop4mcjp91mc30on4ogbp2` (`idcurso`,`idcontenido_educativo`),
  KEY `FKjes4bueq2492sy321pe05oyjl` (`idcontenido_educativo`),
  CONSTRAINT `FKjes4bueq2492sy321pe05oyjl` FOREIGN KEY (`idcontenido_educativo`) REFERENCES `contenidoseducativos` (`idcontenidoeducativo`),
  CONSTRAINT `FKm4rcmwky1d9califn63ppwhxd` FOREIGN KEY (`idcurso`) REFERENCES `cursos` (`idcurso`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `contiene`
--

LOCK TABLES `contiene` WRITE;
/*!40000 ALTER TABLE `contiene` DISABLE KEYS */;
/*!40000 ALTER TABLE `contiene` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `curso_contenido`
--

DROP TABLE IF EXISTS `curso_contenido`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `curso_contenido` (
  `id_contenido_educativo` int NOT NULL,
  `id_curso` int NOT NULL,
  PRIMARY KEY (`id_contenido_educativo`,`id_curso`),
  KEY `FKlu59q6m9ywcjvsett7cgfrnxj` (`id_curso`),
  CONSTRAINT `FK95m58car82ircxile4rfoi6of` FOREIGN KEY (`id_contenido_educativo`) REFERENCES `contenidoseducativos` (`idcontenidoeducativo`),
  CONSTRAINT `FKlu59q6m9ywcjvsett7cgfrnxj` FOREIGN KEY (`id_curso`) REFERENCES `cursos` (`idcurso`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `curso_contenido`
--

LOCK TABLES `curso_contenido` WRITE;
/*!40000 ALTER TABLE `curso_contenido` DISABLE KEYS */;
INSERT INTO `curso_contenido` VALUES (1,1),(2,1),(3,1),(4,1),(5,1),(6,1),(7,1),(8,2),(9,2),(10,2),(11,2),(12,2);
/*!40000 ALTER TABLE `curso_contenido` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cursos`
--

DROP TABLE IF EXISTS `cursos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cursos` (
  `fecha_fin` date DEFAULT NULL,
  `fecha_inicio` date DEFAULT NULL,
  `idcurso` int NOT NULL AUTO_INCREMENT,
  `imagen_url` varchar(255) DEFAULT NULL,
  `nombre_grupo` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`idcurso`),
  UNIQUE KEY `UKnhn1u0vj58c57mfua876kabq4` (`nombre_grupo`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cursos`
--

LOCK TABLES `cursos` WRITE;
/*!40000 ALTER TABLE `cursos` DISABLE KEYS */;
INSERT INTO `cursos` VALUES (NULL,NULL,1,'Ganadería2.png','Ganaderíaa'),(NULL,NULL,2,'agricultura2.jpg','Agricultura');
/*!40000 ALTER TABLE `cursos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `eventos`
--

DROP TABLE IF EXISTS `eventos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `eventos` (
  `fecha` date DEFAULT NULL,
  `idevento` int NOT NULL AUTO_INCREMENT,
  `idpropietario` int DEFAULT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`idevento`),
  UNIQUE KEY `UK5w5ahn43udel0vgqf4gisc124` (`nombre`),
  KEY `FKajkrdni680xbsiu02bscow3gs` (`idpropietario`),
  CONSTRAINT `FKajkrdni680xbsiu02bscow3gs` FOREIGN KEY (`idpropietario`) REFERENCES `usuarios` (`idusuario`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `eventos`
--

LOCK TABLES `eventos` WRITE;
/*!40000 ALTER TABLE `eventos` DISABLE KEYS */;
/*!40000 ALTER TABLE `eventos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `participa`
--

DROP TABLE IF EXISTS `participa`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `participa` (
  `idcurso` int DEFAULT NULL,
  `idparticipa` int NOT NULL AUTO_INCREMENT,
  `idusuario` int DEFAULT NULL,
  PRIMARY KEY (`idparticipa`),
  UNIQUE KEY `UKehtatbiyeun6i5gxnn0jxtqkm` (`idusuario`,`idcurso`),
  KEY `FK6ouy0r5dydt4chd9nr7x9sqyw` (`idcurso`),
  CONSTRAINT `FK6ouy0r5dydt4chd9nr7x9sqyw` FOREIGN KEY (`idcurso`) REFERENCES `cursos` (`idcurso`),
  CONSTRAINT `FK9tl2p796e24at0y4a8a9n7f2l` FOREIGN KEY (`idusuario`) REFERENCES `usuarios` (`idusuario`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `participa`
--

LOCK TABLES `participa` WRITE;
/*!40000 ALTER TABLE `participa` DISABLE KEYS */;
/*!40000 ALTER TABLE `participa` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `respuestas`
--

DROP TABLE IF EXISTS `respuestas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `respuestas` (
  `idrespuesta` int NOT NULL AUTO_INCREMENT,
  `idtema` int DEFAULT NULL,
  `idusuario` int DEFAULT NULL,
  `texto` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`idrespuesta`),
  UNIQUE KEY `UKk2ti1jqyig0n2plg0j2j41uo8` (`idtema`),
  KEY `FKn3sw2b8ong4fwg3v88sqbquo4` (`idusuario`),
  CONSTRAINT `FKg1y0ikfuku4c80i65ogl26ovk` FOREIGN KEY (`idtema`) REFERENCES `temas` (`idtema`),
  CONSTRAINT `FKn3sw2b8ong4fwg3v88sqbquo4` FOREIGN KEY (`idusuario`) REFERENCES `usuarios` (`idusuario`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `respuestas`
--

LOCK TABLES `respuestas` WRITE;
/*!40000 ALTER TABLE `respuestas` DISABLE KEYS */;
/*!40000 ALTER TABLE `respuestas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `roles`
--

DROP TABLE IF EXISTS `roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `roles` (
  `idrol` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`idrol`),
  UNIQUE KEY `UKldv0v52e0udsh2h1rs0r0gw1n` (`nombre`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `roles`
--

LOCK TABLES `roles` WRITE;
/*!40000 ALTER TABLE `roles` DISABLE KEYS */;
/*!40000 ALTER TABLE `roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `temas`
--

DROP TABLE IF EXISTS `temas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `temas` (
  `idtema` int NOT NULL AUTO_INCREMENT,
  `idusuario` int DEFAULT NULL,
  `titulo` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`idtema`),
  UNIQUE KEY `UKfh41poih30ufcufdp2512ap9a` (`titulo`),
  KEY `FKirf0n3g65fyshigy94wy3rn0n` (`idusuario`),
  CONSTRAINT `FKirf0n3g65fyshigy94wy3rn0n` FOREIGN KEY (`idusuario`) REFERENCES `usuarios` (`idusuario`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `temas`
--

LOCK TABLES `temas` WRITE;
/*!40000 ALTER TABLE `temas` DISABLE KEYS */;
/*!40000 ALTER TABLE `temas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuarios`
--

DROP TABLE IF EXISTS `usuarios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuarios` (
  `idusuario` int NOT NULL AUTO_INCREMENT,
  `avatar` varchar(255) DEFAULT NULL,
  `correo_electronico` varchar(255) DEFAULT NULL,
  `nombre_usuario` varchar(255) DEFAULT NULL,
  `nombreyapellidos` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`idusuario`),
  UNIQUE KEY `UKof5vabgukahdwmgxk4kjrbu98` (`nombre_usuario`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuarios`
--

LOCK TABLES `usuarios` WRITE;
/*!40000 ALTER TABLE `usuarios` DISABLE KEYS */;
INSERT INTO `usuarios` VALUES (1,'/images/default/avatar.jpg','admin@correo.com','admin','Administrador','$2a$10$9iXeXEuArTdsfQCMRGFgh.tTufOjjo7aGvw0OGYcROlo24DiFmJHa'),(2,'/images/perfil/vegetta2.jpg','samuel@correo.com','Samu','Samuel de Luque','$2a$10$NzKclTjdgH90QKxtcPnxcOgztssX.x8q7fzgKEBqQoLbRv1W6PEHG'),(3,'/images/perfil/hugo_silva.jpg','hugo@correo.com','Hugo21','Hugo Silva','$2a$10$d1jbBt41D168HcyaRhJBoeZLXaW6cs6uR0Qukzn5rNZ.6PDkbh/cq'),(4,'/images/perfil/cote.jpg','cote@correo.com','Cote34','José Ángel Valdés Díaz','$2a$10$Bb95h3UzXocyiinrxTn8geuFZXeiypFhVPQVJ/QGGyvwaizP/HSam'),(5,'/images/perfil/Alonso.jpg','fernando@correo.com','Nano33','Fernando Alonso Díaz','$2a$10$ZfVWwYX/FP0QMt7mwwSm0O/wbM7gqbJ1fWBPLRff2KezuVo9HMRoe');
/*!40000 ALTER TABLE `usuarios` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2026-01-30 18:19:25
