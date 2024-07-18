-- MySQL dump 10.13  Distrib 8.0.38, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: patients
-- ------------------------------------------------------
-- Server version	8.0.38

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
-- Table structure for table `filtered_patient`
--

DROP TABLE IF EXISTS `filtered_patient`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `filtered_patient` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `birth_date` date DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `gender` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `national_id` varchar(255) DEFAULT NULL,
  `notification_preference` varchar(255) DEFAULT NULL,
  `passport_number` varchar(255) DEFAULT NULL,
  `phone_number` varchar(255) DEFAULT NULL,
  `patient_id` bigint DEFAULT NULL,
  `target_criteria` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK28227pjx8mse6xhacmy799nvh` (`patient_id`),
  KEY `FKq1gw9b2v6rr301f2jdjjo92xb` (`target_criteria`),
  CONSTRAINT `FK28227pjx8mse6xhacmy799nvh` FOREIGN KEY (`patient_id`) REFERENCES `patient` (`id`) ON DELETE CASCADE,
  CONSTRAINT `FKq1gw9b2v6rr301f2jdjjo92xb` FOREIGN KEY (`target_criteria`) REFERENCES `target_criteria` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=77 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `filtered_patient`
--

LOCK TABLES `filtered_patient` WRITE;
/*!40000 ALTER TABLE `filtered_patient` DISABLE KEYS */;
INSERT INTO `filtered_patient` VALUES (15,'1966-08-18','richard.harris@example.com','Male','Richard Harris','11223344556','SMS','A11223344','555-1122',12,1),(16,'1966-08-18','richard.harris@example.com','Male','Richard Harris','11223344556','SMS','A11223344','555-1122',12,3),(20,'1983-09-28','ryan.martinez@example.com','Male','Ryan Martinez','55443322110','SMS','E55443322','555-5678',17,3),(43,'1993-07-20','christopher.moore@example.com','Male','Christopher Moore','55667788990','SMS','E55667788','555-7890',33,3),(52,'1965-07-15','john.smith@example.com','Male','John BU333443U','12345678901','SMS','A12345678','555-1234',35,1),(53,'1965-07-15','john.smith@example.com','Male','John BU333443U','12345678901','SMS','A12345678','555-1234',35,3),(71,'1955-06-19','stephenrome@example.com','Male','Stephen Rome','1381923992','Email','A3289191212','',50,1),(72,'1956-02-19','alicecurry@example.com','Female','Alice Curry','1421913992','Email','A1289191334','',51,2),(73,'1965-07-15','john.smith@example.com','Male','John Dav','12345678901','SMS','A12345678','555-1234',19,1),(74,'1965-07-15','john.smith@example.com','Male','John Dav','12345678901','SMS','A12345678','555-1234',19,3),(76,'1988-02-11','christopher.moore@example.com','Male','Christopher Curry','51243748922','SMS','E54627183','525-7810',14,3);
/*!40000 ALTER TABLE `filtered_patient` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `patient`
--

DROP TABLE IF EXISTS `patient`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `patient` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `birth_date` date DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `gender` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `national_id` varchar(255) DEFAULT NULL,
  `notification_preference` varchar(255) DEFAULT NULL,
  `passport_number` varchar(255) DEFAULT NULL,
  `phone_number` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=52 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `patient`
--

LOCK TABLES `patient` WRITE;
/*!40000 ALTER TABLE `patient` DISABLE KEYS */;
INSERT INTO `patient` VALUES (11,'0333-11-05','jennifer.davis@example.com','Female','Jennifer Davis','56789012345','Email','E56789012','555-3456'),(12,'1966-08-18','richard.harris@example.com','Male','Richard Harris','11223344556','SMS','A11223344','555-1122'),(14,'1988-02-11','christopher.moore@example.com','Male','Christopher Curry','51243748922','SMS','E54627183','525-7810'),(15,'1980-05-30','kevin.young@example.com','Male','Kevin Young','77889900112','Email','G77889900','555-2345'),(17,'1983-09-28','ryan.martinez@example.com','Male','Ryan Martinez','55443322110','SMS','E55443322','555-5678'),(18,'1990-05-15','emily.garcia@example.com','Female','Emily Garcia','66554443321','Email','D66554443','555-4567'),(19,'1965-07-15','john.smith@example.com','Male','John Dav','12345678901','SMS','A12345678','555-1234'),(21,'1988-03-05','jason.thompson@example.com','Male','Jason Thompson','99887766554','Email','A99887766','555-1234'),(22,'2010-03-12','velydagi@gmail.com','Male','deneme','dda','Mail','11121313','05537233927'),(32,'1987-07-12','christopher.hoo@example.com','Male','Christopher Hoo','55667788990','Email','E45267788','551-2840'),(33,'1993-07-20','christopher.moore@example.com','Male','Christopher Moore','55667788990','SMS','E55667788','555-7890'),(35,'1965-07-15','john.smith@example.com','Male','John BU333443U','12345678901','SMS','A12345678','555-1234'),(50,'1955-06-19','stephenrome@example.com','Male','Stephen Rome','1381923992','Email','A3289191212',''),(51,'1956-02-19','alicecurry@example.com','Female','Alice Curry','1421913992','Email','A1289191334','');
/*!40000 ALTER TABLE `patient` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `patient_log`
--

DROP TABLE IF EXISTS `patient_log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `patient_log` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `birth_date` date DEFAULT NULL,
  `change_reason` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `gender` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `national_id` varchar(255) DEFAULT NULL,
  `notification_preference` varchar(255) DEFAULT NULL,
  `passport_number` varchar(255) DEFAULT NULL,
  `patient_id` bigint DEFAULT NULL,
  `phone_number` varchar(255) DEFAULT NULL,
  `version_number` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `patient_log`
--

LOCK TABLES `patient_log` WRITE;
/*!40000 ALTER TABLE `patient_log` DISABLE KEYS */;
INSERT INTO `patient_log` VALUES (1,'1993-07-20','Deleted','christopher.moore@example.com','Male','Christopher Moore','55667788990','SMS','E55667788',34,'555-7890',1),(2,'1993-07-20','Added','christopher.moore@example.com','Male','kiiiii','55667788990','SMS','E55667788',36,'555-7890',1),(3,'1965-07-15','Updated','john.smith@example.com','Male','John kiiiiii','12345678901','SMS','A12345678',36,'555-1234',2),(4,'1965-07-15','Updated','john.smith@example.com','Male','John kii3i','12345678901','SMS','A12345678',36,'555-1234',2),(5,'1965-07-15','Deleted','john.smith@example.com','Male','John kii3i','12345678901','SMS','A12345678',36,'555-1234',1),(6,'1978-04-15','Deleted','laura.martinez@example.com','Female','Laura Martinez','33445566778','Email','C33445566',13,'555-4567',1),(7,'3333-03-12','Added','','Male','','','SMS','',37,'',1),(8,'1988-03-05','Deleted','jason.thompson@example.com','Male','k4','99887766554','Email','A99887766',31,'555-1234',1),(9,'0133-09-24','Deleted','david.lee@example.com','Male','133','67890123456','Mail','',9,'555-7890',29),(10,'1982-07-12','Deleted','robert.miller@example.com','Male','Robert Miller','89012345678','Email','H89012345',10,'555-5678',2),(11,'0333-11-05','Updated','jennifer.davis@example.com','Female','Jennifer Davis','56789012345','Email','E56789012',11,'555-3456',3),(12,'3333-02-13','Deleted','velydagi@gmail.com','Male','Veli Dağı','','SMS','',38,'05537233927',1),(13,'3333-03-12','Deleted','','Male','','','SMS','',37,'',1),(14,'1988-02-11','Updated','christopher.moore@example.com','Male','Christopher Curry','51243748922','SMS','E54627183',14,'525-7810',3),(15,'1975-12-18','Updated','stephanie.hall@example.com','Female','','','Email','H88990011',16,'555-3456',2),(16,'2344-12-18','Deleted','stephanie.hall@example.com','Female','','','Email','H88990011',16,'555-3456',4),(21,'3333-02-03','Added','velydagi@gmail.com','Male','Veli Dağı','','','',45,'05537233927',1),(22,'2222-03-03','Added','','Male','d','1381923992','SMS','',46,'',1),(23,'0021-02-03','Added','','Male','d','1381923992','SMS','',47,'',1),(24,'1111-11-11','Added','2019556022@ogr.cu.edu.tr','Male','d','','SMS','',48,'',1),(25,'1333-03-12','Deleted','','Female','dda','','SMS','',40,'',1),(26,'2222-03-03','Deleted','','Male','d','1381923992','SMS','',46,'',1),(27,'3333-02-03','Deleted','velydagi@gmail.com','Male','Veli Dağı','','','',45,'05537233927',2),(28,'0520-02-12','Added','skaska@ksk','Male','çilli','5445445','SMS','77877',49,'88822',1),(29,'1988-03-05','Deleted','jason.thompson@example.com','Male','k3','99887766554','Email','A99887766',29,'555-1234',4),(30,'1955-06-19','Added','stephenrome@example.com','Male','Stephen Rome','1381923992','Email','A3289191212',50,'',1),(31,'1956-02-19','Added','alicecurry@example.com','Female','Alice Curry','1421913992','Email','A1289191334',51,'',1),(32,'1987-07-12','Updated','christopher.hoo@example.com','Male','Christopher Hoo','55667788990','Email','E45267788',32,'551-2840',6),(33,'1111-11-11','Deleted','2019556022@ogr.cu.edu.tr','Male','d','','SMS','',48,'',2),(34,'0021-02-03','Deleted','','Male','d','1381923992','SMS','',47,'',2),(35,'1965-07-15','Updated','john.smith@example.com','Male','John Dav','12345678901','SMS','A12345678',19,'555-1234',2);
/*!40000 ALTER TABLE `patient_log` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `target_criteria`
--

DROP TABLE IF EXISTS `target_criteria`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `target_criteria` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `message_text` varchar(255) DEFAULT NULL,
  `target_criteria_name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK9uvupluic3nl2n6nr8tg7eb8d` (`target_criteria_name`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `target_criteria`
--

LOCK TABLES `target_criteria` WRITE;
/*!40000 ALTER TABLE `target_criteria` DISABLE KEYS */;
INSERT INTO `target_criteria` VALUES (1,'Take small steps to stay healthy! Don\'t forget to exercise regularly','Stay Healthy'),(2,'Get screened for colon cancer. Early detection is crucial.','Colon Cancer'),(3,'It\'s time for your breast examination! Early detection saves lives.','Breast Cancer');
/*!40000 ALTER TABLE `target_criteria` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-07-18 16:38:37
