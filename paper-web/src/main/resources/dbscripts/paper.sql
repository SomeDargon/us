/*
Navicat MySQL Data Transfer

Source Server         : mysql
Source Server Version : 80000
Source Host           : localhost:3306
Source Database       : paper

Target Server Type    : MYSQL
Target Server Version : 80000
File Encoding         : 65001

Date: 2017-03-28 20:38:07
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for pa_relation
-- ----------------------------
DROP TABLE IF EXISTS `pa_relation`;
CREATE TABLE `pa_relation` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `del_flag` tinyint(4) DEFAULT NULL,
  `student_id` int(11) DEFAULT NULL,
  `teacher_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of pa_relation
-- ----------------------------
INSERT INTO `pa_relation` VALUES ('5', '0', '3', '4');


-- ----------------------------
-- Table structure for sys_attachment
-- ----------------------------
DROP TABLE IF EXISTS `sys_attachment`;
CREATE TABLE `sys_attachment` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `content_type` varchar(255) NOT NULL,
  `create_by` int(11) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `enable` tinyint(1) DEFAULT NULL,
  `file_name` varchar(255) DEFAULT NULL,
  `file_size` int(11) DEFAULT NULL,
  `file_url` varchar(255) DEFAULT NULL,
  `del_flag` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=117 DEFAULT CHARSET=utf8 COMMENT='附件表';

-- ----------------------------
-- Table structure for sys_dict
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict`;
CREATE TABLE `sys_dict` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `label` varchar(100) NOT NULL COMMENT '标签名',
  `value` varchar(100) NOT NULL COMMENT '数据值',
  `type` varchar(255) DEFAULT NULL COMMENT '类型',
  `description` varchar(100) NOT NULL COMMENT '描述',
  `sort` int(11) NOT NULL COMMENT '排序（升序）',
  `remarks` varchar(255) DEFAULT NULL,
  `del_flag` int(11) NOT NULL COMMENT '删除标记（0：正常；1：删除）',
  PRIMARY KEY (`id`),
  KEY `sys_dict_value` (`value`),
  KEY `sys_dict_label` (`label`),
  KEY `sys_dict_del_flag` (`del_flag`)
) ENGINE=InnoDB AUTO_INCREMENT=153 DEFAULT CHARSET=utf8 COMMENT='字典表';

-- ----------------------------
-- Table structure for sys_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_log`;
CREATE TABLE `sys_log` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type` varchar(255) DEFAULT NULL COMMENT '日志类型（1：接入日志；2：异常日志）',
  `create_by` int(11) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `remote_addr` varchar(255) DEFAULT NULL COMMENT '操作IP地址',
  `user_agent` varchar(255) DEFAULT NULL COMMENT '用户代理',
  `request_uri` varchar(255) DEFAULT NULL COMMENT '请求URI',
  `method` varchar(255) DEFAULT NULL COMMENT '操作方式',
  `params` longtext COMMENT '操作提交的数据',
  `exception` text COMMENT '异常信息',
  `del_flag` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `sys_log_create_by` (`create_by`) USING BTREE,
  KEY `sys_log_request_uri` (`request_uri`),
  KEY `sys_log_type` (`type`),
  KEY `sys_log_create_date` (`create_date`),
  KEY `FK_5yqmukedkuti9qdm38cgn5qpo` (`create_by`)
) ENGINE=InnoDB AUTO_INCREMENT=1332 DEFAULT CHARSET=utf8 COMMENT='日志表';

-- ----------------------------
-- Records of sys_log
-- ----------------------------
INSERT INTO `sys_log` VALUES ('1', '2', '1', '2015-12-02 14:17:58', '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/45.0.2454.85 Safari/537.36', '/material/api/bill/download', 'GET', 'name=201512020000983_out_1.jpg', 'java.lang.NullPointerException', '0');
INSERT INTO `sys_log` VALUES ('2', '2', '1', '2015-12-02 14:17:58', '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/45.0.2454.85 Safari/537.36', '/material/api/bill/download', 'GET', 'name=201512020000983_in_0.jpg', 'java.lang.NullPointerException', '0');
INSERT INTO `sys_log` VALUES ('3', '2', '1', '2015-12-02 14:17:58', '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/45.0.2454.85 Safari/537.36', '/material/api/bill/download', 'GET', 'name=201512020000983_in_2.jpg', 'java.lang.NullPointerException', '0');
INSERT INTO `sys_log` VALUES ('4', '2', '1', '2015-12-02 14:17:58', '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/45.0.2454.85 Safari/537.36', '/material/api/bill/download', 'GET', 'name=201512020000983_out_0.jpg', 'java.lang.NullPointerException', '0');
INSERT INTO `sys_log` VALUES ('5', '2', '1', '2015-12-02 14:17:58', '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/45.0.2454.85 Safari/537.36', '/material/api/bill/download', 'GET', 'name=201512020000983_in_1.jpg', 'java.lang.NullPointerException', '0');

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL COMMENT '角色名称',
  `enname` varchar(100) DEFAULT NULL COMMENT '英文名称',
  `role_type` varchar(100) DEFAULT NULL COMMENT '角色类型',
  `data_scope` varchar(255) DEFAULT NULL COMMENT '数据范围（0：所有数据；1：所在公司及以下数据；2：所在公司数据；3：所在部门及以下数据；4：所在部门数据；8：仅本人数据；9：按明细设置）',
  `del_flag` int(11) NOT NULL COMMENT '删除标记（0：正常；1：删除）',
  PRIMARY KEY (`id`),
  KEY `sys_role_del_flag` (`del_flag`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='角色表';

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('1', '管理员', 'admin', 'assignment', '1', '0');
INSERT INTO `sys_role` VALUES ('2', '普通用户', 'teacher', null, '9', '0');
INSERT INTO `sys_role` VALUES ('3', '会员用户', 'student', null, '9', '0');

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `login_name` varchar(255) NOT NULL COMMENT '登录名',
  `password` varchar(255) NOT NULL COMMENT '密码',
  `salt` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `gender` tinyint(4) DEFAULT NULL COMMENT '性别（0：男；1：女；2：保密）',
  `birthdate` date DEFAULT NULL COMMENT '生日',
  `email` varchar(255) DEFAULT NULL COMMENT '邮箱',
  `phone` varchar(255) DEFAULT NULL COMMENT '电话',
  `host` varchar(255) DEFAULT NULL COMMENT '最后登陆IP',
  `login_date` datetime DEFAULT NULL COMMENT '最后登陆时间',
  `avatar` int(11) DEFAULT NULL COMMENT '头像',
  `role_id` int(11) DEFAULT NULL,
  `del_flag` int(11) NOT NULL COMMENT '删除标记（0：正常；1：删除）',
  `company_id` int(11) DEFAULT NULL COMMENT '归属公司',
  `practice_firm` varchar(100) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `qq` varchar(20) DEFAULT NULL,
  `schedule` tinyint(2) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `sys_user_login_name` (`login_name`),
  KEY `sys_user_del_flag` (`del_flag`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8 COMMENT='用户表';

-- ----------------------------
-- ----------------------------
-- Table structure for user_inter_relation
-- ----------------------------
DROP TABLE IF EXISTS `user_inter_relation`;
CREATE TABLE `user_inter_relation` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `sex` int(11) DEFAULT NULL COMMENT '''联系人性别''',
  `email` varchar(45) DEFAULT NULL COMMENT '''邮件''',
  `qq` varchar(45) DEFAULT NULL,
  `we_chat` varchar(45) DEFAULT NULL COMMENT '''微信''',
  `iphone` varchar(45) DEFAULT NULL COMMENT '人际表',
  `company_name` varchar(45) DEFAULT NULL COMMENT '''公司名''',
  `company_position` varchar(45) DEFAULT NULL COMMENT '''公司职位''',
  `u_id` int(11) DEFAULT NULL COMMENT '用户表，当添加用户的时候，如果没找到对应的就不添加',
  `industry` varchar(45) DEFAULT NULL COMMENT '''行业''',
  `insert_time` varchar(45) DEFAULT NULL,
  `uname` varchar(60) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `name` varchar(60) DEFAULT NULL,
  `del_flag` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8 COMMENT='人际表\n';

-- ----------------------------
-- Records of user_inter_relation
-- ----------------------------
INSERT INTO `user_inter_relation` VALUES ('1', '1', '23123@qq', '1234123', '123123', '123123', '泰岳', '白草路519', '1', '啊啊啊', null, '账号', null, '啊啊啊', '1');
INSERT INTO `user_inter_relation` VALUES ('2', null, '123@qq.com', '1233333', null, null, null, null, '1', null, null, null, null, '12333', '1');
INSERT INTO `user_inter_relation` VALUES ('4', '1', '2123@qq.com', '1231', null, null, null, null, '1', null, null, null, null, '123', '1');
INSERT INTO `user_inter_relation` VALUES ('5', '1', '21312@qq.com', '12312', null, null, null, null, '1', null, null, null, null, '123', '1');
INSERT INTO `user_inter_relation` VALUES ('6', '1', '212312@qq.com', '4124', null, null, null, null, '1', null, null, null, null, '123', '1');
INSERT INTO `user_inter_relation` VALUES ('7', null, '123@11.com', '123', null, null, null, null, '1', null, null, null, null, '123', '1');
INSERT INTO `user_inter_relation` VALUES ('9', null, '12312@qq.com', '123', null, null, null, null, '1', null, null, null, null, '123', '0');
INSERT INTO `user_inter_relation` VALUES ('11', null, '123@11.com', '12312', null, null, null, null, '1', null, null, null, null, '123', '0');
INSERT INTO `user_inter_relation` VALUES ('12', null, '123@11.com', '12312', null, null, null, null, '1', null, null, null, null, '123', '0');
INSERT INTO `user_inter_relation` VALUES ('14', null, '123@qq.com', '23', null, null, null, null, '1', null, null, null, null, '123', '1');
INSERT INTO `user_inter_relation` VALUES ('15', '0', '1231@qq.com', '123', null, '123', null, null, '1', null, null, null, null, '123', '1');
INSERT INTO `user_inter_relation` VALUES ('16', '0', '123@qq.com', '123', null, '123', null, null, '1', null, null, null, null, 'fff', '0');

-- ----------------------------
-- Table structure for user_schedule
-- ----------------------------
DROP TABLE IF EXISTS `user_schedule`;
CREATE TABLE `user_schedule` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `describe` varchar(255) DEFAULT NULL,
  `site` varchar(255) DEFAULT NULL COMMENT '地点',
  `start_time` datetime DEFAULT NULL,
  `end_time` datetime DEFAULT NULL,
  `is_message` int(1) DEFAULT NULL,
  `u_id` int(11) DEFAULT NULL,
  `del_flag` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_schedule
-- ----------------------------

-- ----------------------------
-- Table structure for user_visit_record
-- ----------------------------
DROP TABLE IF EXISTS `user_visit_record`;
CREATE TABLE `user_visit_record` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `del_flag` int(11) DEFAULT NULL,
  `insert_time` datetime DEFAULT NULL,
  `u_id` int(11) DEFAULT NULL,
  `module` int(11) DEFAULT NULL COMMENT '访问模块',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_visit_record
-- ----------------------------
INSERT INTO `user_visit_record` VALUES ('1', '1', '2017-05-10 15:40:46', '1', '1');
INSERT INTO `user_visit_record` VALUES ('2', '2', '2017-05-12 13:40:34', '2', '2');