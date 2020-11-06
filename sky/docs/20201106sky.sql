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
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COMMENT='数据字典';

CREATE TABLE `sys_login_log` (
  `state` varchar(20) DEFAULT '' COMMENT '状态',
  `login_ip` varchar(20) DEFAULT '' COMMENT '登录IP',
  `login_time` datetime DEFAULT NULL COMMENT '登录时间',
  `login_code` varchar(100) DEFAULT '' COMMENT '登录帐号',
  `login_password` varchar(100) DEFAULT '' COMMENT '登录密码',
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
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='登录日志';

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

