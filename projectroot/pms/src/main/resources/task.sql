/*
 Navicat Premium Data Transfer

 Source Server         : 192.168.2.10
 Source Server Type    : MySQL
 Source Server Version : 80013
 Source Host           : 192.168.2.10:3306
 Source Schema         : task

 Target Server Type    : MySQL
 Target Server Version : 80013
 File Encoding         : 65001

 Date: 07/01/2021 14:26:05
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sys_company
-- ----------------------------
DROP TABLE IF EXISTS `sys_company`;
CREATE TABLE `sys_company`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '公司名称',
  `admin_id` int(11) NULL DEFAULT NULL COMMENT '管理员用户ID',
  `phone` char(11) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '管理员手机号',
  `logo` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '公司logo',
  `create_id` int(11) NULL DEFAULT NULL COMMENT '创建人ID',
  `create_name` char(8) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '创建人昵称',
  `gmt_create` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `statue` tinyint(1) NULL DEFAULT NULL COMMENT '公司状态(1-正常,0-禁用)',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 17 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '公司表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_company
-- ----------------------------
INSERT INTO `sys_company` VALUES (0, '行行行科技有限公司', 0, '18825487521', '/static/0/tx/0f552c069fbdf99572fd1f7dfd44bc5d.jpeg', 0, '', '2020-12-22 15:02:27', 1);
INSERT INTO `sys_company` VALUES (10, '我的', 0, '17658214759', '', 0, '', NULL, 1);
INSERT INTO `sys_company` VALUES (11, '测试334', NULL, '15214587469', '', NULL, NULL, NULL, 0);
INSERT INTO `sys_company` VALUES (14, '兰斯洛特', 0, '18825487521', '', 0, '', NULL, 1);
INSERT INTO `sys_company` VALUES (16, '莱柯睿思', NULL, '18812345678', '', NULL, NULL, NULL, 1);

-- ----------------------------
-- Table structure for sys_company_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_company_menu`;
CREATE TABLE `sys_company_menu`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `company_id` int(11) NULL DEFAULT NULL COMMENT '公司ID',
  `menu_id` int(11) NULL DEFAULT NULL COMMENT '目录ID',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 262 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '公司对应的菜单表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_company_menu
-- ----------------------------
INSERT INTO `sys_company_menu` VALUES (32, 10, 10);
INSERT INTO `sys_company_menu` VALUES (33, 10, 11);
INSERT INTO `sys_company_menu` VALUES (34, 10, 12);
INSERT INTO `sys_company_menu` VALUES (35, 10, 9);
INSERT INTO `sys_company_menu` VALUES (36, 11, 10);
INSERT INTO `sys_company_menu` VALUES (37, 11, 11);
INSERT INTO `sys_company_menu` VALUES (38, 11, 12);
INSERT INTO `sys_company_menu` VALUES (39, 11, 19);
INSERT INTO `sys_company_menu` VALUES (40, 11, 9);
INSERT INTO `sys_company_menu` VALUES (60, 16, 10);
INSERT INTO `sys_company_menu` VALUES (61, 16, 11);
INSERT INTO `sys_company_menu` VALUES (62, 16, 12);
INSERT INTO `sys_company_menu` VALUES (63, 16, 13);
INSERT INTO `sys_company_menu` VALUES (64, 16, 9);
INSERT INTO `sys_company_menu` VALUES (96, 10, 9);
INSERT INTO `sys_company_menu` VALUES (97, 10, 23);
INSERT INTO `sys_company_menu` VALUES (98, 10, 10);
INSERT INTO `sys_company_menu` VALUES (99, 10, 11);
INSERT INTO `sys_company_menu` VALUES (100, 10, 12);
INSERT INTO `sys_company_menu` VALUES (101, 10, 13);
INSERT INTO `sys_company_menu` VALUES (102, 10, 19);
INSERT INTO `sys_company_menu` VALUES (103, 10, 15);
INSERT INTO `sys_company_menu` VALUES (104, 10, 16);
INSERT INTO `sys_company_menu` VALUES (105, 10, 17);
INSERT INTO `sys_company_menu` VALUES (106, 10, 21);
INSERT INTO `sys_company_menu` VALUES (107, 10, 18);
INSERT INTO `sys_company_menu` VALUES (108, 16, 9);
INSERT INTO `sys_company_menu` VALUES (109, 16, 23);
INSERT INTO `sys_company_menu` VALUES (110, 16, 10);
INSERT INTO `sys_company_menu` VALUES (111, 16, 11);
INSERT INTO `sys_company_menu` VALUES (112, 16, 12);
INSERT INTO `sys_company_menu` VALUES (113, 16, 13);
INSERT INTO `sys_company_menu` VALUES (114, 16, 19);
INSERT INTO `sys_company_menu` VALUES (115, 16, 20);
INSERT INTO `sys_company_menu` VALUES (116, 16, 15);
INSERT INTO `sys_company_menu` VALUES (117, 16, 16);
INSERT INTO `sys_company_menu` VALUES (118, 16, 17);
INSERT INTO `sys_company_menu` VALUES (129, 16, 9);
INSERT INTO `sys_company_menu` VALUES (130, 16, 23);
INSERT INTO `sys_company_menu` VALUES (131, 16, 10);
INSERT INTO `sys_company_menu` VALUES (132, 16, 11);
INSERT INTO `sys_company_menu` VALUES (133, 16, 12);
INSERT INTO `sys_company_menu` VALUES (134, 16, 13);
INSERT INTO `sys_company_menu` VALUES (135, 16, 19);
INSERT INTO `sys_company_menu` VALUES (136, 16, 20);
INSERT INTO `sys_company_menu` VALUES (137, 16, 17);
INSERT INTO `sys_company_menu` VALUES (138, 16, 15);
INSERT INTO `sys_company_menu` VALUES (148, 16, 9);
INSERT INTO `sys_company_menu` VALUES (149, 16, 23);
INSERT INTO `sys_company_menu` VALUES (150, 16, 10);
INSERT INTO `sys_company_menu` VALUES (151, 16, 11);
INSERT INTO `sys_company_menu` VALUES (152, 16, 12);
INSERT INTO `sys_company_menu` VALUES (153, 16, 13);
INSERT INTO `sys_company_menu` VALUES (154, 16, 19);
INSERT INTO `sys_company_menu` VALUES (168, 16, 9);
INSERT INTO `sys_company_menu` VALUES (169, 16, 23);
INSERT INTO `sys_company_menu` VALUES (170, 16, 10);
INSERT INTO `sys_company_menu` VALUES (171, 16, 11);
INSERT INTO `sys_company_menu` VALUES (172, 16, 12);
INSERT INTO `sys_company_menu` VALUES (173, 16, 13);
INSERT INTO `sys_company_menu` VALUES (174, 16, 19);
INSERT INTO `sys_company_menu` VALUES (175, 16, 15);
INSERT INTO `sys_company_menu` VALUES (176, 16, 16);
INSERT INTO `sys_company_menu` VALUES (177, 16, 17);
INSERT INTO `sys_company_menu` VALUES (217, 14, 9);
INSERT INTO `sys_company_menu` VALUES (218, 14, 23);
INSERT INTO `sys_company_menu` VALUES (219, 14, 10);
INSERT INTO `sys_company_menu` VALUES (220, 14, 11);
INSERT INTO `sys_company_menu` VALUES (221, 14, 12);
INSERT INTO `sys_company_menu` VALUES (222, 14, 13);
INSERT INTO `sys_company_menu` VALUES (223, 14, 19);
INSERT INTO `sys_company_menu` VALUES (257, 0, 10);
INSERT INTO `sys_company_menu` VALUES (258, 0, 11);
INSERT INTO `sys_company_menu` VALUES (259, 0, 12);
INSERT INTO `sys_company_menu` VALUES (260, 0, 13);
INSERT INTO `sys_company_menu` VALUES (261, 0, 9);

-- ----------------------------
-- Table structure for sys_dictionary
-- ----------------------------
DROP TABLE IF EXISTS `sys_dictionary`;
CREATE TABLE `sys_dictionary`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `sign` char(8) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '字典标识',
  `description` char(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '字典描述',
  `parent_id` int(11) NULL DEFAULT NULL COMMENT '所属字典',
  `del_flag` bit(1) NULL DEFAULT b'0' COMMENT '是否逻辑删除1删除0正常',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '字典表，用来存储下拉数据中的单选或者多选选项。' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_dictionary
-- ----------------------------

-- ----------------------------
-- Table structure for sys_file
-- ----------------------------
DROP TABLE IF EXISTS `sys_file`;
CREATE TABLE `sys_file`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '文件id',
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '文件名称',
  `url` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '文件地址',
  `size` char(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '文件大小',
  `type` char(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '文件类型',
  `gmt_create` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `status` int(1) NULL DEFAULT 1 COMMENT '文件状态（1：正常，0：已删除）',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '文件表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_file
-- ----------------------------

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `name` char(16) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '目录名称',
  `parent_id` int(11) NULL DEFAULT NULL COMMENT '父目录ID',
  `url` char(16) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '目录跳转的路径',
  `icon` char(16) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '图标',
  `sort` int(11) NULL DEFAULT NULL COMMENT '同级别的顺序，从0开始',
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '描述',
  `create_id` int(11) NULL DEFAULT NULL COMMENT '创建人ID',
  `create_name` char(8) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '创建人昵称',
  `gmt_create` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 24 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '菜单表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES (9, '设置管理', NULL, 'settingManage', 'txitongshezhi', 0, NULL, NULL, '反对反对', '2020-12-25 05:07:27');
INSERT INTO `sys_menu` VALUES (10, '用户管理', 9, 'userManage', 'trenyuanguanli', 1, NULL, NULL, NULL, '2020-12-25 05:08:58');
INSERT INTO `sys_menu` VALUES (11, '公司管理', 9, 'companyManage', 'tgongsi1', 2, NULL, NULL, NULL, '2020-12-25 05:11:12');
INSERT INTO `sys_menu` VALUES (12, '菜单管理', 9, 'menuManage', 'tliebiao', 3, NULL, NULL, NULL, '2020-12-25 05:11:59');
INSERT INTO `sys_menu` VALUES (13, '角色管理', 9, 'roleManage', 'tjiaoseguanli', 4, NULL, NULL, NULL, '2020-12-25 05:12:31');
INSERT INTO `sys_menu` VALUES (15, '测试啊', NULL, 'test3333', 'tzhaotoubiao', 9, NULL, NULL, NULL, '2020-12-28 08:01:25');
INSERT INTO `sys_menu` VALUES (16, 'lalalal', 15, '233333', NULL, 0, NULL, NULL, NULL, '2020-12-28 08:02:40');
INSERT INTO `sys_menu` VALUES (17, '123123123', 15, 'mysert', 'tguoqi1', 10, NULL, NULL, NULL, '2020-12-28 08:03:11');
INSERT INTO `sys_menu` VALUES (18, 'addddd', NULL, 'lidscc', 'txitongguanli', 3453, NULL, NULL, NULL, '2020-12-28 08:03:57');
INSERT INTO `sys_menu` VALUES (19, 'ceshi22', 9, 'ceshi22', 'tmoban1', 56, NULL, NULL, NULL, '2020-12-28 08:05:00');
INSERT INTO `sys_menu` VALUES (20, 'lalalalla', NULL, 'lalalalal', '', 0, '', 0, '', '2020-12-30 02:07:54');
INSERT INTO `sys_menu` VALUES (21, 'mytest', 18, 'testrrrrrrrrrrr', 'trenyuanguanli', NULL, NULL, NULL, NULL, '2020-12-30 02:09:47');
INSERT INTO `sys_menu` VALUES (22, '233333', 18, 'st233333', 'tjichuxinxi1', NULL, NULL, NULL, NULL, '2020-12-30 02:10:23');
INSERT INTO `sys_menu` VALUES (23, 'sehezhisf', 9, 'sehezhisf', 'tmoban', NULL, NULL, NULL, NULL, '2020-12-30 02:12:34');

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `company_id` int(11) NULL DEFAULT NULL COMMENT '公司ID',
  `name` varchar(40) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '角色名称',
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '角色描述',
  `create_id` int(11) NULL DEFAULT NULL COMMENT '创建人ID',
  `create_name` char(50) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '创建人昵称',
  `gmt_create` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 29 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '角色表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES (2, 0, 'ui工程师', '设计ui图形化界面', 0, '梁缘', NULL);
INSERT INTO `sys_role` VALUES (4, 0, '前端工程师', '写前端的', 0, '梁缘', '2020-12-23 14:08:20');
INSERT INTO `sys_role` VALUES (5, 0, '测试工程师', '测试软件', 0, '梁缘', NULL);
INSERT INTO `sys_role` VALUES (6, 1, '产品经理', '负责产品全生命周期', 0, '肖志', NULL);
INSERT INTO `sys_role` VALUES (7, 0, '测试工程师', '测试软件', 0, '梁缘', NULL);
INSERT INTO `sys_role` VALUES (8, 1, '运维工程师', '售后维护工程师', 0, '梁缘', NULL);
INSERT INTO `sys_role` VALUES (10, 0, '主公司超级管理员', '拥有所有的权限', 0, NULL, '2020-12-22 15:08:12');
INSERT INTO `sys_role` VALUES (22, 10, '测试公司2公司管理员', '测试公司2公司管理员', 0, '', NULL);
INSERT INTO `sys_role` VALUES (23, 11, '测试33公司管理员', '测试33公司管理员', NULL, NULL, NULL);
INSERT INTO `sys_role` VALUES (26, 0, '测试公2公司管理员', '测试公2公司管理员', 0, '', NULL);
INSERT INTO `sys_role` VALUES (28, 16, '莱柯睿思公司管理员', '莱柯睿思公司管理员', NULL, NULL, NULL);

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `role_id` int(11) NULL DEFAULT NULL COMMENT '角色ID',
  `menu_id` int(11) NULL DEFAULT NULL COMMENT '菜单ID',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 161 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '角色对应的菜单表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------
INSERT INTO `sys_role_menu` VALUES (25, 7, 9);
INSERT INTO `sys_role_menu` VALUES (26, 7, 10);
INSERT INTO `sys_role_menu` VALUES (27, 7, 11);
INSERT INTO `sys_role_menu` VALUES (28, 7, 12);
INSERT INTO `sys_role_menu` VALUES (29, 7, 13);
INSERT INTO `sys_role_menu` VALUES (30, 7, 19);
INSERT INTO `sys_role_menu` VALUES (31, 22, 10);
INSERT INTO `sys_role_menu` VALUES (32, 22, 11);
INSERT INTO `sys_role_menu` VALUES (33, 22, 19);
INSERT INTO `sys_role_menu` VALUES (34, 22, 15);
INSERT INTO `sys_role_menu` VALUES (35, 22, 17);
INSERT INTO `sys_role_menu` VALUES (36, 22, 9);
INSERT INTO `sys_role_menu` VALUES (37, 12, 10);
INSERT INTO `sys_role_menu` VALUES (38, 12, 12);
INSERT INTO `sys_role_menu` VALUES (39, 12, 15);
INSERT INTO `sys_role_menu` VALUES (40, 12, 17);
INSERT INTO `sys_role_menu` VALUES (41, 12, 9);
INSERT INTO `sys_role_menu` VALUES (73, 5, 10);
INSERT INTO `sys_role_menu` VALUES (74, 5, 19);
INSERT INTO `sys_role_menu` VALUES (75, 5, 9);
INSERT INTO `sys_role_menu` VALUES (131, 6, 18);
INSERT INTO `sys_role_menu` VALUES (132, 6, 21);
INSERT INTO `sys_role_menu` VALUES (133, 6, 22);
INSERT INTO `sys_role_menu` VALUES (145, 4, 9);
INSERT INTO `sys_role_menu` VALUES (146, 4, 10);
INSERT INTO `sys_role_menu` VALUES (147, 4, 11);
INSERT INTO `sys_role_menu` VALUES (148, 4, 12);
INSERT INTO `sys_role_menu` VALUES (149, 4, 13);
INSERT INTO `sys_role_menu` VALUES (150, 4, 19);
INSERT INTO `sys_role_menu` VALUES (151, 4, 23);
INSERT INTO `sys_role_menu` VALUES (154, 2, 12);
INSERT INTO `sys_role_menu` VALUES (155, 2, 13);
INSERT INTO `sys_role_menu` VALUES (156, 2, 19);
INSERT INTO `sys_role_menu` VALUES (157, 2, 23);
INSERT INTO `sys_role_menu` VALUES (158, 2, 22);
INSERT INTO `sys_role_menu` VALUES (159, 2, 9);
INSERT INTO `sys_role_menu` VALUES (160, 2, 18);

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `company_id` int(11) NULL DEFAULT NULL COMMENT '公司ID',
  `name` varchar(35) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '昵称/姓名',
  `initials` char(8) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '昵称首字母',
  `phone` char(11) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '手机号',
  `username` char(11) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '用户名(同手机号)',
  `password` char(64) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '密码',
  `role_id` int(11) NULL DEFAULT NULL COMMENT '角色ID',
  `create_id` int(11) NULL DEFAULT NULL COMMENT '创建人ID',
  `create_name` char(8) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '创建人昵称',
  `image` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '头像',
  `gmt_create` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `statue` tinyint(1) NULL DEFAULT NULL COMMENT '1正常0禁用',
  `del_flag` bit(1) NULL DEFAULT NULL COMMENT '1删除0正常',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 37 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '用户表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES (2, 0, 'admin', 'admin', '', 'admin', '$2a$10$0sT.G.4D1phoDJgf/g4Zye6o30WownZR6ZPYr82aaCRNIp2dj/Nfu', NULL, 1, '11', '', '2020-12-24 09:58:16', 1, b'1');
INSERT INTO `sys_user` VALUES (3, 0, 'aaaabbb', 'aaaabbb', '13342587425', '13342587425', '$2a$10$JfHzrx4FMoCGdKyeO.0uZePEvJFHmB5oHrt5IjY8Nw9J6aR4kp8ZG', 4, 1, '11', '', '2020-12-24 09:58:39', 0, b'1');
INSERT INTO `sys_user` VALUES (5, 0, 'abcd', 'abcde', '', '15882574aaa', '$2a$10$BQiuBfSEQ784OInWa5qYpOaP7CdzsSe5PbQXe1veu1QMmGWj0LvX6', 1, 1, '11', '', '2020-12-24 10:01:02', 0, b'1');
INSERT INTO `sys_user` VALUES (10, 0, '梁缘', 'ly', '18011471135', 'ly', '$2a$10$3Sv5dnegCkc1zdY6/cWlfuvcj/IvbquJNVeiXGoEAnixEfu9GOQZC', 4, 0, '梁缘', '/static/0/tx/f15eeec528c102f63f006da94a25e992.jpg', '2020-09-27 17:10:15', 1, b'0');
INSERT INTO `sys_user` VALUES (14, 10, '测试公司2管理员', 'csgs2gly', '17658214759', '17658214759', '$2a$10$6VziYo3gsDLtD1Gj/V.LaOBovDM7Mfk8bAnf9JYa7Xci5DUvx56IW', 22, 0, '', NULL, '2020-12-29 01:38:13', 1, b'0');
INSERT INTO `sys_user` VALUES (15, 11, '测试33管理员', 'cs33gly', '15214587469', '15214587469', '$2a$10$SH9LYluf1GUbOj7v4g/5CuxH4YAJ.ZApaD7yCYT6v2tkPbSLKgrYu', 23, NULL, NULL, NULL, '2020-12-29 01:39:05', 1, NULL);
INSERT INTO `sys_user` VALUES (16, 0, 'lycoris', 'lycoris', '18302547859', '18302547859', '$2a$10$sqi5U/tfQpq6yW0bAUkSIuTBL2lHvO.zYlMWK/H0IrBz.DlgykVSi', 4, 10, '梁缘', NULL, '2020-12-29 04:17:06', NULL, NULL);
INSERT INTO `sys_user` VALUES (17, 0, '刘婷婷', 'ltt', '18011471163', '18011471163', '$2a$10$ErcSRBFKdxKqGOTlspmS2uHlvLNCBm0fllZ03d0.AGlJCXVCMmlPy', 4, 10, '梁缘', NULL, '2020-12-29 04:18:21', NULL, NULL);
INSERT INTO `sys_user` VALUES (18, 0, '测试公2管理员', 'csg2gly', '18825487521', '18825487521', '$2a$10$UfSH9XLwKsFOrnRdCO8kAuQkMYF84g6nXv5wJyehJw9MqlVOojelK', 26, 0, '', NULL, '2020-12-29 05:18:47', 1, b'0');
INSERT INTO `sys_user` VALUES (19, 0, '搜索', 'ss', '18780049791', '18780049791', '$2a$10$elZOJ98H3AGgs55n.7gRz.yNgyN9E87LSlKBlezkfTn9gJfUXnG4u', 2, 10, '梁缘', NULL, '2020-12-29 05:33:24', NULL, NULL);
INSERT INTO `sys_user` VALUES (20, 0, '林楚', 'lc', '18507892151', '18507892151', '$2a$10$GP6ueOA3wJyRxHYRhPWRt.7HaZrbYP7hhGGx0MBbppcuKGuLYR8yq', 7, 10, '梁缘', NULL, '2020-12-29 05:40:20', NULL, NULL);
INSERT INTO `sys_user` VALUES (21, 0, 'lisaf', 'lisaf', '13345823569', '13345823569', '$2a$10$4nTwyaW6N9JgXDk/f1XSDO2iQkZJgqlOeYoHWpvIMGvHBEmBUhZ6S', 7, 10, '梁缘', NULL, '2020-12-29 07:20:39', NULL, NULL);
INSERT INTO `sys_user` VALUES (23, 0, '测试345345', 'cs345345', '13525842576', '13525842576', '$2a$10$Gn3GbT/7G8ucQy/PQRh7qO5uCqOMuE1zb9oQXbg8YF/xoWN07qXTC', 7, 10, '梁缘', '/static/0/tx/b8c69ccb9a8cea02a077195996139344.jpeg', '2020-12-29 07:57:58', 1, b'1');
INSERT INTO `sys_user` VALUES (24, 0, '林楚', 'lc', '18011471135', '18011471135', '$2a$10$zJzbjwmQ2aVrZXc/cFN0IugBNMnNWd1fEqOSbdtPfnV.k/Q27ikhO', 5, 10, '梁缘', '/static/0/tx/75e87837914c29dd95767f5461328672.jpeg', '2020-12-29 22:28:05', NULL, NULL);
INSERT INTO `sys_user` VALUES (26, 0, 'liliya', 'liliya', '13347258459', '13347258459', '$2a$10$EYiBn.Bi/1wLHKk4PSmgaeC3jP4m/b4bcNjwwNXPtBdnlI1tEvYNe', 10, 10, '梁缘', '', '2020-12-29 22:38:46', NULL, NULL);
INSERT INTO `sys_user` VALUES (27, 0, '苏梦枕', 'smz', '18011412275', '18011412275', '$2a$10$8jMTMaSmlO1kn6EIA0Ke2utT9Igzh24p7oRPh.LsB6DaAmJI0fFUW', NULL, 10, '梁缘', '', '2020-12-29 22:43:54', 0, b'1');
INSERT INTO `sys_user` VALUES (28, 0, '肖志', 'xz', '12345444', '12345444', '$2a$10$9XephMuvgEz7rcWmgRy2bOH2FhvHNmvCNBV2vPEF7Y6elkMA/WqMC', 4, 10, '梁缘', NULL, '2020-12-29 22:45:46', 0, NULL);
INSERT INTO `sys_user` VALUES (29, 0, '苏梦耽', 'smd', '18011412375', '18011412375', '$2a$10$LHFoGi/E2uiE9Gu4lfsEmec7CLz1/4RtLtHahDiasppfGo4Mxf0/.', 4, 10, '梁缘', '', '2020-12-29 22:47:19', 0, b'1');
INSERT INTO `sys_user` VALUES (30, 0, '顾惜朝', 'gxc', '18952487236', '18952487236', '$2a$10$CL/6TW8Oc5WDml3.dhhscOfeRMXDggFLbL321UUYYjnFl1idptdwK', 7, 10, '梁缘', '/static/0/tx/e916077582386f57b6d6afc4b65de613.jpg', '2020-12-29 22:48:14', 0, NULL);
INSERT INTO `sys_user` VALUES (33, 0, 'lance', 'lance', '18702543698', '18702543698', '$2a$10$NeKz1d4TMolNo5t4pi.bGOkXFtRvg2TDfYCmMUfELhxtAyv6csmOS', 4, 10, '梁缘', '/static/0/tx/a087275d031eba73033b008a80d89b9a.jpg', '2020-12-29 23:44:12', 1, NULL);
INSERT INTO `sys_user` VALUES (34, 16, '莱柯睿思管理员', 'lkrsgly', '18812345678', '18812345678', '$2a$10$JmaHNGtKXhjSq8fFHVJ.sedpRWkNOvCHNkQOWzNs61QAaQv1zIMAy', 28, NULL, NULL, NULL, '2020-12-30 03:47:38', 1, b'0');
INSERT INTO `sys_user` VALUES (35, 0, 'saber', 'saber', '13547256983', '13547256983', '$2a$10$ejkMqjydJ4bDuXr4JPOSbOSVZ7f4w.9ZcoQggKfn.zzJSN1aOjxQu', 5, 10, '梁缘', '/static/0/tx/a087275d031eba73033b008a80d89b9a.jpg', '2020-12-30 22:06:04', 1, NULL);
INSERT INTO `sys_user` VALUES (36, 0, '看看', 'kk', '13582426958', '13582426958', '$2a$10$HwXFtTLz2wDZrp5Ge1dKC.6/uwZAvAyeeFG12Qcq.bEGs/BzQhTqW', 2, 10, '梁缘', '', '2020-12-31 01:55:45', 1, NULL);

-- ----------------------------
-- Table structure for sys_user_favorite_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_favorite_menu`;
CREATE TABLE `sys_user_favorite_menu`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `user_id` int(11) NULL DEFAULT NULL COMMENT '用户ID',
  `menu_id` int(11) NULL DEFAULT NULL COMMENT '菜单ID',
  `gmt_create` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `sort` int(11) NULL DEFAULT NULL COMMENT '排序',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 72 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '用户收藏的目录' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_user_favorite_menu
-- ----------------------------
INSERT INTO `sys_user_favorite_menu` VALUES (10, 10, 10, NULL, 0);
INSERT INTO `sys_user_favorite_menu` VALUES (65, 10, NULL, NULL, 0);
INSERT INTO `sys_user_favorite_menu` VALUES (66, 10, NULL, NULL, 0);
INSERT INTO `sys_user_favorite_menu` VALUES (67, 10, NULL, NULL, 0);
INSERT INTO `sys_user_favorite_menu` VALUES (68, 10, NULL, NULL, 0);
INSERT INTO `sys_user_favorite_menu` VALUES (69, 10, NULL, NULL, 0);
INSERT INTO `sys_user_favorite_menu` VALUES (70, 10, NULL, NULL, 0);
INSERT INTO `sys_user_favorite_menu` VALUES (71, 10, NULL, NULL, 0);

SET FOREIGN_KEY_CHECKS = 1;
