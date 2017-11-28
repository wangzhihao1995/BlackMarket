SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for course
-- ----------------------------
DROP TABLE IF EXISTS `course`;
CREATE TABLE `course` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(80) COLLATE utf8mb4_bin DEFAULT NULL,
  `teacher` varchar(80) COLLATE utf8mb4_bin DEFAULT NULL,
  `credit` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- ----------------------------
-- Table structure for course_demand
-- ----------------------------
DROP TABLE IF EXISTS `course_demand`;
CREATE TABLE `course_demand` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `post_id` int(11) DEFAULT NULL,
  `course_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `post_id` (`post_id`),
  CONSTRAINT `course_demand_ibfk_1` FOREIGN KEY (`post_id`) REFERENCES `course_post` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=190 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- ----------------------------
-- Table structure for course_post
-- ----------------------------
DROP TABLE IF EXISTS `course_post`;
CREATE TABLE `course_post` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `student_id` int(11) DEFAULT NULL,
  `status_` smallint(6) DEFAULT NULL,
  `switch` smallint(6) DEFAULT NULL,
  `mobile` varchar(80) COLLATE utf8mb4_bin DEFAULT NULL,
  `wechat` varchar(80) COLLATE utf8mb4_bin DEFAULT NULL,
  `message` varchar(256) COLLATE utf8mb4_bin DEFAULT NULL,
  `pv_` int(11) DEFAULT NULL,
  `editable` smallint(6) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `student_id` (`student_id`),
  CONSTRAINT `course_post_ibfk_1` FOREIGN KEY (`student_id`) REFERENCES `student` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=190 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- ----------------------------
-- Table structure for course_schedule
-- ----------------------------
DROP TABLE IF EXISTS `course_schedule`;
CREATE TABLE `course_schedule` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `course_id` int(11) DEFAULT NULL,
  `day` int(11) DEFAULT NULL,
  `start` int(11) DEFAULT NULL,
  `end` int(11) DEFAULT NULL,
  `frequency` varchar(10) COLLATE utf8mb4_bin DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `course_id` (`course_id`),
  CONSTRAINT `course_schedule_ibfk_1` FOREIGN KEY (`course_id`) REFERENCES `course` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=54 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- ----------------------------
-- Table structure for course_supply
-- ----------------------------
DROP TABLE IF EXISTS `course_supply`;
CREATE TABLE `course_supply` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `post_id` int(11) DEFAULT NULL,
  `course_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `post_id` (`post_id`),
  CONSTRAINT `course_supply_ibfk_1` FOREIGN KEY (`post_id`) REFERENCES `course_post` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=190 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- ----------------------------
-- Table structure for file_photo
-- ----------------------------
DROP TABLE IF EXISTS `file_photo`;
CREATE TABLE `file_photo` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `bucket` varchar(80) COLLATE utf8mb4_bin DEFAULT NULL,
  `file_name` varchar(80) COLLATE utf8mb4_bin DEFAULT NULL,
  `filesize` varchar(80) COLLATE utf8mb4_bin DEFAULT NULL,
  `hash` varchar(128) COLLATE utf8mb4_bin DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- ----------------------------
-- Table structure for goods_post
-- ----------------------------
DROP TABLE IF EXISTS `goods_post`;
CREATE TABLE `goods_post` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `student_id` int(11) DEFAULT NULL,
  `status_` smallint(6) DEFAULT NULL,
  `switch` smallint(6) DEFAULT NULL,
  `mobile` varchar(80) COLLATE utf8mb4_bin DEFAULT NULL,
  `wechat` varchar(80) COLLATE utf8mb4_bin DEFAULT NULL,
  `message` varchar(256) COLLATE utf8mb4_bin DEFAULT NULL,
  `imgs` varchar(80) COLLATE utf8mb4_bin DEFAULT NULL,
  `pv_` int(11) DEFAULT NULL,
  `editable` smallint(6) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `student_id` (`student_id`),
  CONSTRAINT `goods_post_ibfk_1` FOREIGN KEY (`student_id`) REFERENCES `student` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- ----------------------------
-- Table structure for student
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student` (
  `id` int(11) DEFAULT NULL,
  `name` varchar(80) COLLATE utf8mb4_bin DEFAULT NULL,
  `mobile` varchar(80) COLLATE utf8mb4_bin NOT NULL,
  `open_id` varchar(80) COLLATE utf8mb4_bin DEFAULT NULL,
  `type` smallint(6) DEFAULT NULL,
  `grade` varchar(10) COLLATE utf8mb4_bin DEFAULT NULL,
  `status` smallint(6) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`mobile`),
  KEY `id` (`id`),
  KEY `open_id` (`open_id`),
  KEY `ix_student_mobile` (`mobile`),
  CONSTRAINT `student_ibfk_1` FOREIGN KEY (`id`) REFERENCES `wechat_user` (`id`),
  CONSTRAINT `student_ibfk_2` FOREIGN KEY (`open_id`) REFERENCES `wechat_user` (`open_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- ----------------------------
-- Table structure for user_behavior
-- ----------------------------
DROP TABLE IF EXISTS `user_behavior`;
CREATE TABLE `user_behavior` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `type_` smallint(6) DEFAULT NULL,
  `detail` text COLLATE utf8mb4_bin,
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3639 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- ----------------------------
-- Table structure for user_view_record
-- ----------------------------
DROP TABLE IF EXISTS `user_view_record`;
CREATE TABLE `user_view_record` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `student_id` varchar(56) COLLATE utf8mb4_bin DEFAULT NULL,
  `post_id` varchar(56) COLLATE utf8mb4_bin DEFAULT NULL,
  `post_type_` smallint(6) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=205 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- ----------------------------
-- Table structure for wechat_session
-- ----------------------------
DROP TABLE IF EXISTS `wechat_session`;
CREATE TABLE `wechat_session` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `open_id` varchar(80) COLLATE utf8mb4_bin NOT NULL,
  `session_key` varchar(80) COLLATE utf8mb4_bin NOT NULL,
  `third_session_key` varchar(80) COLLATE utf8mb4_bin NOT NULL,
  `create_time` datetime DEFAULT NULL,
  `expire_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `ix_wechat_session_third_session_key` (`third_session_key`),
  KEY `ix_wechat_session_open_id` (`open_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1100 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- ----------------------------
-- Table structure for wechat_user
-- ----------------------------
DROP TABLE IF EXISTS `wechat_user`;
CREATE TABLE `wechat_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `open_id` varchar(80) COLLATE utf8mb4_bin NOT NULL,
  `nickname` varchar(80) COLLATE utf8mb4_bin DEFAULT NULL,
  `avatar_url` varchar(256) COLLATE utf8mb4_bin DEFAULT NULL,
  `city` varchar(80) COLLATE utf8mb4_bin DEFAULT NULL,
  `country` varchar(80) COLLATE utf8mb4_bin DEFAULT NULL,
  `gender` smallint(6) DEFAULT NULL,
  `language` varchar(80) COLLATE utf8mb4_bin DEFAULT NULL,
  `province` varchar(80) COLLATE utf8mb4_bin DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `ix_wechat_user_open_id` (`open_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1039 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

SET FOREIGN_KEY_CHECKS = 1;
