/*
 Navicat Premium Data Transfer

 Source Server         : localhost-root
 Source Server Type    : MySQL
 Source Server Version : 50717
 Source Host           : localhost:3306
 Source Schema         : sky

 Target Server Type    : MySQL
 Target Server Version : 50717
 File Encoding         : 65001

 Date: 09/07/2021 16:09:44
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sys_dictionary
-- ----------------------------
DROP TABLE IF EXISTS `sys_dictionary`;
CREATE TABLE `sys_dictionary`  (
  `code` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '编码',
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '名称',
  `parent_id` int(11) NULL DEFAULT 0 COMMENT '父级ID',
  `parent_code` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '父级编码',
  `parent_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '父级名称',
  `state` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '状态',
  `sort` int(11) NULL DEFAULT 0 COMMENT '顺序',
  `value` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '值',
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `create_by` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '创建人',
  `create_date` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建日期',
  `update_by` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '修改人',
  `update_date` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `version` int(11) NULL DEFAULT 0 COMMENT '版本号',
  `deleted` int(1) NULL DEFAULT 0 COMMENT '是否删除',
  `trace_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '日志ID',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '数据字典' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_dictionary
-- ----------------------------
INSERT INTO `sys_dictionary` VALUES ('', '', 0, '', '', '', 0, '', 2, NULL, '2020-11-04 11:58:47', NULL, '2020-11-04 11:58:47', 0, 0, NULL, '');
INSERT INTO `sys_dictionary` VALUES ('', '', 0, '', '', '', 0, '', 3, NULL, '2020-11-05 17:52:34', NULL, '2020-11-05 17:52:34', 0, 0, NULL, '');
INSERT INTO `sys_dictionary` VALUES ('', '', 0, '', '', '', 0, '', 4, '0', '2020-11-05 17:56:18', '0', '2020-11-05 17:56:18', 0, 0, '0721b6c44ce649e989e0d7368da77b8f', '');
INSERT INTO `sys_dictionary` VALUES ('', '', 0, '', '', '', 0, '', 5, '0', '2020-11-05 17:56:33', '0', '2020-11-05 17:56:33', 0, 0, 'ee1bdde74ba64bc6aa5183b72a6bd89f', '');
INSERT INTO `sys_dictionary` VALUES ('', '', 0, '', '', '', 0, '', 6, '0', '2020-11-06 09:17:34', '0', '2020-11-06 09:17:34', 0, 0, 'bc54a78bc5ae464e82e639997712fb4f', '');
INSERT INTO `sys_dictionary` VALUES ('', '', 0, '', '', '', 0, '', 7, '0', '2020-11-06 09:23:26', '0', '2020-11-06 09:23:26', 0, 0, 'dfb14001a9e14d408b3725c4d2a6d29e', '');
INSERT INTO `sys_dictionary` VALUES ('', '', 0, '', '', '', 0, '', 8, '0', '2020-11-20 10:26:15', '0', '2020-11-20 10:26:15', 0, 0, 'fde4c37a53b94c47a5e447fd080fcf89', '');

-- ----------------------------
-- Table structure for sys_file_info
-- ----------------------------
DROP TABLE IF EXISTS `sys_file_info`;
CREATE TABLE `sys_file_info`  (
  `biz_id` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '业务ID',
  `biz_code` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '业务编码',
  `original_name` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '原文件名称',
  `extend_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '扩展名',
  `name` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '文件名',
  `physical_path` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '物理存储路径',
  `visit_path` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '访问路径',
  `size` int(11) NULL DEFAULT 0 COMMENT '文件大小',
  `content_type` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '文件头类型',
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `create_by` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '创建人',
  `create_date` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建日期',
  `update_by` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '修改人',
  `update_date` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `version` int(11) NULL DEFAULT 0 COMMENT '版本号',
  `deleted` int(1) NULL DEFAULT 0 COMMENT '是否删除',
  `trace_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '日志ID',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '文件信息' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_file_info
-- ----------------------------

-- ----------------------------
-- Table structure for sys_job
-- ----------------------------
DROP TABLE IF EXISTS `sys_job`;
CREATE TABLE `sys_job`  (
  `task_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '任务名称',
  `enable` int(1) NULL DEFAULT 1 COMMENT '任务状态 1=启用 2=禁用',
  `cron_time` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '表达式',
  `has_redo` int(1) NULL DEFAULT 0 COMMENT '是否启动重试机制',
  `end_redo_times` int(2) NULL DEFAULT 0 COMMENT '截止重新执行次数',
  `current_redo_time` int(2) NULL DEFAULT 0 COMMENT '当前第几次自动重试执行',
  `process_status` int(1) NULL DEFAULT 0 COMMENT '是否正在执行中,0--没有正在执行,1--正在手动/自动执行 2--执行失败',
  `process_date` datetime NULL DEFAULT NULL COMMENT '触发时间',
  `process_end_date` datetime NULL DEFAULT NULL COMMENT '结束时间',
  `process_stamp` int(11) NULL DEFAULT 0 COMMENT '耗时',
  `process_msg` blob NULL COMMENT '执行信息',
  `task_group` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '任务分组',
  `http_method` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '请求方式',
  `timing_call_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '调用地址',
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `create_by` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '创建人',
  `create_date` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建日期',
  `update_by` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '修改人',
  `update_date` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `version` int(11) NULL DEFAULT 0 COMMENT '版本号',
  `deleted` int(1) NULL DEFAULT 0 COMMENT '是否删除',
  `trace_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '日志ID',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '任务调度' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_job
-- ----------------------------

-- ----------------------------
-- Table structure for sys_job_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_job_log`;
CREATE TABLE `sys_job_log`  (
  `task_id` int(11) NULL DEFAULT 0 COMMENT '任务ID',
  `response_msg` blob NULL COMMENT '响应信息',
  `begin_time` datetime NULL DEFAULT NULL COMMENT '开始时间',
  `end_time` datetime NULL DEFAULT NULL COMMENT '结束时间',
  `consume_time` int(11) NULL DEFAULT 0 COMMENT '耗时',
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `create_by` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '创建人',
  `create_date` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建日期',
  `update_by` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '修改人',
  `update_date` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `version` int(11) NULL DEFAULT 0 COMMENT '版本号',
  `deleted` int(1) NULL DEFAULT 0 COMMENT '是否删除',
  `trace_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '日志ID',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '任务调度日志' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_job_log
-- ----------------------------

-- ----------------------------
-- Table structure for sys_login_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_login_log`;
CREATE TABLE `sys_login_log`  (
  `state` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '状态',
  `login_ip` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '登录IP',
  `login_time` datetime NULL DEFAULT NULL COMMENT '登录时间',
  `login_code` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '登录帐号',
  `login_password` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '登录密码',
  `login_verify_code_token` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '验证码token',
  `login_verify_code` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '登录验证码',
  `login_token` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '登录token',
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `create_by` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '创建人',
  `create_date` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建日期',
  `update_by` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '修改人',
  `update_date` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `version` int(11) NULL DEFAULT 0 COMMENT '版本号',
  `deleted` int(1) NULL DEFAULT 0 COMMENT '是否删除',
  `trace_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '日志ID',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 53 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '登录日志' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_login_log
-- ----------------------------
INSERT INTO `sys_login_log` VALUES ('SUCCESS', '127.0.0.1', '2020-11-06 13:50:13', 'admin', '123', '', 'XJ3y', 'a591d10ec3164c6cb5e5890865f0e6d0', 4, NULL, '2020-11-06 13:50:13', NULL, '2020-11-06 13:50:13', 0, 0, NULL, '');
INSERT INTO `sys_login_log` VALUES ('FAIL', '127.0.0.1', '2020-11-09 14:24:03', 'admin', '111111', '', 'djDA', '', 5, NULL, '2020-11-09 14:24:03', NULL, '2020-11-09 14:24:03', 0, 0, NULL, '');
INSERT INTO `sys_login_log` VALUES ('SUCCESS', '127.0.0.1', '2020-11-09 14:24:34', 'admin', '123456', '', 'djDA', '3169f1ac180546d195c47fe9eac84834', 6, NULL, '2020-11-09 14:24:34', NULL, '2020-11-09 14:24:34', 0, 0, NULL, '');
INSERT INTO `sys_login_log` VALUES ('FAIL', '127.0.0.1', '2020-11-09 14:26:01', 'admin', '111111', '', 'KMrl', '', 7, NULL, '2020-11-09 14:26:01', NULL, '2020-11-09 14:26:01', 0, 0, NULL, '');
INSERT INTO `sys_login_log` VALUES ('SUCCESS', '127.0.0.1', '2020-11-09 14:26:28', 'admin', '123456', '', 'WBGJ', 'ae61f4271b3642b4be41c2704babd391', 8, NULL, '2020-11-09 14:26:28', NULL, '2020-11-09 14:26:28', 0, 0, NULL, '');
INSERT INTO `sys_login_log` VALUES ('SUCCESS', '127.0.0.1', '2020-11-09 14:29:12', 'admin', '123456', '', 'KrL3', '5ae84a3ff04544eeb365221aba52997a', 9, NULL, '2020-11-09 14:29:12', NULL, '2020-11-09 14:29:12', 0, 0, NULL, '');
INSERT INTO `sys_login_log` VALUES ('SUCCESS', '127.0.0.1', '2020-11-11 11:53:30', 'admin', '123456', '', '6gGw', '461e54fb3b5b421b9127e7604f6736c4', 10, NULL, '2020-11-11 11:53:30', NULL, '2020-11-11 11:53:30', 0, 0, NULL, '');
INSERT INTO `sys_login_log` VALUES ('SUCCESS', '127.0.0.1', '2020-11-11 13:36:39', 'admin', '123456', '', 'xg4Y', 'b56adbc098614421a767c61ff9f37414', 11, NULL, '2020-11-11 13:36:39', NULL, '2020-11-11 13:36:39', 0, 0, NULL, '');
INSERT INTO `sys_login_log` VALUES ('SUCCESS', '127.0.0.1', '2020-11-11 13:41:40', 'admin', '123456', '', 'JjWx', '21e19d70a09d4b7d846ed87778b32a39', 12, NULL, '2020-11-11 13:41:40', NULL, '2020-11-11 13:41:40', 0, 0, NULL, '');
INSERT INTO `sys_login_log` VALUES ('SUCCESS', '127.0.0.1', '2020-11-11 13:51:23', 'admin', '123456', '', 'PB5A', '31765593b401478180dfcd1b9f8a8c51', 13, NULL, '2020-11-11 13:51:23', NULL, '2020-11-11 13:51:23', 0, 0, NULL, '');
INSERT INTO `sys_login_log` VALUES ('SUCCESS', '127.0.0.1', '2020-11-11 13:54:46', 'admin', '123456', '', 'ltUj', 'ea81b6223e024435bf6970aaf338d70f', 14, NULL, '2020-11-11 13:54:46', NULL, '2020-11-11 13:54:46', 0, 0, NULL, '');
INSERT INTO `sys_login_log` VALUES ('SUCCESS', '127.0.0.1', '2020-11-11 13:55:53', 'admin', '123456', '', 'ttsu', 'ba59818100d94f5c9b68af994ac98719', 15, NULL, '2020-11-11 13:55:53', NULL, '2020-11-11 13:55:53', 0, 0, NULL, '');
INSERT INTO `sys_login_log` VALUES ('SUCCESS', '127.0.0.1', '2020-11-11 13:56:37', 'admin', '123456', '', 'ySHx', '4302561564c34fc0b61b103816d6f801', 16, NULL, '2020-11-11 13:56:37', NULL, '2020-11-11 13:56:37', 0, 0, NULL, '');
INSERT INTO `sys_login_log` VALUES ('SUCCESS', '127.0.0.1', '2020-11-11 14:01:10', 'admin', '123456', '', 'yuGM', '449f085f251241abae22f9f5986188a6', 17, NULL, '2020-11-11 14:01:10', NULL, '2020-11-11 14:01:10', 0, 0, NULL, '');
INSERT INTO `sys_login_log` VALUES ('SUCCESS', '127.0.0.1', '2020-12-14 17:43:04', 'admin', '123456', '', 'l24u', 'b7a7fb0e76ac4528ad75137aae5e2671', 18, NULL, '2020-12-14 17:43:04', NULL, '2020-12-14 17:43:04', 0, 0, NULL, '');
INSERT INTO `sys_login_log` VALUES ('SUCCESS', '127.0.0.1', '2020-12-14 17:47:41', 'admin', '123456', '4df89ac7072d4ba2b5db3195ceb17ae6', 'yPfa', '0e8a0fd63c8e424faa990ea74ff34e9f', 19, NULL, '2020-12-14 17:47:41', NULL, '2020-12-14 17:47:41', 0, 0, NULL, '');
INSERT INTO `sys_login_log` VALUES ('SUCCESS', '127.0.0.1', '2020-12-14 17:48:53', 'admin', '123456', '69bddd8202b84d54a11938766a271279', '28Ut', 'c78d6096a56e40bc8d13b46ac69ed07c', 20, NULL, '2020-12-14 17:48:53', NULL, '2020-12-14 17:48:53', 0, 0, NULL, '');
INSERT INTO `sys_login_log` VALUES ('SUCCESS', '127.0.0.1,127.0.0.1', '2020-12-15 16:08:01', 'admin', '123456', '0042b464f0354c3db30fd5cd379464dd', 'f9Ux', '061385bc12a841f1bc4b1d90f35bd68d', 21, NULL, '2020-12-15 16:08:01', NULL, '2020-12-15 16:08:01', 0, 0, NULL, '');
INSERT INTO `sys_login_log` VALUES ('SUCCESS', '127.0.0.1,127.0.0.1', '2020-12-15 16:20:49', 'admin', '123456', '25a0a74c107d408393f644ced9bebba4', 'ccx3', '5d91a642f602428fb053b949d54801fc', 22, NULL, '2020-12-15 16:20:49', NULL, '2020-12-15 16:20:49', 0, 0, NULL, '');
INSERT INTO `sys_login_log` VALUES ('SUCCESS', '127.0.0.1,127.0.0.1', '2020-12-16 16:47:03', 'admin', '123456', '7ff288660bf54a2189c1b81fa72482cc', 'uNTj', '334ba9cf71d54a12a88c5b8df96c87fb', 23, NULL, '2020-12-16 16:47:03', NULL, '2020-12-16 16:47:03', 0, 0, NULL, '');
INSERT INTO `sys_login_log` VALUES ('SUCCESS', '127.0.0.1,127.0.0.1', '2020-12-17 09:32:20', 'admin', '123456', 'f50dabf7b25c440b90b8ba25b67c2079', 'uygR', '8fb2604704114d14887a5ce18191fa3a', 24, NULL, '2020-12-17 09:32:20', NULL, '2020-12-17 09:32:20', 0, 0, NULL, '');
INSERT INTO `sys_login_log` VALUES ('SUCCESS', '127.0.0.1,127.0.0.1', '2020-12-18 11:19:20', 'admin', '123456', 'fa5c32b1666d4a5eb8924279e36dd869', 'JQZk', '0c9330c259194db2b317f52cd9998fa2', 25, NULL, '2020-12-18 11:19:20', NULL, '2020-12-18 11:19:20', 0, 0, NULL, '');
INSERT INTO `sys_login_log` VALUES ('SUCCESS', '127.0.0.1,127.0.0.1', '2020-12-18 11:19:46', 'admin', '123456', 'f04a434926a44de3bdfb1641315c795e', 'WdyZ', 'ab5427fe86814f36928d18e214a9c366', 26, NULL, '2020-12-18 11:19:46', NULL, '2020-12-18 11:19:46', 0, 0, NULL, '');
INSERT INTO `sys_login_log` VALUES ('SUCCESS', '127.0.0.1,127.0.0.1', '2020-12-18 11:21:57', 'admin', '123456', '4a9c848238374b55952b6bfd39c0a1b4', 'Dcft', '2203c714e9044514945539483ba11c66', 27, NULL, '2020-12-18 11:21:57', NULL, '2020-12-18 11:21:57', 0, 0, NULL, '');
INSERT INTO `sys_login_log` VALUES ('SUCCESS', '127.0.0.1,127.0.0.1', '2020-12-18 11:22:54', 'admin', '123456', '642ebc1af7b04da18aebf98f89b00a23', 'Jm7d', '1e3a4a78a7ac44af825dbbc959887bc3', 28, NULL, '2020-12-18 11:22:54', NULL, '2020-12-18 11:22:54', 0, 0, NULL, '');
INSERT INTO `sys_login_log` VALUES ('SUCCESS', '127.0.0.1,127.0.0.1', '2020-12-18 11:23:35', 'admin', '123456', 'fe143137ea504cd5b555c774b329f76f', 'fNaq', '20b5e082965a45558a2a612482297bc8', 29, NULL, '2020-12-18 11:23:35', NULL, '2020-12-18 11:23:35', 0, 0, NULL, '');
INSERT INTO `sys_login_log` VALUES ('SUCCESS', '127.0.0.1,127.0.0.1', '2020-12-18 11:24:59', 'admin', '123456', '0f34e0bef9734b98a129e9a913b71676', '352G', '91d68c9532d14753a0b1be09c9e9f3be', 30, NULL, '2020-12-18 11:24:59', NULL, '2020-12-18 11:24:59', 0, 0, NULL, '');
INSERT INTO `sys_login_log` VALUES ('SUCCESS', '127.0.0.1,127.0.0.1', '2020-12-18 11:25:13', 'admin', '123456', '50fd24bd1a13478db74318c9e28a2253', 'G6u5', 'f7025063d182489baea274af9b41f8b8', 31, NULL, '2020-12-18 11:25:13', NULL, '2020-12-18 11:25:13', 0, 0, NULL, '');
INSERT INTO `sys_login_log` VALUES ('SUCCESS', '127.0.0.1,127.0.0.1', '2020-12-18 11:51:10', 'admin', '123456', 'eb6f1875cf7c45c8844450c8bbe89925', 'RGBs', '7ca21e1d3dec4e4793b226eec9ec64b1', 32, NULL, '2020-12-18 11:51:10', NULL, '2020-12-18 11:51:10', 0, 0, NULL, '');
INSERT INTO `sys_login_log` VALUES ('SUCCESS', '127.0.0.1,127.0.0.1', '2020-12-19 11:44:01', 'admin', '123456', '5aef6defa78f4e2296d38c5f4ab91bb9', 'fBZ3', 'ebf9c1c37be74119946e875495861938', 33, NULL, '2020-12-19 11:44:01', NULL, '2020-12-19 11:44:01', 0, 0, NULL, '');
INSERT INTO `sys_login_log` VALUES ('SUCCESS', '127.0.0.1,127.0.0.1', '2020-12-19 11:55:54', 'admin', '123456', '29f8f7d1a2f0427e8e25911250452c69', 'SX46', 'bc241631068349e0b26004f19fb9b42f', 34, NULL, '2020-12-19 11:55:54', NULL, '2020-12-19 11:55:54', 0, 0, NULL, '');
INSERT INTO `sys_login_log` VALUES ('SUCCESS', '127.0.0.1,127.0.0.1', '2020-12-19 11:58:51', 'admin', '123456', 'ecd0413c98b54c6b9b86c3c43c7dfb2d', '3Cmc', 'f6a2dba2cacd4b60aa6adcfe65a48bf2', 35, NULL, '2020-12-19 11:58:51', NULL, '2020-12-19 11:58:51', 0, 0, NULL, '');
INSERT INTO `sys_login_log` VALUES ('SUCCESS', '127.0.0.1,127.0.0.1', '2020-12-19 14:43:27', 'admin', '123456', 'd5255b9e8c3a4cd4b4d273a1370c67f9', 'yagD', 'f443034d31d642a8a07941c9bf7a3117', 36, NULL, '2020-12-19 14:43:27', NULL, '2020-12-19 14:43:27', 0, 0, NULL, '');
INSERT INTO `sys_login_log` VALUES ('SUCCESS', '127.0.0.1,127.0.0.1', '2021-01-11 19:50:04', 'admin', '123456', '8a781a3d83d5454e94583edf379717c6', 'Slqr', '0070306ec1c24e62af2efdea4440a5da', 37, NULL, '2021-01-11 19:50:04', NULL, '2021-01-11 19:50:04', 0, 0, NULL, '');
INSERT INTO `sys_login_log` VALUES ('SUCCESS', '127.0.0.1,127.0.0.1', '2021-01-12 15:19:04', 'admin', '123456', '0a8f87ff8d224fe58a4a2bd39d2161fa', 'wEC2', 'e380473967eb4aafa5cef0b42d43555b', 38, NULL, '2021-01-12 15:19:04', NULL, '2021-01-12 15:19:04', 0, 0, NULL, '');
INSERT INTO `sys_login_log` VALUES ('SUCCESS', '127.0.0.1,127.0.0.1', '2021-01-21 19:27:35', 'admin', '123456', 'b0e4656cf598450cbfff5064d3382e2a', 'Kkfj', '3488b8f0a11b4a9bbbc12cecbc31eba6', 39, NULL, '2021-01-21 19:27:35', NULL, '2021-01-21 19:27:35', 0, 0, NULL, '');
INSERT INTO `sys_login_log` VALUES ('SUCCESS', '127.0.0.1,127.0.0.1', '2021-01-22 16:10:40', 'admin', '123456', 'd58d2c5f173149e5aaca7a5a15637222', 'mYt5', '62a62090b0b740bfb76598ea27930fb0', 40, NULL, '2021-01-22 16:10:40', NULL, '2021-01-22 16:10:40', 0, 0, NULL, '');
INSERT INTO `sys_login_log` VALUES ('SUCCESS', '127.0.0.1,127.0.0.1', '2021-01-22 16:11:32', 'admin', '123456', 'f38bb4af65ed47629f051a027d1ae06e', 'KMFA', '7ae9b66eb9d443ada867af245dfec565', 41, NULL, '2021-01-22 16:11:32', NULL, '2021-01-22 16:11:32', 0, 0, NULL, '');
INSERT INTO `sys_login_log` VALUES ('SUCCESS', '127.0.0.1,127.0.0.1', '2021-03-03 21:34:33', 'admin', '123456', 'a6fa5875fc664ddcad48b9cb71e88cac', 'YA3m', 'cb31187b67534f66b0770c5c545a2ee4', 42, NULL, '2021-03-03 21:34:33', NULL, '2021-03-03 21:34:33', 0, 0, NULL, '');
INSERT INTO `sys_login_log` VALUES ('SUCCESS', '127.0.0.1,127.0.0.1', '2021-07-09 03:45:18', 'admin', '123456', '3039c0b99b504d4a99f907eab17db1a8', '9llZ', '0efffc40b5064d3ab5969dcc734170e7', 43, NULL, '2021-07-09 03:45:18', NULL, '2021-07-09 03:45:18', 0, 0, NULL, '');
INSERT INTO `sys_login_log` VALUES ('SUCCESS', '127.0.0.1,127.0.0.1', '2021-07-09 03:47:30', 'admin', '123456', 'c63fb14ecc7f476b96464df85caaef03', 'rPgg', 'eda2efb57bab4e689be470d951f307e6', 44, NULL, '2021-07-09 03:47:30', NULL, '2021-07-09 03:47:30', 0, 0, NULL, '');
INSERT INTO `sys_login_log` VALUES ('SUCCESS', '127.0.0.1,127.0.0.1', '2021-07-09 06:05:41', 'admin', '123456', '41df56d231f14fd3bb5781938656a8a2', '2hak', '10b1bd4ae10e40c2bf85d1b6fffd5049', 45, NULL, '2021-07-09 06:05:41', NULL, '2021-07-09 06:05:41', 0, 0, NULL, '');
INSERT INTO `sys_login_log` VALUES ('SUCCESS', '127.0.0.1,127.0.0.1', '2021-07-09 06:06:19', 'admin', '123456', 'fccdacb95ca04921b1a6e0ec01f940f9', 'ZYWM', '991e60c581c34f89bedf7ce5dd7e586d', 46, NULL, '2021-07-09 06:06:19', NULL, '2021-07-09 06:06:19', 0, 0, NULL, '');
INSERT INTO `sys_login_log` VALUES ('SUCCESS', '127.0.0.1,127.0.0.1', '2021-07-09 06:10:20', 'admin', '123456', 'dd4db9cd915f401ea01c06a1efa77997', 'DtFE', 'c60775fa50df41d0adcea2f71268d127', 47, NULL, '2021-07-09 06:10:20', NULL, '2021-07-09 06:10:20', 0, 0, NULL, '');
INSERT INTO `sys_login_log` VALUES ('SUCCESS', '127.0.0.1,127.0.0.1', '2021-07-09 06:44:28', 'admin', '123456', 'ca7af5fe748a4a41b43b2b769088eb53', '2tyr', 'b695cb22b73647259cf6c1ccefeab0f5', 48, NULL, '2021-07-09 06:44:28', NULL, '2021-07-09 06:44:28', 0, 0, NULL, '');
INSERT INTO `sys_login_log` VALUES ('SUCCESS', '127.0.0.1,127.0.0.1', '2021-07-09 06:44:57', 'admin', '123456', 'd82de24040554548b0ab3061ce8f205f', 'MHFv', '04a7ccd424ac449a85c5bb43ff731812', 49, NULL, '2021-07-09 06:44:57', NULL, '2021-07-09 06:44:57', 0, 0, NULL, '');
INSERT INTO `sys_login_log` VALUES ('SUCCESS', '127.0.0.1,127.0.0.1', '2021-07-09 07:23:40', 'admin', '123456', '4b5d64557c324970b605682e247fefd3', 'hEfp', 'c294b64237b54b84b6f3f79141779349', 50, NULL, '2021-07-09 07:23:40', NULL, '2021-07-09 07:23:40', 0, 0, NULL, '');
INSERT INTO `sys_login_log` VALUES ('SUCCESS', '127.0.0.1,127.0.0.1', '2021-07-09 07:24:18', 'admin', '123456', '2de8914483a94be59dd811e5d2b63437', 'tlp4', '67d908bf48834c0cba537c019d782cf9', 51, NULL, '2021-07-09 07:24:18', NULL, '2021-07-09 07:24:18', 0, 0, NULL, '');
INSERT INTO `sys_login_log` VALUES ('SUCCESS', '127.0.0.1,127.0.0.1', '2021-07-09 07:27:04', 'test', '123456', '026adee8bf9d4a9e8a6fd961806b981b', 'gldw', '31dffa1afdd7414d8e4b3fafeba121f6', 52, NULL, '2021-07-09 07:27:04', NULL, '2021-07-09 07:27:04', 0, 0, NULL, '');

-- ----------------------------
-- Table structure for sys_login_visit_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_login_visit_log`;
CREATE TABLE `sys_login_visit_log`  (
  `token` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT 'token',
  `ip` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT 'ip',
  `need_verify_code` int(1) NULL DEFAULT 0 COMMENT '是否需要验证码，0不需要，1需要',
  `verify_code` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '验证码',
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `create_by` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '创建人',
  `create_date` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建日期',
  `update_by` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '修改人',
  `update_date` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `version` int(11) NULL DEFAULT 0 COMMENT '版本号',
  `deleted` int(1) NULL DEFAULT 0 COMMENT '是否删除',
  `trace_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '日志ID',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `sys_login_visit_log_token`(`token`) USING BTREE COMMENT 'token'
) ENGINE = InnoDB AUTO_INCREMENT = 84 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '登录访问日志' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_login_visit_log
-- ----------------------------
INSERT INTO `sys_login_visit_log` VALUES ('9066336d178d44eda1ba6cc35e3bf1e5', '127.0.0.1', 1, 'l24u', 1, NULL, '2020-12-14 17:41:01', NULL, '2020-12-14 17:41:01', 0, 0, NULL, '');
INSERT INTO `sys_login_visit_log` VALUES ('4df89ac7072d4ba2b5db3195ceb17ae6', '127.0.0.1', 1, 'yPfa', 2, NULL, '2020-12-14 17:47:38', NULL, '2020-12-14 17:47:38', 0, 0, NULL, '');
INSERT INTO `sys_login_visit_log` VALUES ('21f17b46510d4f6fa546de2776308adf', '127.0.0.1', 1, 'rawa', 3, NULL, '2020-12-14 17:48:35', NULL, '2020-12-14 17:48:35', 0, 0, NULL, '');
INSERT INTO `sys_login_visit_log` VALUES ('9842e8297d93409986523cf5dea56c83', '127.0.0.1', 1, 'elR4', 4, NULL, '2020-12-14 17:48:36', NULL, '2020-12-14 17:48:36', 0, 0, NULL, '');
INSERT INTO `sys_login_visit_log` VALUES ('d13a93fdb9f446e18980a43cac154425', '127.0.0.1', 1, 'KuBR', 5, NULL, '2020-12-14 17:48:37', NULL, '2020-12-14 17:48:37', 0, 0, NULL, '');
INSERT INTO `sys_login_visit_log` VALUES ('aa3f62dae2ad47639f3fcadcc00cf2ff', '127.0.0.1', 1, 'ck4t', 6, NULL, '2020-12-14 17:48:38', NULL, '2020-12-14 17:48:38', 0, 0, NULL, '');
INSERT INTO `sys_login_visit_log` VALUES ('69bddd8202b84d54a11938766a271279', '127.0.0.1', 1, '28Ut', 7, NULL, '2020-12-14 17:48:39', NULL, '2020-12-14 17:48:39', 0, 0, NULL, '');
INSERT INTO `sys_login_visit_log` VALUES ('411af207a6d148c582b063ede2ec5223', '127.0.0.1,127.0.0.1', 1, 'vqbc', 8, NULL, '2020-12-15 16:07:42', NULL, '2020-12-15 16:07:42', 0, 0, NULL, '');
INSERT INTO `sys_login_visit_log` VALUES ('0042b464f0354c3db30fd5cd379464dd', '127.0.0.1,127.0.0.1', 1, 'f9Ux', 9, NULL, '2020-12-15 16:07:56', NULL, '2020-12-15 16:07:56', 0, 0, NULL, '');
INSERT INTO `sys_login_visit_log` VALUES ('0817373cc918447aae7a1d8e549c80eb', '127.0.0.1,127.0.0.1', 1, 'wCMW', 10, NULL, '2020-12-15 16:20:31', NULL, '2020-12-15 16:20:31', 0, 0, NULL, '');
INSERT INTO `sys_login_visit_log` VALUES ('9f32736410c54915868691779e554b67', '127.0.0.1,127.0.0.1', 1, 'cy7f', 11, NULL, '2020-12-15 16:20:45', NULL, '2020-12-15 16:20:45', 0, 0, NULL, '');
INSERT INTO `sys_login_visit_log` VALUES ('07514753b42540e885ed5dc2c12501b5', '127.0.0.1,127.0.0.1', 1, 'GcHf', 12, NULL, '2020-12-15 16:20:46', NULL, '2020-12-15 16:20:46', 0, 0, NULL, '');
INSERT INTO `sys_login_visit_log` VALUES ('62d618359c284350bdbf0a16619531c8', '127.0.0.1,127.0.0.1', 1, 'FcyN', 13, NULL, '2020-12-15 16:20:47', NULL, '2020-12-15 16:20:47', 0, 0, NULL, '');
INSERT INTO `sys_login_visit_log` VALUES ('25a0a74c107d408393f644ced9bebba4', '127.0.0.1,127.0.0.1', 1, 'ccx3', 14, NULL, '2020-12-15 16:20:48', NULL, '2020-12-15 16:20:48', 0, 0, NULL, '');
INSERT INTO `sys_login_visit_log` VALUES ('495a7602ede34453bb91f562609a0d6f', '127.0.0.1,127.0.0.1', 1, 'bJe2', 15, NULL, '2020-12-16 16:45:32', NULL, '2020-12-16 16:45:32', 0, 0, NULL, '');
INSERT INTO `sys_login_visit_log` VALUES ('2e5b46fe8d0f4e1db0df471b3d8a4504', '127.0.0.1,127.0.0.1', 1, 'WVTH', 16, NULL, '2020-12-16 16:45:41', NULL, '2020-12-16 16:45:41', 0, 0, NULL, '');
INSERT INTO `sys_login_visit_log` VALUES ('ab94e3cf76d441cb950adbee917cd860', '127.0.0.1,127.0.0.1', 1, 'npJX', 17, NULL, '2020-12-16 16:45:43', NULL, '2020-12-16 16:45:43', 0, 0, NULL, '');
INSERT INTO `sys_login_visit_log` VALUES ('61323d26c82942af8d500e612a74b8a2', '127.0.0.1,127.0.0.1', 1, '87fT', 18, NULL, '2020-12-16 16:45:44', NULL, '2020-12-16 16:45:44', 0, 0, NULL, '');
INSERT INTO `sys_login_visit_log` VALUES ('3773ad100097445a9645878d6bc79d13', '127.0.0.1,127.0.0.1', 1, 'e5Jf', 19, NULL, '2020-12-16 16:45:46', NULL, '2020-12-16 16:45:46', 0, 0, NULL, '');
INSERT INTO `sys_login_visit_log` VALUES ('582c7092d0c0410aa662754a07d2bba2', '127.0.0.1,127.0.0.1', 1, 'x2Ze', 20, NULL, '2020-12-16 16:45:48', NULL, '2020-12-16 16:45:48', 0, 0, NULL, '');
INSERT INTO `sys_login_visit_log` VALUES ('7ff288660bf54a2189c1b81fa72482cc', '127.0.0.1,127.0.0.1', 1, 'uNTj', 21, NULL, '2020-12-16 16:45:49', NULL, '2020-12-16 16:45:49', 0, 0, NULL, '');
INSERT INTO `sys_login_visit_log` VALUES ('f50dabf7b25c440b90b8ba25b67c2079', '127.0.0.1,127.0.0.1', 1, 'uygR', 22, NULL, '2020-12-17 09:32:19', NULL, '2020-12-17 09:32:19', 0, 0, NULL, '');
INSERT INTO `sys_login_visit_log` VALUES ('fa5c32b1666d4a5eb8924279e36dd869', '127.0.0.1,127.0.0.1', 1, 'JQZk', 23, NULL, '2020-12-18 11:19:19', NULL, '2020-12-18 11:19:19', 0, 0, NULL, '');
INSERT INTO `sys_login_visit_log` VALUES ('f04a434926a44de3bdfb1641315c795e', '127.0.0.1,127.0.0.1', 1, 'WdyZ', 24, NULL, '2020-12-18 11:19:42', NULL, '2020-12-18 11:19:42', 0, 0, NULL, '');
INSERT INTO `sys_login_visit_log` VALUES ('4a9c848238374b55952b6bfd39c0a1b4', '127.0.0.1,127.0.0.1', 1, 'Dcft', 25, NULL, '2020-12-18 11:21:56', NULL, '2020-12-18 11:21:56', 0, 0, NULL, '');
INSERT INTO `sys_login_visit_log` VALUES ('dd78d3eabcd1430895d3b0189f7c86c7', '127.0.0.1,127.0.0.1', 1, 'H9ha', 26, NULL, '2020-12-18 11:22:52', NULL, '2020-12-18 11:22:52', 0, 0, NULL, '');
INSERT INTO `sys_login_visit_log` VALUES ('642ebc1af7b04da18aebf98f89b00a23', '127.0.0.1,127.0.0.1', 1, 'Jm7d', 27, NULL, '2020-12-18 11:22:53', NULL, '2020-12-18 11:22:53', 0, 0, NULL, '');
INSERT INTO `sys_login_visit_log` VALUES ('fe143137ea504cd5b555c774b329f76f', '127.0.0.1,127.0.0.1', 1, 'fNaq', 28, NULL, '2020-12-18 11:23:29', NULL, '2020-12-18 11:23:29', 0, 0, NULL, '');
INSERT INTO `sys_login_visit_log` VALUES ('0f34e0bef9734b98a129e9a913b71676', '127.0.0.1,127.0.0.1', 1, '352G', 29, NULL, '2020-12-18 11:24:59', NULL, '2020-12-18 11:24:59', 0, 0, NULL, '');
INSERT INTO `sys_login_visit_log` VALUES ('50fd24bd1a13478db74318c9e28a2253', '127.0.0.1,127.0.0.1', 1, 'G6u5', 30, NULL, '2020-12-18 11:25:12', NULL, '2020-12-18 11:25:12', 0, 0, NULL, '');
INSERT INTO `sys_login_visit_log` VALUES ('eb6f1875cf7c45c8844450c8bbe89925', '127.0.0.1,127.0.0.1', 1, 'RGBs', 31, NULL, '2020-12-18 11:51:09', NULL, '2020-12-18 11:51:09', 0, 0, NULL, '');
INSERT INTO `sys_login_visit_log` VALUES ('5aef6defa78f4e2296d38c5f4ab91bb9', '127.0.0.1,127.0.0.1', 1, 'fBZ3', 32, NULL, '2020-12-19 11:37:25', NULL, '2020-12-19 11:37:25', 0, 0, NULL, '');
INSERT INTO `sys_login_visit_log` VALUES ('8cba6281eced411887463c33f7b36515', '127.0.0.1,127.0.0.1', 1, 'Vec2', 33, NULL, '2020-12-19 11:55:48', NULL, '2020-12-19 11:55:48', 0, 0, NULL, '');
INSERT INTO `sys_login_visit_log` VALUES ('29f8f7d1a2f0427e8e25911250452c69', '127.0.0.1,127.0.0.1', 1, 'SX46', 34, NULL, '2020-12-19 11:55:52', NULL, '2020-12-19 11:55:52', 0, 0, NULL, '');
INSERT INTO `sys_login_visit_log` VALUES ('d03c5ae7afbf432b81a919a388bc46bc', '127.0.0.1,127.0.0.1', 1, 'fYmW', 35, NULL, '2020-12-19 11:58:09', NULL, '2020-12-19 11:58:09', 0, 0, NULL, '');
INSERT INTO `sys_login_visit_log` VALUES ('3bd39f95815f4290b9e2a4e25c059a1e', '127.0.0.1,127.0.0.1', 1, 'heZV', 36, NULL, '2020-12-19 11:58:47', NULL, '2020-12-19 11:58:47', 0, 0, NULL, '');
INSERT INTO `sys_login_visit_log` VALUES ('ecd0413c98b54c6b9b86c3c43c7dfb2d', '127.0.0.1,127.0.0.1', 1, '3Cmc', 37, NULL, '2020-12-19 11:58:49', NULL, '2020-12-19 11:58:49', 0, 0, NULL, '');
INSERT INTO `sys_login_visit_log` VALUES ('d5255b9e8c3a4cd4b4d273a1370c67f9', '127.0.0.1,127.0.0.1', 1, 'yagD', 38, NULL, '2020-12-19 14:43:24', NULL, '2020-12-19 14:43:24', 0, 0, NULL, '');
INSERT INTO `sys_login_visit_log` VALUES ('8a781a3d83d5454e94583edf379717c6', '127.0.0.1,127.0.0.1', 1, 'Slqr', 39, NULL, '2021-01-11 19:49:07', NULL, '2021-01-11 19:49:07', 0, 0, NULL, '');
INSERT INTO `sys_login_visit_log` VALUES ('0a8f87ff8d224fe58a4a2bd39d2161fa', '127.0.0.1,127.0.0.1', 1, 'wEC2', 40, NULL, '2021-01-12 15:19:02', NULL, '2021-01-12 15:19:02', 0, 0, NULL, '');
INSERT INTO `sys_login_visit_log` VALUES ('b0e4656cf598450cbfff5064d3382e2a', '127.0.0.1,127.0.0.1', 1, 'Kkfj', 41, NULL, '2021-01-21 19:27:34', NULL, '2021-01-21 19:27:34', 0, 0, NULL, '');
INSERT INTO `sys_login_visit_log` VALUES ('d58d2c5f173149e5aaca7a5a15637222', '127.0.0.1,127.0.0.1', 1, 'mYt5', 42, NULL, '2021-01-22 16:10:38', NULL, '2021-01-22 16:10:38', 0, 0, NULL, '');
INSERT INTO `sys_login_visit_log` VALUES ('f38bb4af65ed47629f051a027d1ae06e', '127.0.0.1,127.0.0.1', 1, 'KMFA', 43, NULL, '2021-01-22 16:10:50', NULL, '2021-01-22 16:10:50', 0, 0, NULL, '');
INSERT INTO `sys_login_visit_log` VALUES ('a6fa5875fc664ddcad48b9cb71e88cac', '127.0.0.1,127.0.0.1', 1, 'YA3m', 44, NULL, '2021-03-03 21:34:32', NULL, '2021-03-03 21:34:32', 0, 0, NULL, '');
INSERT INTO `sys_login_visit_log` VALUES ('e012d01338ff4f5ba491599ce7174b8b', '0:0:0:0:0:0:0:1', 1, '', 45, NULL, '2021-04-21 14:52:56', NULL, '2021-04-21 14:52:56', 0, 0, NULL, '');
INSERT INTO `sys_login_visit_log` VALUES ('928806b5a9a0466e93fe8acdbeb3caf0', '0:0:0:0:0:0:0:1', 1, '', 46, NULL, '2021-04-21 14:54:50', NULL, '2021-04-21 14:54:50', 0, 0, NULL, '');
INSERT INTO `sys_login_visit_log` VALUES ('3c7d496a4fa14d688bf342f77d2af738', '0:0:0:0:0:0:0:1', 1, '', 47, NULL, '2021-04-21 14:54:53', NULL, '2021-04-21 14:54:53', 0, 0, NULL, '');
INSERT INTO `sys_login_visit_log` VALUES ('f97842869d3049dfa069e25226c3b140', '0:0:0:0:0:0:0:1', 1, '', 48, NULL, '2021-04-21 14:54:53', NULL, '2021-04-21 14:54:53', 0, 0, NULL, '');
INSERT INTO `sys_login_visit_log` VALUES ('185859d9d2f9487bb781ad0b1a2cddfa', '0:0:0:0:0:0:0:1', 1, '', 49, NULL, '2021-04-21 14:54:54', NULL, '2021-04-21 14:54:54', 0, 0, NULL, '');
INSERT INTO `sys_login_visit_log` VALUES ('0d21d7edabe6442b8c7245026221b6db', '0:0:0:0:0:0:0:1', 1, '', 50, NULL, '2021-04-21 14:55:07', NULL, '2021-04-21 14:55:07', 0, 0, NULL, '');
INSERT INTO `sys_login_visit_log` VALUES ('e76db6d8059c4d3e9086d475db7b9f55', '0:0:0:0:0:0:0:1', 1, '', 51, NULL, '2021-04-21 15:06:21', NULL, '2021-04-21 15:06:21', 0, 0, NULL, '');
INSERT INTO `sys_login_visit_log` VALUES ('aea0f275962f418ca439699afcd2907b', '0:0:0:0:0:0:0:1', 1, '', 52, NULL, '2021-04-21 15:07:44', NULL, '2021-04-21 15:07:44', 0, 0, NULL, '');
INSERT INTO `sys_login_visit_log` VALUES ('d363d81a94ce46c585ab0bdcf06c9839', '0:0:0:0:0:0:0:1', 1, '', 53, NULL, '2021-04-21 17:25:40', NULL, '2021-04-21 17:25:40', 0, 0, NULL, '');
INSERT INTO `sys_login_visit_log` VALUES ('4694b51020e44be19c0dcde66eb2d7ca', '0:0:0:0:0:0:0:1', 1, '', 54, NULL, '2021-04-21 17:25:56', NULL, '2021-04-21 17:25:56', 0, 0, NULL, '');
INSERT INTO `sys_login_visit_log` VALUES ('baff90b744664843905c81878b1ddecd', '0:0:0:0:0:0:0:1', 1, '', 55, NULL, '2021-04-21 17:27:06', NULL, '2021-04-21 17:27:06', 0, 0, NULL, '');
INSERT INTO `sys_login_visit_log` VALUES ('d5cbb444e72842bdb6c28c7fc01155f6', '0:0:0:0:0:0:0:1', 1, '', 56, NULL, '2021-04-21 17:27:40', NULL, '2021-04-21 17:27:40', 0, 0, NULL, '');
INSERT INTO `sys_login_visit_log` VALUES ('6bc44f8469d541cf9d0eebbbc1c409a2', '0:0:0:0:0:0:0:1', 1, '', 57, NULL, '2021-04-21 17:28:09', NULL, '2021-04-21 17:28:09', 0, 0, NULL, '');
INSERT INTO `sys_login_visit_log` VALUES ('18ddf6a191334cdf8cf7f1c9ac6cd7d3', '0:0:0:0:0:0:0:1', 1, '', 58, NULL, '2021-04-21 17:28:32', NULL, '2021-04-21 17:28:32', 0, 0, NULL, '');
INSERT INTO `sys_login_visit_log` VALUES ('3b47f6a3a04b4a1d88b2e878cfe32efb', '0:0:0:0:0:0:0:1', 1, '', 59, NULL, '2021-04-21 17:29:28', NULL, '2021-04-21 17:29:28', 0, 0, NULL, '');
INSERT INTO `sys_login_visit_log` VALUES ('42bfb22b1b5346a69882974c2991cd10', '0:0:0:0:0:0:0:1', 1, '', 60, NULL, '2021-04-21 17:30:40', NULL, '2021-04-21 17:30:40', 0, 0, NULL, '');
INSERT INTO `sys_login_visit_log` VALUES ('3039c0b99b504d4a99f907eab17db1a8', '127.0.0.1,127.0.0.1', 1, '9llZ', 61, NULL, '2021-07-09 03:45:14', NULL, '2021-07-09 03:45:14', 0, 0, NULL, '');
INSERT INTO `sys_login_visit_log` VALUES ('c63fb14ecc7f476b96464df85caaef03', '127.0.0.1,127.0.0.1', 1, 'rPgg', 62, NULL, '2021-07-09 03:47:29', NULL, '2021-07-09 03:47:29', 0, 0, NULL, '');
INSERT INTO `sys_login_visit_log` VALUES ('41df56d231f14fd3bb5781938656a8a2', '127.0.0.1,127.0.0.1', 1, '2hak', 63, NULL, '2021-07-09 06:05:39', NULL, '2021-07-09 06:05:39', 0, 0, NULL, '');
INSERT INTO `sys_login_visit_log` VALUES ('fccdacb95ca04921b1a6e0ec01f940f9', '127.0.0.1,127.0.0.1', 1, 'ZYWM', 64, NULL, '2021-07-09 06:06:15', NULL, '2021-07-09 06:06:15', 0, 0, NULL, '');
INSERT INTO `sys_login_visit_log` VALUES ('dd4db9cd915f401ea01c06a1efa77997', '127.0.0.1,127.0.0.1', 1, 'DtFE', 65, NULL, '2021-07-09 06:10:13', NULL, '2021-07-09 06:10:13', 0, 0, NULL, '');
INSERT INTO `sys_login_visit_log` VALUES ('62199e50a384441eba5d2111f8af4464', '127.0.0.1,127.0.0.1', 1, 'aYa6', 66, NULL, '2021-07-09 06:13:59', NULL, '2021-07-09 06:13:59', 0, 0, NULL, '');
INSERT INTO `sys_login_visit_log` VALUES ('bc616bab92a1441983714fc92439d9de', '127.0.0.1,127.0.0.1', 1, 'S7Ly', 67, NULL, '2021-07-09 06:36:02', NULL, '2021-07-09 06:36:02', 0, 0, NULL, '');
INSERT INTO `sys_login_visit_log` VALUES ('ca7af5fe748a4a41b43b2b769088eb53', '127.0.0.1,127.0.0.1', 1, '2tyr', 68, NULL, '2021-07-09 06:39:28', NULL, '2021-07-09 06:39:28', 0, 0, NULL, '');
INSERT INTO `sys_login_visit_log` VALUES ('d82de24040554548b0ab3061ce8f205f', '127.0.0.1,127.0.0.1', 1, 'MHFv', 69, NULL, '2021-07-09 06:44:53', NULL, '2021-07-09 06:44:53', 0, 0, NULL, '');
INSERT INTO `sys_login_visit_log` VALUES ('30cb3b8faa93407c9efa49904f5434fd', '127.0.0.1,127.0.0.1', 1, '', 70, NULL, '2021-07-09 06:46:01', NULL, '2021-07-09 06:46:01', 0, 0, NULL, '');
INSERT INTO `sys_login_visit_log` VALUES ('6f864ba2bfd24c4b9aa69d260359953c', '127.0.0.1,127.0.0.1', 1, 'petJ', 71, NULL, '2021-07-09 07:11:06', NULL, '2021-07-09 07:11:06', 0, 0, NULL, '');
INSERT INTO `sys_login_visit_log` VALUES ('fc79fa81ad084deeb48eb59efb2dd46e', '127.0.0.1,127.0.0.1', 1, '9nWH', 72, NULL, '2021-07-09 07:11:33', NULL, '2021-07-09 07:11:33', 0, 0, NULL, '');
INSERT INTO `sys_login_visit_log` VALUES ('c1ce95255b5243618c38410e96daa6d0', '127.0.0.1,127.0.0.1', 1, '5vxm', 73, NULL, '2021-07-09 07:11:42', NULL, '2021-07-09 07:11:42', 0, 0, NULL, '');
INSERT INTO `sys_login_visit_log` VALUES ('5849ed55b6774e05ae4cc8a54e3945d0', '127.0.0.1,127.0.0.1', 1, 'brJ5', 74, NULL, '2021-07-09 07:13:58', NULL, '2021-07-09 07:13:58', 0, 0, NULL, '');
INSERT INTO `sys_login_visit_log` VALUES ('8be23a16dabd42d1aee5f8ec10f9c1f4', '127.0.0.1,127.0.0.1', 1, 'jwVp', 75, NULL, '2021-07-09 07:16:02', NULL, '2021-07-09 07:16:02', 0, 0, NULL, '');
INSERT INTO `sys_login_visit_log` VALUES ('90776ca16c0044bc8c06fe183ac25ba8', '127.0.0.1,127.0.0.1', 1, 'MkJN', 76, NULL, '2021-07-09 07:17:56', NULL, '2021-07-09 07:17:56', 0, 0, NULL, '');
INSERT INTO `sys_login_visit_log` VALUES ('0b0f5e5a7e5041bea7fc127b001da86d', '127.0.0.1,127.0.0.1', 1, 'J47W', 77, NULL, '2021-07-09 07:23:07', NULL, '2021-07-09 07:23:07', 0, 0, NULL, '');
INSERT INTO `sys_login_visit_log` VALUES ('4b5d64557c324970b605682e247fefd3', '127.0.0.1,127.0.0.1', 1, 'hEfp', 78, NULL, '2021-07-09 07:23:15', NULL, '2021-07-09 07:23:15', 0, 0, NULL, '');
INSERT INTO `sys_login_visit_log` VALUES ('2de8914483a94be59dd811e5d2b63437', '127.0.0.1,127.0.0.1', 1, 'tlp4', 79, NULL, '2021-07-09 07:24:17', NULL, '2021-07-09 07:24:17', 0, 0, NULL, '');
INSERT INTO `sys_login_visit_log` VALUES ('5144afe45547471db78deefaf734dc1b', '127.0.0.1,127.0.0.1', 1, 'AjfA', 80, NULL, '2021-07-09 07:24:24', NULL, '2021-07-09 07:24:24', 0, 0, NULL, '');
INSERT INTO `sys_login_visit_log` VALUES ('d3589c9e75064c96a4dfca274c864430', '127.0.0.1,127.0.0.1', 1, '76le', 81, NULL, '2021-07-09 07:24:38', NULL, '2021-07-09 07:24:38', 0, 0, NULL, '');
INSERT INTO `sys_login_visit_log` VALUES ('bed1985679c440838f0a8f3b99bdb4f5', '127.0.0.1,127.0.0.1', 1, 'ZKFe', 82, NULL, '2021-07-09 07:26:58', NULL, '2021-07-09 07:26:58', 0, 0, NULL, '');
INSERT INTO `sys_login_visit_log` VALUES ('026adee8bf9d4a9e8a6fd961806b981b', '127.0.0.1,127.0.0.1', 1, 'gldw', 83, NULL, '2021-07-09 07:27:00', NULL, '2021-07-09 07:27:00', 0, 0, NULL, '');

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu`  (
  `name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '名称',
  `path` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '地址',
  `component` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '组件名称',
  `sort` int(6) NULL DEFAULT 0 COMMENT '顺序',
  `parent_id` int(11) NULL DEFAULT 0 COMMENT '父级id',
  `icon` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '图标',
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `create_by` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '创建人',
  `create_date` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建日期',
  `update_by` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '修改人',
  `update_date` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `version` int(11) NULL DEFAULT 0 COMMENT '版本号',
  `deleted` int(1) NULL DEFAULT 0 COMMENT '是否删除',
  `trace_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '日志ID',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '菜单管理' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES ('系统管理', '/system', 'Layout', 500, 0, '', 1, '0', '2020-12-17 09:12:38', '0', '2020-12-17 09:12:38', 0, 0, '5cc81dcd363346bb9d8fa3bbabf0b57b', '');
INSERT INTO `sys_menu` VALUES ('菜单管理', '/system/menu/index', 'system/menu', 510, 1, 'el-icon-setting', 2, '0', '2020-12-17 09:16:29', '0', '2021-03-03 21:43:14', 1, 0, '1dace487c74e42ce8fcaaa68146fedc4', '');
INSERT INTO `sys_menu` VALUES ('任务调度管理', '/system/job/index', 'system/job/index', 500, 1, 'el-icon-setting', 9, 'admin', '2020-12-17 17:28:19', 'admin', '2020-12-17 17:28:19', 0, 0, 'f0cb3d0b7bd64157a73d7b43dc74ea01', '定时任务');
INSERT INTO `sys_menu` VALUES ('用户管理', '/system/user/index', 'system/user', 520, 1, 'el-icon-setting', 10, 'admin', '2021-07-09 16:04:10', 'admin', '2021-07-09 16:04:10', 0, 0, '', '');

-- ----------------------------
-- Table structure for sys_online_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_online_user`;
CREATE TABLE `sys_online_user`  (
  `user_id` int(11) NOT NULL DEFAULT 0 COMMENT '用户id',
  `user_code` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '用户编号',
  `login_token` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '登录token',
  `login_ip` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '登录ip',
  `login_date` datetime NULL DEFAULT NULL COMMENT '登录时间',
  `last_visit_date` datetime NULL DEFAULT NULL COMMENT '最后访问时间',
  `lease_time` int(11) NULL DEFAULT 0 COMMENT '租期',
  `expire_date` datetime NULL DEFAULT NULL COMMENT '过期时间',
  `logout_ip` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '登出ip',
  `logout_date` datetime NULL DEFAULT NULL COMMENT '登出时间',
  `state` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '状态，01在线，02下线',
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `create_by` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '创建人',
  `create_date` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建日期',
  `update_by` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '修改人',
  `update_date` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `version` int(11) NULL DEFAULT 0 COMMENT '版本号',
  `deleted` int(1) NULL DEFAULT 0 COMMENT '是否删除',
  `trace_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '日志ID',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `sys_online_user_user_id`(`user_id`) USING BTREE COMMENT '用户id',
  INDEX `sys_online_user_user_code`(`user_code`) USING BTREE COMMENT '用户编码',
  INDEX `sys_online_user_login_token`(`login_token`) USING BTREE COMMENT '登录token'
) ENGINE = InnoDB AUTO_INCREMENT = 33 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '在线用户管理' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_online_user
-- ----------------------------
INSERT INTO `sys_online_user` VALUES (1, 'admin', '061385bc12a841f1bc4b1d90f35bd68d', '127.0.0.1,127.0.0.1', '2020-12-15 16:08:01', '2020-12-15 16:20:30', 28800, '2020-12-16 00:20:30', '', '2020-12-15 16:20:30', 'OFFLINE', 1, NULL, '2020-12-15 16:08:01', NULL, '2020-12-15 16:20:31', 1, 0, NULL, '');
INSERT INTO `sys_online_user` VALUES (1, 'admin', '5d91a642f602428fb053b949d54801fc', '127.0.0.1,127.0.0.1', '2020-12-15 16:20:49', '2020-12-15 16:24:11', 28800, '2020-12-16 00:24:11', '', NULL, 'OFFLINE', 2, NULL, '2020-12-15 16:20:49', NULL, '2020-12-15 16:20:49', 0, 0, NULL, '');
INSERT INTO `sys_online_user` VALUES (1, 'admin', '334ba9cf71d54a12a88c5b8df96c87fb', '127.0.0.1,127.0.0.1', '2020-12-16 16:47:03', '2020-12-16 20:04:52', 28800, '2020-12-17 04:04:52', '', NULL, 'OFFLINE', 3, NULL, '2020-12-16 16:47:03', NULL, '2020-12-16 16:47:03', 0, 0, NULL, '');
INSERT INTO `sys_online_user` VALUES (1, 'admin', '8fb2604704114d14887a5ce18191fa3a', '127.0.0.1,127.0.0.1', '2020-12-17 09:32:20', '2020-12-17 17:51:25', 28800, '2020-12-18 01:51:25', '', NULL, 'OFFLINE', 4, NULL, '2020-12-17 09:32:20', NULL, '2020-12-17 09:32:20', 0, 0, NULL, '');
INSERT INTO `sys_online_user` VALUES (1, 'admin', '0c9330c259194db2b317f52cd9998fa2', '127.0.0.1,127.0.0.1', '2020-12-18 11:19:20', '2020-12-18 11:19:20', 28800, '2020-12-18 19:19:20', '', NULL, 'ONLINE', 5, NULL, '2020-12-18 11:19:20', NULL, '2020-12-18 11:19:20', 0, 0, NULL, '');
INSERT INTO `sys_online_user` VALUES (1, 'admin', 'ab5427fe86814f36928d18e214a9c366', '127.0.0.1,127.0.0.1', '2020-12-18 11:19:46', '2020-12-18 11:19:46', 28800, '2020-12-18 19:19:46', '', NULL, 'ONLINE', 6, NULL, '2020-12-18 11:19:46', NULL, '2020-12-18 11:19:46', 0, 0, NULL, '');
INSERT INTO `sys_online_user` VALUES (1, 'admin', '2203c714e9044514945539483ba11c66', '127.0.0.1,127.0.0.1', '2020-12-18 11:21:57', '2020-12-18 11:21:57', 28800, '2020-12-18 19:21:57', '', NULL, 'ONLINE', 7, NULL, '2020-12-18 11:21:57', NULL, '2020-12-18 11:21:57', 0, 0, NULL, '');
INSERT INTO `sys_online_user` VALUES (1, 'admin', '1e3a4a78a7ac44af825dbbc959887bc3', '127.0.0.1,127.0.0.1', '2020-12-18 11:22:54', '2020-12-18 11:22:54', 28800, '2020-12-18 19:22:54', '', NULL, 'ONLINE', 8, NULL, '2020-12-18 11:22:54', NULL, '2020-12-18 11:22:54', 0, 0, NULL, '');
INSERT INTO `sys_online_user` VALUES (1, 'admin', '20b5e082965a45558a2a612482297bc8', '127.0.0.1,127.0.0.1', '2020-12-18 11:23:35', '2020-12-18 11:23:35', 28800, '2020-12-18 19:23:35', '', NULL, 'ONLINE', 9, NULL, '2020-12-18 11:23:35', NULL, '2020-12-18 11:23:35', 0, 0, NULL, '');
INSERT INTO `sys_online_user` VALUES (1, 'admin', '91d68c9532d14753a0b1be09c9e9f3be', '127.0.0.1,127.0.0.1', '2020-12-18 11:24:59', '2020-12-18 11:24:59', 28800, '2020-12-18 19:24:59', '', NULL, 'ONLINE', 10, NULL, '2020-12-18 11:24:59', NULL, '2020-12-18 11:24:59', 0, 0, NULL, '');
INSERT INTO `sys_online_user` VALUES (1, 'admin', 'f7025063d182489baea274af9b41f8b8', '127.0.0.1,127.0.0.1', '2020-12-18 11:25:13', '2020-12-18 11:51:09', 28800, '2020-12-18 19:51:09', '', '2020-12-18 11:51:09', 'OFFLINE', 11, NULL, '2020-12-18 11:25:13', NULL, '2020-12-18 11:51:09', 1, 0, NULL, '');
INSERT INTO `sys_online_user` VALUES (1, 'admin', '7ca21e1d3dec4e4793b226eec9ec64b1', '127.0.0.1,127.0.0.1', '2020-12-18 11:51:10', '2020-12-18 11:52:36', 28800, '2020-12-18 19:52:36', '', NULL, 'ONLINE', 12, NULL, '2020-12-18 11:51:10', NULL, '2020-12-18 11:51:10', 0, 0, NULL, '');
INSERT INTO `sys_online_user` VALUES (1, 'admin', 'ebf9c1c37be74119946e875495861938', '127.0.0.1,127.0.0.1', '2020-12-19 11:44:01', '2020-12-19 11:44:01', 28800, '2020-12-19 19:44:01', '', NULL, 'ONLINE', 13, NULL, '2020-12-19 11:44:01', NULL, '2020-12-19 11:44:01', 0, 0, NULL, '');
INSERT INTO `sys_online_user` VALUES (1, 'admin', 'bc241631068349e0b26004f19fb9b42f', '127.0.0.1,127.0.0.1', '2020-12-19 11:55:54', '2020-12-19 11:55:54', 28800, '2020-12-19 19:55:54', '', NULL, 'ONLINE', 14, NULL, '2020-12-19 11:55:54', NULL, '2020-12-19 11:55:54', 0, 0, NULL, '');
INSERT INTO `sys_online_user` VALUES (1, 'admin', 'f6a2dba2cacd4b60aa6adcfe65a48bf2', '127.0.0.1,127.0.0.1', '2020-12-19 11:58:51', '2020-12-19 11:58:51', 28800, '2020-12-19 19:58:51', '', NULL, 'ONLINE', 15, NULL, '2020-12-19 11:58:51', NULL, '2020-12-19 11:58:51', 0, 0, NULL, '');
INSERT INTO `sys_online_user` VALUES (1, 'admin', 'f443034d31d642a8a07941c9bf7a3117', '127.0.0.1,127.0.0.1', '2020-12-19 14:43:27', '2020-12-19 16:10:09', 28800, '2020-12-20 00:10:09', '', NULL, 'ONLINE', 16, NULL, '2020-12-19 14:43:27', NULL, '2020-12-19 14:43:27', 0, 0, NULL, '');
INSERT INTO `sys_online_user` VALUES (1, 'admin', '0070306ec1c24e62af2efdea4440a5da', '127.0.0.1,127.0.0.1', '2021-01-11 19:50:04', '2021-01-11 20:07:34', 28800, '2021-01-12 04:07:34', '', NULL, 'ONLINE', 17, NULL, '2021-01-11 19:50:04', NULL, '2021-01-11 19:50:04', 0, 0, NULL, '');
INSERT INTO `sys_online_user` VALUES (1, 'admin', 'e380473967eb4aafa5cef0b42d43555b', '127.0.0.1,127.0.0.1', '2021-01-12 15:19:04', '2021-01-12 15:23:28', 28800, '2021-01-12 23:23:28', '', NULL, 'ONLINE', 18, NULL, '2021-01-12 15:19:04', NULL, '2021-01-12 15:19:04', 0, 0, NULL, '');
INSERT INTO `sys_online_user` VALUES (1, 'admin', '3488b8f0a11b4a9bbbc12cecbc31eba6', '127.0.0.1,127.0.0.1', '2021-01-21 19:27:35', '2021-01-22 16:10:38', 28800, '2021-01-23 00:10:38', '', '2021-01-22 16:10:38', 'OFFLINE', 19, NULL, '2021-01-21 19:27:35', NULL, '2021-01-22 16:10:38', 1, 0, NULL, '');
INSERT INTO `sys_online_user` VALUES (1, 'admin', '62a62090b0b740bfb76598ea27930fb0', '127.0.0.1,127.0.0.1', '2021-01-22 16:10:40', '2021-01-22 16:10:49', 28800, '2021-01-23 00:10:49', '', '2021-01-22 16:10:49', 'OFFLINE', 20, NULL, '2021-01-22 16:10:40', NULL, '2021-01-22 16:10:49', 1, 0, NULL, '');
INSERT INTO `sys_online_user` VALUES (1, 'admin', '7ae9b66eb9d443ada867af245dfec565', '127.0.0.1,127.0.0.1', '2021-01-22 16:11:32', '2021-01-22 16:12:14', 28800, '2021-01-23 00:12:14', '', NULL, 'ONLINE', 21, NULL, '2021-01-22 16:11:32', NULL, '2021-01-22 16:11:32', 0, 0, NULL, '');
INSERT INTO `sys_online_user` VALUES (1, 'admin', 'cb31187b67534f66b0770c5c545a2ee4', '127.0.0.1,127.0.0.1', '2021-03-03 21:34:33', '2021-03-03 21:43:21', 28800, '2021-03-04 05:43:21', '', NULL, 'ONLINE', 22, NULL, '2021-03-03 21:34:33', NULL, '2021-03-03 21:34:33', 0, 0, NULL, '');
INSERT INTO `sys_online_user` VALUES (1, 'admin', '0efffc40b5064d3ab5969dcc734170e7', '127.0.0.1,127.0.0.1', '2021-07-09 03:45:18', '2021-07-09 03:47:29', 28800, '2021-07-09 11:47:29', '', '2021-07-09 03:47:29', 'OFFLINE', 23, NULL, '2021-07-09 03:45:18', NULL, '2021-07-09 03:47:29', 1, 0, NULL, '');
INSERT INTO `sys_online_user` VALUES (1, 'admin', 'eda2efb57bab4e689be470d951f307e6', '127.0.0.1,127.0.0.1', '2021-07-09 03:47:30', '2021-07-09 06:05:39', 28800, '2021-07-09 14:05:39', '', '2021-07-09 06:05:39', 'OFFLINE', 24, NULL, '2021-07-09 03:47:30', NULL, '2021-07-09 06:05:39', 1, 0, NULL, '');
INSERT INTO `sys_online_user` VALUES (1, 'admin', '10b1bd4ae10e40c2bf85d1b6fffd5049', '127.0.0.1,127.0.0.1', '2021-07-09 06:05:41', '2021-07-09 06:06:15', 28800, '2021-07-09 14:06:15', '', '2021-07-09 06:06:15', 'OFFLINE', 25, NULL, '2021-07-09 06:05:41', NULL, '2021-07-09 06:06:15', 1, 0, NULL, '');
INSERT INTO `sys_online_user` VALUES (1, 'admin', '991e60c581c34f89bedf7ce5dd7e586d', '127.0.0.1,127.0.0.1', '2021-07-09 06:06:19', '2021-07-09 06:10:12', 28800, '2021-07-09 14:10:12', '', '2021-07-09 06:10:13', 'OFFLINE', 26, NULL, '2021-07-09 06:06:19', NULL, '2021-07-09 06:10:13', 1, 0, NULL, '');
INSERT INTO `sys_online_user` VALUES (1, 'admin', 'c60775fa50df41d0adcea2f71268d127', '127.0.0.1,127.0.0.1', '2021-07-09 06:10:20', '2021-07-09 06:13:58', 28800, '2021-07-09 14:13:58', '', '2021-07-09 06:13:59', 'OFFLINE', 27, NULL, '2021-07-09 06:10:20', NULL, '2021-07-09 06:13:59', 1, 0, NULL, '');
INSERT INTO `sys_online_user` VALUES (1, 'admin', 'b695cb22b73647259cf6c1ccefeab0f5', '127.0.0.1,127.0.0.1', '2021-07-09 06:44:28', '2021-07-09 06:44:28', 28800, '2021-07-09 14:44:28', '', NULL, 'ONLINE', 28, NULL, '2021-07-09 06:44:28', NULL, '2021-07-09 06:44:28', 0, 0, NULL, '');
INSERT INTO `sys_online_user` VALUES (1, 'admin', '04a7ccd424ac449a85c5bb43ff731812', '127.0.0.1,127.0.0.1', '2021-07-09 06:44:57', '2021-07-09 06:44:57', 28800, '2021-07-09 14:44:57', '', NULL, 'ONLINE', 29, NULL, '2021-07-09 06:44:57', NULL, '2021-07-09 06:44:57', 0, 0, NULL, '');
INSERT INTO `sys_online_user` VALUES (1, 'admin', 'c294b64237b54b84b6f3f79141779349', '127.0.0.1,127.0.0.1', '2021-07-09 07:23:40', '2021-07-09 07:23:40', 28800, '2021-07-09 15:23:40', '', NULL, 'ONLINE', 30, NULL, '2021-07-09 07:23:40', NULL, '2021-07-09 07:23:40', 0, 0, NULL, '');
INSERT INTO `sys_online_user` VALUES (1, 'admin', '67d908bf48834c0cba537c019d782cf9', '127.0.0.1,127.0.0.1', '2021-07-09 07:24:18', '2021-07-09 07:24:18', 28800, '2021-07-09 15:24:18', '', NULL, 'ONLINE', 31, NULL, '2021-07-09 07:24:18', NULL, '2021-07-09 07:24:18', 0, 0, NULL, '');
INSERT INTO `sys_online_user` VALUES (10, 'test', '31dffa1afdd7414d8e4b3fafeba121f6', '127.0.0.1,127.0.0.1', '2021-07-09 07:27:04', '2021-07-09 08:08:56', 28800, '2021-07-09 16:08:56', '', NULL, 'ONLINE', 32, NULL, '2021-07-09 07:27:04', NULL, '2021-07-09 07:27:04', 0, 0, NULL, '');

-- ----------------------------
-- Table structure for sys_user0
-- ----------------------------
DROP TABLE IF EXISTS `sys_user0`;
CREATE TABLE `sys_user0`  (
  `code` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '编码',
  `name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '名字',
  `state` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '状态',
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '密码',
  `login_fail_count` int(4) NULL DEFAULT 0 COMMENT '登录失败次数',
  `login_ip` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '登录IP',
  `login_date` datetime NULL DEFAULT NULL COMMENT '登录时间',
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `create_by` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '创建人',
  `create_date` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建日期',
  `update_by` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '修改人',
  `update_date` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `version` int(11) NULL DEFAULT 0 COMMENT '版本号',
  `deleted` int(1) NULL DEFAULT 0 COMMENT '是否删除',
  `trace_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '日志ID',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user0
-- ----------------------------
INSERT INTO `sys_user0` VALUES ('admin', 'admin', '', '123456', 0, '127.0.0.1', '2020-11-05 11:56:56', 1, '', NULL, NULL, '2020-11-05 11:56:56', 1, 0, NULL, '');

-- ----------------------------
-- Table structure for sys_user1
-- ----------------------------
DROP TABLE IF EXISTS `sys_user1`;
CREATE TABLE `sys_user1`  (
  `code` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '编码',
  `name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '名字',
  `state` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '状态',
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '密码',
  `login_fail_count` int(4) NULL DEFAULT 0 COMMENT '登录失败次数',
  `login_ip` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '登录IP',
  `login_date` datetime NULL DEFAULT NULL COMMENT '登录时间',
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `create_by` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '创建人',
  `create_date` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建日期',
  `update_by` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '修改人',
  `update_date` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `version` int(11) NULL DEFAULT 0 COMMENT '版本号',
  `deleted` int(1) NULL DEFAULT 0 COMMENT '是否删除',
  `trace_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '日志ID',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 31 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user1
-- ----------------------------
INSERT INTO `sys_user1` VALUES ('test1', 'test1', '', '123456', 0, '127.0.0.1', '2020-11-05 11:56:56', 10, '', NULL, NULL, '2020-11-05 11:56:56', 1, 0, NULL, '');
INSERT INTO `sys_user1` VALUES ('test2', 'test2', '', '123456', 0, '127.0.0.1', '2020-11-05 11:56:56', 11, '', NULL, NULL, '2020-11-05 11:56:56', 1, 0, NULL, '');
INSERT INTO `sys_user1` VALUES ('test3', 'test3', '', '123456', 0, '127.0.0.1', '2020-11-05 11:56:56', 12, '', NULL, NULL, '2020-11-05 11:56:56', 1, 0, NULL, '');
INSERT INTO `sys_user1` VALUES ('test4', 'test4', '', '123456', 0, '127.0.0.1', '2020-11-05 11:56:56', 13, '', NULL, NULL, '2020-11-05 11:56:56', 1, 0, NULL, '');
INSERT INTO `sys_user1` VALUES ('test5', 'test5', '', '123456', 0, '127.0.0.1', '2020-11-05 11:56:56', 14, '', NULL, NULL, '2020-11-05 11:56:56', 1, 0, NULL, '');
INSERT INTO `sys_user1` VALUES ('test6', 'test6', '', '123456', 0, '127.0.0.1', '2020-11-05 11:56:56', 15, '', NULL, NULL, '2020-11-05 11:56:56', 1, 0, NULL, '');
INSERT INTO `sys_user1` VALUES ('test7', 'test7', '', '123456', 0, '127.0.0.1', '2020-11-05 11:56:56', 16, '', NULL, NULL, '2020-11-05 11:56:56', 1, 0, NULL, '');
INSERT INTO `sys_user1` VALUES ('test8', 'test8', '', '123456', 0, '127.0.0.1', '2020-11-05 11:56:56', 17, '', NULL, NULL, '2020-11-05 11:56:56', 1, 0, NULL, '');
INSERT INTO `sys_user1` VALUES ('test9', 'test9', '', '123456', 0, '127.0.0.1', '2020-11-05 11:56:56', 18, '', NULL, NULL, '2020-11-05 11:56:56', 1, 0, NULL, '');
INSERT INTO `sys_user1` VALUES ('test10', 'test10', '', '123456', 0, '127.0.0.1', '2020-11-05 11:56:56', 19, '', NULL, NULL, '2020-11-05 11:56:56', 1, 0, NULL, '');
INSERT INTO `sys_user1` VALUES ('test11', 'test11', '', '123456', 0, '127.0.0.1', '2020-11-05 11:56:56', 20, '', NULL, NULL, '2020-11-05 11:56:56', 1, 0, NULL, '');
INSERT INTO `sys_user1` VALUES ('test12', 'test12', '', '123456', 0, '127.0.0.1', '2020-11-05 11:56:56', 21, '', NULL, NULL, '2020-11-05 11:56:56', 1, 0, NULL, '');
INSERT INTO `sys_user1` VALUES ('test13', 'test13', '', '123456', 0, '127.0.0.1', '2020-11-05 11:56:56', 22, '', NULL, NULL, '2020-11-05 11:56:56', 1, 0, NULL, '');
INSERT INTO `sys_user1` VALUES ('test14', 'test14', '', '123456', 0, '127.0.0.1', '2020-11-05 11:56:56', 23, '', NULL, NULL, '2020-11-05 11:56:56', 1, 0, NULL, '');
INSERT INTO `sys_user1` VALUES ('test15', 'test15', '', '123456', 0, '127.0.0.1', '2020-11-05 11:56:56', 24, '', NULL, NULL, '2020-11-05 11:56:56', 1, 0, NULL, '');
INSERT INTO `sys_user1` VALUES ('test16', 'test16', '', '123456', 0, '127.0.0.1', '2020-11-05 11:56:56', 25, '', NULL, NULL, '2020-11-05 11:56:56', 1, 0, NULL, '');
INSERT INTO `sys_user1` VALUES ('test17', 'test17', '', '123456', 0, '127.0.0.1', '2020-11-05 11:56:56', 26, '', NULL, NULL, '2020-11-05 11:56:56', 1, 0, NULL, '');
INSERT INTO `sys_user1` VALUES ('test18', 'test18', '', '123456', 0, '127.0.0.1', '2020-11-05 11:56:56', 27, '', NULL, NULL, '2020-11-05 11:56:56', 1, 0, NULL, '');
INSERT INTO `sys_user1` VALUES ('test19', 'test19', '', '123456', 0, '127.0.0.1', '2020-11-05 11:56:56', 28, '', NULL, NULL, '2020-11-05 11:56:56', 1, 0, NULL, '');
INSERT INTO `sys_user1` VALUES ('test20', 'test20', '', '123456', 0, '127.0.0.1', '2020-11-05 11:56:56', 29, '', NULL, NULL, '2020-11-05 11:56:56', 1, 0, NULL, '');
INSERT INTO `sys_user1` VALUES ('test21', 'test21', '', '123456', 0, '127.0.0.1', '2020-11-05 11:56:56', 30, '', NULL, NULL, '2020-11-05 11:56:56', 1, 0, NULL, '');

SET FOREIGN_KEY_CHECKS = 1;
