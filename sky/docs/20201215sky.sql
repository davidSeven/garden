/*
Navicat MySQL Data Transfer

Source Server         : localhost-root
Source Server Version : 50717
Source Host           : localhost:3306
Source Database       : sky

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2020-12-15 16:26:09
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for sys_dictionary
-- ----------------------------
DROP TABLE IF EXISTS `sys_dictionary`;
CREATE TABLE `sys_dictionary` (
  `code` varchar(50) DEFAULT '' COMMENT '编码',
  `name` varchar(50) DEFAULT '' COMMENT '名称',
  `parent_id` int(11) DEFAULT '0' COMMENT '父级ID',
  `parent_code` varchar(50) DEFAULT '' COMMENT '父级编码',
  `parent_name` varchar(50) DEFAULT '' COMMENT '父级名称',
  `state` varchar(20) DEFAULT '' COMMENT '状态',
  `sort` int(11) DEFAULT '0' COMMENT '顺序',
  `value` varchar(255) DEFAULT '' COMMENT '值',
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `create_by` varchar(20) DEFAULT '' COMMENT '创建人',
  `create_date` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建日期',
  `update_by` varchar(20) DEFAULT '' COMMENT '修改人',
  `update_date` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `version` int(11) DEFAULT '0' COMMENT '版本号',
  `deleted` int(1) DEFAULT '0' COMMENT '是否删除',
  `trace_id` varchar(32) DEFAULT '' COMMENT '日志ID',
  `remark` varchar(255) DEFAULT '' COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 COMMENT='数据字典';

-- ----------------------------
-- Records of sys_dictionary
-- ----------------------------
INSERT INTO `sys_dictionary` VALUES ('', '', '0', '', '', '', '0', '', '2', null, '2020-11-04 11:58:47', null, '2020-11-04 11:58:47', '0', '0', null, '');
INSERT INTO `sys_dictionary` VALUES ('', '', '0', '', '', '', '0', '', '3', null, '2020-11-05 17:52:34', null, '2020-11-05 17:52:34', '0', '0', null, '');
INSERT INTO `sys_dictionary` VALUES ('', '', '0', '', '', '', '0', '', '4', '0', '2020-11-05 17:56:18', '0', '2020-11-05 17:56:18', '0', '0', '0721b6c44ce649e989e0d7368da77b8f', '');
INSERT INTO `sys_dictionary` VALUES ('', '', '0', '', '', '', '0', '', '5', '0', '2020-11-05 17:56:33', '0', '2020-11-05 17:56:33', '0', '0', 'ee1bdde74ba64bc6aa5183b72a6bd89f', '');
INSERT INTO `sys_dictionary` VALUES ('', '', '0', '', '', '', '0', '', '6', '0', '2020-11-06 09:17:34', '0', '2020-11-06 09:17:34', '0', '0', 'bc54a78bc5ae464e82e639997712fb4f', '');
INSERT INTO `sys_dictionary` VALUES ('', '', '0', '', '', '', '0', '', '7', '0', '2020-11-06 09:23:26', '0', '2020-11-06 09:23:26', '0', '0', 'dfb14001a9e14d408b3725c4d2a6d29e', '');
INSERT INTO `sys_dictionary` VALUES ('', '', '0', '', '', '', '0', '', '8', '0', '2020-11-20 10:26:15', '0', '2020-11-20 10:26:15', '0', '0', 'fde4c37a53b94c47a5e447fd080fcf89', '');

-- ----------------------------
-- Table structure for sys_file_info
-- ----------------------------
DROP TABLE IF EXISTS `sys_file_info`;
CREATE TABLE `sys_file_info` (
  `biz_id` varchar(40) DEFAULT '' COMMENT '业务ID',
  `biz_code` varchar(40) DEFAULT '' COMMENT '业务编码',
  `original_name` varchar(200) DEFAULT '' COMMENT '原文件名称',
  `extend_name` varchar(20) DEFAULT '' COMMENT '扩展名',
  `name` varchar(200) DEFAULT '' COMMENT '文件名',
  `physical_path` varchar(255) DEFAULT '' COMMENT '物理存储路径',
  `visit_path` varchar(255) DEFAULT '' COMMENT '访问路径',
  `size` int(11) DEFAULT '0' COMMENT '文件大小',
  `content_type` varchar(100) DEFAULT '' COMMENT '文件头类型',
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `create_by` varchar(20) DEFAULT '' COMMENT '创建人',
  `create_date` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建日期',
  `update_by` varchar(20) DEFAULT '' COMMENT '修改人',
  `update_date` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `version` int(11) DEFAULT '0' COMMENT '版本号',
  `deleted` int(1) DEFAULT '0' COMMENT '是否删除',
  `trace_id` varchar(32) DEFAULT '' COMMENT '日志ID',
  `remark` varchar(255) DEFAULT '' COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='文件信息';

-- ----------------------------
-- Records of sys_file_info
-- ----------------------------

-- ----------------------------
-- Table structure for sys_job
-- ----------------------------
DROP TABLE IF EXISTS `sys_job`;
CREATE TABLE `sys_job` (
  `task_name` varchar(100) DEFAULT '' COMMENT '任务名称',
  `enable` int(1) DEFAULT '1' COMMENT '任务状态 1=启用 2=禁用',
  `cron_time` varchar(200) DEFAULT '' COMMENT '表达式',
  `has_redo` int(1) DEFAULT '0' COMMENT '是否启动重试机制',
  `end_redo_times` int(2) DEFAULT '0' COMMENT '截止重新执行次数',
  `current_redo_time` int(2) DEFAULT '0' COMMENT '当前第几次自动重试执行',
  `process_status` int(1) DEFAULT '0' COMMENT '是否正在执行中,0--没有正在执行,1--正在手动/自动执行 2--执行失败',
  `process_date` datetime DEFAULT NULL COMMENT '触发时间',
  `process_end_date` datetime DEFAULT NULL COMMENT '结束时间',
  `process_stamp` int(11) DEFAULT '0' COMMENT '耗时',
  `process_msg` blob COMMENT '执行信息',
  `task_group` varchar(100) DEFAULT '' COMMENT '任务分组',
  `http_method` varchar(20) DEFAULT '' COMMENT '请求方式',
  `timing_call_url` varchar(255) DEFAULT '' COMMENT '调用地址',
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `create_by` varchar(20) DEFAULT '' COMMENT '创建人',
  `create_date` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建日期',
  `update_by` varchar(20) DEFAULT '' COMMENT '修改人',
  `update_date` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `version` int(11) DEFAULT '0' COMMENT '版本号',
  `deleted` int(1) DEFAULT '0' COMMENT '是否删除',
  `trace_id` varchar(32) DEFAULT '' COMMENT '日志ID',
  `remark` varchar(255) DEFAULT '' COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='任务调度';

-- ----------------------------
-- Records of sys_job
-- ----------------------------

-- ----------------------------
-- Table structure for sys_job_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_job_log`;
CREATE TABLE `sys_job_log` (
  `task_id` int(11) DEFAULT '0' COMMENT '任务ID',
  `response_msg` blob COMMENT '响应信息',
  `begin_time` datetime DEFAULT NULL COMMENT '开始时间',
  `end_time` datetime DEFAULT NULL COMMENT '结束时间',
  `consume_time` int(11) DEFAULT '0' COMMENT '耗时',
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `create_by` varchar(20) DEFAULT '' COMMENT '创建人',
  `create_date` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建日期',
  `update_by` varchar(20) DEFAULT '' COMMENT '修改人',
  `update_date` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `version` int(11) DEFAULT '0' COMMENT '版本号',
  `deleted` int(1) DEFAULT '0' COMMENT '是否删除',
  `trace_id` varchar(32) DEFAULT '' COMMENT '日志ID',
  `remark` varchar(255) DEFAULT '' COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='任务调度日志';

-- ----------------------------
-- Records of sys_job_log
-- ----------------------------

-- ----------------------------
-- Table structure for sys_login_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_login_log`;
CREATE TABLE `sys_login_log` (
  `state` varchar(20) DEFAULT '' COMMENT '状态',
  `login_ip` varchar(20) DEFAULT '' COMMENT '登录IP',
  `login_time` datetime DEFAULT NULL COMMENT '登录时间',
  `login_code` varchar(100) DEFAULT '' COMMENT '登录帐号',
  `login_password` varchar(100) DEFAULT '' COMMENT '登录密码',
  `login_verify_code_token` varchar(32) DEFAULT '' COMMENT '验证码token',
  `login_verify_code` varchar(20) DEFAULT '' COMMENT '登录验证码',
  `login_token` varchar(32) DEFAULT '' COMMENT '登录token',
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `create_by` varchar(20) DEFAULT '' COMMENT '创建人',
  `create_date` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建日期',
  `update_by` varchar(20) DEFAULT '' COMMENT '修改人',
  `update_date` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `version` int(11) DEFAULT '0' COMMENT '版本号',
  `deleted` int(1) DEFAULT '0' COMMENT '是否删除',
  `trace_id` varchar(32) DEFAULT '' COMMENT '日志ID',
  `remark` varchar(255) DEFAULT '' COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8 COMMENT='登录日志';

-- ----------------------------
-- Records of sys_login_log
-- ----------------------------
INSERT INTO `sys_login_log` VALUES ('SUCCESS', '127.0.0.1', '2020-11-06 13:50:13', 'admin', '123', '', 'XJ3y', 'a591d10ec3164c6cb5e5890865f0e6d0', '4', null, '2020-11-06 13:50:13', null, '2020-11-06 13:50:13', '0', '0', null, '');
INSERT INTO `sys_login_log` VALUES ('FAIL', '127.0.0.1', '2020-11-09 14:24:03', 'admin', '111111', '', 'djDA', '', '5', null, '2020-11-09 14:24:03', null, '2020-11-09 14:24:03', '0', '0', null, '');
INSERT INTO `sys_login_log` VALUES ('SUCCESS', '127.0.0.1', '2020-11-09 14:24:34', 'admin', '123456', '', 'djDA', '3169f1ac180546d195c47fe9eac84834', '6', null, '2020-11-09 14:24:34', null, '2020-11-09 14:24:34', '0', '0', null, '');
INSERT INTO `sys_login_log` VALUES ('FAIL', '127.0.0.1', '2020-11-09 14:26:01', 'admin', '111111', '', 'KMrl', '', '7', null, '2020-11-09 14:26:01', null, '2020-11-09 14:26:01', '0', '0', null, '');
INSERT INTO `sys_login_log` VALUES ('SUCCESS', '127.0.0.1', '2020-11-09 14:26:28', 'admin', '123456', '', 'WBGJ', 'ae61f4271b3642b4be41c2704babd391', '8', null, '2020-11-09 14:26:28', null, '2020-11-09 14:26:28', '0', '0', null, '');
INSERT INTO `sys_login_log` VALUES ('SUCCESS', '127.0.0.1', '2020-11-09 14:29:12', 'admin', '123456', '', 'KrL3', '5ae84a3ff04544eeb365221aba52997a', '9', null, '2020-11-09 14:29:12', null, '2020-11-09 14:29:12', '0', '0', null, '');
INSERT INTO `sys_login_log` VALUES ('SUCCESS', '127.0.0.1', '2020-11-11 11:53:30', 'admin', '123456', '', '6gGw', '461e54fb3b5b421b9127e7604f6736c4', '10', null, '2020-11-11 11:53:30', null, '2020-11-11 11:53:30', '0', '0', null, '');
INSERT INTO `sys_login_log` VALUES ('SUCCESS', '127.0.0.1', '2020-11-11 13:36:39', 'admin', '123456', '', 'xg4Y', 'b56adbc098614421a767c61ff9f37414', '11', null, '2020-11-11 13:36:39', null, '2020-11-11 13:36:39', '0', '0', null, '');
INSERT INTO `sys_login_log` VALUES ('SUCCESS', '127.0.0.1', '2020-11-11 13:41:40', 'admin', '123456', '', 'JjWx', '21e19d70a09d4b7d846ed87778b32a39', '12', null, '2020-11-11 13:41:40', null, '2020-11-11 13:41:40', '0', '0', null, '');
INSERT INTO `sys_login_log` VALUES ('SUCCESS', '127.0.0.1', '2020-11-11 13:51:23', 'admin', '123456', '', 'PB5A', '31765593b401478180dfcd1b9f8a8c51', '13', null, '2020-11-11 13:51:23', null, '2020-11-11 13:51:23', '0', '0', null, '');
INSERT INTO `sys_login_log` VALUES ('SUCCESS', '127.0.0.1', '2020-11-11 13:54:46', 'admin', '123456', '', 'ltUj', 'ea81b6223e024435bf6970aaf338d70f', '14', null, '2020-11-11 13:54:46', null, '2020-11-11 13:54:46', '0', '0', null, '');
INSERT INTO `sys_login_log` VALUES ('SUCCESS', '127.0.0.1', '2020-11-11 13:55:53', 'admin', '123456', '', 'ttsu', 'ba59818100d94f5c9b68af994ac98719', '15', null, '2020-11-11 13:55:53', null, '2020-11-11 13:55:53', '0', '0', null, '');
INSERT INTO `sys_login_log` VALUES ('SUCCESS', '127.0.0.1', '2020-11-11 13:56:37', 'admin', '123456', '', 'ySHx', '4302561564c34fc0b61b103816d6f801', '16', null, '2020-11-11 13:56:37', null, '2020-11-11 13:56:37', '0', '0', null, '');
INSERT INTO `sys_login_log` VALUES ('SUCCESS', '127.0.0.1', '2020-11-11 14:01:10', 'admin', '123456', '', 'yuGM', '449f085f251241abae22f9f5986188a6', '17', null, '2020-11-11 14:01:10', null, '2020-11-11 14:01:10', '0', '0', null, '');
INSERT INTO `sys_login_log` VALUES ('SUCCESS', '127.0.0.1', '2020-12-14 17:43:04', 'admin', '123456', '', 'l24u', 'b7a7fb0e76ac4528ad75137aae5e2671', '18', null, '2020-12-14 17:43:04', null, '2020-12-14 17:43:04', '0', '0', null, '');
INSERT INTO `sys_login_log` VALUES ('SUCCESS', '127.0.0.1', '2020-12-14 17:47:41', 'admin', '123456', '4df89ac7072d4ba2b5db3195ceb17ae6', 'yPfa', '0e8a0fd63c8e424faa990ea74ff34e9f', '19', null, '2020-12-14 17:47:41', null, '2020-12-14 17:47:41', '0', '0', null, '');
INSERT INTO `sys_login_log` VALUES ('SUCCESS', '127.0.0.1', '2020-12-14 17:48:53', 'admin', '123456', '69bddd8202b84d54a11938766a271279', '28Ut', 'c78d6096a56e40bc8d13b46ac69ed07c', '20', null, '2020-12-14 17:48:53', null, '2020-12-14 17:48:53', '0', '0', null, '');
INSERT INTO `sys_login_log` VALUES ('SUCCESS', '127.0.0.1,127.0.0.1', '2020-12-15 16:08:01', 'admin', '123456', '0042b464f0354c3db30fd5cd379464dd', 'f9Ux', '061385bc12a841f1bc4b1d90f35bd68d', '21', null, '2020-12-15 16:08:01', null, '2020-12-15 16:08:01', '0', '0', null, '');
INSERT INTO `sys_login_log` VALUES ('SUCCESS', '127.0.0.1,127.0.0.1', '2020-12-15 16:20:49', 'admin', '123456', '25a0a74c107d408393f644ced9bebba4', 'ccx3', '5d91a642f602428fb053b949d54801fc', '22', null, '2020-12-15 16:20:49', null, '2020-12-15 16:20:49', '0', '0', null, '');

-- ----------------------------
-- Table structure for sys_login_visit_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_login_visit_log`;
CREATE TABLE `sys_login_visit_log` (
  `token` varchar(32) NOT NULL DEFAULT '' COMMENT 'token',
  `ip` varchar(20) DEFAULT '' COMMENT 'ip',
  `need_verify_code` int(1) DEFAULT '0' COMMENT '是否需要验证码，0不需要，1需要',
  `verify_code` varchar(20) DEFAULT '' COMMENT '验证码',
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `create_by` varchar(20) DEFAULT '' COMMENT '创建人',
  `create_date` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建日期',
  `update_by` varchar(20) DEFAULT '' COMMENT '修改人',
  `update_date` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `version` int(11) DEFAULT '0' COMMENT '版本号',
  `deleted` int(1) DEFAULT '0' COMMENT '是否删除',
  `trace_id` varchar(32) DEFAULT '' COMMENT '日志ID',
  `remark` varchar(255) DEFAULT '' COMMENT '备注',
  PRIMARY KEY (`id`),
  KEY `sys_login_visit_log_token` (`token`) USING HASH COMMENT 'token'
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8 COMMENT='登录访问日志';

-- ----------------------------
-- Records of sys_login_visit_log
-- ----------------------------
INSERT INTO `sys_login_visit_log` VALUES ('9066336d178d44eda1ba6cc35e3bf1e5', '127.0.0.1', '1', 'l24u', '1', null, '2020-12-14 17:41:01', null, '2020-12-14 17:41:01', '0', '0', null, '');
INSERT INTO `sys_login_visit_log` VALUES ('4df89ac7072d4ba2b5db3195ceb17ae6', '127.0.0.1', '1', 'yPfa', '2', null, '2020-12-14 17:47:38', null, '2020-12-14 17:47:38', '0', '0', null, '');
INSERT INTO `sys_login_visit_log` VALUES ('21f17b46510d4f6fa546de2776308adf', '127.0.0.1', '1', 'rawa', '3', null, '2020-12-14 17:48:35', null, '2020-12-14 17:48:35', '0', '0', null, '');
INSERT INTO `sys_login_visit_log` VALUES ('9842e8297d93409986523cf5dea56c83', '127.0.0.1', '1', 'elR4', '4', null, '2020-12-14 17:48:36', null, '2020-12-14 17:48:36', '0', '0', null, '');
INSERT INTO `sys_login_visit_log` VALUES ('d13a93fdb9f446e18980a43cac154425', '127.0.0.1', '1', 'KuBR', '5', null, '2020-12-14 17:48:37', null, '2020-12-14 17:48:37', '0', '0', null, '');
INSERT INTO `sys_login_visit_log` VALUES ('aa3f62dae2ad47639f3fcadcc00cf2ff', '127.0.0.1', '1', 'ck4t', '6', null, '2020-12-14 17:48:38', null, '2020-12-14 17:48:38', '0', '0', null, '');
INSERT INTO `sys_login_visit_log` VALUES ('69bddd8202b84d54a11938766a271279', '127.0.0.1', '1', '28Ut', '7', null, '2020-12-14 17:48:39', null, '2020-12-14 17:48:39', '0', '0', null, '');
INSERT INTO `sys_login_visit_log` VALUES ('411af207a6d148c582b063ede2ec5223', '127.0.0.1,127.0.0.1', '1', 'vqbc', '8', null, '2020-12-15 16:07:42', null, '2020-12-15 16:07:42', '0', '0', null, '');
INSERT INTO `sys_login_visit_log` VALUES ('0042b464f0354c3db30fd5cd379464dd', '127.0.0.1,127.0.0.1', '1', 'f9Ux', '9', null, '2020-12-15 16:07:56', null, '2020-12-15 16:07:56', '0', '0', null, '');
INSERT INTO `sys_login_visit_log` VALUES ('0817373cc918447aae7a1d8e549c80eb', '127.0.0.1,127.0.0.1', '1', 'wCMW', '10', null, '2020-12-15 16:20:31', null, '2020-12-15 16:20:31', '0', '0', null, '');
INSERT INTO `sys_login_visit_log` VALUES ('9f32736410c54915868691779e554b67', '127.0.0.1,127.0.0.1', '1', 'cy7f', '11', null, '2020-12-15 16:20:45', null, '2020-12-15 16:20:45', '0', '0', null, '');
INSERT INTO `sys_login_visit_log` VALUES ('07514753b42540e885ed5dc2c12501b5', '127.0.0.1,127.0.0.1', '1', 'GcHf', '12', null, '2020-12-15 16:20:46', null, '2020-12-15 16:20:46', '0', '0', null, '');
INSERT INTO `sys_login_visit_log` VALUES ('62d618359c284350bdbf0a16619531c8', '127.0.0.1,127.0.0.1', '1', 'FcyN', '13', null, '2020-12-15 16:20:47', null, '2020-12-15 16:20:47', '0', '0', null, '');
INSERT INTO `sys_login_visit_log` VALUES ('25a0a74c107d408393f644ced9bebba4', '127.0.0.1,127.0.0.1', '1', 'ccx3', '14', null, '2020-12-15 16:20:48', null, '2020-12-15 16:20:48', '0', '0', null, '');

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu` (
  `name` varchar(64) DEFAULT '' COMMENT '名称',
  `path` varchar(64) DEFAULT '' COMMENT '地址',
  `sort` int(11) DEFAULT '0' COMMENT '顺序',
  `parent_id` int(11) DEFAULT '0' COMMENT '父级id',
  `icon` varchar(64) DEFAULT '' COMMENT '图标',
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `create_by` varchar(20) DEFAULT '' COMMENT '创建人',
  `create_date` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建日期',
  `update_by` varchar(20) DEFAULT '' COMMENT '修改人',
  `update_date` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `version` int(11) DEFAULT '0' COMMENT '版本号',
  `deleted` int(1) DEFAULT '0' COMMENT '是否删除',
  `trace_id` varchar(32) DEFAULT '' COMMENT '日志ID',
  `remark` varchar(255) DEFAULT '' COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='菜单管理';

-- ----------------------------
-- Records of sys_menu
-- ----------------------------

-- ----------------------------
-- Table structure for sys_online_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_online_user`;
CREATE TABLE `sys_online_user` (
  `user_id` int(11) NOT NULL DEFAULT '0' COMMENT '用户id',
  `user_code` varchar(20) NOT NULL DEFAULT '' COMMENT '用户编号',
  `login_token` varchar(32) NOT NULL DEFAULT '' COMMENT '登录token',
  `login_ip` varchar(100) DEFAULT '' COMMENT '登录ip',
  `login_date` datetime DEFAULT NULL COMMENT '登录时间',
  `last_visit_date` datetime DEFAULT NULL COMMENT '最后访问时间',
  `lease_time` int(11) DEFAULT '0' COMMENT '租期',
  `expire_date` datetime DEFAULT NULL COMMENT '过期时间',
  `logout_ip` varchar(100) DEFAULT '' COMMENT '登出ip',
  `logout_date` datetime DEFAULT NULL COMMENT '登出时间',
  `state` varchar(20) DEFAULT '' COMMENT '状态，01在线，02下线',
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `create_by` varchar(20) DEFAULT '' COMMENT '创建人',
  `create_date` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建日期',
  `update_by` varchar(20) DEFAULT '' COMMENT '修改人',
  `update_date` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `version` int(11) DEFAULT '0' COMMENT '版本号',
  `deleted` int(1) DEFAULT '0' COMMENT '是否删除',
  `trace_id` varchar(32) DEFAULT '' COMMENT '日志ID',
  `remark` varchar(255) DEFAULT '' COMMENT '备注',
  PRIMARY KEY (`id`),
  KEY `sys_online_user_user_id` (`user_id`) USING BTREE COMMENT '用户id',
  KEY `sys_online_user_user_code` (`user_code`) USING HASH COMMENT '用户编码',
  KEY `sys_online_user_login_token` (`login_token`) USING BTREE COMMENT '登录token'
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='在线用户管理';

-- ----------------------------
-- Records of sys_online_user
-- ----------------------------
INSERT INTO `sys_online_user` VALUES ('1', 'admin', '061385bc12a841f1bc4b1d90f35bd68d', '127.0.0.1,127.0.0.1', '2020-12-15 16:08:01', '2020-12-15 16:20:30', '28800', '2020-12-16 00:20:30', '', '2020-12-15 16:20:30', 'OFFLINE', '1', null, '2020-12-15 16:08:01', null, '2020-12-15 16:20:31', '1', '0', null, '');
INSERT INTO `sys_online_user` VALUES ('1', 'admin', '5d91a642f602428fb053b949d54801fc', '127.0.0.1,127.0.0.1', '2020-12-15 16:20:49', '2020-12-15 16:24:11', '28800', '2020-12-16 00:24:11', '', null, 'ONLINE', '2', null, '2020-12-15 16:20:49', null, '2020-12-15 16:20:49', '0', '0', null, '');

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `code` varchar(20) DEFAULT '' COMMENT '编码',
  `name` varchar(100) DEFAULT '' COMMENT '名字',
  `state` varchar(20) DEFAULT '' COMMENT '状态',
  `password` varchar(255) DEFAULT '' COMMENT '密码',
  `login_fail_count` int(4) DEFAULT '0' COMMENT '登录失败次数',
  `login_ip` varchar(100) DEFAULT '' COMMENT '登录IP',
  `login_date` datetime DEFAULT NULL COMMENT '登录时间',
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `create_by` varchar(20) DEFAULT '' COMMENT '创建人',
  `create_date` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建日期',
  `update_by` varchar(20) DEFAULT '' COMMENT '修改人',
  `update_date` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `version` int(11) DEFAULT '0' COMMENT '版本号',
  `deleted` int(1) DEFAULT '0' COMMENT '是否删除',
  `trace_id` varchar(32) DEFAULT '' COMMENT '日志ID',
  `remark` varchar(255) DEFAULT '' COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='用户';

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('admin', '', '', '123456', '0', '127.0.0.1', '2020-11-05 11:56:56', '1', '', null, null, '2020-11-05 11:56:56', '1', '0', null, '');
