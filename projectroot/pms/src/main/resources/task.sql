/*
 Navicat Premium Data Transfer

 Source Server         : 47.108.113.224
 Source Server Type    : MySQL
 Source Server Version : 80021
 Source Host           : 47.108.113.224:3306
 Source Schema         : task

 Target Server Type    : MySQL
 Target Server Version : 80021
 File Encoding         : 65001

 Date: 19/01/2021 13:52:12
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for demand
-- ----------------------------
DROP TABLE IF EXISTS `demand`;
CREATE TABLE `demand`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `project_id` int NULL DEFAULT NULL COMMENT '项目ID',
  `iterative_id` int NULL DEFAULT NULL COMMENT '迭代ID',
  `promoter` char(8) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '需求发起人姓名',
  `context` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '需求内容',
  `create_user_id` int NULL DEFAULT NULL COMMENT '创建人ID',
  `create_user_name` char(8) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '创建人姓名',
  `gmt_create` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `product_prototype_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '产品原型链接地址',
  `ui_prototype_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'UI原型图链接地址',
  `remarks` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '备注',
  `sort` smallint NULL DEFAULT NULL COMMENT '未完成需求紧急情况排序',
  `state` tinyint NULL DEFAULT NULL COMMENT '1已完成0进行中',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of demand
-- ----------------------------
INSERT INTO `demand` VALUES (1, 3, NULL, 'aa', 'aa', 100, '梁缘', '2021-01-19 10:43:08', '', '', NULL, 2, 0);
INSERT INTO `demand` VALUES (2, 3, NULL, 'ff', 'aa', 100, '梁缘', '2021-01-19 10:43:12', '', '', NULL, 1, 0);
INSERT INTO `demand` VALUES (3, 4, NULL, 'aadda', 'adada', 100, '梁缘', '2021-01-19 10:55:40', '', '', NULL, NULL, 0);

-- ----------------------------
-- Table structure for iterative
-- ----------------------------
DROP TABLE IF EXISTS `iterative`;
CREATE TABLE `iterative`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `project_id` int NULL DEFAULT NULL COMMENT '项目ID',
  `name` char(8) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '迭代名字',
  `remarks` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '迭代备注',
  `gmt_start` datetime(0) NULL DEFAULT NULL COMMENT '迭代开始时间',
  `gmt_finish` datetime(0) NULL DEFAULT NULL COMMENT '迭代完成时间',
  `gmt_create` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `create_user_id` int NULL DEFAULT NULL COMMENT '创建用户ID',
  `create_user_name` char(8) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '创建用户姓名',
  `product_prototype_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '产品原型链接地址',
  `ui_prototype_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'UI原型图链接地址',
  `state` tinyint NULL DEFAULT NULL COMMENT '迭代状态1完成，0创建',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '项目迭代表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of iterative
-- ----------------------------

-- ----------------------------
-- Table structure for project
-- ----------------------------
DROP TABLE IF EXISTS `project`;
CREATE TABLE `project`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `company_id` int NULL DEFAULT NULL COMMENT '公司ID',
  `name` char(16) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '项目名称',
  `initials` char(8) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '项目名称首字母',
  `describes` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '项目描述',
  `gmt_create` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `create_user_id` int NULL DEFAULT NULL COMMENT '创建人ID',
  `create_user_name` char(8) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '创建人昵称',
  `state` tinyint NULL DEFAULT NULL COMMENT '0进行中1.完成',
  `del_flag` tinyint NULL DEFAULT 0 COMMENT '1删除0正常',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '公司项目表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of project
-- ----------------------------
INSERT INTO `project` VALUES (3, 0, '测试项目', 'csxm', '我的测试项目', '2021-01-19 10:24:58', 100, '梁缘', 0, 0);
INSERT INTO `project` VALUES (4, 0, '第二个测试项目', 'degcsxm', '第二个测试', '2021-01-19 10:55:21', 100, '梁缘', 0, 0);

-- ----------------------------
-- Table structure for project_user
-- ----------------------------
DROP TABLE IF EXISTS `project_user`;
CREATE TABLE `project_user`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `project_id` int NULL DEFAULT NULL COMMENT '项目ID',
  `user_id` int NULL DEFAULT NULL COMMENT '用户ID',
  `user_name` char(8) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '用户姓名',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '项目关联的用户表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of project_user
-- ----------------------------
INSERT INTO `project_user` VALUES (5, 3, 100, '管理员');
INSERT INTO `project_user` VALUES (6, 4, 100, '管理员');

-- ----------------------------
-- Table structure for sys_company
-- ----------------------------
DROP TABLE IF EXISTS `sys_company`;
CREATE TABLE `sys_company`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `name` varchar(16) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '公司名称',
  `admin_id` int NULL DEFAULT NULL COMMENT '管理员用户ID',
  `admin_name` char(8) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '管理员姓名',
  `phone` char(11) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '管理员手机号',
  `logo` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '公司logo',
  `create_id` int NULL DEFAULT NULL COMMENT '创建人ID',
  `create_name` char(8) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '创建人昵称',
  `gmt_create` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `statue` tinyint(1) NULL DEFAULT NULL COMMENT '公司状态(1-正常,0-禁用)',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 39 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '公司表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_company
-- ----------------------------
INSERT INTO `sys_company` VALUES (0, '任务有限公司2', 100, '管理员', '18780049790', 'static/100/bbfec79b38cb4cb0b4debced8d45f180.jpg', 0, '梁缘', '2020-12-22 15:02:27', 1);
INSERT INTO `sys_company` VALUES (38, '任务有限公司', 108, '呵呵', '18780049791', '', NULL, NULL, '2021-01-19 10:19:14', 0);

-- ----------------------------
-- Table structure for sys_company_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_company_menu`;
CREATE TABLE `sys_company_menu`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `company_id` int NULL DEFAULT NULL COMMENT '公司ID',
  `menu_id` int NULL DEFAULT NULL COMMENT '目录ID',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 128 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '公司对应的菜单表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_company_menu
-- ----------------------------
INSERT INTO `sys_company_menu` VALUES (104, 0, 3);
INSERT INTO `sys_company_menu` VALUES (105, 0, 4);
INSERT INTO `sys_company_menu` VALUES (106, 0, 6);
INSERT INTO `sys_company_menu` VALUES (107, 0, 7);
INSERT INTO `sys_company_menu` VALUES (108, 0, 8);
INSERT INTO `sys_company_menu` VALUES (109, 0, 9);
INSERT INTO `sys_company_menu` VALUES (110, 0, 10);
INSERT INTO `sys_company_menu` VALUES (111, 0, 11);
INSERT INTO `sys_company_menu` VALUES (122, 38, 3);
INSERT INTO `sys_company_menu` VALUES (123, 38, 6);
INSERT INTO `sys_company_menu` VALUES (124, 38, 9);
INSERT INTO `sys_company_menu` VALUES (125, 38, 10);
INSERT INTO `sys_company_menu` VALUES (126, 38, 11);
INSERT INTO `sys_company_menu` VALUES (127, 38, 4);

-- ----------------------------
-- Table structure for sys_dictionary
-- ----------------------------
DROP TABLE IF EXISTS `sys_dictionary`;
CREATE TABLE `sys_dictionary`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `sign` char(8) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '字典标识',
  `description` char(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '字典描述',
  `parent_id` int NULL DEFAULT NULL COMMENT '所属字典',
  `del_flag` bit(1) NULL DEFAULT b'0' COMMENT '是否逻辑删除1删除0正常',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '字典表，用来存储下拉数据中的单选或者多选选项。' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_dictionary
-- ----------------------------

-- ----------------------------
-- Table structure for sys_file
-- ----------------------------
DROP TABLE IF EXISTS `sys_file`;
CREATE TABLE `sys_file`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '文件id',
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '文件名称',
  `url` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '文件地址',
  `size` char(20) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '文件大小',
  `type` char(10) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '文件类型',
  `gmt_create` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `status` int NULL DEFAULT 1 COMMENT '文件状态（1：正常，0：已删除）',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 32 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '文件表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_file
-- ----------------------------
INSERT INTO `sys_file` VALUES (1, '95eef01f3a292df571063fd81da8e36736a87353.jpeg', '/static/0/95eef01f3a292df571063fd81da8e36736a87353.jpeg', '19708', 'jpeg', '2021-01-17 19:20:33', 1);
INSERT INTO `sys_file` VALUES (2, '95eef01f3a292df571063fd81da8e36736a87353.jpeg', '/static/0/95eef01f3a292df571063fd81da8e36736a87353.jpeg', '19708', 'jpeg', '2021-01-17 19:21:36', 1);
INSERT INTO `sys_file` VALUES (3, '95eef01f3a292df571063fd81da8e36736a87353.jpeg', '/static/0/95eef01f3a292df571063fd81da8e36736a87353.jpeg', '19708', 'jpeg', '2021-01-17 19:25:10', 1);
INSERT INTO `sys_file` VALUES (4, '95eef01f3a292df571063fd81da8e36736a87353.jpeg', '/static/0/95eef01f3a292df571063fd81da8e36736a87353.jpeg', '19708', 'jpeg', '2021-01-17 19:25:47', 1);
INSERT INTO `sys_file` VALUES (5, '95eef01f3a292df571063fd81da8e36736a87353.jpeg', '/static/0/95eef01f3a292df571063fd81da8e36736a87353.jpeg', '19708', 'jpeg', '2021-01-17 19:29:01', 1);
INSERT INTO `sys_file` VALUES (6, 'f01c15bb73e1ef3793e64e6b7bbccjpeg.jpeg', '/static/0/f01c15bb73e1ef3793e64e6b7bbccjpeg.jpeg', '447464', 'jpeg', '2021-01-17 19:29:24', 1);
INSERT INTO `sys_file` VALUES (7, 'bbfec79b38cb4cb0b4debced8d45f180.jpg', '/static/0/bbfec79b38cb4cb0b4debced8d45f180.jpg', '206027', 'jpg', '2021-01-17 19:41:24', 1);
INSERT INTO `sys_file` VALUES (8, 'bbfec79b38cb4cb0b4debced8d45f180.jpg', '/static/0/bbfec79b38cb4cb0b4debced8d45f180.jpg', '206027', 'jpg', '2021-01-17 21:57:43', 1);
INSERT INTO `sys_file` VALUES (9, 'bbfec79b38cb4cb0b4debced8d45f180.jpg', '/static/0/bbfec79b38cb4cb0b4debced8d45f180.jpg', '206027', 'jpg', '2021-01-17 22:15:58', 1);
INSERT INTO `sys_file` VALUES (10, '95eef01f3a292df571063fd81da8e36736a87353.jpeg', '/static/0/95eef01f3a292df571063fd81da8e36736a87353.jpeg', '19708', 'jpeg', '2021-01-18 09:59:54', 1);
INSERT INTO `sys_file` VALUES (11, '95eef01f3a292df571063fd81da8e36736a87353.jpeg', '/static/0/95eef01f3a292df571063fd81da8e36736a87353.jpeg', '19708', 'jpeg', '2021-01-18 10:02:33', 1);
INSERT INTO `sys_file` VALUES (12, '95eef01f3a292df571063fd81da8e36736a87353.jpeg', '/static/0/95eef01f3a292df571063fd81da8e36736a87353.jpeg', '19708', 'jpeg', '2021-01-18 10:03:44', 1);
INSERT INTO `sys_file` VALUES (13, 'f01c15bb73e1ef3793e64e6b7bbccjpeg.jpeg', '/static/0/f01c15bb73e1ef3793e64e6b7bbccjpeg.jpeg', '447464', 'jpeg', '2021-01-18 10:59:13', 1);
INSERT INTO `sys_file` VALUES (14, '95eef01f3a292df571063fd81da8e36736a87353.jpeg', '/static/0/95eef01f3a292df571063fd81da8e36736a87353.jpeg', '19708', 'jpeg', '2021-01-18 11:11:34', 1);
INSERT INTO `sys_file` VALUES (15, '95eef01f3a292df571063fd81da8e36736a87353.jpeg', '/static/0/95eef01f3a292df571063fd81da8e36736a87353.jpeg', '19708', 'jpeg', '2021-01-18 11:12:52', 1);
INSERT INTO `sys_file` VALUES (16, '95eef01f3a292df571063fd81da8e36736a87353.jpeg', '/static/0/95eef01f3a292df571063fd81da8e36736a87353.jpeg', '19708', 'jpeg', '2021-01-18 11:32:54', 1);
INSERT INTO `sys_file` VALUES (17, 'f01c15bb73e1ef3793e64e6b7bbccjpeg.jpeg', '/static/0/f01c15bb73e1ef3793e64e6b7bbccjpeg.jpeg', '447464', 'jpeg', '2021-01-18 11:33:13', 1);
INSERT INTO `sys_file` VALUES (18, 'bbfec79b38cb4cb0b4debced8d45f180.jpg', '/static/0/bbfec79b38cb4cb0b4debced8d45f180.jpg', '206027', 'jpg', '2021-01-18 14:11:26', 1);
INSERT INTO `sys_file` VALUES (19, '95eef01f3a292df571063fd81da8e36736a87353.jpeg', '/static/0/95eef01f3a292df571063fd81da8e36736a87353.jpeg', '19708', 'jpeg', '2021-01-18 16:20:07', 1);
INSERT INTO `sys_file` VALUES (20, '95eef01f3a292df571063fd81da8e36736a87353.jpeg', '/static/0/95eef01f3a292df571063fd81da8e36736a87353.jpeg', '19708', 'jpeg', '2021-01-18 16:21:38', 1);
INSERT INTO `sys_file` VALUES (21, '95eef01f3a292df571063fd81da8e36736a87353.jpeg', '/static/0/95eef01f3a292df571063fd81da8e36736a87353.jpeg', '19708', 'jpeg', '2021-01-18 16:25:40', 1);
INSERT INTO `sys_file` VALUES (22, '95eef01f3a292df571063fd81da8e36736a87353.jpeg', '/static/0/95eef01f3a292df571063fd81da8e36736a87353.jpeg', '19708', 'jpeg', '2021-01-18 16:25:53', 1);
INSERT INTO `sys_file` VALUES (23, '95eef01f3a292df571063fd81da8e36736a87353.jpeg', '/static/0/95eef01f3a292df571063fd81da8e36736a87353.jpeg', '19708', 'jpeg', '2021-01-18 16:34:11', 1);
INSERT INTO `sys_file` VALUES (24, 'f01c15bb73e1ef3793e64e6b7bbccjpeg.jpeg', '/static/0/f01c15bb73e1ef3793e64e6b7bbccjpeg.jpeg', '447464', 'jpeg', '2021-01-18 16:36:57', 1);
INSERT INTO `sys_file` VALUES (25, 'bbfec79b38cb4cb0b4debced8d45f180.jpg', '/static/100/bbfec79b38cb4cb0b4debced8d45f180.jpg', '206027', 'jpg', '2021-01-18 17:26:46', 1);
INSERT INTO `sys_file` VALUES (26, 'bbfec79b38cb4cb0b4debced8d45f180.jpg', '/static/100/bbfec79b38cb4cb0b4debced8d45f180.jpg', '206027', 'jpg', '2021-01-18 17:27:06', 1);
INSERT INTO `sys_file` VALUES (27, 'bbfec79b38cb4cb0b4debced8d45f180.jpg', '/static/100/bbfec79b38cb4cb0b4debced8d45f180.jpg', '206027', 'jpg', '2021-01-18 17:54:12', 1);
INSERT INTO `sys_file` VALUES (28, '95eef01f3a292df571063fd81da8e36736a87353.jpeg', '/static/100/95eef01f3a292df571063fd81da8e36736a87353.jpeg', '19708', 'jpeg', '2021-01-18 17:55:05', 1);
INSERT INTO `sys_file` VALUES (29, '95eef01f3a292df571063fd81da8e36736a87353.jpeg', '/static/100/95eef01f3a292df571063fd81da8e36736a87353.jpeg', '19708', 'jpeg', '2021-01-18 17:57:40', 1);
INSERT INTO `sys_file` VALUES (30, 'bbfec79b38cb4cb0b4debced8d45f180.jpg', '/static/100/bbfec79b38cb4cb0b4debced8d45f180.jpg', '206027', 'jpg', '2021-01-18 17:59:38', 1);
INSERT INTO `sys_file` VALUES (31, 'bbfec79b38cb4cb0b4debced8d45f180.jpg', '/static/100/bbfec79b38cb4cb0b4debced8d45f180.jpg', '206027', 'jpg', '2021-01-19 10:34:00', 1);

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `name` char(16) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '目录名称',
  `parent_id` int NULL DEFAULT NULL COMMENT '父目录ID',
  `url` char(16) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '目录跳转的路径',
  `icon` char(16) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '图标',
  `sort` int NULL DEFAULT NULL COMMENT '同级别的顺序，从0开始',
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '描述',
  `create_id` int NULL DEFAULT NULL COMMENT '创建人ID',
  `create_name` char(8) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '创建人昵称',
  `gmt_create` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 18 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '菜单表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES (3, '首页', NULL, 'indexxx', NULL, 1, NULL, NULL, NULL, '2021-01-17 18:13:47');
INSERT INTO `sys_menu` VALUES (4, '系统管理', NULL, '', NULL, 0, NULL, NULL, NULL, '2021-01-17 18:14:08');
INSERT INTO `sys_menu` VALUES (6, '用户管理', 4, 'userManage', NULL, NULL, NULL, NULL, NULL, '2021-01-17 18:17:13');
INSERT INTO `sys_menu` VALUES (7, '公司管理', 4, 'companyManage', NULL, NULL, NULL, NULL, NULL, '2021-01-17 18:17:41');
INSERT INTO `sys_menu` VALUES (8, '菜单管理', 4, 'menuManage', NULL, NULL, NULL, NULL, NULL, '2021-01-17 18:18:02');
INSERT INTO `sys_menu` VALUES (9, '角色管理', 4, 'roleManage', NULL, NULL, NULL, NULL, NULL, '2021-01-17 18:18:36');
INSERT INTO `sys_menu` VALUES (10, '项目管理', 4, 'projectManage', NULL, NULL, NULL, NULL, NULL, '2021-01-17 18:19:04');
INSERT INTO `sys_menu` VALUES (11, '需求管理', 4, 'demand', NULL, NULL, NULL, NULL, NULL, '2021-01-17 18:19:25');

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `company_id` int NULL DEFAULT NULL COMMENT '公司ID',
  `name` char(16) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '角色名称',
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '角色描述',
  `create_id` int NULL DEFAULT NULL COMMENT '创建人ID',
  `create_name` char(50) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '创建人昵称',
  `gmt_create` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 112 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '角色表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES (100, 0, '主公司超级管理员', '拥有所有的权限', 0, NULL, '2020-12-22 15:08:12');
INSERT INTO `sys_role` VALUES (109, 38, '管理员', '任务有限公司的管理员', NULL, NULL, '2021-01-19 10:19:14');
INSERT INTO `sys_role` VALUES (114, 0, '士大夫', 's的', NULL, NULL, '2021-01-19 11:32:02');

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `role_id` int NULL DEFAULT NULL COMMENT '角色ID',
  `menu_id` int NULL DEFAULT NULL COMMENT '菜单ID',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 195 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '角色对应的菜单表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------
INSERT INTO `sys_role_menu` VALUES (169, 100, 3);
INSERT INTO `sys_role_menu` VALUES (170, 100, 4);
INSERT INTO `sys_role_menu` VALUES (171, 100, 6);
INSERT INTO `sys_role_menu` VALUES (172, 100, 7);
INSERT INTO `sys_role_menu` VALUES (173, 100, 8);
INSERT INTO `sys_role_menu` VALUES (174, 100, 9);
INSERT INTO `sys_role_menu` VALUES (175, 100, 10);
INSERT INTO `sys_role_menu` VALUES (176, 100, 11);
INSERT INTO `sys_role_menu` VALUES (187, 109, 3);
INSERT INTO `sys_role_menu` VALUES (188, 109, 6);
INSERT INTO `sys_role_menu` VALUES (189, 109, 9);
INSERT INTO `sys_role_menu` VALUES (190, 109, 10);
INSERT INTO `sys_role_menu` VALUES (191, 109, 11);
INSERT INTO `sys_role_menu` VALUES (192, 109, 4);

-- ----------------------------
-- Table structure for sys_task
-- ----------------------------
DROP TABLE IF EXISTS `sys_task`;
CREATE TABLE `sys_task`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `project_id` int NULL DEFAULT NULL COMMENT '所属项目ID',
  `demand_id` int NULL DEFAULT NULL COMMENT '需求ID',
  `iterative` int NULL DEFAULT NULL COMMENT '迭代ID',
  `create_user_id` int NULL DEFAULT NULL COMMENT '创建人用户ID',
  `create_user_name` char(8) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '创建人昵称',
  `context` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '任务内容',
  `gmt_plan_start` datetime(0) NULL DEFAULT NULL COMMENT '计划开始时间',
  `gmt_plan_end` datetime(0) NULL DEFAULT NULL COMMENT '计划结束时间',
  `plan_minute` smallint NULL DEFAULT NULL COMMENT '计划时间分钟数',
  `gmt_create` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `state` tinyint NULL DEFAULT NULL COMMENT '任务状态-1未完成1完成',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '任务表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_task
-- ----------------------------

-- ----------------------------
-- Table structure for sys_task_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_task_user`;
CREATE TABLE `sys_task_user`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `task_id` int NULL DEFAULT NULL COMMENT '任务ID',
  `user_id` int NULL DEFAULT NULL COMMENT '责任人/关注人ID',
  `user_name` int NULL DEFAULT NULL COMMENT '责任人/关注人昵称',
  `user_type` char(2) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '\"zr\"代表责任人 \"gz\"代表关注人',
  `actual_minute` smallint NULL DEFAULT NULL COMMENT '实际使用分钟数',
  `gmt_start` datetime(0) NULL DEFAULT NULL COMMENT '任务开始时间',
  `gmt_finish` datetime(0) NULL DEFAULT NULL COMMENT '任务完成时间',
  `state` tinyint NULL DEFAULT NULL COMMENT '-1没有开始任务，0开始任务进行中，1完成',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '任务用户表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_task_user
-- ----------------------------

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `company_id` int NULL DEFAULT NULL COMMENT '公司ID',
  `project_id` int NULL DEFAULT NULL COMMENT '项目ID',
  `name` char(8) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '昵称/姓名',
  `initials` char(8) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '昵称首字母',
  `phone` char(11) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '手机号',
  `username` char(11) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '用户名(同手机号)',
  `password` char(64) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '密码',
  `role_id` int NULL DEFAULT NULL COMMENT '角色ID',
  `create_id` int NULL DEFAULT NULL COMMENT '创建人ID',
  `create_name` char(8) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '创建人昵称',
  `image` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '头像',
  `gmt_create` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `statue` tinyint(1) NULL DEFAULT NULL COMMENT '1正常0禁用',
  `del_flag` bit(1) NULL DEFAULT b'0' COMMENT '1删除0正常暂时没用',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 110 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '用户表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES (100, 0, 3, '管理员', 'ly', '18780049790', '18780049790', '$2a$10$3Sv5dnegCkc1zdY6/cWlfuvcj/IvbquJNVeiXGoEAnixEfu9GOQZC', 100, 0, '梁缘', 'static/0/95eef01f3a292df571063fd81da8e36736a87353.jpeg', '2020-09-27 17:10:15', 1, b'0');
INSERT INTO `sys_user` VALUES (108, 38, NULL, '呵呵', 'hh', '18780049791', '18780049791', '$2a$10$sIyBbih6E.tT5KtjLw.nvO/YdM1138YXlTSSi9hIHfZsiWzn5oGRe', 109, NULL, NULL, NULL, '2021-01-19 10:19:14', 1, b'0');
INSERT INTO `sys_user` VALUES (109, 0, NULL, '你好', 'nh', '18780049792', '18780049792', '$2a$10$UcASoo06QlNy/8iXogv8OuwCjGqEtI9fhII3U63vLKBQQTedp4zMS', NULL, 0, '梁缘', 'static/100/bbfec79b38cb4cb0b4debced8d45f180.jpg', '2021-01-19 10:34:18', 1, NULL);

-- ----------------------------
-- Table structure for sys_user_favorite_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_favorite_menu`;
CREATE TABLE `sys_user_favorite_menu`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `user_id` int NULL DEFAULT NULL COMMENT '用户ID',
  `menu_id` int NULL DEFAULT NULL COMMENT '菜单ID',
  `gmt_create` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `sort` int NULL DEFAULT NULL COMMENT '排序',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '用户收藏的目录' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user_favorite_menu
-- ----------------------------

SET FOREIGN_KEY_CHECKS = 1;
