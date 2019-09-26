/*
Navicat MySQL Data Transfer

Source Server         : localhost-garden
Source Server Version : 50717
Source Host           : localhost:3306
Source Database       : garden

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2019-09-26 14:37:29
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for sys_file_info_t
-- ----------------------------
DROP TABLE IF EXISTS `sys_file_info_t`;
CREATE TABLE `sys_file_info_t` (
  `ID` varchar(32) NOT NULL COMMENT 'ID',
  `FILE_MANAGE_ID` varchar(32) DEFAULT '' COMMENT '文件所属ID',
  `BIZ_ID` varchar(32) DEFAULT '' COMMENT '业务ID',
  `BIZ_CODE` varchar(32) DEFAULT '' COMMENT '业务编码',
  `TYPE` varchar(32) DEFAULT NULL COMMENT '文件类型',
  `ORIGINAL_NAME` varchar(128) DEFAULT '' COMMENT '原始文件名称，包含扩展名',
  `EXTEND_NAME` varchar(32) DEFAULT '' COMMENT '文件扩展名，不包含.',
  `NAME` varchar(128) DEFAULT '' COMMENT '文件名称',
  `PHYSICAL_PATH` varchar(255) DEFAULT '' COMMENT '存储物理路径',
  `VISIT_PATH` varchar(255) DEFAULT '' COMMENT '访问路径',
  `SIZE` int(11) DEFAULT '0' COMMENT '文件大小（单件byte）',
  `CONTENT_TYPE` varchar(32) DEFAULT '' COMMENT '文件头类型',
  `CREATED_BY` varchar(32) DEFAULT '' COMMENT '创建人',
  `CREATION_DATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `UPDATED_BY` varchar(32) DEFAULT '' COMMENT '修改人',
  `UPDATION_DATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `ENABLED_FLAG` int(1) DEFAULT '1' COMMENT '是否禁用',
  `TRACE_ID` varchar(16) DEFAULT '' COMMENT '日志ID',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='文件列表';

-- ----------------------------
-- Table structure for sys_file_manage_t
-- ----------------------------
DROP TABLE IF EXISTS `sys_file_manage_t`;
CREATE TABLE `sys_file_manage_t` (
  `ID` varchar(32) NOT NULL COMMENT 'ID',
  `NAME` varchar(32) DEFAULT '' COMMENT '名称',
  `CODE` varchar(32) DEFAULT '' COMMENT '编号',
  `STATE` varchar(2) DEFAULT '' COMMENT '状态',
  `REMARK` varchar(200) DEFAULT '' COMMENT '备注',
  `CREATED_BY` varchar(32) DEFAULT '' COMMENT '创建人',
  `CREATION_DATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `UPDATED_BY` varchar(32) DEFAULT '' COMMENT '修改人',
  `UPDATION_DATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `ENABLED_FLAG` int(1) DEFAULT '1' COMMENT '是否禁用',
  `TRACE_ID` varchar(16) DEFAULT '' COMMENT '日志ID',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='文件管理表';
