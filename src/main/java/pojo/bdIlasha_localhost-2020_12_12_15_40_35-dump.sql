-- MySQL dump 10.17  Distrib 10.3.23-MariaDB, for debian-linux-gnu (x86_64)
--
-- Host: 127.0.0.1    Database: bdIlasha
-- ------------------------------------------------------
-- Server version	10.3.23-MariaDB-0+deb10u1

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `BarMan`
--

DROP TABLE IF EXISTS `BarMan`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `BarMan` (
  `IdBarMan` int(11) NOT NULL AUTO_INCREMENT,
  `Name` varchar(50) NOT NULL,
  `SecondName` varchar(50) NOT NULL,
  `MiddleName` varchar(50) NOT NULL,
  `Phone` varchar(50) NOT NULL,
  `DateJoin` date NOT NULL,
  `DateDismiss` date DEFAULT NULL,
  `Salary` int(11) NOT NULL,
  `IdUser` int(11) NOT NULL,
  PRIMARY KEY (`IdBarMan`),
  KEY `IdUser` (`IdUser`),
  CONSTRAINT `BarMan_ibfk_1` FOREIGN KEY (`IdUser`) REFERENCES `User` (`IdUser`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `BarMan`
--

LOCK TABLES `BarMan` WRITE;
/*!40000 ALTER TABLE `BarMan` DISABLE KEYS */;
/*!40000 ALTER TABLE `BarMan` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `BeerStorage`
--

DROP TABLE IF EXISTS `BeerStorage`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `BeerStorage` (
  `IdBeerKind` int(11) NOT NULL AUTO_INCREMENT,
  `Name` varchar(50) NOT NULL,
  `Price` int(11) NOT NULL,
  `Type` varchar(50) NOT NULL,
  `Amount` int(11) NOT NULL,
  `CookTime` int(11) NOT NULL,
  PRIMARY KEY (`IdBeerKind`),
  UNIQUE KEY `Name` (`Name`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `BeerStorage`
--

LOCK TABLES `BeerStorage` WRITE;
/*!40000 ALTER TABLE `BeerStorage` DISABLE KEYS */;
INSERT INTO `BeerStorage` (`IdBeerKind`, `Name`, `Price`, `Type`, `Amount`, `CookTime`) VALUES (1,'Ale',100,'Our',10,50),(2,'Lager',120,'Our',2,100),(3,'VolkovskoeHoney',150,'Import',4,1),(4,'GarageLemon',80,'Import',3,1),(5,'GingerBeer',90,'Craft',10,30),(6,'BavarianWheat',125,'Our',2,40);
/*!40000 ALTER TABLE `BeerStorage` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ClientList`
--

DROP TABLE IF EXISTS `ClientList`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ClientList` (
  `IdClient` int(11) NOT NULL AUTO_INCREMENT,
  `NameClient` varchar(50) NOT NULL,
  `SecondNameClient` varchar(50) NOT NULL,
  `MiddleNameClient` varchar(50) NOT NULL,
  `PhoneClient` varchar(50) NOT NULL,
  `Age` date NOT NULL,
  `DateJoin` date NOT NULL,
  PRIMARY KEY (`IdClient`),
  UNIQUE KEY `PhoneClient` (`PhoneClient`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ClientList`
--

LOCK TABLES `ClientList` WRITE;
/*!40000 ALTER TABLE `ClientList` DISABLE KEYS */;
/*!40000 ALTER TABLE `ClientList` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `CylindricallyConicalTank`
--

DROP TABLE IF EXISTS `CylindricallyConicalTank`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `CylindricallyConicalTank` (
  `IdCCT` int(11) NOT NULL AUTO_INCREMENT,
  `IdTask` int(11) NOT NULL,
  `DateStart` date NOT NULL,
  `DateEnd` date DEFAULT NULL,
  `StatusCCT` varchar(20) NOT NULL,
  PRIMARY KEY (`IdCCT`),
  KEY `IdTask` (`IdTask`),
  CONSTRAINT `CylindricallyConicalTank_ibfk_1` FOREIGN KEY (`IdTask`) REFERENCES `Task` (`IdTask`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `CylindricallyConicalTank`
--

LOCK TABLES `CylindricallyConicalTank` WRITE;
/*!40000 ALTER TABLE `CylindricallyConicalTank` DISABLE KEYS */;
INSERT INTO `CylindricallyConicalTank` (`IdCCT`, `IdTask`, `DateStart`, `DateEnd`, `StatusCCT`) VALUES (1,1,'1970-01-01','1970-02-10','WORK'),(2,1,'1970-01-01',NULL,'FREE'),(3,1,'1970-01-01',NULL,'FREE');
/*!40000 ALTER TABLE `CylindricallyConicalTank` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ImportAlcBuy`
--

DROP TABLE IF EXISTS `ImportAlcBuy`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ImportAlcBuy` (
  `IdImportAlcBuy` int(11) NOT NULL AUTO_INCREMENT,
  `IdBarman` int(11) NOT NULL,
  `IdManager` int(11) NOT NULL,
  `Date` date NOT NULL,
  `Status` varchar(50) NOT NULL,
  PRIMARY KEY (`IdImportAlcBuy`),
  KEY `IdBarman` (`IdBarman`),
  KEY `IdManager` (`IdManager`),
  CONSTRAINT `ImportAlcBuy_ibfk_1` FOREIGN KEY (`IdBarman`) REFERENCES `BarMan` (`IdBarMan`),
  CONSTRAINT `ImportAlcBuy_ibfk_2` FOREIGN KEY (`IdManager`) REFERENCES `Manager` (`IdManager`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ImportAlcBuy`
--

LOCK TABLES `ImportAlcBuy` WRITE;
/*!40000 ALTER TABLE `ImportAlcBuy` DISABLE KEYS */;
/*!40000 ALTER TABLE `ImportAlcBuy` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ImportAlcBuyPosition`
--

DROP TABLE IF EXISTS `ImportAlcBuyPosition`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ImportAlcBuyPosition` (
  `IdImportAlcPosition` int(11) NOT NULL AUTO_INCREMENT,
  `Number` int(11) NOT NULL,
  `IdImportAlcBuy` int(11) NOT NULL,
  `IdBeerKind` int(11) NOT NULL,
  PRIMARY KEY (`IdImportAlcPosition`),
  KEY `IdImportAlcBuy` (`IdImportAlcBuy`),
  KEY `IdBeerKind` (`IdBeerKind`),
  CONSTRAINT `ImportAlcBuyPosition_ibfk_1` FOREIGN KEY (`IdImportAlcBuy`) REFERENCES `ImportAlcBuy` (`IdImportAlcBuy`),
  CONSTRAINT `ImportAlcBuyPosition_ibfk_2` FOREIGN KEY (`IdBeerKind`) REFERENCES `BeerStorage` (`IdBeerKind`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ImportAlcBuyPosition`
--

LOCK TABLES `ImportAlcBuyPosition` WRITE;
/*!40000 ALTER TABLE `ImportAlcBuyPosition` DISABLE KEYS */;
/*!40000 ALTER TABLE `ImportAlcBuyPosition` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `LoaderMan`
--

DROP TABLE IF EXISTS `LoaderMan`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `LoaderMan` (
  `IdLoaderMan` int(11) NOT NULL AUTO_INCREMENT,
  `Name` varchar(50) NOT NULL,
  `SecondName` varchar(50) NOT NULL,
  `MiddleName` varchar(50) NOT NULL,
  `Phone` varchar(50) NOT NULL,
  `DateJoin` date NOT NULL,
  `DateDismiss` date DEFAULT NULL,
  `Salary` int(11) NOT NULL,
  `IdUser` int(11) NOT NULL,
  PRIMARY KEY (`IdLoaderMan`),
  KEY `IdUser` (`IdUser`),
  CONSTRAINT `LoaderMan_ibfk_1` FOREIGN KEY (`IdUser`) REFERENCES `User` (`IdUser`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `LoaderMan`
--

LOCK TABLES `LoaderMan` WRITE;
/*!40000 ALTER TABLE `LoaderMan` DISABLE KEYS */;
/*!40000 ALTER TABLE `LoaderMan` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `LoaderTask`
--

DROP TABLE IF EXISTS `LoaderTask`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `LoaderTask` (
  `IdLoaderTask` int(11) NOT NULL AUTO_INCREMENT,
  `IdLoaderMan` int(11) NOT NULL,
  `IdResBuy` int(11) NOT NULL,
  `IdImportAlc` int(11) NOT NULL,
  `Date` date NOT NULL,
  `Status` varchar(50) NOT NULL,
  PRIMARY KEY (`IdLoaderTask`),
  KEY `IdLoaderMan` (`IdLoaderMan`),
  KEY `IdResBuy` (`IdResBuy`),
  KEY `IdImportAlc` (`IdImportAlc`),
  CONSTRAINT `LoaderTask_ibfk_1` FOREIGN KEY (`IdLoaderMan`) REFERENCES `LoaderMan` (`IdLoaderMan`),
  CONSTRAINT `LoaderTask_ibfk_2` FOREIGN KEY (`IdResBuy`) REFERENCES `ResBuy` (`IdResBuy`),
  CONSTRAINT `LoaderTask_ibfk_3` FOREIGN KEY (`IdImportAlc`) REFERENCES `ImportAlcBuy` (`IdImportAlcBuy`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `LoaderTask`
--

LOCK TABLES `LoaderTask` WRITE;
/*!40000 ALTER TABLE `LoaderTask` DISABLE KEYS */;
/*!40000 ALTER TABLE `LoaderTask` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Manager`
--

DROP TABLE IF EXISTS `Manager`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Manager` (
  `IdManager` int(11) NOT NULL AUTO_INCREMENT,
  `Name` varchar(50) NOT NULL,
  `SecondName` varchar(50) NOT NULL,
  `MiddleName` varchar(50) NOT NULL,
  `Phone` varchar(50) NOT NULL,
  `DateJoin` date NOT NULL,
  `DateDismiss` date DEFAULT NULL,
  `Salary` int(11) NOT NULL,
  `IdUser` int(11) NOT NULL,
  PRIMARY KEY (`IdManager`),
  KEY `IdUser` (`IdUser`),
  CONSTRAINT `Manager_ibfk_1` FOREIGN KEY (`IdUser`) REFERENCES `User` (`IdUser`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Manager`
--

LOCK TABLES `Manager` WRITE;
/*!40000 ALTER TABLE `Manager` DISABLE KEYS */;
/*!40000 ALTER TABLE `Manager` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `OrderPosition`
--

DROP TABLE IF EXISTS `OrderPosition`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `OrderPosition` (
  `IdOrderPosition` int(11) NOT NULL AUTO_INCREMENT,
  `Number` int(11) NOT NULL,
  `IdOrder` int(11) NOT NULL,
  `IdBeerKind` int(11) NOT NULL,
  PRIMARY KEY (`IdOrderPosition`),
  KEY `IdOrder` (`IdOrder`),
  KEY `IdBeerKind` (`IdBeerKind`),
  CONSTRAINT `OrderPosition_ibfk_1` FOREIGN KEY (`IdOrder`) REFERENCES `Orders` (`IdOrder`),
  CONSTRAINT `OrderPosition_ibfk_2` FOREIGN KEY (`IdBeerKind`) REFERENCES `BeerStorage` (`IdBeerKind`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `OrderPosition`
--

LOCK TABLES `OrderPosition` WRITE;
/*!40000 ALTER TABLE `OrderPosition` DISABLE KEYS */;
/*!40000 ALTER TABLE `OrderPosition` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Orders`
--

DROP TABLE IF EXISTS `Orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Orders` (
  `IdOrder` int(11) NOT NULL AUTO_INCREMENT,
  `IdClient` int(11) NOT NULL,
  `IdManager` int(11) NOT NULL,
  `Date` date NOT NULL,
  `Status` varchar(50) NOT NULL,
  PRIMARY KEY (`IdOrder`),
  KEY `IdClient` (`IdClient`),
  KEY `IdManager` (`IdManager`),
  CONSTRAINT `Orders_ibfk_1` FOREIGN KEY (`IdClient`) REFERENCES `ClientList` (`IdClient`),
  CONSTRAINT `Orders_ibfk_2` FOREIGN KEY (`IdManager`) REFERENCES `Manager` (`IdManager`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Orders`
--

LOCK TABLES `Orders` WRITE;
/*!40000 ALTER TABLE `Orders` DISABLE KEYS */;
/*!40000 ALTER TABLE `Orders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `RecipeList`
--

DROP TABLE IF EXISTS `RecipeList`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `RecipeList` (
  `idRecipe` int(11) NOT NULL AUTO_INCREMENT,
  `IdBeerKind` int(11) NOT NULL,
  `IdResource` int(11) NOT NULL,
  `Amount` int(11) NOT NULL,
  PRIMARY KEY (`idRecipe`),
  KEY `IdBeerKind` (`IdBeerKind`),
  KEY `IdResource` (`IdResource`),
  CONSTRAINT `RecipeList_ibfk_1` FOREIGN KEY (`IdBeerKind`) REFERENCES `BeerStorage` (`IdBeerKind`),
  CONSTRAINT `RecipeList_ibfk_2` FOREIGN KEY (`IdResource`) REFERENCES `ResourceStorage` (`IdResource`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `RecipeList`
--

LOCK TABLES `RecipeList` WRITE;
/*!40000 ALTER TABLE `RecipeList` DISABLE KEYS */;
INSERT INTO `RecipeList` (`idRecipe`, `IdBeerKind`, `IdResource`, `Amount`) VALUES (1,1,1,5),(2,1,2,4),(3,1,3,2),(4,1,8,1),(5,6,1,5),(6,6,2,4),(7,6,6,1),(8,6,7,1),(9,2,1,5),(10,2,2,4),(11,2,5,1);
/*!40000 ALTER TABLE `RecipeList` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ResBuy`
--

DROP TABLE IF EXISTS `ResBuy`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ResBuy` (
  `IdResBuy` int(11) NOT NULL AUTO_INCREMENT,
  `IdEngineer` int(11) NOT NULL,
  `IdManager` int(11) NOT NULL,
  `Date` date NOT NULL,
  `Status` varchar(50) NOT NULL,
  PRIMARY KEY (`IdResBuy`),
  KEY `IdEngineer` (`IdEngineer`),
  KEY `IdManager` (`IdManager`),
  CONSTRAINT `ResBuy_ibfk_1` FOREIGN KEY (`IdEngineer`) REFERENCES `TechnologistEngineer` (`IdTechnologistEngineer`),
  CONSTRAINT `ResBuy_ibfk_2` FOREIGN KEY (`IdManager`) REFERENCES `Manager` (`IdManager`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ResBuy`
--

LOCK TABLES `ResBuy` WRITE;
/*!40000 ALTER TABLE `ResBuy` DISABLE KEYS */;
/*!40000 ALTER TABLE `ResBuy` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ResBuyPosition`
--

DROP TABLE IF EXISTS `ResBuyPosition`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ResBuyPosition` (
  `IdResBuyPosition` int(11) NOT NULL AUTO_INCREMENT,
  `Number` int(11) NOT NULL,
  `IdResBuy` int(11) NOT NULL,
  `IdResource` int(11) NOT NULL,
  PRIMARY KEY (`IdResBuyPosition`),
  KEY `IdResBuy` (`IdResBuy`),
  KEY `IdResource` (`IdResource`),
  CONSTRAINT `ResBuyPosition_ibfk_1` FOREIGN KEY (`IdResBuy`) REFERENCES `ResBuy` (`IdResBuy`),
  CONSTRAINT `ResBuyPosition_ibfk_2` FOREIGN KEY (`IdResource`) REFERENCES `ResourceStorage` (`IdResource`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ResBuyPosition`
--

LOCK TABLES `ResBuyPosition` WRITE;
/*!40000 ALTER TABLE `ResBuyPosition` DISABLE KEYS */;
/*!40000 ALTER TABLE `ResBuyPosition` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ResourceStorage`
--

DROP TABLE IF EXISTS `ResourceStorage`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ResourceStorage` (
  `IdResource` int(11) NOT NULL AUTO_INCREMENT,
  `Name` varchar(50) NOT NULL,
  `Amount` int(11) NOT NULL,
  `Price` int(11) NOT NULL,
  `Unit` varchar(50) NOT NULL,
  PRIMARY KEY (`IdResource`),
  UNIQUE KEY `Name` (`Name`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ResourceStorage`
--

LOCK TABLES `ResourceStorage` WRITE;
/*!40000 ALTER TABLE `ResourceStorage` DISABLE KEYS */;
INSERT INTO `ResourceStorage` (`IdResource`, `Name`, `Amount`, `Price`, `Unit`) VALUES (1,'Water',1000,1,'liters'),(2,'HotWater',1000,2,'liters'),(3,'Hop',250,4,'piece'),(4,'Sugar',20000,3,'kg'),(5,'Wheat',1000,2,'kg'),(6,'MaltPilsher',200,4,'kg'),(7,'MunichMalt',150,3,'kg'),(8,'Malt',50,1,'kg'),(9,'AscorbicAcid',5,1,'kg');
/*!40000 ALTER TABLE `ResourceStorage` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `StaffManager`
--

DROP TABLE IF EXISTS `StaffManager`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `StaffManager` (
  `IdStaffManager` int(11) NOT NULL AUTO_INCREMENT,
  `Name` varchar(50) NOT NULL,
  `SecondName` varchar(50) NOT NULL,
  `MiddleName` varchar(50) NOT NULL,
  `Phone` varchar(50) NOT NULL,
  `DateJoin` date NOT NULL,
  `DateDismiss` date DEFAULT NULL,
  `Salary` int(11) NOT NULL,
  `IdUser` int(11) NOT NULL,
  PRIMARY KEY (`IdStaffManager`),
  KEY `IdUser` (`IdUser`),
  CONSTRAINT `StaffManager_ibfk_1` FOREIGN KEY (`IdUser`) REFERENCES `User` (`IdUser`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `StaffManager`
--

LOCK TABLES `StaffManager` WRITE;
/*!40000 ALTER TABLE `StaffManager` DISABLE KEYS */;
/*!40000 ALTER TABLE `StaffManager` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Task`
--

DROP TABLE IF EXISTS `Task`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Task` (
  `IdTask` int(11) NOT NULL AUTO_INCREMENT,
  `IdTechnologicalEngineer` int(11) NOT NULL,
  `IdBeerKind` int(11) NOT NULL,
  `Date` date NOT NULL,
  `Status` varchar(50) NOT NULL,
  `Amount` int(11) NOT NULL,
  PRIMARY KEY (`IdTask`),
  KEY `IdTechnologicalEngineer` (`IdTechnologicalEngineer`),
  KEY `IdBeerKind` (`IdBeerKind`),
  CONSTRAINT `Task_ibfk_1` FOREIGN KEY (`IdTechnologicalEngineer`) REFERENCES `TechnologistEngineer` (`IdTechnologistEngineer`),
  CONSTRAINT `Task_ibfk_2` FOREIGN KEY (`IdBeerKind`) REFERENCES `BeerStorage` (`IdBeerKind`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Task`
--

LOCK TABLES `Task` WRITE;
/*!40000 ALTER TABLE `Task` DISABLE KEYS */;
INSERT INTO `Task` (`IdTask`, `IdTechnologicalEngineer`, `IdBeerKind`, `Date`, `Status`, `Amount`) VALUES (1,1,1,'2020-01-14','Active',3),(3,2,2,'2020-01-04','Done',4),(4,1,6,'2020-01-15','Active',5);
/*!40000 ALTER TABLE `Task` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `TechnologistEngineer`
--

DROP TABLE IF EXISTS `TechnologistEngineer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `TechnologistEngineer` (
  `IdTechnologistEngineer` int(11) NOT NULL AUTO_INCREMENT,
  `Name` varchar(50) NOT NULL,
  `SecondName` varchar(50) NOT NULL,
  `MiddleName` varchar(50) NOT NULL,
  `Phone` varchar(50) NOT NULL,
  `DateJoin` date NOT NULL,
  `DateDismiss` date DEFAULT NULL,
  `Salary` int(11) NOT NULL,
  `IdUser` int(11) NOT NULL,
  PRIMARY KEY (`IdTechnologistEngineer`),
  KEY `IdUser` (`IdUser`),
  CONSTRAINT `TechnologistEngineer_ibfk_1` FOREIGN KEY (`IdUser`) REFERENCES `User` (`IdUser`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `TechnologistEngineer`
--

LOCK TABLES `TechnologistEngineer` WRITE;
/*!40000 ALTER TABLE `TechnologistEngineer` DISABLE KEYS */;
INSERT INTO `TechnologistEngineer` (`IdTechnologistEngineer`, `Name`, `SecondName`, `MiddleName`, `Phone`, `DateJoin`, `DateDismiss`, `Salary`, `IdUser`) VALUES (1,'Novikov','Alexey','Igorevich','8(952)344-24-15','2020-12-01',NULL,20000,1),(2,'Ptushkin','Anton','Olegovich','8(911)234-123-33','2020-12-02',NULL,20000,2);
/*!40000 ALTER TABLE `TechnologistEngineer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `User`
--

DROP TABLE IF EXISTS `User`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `User` (
  `IdUser` int(11) NOT NULL AUTO_INCREMENT,
  `Login` varchar(30) NOT NULL,
  `Password` varchar(30) NOT NULL,
  `Role` varchar(50) NOT NULL,
  PRIMARY KEY (`IdUser`),
  UNIQUE KEY `Login` (`Login`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `User`
--

LOCK TABLES `User` WRITE;
/*!40000 ALTER TABLE `User` DISABLE KEYS */;
INSERT INTO `User` (`IdUser`, `Login`, `Password`, `Role`) VALUES (1,'w_eng1','d19lbmcx','engineer'),(2,'w_eng2','d19lbmcy','engineer'),(3,'w_man1','d19tYW4x','staff_manager'),(4,'w_bar1','d19iYXIx','barman'),(5,'w_load1','d19sb2FkMQ==','loader'),(6,'w_man2','d19tYW4y','manager'),(7,'w_staff1','d19zdGFmZjE=','staff_manager'),(8,'w_man3','d19tYW4z','manager');
/*!40000 ALTER TABLE `User` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-12-12 15:40:35
