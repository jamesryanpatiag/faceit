-- MySQL dump 10.13  Distrib 5.6.17, for Win32 (x86)
--
-- Host: localhost    Database: faceit
-- ------------------------------------------------------
-- Server version	5.6.22-log

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
-- Table structure for table `comments`
--

DROP TABLE IF EXISTS `comments`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `comments` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `post_id` int(11) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  `description` longtext,
  `datecreated` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `status` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `comment_postid_idx` (`post_id`),
  KEY `comment_userid_idx` (`user_id`),
  CONSTRAINT `comment_postid` FOREIGN KEY (`post_id`) REFERENCES `posts` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `comment_userid` FOREIGN KEY (`user_id`) REFERENCES `users_profile` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;


--
-- Table structure for table `connections`
--

DROP TABLE IF EXISTS `connections`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `connections` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id_one` int(11) DEFAULT NULL,
  `user_id_two` int(11) DEFAULT NULL,
  `datecreated` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `status` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `connections_userid_one_idx` (`user_id_one`),
  KEY `connections_userid_two_idx` (`user_id_two`),
  CONSTRAINT `connections_userid_one` FOREIGN KEY (`user_id_one`) REFERENCES `users_profile` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `connections_userid_two` FOREIGN KEY (`user_id_two`) REFERENCES `users_profile` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;


--
-- Table structure for table `history`
--

DROP TABLE IF EXISTS `history`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `history` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `message` varchar(200) DEFAULT NULL,
  `datecreated` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `modifiedby` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `history_userid_idx` (`user_id`),
  KEY `history_modifiedby_idx` (`modifiedby`),
  CONSTRAINT `history_modifiedby` FOREIGN KEY (`modifiedby`) REFERENCES `users_profile` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `history_userid` FOREIGN KEY (`user_id`) REFERENCES `users_profile` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;


--
-- Table structure for table `likes_comments`
--

DROP TABLE IF EXISTS `likes_comments`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `likes_comments` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `comment_id` int(11) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  `datecreated` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `status` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `like_comments_commentid_idx` (`comment_id`),
  KEY `like_comments_userid_idx` (`user_id`),
  CONSTRAINT `like_comments_commentid` FOREIGN KEY (`comment_id`) REFERENCES `comments` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `like_comments_userid` FOREIGN KEY (`user_id`) REFERENCES `users_profile` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `likes_posts`
--

DROP TABLE IF EXISTS `likes_posts`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `likes_posts` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `post_id` int(11) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  `datecreated` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `status` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `like_postid_idx` (`post_id`),
  KEY `like_userid_idx` (`user_id`),
  CONSTRAINT `like_posts_postid` FOREIGN KEY (`post_id`) REFERENCES `posts` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `like_posts_userid` FOREIGN KEY (`user_id`) REFERENCES `users_profile` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `posts`
--

DROP TABLE IF EXISTS `posts`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `posts` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id_to` int(11) DEFAULT NULL,
  `user_id_from` int(11) DEFAULT NULL,
  `description` longtext,
  `datecreated` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `privacy` varchar(10) DEFAULT NULL,
  `status` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `post_userid_to_idx` (`user_id_to`,`user_id_from`),
  KEY `post_userid_from_idx` (`user_id_from`),
  CONSTRAINT `post_userid_from` FOREIGN KEY (`user_id_from`) REFERENCES `users_profile` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `post_userid_to` FOREIGN KEY (`user_id_to`) REFERENCES `users_profile` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;


--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `status` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;


--
-- Table structure for table `users_profile`
--

DROP TABLE IF EXISTS `users_profile`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users_profile` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `firstname` varchar(50) DEFAULT NULL,
  `middlename` varchar(50) DEFAULT NULL,
  `lastname` varchar(50) DEFAULT NULL,
  `gender` varchar(10) DEFAULT NULL,
  `birthdate` date DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `address` varchar(300) DEFAULT NULL,
  `mobile` varchar(20) DEFAULT NULL,
  `photo` varchar(100) DEFAULT NULL,
  `datecreated` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `user_id_idx` (`user_id`),
  CONSTRAINT `user_id` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;


--
-- Dumping routines for database 'faceit'
--
/*!50003 DROP PROCEDURE IF EXISTS `getComments` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `getComments`(in postId int)
BEGIN

SELECT comments.id AS commentid, comments.datecreated AS datecreated, comments.description AS description,
	CONCAT(users_profile.firstname, " ", COALESCE(users_profile.middlename,""), " ", users_profile.lastname) AS fullname
FROM comments
INNER JOIN users_profile
	ON comments.user_id = users_profile.id
WHERE comments.post_id = postId and
	comments.status = 'ACTIVE'
ORDER BY datecreated ASC;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `newsfeedPosts` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `newsfeedPosts`(in userId int)
BEGIN

SELECT posts.id AS postid, posts.datecreated AS datecreated, posts.description AS description,
	CONCAT(users_profile.firstname, " ", COALESCE(users_profile.middlename,""), " ", users_profile.lastname) AS fullname
FROM posts
INNER JOIN users_profile
	ON posts.user_id_to = users_profile.id
WHERE (users_profile.id = userId OR 
		(users_profile.id IN (SELECT user_id_one FROM connections WHERE user_id_two = userId AND status='ACTIVE') OR
		users_profile.id IN (SELECT user_id_two FROM connections WHERE user_id_one = userId AND status='ACTIVE')
        )
    ) AND
	posts.status = 'ACTIVE'
ORDER BY datecreated DESC;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2015-10-14  9:23:13
