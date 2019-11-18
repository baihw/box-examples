
/**
 * box_example数据库初始化。
 * url: jdbc:mysql://127.0.0.1:3306/box_example?useUnicode=true&characterEncoding=utf8mb4&useSSL=false&useAffectedRows=true&useOldAliasMetadataBehavior=true
 * user: box
 * password: box
 **/
use mysql;
drop database if exists box_example;
create database if not exists box_example default character set=utf8mb4 default collate=utf8mb4_general_ci;
grant all privileges on box_example.* to box@'%' identified by 'box';
flush privileges ;
use box_example;

-- ----------------------------
-- 用户信息表
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` char(32) not null comment '唯一标识',
  `user_name` varchar(64) NOT NULL DEFAULT '' COMMENT '用户名称',
  `user_pwd` varchar(64) NOT NULL DEFAULT '' COMMENT '用户密码',
  `nick_name` varchar(64) NOT NULL DEFAULT '' COMMENT '用户昵称',
  `sex` int(1) NOT NULL DEFAULT '0' COMMENT '性别 0:女 1:男',
  `email` varchar(64) NOT NULL DEFAULT '' COMMENT '邮箱',
  `mobile` varchar(24) NOT NULL DEFAULT '' COMMENT '移动电话',
  `create_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP comment '记录创建时间',
  `create_user` char(32) default null comment '记录创建用户',
  `update_time` TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP comment '记录修改时间',
  `update_user` char(32) default null comment '记录修改用户',
  `is_deleted` tinyint(1) unsigned NOT NULL DEFAULT 0 COMMENT '标记已删除 0:否 1:是',
  PRIMARY KEY (`id`),
  unique key `uni_user_name`(`user_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户基础表';

-- -------------------------------------------
--  角色信息表
-- -------------------------------------------
drop table if exists `sys_role`;
create table if not exists `sys_role`
(	`id` char(32) not null comment '唯一标识',
	`role_name` varchar(64) not null comment '角色名称',
	`role_memo` varchar(128) default null comment '角色描述',
	`create_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP comment '记录创建时间',
        `create_user` char(32) default null comment '记录创建用户',
        `update_time` TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP comment '记录修改时间',
        `update_user` char(32) default null comment '记录修改用户',
        `is_deleted` tinyint(1) unsigned NOT NULL DEFAULT 0 COMMENT '标记已删除 0:否 1:是',
	primary key(`id`),
        unique key `uni_role_name`(`role_name`)
)engine=innodb default charset=utf8mb4 collate=utf8mb4_general_ci COMMENT='角色信息表';

-- -------------------------------------------
--  用户角色关系表
-- -------------------------------------------
drop table if exists `sys_user_role_rel`;
create table if not exists `sys_user_role_rel` 
(	`id` char(32) not null comment '唯一标识',
	`user_id` char(32) not null comment '用户信息表唯一标识',
        `role_id` char(32) not null comment '角色信息表唯一标识',
	`create_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP comment '记录创建时间',
        `create_user` char(32) default null comment '记录创建用户',
        `update_time` TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP comment '记录修改时间',
        `update_user` char(32) default null comment '记录修改用户',
        `is_deleted` tinyint(1) unsigned NOT NULL DEFAULT 0 COMMENT '标记已删除 0:否 1:是',
	primary key(`id`),
        unique key `uni_user_id_role_id`(`user_id`, `role_id`)
)engine=innodb default charset=utf8mb4 collate=utf8mb4_general_ci COMMENT='用户角色关系表';

-- -------------------------------------------
--  权限信息表
-- -------------------------------------------
drop table if exists `sys_permission`;
create table if not exists `sys_permission`
(	`id` char(32) not null comment '唯一标识',
	`permission_code` varchar(64) not null comment '权限编码',
	`permission_type` char(2) not null default 01 comment '权限类别（01:自定义, 02:路径, 03:菜单）',
	`permission_memo` varchar(128) default null comment '权限描述',
	`create_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP comment '记录创建时间',
        `create_user` char(32) default null comment '记录创建用户',
        `update_time` TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP comment '记录修改时间',
        `update_user` char(32) default null comment '记录修改用户',
        `is_deleted` tinyint(1) unsigned NOT NULL DEFAULT 0 COMMENT '标记已删除 0:否 1:是',
	primary key(`id`),
        unique key `uni_permission_code`(`permission_code`),
        unique key `uni_permission_type_permission_code`(`permission_type`, `permission_code`)
)engine=innodb default charset=utf8mb4 collate=utf8mb4_general_ci COMMENT='权限信息表';

-- -------------------------------------------
--  角色权限关系表
-- -------------------------------------------
drop table if exists `sys_role_permission_rel`;
create table if not exists `sys_role_permission_rel`
(	`id` char(32) not null comment '唯一标识',
	`role_id` char(32) not null comment '角色信息表唯一标识',
	`permission_id` char(32) not null comment '权限信息表唯一标识',
	`create_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP comment '记录创建时间',
        `create_user` char(32) default null comment '记录创建用户',
        `update_time` TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP comment '记录修改时间',
        `update_user` char(32) default null comment '记录修改用户',
        `is_deleted` tinyint(1) unsigned NOT NULL DEFAULT 0 COMMENT '标记已删除 0:否 1:是',
	primary key(`id`),
        unique key `uni_role_id_permission_id`(`role_id`, `permission_id`)
)engine=innodb default charset=utf8mb4 collate=utf8mb4_general_ci COMMENT='角色权限关系表';


-- 插入测试数据
insert into sys_user(id, user_name, user_pwd) value( '89232296e8a911e9b3700242ac12010a', 'admin', 'admin');
insert into sys_user(id, user_name, user_pwd) value( '0a19ba58e8ab11e9b3700242ac12010a', 'guest', '123456');

insert into sys_role(id, role_name, role_memo) value( '87feedeae99911e99cb80242ac12010a', 'admin', '管理员');
insert into sys_role(id, role_name, role_memo) value( 'add26716e99911e99cb80242ac12010a', 'guest', '来宾');

insert into sys_permission(id, permission_code, permission_type) value('cde9ed55e99e11e99cb80242ac12010a', 'common_add', '01');
insert into sys_permission(id, permission_code, permission_type) value('d3d3ebb3e99e11e99cb80242ac12010a', 'common_edit', '01');
insert into sys_permission(id, permission_code, permission_type) value('d9775fb9e99e11e99cb80242ac12010a', 'common_delete', '01');
insert into sys_permission(id, permission_code, permission_type) value('dd0d07dee99e11e99cb80242ac12010a', 'common_read', '01');

insert into sys_user_role_rel(id, user_id, role_id) value('e062d8aee99e11e99cb80242ac12010a', '89232296e8a911e9b3700242ac12010a', '87feedeae99911e99cb80242ac12010a');
insert into sys_user_role_rel(id, user_id, role_id) value('e70b8a96e99e11e99cb80242ac12010a', '0a19ba58e8ab11e9b3700242ac12010a', 'add26716e99911e99cb80242ac12010a');

-- admin - all
insert into sys_role_permission_rel(id, role_id, permission_id) value('e330e8eae99f11e99cb80242ac12010a', '87feedeae99911e99cb80242ac12010a', 'cde9ed55e99e11e99cb80242ac12010a');
insert into sys_role_permission_rel(id, role_id, permission_id) value('eb2d9253e99f11e99cb80242ac12010a', '87feedeae99911e99cb80242ac12010a', 'd3d3ebb3e99e11e99cb80242ac12010a');
insert into sys_role_permission_rel(id, role_id, permission_id) value('860fbfcfe9a011e99cb80242ac12010a', '87feedeae99911e99cb80242ac12010a', 'd9775fb9e99e11e99cb80242ac12010a');
insert into sys_role_permission_rel(id, role_id, permission_id) value('b9a469b1e9a011e99cb80242ac12010a', '87feedeae99911e99cb80242ac12010a', 'dd0d07dee99e11e99cb80242ac12010a');
-- guest - common_read
insert into sys_role_permission_rel(id, role_id, permission_id) value('f697321be9a011e99cb80242ac12010a', 'add26716e99911e99cb80242ac12010a', 'dd0d07dee99e11e99cb80242ac12010a');












