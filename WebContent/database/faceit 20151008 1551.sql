-- MySQL Administrator dump 1.4
--
-- ------------------------------------------------------
-- Server version	5.1.73-community


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


--
-- Create schema faceit
--

CREATE DATABASE IF NOT EXISTS faceit;
USE faceit;

--
-- Definition of table `comments`
--

DROP TABLE IF EXISTS `comments`;
CREATE TABLE `comments` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `post_id` int(11) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  `description` longtext,
  `datecreated` datetime DEFAULT NULL,
  `status` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `comment_postid_idx` (`post_id`),
  KEY `comment_userid_idx` (`user_id`),
  CONSTRAINT `comment_postid` FOREIGN KEY (`post_id`) REFERENCES `posts` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `comment_userid` FOREIGN KEY (`user_id`) REFERENCES `users_profile` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `comments`
--

/*!40000 ALTER TABLE `comments` DISABLE KEYS */;
/*!40000 ALTER TABLE `comments` ENABLE KEYS */;


--
-- Definition of table `connections`
--

DROP TABLE IF EXISTS `connections`;
CREATE TABLE `connections` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id_one` int(11) DEFAULT NULL,
  `user_id_two` int(11) DEFAULT NULL,
  `datecreated` datetime DEFAULT NULL,
  `status` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `connections_userid_one_idx` (`user_id_one`),
  KEY `connections_userid_two_idx` (`user_id_two`),
  CONSTRAINT `connections_userid_one` FOREIGN KEY (`user_id_one`) REFERENCES `users_profile` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `connections_userid_two` FOREIGN KEY (`user_id_two`) REFERENCES `users_profile` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `connections`
--

/*!40000 ALTER TABLE `connections` DISABLE KEYS */;
/*!40000 ALTER TABLE `connections` ENABLE KEYS */;


--
-- Definition of table `history`
--

DROP TABLE IF EXISTS `history`;
CREATE TABLE `history` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `message` varchar(200) DEFAULT NULL,
  `datecreated` datetime DEFAULT NULL,
  `modifiedby` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `history_userid_idx` (`user_id`),
  KEY `history_modifiedby_idx` (`modifiedby`),
  CONSTRAINT `history_modifiedby` FOREIGN KEY (`modifiedby`) REFERENCES `users_profile` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `history_userid` FOREIGN KEY (`user_id`) REFERENCES `users_profile` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `history`
--

/*!40000 ALTER TABLE `history` DISABLE KEYS */;
/*!40000 ALTER TABLE `history` ENABLE KEYS */;


--
-- Definition of table `likes`
--

DROP TABLE IF EXISTS `likes`;
CREATE TABLE `likes` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `post_id` int(11) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  `datecreated` datetime DEFAULT NULL,
  `status` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `like_postid_idx` (`post_id`),
  KEY `like_userid_idx` (`user_id`),
  CONSTRAINT `like_postid` FOREIGN KEY (`post_id`) REFERENCES `posts` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `like_userid` FOREIGN KEY (`user_id`) REFERENCES `users_profile` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `likes`
--

/*!40000 ALTER TABLE `likes` DISABLE KEYS */;
/*!40000 ALTER TABLE `likes` ENABLE KEYS */;


--
-- Definition of table `posts`
--

DROP TABLE IF EXISTS `posts`;
CREATE TABLE `posts` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id_to` int(11) DEFAULT NULL,
  `user_id_from` int(11) DEFAULT NULL,
  `description` longtext,
  `datecreated` datetime DEFAULT NULL,
  `privacy` varchar(10) DEFAULT NULL,
  `status` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `post_userid_to_idx` (`user_id_to`,`user_id_from`),
  KEY `post_userid_from_idx` (`user_id_from`),
  CONSTRAINT `post_userid_from` FOREIGN KEY (`user_id_from`) REFERENCES `users_profile` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `post_userid_to` FOREIGN KEY (`user_id_to`) REFERENCES `users_profile` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `posts`
--

/*!40000 ALTER TABLE `posts` DISABLE KEYS */;
/*!40000 ALTER TABLE `posts` ENABLE KEYS */;


--
-- Definition of table `users`
--

DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) DEFAULT NULL,
  `password` varchar(225) DEFAULT NULL,
  `status` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `users`
--

/*!40000 ALTER TABLE `users` DISABLE KEYS */;
/*!40000 ALTER TABLE `users` ENABLE KEYS */;


--
-- Definition of table `users_profile`
--

DROP TABLE IF EXISTS `users_profile`;
CREATE TABLE `users_profile` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `firstname` varchar(50) DEFAULT NULL,
  `middlename` varchar(50) DEFAULT NULL,
  `lastname` varchar(50) DEFAULT NULL,
  `birthdate` date DEFAULT NULL,
  `address` varchar(300) DEFAULT NULL,
  `mobile` varchar(20) DEFAULT NULL,
  `photo` varchar(100) DEFAULT NULL,
  `datecreated` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `email` varchar(50) DEFAULT NULL,
  `gender` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `user_id_idx` (`user_id`),
  CONSTRAINT `user_id` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `users_profile`
--

/*!40000 ALTER TABLE `users_profile` DISABLE KEYS */;
/*!40000 ALTER TABLE `users_profile` ENABLE KEYS */;




/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
