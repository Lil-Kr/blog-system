/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 80017
 Source Host           : localhost:3306
 Source Schema         : permission

 Target Server Type    : MySQL
 Target Server Version : 80017
 File Encoding         : 65001

 Date: 13/04/2022 23:55:14
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sys_acl
-- ----------------------------
DROP TABLE IF EXISTS `sys_acl`;
CREATE TABLE `sys_acl`  (
  `id` bigint(22) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `surrogate_id` bigint(22) NOT NULL COMMENT '权限id唯一主键',
  `number` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '权限编码',
  `name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '权限名',
  `acl_module_id` bigint(22) NOT NULL COMMENT '权限模块id',
  `url` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '请求的url',
  `type` int(2) NOT NULL DEFAULT 1 COMMENT '1:菜单权限, 2按钮, 3其他',
  `status` int(2) DEFAULT NULL COMMENT '状态, 0正常, 冻结',
  `seq` int(2) DEFAULT NULL COMMENT '排序',
  `remark` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '备注',
  `operator` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '操作人',
  `operate_ip` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '操作ip',
  `create_time` datetime(0) NOT NULL COMMENT '创建时间',
  `update_time` datetime(0) NOT NULL COMMENT '更改时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `idx_surrogate_key`(`surrogate_id`) USING BTREE COMMENT '唯一键'
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_acl
-- ----------------------------
INSERT INTO `sys_acl` VALUES (1, 1334005930790096896, 'ACL1334005930790096896', '用户管理', 1332721906033496064, '/sys/user/listAll', 1, 0, 0, '这是用户管理菜单', 'R2884', '127.0.0.1', '2020-12-02 13:28:39', '2020-12-02 13:28:41');
INSERT INTO `sys_acl` VALUES (2, 1334023597823496192, 'ACL1334023597823496192', '新增用户', 1332721906033496064, '/sys/user/add', 2, 0, 1, '新增用户按钮', 'R2884', '127.0.0.1', '2020-12-02 14:38:17', '2020-12-02 14:38:19');
INSERT INTO `sys_acl` VALUES (4, 1334030642446471168, 'ACL1334030642446471168', '编辑用户', 1332721906033496064, '/sys/user/edit', 2, 0, 2, '编辑用户', 'R2884', '127.0.0.1', '2020-12-02 15:04:24', '2020-12-02 15:12:40');

-- ----------------------------
-- Table structure for sys_acl_module
-- ----------------------------
DROP TABLE IF EXISTS `sys_acl_module`;
CREATE TABLE `sys_acl_module`  (
  `id` bigint(22) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `surrogate_id` bigint(22) NOT NULL COMMENT '权限模块id,唯一主键',
  `number` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '权限模块编码',
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '权限模块名称',
  `parent_id` bigint(22) NOT NULL COMMENT '父surrogate_id',
  `level` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '权限模块层级',
  `seq` int(2) NOT NULL COMMENT '顺序',
  `status` int(1) NOT NULL DEFAULT 0 COMMENT '0正常, 1冻结',
  `remark` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '备注',
  `operator` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '操作人',
  `operate_ip` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '操作ip',
  `create_time` datetime(0) NOT NULL COMMENT '创建时间',
  `update_time` datetime(0) NOT NULL COMMENT '更改时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `idx_surrogate_key`(`surrogate_id`) USING BTREE COMMENT '唯一键'
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_acl_module
-- ----------------------------
INSERT INTO `sys_acl_module` VALUES (0, 1332721906033496064, 'ACLM1332721906033496064', '权限模块1', 0, '0', 0, 0, '权限模块1', 'system', '127.0.0.1', '2020-11-29 00:23:01', '2020-11-29 00:32:01');
INSERT INTO `sys_acl_module` VALUES (2, 1332993819473481728, 'ACLM1332993819473481728', '权限模块2', 0, '0', 0, 0, NULL, 'system', '127.0.0.1', '2020-11-29 18:24:27', '2020-11-29 18:24:27');
INSERT INTO `sys_acl_module` VALUES (5, 1333001502767321088, 'ACLM1333001502767321088', '权限模块2-1', 1332993819473481728, '0.2', 1, 0, NULL, 'system', '127.0.0.1', '2020-11-29 18:54:58', '2020-11-29 18:59:27');
INSERT INTO `sys_acl_module` VALUES (6, 1333002627360886784, 'ACLM1333002627360886784', '权限模块1-2', 1332721906033496064, '0.1', 1, 0, NULL, 'system', '127.0.0.1', '2020-11-29 18:59:27', '2020-11-29 18:59:27');
INSERT INTO `sys_acl_module` VALUES (7, 1333002858555117568, 'ACLM1333002858555117568', '权限模块1-1', 1332721906033496064, '0.1', 0, 0, NULL, 'system', '127.0.0.1', '2020-11-29 18:59:27', '2020-11-29 18:59:27');

-- ----------------------------
-- Table structure for sys_dict
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict`;
CREATE TABLE `sys_dict`  (
  `id` bigint(22) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `surrogate_id` bigint(22) NOT NULL COMMENT '数据字典id唯一主键',
  `name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '数据字典名称',
  `remark` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '备注',
  `operator` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '' COMMENT '操作人',
  `operate_ip` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '操作ip',
  `create_time` datetime(0) NOT NULL COMMENT '创建时间',
  `update_time` datetime(0) NOT NULL COMMENT '更改时间',
  `deleted` int(1) DEFAULT NULL COMMENT '删除状态, 0正常, 1删除',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `idx_surrogate_id`(`surrogate_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_dict
-- ----------------------------
INSERT INTO `sys_dict` VALUES (1, 1334038283956654080, '权限点类型', '权限点类型', 'R2884', '127.0.0.1', '2020-12-02 15:35:20', '2020-12-02 15:35:25', 0);
INSERT INTO `sys_dict` VALUES (2, 1334072837190848512, '角色类型', '角色类型', 'R2884', '127.0.0.1', '2020-12-02 17:52:39', '2020-12-02 17:52:42', 0);

-- ----------------------------
-- Table structure for sys_dict_detail
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict_detail`;
CREATE TABLE `sys_dict_detail`  (
  `surrogate_id` bigint(22) NOT NULL COMMENT '数据字典id唯一主键',
  `parent_id` bigint(22) NOT NULL COMMENT '数据字典主表id',
  `type` int(2) NOT NULL COMMENT '具体类型',
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '数据字典明细名称',
  `remark` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`surrogate_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_dict_detail
-- ----------------------------
INSERT INTO `sys_dict_detail` VALUES (1334038769707388928, 1334038283956654080, 1, '菜单', '菜单类型');
INSERT INTO `sys_dict_detail` VALUES (1334039373016076288, 1334038283956654080, 2, '按钮', '按钮类型');
INSERT INTO `sys_dict_detail` VALUES (1334039520135483392, 1334038283956654080, 3, '查看数据列表', '查看数据列表类型');
INSERT INTO `sys_dict_detail` VALUES (1334039880686243840, 1334038283956654080, 100, '其他', '其他类型');

-- ----------------------------
-- Table structure for sys_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_log`;
CREATE TABLE `sys_log`  (
  `id` bigint(22) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `surrogate_id` bigint(22) NOT NULL COMMENT '日志id,唯一主键',
  `type` int(2) NOT NULL COMMENT '1部门，2用户，3权限模块, 4权限， 5角色, 6角色用户关系, 7角色权限关系',
  `target_id` bigint(22) NOT NULL COMMENT '各个模块的主键id, 涉及到关联关系的操作存放的是角色id',
  `old_value` text CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '更新前的值',
  `new_value` text CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '更新后的值',
  `status` int(2) DEFAULT NULL COMMENT '状态, 当前是否复原过, 0没有, 1复原过',
  `remark` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '备注',
  `operator` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '操作人',
  `operate_ip` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '操作ip',
  `create_time` datetime(0) NOT NULL COMMENT '创建时间',
  `update_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更改时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `idx_surrogate_key`(`surrogate_id`) USING BTREE COMMENT '唯一id'
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_org
-- ----------------------------
DROP TABLE IF EXISTS `sys_org`;
CREATE TABLE `sys_org`  (
  `id` bigint(22) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `surrogate_id` bigint(22) NOT NULL COMMENT '唯一主键',
  `number` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '部门编号',
  `name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '部门名称',
  `parent_id` bigint(22) NOT NULL COMMENT '父id',
  `level` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '部门层级, 0,  0.1, 0.2',
  `seq` int(2) DEFAULT NULL COMMENT '排序, 部门咋当前层级目录下的顺序',
  `remark` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '备注',
  `operator` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '操作人',
  `operate_ip` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '操作ip',
  `create_time` datetime(0) NOT NULL COMMENT '创建时间',
  `update_time` datetime(0) NOT NULL COMMENT '更改时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `idx_surrogate_key`(`surrogate_id`) USING BTREE COMMENT '唯一主键'
) ENGINE = InnoDB AUTO_INCREMENT = 16 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_org
-- ----------------------------
INSERT INTO `sys_org` VALUES (1, 1331597298538516480, 'DEPT1331597298538516480', '广州若羽臣科技股份有限公司', 0, '0', 1, '广州若羽臣科技股份有限公司', 'system', '127.0.0.1', '2020-11-25 21:55:10', '2020-11-25 21:55:10');
INSERT INTO `sys_org` VALUES (3, 1331599220599296000, 'DEPT1331599220599296000', '中台管理体系', 1331597298538516480, '0.1', 2, '中台管理体系', 'system', '127.0.0.1', '2020-11-25 22:05:18', '2020-11-25 21:55:10');
INSERT INTO `sys_org` VALUES (4, 1331599651484340224, 'DEPT1331599651484340224', '信息化中心', 1331599220599296000, '0.1.3', 2, '信息化中心', 'system', '127.0.0.1', '2020-11-04 22:54:48', '2020-11-26 21:27:44');
INSERT INTO `sys_org` VALUES (5, 1331599887577518080, 'DEPT1331599887577518080', '全栈开发部', 1331599651484340224, '0.1.3.4', 1, '全栈开发部', 'system', '127.0.0.1', '2020-11-25 21:55:10', '2020-11-26 21:27:44');
INSERT INTO `sys_org` VALUES (6, 1331600039633620992, 'DEPT1331600039633620992', '商业智能部门', 1331599651484340224, '0.1.3.4', 2, '商业智能部门', 'system', '127.0.0.1', '2020-11-25 22:06:17', '2020-11-26 21:27:44');
INSERT INTO `sys_org` VALUES (7, 1331600304105459712, 'DEPT1331600304105459712', '运维部门', 1331599651484340224, '0.1.3.4', 3, '运维部门', 'system', '127.0.0.1', '2020-11-25 22:33:33', '2020-11-26 21:27:44');
INSERT INTO `sys_org` VALUES (9, 1331633033643692032, 'DEPT1331633033643692032', '大可事业部', 1331597298538516480, '0.1', 3, '大可事业部', 'system', '127.0.0.1', '2020-11-26 00:17:10', '2020-11-26 00:17:10');
INSERT INTO `sys_org` VALUES (10, 1331651639584624640, 'DEPT1331651639584624640', '供应链中心', 1331597298538516480, '0.1', 1, '供应链中心', 'system', '127.0.0.1', '2020-11-26 01:31:06', '2020-11-26 01:31:06');
INSERT INTO `sys_org` VALUES (11, 1331651749244702720, 'DEPT1331651749244702720', '订单部', 1331651639584624640, '0.1.10', 1, '订单部', 'system', '127.0.0.1', '2020-11-26 01:31:06', '2020-11-26 01:31:06');
INSERT INTO `sys_org` VALUES (12, 1331656293252993024, 'DEPT1331656293252993024', '前端', 1331599887577518080, '0.1.3.4.5', 2, '前端', 'system', '127.0.0.1', '2020-11-26 01:49:35', '2020-11-26 21:27:44');
INSERT INTO `sys_org` VALUES (13, 1331656331899310080, 'DEPT1331656331899310080', '后端', 1331599887577518080, '0.1.3.4.5', 2, '后端', 'system', '127.0.0.1', '2020-11-26 01:49:35', '2020-11-26 21:27:44');
INSERT INTO `sys_org` VALUES (15, 1334700066715340800, 'DEPT1334700066715340800', '数据中心666', 1331599651484340224, '0.1.3.4', 1, '数据中心666', 'R2884', '127.0.0.1', '2020-12-04 11:24:28', '2020-12-04 11:38:20');

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`  (
  `id` bigint(22) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `surrogate_id` bigint(22) NOT NULL COMMENT '角色id唯一主键',
  `name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '角色名称',
  `type` int(2) NOT NULL COMMENT '角色类型, 1超级管理员, 2管理员, 3.普通角色',
  `remark` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '备注',
  `deleted` int(1) DEFAULT NULL COMMENT '删除状态, 0正常, 1删除',
  `operator` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '' COMMENT '操作人',
  `operate_ip` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '操作ip',
  `create_time` datetime(0) NOT NULL COMMENT '创建时间',
  `update_time` datetime(0) NOT NULL COMMENT '更改时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `idx_surrogate_key`(`surrogate_id`) USING BTREE COMMENT '唯一键'
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES (1, 1333709267219386368, '超级管理员', 1, '超级管理员', 0, 'R2884', '127.0.0.1', '2020-12-01 17:46:02', '2020-12-02 12:23:45');
INSERT INTO `sys_role` VALUES (4, 1334072648673660928, '普通管理员', 2, '普通管理员', 0, 'R2884', '127.0.0.1', '2020-12-02 17:53:08', '2020-12-02 17:53:09');

-- ----------------------------
-- Table structure for sys_role_acl
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_acl`;
CREATE TABLE `sys_role_acl`  (
  `id` bigint(22) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `surrogate_id` bigint(22) NOT NULL COMMENT '角色-权限id唯一主键',
  `role_id` bigint(22) DEFAULT NULL COMMENT '角色id',
  `acl_id` bigint(22) DEFAULT NULL COMMENT '权限id',
  `operator` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '操作人',
  `operate_ip` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '操作ip',
  `create_time` datetime(0) NOT NULL COMMENT '创建时间',
  `update_time` datetime(0) NOT NULL COMMENT '更改时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `idx_surrogate_key`(`surrogate_id`) USING BTREE COMMENT '唯一键'
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role_acl
-- ----------------------------
INSERT INTO `sys_role_acl` VALUES (4, 1334062573645074432, 1334072648673660928, 1334005930790096896, 'R2884', '127.0.0.1', '2020-12-02 17:11:17', '2020-12-02 17:11:17');
INSERT INTO `sys_role_acl` VALUES (5, 1334062573645074433, 1334072648673660928, 1334023597823496192, 'R2884', '127.0.0.1', '2020-12-02 17:11:17', '2020-12-02 17:11:17');

-- ----------------------------
-- Table structure for sys_role_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_user`;
CREATE TABLE `sys_role_user`  (
  `id` bigint(22) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `surrogate_id` bigint(22) NOT NULL COMMENT '角色-用户id唯一主键',
  `role_id` bigint(22) NOT NULL COMMENT '角色id',
  `user_id` bigint(22) NOT NULL COMMENT '用户id',
  `operate_ip` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '操作ip',
  `operator` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '操作人',
  `create_time` datetime(0) NOT NULL COMMENT '创建时间',
  `update_time` datetime(0) NOT NULL COMMENT '更改时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `idx_surrogate_key`(`surrogate_id`) USING BTREE COMMENT '唯一键'
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role_user
-- ----------------------------
INSERT INTO `sys_role_user` VALUES (1, 1334016076962467840, 1334072648673660928, 1330756438846476314, '127.0.0.1', 'R2884', '2020-12-02 14:07:38', '2020-12-02 14:07:40');

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
  `id` bigint(22) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `surrogate_id` bigint(22) NOT NULL COMMENT '唯一主键',
  `number` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '员工编号',
  `login_account` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '登录账号',
  `password` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '密码',
  `user_name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '员工姓名',
  `telephone` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '' COMMENT '员工电话',
  `mail` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '邮箱',
  `org_id` bigint(22) DEFAULT NULL COMMENT '用户所在部门id',
  `status` int(1) DEFAULT NULL COMMENT '状态, 0正常, 1冻结',
  `deleted` int(1) DEFAULT NULL COMMENT '0正常, 1删除',
  `operator` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '操作人',
  `operate_ip` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '操作ip',
  `remark` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '备注',
  `create_time` datetime(0) DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) DEFAULT NULL COMMENT '更改时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `idx_surrogate_key`(`surrogate_id`) USING BTREE COMMENT '唯一键'
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES (1, 1330756438846476314, 'R2884', 'R2884', 'e10adc3949ba59abbe56e057f20f883e', '陈羽', NULL, NULL, NULL, 0, 0, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_user` VALUES (2, 1330756438854864908, 'R2885', 'R2885', NULL, 'A', '', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_user` VALUES (3, 1330756438854864910, 'R2886', 'R2886', NULL, 'B', '', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_user` VALUES (4, 1330756438854864912, 'R2887', 'R2887', NULL, 'C', '', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);

SET FOREIGN_KEY_CHECKS = 1;
