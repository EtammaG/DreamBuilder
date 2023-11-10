-- MySQL dump 10.13  Distrib 8.0.34, for Linux (x86_64)
--
-- Host: localhost    Database: dream_builder
-- ------------------------------------------------------
-- Server version	8.0.34

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `chat`
--

DROP TABLE IF EXISTS `chat`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `chat` (
  `content` text COMMENT '消息内容',
  `from_id` bigint DEFAULT NULL COMMENT '发送方ID',
  `to_id` bigint DEFAULT NULL COMMENT '接收方ID',
  `type` tinyint DEFAULT NULL COMMENT '0表示volunteer->kid，1表示kid->volunteer',
  `time` datetime DEFAULT NULL COMMENT '发送时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='聊天记录暂存';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `chat`
--

LOCK TABLES `chat` WRITE;
/*!40000 ALTER TABLE `chat` DISABLE KEYS */;
INSERT INTO `chat` VALUES ('1',1,1,0,'2023-10-10 15:24:11'),('2',1,1,0,'2023-11-17 15:43:16'),('3',1,1,0,'2023-12-10 15:57:04');
/*!40000 ALTER TABLE `chat` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `donor`
--

DROP TABLE IF EXISTS `donor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `donor` (
  `id` bigint NOT NULL COMMENT '唯一ID',
  `nickname` varchar(50) DEFAULT NULL COMMENT '昵称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='捐赠者';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `donor`
--

LOCK TABLES `donor` WRITE;
/*!40000 ALTER TABLE `donor` DISABLE KEYS */;
INSERT INTO `donor` VALUES (1,'A'),(2,'B'),(3,'C');
/*!40000 ALTER TABLE `donor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `donor_kid_donation`
--

DROP TABLE IF EXISTS `donor_kid_donation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `donor_kid_donation` (
  `donor_id` bigint DEFAULT NULL COMMENT '捐赠者ID',
  `kid_id` bigint DEFAULT NULL COMMENT '孩子ID',
  `amount` int DEFAULT NULL COMMENT '额度',
  `time` datetime DEFAULT NULL COMMENT '时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='捐赠者捐助孩子善款记录';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `donor_kid_donation`
--

LOCK TABLES `donor_kid_donation` WRITE;
/*!40000 ALTER TABLE `donor_kid_donation` DISABLE KEYS */;
INSERT INTO `donor_kid_donation` VALUES (1,1,1,'2023-11-06 22:23:53'),(1,2,4,'2023-11-06 22:24:02'),(2,3,3,'2023-11-06 22:24:10'),(2,123,5,'2023-11-06 22:24:16'),(1,432,100,'2023-10-16 18:24:06'),(3,1,44,'2023-11-09 22:46:18'),(3,432,NULL,'2023-10-16 22:46:39');
/*!40000 ALTER TABLE `donor_kid_donation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `donor_kid_thing`
--

DROP TABLE IF EXISTS `donor_kid_thing`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `donor_kid_thing` (
  `donor_id` bigint DEFAULT NULL COMMENT '捐赠者ID',
  `kid_id` bigint DEFAULT NULL COMMENT '孩子ID',
  `thing_name` varchar(45) DEFAULT NULL COMMENT '物品名称',
  `time` datetime DEFAULT NULL COMMENT '捐赠时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='捐赠者捐助孩子物品记录';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `donor_kid_thing`
--

LOCK TABLES `donor_kid_thing` WRITE;
/*!40000 ALTER TABLE `donor_kid_thing` DISABLE KEYS */;
INSERT INTO `donor_kid_thing` VALUES (1,123,'1-123','2023-11-09 19:16:18'),(1,432,'1-432','2023-11-09 19:16:28'),(2,1,'2-1','2023-11-09 19:16:40'),(1,2,'1-2','2023-11-09 19:17:56'),(3,432,'3-432','2023-11-09 22:47:21'),(3,1,'3-1','2023-11-09 22:47:33');
/*!40000 ALTER TABLE `donor_kid_thing` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `donor_project`
--

DROP TABLE IF EXISTS `donor_project`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `donor_project` (
  `id` bigint NOT NULL COMMENT '项目ID',
  `name` varchar(45) DEFAULT NULL COMMENT '项目名称',
  `description` varchar(500) DEFAULT NULL COMMENT '项目简介',
  `detail` text COMMENT '详细信息',
  `pic` varchar(500) DEFAULT NULL COMMENT '项目照片地址',
  `location` varchar(50) DEFAULT NULL COMMENT '项目实施地',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='爱心项目';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `donor_project`
--

LOCK TABLES `donor_project` WRITE;
/*!40000 ALTER TABLE `donor_project` DISABLE KEYS */;
INSERT INTO `donor_project` VALUES (1,'A','AA','AAA','pic.png','辽宁沈阳'),(2,'B','BB','BBB','pic.png','辽宁沈阳'),(3,'V','VV','VVV','pic.png','辽宁沈阳'),(4,'C','CC','CCC','pic.png','辽宁沈阳');
/*!40000 ALTER TABLE `donor_project` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `donor_project_donation`
--

DROP TABLE IF EXISTS `donor_project_donation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `donor_project_donation` (
  `project_id` bigint DEFAULT NULL COMMENT '项目ID',
  `donor_id` bigint DEFAULT NULL COMMENT '捐助者ID',
  `amount` int DEFAULT NULL COMMENT '捐助项目的金额',
  `time` datetime DEFAULT NULL COMMENT '捐助的时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='捐赠者捐助项目善款记录';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `donor_project_donation`
--

LOCK TABLES `donor_project_donation` WRITE;
/*!40000 ALTER TABLE `donor_project_donation` DISABLE KEYS */;
INSERT INTO `donor_project_donation` VALUES (1,1,200,'2023-11-06 22:37:19'),(2,2,300,'2023-11-06 22:37:27'),(3,1,400,'2023-11-09 19:19:03'),(4,1,1,'2023-11-09 19:22:57');
/*!40000 ALTER TABLE `donor_project_donation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `donor_project_to_type`
--

DROP TABLE IF EXISTS `donor_project_to_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `donor_project_to_type` (
  `project_id` bigint DEFAULT NULL COMMENT '项目ID',
  `type_id` bigint DEFAULT NULL COMMENT '类别ID'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='项目和类别关系';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `donor_project_to_type`
--

LOCK TABLES `donor_project_to_type` WRITE;
/*!40000 ALTER TABLE `donor_project_to_type` DISABLE KEYS */;
INSERT INTO `donor_project_to_type` VALUES (1,1),(2,2),(3,1),(4,1);
/*!40000 ALTER TABLE `donor_project_to_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `donor_project_type`
--

DROP TABLE IF EXISTS `donor_project_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `donor_project_type` (
  `id` bigint NOT NULL,
  `name` varchar(50) DEFAULT NULL COMMENT '名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='项目类别';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `donor_project_type`
--

LOCK TABLES `donor_project_type` WRITE;
/*!40000 ALTER TABLE `donor_project_type` DISABLE KEYS */;
INSERT INTO `donor_project_type` VALUES (1,'Q'),(2,'W');
/*!40000 ALTER TABLE `donor_project_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `kid`
--

DROP TABLE IF EXISTS `kid`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `kid` (
  `id` bigint NOT NULL COMMENT '唯一ID',
  `name` varchar(50) DEFAULT NULL COMMENT '姓名',
  `age` int DEFAULT NULL COMMENT '年龄',
  `photo` varchar(500) DEFAULT NULL COMMENT '孩子照片路径',
  `school_id` bigint DEFAULT NULL COMMENT '学校ID',
  `grade` int DEFAULT NULL COMMENT '年级',
  `address` varchar(300) DEFAULT NULL COMMENT '住址',
  `description` varchar(500) DEFAULT NULL COMMENT '简介',
  `detail` text COMMENT '详细信息',
  `total_point` int DEFAULT NULL COMMENT '总点数（学习点数）',
  `mall_point` int DEFAULT NULL COMMENT '商城点数',
  `point_week_ago` int DEFAULT NULL COMMENT '上周的total_point',
  `rank_week_ago` int DEFAULT NULL COMMENT '上周的total_point排行',
  `weekrank_week_ago` int DEFAULT NULL COMMENT '上周的周排名',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='被捐助的孩子';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `kid`
--

LOCK TABLES `kid` WRITE;
/*!40000 ALTER TABLE `kid` DISABLE KEYS */;
INSERT INTO `kid` VALUES (1,'A',11,'assets/zuijinjuanzhu.png',1,1,'沈阳','11111111111111','阿斯顿发生',1,1,1,1,1),(2,'B',22,'assets/zuijinjuanzhu.png',1,2,'shen','22222222222222222222222222','认为人发送',1,1,1,1,1),(3,'C',33,'assets/zuijinjuanzhu.png',1,3,'l','3333333333333333333','发士大夫',1,1,1,1,1),(123,'123A',123123,'assets/zuijinjuanzhu.png',1,4,'liao','5423523','的方式发',12,1,1,1,1),(432,'432B',1,'assets/zuijinjuanzhu.png',1,5,'辽宁','87686','发生的',1,1,1,1,1);
/*!40000 ALTER TABLE `kid` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `kid_award`
--

DROP TABLE IF EXISTS `kid_award`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `kid_award` (
  `id` bigint NOT NULL COMMENT '唯一ID',
  `name` varchar(30) DEFAULT NULL COMMENT '名称',
  `description` varchar(100) DEFAULT NULL COMMENT '描述',
  `pic` varchar(100) DEFAULT NULL,
  `point` int DEFAULT NULL COMMENT '需要点数',
  `stock` int DEFAULT NULL COMMENT '库存',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='奖品（供孩子兑换）';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `kid_award`
--

LOCK TABLES `kid_award` WRITE;
/*!40000 ALTER TABLE `kid_award` DISABLE KEYS */;
INSERT INTO `kid_award` VALUES (1,'AA','AAAA','pic.png',10,100),(2,'BB','BBBB','pic.png',20,200),(3,'CC','CCCC','pic.png',5,50),(4,'DD','DDDD','pic.png',15,75);
/*!40000 ALTER TABLE `kid_award` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `kid_award_exchange`
--

DROP TABLE IF EXISTS `kid_award_exchange`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `kid_award_exchange` (
  `award_id` bigint DEFAULT NULL COMMENT '奖品ID',
  `kid_id` bigint DEFAULT NULL COMMENT '孩子ID',
  `address` varchar(200) DEFAULT NULL COMMENT '收货地址',
  `time` datetime DEFAULT NULL COMMENT '兑换时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='奖品的兑换记录';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `kid_award_exchange`
--

LOCK TABLES `kid_award_exchange` WRITE;
/*!40000 ALTER TABLE `kid_award_exchange` DISABLE KEYS */;
INSERT INTO `kid_award_exchange` VALUES (1,1,'1','2023-11-09 23:22:41');
/*!40000 ALTER TABLE `kid_award_exchange` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `kid_award_like`
--

DROP TABLE IF EXISTS `kid_award_like`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `kid_award_like` (
  `award_id` bigint DEFAULT NULL COMMENT '奖品ID',
  `kid_id` bigint DEFAULT NULL COMMENT '点赞孩子的ID'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='奖品的点赞';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `kid_award_like`
--

LOCK TABLES `kid_award_like` WRITE;
/*!40000 ALTER TABLE `kid_award_like` DISABLE KEYS */;
INSERT INTO `kid_award_like` VALUES (1,1),(2,1);
/*!40000 ALTER TABLE `kid_award_like` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `kid_award_to_type`
--

DROP TABLE IF EXISTS `kid_award_to_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `kid_award_to_type` (
  `award_id` bigint DEFAULT NULL COMMENT '奖品ID',
  `type_id` bigint DEFAULT NULL COMMENT '类别ID'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='奖品和奖品类别关系';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `kid_award_to_type`
--

LOCK TABLES `kid_award_to_type` WRITE;
/*!40000 ALTER TABLE `kid_award_to_type` DISABLE KEYS */;
INSERT INTO `kid_award_to_type` VALUES (1,2),(2,1),(3,1),(4,1);
/*!40000 ALTER TABLE `kid_award_to_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `kid_award_type`
--

DROP TABLE IF EXISTS `kid_award_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `kid_award_type` (
  `id` bigint NOT NULL COMMENT '唯一ID',
  `name` varchar(50) DEFAULT NULL COMMENT '名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='奖品的类型';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `kid_award_type`
--

LOCK TABLES `kid_award_type` WRITE;
/*!40000 ALTER TABLE `kid_award_type` DISABLE KEYS */;
INSERT INTO `kid_award_type` VALUES (1,'Z'),(2,'X'),(3,'C'),(4,'V'),(5,'B');
/*!40000 ALTER TABLE `kid_award_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `kid_mission`
--

DROP TABLE IF EXISTS `kid_mission`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `kid_mission` (
  `id` bigint NOT NULL COMMENT '唯一ID',
  `tag` varchar(20) DEFAULT NULL COMMENT '标签',
  `title` varchar(30) DEFAULT NULL COMMENT '任务题目',
  `description` varchar(500) DEFAULT NULL COMMENT '任务描述',
  `type` tinyint DEFAULT NULL COMMENT '0表示选做，1表示必做',
  `level` varchar(20) DEFAULT NULL COMMENT '任务的难度',
  `point` int DEFAULT NULL COMMENT '题目的点数',
  `date` date DEFAULT NULL COMMENT '日期',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='孩子的任务';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `kid_mission`
--

LOCK TABLES `kid_mission` WRITE;
/*!40000 ALTER TABLE `kid_mission` DISABLE KEYS */;
INSERT INTO `kid_mission` VALUES (1,'背书','背诵红楼梦','花一天时间背诵红楼梦',0,'A',10,'2023-11-09'),(2,'背书','背诵红楼梦','花一天时间背诵红楼梦',1,'B',20,'2023-11-08'),(3,'背书','背诵红楼梦','花一天时间背诵红楼梦',0,'C',30,'2023-11-08'),(4,'运动','跳绳','一分钟时间跳三次绳',1,'C',10,'2023-11-09'),(5,'运动','跳绳','一分钟时间跳三次绳',0,'B',10,'2023-11-09'),(6,'运动','跳绳','一分钟时间跳三次绳',1,'A',10,'2023-11-09');
/*!40000 ALTER TABLE `kid_mission` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `kid_reply`
--

DROP TABLE IF EXISTS `kid_reply`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `kid_reply` (
  `id` bigint NOT NULL COMMENT '唯一ID',
  `media` varchar(500) DEFAULT NULL COMMENT '提交媒体文件的路径',
  `score` int DEFAULT NULL COMMENT '对提交的评分',
  `comment` varchar(500) DEFAULT NULL COMMENT '对提交的评语',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='对任务的提交';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `kid_reply`
--

LOCK TABLES `kid_reply` WRITE;
/*!40000 ALTER TABLE `kid_reply` DISABLE KEYS */;
INSERT INTO `kid_reply` VALUES (1,'reply.png',100,'A'),(2,'reply.png',33,'B'),(3,'reply.png',100,'C'),(4,'reply.png',NULL,''),(5,'reply.png',88,'A'),(6,'reply.png',9,'C'),(11,'reply.png',88,'Z'),(33,'reply.png',NULL,''),(55,'reply.png',44,'Z');
/*!40000 ALTER TABLE `kid_reply` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `kid_reply_hot`
--

DROP TABLE IF EXISTS `kid_reply_hot`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `kid_reply_hot` (
  `id` bigint NOT NULL COMMENT '唯一ID',
  `reply_id` bigint DEFAULT NULL COMMENT '提交成果ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='热门提交成果';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `kid_reply_hot`
--

LOCK TABLES `kid_reply_hot` WRITE;
/*!40000 ALTER TABLE `kid_reply_hot` DISABLE KEYS */;
INSERT INTO `kid_reply_hot` VALUES (1,11),(2,1),(3,2),(4,3),(5,4),(6,5);
/*!40000 ALTER TABLE `kid_reply_hot` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `kid_reply_hot_comment`
--

DROP TABLE IF EXISTS `kid_reply_hot_comment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `kid_reply_hot_comment` (
  `hot_id` bigint DEFAULT NULL COMMENT '提交成果的热门ID',
  `kid_id` bigint DEFAULT NULL COMMENT '发布评论孩子的ID',
  `content` varchar(500) DEFAULT NULL COMMENT '评论的内容',
  `time` datetime DEFAULT NULL COMMENT '评论时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='热门提交成果的评论';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `kid_reply_hot_comment`
--

LOCK TABLES `kid_reply_hot_comment` WRITE;
/*!40000 ALTER TABLE `kid_reply_hot_comment` DISABLE KEYS */;
INSERT INTO `kid_reply_hot_comment` VALUES (1,1,'你好棒','2023-11-09 23:34:50'),(1,3,'你真棒','2023-11-09 23:35:10'),(2,3,'6','2023-11-09 23:35:36'),(2,3,'6','2023-11-09 23:35:42'),(4,123,'7','2023-11-09 23:35:56'),(5,432,'9','2023-11-09 23:36:02'),(1,1,'666','2023-11-09 23:49:30');
/*!40000 ALTER TABLE `kid_reply_hot_comment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `kid_reply_hot_like`
--

DROP TABLE IF EXISTS `kid_reply_hot_like`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `kid_reply_hot_like` (
  `hot_id` bigint DEFAULT NULL COMMENT '热门提交成果的热门ID',
  `kid_id` bigint DEFAULT NULL COMMENT '点赞孩子的ID'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='热门提交成果的点赞';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `kid_reply_hot_like`
--

LOCK TABLES `kid_reply_hot_like` WRITE;
/*!40000 ALTER TABLE `kid_reply_hot_like` DISABLE KEYS */;
INSERT INTO `kid_reply_hot_like` VALUES (1,1),(1,3),(1,123),(2,1),(2,123),(3,1),(5,1);
/*!40000 ALTER TABLE `kid_reply_hot_like` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `kid_school`
--

DROP TABLE IF EXISTS `kid_school`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `kid_school` (
  `id` bigint NOT NULL COMMENT '唯一ID',
  `name` varchar(50) DEFAULT NULL COMMENT '学校名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='孩子的学校信息';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `kid_school`
--

LOCK TABLES `kid_school` WRITE;
/*!40000 ALTER TABLE `kid_school` DISABLE KEYS */;
INSERT INTO `kid_school` VALUES (1,'希望小学');
/*!40000 ALTER TABLE `kid_school` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `kid_to_mission`
--

DROP TABLE IF EXISTS `kid_to_mission`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `kid_to_mission` (
  `kid_id` bigint DEFAULT NULL COMMENT '孩子ID',
  `mission_id` bigint DEFAULT NULL COMMENT '任务ID',
  `reply_id` bigint DEFAULT NULL COMMENT '提交ID，为空说明未提交'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='孩子、任务和提交关系';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `kid_to_mission`
--

LOCK TABLES `kid_to_mission` WRITE;
/*!40000 ALTER TABLE `kid_to_mission` DISABLE KEYS */;
INSERT INTO `kid_to_mission` VALUES (1,1,11),(1,2,NULL),(1,3,33),(1,4,NULL),(1,5,55),(2,1,1),(2,2,2),(2,3,3),(2,4,4),(2,5,5),(2,6,6);
/*!40000 ALTER TABLE `kid_to_mission` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `kid_to_type`
--

DROP TABLE IF EXISTS `kid_to_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `kid_to_type` (
  `kid_id` bigint DEFAULT NULL COMMENT '唯一ID',
  `type_id` bigint DEFAULT NULL COMMENT '类别ID'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='孩子和类别关系';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `kid_to_type`
--

LOCK TABLES `kid_to_type` WRITE;
/*!40000 ALTER TABLE `kid_to_type` DISABLE KEYS */;
INSERT INTO `kid_to_type` VALUES (123,1),(432,1),(1,2),(2,2),(3,2);
/*!40000 ALTER TABLE `kid_to_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `kid_type`
--

DROP TABLE IF EXISTS `kid_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `kid_type` (
  `id` bigint NOT NULL COMMENT '唯一ID',
  `name` varchar(30) DEFAULT NULL COMMENT '名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='孩子类别';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `kid_type`
--

LOCK TABLES `kid_type` WRITE;
/*!40000 ALTER TABLE `kid_type` DISABLE KEYS */;
INSERT INTO `kid_type` VALUES (1,'AA'),(2,'BB');
/*!40000 ALTER TABLE `kid_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_donor`
--

DROP TABLE IF EXISTS `user_donor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_donor` (
  `donor_id` bigint DEFAULT NULL,
  `username` varchar(64) DEFAULT NULL,
  `password` varchar(64) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='捐赠者用户';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_donor`
--

LOCK TABLES `user_donor` WRITE;
/*!40000 ALTER TABLE `user_donor` DISABLE KEYS */;
INSERT INTO `user_donor` VALUES (1,'dddddd','$2a$10$rt3xG43aj7nrHo9cqFhU2e1lIeBMJdZT3jocGT6.5mTYSBfYorLWC');
/*!40000 ALTER TABLE `user_donor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_kid`
--

DROP TABLE IF EXISTS `user_kid`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_kid` (
  `kid_id` bigint DEFAULT NULL,
  `username` varchar(64) DEFAULT NULL,
  `password` varchar(64) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='孩子用户';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_kid`
--

LOCK TABLES `user_kid` WRITE;
/*!40000 ALTER TABLE `user_kid` DISABLE KEYS */;
INSERT INTO `user_kid` VALUES (1,'kkkkkk','$2a$10$YswWv.C8pfK0KST8m5Sb4OfD.gVDwnLiiDepTivsahA.FgmKtLYX.');
/*!40000 ALTER TABLE `user_kid` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_volunteer`
--

DROP TABLE IF EXISTS `user_volunteer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_volunteer` (
  `volunteer_id` bigint DEFAULT NULL,
  `username` varchar(64) DEFAULT NULL,
  `password` varchar(64) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='志愿者用户';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_volunteer`
--

LOCK TABLES `user_volunteer` WRITE;
/*!40000 ALTER TABLE `user_volunteer` DISABLE KEYS */;
INSERT INTO `user_volunteer` VALUES (1,'vvvvvv','$2a$10$LPCkNME6a15PQXOq.unS8eXbf3JyGDJ3A9mWoPaXDUShO9j8A2cpi');
/*!40000 ALTER TABLE `user_volunteer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `volun_article`
--

DROP TABLE IF EXISTS `volun_article`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `volun_article` (
  `id` bigint NOT NULL COMMENT '唯一ID',
  `author_name` varchar(50) DEFAULT NULL COMMENT '作者名称',
  `content_pic` varchar(500) DEFAULT NULL COMMENT '内容图片地址',
  `author_pic` varchar(500) DEFAULT NULL COMMENT '作者图片地址',
  `title` varchar(100) DEFAULT NULL COMMENT '标题',
  `content` text COMMENT '内容',
  `article_time` datetime DEFAULT NULL COMMENT '发布时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='志愿者社区文章';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `volun_article`
--

LOCK TABLES `volun_article` WRITE;
/*!40000 ALTER TABLE `volun_article` DISABLE KEYS */;
/*!40000 ALTER TABLE `volun_article` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `volun_article_comment`
--

DROP TABLE IF EXISTS `volun_article_comment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `volun_article_comment` (
  `article_id` bigint DEFAULT NULL COMMENT '文章ID',
  `volun_id` bigint DEFAULT NULL COMMENT '评论发布者ID',
  `content` text COMMENT '评论内容',
  `time` datetime DEFAULT NULL COMMENT '评论时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='文章的评论';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `volun_article_comment`
--

LOCK TABLES `volun_article_comment` WRITE;
/*!40000 ALTER TABLE `volun_article_comment` DISABLE KEYS */;
/*!40000 ALTER TABLE `volun_article_comment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `volun_article_like`
--

DROP TABLE IF EXISTS `volun_article_like`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `volun_article_like` (
  `volun_id` bigint NOT NULL COMMENT '收藏者ID',
  `article_id` bigint DEFAULT NULL COMMENT '文章ID'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='志愿者收藏的文章';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `volun_article_like`
--

LOCK TABLES `volun_article_like` WRITE;
/*!40000 ALTER TABLE `volun_article_like` DISABLE KEYS */;
/*!40000 ALTER TABLE `volun_article_like` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `volun_article_love`
--

DROP TABLE IF EXISTS `volun_article_love`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `volun_article_love` (
  `volun_id` bigint DEFAULT NULL COMMENT '志愿者id',
  `article_id` bigint DEFAULT NULL COMMENT '文章id',
  `love_id` bigint DEFAULT NULL COMMENT '点赞id'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='文章点赞';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `volun_article_love`
--

LOCK TABLES `volun_article_love` WRITE;
/*!40000 ALTER TABLE `volun_article_love` DISABLE KEYS */;
/*!40000 ALTER TABLE `volun_article_love` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `volun_to_kid`
--

DROP TABLE IF EXISTS `volun_to_kid`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `volun_to_kid` (
  `volun_id` bigint DEFAULT NULL COMMENT '志愿者ID',
  `kid_id` bigint DEFAULT NULL COMMENT '孩子ID'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='志愿者、孩子的对应关系';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `volun_to_kid`
--

LOCK TABLES `volun_to_kid` WRITE;
/*!40000 ALTER TABLE `volun_to_kid` DISABLE KEYS */;
/*!40000 ALTER TABLE `volun_to_kid` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `volun_to_mission`
--

DROP TABLE IF EXISTS `volun_to_mission`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `volun_to_mission` (
  `volun_id` bigint DEFAULT NULL COMMENT '志愿者ID',
  `mission_id` bigint DEFAULT NULL COMMENT '任务ID'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='志愿者与任务关系';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `volun_to_mission`
--

LOCK TABLES `volun_to_mission` WRITE;
/*!40000 ALTER TABLE `volun_to_mission` DISABLE KEYS */;
/*!40000 ALTER TABLE `volun_to_mission` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `volunteer`
--

DROP TABLE IF EXISTS `volunteer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `volunteer` (
  `id` bigint NOT NULL COMMENT '唯一ID',
  `name` varchar(30) DEFAULT NULL COMMENT '姓名',
  `gender` tinyint DEFAULT NULL COMMENT '0表示女性，1表示男性',
  `age` int DEFAULT NULL COMMENT '年龄',
  `birthday` date DEFAULT NULL COMMENT '生日',
  `area` varchar(200) DEFAULT NULL COMMENT '志愿者当前所在地区',
  `job` varchar(50) DEFAULT NULL COMMENT '志愿者职业',
  `hometown` varchar(200) DEFAULT NULL COMMENT '志愿者家乡',
  `photo` varchar(500) DEFAULT NULL COMMENT '照片地址',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='志愿者';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `volunteer`
--

LOCK TABLES `volunteer` WRITE;
/*!40000 ALTER TABLE `volunteer` DISABLE KEYS */;
INSERT INTO `volunteer` VALUES (1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `volunteer` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-11-10 16:10:43
