-- MySQL dump 10.15  Distrib 10.0.18-MariaDB, for Linux (x86_64)
--
-- Host: localhost    Database: elcucharon
-- ------------------------------------------------------
-- Server version	10.0.18-MariaDB-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `reserva`
--

DROP TABLE IF EXISTS `reserva`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `reserva` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_restaurante` int(11) NOT NULL,
  `id_user` int(11) NOT NULL,
  `fecha` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `id_restaurante` (`id_restaurante`),
  KEY `id_user` (`id_user`),
  CONSTRAINT `reserva_ibfk_1` FOREIGN KEY (`id_restaurante`) REFERENCES `restaurante` (`id`),
  CONSTRAINT `reserva_ibfk_2` FOREIGN KEY (`id_user`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reserva`
--

LOCK TABLES `reserva` WRITE;
/*!40000 ALTER TABLE `reserva` DISABLE KEYS */;
INSERT INTO `reserva` VALUES (2,5,18,'2015-06-05 19:00:00'),(3,5,18,'2015-06-05 19:15:00'),(4,5,18,'2015-06-05 19:30:00'),(5,5,18,'2015-06-05 19:45:00'),(6,5,18,'2015-06-05 20:00:00'),(7,5,18,'2015-06-15 19:00:00'),(8,5,18,'2015-06-15 19:15:00'),(9,5,18,'2015-06-15 19:30:00'),(10,5,18,'2015-06-15 19:45:00'),(11,5,18,'2015-06-15 20:00:00'),(13,5,18,'2015-06-04 19:00:00'),(14,5,17,'2015-06-11 19:30:00');
/*!40000 ALTER TABLE `reserva` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `restaurante`
--

DROP TABLE IF EXISTS `restaurante`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `restaurante` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `lat` decimal(10,8) DEFAULT '0.00000000',
  `lng` decimal(11,8) DEFAULT '0.00000000',
  `mesas` int(11) DEFAULT NULL,
  `foto` varchar(255) DEFAULT NULL,
  `descripcion` text,
  `nombre` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `restaurante`
--

LOCK TABLES `restaurante` WRITE;
/*!40000 ALTER TABLE `restaurante` DISABLE KEYS */;
INSERT INTO `restaurante` VALUES (5,42.68938200,-2.94125500,3,'el_vagon.jpg','Una joya suiza de 1946 habilitado como comedor y con todas las comodidades como calefacción, hilo musical, ventanas motorizadas, aseo y una decoración cálida y acogedora','El Vagón'),(6,42.68757000,-2.94301300,3,'espaciobocca.jpg','Bocca es un espacio vivo, un restaurante donde comer bien, un espacio artístico donde tomar café y tapear, consultar internet, encontrarte con amigos, tomar una copa o bailar','Bocca'),(7,42.68864800,-2.95352800,2,'horno_san_juan.jpg','Comidas, desayunos y cenas','Horno de San Juan'),(9,42.68760500,-2.94289400,2,'la_roca.jpg','Me atrevería a decir que habrá pocos lugares donde preparen tapas de este nivel en vivo. Un auténtico show cooking en directo! Si vienes a Miranda no te pierdas este lugar. Acertaras con cualquier plato de la carta. Entrantes fantásticos. La única pega es que no te puedes sentar en una mesa a disfrutar de estos platos.','La Roca'),(12,42.68431500,-2.94935000,3,'la-vasca.jpg','Un buen sitio para tomar un buen lechazo asado, manteniendo tradición y calidad','La Vasca');
/*!40000 ALTER TABLE `restaurante` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(255) DEFAULT NULL,
  `pass` varchar(255) DEFAULT NULL,
  `movil` varchar(15) DEFAULT NULL,
  `dni` varchar(255) DEFAULT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  `foto` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `email` (`email`),
  UNIQUE KEY `movil` (`movil`),
  UNIQUE KEY `dni` (`dni`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (6,'pedro@picapiedra.com','12345','677777777','76666666G','Pedro',NULL),(7,'pablo@toledo.com','12345','655201763','75555555W','Pablito','user_pablo@toledo.com.png'),(11,'diego@depablo.com','12345','84524','555L','diego',NULL),(15,'miprima@miprimita.com','12345','4656','djdk','Mi prima','user_miprima@miprimita.com.png'),(16,'miotraprima@miotraprimita.com','12345','5656262','jdjdjf','Mi otra prima','user_miotraprima@miotraprimita.com.png'),(17,'user@sinfoto.com','12345','999999899','55555554H','User sin foto',NULL),(18,'portu@gues.com','12345','666666666','77777777E','Edu','user_portu@gues.com.png'),(19,'pablo@marmol.com','12345','677777776','76666667G','Pablo',NULL);
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

-- Dump completed on 2015-06-03 17:26:19
