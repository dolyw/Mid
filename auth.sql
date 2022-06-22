-- 系统表
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `username` varchar(255) NOT NULL COMMENT '用户名',
  `password` varchar(255) NOT NULL COMMENT '密码',
  `nickname` varchar(255) DEFAULT NULL COMMENT '昵称',
  `avatar` varchar(255) DEFAULT NULL COMMENT '头像',
  `gender` tinyint DEFAULT NULL COMMENT '性别',
  `email` varchar(255) DEFAULT NULL COMMENT '邮箱',
  `calling_code` varchar(10) DEFAULT NULL COMMENT '电话区号',
  `mobile` varchar(20) DEFAULT NULL COMMENT '手机号',
  `deleted_ind` tinyint unsigned DEFAULT '0' COMMENT '逻辑删除',
  `created_time` datetime(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) COMMENT '创建时间',
  `updated_time` datetime(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6) COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `idx_username` (`username`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC COMMENT='用户表';

DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `code` varchar(255) NOT NULL COMMENT '角色代码',
  `name` varchar(255) DEFAULT NULL COMMENT '角色名称',
  `parent_id` bigint unsigned DEFAULT NULL COMMENT '父角色ID',
  `deleted_ind` tinyint unsigned NOT NULL DEFAULT '0' COMMENT '逻辑删除',
  `created_time` datetime(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) COMMENT '创建时间',
  `updated_time` datetime(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6) COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `idx_code` (`code`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC COMMENT='角色表';

DROP TABLE IF EXISTS `sys_resource`;
CREATE TABLE `sys_resource` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `name` varchar(100) NOT NULL COMMENT '资源名称',
  `title` varchar(255) NOT NULL COMMENT '显示标题',
  `permission` varchar(100) NOT NULL COMMENT '权限代码',
  `path` varchar(255) DEFAULT NULL COMMENT '访问路径',
  `component` varchar(255) DEFAULT NULL COMMENT '组件路径',
  `icon` varchar(80) DEFAULT NULL COMMENT '菜单图标',
  `sort` int DEFAULT '100' COMMENT '排序',
  `type` int NOT NULL COMMENT '资源类型 1-菜单 2-按钮 3-资源',
  `parent_id` bigint unsigned DEFAULT NULL COMMENT '父节点',
  `deleted_ind` tinyint unsigned NOT NULL DEFAULT '0' COMMENT '逻辑删除',
  `created_time` datetime(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) COMMENT '创建时间',
  `updated_time` datetime(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6) COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC COMMENT='资源表';


DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `user_id` bigint unsigned DEFAULT NULL COMMENT '用户ID',
  `role_id` bigint unsigned DEFAULT NULL COMMENT '角色ID',
  `deleted_ind` tinyint unsigned NOT NULL DEFAULT '0' COMMENT '逻辑删除',
  `created_time` datetime(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) COMMENT '创建时间',
  `updated_time` datetime(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6) COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `idx_user_id` (`user_id`) USING BTREE,
  KEY `idx_role_id` (`role_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC COMMENT='用户角色表';

DROP TABLE IF EXISTS `sys_role_resource`;
CREATE TABLE `sys_role_resource` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `role_id` bigint unsigned DEFAULT NULL COMMENT '角色ID',
  `resource_id` bigint unsigned DEFAULT NULL COMMENT '资源ID',
  `deleted_ind` tinyint unsigned NOT NULL DEFAULT '0' COMMENT '逻辑删除',
  `created_time` datetime(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) COMMENT '创建时间',
  `updated_time` datetime(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6) COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `idx_role_id` (`role_id`) USING BTREE,
  KEY `idx_resource_id` (`resource_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC COMMENT='角色资源表';


-- 业务表
DROP TABLE IF EXISTS `t_app`;
CREATE TABLE `t_app` (
  `app_id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `app_code` varchar(64) NOT NULL COMMENT '应用代码',
  `app_name` varchar(64) NOT NULL COMMENT '应用名称',
  `app_secret` varchar(64) NOT NULL COMMENT '应用私钥',
  `app_type` varchar(50) DEFAULT NULL COMMENT '应用类型',
  `invalid_flag` tinyint unsigned NOT NULL DEFAULT '0' COMMENT '失效标识:  0-有效，1-失效',
  `created_by` varchar(50) NOT NULL COMMENT '创建人',
  `created_time` datetime(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) COMMENT '创建时间',
  `updated_by` varchar(50) NOT NULL COMMENT '更新人',
  `updated_time` datetime(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6) COMMENT '更新时间',
  PRIMARY KEY (`app_id`),
  UNIQUE KEY `uk_app_code` (`app_code`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC COMMENT='应用配置表';

DROP TABLE IF EXISTS `t_channel`;
CREATE TABLE `t_channel` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `channel_code` varchar(64) NOT NULL COMMENT '渠道代码',
  `channel_name` varchar(64) DEFAULT NULL COMMENT '渠道简称',
  `channel_desc` varchar(200) DEFAULT NULL COMMENT '渠道全称',
  `channel_type` tinyint unsigned DEFAULT NULL COMMENT '渠道类型',
  `status` tinyint unsigned NOT NULL COMMENT '渠道状态：1-正常合作 2-暂停合作',
  `effective_date` datetime DEFAULT NULL COMMENT '渠道生效日期',
  `invalid_date` datetime DEFAULT NULL COMMENT '渠道失效日期',
  `contact_name` varchar(64) DEFAULT NULL COMMENT '渠道联系人',
  `contact_phone` varchar(20) DEFAULT NULL COMMENT '渠道联系电话',
  `contact_email` varchar(64) DEFAULT NULL COMMENT '渠道联系邮箱',
  `invalid_flag` tinyint unsigned NOT NULL DEFAULT '0' COMMENT '失效标识:  0-有效，1-失效',
  `created_by` varchar(50) NOT NULL COMMENT '创建人',
  `created_time` datetime(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) COMMENT '创建时间',
  `updated_by` varchar(50) NOT NULL COMMENT '更新人',
  `updated_time` datetime(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6) COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_channel_code` (`channel_code`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC COMMENT='渠道表';

DROP TABLE IF EXISTS `t_pub_dict_data`;
CREATE TABLE `t_pub_dict_data` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `dict_type` varchar(100) NOT NULL COMMENT '字典类型',
  `dict_code` varchar(100) NOT NULL COMMENT '字典编码',
  `dict_c_name` varchar(100) NOT NULL COMMENT '字典中文名称',
  `dict_e_name` varchar(100) DEFAULT NULL COMMENT '字典英文名称',
  `dict_t_name` varchar(100) DEFAULT NULL COMMENT '字典繁体中文名称',
  `display_no` int NOT NULL COMMENT '显示序号',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  `invalid_flag` tinyint unsigned NOT NULL DEFAULT '0' COMMENT '失效标识:  0-有效，1-失效',
  `created_by` varchar(50) NOT NULL COMMENT '创建人',
  `created_time` datetime(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) COMMENT '创建时间',
  `updated_by` varchar(50) NOT NULL COMMENT '更新人',
  `updated_time` datetime(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6) COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `idx_dict_type` (`dict_type`) USING BTREE,
  KEY `idx_display_no` (`display_no`) USING BTREE,
  KEY `idx_updated_time` (`updated_time`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC COMMENT='字典数据表';

DROP TABLE IF EXISTS `t_pub_dict_type`;
CREATE TABLE `t_pub_dict_type` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `dict_type` varchar(100) NOT NULL COMMENT '字典类型',
  `dict_type_c_name` varchar(100) NOT NULL COMMENT '字典中文名称',
  `dict_type_e_name` varchar(100) DEFAULT NULL COMMENT '字典英文名称',
  `dict_type_t_name` varchar(100) DEFAULT NULL COMMENT '字典繁体中文名称',
  `remark` varchar(200) DEFAULT NULL COMMENT '备注',
  `invalid_flag` tinyint unsigned NOT NULL DEFAULT '0' COMMENT '失效标识:  0-有效，1-失效',
  `created_by` varchar(50) NOT NULL COMMENT '创建人',
  `created_time` datetime(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) COMMENT '创建时间',
  `updated_by` varchar(50) NOT NULL COMMENT '更新人',
  `updated_time` datetime(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6) COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `idx_dict_type` (`dict_type`) USING BTREE,
  KEY `idx_updated_time` (`updated_time`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC COMMENT='字典类型表';

DROP TABLE IF EXISTS `t_message_log`;
CREATE TABLE `t_message_log` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `correlation_id` bigint unsigned NOT NULL COMMENT '消息ID',
  `business_no` varchar(30) DEFAULT NULL COMMENT '业务号',
  `business_type` varchar(4) NOT NULL COMMENT '业务类型',
  `deal_system` varchar(30) DEFAULT NULL COMMENT '处理系统',
  `deal_status` int unsigned NOT NULL DEFAULT '0' COMMENT '处理状态: 0-未处理，1-处理中，2-处理成功，3-处理失败',
  `message_type` int unsigned NOT NULL COMMENT '消息类型: 1-生产消息，2-消费消息',
  `message_queue` varchar(50) DEFAULT NULL COMMENT '消息队列',
  `message_routing` varchar(50) DEFAULT NULL COMMENT '消息路由键',
  `message_content` mediumtext NOT NULL COMMENT '消息内容',
  `message_result` text COMMENT '消息结果',
  `field_a` int DEFAULT NULL COMMENT '备用字段a',
  `field_b` varchar(30) DEFAULT NULL COMMENT '备用字段b',
  `field_c` varchar(300) DEFAULT NULL COMMENT '备用字段c',
  `invalid_flag` tinyint unsigned NOT NULL DEFAULT '0' COMMENT '失效标识:  0-有效，1-失效',
  `created_by` varchar(50) NOT NULL COMMENT '创建人',
  `created_time` datetime(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) COMMENT '创建时间',
  `updated_by` varchar(50) NOT NULL COMMENT '更新人',
  `updated_time` datetime(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6) COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `idx_correlation_id` (`correlation_id`) USING BTREE,
  KEY `idx_business_no` (`business_no`) USING BTREE,
  KEY `idx_business_type` (`business_type`) USING BTREE,
  KEY `idx_deal_status` (`deal_status`) USING BTREE,
  KEY `idx_message_type` (`message_type`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC COMMENT='消息日志表';

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('1', 'admin', '21232F297A57A5A743894A0E4A801FC3', '系统管理员', '', '1', 'admin@qq.com', '86', '18603046467', '0', now(), now());

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('1', 'admin', '系统管理员', '0', '0', now(), now());

-- ----------------------------
-- Records of sys_resource
-- ----------------------------
INSERT INTO `sys_resource` VALUES ('1', 'Index', '首页', 'sys:index', '/index', null, 'el-icon-home-filled', '1', '1', null, '0', now(), now());
INSERT INTO `sys_resource` VALUES ('2', 'System', '系统管理', 'sys:manager', '/system', null, 'el-icon-setting', '2', '1', null, '0', now(), now());
INSERT INTO `sys_resource` VALUES ('6', 'Dashboard', '控制台', 'sys:index:dashboard', '/dashboard', '/dashboard', 'el-icon-film', '3', '1', '1', '0', now(), now());
INSERT INTO `sys_resource` VALUES ('7', 'Permission', '权限管理', 'sys:auth:auth', '/auth', null, 'el-icon-connection', '12', '1', '2', '0', now(), now());
INSERT INTO `sys_resource` VALUES ('8', 'Resource', '资源管理', 'sys:auth:resource', '/system/resource', '/system/resource', 'el-icon-help', '22', '1', '7', '0', now(), now());
INSERT INTO `sys_resource` VALUES ('9', 'Role', '角色管理', 'sys:auth:role', '/system/role', '/system/role', 'el-icon-avatar', '21', '1', '7', '0', now(), now());
INSERT INTO `sys_resource` VALUES ('11', 'User', '用户管理', 'sys:auth:user', '/system/user', '/system/user', 'el-icon-user-filled', '11', '1', '2', '0', now(), now());
INSERT INTO `sys_resource` VALUES ('12', 'ResourceAdd', '添加资源', 'sys:auth:resource:add', null, null, null, '100', '2', '8', '0', now(), now());
INSERT INTO `sys_resource` VALUES ('13', 'ResourceUpdate', '修改资源', 'sys:auth:resource:update', null, null, null, '100', '2', '8', '0', now(), now());
INSERT INTO `sys_resource` VALUES ('14', 'ResourceDelete', '删除资源', 'sys:auth:resource:delete', null, null, null, '100', '2', '8', '0', now(), now());
INSERT INTO `sys_resource` VALUES ('15', 'ResourceView', '查看资源', 'sys:auth:resource:view', null, null, null, '100', '2', '8', '0', now(), now());
INSERT INTO `sys_resource` VALUES ('23', 'RoleAdd', '添加角色', 'sys:auth:role:add', null, null, null, '100', '2', '9', '0', now(), now());
INSERT INTO `sys_resource` VALUES ('24', 'RoleUpdate', '修改角色', 'sys:auth:role:update', null, null, null, '100', '2', '9', '0', now(), now());
INSERT INTO `sys_resource` VALUES ('25', 'RoleDelete', '删除角色', 'sys:auth:role:delete', null, null, null, '100', '2', '9', '0', now(), now());
INSERT INTO `sys_resource` VALUES ('26', 'RoleView', '查看角色', 'sys:auth:role:view', null, null, null, '100', '2', '9', '0', now(), now());
INSERT INTO `sys_resource` VALUES ('27', 'RoleAuth', '角色授权', 'sys:auth:role:auth', null, null, null, '100', '2', '9', '0', now(), now());
INSERT INTO `sys_resource` VALUES ('28', 'UserView', '用户查看', 'sys:auth:user:view', null, null, null, '100', '2', '11', '0', now(), now());
INSERT INTO `sys_resource` VALUES ('29', 'UserAdd', '用户新增', 'sys:auth:user:add', null, null, null, '100', '2', '11', '0', now(), now());
INSERT INTO `sys_resource` VALUES ('30', 'UserUpdate', '用户修改', 'sys:auth:user:update', null, null, null, '100', '2', '11', '0', now(), now());
INSERT INTO `sys_resource` VALUES ('31', 'UserDelete', '用户删除', 'sys:auth:user:delete', null, null, null, '100', '2', '11', '0', now(), now());
INSERT INTO `sys_resource` VALUES ('32', 'UserAuth', '用户授予角色', 'sys:auth:user:auth', null, null, null, '100', '2', '11', '0', now(), now());
INSERT INTO `sys_resource` VALUES ('33', 'CrudView', 'FastCrud示例', 'sys:index:crud:view', '/crud', '/crud', null, '100', '1', '1', '0', now(), now());
INSERT INTO `sys_resource` VALUES ('34', 'CrudAdd', '新增', 'sys:index:crud:add', null, null, null, '100', '2', '33', '0', now(), now());
INSERT INTO `sys_resource` VALUES ('35', 'CrudUpdate', '编辑', 'sys:index:crud:update', null, null, null, '100', '2', '33', '0', now(), now());
INSERT INTO `sys_resource` VALUES ('36', 'CrudDelete', '删除', 'sys:index:crud:delete', null, null, null, '100', '2', '33', '0', now(), now());
INSERT INTO `sys_resource` VALUES ('37', 'ExampleView', 'Example示例', 'sys:index:example:view', '/example', '/example', null, '100', '1', '1', '0', now(), now());
INSERT INTO `sys_resource` VALUES ('38', 'ExampleAdd', '新增', 'sys:index:example:add', null, null, null, '100', '2', '37', '0', now(), now());
INSERT INTO `sys_resource` VALUES ('39', 'ExampleUpdate', '编辑', 'sys:index:example:update', null, null, null, '100', '2', '37', '0', now(), now());
INSERT INTO `sys_resource` VALUES ('40', 'ExampleDelete', '删除', 'sys:index:example:delete', null, null, null, '100', '2', '37', '0', now(), now());

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES ('1', '1', '1', '0', now(), now());

-- ----------------------------
-- Records of sys_role_resource
-- ----------------------------
INSERT INTO `sys_role_resource` VALUES ('1', '1', '0', '0', now(), now());
INSERT INTO `sys_role_resource` VALUES ('2', '1', '1', '0', now(), now());
INSERT INTO `sys_role_resource` VALUES ('3', '1', '6', '0', now(), now());
INSERT INTO `sys_role_resource` VALUES ('4', '1', '33', '0', now(), now());
INSERT INTO `sys_role_resource` VALUES ('5', '1', '34', '0', now(), now());
INSERT INTO `sys_role_resource` VALUES ('6', '1', '35', '0', now(), now());
INSERT INTO `sys_role_resource` VALUES ('7', '1', '36', '0', now(), now());
INSERT INTO `sys_role_resource` VALUES ('8', '1', '37', '0', now(), now());
INSERT INTO `sys_role_resource` VALUES ('9', '1', '40', '0', now(), now());
INSERT INTO `sys_role_resource` VALUES ('10', '1', '38', '0', now(), now());
INSERT INTO `sys_role_resource` VALUES ('11', '1', '39', '0', now(), now());
INSERT INTO `sys_role_resource` VALUES ('12', '1', '2', '0', now(), now());
INSERT INTO `sys_role_resource` VALUES ('13', '1', '11', '0', now(), now());
INSERT INTO `sys_role_resource` VALUES ('14', '1', '30', '0', now(), now());
INSERT INTO `sys_role_resource` VALUES ('15', '1', '31', '0', now(), now());
INSERT INTO `sys_role_resource` VALUES ('16', '1', '32', '0', now(), now());
INSERT INTO `sys_role_resource` VALUES ('17', '1', '29', '0', now(), now());
INSERT INTO `sys_role_resource` VALUES ('18', '1', '28', '0', now(), now());
INSERT INTO `sys_role_resource` VALUES ('19', '1', '7', '0', now(), now());
INSERT INTO `sys_role_resource` VALUES ('20', '1', '9', '0', now(), now());
INSERT INTO `sys_role_resource` VALUES ('21', '1', '27', '0', now(), now());
INSERT INTO `sys_role_resource` VALUES ('22', '1', '26', '0', now(), now());
INSERT INTO `sys_role_resource` VALUES ('23', '1', '25', '0', now(), now());
INSERT INTO `sys_role_resource` VALUES ('24', '1', '24', '0', now(), now());
INSERT INTO `sys_role_resource` VALUES ('25', '1', '23', '0', now(), now());
INSERT INTO `sys_role_resource` VALUES ('26', '1', '8', '0', now(), now());
INSERT INTO `sys_role_resource` VALUES ('27', '1', '15', '0', now(), now());
INSERT INTO `sys_role_resource` VALUES ('28', '1', '14', '0', now(), now());
INSERT INTO `sys_role_resource` VALUES ('29', '1', '13', '0', now(), now());
INSERT INTO `sys_role_resource` VALUES ('30', '1', '12', '0', now(), now());
