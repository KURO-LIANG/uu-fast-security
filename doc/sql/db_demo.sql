CREATE TABLE `tb_模块名称_业务表`
(
    `业务表_id`      bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `version`     int(11)             NOT NULL DEFAULT 1 COMMENT '乐观锁标识',
    `dept_id`     bigint(20)          NOT NULL DEFAULT 0 COMMENT '部门id',
    `create_time` datetime                     DEFAULT current_timestamp() COMMENT '创建时间',
    `update_time` datetime                     DEFAULT current_timestamp() COMMENT '更新时间',
    `order_num`   int(5) unsigned     NOT NULL DEFAULT 0 COMMENT '排序',
    `del_flag`    tinyint(4)                   DEFAULT 0 COMMENT '删除标记  -1：已删除  0：正常',

    PRIMARY KEY (`业务表_id`) USING BTREE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  ROW_FORMAT = DYNAMIC COMMENT ='模块—业务名称';

