-- MySQL dump 10.13  Distrib 8.0.28, for Win64 (x86_64)
--
-- Host: localhost    Database: webshop
-- ------------------------------------------------------
-- Server version	8.0.28

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
-- Table structure for table `comment`
--

DROP TABLE IF EXISTS `comment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `comment` (
  `commentID` int NOT NULL AUTO_INCREMENT,
  `comment` varchar(100) DEFAULT NULL,
  `client_clientID` int NOT NULL,
  `date` datetime DEFAULT NULL,
  `product_productID` int NOT NULL,
  `rating` tinyint DEFAULT NULL,
  PRIMARY KEY (`commentID`,`client_clientID`,`product_productID`),
  KEY `fk_comment_silent1_idx` (`client_clientID`),
  KEY `fk_comment_product_idx` (`product_productID`),
  CONSTRAINT `fk_comment_client1` FOREIGN KEY (`client_clientID`) REFERENCES `client` (`clientID`),
  CONSTRAINT `fk_comment_product` FOREIGN KEY (`product_productID`) REFERENCES `product` (`productID`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comment`
--

LOCK TABLES `comment` WRITE;
/*!40000 ALTER TABLE `comment` DISABLE KEYS */;
INSERT INTO `comment` VALUES (2,'sản phẩm tốt\r\n',1,'2022-05-12 10:40:17',5,NULL),(3,'sản phẩm tốt',1,'2022-05-12 10:44:58',5,NULL),(4,'Sản phẩm tốt',1,'2022-05-12 13:00:57',4,NULL),(5,'Sản phẩm tốt xungwss đáng 100Đ',1,'2022-05-12 13:19:54',2,NULL),(6,'Sản phẩm tốt xungwss đáng 100Đ',1,'2022-05-12 13:22:43',2,NULL),(7,'Sản phẩm tốt xungwss đáng 100Đ',1,'2022-05-12 13:22:53',2,NULL),(8,'Sản Phẩm quá tệ ..................................??????',2,'2022-05-12 14:01:44',2,NULL),(9,'Sản Phẩm rất tốt',1,'2022-05-12 18:51:01',6,NULL);
/*!40000 ALTER TABLE `comment` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-06-04 13:22:17
