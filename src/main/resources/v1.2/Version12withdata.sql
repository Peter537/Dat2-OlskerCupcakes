CREATE DATABASE  IF NOT EXISTS `olskercupcakes` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `olskercupcakes`;
-- MySQL dump 10.13  Distrib 8.0.31, for Win64 (x86_64)
--
-- Host: localhost    Database: olskercupcakes
-- ------------------------------------------------------
-- Server version	8.0.31

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
-- Table structure for table `cupcake`
--

DROP TABLE IF EXISTS `cupcake`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cupcake` (
  `cupcake_id` int NOT NULL AUTO_INCREMENT,
  `fk_cupcaketop_id` int NOT NULL,
  `fk_cupcakebottom_id` int NOT NULL,
  `fk_order_id` int NOT NULL,
  PRIMARY KEY (`cupcake_id`),
  KEY `fk_cupcake_cupcaketop1_idx` (`fk_cupcaketop_id`),
  KEY `fk_cupcake_cupcakebottom1_idx` (`fk_cupcakebottom_id`),
  KEY `fk_cupcake_ordre1_idx` (`fk_order_id`),
  CONSTRAINT `fk_cupcake_cupcakebottom1` FOREIGN KEY (`fk_cupcakebottom_id`) REFERENCES `cupcakebottom` (`cupcakebottom_id`),
  CONSTRAINT `fk_cupcake_cupcaketop1` FOREIGN KEY (`fk_cupcaketop_id`) REFERENCES `cupcaketop` (`cupcaketop_id`),
  CONSTRAINT `fk_cupcake_ordre1` FOREIGN KEY (`fk_order_id`) REFERENCES `order` (`order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cupcake`
--

LOCK TABLES `cupcake` WRITE;
/*!40000 ALTER TABLE `cupcake` DISABLE KEYS */;
/*!40000 ALTER TABLE `cupcake` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cupcakebottom`
--

DROP TABLE IF EXISTS `cupcakebottom`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cupcakebottom` (
  `cupcakebottom_id` int NOT NULL AUTO_INCREMENT,
  `bottom` varchar(45) NOT NULL,
  `price` float NOT NULL,
  PRIMARY KEY (`cupcakebottom_id`),
  UNIQUE KEY `cupcakebottom_id_UNIQUE` (`cupcakebottom_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cupcakebottom`
--

LOCK TABLES `cupcakebottom` WRITE;
/*!40000 ALTER TABLE `cupcakebottom` DISABLE KEYS */;
INSERT INTO `cupcakebottom` VALUES (1,'Chocolate',5),(2,'Vanilla',5),(3,'Nutmeg',5),(4,'Pistacio',6),(5,'Almond',7);
/*!40000 ALTER TABLE `cupcakebottom` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cupcaketop`
--

DROP TABLE IF EXISTS `cupcaketop`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cupcaketop` (
  `cupcaketop_id` int NOT NULL AUTO_INCREMENT,
  `topping` varchar(45) NOT NULL,
  `price` float NOT NULL,
  PRIMARY KEY (`cupcaketop_id`),
  UNIQUE KEY `cupcaketop_id_UNIQUE` (`cupcaketop_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cupcaketop`
--

LOCK TABLES `cupcaketop` WRITE;
/*!40000 ALTER TABLE `cupcaketop` DISABLE KEYS */;
INSERT INTO `cupcaketop` VALUES (1,'Chocolate',5),(2,'Blueberry',5),(3,'Rasberry',5),(4,'Crispy',6),(5,'Strawberry',6),(6,'Rum/Raisin',7),(7,'Orange',8),(8,'Lemon',8),(9,'Blue cheese',9);
/*!40000 ALTER TABLE `cupcaketop` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order`
--

DROP TABLE IF EXISTS `order`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `order` (
  `order_id` int NOT NULL AUTO_INCREMENT,
  `fk_user_email` varchar(90) NOT NULL,
  `readytime` varchar(90) DEFAULT NULL,
  `totalprice` float DEFAULT NULL,
  `cupcakecount` int DEFAULT NULL,
  `status` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`order_id`),
  UNIQUE KEY `ordre_id_UNIQUE` (`order_id`),
  KEY `fk_ordre_user_idx` (`fk_user_email`),
  CONSTRAINT `fk_ordre_user` FOREIGN KEY (`fk_user_email`) REFERENCES `user` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order`
--

LOCK TABLES `order` WRITE;
/*!40000 ALTER TABLE `order` DISABLE KEYS */;
/*!40000 ALTER TABLE `order` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `email` varchar(90) NOT NULL,
  `password` varchar(45) NOT NULL,
  `role` varchar(45) NOT NULL,
  `balance` float DEFAULT NULL,
  PRIMARY KEY (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES ('admin','1234','ADMIN',15);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-03-27  8:49:05
