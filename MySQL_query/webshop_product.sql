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
-- Table structure for table `product`
--

DROP TABLE IF EXISTS `product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product` (
  `productID` int NOT NULL AUTO_INCREMENT,
  `product` varchar(100) DEFAULT NULL,
  `priceO` varchar(20) DEFAULT NULL,
  `priceS` varchar(20) DEFAULT NULL,
  `img` varchar(200) DEFAULT NULL,
  `numsold` varchar(20) DEFAULT NULL,
  `shop_shopID` int NOT NULL,
  `category_categoryID` int NOT NULL,
  PRIMARY KEY (`productID`,`shop_shopID`,`category_categoryID`),
  KEY `fk_product_shop1_idx` (`shop_shopID`),
  KEY `fk_product_category_idx` (`category_categoryID`),
  CONSTRAINT `fk_product_category` FOREIGN KEY (`category_categoryID`) REFERENCES `category` (`categoryID`),
  CONSTRAINT `fk_product_shop1` FOREIGN KEY (`shop_shopID`) REFERENCES `shop` (`shopID`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product`
--

LOCK TABLES `product` WRITE;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
INSERT INTO `product` VALUES (1,'Apple MacBook Air (2020)     ','27950000','27950000','https://cf.shopee.vn/file/575e04c0e1d08b5f1b9f624a8d6b1419','1.4 k',1,2),(2,'Apple MacBook Air (2020) ','27950000','25950000','https://cf.shopee.vn/file/575e04c0e1d08b5f1b9f624a8d6b1419','24,4 k',2,2),(4,'Apple Smart Keyboard Folio For iPad Pro ','5999000','4999999','https://cf.shopee.vn/file/fabe5d101de9ff01342f89a2f056aa12',NULL,2,2),(5,'Dép dê lê trắng đen ','499999','299999','https://cf.shopee.vn/file/a1920c6d84340c1f718857488012eb6c',NULL,1,1),(6,'Apple Smart Keyboard Folio For iPad Pro ','6999999','5950000','https://cf.shopee.vn/file/fabe5d101de9ff01342f89a2f056aa12',NULL,1,2),(7,'Airpods2 Chính Hãng Repi ','1250000','950000','https://cf.shopee.vn/file/fb6dde613884de327e986c341d7b163a_tn',NULL,1,2),(8,'iPhone 13 12 11 Pro Max 7 8 6 6s Plus SE 2020 XS M... ','23550000','23550000','https://cf.shopee.vn/file/b7646d2ef4f1f9aab365eabbb57512d6',NULL,1,2),(9,'Apple Smart Keyboard Folio For iPad Pro','5950000','5950000','https://cf.shopee.vn/file/fabe5d101de9ff01342f89a2f056aa12',NULL,1,2),(10,'Note30 Plus nguyên bản 2022 màn hình lớn Video hỗ trợ trò chơi 5G Máy ảnh HD giảm','1999000','1250000','https://cf.shopee.vn/file/5eebc87c08a084fd557083eb4037a9e4_tn',NULL,2,2),(11,'Ốp Điện Thoại Chống Sốc Cho iPhone 12 Pro 12 Pro M... ','120000','120000','https://cf.shopee.vn/file/7a4dc9e7c0d39a14453b7579fe652c8d',NULL,2,16),(12,'Bộ Nồi Inox 5 Món STOCK POT Không Quai Đủ Kích Cỡ Siêu Tiện Lợi Đa Năng','190000','125000','https://cf.shopee.vn/file/9e421710e7fd8ca32d1449622b3bb3e7',NULL,1,3),(13,'Thẻ tag ','275000','255000','https://cf.shopee.vn/file/dbbf3af22dc1bfe6fbbfe8198a003fe0_tn',NULL,2,8),(14,'Mũ Bảo Hiểm Nửa Đầu Andes 139 Kính Âm An Toàn Gọn Nhẹ','299000','299000','https://cf.shopee.vn/file/376556663700ac58d0a464ce5d2a621e',NULL,2,1),(15,'Máy Cạo râu Khô YANDOU 301U - Máy cạo râu yandou','456000','399000','https://cf.shopee.vn/file/00275a88cc81d25f3be6a64596b45c0c',NULL,2,6),(16,'Kính cường lực chống nhìn trộm iphone full màn, đủ size 6/7/8/X/11/12/13/Plus/Max/Pro/Promax','45000','45000','https://cf.shopee.vn/file/31bd8b509e6746b5bc8b4072959f883a',NULL,2,6),(17,'Màn Hình hãng GLOWAY17|19|22|24 inch','2455000','2200000','https://cf.shopee.vn/file/9195d5e4ce67710b2f790e9f06634a12',NULL,2,2),(18,'GIÀY SỤC THỂ THAO ĐẾ CAO SU CAO CẤP','490000','389000','https://cf.shopee.vn/file/6c13bef8a6cf09936bab34a3be15b380',NULL,3,10),(19,'Apple MacBook Air (2020)','27950000','27950000','https://cf.shopee.vn/file/575e04c0e1d08b5f1b9f624a8d6b1419',NULL,1,2),(20,'Apple MacBook Air (2020)','27950000','27950000','https://cf.shopee.vn/file/575e04c0e1d08b5f1b9f624a8d6b1419',NULL,1,2),(21,'Apple MacBook Air (2020)','27950000','	27950000','https://cf.shopee.vn/file/575e04c0e1d08b5f1b9f624a8d6b1419',NULL,1,2),(22,'	Bộ Nồi Inox 5 Món STOCK POT Không Quai Đủ Kích Cỡ ...','5950000','5950000','https://cf.shopee.vn/file/fabe5d101de9ff01342f89a2f056aa12',NULL,1,2);
/*!40000 ALTER TABLE `product` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-06-04 13:22:16
