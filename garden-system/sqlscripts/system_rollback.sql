drop index IDX_SYS_USER_T_CODE on SYS_USER_T;
drop table if exists SYS_USER_T;

drop index IDX_SYS_ROLE_T_CODE on SYS_ROLE_T;
drop table if exists SYS_ROLE_T;

drop index IDX_SYS_GROUP_T_CODE on SYS_GROUP_T;
drop table if exists SYS_GROUP_T;

drop index IDX_SYS_USER_ROLE_T_ROLE_ID on SYS_USER_ROLE_T;
drop index IDX_SYS_USER_ROLE_T_USER_ID on SYS_USER_ROLE_T;
drop table if exists SYS_USER_ROLE_T;

drop index IDX_SYS_ROLE_GROUP_T_GROUP_ID on SYS_ROLE_GROUP_T;
drop index IDX_SYS_ROLE_GROUP_T_ROLE_ID on SYS_ROLE_GROUP_T;
drop table if exists SYS_ROLE_GROUP_T;

drop index IDX_SYS_GROUP_USER_T_USER_ID on SYS_GROUP_USER_T;
drop index IDX_SYS_GROUP_USER_T_GROUP_ID on SYS_GROUP_USER_T;
drop table if exists SYS_GROUP_USER_T;

drop table if exists SYS_MENU_T;
drop table if exists SYS_FUNCTION_T;
drop index IDX_SYS_ROLE_MENU_T_USER_ID on SYS_ROLE_MENU_T;
drop index IDX_SYS_ROLE_MENU_T_ROLE_ID on SYS_ROLE_MENU_T;
drop table if exists SYS_ROLE_MENU_T;
drop index IDX_SYS_ROLE_FUNCTION_T_USER_ID on SYS_ROLE_FUNCTION_T;
drop index IDX_SYS_ROLE_FUNCTION_T_ROLE_ID on SYS_ROLE_FUNCTION_T;
drop table if exists SYS_ROLE_FUNCTION_T;

drop index IDX_SYS_LOOKUP_T_CODE on SYS_LOOKUP_T;
drop table if exists SYS_LOOKUP_T;
drop index IDX_SYS_LOOKUP_ITEM_T_PARENT_CODE on SYS_LOOKUP_ITEM_T;
drop index IDX_SYS_LOOKUP_ITEM_T_CODE on SYS_LOOKUP_ITEM_T;
drop table if exists SYS_LOOKUP_ITEM_T;