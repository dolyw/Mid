/*
Navicat MySQL Data Transfer

Source Server         : Dev-GrNewMid
Source Server Version : 80025
Source Host           : 10.75.43.5:4305
Source Database       : grbnmpcs

Target Server Type    : MYSQL
Target Server Version : 80025
File Encoding         : 65001

Date: 2022-05-26 14:19:39
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_app
-- ----------------------------
DROP TABLE IF EXISTS `t_app`;
CREATE TABLE `t_app` (
  `app_id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `app_code` varchar(64) NOT NULL COMMENT '应用代码',
  `app_name` varchar(64) NOT NULL COMMENT '应用名称',
  `app_secret` varchar(64) NOT NULL COMMENT '应用私钥',
  `app_type` varchar(50) DEFAULT NULL COMMENT '应用类型',
  `invalid_flag` tinyint unsigned NOT NULL DEFAULT '0' COMMENT '是否有效:  0-有效，1-失效',
  `created_by` varchar(50) NOT NULL COMMENT '创建人',
  `created_time` datetime NOT NULL COMMENT '创建时间',
  `updated_by` varchar(50) NOT NULL COMMENT '更新人',
  `updated_time` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`app_id`),
  UNIQUE KEY `uk_app_code` (`app_code`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='应用配置表';

-- ----------------------------
-- Records of t_app
-- ----------------------------
INSERT INTO `t_app` VALUES ('1', '2', '', '', '', '0', 'SYSTEM', now(), 'SYSTEM', now());

-- ----------------------------
-- Table structure for t_channel
-- ----------------------------
DROP TABLE IF EXISTS `t_channel`;
CREATE TABLE `t_channel` (
  `channel_id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `channel_code` varchar(64) NOT NULL COMMENT '渠道代码',
  `channel_name` varchar(64) DEFAULT NULL COMMENT '渠道简称',
  `channel_desc` varchar(200) DEFAULT NULL COMMENT '渠道全称',
  `channel_type` tinyint unsigned DEFAULT NULL COMMENT '渠道类型',
  `status` tinyint unsigned NOT NULL COMMENT '渠道状态：1-正常合作 2-暂停合作',
  `effective_date` datetime DEFAULT NULL COMMENT '渠道生效日期',
  `invalid_date` datetime DEFAULT NULL COMMENT '渠道失效日期',
  `contact_name` varchar(64) NOT NULL COMMENT '渠道联系人',
  `contact_phone` varchar(64) NOT NULL COMMENT '渠道联系电话',
  `contact_email` varchar(64) NOT NULL COMMENT '渠道联系邮箱',
  `invalid_flag` tinyint unsigned NOT NULL DEFAULT '0' COMMENT '是否有效:  0-有效，1-失效',
  `created_by` varchar(50) NOT NULL COMMENT '创建人',
  `created_time` datetime NOT NULL COMMENT '创建时间',
  `updated_by` varchar(50) NOT NULL COMMENT '更新人',
  `updated_time` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`channel_id`),
  UNIQUE KEY `uk_channel_code` (`channel_code`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='渠道表';

-- ----------------------------
-- Records of t_channel
-- ----------------------------

-- ----------------------------
-- Table structure for t_message_log
-- ----------------------------
DROP TABLE IF EXISTS `t_message_log`;
CREATE TABLE `t_message_log` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `correlation_id` bigint NOT NULL COMMENT '消息id',
  `business_no` varchar(30) DEFAULT NULL COMMENT '业务号',
  `business_type` varchar(4) NOT NULL COMMENT '业务类型',
  `deal_system` varchar(30) DEFAULT NULL COMMENT '处理系统',
  `deal_status` varchar(1) NOT NULL DEFAULT '0' COMMENT '处理状态: 0-未处理，1-处理中，2-处理成功，3-处理失败',
  `message_type` varchar(1) NOT NULL COMMENT '消息类型: 1-生产消息，2-消费消息',
  `message_queue` varchar(50) DEFAULT NULL COMMENT '消息队列',
  `message_routing` varchar(50) DEFAULT NULL COMMENT '消息路由键',
  `message_content` text NOT NULL COMMENT '消息内容',
  `message_result` text COMMENT '消息结果',
  `field_a` int DEFAULT NULL COMMENT '备用字段a',
  `field_b` varchar(30) DEFAULT NULL COMMENT '备用字段b',
  `field_c` varchar(300) DEFAULT NULL COMMENT '备用字段c',
  `invalid_flag` tinyint NOT NULL DEFAULT '0' COMMENT '失效标识:  0-有效，1-失效',
  `created_by` varchar(50) NOT NULL COMMENT '创建人',
  `created_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_by` varchar(50) NOT NULL COMMENT '更新人',
  `updated_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `idx_correlation_id` (`correlation_id`) USING BTREE,
  KEY `idx_business_no` (`business_no`) USING BTREE,
  KEY `idx_business_type` (`business_type`) USING BTREE,
  KEY `idx_deal_status` (`deal_status`) USING BTREE,
  KEY `idx_message_type` (`message_type`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='消息日志表';

-- ----------------------------
-- Records of t_message_log
-- ----------------------------

-- ----------------------------
-- Table structure for t_pub_dict_data
-- ----------------------------
DROP TABLE IF EXISTS `t_pub_dict_data`;
CREATE TABLE `t_pub_dict_data` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `dict_type` varchar(100) NOT NULL COMMENT '字典类型',
  `dict_code` varchar(100) NOT NULL COMMENT '字典编码',
  `dict_c_name` varchar(100) NOT NULL COMMENT '字典中文名称',
  `dict_e_name` varchar(100) DEFAULT NULL COMMENT '字典英文名称',
  `dict_t_name` varchar(100) DEFAULT NULL COMMENT '字典繁体中文名称',
  `display_no` int NOT NULL COMMENT '显示序号',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  `invalid_flag` tinyint(1) NOT NULL DEFAULT '0' COMMENT '失效标识：0-有效，1-失效',
  `created_by` varchar(50) NOT NULL COMMENT '创建人',
  `created_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_by` varchar(50) NOT NULL COMMENT '更新人',
  `updated_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `idx_dict_type` (`dict_type`) USING BTREE,
  KEY `idx_display_no` (`display_no`) USING BTREE,
  KEY `idx_updated_time` (`updated_time`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='字典数据表';

-- ----------------------------
-- Records of t_pub_dict_data
-- ----------------------------

-- ----------------------------
-- Table structure for t_pub_dict_type
-- ----------------------------
DROP TABLE IF EXISTS `t_pub_dict_type`;
CREATE TABLE `t_pub_dict_type` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `dict_type` varchar(100) NOT NULL COMMENT '字典类型',
  `dict_type_c_name` varchar(100) NOT NULL COMMENT '字典中文名称',
  `dict_type_e_name` varchar(100) DEFAULT NULL COMMENT '字典英文名称',
  `dict_type_t_name` varchar(100) DEFAULT NULL COMMENT '字典繁体中文名称',
  `remark` varchar(200) DEFAULT NULL COMMENT '备注',
  `invalid_flag` tinyint(1) NOT NULL DEFAULT '0' COMMENT '失效标识：0-有效，1-失效',
  `created_by` varchar(50) NOT NULL COMMENT '创建人',
  `created_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_by` varchar(50) NOT NULL COMMENT '更新人',
  `updated_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `idx_dict_type` (`dict_type`) USING BTREE,
  KEY `idx_updated_time` (`updated_time`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='字典类型表';

-- ----------------------------
-- Records of t_pub_dict_type
-- ----------------------------
