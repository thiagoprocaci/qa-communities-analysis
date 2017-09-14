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

-- Copiando estrutura do banco de dados para communities-test
CREATE DATABASE IF NOT EXISTS `communities-test` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `communities-test`;


-- Copiando estrutura para view communities-test.ai_user
-- Criando tabela temporária para evitar erros de dependência de VIEW
CREATE TABLE `ai_user` (
	`id` INT(11) NOT NULL,
	`id_user_comm` INT(11) NOT NULL,
	`id_community` INT(11) NOT NULL,
	`reputation` INT(11) NOT NULL,
	`creation_date` DATETIME NOT NULL,
	`display_name` VARCHAR(500) NOT NULL COLLATE 'utf8_general_ci',
	`last_access_date` DATETIME NOT NULL,
	`website_url` VARCHAR(1000) NULL COLLATE 'utf8_general_ci',
	`location` VARCHAR(500) NULL COLLATE 'utf8_general_ci',
	`age` INT(11) NULL,
	`about_me` TEXT NULL COLLATE 'utf8_general_ci',
	`views` INT(11) NOT NULL,
	`up_votes` INT(11) NULL,
	`down_votes` INT(11) NULL,
	`period` INT(11) NULL
) ENGINE=MyISAM;


-- Copiando estrutura para view communities-test.biology_user
-- Criando tabela temporária para evitar erros de dependência de VIEW
CREATE TABLE `biology_user` (
	`id` INT(11) NOT NULL,
	`id_user_comm` INT(11) NOT NULL,
	`id_community` INT(11) NOT NULL,
	`reputation` INT(11) NOT NULL,
	`creation_date` DATETIME NOT NULL,
	`display_name` VARCHAR(500) NOT NULL COLLATE 'utf8_general_ci',
	`last_access_date` DATETIME NOT NULL,
	`website_url` VARCHAR(1000) NULL COLLATE 'utf8_general_ci',
	`location` VARCHAR(500) NULL COLLATE 'utf8_general_ci',
	`age` INT(11) NULL,
	`about_me` TEXT NULL COLLATE 'utf8_general_ci',
	`views` INT(11) NOT NULL,
	`up_votes` INT(11) NULL,
	`down_votes` INT(11) NULL,
	`period` INT(11) NULL
) ENGINE=MyISAM;


-- Copiando estrutura para view communities-test.biology_user_profile
-- Criando tabela temporária para evitar erros de dependência de VIEW
CREATE TABLE `biology_user_profile` (
	`id` INT(11) NOT NULL,
	`id_user_comm` INT(11) NOT NULL,
	`id_community` INT(11) NOT NULL,
	`reputation` INT(11) NOT NULL,
	`creation_date` DATETIME NOT NULL,
	`display_name` VARCHAR(500) NOT NULL COLLATE 'utf8_general_ci',
	`last_access_date` DATETIME NOT NULL,
	`website_url` VARCHAR(1000) NULL COLLATE 'utf8_general_ci',
	`location` VARCHAR(500) NULL COLLATE 'utf8_general_ci',
	`age` INT(11) NULL,
	`about_me` TEXT NULL COLLATE 'utf8_general_ci',
	`views` INT(11) NOT NULL,
	`up_votes` INT(11) NULL,
	`down_votes` INT(11) NULL,
	`period` INT(11) NULL,
	`profile` INT(0) NULL
) ENGINE=MyISAM;


-- Copiando estrutura para view communities-test.chemistry_user
-- Criando tabela temporária para evitar erros de dependência de VIEW
CREATE TABLE `chemistry_user` (
	`id` INT(11) NOT NULL,
	`id_user_comm` INT(11) NOT NULL,
	`id_community` INT(11) NOT NULL,
	`reputation` INT(11) NOT NULL,
	`creation_date` DATETIME NOT NULL,
	`display_name` VARCHAR(500) NOT NULL COLLATE 'utf8_general_ci',
	`last_access_date` DATETIME NOT NULL,
	`website_url` VARCHAR(1000) NULL COLLATE 'utf8_general_ci',
	`location` VARCHAR(500) NULL COLLATE 'utf8_general_ci',
	`age` INT(11) NULL,
	`about_me` TEXT NULL COLLATE 'utf8_general_ci',
	`views` INT(11) NOT NULL,
	`up_votes` INT(11) NULL,
	`down_votes` INT(11) NULL,
	`period` INT(11) NULL
) ENGINE=MyISAM;


-- Copiando estrutura para view communities-test.chemistry_user_profile
-- Criando tabela temporária para evitar erros de dependência de VIEW
CREATE TABLE `chemistry_user_profile` (
	`id` INT(11) NOT NULL,
	`id_user_comm` INT(11) NOT NULL,
	`id_community` INT(11) NOT NULL,
	`reputation` INT(11) NOT NULL,
	`creation_date` DATETIME NOT NULL,
	`display_name` VARCHAR(500) NOT NULL COLLATE 'utf8_general_ci',
	`last_access_date` DATETIME NOT NULL,
	`website_url` VARCHAR(1000) NULL COLLATE 'utf8_general_ci',
	`location` VARCHAR(500) NULL COLLATE 'utf8_general_ci',
	`age` INT(11) NULL,
	`about_me` TEXT NULL COLLATE 'utf8_general_ci',
	`views` INT(11) NOT NULL,
	`up_votes` INT(11) NULL,
	`down_votes` INT(11) NULL,
	`period` INT(11) NULL,
	`profile` INT(0) NULL
) ENGINE=MyISAM;


-- Copiando estrutura para tabela communities-test.comment
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
  `period` int(11) DEFAULT NULL,
  `ari` double DEFAULT NULL,
  `smog` double DEFAULT NULL,
  `flesch_reading` double DEFAULT NULL,
  `flesch_kincaid` double DEFAULT NULL,
  `gunning_fog` double DEFAULT NULL,
  `coleman_liau` double DEFAULT NULL,
  `smog_index` double DEFAULT NULL,
  `characters` double DEFAULT NULL,
  `syllables` double DEFAULT NULL,
  `words` double DEFAULT NULL,
  `complexwords` double DEFAULT NULL,
  `sentences` double DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_community_id_comment_comm` (`id_community`,`id_comment_comm`),
  KEY `FK_comment_user` (`id_user`),
  KEY `FK_comment_post` (`id_post`),
  CONSTRAINT `FK_comment_community` FOREIGN KEY (`id_community`) REFERENCES `community` (`id`),
  CONSTRAINT `FK_comment_post` FOREIGN KEY (`id_post`) REFERENCES `post` (`id`),
  CONSTRAINT `FK_comment_user` FOREIGN KEY (`id_user`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Exportação de dados foi desmarcado.


-- Copiando estrutura para tabela communities-test.community
CREATE TABLE IF NOT EXISTS `community` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(150) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Exportação de dados foi desmarcado.


-- Copiando estrutura para tabela communities-test.graph_analysis_context
CREATE TABLE IF NOT EXISTS `graph_analysis_context` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `period` int(11) NOT NULL,
  `id_community` int(11) NOT NULL,
  `nodes` int(11) NOT NULL,
  `edges` int(11) NOT NULL,
  `density` double NOT NULL,
  `diameter` double NOT NULL,
  `radius` double NOT NULL,
  `avg_dist` double NOT NULL,
  `weakly_component_count` int(11) NOT NULL,
  `strongly_component_count` int(11) NOT NULL,
  `number_communities` int(11) NOT NULL,
  `modularity_with_resolution` double NOT NULL,
  `modularity` double NOT NULL,
  `avg_degree` double NOT NULL,
  `avg_clustering_coef` double NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `period_id_community` (`period`,`id_community`),
  KEY `FK_graph_analysis_context_community` (`id_community`),
  CONSTRAINT `FK_graph_analysis_context_community` FOREIGN KEY (`id_community`) REFERENCES `community` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Exportação de dados foi desmarcado.


-- Copiando estrutura para tabela communities-test.graph_edge
CREATE TABLE IF NOT EXISTS `graph_edge` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_user_source` int(11) NOT NULL,
  `id_user_dest` int(11) NOT NULL,
  `weight` int(11) NOT NULL,
  `id_graph_analysis_context` int(11) NOT NULL,
  `id_community` int(11) NOT NULL,
  `id_graph_node_source` int(11) NOT NULL,
  `id_graph_node_dest` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_user_source_id_user_dest_id_graph_analysis_context` (`id_user_source`,`id_user_dest`,`id_graph_analysis_context`),
  KEY `FK_graph_edge_user_2` (`id_user_dest`),
  KEY `FK_graph_edge_graph_analysis_context` (`id_graph_analysis_context`),
  KEY `FK_graph_edge_community` (`id_community`),
  KEY `FK_graph_edge_graph_node` (`id_graph_node_source`),
  KEY `FK_graph_edge_graph_node_2` (`id_graph_node_dest`),
  CONSTRAINT `FK_graph_edge_community` FOREIGN KEY (`id_community`) REFERENCES `community` (`id`),
  CONSTRAINT `FK_graph_edge_graph_analysis_context` FOREIGN KEY (`id_graph_analysis_context`) REFERENCES `graph_analysis_context` (`id`),
  CONSTRAINT `FK_graph_edge_graph_node` FOREIGN KEY (`id_graph_node_source`) REFERENCES `graph_node` (`id`),
  CONSTRAINT `FK_graph_edge_graph_node_2` FOREIGN KEY (`id_graph_node_dest`) REFERENCES `graph_node` (`id`),
  CONSTRAINT `FK_graph_edge_user` FOREIGN KEY (`id_user_source`) REFERENCES `user` (`id`),
  CONSTRAINT `FK_graph_edge_user_2` FOREIGN KEY (`id_user_dest`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Exportação de dados foi desmarcado.


-- Copiando estrutura para tabela communities-test.graph_node
CREATE TABLE IF NOT EXISTS `graph_node` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `betweenness` double NOT NULL,
  `closeness` double NOT NULL,
  `eccentricity` double NOT NULL,
  `harmonic_closeness` double NOT NULL,
  `page_rank` double NOT NULL,
  `indegree` int(11) NOT NULL,
  `outdegree` int(11) NOT NULL,
  `degree` int(11) NOT NULL,
  `eigenvector` double NOT NULL,
  `modularity_class` int(11) NOT NULL,
  `clustering_coefficient` double NOT NULL,
  `strongly_component` int(11) NOT NULL,
  `weakly_component` int(11) NOT NULL,
  `interactions` int(11) NOT NULL,
  `id_graph_analysis_context` int(11) NOT NULL,
  `id_user` int(11) NOT NULL,
  `id_community` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_graph_analysis_context_id_user` (`id_graph_analysis_context`,`id_user`),
  KEY `FK_graph_node_user` (`id_user`),
  KEY `FK_graph_node_community` (`id_community`),
  CONSTRAINT `FK_graph_node_community` FOREIGN KEY (`id_community`) REFERENCES `community` (`id`),
  CONSTRAINT `FK_graph_node_graph_analysis_context` FOREIGN KEY (`id_graph_analysis_context`) REFERENCES `graph_analysis_context` (`id`),
  CONSTRAINT `FK_graph_node_user` FOREIGN KEY (`id_user`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Exportação de dados foi desmarcado.


-- Copiando estrutura para tabela communities-test.post
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
  `period` int(11) DEFAULT NULL,
  `ari_text` double DEFAULT NULL,
  `smog_text` double DEFAULT NULL,
  `flesch_reading_text` double DEFAULT NULL,
  `flesch_kincaid_text` double DEFAULT NULL,
  `gunning_fog_text` double DEFAULT NULL,
  `coleman_liau_text` double DEFAULT NULL,
  `smog_index_text` double DEFAULT NULL,
  `characters_text` double DEFAULT NULL,
  `syllables_text` double DEFAULT NULL,
  `words_text` double DEFAULT NULL,
  `complexwords_text` double DEFAULT NULL,
  `sentences_text` double DEFAULT NULL,
  `ari_title` double DEFAULT NULL,
  `smog_title` double DEFAULT NULL,
  `flesch_reading_title` double DEFAULT NULL,
  `flesch_kincaid_title` double DEFAULT NULL,
  `gunning_fog_title` double DEFAULT NULL,
  `coleman_liau_title` double DEFAULT NULL,
  `smog_index_title` double DEFAULT NULL,
  `characters_title` double DEFAULT NULL,
  `syllables_title` double DEFAULT NULL,
  `words_title` double DEFAULT NULL,
  `complexwords_title` double DEFAULT NULL,
  `sentences_title` double DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_post_comm_id_community` (`id_post_comm`,`id_community`),
  KEY `FK_post_community` (`id_community`),
  KEY `FK_post_user` (`id_user`),
  CONSTRAINT `FK_question_community` FOREIGN KEY (`id_community`) REFERENCES `community` (`id`),
  CONSTRAINT `FK_question_user` FOREIGN KEY (`id_user`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Exportação de dados foi desmarcado.


-- Copiando estrutura para tabela communities-test.post_link
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
  `period` int(11) DEFAULT NULL,
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


-- Copiando estrutura para tabela communities-test.user
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
  `period` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_user_comm_id_community` (`id_user_comm`,`id_community`),
  KEY `FK__community` (`id_community`),
  CONSTRAINT `FK__community` FOREIGN KEY (`id_community`) REFERENCES `community` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Exportação de dados foi desmarcado.


-- Copiando estrutura para função communities-test.USER_BIOLOGY_REP_MEDIAN
DELIMITER //
CREATE DEFINER=`root`@`localhost` FUNCTION `USER_BIOLOGY_REP_MEDIAN`() RETURNS double
BEGIN

  DECLARE MEDIAN DOUBLE DEFAULT null;

select C.reputation  INTO MEDIAN from (
	SELECT avg(t1.reputation) as reputation, (select 'median' from dual) as description FROM (
SELECT @rownum:=@rownum+1 as `row_number`, d.reputation
  FROM biology_user d,  (SELECT @rownum:=0) r
  WHERE 1
  ORDER BY d.reputation
) as t1,
(
  SELECT count(*) as total_rows
  FROM biology_user d
  WHERE 1
) as t2
WHERE 1
AND t1.row_number in ( floor((total_rows+1)/2), floor((total_rows+2)/2))
	)C;

  RETURN MEDIAN;
END//
DELIMITER ;


-- Copiando estrutura para view communities-test.user_biology_rep_overview
-- Criando tabela temporária para evitar erros de dependência de VIEW
CREATE TABLE `user_biology_rep_overview` (
	`minimum` BIGINT(11) NULL,
	`Q1` DOUBLE NULL,
	`median` DOUBLE NULL,
	`Q3` DOUBLE NULL,
	`maximum` BIGINT(11) NULL
) ENGINE=MyISAM;


-- Copiando estrutura para função communities-test.USER_BIOLOGY_REP_Q1
DELIMITER //
CREATE DEFINER=`root`@`localhost` FUNCTION `USER_BIOLOGY_REP_Q1`() RETURNS double
BEGIN

  DECLARE Q1 DOUBLE DEFAULT null;
	select max(B.reputation) into Q1 from (
    SELECT
	  A.rank,
	  A.id,
	  A.reputation,
	  (select 'Q1' from dual) as description,
	  CASE WHEN A.rank <= (select (count(*) * 25)/100 from biology_user)  THEN 'Q1_start'
	  END AS 'quartile'
	FROM (
			select @rownum:=@rownum + 1 rank, p.* from biology_user p ,
			(SELECT @rownum:=0) r order by reputation asc

	)A

	)B where B.quartile = 'Q1_start' order by B.reputation;
  RETURN Q1;
END//
DELIMITER ;


-- Copiando estrutura para função communities-test.USER_BIOLOGY_REP_Q3
DELIMITER //
CREATE DEFINER=`root`@`localhost` FUNCTION `USER_BIOLOGY_REP_Q3`() RETURNS double
BEGIN

  DECLARE Q3 DOUBLE DEFAULT null;
select min(B.reputation) into Q3 from (

 SELECT
	  A.rank,
	  A.id,
	  A.reputation,
	  (select 'Q3' from dual) as description,
	  CASE WHEN A.rank >= (select (count(*) * 75)/100 from biology_user) THEN 'Q3_start'
	  END AS 'quartile'
	FROM (
			select @rownum:=@rownum + 1 rank, p.* from biology_user p ,
			(SELECT @rownum:=0) r order by reputation asc

	)A

	)B where B.quartile = 'Q3_start' order by B.reputation;
  RETURN Q3;
END//
DELIMITER ;


-- Copiando estrutura para função communities-test.USER_CHEMISTRY_REP_MEDIAN
DELIMITER //
CREATE DEFINER=`root`@`localhost` FUNCTION `USER_CHEMISTRY_REP_MEDIAN`() RETURNS double
BEGIN

  DECLARE MEDIAN DOUBLE DEFAULT null;

select C.reputation  INTO MEDIAN from (
	SELECT avg(t1.reputation) as reputation, (select 'median' from dual) as description FROM (
SELECT @rownum:=@rownum+1 as `row_number`, d.reputation
  FROM chemistry_user d,  (SELECT @rownum:=0) r
  WHERE 1
  ORDER BY d.reputation
) as t1,
(
  SELECT count(*) as total_rows
  FROM chemistry_user d
  WHERE 1
) as t2
WHERE 1
AND t1.row_number in ( floor((total_rows+1)/2), floor((total_rows+2)/2))
	)C;

  RETURN MEDIAN;
END//
DELIMITER ;


-- Copiando estrutura para view communities-test.user_chemistry_rep_overview
-- Criando tabela temporária para evitar erros de dependência de VIEW
CREATE TABLE `user_chemistry_rep_overview` (
	`minimum` BIGINT(11) NULL,
	`Q1` DOUBLE NULL,
	`median` DOUBLE NULL,
	`Q3` DOUBLE NULL,
	`maximum` BIGINT(11) NULL
) ENGINE=MyISAM;


-- Copiando estrutura para função communities-test.USER_CHEMISTRY_REP_Q1
DELIMITER //
CREATE DEFINER=`root`@`localhost` FUNCTION `USER_CHEMISTRY_REP_Q1`() RETURNS double
BEGIN

  DECLARE Q1 DOUBLE DEFAULT null;
	select max(B.reputation) into Q1 from (
    SELECT
	  A.rank,
	  A.id,
	  A.reputation,
	  (select 'Q1' from dual) as description,
	  CASE WHEN A.rank <= (select (count(*) * 25)/100 from chemistry_user)  THEN 'Q1_start'
	  END AS 'quartile'
	FROM (
			select @rownum:=@rownum + 1 rank, p.* from chemistry_user p ,
			(SELECT @rownum:=0) r order by reputation asc

	)A

	)B where B.quartile = 'Q1_start' order by B.reputation;
  RETURN Q1;
END//
DELIMITER ;


-- Copiando estrutura para função communities-test.USER_CHEMISTRY_REP_Q3
DELIMITER //
CREATE DEFINER=`root`@`localhost` FUNCTION `USER_CHEMISTRY_REP_Q3`() RETURNS double
BEGIN

  DECLARE Q3 DOUBLE DEFAULT null;
select min(B.reputation) into Q3 from (

 SELECT
	  A.rank,
	  A.id,
	  A.reputation,
	  (select 'Q3' from dual) as description,
	  CASE WHEN A.rank >= (select (count(*) * 75)/100 from chemistry_user) THEN 'Q3_start'
	  END AS 'quartile'
	FROM (
			select @rownum:=@rownum + 1 rank, p.* from chemistry_user p ,
			(SELECT @rownum:=0) r order by reputation asc

	)A

	)B where B.quartile = 'Q3_start' order by B.reputation;
  RETURN Q3;
END//
DELIMITER ;


-- Copiando estrutura para tabela communities-test.vote
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
  `period` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_community_id_vote_comm` (`id_community`,`id_vote_comm`),
  KEY `FK_vote_user` (`id_user`),
  KEY `FK_vote_post` (`id_post`),
  CONSTRAINT `FK_vote_community` FOREIGN KEY (`id_community`) REFERENCES `community` (`id`),
  CONSTRAINT `FK_vote_post` FOREIGN KEY (`id_post`) REFERENCES `post` (`id`),
  CONSTRAINT `FK_vote_user` FOREIGN KEY (`id_user`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Exportação de dados foi desmarcado.


-- Copiando estrutura para view communities-test.ai_user
-- Removendo tabela temporária e criando a estrutura VIEW final
DROP TABLE IF EXISTS `ai_user`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `ai_user` AS select `u`.`id` AS `id`,`u`.`id_user_comm` AS `id_user_comm`,`u`.`id_community` AS `id_community`,`u`.`reputation` AS `reputation`,`u`.`creation_date` AS `creation_date`,`u`.`display_name` AS `display_name`,`u`.`last_access_date` AS `last_access_date`,`u`.`website_url` AS `website_url`,`u`.`location` AS `location`,`u`.`age` AS `age`,`u`.`about_me` AS `about_me`,`u`.`views` AS `views`,`u`.`up_votes` AS `up_votes`,`u`.`down_votes` AS `down_votes`,`u`.`period` AS `period` from `user` `u` where `u`.`id_community` in (select `c`.`id` from `community` `c` where (`c`.`name` = 'ai.stackexchange.com'));


-- Copiando estrutura para view communities-test.biology_user
-- Removendo tabela temporária e criando a estrutura VIEW final
DROP TABLE IF EXISTS `biology_user`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `biology_user` AS select `u`.`id` AS `id`,`u`.`id_user_comm` AS `id_user_comm`,`u`.`id_community` AS `id_community`,`u`.`reputation` AS `reputation`,`u`.`creation_date` AS `creation_date`,`u`.`display_name` AS `display_name`,`u`.`last_access_date` AS `last_access_date`,`u`.`website_url` AS `website_url`,`u`.`location` AS `location`,`u`.`age` AS `age`,`u`.`about_me` AS `about_me`,`u`.`views` AS `views`,`u`.`up_votes` AS `up_votes`,`u`.`down_votes` AS `down_votes`,`u`.`period` AS `period` from `user` `u` where `u`.`id_community` in (select `c`.`id` from `community` `c` where (`c`.`name` = 'biology.stackexchange.com'));


-- Copiando estrutura para view communities-test.biology_user_profile
-- Removendo tabela temporária e criando a estrutura VIEW final
DROP TABLE IF EXISTS `biology_user_profile`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `biology_user_profile` AS (select `u`.`id` AS `id`,`u`.`id_user_comm` AS `id_user_comm`,`u`.`id_community` AS `id_community`,`u`.`reputation` AS `reputation`,`u`.`creation_date` AS `creation_date`,`u`.`display_name` AS `display_name`,`u`.`last_access_date` AS `last_access_date`,`u`.`website_url` AS `website_url`,`u`.`location` AS `location`,`u`.`age` AS `age`,`u`.`about_me` AS `about_me`,`u`.`views` AS `views`,`u`.`up_votes` AS `up_votes`,`u`.`down_votes` AS `down_votes`,`u`.`period` AS `period`,(case when (`u`.`reputation` <= `o`.`Q1`) then 1 when ((`u`.`reputation` > `o`.`Q1`) and (`u`.`reputation` <= `o`.`median`)) then 2 when ((`u`.`reputation` > `o`.`median`) and (`u`.`reputation` <= `o`.`Q3`)) then 3 when (`u`.`reputation` > `o`.`Q3`) then 4 end) AS `profile` from (`biology_user` `u` join `user_biology_rep_overview` `o`));


-- Copiando estrutura para view communities-test.chemistry_user
-- Removendo tabela temporária e criando a estrutura VIEW final
DROP TABLE IF EXISTS `chemistry_user`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `chemistry_user` AS select `u`.`id` AS `id`,`u`.`id_user_comm` AS `id_user_comm`,`u`.`id_community` AS `id_community`,`u`.`reputation` AS `reputation`,`u`.`creation_date` AS `creation_date`,`u`.`display_name` AS `display_name`,`u`.`last_access_date` AS `last_access_date`,`u`.`website_url` AS `website_url`,`u`.`location` AS `location`,`u`.`age` AS `age`,`u`.`about_me` AS `about_me`,`u`.`views` AS `views`,`u`.`up_votes` AS `up_votes`,`u`.`down_votes` AS `down_votes`,`u`.`period` AS `period` from `user` `u` where `u`.`id_community` in (select `c`.`id` from `community` `c` where (`c`.`name` = 'chemistry.stackexchange.com'));


-- Copiando estrutura para view communities-test.chemistry_user_profile
-- Removendo tabela temporária e criando a estrutura VIEW final
DROP TABLE IF EXISTS `chemistry_user_profile`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `chemistry_user_profile` AS (select `u`.`id` AS `id`,`u`.`id_user_comm` AS `id_user_comm`,`u`.`id_community` AS `id_community`,`u`.`reputation` AS `reputation`,`u`.`creation_date` AS `creation_date`,`u`.`display_name` AS `display_name`,`u`.`last_access_date` AS `last_access_date`,`u`.`website_url` AS `website_url`,`u`.`location` AS `location`,`u`.`age` AS `age`,`u`.`about_me` AS `about_me`,`u`.`views` AS `views`,`u`.`up_votes` AS `up_votes`,`u`.`down_votes` AS `down_votes`,`u`.`period` AS `period`,(case when (`u`.`reputation` <= `o`.`Q1`) then 1 when ((`u`.`reputation` > `o`.`Q1`) and (`u`.`reputation` <= `o`.`median`)) then 2 when ((`u`.`reputation` > `o`.`median`) and (`u`.`reputation` <= `o`.`Q3`)) then 3 when (`u`.`reputation` > `o`.`Q3`) then 4 end) AS `profile` from (`chemistry_user` `u` join `user_chemistry_rep_overview` `o`));


-- Copiando estrutura para view communities-test.user_biology_rep_overview
-- Removendo tabela temporária e criando a estrutura VIEW final
DROP TABLE IF EXISTS `user_biology_rep_overview`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `user_biology_rep_overview` AS (select (select min(`u`.`reputation`) from `biology_user` `u`) AS `minimum`,(select `USER_BIOLOGY_REP_Q1`()) AS `Q1`,(select `USER_BIOLOGY_REP_MEDIAN`()) AS `median`,(select `USER_BIOLOGY_REP_Q3`()) AS `Q3`,(select max(`u`.`reputation`) from `biology_user` `u`) AS `maximum`);


-- Copiando estrutura para view communities-test.user_chemistry_rep_overview
-- Removendo tabela temporária e criando a estrutura VIEW final
DROP TABLE IF EXISTS `user_chemistry_rep_overview`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `user_chemistry_rep_overview` AS (select (select min(`u`.`reputation`) from `chemistry_user` `u`) AS `minimum`,(select `USER_CHEMISTRY_REP_Q1`()) AS `Q1`,(select `USER_CHEMISTRY_REP_MEDIAN`()) AS `median`,(select `USER_CHEMISTRY_REP_Q3`()) AS `Q3`,(select max(`u`.`reputation`) from `chemistry_user` `u`) AS `maximum`);
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
