/*
Navicat MySQL Data Transfer

Source Server         : localhost-garden
Source Server Version : 50717
Source Host           : localhost:3306
Source Database       : garden

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2019-12-29 16:05:38
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
-- Table structure for sys_excel_config_t
-- ----------------------------
DROP TABLE IF EXISTS `sys_excel_config_t`;
CREATE TABLE `sys_excel_config_t` (
  `ID` varchar(32) NOT NULL COMMENT 'ID',
  `CODE` varchar(32) DEFAULT '' COMMENT '编码',
  `NAME` varchar(32) DEFAULT '' COMMENT '名称',
  `IMPORT_CODE` varchar(32) DEFAULT '' COMMENT '导入文件编码',
  `IMPORT_ID` varchar(32) DEFAULT '' COMMENT '导入文件ID',
  `IMPORT_NAME` varchar(128) DEFAULT '' COMMENT '导入文件名称',
  `EXPORT_CODE` varchar(32) DEFAULT '' COMMENT '导出文件编码',
  `EXPORT_ID` varchar(32) DEFAULT '' COMMENT '导出文件ID',
  `EXPORT_NAME` varchar(128) DEFAULT '' COMMENT '导出文件名称',
  `STATE` varchar(2) DEFAULT '' COMMENT '状态',
  `REMARK` varchar(255) DEFAULT '' COMMENT '备注',
  `CREATED_BY` varchar(32) DEFAULT '' COMMENT '创建人',
  `CREATION_DATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `UPDATED_BY` varchar(32) DEFAULT '' COMMENT '修改人',
  `UPDATION_DATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `ENABLED_FLAG` int(1) DEFAULT '1' COMMENT '是否禁用',
  `TRACE_ID` varchar(16) DEFAULT '' COMMENT '日志ID',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='导入导出配置表';

-- ----------------------------
-- Records of sys_excel_config_t
-- ----------------------------
INSERT INTO `sys_excel_config_t` VALUES ('1', 'test', 'test', 'test1', '1', 'test', '', '', '', '', '', '', '2019-11-09 14:38:14', '', '2019-11-09 14:38:14', '1', '');

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
  `CONTENT_TYPE` varchar(128) DEFAULT '' COMMENT '文件头类型',
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
INSERT INTO `sys_file_info_t` VALUES ('1f00aa0534574dcb9711e56765d98289', '353032500838416384', '1573548377947', 'test', null, '订单导入模板 (2).xlsx', 'xlsx', '订单导入模板 (2)', '/www/garden\\c9a6d2470c9a42f481718e3010af0677\\1f00aa0534574dcb9711e56765d98289.xlsx', '\\c9a6d2470c9a42f481718e3010af0677\\1f00aa0534574dcb9711e56765d98289.xlsx', '142205', 'application/vnd.openxmlformats-officedocument.spreadsheetml.sheet', null, '2019-11-12 16:46:21', null, '2019-11-12 16:46:21', null, null);
INSERT INTO `sys_file_info_t` VALUES ('1ffb948c733349abb4395d50a4a148df', '337725915249524736', '1569653504968', 'user_head', null, 'timg.jpg', 'jpg', 'timg', '/www/garden\\3b711638c1e741819506ef1d3ddeca2b\\1ffb948c733349abb4395d50a4a148df.jpg', '\\3b711638c1e741819506ef1d3ddeca2b\\1ffb948c733349abb4395d50a4a148df.jpg', '25866', 'image/jpeg', null, '2019-09-28 14:51:49', null, '2019-09-28 14:51:49', null, null);
INSERT INTO `sys_file_info_t` VALUES ('40d13a27f47d41909e0809835dadc46e', '337725915249524736', '1569652871160', 'user_head', null, '微信图片_20190925141416.jpg', 'jpg', '微信图片_20190925141416', '/www/garden\\5e28883b5e804c189f8b32bcd8e0d7f1\\40d13a27f47d41909e0809835dadc46e.jpg', '\\5e28883b5e804c189f8b32bcd8e0d7f1\\40d13a27f47d41909e0809835dadc46e.jpg', '43924', 'image/jpeg', null, '2019-09-28 14:41:22', null, '2019-09-28 14:41:22', null, null);
INSERT INTO `sys_file_info_t` VALUES ('51fd9586728c4a8288c3120f095e4ae0', '353032500838416384', '1573548271116', 'test', null, '订单导入模板 (2).xlsx', 'xlsx', '订单导入模板 (2)', '/www/garden\\8a37a7f6924c4f8dbf809a76397f3c1e\\51fd9586728c4a8288c3120f095e4ae0.xlsx', '\\8a37a7f6924c4f8dbf809a76397f3c1e\\51fd9586728c4a8288c3120f095e4ae0.xlsx', '142205', 'application/vnd.openxmlformats-officedocument.spreadsheetml.sheet', null, '2019-11-12 16:44:39', null, '2019-11-12 16:44:39', null, null);
INSERT INTO `sys_file_info_t` VALUES ('5866e24e84064e36b984260b8b53da57', '337725915249524736', '1569727033682', 'user_head', null, '微信图片_20190925141416.jpg', 'jpg', '微信图片_20190925141416', '/www/garden\\c495e634ab614045929b421f3839dcac\\5866e24e84064e36b984260b8b53da57.jpg', '\\c495e634ab614045929b421f3839dcac\\5866e24e84064e36b984260b8b53da57.jpg', '39040', 'image/jpeg', null, '2019-09-29 11:17:24', null, '2019-09-29 11:17:24', null, null);
INSERT INTO `sys_file_info_t` VALUES ('5dabf8efb98f4f3dae536f971075171a', '337725915249524736', '1569654019392', 'user_head', null, '20190316225645695.gif', 'gif', '20190316225645695', '/www/garden\\71b6f9a6d5e8497bb8c11925efa6f086\\5dabf8efb98f4f3dae536f971075171a.gif', '\\71b6f9a6d5e8497bb8c11925efa6f086\\5dabf8efb98f4f3dae536f971075171a.gif', '5503', 'image/jpeg', null, '2019-09-28 15:00:24', null, '2019-09-28 15:00:24', null, null);
INSERT INTO `sys_file_info_t` VALUES ('67ac3d8f1028446c896fa6749d1cfbdd', '353032500838416384', '1', 'test1', null, 'importI18nData.xml', 'xml', 'importI18nData', '/www/garden\\47ce95f7abe546d2a3367425e1231a84\\67ac3d8f1028446c896fa6749d1cfbdd.xml', '\\47ce95f7abe546d2a3367425e1231a84\\67ac3d8f1028446c896fa6749d1cfbdd.xml', '800', 'application/xml', null, '2019-11-09 14:36:30', null, '2019-11-09 14:36:30', null, null);
INSERT INTO `sys_file_info_t` VALUES ('7e5a50dd45d6486a9d3f6f184f3217ad', '337725915249524736', '1569652785376', 'user_head', null, 'timg.jpg', 'jpg', 'timg', '/www/garden\\5e2717d5aab147b5988e15e300099921\\7e5a50dd45d6486a9d3f6f184f3217ad.jpg', '\\5e2717d5aab147b5988e15e300099921\\7e5a50dd45d6486a9d3f6f184f3217ad.jpg', '36006', 'image/jpeg', null, '2019-09-28 14:39:54', null, '2019-09-28 14:39:54', null, null);
INSERT INTO `sys_file_info_t` VALUES ('8333410d68144493ac3342b4c9ba4f67', '337725915249524736', '1569654307169', 'user_head', null, 'timg.jpg', 'jpg', 'timg', '/www/garden\\2a86008d264647d68d1d649bd54e2982\\8333410d68144493ac3342b4c9ba4f67.jpg', '\\2a86008d264647d68d1d649bd54e2982\\8333410d68144493ac3342b4c9ba4f67.jpg', '17045', 'image/jpeg', null, '2019-09-28 15:05:15', null, '2019-09-28 15:05:15', null, null);
INSERT INTO `sys_file_info_t` VALUES ('835148d67b694b1784b0ea09252cff25', '337725915249524736', '1569653462232', 'user_head', null, 'MyJsonView.png', 'png', 'MyJsonView', '/www/garden\\6d90568cc4b8406a9d3c6e257bee8007\\835148d67b694b1784b0ea09252cff25.png', '\\6d90568cc4b8406a9d3c6e257bee8007\\835148d67b694b1784b0ea09252cff25.png', '52751', 'image/jpeg', null, '2019-09-28 14:51:07', null, '2019-09-28 14:51:07', null, null);
INSERT INTO `sys_file_info_t` VALUES ('a327c908cbc84132a0d821bd1d52d35b', '337725915249524736', '1569631748416', 'user_head', null, 'timg.jpg', 'jpg', 'timg', '/www/garden\\353ae93f3e0545c9ac785fb5cee48809\\a327c908cbc84132a0d821bd1d52d35b.jpg', '\\353ae93f3e0545c9ac785fb5cee48809\\a327c908cbc84132a0d821bd1d52d35b.jpg', '33061', 'image/jpeg', null, '2019-09-28 08:49:17', null, '2019-09-28 08:49:17', null, null);
INSERT INTO `sys_file_info_t` VALUES ('a89096c3473a472a9fa97e7defc2d1d3', '337725915249524736', '1569653415440', 'user_head', null, 'timg.jpg', 'jpg', 'timg', '/www/garden\\0da06a4e33c942a4a365e7b1b4fcc597\\a89096c3473a472a9fa97e7defc2d1d3.jpg', '\\0da06a4e33c942a4a365e7b1b4fcc597\\a89096c3473a472a9fa97e7defc2d1d3.jpg', '25866', 'image/jpeg', null, '2019-09-28 14:50:19', null, '2019-09-28 14:50:19', null, null);
INSERT INTO `sys_file_info_t` VALUES ('abe53ef4670943dfbdd4f8952a9e1e26', '337725915249524736', '1569652686168', 'user_head', null, '微信图片_20190925141416.jpg', 'jpg', '微信图片_20190925141416', '/www/garden\\b2cda4685fc748a093487c8fc6751a79\\abe53ef4670943dfbdd4f8952a9e1e26.jpg', '\\b2cda4685fc748a093487c8fc6751a79\\abe53ef4670943dfbdd4f8952a9e1e26.jpg', '56858', 'image/jpeg', null, '2019-09-28 14:38:19', null, '2019-09-28 14:38:19', null, null);
INSERT INTO `sys_file_info_t` VALUES ('bf2551b690ae4ff29536853af52fb9e3', '353032500838416384', '1573548440357', 'test', null, '订单导入模板 (2).xlsx', 'xlsx', '订单导入模板 (2)', '/www/garden\\f7716c180e4c453d9fdf93d3686189d5\\bf2551b690ae4ff29536853af52fb9e3.xlsx', '\\f7716c180e4c453d9fdf93d3686189d5\\bf2551b690ae4ff29536853af52fb9e3.xlsx', '142205', 'application/vnd.openxmlformats-officedocument.spreadsheetml.sheet', null, '2019-11-12 16:47:24', null, '2019-11-12 16:47:24', null, null);
INSERT INTO `sys_file_info_t` VALUES ('d37f7a96fbe745b8a8f985c5897d6334', '353032500838416384', '2', 'test2', null, '新建 XLSX 工作表.xlsx', 'xlsx', '新建 XLSX 工作表', '/www/garden\\0eb903d71009406db7b1d003ccbe2ed8\\d37f7a96fbe745b8a8f985c5897d6334.xlsx', '\\0eb903d71009406db7b1d003ccbe2ed8\\d37f7a96fbe745b8a8f985c5897d6334.xlsx', '10152', 'application/vnd.openxmlformats-officedocument.spreadsheetml.sheet', null, '2019-11-09 14:41:02', null, '2019-11-09 14:41:02', null, null);
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
INSERT INTO `sys_file_manage_t` VALUES ('353032500838416384', 'test', 'test', '1', '', null, '2019-11-09 14:31:57', null, '2019-11-09 14:31:57', null, null);

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
INSERT INTO `sys_i18n_t` VALUES ('347600639295242240', 'message.i18n.code', '编码', 'zh', '1', '', null, '2019-11-01 16:26:18', null, '2019-11-01 16:26:18', null, null);
INSERT INTO `sys_i18n_t` VALUES ('347601566848794624', 'message.i18n.code', 'Code', 'en', '1', '', null, '2019-10-25 14:51:22', null, '2019-10-25 14:51:22', null, null);
INSERT INTO `sys_i18n_t` VALUES ('350162257124278272', 'message.i18n.state', '状态', 'zh', '1', '', null, '2019-11-01 16:26:38', null, '2019-11-01 16:26:38', null, null);
INSERT INTO `sys_i18n_t` VALUES ('350162308735188992', 'message.i18n.state', 'State', 'en', '1', '', null, '2019-11-01 16:26:56', null, '2019-11-01 16:26:56', null, null);

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
INSERT INTO `sys_lookup_item_t` VALUES ('350173370113081344', '中文', 'zh', '1', 'LANGUAGE_TYPE', '', '', '', '', '', '', '2019-11-01 17:10:48', '', '2019-11-01 17:10:48', '1', '');
INSERT INTO `sys_lookup_item_t` VALUES ('350173393383079936', '英文', 'en', '1', 'LANGUAGE_TYPE', '', '', '', '', '', '', '2019-11-01 17:10:53', '', '2019-11-01 17:10:53', '1', '');

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
INSERT INTO `sys_lookup_t` VALUES ('334519396320165888', '语言类型', 'LANGUAGE_TYPE', '1', null, '2019-09-20 14:40:34', null, '2019-11-01 17:10:30', null, null);
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
INSERT INTO `sys_menu_t` VALUES ('306008072466173952', '菜单管理', '', '1', 'system/menu/toList', '10', '', '306007560043859968', '', '', '', '2019-07-02 20:13:40', '', '2019-12-26 14:27:33', '1', '');
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
INSERT INTO `sys_menu_t` VALUES ('351280599151165440', '导入导出管理', '', '1', 'excel/config/toList', '60', '', '306009670156259328', '', '', '', '2019-11-04 18:30:32', '', '2019-11-04 18:30:32', '1', '');

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
INSERT INTO `sys_role_function_t` VALUES ('303106948964782080', '312554985592164352', '2');
INSERT INTO `sys_role_function_t` VALUES ('303106948964782080', '312557438165291008', '2');
INSERT INTO `sys_role_function_t` VALUES ('303106948964782080', '312557544130187264', '2');
INSERT INTO `sys_role_function_t` VALUES ('303106948964782080', '312557853078425600', '2');
INSERT INTO `sys_role_function_t` VALUES ('303106948964782080', '338169446418104320', '2');
INSERT INTO `sys_role_function_t` VALUES ('303106948964782080', '338169554983469056', '2');
INSERT INTO `sys_role_function_t` VALUES ('303106948964782080', '338170065937776640', '2');
INSERT INTO `sys_role_function_t` VALUES ('303106948964782080', '338170112028983296', '2');
INSERT INTO `sys_role_function_t` VALUES ('338125469321019392', '312554985592164352', '2');
INSERT INTO `sys_role_function_t` VALUES ('338125469321019392', '312557438165291008', '2');
INSERT INTO `sys_role_function_t` VALUES ('338125469321019392', '312557544130187264', '2');

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
INSERT INTO `sys_role_menu_t` VALUES ('303106948964782080', '351280599151165440');
INSERT INTO `sys_role_menu_t` VALUES ('338125469321019392', '306007560043859968');
INSERT INTO `sys_role_menu_t` VALUES ('338125469321019392', '306008072466173952');

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
INSERT INTO `sys_role_t` VALUES ('338122550211264512', '管理员', '0175', '1', null, '2019-11-11 15:41:46', null, '2019-11-11 15:41:47', null, null);
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
INSERT INTO `sys_task_log_t` VALUES ('350159331018424320', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-11-01 16:15:00', '2019-11-01 16:15:00', '296', null, null, '2019-11-01 16:15:00', null, '2019-11-01 16:15:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('350159581225435136', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-11-01 16:16:00', '2019-11-01 16:16:00', '32', null, null, '2019-11-01 16:16:00', null, '2019-11-01 16:16:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('350159832833343488', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-11-01 16:17:00', '2019-11-01 16:17:00', '26', null, null, '2019-11-01 16:17:00', null, '2019-11-01 16:17:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('350160084504166400', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-11-01 16:18:00', '2019-11-01 16:18:00', '26', null, null, '2019-11-01 16:18:00', null, '2019-11-01 16:18:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('350160336174989312', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-11-01 16:19:00', '2019-11-01 16:19:00', '30', null, null, '2019-11-01 16:19:00', null, '2019-11-01 16:19:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('350160587799674880', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-11-01 16:20:00', '2019-11-01 16:20:00', '19', null, null, '2019-11-01 16:20:00', null, '2019-11-01 16:20:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('350160839411777536', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-11-01 16:21:00', '2019-11-01 16:21:00', '13', null, null, '2019-11-01 16:21:00', null, '2019-11-01 16:21:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('350161091145515008', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-11-01 16:22:00', '2019-11-01 16:22:00', '26', null, null, '2019-11-01 16:22:00', null, '2019-11-01 16:22:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('350161595829338112', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-11-01 16:24:00', '2019-11-01 16:24:00', '296', null, null, '2019-11-01 16:24:00', null, '2019-11-01 16:24:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('350161846086680576', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-11-01 16:25:00', '2019-11-01 16:25:00', '20', null, null, '2019-11-01 16:25:00', null, '2019-11-01 16:25:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('350162130254970880', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-11-01 16:26:04', '2019-11-01 16:26:08', '4259', null, null, '2019-11-01 16:26:08', null, '2019-11-01 16:26:08', null, null);
INSERT INTO `sys_task_log_t` VALUES ('350162349491240960', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-11-01 16:27:00', '2019-11-01 16:27:00', '20', null, null, '2019-11-01 16:27:00', null, '2019-11-01 16:27:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('350162601107537920', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-11-01 16:28:00', '2019-11-01 16:28:00', '29', null, null, '2019-11-01 16:28:00', null, '2019-11-01 16:28:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('350162852782555136', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-11-01 16:29:00', '2019-11-01 16:29:00', '35', null, null, '2019-11-01 16:29:00', null, '2019-11-01 16:29:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('350163104415629312', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-11-01 16:30:00', '2019-11-01 16:30:00', '27', null, null, '2019-11-01 16:30:00', null, '2019-11-01 16:30:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('350163356073869312', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-11-01 16:31:00', '2019-11-01 16:31:00', '28', null, null, '2019-11-01 16:31:00', null, '2019-11-01 16:31:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('350163607732109312', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-11-01 16:32:00', '2019-11-01 16:32:00', '29', null, null, '2019-11-01 16:32:00', null, '2019-11-01 16:32:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('350163859407126528', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-11-01 16:33:00', '2019-11-01 16:33:00', '34', null, null, '2019-11-01 16:33:00', null, '2019-11-01 16:33:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('350164110994063360', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-11-01 16:34:00', '2019-11-01 16:34:00', '17', null, null, '2019-11-01 16:34:00', null, '2019-11-01 16:34:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('350164362711023616', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-11-01 16:35:00', '2019-11-01 16:35:00', '29', null, null, '2019-11-01 16:35:00', null, '2019-11-01 16:35:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('350164614369263616', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-11-01 16:36:00', '2019-11-01 16:36:00', '29', null, null, '2019-11-01 16:36:00', null, '2019-11-01 16:36:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('350164865939423232', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-11-01 16:37:00', '2019-11-01 16:37:00', '11', null, null, '2019-11-01 16:37:00', null, '2019-11-01 16:37:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('350165117668966400', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-11-01 16:38:00', '2019-11-01 16:38:00', '26', null, null, '2019-11-01 16:38:00', null, '2019-11-01 16:38:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('350165369255903232', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-11-01 16:39:00', '2019-11-01 16:39:00', '12', null, null, '2019-11-01 16:39:00', null, '2019-11-01 16:39:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('350165620935114752', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-11-01 16:40:00', '2019-11-01 16:40:00', '16', null, null, '2019-11-01 16:40:00', null, '2019-11-01 16:40:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('350165872584966144', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-11-01 16:41:00', '2019-11-01 16:41:00', '14', null, null, '2019-11-01 16:41:00', null, '2019-11-01 16:41:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('350166124322897920', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-11-01 16:42:00', '2019-11-01 16:42:00', '31', null, null, '2019-11-01 16:42:00', null, '2019-11-01 16:42:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('350166375926611968', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-11-01 16:43:00', '2019-11-01 16:43:00', '21', null, null, '2019-11-01 16:43:00', null, '2019-11-01 16:43:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('350166627630989312', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-11-01 16:44:00', '2019-11-01 16:44:00', '29', null, null, '2019-11-01 16:44:00', null, '2019-11-01 16:44:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('350166879213731840', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-11-01 16:45:00', '2019-11-01 16:45:00', '14', null, null, '2019-11-01 16:45:00', null, '2019-11-01 16:45:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('350167130876166144', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-11-01 16:46:00', '2019-11-01 16:46:00', '15', null, null, '2019-11-01 16:46:00', null, '2019-11-01 16:46:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('350167382521823232', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-11-01 16:47:00', '2019-11-01 16:47:00', '12', null, null, '2019-11-01 16:47:00', null, '2019-11-01 16:47:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('350167634209423360', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-11-01 16:48:00', '2019-11-01 16:48:00', '18', null, null, '2019-11-01 16:48:00', null, '2019-11-01 16:48:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('350167885917995008', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-11-01 16:49:00', '2019-11-01 16:49:00', '28', null, null, '2019-11-01 16:49:00', null, '2019-11-01 16:49:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('350168137613983744', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-11-01 16:50:00', '2019-11-01 16:50:00', '35', null, null, '2019-11-01 16:50:00', null, '2019-11-01 16:50:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('350168389167366144', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-11-01 16:51:00', '2019-11-01 16:51:00', '16', null, null, '2019-11-01 16:51:00', null, '2019-11-01 16:51:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('350168640896909312', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-11-01 16:52:00', '2019-11-01 16:52:00', '29', null, null, '2019-11-01 16:52:00', null, '2019-11-01 16:52:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('350168892462874624', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-11-01 16:53:00', '2019-11-01 16:53:00', '12', null, null, '2019-11-01 16:53:00', null, '2019-11-01 16:53:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('350169144209195008', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-11-01 16:54:00', '2019-11-01 16:54:00', '29', null, null, '2019-11-01 16:54:00', null, '2019-11-01 16:54:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('350169395808714752', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-11-01 16:55:00', '2019-11-01 16:55:00', '14', null, null, '2019-11-01 16:55:00', null, '2019-11-01 16:55:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('350169647479537664', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-11-01 16:56:00', '2019-11-01 16:56:00', '19', null, null, '2019-11-01 16:56:00', null, '2019-11-01 16:56:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('350169899271995392', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-11-01 16:57:00', '2019-11-01 16:57:00', '49', null, null, '2019-11-01 16:57:00', null, '2019-11-01 16:57:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('350170150842155008', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-11-01 16:58:00', '2019-11-01 16:58:00', '29', null, null, '2019-11-01 16:58:00', null, '2019-11-01 16:58:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('350170415339159552', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-11-01 16:59:00', '2019-11-01 16:59:03', '3050', null, null, '2019-11-01 16:59:03', null, '2019-11-01 16:59:03', null, null);
INSERT INTO `sys_task_log_t` VALUES ('350170654120886272', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-11-01 17:00:00', '2019-11-01 17:00:00', '20', null, null, '2019-11-01 17:00:00', null, '2019-11-01 17:00:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('350170905833652224', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-11-01 17:01:00', '2019-11-01 17:01:00', '32', null, null, '2019-11-01 17:01:00', null, '2019-11-01 17:01:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('350171157428977664', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-11-01 17:02:00', '2019-11-01 17:02:00', '20', null, null, '2019-11-01 17:02:00', null, '2019-11-01 17:02:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('350171409150132224', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-11-01 17:03:00', '2019-11-01 17:03:00', '31', null, null, '2019-11-01 17:03:00', null, '2019-11-01 17:03:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('350171660795789312', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-11-01 17:04:00', '2019-11-01 17:04:00', '29', null, null, '2019-11-01 17:04:00', null, '2019-11-01 17:04:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('350171912374337536', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-11-01 17:05:00', '2019-11-01 17:05:00', '12', null, null, '2019-11-01 17:05:00', null, '2019-11-01 17:05:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('350172164036771840', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-11-01 17:06:00', '2019-11-01 17:06:00', '12', null, null, '2019-11-01 17:06:00', null, '2019-11-01 17:06:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('350172415686623232', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-11-01 17:07:00', '2019-11-01 17:07:00', '11', null, null, '2019-11-01 17:07:00', null, '2019-11-01 17:07:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('350172667344863232', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-11-01 17:08:00', '2019-11-01 17:08:00', '10', null, null, '2019-11-01 17:08:00', null, '2019-11-01 17:08:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('350172919015686144', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-11-01 17:09:00', '2019-11-01 17:09:00', '14', null, null, '2019-11-01 17:09:00', null, '2019-11-01 17:09:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('350173170749423616', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-11-01 17:10:00', '2019-11-01 17:10:00', '29', null, null, '2019-11-01 17:10:00', null, '2019-11-01 17:10:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('350173422336360448', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-11-01 17:11:00', '2019-11-01 17:11:00', '15', null, null, '2019-11-01 17:11:00', null, '2019-11-01 17:11:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('350173674057515008', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-11-01 17:12:00', '2019-11-01 17:12:00', '27', null, null, '2019-11-01 17:12:00', null, '2019-11-01 17:12:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('350173925665423360', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-11-01 17:13:00', '2019-11-01 17:13:00', '18', null, null, '2019-11-01 17:13:00', null, '2019-11-01 17:13:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('350174177340440576', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-11-01 17:14:00', '2019-11-01 17:14:00', '20', null, null, '2019-11-01 17:14:00', null, '2019-11-01 17:14:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('350174429032235008', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-11-01 17:15:00', '2019-11-01 17:15:00', '28', null, null, '2019-11-01 17:15:00', null, '2019-11-01 17:15:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('350174680711446528', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-11-01 17:16:00', '2019-11-01 17:16:00', '32', null, null, '2019-11-01 17:16:00', null, '2019-11-01 17:16:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('350174932260634624', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-11-01 17:17:00', '2019-11-01 17:17:00', '10', null, null, '2019-11-01 17:17:00', null, '2019-11-01 17:17:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('350175184019537920', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-11-01 17:18:00', '2019-11-01 17:18:00', '29', null, null, '2019-11-01 17:18:00', null, '2019-11-01 17:18:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('350175435681972224', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-11-01 17:19:00', '2019-11-01 17:19:00', '32', null, null, '2019-11-01 17:19:00', null, '2019-11-01 17:19:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('350175687319240704', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-11-01 17:20:00', '2019-11-01 17:20:00', '29', null, null, '2019-11-01 17:20:00', null, '2019-11-01 17:20:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('350175938985869312', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-11-01 17:21:00', '2019-11-01 17:21:00', '28', null, null, '2019-11-01 17:21:00', null, '2019-11-01 17:21:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('350176190635720704', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-11-01 17:22:00', '2019-11-01 17:22:00', '29', null, null, '2019-11-01 17:22:00', null, '2019-11-01 17:22:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('350176442205880320', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-11-01 17:23:00', '2019-11-01 17:23:00', '10', null, null, '2019-11-01 17:23:00', null, '2019-11-01 17:23:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('350176693943812096', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-11-01 17:24:00', '2019-11-01 17:24:00', '26', null, null, '2019-11-01 17:24:00', null, '2019-11-01 17:24:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('350176945627217920', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-11-01 17:25:00', '2019-11-01 17:25:00', '31', null, null, '2019-11-01 17:25:00', null, '2019-11-01 17:25:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('350177197264486400', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-11-01 17:26:00', '2019-11-01 17:26:00', '27', null, null, '2019-11-01 17:26:00', null, '2019-11-01 17:26:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('350177448956280832', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-11-01 17:27:00', '2019-11-01 17:27:00', '34', null, null, '2019-11-01 17:27:00', null, '2019-11-01 17:27:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('350177700589355008', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-11-01 17:28:00', '2019-11-01 17:28:00', '29', null, null, '2019-11-01 17:28:00', null, '2019-11-01 17:28:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('350177952214040576', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-11-01 17:29:00', '2019-11-01 17:29:00', '22', null, null, '2019-11-01 17:29:00', null, '2019-11-01 17:29:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('350178203876474880', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-11-01 17:30:00', '2019-11-01 17:30:00', '22', null, null, '2019-11-01 17:30:00', null, '2019-11-01 17:30:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('350178455564075008', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-11-01 17:31:00', '2019-11-01 17:31:00', '29', null, null, '2019-11-01 17:31:00', null, '2019-11-01 17:31:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('350178707243286528', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-11-01 17:32:00', '2019-11-01 17:32:00', '32', null, null, '2019-11-01 17:32:00', null, '2019-11-01 17:32:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('350178958897332224', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-11-01 17:33:00', '2019-11-01 17:33:00', '31', null, null, '2019-11-01 17:33:00', null, '2019-11-01 17:33:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('350179210454908928', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-11-01 17:34:00', '2019-11-01 17:34:00', '10', null, null, '2019-11-01 17:34:00', null, '2019-11-01 17:34:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('350179462129926144', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-11-01 17:35:00', '2019-11-01 17:35:00', '14', null, null, '2019-11-01 17:35:00', null, '2019-11-01 17:35:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('350179713796554752', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-11-01 17:36:00', '2019-11-01 17:36:00', '17', null, null, '2019-11-01 17:36:00', null, '2019-11-01 17:36:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('350179965446406144', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-11-01 17:37:00', '2019-11-01 17:37:00', '12', null, null, '2019-11-01 17:37:00', null, '2019-11-01 17:37:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('350180217154977792', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-11-01 17:38:00', '2019-11-01 17:38:00', '21', null, null, '2019-11-01 17:38:00', null, '2019-11-01 17:38:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('350180468817412096', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-11-01 17:39:00', '2019-11-01 17:39:00', '26', null, null, '2019-11-01 17:39:00', null, '2019-11-01 17:39:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('350180720496623616', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-11-01 17:40:00', '2019-11-01 17:40:00', '30', null, null, '2019-11-01 17:40:00', null, '2019-11-01 17:40:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('350180972117114880', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-11-01 17:41:00', '2019-11-01 17:41:00', '22', null, null, '2019-11-01 17:41:00', null, '2019-11-01 17:41:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('350181223725023232', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-11-01 17:42:00', '2019-11-01 17:42:00', '11', null, null, '2019-11-01 17:42:00', null, '2019-11-01 17:42:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('350181475425206272', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-11-01 17:43:00', '2019-11-01 17:43:00', '22', null, null, '2019-11-01 17:43:00', null, '2019-11-01 17:43:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('350181727125389312', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-11-01 17:44:00', '2019-11-01 17:44:00', '28', null, null, '2019-11-01 17:44:00', null, '2019-11-01 17:44:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('350181978716520448', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-11-01 17:45:00', '2019-11-01 17:45:00', '13', null, null, '2019-11-01 17:45:00', null, '2019-11-01 17:45:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('350182230441869312', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-11-01 17:46:00', '2019-11-01 17:46:00', '29', null, null, '2019-11-01 17:46:00', null, '2019-11-01 17:46:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('350182482112692224', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-11-01 17:47:00', '2019-11-01 17:47:00', '31', null, null, '2019-11-01 17:47:00', null, '2019-11-01 17:47:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('350182733770932224', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-11-01 17:48:00', '2019-11-01 17:48:00', '31', null, null, '2019-11-01 17:48:00', null, '2019-11-01 17:48:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('350182985424977920', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-11-01 17:49:00', '2019-11-01 17:49:00', '30', null, null, '2019-11-01 17:49:00', null, '2019-11-01 17:49:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('350183237007720448', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-11-01 17:50:00', '2019-11-01 17:50:00', '15', null, null, '2019-11-01 17:50:00', null, '2019-11-01 17:50:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('350183488724680704', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-11-01 17:51:00', '2019-11-01 17:51:00', '28', null, null, '2019-11-01 17:51:00', null, '2019-11-01 17:51:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('350183740324200448', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-11-01 17:52:00', '2019-11-01 17:52:00', '15', null, null, '2019-11-01 17:52:00', null, '2019-11-01 17:52:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('350183992036966400', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-11-01 17:53:00', '2019-11-01 17:53:00', '26', null, null, '2019-11-01 17:53:00', null, '2019-11-01 17:53:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('350184243615514624', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-11-01 17:54:00', '2019-11-01 17:54:00', '9', null, null, '2019-11-01 17:54:00', null, '2019-11-01 17:54:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('350184495345057792', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-11-01 17:55:00', '2019-11-01 17:55:00', '25', null, null, '2019-11-01 17:55:00', null, '2019-11-01 17:55:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('350185529027084288', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-11-01 17:59:06', '2019-11-01 17:59:06', '314', null, null, '2019-11-01 17:59:06', null, '2019-11-01 17:59:06', null, null);
INSERT INTO `sys_task_log_t` VALUES ('350185753581731840', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-11-01 18:00:00', '2019-11-01 18:00:00', '13', null, null, '2019-11-01 18:00:00', null, '2019-11-01 18:00:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('350186005294497792', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-11-01 18:01:00', '2019-11-01 18:01:00', '25', null, null, '2019-11-01 18:01:00', null, '2019-11-01 18:01:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('350186256944349184', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-11-01 18:02:00', '2019-11-01 18:02:00', '23', null, null, '2019-11-01 18:02:00', null, '2019-11-01 18:02:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('350186508665503744', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-11-01 18:03:00', '2019-11-01 18:03:00', '37', null, null, '2019-11-01 18:03:00', null, '2019-11-01 18:03:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('350186760294383616', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-11-01 18:04:00', '2019-11-01 18:04:00', '29', null, null, '2019-11-01 18:04:00', null, '2019-11-01 18:04:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('350187011940040704', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-11-01 18:05:00', '2019-11-01 18:05:00', '27', null, null, '2019-11-01 18:05:00', null, '2019-11-01 18:05:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('350187263615057920', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-11-01 18:06:00', '2019-11-01 18:06:00', '30', null, null, '2019-11-01 18:06:00', null, '2019-11-01 18:06:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('350187515260715008', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-11-01 18:07:00', '2019-11-01 18:07:00', '29', null, null, '2019-11-01 18:07:00', null, '2019-11-01 18:07:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('350187766923149312', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-11-01 18:08:00', '2019-11-01 18:08:00', '28', null, null, '2019-11-01 18:08:00', null, '2019-11-01 18:08:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('350188018564612096', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-11-01 18:09:00', '2019-11-01 18:09:00', '26', null, null, '2019-11-01 18:09:00', null, '2019-11-01 18:09:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('350188270256406528', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-11-01 18:10:00', '2019-11-01 18:10:00', '32', null, null, '2019-11-01 18:10:00', null, '2019-11-01 18:10:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('350188521906257920', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-11-01 18:11:00', '2019-11-01 18:11:00', '32', null, null, '2019-11-01 18:11:00', null, '2019-11-01 18:11:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('350188773543526400', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-11-01 18:12:00', '2019-11-01 18:12:00', '27', null, null, '2019-11-01 18:12:00', null, '2019-11-01 18:12:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('350189025180794880', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-11-01 18:13:00', '2019-11-01 18:13:00', '22', null, null, '2019-11-01 18:13:00', null, '2019-11-01 18:13:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('350189276901949440', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-11-01 18:14:00', '2019-11-01 18:14:00', '34', null, null, '2019-11-01 18:14:00', null, '2019-11-01 18:14:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('350189528522440704', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-11-01 18:15:00', '2019-11-01 18:15:00', '28', null, null, '2019-11-01 18:15:00', null, '2019-11-01 18:15:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('350189780176486400', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-11-01 18:16:00', '2019-11-01 18:16:00', '27', null, null, '2019-11-01 18:16:00', null, '2019-11-01 18:16:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('350190031847309312', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-11-01 18:17:00', '2019-11-01 18:17:00', '27', null, null, '2019-11-01 18:17:00', null, '2019-11-01 18:17:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('350190283509743616', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-11-01 18:18:00', '2019-11-01 18:18:00', '32', null, null, '2019-11-01 18:18:00', null, '2019-11-01 18:18:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('350190535163789312', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-11-01 18:19:00', '2019-11-01 18:19:00', '28', null, null, '2019-11-01 18:19:00', null, '2019-11-01 18:19:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('350190786788474880', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-11-01 18:20:00', '2019-11-01 18:20:00', '23', null, null, '2019-11-01 18:20:00', null, '2019-11-01 18:20:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('350191038476075008', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-11-01 18:21:00', '2019-11-01 18:21:00', '29', null, null, '2019-11-01 18:21:00', null, '2019-11-01 18:21:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('350191290167869440', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-11-01 18:22:00', '2019-11-01 18:22:00', '35', null, null, '2019-11-01 18:22:00', null, '2019-11-01 18:22:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('350191541792555008', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-11-01 18:23:00', '2019-11-01 18:23:00', '29', null, null, '2019-11-01 18:23:00', null, '2019-11-01 18:23:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('350191793467572224', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-11-01 18:24:00', '2019-11-01 18:24:00', '31', null, null, '2019-11-01 18:24:00', null, '2019-11-01 18:24:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('350192045071286272', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-11-01 18:25:00', '2019-11-01 18:25:00', '21', null, null, '2019-11-01 18:25:00', null, '2019-11-01 18:25:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('350192296767275008', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-11-01 18:26:00', '2019-11-01 18:26:00', '29', null, null, '2019-11-01 18:26:00', null, '2019-11-01 18:26:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('350192548425515008', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-11-01 18:27:00', '2019-11-01 18:27:00', '27', null, null, '2019-11-01 18:27:00', null, '2019-11-01 18:27:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('350192800096337920', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-11-01 18:28:00', '2019-11-01 18:28:00', '31', null, null, '2019-11-01 18:28:00', null, '2019-11-01 18:28:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('350193051754577920', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-11-01 18:29:00', '2019-11-01 18:29:00', '31', null, null, '2019-11-01 18:29:00', null, '2019-11-01 18:29:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('350193303417012224', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-11-01 18:30:00', '2019-11-01 18:30:00', '29', null, null, '2019-11-01 18:30:00', null, '2019-11-01 18:30:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('350193555050086400', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-11-01 18:31:00', '2019-11-01 18:31:00', '26', null, null, '2019-11-01 18:31:00', null, '2019-11-01 18:31:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('350193806725103616', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-11-01 18:32:00', '2019-11-01 18:32:00', '30', null, null, '2019-11-01 18:32:00', null, '2019-11-01 18:32:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('350194058366566400', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-11-01 18:33:00', '2019-11-01 18:33:00', '26', null, null, '2019-11-01 18:33:00', null, '2019-11-01 18:33:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('350194309995446272', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-11-01 18:34:00', '2019-11-01 18:34:00', '21', null, null, '2019-11-01 18:34:00', null, '2019-11-01 18:34:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('350194561674657792', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-11-01 18:35:00', '2019-11-01 18:35:00', '24', null, null, '2019-11-01 18:35:00', null, '2019-11-01 18:35:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('350194813366452224', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-11-01 18:36:00', '2019-11-01 18:36:00', '32', null, null, '2019-11-01 18:36:00', null, '2019-11-01 18:36:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('350195064974360576', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-11-01 18:37:00', '2019-11-01 18:37:00', '22', null, null, '2019-11-01 18:37:00', null, '2019-11-01 18:37:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('350195569523965952', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-11-01 18:39:00', '2019-11-01 18:39:00', '265', null, null, '2019-11-01 18:39:00', null, '2019-11-01 18:39:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('350195819940691968', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-11-01 18:40:00', '2019-11-01 18:40:00', '21', null, null, '2019-11-01 18:40:00', null, '2019-11-01 18:40:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('350196071657652224', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-11-01 18:41:00', '2019-11-01 18:41:00', '30', null, null, '2019-11-01 18:41:00', null, '2019-11-01 18:41:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('350196323223617536', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-11-01 18:42:00', '2019-11-01 18:42:00', '13', null, null, '2019-11-01 18:42:00', null, '2019-11-01 18:42:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('350196574936383488', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-11-01 18:43:00', '2019-11-01 18:43:00', '26', null, null, '2019-11-01 18:43:00', null, '2019-11-01 18:43:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('350197331110674432', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-11-01 18:46:00', '2019-11-01 18:46:00', '251', null, null, '2019-11-01 18:46:00', null, '2019-11-01 18:46:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('350197581649035264', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-11-01 18:47:00', '2019-11-01 18:47:00', '38', null, null, '2019-11-01 18:47:00', null, '2019-11-01 18:47:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('350197833177251840', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-11-01 18:48:00', '2019-11-01 18:48:00', '14', null, null, '2019-11-01 18:48:00', null, '2019-11-01 18:48:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('350198084915183616', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-11-01 18:49:00', '2019-11-01 18:49:00', '30', null, null, '2019-11-01 18:49:00', null, '2019-11-01 18:49:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('350198336510509056', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-11-01 18:50:00', '2019-11-01 18:50:00', '18', null, null, '2019-11-01 18:50:00', null, '2019-11-01 18:50:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('350198588231663616', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-11-01 18:51:00', '2019-11-01 18:51:00', '31', null, null, '2019-11-01 18:51:00', null, '2019-11-01 18:51:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('350198839881515008', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-11-01 18:52:00', '2019-11-01 18:52:00', '26', null, null, '2019-11-01 18:52:00', null, '2019-11-01 18:52:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('350199091460063232', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-11-01 18:53:00', '2019-11-01 18:53:00', '11', null, null, '2019-11-01 18:53:00', null, '2019-11-01 18:53:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('350199343143469056', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-11-01 18:54:00', '2019-11-01 18:54:00', '16', null, null, '2019-11-01 18:54:00', null, '2019-11-01 18:54:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('350199594864623616', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-11-01 18:55:00', '2019-11-01 18:55:00', '29', null, null, '2019-11-01 18:55:00', null, '2019-11-01 18:55:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('350199846510280704', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-11-01 18:56:00', '2019-11-01 18:56:00', '28', null, null, '2019-11-01 18:56:00', null, '2019-11-01 18:56:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('350200098118189056', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-11-01 18:57:00', '2019-11-01 18:57:00', '17', null, null, '2019-11-01 18:57:00', null, '2019-11-01 18:57:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('350200349797400576', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-11-01 18:58:00', '2019-11-01 18:58:00', '20', null, null, '2019-11-01 18:58:00', null, '2019-11-01 18:58:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('350200601505972224', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-11-01 18:59:00', '2019-11-01 18:59:00', '32', null, null, '2019-11-01 18:59:00', null, '2019-11-01 18:59:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('351237183100960768', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-11-04 15:38:00', '2019-11-04 15:38:00', '285', null, null, '2019-11-04 15:38:00', null, '2019-11-04 15:38:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('351237942832021504', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-11-04 15:41:00', '2019-11-04 15:41:01', '1428', null, null, '2019-11-04 15:41:01', null, '2019-11-04 15:41:01', null, null);
INSERT INTO `sys_task_log_t` VALUES ('351241214041014272', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-11-04 15:54:00', '2019-11-04 15:54:01', '1359', null, null, '2019-11-04 15:54:01', null, '2019-11-04 15:54:01', null, null);
INSERT INTO `sys_task_log_t` VALUES ('351273422210482176', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-11-04 18:02:00', '2019-11-04 18:02:00', '346', null, null, '2019-11-04 18:02:00', null, '2019-11-04 18:02:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('351273672291663872', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-11-04 18:03:00', '2019-11-04 18:03:00', '32', null, null, '2019-11-04 18:03:00', null, '2019-11-04 18:03:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('351273923828269056', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-11-04 18:04:00', '2019-11-04 18:04:00', '13', null, null, '2019-11-04 18:04:00', null, '2019-11-04 18:04:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('351274175520063488', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-11-04 18:05:00', '2019-11-04 18:05:00', '23', null, null, '2019-11-04 18:05:00', null, '2019-11-04 18:05:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('351274427186692096', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-11-04 18:06:00', '2019-11-04 18:06:00', '25', null, null, '2019-11-04 18:06:00', null, '2019-11-04 18:06:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('351274678777823232', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-11-04 18:07:00', '2019-11-04 18:07:00', '11', null, null, '2019-11-04 18:07:00', null, '2019-11-04 18:07:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('351274930444451840', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-11-04 18:08:00', '2019-11-04 18:08:00', '13', null, null, '2019-11-04 18:08:00', null, '2019-11-04 18:08:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('351275182199160832', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-11-04 18:09:00', '2019-11-04 18:09:00', '32', null, null, '2019-11-04 18:09:00', null, '2019-11-04 18:09:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('351275433765126144', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-11-04 18:10:00', '2019-11-04 18:10:00', '15', null, null, '2019-11-04 18:10:00', null, '2019-11-04 18:10:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('351275685507252224', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-11-04 18:11:00', '2019-11-04 18:11:00', '31', null, null, '2019-11-04 18:11:00', null, '2019-11-04 18:11:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('351275937073217536', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-11-04 18:12:00', '2019-11-04 18:12:00', '13', null, null, '2019-11-04 18:12:00', null, '2019-11-04 18:12:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('351276188748234752', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-11-04 18:13:00', '2019-11-04 18:13:00', '16', null, null, '2019-11-04 18:13:00', null, '2019-11-04 18:13:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('351276440385503232', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-11-04 18:14:00', '2019-11-04 18:14:00', '11', null, null, '2019-11-04 18:14:00', null, '2019-11-04 18:14:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('351276692123435008', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-11-04 18:15:00', '2019-11-04 18:15:00', '28', null, null, '2019-11-04 18:15:00', null, '2019-11-04 18:15:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('351276943785869312', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-11-04 18:16:00', '2019-11-04 18:16:00', '28', null, null, '2019-11-04 18:16:00', null, '2019-11-04 18:16:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('351277195460886528', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-11-04 18:17:00', '2019-11-04 18:17:00', '32', null, null, '2019-11-04 18:17:00', null, '2019-11-04 18:17:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('351277447047823360', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-11-04 18:18:00', '2019-11-04 18:18:00', '18', null, null, '2019-11-04 18:18:00', null, '2019-11-04 18:18:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('351277698710257664', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-11-04 18:19:00', '2019-11-04 18:19:00', '20', null, null, '2019-11-04 18:19:00', null, '2019-11-04 18:19:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('351277950343331840', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-11-04 18:20:00', '2019-11-04 18:20:00', '12', null, null, '2019-11-04 18:20:00', null, '2019-11-04 18:20:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('351278202089652224', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-11-04 18:21:00', '2019-11-04 18:21:00', '33', null, null, '2019-11-04 18:21:00', null, '2019-11-04 18:21:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('351278453722726400', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-11-04 18:22:00', '2019-11-04 18:22:00', '25', null, null, '2019-11-04 18:22:00', null, '2019-11-04 18:22:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('351278705364189184', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-11-04 18:23:00', '2019-11-04 18:23:00', '25', null, null, '2019-11-04 18:23:00', null, '2019-11-04 18:23:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('351278957072760832', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-11-04 18:24:00', '2019-11-04 18:24:00', '34', null, null, '2019-11-04 18:24:00', null, '2019-11-04 18:24:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('351279208722612224', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-11-04 18:25:00', '2019-11-04 18:25:00', '31', null, null, '2019-11-04 18:25:00', null, '2019-11-04 18:25:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('351279460359880704', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-11-04 18:26:00', '2019-11-04 18:26:00', '28', null, null, '2019-11-04 18:26:00', null, '2019-11-04 18:26:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('351279712022315008', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-11-04 18:27:00', '2019-11-04 18:27:00', '27', null, null, '2019-11-04 18:27:00', null, '2019-11-04 18:27:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('351280485334532096', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-11-04 18:30:00', '2019-11-04 18:30:04', '4364', null, null, '2019-11-04 18:30:04', null, '2019-11-04 18:30:04', null, null);
INSERT INTO `sys_task_log_t` VALUES ('351280718684635136', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-11-04 18:31:00', '2019-11-04 18:31:00', '32', null, null, '2019-11-04 18:31:00', null, '2019-11-04 18:31:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('351285503739576320', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-11-04 18:50:00', '2019-11-04 18:50:01', '848', null, null, '2019-11-04 18:50:01', null, '2019-11-04 18:50:01', null, null);
INSERT INTO `sys_task_log_t` VALUES ('351285751853629440', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-11-04 18:51:00', '2019-11-04 18:51:00', '35', null, null, '2019-11-04 18:51:00', null, '2019-11-04 18:51:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('351286003516063744', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-11-04 18:52:00', '2019-11-04 18:52:00', '35', null, null, '2019-11-04 18:52:00', null, '2019-11-04 18:52:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('351286255144943616', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-11-04 18:53:00', '2019-11-04 18:53:00', '29', null, null, '2019-11-04 18:53:00', null, '2019-11-04 18:53:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('351286506794795008', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-11-04 18:54:00', '2019-11-04 18:54:00', '28', null, null, '2019-11-04 18:54:00', null, '2019-11-04 18:54:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('351286758398509056', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-11-04 18:55:00', '2019-11-04 18:55:00', '18', null, null, '2019-11-04 18:55:00', null, '2019-11-04 18:55:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('351287010111275008', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-11-04 18:56:00', '2019-11-04 18:56:00', '27', null, null, '2019-11-04 18:56:00', null, '2019-11-04 18:56:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('351287261773709312', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-11-04 18:57:00', '2019-11-04 18:57:00', '28', null, null, '2019-11-04 18:57:00', null, '2019-11-04 18:57:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('351287513448726528', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-11-04 18:58:00', '2019-11-04 18:58:00', '34', null, null, '2019-11-04 18:58:00', null, '2019-11-04 18:58:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('351287765073412096', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-11-04 18:59:00', '2019-11-04 18:59:00', '26', null, null, '2019-11-04 18:59:00', null, '2019-11-04 18:59:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('351288016685514752', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-11-04 19:00:00', '2019-11-04 19:00:00', '18', null, null, '2019-11-04 19:00:00', null, '2019-11-04 19:00:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('351288268326977536', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-11-04 19:01:00', '2019-11-04 19:01:00', '12', null, null, '2019-11-04 19:01:00', null, '2019-11-04 19:01:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('351288519989411840', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-11-04 19:02:00', '2019-11-04 19:02:00', '15', null, null, '2019-11-04 19:02:00', null, '2019-11-04 19:02:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('351288771693789184', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-11-04 19:03:00', '2019-11-04 19:03:00', '23', null, null, '2019-11-04 19:03:00', null, '2019-11-04 19:03:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('351293302926229504', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-11-04 19:21:00', '2019-11-04 19:21:00', '287', null, null, '2019-11-04 19:21:00', null, '2019-11-04 19:21:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('353032272643112960', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-11-09 14:31:00', '2019-11-09 14:31:03', '2998', null, null, '2019-11-09 14:31:03', null, '2019-11-09 14:31:03', null, null);
INSERT INTO `sys_task_log_t` VALUES ('353032511642943488', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-11-09 14:32:00', '2019-11-09 14:32:00', '23', null, null, '2019-11-09 14:32:00', null, '2019-11-09 14:32:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('353032763468955648', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-11-09 14:33:00', '2019-11-09 14:33:00', '63', null, null, '2019-11-09 14:33:00', null, '2019-11-09 14:33:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('353033015135584256', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-11-09 14:34:00', '2019-11-09 14:34:00', '63', null, null, '2019-11-09 14:34:00', null, '2019-11-09 14:34:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('353033266626052096', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-11-09 14:35:00', '2019-11-09 14:35:00', '26', null, null, '2019-11-09 14:35:00', null, '2019-11-09 14:35:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('353033518322040832', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-11-09 14:36:00', '2019-11-09 14:36:00', '33', null, null, '2019-11-09 14:36:00', null, '2019-11-09 14:36:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('353033770005446656', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-11-09 14:37:00', '2019-11-09 14:37:00', '33', null, null, '2019-11-09 14:37:00', null, '2019-11-09 14:37:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('353034021604966400', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-11-09 14:38:00', '2019-11-09 14:38:00', '29', null, null, '2019-11-09 14:38:00', null, '2019-11-09 14:38:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('353034273187708928', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-11-09 14:39:00', '2019-11-09 14:39:00', '9', null, null, '2019-11-09 14:39:00', null, '2019-11-09 14:39:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('353034524845948928', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-11-09 14:40:00', '2019-11-09 14:40:00', '10', null, null, '2019-11-09 14:40:00', null, '2019-11-09 14:40:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('353034776600657920', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-11-09 14:41:00', '2019-11-09 14:41:00', '30', null, null, '2019-11-09 14:41:00', null, '2019-11-09 14:41:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('353035028221149184', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-11-09 14:42:00', '2019-11-09 14:42:00', '24', null, null, '2019-11-09 14:42:00', null, '2019-11-09 14:42:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('353035279896166400', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-11-09 14:43:00', '2019-11-09 14:43:00', '27', null, null, '2019-11-09 14:43:00', null, '2019-11-09 14:43:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('353035531579572224', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-11-09 14:44:00', '2019-11-09 14:44:00', '32', null, null, '2019-11-09 14:44:00', null, '2019-11-09 14:44:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('353036539391131648', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-11-09 14:48:00', '2019-11-09 14:48:00', '282', null, null, '2019-11-09 14:48:00', null, '2019-11-09 14:48:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('353036869877121024', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-11-09 14:49:01', '2019-11-09 14:49:19', '17859', null, null, '2019-11-09 14:49:19', null, '2019-11-09 14:49:19', null, null);
INSERT INTO `sys_task_log_t` VALUES ('353037041445126144', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-11-09 14:50:00', '2019-11-09 14:50:00', '14', null, null, '2019-11-09 14:50:00', null, '2019-11-09 14:50:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('353742441047932928', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-11-11 13:33:00', '2019-11-11 13:33:00', '323', null, null, '2019-11-11 13:33:00', null, '2019-11-11 13:33:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('353742691233972224', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-11-11 13:34:00', '2019-11-11 13:34:00', '30', null, null, '2019-11-11 13:34:00', null, '2019-11-11 13:34:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('353742942804131840', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-11-11 13:35:00', '2019-11-11 13:35:00', '14', null, null, '2019-11-11 13:35:00', null, '2019-11-11 13:35:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('353743194508509184', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-11-11 13:36:00', '2019-11-11 13:36:00', '24', null, null, '2019-11-11 13:36:00', null, '2019-11-11 13:36:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('353743446204497920', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-11-11 13:37:00', '2019-11-11 13:37:00', '32', null, null, '2019-11-11 13:37:00', null, '2019-11-11 13:37:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('353743697845960704', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-11-11 13:38:00', '2019-11-11 13:38:00', '27', null, null, '2019-11-11 13:38:00', null, '2019-11-11 13:38:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('353743949508395008', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-11-11 13:39:00', '2019-11-11 13:39:00', '30', null, null, '2019-11-11 13:39:00', null, '2019-11-11 13:39:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('353744201137274880', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-11-11 13:40:00', '2019-11-11 13:40:00', '21', null, null, '2019-11-11 13:40:00', null, '2019-11-11 13:40:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('353744452824875008', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-11-11 13:41:00', '2019-11-11 13:41:00', '29', null, null, '2019-11-11 13:41:00', null, '2019-11-11 13:41:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('353744704399228928', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-11-11 13:42:00', '2019-11-11 13:42:00', '10', null, null, '2019-11-11 13:42:00', null, '2019-11-11 13:42:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('353744956162326528', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-11-11 13:43:00', '2019-11-11 13:43:00', '32', null, null, '2019-11-11 13:43:00', null, '2019-11-11 13:43:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('353745207782817792', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-11-11 13:44:00', '2019-11-11 13:44:00', '25', null, null, '2019-11-11 13:44:00', null, '2019-11-11 13:44:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('353745459424280576', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-11-11 13:45:00', '2019-11-11 13:45:00', '22', null, null, '2019-11-11 13:45:00', null, '2019-11-11 13:45:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('353745711086714880', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-11-11 13:46:00', '2019-11-11 13:46:00', '23', null, null, '2019-11-11 13:46:00', null, '2019-11-11 13:46:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('353745962728177664', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-11-11 13:47:00', '2019-11-11 13:47:00', '12', null, null, '2019-11-11 13:47:00', null, '2019-11-11 13:47:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('353746214399000576', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-11-11 13:48:00', '2019-11-11 13:48:00', '21', null, null, '2019-11-11 13:48:00', null, '2019-11-11 13:48:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('353746466061434880', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-11-11 13:49:00', '2019-11-11 13:49:00', '22', null, null, '2019-11-11 13:49:00', null, '2019-11-11 13:49:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('353746717686120448', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-11-11 13:50:00', '2019-11-11 13:50:00', '16', null, null, '2019-11-11 13:50:00', null, '2019-11-11 13:50:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('353746969398886400', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-11-11 13:51:00', '2019-11-11 13:51:00', '26', null, null, '2019-11-11 13:51:00', null, '2019-11-11 13:51:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('353747221061320704', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-11-11 13:52:00', '2019-11-11 13:52:00', '28', null, null, '2019-11-11 13:52:00', null, '2019-11-11 13:52:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('353747472727949312', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-11-11 13:53:00', '2019-11-11 13:53:00', '30', null, null, '2019-11-11 13:53:00', null, '2019-11-11 13:53:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('353747724377800704', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-11-11 13:54:00', '2019-11-11 13:54:00', '29', null, null, '2019-11-11 13:54:00', null, '2019-11-11 13:54:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('353747976010874880', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-11-11 13:55:00', '2019-11-11 13:55:00', '22', null, null, '2019-11-11 13:55:00', null, '2019-11-11 13:55:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('353748227694280704', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-11-11 13:56:00', '2019-11-11 13:56:00', '29', null, null, '2019-11-11 13:56:00', null, '2019-11-11 13:56:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('353748479264440320', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-11-11 13:57:00', '2019-11-11 13:57:00', '10', null, null, '2019-11-11 13:57:00', null, '2019-11-11 13:57:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('353748731010760704', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-11-11 13:58:00', '2019-11-11 13:58:00', '29', null, null, '2019-11-11 13:58:00', null, '2019-11-11 13:58:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('353748982635446272', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-11-11 13:59:00', '2019-11-11 13:59:00', '21', null, null, '2019-11-11 13:59:00', null, '2019-11-11 13:59:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('353749234339823616', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-11-11 14:00:00', '2019-11-11 14:00:00', '32', null, null, '2019-11-11 14:00:00', null, '2019-11-11 14:00:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('353762087964786688', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-11-11 14:51:00', '2019-11-11 14:51:05', '4546', null, null, '2019-11-11 14:51:05', null, '2019-11-11 14:51:05', null, null);
INSERT INTO `sys_task_log_t` VALUES ('353762840829771776', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-11-11 14:54:00', '2019-11-11 14:54:04', '4007', null, null, '2019-11-11 14:54:04', null, '2019-11-11 14:54:04', null, null);
INSERT INTO `sys_task_log_t` VALUES ('353763075593355264', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-11-11 14:55:00', '2019-11-11 14:55:00', '40', null, null, '2019-11-11 14:55:00', null, '2019-11-11 14:55:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('353781196022628352', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-11-11 16:07:00', '2019-11-11 16:07:00', '237', null, null, '2019-11-11 16:07:00', null, '2019-11-11 16:07:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('353781446821036032', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-11-11 16:08:00', '2019-11-11 16:08:00', '79', null, null, '2019-11-11 16:08:00', null, '2019-11-11 16:08:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('353781698177286144', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-11-11 16:09:00', '2019-11-11 16:09:00', '14', null, null, '2019-11-11 16:09:00', null, '2019-11-11 16:09:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('353782454359965696', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-11-11 16:12:00', '2019-11-11 16:12:00', '276', null, null, '2019-11-11 16:12:00', null, '2019-11-11 16:12:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('353783208135114752', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-11-11 16:15:00', '2019-11-11 16:15:00', '17', null, null, '2019-11-11 16:15:00', null, '2019-11-11 16:15:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('353783459864657920', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-11-11 16:16:00', '2019-11-11 16:16:00', '32', null, null, '2019-11-11 16:16:00', null, '2019-11-11 16:16:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('353784214763880448', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-11-11 16:19:00', '2019-11-11 16:19:00', '15', null, null, '2019-11-11 16:19:00', null, '2019-11-11 16:19:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('353784466413731840', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-11-11 16:20:00', '2019-11-11 16:20:00', '14', null, null, '2019-11-11 16:20:00', null, '2019-11-11 16:20:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('353784718118109184', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-11-11 16:21:00', '2019-11-11 16:21:00', '22', null, null, '2019-11-11 16:21:00', null, '2019-11-11 16:21:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('353784969814097920', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-11-11 16:22:00', '2019-11-11 16:22:00', '31', null, null, '2019-11-11 16:22:00', null, '2019-11-11 16:22:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('353785221480726528', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-11-11 16:23:00', '2019-11-11 16:23:00', '33', null, null, '2019-11-11 16:23:00', null, '2019-11-11 16:23:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('353785473117995008', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-11-11 16:24:00', '2019-11-11 16:24:00', '29', null, null, '2019-11-11 16:24:00', null, '2019-11-11 16:24:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('353785724767846400', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-11-11 16:25:00', '2019-11-11 16:25:00', '28', null, null, '2019-11-11 16:25:00', null, '2019-11-11 16:25:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('353785976358977536', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-11-11 16:26:00', '2019-11-11 16:26:00', '13', null, null, '2019-11-11 16:26:00', null, '2019-11-11 16:26:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('353786228101103616', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-11-11 16:27:00', '2019-11-11 16:27:00', '30', null, null, '2019-11-11 16:27:00', null, '2019-11-11 16:27:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('353786479746760704', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-11-11 16:28:00', '2019-11-11 16:28:00', '27', null, null, '2019-11-11 16:28:00', null, '2019-11-11 16:28:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('353786731417583616', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-11-11 16:29:00', '2019-11-11 16:29:00', '29', null, null, '2019-11-11 16:29:00', null, '2019-11-11 16:29:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('353786983075823616', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-11-11 16:30:00', '2019-11-11 16:30:00', '31', null, null, '2019-11-11 16:30:00', null, '2019-11-11 16:30:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('353797554294767616', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-11-11 17:12:00', '2019-11-11 17:12:00', '363', null, null, '2019-11-11 17:12:00', null, '2019-11-11 17:12:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('353797804413698048', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-11-11 17:13:00', '2019-11-11 17:13:00', '33', null, null, '2019-11-11 17:13:00', null, '2019-11-11 17:13:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('353798056071938048', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-11-11 17:14:00', '2019-11-11 17:14:00', '39', null, null, '2019-11-11 17:14:00', null, '2019-11-11 17:14:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('353798565096865792', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-11-11 17:16:00', '2019-11-11 17:16:01', '1373', null, null, '2019-11-11 17:16:01', null, '2019-11-11 17:16:01', null, null);
INSERT INTO `sys_task_log_t` VALUES ('353798811055046656', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-11-11 17:17:00', '2019-11-11 17:17:00', '36', null, null, '2019-11-11 17:17:00', null, '2019-11-11 17:17:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('353799062604234752', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-11-11 17:18:00', '2019-11-11 17:18:00', '14', null, null, '2019-11-11 17:18:00', null, '2019-11-11 17:18:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('353799314296029184', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-11-11 17:19:00', '2019-11-11 17:19:00', '22', null, null, '2019-11-11 17:19:00', null, '2019-11-11 17:19:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('353799629879656448', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-11-11 17:20:02', '2019-11-11 17:20:15', '13110', null, null, '2019-11-11 17:20:15', null, '2019-11-11 17:20:15', null, null);
INSERT INTO `sys_task_log_t` VALUES ('353799856447569920', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-11-11 17:21:05', '2019-11-11 17:21:09', '4474', null, null, '2019-11-11 17:21:09', null, '2019-11-11 17:21:09', null, null);
INSERT INTO `sys_task_log_t` VALUES ('353800069329469440', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-11-11 17:22:00', '2019-11-11 17:22:00', '37', null, null, '2019-11-11 17:22:00', null, '2019-11-11 17:22:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('353800320891240448', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-11-11 17:23:00', '2019-11-11 17:23:00', '14', null, null, '2019-11-11 17:23:00', null, '2019-11-11 17:23:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('353800572629172224', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-11-11 17:24:00', '2019-11-11 17:24:00', '32', null, null, '2019-11-11 17:24:00', null, '2019-11-11 17:24:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('353800824232886272', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-11-11 17:25:00', '2019-11-11 17:25:00', '22', null, null, '2019-11-11 17:25:00', null, '2019-11-11 17:25:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('353801075861766144', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-11-11 17:26:00', '2019-11-11 17:26:00', '14', null, null, '2019-11-11 17:26:00', null, '2019-11-11 17:26:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('353801327591309312', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-11-11 17:27:00', '2019-11-11 17:27:00', '28', null, null, '2019-11-11 17:27:00', null, '2019-11-11 17:27:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('353801579249549312', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-11-11 17:28:00', '2019-11-11 17:28:00', '30', null, null, '2019-11-11 17:28:00', null, '2019-11-11 17:28:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('353801830928760832', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-11-11 17:29:00', '2019-11-11 17:29:00', '32', null, null, '2019-11-11 17:29:00', null, '2019-11-11 17:29:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('353802082519891968', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-11-11 17:30:00', '2019-11-11 17:30:00', '19', null, null, '2019-11-11 17:30:00', null, '2019-11-11 17:30:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('353802334224269312', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-11-11 17:31:00', '2019-11-11 17:31:00', '29', null, null, '2019-11-11 17:31:00', null, '2019-11-11 17:31:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('353802585798623232', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-11-11 17:32:00', '2019-11-11 17:32:00', '12', null, null, '2019-11-11 17:32:00', null, '2019-11-11 17:32:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('353802837482029056', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-11-11 17:33:00', '2019-11-11 17:33:00', '18', null, null, '2019-11-11 17:33:00', null, '2019-11-11 17:33:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('353812658209964032', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-11-11 18:12:00', '2019-11-11 18:12:01', '1397', null, null, '2019-11-11 18:12:01', null, '2019-11-11 18:12:01', null, null);
INSERT INTO `sys_task_log_t` VALUES ('353812903895515136', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-11-11 18:13:00', '2019-11-11 18:13:00', '33', null, null, '2019-11-11 18:13:00', null, '2019-11-11 18:13:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('353813155486646272', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-11-11 18:14:00', '2019-11-11 18:14:00', '20', null, null, '2019-11-11 18:14:00', null, '2019-11-11 18:14:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('353813460307689472', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-11-11 18:15:02', '2019-11-11 18:15:13', '11171', null, null, '2019-11-11 18:15:13', null, '2019-11-11 18:15:13', null, null);
INSERT INTO `sys_task_log_t` VALUES ('353813658849263616', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-11-11 18:16:00', '2019-11-11 18:16:00', '30', null, null, '2019-11-11 18:16:00', null, '2019-11-11 18:16:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('353813910461366272', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-11-11 18:17:00', '2019-11-11 18:17:00', '19', null, null, '2019-11-11 18:17:00', null, '2019-11-11 18:17:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('353814162090246144', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-11-11 18:18:00', '2019-11-11 18:18:00', '14', null, null, '2019-11-11 18:18:00', null, '2019-11-11 18:18:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('353834815359041536', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-11-11 19:40:00', '2019-11-11 19:40:04', '4106', null, null, '2019-11-11 19:40:04', null, '2019-11-11 19:40:04', null, null);
INSERT INTO `sys_task_log_t` VALUES ('353835086617264128', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-11-11 19:41:06', '2019-11-11 19:41:09', '2480', null, null, '2019-11-11 19:41:09', null, '2019-11-11 19:41:09', null, null);
INSERT INTO `sys_task_log_t` VALUES ('353835301457903616', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-11-11 19:42:00', '2019-11-11 19:42:00', '31', null, null, '2019-11-11 19:42:00', null, '2019-11-11 19:42:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('353835553116143616', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-11-11 19:43:00', '2019-11-11 19:43:00', '31', null, null, '2019-11-11 19:43:00', null, '2019-11-11 19:43:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('353835804682108928', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-11-11 19:44:00', '2019-11-11 19:44:00', '11', null, null, '2019-11-11 19:44:00', null, '2019-11-11 19:44:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('354044700294135808', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-11-12 09:34:00', '2019-11-12 09:34:05', '4571', null, null, '2019-11-12 09:34:05', null, '2019-11-12 09:34:05', null, null);
INSERT INTO `sys_task_log_t` VALUES ('354044932813766656', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-11-12 09:35:00', '2019-11-12 09:35:00', '34', null, null, '2019-11-12 09:35:00', null, '2019-11-12 09:35:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('354045184409092096', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-11-12 09:36:00', '2019-11-12 09:36:00', '26', null, null, '2019-11-12 09:36:00', null, '2019-11-12 09:36:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('354045436063137792', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-11-12 09:37:00', '2019-11-12 09:37:00', '24', null, null, '2019-11-12 09:37:00', null, '2019-11-12 09:37:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('354142844914778112', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-11-12 16:04:00', '2019-11-12 16:04:04', '4048', null, null, '2019-11-12 16:04:04', null, '2019-11-12 16:04:04', null, null);
INSERT INTO `sys_task_log_t` VALUES ('354143079502200832', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-11-12 16:05:00', '2019-11-12 16:05:00', '32', null, null, '2019-11-12 16:05:00', null, '2019-11-12 16:05:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('354143331122692096', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-11-12 16:06:00', '2019-11-12 16:06:00', '25', null, null, '2019-11-12 16:06:00', null, '2019-11-12 16:06:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('354143582730600448', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-11-12 16:07:00', '2019-11-12 16:07:00', '15', null, null, '2019-11-12 16:07:00', null, '2019-11-12 16:07:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('354143834414006272', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-11-12 16:08:00', '2019-11-12 16:08:00', '21', null, null, '2019-11-12 16:08:00', null, '2019-11-12 16:08:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('354144086089023488', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-11-12 16:09:00', '2019-11-12 16:09:00', '25', null, null, '2019-11-12 16:09:00', null, '2019-11-12 16:09:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('354144337688543232', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-11-12 16:10:00', '2019-11-12 16:10:00', '12', null, null, '2019-11-12 16:10:00', null, '2019-11-12 16:10:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('354147108202496000', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-11-12 16:21:00', '2019-11-12 16:21:01', '442', null, null, '2019-11-12 16:21:01', null, '2019-11-12 16:21:01', null, null);
INSERT INTO `sys_task_log_t` VALUES ('354147357713252352', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-11-12 16:22:00', '2019-11-12 16:22:00', '37', null, null, '2019-11-12 16:22:00', null, '2019-11-12 16:22:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('354147609325355008', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-11-12 16:23:00', '2019-11-12 16:23:00', '27', null, null, '2019-11-12 16:23:00', null, '2019-11-12 16:23:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('354147860920680448', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-11-12 16:24:00', '2019-11-12 16:24:00', '15', null, null, '2019-11-12 16:24:00', null, '2019-11-12 16:24:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('354148112625057792', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-11-12 16:25:00', '2019-11-12 16:25:00', '25', null, null, '2019-11-12 16:25:00', null, '2019-11-12 16:25:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('354148364321046528', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-11-12 16:26:00', '2019-11-12 16:26:00', '33', null, null, '2019-11-12 16:26:00', null, '2019-11-12 16:26:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('354148615970897920', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-11-12 16:27:00', '2019-11-12 16:27:00', '30', null, null, '2019-11-12 16:27:00', null, '2019-11-12 16:27:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('354148867587194880', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-11-12 16:28:00', '2019-11-12 16:28:00', '22', null, null, '2019-11-12 16:28:00', null, '2019-11-12 16:28:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('354149119258017792', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-11-12 16:29:00', '2019-11-12 16:29:00', '26', null, null, '2019-11-12 16:29:00', null, '2019-11-12 16:29:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('354149370891091968', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-11-12 16:30:00', '2019-11-12 16:30:00', '19', null, null, '2019-11-12 16:30:00', null, '2019-11-12 16:30:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('354149622528360448', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-11-12 16:31:00', '2019-11-12 16:31:00', '16', null, null, '2019-11-12 16:31:00', null, '2019-11-12 16:31:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('354149874236932096', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-11-12 16:32:00', '2019-11-12 16:32:00', '26', null, null, '2019-11-12 16:32:00', null, '2019-11-12 16:32:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('354150125832257536', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-11-12 16:33:00', '2019-11-12 16:33:00', '14', null, null, '2019-11-12 16:33:00', null, '2019-11-12 16:33:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('354150377565995008', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-11-12 16:34:00', '2019-11-12 16:34:00', '27', null, null, '2019-11-12 16:34:00', null, '2019-11-12 16:34:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('354150629173903360', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-11-12 16:35:00', '2019-11-12 16:35:00', '15', null, null, '2019-11-12 16:35:00', null, '2019-11-12 16:35:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('354150880840531968', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-11-12 16:36:00', '2019-11-12 16:36:00', '18', null, null, '2019-11-12 16:36:00', null, '2019-11-12 16:36:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('354151132532326400', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-11-12 16:37:00', '2019-11-12 16:37:00', '28', null, null, '2019-11-12 16:37:00', null, '2019-11-12 16:37:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('354151384186372096', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-11-12 16:38:00', '2019-11-12 16:38:00', '25', null, null, '2019-11-12 16:38:00', null, '2019-11-12 16:38:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('354151635848806400', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-11-12 16:39:00', '2019-11-12 16:39:00', '26', null, null, '2019-11-12 16:39:00', null, '2019-11-12 16:39:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('354151887502852096', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-11-12 16:40:00', '2019-11-12 16:40:00', '26', null, null, '2019-11-12 16:40:00', null, '2019-11-12 16:40:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('354152139177869312', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-11-12 16:41:00', '2019-11-12 16:41:00', '30', null, null, '2019-11-12 16:41:00', null, '2019-11-12 16:41:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('354152390756417536', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-11-12 16:42:00', '2019-11-12 16:42:00', '13', null, null, '2019-11-12 16:42:00', null, '2019-11-12 16:42:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('354152642477572096', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-11-12 16:43:00', '2019-11-12 16:43:00', '25', null, null, '2019-11-12 16:43:00', null, '2019-11-12 16:43:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('354152894144200704', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-11-12 16:44:00', '2019-11-12 16:44:00', '29', null, null, '2019-11-12 16:44:00', null, '2019-11-12 16:44:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('354153145718554624', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-11-12 16:45:00', '2019-11-12 16:45:00', '10', null, null, '2019-11-12 16:45:00', null, '2019-11-12 16:45:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('354153397456486400', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-11-12 16:46:00', '2019-11-12 16:46:00', '25', null, null, '2019-11-12 16:46:00', null, '2019-11-12 16:46:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('354153649139892224', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-11-12 16:47:00', '2019-11-12 16:47:00', '33', null, null, '2019-11-12 16:47:00', null, '2019-11-12 16:47:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('354153900760383488', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-11-12 16:48:00', '2019-11-12 16:48:00', '25', null, null, '2019-11-12 16:48:00', null, '2019-11-12 16:48:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('354154152435400704', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-11-12 16:49:00', '2019-11-12 16:49:00', '27', null, null, '2019-11-12 16:49:00', null, '2019-11-12 16:49:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('354154404085252096', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-11-12 16:50:00', '2019-11-12 16:50:00', '25', null, null, '2019-11-12 16:50:00', null, '2019-11-12 16:50:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('354154655751880704', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-11-12 16:51:00', '2019-11-12 16:51:00', '26', null, null, '2019-11-12 16:51:00', null, '2019-11-12 16:51:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('354154907351400448', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-11-12 16:52:00', '2019-11-12 16:52:00', '16', null, null, '2019-11-12 16:52:00', null, '2019-11-12 16:52:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('354155159055777792', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-11-12 16:53:00', '2019-11-12 16:53:00', '24', null, null, '2019-11-12 16:53:00', null, '2019-11-12 16:53:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('354155410709823488', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-11-12 16:54:00', '2019-11-12 16:54:00', '25', null, null, '2019-11-12 16:54:00', null, '2019-11-12 16:54:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('354155662376452096', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-11-12 16:55:00', '2019-11-12 16:55:00', '23', null, null, '2019-11-12 16:55:00', null, '2019-11-12 16:55:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('354155914038886400', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-11-12 16:56:00', '2019-11-12 16:56:00', '27', null, null, '2019-11-12 16:56:00', null, '2019-11-12 16:56:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('354156165688737792', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-11-12 16:57:00', '2019-11-12 16:57:00', '24', null, null, '2019-11-12 16:57:00', null, '2019-11-12 16:57:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('354156417346977792', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-11-12 16:58:00', '2019-11-12 16:58:00', '25', null, null, '2019-11-12 16:58:00', null, '2019-11-12 16:58:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('354156668925526016', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-11-12 16:59:00', '2019-11-12 16:59:00', '9', null, null, '2019-11-12 16:59:00', null, '2019-11-12 16:59:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('354156920684429312', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-11-12 17:00:00', '2019-11-12 17:00:00', '30', null, null, '2019-11-12 17:00:00', null, '2019-11-12 17:00:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('354157172321697792', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-11-12 17:01:00', '2019-11-12 17:01:00', '26', null, null, '2019-11-12 17:01:00', null, '2019-11-12 17:01:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('354157423988326400', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-11-12 17:02:00', '2019-11-12 17:02:00', '27', null, null, '2019-11-12 17:02:00', null, '2019-11-12 17:02:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('354157675667537920', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-11-12 17:03:00', '2019-11-12 17:03:00', '31', null, null, '2019-11-12 17:03:00', null, '2019-11-12 17:03:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('354157927220920320', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-11-12 17:04:00', '2019-11-12 17:04:00', '9', null, null, '2019-11-12 17:04:00', null, '2019-11-12 17:04:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('354158178958852096', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-11-12 17:05:00', '2019-11-12 17:05:00', '26', null, null, '2019-11-12 17:05:00', null, '2019-11-12 17:05:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('354158430621286400', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-11-12 17:06:00', '2019-11-12 17:06:00', '27', null, null, '2019-11-12 17:06:00', null, '2019-11-12 17:06:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('354158682262749184', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-11-12 17:07:00', '2019-11-12 17:07:00', '24', null, null, '2019-11-12 17:07:00', null, '2019-11-12 17:07:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('354158933941960704', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-11-12 17:08:00', '2019-11-12 17:08:00', '27', null, null, '2019-11-12 17:08:00', null, '2019-11-12 17:08:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('354159185612783616', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-11-12 17:09:00', '2019-11-12 17:09:00', '30', null, null, '2019-11-12 17:09:00', null, '2019-11-12 17:09:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('354159437208109056', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-11-12 17:10:00', '2019-11-12 17:10:00', '17', null, null, '2019-11-12 17:10:00', null, '2019-11-12 17:10:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('354159688904097792', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-11-12 17:11:00', '2019-11-12 17:11:00', '24', null, null, '2019-11-12 17:11:00', null, '2019-11-12 17:11:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('354159940579115008', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-11-12 17:12:00', '2019-11-12 17:12:00', '29', null, null, '2019-11-12 17:12:00', null, '2019-11-12 17:12:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('354160192249937920', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-11-12 17:13:00', '2019-11-12 17:13:00', '31', null, null, '2019-11-12 17:13:00', null, '2019-11-12 17:13:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('354160443878817792', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-11-12 17:14:00', '2019-11-12 17:14:00', '26', null, null, '2019-11-12 17:14:00', null, '2019-11-12 17:14:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('354160695558029312', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-11-12 17:15:00', '2019-11-12 17:15:00', '27', null, null, '2019-11-12 17:15:00', null, '2019-11-12 17:15:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('354160947203686400', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-11-12 17:16:00', '2019-11-12 17:16:00', '27', null, null, '2019-11-12 17:16:00', null, '2019-11-12 17:16:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('354161198857732096', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-11-12 17:17:00', '2019-11-12 17:17:00', '27', null, null, '2019-11-12 17:17:00', null, '2019-11-12 17:17:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('354164220115566592', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-11-12 17:29:00', '2019-11-12 17:29:00', '302', null, null, '2019-11-12 17:29:00', null, '2019-11-12 17:29:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('354164723335577600', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-11-12 17:31:00', '2019-11-12 17:31:00', '302', null, null, '2019-11-12 17:31:00', null, '2019-11-12 17:31:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('354164973710360576', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-11-12 17:32:00', '2019-11-12 17:32:00', '21', null, null, '2019-11-12 17:32:00', null, '2019-11-12 17:32:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('354165225393766400', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-11-12 17:33:00', '2019-11-12 17:33:00', '27', null, null, '2019-11-12 17:33:00', null, '2019-11-12 17:33:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('354165477043617792', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-11-12 17:34:00', '2019-11-12 17:34:00', '25', null, null, '2019-11-12 17:34:00', null, '2019-11-12 17:34:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('354165728701857792', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-11-12 17:35:00', '2019-11-12 17:35:00', '24', null, null, '2019-11-12 17:35:00', null, '2019-11-12 17:35:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('354165980364292096', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-11-12 17:36:00', '2019-11-12 17:36:00', '26', null, null, '2019-11-12 17:36:00', null, '2019-11-12 17:36:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('354470757954502656', '346902598779158528', '1', '{\"code\":-1,\"data\":\"登录过期\",\"msg\":\"请求失败\",\"success\":false}', '2019-11-13 13:47:00', '2019-11-13 13:47:05', '4630', null, null, '2019-11-13 13:47:05', null, '2019-11-13 13:47:05', null, null);
INSERT INTO `sys_task_log_t` VALUES ('354471121441275904', '346902598779158528', '1', '{\"code\":-1,\"data\":\"登录过期\",\"msg\":\"请求失败\",\"success\":false}', '2019-11-13 13:48:00', '2019-11-13 13:48:31', '31328', null, null, '2019-11-13 13:48:31', null, '2019-11-13 13:48:31', null, null);
INSERT INTO `sys_task_log_t` VALUES ('354471241733914624', '346902598779158528', '1', '{\"code\":-1,\"data\":\"登录过期\",\"msg\":\"请求失败\",\"success\":false}', '2019-11-13 13:49:00', '2019-11-13 13:49:00', '10', null, null, '2019-11-13 13:49:00', null, '2019-11-13 13:49:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('354471493392154624', '346902598779158528', '1', '{\"code\":-1,\"data\":\"登录过期\",\"msg\":\"请求失败\",\"success\":false}', '2019-11-13 13:50:00', '2019-11-13 13:50:00', '10', null, null, '2019-11-13 13:50:00', null, '2019-11-13 13:50:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('354471745096531968', '346902598779158528', '1', '{\"code\":-1,\"data\":\"登录过期\",\"msg\":\"请求失败\",\"success\":false}', '2019-11-13 13:51:00', '2019-11-13 13:51:00', '20', null, null, '2019-11-13 13:51:00', null, '2019-11-13 13:51:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('354471996788326400', '346902598779158528', '1', '{\"code\":-1,\"data\":\"登录过期\",\"msg\":\"请求失败\",\"success\":false}', '2019-11-13 13:52:00', '2019-11-13 13:52:00', '28', null, null, '2019-11-13 13:52:00', null, '2019-11-13 13:52:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('354473021712646144', '346902598779158528', '1', '{\"code\":-1,\"data\":\"登录过期\",\"msg\":\"请求失败\",\"success\":false}', '2019-11-13 13:56:02', '2019-11-13 13:56:04', '1735', null, null, '2019-11-13 13:56:04', null, '2019-11-13 13:56:04', null, null);
INSERT INTO `sys_task_log_t` VALUES ('354473255188578304', '346902598779158528', '1', '{\"code\":-1,\"data\":\"登录过期\",\"msg\":\"请求失败\",\"success\":false}', '2019-11-13 13:57:00', '2019-11-13 13:57:00', '24', null, null, '2019-11-13 13:57:00', null, '2019-11-13 13:57:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('354473506746155008', '346902598779158528', '1', '{\"code\":-1,\"data\":\"登录过期\",\"msg\":\"请求失败\",\"success\":false}', '2019-11-13 13:58:00', '2019-11-13 13:58:00', '27', null, null, '2019-11-13 13:58:00', null, '2019-11-13 13:58:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('354473758408589312', '346902598779158528', '1', '{\"code\":-1,\"data\":\"登录过期\",\"msg\":\"请求失败\",\"success\":false}', '2019-11-13 13:59:00', '2019-11-13 13:59:00', '28', null, null, '2019-11-13 13:59:00', null, '2019-11-13 13:59:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('354474010045857792', '346902598779158528', '1', '{\"code\":-1,\"data\":\"登录过期\",\"msg\":\"请求失败\",\"success\":false}', '2019-11-13 14:00:00', '2019-11-13 14:00:00', '22', null, null, '2019-11-13 14:00:00', null, '2019-11-13 14:00:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('354474261636988928', '346902598779158528', '1', '{\"code\":-1,\"data\":\"登录过期\",\"msg\":\"请求失败\",\"success\":false}', '2019-11-13 14:01:00', '2019-11-13 14:01:00', '11', null, null, '2019-11-13 14:01:00', null, '2019-11-13 14:01:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('354474513370726400', '346902598779158528', '1', '{\"code\":-1,\"data\":\"登录过期\",\"msg\":\"请求失败\",\"success\":false}', '2019-11-13 14:02:00', '2019-11-13 14:02:00', '28', null, null, '2019-11-13 14:02:00', null, '2019-11-13 14:02:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('354474812089057280', '346902598779158528', '1', '{\"code\":-1,\"data\":\"登录过期\",\"msg\":\"请求失败\",\"success\":false}', '2019-11-13 14:03:00', '2019-11-13 14:03:11', '11248', null, null, '2019-11-13 14:03:11', null, '2019-11-13 14:03:11', null, null);
INSERT INTO `sys_task_log_t` VALUES ('354475026577375232', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-11-13 14:04:00', '2019-11-13 14:04:02', '2386', null, null, '2019-11-13 14:04:02', null, '2019-11-13 14:04:02', null, null);
INSERT INTO `sys_task_log_t` VALUES ('354475268337057792', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-11-13 14:05:00', '2019-11-13 14:05:00', '26', null, null, '2019-11-13 14:05:00', null, '2019-11-13 14:05:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('354475519936577536', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-11-13 14:06:00', '2019-11-13 14:06:00', '14', null, null, '2019-11-13 14:06:00', null, '2019-11-13 14:06:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('354475771607400448', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-11-13 14:07:00', '2019-11-13 14:07:00', '15', null, null, '2019-11-13 14:07:00', null, '2019-11-13 14:07:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('354476023269834752', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-11-13 14:08:00', '2019-11-13 14:08:00', '15', null, null, '2019-11-13 14:08:00', null, '2019-11-13 14:08:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('354476274907103232', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-11-13 14:09:00', '2019-11-13 14:09:00', '11', null, null, '2019-11-13 14:09:00', null, '2019-11-13 14:09:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('354476526628257792', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-11-13 14:10:00', '2019-11-13 14:10:00', '24', null, null, '2019-11-13 14:10:00', null, '2019-11-13 14:10:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('354476778307469312', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-11-13 14:11:00', '2019-11-13 14:11:00', '27', null, null, '2019-11-13 14:11:00', null, '2019-11-13 14:11:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('354477029894406144', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-11-13 14:12:00', '2019-11-13 14:12:00', '15', null, null, '2019-11-13 14:12:00', null, '2019-11-13 14:12:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('354477281611366400', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-11-13 14:13:00', '2019-11-13 14:13:00', '27', null, null, '2019-11-13 14:13:00', null, '2019-11-13 14:13:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('354477533265412096', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-11-13 14:14:00', '2019-11-13 14:14:00', '26', null, null, '2019-11-13 14:14:00', null, '2019-11-13 14:14:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('354477784869126144', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-11-13 14:15:00', '2019-11-13 14:15:00', '15', null, null, '2019-11-13 14:15:00', null, '2019-11-13 14:15:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('354478036535754752', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-11-13 14:16:00', '2019-11-13 14:16:00', '17', null, null, '2019-11-13 14:16:00', null, '2019-11-13 14:16:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('354478288168828928', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-11-13 14:17:00', '2019-11-13 14:17:00', '11', null, null, '2019-11-13 14:17:00', null, '2019-11-13 14:17:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('354478539889983488', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-11-13 14:18:00', '2019-11-13 14:18:00', '26', null, null, '2019-11-13 14:18:00', null, '2019-11-13 14:18:00', null, null);
INSERT INTO `sys_task_log_t` VALUES ('354512080430448640', '346902598779158528', '1', '{\"code\":0,\"msg\":\"success\",\"data\":null,\"traceId\":null,\"success\":true}', '2019-11-13 16:31:03', '2019-11-13 16:31:17', '13441', null, null, '2019-11-13 16:31:17', null, '2019-11-13 16:31:17', null, null);

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
INSERT INTO `sys_task_t` VALUES ('346902598779158528', 'job', '0 * * * * ?', 'http://localhost:8090/job/task/job', '0', '', '', '', '2019-10-23 16:33:55', '', '2019-11-15 18:57:29', '1', '');

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
INSERT INTO `sys_user_t` VALUES ('303106967935619072', '佟丽娅', '0000000001', '1', '303106948964782080', '8FEF13C67B2F87E513492A861EDCA67089F3E7A6AB081B625C2DDA03', '0', '172.29.144.129', '2019-12-26 14:32:25', 'user_head', '1569654307169', '\\2a86008d264647d68d1d649bd54e2982\\8333410d68144493ac3342b4c9ba4f67.jpg', '', '2019-07-24 20:31:01', '', '2019-12-26 14:32:25', '1', '');
INSERT INTO `sys_user_t` VALUES ('338122729266102272', '细个呢', '07555', '1', '338122550211264512', '6912D5ED812358C9FEE33E3AC047BA1F8454C912E462E619B5D5121E', '0', '172.29.144.129', '2019-09-29 11:15:40', '', '', '', '', '2019-09-29 11:05:51', '', '2019-09-29 11:15:40', '1', '');
INSERT INTO `sys_user_t` VALUES ('338125654218522624', 'idid', '123456', '1', '338125469321019392', '17488EA357A564C0E41BF10D85B597D6A056826FEB73598442C3D5FF', '0', '172.29.144.129', '2019-12-26 14:32:15', 'user_head', '1569727033682', '\\c495e634ab614045929b421f3839dcac\\5866e24e84064e36b984260b8b53da57.jpg', '', '2019-09-29 11:17:28', '', '2019-12-26 14:32:15', '1', '');
INSERT INTO `sys_user_t` VALUES ('338126485835759616', '1', '1', '1', '338125469321019392', '066CF4409F5B0D3755BAEB373D075561D0E49C181042477A92D2F069', '0', '172.29.144.129', '2019-09-29 11:21:02', '', '', '', '', '2019-09-29 11:20:47', '', '2019-09-29 11:21:02', '1', '');
INSERT INTO `sys_user_t` VALUES ('338126770465423360', '2', '2', '1', '338125469321019392', '1EA3944F7842D223AC37EC533EA5ADC45751941B41107409D1813E26', '0', '172.29.144.129', '2019-09-29 11:22:27', '', '', '', '', '2019-09-29 11:21:54', '', '2019-09-29 11:22:27', '1', '');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL DEFAULT '',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------

-- ----------------------------
-- Table structure for war_warehouse_t
-- ----------------------------
DROP TABLE IF EXISTS `war_warehouse_t`;
CREATE TABLE `war_warehouse_t` (
  `ID` varchar(32) NOT NULL COMMENT 'ID',
  `CODE` varchar(32) DEFAULT '' COMMENT '编码',
  `NAME` varchar(32) DEFAULT '' COMMENT '名称',
  `STATE` varchar(2) DEFAULT '' COMMENT '状态',
  `CREATED_BY` varchar(32) DEFAULT '' COMMENT '创建人',
  `CREATION_DATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `UPDATED_BY` varchar(32) DEFAULT '' COMMENT '修改人',
  `UPDATION_DATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `ENABLED_FLAG` int(1) DEFAULT '1' COMMENT '是否禁用',
  `TRACE_ID` varchar(16) DEFAULT '' COMMENT '日志ID',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='仓库表';

-- ----------------------------
-- Records of war_warehouse_t
-- ----------------------------
