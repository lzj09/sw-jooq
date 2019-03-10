create table  `jooq`.`user` (
  `user_id` varchar(64) not null default '' comment '用户id',
  `name` varchar(200) default null comment '用户名',
  `intro` varchar(200) default null comment '简介',
  primary key (`user_id`)
) engine=innodb default charset=utf8 comment='用户信息';