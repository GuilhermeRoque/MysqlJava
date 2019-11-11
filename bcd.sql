-- MySQL dump 10.13  Distrib 5.7.27, for Linux (x86_64)
--
-- Host: localhost    Database: mydb
-- ------------------------------------------------------
-- Server version	5.7.27-0ubuntu0.18.04.1

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
-- Table structure for table `Aluno`
--

DROP TABLE IF EXISTS `Aluno`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Aluno` (
  `idAluno` int(11) NOT NULL,
  `nome` varchar(45) NOT NULL,
  `idCurso` int(11) NOT NULL,
  `estado` bit(1) NOT NULL,
  PRIMARY KEY (`idAluno`,`idCurso`),
  KEY `fk_Aluno_Curso1_idx` (`idCurso`),
  CONSTRAINT `fk_Aluno_Curso1` FOREIGN KEY (`idCurso`) REFERENCES `Curso` (`idCurso`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Aluno`
--

LOCK TABLES `Aluno` WRITE;
/*!40000 ALTER TABLE `Aluno` DISABLE KEYS */;
INSERT INTO `Aluno` VALUES (1464166,'Juca',1,_binary ''),(3453224,'Roque',1,_binary ''),(3892138,'Joao',1,_binary ''),(4324324,'Mateus',1,_binary ''),(4783279,'Ana',1,_binary ''),(6461661,'Gabriela',1,_binary ''),(7381293,'Carlos',1,_binary ''),(9312903,'Alberto',1,_binary ''),(9333230,'Mateus',1,_binary ''),(83920338,'Jacinto',1,_binary '');
/*!40000 ALTER TABLE `Aluno` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Componente`
--

DROP TABLE IF EXISTS `Componente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Componente` (
  `idComponente` int(11) NOT NULL AUTO_INCREMENT,
  `pn` varchar(45) NOT NULL,
  PRIMARY KEY (`idComponente`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Componente`
--

LOCK TABLES `Componente` WRITE;
/*!40000 ALTER TABLE `Componente` DISABLE KEYS */;
INSERT INTO `Componente` VALUES (1,'AT8023280'),(2,'LM4738244'),(3,'AC3098302'),(4,'TRC873428'),(5,'LX8308408'),(6,'LA8304094');
/*!40000 ALTER TABLE `Componente` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Curso`
--

DROP TABLE IF EXISTS `Curso`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Curso` (
  `idCurso` int(11) NOT NULL AUTO_INCREMENT,
  `inicioLetivo` varchar(45) NOT NULL,
  `fimLetivo` varchar(45) NOT NULL,
  `inicioFeriasInverno` varchar(45) NOT NULL,
  `fimFeriasInverno` varchar(45) NOT NULL,
  `nome` varchar(45) NOT NULL,
  PRIMARY KEY (`idCurso`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Curso`
--

LOCK TABLES `Curso` WRITE;
/*!40000 ALTER TABLE `Curso` DISABLE KEYS */;
INSERT INTO `Curso` VALUES (1,'2019-02-02','2019-12-02','2019-06-02','2019-06-18','Eng Tele');
/*!40000 ALTER TABLE `Curso` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Emprestimo`
--

DROP TABLE IF EXISTS `Emprestimo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Emprestimo` (
  `idEmprestimo` int(11) NOT NULL AUTO_INCREMENT,
  `idAluno` int(11) NOT NULL,
  `dataRetirada` varchar(45) DEFAULT NULL,
  `dataEntrega` varchar(45) DEFAULT NULL,
  `motivo` varchar(45) NOT NULL,
  `count` varchar(45) NOT NULL,
  `dataAgendadaEntrega` varchar(45) NOT NULL,
  `dataAgendadaRetirada` varchar(45) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`idEmprestimo`,`idAluno`),
  KEY `fk_Emprestimo_Aluno1_idx` (`idAluno`),
  CONSTRAINT `fk_Emprestimo_Aluno1` FOREIGN KEY (`idAluno`) REFERENCES `Aluno` (`idAluno`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Emprestimo`
--

LOCK TABLES `Emprestimo` WRITE;
/*!40000 ALTER TABLE `Emprestimo` DISABLE KEYS */;
INSERT INTO `Emprestimo` VALUES (1,3453224,'2019-11-10',NULL,'1','0','2019-11-20','2019-11-10'),(2,4324324,'2019-11-10',NULL,'1','0','2019-11-20','2019-11-10'),(3,9333230,'2019-11-10',NULL,'1','0','2019-11-20','2019-11-10'),(4,6461661,'2019-11-10',NULL,'1','0','2019-11-20','2019-11-10');
/*!40000 ALTER TABLE `Emprestimo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Emprestimo_has_Equipamento`
--

DROP TABLE IF EXISTS `Emprestimo_has_Equipamento`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Emprestimo_has_Equipamento` (
  `idEquipamento` int(11) NOT NULL,
  `idEmprestimo` int(11) NOT NULL,
  `idAluno` int(11) NOT NULL,
  PRIMARY KEY (`idEquipamento`,`idEmprestimo`,`idAluno`),
  KEY `fk_Equipamento_has_Emprestimo_Emprestimo1_idx` (`idEmprestimo`,`idAluno`),
  KEY `fk_Equipamento_has_Emprestimo_Equipamento1_idx` (`idEquipamento`),
  CONSTRAINT `fk_Equipamento_has_Emprestimo_Emprestimo1` FOREIGN KEY (`idEmprestimo`, `idAluno`) REFERENCES `Emprestimo` (`idEmprestimo`, `idAluno`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Equipamento_has_Emprestimo_Equipamento1` FOREIGN KEY (`idEquipamento`) REFERENCES `Equipamento` (`idEquipamento`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Emprestimo_has_Equipamento`
--

LOCK TABLES `Emprestimo_has_Equipamento` WRITE;
/*!40000 ALTER TABLE `Emprestimo_has_Equipamento` DISABLE KEYS */;
INSERT INTO `Emprestimo_has_Equipamento` VALUES (1,1,3453224),(2,2,4324324),(3,3,9333230),(4,4,6461661);
/*!40000 ALTER TABLE `Emprestimo_has_Equipamento` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Equipamento`
--

DROP TABLE IF EXISTS `Equipamento`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Equipamento` (
  `idEquipamento` int(11) NOT NULL AUTO_INCREMENT,
  `descricao` varchar(45) NOT NULL,
  PRIMARY KEY (`idEquipamento`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Equipamento`
--

LOCK TABLES `Equipamento` WRITE;
/*!40000 ALTER TABLE `Equipamento` DISABLE KEYS */;
INSERT INTO `Equipamento` VALUES (1,'Equipamento A'),(2,'Equipamento B'),(3,'Equipamento C'),(4,'Equipamento D'),(5,'Equipamento E'),(6,'Equipamento F'),(7,'Equipamento G');
/*!40000 ALTER TABLE `Equipamento` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Equipamento_has_Componente`
--

DROP TABLE IF EXISTS `Equipamento_has_Componente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Equipamento_has_Componente` (
  `idEquipamento` int(11) NOT NULL,
  `idComponente` int(11) NOT NULL,
  PRIMARY KEY (`idEquipamento`,`idComponente`),
  KEY `fk_Equipamento_has_Componente_Componente1_idx` (`idComponente`),
  KEY `fk_Equipamento_has_Componente_Equipamento_idx` (`idEquipamento`),
  CONSTRAINT `fk_Equipamento_has_Componente_Componente1` FOREIGN KEY (`idComponente`) REFERENCES `Componente` (`idComponente`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Equipamento_has_Componente_Equipamento` FOREIGN KEY (`idEquipamento`) REFERENCES `Equipamento` (`idEquipamento`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Equipamento_has_Componente`
--

LOCK TABLES `Equipamento_has_Componente` WRITE;
/*!40000 ALTER TABLE `Equipamento_has_Componente` DISABLE KEYS */;
INSERT INTO `Equipamento_has_Componente` VALUES (1,1),(2,1),(3,1),(4,1),(1,2),(2,2),(4,2),(1,3),(3,3),(1,4),(2,4);
/*!40000 ALTER TABLE `Equipamento_has_Componente` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-11-10 23:04:01
