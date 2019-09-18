/*
Navicat MySQL Data Transfer

Source Server         : localhost-garden
Source Server Version : 50717
Source Host           : localhost:3306
Source Database       : garden

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2019-09-18 21:20:12
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for sys_data_scope_t
-- ----------------------------
DROP TABLE IF EXISTS `sys_data_scope_t`;
CREATE TABLE `sys_data_scope_t` (
  `ID` varchar(32) NOT NULL COMMENT 'ID',
  `MENU_ID` varchar(32) DEFAULT '' COMMENT '菜单ID',
  `NAME` varchar(32) DEFAULT '' COMMENT '名称',
  `CODE` varchar(128) DEFAULT '' COMMENT '编号',
  `STATE` varchar(2) DEFAULT '' COMMENT '状态',
  `URL` varchar(128) DEFAULT NULL COMMENT '路径',
  `CREATED_BY` varchar(32) DEFAULT '' COMMENT '创建人',
  `CREATION_DATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `UPDATED_BY` varchar(32) DEFAULT '' COMMENT '修改人',
  `UPDATION_DATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `ENABLED_FLAG` int(1) DEFAULT '1' COMMENT '是否禁用',
  `TRACE_ID` varchar(16) DEFAULT '' COMMENT '日志ID',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='数据范围表';

-- ----------------------------
-- Records of sys_data_scope_t
-- ----------------------------

-- ----------------------------
-- Table structure for sys_function_t
-- ----------------------------
DROP TABLE IF EXISTS `sys_function_t`;
CREATE TABLE `sys_function_t` (
  `ID` varchar(32) NOT NULL COMMENT 'ID',
  `MENU_ID` varchar(32) DEFAULT '' COMMENT '菜单ID',
  `NAME` varchar(32) DEFAULT '' COMMENT '名称',
  `CODE` varchar(128) DEFAULT '' COMMENT '编号',
  `STATE` varchar(2) DEFAULT '' COMMENT '状态',
  `URL` varchar(128) DEFAULT NULL COMMENT '路径',
  `CREATED_BY` varchar(32) DEFAULT '' COMMENT '创建人',
  `CREATION_DATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `UPDATED_BY` varchar(32) DEFAULT '' COMMENT '修改人',
  `UPDATION_DATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `ENABLED_FLAG` int(1) DEFAULT '1' COMMENT '是否禁用',
  `TRACE_ID` varchar(16) DEFAULT '' COMMENT '日志ID',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='功能表';

-- ----------------------------
-- Records of sys_function_t
-- ----------------------------
INSERT INTO `sys_function_t` VALUES ('312554985592164352', '306008072466173952', '列表', 'sys:menu:pageList', '1', '/system/menu/pageList', null, '2019-07-21 11:25:21', null, '2019-07-21 11:25:21', null, null);
INSERT INTO `sys_function_t` VALUES ('312557438165291008', '306008072466173952', '新增', 'sys:menu:add', '1', '/system/menu/add', null, '2019-07-21 11:25:29', null, '2019-07-21 11:25:29', null, null);
INSERT INTO `sys_function_t` VALUES ('312557544130187264', '306008072466173952', '修改', 'sys:menu:edit', '1', '/system/menu/edit', null, '2019-07-21 11:25:37', null, '2019-07-21 11:25:37', null, null);
INSERT INTO `sys_function_t` VALUES ('312557853078425600', '306008072466173952', '删除', 'sys:menu:del', '1', '/system/menu/delete', null, '2019-07-21 11:25:45', null, '2019-07-21 11:25:45', null, null);

-- ----------------------------
-- Table structure for sys_group_t
-- ----------------------------
DROP TABLE IF EXISTS `sys_group_t`;
CREATE TABLE `sys_group_t` (
  `ID` varchar(32) NOT NULL COMMENT 'ID',
  `NAME` varchar(32) DEFAULT '' COMMENT '名称',
  `CODE` varchar(32) DEFAULT '' COMMENT '编号',
  `STATE` varchar(2) DEFAULT '' COMMENT '状态',
  `CREATED_BY` varchar(32) DEFAULT '' COMMENT '创建人',
  `CREATION_DATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `UPDATED_BY` varchar(32) DEFAULT '' COMMENT '修改人',
  `UPDATION_DATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `ENABLED_FLAG` int(1) DEFAULT '1' COMMENT '是否禁用',
  `TRACE_ID` varchar(16) DEFAULT '' COMMENT '日志ID',
  PRIMARY KEY (`ID`),
  KEY `IDX_SYS_GROUP_T_CODE` (`CODE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='群组表';

-- ----------------------------
-- Records of sys_group_t
-- ----------------------------

-- ----------------------------
-- Table structure for sys_group_user_t
-- ----------------------------
DROP TABLE IF EXISTS `sys_group_user_t`;
CREATE TABLE `sys_group_user_t` (
  `GROUP_ID` varchar(32) DEFAULT NULL COMMENT '群组ID',
  `USER_ID` varchar(32) DEFAULT NULL COMMENT '用户ID',
  KEY `IDX_SYS_GROUP_USER_T_GROUP_ID` (`GROUP_ID`),
  KEY `IDX_SYS_GROUP_USER_T_USER_ID` (`USER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='群组用户关联表';

-- ----------------------------
-- Records of sys_group_user_t
-- ----------------------------

-- ----------------------------
-- Table structure for sys_lookup_item_t
-- ----------------------------
DROP TABLE IF EXISTS `sys_lookup_item_t`;
CREATE TABLE `sys_lookup_item_t` (
  `ID` varchar(32) NOT NULL COMMENT 'ID',
  `NAME` varchar(32) DEFAULT '' COMMENT '名称',
  `CODE` varchar(32) DEFAULT '' COMMENT '编号',
  `STATE` varchar(2) DEFAULT '' COMMENT '状态',
  `PARENT_CODE` varchar(32) DEFAULT '' COMMENT '父级编号',
  `PARENT_NAME` varchar(32) DEFAULT '' COMMENT '父级名称',
  `EXTEND_FIELD1` varchar(255) DEFAULT '' COMMENT '扩展字段1',
  `EXTEND_FIELD2` varchar(255) DEFAULT '' COMMENT '扩展字段2',
  `EXTEND_FIELD3` varchar(255) DEFAULT '' COMMENT '扩展字段3',
  `EXTEND_FIELD4` varchar(255) DEFAULT '' COMMENT '扩展字段4',
  `CREATED_BY` varchar(32) DEFAULT '' COMMENT '创建人',
  `CREATION_DATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `UPDATED_BY` varchar(32) DEFAULT '' COMMENT '修改人',
  `UPDATION_DATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `ENABLED_FLAG` int(1) DEFAULT '1' COMMENT '是否禁用',
  `TRACE_ID` varchar(16) DEFAULT '' COMMENT '日志ID',
  PRIMARY KEY (`ID`),
  KEY `IDX_SYS_LOOKUP_ITEM_T_CODE` (`CODE`),
  KEY `IDX_SYS_LOOKUP_ITEM_T_PARENT_CODE` (`PARENT_CODE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='LookupItem表';

-- ----------------------------
-- Records of sys_lookup_item_t
-- ----------------------------

-- ----------------------------
-- Table structure for sys_lookup_t
-- ----------------------------
DROP TABLE IF EXISTS `sys_lookup_t`;
CREATE TABLE `sys_lookup_t` (
  `ID` varchar(32) NOT NULL COMMENT 'ID',
  `NAME` varchar(32) DEFAULT '' COMMENT '名称',
  `CODE` varchar(32) DEFAULT '' COMMENT '编号',
  `STATE` varchar(2) DEFAULT '' COMMENT '状态',
  `CREATED_BY` varchar(32) DEFAULT '' COMMENT '创建人',
  `CREATION_DATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `UPDATED_BY` varchar(32) DEFAULT '' COMMENT '修改人',
  `UPDATION_DATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `ENABLED_FLAG` int(1) DEFAULT '1' COMMENT '是否禁用',
  `TRACE_ID` varchar(16) DEFAULT '' COMMENT '日志ID',
  PRIMARY KEY (`ID`),
  KEY `IDX_SYS_LOOKUP_T_CODE` (`CODE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Lookup表';

-- ----------------------------
-- Records of sys_lookup_t
-- ----------------------------
INSERT INTO `sys_lookup_t` VALUES ('312570714076975104', '状态（1启用，0禁用）', 'COMMON_STATE', '1', null, '2019-07-20 22:51:58', null, '2019-07-20 22:51:58', null, null);
INSERT INTO `sys_lookup_t` VALUES ('325575211501981696', 'x', 'x', '1', null, '2019-08-25 20:06:29', null, '2019-08-25 20:06:29', null, null);

-- ----------------------------
-- Table structure for sys_menu_t
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu_t`;
CREATE TABLE `sys_menu_t` (
  `ID` varchar(32) NOT NULL COMMENT 'ID',
  `NAME` varchar(32) DEFAULT '' COMMENT '名称',
  `CODE` varchar(32) DEFAULT '' COMMENT '编号',
  `STATE` varchar(2) DEFAULT '' COMMENT '状态',
  `PATH` varchar(128) DEFAULT '' COMMENT '路径',
  `SORTS` int(4) DEFAULT '1' COMMENT '顺序',
  `TYPE` varchar(2) DEFAULT '' COMMENT '类型',
  `PARENT_ID` varchar(32) DEFAULT '' COMMENT '父级ID',
  `ICON` varchar(32) DEFAULT '' COMMENT '图标',
  `REMARK` varchar(255) DEFAULT '' COMMENT '备注',
  `CREATED_BY` varchar(32) DEFAULT '' COMMENT '创建人',
  `CREATION_DATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `UPDATED_BY` varchar(32) DEFAULT '' COMMENT '修改人',
  `UPDATION_DATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `ENABLED_FLAG` int(1) DEFAULT '1' COMMENT '是否禁用',
  `TRACE_ID` varchar(16) DEFAULT '' COMMENT '日志ID',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='菜单表';

-- ----------------------------
-- Records of sys_menu_t
-- ----------------------------
INSERT INTO `sys_menu_t` VALUES ('306007560043859968', '菜单管理', '', '1', '', '100', '', '0', '&#xe653;', '', '', '2019-07-02 20:11:37', '', '2019-07-02 20:11:37', '1', '');
INSERT INTO `sys_menu_t` VALUES ('306007745968967680', '系统管理', '', '1', '', '110', '', '0', '&#xe672;', '', '', '2019-07-02 20:12:22', '', '2019-07-02 20:12:22', '1', '');
INSERT INTO `sys_menu_t` VALUES ('306008072466173952', '菜单管理', '', '1', 'system/menu/toList', '10', '', '306007560043859968', '', '', '', '2019-07-02 20:13:40', '', '2019-07-02 20:13:40', '1', '');
INSERT INTO `sys_menu_t` VALUES ('306008179878105088', '用户管理', '', '1', 'system/user/toList', '10', '', '306007745968967680', '', '', '', '2019-07-02 20:14:05', '', '2019-07-02 20:14:05', '1', '');
INSERT INTO `sys_menu_t` VALUES ('306008313688985600', '角色管理', '', '1', 'system/role/toList', '20', '', '306007745968967680', '', '', '', '2019-07-02 20:14:37', '', '2019-07-02 20:14:37', '1', '');
INSERT INTO `sys_menu_t` VALUES ('306009272351690752', '群组管理', '', '1', 'system/group/toList', '30', '', '306007745968967680', '', '', '', '2019-07-02 20:18:26', '', '2019-07-02 20:18:26', '1', '');
INSERT INTO `sys_menu_t` VALUES ('306009324067459072', '权限管理', '', '1', '', '50', '', '306007745968967680', '', '', '', '2019-07-02 20:18:38', '', '2019-07-02 20:18:38', '1', '');
INSERT INTO `sys_menu_t` VALUES ('306009578489745408', '系统日志', '', '1', '', '200', '', '0', '&#xe60a;', '', '', '2019-07-02 20:19:39', '', '2019-07-02 20:19:39', '1', '');
INSERT INTO `sys_menu_t` VALUES ('306009670156259328', '系统设置', '', '1', '', '130', '', '0', '&#xe716;', '系统设置的地方', '', '2019-07-02 20:20:00', '', '2019-07-02 20:20:00', '1', '');
INSERT INTO `sys_menu_t` VALUES ('306009975694528512', '数据字典', '', '1', '', '20', '', '306009670156259328', '', '', '', '2019-07-02 20:21:13', '', '2019-07-02 20:21:13', '1', '');
INSERT INTO `sys_menu_t` VALUES ('306010239977623552', 'Lookup管理', '', '1', 'lookup/lookup/toList', '10', '', '306009670156259328', '', '', '', '2019-07-02 20:22:16', '', '2019-07-02 20:22:16', '1', '');
INSERT INTO `sys_menu_t` VALUES ('312556679147261952', '功能管理', '', '1', 'system/function/toList', '40', '', '306007745968967680', '', '', '', '2019-07-20 21:55:29', '', '2019-07-20 21:55:29', '1', '');
INSERT INTO `sys_menu_t` VALUES ('325600478249324544', '组织管理', '', '1', 'system/organization/toList', '60', '', '306007745968967680', '', '', '', '2019-08-25 21:46:53', '', '2019-08-25 21:46:53', '1', '');
INSERT INTO `sys_menu_t` VALUES ('325600600563617792', '职务管理', '', '1', 'system/position/toList', '70', '', '306007745968967680', '', '', '', '2019-08-25 21:47:22', '', '2019-08-25 21:47:22', '1', '');

-- ----------------------------
-- Table structure for sys_organization_t
-- ----------------------------
DROP TABLE IF EXISTS `sys_organization_t`;
CREATE TABLE `sys_organization_t` (
  `ID` varchar(32) NOT NULL COMMENT 'ID',
  `NAME` varchar(32) DEFAULT '' COMMENT '名称',
  `CODE` varchar(128) DEFAULT '' COMMENT '编号',
  `STATE` varchar(2) DEFAULT '' COMMENT '状态',
  `SORTS` int(4) DEFAULT '1' COMMENT '顺序',
  `PARENT_ID` varchar(32) DEFAULT NULL COMMENT '父级ID',
  `REMARK` varchar(255) DEFAULT '' COMMENT '备注',
  `CREATED_BY` varchar(32) DEFAULT '' COMMENT '创建人',
  `CREATION_DATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `UPDATED_BY` varchar(32) DEFAULT '' COMMENT '修改人',
  `UPDATION_DATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `ENABLED_FLAG` int(1) DEFAULT '1' COMMENT '是否禁用',
  `TRACE_ID` varchar(16) DEFAULT '' COMMENT '日志ID',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='组织架构表';

-- ----------------------------
-- Records of sys_organization_t
-- ----------------------------
INSERT INTO `sys_organization_t` VALUES ('312768349954084864', '蓝东集团', '', '1', '1', '0', '', '', '2019-07-21 11:56:35', '', '2019-07-21 11:56:35', '1', '');
INSERT INTO `sys_organization_t` VALUES ('312768450189561856', '广州公司', '', '1', '1', '312768349954084864', '', '', '2019-07-21 11:56:59', '', '2019-07-21 11:56:59', '1', '');
INSERT INTO `sys_organization_t` VALUES ('312768493931958272', '武汉公司', '', '1', '2', '312768349954084864', '', '', '2019-07-21 11:57:10', '', '2019-07-21 11:57:10', '1', '');
INSERT INTO `sys_organization_t` VALUES ('312768632281075712', '深圳公司', '', '1', '3', '312768349954084864', '', '', '2019-07-21 11:57:43', '', '2019-07-21 11:57:43', '1', '');
INSERT INTO `sys_organization_t` VALUES ('312768873617133568', '总裁办', '', '1', '1', '312768450189561856', '', '', '2019-07-21 11:58:40', '', '2019-07-21 11:58:40', '1', '');
INSERT INTO `sys_organization_t` VALUES ('312768933142695936', '行政中心', '', '1', '2', '312768450189561856', '', '', '2019-07-21 11:58:54', '', '2019-07-21 11:58:54', '1', '');
INSERT INTO `sys_organization_t` VALUES ('312768975828127744', '人力部', '', '1', '1', '312768933142695936', '', '', '2019-07-21 11:59:05', '', '2019-07-21 11:59:05', '1', '');
INSERT INTO `sys_organization_t` VALUES ('312769002776530944', '行政部', '', '1', '2', '312768933142695936', '', '', '2019-07-21 11:59:11', '', '2019-07-21 11:59:11', '1', '');
INSERT INTO `sys_organization_t` VALUES ('312769101078433792', '营销中心', '', '1', '3', '312768450189561856', '', '', '2019-07-21 11:59:34', '', '2019-07-21 11:59:34', '1', '');
INSERT INTO `sys_organization_t` VALUES ('312769121659883520', '客服中心', '', '1', '4', '312768450189561856', '', '', '2019-07-21 11:59:39', '', '2019-07-21 11:59:39', '1', '');
INSERT INTO `sys_organization_t` VALUES ('312769191054643200', '技术中心', '', '1', '6', '312768450189561856', '', '', '2019-07-21 11:59:56', '', '2019-07-21 11:59:56', '1', '');
INSERT INTO `sys_organization_t` VALUES ('312770991996506112', '营销一部', '', '1', '1', '312769101078433792', '', '', '2019-07-21 12:07:05', '', '2019-07-21 12:07:05', '1', '');
INSERT INTO `sys_organization_t` VALUES ('312771025467052032', '营销二部', '', '1', '2', '312769101078433792', '', '', '2019-07-21 12:07:13', '', '2019-07-21 12:07:13', '1', '');
INSERT INTO `sys_organization_t` VALUES ('312771062943158272', '营销三部', '', '1', '3', '312769101078433792', '', '', '2019-07-21 12:07:22', '', '2019-07-21 12:07:22', '1', '');
INSERT INTO `sys_organization_t` VALUES ('312771100687699968', '信息管理', '', '1', '1', '312769121659883520', '', '', '2019-07-21 12:07:31', '', '2019-07-21 12:07:31', '1', '');
INSERT INTO `sys_organization_t` VALUES ('312771124939165696', '客户管理', '', '1', '2', '312769121659883520', '', '', '2019-07-21 12:07:37', '', '2019-07-21 12:07:37', '1', '');
INSERT INTO `sys_organization_t` VALUES ('312771158187413504', '售后服务', '', '1', '3', '312769121659883520', '', '', '2019-07-21 12:07:45', '', '2019-07-21 12:07:45', '1', '');
INSERT INTO `sys_organization_t` VALUES ('312771247773552640', '后勤部', '', '1', '7', '312768450189561856', '', '', '2019-07-21 12:08:06', '', '2019-07-21 12:08:06', '1', '');
INSERT INTO `sys_organization_t` VALUES ('312771336306921472', '生产中心', '', '1', '5', '312768450189561856', '', '', '2019-07-21 12:08:27', '', '2019-07-21 12:08:27', '1', '');
INSERT INTO `sys_organization_t` VALUES ('312771586027393024', '供应部', '', '1', '1', '312771336306921472', '', '', '2019-07-21 12:09:27', '', '2019-07-21 12:09:27', '1', '');
INSERT INTO `sys_organization_t` VALUES ('312771649571098624', '生产部', '', '1', '3', '312771336306921472', '', '', '2019-07-21 12:09:42', '', '2019-07-21 12:09:42', '1', '');
INSERT INTO `sys_organization_t` VALUES ('312771791187578880', '质检部', '', '1', '6', '312771336306921472', '', '', '2019-07-21 12:10:16', '', '2019-07-21 12:10:16', '1', '');
INSERT INTO `sys_organization_t` VALUES ('312771828923731968', '设备部', '', '1', '7', '312771336306921472', '', '', '2019-07-21 12:10:25', '', '2019-07-21 12:10:25', '1', '');
INSERT INTO `sys_organization_t` VALUES ('312771933223489536', '生产一部', '', '1', '1', '312771649571098624', '', '', '2019-07-21 12:10:50', '', '2019-07-21 12:10:50', '1', '');
INSERT INTO `sys_organization_t` VALUES ('312771964026458112', '生产二部', '', '1', '2', '312771649571098624', '', '', '2019-07-21 12:10:57', '', '2019-07-21 12:10:57', '1', '');
INSERT INTO `sys_organization_t` VALUES ('312772114690052096', '产品部', '', '1', '1', '312769191054643200', '', '', '2019-07-21 12:11:33', '', '2019-07-21 12:11:33', '1', '');
INSERT INTO `sys_organization_t` VALUES ('312772136240386048', '研发部', '', '1', '3', '312769191054643200', '', '', '2019-07-21 12:11:38', '', '2019-07-21 12:11:38', '1', '');
INSERT INTO `sys_organization_t` VALUES ('312772198026678272', '架构部', '', '1', '2', '312769191054643200', '', '', '2019-07-21 12:11:53', '', '2019-07-21 12:11:53', '1', '');
INSERT INTO `sys_organization_t` VALUES ('312772223670652928', '测试部', '', '1', '4', '312769191054643200', '', '', '2019-07-21 12:11:59', '', '2019-07-21 12:11:59', '1', '');
INSERT INTO `sys_organization_t` VALUES ('312772349222948864', '采购组', '', '1', '1', '312771586027393024', '', '', '2019-07-21 12:12:29', '', '2019-07-21 12:12:29', '1', '');
INSERT INTO `sys_organization_t` VALUES ('312772466894147584', '供应组', '', '1', '2', '312771586027393024', '', '', '2019-07-21 12:12:57', '', '2019-07-21 12:12:57', '1', '');
INSERT INTO `sys_organization_t` VALUES ('312772498263347200', '仓储组', '', '1', '3', '312771586027393024', '', '', '2019-07-21 12:13:04', '', '2019-07-21 12:13:04', '1', '');
INSERT INTO `sys_organization_t` VALUES ('312772552457949184', '电工组', '', '1', '1', '312771828923731968', '', '', '2019-07-21 12:13:17', '', '2019-07-21 12:13:17', '1', '');
INSERT INTO `sys_organization_t` VALUES ('312772578529742848', '机修组', '', '1', '2', '312771828923731968', '', '', '2019-07-21 12:13:23', '', '2019-07-21 12:13:23', '1', '');
INSERT INTO `sys_organization_t` VALUES ('312772643457568768', '质检一部', '', '1', '1', '312771791187578880', '', '', '2019-07-21 12:13:39', '', '2019-07-21 12:13:39', '1', '');
INSERT INTO `sys_organization_t` VALUES ('312772670020096000', '质检二部', '', '1', '2', '312771791187578880', '', '', '2019-07-21 12:13:45', '', '2019-07-21 12:13:45', '1', '');

-- ----------------------------
-- Table structure for sys_position_t
-- ----------------------------
DROP TABLE IF EXISTS `sys_position_t`;
CREATE TABLE `sys_position_t` (
  `ID` varchar(32) NOT NULL COMMENT 'ID',
  `NAME` varchar(32) DEFAULT '' COMMENT '名称',
  `CODE` varchar(128) DEFAULT '' COMMENT '编号',
  `STATE` varchar(2) DEFAULT '' COMMENT '状态',
  `REMARK` varchar(255) DEFAULT '' COMMENT '备注',
  `CREATED_BY` varchar(32) DEFAULT '' COMMENT '创建人',
  `CREATION_DATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `UPDATED_BY` varchar(32) DEFAULT '' COMMENT '修改人',
  `UPDATION_DATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `ENABLED_FLAG` int(1) DEFAULT '1' COMMENT '是否禁用',
  `TRACE_ID` varchar(16) DEFAULT '' COMMENT '日志ID',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='职务表';

-- ----------------------------
-- Records of sys_position_t
-- ----------------------------
INSERT INTO `sys_position_t` VALUES ('312777571362115584', '超级管理员', '超级管理员', '1', null, null, '2019-07-21 12:33:14', null, '2019-07-21 12:33:14', null, null);
INSERT INTO `sys_position_t` VALUES ('312777600860655616', '管理员', '管理员', '1', null, null, '2019-07-21 12:33:21', null, '2019-07-21 12:33:21', null, null);
INSERT INTO `sys_position_t` VALUES ('312777666937720832', '总经理', '总经理', '1', null, null, '2019-07-21 12:33:37', null, '2019-07-21 12:33:37', null, null);

-- ----------------------------
-- Table structure for sys_role_function_t
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_function_t`;
CREATE TABLE `sys_role_function_t` (
  `ROLE_ID` varchar(32) DEFAULT NULL COMMENT '角色ID',
  `FUNCTION_ID` varchar(32) DEFAULT NULL COMMENT '功能ID',
  `TYPE` int(4) DEFAULT '0' COMMENT '类型，1菜单，2功能',
  KEY `IDX_SYS_ROLE_FUNCTION_T_ROLE_ID` (`ROLE_ID`),
  KEY `IDX_SYS_ROLE_FUNCTION_T_USER_ID` (`FUNCTION_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色菜单功能关联表';

-- ----------------------------
-- Records of sys_role_function_t
-- ----------------------------
INSERT INTO `sys_role_function_t` VALUES ('303106948964782080', '312554985592164352', '2');
INSERT INTO `sys_role_function_t` VALUES ('303106948964782080', '312557438165291008', '2');
INSERT INTO `sys_role_function_t` VALUES ('303106948964782080', '312557544130187264', '2');
INSERT INTO `sys_role_function_t` VALUES ('303106948964782080', '312557853078425600', '2');

-- ----------------------------
-- Table structure for sys_role_group_t
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_group_t`;
CREATE TABLE `sys_role_group_t` (
  `ROLE_ID` varchar(32) DEFAULT NULL COMMENT '角色ID',
  `GROUP_ID` varchar(32) DEFAULT NULL COMMENT '群组ID',
  KEY `IDX_SYS_ROLE_GROUP_T_ROLE_ID` (`ROLE_ID`),
  KEY `IDX_SYS_ROLE_GROUP_T_GROUP_ID` (`GROUP_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色群组关联表';

-- ----------------------------
-- Records of sys_role_group_t
-- ----------------------------

-- ----------------------------
-- Table structure for sys_role_menu_t
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu_t`;
CREATE TABLE `sys_role_menu_t` (
  `ROLE_ID` varchar(32) DEFAULT NULL COMMENT '角色ID',
  `MENU_ID` varchar(32) DEFAULT NULL COMMENT '菜单ID',
  KEY `IDX_SYS_ROLE_MENU_T_ROLE_ID` (`ROLE_ID`),
  KEY `IDX_SYS_ROLE_MENU_T_USER_ID` (`MENU_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色菜单关联表';

-- ----------------------------
-- Records of sys_role_menu_t
-- ----------------------------
INSERT INTO `sys_role_menu_t` VALUES ('303106948964782080', '306007560043859968');
INSERT INTO `sys_role_menu_t` VALUES ('303106948964782080', '306008072466173952');
INSERT INTO `sys_role_menu_t` VALUES ('303106948964782080', '306007745968967680');
INSERT INTO `sys_role_menu_t` VALUES ('303106948964782080', '306008179878105088');
INSERT INTO `sys_role_menu_t` VALUES ('303106948964782080', '306008313688985600');
INSERT INTO `sys_role_menu_t` VALUES ('303106948964782080', '306009272351690752');
INSERT INTO `sys_role_menu_t` VALUES ('303106948964782080', '306009324067459072');
INSERT INTO `sys_role_menu_t` VALUES ('303106948964782080', '312556679147261952');
INSERT INTO `sys_role_menu_t` VALUES ('303106948964782080', '325600478249324544');
INSERT INTO `sys_role_menu_t` VALUES ('303106948964782080', '325600600563617792');
INSERT INTO `sys_role_menu_t` VALUES ('303106948964782080', '306009670156259328');
INSERT INTO `sys_role_menu_t` VALUES ('303106948964782080', '306009975694528512');
INSERT INTO `sys_role_menu_t` VALUES ('303106948964782080', '306010239977623552');

-- ----------------------------
-- Table structure for sys_role_t
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_t`;
CREATE TABLE `sys_role_t` (
  `ID` varchar(32) NOT NULL COMMENT 'ID',
  `NAME` varchar(32) DEFAULT '' COMMENT '名称',
  `CODE` varchar(32) DEFAULT '' COMMENT '编号',
  `STATE` varchar(2) DEFAULT '' COMMENT '状态',
  `CREATED_BY` varchar(32) DEFAULT '' COMMENT '创建人',
  `CREATION_DATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `UPDATED_BY` varchar(32) DEFAULT '' COMMENT '修改人',
  `UPDATION_DATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `ENABLED_FLAG` int(1) DEFAULT '1' COMMENT '是否禁用',
  `TRACE_ID` varchar(16) DEFAULT '' COMMENT '日志ID',
  PRIMARY KEY (`ID`),
  KEY `IDX_SYS_ROLE_T_CODE` (`CODE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色表';

-- ----------------------------
-- Records of sys_role_t
-- ----------------------------
INSERT INTO `sys_role_t` VALUES ('303106948964782080', '4BPgspLo3RnHsATM', '0000000001', '1', null, '2019-06-24 20:05:40', null, '2019-06-24 20:05:40', null, null);

-- ----------------------------
-- Table structure for sys_user_role_t
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role_t`;
CREATE TABLE `sys_user_role_t` (
  `USER_ID` varchar(32) DEFAULT NULL COMMENT '用户ID',
  `ROLE_ID` varchar(32) DEFAULT NULL COMMENT '角色ID',
  KEY `IDX_SYS_USER_ROLE_T_USER_ID` (`USER_ID`),
  KEY `IDX_SYS_USER_ROLE_T_ROLE_ID` (`ROLE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户角色关联表';

-- ----------------------------
-- Records of sys_user_role_t
-- ----------------------------
INSERT INTO `sys_user_role_t` VALUES ('303106967935619072', '303106948964782080');

-- ----------------------------
-- Table structure for sys_user_t
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_t`;
CREATE TABLE `sys_user_t` (
  `ID` varchar(32) NOT NULL COMMENT 'ID',
  `NAME` varchar(32) DEFAULT '' COMMENT '名称',
  `CODE` varchar(32) DEFAULT '' COMMENT '编号',
  `STATE` varchar(2) DEFAULT '' COMMENT '状态',
  `CURRENT_ROLE_ID` varchar(32) DEFAULT '' COMMENT '当前角色ID',
  `PASSWORD` varchar(255) DEFAULT '' COMMENT '密码',
  `LOGIN_FAIL_COUNT` int(2) DEFAULT '0' COMMENT '登录失败次数',
  `LAST_LOGIN_IP` varchar(32) DEFAULT '' COMMENT '最后登录IP',
  `LAST_LOGIN_DATE` timestamp NULL DEFAULT NULL COMMENT '最后登录时间',
  `CREATED_BY` varchar(32) DEFAULT '' COMMENT '创建人',
  `CREATION_DATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `UPDATED_BY` varchar(32) DEFAULT '' COMMENT '修改人',
  `UPDATION_DATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `ENABLED_FLAG` int(1) DEFAULT '1' COMMENT '是否禁用',
  `TRACE_ID` varchar(16) DEFAULT '' COMMENT '日志ID',
  PRIMARY KEY (`ID`),
  KEY `IDX_SYS_USER_T_CODE` (`CODE`) USING HASH
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户表';

-- ----------------------------
-- Records of sys_user_t
-- ----------------------------
INSERT INTO `sys_user_t` VALUES ('303106967935619072', '姜绀怡', '0000000001', '1', null, '8FEF13C67B2F87E513492A861EDCA67089F3E7A6AB081B625C2DDA03', '0', '169.254.28.64', '2019-09-18 21:12:28', null, '2019-07-24 20:31:01', null, '2019-07-24 20:31:01', null, null);
