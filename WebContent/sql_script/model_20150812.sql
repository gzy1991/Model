-- MySQL dump 10.13  Distrib 5.6.23, for Win32 (x86)
--
-- Host: localhost    Database: model
-- ------------------------------------------------------
-- Server version	5.6.23-log

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
-- Table structure for table `t_board`
--

DROP TABLE IF EXISTS `t_board`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_board` (
  `boardId` int(11) NOT NULL AUTO_INCREMENT,
  `boardName` varchar(20) DEFAULT NULL,
  `boardDesc` varchar(20) DEFAULT NULL,
  `topicNum` int(11) DEFAULT NULL,
  PRIMARY KEY (`boardId`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_board`
--

LOCK TABLES `t_board` WRITE;
/*!40000 ALTER TABLE `t_board` DISABLE KEYS */;
INSERT INTO `t_board` VALUES (1,'news','news board',10),(2,'amusement','amusement board',2),(3,'weather','weather board',2),(4,'society','society board',4);
/*!40000 ALTER TABLE `t_board` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_group`
--

DROP TABLE IF EXISTS `t_group`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_group` (
  `gourpId` int(11) NOT NULL,
  `groupName` varchar(10) DEFAULT NULL,
  `leader` varchar(10) DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  PRIMARY KEY (`gourpId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_group`
--

LOCK TABLES `t_group` WRITE;
/*!40000 ALTER TABLE `t_group` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_group` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_member`
--

DROP TABLE IF EXISTS `t_member`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_member` (
  `memberId` int(11) NOT NULL AUTO_INCREMENT,
  `grade` varchar(255) DEFAULT NULL,
  `memberName` varchar(255) DEFAULT NULL,
  `birthDate` date DEFAULT NULL,
  `nowAddress` varchar(255) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `expProject` varchar(255) DEFAULT NULL,
  `skill` varchar(255) DEFAULT NULL,
  `award` varchar(255) DEFAULT NULL,
  `selfEvaluation` varchar(255) DEFAULT NULL,
  `imgUrl` varchar(255) DEFAULT NULL,
  `category` int(11) DEFAULT NULL,
  `loadname` varchar(20) DEFAULT NULL,
  `password` varchar(20) DEFAULT NULL,
  `email` varchar(25) DEFAULT NULL,
  `gender` varchar(10) DEFAULT NULL,
  `QQ` varchar(20) DEFAULT NULL,
  `mobilePhone` varchar(20) DEFAULT NULL,
  `major` varchar(10) DEFAULT '',
  `classNo` varchar(7) DEFAULT NULL,
  PRIMARY KEY (`memberId`),
  KEY `memberName` (`memberName`)
) ENGINE=InnoDB AUTO_INCREMENT=49 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_member`
--

LOCK TABLES `t_member` WRITE;
/*!40000 ALTER TABLE `t_member` DISABLE KEYS */;
INSERT INTO `t_member` VALUES (3,'2','黄果',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,2,'aa','a',NULL,NULL,NULL,NULL,NULL,NULL),(6,'14','3','1990-00-00','大连','河南','啦啦','啦啦','啦啦','啦啦','GZY.jpg',0,'','','20@qq',NULL,NULL,NULL,NULL,NULL),(33,'14','耿彩丽','1990-00-00','大连','河南','啦啦','啦啦','啦啦','啦啦','GZY.jpg',0,'','','20@qq','','','','',''),(44,'14','关振宇','1990-00-00','大连','河南','啦啦','啦啦','啦啦','啦啦','GZY.jpg',0,'','','20@qq','','','','',''),(45,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,2,'123','123456',NULL,NULL,NULL,NULL,NULL,NULL),(46,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,2,'哈哈','123456',NULL,NULL,NULL,NULL,NULL,NULL),(47,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,'test1','test1',NULL,NULL,NULL,NULL,NULL,NULL),(48,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,2,'bb','bb',NULL,NULL,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `t_member` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_news`
--

DROP TABLE IF EXISTS `t_news`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_news` (
  `newsId` int(11) NOT NULL AUTO_INCREMENT,
  `publishDate` date DEFAULT NULL,
  `publishName` varchar(255) DEFAULT NULL,
  `content` varchar(255) DEFAULT NULL,
  `newsName` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`newsId`),
  KEY `newsPublisher` (`publishName`),
  CONSTRAINT `t_news_ibfk_1` FOREIGN KEY (`publishName`) REFERENCES `t_member` (`memberName`) ON DELETE SET NULL
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_news`
--

LOCK TABLES `t_news` WRITE;
/*!40000 ALTER TABLE `t_news` DISABLE KEYS */;
INSERT INTO `t_news` VALUES (1,'2015-06-11','3','11','新闻1'),(2,'2015-06-10','关振宇','1','新闻22'),(3,'2015-06-25','关振宇','2','新闻33'),(4,'2015-06-24','3','3','新闻44'),(5,'2015-06-23','耿彩丽','5','新闻55'),(6,'2015-06-22','黄果','6','新闻66'),(7,'2015-06-21','关振宇','7','新闻77'),(8,'2015-06-11','耿彩丽','8','新闻88'),(9,'2015-06-17','耿彩丽','9','新闻99'),(10,'2015-06-08','3','10','新闻1010'),(11,'2015-06-23','3','11','新闻1111'),(12,'2015-05-26','耿彩丽','12','新闻1212'),(13,'2015-05-26','耿彩丽','133','新闻1313'),(14,'2015-05-26','黄果','1412','新闻1414'),(15,'2015-05-26','黄果','1412','新闻1515'),(16,'2015-05-01','耿彩丽','124124','新闻1616'),(17,'2015-05-30','耿彩丽','12412','新闻1717'),(18,'2015-05-02','关振宇','41241','新闻1818'),(19,'2015-05-04','关振宇','123412','新闻1919'),(20,'2015-05-09','关振宇','4','新闻2020'),(21,'2015-05-26','关振宇','33','新闻2121'),(22,'2015-07-05','关振宇','测试','测试'),(23,'2015-07-05','关振宇','测试2','测试2'),(24,'2015-07-05','关振宇','测试4','测试4'),(25,'2015-07-11','关振宇','111','222'),(26,'2015-07-11','关振宇','123','123'),(28,'2015-07-16','关振宇','28','28'),(29,'2015-07-16','关振宇','你好，FEP欢迎你','你好，FEP欢迎你'),(30,'2015-07-16','关振宇','30','30'),(31,'2015-07-16','关振宇','31','31');
/*!40000 ALTER TABLE `t_news` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_post`
--

DROP TABLE IF EXISTS `t_post`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_post` (
  `topicId` int(11) NOT NULL,
  `boardId` int(11) DEFAULT NULL,
  `topicTitle` varchar(255) DEFAULT NULL,
  `userId` int(11) DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  `lastPost` datetime DEFAULT NULL,
  `topicViews` varchar(255) DEFAULT NULL,
  `topicReplies` varchar(255) DEFAULT NULL,
  `digest` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`topicId`),
  KEY `userId` (`userId`),
  KEY `boardId` (`boardId`),
  CONSTRAINT `t_post_ibfk_1` FOREIGN KEY (`userId`) REFERENCES `t_user` (`userId`),
  CONSTRAINT `t_post_ibfk_2` FOREIGN KEY (`boardId`) REFERENCES `t_board` (`boardId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_post`
--

LOCK TABLES `t_post` WRITE;
/*!40000 ALTER TABLE `t_post` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_post` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_topic`
--

DROP TABLE IF EXISTS `t_topic`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_topic` (
  `topicId` int(11) NOT NULL AUTO_INCREMENT,
  `boardId` int(11) DEFAULT NULL,
  `topicTitle` varchar(100) DEFAULT NULL,
  `userId` int(11) DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  `lastPost` datetime DEFAULT NULL,
  `topicViews` int(11) DEFAULT NULL,
  `topicReplies` int(11) DEFAULT NULL,
  `digest` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`topicId`),
  KEY `boardId` (`boardId`),
  KEY `userId` (`userId`),
  CONSTRAINT `t_topic_ibfk_1` FOREIGN KEY (`boardId`) REFERENCES `t_board` (`boardId`),
  CONSTRAINT `t_topic_ibfk_2` FOREIGN KEY (`userId`) REFERENCES `t_user` (`userId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_topic`
--

LOCK TABLES `t_topic` WRITE;
/*!40000 ALTER TABLE `t_topic` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_topic` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_user`
--

DROP TABLE IF EXISTS `t_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_user` (
  `userId` int(11) NOT NULL AUTO_INCREMENT,
  `userName` varchar(30) DEFAULT NULL,
  `gender` varchar(255) DEFAULT NULL,
  `studentId` varchar(255) DEFAULT NULL,
  `category` varchar(11) DEFAULT NULL,
  `birthday` date DEFAULT NULL,
  `classType` varchar(255) DEFAULT NULL,
  `grade` varchar(255) DEFAULT NULL,
  `classNo` int(11) unsigned zerofill DEFAULT NULL,
  `major` varchar(255) DEFAULT NULL,
  `mobilePhone` varchar(255) DEFAULT NULL,
  `ranking` varchar(255) DEFAULT NULL,
  `lowCourse` varchar(255) DEFAULT NULL,
  `highCourse` varchar(255) DEFAULT NULL,
  `changeGroup` varchar(255) DEFAULT NULL,
  `subtitle2` varchar(255) DEFAULT NULL,
  `subtitle3` varchar(255) DEFAULT NULL,
  `subtitle4` varchar(255) DEFAULT NULL,
  `subtitle5` varchar(255) DEFAULT NULL,
  `subtitle6` varchar(255) DEFAULT NULL,
  `subtitle1` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `QQ` varchar(255) DEFAULT NULL,
  `checkUp` tinyint(4) DEFAULT NULL,
  `setUpTime` date DEFAULT NULL,
  PRIMARY KEY (`userId`),
  KEY `userName` (`userName`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_user`
--

LOCK TABLES `t_user` WRITE;
/*!40000 ALTER TABLE `t_user` DISABLE KEYS */;
INSERT INTO `t_user` VALUES (5,'as',NULL,'12','FEP','2001-01-01',NULL,'11',00000000012,'12',NULL,'12','12','12','yes','12','12','12','12','12','12','54123465@qq.com',NULL,1,NULL),(6,'面试1',NULL,'12','FEP','2001-01-01','Eng','11',00000000012,'12','12','23','23','23','yes','23','23','23','23','23','23','54123465@qq.com','12',0,NULL),(7,'面试','Male','12','FEP','2001-01-01','Eng','11',00000000012,'12','12','23','23','23','yes','23','23','23','23','23','23','54123465@qq.com','12',0,NULL),(8,'张三','Male','201312121','FEP','1994-03-04','Eng','13',00000000014,'嵌入式','13985545744','20%','数据库','JAVA','yes','JAVA','考研','没有啊','没有啊','你猜','羽毛球','zhangsan@gslab.com','345784477',1,'2015-06-23'),(9,'李四','Female','201314074','FEP','2001-01-01','Net','13',00000000012,'物联网','18985547455','10%','物联网','ERP','no','号','号','号','号','咋这么多','号','lisi@gslab.com','312457845',0,'2015-06-23'),(10,'王五','Female','201401011','OME','1990-01-08','Net','14',00000000001,'网络安全','18840522112','20%','网络安全','密码学','no','没有','没有','没有','没有','没有','没有','wangwu@gslab.com','470741425',0,'2015-06-24'),(11,NULL,NULL,NULL,'VLAB',NULL,NULL,NULL,00000000000,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,'2015-06-28'),(12,NULL,NULL,NULL,'VLAB',NULL,NULL,NULL,00000000000,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,'2015-06-28'),(13,'关振宇','Male','201092096','FEP','1911-11-10','Eng','11',00000001008,'软件','111','1','1','1',NULL,'11','11','11','11','11','11','11@qq.com','1',0,'2015-07-04');
/*!40000 ALTER TABLE `t_user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2015-08-12 16:59:50
