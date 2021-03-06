/*
 Navicat Premium Data Transfer

 Source Server         : 114ByNPC
 Source Server Type    : MariaDB
 Source Server Version : 100603
 Source Host           : localhost:2000
 Source Schema         : sc_test_community

 Target Server Type    : MariaDB
 Target Server Version : 100603
 File Encoding         : 65001

 Date: 17/07/2021 10:14:47
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for QRTZ_BLOB_TRIGGERS
-- ----------------------------
DROP TABLE IF EXISTS `QRTZ_BLOB_TRIGGERS`;
CREATE TABLE `QRTZ_BLOB_TRIGGERS` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `BLOB_DATA` blob DEFAULT NULL,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  KEY `SCHED_NAME` (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  CONSTRAINT `QRTZ_BLOB_TRIGGERS_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `QRTZ_TRIGGERS` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Records of QRTZ_BLOB_TRIGGERS
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for QRTZ_CALENDARS
-- ----------------------------
DROP TABLE IF EXISTS `QRTZ_CALENDARS`;
CREATE TABLE `QRTZ_CALENDARS` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `CALENDAR_NAME` varchar(200) NOT NULL,
  `CALENDAR` blob NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`CALENDAR_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Records of QRTZ_CALENDARS
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for QRTZ_CRON_TRIGGERS
-- ----------------------------
DROP TABLE IF EXISTS `QRTZ_CRON_TRIGGERS`;
CREATE TABLE `QRTZ_CRON_TRIGGERS` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `CRON_EXPRESSION` varchar(120) NOT NULL,
  `TIME_ZONE_ID` varchar(80) DEFAULT NULL,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  CONSTRAINT `QRTZ_CRON_TRIGGERS_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `QRTZ_TRIGGERS` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Records of QRTZ_CRON_TRIGGERS
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for QRTZ_FIRED_TRIGGERS
-- ----------------------------
DROP TABLE IF EXISTS `QRTZ_FIRED_TRIGGERS`;
CREATE TABLE `QRTZ_FIRED_TRIGGERS` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `ENTRY_ID` varchar(95) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `INSTANCE_NAME` varchar(200) NOT NULL,
  `FIRED_TIME` bigint(13) NOT NULL,
  `SCHED_TIME` bigint(13) NOT NULL,
  `PRIORITY` int(11) NOT NULL,
  `STATE` varchar(16) NOT NULL,
  `JOB_NAME` varchar(200) DEFAULT NULL,
  `JOB_GROUP` varchar(200) DEFAULT NULL,
  `IS_NONCONCURRENT` varchar(1) DEFAULT NULL,
  `REQUESTS_RECOVERY` varchar(1) DEFAULT NULL,
  PRIMARY KEY (`SCHED_NAME`,`ENTRY_ID`),
  KEY `IDX_QRTZ_FT_TRIG_INST_NAME` (`SCHED_NAME`,`INSTANCE_NAME`),
  KEY `IDX_QRTZ_FT_INST_JOB_REQ_RCVRY` (`SCHED_NAME`,`INSTANCE_NAME`,`REQUESTS_RECOVERY`),
  KEY `IDX_QRTZ_FT_J_G` (`SCHED_NAME`,`JOB_NAME`,`JOB_GROUP`),
  KEY `IDX_QRTZ_FT_JG` (`SCHED_NAME`,`JOB_GROUP`),
  KEY `IDX_QRTZ_FT_T_G` (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  KEY `IDX_QRTZ_FT_TG` (`SCHED_NAME`,`TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Records of QRTZ_FIRED_TRIGGERS
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for QRTZ_JOB_DETAILS
-- ----------------------------
DROP TABLE IF EXISTS `QRTZ_JOB_DETAILS`;
CREATE TABLE `QRTZ_JOB_DETAILS` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `JOB_NAME` varchar(200) NOT NULL,
  `JOB_GROUP` varchar(200) NOT NULL,
  `DESCRIPTION` varchar(250) DEFAULT NULL,
  `JOB_CLASS_NAME` varchar(250) NOT NULL,
  `IS_DURABLE` varchar(1) NOT NULL,
  `IS_NONCONCURRENT` varchar(1) NOT NULL,
  `IS_UPDATE_DATA` varchar(1) NOT NULL,
  `REQUESTS_RECOVERY` varchar(1) NOT NULL,
  `JOB_DATA` blob DEFAULT NULL,
  PRIMARY KEY (`SCHED_NAME`,`JOB_NAME`,`JOB_GROUP`),
  KEY `IDX_QRTZ_J_REQ_RECOVERY` (`SCHED_NAME`,`REQUESTS_RECOVERY`),
  KEY `IDX_QRTZ_J_GRP` (`SCHED_NAME`,`JOB_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Records of QRTZ_JOB_DETAILS
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for QRTZ_LOCKS
-- ----------------------------
DROP TABLE IF EXISTS `QRTZ_LOCKS`;
CREATE TABLE `QRTZ_LOCKS` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `LOCK_NAME` varchar(40) NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`LOCK_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Records of QRTZ_LOCKS
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for QRTZ_PAUSED_TRIGGER_GRPS
-- ----------------------------
DROP TABLE IF EXISTS `QRTZ_PAUSED_TRIGGER_GRPS`;
CREATE TABLE `QRTZ_PAUSED_TRIGGER_GRPS` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Records of QRTZ_PAUSED_TRIGGER_GRPS
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for QRTZ_SCHEDULER_STATE
-- ----------------------------
DROP TABLE IF EXISTS `QRTZ_SCHEDULER_STATE`;
CREATE TABLE `QRTZ_SCHEDULER_STATE` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `INSTANCE_NAME` varchar(200) NOT NULL,
  `LAST_CHECKIN_TIME` bigint(13) NOT NULL,
  `CHECKIN_INTERVAL` bigint(13) NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`INSTANCE_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Records of QRTZ_SCHEDULER_STATE
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for QRTZ_SIMPLE_TRIGGERS
-- ----------------------------
DROP TABLE IF EXISTS `QRTZ_SIMPLE_TRIGGERS`;
CREATE TABLE `QRTZ_SIMPLE_TRIGGERS` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `REPEAT_COUNT` bigint(7) NOT NULL,
  `REPEAT_INTERVAL` bigint(12) NOT NULL,
  `TIMES_TRIGGERED` bigint(10) NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  CONSTRAINT `QRTZ_SIMPLE_TRIGGERS_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `QRTZ_TRIGGERS` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Records of QRTZ_SIMPLE_TRIGGERS
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for QRTZ_SIMPROP_TRIGGERS
-- ----------------------------
DROP TABLE IF EXISTS `QRTZ_SIMPROP_TRIGGERS`;
CREATE TABLE `QRTZ_SIMPROP_TRIGGERS` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `STR_PROP_1` varchar(512) DEFAULT NULL,
  `STR_PROP_2` varchar(512) DEFAULT NULL,
  `STR_PROP_3` varchar(512) DEFAULT NULL,
  `INT_PROP_1` int(11) DEFAULT NULL,
  `INT_PROP_2` int(11) DEFAULT NULL,
  `LONG_PROP_1` bigint(20) DEFAULT NULL,
  `LONG_PROP_2` bigint(20) DEFAULT NULL,
  `DEC_PROP_1` decimal(13,4) DEFAULT NULL,
  `DEC_PROP_2` decimal(13,4) DEFAULT NULL,
  `BOOL_PROP_1` varchar(1) DEFAULT NULL,
  `BOOL_PROP_2` varchar(1) DEFAULT NULL,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  CONSTRAINT `QRTZ_SIMPROP_TRIGGERS_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `QRTZ_TRIGGERS` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Records of QRTZ_SIMPROP_TRIGGERS
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for QRTZ_TRIGGERS
-- ----------------------------
DROP TABLE IF EXISTS `QRTZ_TRIGGERS`;
CREATE TABLE `QRTZ_TRIGGERS` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `JOB_NAME` varchar(200) NOT NULL,
  `JOB_GROUP` varchar(200) NOT NULL,
  `DESCRIPTION` varchar(250) DEFAULT NULL,
  `NEXT_FIRE_TIME` bigint(13) DEFAULT NULL,
  `PREV_FIRE_TIME` bigint(13) DEFAULT NULL,
  `PRIORITY` int(11) DEFAULT NULL,
  `TRIGGER_STATE` varchar(16) NOT NULL,
  `TRIGGER_TYPE` varchar(8) NOT NULL,
  `START_TIME` bigint(13) NOT NULL,
  `END_TIME` bigint(13) DEFAULT NULL,
  `CALENDAR_NAME` varchar(200) DEFAULT NULL,
  `MISFIRE_INSTR` smallint(2) DEFAULT NULL,
  `JOB_DATA` blob DEFAULT NULL,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  KEY `IDX_QRTZ_T_J` (`SCHED_NAME`,`JOB_NAME`,`JOB_GROUP`),
  KEY `IDX_QRTZ_T_JG` (`SCHED_NAME`,`JOB_GROUP`),
  KEY `IDX_QRTZ_T_C` (`SCHED_NAME`,`CALENDAR_NAME`),
  KEY `IDX_QRTZ_T_G` (`SCHED_NAME`,`TRIGGER_GROUP`),
  KEY `IDX_QRTZ_T_STATE` (`SCHED_NAME`,`TRIGGER_STATE`),
  KEY `IDX_QRTZ_T_N_STATE` (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`,`TRIGGER_STATE`),
  KEY `IDX_QRTZ_T_N_G_STATE` (`SCHED_NAME`,`TRIGGER_GROUP`,`TRIGGER_STATE`),
  KEY `IDX_QRTZ_T_NEXT_FIRE_TIME` (`SCHED_NAME`,`NEXT_FIRE_TIME`),
  KEY `IDX_QRTZ_T_NFT_ST` (`SCHED_NAME`,`TRIGGER_STATE`,`NEXT_FIRE_TIME`),
  KEY `IDX_QRTZ_T_NFT_MISFIRE` (`SCHED_NAME`,`MISFIRE_INSTR`,`NEXT_FIRE_TIME`),
  KEY `IDX_QRTZ_T_NFT_ST_MISFIRE` (`SCHED_NAME`,`MISFIRE_INSTR`,`NEXT_FIRE_TIME`,`TRIGGER_STATE`),
  KEY `IDX_QRTZ_T_NFT_ST_MISFIRE_GRP` (`SCHED_NAME`,`MISFIRE_INSTR`,`NEXT_FIRE_TIME`,`TRIGGER_GROUP`,`TRIGGER_STATE`),
  CONSTRAINT `QRTZ_TRIGGERS_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `JOB_NAME`, `JOB_GROUP`) REFERENCES `QRTZ_JOB_DETAILS` (`SCHED_NAME`, `JOB_NAME`, `JOB_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Records of QRTZ_TRIGGERS
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for schedule_job
-- ----------------------------
DROP TABLE IF EXISTS `schedule_job`;
CREATE TABLE `schedule_job` (
  `job_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '??????id',
  `bean_name` varchar(200) DEFAULT NULL COMMENT 'spring bean??????',
  `params` varchar(2000) DEFAULT NULL COMMENT '??????',
  `cron_expression` varchar(100) DEFAULT NULL COMMENT 'cron?????????',
  `status` tinyint(4) DEFAULT NULL COMMENT '????????????  0?????????  1?????????',
  `remark` varchar(255) DEFAULT NULL COMMENT '??????',
  `create_time` datetime DEFAULT NULL COMMENT '????????????',
  `version` int(11) NOT NULL DEFAULT 0 COMMENT '?????????',
  PRIMARY KEY (`job_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb3 COMMENT='????????????';

-- ----------------------------
-- Records of schedule_job
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for schedule_job_log
-- ----------------------------
DROP TABLE IF EXISTS `schedule_job_log`;
CREATE TABLE `schedule_job_log` (
  `log_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '????????????id',
  `job_id` bigint(20) NOT NULL COMMENT '??????id',
  `bean_name` varchar(200) DEFAULT NULL COMMENT 'spring bean??????',
  `params` varchar(2000) DEFAULT NULL COMMENT '??????',
  `status` tinyint(4) NOT NULL COMMENT '????????????    0?????????    1?????????',
  `error` varchar(2000) DEFAULT NULL COMMENT '????????????',
  `times` int(11) NOT NULL COMMENT '??????(???????????????)',
  `create_time` datetime DEFAULT NULL COMMENT '????????????',
  PRIMARY KEY (`log_id`),
  KEY `job_id` (`job_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb3 COMMENT='??????????????????';

-- ----------------------------
-- Table structure for sys_captcha
-- ----------------------------
DROP TABLE IF EXISTS `sys_captcha`;
CREATE TABLE `sys_captcha` (
  `uuid` char(36) NOT NULL COMMENT 'uuid',
  `code` varchar(6) NOT NULL COMMENT '?????????',
  `expire_time` datetime DEFAULT NULL COMMENT '????????????',
  PRIMARY KEY (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COMMENT='???????????????';

-- ----------------------------
-- Table structure for sys_config
-- ----------------------------
DROP TABLE IF EXISTS `sys_config`;
CREATE TABLE `sys_config` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `param_key` varchar(50) DEFAULT NULL COMMENT 'key',
  `param_value` varchar(2000) DEFAULT NULL COMMENT 'value',
  `status` tinyint(4) DEFAULT 1 COMMENT '??????   0?????????   1?????????',
  `remark` varchar(500) DEFAULT NULL COMMENT '??????',
  `create_time` datetime NOT NULL COMMENT '????????????',
  `update_time` datetime DEFAULT NULL COMMENT '????????????',
  `update_by` varchar(100) DEFAULT '' COMMENT '?????????',
  PRIMARY KEY (`id`),
  UNIQUE KEY `param_key` (`param_key`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COMMENT='?????????????????????';

-- ----------------------------
-- Records of sys_config
-- ----------------------------
BEGIN;
INSERT INTO `sys_config` VALUES (1, 'CLOUD_STORAGE_CONFIG_KEY', '{\"type\":1,\"qiniuDomain\":\"http://smart.qiniu.slowcom.cn\",\"qiniuPrefix\":\"\",\"qiniuAccessKey\":\"m4HSivqb0vE3h0hhvHbCfIexFfaFXE2AEPwncEpr\",\"qiniuSecretKey\":\"cyUCgPJqsQKWmWU6RaoN3Y05fA4ebOEHiUyIJ6cQ\",\"qiniuBucketName\":\"slowcom-smart-community\",\"aliyunDomain\":\"\",\"aliyunPrefix\":\"\",\"aliyunEndPoint\":\"\",\"aliyunAccessKeyId\":\"\",\"aliyunAccessKeySecret\":\"\",\"aliyunBucketName\":\"\",\"qcloudDomain\":\"\",\"qcloudPrefix\":\"\",\"qcloudSecretId\":\"\",\"qcloudSecretKey\":\"\",\"qcloudBucketName\":\"\",\"qcloudRegion\":\"\",\"privateDomain\":\"\",\"privatePrefix\":\"\",\"privateWindowsUrl\":\"\",\"privateLinuxUrl\":\"\"}', 1, NULL, '2021-07-14 14:07:40', NULL, 'kuro');
COMMIT;

-- ----------------------------
-- Table structure for sys_dept
-- ----------------------------
DROP TABLE IF EXISTS `sys_dept`;
CREATE TABLE `sys_dept` (
  `dept_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `parent_id` bigint(20) DEFAULT NULL COMMENT '????????????ID??????????????????0',
  `name` varchar(50) DEFAULT NULL COMMENT '????????????',
  `order_num` int(11) DEFAULT NULL COMMENT '??????',
  `del_flag` tinyint(4) DEFAULT 0 COMMENT '????????????  -1????????????  0?????????',
  PRIMARY KEY (`dept_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COMMENT='????????????';

-- ----------------------------
-- Records of sys_dept
-- ----------------------------
BEGIN;
INSERT INTO `sys_dept` VALUES (1, 0, '??????', 0, 0);
INSERT INTO `sys_dept` VALUES (2, 1, '????????????', 1, 0);
INSERT INTO `sys_dept` VALUES (3, 2, '?????????', 0, 0);
COMMIT;

-- ----------------------------
-- Table structure for sys_dict
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict`;
CREATE TABLE `sys_dict` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL COMMENT '????????????',
  `type` varchar(100) NOT NULL COMMENT '????????????',
  `code` varchar(100) NOT NULL COMMENT '?????????',
  `value` varchar(1000) NOT NULL COMMENT '?????????',
  `order_num` int(11) DEFAULT 0 COMMENT '??????',
  `remark` varchar(255) DEFAULT NULL COMMENT '??????',
  `del_flag` tinyint(4) DEFAULT 0 COMMENT '????????????  -1????????????  0?????????',
  PRIMARY KEY (`id`),
  UNIQUE KEY `type` (`type`,`code`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COMMENT='???????????????';

-- ----------------------------
-- Records of sys_dict
-- ----------------------------
BEGIN;
INSERT INTO `sys_dict` VALUES (1, '??????', 'sex', '0', '???', 0, NULL, 0);
INSERT INTO `sys_dict` VALUES (2, '??????', 'sex', '1', '???', 1, NULL, 0);
INSERT INTO `sys_dict` VALUES (3, '??????', 'sex', '2', '??????', 3, NULL, 0);
COMMIT;

-- ----------------------------
-- Table structure for sys_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_log`;
CREATE TABLE `sys_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) DEFAULT NULL COMMENT '?????????',
  `operation` varchar(50) DEFAULT NULL COMMENT '????????????',
  `method` varchar(200) DEFAULT NULL COMMENT '????????????',
  `params` varchar(5000) DEFAULT NULL COMMENT '????????????',
  `time` bigint(20) NOT NULL COMMENT '????????????(??????)',
  `ip` varchar(64) DEFAULT NULL COMMENT 'IP??????',
  `create_date` datetime DEFAULT NULL COMMENT '????????????',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=utf8mb4 COMMENT='????????????';

/*
 Navicat Premium Data Transfer

 Source Server         : slowcom_pms_test
 Source Server Type    : MySQL
 Source Server Version : 100307
 Source Host           : 47.106.210.185:3306
 Source Schema         : sc_community

 Target Server Type    : MySQL
 Target Server Version : 100307
 File Encoding         : 65001

 Date: 15/10/2021 09:43:38
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu` (
                            `menu_id` bigint(20) NOT NULL AUTO_INCREMENT,
                            `parent_id` bigint(20) DEFAULT NULL COMMENT '?????????ID??????????????????0',
                            `name` varchar(50) DEFAULT NULL COMMENT '????????????',
                            `url` varchar(200) DEFAULT NULL COMMENT '??????URL',
                            `perms` varchar(500) DEFAULT NULL COMMENT '??????(??????????????????????????????user:list,user:create)',
                            `type` int(11) DEFAULT NULL COMMENT '??????   0?????????   1?????????   2?????????',
                            `icon` varchar(50) DEFAULT NULL COMMENT '????????????',
                            `version` int(11) NOT NULL DEFAULT 0 COMMENT '?????????',
                            `order_num` int(11) DEFAULT NULL COMMENT '??????',
                            PRIMARY KEY (`menu_id`)
) ENGINE=InnoDB AUTO_INCREMENT=154 DEFAULT CHARSET=utf8mb4 COMMENT='????????????';

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
BEGIN;
INSERT INTO `sys_menu` VALUES (1, 0, '????????????', NULL, NULL, 0, 'system', 0, 666);
INSERT INTO `sys_menu` VALUES (2, 1, '???????????????', 'sys/user', NULL, 1, '', 0, 1);
INSERT INTO `sys_menu` VALUES (3, 1, '????????????', 'sys/role', NULL, 1, '', 0, 2);
INSERT INTO `sys_menu` VALUES (4, 1, '????????????', 'sys/menu', NULL, 1, '', 0, 3);
INSERT INTO `sys_menu` VALUES (6, 1, '????????????', 'job/schedule', NULL, 1, '', 0, 5);
INSERT INTO `sys_menu` VALUES (7, 6, '??????', NULL, 'sys:schedule:list,sys:schedule:info', 2, NULL, 0, 0);
INSERT INTO `sys_menu` VALUES (8, 6, '??????', NULL, 'sys:schedule:save', 2, NULL, 0, 0);
INSERT INTO `sys_menu` VALUES (9, 6, '??????', NULL, 'sys:schedule:update', 2, NULL, 0, 0);
INSERT INTO `sys_menu` VALUES (10, 6, '??????', NULL, 'sys:schedule:delete', 2, NULL, 0, 0);
INSERT INTO `sys_menu` VALUES (11, 6, '??????', NULL, 'sys:schedule:pause', 2, NULL, 0, 0);
INSERT INTO `sys_menu` VALUES (12, 6, '??????', NULL, 'sys:schedule:resume', 2, NULL, 0, 0);
INSERT INTO `sys_menu` VALUES (13, 6, '????????????', NULL, 'sys:schedule:run', 2, NULL, 0, 0);
INSERT INTO `sys_menu` VALUES (14, 6, '????????????', NULL, 'sys:schedule:log', 2, NULL, 0, 0);
INSERT INTO `sys_menu` VALUES (15, 2, '??????', NULL, 'sys:user:list,sys:user:info', 2, NULL, 0, 0);
INSERT INTO `sys_menu` VALUES (16, 2, '??????', NULL, 'sys:user:save,sys:role:select', 2, NULL, 0, 0);
INSERT INTO `sys_menu` VALUES (17, 2, '??????', NULL, 'sys:user:update,sys:role:select', 2, NULL, 0, 0);
INSERT INTO `sys_menu` VALUES (18, 2, '??????', NULL, 'sys:user:delete', 2, NULL, 0, 0);
INSERT INTO `sys_menu` VALUES (19, 3, '??????', NULL, 'sys:role:list,sys:role:info', 2, NULL, 0, 0);
INSERT INTO `sys_menu` VALUES (20, 3, '??????', NULL, 'sys:role:save,sys:menu:perms', 2, NULL, 0, 0);
INSERT INTO `sys_menu` VALUES (21, 3, '??????', NULL, 'sys:role:update,sys:menu:perms', 2, NULL, 0, 0);
INSERT INTO `sys_menu` VALUES (22, 3, '??????', NULL, 'sys:role:delete', 2, NULL, 0, 0);
INSERT INTO `sys_menu` VALUES (23, 4, '??????', NULL, 'sys:menu:list,sys:menu:info', 2, NULL, 0, 0);
INSERT INTO `sys_menu` VALUES (24, 4, '??????', NULL, 'sys:menu:save,sys:menu:select', 2, NULL, 0, 0);
INSERT INTO `sys_menu` VALUES (25, 4, '??????', NULL, 'sys:menu:update,sys:menu:select', 2, NULL, 0, 0);
INSERT INTO `sys_menu` VALUES (26, 4, '??????', NULL, 'sys:menu:delete', 2, NULL, 0, 0);
INSERT INTO `sys_menu` VALUES (27, 1, '????????????', 'sys/config', NULL, 1, '', 0, 6);
INSERT INTO `sys_menu` VALUES (29, 1, '????????????', 'sys/log', 'sys:log:list', 1, '', 0, 7);
INSERT INTO `sys_menu` VALUES (30, 1, '????????????', 'sys/oss', 'sys:oss:list', 1, '', 0, 6);
INSERT INTO `sys_menu` VALUES (31, 1, '????????????', 'sys/dept', NULL, 1, '', 0, 1);
INSERT INTO `sys_menu` VALUES (32, 31, '??????', NULL, 'sys:dept:list,sys:dept:info', 2, NULL, 0, 0);
INSERT INTO `sys_menu` VALUES (33, 31, '??????', NULL, 'sys:dept:save,sys:dept:select', 2, NULL, 0, 0);
INSERT INTO `sys_menu` VALUES (34, 31, '??????', NULL, 'sys:dept:update,sys:dept:select', 2, NULL, 0, 0);
INSERT INTO `sys_menu` VALUES (35, 31, '??????', NULL, 'sys:dept:delete', 2, NULL, 0, 0);
INSERT INTO `sys_menu` VALUES (42, 27, '??????', NULL, 'sys:config:list,report:config:info', 2, NULL, 0, 6);
INSERT INTO `sys_menu` VALUES (43, 27, '??????', NULL, 'sys:config:save', 2, NULL, 0, 6);
INSERT INTO `sys_menu` VALUES (44, 27, '??????', NULL, 'sys:config:update', 2, NULL, 0, 6);
INSERT INTO `sys_menu` VALUES (45, 27, '??????', NULL, 'sys:config:delete', 2, NULL, 0, 6);
INSERT INTO `sys_menu` VALUES (96, 131, '????????????', 'user/userCenter', NULL, 1, '', 0, 0);
INSERT INTO `sys_menu` VALUES (97, 96, '??????', NULL, 'user:user:list, user:user:info', 2, NULL, 0, 6);
INSERT INTO `sys_menu` VALUES (98, 96, '??????', NULL, 'user:user:save', 2, NULL, 0, 6);
INSERT INTO `sys_menu` VALUES (99, 96, '??????', NULL, 'user:user:update', 2, NULL, 0, 6);
INSERT INTO `sys_menu` VALUES (100, 96, '??????', NULL, 'user:user:delete', 2, NULL, 0, 6);
INSERT INTO `sys_menu` VALUES (131, 0, '????????????', '', '', 0, 'user', 0, 0);
INSERT INTO `sys_menu` VALUES (134, 30, '???????????????', '', 'sys:oss:config', 2, '', 0, 0);
INSERT INTO `sys_menu` VALUES (149, 1, '??????????????????', 'sys/Updatelog', 'sys:updatelog:list', 1, '', 0, 5);
INSERT INTO `sys_menu` VALUES (150, 149, '??????', '', 'sys:updatelog:info', 2, '', 0, 0);
INSERT INTO `sys_menu` VALUES (151, 149, '??????', '', 'sys:updatelog:save', 2, '', 0, 0);
INSERT INTO `sys_menu` VALUES (152, 149, '??????', '', 'sys:updatelog:update', 2, '', 0, 0);
INSERT INTO `sys_menu` VALUES (153, 149, '??????', '', 'sys:updatelog:delete', 2, '', 0, 0);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;


-- ----------------------------
-- Table structure for sys_oss
-- ----------------------------
DROP TABLE IF EXISTS `sys_oss`;
CREATE TABLE `sys_oss` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `url` varchar(200) DEFAULT NULL COMMENT 'URL??????',
  `create_date` datetime DEFAULT NULL COMMENT '????????????',
  `update_by` varchar(100) DEFAULT '' COMMENT '?????????',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb3 COMMENT='????????????';


-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `role_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(100) DEFAULT NULL COMMENT '????????????',
  `remark` varchar(100) DEFAULT NULL COMMENT '??????',
  `dept_id` bigint(20) DEFAULT NULL COMMENT '??????ID',
  `create_user_id` bigint(20) DEFAULT NULL COMMENT '?????????ID',
  `version` int(11) NOT NULL DEFAULT 0 COMMENT '?????????',
  `create_time` datetime DEFAULT NULL COMMENT '????????????',
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COMMENT='??????';

-- ----------------------------
-- Records of sys_role
-- ----------------------------
BEGIN;
INSERT INTO `sys_role` VALUES (1, '???????????????', '', 1, 2, 0, '2021-07-14 10:54:01');
COMMIT;

-- ----------------------------
-- Table structure for sys_role_dept
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_dept`;
CREATE TABLE `sys_role_dept` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_id` bigint(20) DEFAULT NULL COMMENT '??????ID',
  `dept_id` bigint(20) DEFAULT NULL COMMENT '??????ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COMMENT='???????????????????????????';

-- ----------------------------
-- Records of sys_role_dept
-- ----------------------------
BEGIN;
INSERT INTO `sys_role_dept` VALUES (5, 1, 1);
INSERT INTO `sys_role_dept` VALUES (6, 1, 2);
INSERT INTO `sys_role_dept` VALUES (7, 1, 4);
INSERT INTO `sys_role_dept` VALUES (8, 1, -666666);
COMMIT;

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_id` bigint(20) DEFAULT NULL COMMENT '??????ID',
  `menu_id` bigint(20) DEFAULT NULL COMMENT '??????ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=125 DEFAULT CHARSET=utf8mb4 COMMENT='???????????????????????????';

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------
BEGIN;
INSERT INTO `sys_role_menu` VALUES (63, 1, 1);
INSERT INTO `sys_role_menu` VALUES (64, 1, 2);
INSERT INTO `sys_role_menu` VALUES (65, 1, 15);
INSERT INTO `sys_role_menu` VALUES (66, 1, 16);
INSERT INTO `sys_role_menu` VALUES (67, 1, 17);
INSERT INTO `sys_role_menu` VALUES (68, 1, 18);
INSERT INTO `sys_role_menu` VALUES (69, 1, 3);
INSERT INTO `sys_role_menu` VALUES (70, 1, 19);
INSERT INTO `sys_role_menu` VALUES (71, 1, 20);
INSERT INTO `sys_role_menu` VALUES (72, 1, 21);
INSERT INTO `sys_role_menu` VALUES (73, 1, 22);
INSERT INTO `sys_role_menu` VALUES (74, 1, 4);
INSERT INTO `sys_role_menu` VALUES (75, 1, 23);
INSERT INTO `sys_role_menu` VALUES (76, 1, 24);
INSERT INTO `sys_role_menu` VALUES (77, 1, 25);
INSERT INTO `sys_role_menu` VALUES (78, 1, 26);
INSERT INTO `sys_role_menu` VALUES (79, 1, 6);
INSERT INTO `sys_role_menu` VALUES (80, 1, 10);
INSERT INTO `sys_role_menu` VALUES (81, 1, 11);
INSERT INTO `sys_role_menu` VALUES (82, 1, 12);
INSERT INTO `sys_role_menu` VALUES (83, 1, 13);
INSERT INTO `sys_role_menu` VALUES (84, 1, 14);
INSERT INTO `sys_role_menu` VALUES (85, 1, 7);
INSERT INTO `sys_role_menu` VALUES (86, 1, 8);
INSERT INTO `sys_role_menu` VALUES (87, 1, 9);
INSERT INTO `sys_role_menu` VALUES (88, 1, 27);
INSERT INTO `sys_role_menu` VALUES (89, 1, 44);
INSERT INTO `sys_role_menu` VALUES (90, 1, 45);
INSERT INTO `sys_role_menu` VALUES (91, 1, 42);
INSERT INTO `sys_role_menu` VALUES (92, 1, 43);
INSERT INTO `sys_role_menu` VALUES (93, 1, 29);
INSERT INTO `sys_role_menu` VALUES (94, 1, 30);
INSERT INTO `sys_role_menu` VALUES (95, 1, 134);
INSERT INTO `sys_role_menu` VALUES (96, 1, 31);
INSERT INTO `sys_role_menu` VALUES (97, 1, 32);
INSERT INTO `sys_role_menu` VALUES (98, 1, 33);
INSERT INTO `sys_role_menu` VALUES (99, 1, 34);
INSERT INTO `sys_role_menu` VALUES (100, 1, 35);
INSERT INTO `sys_role_menu` VALUES (101, 1, 131);
INSERT INTO `sys_role_menu` VALUES (102, 1, 96);
INSERT INTO `sys_role_menu` VALUES (103, 1, 97);
INSERT INTO `sys_role_menu` VALUES (104, 1, 98);
INSERT INTO `sys_role_menu` VALUES (105, 1, 99);
INSERT INTO `sys_role_menu` VALUES (106, 1, 100);
INSERT INTO `sys_role_menu` VALUES (107, 1, 132);
INSERT INTO `sys_role_menu` VALUES (108, 1, 106);
INSERT INTO `sys_role_menu` VALUES (109, 1, 110);
INSERT INTO `sys_role_menu` VALUES (110, 1, 107);
INSERT INTO `sys_role_menu` VALUES (111, 1, 108);
INSERT INTO `sys_role_menu` VALUES (112, 1, 109);
INSERT INTO `sys_role_menu` VALUES (113, 1, 133);
INSERT INTO `sys_role_menu` VALUES (114, 1, 121);
INSERT INTO `sys_role_menu` VALUES (115, 1, 122);
INSERT INTO `sys_role_menu` VALUES (116, 1, 123);
INSERT INTO `sys_role_menu` VALUES (117, 1, 124);
INSERT INTO `sys_role_menu` VALUES (118, 1, 125);
INSERT INTO `sys_role_menu` VALUES (119, 1, 126);
INSERT INTO `sys_role_menu` VALUES (120, 1, 127);
INSERT INTO `sys_role_menu` VALUES (121, 1, 128);
INSERT INTO `sys_role_menu` VALUES (122, 1, 129);
INSERT INTO `sys_role_menu` VALUES (123, 1, 130);
INSERT INTO `sys_role_menu` VALUES (124, 1, -666666);
COMMIT;

-- ----------------------------
-- Table structure for sys_update_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_update_log`;
CREATE TABLE `sys_update_log` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `update_version` varchar(100) NOT NULL DEFAULT '' COMMENT '????????????',
  `content` text NOT NULL COMMENT '????????????',
  `create_time` datetime NOT NULL COMMENT '????????????',
  `sys_user_id` bigint(11) NOT NULL DEFAULT 0 COMMENT '????????????ID',
  `sys_user_name` varchar(100) NOT NULL DEFAULT '' COMMENT '????????????',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COMMENT='???????????????';

-- ----------------------------
-- Records of sys_update_log
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `user_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL COMMENT '?????????',
  `password` varchar(100) DEFAULT NULL COMMENT '??????',
  `salt` varchar(20) DEFAULT NULL COMMENT '???',
  `email` varchar(100) DEFAULT NULL COMMENT '??????',
  `mobile` varchar(100) DEFAULT NULL COMMENT '?????????',
  `remark` varchar(200) DEFAULT NULL COMMENT '??????',
  `status` tinyint(4) DEFAULT NULL COMMENT '??????  0?????????   1?????????',
  `dept_id` bigint(20) DEFAULT NULL COMMENT '??????ID',
  `create_time` datetime DEFAULT NULL COMMENT '????????????',
  `version` int(11) NOT NULL DEFAULT 0 COMMENT '?????????',
  `create_user_id` bigint(11) DEFAULT 0 COMMENT '?????????ID',
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COMMENT='????????????';

-- ----------------------------
-- Records of sys_user ?????????kuro ?????????5149330
-- ----------------------------
BEGIN;
INSERT INTO `sys_user` VALUES (1, 'admin', '489c3ab62263b02eda7ef7fb8cb815518620c322b2712c3a0b48914a09655636', 'YzcmCZNvbXocrsz9dm8e', 'root@renren.io', '13612345678', NULL, 0, 1, '2016-11-11 11:11:11', 1, 1);
INSERT INTO `sys_user` VALUES (2, 'kuro', '964bece429a121b832381a788979a822494a84376a326937f466233e9ae51a1e', 'zcYamuHDDKTAEdBK9AcD', 'clarence_liang@163.com', '17688942200', NULL, 1, 1, '2021-07-14 10:54:25', 0, 1);
COMMIT;

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) DEFAULT NULL COMMENT '??????ID',
  `role_id` bigint(20) DEFAULT NULL COMMENT '??????ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COMMENT='???????????????????????????';

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
BEGIN;
INSERT INTO `sys_user_role` VALUES (1, 2, 1);
COMMIT;

-- ----------------------------
-- Table structure for sys_user_token
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_token`;
CREATE TABLE `sys_user_token` (
  `user_id` bigint(20) NOT NULL,
  `token` varchar(100) NOT NULL COMMENT 'token',
  `expire_time` datetime DEFAULT NULL COMMENT '????????????',
  `update_time` datetime DEFAULT NULL COMMENT '????????????',
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `token` (`token`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COMMENT='????????????Token';

-- ----------------------------
-- Table structure for tb_user
-- ----------------------------
DROP TABLE IF EXISTS `tb_user`;
CREATE TABLE `tb_user` (
  `user_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '??????id',
  `phone` varchar(50) DEFAULT '' COMMENT '?????????',
  `nick_name` varchar(200) DEFAULT '' COMMENT '????????????',
  `avatar` varchar(200) DEFAULT '' COMMENT '????????????',
  `last_login_time` datetime DEFAULT NULL COMMENT '??????????????????',
  `version` int(11) NOT NULL DEFAULT 0 COMMENT '?????????',
  `del_flag` tinyint(4) NOT NULL DEFAULT 0 COMMENT '???????????? 0-?????????1-??????',
  `create_time` datetime NOT NULL COMMENT '????????????',
  `update_time` datetime DEFAULT NULL COMMENT '????????????',
  `update_by` varchar(100) DEFAULT '' COMMENT '?????????',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COMMENT='?????????';


-- ----------------------------
-- Table structure for tb_user_token
-- ----------------------------
DROP TABLE IF EXISTS `tb_user_token`;
CREATE TABLE `tb_user_token` (
  `user_id` bigint(20) NOT NULL,
  `token` varchar(500) NOT NULL COMMENT 'token',
  `expire_time` datetime DEFAULT NULL COMMENT '????????????',
  `update_time` datetime DEFAULT NULL COMMENT '????????????',
  PRIMARY KEY (`user_id`),
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COMMENT='??????Token';

-- ----------------------------
-- Table structure for tb_user_wechat
-- ----------------------------
DROP TABLE IF EXISTS `tb_user_wechat`;
CREATE TABLE `tb_user_wechat` (
  `wechat_id` bigint(11) NOT NULL AUTO_INCREMENT COMMENT '????????????id',
  `user_id` bigint(11) NOT NULL COMMENT '??????id',
  `nick_name` varchar(200) DEFAULT '' COMMENT '????????????',
  `avatar` varchar(200) DEFAULT '' COMMENT '????????????',
  `gender` tinyint(4) DEFAULT 0 COMMENT '????????? 0-?????????1-??????2-???',
  `birthday` datetime DEFAULT NULL COMMENT '??????',
  `phone` varchar(50) DEFAULT '' COMMENT '?????????',
  `province` varchar(50) DEFAULT '' COMMENT '??????',
  `city` varchar(50) DEFAULT '' COMMENT '??????',
  `country` varchar(150) DEFAULT '' COMMENT '??????',
  `mini_open_id` varchar(200) DEFAULT '' COMMENT '???????????????openid',
  `app_open_id` varchar(200) DEFAULT '' COMMENT 'APP??????openid',
  `union_id` varchar(200) DEFAULT '' COMMENT '??????????????????id',
  `last_login_time` datetime DEFAULT NULL COMMENT '??????????????????',
  `last_login_ip` varchar(100) DEFAULT '' COMMENT '????????????ip??????',
  `last_login_info` varchar(200) DEFAULT '' COMMENT '????????????????????????',
  long_and_lati varchar(100) default '' comment '?????????  ??????,??????',
  `version` int(11) NOT NULL DEFAULT 0 COMMENT '?????????',
  `del_flag` tinyint(4) NOT NULL DEFAULT 0 COMMENT '???????????? 0-?????????1-??????',
  `create_time` datetime NOT NULL COMMENT '????????????',
  `update_time` datetime DEFAULT NULL COMMENT '????????????',
  `update_by` varchar(100) DEFAULT '' COMMENT '?????????',
  PRIMARY KEY (`wechat_id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COMMENT='???????????????';


SET FOREIGN_KEY_CHECKS = 1;
