-- MySQL dump 10.13  Distrib 8.0.36, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: estimator
-- ------------------------------------------------------
-- Server version	8.0.36

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
-- Table structure for table `apartment`
--

DROP TABLE IF EXISTS `apartment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `apartment` (
  `id` int NOT NULL AUTO_INCREMENT,
  `address` varchar(255) NOT NULL,
  `area` int NOT NULL,
  `start_date` date NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=72 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `apartment`
--

LOCK TABLES `apartment` WRITE;
/*!40000 ALTER TABLE `apartment` DISABLE KEYS */;
INSERT INTO `apartment` VALUES (1,'г. Сочи, ул. Гончарова, д. 5б, кв. 79',25,'2024-10-11'),(2,'г. Сочи, ул. Вишневая, д. 18/8, кв. 190',31,'2024-10-10'),(3,'г. Сочи, ул. Дмитриевой, д. 5, кв. 15',99,'2024-10-12'),(54,'г. Сочи, ул. Мира, д. 5, кв. 13',50,'2024-10-31'),(60,'г. Сочи, ул. Донская, д. 13, кв. 98',34,'2024-11-03');
/*!40000 ALTER TABLE `apartment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `apartment_category_quantity`
--

DROP TABLE IF EXISTS `apartment_category_quantity`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `apartment_category_quantity` (
  `apartment_id` int NOT NULL,
  `category_id` int NOT NULL,
  `quantity` int NOT NULL,
  PRIMARY KEY (`apartment_id`,`category_id`),
  KEY `fk_category_object_category_quantity_idx` (`category_id`),
  CONSTRAINT `fk_apartment_apartment_category_quantity` FOREIGN KEY (`apartment_id`) REFERENCES `apartment` (`id`) ON DELETE CASCADE,
  CONSTRAINT `fk_category_apartment_category_quantity` FOREIGN KEY (`category_id`) REFERENCES `category` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `apartment_category_quantity`
--

LOCK TABLES `apartment_category_quantity` WRITE;
/*!40000 ALTER TABLE `apartment_category_quantity` DISABLE KEYS */;
INSERT INTO `apartment_category_quantity` VALUES (1,1,26),(1,2,26),(1,3,45),(1,4,78),(1,5,10),(1,7,10),(1,8,3),(2,1,30),(2,2,30),(2,3,55),(2,4,50),(2,5,2),(2,6,3),(2,7,15),(3,1,95),(3,2,95),(3,3,150),(3,5,3),(54,1,50),(54,3,68),(54,6,6),(54,7,14),(60,1,34),(60,2,3),(60,4,41),(60,6,3),(60,7,20);
/*!40000 ALTER TABLE `apartment_category_quantity` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `apartment_cost`
--

DROP TABLE IF EXISTS `apartment_cost`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `apartment_cost` (
  `apartment_id` int NOT NULL,
  `cost_id` int NOT NULL,
  PRIMARY KEY (`cost_id`,`apartment_id`),
  KEY `fk_object_object_cost_idx` (`apartment_id`),
  CONSTRAINT `fk_apartment_apartment_cost` FOREIGN KEY (`apartment_id`) REFERENCES `apartment` (`id`),
  CONSTRAINT `fk_cost_apartment_cost` FOREIGN KEY (`cost_id`) REFERENCES `cost` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `apartment_cost`
--

LOCK TABLES `apartment_cost` WRITE;
/*!40000 ALTER TABLE `apartment_cost` DISABLE KEYS */;
INSERT INTO `apartment_cost` VALUES (1,1),(1,4),(1,76),(1,92),(1,102),(1,121),(1,122),(1,127),(2,102),(2,121),(2,122),(3,76),(3,92),(3,122),(3,128),(3,134),(54,76),(54,121),(54,128),(54,129),(54,132),(54,133),(60,4),(60,92),(60,121),(60,122),(60,129),(60,131),(60,133);
/*!40000 ALTER TABLE `apartment_cost` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `category`
--

DROP TABLE IF EXISTS `category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `category` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `unit` varchar(10) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category`
--

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
INSERT INTO `category` VALUES (1,'FLOORS','кв.м'),(2,'CEILINGS','кв.м'),(3,'WALLS','кв.м'),(4,'BASEBOARDS','м'),(5,'DOORS','шт'),(6,'HEATING','шт'),(7,'ELECTRICS','шт'),(8,'PLUMBING','шт');
/*!40000 ALTER TABLE `category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cost`
--

DROP TABLE IF EXISTS `cost`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cost` (
  `id` int NOT NULL AUTO_INCREMENT,
  `work_title` varchar(255) NOT NULL,
  `cost_value` decimal(10,2) NOT NULL,
  `category_id` int NOT NULL,
  `cost_date` datetime NOT NULL,
  `is_actual` tinyint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_category_cost_idx` (`category_id`)
) ENGINE=InnoDB AUTO_INCREMENT=138 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cost`
--

LOCK TABLES `cost` WRITE;
/*!40000 ALTER TABLE `cost` DISABLE KEYS */;
INSERT INTO `cost` VALUES (1,'Штукатурка стен',850.00,3,'2024-10-29 11:43:39',0),(2,'Шпатлевка стен',500.00,3,'2024-10-01 00:00:00',0),(3,'Окраска стен',600.00,3,'2024-10-01 00:00:00',0),(4,'Шпатлевка потолков',700.00,2,'2024-10-01 00:00:00',1),(5,'Укладка плитки на пол',1500.00,1,'2024-10-01 00:00:00',0),(58,'Укладка ламината',700.00,1,'2024-10-25 11:07:32',0),(63,'Монтаж плинтуса',700.00,4,'2024-10-25 12:09:14',0),(64,'Монтаж плинтуса',800.00,4,'2024-10-28 12:03:10',0),(66,'Штукатурка стен',800.00,3,'2024-10-28 16:10:28',0),(68,'Штукатурка стен',880.00,3,'2024-10-29 12:16:52',0),(69,'Штукатурка стен',900.00,3,'2024-10-29 12:34:49',0),(70,'Штукатурка стен',1000.00,3,'2024-10-31 12:55:44',0),(72,'Монтаж плинтуса',900.00,4,'2024-10-31 14:13:23',0),(74,'Окраска стен',700.00,3,'2024-10-31 15:05:51',0),(75,'Окраска стен',800.00,3,'2024-10-31 15:06:21',0),(76,'Окраска стен',850.00,3,'2024-10-31 15:07:51',1),(78,'Монтаж плинтуса',950.00,4,'2024-10-31 18:22:45',0),(79,'Монтаж плинтуса',1000.00,4,'2024-10-31 19:23:22',0),(80,'Монтаж плинтуса',700.00,4,'2024-10-31 20:18:12',0),(81,'Монтаж плинтуса',800.00,4,'2024-10-31 22:39:23',0),(82,'Монтаж плинтуса',850.00,4,'2024-10-31 22:42:19',0),(83,'Укладка ламината',800.00,1,'2024-11-02 11:05:29',0),(84,'Укладка ламината',850.00,1,'2024-11-02 11:05:37',0),(91,'Укладка ламината',800.00,1,'2024-11-02 12:01:02',0),(92,'Окраска потолков',800.00,2,'2024-11-03 14:17:20',1),(93,'Укладка ламината',500.00,1,'2024-11-03 14:17:57',0),(94,'Демонтаж ламината',1.00,1,'2024-11-03 14:18:33',0),(95,'Монтаж розеток',500.00,7,'2024-11-03 14:24:14',0),(96,'Монтаж розеток',700.00,7,'2024-11-03 14:25:52',0),(97,'Монтаж розеток',500.00,7,'2024-11-03 14:27:21',0),(98,'Монтаж розеток',700.00,7,'2024-11-03 14:27:34',0),(99,'Монтаж розеток',500.00,7,'2024-11-03 14:28:32',0),(100,'Монтаж розеток',700.00,7,'2024-11-03 14:28:43',0),(101,'Монтаж розеток',500.00,7,'2024-11-04 21:07:56',0),(102,'Шпатлевка стен',600.00,3,'2024-11-05 14:45:27',0),(103,'Укладка плитки на пол',1700.00,1,'2024-11-05 15:08:43',0),(104,'Монтаж розеток',600.00,7,'2024-11-05 17:34:51',0),(105,'Монтаж плинтуса',500.00,4,'2024-11-06 09:49:26',0),(106,'Монтаж розеток',700.00,7,'2024-11-06 14:55:45',0),(107,'Монтаж розеток',500.00,7,'2024-11-06 22:17:04',0),(108,'Монтаж розеток',500.00,7,'2024-11-06 22:18:04',0),(109,'Монтаж плинтуса',600.00,4,'2024-11-07 10:26:41',0),(110,'Монтаж плинтуса',500.00,4,'2024-11-07 10:27:23',0),(111,'Монтаж розеток',500.00,7,'2024-11-07 10:28:12',0),(113,'Монтаж розеток',500.00,7,'2024-11-08 14:37:33',0),(114,'Монтаж плинтуса',600.00,4,'2024-11-08 14:40:40',0),(115,'Монтаж розеток',600.00,7,'2024-11-08 14:41:36',0),(116,'Монтаж плинтуса',500.00,4,'2024-11-08 14:45:01',0),(117,'Монтаж плинтуса',600.00,4,'2024-11-08 14:52:08',0),(118,'Монтаж плинтуса',500.00,1,'2024-11-11 12:53:09',0),(119,'Монтаж розеток',500.00,1,'2024-11-11 12:59:31',0),(120,'Монтаж розеток',500.00,7,'2024-11-11 13:05:46',0),(121,'Укладка ламината',500.00,1,'2024-11-11 13:05:56',1),(122,'Укладка плитки на пол',1800.00,1,'2024-11-11 13:06:02',1),(123,'Монтаж розеток',500.00,7,'2024-11-12 14:58:34',0),(124,'Монтаж радиаторов',2500.00,6,'2024-11-12 14:58:48',0),(125,'Монтаж радиаторов',3000.00,6,'2024-11-12 15:01:49',0),(126,'Монтаж розеток',550.00,7,'2024-11-12 15:57:09',0),(127,'Монтаж розеток',500.00,7,'2024-11-12 15:57:42',0),(128,'Шпатлевка стен',650.00,3,'2024-11-12 17:47:32',1),(129,'Монтаж радиаторов',2500.00,6,'2024-11-13 09:53:40',1),(130,'Демонтаж ламината',300.00,1,'2024-11-16 23:45:26',0),(131,'Монтаж плинтуса',500.00,4,'2024-11-16 23:46:27',1),(132,'Демонтаж ламината',200.00,1,'2024-11-16 23:47:38',1),(133,'Монтаж розеток',550.00,7,'2024-11-16 23:55:16',1),(134,'Монтаж дверей',4500.00,5,'2024-11-26 14:14:26',0),(135,'Установка сантехнических приборов',2500.00,8,'2024-11-26 14:15:28',1),(136,'Монтаж дверей',4000.00,5,'2024-11-26 14:19:42',1),(137,'Штукатурка стен',600.00,3,'2024-11-26 14:23:42',1);
/*!40000 ALTER TABLE `cost` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-11-26 17:06:11
