drop index IDX_SYS_USER_CODE on SYS_USER;

drop table if exists SYS_USER;

/*==============================================================*/
/* Table: SYS_USER                                              */
/*==============================================================*/
create table SYS_USER
(
   ID                   VARCHAR(32) not null comment 'ID',
   NAME                 VARCHAR(32) default '' comment '名称',
   CODE                 VARCHAR(16) default '' comment '编号',
   STATE                VARCHAR(2) default '' comment '状态',
   CREATED_BY           VARCHAR(32) default '' comment '创建人',
   CREATION_DATE        TIMESTAMP default CURRENT_TIMESTAMP comment '创建时间',
   UPDATED_BY           VARCHAR(32) default '' comment '修改人',
   UPDATION_DATE        TIMESTAMP default CURRENT_TIMESTAMP comment '修改时间',
   ENABLED_FLAG         INT(1) default 1 comment '是否禁用',
   TRACE_ID             VARCHAR(16) default '' comment '日志ID',
   primary key (ID)
);

alter table SYS_USER comment '用户表';

/*==============================================================*/
/* Index: IDX_SYS_USER_CODE                                     */
/*==============================================================*/
create index IDX_SYS_USER_CODE on SYS_USER
(
   CODE
);
