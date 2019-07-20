/*==============================================================*/
/* Table: SYS_USER_T                                            */
/*==============================================================*/
create table SYS_USER_T
(
   ID                   VARCHAR(32) not null comment 'ID',
   NAME                 VARCHAR(32) default '' comment '名称',
   CODE                 VARCHAR(32) default '' comment '编号',
   STATE                VARCHAR(2) default '' comment '状态',
   CREATED_BY           VARCHAR(32) default '' comment '创建人',
   CREATION_DATE        TIMESTAMP default CURRENT_TIMESTAMP comment '创建时间',
   UPDATED_BY           VARCHAR(32) default '' comment '修改人',
   UPDATION_DATE        TIMESTAMP default CURRENT_TIMESTAMP comment '修改时间',
   ENABLED_FLAG         INT(1) default 1 comment '是否禁用',
   TRACE_ID             VARCHAR(16) default '' comment '日志ID',
   primary key (ID)
);
alter table SYS_USER_T comment '用户表';
/*==============================================================*/
/* Index: IDX_SYS_USER_T_CODE                                   */
/*==============================================================*/
create index IDX_SYS_USER_T_CODE on SYS_USER_T (CODE);

/*==============================================================*/
/* Table: SYS_ROLE_T                                            */
/*==============================================================*/
create table SYS_ROLE_T
(
   ID                   VARCHAR(32) not null comment 'ID',
   NAME                 VARCHAR(32) default '' comment '名称',
   CODE                 VARCHAR(32) default '' comment '编号',
   STATE                VARCHAR(2) default '' comment '状态',
   CREATED_BY           VARCHAR(32) default '' comment '创建人',
   CREATION_DATE        TIMESTAMP default CURRENT_TIMESTAMP comment '创建时间',
   UPDATED_BY           VARCHAR(32) default '' comment '修改人',
   UPDATION_DATE        TIMESTAMP default CURRENT_TIMESTAMP comment '修改时间',
   ENABLED_FLAG         INT(1) default 1 comment '是否禁用',
   TRACE_ID             VARCHAR(16) default '' comment '日志ID',
   primary key (ID)
);
alter table SYS_ROLE_T comment '角色表';
/*==============================================================*/
/* Index: IDX_SYS_ROLE_T_CODE                                   */
/*==============================================================*/
create index IDX_SYS_ROLE_T_CODE on SYS_ROLE_T (CODE);

/*==============================================================*/
/* Table: SYS_GROUP_T                                           */
/*==============================================================*/
create table SYS_GROUP_T
(
   ID                   VARCHAR(32) not null comment 'ID',
   NAME                 VARCHAR(32) default '' comment '名称',
   CODE                 VARCHAR(32) default '' comment '编号',
   STATE                VARCHAR(2) default '' comment '状态',
   CREATED_BY           VARCHAR(32) default '' comment '创建人',
   CREATION_DATE        TIMESTAMP default CURRENT_TIMESTAMP comment '创建时间',
   UPDATED_BY           VARCHAR(32) default '' comment '修改人',
   UPDATION_DATE        TIMESTAMP default CURRENT_TIMESTAMP comment '修改时间',
   ENABLED_FLAG         INT(1) default 1 comment '是否禁用',
   TRACE_ID             VARCHAR(16) default '' comment '日志ID',
   primary key (ID)
);
alter table SYS_GROUP_T comment '群组表';
/*==============================================================*/
/* Index: IDX_SYS_GROUP_T_CODE                                  */
/*==============================================================*/
create index IDX_SYS_GROUP_T_CODE on SYS_GROUP_T (CODE);



/*==============================================================*/
/* Table: SYS_USER_ROLE_T                                       */
/*==============================================================*/
create table SYS_USER_ROLE_T
(
   USER_ID              VARCHAR(32) comment '用户ID',
   ROLE_ID              VARCHAR(32) comment '角色ID'
);
alter table SYS_USER_ROLE_T comment '用户角色关联表';
/*==============================================================*/
/* Index: IDX_SYS_USER_ROLE_T_USER_ID                           */
/*==============================================================*/
create index IDX_SYS_USER_ROLE_T_USER_ID on SYS_USER_ROLE_T (USER_ID);
/*==============================================================*/
/* Index: IDX_SYS_USER_ROLE_T_ROLE_ID                           */
/*==============================================================*/
create index IDX_SYS_USER_ROLE_T_ROLE_ID on SYS_USER_ROLE_T (ROLE_ID);


/*==============================================================*/
/* Table: SYS_ROLE_GROUP_T                                      */
/*==============================================================*/
create table SYS_ROLE_GROUP_T
(
   ROLE_ID              VARCHAR(32) comment '角色ID',
   GROUP_ID             VARCHAR(32) comment '群组ID'
);
alter table SYS_ROLE_GROUP_T comment '角色群组关联表';
/*==============================================================*/
/* Index: IDX_SYS_ROLE_GROUP_T_ROLE_ID                          */
/*==============================================================*/
create index IDX_SYS_ROLE_GROUP_T_ROLE_ID on SYS_ROLE_GROUP_T (ROLE_ID);
/*==============================================================*/
/* Index: IDX_SYS_ROLE_GROUP_T_GROUP_ID                         */
/*==============================================================*/
create index IDX_SYS_ROLE_GROUP_T_GROUP_ID on SYS_ROLE_GROUP_T (GROUP_ID);

/*==============================================================*/
/* Table: SYS_GROUP_USER_T                                      */
/*==============================================================*/
create table SYS_GROUP_USER_T
(
   GROUP_ID             VARCHAR(32) comment '群组ID',
   USER_ID              VARCHAR(32) comment '用户ID'
);
alter table SYS_GROUP_USER_T comment '群组用户关联表';
/*==============================================================*/
/* Index: IDX_SYS_GROUP_USER_T_GROUP_ID                         */
/*==============================================================*/
create index IDX_SYS_GROUP_USER_T_GROUP_ID on SYS_GROUP_USER_T (GROUP_ID);
/*==============================================================*/
/* Index: IDX_SYS_GROUP_USER_T_USER_ID                          */
/*==============================================================*/
create index IDX_SYS_GROUP_USER_T_USER_ID on SYS_GROUP_USER_T (USER_ID);

/*==============================================================*/
/* Table: SYS_MENU_T                                            */
/*==============================================================*/
create table SYS_MENU_T
(
   ID                   VARCHAR(32) not null comment 'ID',
   NAME                 VARCHAR(32) default '' comment '名称',
   CODE                 VARCHAR(32) default '' comment '编号',
   STATE                VARCHAR(2) default '' comment '状态',
   PATH                 VARCHAR(128) default '' comment '路径',
   SORTS                INT(4) default 1 comment '顺序',
   TYPE                 VARCHAR(2) default '' comment '类型',
   PARENT_ID            VARCHAR(32) default '' comment '父级ID',
   CREATED_BY           VARCHAR(32) default '' comment '创建人',
   CREATION_DATE        TIMESTAMP default CURRENT_TIMESTAMP comment '创建时间',
   UPDATED_BY           VARCHAR(32) default '' comment '修改人',
   UPDATION_DATE        TIMESTAMP default CURRENT_TIMESTAMP comment '修改时间',
   ENABLED_FLAG         INT(1) default 1 comment '是否禁用',
   TRACE_ID             VARCHAR(16) default '' comment '日志ID',
   primary key (ID)
);
alter table SYS_MENU_T comment '菜单表';

/*==============================================================*/
/* Table: SYS_FUNCTION_T                                        */
/*==============================================================*/
create table SYS_FUNCTION_T
(
   ID                   VARCHAR(32) not null comment 'ID',
   MENU_ID              VARCHAR(32) default '' comment '菜单ID',
   NAME                 VARCHAR(32) default '' comment '名称',
   CODE                 VARCHAR(128) default '' comment '编号',
   STATE                VARCHAR(2) default '' comment '状态',
   URL                  VARCHAR(128) default '' comment '路径',
   CREATED_BY           VARCHAR(32) default '' comment '创建人',
   CREATION_DATE        TIMESTAMP default CURRENT_TIMESTAMP comment '创建时间',
   UPDATED_BY           VARCHAR(32) default '' comment '修改人',
   UPDATION_DATE        TIMESTAMP default CURRENT_TIMESTAMP comment '修改时间',
   ENABLED_FLAG         INT(1) default 1 comment '是否禁用',
   TRACE_ID             VARCHAR(16) default '' comment '日志ID',
   primary key (ID)
);
alter table SYS_FUNCTION_T comment '功能表';

/*==============================================================*/
/* Table: SYS_ROLE_MENU_T                                       */
/*==============================================================*/
create table SYS_ROLE_MENU_T
(
   ROLE_ID              VARCHAR(32) comment '角色ID',
   MENU_ID              VARCHAR(32) comment '菜单ID'
);
alter table SYS_ROLE_MENU_T comment '角色菜单关联表';
/*==============================================================*/
/* Index: IDX_SYS_ROLE_MENU_T_ROLE_ID                           */
/*==============================================================*/
create index IDX_SYS_ROLE_MENU_T_ROLE_ID on SYS_ROLE_MENU_T (ROLE_ID);
/*==============================================================*/
/* Index: IDX_SYS_ROLE_MENU_T_USER_ID                           */
/*==============================================================*/
create index IDX_SYS_ROLE_MENU_T_USER_ID on SYS_ROLE_MENU_T (MENU_ID);

/*==============================================================*/
/* Table: SYS_ROLE_FUNCTION_T                                   */
/*==============================================================*/
create table SYS_ROLE_FUNCTION_T
(
   ROLE_ID              VARCHAR(32) comment '角色ID',
   FUNCTION_ID          VARCHAR(32) comment '功能ID'
);
alter table SYS_ROLE_FUNCTION_T comment '角色菜单功能关联表';
/*==============================================================*/
/* Index: IDX_SYS_ROLE_FUNCTION_T_ROLE_ID                       */
/*==============================================================*/
create index IDX_SYS_ROLE_FUNCTION_T_ROLE_ID on SYS_ROLE_FUNCTION_T (ROLE_ID);
/*==============================================================*/
/* Index: IDX_SYS_ROLE_FUNCTION_T_USER_ID                       */
/*==============================================================*/
create index IDX_SYS_ROLE_FUNCTION_T_USER_ID on SYS_ROLE_FUNCTION_T (FUNCTION_ID);

/*==============================================================*/
/* Table: SYS_LOOKUP_T                                          */
/*==============================================================*/
create table SYS_LOOKUP_T
(
   ID                   VARCHAR(32) not null comment 'ID',
   NAME                 VARCHAR(32) default '' comment '名称',
   CODE                 VARCHAR(32) default '' comment '编号',
   STATE                VARCHAR(2) default '' comment '状态',
   CREATED_BY           VARCHAR(32) default '' comment '创建人',
   CREATION_DATE        TIMESTAMP default CURRENT_TIMESTAMP comment '创建时间',
   UPDATED_BY           VARCHAR(32) default '' comment '修改人',
   UPDATION_DATE        TIMESTAMP default CURRENT_TIMESTAMP comment '修改时间',
   ENABLED_FLAG         INT(1) default 1 comment '是否禁用',
   TRACE_ID             VARCHAR(16) default '' comment '日志ID',
   primary key (ID)
);
alter table SYS_LOOKUP_T comment 'Lookup表';
/*==============================================================*/
/* Index: IDX_SYS_LOOKUP_T_CODE                                 */
/*==============================================================*/
create index IDX_SYS_LOOKUP_T_CODE on SYS_LOOKUP_T (CODE);

/*==============================================================*/
/* Table: SYS_LOOKUP_ITEM_T                                     */
/*==============================================================*/
create table SYS_LOOKUP_ITEM_T
(
   ID                   VARCHAR(32) not null comment 'ID',
   NAME                 VARCHAR(32) default '' comment '名称',
   CODE                 VARCHAR(32) default '' comment '编号',
   STATE                VARCHAR(2) default '' comment '状态',
   PARENT_CODE          VARCHAR(32) default '' comment '父级编号',
   PARENT_NAME          VARCHAR(32) default '' comment '父级名称',
   EXTEND_FIELD1        VARCHAR(255) default '' comment '扩展字段1',
   EXTEND_FIELD2        VARCHAR(255) default '' comment '扩展字段2',
   EXTEND_FIELD3        VARCHAR(255) default '' comment '扩展字段3',
   EXTEND_FIELD4        VARCHAR(255) default '' comment '扩展字段4',
   CREATED_BY           VARCHAR(32) default '' comment '创建人',
   CREATION_DATE        TIMESTAMP default CURRENT_TIMESTAMP comment '创建时间',
   UPDATED_BY           VARCHAR(32) default '' comment '修改人',
   UPDATION_DATE        TIMESTAMP default CURRENT_TIMESTAMP comment '修改时间',
   ENABLED_FLAG         INT(1) default 1 comment '是否禁用',
   TRACE_ID             VARCHAR(16) default '' comment '日志ID',
   primary key (ID)
);
alter table SYS_LOOKUP_ITEM_T comment 'LookupItem表';
/*==============================================================*/
/* Index: IDX_SYS_LOOKUP_ITEM_T_CODE                            */
/*==============================================================*/
create index IDX_SYS_LOOKUP_ITEM_T_CODE on SYS_LOOKUP_ITEM_T (CODE);
/*==============================================================*/
/* Index: IDX_SYS_LOOKUP_ITEM_T_PARENT_CODE                     */
/*==============================================================*/
create index IDX_SYS_LOOKUP_ITEM_T_PARENT_CODE on SYS_LOOKUP_ITEM_T (PARENT_CODE);
