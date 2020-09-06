/*
 Navicat Premium Data Transfer

 Source Server         : mac本地数据库
 Source Server Type    : MySQL
 Source Server Version : 50730
 Source Host           : localhost:3306
 Source Schema         : auth_demo

 Target Server Type    : MySQL
 Target Server Version : 50730
 File Encoding         : 65001

 Date: 06/09/2020 10:43:32
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_class
-- ----------------------------
DROP TABLE IF EXISTS `t_class`;
CREATE TABLE `t_class` (
  `id` bigint(20) NOT NULL,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of t_class
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for t_permission
-- ----------------------------
DROP TABLE IF EXISTS `t_permission`;
CREATE TABLE `t_permission` (
  `id` bigint(20) NOT NULL,
  `name` varchar(32) NOT NULL,
  `url` varchar(128) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of t_permission
-- ----------------------------
BEGIN;
INSERT INTO `t_permission` VALUES (1, '查看当前用户', '/user/current');
INSERT INTO `t_permission` VALUES (2, '根据ID查询用户', '/user/findById/**');
INSERT INTO `t_permission` VALUES (3, '保存用户', '/user/save');
INSERT INTO `t_permission` VALUES (4, '根据ID查询学生', '/student/findById/**');
COMMIT;

-- ----------------------------
-- Table structure for t_role
-- ----------------------------
DROP TABLE IF EXISTS `t_role`;
CREATE TABLE `t_role` (
  `id` bigint(20) NOT NULL,
  `name` varchar(32) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of t_role
-- ----------------------------
BEGIN;
INSERT INTO `t_role` VALUES (1, 'ADMIN');
INSERT INTO `t_role` VALUES (2, 'VIP');
COMMIT;

-- ----------------------------
-- Table structure for t_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `t_role_permission`;
CREATE TABLE `t_role_permission` (
  `id` bigint(20) NOT NULL,
  `role_id` bigint(20) NOT NULL,
  `permission_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of t_role_permission
-- ----------------------------
BEGIN;
INSERT INTO `t_role_permission` VALUES (1, 1, 1);
INSERT INTO `t_role_permission` VALUES (2, 1, 2);
INSERT INTO `t_role_permission` VALUES (3, 1, 3);
INSERT INTO `t_role_permission` VALUES (4, 1, 4);
COMMIT;

-- ----------------------------
-- Table structure for t_student
-- ----------------------------
DROP TABLE IF EXISTS `t_student`;
CREATE TABLE `t_student` (
  `id` bigint(20) NOT NULL,
  `name` varchar(255) NOT NULL,
  `class_id` bigint(20) NOT NULL,
  `teacher_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of t_student
-- ----------------------------
BEGIN;
INSERT INTO `t_student` VALUES (1, '张学生', 1, 1);
COMMIT;

-- ----------------------------
-- Table structure for t_teacher
-- ----------------------------
DROP TABLE IF EXISTS `t_teacher`;
CREATE TABLE `t_teacher` (
  `id` bigint(20) NOT NULL,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of t_teacher
-- ----------------------------
BEGIN;
INSERT INTO `t_teacher` VALUES (1, '张老师');
COMMIT;

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `id` bigint(21) NOT NULL COMMENT '主键',
  `name` varchar(32) NOT NULL,
  `password` varchar(64) NOT NULL,
  `lock_flag` tinyint(4) NOT NULL,
  `enable_flag` tinyint(4) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of t_user
-- ----------------------------
BEGIN;
INSERT INTO `t_user` VALUES (1298901526921543681, 'mary', '$2a$10$dZmgjRqchogkR5.FXIyvh./AYXRu22Lsky7BcaKuFgitXU9zHA16C', 0, 0);
INSERT INTO `t_user` VALUES (1298901673227255809, '张大帅1号', '$2a$10$PPmsv7.H0A69W4uAbN62J.5AdxAihd3P5.jFkaJBAH0lj10tCP6bG', 0, 0);
INSERT INTO `t_user` VALUES (1300103441425915905, 'kangkang', '$2a$10$y9BL9kVpdXGbFLh6ARcXW.9/.QDqXWSOxCfWIKP6Sx80aIqzTEX6C', 0, 0);
COMMIT;

-- ----------------------------
-- Table structure for t_user_role
-- ----------------------------
DROP TABLE IF EXISTS `t_user_role`;
CREATE TABLE `t_user_role` (
  `id` bigint(20) NOT NULL,
  `user_id` bigint(20) NOT NULL,
  `role_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of t_user_role
-- ----------------------------
BEGIN;
INSERT INTO `t_user_role` VALUES (1, 1298901526921543681, 1);
INSERT INTO `t_user_role` VALUES (2, 1298901526921543681, 2);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
