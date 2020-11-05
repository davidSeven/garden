/*
Navicat MySQL Data Transfer

Source Server         : localhost-root
Source Server Version : 50717
Source Host           : localhost:3306
Source Database       : sky

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2020-11-05 17:02:48
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
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='数据字典';

-- ----------------------------
-- Table structure for sys_login_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_login_log`;
CREATE TABLE `sys_login_log` (
  `state` varchar(20) DEFAULT 'SAFETY_CHECK' COMMENT '状态',
  `safety_check_ip` varchar(20) DEFAULT '' COMMENT '检查IP',
  `safety_check_time` datetime DEFAULT NULL COMMENT '检查时间',
  `safety_check_value` varchar(10) DEFAULT '' COMMENT '检查值',
  `safety_check_token` varchar(32) DEFAULT '' COMMENT '检查token',
  `verify_code_ip` varchar(20) DEFAULT '' COMMENT '验证码IP',
  `verify_code_time` datetime DEFAULT NULL COMMENT '验证码时间',
  `verify_code_value` varchar(4) DEFAULT '' COMMENT '验证码值',
  `verify_code_type` varchar(1) DEFAULT '' COMMENT '验证码类型',
  `verify_code_count` int(11) DEFAULT NULL COMMENT '获取验证码次数',
  `verify_code_token` varchar(32) DEFAULT '' COMMENT '验证码token',
  `login_ip` varchar(20) DEFAULT '' COMMENT '登录IP',
  `login_time` datetime DEFAULT NULL COMMENT '登录时间',
  `login_code` varchar(100) DEFAULT '' COMMENT '登录帐号',
  `login_password` varchar(100) DEFAULT '' COMMENT '登录密码',
  `login_verify_code` varchar(20) DEFAULT '' COMMENT '登录验证码',
  `login_safety_check_token` varchar(32) DEFAULT '' COMMENT '登录检查token',
  `login_count` int(11) DEFAULT '0' COMMENT '登录次数',
  `login_fail_count` int(11) DEFAULT '0' COMMENT '登录失败次数',
  `login_result` varchar(20) DEFAULT '' COMMENT '登录结果',
  `login_token` varchar(32) DEFAULT '' COMMENT '登录token',
  `logout_ip` varchar(20) DEFAULT '' COMMENT '登出IP',
  `logout_time` datetime DEFAULT NULL COMMENT '登出时间',
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
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='登录日志';

-- ----------------------------
-- Table structure for sys_login_log_token
-- ----------------------------
DROP TABLE IF EXISTS `sys_login_log_token`;
CREATE TABLE `sys_login_log_token` (
  `safety_check_token` varchar(32) DEFAULT '' COMMENT '检查token',
  `login_token` varchar(32) DEFAULT '' COMMENT '登录token',
  `log_id` int(11) NOT NULL DEFAULT '0' COMMENT 'ID',
  PRIMARY KEY (`log_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='登录日志token';

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
