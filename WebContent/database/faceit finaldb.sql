-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               5.6.27-log - MySQL Community Server (GPL)
-- Server OS:                    Win64
-- HeidiSQL Version:             9.2.0.4947
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

-- Dumping database structure for faceit
DROP DATABASE IF EXISTS `faceit`;
CREATE DATABASE IF NOT EXISTS `faceit` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `faceit`;


-- Dumping structure for table faceit.comments
DROP TABLE IF EXISTS `comments`;
CREATE TABLE IF NOT EXISTS `comments` (
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Data exporting was unselected.


-- Dumping structure for table faceit.connections
DROP TABLE IF EXISTS `connections`;
CREATE TABLE IF NOT EXISTS `connections` (
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

-- Data exporting was unselected.


-- Dumping structure for procedure faceit.getComments
DROP PROCEDURE IF EXISTS `getComments`;
DELIMITER //
CREATE DEFINER=`root`@`localhost` PROCEDURE `getComments`(in postId int)
BEGIN

SELECT comments.id AS commentid, comments.datecreated AS datecreated, comments.description AS description,  users_profile.id AS userid,
	CONCAT(users_profile.firstname, " ", COALESCE(users_profile.middlename,""), " ", users_profile.lastname) AS fullname
FROM comments
INNER JOIN users_profile
	ON comments.user_id = users_profile.id
WHERE comments.post_id = postId and
	comments.status = 'ACTIVE'
ORDER BY datecreated ASC;
END//
DELIMITER ;


-- Dumping structure for table faceit.history
DROP TABLE IF EXISTS `history`;
CREATE TABLE IF NOT EXISTS `history` (
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Data exporting was unselected.


-- Dumping structure for table faceit.likes_comments
DROP TABLE IF EXISTS `likes_comments`;
CREATE TABLE IF NOT EXISTS `likes_comments` (
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Data exporting was unselected.


-- Dumping structure for table faceit.likes_posts
DROP TABLE IF EXISTS `likes_posts`;
CREATE TABLE IF NOT EXISTS `likes_posts` (
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Data exporting was unselected.


-- Dumping structure for procedure faceit.newsfeedPosts
DROP PROCEDURE IF EXISTS `newsfeedPosts`;
DELIMITER //
CREATE DEFINER=`root`@`localhost` PROCEDURE `newsfeedPosts`(in userId int)
BEGIN

SELECT posts.id AS postid, posts.datecreated AS datecreated, posts.description AS description, users_profile.id AS userid,
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
END//
DELIMITER ;


-- Dumping structure for table faceit.posts
DROP TABLE IF EXISTS `posts`;
CREATE TABLE IF NOT EXISTS `posts` (
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Data exporting was unselected.


-- Dumping structure for table faceit.users
DROP TABLE IF EXISTS `users`;
CREATE TABLE IF NOT EXISTS `users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `status` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Data exporting was unselected.


-- Dumping structure for table faceit.users_profile
DROP TABLE IF EXISTS `users_profile`;
CREATE TABLE IF NOT EXISTS `users_profile` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `firstname` varchar(50) DEFAULT NULL,
  `middlename` varchar(50) DEFAULT NULL,
  `lastname` varchar(50) DEFAULT NULL,
  `gender` varchar(10) DEFAULT NULL,
  `birthdate` date DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `address` varchar(50) DEFAULT NULL,
  `mobile` varchar(20) DEFAULT NULL,
  `photo` varchar(100) DEFAULT NULL,
  `datecreated` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `user_id_idx` (`user_id`),
  CONSTRAINT `user_id` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Data exporting was unselected.
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
