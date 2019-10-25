/*
Navicat MySQL Data Transfer

Source Server         : localhost-garden
Source Server Version : 50717
Source Host           : localhost:3306
Source Database       : garden

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2019-10-25 16:53:16
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for sys_content_t
-- ----------------------------
DROP TABLE IF EXISTS `sys_content_t`;
CREATE TABLE `sys_content_t` (
  `ID` varchar(32) NOT NULL COMMENT 'ID',
  `CODE` varchar(32) DEFAULT '' COMMENT '编码',
  `URL` varchar(64) DEFAULT '' COMMENT '路径',
  `CONTENT` text COMMENT '内容',
  `STATE` varchar(2) DEFAULT '' COMMENT '状态',
  `REMARK` varchar(255) DEFAULT '' COMMENT '备注',
  `CREATED_BY` varchar(32) DEFAULT '' COMMENT '创建人',
  `CREATION_DATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `UPDATED_BY` varchar(32) DEFAULT '' COMMENT '修改人',
  `UPDATION_DATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `ENABLED_FLAG` int(1) DEFAULT '1' COMMENT '是否禁用',
  `TRACE_ID` varchar(16) DEFAULT '' COMMENT '日志ID',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='富文本表';

-- ----------------------------
-- Records of sys_content_t
-- ----------------------------

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
-- Table structure for sys_dictionary_t
-- ----------------------------
DROP TABLE IF EXISTS `sys_dictionary_t`;
CREATE TABLE `sys_dictionary_t` (
  `ID` varchar(32) NOT NULL,
  `CODE` varchar(64) DEFAULT '' COMMENT '编码',
  `NAME` varchar(64) DEFAULT '' COMMENT '名称',
  `PARENT_ID` varchar(32) DEFAULT '' COMMENT '父级ID',
  `PARENT_CODE` varchar(64) DEFAULT '' COMMENT '父级编码',
  `STATE` varchar(2) DEFAULT '' COMMENT '状态',
  `SORTS` int(4) DEFAULT '1' COMMENT '顺序',
  `VALUE` varchar(255) DEFAULT '' COMMENT '值',
  `REMARK` varchar(255) DEFAULT '' COMMENT '备注',
  `CREATED_BY` varchar(32) DEFAULT '' COMMENT '创建人',
  `CREATION_DATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `UPDATED_BY` varchar(32) DEFAULT '' COMMENT '修改人',
  `UPDATION_DATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `ENABLED_FLAG` int(1) DEFAULT '1' COMMENT '是否禁用',
  `TRACE_ID` varchar(16) DEFAULT '' COMMENT '日志ID',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='数据字典';

-- ----------------------------
-- Records of sys_dictionary_t
-- ----------------------------
INSERT INTO `sys_dictionary_t` VALUES ('338169657953632256', 'system', '系统', '0', '', '1', '1', '', '', '', '2019-09-29 14:12:20', '', '2019-09-29 14:12:20', '1', '');
INSERT INTO `sys_dictionary_t` VALUES ('338169727960760320', 'config', '配置', '338169657953632256', 'system', '0', '1', '', '', '', '2019-09-29 14:12:36', '', '2019-09-29 14:17:53', '1', '');
INSERT INTO `sys_dictionary_t` VALUES ('338171164941893632', 'app', '应用', '0', '', '1', '2', '', '', '', '2019-09-29 14:18:19', '', '2019-09-29 14:18:25', '1', '');
INSERT INTO `sys_dictionary_t` VALUES ('338171255920541696', 'login', '登录', '338169657953632256', 'system', '1', '1', '', '', '', '2019-09-29 14:18:41', '', '2019-09-29 14:18:41', '1', '');
INSERT INTO `sys_dictionary_t` VALUES ('338171550863998976', 'failCount', '登录失败次数', '338171255920541696', 'login', '1', '1', '3', '登录失败次数超过3次锁定账户', '', '2019-09-29 14:19:51', '', '2019-09-29 14:19:51', '1', '');
INSERT INTO `sys_dictionary_t` VALUES ('338172267158847488', 'cookieTime', 'Cookie有效时间', '338171255920541696', 'login', '1', '1', '86400', '单位：秒\n有效期1天', '', '2019-09-29 14:22:42', '', '2019-09-29 14:22:42', '1', '');
INSERT INTO `sys_dictionary_t` VALUES ('338172402295128064', 'cacheTime', '缓存有效时间', '338171255920541696', 'login', '1', '1', '86400', '单位：秒\n有效期1天', '', '2019-09-29 14:23:14', '', '2019-09-29 14:23:14', '1', '');

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
-- Records of sys_file_info_t
-- ----------------------------
INSERT INTO `sys_file_info_t` VALUES ('0eace53856494d3cace840434d8b2258', '337725915249524736', '1569653620315', 'user_head', null, 'timg.jpg', 'jpg', 'timg', '/www/garden\\30cfa0be9cb3457abd25807a005c2386\\0eace53856494d3cace840434d8b2258.jpg', '\\30cfa0be9cb3457abd25807a005c2386\\0eace53856494d3cace840434d8b2258.jpg', '25866', 'image/jpeg', null, '2019-09-28 14:53:43', null, '2019-09-28 14:53:43', null, null);
INSERT INTO `sys_file_info_t` VALUES ('1a1713df5c2741ac9099b1e8b89f1097', '337725915249524736', '1569653242176', 'user_head', null, 'u=2692711075,474994291&fm=26&gp=0.jpg', 'jpg', 'u=2692711075,474994291&fm=26&gp=0', '/www/garden\\1e90811ead5545048a39f8729e7542c1\\1a1713df5c2741ac9099b1e8b89f1097.jpg', '\\1e90811ead5545048a39f8729e7542c1\\1a1713df5c2741ac9099b1e8b89f1097.jpg', '13520', 'image/jpeg', null, '2019-09-28 14:47:27', null, '2019-09-28 14:47:27', null, null);
INSERT INTO `sys_file_info_t` VALUES ('1ffb948c733349abb4395d50a4a148df', '337725915249524736', '1569653504968', 'user_head', null, 'timg.jpg', 'jpg', 'timg', '/www/garden\\3b711638c1e741819506ef1d3ddeca2b\\1ffb948c733349abb4395d50a4a148df.jpg', '\\3b711638c1e741819506ef1d3ddeca2b\\1ffb948c733349abb4395d50a4a148df.jpg', '25866', 'image/jpeg', null, '2019-09-28 14:51:49', null, '2019-09-28 14:51:49', null, null);
INSERT INTO `sys_file_info_t` VALUES ('40d13a27f47d41909e0809835dadc46e', '337725915249524736', '1569652871160', 'user_head', null, '微信图片_20190925141416.jpg', 'jpg', '微信图片_20190925141416', '/www/garden\\5e28883b5e804c189f8b32bcd8e0d7f1\\40d13a27f47d41909e0809835dadc46e.jpg', '\\5e28883b5e804c189f8b32bcd8e0d7f1\\40d13a27f47d41909e0809835dadc46e.jpg', '43924', 'image/jpeg', null, '2019-09-28 14:41:22', null, '2019-09-28 14:41:22', null, null);
INSERT INTO `sys_file_info_t` VALUES ('5866e24e84064e36b984260b8b53da57', '337725915249524736', '1569727033682', 'user_head', null, '微信图片_20190925141416.jpg', 'jpg', '微信图片_20190925141416', '/www/garden\\c495e634ab614045929b421f3839dcac\\5866e24e84064e36b984260b8b53da57.jpg', '\\c495e634ab614045929b421f3839dcac\\5866e24e84064e36b984260b8b53da57.jpg', '39040', 'image/jpeg', null, '2019-09-29 11:17:24', null, '2019-09-29 11:17:24', null, null);
INSERT INTO `sys_file_info_t` VALUES ('5dabf8efb98f4f3dae536f971075171a', '337725915249524736', '1569654019392', 'user_head', null, '20190316225645695.gif', 'gif', '20190316225645695', '/www/garden\\71b6f9a6d5e8497bb8c11925efa6f086\\5dabf8efb98f4f3dae536f971075171a.gif', '\\71b6f9a6d5e8497bb8c11925efa6f086\\5dabf8efb98f4f3dae536f971075171a.gif', '5503', 'image/jpeg', null, '2019-09-28 15:00:24', null, '2019-09-28 15:00:24', null, null);
INSERT INTO `sys_file_info_t` VALUES ('7e5a50dd45d6486a9d3f6f184f3217ad', '337725915249524736', '1569652785376', 'user_head', null, 'timg.jpg', 'jpg', 'timg', '/www/garden\\5e2717d5aab147b5988e15e300099921\\7e5a50dd45d6486a9d3f6f184f3217ad.jpg', '\\5e2717d5aab147b5988e15e300099921\\7e5a50dd45d6486a9d3f6f184f3217ad.jpg', '36006', 'image/jpeg', null, '2019-09-28 14:39:54', null, '2019-09-28 14:39:54', null, null);
INSERT INTO `sys_file_info_t` VALUES ('8333410d68144493ac3342b4c9ba4f67', '337725915249524736', '1569654307169', 'user_head', null, 'timg.jpg', 'jpg', 'timg', '/www/garden\\2a86008d264647d68d1d649bd54e2982\\8333410d68144493ac3342b4c9ba4f67.jpg', '\\2a86008d264647d68d1d649bd54e2982\\8333410d68144493ac3342b4c9ba4f67.jpg', '17045', 'image/jpeg', null, '2019-09-28 15:05:15', null, '2019-09-28 15:05:15', null, null);
INSERT INTO `sys_file_info_t` VALUES ('835148d67b694b1784b0ea09252cff25', '337725915249524736', '1569653462232', 'user_head', null, 'MyJsonView.png', 'png', 'MyJsonView', '/www/garden\\6d90568cc4b8406a9d3c6e257bee8007\\835148d67b694b1784b0ea09252cff25.png', '\\6d90568cc4b8406a9d3c6e257bee8007\\835148d67b694b1784b0ea09252cff25.png', '52751', 'image/jpeg', null, '2019-09-28 14:51:07', null, '2019-09-28 14:51:07', null, null);
INSERT INTO `sys_file_info_t` VALUES ('a327c908cbc84132a0d821bd1d52d35b', '337725915249524736', '1569631748416', 'user_head', null, 'timg.jpg', 'jpg', 'timg', '/www/garden\\353ae93f3e0545c9ac785fb5cee48809\\a327c908cbc84132a0d821bd1d52d35b.jpg', '\\353ae93f3e0545c9ac785fb5cee48809\\a327c908cbc84132a0d821bd1d52d35b.jpg', '33061', 'image/jpeg', null, '2019-09-28 08:49:17', null, '2019-09-28 08:49:17', null, null);
INSERT INTO `sys_file_info_t` VALUES ('a89096c3473a472a9fa97e7defc2d1d3', '337725915249524736', '1569653415440', 'user_head', null, 'timg.jpg', 'jpg', 'timg', '/www/garden\\0da06a4e33c942a4a365e7b1b4fcc597\\a89096c3473a472a9fa97e7defc2d1d3.jpg', '\\0da06a4e33c942a4a365e7b1b4fcc597\\a89096c3473a472a9fa97e7defc2d1d3.jpg', '25866', 'image/jpeg', null, '2019-09-28 14:50:19', null, '2019-09-28 14:50:19', null, null);
INSERT INTO `sys_file_info_t` VALUES ('abe53ef4670943dfbdd4f8952a9e1e26', '337725915249524736', '1569652686168', 'user_head', null, '微信图片_20190925141416.jpg', 'jpg', '微信图片_20190925141416', '/www/garden\\b2cda4685fc748a093487c8fc6751a79\\abe53ef4670943dfbdd4f8952a9e1e26.jpg', '\\b2cda4685fc748a093487c8fc6751a79\\abe53ef4670943dfbdd4f8952a9e1e26.jpg', '56858', 'image/jpeg', null, '2019-09-28 14:38:19', null, '2019-09-28 14:38:19', null, null);
INSERT INTO `sys_file_info_t` VALUES ('e34191402169438582f0ffada341c2d2', '337725915249524736', '1569654106264', 'user_head', null, 'timg.jpg', 'jpg', 'timg', '/www/garden\\53a42d0098c348ab93a511e9906f5175\\e34191402169438582f0ffada341c2d2.jpg', '\\53a42d0098c348ab93a511e9906f5175\\e34191402169438582f0ffada341c2d2.jpg', '25866', 'image/jpeg', null, '2019-09-28 15:01:50', null, '2019-09-28 15:01:50', null, null);

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

-- ----------------------------
-- Records of sys_file_manage_t
-- ----------------------------
INSERT INTO `sys_file_manage_t` VALUES ('337091626111614976', 'asx', 'as', '1', '', null, '2019-09-26 15:13:33', null, '2019-09-26 15:13:33', null, null);
INSERT INTO `sys_file_manage_t` VALUES ('337725915249524736', '用户头像', 'garden_user_head', '1', '', null, '2019-09-28 08:49:03', null, '2019-09-28 08:49:03', null, null);

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
INSERT INTO `sys_function_t` VALUES ('312554985592164352', '306008072466173952', '列表', 'system:menu:list', '1', '/system/menu/list', null, '2019-09-28 10:45:31', null, '2019-09-28 10:45:32', null, null);
INSERT INTO `sys_function_t` VALUES ('312557438165291008', '306008072466173952', '新增', 'system:menu:add', '1', '/system/menu/add', null, '2019-09-28 10:45:34', null, '2019-09-28 10:45:35', null, null);
INSERT INTO `sys_function_t` VALUES ('312557544130187264', '306008072466173952', '修改', 'system:menu:edit', '1', '/system/menu/edit', null, '2019-09-28 10:45:37', null, '2019-09-28 10:45:38', null, null);
INSERT INTO `sys_function_t` VALUES ('312557853078425600', '306008072466173952', '删除', 'system:menu:del', '1', '/system/menu/delete', null, '2019-09-28 10:45:40', null, '2019-09-28 10:45:40', null, null);
INSERT INTO `sys_function_t` VALUES ('338169446418104320', '306009975694528512', '列表', 'dictionary:dictionary:list', '1', '/dictionary/dictionary/list', null, '2019-09-29 14:11:29', null, '2019-09-29 14:11:29', null, null);
INSERT INTO `sys_function_t` VALUES ('338169554983469056', '306009975694528512', '新增', 'dictionary:dictionary:add', '1', '/dictionary/dictionary/add', null, '2019-09-29 14:11:55', null, '2019-09-29 14:11:55', null, null);
INSERT INTO `sys_function_t` VALUES ('338170065937776640', '306009975694528512', '修改', 'dictionary:dictionary:edit', '1', '/dictionary/dictionary/edit', null, '2019-09-29 14:13:57', null, '2019-09-29 14:13:57', null, null);
INSERT INTO `sys_function_t` VALUES ('338170112028983296', '306009975694528512', '删除', 'dictionary:dictionary:del', '1', '/dictionary/dictionary/delete', null, '2019-09-29 14:14:08', null, '2019-09-29 14:14:08', null, null);

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
INSERT INTO `sys_group_t` VALUES ('334940055156310016', 'g', 'f', '1', null, '2019-10-25 14:48:04', null, '2019-10-25 14:48:05', null, null);

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
-- Table structure for sys_i18n_t
-- ----------------------------
DROP TABLE IF EXISTS `sys_i18n_t`;
CREATE TABLE `sys_i18n_t` (
  `ID` varchar(32) NOT NULL COMMENT 'ID',
  `CODE` varchar(128) DEFAULT '' COMMENT '国际化Code',
  `VALUE` varchar(255) DEFAULT '' COMMENT '国际化值',
  `LANGUAGE_TYPE` varchar(32) DEFAULT '' COMMENT '语言类型',
  `STATE` varchar(2) DEFAULT '' COMMENT '状态',
  `REMARK` varchar(255) DEFAULT '' COMMENT '备注',
  `CREATED_BY` varchar(32) DEFAULT '' COMMENT '创建人',
  `CREATION_DATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `UPDATED_BY` varchar(32) DEFAULT '' COMMENT '修改人',
  `UPDATION_DATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `ENABLED_FLAG` int(1) DEFAULT '1' COMMENT '是否禁用',
  `TRACE_ID` varchar(16) DEFAULT '' COMMENT '日志ID',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='国际化表';

-- ----------------------------
-- Records of sys_i18n_t
-- ----------------------------
INSERT INTO `sys_i18n_t` VALUES ('347600639295242240', 'message.i18n.code', '编码', 'zh', '1', '', null, '2019-10-25 14:50:53', null, '2019-10-25 14:50:54', null, null);
INSERT INTO `sys_i18n_t` VALUES ('347601566848794624', 'message.i18n.code', 'Code', 'en', '1', '', null, '2019-10-25 14:51:22', null, '2019-10-25 14:51:22', null, null);

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
INSERT INTO `sys_lookup_item_t` VALUES ('336006672686530560', '启用', '1', '1', 'COMMON_STATE', '', '', '', '', '', '', '2019-09-23 14:57:24', '', '2019-09-27 16:17:25', '1', '');
INSERT INTO `sys_lookup_item_t` VALUES ('336006729607430144', '禁用', '0', '1', 'COMMON_STATE', '', '', '', '', '', '', '2019-09-23 14:57:37', '', '2019-09-23 14:58:44', '1', '');
INSERT INTO `sys_lookup_item_t` VALUES ('336805656418074624', '5222=3', '5', '1', 'COMMON_STATE', '', '', '', '', '', '', '2019-09-25 19:52:16', '', '2019-09-28 11:02:18', '1', '');

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
INSERT INTO `sys_lookup_t` VALUES ('312570714076975104', '状态', 'COMMON_STATE', '1', null, '2019-07-20 22:51:58', null, '2019-09-23 14:59:52', null, null);
INSERT INTO `sys_lookup_t` VALUES ('334519396320165888', 'xxa', 'x2', '0', null, '2019-09-20 14:40:34', null, '2019-09-27 16:43:57', null, null);
INSERT INTO `sys_lookup_t` VALUES ('334519398597672960', 'x', 'x', '1', null, '2019-09-19 12:27:30', null, '2019-09-19 12:27:30', null, null);
INSERT INTO `sys_lookup_t` VALUES ('334519400812265472', 'x', 'x', '1', null, '2019-09-19 12:27:30', null, '2019-09-19 12:27:30', null, null);
INSERT INTO `sys_lookup_t` VALUES ('334519403194630144', 'x', 'x', '1', null, '2019-09-19 12:27:31', null, '2019-09-19 12:27:31', null, null);
INSERT INTO `sys_lookup_t` VALUES ('334519408252960768', 'x', 'x', '1', null, '2019-09-19 12:27:32', null, '2019-09-19 12:27:32', null, null);
INSERT INTO `sys_lookup_t` VALUES ('334519413193850880', 'x', 'x', '1', null, '2019-09-19 12:27:33', null, '2019-09-19 12:27:33', null, null);
INSERT INTO `sys_lookup_t` VALUES ('334519416217944064', 'x', 'x', '1', null, '2019-09-19 12:27:34', null, '2019-09-19 12:27:34', null, null);
INSERT INTO `sys_lookup_t` VALUES ('334519422920441856', 'x', 'x', '1', null, '2019-09-19 12:27:36', null, '2019-09-19 12:27:36', null, null);
INSERT INTO `sys_lookup_t` VALUES ('334519426548514816', 'x', 'x', '1', null, '2019-09-19 12:27:37', null, '2019-09-19 12:27:37', null, null);
INSERT INTO `sys_lookup_t` VALUES ('334519430566658048', 'x', 'x', '1', null, '2019-09-19 12:27:38', null, '2019-09-19 12:27:38', null, null);
INSERT INTO `sys_lookup_t` VALUES ('334519433762717696', 'x', 'x', '1', null, '2019-09-19 12:27:38', null, '2019-09-19 12:27:38', null, null);
INSERT INTO `sys_lookup_t` VALUES ('334567734239313920', 'd', 's', '1', null, '2019-09-19 15:39:34', null, '2019-09-19 15:39:34', null, null);
INSERT INTO `sys_lookup_t` VALUES ('334567737330515968', 'd', 's', '1', null, '2019-09-19 15:39:35', null, '2019-09-19 15:39:35', null, null);
INSERT INTO `sys_lookup_t` VALUES ('334567738202931200', 'd', 's', '1', null, '2019-09-19 15:39:35', null, '2019-09-19 15:39:35', null, null);
INSERT INTO `sys_lookup_t` VALUES ('334567738974683136', 'd', 's', '1', null, '2019-09-19 15:39:35', null, '2019-09-19 15:39:35', null, null);
INSERT INTO `sys_lookup_t` VALUES ('334567739712880640', 'd', 's', '1', null, '2019-09-19 15:39:35', null, '2019-09-19 15:39:35', null, null);
INSERT INTO `sys_lookup_t` VALUES ('334567740413329408', 'd', 's', '1', null, '2019-09-19 15:39:36', null, '2019-09-19 15:39:36', null, null);
INSERT INTO `sys_lookup_t` VALUES ('334567741185081344', 'd', 's', '1', null, '2019-09-19 15:39:36', null, '2019-09-19 15:39:36', null, null);
INSERT INTO `sys_lookup_t` VALUES ('334567742565007360', 'd', 's', '1', null, '2019-09-19 15:39:36', null, '2019-09-19 15:39:36', null, null);
INSERT INTO `sys_lookup_t` VALUES ('334567743437422592', 'd', 's', '1', null, '2019-09-19 15:39:36', null, '2019-09-19 15:39:36', null, null);
INSERT INTO `sys_lookup_t` VALUES ('334567744406306816', 'd', 's', '1', null, '2019-09-19 15:39:36', null, '2019-09-19 15:39:36', null, null);
INSERT INTO `sys_lookup_t` VALUES ('334567745282916352', 'd', 's', '1', null, '2019-09-19 15:39:37', null, '2019-09-19 15:39:37', null, null);
INSERT INTO `sys_lookup_t` VALUES ('334567746054668288', 'd', 's', '1', null, '2019-09-19 15:39:37', null, '2019-09-19 15:39:37', null, null);
INSERT INTO `sys_lookup_t` VALUES ('334567746792865792', 'd', 's', '1', null, '2019-09-19 15:39:37', null, '2019-09-19 15:39:37', null, null);
INSERT INTO `sys_lookup_t` VALUES ('334567752295792640', 'd', 's', '1', null, '2019-09-19 15:39:38', null, '2019-09-19 15:39:38', null, null);
INSERT INTO `sys_lookup_t` VALUES ('334567755584126976', 'd', 's', '1', null, '2019-09-19 15:39:39', null, '2019-09-19 15:39:39', null, null);
INSERT INTO `sys_lookup_t` VALUES ('334567759317057536', 'd', 's', '1', null, '2019-09-19 15:39:40', null, '2019-09-19 15:39:40', null, null);
INSERT INTO `sys_lookup_t` VALUES ('334567764148895744', 'd', 's', '1', null, '2019-09-19 15:39:41', null, '2019-09-19 15:39:41', null, null);
INSERT INTO `sys_lookup_t` VALUES ('334567769144311808', 'd', 's', '1', null, '2019-09-19 15:39:42', null, '2019-09-19 15:39:42', null, null);
INSERT INTO `sys_lookup_t` VALUES ('334567773460250624', 'd', 's', '1', null, '2019-09-19 15:39:43', null, '2019-09-19 15:39:43', null, null);
INSERT INTO `sys_lookup_t` VALUES ('334911952426123264', 'g', 'g', '1', null, '2019-09-20 14:27:22', null, '2019-09-20 14:27:22', null, null);
INSERT INTO `sys_lookup_t` VALUES ('334911955613794304', 'g', 'g', '1', null, '2019-09-20 14:27:23', null, '2019-09-20 14:27:23', null, null);
INSERT INTO `sys_lookup_t` VALUES ('334911958205874176', 'g', 'g', '1', null, '2019-09-20 14:27:23', null, '2019-09-20 14:27:23', null, null);
INSERT INTO `sys_lookup_t` VALUES ('334911960760205312', 'g', 'g', '1', null, '2019-09-20 14:27:24', null, '2019-09-20 14:27:24', null, null);
INSERT INTO `sys_lookup_t` VALUES ('334911963008352256', 'g', 'g', '1', null, '2019-09-20 14:27:25', null, '2019-09-20 14:27:25', null, null);
INSERT INTO `sys_lookup_t` VALUES ('334911965428465664', 'g', 'g', '1', null, '2019-09-20 14:27:25', null, '2019-09-20 14:27:25', null, null);
INSERT INTO `sys_lookup_t` VALUES ('334911967617892352', 'g', 'g', '1', null, '2019-09-20 14:27:26', null, '2019-09-20 14:27:26', null, null);
INSERT INTO `sys_lookup_t` VALUES ('334911970038005760', 'g', 'g', '1', null, '2019-09-20 14:27:26', null, '2019-09-20 14:27:26', null, null);
INSERT INTO `sys_lookup_t` VALUES ('334911972068048896', 'g', 'g', '1', null, '2019-09-20 14:27:27', null, '2019-09-20 14:27:27', null, null);
INSERT INTO `sys_lookup_t` VALUES ('334911974186172416', 'g', 'g', '1', null, '2019-09-20 14:27:27', null, '2019-09-20 14:27:27', null, null);
INSERT INTO `sys_lookup_t` VALUES ('334911976698560512', 'g', 'g', '1', null, '2019-09-20 14:27:28', null, '2019-09-20 14:27:28', null, null);
INSERT INTO `sys_lookup_t` VALUES ('334911978950901760', 'g', 'g', '1', null, '2019-09-20 14:27:28', null, '2019-09-20 14:27:28', null, null);
INSERT INTO `sys_lookup_t` VALUES ('334911981232603136', 'g', 'g', '1', null, '2019-09-20 14:27:29', null, '2019-09-20 14:27:29', null, null);
INSERT INTO `sys_lookup_t` VALUES ('334911983744991232', 'g', 'g', '1', null, '2019-09-20 14:27:30', null, '2019-09-20 14:27:30', null, null);
INSERT INTO `sys_lookup_t` VALUES ('334911986261573632', 'g', 'g', '1', null, '2019-09-20 14:27:30', null, '2019-09-20 14:27:30', null, null);
INSERT INTO `sys_lookup_t` VALUES ('334911988757184512', 'g', 'g', '1', null, '2019-09-20 14:27:31', null, '2019-09-20 14:27:31', null, null);
INSERT INTO `sys_lookup_t` VALUES ('334911990925639680', 'g', 'g', '1', null, '2019-09-20 14:27:31', null, '2019-09-20 14:27:31', null, null);
INSERT INTO `sys_lookup_t` VALUES ('334911993547079680', 'g', 'g', '1', null, '2019-09-20 14:27:32', null, '2019-09-20 14:27:32', null, null);
INSERT INTO `sys_lookup_t` VALUES ('334911995929444352', 'g', 'g', '1', null, '2019-09-20 14:27:32', null, '2019-09-20 14:27:32', null, null);
INSERT INTO `sys_lookup_t` VALUES ('334911998408278016', 'g', 'g', '1', null, '2019-09-20 14:27:33', null, '2019-09-20 14:27:33', null, null);
INSERT INTO `sys_lookup_t` VALUES ('334912000824197120', 'g', 'g', '1', null, '2019-09-20 14:27:34', null, '2019-09-20 14:27:34', null, null);
INSERT INTO `sys_lookup_t` VALUES ('334912003105898496', 'g', 'g', '1', null, '2019-09-20 14:27:34', null, '2019-09-20 14:27:34', null, null);
INSERT INTO `sys_lookup_t` VALUES ('334912007606386688', 'g', 'g', '1', null, '2019-09-20 14:27:35', null, '2019-09-20 14:27:35', null, null);
INSERT INTO `sys_lookup_t` VALUES ('334912011037327360', 'g', 'g', '1', null, '2019-09-20 14:27:36', null, '2019-09-20 14:27:36', null, null);
INSERT INTO `sys_lookup_t` VALUES ('334912015449735168', 'g', 'g', '1', null, '2019-09-20 14:27:37', null, '2019-09-20 14:27:37', null, null);
INSERT INTO `sys_lookup_t` VALUES ('334912019589513216', 'g', 'g', '1', null, '2019-09-20 14:27:38', null, '2019-09-20 14:27:38', null, null);
INSERT INTO `sys_lookup_t` VALUES ('334912023716708352', 'g', 'g', '1', null, '2019-09-20 14:27:39', null, '2019-09-20 14:27:39', null, null);
INSERT INTO `sys_lookup_t` VALUES ('334912027911012352', 'g', 'g', '1', null, '2019-09-20 14:27:40', null, '2019-09-20 14:27:40', null, null);
INSERT INTO `sys_lookup_t` VALUES ('334912032164036608', 'g', 'g', '1', null, '2019-09-20 14:27:41', null, '2019-09-20 14:27:41', null, null);
INSERT INTO `sys_lookup_t` VALUES ('334912036337369088', 'g', 'g', '1', null, '2019-09-20 14:27:42', null, '2019-09-20 14:27:42', null, null);
INSERT INTO `sys_lookup_t` VALUES ('334912040359706624', 'g', 'g', '1', null, '2019-09-20 14:27:43', null, '2019-09-20 14:27:43', null, null);
INSERT INTO `sys_lookup_t` VALUES ('334931508695678976', 'xx', 'xx', '1', null, '2019-09-20 15:45:05', null, '2019-09-20 15:45:05', null, null);

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
INSERT INTO `sys_menu_t` VALUES ('306008072466173952', '菜单管理', '', '1', 'system/menu/toList', '10', '', '306007560043859968', '', '', '', '2019-07-02 20:13:40', '', '2019-09-28 16:07:42', '1', '');
INSERT INTO `sys_menu_t` VALUES ('306008179878105088', '用户管理', '', '1', 'system/user/toList', '10', '', '306007745968967680', '', '', '', '2019-07-02 20:14:05', '', '2019-07-02 20:14:05', '1', '');
INSERT INTO `sys_menu_t` VALUES ('306008313688985600', '角色管理', '', '1', 'system/role/toList', '20', '', '306007745968967680', '', '', '', '2019-07-02 20:14:37', '', '2019-07-02 20:14:37', '1', '');
INSERT INTO `sys_menu_t` VALUES ('306009272351690752', '群组管理', '', '1', 'system/group/toList', '30', '', '306007745968967680', '', '', '', '2019-07-02 20:18:26', '', '2019-07-02 20:18:26', '1', '');
INSERT INTO `sys_menu_t` VALUES ('306009324067459072', '权限管理', '', '0', '', '50', '', '306007745968967680', '', '', '', '2019-07-02 20:18:38', '', '2019-09-29 16:21:30', '1', '');
INSERT INTO `sys_menu_t` VALUES ('306009578489745408', '系统日志', '', '0', '', '200', '', '0', '&#xe60a;', '', '', '2019-07-02 20:19:39', '', '2019-09-29 16:21:33', '1', '');
INSERT INTO `sys_menu_t` VALUES ('306009670156259328', '系统设置', '', '1', '', '130', '', '0', '&#xe716;', '系统设置的地方', '', '2019-07-02 20:20:00', '', '2019-07-02 20:20:00', '1', '');
INSERT INTO `sys_menu_t` VALUES ('306009975694528512', '数据字典', '', '1', 'dictionary/dictionary/toList', '20', '', '306009670156259328', '', '', '', '2019-07-02 20:21:13', '', '2019-09-29 14:09:17', '1', '');
INSERT INTO `sys_menu_t` VALUES ('306010239977623552', 'Lookup管理', '', '1', 'lookup/lookup/toList', '10', '', '306009670156259328', '', '', '', '2019-07-02 20:22:16', '', '2019-07-02 20:22:16', '1', '');
INSERT INTO `sys_menu_t` VALUES ('312556679147261952', '功能管理', '', '1', 'system/function/toList', '40', '', '306007745968967680', '', '', '', '2019-07-20 21:55:29', '', '2019-09-29 14:13:24', '1', '');
INSERT INTO `sys_menu_t` VALUES ('325600478249324544', '组织管理', '', '1', 'system/organization/toList', '60', '', '306007745968967680', '', '', '', '2019-08-25 21:46:53', '', '2019-08-25 21:46:53', '1', '');
INSERT INTO `sys_menu_t` VALUES ('325600600563617792', '职务管理', '', '1', 'system/position/toList', '70', '', '306007745968967680', '', '', '', '2019-08-25 21:47:22', '', '2019-08-25 21:47:22', '1', '');
INSERT INTO `sys_menu_t` VALUES ('337089371388002304', '文件管理', '', '1', 'file/fileManage/toList', '30', '', '306009670156259328', '', '', '', '2019-09-26 14:39:39', '', '2019-09-26 14:40:15', '1', '');
INSERT INTO `sys_menu_t` VALUES ('346876411570831360', '任务管理', '', '1', 'job/task/toList', '40', '', '306009670156259328', '', '', '', '2019-10-23 14:49:51', '', '2019-10-23 14:50:21', '1', '');
INSERT INTO `sys_menu_t` VALUES ('347596715825840128', '国际化管理', '', '1', 'i18n/i18n/toList', '50', '', '306009670156259328', '', '', '', '2019-10-25 14:32:05', '', '2019-10-25 14:32:08', '1', '');

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
INSERT INTO `sys_role_function_t` VALUES ('338122550211264512', '312554985592164352', '2');
INSERT INTO `sys_role_function_t` VALUES ('338122550211264512', '312557438165291008', '2');
INSERT INTO `sys_role_function_t` VALUES ('338122550211264512', '312557544130187264', '2');
INSERT INTO `sys_role_function_t` VALUES ('338122550211264512', '312557853078425600', '2');
INSERT INTO `sys_role_function_t` VALUES ('338125469321019392', '312554985592164352', '2');
INSERT INTO `sys_role_function_t` VALUES ('338125469321019392', '312557438165291008', '2');
INSERT INTO `sys_role_function_t` VALUES ('338125469321019392', '312557544130187264', '2');
INSERT INTO `sys_role_function_t` VALUES ('338125469321019392', '312557853078425600', '2');
INSERT INTO `sys_role_function_t` VALUES ('303106948964782080', '312554985592164352', '2');
INSERT INTO `sys_role_function_t` VALUES ('303106948964782080', '312557438165291008', '2');
INSERT INTO `sys_role_function_t` VALUES ('303106948964782080', '312557544130187264', '2');
INSERT INTO `sys_role_function_t` VALUES ('303106948964782080', '312557853078425600', '2');
INSERT INTO `sys_role_function_t` VALUES ('303106948964782080', '338169446418104320', '2');
INSERT INTO `sys_role_function_t` VALUES ('303106948964782080', '338169554983469056', '2');
INSERT INTO `sys_role_function_t` VALUES ('303106948964782080', '338170065937776640', '2');
INSERT INTO `sys_role_function_t` VALUES ('303106948964782080', '338170112028983296', '2');

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
INSERT INTO `sys_role_menu_t` VALUES ('338122550211264512', '306007560043859968');
INSERT INTO `sys_role_menu_t` VALUES ('338122550211264512', '306008072466173952');
INSERT INTO `sys_role_menu_t` VALUES ('338122550211264512', '306009670156259328');
INSERT INTO `sys_role_menu_t` VALUES ('338122550211264512', '306009975694528512');
INSERT INTO `sys_role_menu_t` VALUES ('338122550211264512', '306010239977623552');
INSERT INTO `sys_role_menu_t` VALUES ('338125469321019392', '306007560043859968');
INSERT INTO `sys_role_menu_t` VALUES ('338125469321019392', '306008072466173952');
INSERT INTO `sys_role_menu_t` VALUES ('303106948964782080', '306007560043859968');
INSERT INTO `sys_role_menu_t` VALUES ('303106948964782080', '306008072466173952');
INSERT INTO `sys_role_menu_t` VALUES ('303106948964782080', '306007745968967680');
INSERT INTO `sys_role_menu_t` VALUES ('303106948964782080', '306008179878105088');
INSERT INTO `sys_role_menu_t` VALUES ('303106948964782080', '306008313688985600');
INSERT INTO `sys_role_menu_t` VALUES ('303106948964782080', '306009272351690752');
INSERT INTO `sys_role_menu_t` VALUES ('303106948964782080', '312556679147261952');
INSERT INTO `sys_role_menu_t` VALUES ('303106948964782080', '325600478249324544');
INSERT INTO `sys_role_menu_t` VALUES ('303106948964782080', '325600600563617792');
INSERT INTO `sys_role_menu_t` VALUES ('303106948964782080', '306009670156259328');
INSERT INTO `sys_role_menu_t` VALUES ('303106948964782080', '306009975694528512');
INSERT INTO `sys_role_menu_t` VALUES ('303106948964782080', '306010239977623552');
INSERT INTO `sys_role_menu_t` VALUES ('303106948964782080', '337089371388002304');
INSERT INTO `sys_role_menu_t` VALUES ('303106948964782080', '346876411570831360');
INSERT INTO `sys_role_menu_t` VALUES ('303106948964782080', '347596715825840128');

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
INSERT INTO `sys_role_t` VALUES ('303106948964782080', '超级管理员', '0000000001', '1', null, '2019-09-28 14:32:19', null, '2019-09-28 14:32:19', null, null);
INSERT INTO `sys_role_t` VALUES ('338122550211264512', '管理员', '0175', '1', null, '2019-09-29 11:05:08', null, '2019-09-29 11:05:08', null, null);
INSERT INTO `sys_role_t` VALUES ('338125469321019392', '管理员2', '0176', '1', null, '2019-09-29 11:16:44', null, '2019-09-29 11:16:44', null, null);

-- ----------------------------
-- Table structure for sys_task_log_t
-- ----------------------------
DROP TABLE IF EXISTS `sys_task_log_t`;
CREATE TABLE `sys_task_log_t` (
  `ID` varchar(32) NOT NULL,
  `TASK_ID` varchar(32) DEFAULT '' COMMENT '任务ID',
  `TYPE` varchar(2) DEFAULT '' COMMENT '类型',
  `RESPONSE_CONTENT` varchar(512) DEFAULT '' COMMENT '响应内容',
  `BEGIN_DATE` timestamp NULL DEFAULT NULL COMMENT '任务开始执行时间',
  `END_DATE` timestamp NULL DEFAULT NULL COMMENT '任务结束执行时间',
  `CONSUME_TIME` int(11) DEFAULT '0' COMMENT '任务消耗的时间',
  `REMARK` varchar(255) DEFAULT '' COMMENT '备注',
  `CREATED_BY` varchar(32) DEFAULT '' COMMENT '创建人',
  `CREATION_DATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `UPDATED_BY` varchar(32) DEFAULT '' COMMENT '修改人',
  `UPDATION_DATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `ENABLED_FLAG` int(1) DEFAULT '1' COMMENT '是否禁用',
  `TRACE_ID` varchar(16) DEFAULT '' COMMENT '日志ID',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='任务日志表';

-- ----------------------------
-- Records of sys_task_log_t
-- ----------------------------
INSERT INTO `sys_task_log_t` VALUES ('346902621327736832', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-10-23 16:34:00', '2019-10-23 16:34:00', '267', null, null, '2019-10-23 16:34:00', null, '2019-10-23 16:34:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('346902871878680576', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-10-23 16:35:00', '2019-10-23 16:35:00', '18', null, null, '2019-10-23 16:35:00', null, '2019-10-23 16:35:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('346903123817938944', '346902598779158528', '0', 'Source string may not be null', '2019-10-23 16:36:00', '2019-10-23 16:36:00', '63', null, null, '2019-10-23 16:36:00', null, '2019-10-23 16:36:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('346903433038807040', '346902598779158528', '0', 'Source string may not be null', '2019-10-23 16:37:03', '2019-10-23 16:37:14', '11071', null, null, '2019-10-23 16:37:14', null, '2019-10-23 16:37:14', null, null);
INSERT INTO `sys_task_log_t` VALUES ('346903888733159424', '346902598779158528', '0', 'Source string may not be null', '2019-10-23 16:39:02', '2019-10-23 16:39:02', '95', null, null, '2019-10-23 16:39:02', null, '2019-10-23 16:39:02', null, null);
INSERT INTO `sys_task_log_t` VALUES ('346904393509257216', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-10-23 16:41:00', '2019-10-23 16:41:03', '2777', null, null, '2019-10-23 16:41:03', null, '2019-10-23 16:41:03', null, null);
INSERT INTO `sys_task_log_t` VALUES ('346904633574440960', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-10-23 16:42:00', '2019-10-23 16:42:00', '32', null, null, '2019-10-23 16:42:00', null, '2019-10-23 16:42:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('346904885211709440', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-10-23 16:43:00', '2019-10-23 16:43:00', '33', null, null, '2019-10-23 16:43:00', null, '2019-10-23 16:43:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('346918475675222016', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-10-23 17:37:00', '2019-10-23 17:37:00', '230', null, null, '2019-10-23 17:37:00', null, '2019-10-23 17:37:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('347234558462410752', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-10-24 14:33:00', '2019-10-24 14:33:00', '225', null, null, '2019-10-24 14:33:00', null, '2019-10-24 14:33:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('347234809206292480', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-10-24 14:34:00', '2019-10-24 14:34:00', '31', null, null, '2019-10-24 14:34:00', null, '2019-10-24 14:34:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('347235060814200832', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-10-24 14:35:00', '2019-10-24 14:35:00', '37', null, null, '2019-10-24 14:35:00', null, '2019-10-24 14:35:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('347235312451469312', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-10-24 14:36:00', '2019-10-24 14:36:00', '29', null, null, '2019-10-24 14:36:00', null, '2019-10-24 14:36:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('347235564025823232', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-10-24 14:37:00', '2019-10-24 14:37:00', '11', null, null, '2019-10-24 14:37:00', null, '2019-10-24 14:37:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('347235815763755008', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-10-24 14:38:00', '2019-10-24 14:38:00', '28', null, null, '2019-10-24 14:38:00', null, '2019-10-24 14:38:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('347237077179711488', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-10-24 14:43:00', '2019-10-24 14:43:01', '718', null, null, '2019-10-24 14:43:01', null, '2019-10-24 14:43:01', null, null);
INSERT INTO `sys_task_log_t` VALUES ('347237598091296768', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-10-24 14:45:03', '2019-10-24 14:45:05', '2085', null, null, '2019-10-24 14:45:05', null, '2019-10-24 14:45:05', null, null);
INSERT INTO `sys_task_log_t` VALUES ('347237829008703488', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-10-24 14:46:00', '2019-10-24 14:46:00', '16', null, null, '2019-10-24 14:46:00', null, '2019-10-24 14:46:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('347238080725663744', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-10-24 14:47:00', '2019-10-24 14:47:00', '35', null, null, '2019-10-24 14:47:00', null, '2019-10-24 14:47:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('347239341906739200', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-10-24 14:52:00', '2019-10-24 14:52:01', '674', null, null, '2019-10-24 14:52:01', null, '2019-10-24 14:52:01', null, null);
INSERT INTO `sys_task_log_t` VALUES ('347239590616383488', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-10-24 14:53:00', '2019-10-24 14:53:00', '19', null, null, '2019-10-24 14:53:00', null, '2019-10-24 14:53:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('347239842320760832', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-10-24 14:54:00', '2019-10-24 14:54:00', '33', null, null, '2019-10-24 14:54:00', null, '2019-10-24 14:54:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('347240093928669184', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-10-24 14:55:00', '2019-10-24 14:55:00', '22', null, null, '2019-10-24 14:55:00', null, '2019-10-24 14:55:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('347240345549160448', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-10-24 14:56:00', '2019-10-24 14:56:00', '16', null, null, '2019-10-24 14:56:00', null, '2019-10-24 14:56:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('347240597270315008', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-10-24 14:57:00', '2019-10-24 14:57:00', '28', null, null, '2019-10-24 14:57:00', null, '2019-10-24 14:57:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('347240848932749312', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-10-24 14:58:00', '2019-10-24 14:58:00', '29', null, null, '2019-10-24 14:58:00', null, '2019-10-24 14:58:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('347241100595183616', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-10-24 14:59:00', '2019-10-24 14:59:00', '31', null, null, '2019-10-24 14:59:00', null, '2019-10-24 14:59:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('347241352198897664', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-10-24 15:00:00', '2019-10-24 15:00:00', '19', null, null, '2019-10-24 15:00:00', null, '2019-10-24 15:00:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('347241603932635136', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-10-24 15:01:00', '2019-10-24 15:01:00', '33', null, null, '2019-10-24 15:01:00', null, '2019-10-24 15:01:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('347241855569903616', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-10-24 15:02:00', '2019-10-24 15:02:00', '29', null, null, '2019-10-24 15:02:00', null, '2019-10-24 15:02:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('347242107194589184', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-10-24 15:03:00', '2019-10-24 15:03:00', '24', null, null, '2019-10-24 15:03:00', null, '2019-10-24 15:03:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('347242358794108928', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-10-24 15:04:00', '2019-10-24 15:04:00', '10', null, null, '2019-10-24 15:04:00', null, '2019-10-24 15:04:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('347242610561400832', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-10-24 15:05:00', '2019-10-24 15:05:00', '33', null, null, '2019-10-24 15:05:00', null, '2019-10-24 15:05:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('347595698069585920', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-10-25 14:28:00', '2019-10-25 14:28:03', '2313', null, null, '2019-10-25 14:28:03', null, '2019-10-25 14:28:03', null, null);
INSERT INTO `sys_task_log_t` VALUES ('347595938675834880', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-10-25 14:29:00', '2019-10-25 14:29:00', '16', null, null, '2019-10-25 14:29:00', null, '2019-10-25 14:29:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('347596190346657792', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-10-25 14:30:00', '2019-10-25 14:30:00', '25', null, null, '2019-10-25 14:30:00', null, '2019-10-25 14:30:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('347596441962954752', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-10-25 14:31:00', '2019-10-25 14:31:00', '16', null, null, '2019-10-25 14:31:00', null, '2019-10-25 14:31:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('347596693637971968', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-10-25 14:32:00', '2019-10-25 14:32:00', '19', null, null, '2019-10-25 14:32:00', null, '2019-10-25 14:32:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('347596945304600576', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-10-25 14:33:00', '2019-10-25 14:33:00', '21', null, null, '2019-10-25 14:33:00', null, '2019-10-25 14:33:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('347598213846679552', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-10-25 14:38:00', '2019-10-25 14:38:02', '2431', null, null, '2019-10-25 14:38:02', null, '2019-10-25 14:38:02', null, null);
INSERT INTO `sys_task_log_t` VALUES ('347598708006993920', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-10-25 14:40:00', '2019-10-25 14:40:00', '236', null, null, '2019-10-25 14:40:00', null, '2019-10-25 14:40:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('347598958591492096', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-10-25 14:41:00', '2019-10-25 14:41:00', '26', null, null, '2019-10-25 14:41:00', null, '2019-10-25 14:41:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('347599210224566272', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-10-25 14:42:00', '2019-10-25 14:42:00', '20', null, null, '2019-10-25 14:42:00', null, '2019-10-25 14:42:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('347599461857640448', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-10-25 14:43:00', '2019-10-25 14:43:00', '17', null, null, '2019-10-25 14:43:00', null, '2019-10-25 14:43:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('347599713532657664', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-10-25 14:44:00', '2019-10-25 14:44:00', '20', null, null, '2019-10-25 14:44:00', null, '2019-10-25 14:44:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('347599965266395136', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-10-25 14:45:00', '2019-10-25 14:45:00', '32', null, null, '2019-10-25 14:45:00', null, '2019-10-25 14:45:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('347600216811388928', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-10-25 14:46:00', '2019-10-25 14:46:00', '12', null, null, '2019-10-25 14:46:00', null, '2019-10-25 14:46:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('347600468515766272', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-10-25 14:47:00', '2019-10-25 14:47:00', '21', null, null, '2019-10-25 14:47:00', null, '2019-10-25 14:47:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('347600720169811968', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-10-25 14:48:00', '2019-10-25 14:48:00', '20', null, null, '2019-10-25 14:48:00', null, '2019-10-25 14:48:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('347600971819663360', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-10-25 14:49:00', '2019-10-25 14:49:00', '17', null, null, '2019-10-25 14:49:00', null, '2019-10-25 14:49:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('347601223448543232', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-10-25 14:50:00', '2019-10-25 14:50:00', '12', null, null, '2019-10-25 14:50:00', null, '2019-10-25 14:50:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('347601475127754752', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-10-25 14:51:00', '2019-10-25 14:51:00', '17', null, null, '2019-10-25 14:51:00', null, '2019-10-25 14:51:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('347601726765023232', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-10-25 14:52:00', '2019-10-25 14:52:00', '13', null, null, '2019-10-25 14:52:00', null, '2019-10-25 14:52:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('347602231302045696', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-10-25 14:54:00', '2019-10-25 14:54:00', '255', null, null, '2019-10-25 14:54:00', null, '2019-10-25 14:54:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('347602481794269184', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-10-25 14:55:00', '2019-10-25 14:55:00', '21', null, null, '2019-10-25 14:55:00', null, '2019-10-25 14:55:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('347602733423149056', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-10-25 14:56:00', '2019-10-25 14:56:00', '17', null, null, '2019-10-25 14:56:00', null, '2019-10-25 14:56:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('347602985106554880', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-10-25 14:57:00', '2019-10-25 14:57:00', '24', null, null, '2019-10-25 14:57:00', null, '2019-10-25 14:57:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('347603236781572096', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-10-25 14:58:00', '2019-10-25 14:58:00', '26', null, null, '2019-10-25 14:58:00', null, '2019-10-25 14:58:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('347603488414646272', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-10-25 14:59:00', '2019-10-25 14:59:00', '21', null, null, '2019-10-25 14:59:00', null, '2019-10-25 14:59:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('347603740081274880', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-10-25 15:00:00', '2019-10-25 15:00:00', '22', null, null, '2019-10-25 15:00:00', null, '2019-10-25 15:00:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('347603991739514880', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-10-25 15:01:00', '2019-10-25 15:01:00', '23', null, null, '2019-10-25 15:01:00', null, '2019-10-25 15:01:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('347604243464863744', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-10-25 15:02:00', '2019-10-25 15:02:00', '36', null, null, '2019-10-25 15:02:00', null, '2019-10-25 15:02:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('347604495022440448', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-10-25 15:03:00', '2019-10-25 15:03:00', '14', null, null, '2019-10-25 15:03:00', null, '2019-10-25 15:03:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('347604746785538048', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-10-25 15:04:00', '2019-10-25 15:04:00', '37', null, null, '2019-10-25 15:04:00', null, '2019-10-25 15:04:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('347604998372474880', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-10-25 15:05:00', '2019-10-25 15:05:00', '22', null, null, '2019-10-25 15:05:00', null, '2019-10-25 15:05:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('347605250030714880', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-10-25 15:06:00', '2019-10-25 15:06:00', '23', null, null, '2019-10-25 15:06:00', null, '2019-10-25 15:06:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('347605501684760576', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-10-25 15:07:00', '2019-10-25 15:07:00', '21', null, null, '2019-10-25 15:07:00', null, '2019-10-25 15:07:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('347605753351389184', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-10-25 15:08:00', '2019-10-25 15:08:00', '21', null, null, '2019-10-25 15:08:00', null, '2019-10-25 15:08:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('347606004963491840', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-10-25 15:09:00', '2019-10-25 15:09:00', '13', null, null, '2019-10-25 15:09:00', null, '2019-10-25 15:09:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('347606256751755264', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-10-25 15:10:00', '2019-10-25 15:10:00', '39', null, null, '2019-10-25 15:10:00', null, '2019-10-25 15:10:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('347606508313526272', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-10-25 15:11:00', '2019-10-25 15:11:00', '22', null, null, '2019-10-25 15:11:00', null, '2019-10-25 15:11:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('347606759967571968', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-10-25 15:12:00', '2019-10-25 15:12:00', '20', null, null, '2019-10-25 15:12:00', null, '2019-10-25 15:12:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('347607011625811968', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-10-25 15:13:00', '2019-10-25 15:13:00', '20', null, null, '2019-10-25 15:13:00', null, '2019-10-25 15:13:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('347607263288246272', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-10-25 15:14:00', '2019-10-25 15:14:00', '21', null, null, '2019-10-25 15:14:00', null, '2019-10-25 15:14:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('347607514938097664', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-10-25 15:15:00', '2019-10-25 15:15:00', '18', null, null, '2019-10-25 15:15:00', null, '2019-10-25 15:15:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('347607766587949056', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-10-25 15:16:00', '2019-10-25 15:16:00', '18', null, null, '2019-10-25 15:16:00', null, '2019-10-25 15:16:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('347608018279743488', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-10-25 15:17:00', '2019-10-25 15:17:00', '25', null, null, '2019-10-25 15:17:00', null, '2019-10-25 15:17:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('347608269929594880', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-10-25 15:18:00', '2019-10-25 15:18:00', '23', null, null, '2019-10-25 15:18:00', null, '2019-10-25 15:18:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('347608521592029184', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-10-25 15:19:00', '2019-10-25 15:19:00', '25', null, null, '2019-10-25 15:19:00', null, '2019-10-25 15:19:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('347608773233491968', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-10-25 15:20:00', '2019-10-25 15:20:00', '19', null, null, '2019-10-25 15:20:00', null, '2019-10-25 15:20:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('347609024908509184', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-10-25 15:21:00', '2019-10-25 15:21:00', '23', null, null, '2019-10-25 15:21:00', null, '2019-10-25 15:21:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('347609276570943488', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-10-25 15:22:00', '2019-10-25 15:22:00', '24', null, null, '2019-10-25 15:22:00', null, '2019-10-25 15:22:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('347609528220794880', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-10-25 15:23:00', '2019-10-25 15:23:00', '23', null, null, '2019-10-25 15:23:00', null, '2019-10-25 15:23:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('347609779866451968', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-10-25 15:24:00', '2019-10-25 15:24:00', '19', null, null, '2019-10-25 15:24:00', null, '2019-10-25 15:24:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('347610031503720448', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-10-25 15:25:00', '2019-10-25 15:25:00', '16', null, null, '2019-10-25 15:25:00', null, '2019-10-25 15:25:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('347610283203903488', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-10-25 15:26:00', '2019-10-25 15:26:00', '26', null, null, '2019-10-25 15:26:00', null, '2019-10-25 15:26:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('347610534841171968', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-10-25 15:27:00', '2019-10-25 15:27:00', '19', null, null, '2019-10-25 15:27:00', null, '2019-10-25 15:27:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('347610786520383488', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-10-25 15:28:00', '2019-10-25 15:28:00', '25', null, null, '2019-10-25 15:28:00', null, '2019-10-25 15:28:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('347611038170234880', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-10-25 15:29:00', '2019-10-25 15:29:00', '23', null, null, '2019-10-25 15:29:00', null, '2019-10-25 15:29:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('347611289815891968', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-10-25 15:30:00', '2019-10-25 15:30:00', '21', null, null, '2019-10-25 15:30:00', null, '2019-10-25 15:30:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('347611541482520576', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-10-25 15:31:00', '2019-10-25 15:31:00', '22', null, null, '2019-10-25 15:31:00', null, '2019-10-25 15:31:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('347611793149149184', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-10-25 15:32:00', '2019-10-25 15:32:00', '24', null, null, '2019-10-25 15:32:00', null, '2019-10-25 15:32:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('347612044782223360', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-10-25 15:33:00', '2019-10-25 15:33:00', '19', null, null, '2019-10-25 15:33:00', null, '2019-10-25 15:33:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('347612296465629184', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-10-25 15:34:00', '2019-10-25 15:34:00', '23', null, null, '2019-10-25 15:34:00', null, '2019-10-25 15:34:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('347612548136452096', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-10-25 15:35:00', '2019-10-25 15:35:00', '26', null, null, '2019-10-25 15:35:00', null, '2019-10-25 15:35:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('347612799765331968', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-10-25 15:36:00', '2019-10-25 15:36:00', '19', null, null, '2019-10-25 15:36:00', null, '2019-10-25 15:36:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('347613051419377664', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-10-25 15:37:00', '2019-10-25 15:37:00', '18', null, null, '2019-10-25 15:37:00', null, '2019-10-25 15:37:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('347613303102783488', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-10-25 15:38:00', '2019-10-25 15:38:00', '24', null, null, '2019-10-25 15:38:00', null, '2019-10-25 15:38:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('347613554731663360', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-10-25 15:39:00', '2019-10-25 15:39:00', '18', null, null, '2019-10-25 15:39:00', null, '2019-10-25 15:39:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('347613806389903360', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-10-25 15:40:00', '2019-10-25 15:40:00', '17', null, null, '2019-10-25 15:40:00', null, '2019-10-25 15:40:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('347614058064920576', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-10-25 15:41:00', '2019-10-25 15:41:00', '21', null, null, '2019-10-25 15:41:00', null, '2019-10-25 15:41:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('347614309731549184', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-10-25 15:42:00', '2019-10-25 15:42:00', '24', null, null, '2019-10-25 15:42:00', null, '2019-10-25 15:42:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('347614561402372096', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-10-25 15:43:00', '2019-10-25 15:43:00', '26', null, null, '2019-10-25 15:43:00', null, '2019-10-25 15:43:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('347614813043834880', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-10-25 15:44:00', '2019-10-25 15:44:00', '23', null, null, '2019-10-25 15:44:00', null, '2019-10-25 15:44:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('347615064710463488', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-10-25 15:45:00', '2019-10-25 15:45:00', '24', null, null, '2019-10-25 15:45:00', null, '2019-10-25 15:45:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('347615316351926272', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-10-25 15:46:00', '2019-10-25 15:46:00', '21', null, null, '2019-10-25 15:46:00', null, '2019-10-25 15:46:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('347615568001777664', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-10-25 15:47:00', '2019-10-25 15:47:00', '18', null, null, '2019-10-25 15:47:00', null, '2019-10-25 15:47:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('347615819668406272', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-10-25 15:48:00', '2019-10-25 15:48:00', '20', null, null, '2019-10-25 15:48:00', null, '2019-10-25 15:48:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('347616071330840576', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-10-25 15:49:00', '2019-10-25 15:49:00', '21', null, null, '2019-10-25 15:49:00', null, '2019-10-25 15:49:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('347616322984886272', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-10-25 15:50:00', '2019-10-25 15:50:00', '21', null, null, '2019-10-25 15:50:00', null, '2019-10-25 15:50:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('347616574643126272', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-10-25 15:51:00', '2019-10-25 15:51:00', '21', null, null, '2019-10-25 15:51:00', null, '2019-10-25 15:51:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('347616826297171968', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-10-25 15:52:00', '2019-10-25 15:52:00', '17', null, null, '2019-10-25 15:52:00', null, '2019-10-25 15:52:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('347617077984772096', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-10-25 15:53:00', '2019-10-25 15:53:00', '25', null, null, '2019-10-25 15:53:00', null, '2019-10-25 15:53:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('347617329609457664', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-10-25 15:54:00', '2019-10-25 15:54:00', '18', null, null, '2019-10-25 15:54:00', null, '2019-10-25 15:54:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('347617581276086272', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-10-25 15:55:00', '2019-10-25 15:55:00', '22', null, null, '2019-10-25 15:55:00', null, '2019-10-25 15:55:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('347617832930131968', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-10-25 15:56:00', '2019-10-25 15:56:00', '19', null, null, '2019-10-25 15:56:00', null, '2019-10-25 15:56:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('347618084638703616', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-10-25 15:57:00', '2019-10-25 15:57:00', '31', null, null, '2019-10-25 15:57:00', null, '2019-10-25 15:57:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('347618336242417664', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-10-25 15:58:00', '2019-10-25 15:58:00', '19', null, null, '2019-10-25 15:58:00', null, '2019-10-25 15:58:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('347618587913240576', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-10-25 15:59:00', '2019-10-25 15:59:00', '23', null, null, '2019-10-25 15:59:00', null, '2019-10-25 15:59:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('347618839554703360', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-10-25 16:00:00', '2019-10-25 16:00:00', '19', null, null, '2019-10-25 16:00:00', null, '2019-10-25 16:00:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('347619091271663616', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-10-25 16:01:00', '2019-10-25 16:01:00', '29', null, null, '2019-10-25 16:01:00', null, '2019-10-25 16:01:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('347619342875377664', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-10-25 16:02:00', '2019-10-25 16:02:00', '19', null, null, '2019-10-25 16:02:00', null, '2019-10-25 16:02:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('347619594575560704', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-10-25 16:03:00', '2019-10-25 16:03:00', '28', null, null, '2019-10-25 16:03:00', null, '2019-10-25 16:03:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('347619846204440576', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-10-25 16:04:00', '2019-10-25 16:04:00', '22', null, null, '2019-10-25 16:04:00', null, '2019-10-25 16:04:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('347620097921400832', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-10-25 16:05:00', '2019-10-25 16:05:00', '34', null, null, '2019-10-25 16:05:00', null, '2019-10-25 16:05:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('347620349512531968', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-10-25 16:06:00', '2019-10-25 16:06:00', '19', null, null, '2019-10-25 16:06:00', null, '2019-10-25 16:06:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('347620601166577664', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-10-25 16:07:00', '2019-10-25 16:07:00', '20', null, null, '2019-10-25 16:07:00', null, '2019-10-25 16:07:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('347620852812234752', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-10-25 16:08:00', '2019-10-25 16:08:00', '17', null, null, '2019-10-25 16:08:00', null, '2019-10-25 16:08:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('347621104545972224', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-10-25 16:09:00', '2019-10-25 16:09:00', '31', null, null, '2019-10-25 16:09:00', null, '2019-10-25 16:09:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('347621356200017920', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-10-25 16:10:00', '2019-10-25 16:10:00', '30', null, null, '2019-10-25 16:10:00', null, '2019-10-25 16:10:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('347621607854063616', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-10-25 16:11:00', '2019-10-25 16:11:00', '31', null, null, '2019-10-25 16:11:00', null, '2019-10-25 16:11:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('347621859436806144', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-10-25 16:12:00', '2019-10-25 16:12:00', '15', null, null, '2019-10-25 16:12:00', null, '2019-10-25 16:12:00', null, null);

-- ----------------------------
-- Table structure for sys_task_t
-- ----------------------------
DROP TABLE IF EXISTS `sys_task_t`;
CREATE TABLE `sys_task_t` (
  `ID` varchar(32) NOT NULL,
  `NAME` varchar(64) DEFAULT '' COMMENT '名称',
  `CRON` varchar(64) DEFAULT '' COMMENT 'cron表达式',
  `URL` varchar(255) DEFAULT '' COMMENT '请求路径',
  `STATE` varchar(2) DEFAULT '' COMMENT '状态',
  `PARAMS` varchar(255) DEFAULT '' COMMENT '参数',
  `REMARK` varchar(255) DEFAULT '' COMMENT '备注',
  `CREATED_BY` varchar(32) DEFAULT '' COMMENT '创建人',
  `CREATION_DATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `UPDATED_BY` varchar(32) DEFAULT '' COMMENT '修改人',
  `UPDATION_DATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `ENABLED_FLAG` int(1) DEFAULT '1' COMMENT '是否禁用',
  `TRACE_ID` varchar(16) DEFAULT '' COMMENT '日志ID',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='任务表';

-- ----------------------------
-- Records of sys_task_t
-- ----------------------------
INSERT INTO `sys_task_t` VALUES ('346902598779158528', 'job', '0 * * * * ?', 'http://localhost:8090/job/task/job', '1', '', '', '', '2019-10-23 16:33:55', '', '2019-10-23 16:33:55', '1', '');

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
INSERT INTO `sys_user_role_t` VALUES ('338122729266102272', '338122550211264512');
INSERT INTO `sys_user_role_t` VALUES ('338125654218522624', '338125469321019392');
INSERT INTO `sys_user_role_t` VALUES ('338126485835759616', '338125469321019392');
INSERT INTO `sys_user_role_t` VALUES ('338126770465423360', '338125469321019392');

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
  `BIZ_CODE` varchar(32) DEFAULT '' COMMENT '文件业务编码-头像',
  `BIZ_ID` varchar(32) DEFAULT '' COMMENT '文件业务ID-头像',
  `BIZ_HEAD_PATH` varchar(255) DEFAULT '' COMMENT '头像地址',
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
INSERT INTO `sys_user_t` VALUES ('303106967935619072', '佟丽娅', '0000000001', '1', '303106948964782080', '8FEF13C67B2F87E513492A861EDCA67089F3E7A6AB081B625C2DDA03', '0', '172.29.144.129', '2019-10-25 14:38:14', 'user_head', '1569654307169', '\\2a86008d264647d68d1d649bd54e2982\\8333410d68144493ac3342b4c9ba4f67.jpg', null, '2019-07-24 20:31:01', null, '2019-10-25 14:38:14', null, null);
INSERT INTO `sys_user_t` VALUES ('338122729266102272', '细个呢', '07555', '1', '338122550211264512', '6912D5ED812358C9FEE33E3AC047BA1F8454C912E462E619B5D5121E', '0', '172.29.144.129', '2019-09-29 11:15:40', '', '', '', '', '2019-09-29 11:05:51', '', '2019-09-29 11:15:40', '1', '');
INSERT INTO `sys_user_t` VALUES ('338125654218522624', 'idid', '123456', '1', '338125469321019392', '17488EA357A564C0E41BF10D85B597D6A056826FEB73598442C3D5FF', '0', '172.29.144.129', '2019-09-29 11:20:12', 'user_head', '1569727033682', '\\c495e634ab614045929b421f3839dcac\\5866e24e84064e36b984260b8b53da57.jpg', '', '2019-09-29 11:17:28', '', '2019-09-29 11:20:12', '1', '');
INSERT INTO `sys_user_t` VALUES ('338126485835759616', '1', '1', '1', '338125469321019392', '066CF4409F5B0D3755BAEB373D075561D0E49C181042477A92D2F069', '0', '172.29.144.129', '2019-09-29 11:21:02', '', '', '', '', '2019-09-29 11:20:47', '', '2019-09-29 11:21:02', '1', '');
INSERT INTO `sys_user_t` VALUES ('338126770465423360', '2', '2', '1', '338125469321019392', '1EA3944F7842D223AC37EC533EA5ADC45751941B41107409D1813E26', '0', '172.29.144.129', '2019-09-29 11:22:27', '', '', '', '', '2019-09-29 11:21:54', '', '2019-09-29 11:22:27', '1', '');
