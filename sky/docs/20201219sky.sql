/*
Navicat MySQL Data Transfer

Source Server         : localhost-root
Source Server Version : 50717
Source Host           : localhost:3306
Source Database       : sky

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2020-12-19 16:12:09
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
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=utf8 COMMENT='登录日志';

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
INSERT INTO `sys_login_log` VALUES ('SUCCESS', '127.0.0.1,127.0.0.1', '2020-12-16 16:47:03', 'admin', '123456', '7ff288660bf54a2189c1b81fa72482cc', 'uNTj', '334ba9cf71d54a12a88c5b8df96c87fb', '23', null, '2020-12-16 16:47:03', null, '2020-12-16 16:47:03', '0', '0', null, '');
INSERT INTO `sys_login_log` VALUES ('SUCCESS', '127.0.0.1,127.0.0.1', '2020-12-17 09:32:20', 'admin', '123456', 'f50dabf7b25c440b90b8ba25b67c2079', 'uygR', '8fb2604704114d14887a5ce18191fa3a', '24', null, '2020-12-17 09:32:20', null, '2020-12-17 09:32:20', '0', '0', null, '');
INSERT INTO `sys_login_log` VALUES ('SUCCESS', '127.0.0.1,127.0.0.1', '2020-12-18 11:19:20', 'admin', '123456', 'fa5c32b1666d4a5eb8924279e36dd869', 'JQZk', '0c9330c259194db2b317f52cd9998fa2', '25', null, '2020-12-18 11:19:20', null, '2020-12-18 11:19:20', '0', '0', null, '');
INSERT INTO `sys_login_log` VALUES ('SUCCESS', '127.0.0.1,127.0.0.1', '2020-12-18 11:19:46', 'admin', '123456', 'f04a434926a44de3bdfb1641315c795e', 'WdyZ', 'ab5427fe86814f36928d18e214a9c366', '26', null, '2020-12-18 11:19:46', null, '2020-12-18 11:19:46', '0', '0', null, '');
INSERT INTO `sys_login_log` VALUES ('SUCCESS', '127.0.0.1,127.0.0.1', '2020-12-18 11:21:57', 'admin', '123456', '4a9c848238374b55952b6bfd39c0a1b4', 'Dcft', '2203c714e9044514945539483ba11c66', '27', null, '2020-12-18 11:21:57', null, '2020-12-18 11:21:57', '0', '0', null, '');
INSERT INTO `sys_login_log` VALUES ('SUCCESS', '127.0.0.1,127.0.0.1', '2020-12-18 11:22:54', 'admin', '123456', '642ebc1af7b04da18aebf98f89b00a23', 'Jm7d', '1e3a4a78a7ac44af825dbbc959887bc3', '28', null, '2020-12-18 11:22:54', null, '2020-12-18 11:22:54', '0', '0', null, '');
INSERT INTO `sys_login_log` VALUES ('SUCCESS', '127.0.0.1,127.0.0.1', '2020-12-18 11:23:35', 'admin', '123456', 'fe143137ea504cd5b555c774b329f76f', 'fNaq', '20b5e082965a45558a2a612482297bc8', '29', null, '2020-12-18 11:23:35', null, '2020-12-18 11:23:35', '0', '0', null, '');
INSERT INTO `sys_login_log` VALUES ('SUCCESS', '127.0.0.1,127.0.0.1', '2020-12-18 11:24:59', 'admin', '123456', '0f34e0bef9734b98a129e9a913b71676', '352G', '91d68c9532d14753a0b1be09c9e9f3be', '30', null, '2020-12-18 11:24:59', null, '2020-12-18 11:24:59', '0', '0', null, '');
INSERT INTO `sys_login_log` VALUES ('SUCCESS', '127.0.0.1,127.0.0.1', '2020-12-18 11:25:13', 'admin', '123456', '50fd24bd1a13478db74318c9e28a2253', 'G6u5', 'f7025063d182489baea274af9b41f8b8', '31', null, '2020-12-18 11:25:13', null, '2020-12-18 11:25:13', '0', '0', null, '');
INSERT INTO `sys_login_log` VALUES ('SUCCESS', '127.0.0.1,127.0.0.1', '2020-12-18 11:51:10', 'admin', '123456', 'eb6f1875cf7c45c8844450c8bbe89925', 'RGBs', '7ca21e1d3dec4e4793b226eec9ec64b1', '32', null, '2020-12-18 11:51:10', null, '2020-12-18 11:51:10', '0', '0', null, '');
INSERT INTO `sys_login_log` VALUES ('SUCCESS', '127.0.0.1,127.0.0.1', '2020-12-19 11:44:01', 'admin', '123456', '5aef6defa78f4e2296d38c5f4ab91bb9', 'fBZ3', 'ebf9c1c37be74119946e875495861938', '33', null, '2020-12-19 11:44:01', null, '2020-12-19 11:44:01', '0', '0', null, '');
INSERT INTO `sys_login_log` VALUES ('SUCCESS', '127.0.0.1,127.0.0.1', '2020-12-19 11:55:54', 'admin', '123456', '29f8f7d1a2f0427e8e25911250452c69', 'SX46', 'bc241631068349e0b26004f19fb9b42f', '34', null, '2020-12-19 11:55:54', null, '2020-12-19 11:55:54', '0', '0', null, '');
INSERT INTO `sys_login_log` VALUES ('SUCCESS', '127.0.0.1,127.0.0.1', '2020-12-19 11:58:51', 'admin', '123456', 'ecd0413c98b54c6b9b86c3c43c7dfb2d', '3Cmc', 'f6a2dba2cacd4b60aa6adcfe65a48bf2', '35', null, '2020-12-19 11:58:51', null, '2020-12-19 11:58:51', '0', '0', null, '');
INSERT INTO `sys_login_log` VALUES ('SUCCESS', '127.0.0.1,127.0.0.1', '2020-12-19 14:43:27', 'admin', '123456', 'd5255b9e8c3a4cd4b4d273a1370c67f9', 'yagD', 'f443034d31d642a8a07941c9bf7a3117', '36', null, '2020-12-19 14:43:27', null, '2020-12-19 14:43:27', '0', '0', null, '');

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
) ENGINE=InnoDB AUTO_INCREMENT=39 DEFAULT CHARSET=utf8 COMMENT='登录访问日志';

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
INSERT INTO `sys_login_visit_log` VALUES ('495a7602ede34453bb91f562609a0d6f', '127.0.0.1,127.0.0.1', '1', 'bJe2', '15', null, '2020-12-16 16:45:32', null, '2020-12-16 16:45:32', '0', '0', null, '');
INSERT INTO `sys_login_visit_log` VALUES ('2e5b46fe8d0f4e1db0df471b3d8a4504', '127.0.0.1,127.0.0.1', '1', 'WVTH', '16', null, '2020-12-16 16:45:41', null, '2020-12-16 16:45:41', '0', '0', null, '');
INSERT INTO `sys_login_visit_log` VALUES ('ab94e3cf76d441cb950adbee917cd860', '127.0.0.1,127.0.0.1', '1', 'npJX', '17', null, '2020-12-16 16:45:43', null, '2020-12-16 16:45:43', '0', '0', null, '');
INSERT INTO `sys_login_visit_log` VALUES ('61323d26c82942af8d500e612a74b8a2', '127.0.0.1,127.0.0.1', '1', '87fT', '18', null, '2020-12-16 16:45:44', null, '2020-12-16 16:45:44', '0', '0', null, '');
INSERT INTO `sys_login_visit_log` VALUES ('3773ad100097445a9645878d6bc79d13', '127.0.0.1,127.0.0.1', '1', 'e5Jf', '19', null, '2020-12-16 16:45:46', null, '2020-12-16 16:45:46', '0', '0', null, '');
INSERT INTO `sys_login_visit_log` VALUES ('582c7092d0c0410aa662754a07d2bba2', '127.0.0.1,127.0.0.1', '1', 'x2Ze', '20', null, '2020-12-16 16:45:48', null, '2020-12-16 16:45:48', '0', '0', null, '');
INSERT INTO `sys_login_visit_log` VALUES ('7ff288660bf54a2189c1b81fa72482cc', '127.0.0.1,127.0.0.1', '1', 'uNTj', '21', null, '2020-12-16 16:45:49', null, '2020-12-16 16:45:49', '0', '0', null, '');
INSERT INTO `sys_login_visit_log` VALUES ('f50dabf7b25c440b90b8ba25b67c2079', '127.0.0.1,127.0.0.1', '1', 'uygR', '22', null, '2020-12-17 09:32:19', null, '2020-12-17 09:32:19', '0', '0', null, '');
INSERT INTO `sys_login_visit_log` VALUES ('fa5c32b1666d4a5eb8924279e36dd869', '127.0.0.1,127.0.0.1', '1', 'JQZk', '23', null, '2020-12-18 11:19:19', null, '2020-12-18 11:19:19', '0', '0', null, '');
INSERT INTO `sys_login_visit_log` VALUES ('f04a434926a44de3bdfb1641315c795e', '127.0.0.1,127.0.0.1', '1', 'WdyZ', '24', null, '2020-12-18 11:19:42', null, '2020-12-18 11:19:42', '0', '0', null, '');
INSERT INTO `sys_login_visit_log` VALUES ('4a9c848238374b55952b6bfd39c0a1b4', '127.0.0.1,127.0.0.1', '1', 'Dcft', '25', null, '2020-12-18 11:21:56', null, '2020-12-18 11:21:56', '0', '0', null, '');
INSERT INTO `sys_login_visit_log` VALUES ('dd78d3eabcd1430895d3b0189f7c86c7', '127.0.0.1,127.0.0.1', '1', 'H9ha', '26', null, '2020-12-18 11:22:52', null, '2020-12-18 11:22:52', '0', '0', null, '');
INSERT INTO `sys_login_visit_log` VALUES ('642ebc1af7b04da18aebf98f89b00a23', '127.0.0.1,127.0.0.1', '1', 'Jm7d', '27', null, '2020-12-18 11:22:53', null, '2020-12-18 11:22:53', '0', '0', null, '');
INSERT INTO `sys_login_visit_log` VALUES ('fe143137ea504cd5b555c774b329f76f', '127.0.0.1,127.0.0.1', '1', 'fNaq', '28', null, '2020-12-18 11:23:29', null, '2020-12-18 11:23:29', '0', '0', null, '');
INSERT INTO `sys_login_visit_log` VALUES ('0f34e0bef9734b98a129e9a913b71676', '127.0.0.1,127.0.0.1', '1', '352G', '29', null, '2020-12-18 11:24:59', null, '2020-12-18 11:24:59', '0', '0', null, '');
INSERT INTO `sys_login_visit_log` VALUES ('50fd24bd1a13478db74318c9e28a2253', '127.0.0.1,127.0.0.1', '1', 'G6u5', '30', null, '2020-12-18 11:25:12', null, '2020-12-18 11:25:12', '0', '0', null, '');
INSERT INTO `sys_login_visit_log` VALUES ('eb6f1875cf7c45c8844450c8bbe89925', '127.0.0.1,127.0.0.1', '1', 'RGBs', '31', null, '2020-12-18 11:51:09', null, '2020-12-18 11:51:09', '0', '0', null, '');
INSERT INTO `sys_login_visit_log` VALUES ('5aef6defa78f4e2296d38c5f4ab91bb9', '127.0.0.1,127.0.0.1', '1', 'fBZ3', '32', null, '2020-12-19 11:37:25', null, '2020-12-19 11:37:25', '0', '0', null, '');
INSERT INTO `sys_login_visit_log` VALUES ('8cba6281eced411887463c33f7b36515', '127.0.0.1,127.0.0.1', '1', 'Vec2', '33', null, '2020-12-19 11:55:48', null, '2020-12-19 11:55:48', '0', '0', null, '');
INSERT INTO `sys_login_visit_log` VALUES ('29f8f7d1a2f0427e8e25911250452c69', '127.0.0.1,127.0.0.1', '1', 'SX46', '34', null, '2020-12-19 11:55:52', null, '2020-12-19 11:55:52', '0', '0', null, '');
INSERT INTO `sys_login_visit_log` VALUES ('d03c5ae7afbf432b81a919a388bc46bc', '127.0.0.1,127.0.0.1', '1', 'fYmW', '35', null, '2020-12-19 11:58:09', null, '2020-12-19 11:58:09', '0', '0', null, '');
INSERT INTO `sys_login_visit_log` VALUES ('3bd39f95815f4290b9e2a4e25c059a1e', '127.0.0.1,127.0.0.1', '1', 'heZV', '36', null, '2020-12-19 11:58:47', null, '2020-12-19 11:58:47', '0', '0', null, '');
INSERT INTO `sys_login_visit_log` VALUES ('ecd0413c98b54c6b9b86c3c43c7dfb2d', '127.0.0.1,127.0.0.1', '1', '3Cmc', '37', null, '2020-12-19 11:58:49', null, '2020-12-19 11:58:49', '0', '0', null, '');
INSERT INTO `sys_login_visit_log` VALUES ('d5255b9e8c3a4cd4b4d273a1370c67f9', '127.0.0.1,127.0.0.1', '1', 'yagD', '38', null, '2020-12-19 14:43:24', null, '2020-12-19 14:43:24', '0', '0', null, '');

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu` (
  `name` varchar(64) DEFAULT '' COMMENT '名称',
  `path` varchar(64) DEFAULT '' COMMENT '地址',
  `component` varchar(255) DEFAULT '' COMMENT '组件名称',
  `sort` int(6) DEFAULT '0' COMMENT '顺序',
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
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8 COMMENT='菜单管理';

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES ('系统管理', '/system', '', '500', '0', '', '1', '0', '2020-12-17 09:12:38', '0', '2020-12-17 09:12:38', '0', '0', '5cc81dcd363346bb9d8fa3bbabf0b57b', '');
INSERT INTO `sys_menu` VALUES ('菜单管理', '/system/menu/index', '', '510', '1', '', '2', '0', '2020-12-17 09:16:29', '0', '2020-12-17 09:16:29', '0', '0', '17269a322a6b4fcfa449d96fe07bdf0a', '');
INSERT INTO `sys_menu` VALUES ('test', 'test', '', '50', '0', 'el-icon-setting', '3', 'admin', '2020-12-17 14:49:54', 'admin', '2020-12-17 17:08:28', '1', '0', '56b679ec81ed436593acf7e75118cac5', '333xxx');
INSERT INTO `sys_menu` VALUES ('3', '3', '', '0', '0', 'el-icon-setting', '4', 'admin', '2020-12-17 14:50:22', 'admin', '2020-12-17 14:50:22', '0', '0', '47c9915ca30d444a80b86d7a7f790a88', '');
INSERT INTO `sys_menu` VALUES ('a', 'a', '', '0', '0', '', '5', 'admin', '2020-12-17 14:50:39', 'admin', '2020-12-17 14:50:39', '0', '1', 'bf1bb821c65844ee9386d0268975b5b7', '');
INSERT INTO `sys_menu` VALUES ('33', '333', '', '0', '0', '', '6', 'admin', '2020-12-17 14:50:49', 'admin', '2020-12-17 14:50:49', '0', '1', '47308a702fc3436981f1e7844966b2f2', '');
INSERT INTO `sys_menu` VALUES ('3', '3', '', '0', '0', '', '7', 'admin', '2020-12-17 14:54:30', 'admin', '2020-12-17 14:54:30', '0', '1', '363742c27ff24495b2541593addf3818', '');
INSERT INTO `sys_menu` VALUES ('asdfasdf', 'asdfasdfasdf', '', '0', '0', '', '8', 'admin', '2020-12-17 14:55:54', 'admin', '2020-12-17 14:55:54', '0', '0', 'cc1c1d7c52424f859228732968465615', '');
INSERT INTO `sys_menu` VALUES ('任务调度管理', '/system/job/index', '', '500', '1', 'el-icon-setting', '9', 'admin', '2020-12-17 17:28:19', 'admin', '2020-12-17 17:28:19', '0', '0', 'f0cb3d0b7bd64157a73d7b43dc74ea01', '定时任务');
INSERT INTO `sys_menu` VALUES ('test', 'test', '', '0', '1', '', '10', 'admin', '2020-12-17 17:31:07', 'admin', '2020-12-17 17:31:07', '0', '0', '5110e93b18374f3e98998d6e89c88a2b', '');
INSERT INTO `sys_menu` VALUES ('test', 'test', '', '0', '0', '', '11', 'admin', '2020-12-17 17:31:40', 'admin', '2020-12-17 17:31:40', '0', '0', 'd8d9a31134f7455db459c961239f90d0', '');
INSERT INTO `sys_menu` VALUES ('test', 'test', '', '0', '0', '', '12', 'admin', '2020-12-17 17:31:43', 'admin', '2020-12-17 17:31:43', '0', '0', 'dbcb9d1df2ae4187bb800d39b631b00a', '');
INSERT INTO `sys_menu` VALUES ('test2', 'test2', '', '880', '1', '', '13', '0', '2020-12-17 17:51:12', '0', '2020-12-17 17:51:12', '0', '0', '184ed6aacb784fdb8d60af9addd3febf', '');
INSERT INTO `sys_menu` VALUES ('aaa', 'nnnn', '', '444', '0', 'el-icon-setting', '14', 'admin', '2020-12-19 16:08:01', 'admin', '2020-12-19 16:08:01', '0', '0', 'af9e8dbf75414e23a2d32cd1a03fbdbc', '');
INSERT INTO `sys_menu` VALUES ('aasdf', 'asasdf', '', '333', '10', 'el-icon-setting', '15', 'admin', '2020-12-19 16:08:22', 'admin', '2020-12-19 16:08:22', '0', '0', '6af9ffbacfed4d8cad7d8b7986dc82c3', '');

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
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8 COMMENT='在线用户管理';

-- ----------------------------
-- Records of sys_online_user
-- ----------------------------
INSERT INTO `sys_online_user` VALUES ('1', 'admin', '061385bc12a841f1bc4b1d90f35bd68d', '127.0.0.1,127.0.0.1', '2020-12-15 16:08:01', '2020-12-15 16:20:30', '28800', '2020-12-16 00:20:30', '', '2020-12-15 16:20:30', 'OFFLINE', '1', null, '2020-12-15 16:08:01', null, '2020-12-15 16:20:31', '1', '0', null, '');
INSERT INTO `sys_online_user` VALUES ('1', 'admin', '5d91a642f602428fb053b949d54801fc', '127.0.0.1,127.0.0.1', '2020-12-15 16:20:49', '2020-12-15 16:24:11', '28800', '2020-12-16 00:24:11', '', null, 'OFFLINE', '2', null, '2020-12-15 16:20:49', null, '2020-12-15 16:20:49', '0', '0', null, '');
INSERT INTO `sys_online_user` VALUES ('1', 'admin', '334ba9cf71d54a12a88c5b8df96c87fb', '127.0.0.1,127.0.0.1', '2020-12-16 16:47:03', '2020-12-16 20:04:52', '28800', '2020-12-17 04:04:52', '', null, 'OFFLINE', '3', null, '2020-12-16 16:47:03', null, '2020-12-16 16:47:03', '0', '0', null, '');
INSERT INTO `sys_online_user` VALUES ('1', 'admin', '8fb2604704114d14887a5ce18191fa3a', '127.0.0.1,127.0.0.1', '2020-12-17 09:32:20', '2020-12-17 17:51:25', '28800', '2020-12-18 01:51:25', '', null, 'OFFLINE', '4', null, '2020-12-17 09:32:20', null, '2020-12-17 09:32:20', '0', '0', null, '');
INSERT INTO `sys_online_user` VALUES ('1', 'admin', '0c9330c259194db2b317f52cd9998fa2', '127.0.0.1,127.0.0.1', '2020-12-18 11:19:20', '2020-12-18 11:19:20', '28800', '2020-12-18 19:19:20', '', null, 'ONLINE', '5', null, '2020-12-18 11:19:20', null, '2020-12-18 11:19:20', '0', '0', null, '');
INSERT INTO `sys_online_user` VALUES ('1', 'admin', 'ab5427fe86814f36928d18e214a9c366', '127.0.0.1,127.0.0.1', '2020-12-18 11:19:46', '2020-12-18 11:19:46', '28800', '2020-12-18 19:19:46', '', null, 'ONLINE', '6', null, '2020-12-18 11:19:46', null, '2020-12-18 11:19:46', '0', '0', null, '');
INSERT INTO `sys_online_user` VALUES ('1', 'admin', '2203c714e9044514945539483ba11c66', '127.0.0.1,127.0.0.1', '2020-12-18 11:21:57', '2020-12-18 11:21:57', '28800', '2020-12-18 19:21:57', '', null, 'ONLINE', '7', null, '2020-12-18 11:21:57', null, '2020-12-18 11:21:57', '0', '0', null, '');
INSERT INTO `sys_online_user` VALUES ('1', 'admin', '1e3a4a78a7ac44af825dbbc959887bc3', '127.0.0.1,127.0.0.1', '2020-12-18 11:22:54', '2020-12-18 11:22:54', '28800', '2020-12-18 19:22:54', '', null, 'ONLINE', '8', null, '2020-12-18 11:22:54', null, '2020-12-18 11:22:54', '0', '0', null, '');
INSERT INTO `sys_online_user` VALUES ('1', 'admin', '20b5e082965a45558a2a612482297bc8', '127.0.0.1,127.0.0.1', '2020-12-18 11:23:35', '2020-12-18 11:23:35', '28800', '2020-12-18 19:23:35', '', null, 'ONLINE', '9', null, '2020-12-18 11:23:35', null, '2020-12-18 11:23:35', '0', '0', null, '');
INSERT INTO `sys_online_user` VALUES ('1', 'admin', '91d68c9532d14753a0b1be09c9e9f3be', '127.0.0.1,127.0.0.1', '2020-12-18 11:24:59', '2020-12-18 11:24:59', '28800', '2020-12-18 19:24:59', '', null, 'ONLINE', '10', null, '2020-12-18 11:24:59', null, '2020-12-18 11:24:59', '0', '0', null, '');
INSERT INTO `sys_online_user` VALUES ('1', 'admin', 'f7025063d182489baea274af9b41f8b8', '127.0.0.1,127.0.0.1', '2020-12-18 11:25:13', '2020-12-18 11:51:09', '28800', '2020-12-18 19:51:09', '', '2020-12-18 11:51:09', 'OFFLINE', '11', null, '2020-12-18 11:25:13', null, '2020-12-18 11:51:09', '1', '0', null, '');
INSERT INTO `sys_online_user` VALUES ('1', 'admin', '7ca21e1d3dec4e4793b226eec9ec64b1', '127.0.0.1,127.0.0.1', '2020-12-18 11:51:10', '2020-12-18 11:52:36', '28800', '2020-12-18 19:52:36', '', null, 'ONLINE', '12', null, '2020-12-18 11:51:10', null, '2020-12-18 11:51:10', '0', '0', null, '');
INSERT INTO `sys_online_user` VALUES ('1', 'admin', 'ebf9c1c37be74119946e875495861938', '127.0.0.1,127.0.0.1', '2020-12-19 11:44:01', '2020-12-19 11:44:01', '28800', '2020-12-19 19:44:01', '', null, 'ONLINE', '13', null, '2020-12-19 11:44:01', null, '2020-12-19 11:44:01', '0', '0', null, '');
INSERT INTO `sys_online_user` VALUES ('1', 'admin', 'bc241631068349e0b26004f19fb9b42f', '127.0.0.1,127.0.0.1', '2020-12-19 11:55:54', '2020-12-19 11:55:54', '28800', '2020-12-19 19:55:54', '', null, 'ONLINE', '14', null, '2020-12-19 11:55:54', null, '2020-12-19 11:55:54', '0', '0', null, '');
INSERT INTO `sys_online_user` VALUES ('1', 'admin', 'f6a2dba2cacd4b60aa6adcfe65a48bf2', '127.0.0.1,127.0.0.1', '2020-12-19 11:58:51', '2020-12-19 11:58:51', '28800', '2020-12-19 19:58:51', '', null, 'ONLINE', '15', null, '2020-12-19 11:58:51', null, '2020-12-19 11:58:51', '0', '0', null, '');
INSERT INTO `sys_online_user` VALUES ('1', 'admin', 'f443034d31d642a8a07941c9bf7a3117', '127.0.0.1,127.0.0.1', '2020-12-19 14:43:27', '2020-12-19 16:10:09', '28800', '2020-12-20 00:10:09', '', null, 'ONLINE', '16', null, '2020-12-19 14:43:27', null, '2020-12-19 14:43:27', '0', '0', null, '');

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
