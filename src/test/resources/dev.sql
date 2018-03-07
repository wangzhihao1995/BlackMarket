SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for config
-- ----------------------------
DROP TABLE IF EXISTS `config`;
CREATE TABLE `config` (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `k` varchar(80) NOT NULL,
  `v` varchar(256) DEFAULT NULL,
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_key` (`k`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- ----------------------------
-- Table structure for course
-- ----------------------------
DROP TABLE IF EXISTS `course`;
CREATE TABLE `course` (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` varchar(80) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `teacher` varchar(80) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `credit` int(11) DEFAULT NULL,
  `year` int(4) NOT NULL,
  `semester` tinyint NOT NULL,
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `idx_course_year_semester` (`year`, `semester`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- ----------------------------
-- Table structure for course_post
-- ----------------------------
DROP TABLE IF EXISTS `course_post`;
CREATE TABLE `course_post` (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `student_id` int(11) DEFAULT NULL,
  `demand` int(11) DEFAULT NULL,
  `supply` int(11) DEFAULT NULL,
  `status` tinyint(6) DEFAULT NULL,
  `mobile_switch` tinyint(6) DEFAULT NULL,
  `wechat` varchar(80) DEFAULT NULL,
  `message` varchar(256) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `pv` int(11) DEFAULT NULL,
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `idx_course_post_student_id` (`student_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- ----------------------------
-- Table structure for course_schedule
-- ----------------------------
DROP TABLE IF EXISTS `course_schedule`;
CREATE TABLE `course_schedule` (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `course_id` int(11) DEFAULT NULL,
  `day` int(11) DEFAULT NULL,
  `start` int(11) DEFAULT NULL,
  `end` int(11) DEFAULT NULL,
  `frequency` varchar(10) DEFAULT NULL,
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `course_id` (`course_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- ----------------------------
-- Table structure for file
-- ----------------------------
DROP TABLE IF EXISTS `file`;
CREATE TABLE `file` (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `type` tinyint(2) unsigned NOT NULL COMMENT '文件类型, 1:图片, 2:文档, 3:音频, 4:视频, 5:其他',
  `uploader_id` int(11) unsigned NOT NULL COMMENT '上传用户ID',
  `status` tinyint(2) unsigned NOT NULL DEFAULT '1' COMMENT '文件上传状态',
  `key` varchar(255) NOT NULL COMMENT '文件名',
  `url` varchar(255) DEFAULT NULL COMMENT '文件地址',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- ----------------------------
-- Table structure for goods_post
-- ----------------------------
DROP TABLE IF EXISTS `goods_post`;
CREATE TABLE `goods_post` (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `student_id` int(11) DEFAULT NULL,
  `status` tinyint DEFAULT NULL,
  `mobile_switch` tinyint DEFAULT NULL,
  `wechat` varchar(32) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `content` varchar(512) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `pv` int(11) DEFAULT NULL,
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `idx_goods_post_student_id` (`student_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- ----------------------------
-- Table structure for goods_post_image
-- ----------------------------
DROP TABLE IF EXISTS `goods_post_image`;
CREATE TABLE `goods_post_image` (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `post_id` int(11) UNSIGNED NOT NULL,
  `image_id` int(11) UNSIGNED NOT NULL,
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_post_image` (`post_id`, `image_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- ----------------------------
-- Table structure for tag
-- ----------------------------
DROP TABLE IF EXISTS `tag`;
CREATE TABLE `tag` (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` varchar(80) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_tag_name` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- ----------------------------
-- Table structure for tag_post
-- ----------------------------
DROP TABLE IF EXISTS `tag_post`;
CREATE TABLE `tag_post` (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `tag_id` int(11) UNSIGNED NOT NULL,
  `post_id` int(11) UNSIGNED NOT NULL,
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_tag_post` (`tag_id`, `post_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- ----------------------------
-- Table structure for student
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student` (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `wechat_user_id` int(11) UNSIGNED NOT NULL,
  `name` varchar(80) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `mobile` varchar(80) COLLATE utf8mb4_general_ci NOT NULL,
  `open_id` varchar(80) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `type` int(6) DEFAULT NULL,
  `grade` int(4) DEFAULT NULL,
  `status` tinyint DEFAULT NULL,
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`mobile`),
  KEY `id` (`id`),
  KEY `idx_student_open_id` (`open_id`),
  KEY `idx_student_mobile` (`mobile`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- ----------------------------
-- Table structure for wechat_session
-- ----------------------------
DROP TABLE IF EXISTS `wechat_session`;
CREATE TABLE `wechat_session` (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `open_id` varchar(80) COLLATE utf8mb4_general_ci NOT NULL,
  `session_key` varchar(80) COLLATE utf8mb4_general_ci NOT NULL,
  `third_session_key` varchar(80) COLLATE utf8mb4_general_ci NOT NULL,
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_wechat_session_third_session_key` (`third_session_key`),
  KEY `idx_wechat_session_open_id` (`open_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- ----------------------------
-- Table structure for wechat_user
-- ----------------------------
DROP TABLE IF EXISTS `wechat_user`;
CREATE TABLE `wechat_user` (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `open_id` varchar(80) COLLATE utf8mb4_general_ci NOT NULL,
  `nick_name` varchar(80) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `avatar_url` varchar(256) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `city` varchar(80) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `country` varchar(80) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `gender` smallint(6) DEFAULT NULL,
  `language` varchar(80) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `province` varchar(80) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_wechat_user_open_id` (`open_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

SET FOREIGN_KEY_CHECKS = 1;
