/*
 Navicat MySQL Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 80016
 Source Host           : localhost:3306
 Source Schema         : demo

 Target Server Type    : MySQL
 Target Server Version : 80016
 File Encoding         : 65001

 Date: 06/05/2021 18:20:00
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for dict_type
-- ----------------------------
DROP TABLE IF EXISTS `dict_type`;
CREATE TABLE `dict_type`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `type` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `del` tinyint(1) NULL DEFAULT 0,
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0),
  `update_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of dict_type
-- ----------------------------
INSERT INTO `dict_type` VALUES (1, 'dept', '部门', 0, '2021-05-06 17:05:01', '2021-05-06 17:05:07');
INSERT INTO `dict_type` VALUES (2, 'occupation', '职业', 0, '2021-05-06 17:05:04', '2021-05-06 17:05:09');

-- ----------------------------
-- Table structure for dict_value
-- ----------------------------
DROP TABLE IF EXISTS `dict_value`;
CREATE TABLE `dict_value`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `value` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `type` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `del` tinyint(1) NULL DEFAULT 0,
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0),
  `update_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of dict_value
-- ----------------------------
INSERT INTO `dict_value` VALUES (1, 'IT', 'occupation', 0, '2021-05-06 17:07:41', '2021-05-06 17:07:41');
INSERT INTO `dict_value` VALUES (2, '医生', 'occupation', 0, '2021-05-06 17:07:54', '2021-05-06 17:07:54');
INSERT INTO `dict_value` VALUES (3, '教师', 'occupation', 0, '2021-05-06 17:08:00', '2021-05-06 17:08:00');
INSERT INTO `dict_value` VALUES (4, '司机', 'occupation', 0, '2021-05-06 17:08:10', '2021-05-06 17:08:10');
INSERT INTO `dict_value` VALUES (5, '学生', 'occupation', 0, '2021-05-06 17:08:34', '2021-05-06 17:08:34');
INSERT INTO `dict_value` VALUES (6, '演员', 'occupation', 0, '2021-05-06 17:08:40', '2021-05-06 17:08:40');
INSERT INTO `dict_value` VALUES (7, '厨师', 'occupation', 0, '2021-05-06 17:08:53', '2021-05-06 17:08:53');
INSERT INTO `dict_value` VALUES (8, '军人', 'occupation', 0, '2021-05-06 17:09:01', '2021-05-06 17:09:01');
INSERT INTO `dict_value` VALUES (9, '律师', 'occupation', 0, '2021-05-06 17:09:16', '2021-05-06 17:09:16');
INSERT INTO `dict_value` VALUES (10, '模特', 'occupation', 0, '2021-05-06 17:09:30', '2021-05-06 17:09:30');
INSERT INTO `dict_value` VALUES (11, '研发部', 'dept', 0, '2021-05-06 17:36:35', '2021-05-06 17:36:35');
INSERT INTO `dict_value` VALUES (12, '人事部', 'dept', 0, '2021-05-06 17:36:39', '2021-05-06 17:36:39');
INSERT INTO `dict_value` VALUES (13, '财务部', 'dept', 0, '2021-05-06 17:36:40', '2021-05-06 17:36:40');
INSERT INTO `dict_value` VALUES (14, '商务部', 'dept', 0, '2021-05-06 17:36:41', '2021-05-06 17:36:41');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `age` int(11) NULL DEFAULT NULL,
  `department` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `occupation` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `del` tinyint(1) NULL DEFAULT 0,
  `update_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0),
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'rstyro', 25, '研发部', 'IT', 0, '2021-05-06 17:55:58', '2021-05-06 17:56:00');
INSERT INTO `user` VALUES (2, '菜鸡', 25, '研发部', 'IT', 0, '2021-05-06 18:12:51', '2021-05-06 17:56:00');
INSERT INTO `user` VALUES (3, '搬砖', 25, '研发部', 'IT', 0, '2021-05-06 18:14:26', '2021-05-06 18:14:26');
INSERT INTO `user` VALUES (6, '张三', 25, '研发部', 'IT', 0, '2021-05-06 18:18:47', '2021-05-06 18:18:47');
INSERT INTO `user` VALUES (7, '李四', 26, '商务部', '军人', 0, '2021-05-06 18:18:47', '2021-05-06 18:18:47');

SET FOREIGN_KEY_CHECKS = 1;
