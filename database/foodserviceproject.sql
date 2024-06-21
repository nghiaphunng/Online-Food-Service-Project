CREATE DATABASE  IF NOT EXISTS `onlinefoodserviceproject` /*!40100 DEFAULT CHARACTER SET utf8mb3 COLLATE utf8mb3_swedish_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `onlinefoodserviceproject`;
-- MySQL dump 10.13  Distrib 8.0.36, for Win64 (x86_64)
--
-- Host: localhost    Database: onlinefoodserviceproject
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
-- Table structure for table `cart`
--

DROP TABLE IF EXISTS `cart`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cart` (
  `id` int NOT NULL AUTO_INCREMENT,
  `users_id` int NOT NULL,
  `restaurant_id` int NOT NULL,
  `status` int NOT NULL DEFAULT '0',
  `created_at` datetime DEFAULT CURRENT_TIMESTAMP,
  `updated_at` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `fk_users_id_cart_idx` (`users_id`),
  KEY `restaurant_id_cart_idx` (`restaurant_id`),
  CONSTRAINT `fk_users_id_cart` FOREIGN KEY (`users_id`) REFERENCES `user` (`userId`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `restaurant_id_cart` FOREIGN KEY (`restaurant_id`) REFERENCES `restaurant` (`RestaurantId`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=57 DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_swedish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cart`
--

LOCK TABLES `cart` WRITE;
/*!40000 ALTER TABLE `cart` DISABLE KEYS */;
/*!40000 ALTER TABLE `cart` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cart_item`
--

DROP TABLE IF EXISTS `cart_item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cart_item` (
  `id` int NOT NULL AUTO_INCREMENT,
  `cart_id` int NOT NULL,
  `item_id` int NOT NULL,
  `quantity` int NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`),
  UNIQUE KEY `UC_Cart_Item` (`cart_id`,`item_id`),
  KEY `cart_item_ibfk_2` (`item_id`),
  CONSTRAINT `cart_item_ibfk_1` FOREIGN KEY (`cart_id`) REFERENCES `cart` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `cart_item_ibfk_2` FOREIGN KEY (`item_id`) REFERENCES `item` (`ItemId`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=102 DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_swedish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cart_item`
--

LOCK TABLES `cart_item` WRITE;
/*!40000 ALTER TABLE `cart_item` DISABLE KEYS */;
/*!40000 ALTER TABLE `cart_item` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `error`
--

DROP TABLE IF EXISTS `error`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `error` (
  `errorId` int NOT NULL AUTO_INCREMENT,
  `errorType` varchar(100) COLLATE utf8mb3_swedish_ci DEFAULT NULL,
  `errorDesc` text COLLATE utf8mb3_swedish_ci,
  `errorOccurrenceTime` datetime DEFAULT NULL,
  `ProcessingStatus` int DEFAULT '0',
  PRIMARY KEY (`errorId`)
) ENGINE=InnoDB AUTO_INCREMENT=90 DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_swedish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `error`
--

LOCK TABLES `error` WRITE;
/*!40000 ALTER TABLE `error` DISABLE KEYS */;
INSERT INTO `error` VALUES (1,'messageErrorReturnListItemDefaultHome','Column \'item.ItemId\' not found.','2024-06-14 16:02:40',2),(2,'messageErrorReturnSearchItemByLowerBoundPrice','You have an error in your SQL syntax; check the manual that corresponds to your MySQL server version for the right syntax to use near \'< 30000) and (i.ItemType = 3) order by i.SalesCount desc, i.ItemId desc\' at line 1','2024-06-15 00:38:45',2),(3,'messageErrorReturnSearchItemByUpperBoundPrice','You have an error in your SQL syntax; check the manual that corresponds to your MySQL server version for the right syntax to use near \'> 100000) and (i.ItemType = 3) order by i.SalesCount desc, i.ItemId desc\' at line 1','2024-06-15 00:38:47',2),(4,'messageErrorReturnSearchItemByUpperBoundPrice','You have an error in your SQL syntax; check the manual that corresponds to your MySQL server version for the right syntax to use near \'> 100000) and (i.ItemType = 3) order by i.SalesCount desc, i.ItemId desc\' at line 1','2024-06-15 00:38:48',1),(5,'messageErrorReturnSearchItemByLowerBoundPrice','You have an error in your SQL syntax; check the manual that corresponds to your MySQL server version for the right syntax to use near \'< 30000) and (i.ItemType = 3) order by i.SalesCount desc, i.ItemId desc\' at line 1','2024-06-15 00:38:54',2),(6,'messageErrorReturnSearchItemByLowerBoundPrice','You have an error in your SQL syntax; check the manual that corresponds to your MySQL server version for the right syntax to use near \'< 30000) and (i.ItemType = 3) order by i.SalesCount desc, i.ItemId desc\' at line 1','2024-06-15 00:38:54',2),(7,'messageErrorReturnSearchItemByLowerBoundPrice','You have an error in your SQL syntax; check the manual that corresponds to your MySQL server version for the right syntax to use near \'< 30000) and (i.ItemType = 3) order by i.SalesCount desc, i.ItemId desc\' at line 1','2024-06-15 00:38:54',2),(8,'messageErrorReturnSearchItemByLowerBoundPrice','You have an error in your SQL syntax; check the manual that corresponds to your MySQL server version for the right syntax to use near \'< 30000) and (i.ItemType = 1 or i.ItemType = 3) order by i.SalesCount desc, i.It\' at line 1','2024-06-15 00:38:57',2),(9,'messageErrorReturnSearchItemByLowerBoundPrice','You have an error in your SQL syntax; check the manual that corresponds to your MySQL server version for the right syntax to use near \'< 30000) and (i.ItemType = 1 or i.ItemType = 3) order by i.SalesCount desc, i.It\' at line 1','2024-06-15 00:38:57',2),(10,'messageErrorReturnSearchItemByLowerBoundPrice','You have an error in your SQL syntax; check the manual that corresponds to your MySQL server version for the right syntax to use near \'< 30000) and (i.ItemType = 1 or i.ItemType = 3) order by i.SalesCount desc, i.It\' at line 1','2024-06-15 00:39:51',2),(11,'messageErrorReturnSearchItemByLowerBoundPrice','You have an error in your SQL syntax; check the manual that corresponds to your MySQL server version for the right syntax to use near \'< 30000) and (i.ItemType = 2 or i.ItemType = 3) order by i.SalesCount desc, i.It\' at line 1','2024-06-15 00:39:55',2),(12,'messageErrorReturnSearchItemByLowerBoundPrice','You have an error in your SQL syntax; check the manual that corresponds to your MySQL server version for the right syntax to use near \'< 30000) and (i.ItemType = 1 or i.ItemType = 3) order by i.SalesCount desc, i.It\' at line 1','2024-06-15 00:40:15',2),(13,'messageErrorReturnSearchItemByLowerBoundPrice','You have an error in your SQL syntax; check the manual that corresponds to your MySQL server version for the right syntax to use near \'< 30000) and (i.ItemType = 2 or i.ItemType = 3) order by i.SalesCount desc, i.It\' at line 1','2024-06-15 00:40:17',2),(14,'messageErrorReturnSearchItemByLowerBoundPrice','You have an error in your SQL syntax; check the manual that corresponds to your MySQL server version for the right syntax to use near \'< 30000) and (i.ItemType = 2 or i.ItemType = 3) order by i.SalesCount desc, i.It\' at line 1','2024-06-15 00:40:17',2),(15,'messageErrorReturnSearchItemByLowerBoundPrice','You have an error in your SQL syntax; check the manual that corresponds to your MySQL server version for the right syntax to use near \'< 30000) and (i.ItemType = 2 or i.ItemType = 3) order by i.SalesCount desc, i.It\' at line 1','2024-06-15 00:40:17',2),(16,'messageErrorReturnSearchItemByLowerBoundPrice','You have an error in your SQL syntax; check the manual that corresponds to your MySQL server version for the right syntax to use near \'< 30000) and (i.ItemType = 2 or i.ItemType = 3) order by i.SalesCount desc, i.It\' at line 1','2024-06-15 00:40:17',2),(17,'messageErrorReturnSearchItemByLowerBoundPrice','You have an error in your SQL syntax; check the manual that corresponds to your MySQL server version for the right syntax to use near \'< 30000) and (i.ItemType = 2 or i.ItemType = 3) order by i.SalesCount desc, i.It\' at line 1','2024-06-15 00:40:18',2),(18,'messageErrorReturnSearchItemByLowerBoundPrice','You have an error in your SQL syntax; check the manual that corresponds to your MySQL server version for the right syntax to use near \'< 30000) and (i.ItemType = 2 or i.ItemType = 3) order by i.SalesCount desc, i.It\' at line 1','2024-06-15 00:40:18',2),(19,'messageErrorReturnSearchItemByLowerBoundPrice','You have an error in your SQL syntax; check the manual that corresponds to your MySQL server version for the right syntax to use near \'< 30000) and (i.ItemType = 2 or i.ItemType = 3) order by i.SalesCount desc, i.It\' at line 1','2024-06-15 00:40:19',2),(20,'messageErrorReturnSearchItemByLowerBoundPrice','You have an error in your SQL syntax; check the manual that corresponds to your MySQL server version for the right syntax to use near \'< 30000) and (i.ItemType = 2 or i.ItemType = 3) order by i.SalesCount desc, i.It\' at line 1','2024-06-15 00:40:19',2),(21,'messageErrorReturnSearchItemByLowerBoundPrice','You have an error in your SQL syntax; check the manual that corresponds to your MySQL server version for the right syntax to use near \'< 30000) and (i.ItemType = 3) order by i.SalesCount desc, i.ItemId desc\' at line 1','2024-06-15 00:40:20',2),(22,'messageErrorReturnSearchItemByLowerBoundPrice','You have an error in your SQL syntax; check the manual that corresponds to your MySQL server version for the right syntax to use near \'< 30000) and (i.ItemType = 3) order by i.SalesCount desc, i.ItemId desc\' at line 1','2024-06-15 00:40:20',2),(23,'messageErrorReturnSearchItemByLowerBoundPrice','You have an error in your SQL syntax; check the manual that corresponds to your MySQL server version for the right syntax to use near \'< 30000) and (i.ItemType = 3) order by i.SalesCount desc, i.ItemId desc\' at line 1','2024-06-15 00:40:20',2),(24,'messageErrorReturnSearchItemByLowerBoundPrice','You have an error in your SQL syntax; check the manual that corresponds to your MySQL server version for the right syntax to use near \'< 30000) and (i.ItemType = 3) order by i.SalesCount desc, i.ItemId desc\' at line 1','2024-06-15 00:40:21',2),(25,'messageErrorReturnSearchItemByLowerBoundPrice','You have an error in your SQL syntax; check the manual that corresponds to your MySQL server version for the right syntax to use near \'< 30000) and (i.ItemType = 2 or i.ItemType = 3) order by i.SalesCount desc, i.It\' at line 1','2024-06-15 00:40:30',2),(26,'messageErrorReturnSearchItemByLowerBoundPrice','You have an error in your SQL syntax; check the manual that corresponds to your MySQL server version for the right syntax to use near \'< 30000) and (i.ItemType = 2 or i.ItemType = 3) order by i.SalesCount desc, i.It\' at line 1','2024-06-15 00:40:31',2),(27,'messageErrorReturnSearchItemByLowerBoundPrice','You have an error in your SQL syntax; check the manual that corresponds to your MySQL server version for the right syntax to use near \'< 30000) and (i.ItemType = 2 or i.ItemType = 3) order by i.SalesCount desc, i.It\' at line 1','2024-06-15 00:40:32',2),(28,'messageErrorReturnSearchItemByLowerBoundPrice','You have an error in your SQL syntax; check the manual that corresponds to your MySQL server version for the right syntax to use near \'< 30000) and (i.ItemType = 2 or i.ItemType = 3) order by i.SalesCount desc, i.It\' at line 1','2024-06-15 00:40:32',2),(30,'messageErrorInsertNotiToUser','Unknown column \'duynghia135z\' in \'where clause\'','2024-06-15 23:21:44',1),(31,'messageErrorInsertNotiToUser','Unknown column \'duynghia135z\' in \'where clause\'','2024-06-15 23:22:48',2),(32,'messageErrorDisplayRestaurantList','Unknown table \'r\'','2024-06-16 01:29:01',2),(33,'Error(AddToCart)','You have an error in your SQL syntax; check the manual that corresponds to your MySQL server version for the right syntax to use near \'{add_to_cart(6,35,3)}\' at line 1','2024-06-16 21:51:27',2),(34,'Error(AddToCart)','You have an error in your SQL syntax; check the manual that corresponds to your MySQL server version for the right syntax to use near \'{add_to_cart(6,35,3)}\' at line 1','2024-06-16 21:52:43',1),(35,'Error(AddToCart)','You have an error in your SQL syntax; check the manual that corresponds to your MySQL server version for the right syntax to use near \'{add_to_cart(6,25,2)}\' at line 1','2024-06-16 21:56:01',2),(36,'Error(AddToCart)','You have an error in your SQL syntax; check the manual that corresponds to your MySQL server version for the right syntax to use near \'{add_to_cart(6,35,3)}\' at line 1','2024-06-16 21:56:04',2),(37,'Error(AddToCart)','You have an error in your SQL syntax; check the manual that corresponds to your MySQL server version for the right syntax to use near \'{add_to_cart(6,16,1)}\' at line 1','2024-06-16 21:56:08',2),(38,'Error(UpdateCartStatus0123)','Duplicate entry \'6-1-1\' for key \'cart.UC_Cart\'','2024-06-17 03:35:26',2),(39,'Error(UpdateCartStatus0123)','Duplicate entry \'6-1-1\' for key \'cart.UC_Cart\'','2024-06-17 03:35:45',1),(40,'Error(UpdateCartStatus0123)','Duplicate entry \'6-3-1\' for key \'cart.UC_Cart\'','2024-06-17 03:44:30',1),(41,'Error(UpdateCartStatus0123)','Duplicate entry \'6-3-1\' for key \'cart.UC_Cart\'','2024-06-17 03:50:33',1),(42,'Error(UpdateCartStatus0123)','Duplicate entry \'6-3-1\' for key \'cart.UC_Cart\'','2024-06-17 03:50:41',2),(43,'Error(UpdateCartStatus0123)','Duplicate entry \'6-3-1\' for key \'cart.UC_Cart\'','2024-06-17 03:50:45',1),(44,'messageErrorReturnGetRestaurant','Unknown table \'r\'','2024-06-17 22:01:15',1),(45,'messageErrorReturnGetRestaurant','Unknown table \'r\'','2024-06-17 22:01:32',2),(46,'messageErrorReturnGetRestaurant','Unknown table \'r\'','2024-06-17 22:01:58',1),(47,'messageErrorReturnGetRestaurant','Unknown table \'r\'','2024-06-17 22:03:22',1),(48,'messageErrorReturnGetRestaurant','Unknown table \'r\'','2024-06-17 22:03:23',1),(49,'messageErrorReturnGetRestaurant','Unknown table \'r\'','2024-06-17 22:03:34',1),(50,'messageErrorReturnGetRestaurant','Unknown table \'r\'','2024-06-17 22:04:18',1),(51,'messageErrorReturnGetRestaurant','Unknown table \'r\'','2024-06-17 22:04:22',1),(52,'messageErrorReturnGetRestaurant','Unknown table \'r\'','2024-06-17 22:05:07',1),(53,'messageErrorReturnGetRestaurant','Unknown table \'r\'','2024-06-17 22:07:02',1),(54,'messageErrorReturnGetRestaurant','Unknown table \'r\'','2024-06-17 22:07:50',1),(55,'messageErrorReturnGetRestaurant','Unknown table \'r\'','2024-06-17 22:08:09',1),(56,'messageErrorReturnGetRestaurant','Unknown table \'r\'','2024-06-17 22:09:27',1),(57,'messageErrorReturnGetRestaurant','Unknown table \'r\'','2024-06-17 22:09:30',1),(58,'Error(insertInfoReservation','Data truncation: Incorrect date value: \'\' for column \'ReservationDate\' at row 1','2024-06-18 00:38:46',1),(59,'messageErrorReturnListItemRestaurantId','You have an error in your SQL syntax; check the manual that corresponds to your MySQL server version for the right syntax to use near \'where res.CustomerId = 6\' at line 1','2024-06-18 00:39:09',1),(60,'messageErrorReturnListItemRestaurantId','You have an error in your SQL syntax; check the manual that corresponds to your MySQL server version for the right syntax to use near \'where res.CustomerId = 6\' at line 1','2024-06-18 00:39:21',2),(61,'messageErrorReturnListItemRestaurantId','You have an error in your SQL syntax; check the manual that corresponds to your MySQL server version for the right syntax to use near \'where res.CustomerId = 6\' at line 1','2024-06-18 00:39:44',2),(62,'messageErrorReturnListItemRestaurantId','You have an error in your SQL syntax; check the manual that corresponds to your MySQL server version for the right syntax to use near \'where res.CustomerId = 6\' at line 1','2024-06-18 00:42:52',2),(63,'messageErrorReturnListItemRestaurantId','You have an error in your SQL syntax; check the manual that corresponds to your MySQL server version for the right syntax to use near \'where res.CustomerId = 6\' at line 1','2024-06-18 00:43:01',2),(64,'messageErrorReturnListItemRestaurantId','You have an error in your SQL syntax; check the manual that corresponds to your MySQL server version for the right syntax to use near \'where res.CustomerId = 6\' at line 1','2024-06-18 00:43:02',2),(65,'messageErrorReturnListItemRestaurantId','You have an error in your SQL syntax; check the manual that corresponds to your MySQL server version for the right syntax to use near \'where res.CustomerId = 3\' at line 1','2024-06-18 00:43:13',2),(66,'messageErrorReturnListItemRestaurantId','You have an error in your SQL syntax; check the manual that corresponds to your MySQL server version for the right syntax to use near \'where res.CustomerId = 6\' at line 1','2024-06-18 00:44:50',2),(67,'Error update status(UpdateReservationStatus)','Unknown column \'eReservationId\' in \'where clause\'','2024-06-18 00:48:42',2),(68,'Error update status(UpdateReservationStatus)','Unknown column \'eReservationId\' in \'where clause\'','2024-06-18 00:48:47',2),(69,'messageErrorReturnListItemRestaurantId','You have an error in your SQL syntax; check the manual that corresponds to your MySQL server version for the right syntax to use near \'where res.CustomerId = 6\' at line 1','2024-06-18 00:49:51',0),(70,'Error(insertInfoReservation','Data truncation: Incorrect date value: \'\' for column \'ReservationDate\' at row 1','2024-06-18 00:51:59',0),(71,'Error(insertInfoReservation','Data truncation: Incorrect date value: \'275760-09-12\' for column \'ReservationDate\' at row 1','2024-06-18 00:53:05',0),(72,'messageInsertUser','Unknown column \'img\' in \'field list\'','2024-06-18 10:09:12',0),(73,'Error(UpdateInfoRestaurant)','No value specified for parameter 4','2024-06-18 10:48:29',0),(74,'Error(UpdateInfoRestaurant)','You have an error in your SQL syntax; check the manual that corresponds to your MySQL server version for the right syntax to use near \')\' at line 1','2024-06-18 10:49:40',0),(75,'messageErrorReturnListItemRestaurantId','Column \'r.Location\' not found.','2024-06-18 11:55:36',0),(76,'messageErrorReturnListItemRestaurantId','Column \'r.Location\' not found.','2024-06-18 11:56:49',0),(77,'messageErrorReturnListItemRestaurantId','Column \'r.Location\' not found.','2024-06-18 11:57:21',0),(78,'messageErrorUpdateAvatarDB','Data truncation: Data too long for column \'urlAvatar\' at row 1','2024-06-19 22:39:51',0),(79,'messageErrorReturnListItemRestaurantId','You have an error in your SQL syntax; check the manual that corresponds to your MySQL server version for the right syntax to use near \'by res.Status asc\' at line 1','2024-06-19 23:32:05',0),(80,'messageErrorReturnListItemRestaurantId','You have an error in your SQL syntax; check the manual that corresponds to your MySQL server version for the right syntax to use near \'by res.Status asc\' at line 1','2024-06-19 23:34:03',0),(81,'messageErrorUpdateImgItem','the request doesn\'t contain a multipart/form-data or multipart/mixed stream, content type header is null','2024-06-20 16:59:40',0),(82,'messageErrorUpdateImgItem','the request doesn\'t contain a multipart/form-data or multipart/mixed stream, content type header is null','2024-06-20 17:02:51',0),(83,'messageErrorUpdateAvatarDBItem','Data truncation: Data too long for column \'Image\' at row 1','2024-06-20 17:08:45',0),(84,'messageErrorUpdateAvatarDBItem','Data truncation: Data too long for column \'Image\' at row 1','2024-06-20 17:09:09',0),(85,'messageErrorUpdateAvatarDBItem','Data truncation: Data too long for column \'Image\' at row 1','2024-06-20 17:31:47',0),(86,'messageErrorUpdateAvatarDBItem','Data truncation: Data too long for column \'Image\' at row 1','2024-06-20 17:32:28',0),(87,'messageErrorUpdateAvatarDBItem','Data truncation: Data too long for column \'Image\' at row 1','2024-06-20 17:32:37',0),(88,'messageErrorUpdateAvatarDBItem','Data truncation: Data too long for column \'Image\' at row 1','2024-06-20 17:32:41',0),(89,'Error(insertInfoReservation','Data truncation: Incorrect date value: \'\' for column \'ReservationDate\' at row 1','2024-06-21 00:31:44',0);
/*!40000 ALTER TABLE `error` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `item`
--

DROP TABLE IF EXISTS `item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `item` (
  `ItemId` int NOT NULL AUTO_INCREMENT,
  `RestaurantId` int DEFAULT NULL,
  `Name` varchar(255) COLLATE utf8mb3_swedish_ci DEFAULT NULL,
  `Description` text COLLATE utf8mb3_swedish_ci,
  `Price` int DEFAULT NULL,
  `ItemType` int DEFAULT NULL,
  `SearchSuggestion` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_swedish_ci DEFAULT NULL,
  `Image` varchar(200) COLLATE utf8mb3_swedish_ci DEFAULT NULL,
  `NumberOfItem` int DEFAULT NULL,
  `SalesCount` int DEFAULT '0',
  PRIMARY KEY (`ItemId`),
  KEY `fk_restaurant_item_idx` (`RestaurantId`),
  CONSTRAINT `fk_restaurant_item` FOREIGN KEY (`RestaurantId`) REFERENCES `restaurant` (`RestaurantId`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=53 DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_swedish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `item`
--

LOCK TABLES `item` WRITE;
/*!40000 ALTER TABLE `item` DISABLE KEYS */;
INSERT INTO `item` VALUES (1,1,'Trà Sữa Phô Mai Tươi','Trà Sữa Phô Mai Tươi',28000,2,'trà sữa','vn-11134517-7r98o-lqzp9kaupltw97.jpeg',20,0),(2,1,'Trà Sữa Tocotoco Ba Anh Em','Trà Sữa Tocotoco Ba Anh Em',30000,2,'trà sữa','vn-11134517-7r98o-lr2y57n6xfvd37.jpeg',20,0),(3,1,'Trà Sữa Toco Trân Châu Hoàng Gia','Trà Sữa Toco Trân Châu Hoàng Gia',26600,2,'trà sữa','vn-11134517-7r98o-lr2y585s4ty16f.jpeg',20,0),(4,1,'Trà Sữa Toco Trân Châu Sợi','Trà Sữa Toco Trân Châu Sợi',27300,2,'trà sữa','vn-11134517-7r98o-lr2y5cijrf7o00.jpeg',20,0),(5,1,'Trà Sữa Kim Cương Đen Okinawa','Trà Sữa Kim Cương Đen Okinawa',26600,2,'trà sữa','vn-11134517-7r98o-lqxi0mv7pspl13.jpeg',20,0),(6,1,'Ô Long Thái Cực','Ô Long Thái Cực',27300,2,'trà sữa','vn-11134517-7r98o-lr5oqtw76q4ka5.jpeg',20,0),(7,1,'Trà Dứa Thạch Konjac','Trà Dứa Thạch Konjac',28000,2,'trà sữa','vn-11134517-7r98o-lqzoz4ss7nwkb7.jpeg',20,0),(8,1,'Trà Sữa Xanh Vị Nhài','Trà Sữa Xanh Vị Nhài',23100,2,'trà sữa','vn-11134517-7r98o-lr2y58prblj876.jpeg',20,1),(9,1,'Trà Sữa Bạc Hà','Trà Sữa Bạc Hà',23800,2,'trà sữa','vn-11134517-7r98o-lr2xlfxke6kkef.jpeg',20,2),(10,1,'Trà Sữa Socola','Trà Sữa Socola',24500,2,'trà sữa','vn-11134517-7r98o-lr2xnfbzy2l5de.jpeg',20,3),(11,1,'Trà Sữa Matcha','Trà Sữa Matcha',24500,2,'trà sữa','vn-11134517-7r98o-lqxhxneixqxlaa.jpeg',20,4),(12,1,'Trà Sữa Ô Long','Trà Sữa Ô Long',23800,2,'trà sữa','vn-11134517-7r98o-lr5oqseddhuhab.jpeg',20,5),(13,1,'Trà Sữa Dâu Tây','Trà Sữa Dâu Tây',23800,2,'trà sữa','vn-11134517-7r98o-lr5oquzll2h0e5.jpeg',20,6),(14,1,'Trà Sữa','Trà Sữa',16500,2,'trà sữa','vn-11134517-7r98o-lr5ohk69jnwkfb.jpeg',20,7),(15,1,'Jelly Milk Coffee (size S only)','Jelly Milk Coffee (size S only)',15400,2,'trà sữa','vn-11134517-7r98o-lr6p72t7tpcp09.jpeg',20,8),(16,1,'Grass Jelly Milk Coffee (size S only)','Grass Jelly Milk Coffee (size S only)',15400,2,'trà sữa','vn-11134517-7r98o-lr6p6y3opsih6e.jpeg',20,9),(17,1,'Combo 2 Trà Sữa Ba Anh Em','Combo 2 Trà Sữa Ba Anh Em',39000,2,'trà sữa','vn-11134517-7r98o-lqxu96jkylcp9d.jpeg',20,1),(18,1,'Combo 2 Trà Sữa Matcha','Combo 2 Trà Sữa Matcha',49000,2,'trà sữa','vn-11134517-7r98o-lr5u9p66g98kf9.jpeg',20,2),(19,1,'Combo 2 Trà Xanh Sữa Vị Nhài','Combo 2 Trà Xanh Sữa Vị Nhài',46200,2,'trà sữa','vn-11134517-7r98o-lr34ww81a6kke8.jpeg',20,3),(20,2,'Special Tiramisu','Đậm đà cafe và rượu, béo ngậy vị kem',150000,1,'bánh Tiramisu','vn-11134517-7r98o-lvtql9jr1gwp98.jpeg',15,4),(21,2,'Tiramisu Ladyfinger Matcha','Matcha Tiramisu cốt matcha lady finger',95000,1,'bánh Tiramisu','vn-11134517-7r98o-lr60oa58zbfd59.jpeg',15,5),(22,2,'Tiramisu Matcha','bánh Tiramisu',95000,1,'bánh Tiramisu','vn-11134517-7r98o-lqzrtxhmp8d5f9.jpeg',15,6),(23,2,'Bánh Bông Lan Trứng Muối Mix Sốt Trứng và sốt Phô Mai','Bánh Bông Lan Trứng Muối Mix Sốt Trứng và sốt Phô Mai',75000,1,'Bánh Bông Lan','vn-11134517-7r98o-lr2s58tse4zof3.jpeg',15,7),(24,2,'Redvelvet','bánh Redvelvet',60000,1,'bánh Redvelvet','vn-11134517-7r98o-lr5ni3ltncm187.jpeg',15,8),(25,2,'Su kem vỏ giòn ( hộp 6 chiếc )','Vị cheese',70000,1,'Su kem vỏ giòn','vn-11134517-7r98o-lqxmzm3cso0pbc.jpeg',15,9),(26,2,'Tiramisu cốt Chiffon','Tiramisu cacao',95000,1,'bánh Tiramisu','vn-11134517-7r98o-lqxbhvb1igexb5.jpeg',15,0),(27,2,'Bánh Phomai Tươi','Bánh Phomai Tươi',70000,1,'bánh phô mai','vn-11134517-7r98o-lqxdy874nvt0f7.jpeg',15,1),(28,2,'Bánh mì phô mai bơ tỏi',NULL,80000,1,'Bánh mì phô mai bơ tỏi','vn-11134517-7r98o-lqxos9v6dryc1d.jpeg',15,2),(29,2,'Bánh earl xoài kem cheese','Bánh earl grey xoài kem cheese',70000,1,'Bánh earl xoài kem cheese','vn-11134517-7r98o-lvodleghw7zt92.jpeg',15,3),(32,3,'Bánh Tráng Nướng',NULL,30000,1,'Bánh Tráng Nướng','vn-11134517-7r98o-lqy1u7j3fgk9bb.jpeg',30,6),(33,3,'Bánh Tráng Trộn',NULL,30000,1,'Bánh Tráng Trộn','vn-11134517-7r98o-lqxt8zjzq66cbe.jpeg',30,7),(34,3,'Bánh Trứng Cút Nướng',NULL,40000,1,'Bánh Trứng Cút Nướng','vn-11134517-7r98o-lqxuej6j24yh98.jpeg',30,8),(35,3,'Nem Chua Rán','5 cái',40000,1,'Nem Chua Rán','vn-11134517-7r98o-lqybwowz8eysd3.jpeg',30,9),(36,3,'Bánh Tráng Nướng Phô Mai',NULL,35000,1,'Bánh Tráng Nướng Phô Mai','vn-11134517-7r98o-lr3wmcd62bh5a5.jpeg',30,0),(37,3,'Nem Chua Rán To','10 cái',80000,1,'Nem Chua Rán To','vn-11134517-7r98o-lqyaz7zaiemxf8.jpeg',30,1),(38,3,'Ngô xào tép',NULL,40000,1,'Ngô xào tép','vn-11134517-7r98o-lv8vj2uh1q7t96.jpeg',30,2),(39,3,'Combo 4 món Tặng 1 Trà Quất','1 Nướng + 1 Trộn + 1 Cuộn + 1 Trứng Cút Nướng + 1 Trà Quất',135000,3,'Combo 4 món Tặng 1 Trà Quất','vn-11134517-7r98o-lr3fulgeh1ok26.jpeg',30,3),(40,3,'Combo Best Seller Tặng 1 đồ uống','Bánh tráng nướng Phô mai + Bánh tráng cuộn + Bánh trứng cút nướng + Nem chua rán nhỏ + 1 cốc trà',150000,3,'Combo Best Seller Tặng 1 đồ uống','vn-11134517-7r98o-lvompr2rumpld9.jpeg',30,4),(41,3,'Trà Quất',NULL,20000,2,'Trà Quất','vn-11134517-7r98o-lqy46vc7bwk983.jpeg',30,5),(42,3,'Trà Đào',NULL,25000,2,'Trà Đào','vn-11134517-7r98o-lqy46ure6cw403.jpeg',30,6),(43,3,'Trà Quất Nha Đam',NULL,25000,2,'Trà Quất Nha Đam','vn-11134517-7r98o-lqy6spntkbkpf8.jpeg',30,7),(44,3,'Trà Chanh',NULL,20000,2,'Trà Chanh','vn-11134517-7r98o-lqy46vuskp8k7e.jpeg',30,8),(45,3,'Sữa Đậu Nành Lá Dứa',NULL,20000,2,'Sữa Đậu Nành Lá Dứa','vn-11134517-7r98o-lr6jzvaianq15d.jpeg',30,9),(46,3,'Trà Chanh Nha Đam',NULL,25000,2,'Trà Chanh Nha Đam','vn-11134517-7r98o-lqy69jithz15d5.jpeg',30,0),(47,3,'Coca-Cola',NULL,15000,2,'Coca-Cola','vn-11134517-7r98o-lqy4ov5xcsk993.jpeg',30,1),(48,3,'7Up',NULL,15000,2,'7Up','vn-11134517-7r98o-lqy50lmb3rfdd5.jpeg',30,2),(49,3,'Khoai tây chiên','siêu ngon, siêu thích',39000,1,'khoai tây chiên','vn-11134517-7r98o-lr4311tq12ft51.jpeg',30,2),(52,3,'Bánh tráng kèm bơ tỏi','Bánh tráng kèm bơ tỏi, ăn siêu đã',32000,1,'Bánh tráng kèm bơ tỏi, siêu ngon','vn-11134517-7r98o-lr6z1f1qm849c6.jpeg',50,0);
/*!40000 ALTER TABLE `item` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `notification`
--

DROP TABLE IF EXISTS `notification`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `notification` (
  `UserNotificationId` int NOT NULL AUTO_INCREMENT,
  `SenderId` int DEFAULT NULL,
  `RecipientId` int DEFAULT NULL,
  `Title` varchar(100) COLLATE utf8mb3_swedish_ci DEFAULT NULL,
  `Content` text COLLATE utf8mb3_swedish_ci,
  `SendingTime` datetime DEFAULT NULL,
  `Status` int DEFAULT '0',
  PRIMARY KEY (`UserNotificationId`),
  KEY `fk_sender_admin_idx` (`SenderId`),
  KEY `fk_sender_user_idx` (`RecipientId`),
  CONSTRAINT `fk_sender_admin` FOREIGN KEY (`SenderId`) REFERENCES `user` (`userId`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_sender_user` FOREIGN KEY (`RecipientId`) REFERENCES `user` (`userId`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=82 DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_swedish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `notification`
--

LOCK TABLES `notification` WRITE;
/*!40000 ALTER TABLE `notification` DISABLE KEYS */;
INSERT INTO `notification` VALUES (71,5,1,'Thông báo này gửi tới tất cả mọi người','Xin chào mọi người','2024-06-21 00:45:45',2),(72,5,2,'Thông báo này gửi tới tất cả mọi người','Xin chào mọi người','2024-06-21 00:45:45',0),(73,5,3,'Thông báo này gửi tới tất cả mọi người','Xin chào mọi người','2024-06-21 00:45:45',0),(75,5,18,'Thông báo này gửi tới tất cả mọi người','Xin chào mọi người','2024-06-21 00:45:45',2),(76,5,19,'Thông báo này gửi tới tất cả mọi người','Xin chào mọi người','2024-06-21 00:45:45',0),(81,5,1,'Xin chào','Bạn có phải chủ nhà hàng 1 không','2024-06-21 01:47:38',2);
/*!40000 ALTER TABLE `notification` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reservation`
--

DROP TABLE IF EXISTS `reservation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `reservation` (
  `ReservationId` int NOT NULL AUTO_INCREMENT,
  `CustomerId` int DEFAULT NULL,
  `RestaurantId` int DEFAULT NULL,
  `ReservationDate` date DEFAULT NULL,
  `NumberOfGuests` int DEFAULT NULL,
  `AdditionalReminder` text COLLATE utf8mb3_swedish_ci,
  `Status` int DEFAULT '0',
  PRIMARY KEY (`ReservationId`),
  KEY `reservation_ibfk_1` (`CustomerId`),
  KEY `reservation_ibfk_2` (`RestaurantId`),
  CONSTRAINT `reservation_ibfk_1` FOREIGN KEY (`CustomerId`) REFERENCES `user` (`userId`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `reservation_ibfk_2` FOREIGN KEY (`RestaurantId`) REFERENCES `restaurant` (`RestaurantId`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_swedish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reservation`
--

LOCK TABLES `reservation` WRITE;
/*!40000 ALTER TABLE `reservation` DISABLE KEYS */;
/*!40000 ALTER TABLE `reservation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `restaurant`
--

DROP TABLE IF EXISTS `restaurant`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `restaurant` (
  `RestaurantId` int NOT NULL AUTO_INCREMENT,
  `userId` int DEFAULT NULL,
  `Name` varchar(255) COLLATE utf8mb3_swedish_ci DEFAULT NULL,
  `Location` varchar(255) COLLATE utf8mb3_swedish_ci DEFAULT NULL,
  `CuisineTypeDESC` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_swedish_ci DEFAULT NULL,
  `img` varchar(100) COLLATE utf8mb3_swedish_ci DEFAULT NULL,
  PRIMARY KEY (`RestaurantId`),
  KEY `fk_userId_restaurant_idx` (`userId`),
  CONSTRAINT `fk_userId_restaurant` FOREIGN KEY (`userId`) REFERENCES `user` (`userId`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_swedish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `restaurant`
--

LOCK TABLES `restaurant` WRITE;
/*!40000 ALTER TABLE `restaurant` DISABLE KEYS */;
INSERT INTO `restaurant` VALUES (1,1,'Trà Sữa Tocotoco - Chùa Láng','97 Chùa Láng, P. Láng Thượng, Đống Đa, Hà Nội','Trà sữa, trà xanh, kem','img/restaurant/vn-11134513-7r98o-lwc8267puswp20@resize_ss640x400!@crop_w640_h400_cT.png'),(2,2,'Sweetc Hut - Tiệm Bánh Online','49 Ngõ 93 Vương Thừa Vũ, P. Khương Trung, Thanh Xuân, Hà Nội','bánh mì, bánh kem','img/restaurant/vn-11134513-7r98o-lsvckbcrosd50c@resize_ss640x400!@crop_w640_h400_cT.jpeg'),(3,3,'Nhím- Bánh Tráng Trộn & Bánh Tráng Nướng Siêu ngon (dã đổi tên)','1 Ngõ 84 Chùa Láng, P. Láng Thượng, Đống Đa, Hà Nội','bánh tráng, cơm hộp','img/restaurant/vn-11134513-7r98o-lsvgoitry5s4ae@resize_ss640x400!@crop_w640_h400_cT.jpeg'),(8,19,'Nhà hàng số 4','Hà Nội',NULL,'img/avatar/img_avatar_1.png');
/*!40000 ALTER TABLE `restaurant` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `userId` int NOT NULL AUTO_INCREMENT,
  `typeOfUser` int DEFAULT NULL,
  `nameUser` varchar(60) COLLATE utf8mb3_swedish_ci DEFAULT NULL,
  `nameLogin` varchar(60) COLLATE utf8mb3_swedish_ci DEFAULT NULL,
  `emailUser` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_swedish_ci DEFAULT NULL,
  `phoneUser` varchar(20) COLLATE utf8mb3_swedish_ci DEFAULT NULL,
  `addressUser` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_swedish_ci DEFAULT NULL,
  `passwordUser` varchar(60) CHARACTER SET utf8mb3 COLLATE utf8mb3_swedish_ci DEFAULT NULL,
  `gender` int DEFAULT NULL,
  `urlAvatar` varchar(60) COLLATE utf8mb3_swedish_ci DEFAULT NULL,
  PRIMARY KEY (`userId`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_swedish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,2,'Nhà hàng 1','nhahang1','nhahang1@gmail.com','123456789','Thành phố Hà Nội','123456',1,'img/avatar/img_avatar_1.png'),(2,2,'Nhà hàng 2','nhahang2','nhahang2@gmail.com','123456789','Thành phố Hà Nội','123456',1,'img/avatar/img_avatar_1.png'),(3,2,'Nhà hàng số 3 (đã đổi tên chủ nhà hàng lần 1)','nhahang3','nhahang3@gmail.com','123456789','Thành phố Hà Nội','123456',1,'img/avatar/vn-11134517-7r98o-lr0f1hgabg7888.jpeg'),(5,3,'Duy Nghĩa','admin123','admin@gmail.com','123456789','Thành phố Hà Nội','123456',1,'img/avatar/img_avatar_1.png'),(18,1,'Phùng Duy A','user2','user2@gmail.com','0123456789','Hà Nội','123456',1,'img/avatar/img_avatar_1.png'),(19,2,'Nhà hàng số 4','nhahang4','nhahang4@gmail.com','0123456789','Hà Nội','123456',1,'img/avatar/img_avatar_1.png');
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

-- Dump completed on 2024-06-21 11:48:40
