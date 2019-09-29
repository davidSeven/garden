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

