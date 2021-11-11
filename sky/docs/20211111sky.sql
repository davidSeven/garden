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

 Date: 11/11/2021 22:10:25
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for acc_account
-- ----------------------------
DROP TABLE IF EXISTS `acc_account`;
CREATE TABLE `acc_account`  (
  `id` int(11) NOT NULL,
  `create_by` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '创建人编号',
  `create_date` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_by` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '修改人编号',
  `update_date` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `version` int(11) NULL DEFAULT 0 COMMENT '版本号',
  `deleted` int(1) NULL DEFAULT 0 COMMENT '是否删除',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '备注',
  `trace_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '日志ID',
  `customer_code` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '客户编码',
  `currency` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '币种',
  `available_balance` decimal(20, 4) NULL DEFAULT 0.0000 COMMENT '可用余额',
  `freeze_balance` decimal(20, 4) NULL DEFAULT 0.0000 COMMENT '冻结余额',
  `balance` decimal(20, 4) NULL DEFAULT 0.0000 COMMENT '余额',
  `credit_balance` decimal(20, 4) NULL DEFAULT 0.0000 COMMENT '信用余额',
  `overdraw_balance` decimal(20, 4) NULL DEFAULT 0.0000 COMMENT '透支余额',
  `state` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '状态',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '账户信息' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for acc_account_freeze
-- ----------------------------
DROP TABLE IF EXISTS `acc_account_freeze`;
CREATE TABLE `acc_account_freeze`  (
  `id` int(11) NOT NULL,
  `create_by` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '创建人编号',
  `create_date` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_by` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '修改人编号',
  `update_date` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `version` int(11) NULL DEFAULT 0 COMMENT '版本号',
  `deleted` int(1) NULL DEFAULT 0 COMMENT '是否删除',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '备注',
  `trace_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '日志ID',
  `customer_code` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '客户编号',
  `currency` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '币种',
  `amount` decimal(20, 4) NULL DEFAULT 0.0000 COMMENT '金额',
  `state` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '状态',
  `source_type` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '来源类型',
  `source_no` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '来源单号',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '账户冻结信息' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for acc_account_statement
-- ----------------------------
DROP TABLE IF EXISTS `acc_account_statement`;
CREATE TABLE `acc_account_statement`  (
  `id` int(11) NOT NULL,
  `create_by` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '创建人编号',
  `create_date` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_by` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '修改人编号',
  `update_date` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `version` int(11) NULL DEFAULT 0 COMMENT '版本号',
  `deleted` int(1) NULL DEFAULT 0 COMMENT '是否删除',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '备注',
  `trace_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '日志ID',
  `customer_code` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '客户编号',
  `currency` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '币种',
  `amount` decimal(20, 4) NULL DEFAULT 0.0000 COMMENT '金额',
  `type` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '类型',
  `state` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '状态',
  `source_type` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '来源类型',
  `source_no` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '来源单号',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '账户流水信息' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for bas_customer
-- ----------------------------
DROP TABLE IF EXISTS `bas_customer`;
CREATE TABLE `bas_customer`  (
  `id` int(11) NOT NULL,
  `create_by` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '创建人编号',
  `create_date` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_by` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '修改人编号',
  `update_date` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `version` int(11) NULL DEFAULT 0 COMMENT '版本号',
  `deleted` int(1) NULL DEFAULT 0 COMMENT '是否删除',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '备注',
  `trace_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '日志ID',
  `customer_code` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '客户编码',
  `customer_name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '客户名称',
  `state` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '状态',
  `email` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '邮箱',
  `mobile_phone` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '手机',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '客户信息' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for bas_product
-- ----------------------------
DROP TABLE IF EXISTS `bas_product`;
CREATE TABLE `bas_product`  (
  `id` int(11) NOT NULL,
  `create_by` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '创建人编号',
  `create_date` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_by` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '修改人编号',
  `update_date` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `version` int(11) NULL DEFAULT 0 COMMENT '版本号',
  `deleted` int(1) NULL DEFAULT 0 COMMENT '是否删除',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '备注',
  `trace_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '日志ID',
  `code` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '编码',
  `name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '名称',
  `length` double(16, 4) NULL DEFAULT 0.0000 COMMENT '长(cm)',
  `width` double(16, 4) NULL DEFAULT 0.0000 COMMENT '长(cm)',
  `height` double(16, 4) NULL DEFAULT 0.0000 COMMENT '长(cm)',
  `volume` double(20, 4) NULL DEFAULT 0.0000 COMMENT '体积(cm3)',
  `weight` double(16, 4) NULL DEFAULT 0.0000 COMMENT '重量(g)',
  `customer_code` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '客户编码',
  `warehouse_code` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '仓库编码',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '产品信息' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for bas_warehouse
-- ----------------------------
DROP TABLE IF EXISTS `bas_warehouse`;
CREATE TABLE `bas_warehouse`  (
  `id` int(11) NOT NULL,
  `create_by` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '创建人编号',
  `create_date` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_by` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '修改人编号',
  `update_date` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `version` int(11) NULL DEFAULT 0 COMMENT '版本号',
  `deleted` int(1) NULL DEFAULT 0 COMMENT '是否删除',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '备注',
  `trace_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '日志ID',
  `warehouse_code` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '仓库编码',
  `warehouse_name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '仓库名称',
  `state` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '状态',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '仓库信息' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for del_order
-- ----------------------------
DROP TABLE IF EXISTS `del_order`;
CREATE TABLE `del_order`  (
  `id` int(11) NOT NULL,
  `create_by` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '创建人编号',
  `create_date` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_by` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '修改人编号',
  `update_date` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `version` int(11) NULL DEFAULT 0 COMMENT '版本号',
  `deleted` int(1) NULL DEFAULT 0 COMMENT '是否删除',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '备注',
  `trace_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '日志ID',
  `batch_no` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '批次号',
  `order_no` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '订单号',
  `box_no` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '箱号',
  `box_spec` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '每箱规格',
  `box_length` decimal(18, 2) NULL DEFAULT 0.00 COMMENT '每箱长',
  `box_width` decimal(18, 2) NULL DEFAULT 0.00 COMMENT '每箱宽',
  `box_height` decimal(18, 2) NULL DEFAULT 0.00 COMMENT '每箱高',
  `box_weight` decimal(18, 2) NULL DEFAULT 0.00 COMMENT '每箱重量',
  `box_volume` decimal(18, 2) NULL DEFAULT 0.00 COMMENT '每箱体积',
  `parcels_num` int(11) NULL DEFAULT 0 COMMENT '包裹数量',
  `parcels_spec` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '包裹规格',
  `parcels_weight` decimal(18, 3) NULL DEFAULT 0.000 COMMENT '包裹重量',
  `parcels_volume` decimal(18, 2) NULL DEFAULT 0.00 COMMENT '包裹体积',
  `warehouse_code` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '仓库编号',
  `site_code` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '网点编号',
  `cus_code` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '客户编号',
  `sender` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '发件人',
  `sender_phone` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '发件电话',
  `sender_postcode` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '发件人邮编',
  `sender_country` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '发件人国家',
  `sender_province` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '发件人省份',
  `sender_city` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '发件人城市',
  `sender_address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '发件人地址',
  `receiver` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '收件人',
  `receiver_phone` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '收件电话',
  `receiver_postcode` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '收件人邮编',
  `receiver_country` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '收件人国家',
  `receiver_province` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '收件人省份',
  `receiver_city` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '收件人城市',
  `receiver_address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '收件人地址',
  `channel` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '渠道',
  `service_code` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '服务代码',
  `dest_code` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '目的地代码',
  `declare_user_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '申报人',
  `declare_user_telephone` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '申报人电话',
  `declare_user` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '申报人',
  `declared_value` decimal(20, 4) NULL DEFAULT 0.0000 COMMENT '申报价值',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '订单信息' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for del_order_detail
-- ----------------------------
DROP TABLE IF EXISTS `del_order_detail`;
CREATE TABLE `del_order_detail`  (
  `id` int(11) NOT NULL,
  `create_by` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '创建人编号',
  `create_date` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_by` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '修改人编号',
  `update_date` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `version` int(11) NULL DEFAULT 0 COMMENT '版本号',
  `deleted` int(1) NULL DEFAULT 0 COMMENT '是否删除',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '备注',
  `trace_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '日志ID',
  `batch_no` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '批次号',
  `order_no` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '订单号',
  `box_no` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '箱号',
  `declared_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '申报品名',
  `product_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '品名',
  `declared_value` decimal(20, 4) NULL DEFAULT 0.0000 COMMENT '申报价值',
  `declared_qty` int(11) NULL DEFAULT 0 COMMENT '申报数量',
  `warehouse_code` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '仓库编号',
  `site_code` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '网点编号',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '订单明细信息' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for inv_inventory
-- ----------------------------
DROP TABLE IF EXISTS `inv_inventory`;
CREATE TABLE `inv_inventory`  (
  `id` int(11) NOT NULL,
  `create_by` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '创建人编号',
  `create_date` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_by` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '修改人编号',
  `update_date` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `version` int(11) NULL DEFAULT 0 COMMENT '版本号',
  `deleted` int(1) NULL DEFAULT 0 COMMENT '是否删除',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '备注',
  `trace_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '日志ID',
  `customer_code` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '客户编码',
  `warehouse_code` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '仓库编码',
  `product_code` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '产品编码',
  `quantity` decimal(16, 4) NULL DEFAULT 0.0000 COMMENT '数量',
  `warehouse_date` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '入库日期',
  `invoice_type` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '单据类型',
  `invoice_no` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '单据号',
  `invoice_line_no` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '单据行号',
  `batch_no` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '批次号',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '库存信息' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for inv_inventory_occupation
-- ----------------------------
DROP TABLE IF EXISTS `inv_inventory_occupation`;
CREATE TABLE `inv_inventory_occupation`  (
  `id` int(11) NOT NULL,
  `create_by` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '创建人编号',
  `create_date` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_by` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '修改人编号',
  `update_date` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `version` int(11) NULL DEFAULT 0 COMMENT '版本号',
  `deleted` int(1) NULL DEFAULT 0 COMMENT '是否删除',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '备注',
  `trace_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '日志ID',
  `customer_code` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '客户编码',
  `warehouse_code` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '仓库编码',
  `product_code` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '产品编码',
  `quantity` decimal(16, 4) NULL DEFAULT 0.0000 COMMENT '数量',
  `state` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '状态',
  `invoice_type` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '单据类型',
  `invoice_no` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '单据号',
  `invoice_line_no` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '单据行号',
  `batch_no` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '批次号',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '库存占用信息' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for inv_inventory_statement
-- ----------------------------
DROP TABLE IF EXISTS `inv_inventory_statement`;
CREATE TABLE `inv_inventory_statement`  (
  `id` int(11) NOT NULL,
  `create_by` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '创建人编号',
  `create_date` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_by` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '修改人编号',
  `update_date` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `version` int(11) NULL DEFAULT 0 COMMENT '版本号',
  `deleted` int(1) NULL DEFAULT 0 COMMENT '是否删除',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '备注',
  `trace_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '日志ID',
  `customer_code` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '客户编码',
  `warehouse_code` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '仓库编码',
  `product_code` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '产品编码',
  `quantity` decimal(16, 4) NULL DEFAULT 0.0000 COMMENT '数量',
  `invoice_type` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '单据类型',
  `invoice_no` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '单据号',
  `invoice_line_no` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '单据行号',
  `batch_no` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '批次号',
  `operate_type` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '操作类型',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '库存流水信息' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for rec_order
-- ----------------------------
DROP TABLE IF EXISTS `rec_order`;
CREATE TABLE `rec_order`  (
  `id` int(11) NOT NULL,
  `create_by` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '创建人编号',
  `create_date` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_by` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '修改人编号',
  `update_date` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `version` int(11) NULL DEFAULT 0 COMMENT '版本号',
  `deleted` int(1) NULL DEFAULT 0 COMMENT '是否删除',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '备注',
  `trace_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '日志ID',
  `customer_code` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '客户编码',
  `warehouse_code` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '仓库编码',
  `order_no` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '订单号',
  `batch_no` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '批次号',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '入库单信息' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for rec_order_detail
-- ----------------------------
DROP TABLE IF EXISTS `rec_order_detail`;
CREATE TABLE `rec_order_detail`  (
  `id` int(11) NOT NULL,
  `create_by` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '创建人编号',
  `create_date` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_by` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '修改人编号',
  `update_date` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `version` int(11) NULL DEFAULT 0 COMMENT '版本号',
  `deleted` int(1) NULL DEFAULT 0 COMMENT '是否删除',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '备注',
  `trace_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '日志ID',
  `customer_code` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '客户编码',
  `warehouse_code` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '仓库编码',
  `order_no` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '订单号',
  `line_no` int(6) NULL DEFAULT NULL COMMENT '行号',
  `batch_no` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '批次号',
  `product_code` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '产品编号',
  `quantity` decimal(16, 4) NULL DEFAULT 0.0000 COMMENT '数量',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '入库单明细信息' ROW_FORMAT = Dynamic;

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
) ENGINE = InnoDB AUTO_INCREMENT = 65 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '登录日志' ROW_FORMAT = Dynamic;

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
) ENGINE = InnoDB AUTO_INCREMENT = 102 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '登录访问日志' ROW_FORMAT = Dynamic;

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
) ENGINE = InnoDB AUTO_INCREMENT = 45 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '在线用户管理' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_request_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_request_log`;
CREATE TABLE `sys_request_log`  (
  `id` int(11) NOT NULL,
  `create_by` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '创建人编号',
  `create_date` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_by` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '修改人编号',
  `update_date` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `version` int(11) NULL DEFAULT 0 COMMENT '版本号',
  `deleted` int(1) NULL DEFAULT 0 COMMENT '是否删除',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '备注',
  `trace_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '日志ID',
  `span_id` int(11) NULL DEFAULT 0 COMMENT '跨度ID',
  `request_uri` varchar(512) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '请求路径',
  `request_method` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '请求方法',
  `request_header` varchar(512) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '请求header',
  `request_body` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '请求body',
  `request_time` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '请求时间',
  `response_header` varchar(512) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '响应header',
  `response_body` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '响应body',
  `response_time` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '响应时间',
  `request_consume_time` int(11) NULL DEFAULT 0 COMMENT '请求耗时',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '请求日志信息' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_serial_number
-- ----------------------------
DROP TABLE IF EXISTS `sys_serial_number`;
CREATE TABLE `sys_serial_number`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `create_by` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '创建人编号',
  `create_date` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_by` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '修改人编号',
  `update_date` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `version` int(11) NULL DEFAULT 0 COMMENT '版本号',
  `deleted` int(1) NULL DEFAULT 0 COMMENT '是否删除',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '备注',
  `trace_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '日志ID',
  `code` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '业务编码',
  `current_sequence` int(11) NULL DEFAULT 1 COMMENT '当前序列',
  `step` int(11) NULL DEFAULT 1 COMMENT '步长',
  `length` int(11) NULL DEFAULT 1 COMMENT '流水号长度',
  `prefix` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '前缀',
  `type` int(11) NULL DEFAULT 1 COMMENT '流水号类型',
  `template` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '模板',
  `cycle_format` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '周期格式',
  `current_cycle` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '当前周期',
  `cache_size` int(11) NULL DEFAULT 1 COMMENT '缓存数量',
  `converts` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '转换器',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 25 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '流水号信息' ROW_FORMAT = Dynamic;

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

SET FOREIGN_KEY_CHECKS = 1;
