-- --------------------------------------------------------
-- Servidor:                     127.0.0.1
-- Versão do servidor:           5.6.31-0ubuntu0.15.10.1 - (Ubuntu)
-- OS do Servidor:               debian-linux-gnu
-- HeidiSQL Versão:              9.3.0.4984
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

-- Copiando estrutura do banco de dados para communities-dataset
CREATE DATABASE IF NOT EXISTS `communities-dataset` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `communities-dataset`;


-- Copiando estrutura para tabela communities-dataset.comment
CREATE TABLE IF NOT EXISTS `comment` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_community` int(11) NOT NULL,
  `id_comment_comm` int(11) NOT NULL,
  `id_post_comm` int(11) NOT NULL,
  `score` int(11) NOT NULL,
  `text` text NOT NULL,
  `creation_date` datetime NOT NULL,
  `id_user_comm` int(11) DEFAULT NULL,
  `id_user` int(11) DEFAULT NULL,
  `id_post` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_community_id_comment_comm` (`id_community`,`id_comment_comm`),
  KEY `FK_comment_user` (`id_user`),
  KEY `FK_comment_post` (`id_post`),
  CONSTRAINT `FK_comment_community` FOREIGN KEY (`id_community`) REFERENCES `community` (`id`),
  CONSTRAINT `FK_comment_post` FOREIGN KEY (`id_post`) REFERENCES `post` (`id`),
  CONSTRAINT `FK_comment_user` FOREIGN KEY (`id_user`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Exportação de dados foi desmarcado.


-- Copiando estrutura para tabela communities-dataset.community
CREATE TABLE IF NOT EXISTS `community` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(150) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Exportação de dados foi desmarcado.


-- Copiando estrutura para tabela communities-dataset.post
CREATE TABLE IF NOT EXISTS `post` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_post_comm` int(11) NOT NULL,
  `id_community` int(11) NOT NULL,
  `post_type` tinyint(4) NOT NULL,
  `parent_post_comm_id` int(11) DEFAULT NULL,
  `creation_date` datetime NOT NULL,
  `accepted_answer_comm_id` int(11) DEFAULT NULL,
  `score` int(11) NOT NULL,
  `view_count` int(11) DEFAULT NULL,
  `body` text,
  `id_user_community` int(11) DEFAULT NULL,
  `last_editor_user_community_id` int(11) DEFAULT NULL,
  `last_editor_display_name` varchar(500) DEFAULT NULL,
  `last_edit_date` datetime DEFAULT NULL,
  `last_activity_date` datetime DEFAULT NULL,
  `community_owned_date` datetime DEFAULT NULL,
  `closed_date` datetime DEFAULT NULL,
  `title` mediumtext,
  `tags` varchar(700) DEFAULT NULL,
  `answer_count` int(11) DEFAULT NULL,
  `comment_count` int(11) NOT NULL,
  `favorite_count` int(11) DEFAULT NULL,
  `id_user` int(11) DEFAULT NULL,
  `ari` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_post_comm_id_community` (`id_post_comm`,`id_community`),
  KEY `FK_post_community` (`id_community`),
  KEY `FK_post_user` (`id_user`),
  CONSTRAINT `FK_question_community` FOREIGN KEY (`id_community`) REFERENCES `community` (`id`),
  CONSTRAINT `FK_question_user` FOREIGN KEY (`id_user`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Exportação de dados foi desmarcado.


-- Copiando estrutura para tabela communities-dataset.post_link
CREATE TABLE IF NOT EXISTS `post_link` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_post_link_comm` int(11) NOT NULL,
  `id_community` int(11) NOT NULL,
  `creation_date` datetime NOT NULL,
  `id_post_comm` int(11) NOT NULL,
  `id_related_post_comm` int(11) NOT NULL,
  `post_link_type` tinyint(4) NOT NULL,
  `id_post` int(11) DEFAULT NULL,
  `id_related_post` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_post_link_comm_id_community` (`id_post_link_comm`,`id_community`),
  KEY `FK_post_link_community` (`id_community`),
  KEY `FK_post_link_post` (`id_post`),
  KEY `FK_post_link_post_2` (`id_related_post`),
  CONSTRAINT `FK_post_link_community` FOREIGN KEY (`id_community`) REFERENCES `community` (`id`),
  CONSTRAINT `FK_post_link_post` FOREIGN KEY (`id_post`) REFERENCES `post` (`id`),
  CONSTRAINT `FK_post_link_post_2` FOREIGN KEY (`id_related_post`) REFERENCES `post` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Exportação de dados foi desmarcado.


-- Copiando estrutura para tabela communities-dataset.user
CREATE TABLE IF NOT EXISTS `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_user_comm` int(11) NOT NULL,
  `id_community` int(11) NOT NULL,
  `reputation` int(11) NOT NULL,
  `creation_date` datetime NOT NULL,
  `display_name` varchar(500) NOT NULL,
  `last_access_date` datetime NOT NULL,
  `website_url` varchar(1000) DEFAULT NULL,
  `location` varchar(500) DEFAULT NULL,
  `age` int(11) DEFAULT NULL,
  `about_me` text,
  `views` int(11) NOT NULL,
  `up_votes` int(11) DEFAULT NULL,
  `down_votes` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_user_comm_id_community` (`id_user_comm`,`id_community`),
  KEY `FK__community` (`id_community`),
  CONSTRAINT `FK__community` FOREIGN KEY (`id_community`) REFERENCES `community` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Exportação de dados foi desmarcado.


-- Copiando estrutura para tabela communities-dataset.vote
CREATE TABLE IF NOT EXISTS `vote` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_community` int(11) NOT NULL,
  `id_vote_comm` int(11) NOT NULL,
  `id_post_comm` int(11) NOT NULL,
  `vote_type` tinyint(4) NOT NULL,
  `creation_date` datetime NOT NULL,
  `id_user_comm` int(11) DEFAULT NULL,
  `bounty_amount` int(11) DEFAULT NULL,
  `id_user` int(11) DEFAULT NULL,
  `id_post` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_community_id_vote_comm` (`id_community`,`id_vote_comm`),
  KEY `FK_vote_user` (`id_user`),
  KEY `FK_vote_post` (`id_post`),
  CONSTRAINT `FK_vote_community` FOREIGN KEY (`id_community`) REFERENCES `community` (`id`),
  CONSTRAINT `FK_vote_post` FOREIGN KEY (`id_post`) REFERENCES `post` (`id`),
  CONSTRAINT `FK_vote_user` FOREIGN KEY (`id_user`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Exportação de dados foi desmarcado.
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
