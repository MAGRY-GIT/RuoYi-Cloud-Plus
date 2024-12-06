-- MySQL dump 10.13  Distrib 8.0.33, for Linux (x86_64)
--
-- Host: 127.0.0.1    Database: ry-cloud
-- ------------------------------------------------------
-- Server version	8.0.33

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Current Database: `ry-cloud`
--

CREATE DATABASE /*!32312 IF NOT EXISTS*/ `ry-cloud` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;

USE `ry-cloud`;

--
-- Table structure for table `gen_table`
--

DROP TABLE IF EXISTS `gen_table`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `gen_table` (
  `table_id` bigint NOT NULL COMMENT '编号',
  `data_name` varchar(200) COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '数据源名称',
  `table_name` varchar(200) COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '表名称',
  `table_comment` varchar(500) COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '表描述',
  `sub_table_name` varchar(64) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '关联子表的表名',
  `sub_table_fk_name` varchar(64) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '子表关联的外键名',
  `class_name` varchar(100) COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '实体类名称',
  `tpl_category` varchar(200) COLLATE utf8mb4_general_ci DEFAULT 'crud' COMMENT '使用的模板（crud单表操作 tree树表操作）',
  `package_name` varchar(100) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '生成包路径',
  `module_name` varchar(30) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '生成模块名',
  `business_name` varchar(30) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '生成业务名',
  `function_name` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '生成功能名',
  `function_author` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '生成功能作者',
  `gen_type` char(1) COLLATE utf8mb4_general_ci DEFAULT '0' COMMENT '生成代码方式（0zip压缩包 1自定义路径）',
  `gen_path` varchar(200) COLLATE utf8mb4_general_ci DEFAULT '/' COMMENT '生成路径（不填默认项目路径）',
  `options` varchar(1000) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '其它生成选项',
  `create_dept` bigint DEFAULT NULL COMMENT '创建部门',
  `create_by` bigint DEFAULT NULL COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` bigint DEFAULT NULL COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`table_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='代码生成业务表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `gen_table`
--

LOCK TABLES `gen_table` WRITE;
/*!40000 ALTER TABLE `gen_table` DISABLE KEYS */;
/*!40000 ALTER TABLE `gen_table` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `gen_table_column`
--

DROP TABLE IF EXISTS `gen_table_column`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `gen_table_column` (
  `column_id` bigint NOT NULL COMMENT '编号',
  `table_id` bigint DEFAULT NULL COMMENT '归属表编号',
  `column_name` varchar(200) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '列名称',
  `column_comment` varchar(500) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '列描述',
  `column_type` varchar(100) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '列类型',
  `java_type` varchar(500) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT 'JAVA类型',
  `java_field` varchar(200) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT 'JAVA字段名',
  `is_pk` char(1) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '是否主键（1是）',
  `is_increment` char(1) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '是否自增（1是）',
  `is_required` char(1) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '是否必填（1是）',
  `is_insert` char(1) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '是否为插入字段（1是）',
  `is_edit` char(1) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '是否编辑字段（1是）',
  `is_list` char(1) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '是否列表字段（1是）',
  `is_query` char(1) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '是否查询字段（1是）',
  `query_type` varchar(200) COLLATE utf8mb4_general_ci DEFAULT 'EQ' COMMENT '查询方式（等于、不等于、大于、小于、范围）',
  `html_type` varchar(200) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '显示类型（文本框、文本域、下拉框、复选框、单选框、日期控件）',
  `dict_type` varchar(200) COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '字典类型',
  `sort` int DEFAULT NULL COMMENT '排序',
  `create_dept` bigint DEFAULT NULL COMMENT '创建部门',
  `create_by` bigint DEFAULT NULL COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` bigint DEFAULT NULL COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`column_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='代码生成业务表字段';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `gen_table_column`
--

LOCK TABLES `gen_table_column` WRITE;
/*!40000 ALTER TABLE `gen_table_column` DISABLE KEYS */;
/*!40000 ALTER TABLE `gen_table_column` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_client`
--

DROP TABLE IF EXISTS `sys_client`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sys_client` (
  `id` bigint NOT NULL COMMENT 'id',
  `client_id` varchar(64) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '客户端id',
  `client_key` varchar(32) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '客户端key',
  `client_secret` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '客户端秘钥',
  `grant_type` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '授权类型',
  `device_type` varchar(32) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '设备类型',
  `active_timeout` int DEFAULT '1800' COMMENT 'token活跃超时时间',
  `timeout` int DEFAULT '604800' COMMENT 'token固定超时',
  `status` char(1) COLLATE utf8mb4_general_ci DEFAULT '0' COMMENT '状态（0正常 1停用）',
  `del_flag` char(1) COLLATE utf8mb4_general_ci DEFAULT '0' COMMENT '删除标志（0代表存在 2代表删除）',
  `create_dept` bigint DEFAULT NULL COMMENT '创建部门',
  `create_by` bigint DEFAULT NULL COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` bigint DEFAULT NULL COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='系统授权表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_client`
--

LOCK TABLES `sys_client` WRITE;
/*!40000 ALTER TABLE `sys_client` DISABLE KEYS */;
INSERT INTO `sys_client` VALUES (1,'e5cd7e4891bf95d1d19206ce24a7b32e','pc','pc123','password,social','pc',1800,604800,'0','0',103,1,'2024-12-04 17:33:33',1,'2024-12-04 17:33:33'),(2,'428a8310cd442757ae699df5d894f051','app','app123','password,sms,social','android',1800,604800,'0','0',103,1,'2024-12-04 17:33:33',1,'2024-12-04 17:33:33');
/*!40000 ALTER TABLE `sys_client` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_config`
--

DROP TABLE IF EXISTS `sys_config`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sys_config` (
  `config_id` bigint NOT NULL COMMENT '参数主键',
  `tenant_id` varchar(20) COLLATE utf8mb4_general_ci DEFAULT '000000' COMMENT '租户编号',
  `config_name` varchar(100) COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '参数名称',
  `config_key` varchar(100) COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '参数键名',
  `config_value` varchar(500) COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '参数键值',
  `config_type` char(1) COLLATE utf8mb4_general_ci DEFAULT 'N' COMMENT '系统内置（Y是 N否）',
  `create_dept` bigint DEFAULT NULL COMMENT '创建部门',
  `create_by` bigint DEFAULT NULL COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` bigint DEFAULT NULL COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`config_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='参数配置表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_config`
--

LOCK TABLES `sys_config` WRITE;
/*!40000 ALTER TABLE `sys_config` DISABLE KEYS */;
INSERT INTO `sys_config` VALUES (1,'000000','主框架页-默认皮肤样式名称','sys.index.skinName','skin-blue','Y',103,1,'2024-12-04 17:33:29',NULL,NULL,'蓝色 skin-blue、绿色 skin-green、紫色 skin-purple、红色 skin-red、黄色 skin-yellow'),(2,'000000','用户管理-账号初始密码','sys.user.initPassword','123456','Y',103,1,'2024-12-04 17:33:29',NULL,NULL,'初始化密码 123456'),(3,'000000','主框架页-侧边栏主题','sys.index.sideTheme','theme-dark','Y',103,1,'2024-12-04 17:33:29',NULL,NULL,'深色主题theme-dark，浅色主题theme-light'),(5,'000000','账号自助-是否开启用户注册功能','sys.account.registerUser','false','Y',103,1,'2024-12-04 17:33:29',NULL,NULL,'是否开启注册用户功能（true开启，false关闭）'),(11,'000000','OSS预览列表资源开关','sys.oss.previewListResource','true','Y',103,1,'2024-12-04 17:33:29',NULL,NULL,'true:开启, false:关闭');
/*!40000 ALTER TABLE `sys_config` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_dept`
--

DROP TABLE IF EXISTS `sys_dept`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sys_dept` (
  `dept_id` bigint NOT NULL COMMENT '部门id',
  `tenant_id` varchar(20) COLLATE utf8mb4_general_ci DEFAULT '000000' COMMENT '租户编号',
  `parent_id` bigint DEFAULT '0' COMMENT '父部门id',
  `ancestors` varchar(500) COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '祖级列表',
  `dept_name` varchar(30) COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '部门名称',
  `dept_category` varchar(100) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '部门类别编码',
  `order_num` int DEFAULT '0' COMMENT '显示顺序',
  `leader` bigint DEFAULT NULL COMMENT '负责人',
  `phone` varchar(11) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '联系电话',
  `email` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '邮箱',
  `status` char(1) COLLATE utf8mb4_general_ci DEFAULT '0' COMMENT '部门状态（0正常 1停用）',
  `del_flag` char(1) COLLATE utf8mb4_general_ci DEFAULT '0' COMMENT '删除标志（0代表存在 2代表删除）',
  `create_dept` bigint DEFAULT NULL COMMENT '创建部门',
  `create_by` bigint DEFAULT NULL COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` bigint DEFAULT NULL COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`dept_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='部门表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_dept`
--

LOCK TABLES `sys_dept` WRITE;
/*!40000 ALTER TABLE `sys_dept` DISABLE KEYS */;
INSERT INTO `sys_dept` VALUES (100,'000000',0,'0','XXX科技',NULL,0,NULL,'15888888888','xxx@qq.com','0','0',103,1,'2024-12-04 17:33:23',NULL,NULL),(101,'000000',100,'0,100','深圳总公司',NULL,1,NULL,'15888888888','xxx@qq.com','0','0',103,1,'2024-12-04 17:33:23',NULL,NULL),(102,'000000',100,'0,100','长沙分公司',NULL,2,NULL,'15888888888','xxx@qq.com','0','0',103,1,'2024-12-04 17:33:23',NULL,NULL),(103,'000000',101,'0,100,101','研发部门',NULL,1,1,'15888888888','xxx@qq.com','0','0',103,1,'2024-12-04 17:33:23',NULL,NULL),(104,'000000',101,'0,100,101','市场部门',NULL,2,NULL,'15888888888','xxx@qq.com','0','0',103,1,'2024-12-04 17:33:23',NULL,NULL),(105,'000000',101,'0,100,101','测试部门',NULL,3,NULL,'15888888888','xxx@qq.com','0','0',103,1,'2024-12-04 17:33:23',NULL,NULL),(106,'000000',101,'0,100,101','财务部门',NULL,4,NULL,'15888888888','xxx@qq.com','0','0',103,1,'2024-12-04 17:33:23',NULL,NULL),(107,'000000',101,'0,100,101','运维部门',NULL,5,NULL,'15888888888','xxx@qq.com','0','0',103,1,'2024-12-04 17:33:23',NULL,NULL),(108,'000000',102,'0,100,102','市场部门',NULL,1,NULL,'15888888888','xxx@qq.com','0','0',103,1,'2024-12-04 17:33:23',NULL,NULL),(109,'000000',102,'0,100,102','财务部门',NULL,2,NULL,'15888888888','xxx@qq.com','0','0',103,1,'2024-12-04 17:33:23',NULL,NULL);
/*!40000 ALTER TABLE `sys_dept` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_dict_data`
--

DROP TABLE IF EXISTS `sys_dict_data`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sys_dict_data` (
  `dict_code` bigint NOT NULL COMMENT '字典编码',
  `tenant_id` varchar(20) COLLATE utf8mb4_general_ci DEFAULT '000000' COMMENT '租户编号',
  `dict_sort` int DEFAULT '0' COMMENT '字典排序',
  `dict_label` varchar(100) COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '字典标签',
  `dict_value` varchar(100) COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '字典键值',
  `dict_type` varchar(100) COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '字典类型',
  `css_class` varchar(100) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '样式属性（其他样式扩展）',
  `list_class` varchar(100) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '表格回显样式',
  `is_default` char(1) COLLATE utf8mb4_general_ci DEFAULT 'N' COMMENT '是否默认（Y是 N否）',
  `create_dept` bigint DEFAULT NULL COMMENT '创建部门',
  `create_by` bigint DEFAULT NULL COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` bigint DEFAULT NULL COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`dict_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='字典数据表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_dict_data`
--

LOCK TABLES `sys_dict_data` WRITE;
/*!40000 ALTER TABLE `sys_dict_data` DISABLE KEYS */;
INSERT INTO `sys_dict_data` VALUES (1,'000000',1,'男','0','sys_user_sex','','','Y',103,1,'2024-12-04 17:33:29',NULL,NULL,'性别男'),(2,'000000',2,'女','1','sys_user_sex','','','N',103,1,'2024-12-04 17:33:29',NULL,NULL,'性别女'),(3,'000000',3,'未知','2','sys_user_sex','','','N',103,1,'2024-12-04 17:33:29',NULL,NULL,'性别未知'),(4,'000000',1,'显示','0','sys_show_hide','','primary','Y',103,1,'2024-12-04 17:33:29',NULL,NULL,'显示菜单'),(5,'000000',2,'隐藏','1','sys_show_hide','','danger','N',103,1,'2024-12-04 17:33:29',NULL,NULL,'隐藏菜单'),(6,'000000',1,'正常','0','sys_normal_disable','','primary','Y',103,1,'2024-12-04 17:33:29',NULL,NULL,'正常状态'),(7,'000000',2,'停用','1','sys_normal_disable','','danger','N',103,1,'2024-12-04 17:33:29',NULL,NULL,'停用状态'),(12,'000000',1,'是','Y','sys_yes_no','','primary','Y',103,1,'2024-12-04 17:33:29',NULL,NULL,'系统默认是'),(13,'000000',2,'否','N','sys_yes_no','','danger','N',103,1,'2024-12-04 17:33:29',NULL,NULL,'系统默认否'),(14,'000000',1,'通知','1','sys_notice_type','','warning','Y',103,1,'2024-12-04 17:33:29',NULL,NULL,'通知'),(15,'000000',2,'公告','2','sys_notice_type','','success','N',103,1,'2024-12-04 17:33:29',NULL,NULL,'公告'),(16,'000000',1,'正常','0','sys_notice_status','','primary','Y',103,1,'2024-12-04 17:33:29',NULL,NULL,'正常状态'),(17,'000000',2,'关闭','1','sys_notice_status','','danger','N',103,1,'2024-12-04 17:33:29',NULL,NULL,'关闭状态'),(18,'000000',1,'新增','1','sys_oper_type','','info','N',103,1,'2024-12-04 17:33:29',NULL,NULL,'新增操作'),(19,'000000',2,'修改','2','sys_oper_type','','info','N',103,1,'2024-12-04 17:33:29',NULL,NULL,'修改操作'),(20,'000000',3,'删除','3','sys_oper_type','','danger','N',103,1,'2024-12-04 17:33:29',NULL,NULL,'删除操作'),(21,'000000',4,'授权','4','sys_oper_type','','primary','N',103,1,'2024-12-04 17:33:29',NULL,NULL,'授权操作'),(22,'000000',5,'导出','5','sys_oper_type','','warning','N',103,1,'2024-12-04 17:33:29',NULL,NULL,'导出操作'),(23,'000000',6,'导入','6','sys_oper_type','','warning','N',103,1,'2024-12-04 17:33:29',NULL,NULL,'导入操作'),(24,'000000',7,'强退','7','sys_oper_type','','danger','N',103,1,'2024-12-04 17:33:29',NULL,NULL,'强退操作'),(25,'000000',8,'生成代码','8','sys_oper_type','','warning','N',103,1,'2024-12-04 17:33:29',NULL,NULL,'生成操作'),(26,'000000',9,'清空数据','9','sys_oper_type','','danger','N',103,1,'2024-12-04 17:33:29',NULL,NULL,'清空操作'),(27,'000000',1,'成功','0','sys_common_status','','primary','N',103,1,'2024-12-04 17:33:29',NULL,NULL,'正常状态'),(28,'000000',2,'失败','1','sys_common_status','','danger','N',103,1,'2024-12-04 17:33:29',NULL,NULL,'停用状态'),(29,'000000',99,'其他','0','sys_oper_type','','info','N',103,1,'2024-12-04 17:33:29',NULL,NULL,'其他操作'),(30,'000000',0,'密码认证','password','sys_grant_type','el-check-tag','default','N',103,1,'2024-12-04 17:33:29',NULL,NULL,'密码认证'),(31,'000000',0,'短信认证','sms','sys_grant_type','el-check-tag','default','N',103,1,'2024-12-04 17:33:29',NULL,NULL,'短信认证'),(32,'000000',0,'邮件认证','email','sys_grant_type','el-check-tag','default','N',103,1,'2024-12-04 17:33:29',NULL,NULL,'邮件认证'),(33,'000000',0,'小程序认证','xcx','sys_grant_type','el-check-tag','default','N',103,1,'2024-12-04 17:33:29',NULL,NULL,'小程序认证'),(34,'000000',0,'三方登录认证','social','sys_grant_type','el-check-tag','default','N',103,1,'2024-12-04 17:33:29',NULL,NULL,'三方登录认证'),(35,'000000',0,'PC','pc','sys_device_type','','default','N',103,1,'2024-12-04 17:33:29',NULL,NULL,'PC'),(36,'000000',0,'安卓','android','sys_device_type','','default','N',103,1,'2024-12-04 17:33:29',NULL,NULL,'安卓'),(37,'000000',0,'iOS','ios','sys_device_type','','default','N',103,1,'2024-12-04 17:33:29',NULL,NULL,'iOS'),(38,'000000',0,'小程序','xcx','sys_device_type','','default','N',103,1,'2024-12-04 17:33:29',NULL,NULL,'小程序'),(39,'000000',1,'已撤销','cancel','wf_business_status','','danger','N',103,1,'2024-12-04 17:33:29',NULL,NULL,'已撤销'),(40,'000000',2,'草稿','draft','wf_business_status','','info','N',103,1,'2024-12-04 17:33:29',NULL,NULL,'草稿'),(41,'000000',3,'待审核','waiting','wf_business_status','','primary','N',103,1,'2024-12-04 17:33:29',NULL,NULL,'待审核'),(42,'000000',4,'已完成','finish','wf_business_status','','success','N',103,1,'2024-12-04 17:33:29',NULL,NULL,'已完成'),(43,'000000',5,'已作废','invalid','wf_business_status','','danger','N',103,1,'2024-12-04 17:33:29',NULL,NULL,'已作废'),(44,'000000',6,'已退回','back','wf_business_status','','danger','N',103,1,'2024-12-04 17:33:29',NULL,NULL,'已退回'),(45,'000000',7,'已终止','termination','wf_business_status','','danger','N',103,1,'2024-12-04 17:33:29',NULL,NULL,'已终止'),(46,'000000',1,'自定义表单','static','wf_form_type','','success','N',103,1,'2024-12-04 17:33:29',NULL,NULL,'自定义表单'),(47,'000000',2,'动态表单','dynamic','wf_form_type','','primary','N',103,1,'2024-12-04 17:33:29',NULL,NULL,'动态表单');
/*!40000 ALTER TABLE `sys_dict_data` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_dict_type`
--

DROP TABLE IF EXISTS `sys_dict_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sys_dict_type` (
  `dict_id` bigint NOT NULL COMMENT '字典主键',
  `tenant_id` varchar(20) COLLATE utf8mb4_general_ci DEFAULT '000000' COMMENT '租户编号',
  `dict_name` varchar(100) COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '字典名称',
  `dict_type` varchar(100) COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '字典类型',
  `create_dept` bigint DEFAULT NULL COMMENT '创建部门',
  `create_by` bigint DEFAULT NULL COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` bigint DEFAULT NULL COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`dict_id`),
  UNIQUE KEY `tenant_id` (`tenant_id`,`dict_type`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='字典类型表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_dict_type`
--

LOCK TABLES `sys_dict_type` WRITE;
/*!40000 ALTER TABLE `sys_dict_type` DISABLE KEYS */;
INSERT INTO `sys_dict_type` VALUES (1,'000000','用户性别','sys_user_sex',103,1,'2024-12-04 17:33:28',NULL,NULL,'用户性别列表'),(2,'000000','菜单状态','sys_show_hide',103,1,'2024-12-04 17:33:28',NULL,NULL,'菜单状态列表'),(3,'000000','系统开关','sys_normal_disable',103,1,'2024-12-04 17:33:28',NULL,NULL,'系统开关列表'),(6,'000000','系统是否','sys_yes_no',103,1,'2024-12-04 17:33:28',NULL,NULL,'系统是否列表'),(7,'000000','通知类型','sys_notice_type',103,1,'2024-12-04 17:33:28',NULL,NULL,'通知类型列表'),(8,'000000','通知状态','sys_notice_status',103,1,'2024-12-04 17:33:28',NULL,NULL,'通知状态列表'),(9,'000000','操作类型','sys_oper_type',103,1,'2024-12-04 17:33:28',NULL,NULL,'操作类型列表'),(10,'000000','系统状态','sys_common_status',103,1,'2024-12-04 17:33:28',NULL,NULL,'登录状态列表'),(11,'000000','授权类型','sys_grant_type',103,1,'2024-12-04 17:33:28',NULL,NULL,'认证授权类型'),(12,'000000','设备类型','sys_device_type',103,1,'2024-12-04 17:33:28',NULL,NULL,'客户端设备类型'),(13,'000000','业务状态','wf_business_status',103,1,'2024-12-04 17:33:28',NULL,NULL,'业务状态列表'),(14,'000000','表单类型','wf_form_type',103,1,'2024-12-04 17:33:28',NULL,NULL,'表单类型列表');
/*!40000 ALTER TABLE `sys_dict_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_logininfor`
--

DROP TABLE IF EXISTS `sys_logininfor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sys_logininfor` (
  `info_id` bigint NOT NULL COMMENT '访问ID',
  `tenant_id` varchar(20) COLLATE utf8mb4_general_ci DEFAULT '000000' COMMENT '租户编号',
  `user_name` varchar(50) COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '用户账号',
  `client_key` varchar(32) COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '客户端',
  `device_type` varchar(32) COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '设备类型',
  `ipaddr` varchar(128) COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '登录IP地址',
  `login_location` varchar(255) COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '登录地点',
  `browser` varchar(50) COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '浏览器类型',
  `os` varchar(50) COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '操作系统',
  `status` char(1) COLLATE utf8mb4_general_ci DEFAULT '0' COMMENT '登录状态（0成功 1失败）',
  `msg` varchar(255) COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '提示消息',
  `login_time` datetime DEFAULT NULL COMMENT '访问时间',
  PRIMARY KEY (`info_id`),
  KEY `idx_sys_logininfor_s` (`status`),
  KEY `idx_sys_logininfor_lt` (`login_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='系统访问记录';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_logininfor`
--

LOCK TABLES `sys_logininfor` WRITE;
/*!40000 ALTER TABLE `sys_logininfor` DISABLE KEYS */;
INSERT INTO `sys_logininfor` VALUES (1864576894171594753,'000000','admin','pc','pc','0:0:0:0:0:0:0:1','内网IP','Chrome','Windows 10 or Windows Server 2016','0','登录成功','2024-12-05 15:45:48'),(1864599186725318657,'000000','admin','pc','pc','0:0:0:0:0:0:0:1','内网IP','Chrome','Windows 10 or Windows Server 2016','0','登录成功','2024-12-05 17:14:23'),(1864599532424048641,'000000','admin','pc','pc','0:0:0:0:0:0:0:1','内网IP','Chrome','Windows 10 or Windows Server 2016','0','登录成功','2024-12-05 17:15:46'),(1864600889302364162,'000000','admin','pc','pc','0:0:0:0:0:0:0:1','内网IP','Chrome','Windows 10 or Windows Server 2016','0','退出成功','2024-12-05 17:21:09'),(1864600965399621634,'000000','admin','pc','pc','0:0:0:0:0:0:0:1','内网IP','Chrome','Windows 10 or Windows Server 2016','0','登录成功','2024-12-05 17:21:27'),(1864607276300472322,'000000','admin','pc','pc','0:0:0:0:0:0:0:1','内网IP','Chrome','Windows 10 or Windows Server 2016','0','登录成功','2024-12-05 17:46:32'),(1864839573771542530,'000000','admin','pc','pc','0:0:0:0:0:0:0:1','内网IP','Chrome','Windows 10 or Windows Server 2016','0','登录成功','2024-12-06 09:09:36'),(1864861810960523265,'000000','admin','pc','pc','0:0:0:0:0:0:0:1','内网IP','Chrome','Windows 10 or Windows Server 2016','0','登录成功','2024-12-06 10:37:58');
/*!40000 ALTER TABLE `sys_logininfor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_menu`
--

DROP TABLE IF EXISTS `sys_menu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sys_menu` (
  `menu_id` bigint NOT NULL COMMENT '菜单ID',
  `menu_name` varchar(50) COLLATE utf8mb4_general_ci NOT NULL COMMENT '菜单名称',
  `parent_id` bigint DEFAULT '0' COMMENT '父菜单ID',
  `order_num` int DEFAULT '0' COMMENT '显示顺序',
  `path` varchar(200) COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '路由地址',
  `component` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '组件路径',
  `query_param` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '路由参数',
  `is_frame` int DEFAULT '1' COMMENT '是否为外链（0是 1否）',
  `is_cache` int DEFAULT '0' COMMENT '是否缓存（0缓存 1不缓存）',
  `menu_type` char(1) COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '菜单类型（M目录 C菜单 F按钮）',
  `visible` char(1) COLLATE utf8mb4_general_ci DEFAULT '0' COMMENT '显示状态（0显示 1隐藏）',
  `status` char(1) COLLATE utf8mb4_general_ci DEFAULT '0' COMMENT '菜单状态（0正常 1停用）',
  `perms` varchar(100) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '权限标识',
  `icon` varchar(100) COLLATE utf8mb4_general_ci DEFAULT '#' COMMENT '菜单图标',
  `create_dept` bigint DEFAULT NULL COMMENT '创建部门',
  `create_by` bigint DEFAULT NULL COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` bigint DEFAULT NULL COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '备注',
  PRIMARY KEY (`menu_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='菜单权限表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_menu`
--

LOCK TABLES `sys_menu` WRITE;
/*!40000 ALTER TABLE `sys_menu` DISABLE KEYS */;
INSERT INTO `sys_menu` VALUES (1,'系统管理',0,1,'system',NULL,'',1,0,'M','0','0','','system',103,1,'2024-12-04 17:33:25',NULL,NULL,'系统管理目录'),(2,'系统监控',0,3,'monitor',NULL,'',1,0,'M','0','0','','monitor',103,1,'2024-12-04 17:33:25',NULL,NULL,'系统监控目录'),(3,'系统工具',0,4,'tool',NULL,'',1,0,'M','0','0','','tool',103,1,'2024-12-04 17:33:25',NULL,NULL,'系统工具目录'),(5,'测试菜单',0,5,'demo',NULL,'',1,0,'M','0','0','','star',103,1,'2024-12-04 17:33:25',NULL,NULL,'测试菜单'),(6,'租户管理',0,2,'tenant',NULL,'',1,0,'M','0','0','','chart',103,1,'2024-12-04 17:33:25',NULL,NULL,'租户管理目录'),(100,'用户管理',1,1,'user','system/user/index','',1,0,'C','0','0','system:user:list','user',103,1,'2024-12-04 17:33:25',NULL,NULL,'用户管理菜单'),(101,'角色管理',1,2,'role','system/role/index','',1,0,'C','0','0','system:role:list','peoples',103,1,'2024-12-04 17:33:25',NULL,NULL,'角色管理菜单'),(102,'菜单管理',1,3,'menu','system/menu/index','',1,0,'C','0','0','system:menu:list','tree-table',103,1,'2024-12-04 17:33:25',NULL,NULL,'菜单管理菜单'),(103,'部门管理',1,4,'dept','system/dept/index','',1,0,'C','0','0','system:dept:list','tree',103,1,'2024-12-04 17:33:25',NULL,NULL,'部门管理菜单'),(104,'岗位管理',1,5,'post','system/post/index','',1,0,'C','0','0','system:post:list','post',103,1,'2024-12-04 17:33:25',NULL,NULL,'岗位管理菜单'),(105,'字典管理',1,6,'dict','system/dict/index','',1,0,'C','0','0','system:dict:list','dict',103,1,'2024-12-04 17:33:25',NULL,NULL,'字典管理菜单'),(106,'参数设置',1,7,'config','system/config/index','',1,0,'C','0','0','system:config:list','edit',103,1,'2024-12-04 17:33:25',NULL,NULL,'参数设置菜单'),(107,'通知公告',1,8,'notice','system/notice/index','',1,0,'C','0','0','system:notice:list','message',103,1,'2024-12-04 17:33:25',NULL,NULL,'通知公告菜单'),(108,'日志管理',1,9,'log','','',1,0,'M','0','0','','log',103,1,'2024-12-04 17:33:25',NULL,NULL,'日志管理菜单'),(109,'在线用户',2,1,'online','monitor/online/index','',1,0,'C','0','0','monitor:online:list','online',103,1,'2024-12-04 17:33:25',NULL,NULL,'在线用户菜单'),(110,'SnailJob控制台',2,2,'http://localhost:8800/snail-job','','',0,0,'C','0','0','monitor:job:list','job',103,1,'2024-12-04 17:33:25',NULL,NULL,'SJ定时任务菜单'),(111,'Sentinel控制台',2,3,'http://localhost:8718','','',0,0,'C','0','0','monitor:sentinel:list','sentinel',103,1,'2024-12-04 17:33:25',NULL,NULL,'流量控制菜单'),(112,'Nacos控制台',2,4,'http://localhost:8848/nacos','','',0,0,'C','0','0','monitor:nacos:list','nacos',103,1,'2024-12-04 17:33:25',NULL,NULL,'服务治理菜单'),(113,'Admin控制台',2,5,'http://localhost:9100/login','','',0,0,'C','0','0','monitor:server:list','server',103,1,'2024-12-04 17:33:25',NULL,NULL,'服务监控菜单'),(115,'代码生成',3,2,'gen','tool/gen/index','',1,0,'C','0','0','tool:gen:list','code',103,1,'2024-12-04 17:33:25',NULL,NULL,'代码生成菜单'),(118,'文件管理',1,10,'oss','system/oss/index','',1,0,'C','0','0','system:oss:list','upload',103,1,'2024-12-04 17:33:25',NULL,NULL,'文件管理菜单'),(121,'租户管理',6,1,'tenant','system/tenant/index','',1,0,'C','0','0','system:tenant:list','list',103,1,'2024-12-04 17:33:25',NULL,NULL,'租户管理菜单'),(122,'租户套餐管理',6,2,'tenantPackage','system/tenantPackage/index','',1,0,'C','0','0','system:tenantPackage:list','form',103,1,'2024-12-04 17:33:25',NULL,NULL,'租户套餐管理菜单'),(123,'客户端管理',1,11,'client','system/client/index','',1,0,'C','0','0','system:client:list','international',103,1,'2024-12-04 17:33:25',NULL,NULL,'客户端管理菜单'),(124,'缓存监控',2,1,'cache','monitor/cache/index','',1,0,'C','0','0','monitor:cache:list','redis',103,1,'2024-12-04 17:33:25',NULL,NULL,'缓存监控'),(500,'操作日志',108,1,'operlog','monitor/operlog/index','',1,0,'C','0','0','monitor:operlog:list','form',103,1,'2024-12-04 17:33:25',NULL,NULL,'操作日志菜单'),(501,'登录日志',108,2,'logininfor','monitor/logininfor/index','',1,0,'C','0','0','monitor:logininfor:list','logininfor',103,1,'2024-12-04 17:33:25',NULL,NULL,'登录日志菜单'),(1001,'用户查询',100,1,'','','',1,0,'F','0','0','system:user:query','#',103,1,'2024-12-04 17:33:25',NULL,NULL,''),(1002,'用户新增',100,2,'','','',1,0,'F','0','0','system:user:add','#',103,1,'2024-12-04 17:33:25',NULL,NULL,''),(1003,'用户修改',100,3,'','','',1,0,'F','0','0','system:user:edit','#',103,1,'2024-12-04 17:33:25',NULL,NULL,''),(1004,'用户删除',100,4,'','','',1,0,'F','0','0','system:user:remove','#',103,1,'2024-12-04 17:33:25',NULL,NULL,''),(1005,'用户导出',100,5,'','','',1,0,'F','0','0','system:user:export','#',103,1,'2024-12-04 17:33:25',NULL,NULL,''),(1006,'用户导入',100,6,'','','',1,0,'F','0','0','system:user:import','#',103,1,'2024-12-04 17:33:25',NULL,NULL,''),(1007,'重置密码',100,7,'','','',1,0,'F','0','0','system:user:resetPwd','#',103,1,'2024-12-04 17:33:25',NULL,NULL,''),(1008,'角色查询',101,1,'','','',1,0,'F','0','0','system:role:query','#',103,1,'2024-12-04 17:33:25',NULL,NULL,''),(1009,'角色新增',101,2,'','','',1,0,'F','0','0','system:role:add','#',103,1,'2024-12-04 17:33:25',NULL,NULL,''),(1010,'角色修改',101,3,'','','',1,0,'F','0','0','system:role:edit','#',103,1,'2024-12-04 17:33:25',NULL,NULL,''),(1011,'角色删除',101,4,'','','',1,0,'F','0','0','system:role:remove','#',103,1,'2024-12-04 17:33:25',NULL,NULL,''),(1012,'角色导出',101,5,'','','',1,0,'F','0','0','system:role:export','#',103,1,'2024-12-04 17:33:25',NULL,NULL,''),(1013,'菜单查询',102,1,'','','',1,0,'F','0','0','system:menu:query','#',103,1,'2024-12-04 17:33:25',NULL,NULL,''),(1014,'菜单新增',102,2,'','','',1,0,'F','0','0','system:menu:add','#',103,1,'2024-12-04 17:33:25',NULL,NULL,''),(1015,'菜单修改',102,3,'','','',1,0,'F','0','0','system:menu:edit','#',103,1,'2024-12-04 17:33:25',NULL,NULL,''),(1016,'菜单删除',102,4,'','','',1,0,'F','0','0','system:menu:remove','#',103,1,'2024-12-04 17:33:25',NULL,NULL,''),(1017,'部门查询',103,1,'','','',1,0,'F','0','0','system:dept:query','#',103,1,'2024-12-04 17:33:25',NULL,NULL,''),(1018,'部门新增',103,2,'','','',1,0,'F','0','0','system:dept:add','#',103,1,'2024-12-04 17:33:25',NULL,NULL,''),(1019,'部门修改',103,3,'','','',1,0,'F','0','0','system:dept:edit','#',103,1,'2024-12-04 17:33:25',NULL,NULL,''),(1020,'部门删除',103,4,'','','',1,0,'F','0','0','system:dept:remove','#',103,1,'2024-12-04 17:33:25',NULL,NULL,''),(1021,'岗位查询',104,1,'','','',1,0,'F','0','0','system:post:query','#',103,1,'2024-12-04 17:33:25',NULL,NULL,''),(1022,'岗位新增',104,2,'','','',1,0,'F','0','0','system:post:add','#',103,1,'2024-12-04 17:33:25',NULL,NULL,''),(1023,'岗位修改',104,3,'','','',1,0,'F','0','0','system:post:edit','#',103,1,'2024-12-04 17:33:25',NULL,NULL,''),(1024,'岗位删除',104,4,'','','',1,0,'F','0','0','system:post:remove','#',103,1,'2024-12-04 17:33:25',NULL,NULL,''),(1025,'岗位导出',104,5,'','','',1,0,'F','0','0','system:post:export','#',103,1,'2024-12-04 17:33:25',NULL,NULL,''),(1026,'字典查询',105,1,'#','','',1,0,'F','0','0','system:dict:query','#',103,1,'2024-12-04 17:33:25',NULL,NULL,''),(1027,'字典新增',105,2,'#','','',1,0,'F','0','0','system:dict:add','#',103,1,'2024-12-04 17:33:25',NULL,NULL,''),(1028,'字典修改',105,3,'#','','',1,0,'F','0','0','system:dict:edit','#',103,1,'2024-12-04 17:33:25',NULL,NULL,''),(1029,'字典删除',105,4,'#','','',1,0,'F','0','0','system:dict:remove','#',103,1,'2024-12-04 17:33:25',NULL,NULL,''),(1030,'字典导出',105,5,'#','','',1,0,'F','0','0','system:dict:export','#',103,1,'2024-12-04 17:33:25',NULL,NULL,''),(1031,'参数查询',106,1,'#','','',1,0,'F','0','0','system:config:query','#',103,1,'2024-12-04 17:33:25',NULL,NULL,''),(1032,'参数新增',106,2,'#','','',1,0,'F','0','0','system:config:add','#',103,1,'2024-12-04 17:33:25',NULL,NULL,''),(1033,'参数修改',106,3,'#','','',1,0,'F','0','0','system:config:edit','#',103,1,'2024-12-04 17:33:25',NULL,NULL,''),(1034,'参数删除',106,4,'#','','',1,0,'F','0','0','system:config:remove','#',103,1,'2024-12-04 17:33:25',NULL,NULL,''),(1035,'参数导出',106,5,'#','','',1,0,'F','0','0','system:config:export','#',103,1,'2024-12-04 17:33:25',NULL,NULL,''),(1036,'公告查询',107,1,'#','','',1,0,'F','0','0','system:notice:query','#',103,1,'2024-12-04 17:33:25',NULL,NULL,''),(1037,'公告新增',107,2,'#','','',1,0,'F','0','0','system:notice:add','#',103,1,'2024-12-04 17:33:25',NULL,NULL,''),(1038,'公告修改',107,3,'#','','',1,0,'F','0','0','system:notice:edit','#',103,1,'2024-12-04 17:33:25',NULL,NULL,''),(1039,'公告删除',107,4,'#','','',1,0,'F','0','0','system:notice:remove','#',103,1,'2024-12-04 17:33:25',NULL,NULL,''),(1040,'操作查询',500,1,'#','','',1,0,'F','0','0','monitor:operlog:query','#',103,1,'2024-12-04 17:33:25',NULL,NULL,''),(1041,'操作删除',500,2,'#','','',1,0,'F','0','0','monitor:operlog:remove','#',103,1,'2024-12-04 17:33:25',NULL,NULL,''),(1042,'日志导出',500,4,'#','','',1,0,'F','0','0','monitor:operlog:export','#',103,1,'2024-12-04 17:33:25',NULL,NULL,''),(1043,'登录查询',501,1,'#','','',1,0,'F','0','0','monitor:logininfor:query','#',103,1,'2024-12-04 17:33:25',NULL,NULL,''),(1044,'登录删除',501,2,'#','','',1,0,'F','0','0','monitor:logininfor:remove','#',103,1,'2024-12-04 17:33:25',NULL,NULL,''),(1045,'日志导出',501,3,'#','','',1,0,'F','0','0','monitor:logininfor:export','#',103,1,'2024-12-04 17:33:25',NULL,NULL,''),(1046,'在线查询',109,1,'#','','',1,0,'F','0','0','monitor:online:query','#',103,1,'2024-12-04 17:33:25',NULL,NULL,''),(1047,'批量强退',109,2,'#','','',1,0,'F','0','0','monitor:online:batchLogout','#',103,1,'2024-12-04 17:33:25',NULL,NULL,''),(1048,'单条强退',109,3,'#','','',1,0,'F','0','0','monitor:online:forceLogout','#',103,1,'2024-12-04 17:33:25',NULL,NULL,''),(1050,'账户解锁',501,4,'#','','',1,0,'F','0','0','monitor:logininfor:unlock','#',103,1,'2024-12-04 17:33:25',NULL,NULL,''),(1055,'生成查询',115,1,'#','','',1,0,'F','0','0','tool:gen:query','#',103,1,'2024-12-04 17:33:25',NULL,NULL,''),(1056,'生成修改',115,2,'#','','',1,0,'F','0','0','tool:gen:edit','#',103,1,'2024-12-04 17:33:25',NULL,NULL,''),(1057,'生成删除',115,3,'#','','',1,0,'F','0','0','tool:gen:remove','#',103,1,'2024-12-04 17:33:25',NULL,NULL,''),(1058,'导入代码',115,2,'#','','',1,0,'F','0','0','tool:gen:import','#',103,1,'2024-12-04 17:33:25',NULL,NULL,''),(1059,'预览代码',115,4,'#','','',1,0,'F','0','0','tool:gen:preview','#',103,1,'2024-12-04 17:33:25',NULL,NULL,''),(1060,'生成代码',115,5,'#','','',1,0,'F','0','0','tool:gen:code','#',103,1,'2024-12-04 17:33:25',NULL,NULL,''),(1061,'客户端管理查询',123,1,'#','','',1,0,'F','0','0','system:client:query','#',103,1,'2024-12-04 17:33:25',NULL,NULL,''),(1062,'客户端管理新增',123,2,'#','','',1,0,'F','0','0','system:client:add','#',103,1,'2024-12-04 17:33:25',NULL,NULL,''),(1063,'客户端管理修改',123,3,'#','','',1,0,'F','0','0','system:client:edit','#',103,1,'2024-12-04 17:33:25',NULL,NULL,''),(1064,'客户端管理删除',123,4,'#','','',1,0,'F','0','0','system:client:remove','#',103,1,'2024-12-04 17:33:25',NULL,NULL,''),(1065,'客户端管理导出',123,5,'#','','',1,0,'F','0','0','system:client:export','#',103,1,'2024-12-04 17:33:25',NULL,NULL,''),(1500,'测试单表',5,1,'demo','demo/demo/index','',1,0,'C','0','0','demo:demo:list','#',103,1,'2024-12-04 17:33:25',NULL,NULL,'测试单表菜单'),(1501,'测试单表查询',1500,1,'#','','',1,0,'F','0','0','demo:demo:query','#',103,1,'2024-12-04 17:33:25',NULL,NULL,''),(1502,'测试单表新增',1500,2,'#','','',1,0,'F','0','0','demo:demo:add','#',103,1,'2024-12-04 17:33:25',NULL,NULL,''),(1503,'测试单表修改',1500,3,'#','','',1,0,'F','0','0','demo:demo:edit','#',103,1,'2024-12-04 17:33:25',NULL,NULL,''),(1504,'测试单表删除',1500,4,'#','','',1,0,'F','0','0','demo:demo:remove','#',103,1,'2024-12-04 17:33:25',NULL,NULL,''),(1505,'测试单表导出',1500,5,'#','','',1,0,'F','0','0','demo:demo:export','#',103,1,'2024-12-04 17:33:25',NULL,NULL,''),(1506,'测试树表',5,1,'tree','demo/tree/index','',1,0,'C','0','0','demo:tree:list','#',103,1,'2024-12-04 17:33:25',NULL,NULL,'测试树表菜单'),(1507,'测试树表查询',1506,1,'#','','',1,0,'F','0','0','demo:tree:query','#',103,1,'2024-12-04 17:33:25',NULL,NULL,''),(1508,'测试树表新增',1506,2,'#','','',1,0,'F','0','0','demo:tree:add','#',103,1,'2024-12-04 17:33:25',NULL,NULL,''),(1509,'测试树表修改',1506,3,'#','','',1,0,'F','0','0','demo:tree:edit','#',103,1,'2024-12-04 17:33:25',NULL,NULL,''),(1510,'测试树表删除',1506,4,'#','','',1,0,'F','0','0','demo:tree:remove','#',103,1,'2024-12-04 17:33:25',NULL,NULL,''),(1511,'测试树表导出',1506,5,'#','','',1,0,'F','0','0','demo:tree:export','#',103,1,'2024-12-04 17:33:25',NULL,NULL,''),(1600,'文件查询',118,1,'#','','',1,0,'F','0','0','system:oss:query','#',103,1,'2024-12-04 17:33:25',NULL,NULL,''),(1601,'文件上传',118,2,'#','','',1,0,'F','0','0','system:oss:upload','#',103,1,'2024-12-04 17:33:25',NULL,NULL,''),(1602,'文件下载',118,3,'#','','',1,0,'F','0','0','system:oss:download','#',103,1,'2024-12-04 17:33:25',NULL,NULL,''),(1603,'文件删除',118,4,'#','','',1,0,'F','0','0','system:oss:remove','#',103,1,'2024-12-04 17:33:25',NULL,NULL,''),(1606,'租户查询',121,1,'#','','',1,0,'F','0','0','system:tenant:query','#',103,1,'2024-12-04 17:33:25',NULL,NULL,''),(1607,'租户新增',121,2,'#','','',1,0,'F','0','0','system:tenant:add','#',103,1,'2024-12-04 17:33:25',NULL,NULL,''),(1608,'租户修改',121,3,'#','','',1,0,'F','0','0','system:tenant:edit','#',103,1,'2024-12-04 17:33:25',NULL,NULL,''),(1609,'租户删除',121,4,'#','','',1,0,'F','0','0','system:tenant:remove','#',103,1,'2024-12-04 17:33:25',NULL,NULL,''),(1610,'租户导出',121,5,'#','','',1,0,'F','0','0','system:tenant:export','#',103,1,'2024-12-04 17:33:25',NULL,NULL,''),(1611,'租户套餐查询',122,1,'#','','',1,0,'F','0','0','system:tenantPackage:query','#',103,1,'2024-12-04 17:33:25',NULL,NULL,''),(1612,'租户套餐新增',122,2,'#','','',1,0,'F','0','0','system:tenantPackage:add','#',103,1,'2024-12-04 17:33:25',NULL,NULL,''),(1613,'租户套餐修改',122,3,'#','','',1,0,'F','0','0','system:tenantPackage:edit','#',103,1,'2024-12-04 17:33:25',NULL,NULL,''),(1614,'租户套餐删除',122,4,'#','','',1,0,'F','0','0','system:tenantPackage:remove','#',103,1,'2024-12-04 17:33:25',NULL,NULL,''),(1615,'租户套餐导出',122,5,'#','','',1,0,'F','0','0','system:tenantPackage:export','#',103,1,'2024-12-04 17:33:25',NULL,NULL,''),(1620,'配置列表',118,5,'#','','',1,0,'F','0','0','system:ossConfig:list','#',103,1,'2024-12-04 17:33:25',NULL,NULL,''),(1621,'配置添加',118,6,'#','','',1,0,'F','0','0','system:ossConfig:add','#',103,1,'2024-12-04 17:33:25',NULL,NULL,''),(1622,'配置编辑',118,6,'#','','',1,0,'F','0','0','system:ossConfig:edit','#',103,1,'2024-12-04 17:33:25',NULL,NULL,''),(1623,'配置删除',118,6,'#','','',1,0,'F','0','0','system:ossConfig:remove','#',103,1,'2024-12-04 17:33:25',NULL,NULL,''),(11616,'工作流',0,6,'workflow','','',1,0,'M','0','0','','workflow',103,1,'2024-12-04 17:33:25',NULL,NULL,''),(11617,'模型管理',11616,2,'model','workflow/model/index','',1,1,'C','0','0','workflow:model:list','model',103,1,'2024-12-04 17:33:25',NULL,NULL,''),(11618,'我的任务',0,7,'task','','',1,0,'M','0','0','','my-task',103,1,'2024-12-04 17:33:25',NULL,NULL,''),(11619,'我的待办',11618,2,'taskWaiting','workflow/task/taskWaiting','',1,1,'C','0','0','','waiting',103,1,'2024-12-04 17:33:25',NULL,NULL,''),(11620,'流程定义',11616,3,'processDefinition','workflow/processDefinition/index','',1,1,'C','0','0','','process-definition',103,1,'2024-12-04 17:33:25',NULL,NULL,''),(11621,'流程实例',11630,1,'processInstance','workflow/processInstance/index','',1,1,'C','0','0','','tree-table',103,1,'2024-12-04 17:33:25',NULL,NULL,''),(11622,'流程分类',11616,1,'category','workflow/category/index','',1,0,'C','0','0','workflow:category:list','category',103,1,'2024-12-04 17:33:25',NULL,NULL,''),(11623,'流程分类查询',11622,1,'#','','',1,0,'F','0','0','workflow:category:query','#',103,1,'2024-12-04 17:33:25',NULL,NULL,''),(11624,'流程分类新增',11622,2,'#','','',1,0,'F','0','0','workflow:category:add','#',103,1,'2024-12-04 17:33:25',NULL,NULL,''),(11625,'流程分类修改',11622,3,'#','','',1,0,'F','0','0','workflow:category:edit','#',103,1,'2024-12-04 17:33:25',NULL,NULL,''),(11626,'流程分类删除',11622,4,'#','','',1,0,'F','0','0','workflow:category:remove','#',103,1,'2024-12-04 17:33:25',NULL,NULL,''),(11627,'流程分类导出',11622,5,'#','','',1,0,'F','0','0','workflow:category:export','#',103,1,'2024-12-04 17:33:25',NULL,NULL,''),(11628,'表单管理',11616,5,'formManage','workflow/formManage/index','',1,0,'C','0','0','workflow:formManage:list','tree-table',103,1,'2024-12-04 17:33:25',NULL,NULL,'表单管理菜单'),(11629,'我发起的',11618,1,'myDocument','workflow/task/myDocument','',1,1,'C','0','0','','guide',103,1,'2024-12-04 17:33:25',NULL,NULL,''),(11630,'流程监控',11616,4,'monitor','','',1,0,'M','0','0','','monitor',103,1,'2024-12-04 17:33:25',NULL,NULL,''),(11631,'待办任务',11630,2,'allTaskWaiting','workflow/task/allTaskWaiting','',1,1,'C','0','0','','waiting',103,1,'2024-12-04 17:33:25',NULL,NULL,''),(11632,'我的已办',11618,3,'taskFinish','workflow/task/taskFinish','',1,1,'C','0','0','','finish',103,1,'2024-12-04 17:33:25',NULL,NULL,''),(11633,'我的抄送',11618,4,'taskCopyList','workflow/task/taskCopyList','',1,1,'C','0','0','','my-copy',103,1,'2024-12-04 17:33:25',NULL,NULL,''),(11638,'请假申请',5,1,'leave','workflow/leave/index','',1,0,'C','0','0','workflow:leave:list','#',103,1,'2024-12-04 17:33:25',NULL,NULL,'请假申请菜单'),(11639,'请假申请查询',11638,1,'#','','',1,0,'F','0','0','workflow:leave:query','#',103,1,'2024-12-04 17:33:25',NULL,NULL,''),(11640,'请假申请新增',11638,2,'#','','',1,0,'F','0','0','workflow:leave:add','#',103,1,'2024-12-04 17:33:25',NULL,NULL,''),(11641,'请假申请修改',11638,3,'#','','',1,0,'F','0','0','workflow:leave:edit','#',103,1,'2024-12-04 17:33:25',NULL,NULL,''),(11642,'请假申请删除',11638,4,'#','','',1,0,'F','0','0','workflow:leave:remove','#',103,1,'2024-12-04 17:33:25',NULL,NULL,''),(11643,'请假申请导出',11638,5,'#','','',1,0,'F','0','0','workflow:leave:export','#',103,1,'2024-12-04 17:33:25',NULL,NULL,''),(11644,'表单管理查询',11628,1,'#','','',1,0,'F','0','0','workflow:formManage:query','',103,1,'2024-12-04 17:33:25',NULL,NULL,''),(11645,'表单管理新增',11628,2,'#','','',1,0,'F','0','0','workflow:formManage:add','',103,1,'2024-12-04 17:33:25',NULL,NULL,''),(11646,'表单管理修改',11628,3,'#','','',1,0,'F','0','0','workflow:formManage:edit','',103,1,'2024-12-04 17:33:25',NULL,NULL,''),(11647,'表单管理删除',11628,4,'#','','',1,0,'F','0','0','workflow:formManage:remove','',103,1,'2024-12-04 17:33:25',NULL,NULL,''),(11648,'表单管理导出',11628,5,'#','','',1,0,'F','0','0','workflow:formManage:export','tree-table',103,1,'2024-12-04 17:33:25',NULL,NULL,'');
/*!40000 ALTER TABLE `sys_menu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_notice`
--

DROP TABLE IF EXISTS `sys_notice`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sys_notice` (
  `notice_id` bigint NOT NULL COMMENT '公告ID',
  `tenant_id` varchar(20) COLLATE utf8mb4_general_ci DEFAULT '000000' COMMENT '租户编号',
  `notice_title` varchar(50) COLLATE utf8mb4_general_ci NOT NULL COMMENT '公告标题',
  `notice_type` char(1) COLLATE utf8mb4_general_ci NOT NULL COMMENT '公告类型（1通知 2公告）',
  `notice_content` longblob COMMENT '公告内容',
  `status` char(1) COLLATE utf8mb4_general_ci DEFAULT '0' COMMENT '公告状态（0正常 1关闭）',
  `create_dept` bigint DEFAULT NULL COMMENT '创建部门',
  `create_by` bigint DEFAULT NULL COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` bigint DEFAULT NULL COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`notice_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='通知公告表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_notice`
--

LOCK TABLES `sys_notice` WRITE;
/*!40000 ALTER TABLE `sys_notice` DISABLE KEYS */;
INSERT INTO `sys_notice` VALUES (1,'000000','温馨提醒：2018-07-01 新版本发布啦','2',_binary '新版本内容','0',103,1,'2024-12-04 17:33:31',NULL,NULL,'管理员'),(2,'000000','维护通知：2018-07-01 系统凌晨维护','1',_binary '维护内容','0',103,1,'2024-12-04 17:33:31',NULL,NULL,'管理员');
/*!40000 ALTER TABLE `sys_notice` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_oper_log`
--

DROP TABLE IF EXISTS `sys_oper_log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sys_oper_log` (
  `oper_id` bigint NOT NULL COMMENT '日志主键',
  `tenant_id` varchar(20) COLLATE utf8mb4_general_ci DEFAULT '000000' COMMENT '租户编号',
  `title` varchar(50) COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '模块标题',
  `business_type` int DEFAULT '0' COMMENT '业务类型（0其它 1新增 2修改 3删除）',
  `method` varchar(100) COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '方法名称',
  `request_method` varchar(10) COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '请求方式',
  `operator_type` int DEFAULT '0' COMMENT '操作类别（0其它 1后台用户 2手机端用户）',
  `oper_name` varchar(50) COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '操作人员',
  `dept_name` varchar(50) COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '部门名称',
  `oper_url` varchar(255) COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '请求URL',
  `oper_ip` varchar(128) COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '主机地址',
  `oper_location` varchar(255) COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '操作地点',
  `oper_param` varchar(2000) COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '请求参数',
  `json_result` varchar(2000) COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '返回参数',
  `status` int DEFAULT '0' COMMENT '操作状态（0正常 1异常）',
  `error_msg` varchar(2000) COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '错误消息',
  `oper_time` datetime DEFAULT NULL COMMENT '操作时间',
  `cost_time` bigint DEFAULT '0' COMMENT '消耗时间',
  PRIMARY KEY (`oper_id`),
  KEY `idx_sys_oper_log_bt` (`business_type`),
  KEY `idx_sys_oper_log_s` (`status`),
  KEY `idx_sys_oper_log_ot` (`oper_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='操作日志记录';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_oper_log`
--

LOCK TABLES `sys_oper_log` WRITE;
/*!40000 ALTER TABLE `sys_oper_log` DISABLE KEYS */;
INSERT INTO `sys_oper_log` VALUES (1864603038639894530,'000000','菜单管理',3,'com.cdzeroly.system.controller.system.SysMenuController.remove()','DELETE',1,'admin','研发部门','/menu/4','0:0:0:0:0:0:0:1','','4','{\"code\":200,\"msg\":\"操作成功\",\"data\":null}',0,'','2024-12-05 17:29:42',798),(1864607726131187714,'000000','流程定义管理',1,'com.cdzeroly.workflow.controller.ActProcessDefinitionController.deployByFile()','POST',1,'admin','研发部门','/processDefinition/deployByFile','0:0:0:0:0:0:0:1','','{\"categoryCode\":\"OA\"}','',0,'','2024-12-05 17:48:19',3422),(1864607916078632962,'000000','请假',1,'com.cdzeroly.workflow.controller.TestLeaveController.add()','POST',1,'admin','研发部门','/leave','0:0:0:0:0:0:0:1','','{\"createDept\":null,\"createBy\":null,\"createTime\":null,\"updateBy\":null,\"updateTime\":null,\"id\":\"1864607914954567681\",\"leaveType\":\"1\",\"startDate\":\"2024-12-05\",\"endDate\":\"2025-01-15\",\"leaveDays\":42,\"startLeaveDays\":null,\"endLeaveDays\":null,\"remark\":\"玩\",\"status\":null}','{\"code\":200,\"msg\":\"操作成功\",\"data\":{\"id\":\"1864607914954567681\",\"leaveType\":\"1\",\"startDate\":\"2024-12-05 00:00:00\",\"endDate\":\"2025-01-15 00:00:00\",\"leaveDays\":42,\"remark\":\"玩\",\"status\":\"draft\"}}',0,'','2024-12-05 17:49:05',267),(1864607920436514818,'000000','任务管理',1,'com.cdzeroly.workflow.controller.ActTaskController.startWorkFlow()','POST',1,'admin','研发部门','/task/startWorkFlow','0:0:0:0:0:0:0:1','','{\"businessKey\":\"1864607914954567681\",\"tableName\":\"test_leave\",\"variables\":{\"entity\":{\"id\":\"1864607914954567681\",\"leaveType\":\"1\",\"startDate\":\"2024-12-05 00:00:00\",\"endDate\":\"2025-01-15 00:00:00\",\"leaveDays\":42,\"remark\":\"玩\",\"status\":\"draft\"},\"leaveDays\":42,\"userList\":[\"1\",\"3\"],\"userList2\":[\"1\",\"3\"],\"_FLOWABLE_SKIP_EXPRESSION_ENABLED\":true,\"initiator\":\"1\"}}','{\"code\":200,\"msg\":\"提交成功\",\"data\":{\"processInstanceId\":\"1864607918167404545\",\"taskId\":\"1864607918322593794\"}}',0,'','2024-12-05 17:49:06',706),(1864608034588692482,'000000','任务管理',1,'com.cdzeroly.workflow.controller.ActTaskController.completeTask()','POST',1,'admin','研发部门','/task/completeTask','0:0:0:0:0:0:0:1','','{\"taskId\":\"1864607918322593794\",\"fileId\":null,\"wfCopyList\":[{\"userId\":4,\"userName\":\"仅本人 密码666666\"}],\"messageType\":[\"1\"],\"message\":null,\"variables\":{}}','{\"code\":200,\"msg\":\"操作成功\",\"data\":null}',0,'','2024-12-05 17:49:33',1058),(1864608564190875649,'000000','流程定义管理',2,'com.cdzeroly.workflow.controller.ActProcessDefinitionController.convertToModel()','PUT',1,'admin','研发部门','/processDefinition/convertToModel/leave6:1:1864607723623002114','0:0:0:0:0:0:0:1','','\"leave6:1:1864607723623002114\"','{\"code\":200,\"msg\":\"操作成功\",\"data\":null}',0,'','2024-12-05 17:51:39',177),(1864608603659276290,'000000','流程定义管理',2,'com.cdzeroly.workflow.controller.ActProcessDefinitionController.convertToModel()','PUT',1,'admin','研发部门','/processDefinition/convertToModel/leave6:1:1864607723623002114','0:0:0:0:0:0:0:1','','\"leave6:1:1864607723623002114\"','{\"code\":200,\"msg\":\"操作成功\",\"data\":null}',0,'','2024-12-05 17:51:49',71),(1864609413872979970,'000000','流程定义管理',2,'com.cdzeroly.workflow.controller.ActProcessDefinitionController.convertToModel()','PUT',1,'admin','研发部门','/processDefinition/convertToModel/leave2:1:1864607722519900161','0:0:0:0:0:0:0:1','','\"leave2:1:1864607722519900161\"','{\"code\":200,\"msg\":\"操作成功\",\"data\":null}',0,'','2024-12-05 17:55:02',242);
/*!40000 ALTER TABLE `sys_oper_log` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_oss`
--

DROP TABLE IF EXISTS `sys_oss`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sys_oss` (
  `oss_id` bigint NOT NULL COMMENT '对象存储主键',
  `tenant_id` varchar(20) COLLATE utf8mb4_general_ci DEFAULT '000000' COMMENT '租户编号',
  `file_name` varchar(255) COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '文件名',
  `original_name` varchar(255) COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '原名',
  `file_suffix` varchar(10) COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '文件后缀名',
  `url` varchar(500) COLLATE utf8mb4_general_ci NOT NULL COMMENT 'URL地址',
  `create_dept` bigint DEFAULT NULL COMMENT '创建部门',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `create_by` bigint DEFAULT NULL COMMENT '上传人',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `update_by` bigint DEFAULT NULL COMMENT '更新人',
  `service` varchar(20) COLLATE utf8mb4_general_ci NOT NULL DEFAULT 'minio' COMMENT '服务商',
  PRIMARY KEY (`oss_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='OSS对象存储表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_oss`
--

LOCK TABLES `sys_oss` WRITE;
/*!40000 ALTER TABLE `sys_oss` DISABLE KEYS */;
/*!40000 ALTER TABLE `sys_oss` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_oss_config`
--

DROP TABLE IF EXISTS `sys_oss_config`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sys_oss_config` (
  `oss_config_id` bigint NOT NULL COMMENT '主键',
  `tenant_id` varchar(20) COLLATE utf8mb4_general_ci DEFAULT '000000' COMMENT '租户编号',
  `config_key` varchar(20) COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '配置key',
  `access_key` varchar(255) COLLATE utf8mb4_general_ci DEFAULT '' COMMENT 'accessKey',
  `secret_key` varchar(255) COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '秘钥',
  `bucket_name` varchar(255) COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '桶名称',
  `prefix` varchar(255) COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '前缀',
  `endpoint` varchar(255) COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '访问站点',
  `domain` varchar(255) COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '自定义域名',
  `is_https` char(1) COLLATE utf8mb4_general_ci DEFAULT 'N' COMMENT '是否https（Y=是,N=否）',
  `region` varchar(255) COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '域',
  `access_policy` char(1) COLLATE utf8mb4_general_ci NOT NULL DEFAULT '1' COMMENT '桶权限类型(0=private 1=public 2=custom)',
  `status` char(1) COLLATE utf8mb4_general_ci DEFAULT '1' COMMENT '是否默认（0=是,1=否）',
  `ext1` varchar(255) COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '扩展字段',
  `create_dept` bigint DEFAULT NULL COMMENT '创建部门',
  `create_by` bigint DEFAULT NULL COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` bigint DEFAULT NULL COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`oss_config_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='对象存储配置表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_oss_config`
--

LOCK TABLES `sys_oss_config` WRITE;
/*!40000 ALTER TABLE `sys_oss_config` DISABLE KEYS */;
INSERT INTO `sys_oss_config` VALUES (1,'000000','minio','ruoyi','ruoyi123','ruoyi','','127.0.0.1:9000','','N','','1','0','',103,1,'2024-12-04 17:33:33',1,'2024-12-04 17:33:33',NULL),(2,'000000','qiniu','XXXXXXXXXXXXXXX','XXXXXXXXXXXXXXX','ruoyi','','s3-cn-north-1.qiniucs.com','','N','','1','1','',103,1,'2024-12-04 17:33:33',1,'2024-12-04 17:33:33',NULL),(3,'000000','aliyun','XXXXXXXXXXXXXXX','XXXXXXXXXXXXXXX','ruoyi','','oss-cn-beijing.aliyuncs.com','','N','','1','1','',103,1,'2024-12-04 17:33:33',1,'2024-12-04 17:33:33',NULL),(4,'000000','qcloud','XXXXXXXXXXXXXXX','XXXXXXXXXXXXXXX','ruoyi-1250000000','','cos.ap-beijing.myqcloud.com','','N','ap-beijing','1','1','',103,1,'2024-12-04 17:33:33',1,'2024-12-04 17:33:33',NULL),(5,'000000','image','ruoyi','ruoyi123','ruoyi','image','127.0.0.1:9000','','N','','1','1','',103,1,'2024-12-04 17:33:33',1,'2024-12-04 17:33:33',NULL);
/*!40000 ALTER TABLE `sys_oss_config` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_post`
--

DROP TABLE IF EXISTS `sys_post`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sys_post` (
  `post_id` bigint NOT NULL COMMENT '岗位ID',
  `tenant_id` varchar(20) COLLATE utf8mb4_general_ci DEFAULT '000000' COMMENT '租户编号',
  `dept_id` bigint NOT NULL COMMENT '部门id',
  `post_code` varchar(64) COLLATE utf8mb4_general_ci NOT NULL COMMENT '岗位编码',
  `post_category` varchar(100) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '岗位类别编码',
  `post_name` varchar(50) COLLATE utf8mb4_general_ci NOT NULL COMMENT '岗位名称',
  `post_sort` int NOT NULL COMMENT '显示顺序',
  `status` char(1) COLLATE utf8mb4_general_ci NOT NULL COMMENT '状态（0正常 1停用）',
  `create_dept` bigint DEFAULT NULL COMMENT '创建部门',
  `create_by` bigint DEFAULT NULL COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` bigint DEFAULT NULL COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`post_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='岗位信息表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_post`
--

LOCK TABLES `sys_post` WRITE;
/*!40000 ALTER TABLE `sys_post` DISABLE KEYS */;
INSERT INTO `sys_post` VALUES (1,'000000',103,'ceo',NULL,'董事长',1,'0',103,1,'2024-12-04 17:33:24',NULL,NULL,''),(2,'000000',100,'se',NULL,'项目经理',2,'0',103,1,'2024-12-04 17:33:24',NULL,NULL,''),(3,'000000',100,'hr',NULL,'人力资源',3,'0',103,1,'2024-12-04 17:33:24',NULL,NULL,''),(4,'000000',100,'user',NULL,'普通员工',4,'0',103,1,'2024-12-04 17:33:24',NULL,NULL,'');
/*!40000 ALTER TABLE `sys_post` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_role`
--

DROP TABLE IF EXISTS `sys_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sys_role` (
  `role_id` bigint NOT NULL COMMENT '角色ID',
  `tenant_id` varchar(20) COLLATE utf8mb4_general_ci DEFAULT '000000' COMMENT '租户编号',
  `role_name` varchar(30) COLLATE utf8mb4_general_ci NOT NULL COMMENT '角色名称',
  `role_key` varchar(100) COLLATE utf8mb4_general_ci NOT NULL COMMENT '角色权限字符串',
  `role_sort` int NOT NULL COMMENT '显示顺序',
  `data_scope` char(1) COLLATE utf8mb4_general_ci DEFAULT '1' COMMENT '数据范围（1：全部数据权限 2：自定数据权限 3：本部门数据权限 4：本部门及以下数据权限）',
  `menu_check_strictly` tinyint(1) DEFAULT '1' COMMENT '菜单树选择项是否关联显示',
  `dept_check_strictly` tinyint(1) DEFAULT '1' COMMENT '部门树选择项是否关联显示',
  `status` char(1) COLLATE utf8mb4_general_ci NOT NULL COMMENT '角色状态（0正常 1停用）',
  `del_flag` char(1) COLLATE utf8mb4_general_ci DEFAULT '0' COMMENT '删除标志（0代表存在 2代表删除）',
  `create_dept` bigint DEFAULT NULL COMMENT '创建部门',
  `create_by` bigint DEFAULT NULL COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` bigint DEFAULT NULL COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='角色信息表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_role`
--

LOCK TABLES `sys_role` WRITE;
/*!40000 ALTER TABLE `sys_role` DISABLE KEYS */;
INSERT INTO `sys_role` VALUES (1,'000000','超级管理员','superadmin',1,'1',1,1,'0','0',103,1,'2024-12-04 17:33:24',NULL,NULL,'超级管理员'),(3,'000000','本部门及以下','test1',3,'4',1,1,'0','0',103,1,'2024-12-04 17:33:24',NULL,NULL,''),(4,'000000','仅本人','test2',4,'5',1,1,'0','0',103,1,'2024-12-04 17:33:24',NULL,NULL,'');
/*!40000 ALTER TABLE `sys_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_role_dept`
--

DROP TABLE IF EXISTS `sys_role_dept`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sys_role_dept` (
  `role_id` bigint NOT NULL COMMENT '角色ID',
  `dept_id` bigint NOT NULL COMMENT '部门ID',
  PRIMARY KEY (`role_id`,`dept_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='角色和部门关联表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_role_dept`
--

LOCK TABLES `sys_role_dept` WRITE;
/*!40000 ALTER TABLE `sys_role_dept` DISABLE KEYS */;
/*!40000 ALTER TABLE `sys_role_dept` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_role_menu`
--

DROP TABLE IF EXISTS `sys_role_menu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sys_role_menu` (
  `role_id` bigint NOT NULL COMMENT '角色ID',
  `menu_id` bigint NOT NULL COMMENT '菜单ID',
  PRIMARY KEY (`role_id`,`menu_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='角色和菜单关联表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_role_menu`
--

LOCK TABLES `sys_role_menu` WRITE;
/*!40000 ALTER TABLE `sys_role_menu` DISABLE KEYS */;
INSERT INTO `sys_role_menu` VALUES (3,1),(3,5),(3,100),(3,101),(3,102),(3,103),(3,104),(3,105),(3,106),(3,107),(3,108),(3,500),(3,501),(3,1001),(3,1002),(3,1003),(3,1004),(3,1005),(3,1006),(3,1007),(3,1008),(3,1009),(3,1010),(3,1011),(3,1012),(3,1013),(3,1014),(3,1015),(3,1016),(3,1017),(3,1018),(3,1019),(3,1020),(3,1021),(3,1022),(3,1023),(3,1024),(3,1025),(3,1026),(3,1027),(3,1028),(3,1029),(3,1030),(3,1031),(3,1032),(3,1033),(3,1034),(3,1035),(3,1036),(3,1037),(3,1038),(3,1039),(3,1040),(3,1041),(3,1042),(3,1043),(3,1044),(3,1045),(3,1500),(3,1501),(3,1502),(3,1503),(3,1504),(3,1505),(3,1506),(3,1507),(3,1508),(3,1509),(3,1510),(3,1511),(4,5),(4,1500),(4,1501),(4,1502),(4,1503),(4,1504),(4,1505),(4,1506),(4,1507),(4,1508),(4,1509),(4,1510),(4,1511);
/*!40000 ALTER TABLE `sys_role_menu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_social`
--

DROP TABLE IF EXISTS `sys_social`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sys_social` (
  `id` bigint NOT NULL COMMENT '主键',
  `user_id` bigint NOT NULL COMMENT '用户ID',
  `tenant_id` varchar(20) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '租户id',
  `auth_id` varchar(255) COLLATE utf8mb4_general_ci NOT NULL COMMENT '平台+平台唯一id',
  `source` varchar(255) COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户来源',
  `open_id` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '平台编号唯一id',
  `user_name` varchar(30) COLLATE utf8mb4_general_ci NOT NULL COMMENT '登录账号',
  `nick_name` varchar(30) COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '用户昵称',
  `email` varchar(255) COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '用户邮箱',
  `avatar` varchar(500) COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '头像地址',
  `access_token` varchar(255) COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户的授权令牌',
  `expire_in` int DEFAULT NULL COMMENT '用户的授权令牌的有效期，部分平台可能没有',
  `refresh_token` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '刷新令牌，部分平台可能没有',
  `access_code` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '平台的授权信息，部分平台可能没有',
  `union_id` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '用户的 unionid',
  `scope` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '授予的权限，部分平台可能没有',
  `token_type` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '个别平台的授权信息，部分平台可能没有',
  `id_token` varchar(2000) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT 'id token，部分平台可能没有',
  `mac_algorithm` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '小米平台用户的附带属性，部分平台可能没有',
  `mac_key` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '小米平台用户的附带属性，部分平台可能没有',
  `code` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '用户的授权code，部分平台可能没有',
  `oauth_token` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT 'Twitter平台用户的附带属性，部分平台可能没有',
  `oauth_token_secret` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT 'Twitter平台用户的附带属性，部分平台可能没有',
  `create_dept` bigint DEFAULT NULL COMMENT '创建部门',
  `create_by` bigint DEFAULT NULL COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` bigint DEFAULT NULL COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `del_flag` char(1) COLLATE utf8mb4_general_ci DEFAULT '0' COMMENT '删除标志（0代表存在 2代表删除）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='社会化关系表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_social`
--

LOCK TABLES `sys_social` WRITE;
/*!40000 ALTER TABLE `sys_social` DISABLE KEYS */;
/*!40000 ALTER TABLE `sys_social` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_tenant`
--

DROP TABLE IF EXISTS `sys_tenant`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sys_tenant` (
  `id` bigint NOT NULL COMMENT 'id',
  `tenant_id` varchar(20) COLLATE utf8mb4_general_ci NOT NULL COMMENT '租户编号',
  `contact_user_name` varchar(20) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '联系人',
  `contact_phone` varchar(20) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '联系电话',
  `company_name` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '企业名称',
  `license_number` varchar(30) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '统一社会信用代码',
  `address` varchar(200) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '地址',
  `intro` varchar(200) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '企业简介',
  `domain` varchar(200) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '域名',
  `remark` varchar(200) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '备注',
  `package_id` bigint DEFAULT NULL COMMENT '租户套餐编号',
  `expire_time` datetime DEFAULT NULL COMMENT '过期时间',
  `account_count` int DEFAULT '-1' COMMENT '用户数量（-1不限制）',
  `status` char(1) COLLATE utf8mb4_general_ci DEFAULT '0' COMMENT '租户状态（0正常 1停用）',
  `del_flag` char(1) COLLATE utf8mb4_general_ci DEFAULT '0' COMMENT '删除标志（0代表存在 2代表删除）',
  `create_dept` bigint DEFAULT NULL COMMENT '创建部门',
  `create_by` bigint DEFAULT NULL COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` bigint DEFAULT NULL COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='租户表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_tenant`
--

LOCK TABLES `sys_tenant` WRITE;
/*!40000 ALTER TABLE `sys_tenant` DISABLE KEYS */;
INSERT INTO `sys_tenant` VALUES (1,'000000','管理组','15888888888','XXX有限公司',NULL,NULL,'多租户通用后台管理管理系统',NULL,NULL,NULL,NULL,-1,'0','0',103,1,'2024-12-04 17:33:21',NULL,NULL);
/*!40000 ALTER TABLE `sys_tenant` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_tenant_package`
--

DROP TABLE IF EXISTS `sys_tenant_package`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sys_tenant_package` (
  `package_id` bigint NOT NULL COMMENT '租户套餐id',
  `package_name` varchar(20) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '套餐名称',
  `menu_ids` varchar(3000) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '关联菜单id',
  `remark` varchar(200) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '备注',
  `menu_check_strictly` tinyint(1) DEFAULT '1' COMMENT '菜单树选择项是否关联显示',
  `status` char(1) COLLATE utf8mb4_general_ci DEFAULT '0' COMMENT '状态（0正常 1停用）',
  `del_flag` char(1) COLLATE utf8mb4_general_ci DEFAULT '0' COMMENT '删除标志（0代表存在 2代表删除）',
  `create_dept` bigint DEFAULT NULL COMMENT '创建部门',
  `create_by` bigint DEFAULT NULL COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` bigint DEFAULT NULL COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`package_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='租户套餐表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_tenant_package`
--

LOCK TABLES `sys_tenant_package` WRITE;
/*!40000 ALTER TABLE `sys_tenant_package` DISABLE KEYS */;
/*!40000 ALTER TABLE `sys_tenant_package` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_user`
--

DROP TABLE IF EXISTS `sys_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sys_user` (
  `user_id` bigint NOT NULL COMMENT '用户ID',
  `tenant_id` varchar(20) COLLATE utf8mb4_general_ci DEFAULT '000000' COMMENT '租户编号',
  `dept_id` bigint DEFAULT NULL COMMENT '部门ID',
  `user_name` varchar(30) COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户账号',
  `nick_name` varchar(30) COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户昵称',
  `user_type` varchar(10) COLLATE utf8mb4_general_ci DEFAULT 'sys_user' COMMENT '用户类型（sys_user系统用户）',
  `email` varchar(50) COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '用户邮箱',
  `phonenumber` varchar(11) COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '手机号码',
  `sex` char(1) COLLATE utf8mb4_general_ci DEFAULT '0' COMMENT '用户性别（0男 1女 2未知）',
  `avatar` bigint DEFAULT NULL COMMENT '头像地址',
  `password` varchar(100) COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '密码',
  `status` char(1) COLLATE utf8mb4_general_ci DEFAULT '0' COMMENT '帐号状态（0正常 1停用）',
  `del_flag` char(1) COLLATE utf8mb4_general_ci DEFAULT '0' COMMENT '删除标志（0代表存在 2代表删除）',
  `login_ip` varchar(128) COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '最后登录IP',
  `login_date` datetime DEFAULT NULL COMMENT '最后登录时间',
  `create_dept` bigint DEFAULT NULL COMMENT '创建部门',
  `create_by` bigint DEFAULT NULL COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` bigint DEFAULT NULL COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='用户信息表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_user`
--

LOCK TABLES `sys_user` WRITE;
/*!40000 ALTER TABLE `sys_user` DISABLE KEYS */;
INSERT INTO `sys_user` VALUES (1,'000000',103,'admin','疯狂的狮子Li','sys_user','crazyLionLi@163.com','15888888888','1',NULL,'$2a$10$7JB720yubVSZvUI0rEqK/.VqGOZTH.ulu33dHOiBE8ByOhJIrdAu2','0','0','0:0:0:0:0:0:0:1','2024-12-06 10:37:58',103,1,'2024-12-04 17:33:23',1,'2024-12-06 10:37:58','管理员'),(3,'000000',108,'test','本部门及以下 密码666666','sys_user','','','0',NULL,'$2a$10$b8yUzN0C71sbz.PhNOCgJe.Tu1yWC3RNrTyjSQ8p1W0.aaUXUJ.Ne','0','0','127.0.0.1','2024-12-04 17:33:23',103,1,'2024-12-04 17:33:23',NULL,NULL,''),(4,'000000',102,'test1','仅本人 密码666666','sys_user','','','0',NULL,'$2a$10$b8yUzN0C71sbz.PhNOCgJe.Tu1yWC3RNrTyjSQ8p1W0.aaUXUJ.Ne','0','0','127.0.0.1','2024-12-04 17:33:23',103,1,'2024-12-04 17:33:23',NULL,NULL,'');
/*!40000 ALTER TABLE `sys_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_user_post`
--

DROP TABLE IF EXISTS `sys_user_post`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sys_user_post` (
  `user_id` bigint NOT NULL COMMENT '用户ID',
  `post_id` bigint NOT NULL COMMENT '岗位ID',
  PRIMARY KEY (`user_id`,`post_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='用户与岗位关联表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_user_post`
--

LOCK TABLES `sys_user_post` WRITE;
/*!40000 ALTER TABLE `sys_user_post` DISABLE KEYS */;
INSERT INTO `sys_user_post` VALUES (1,1);
/*!40000 ALTER TABLE `sys_user_post` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_user_role`
--

DROP TABLE IF EXISTS `sys_user_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sys_user_role` (
  `user_id` bigint NOT NULL COMMENT '用户ID',
  `role_id` bigint NOT NULL COMMENT '角色ID',
  PRIMARY KEY (`user_id`,`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='用户和角色关联表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_user_role`
--

LOCK TABLES `sys_user_role` WRITE;
/*!40000 ALTER TABLE `sys_user_role` DISABLE KEYS */;
INSERT INTO `sys_user_role` VALUES (1,1),(3,3),(4,4);
/*!40000 ALTER TABLE `sys_user_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `test_demo`
--

DROP TABLE IF EXISTS `test_demo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `test_demo` (
  `id` bigint NOT NULL COMMENT '主键',
  `tenant_id` varchar(20) COLLATE utf8mb4_general_ci DEFAULT '000000' COMMENT '租户编号',
  `dept_id` bigint DEFAULT NULL COMMENT '部门id',
  `user_id` bigint DEFAULT NULL COMMENT '用户id',
  `order_num` int DEFAULT '0' COMMENT '排序号',
  `test_key` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT 'key键',
  `value` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '值',
  `version` int DEFAULT '0' COMMENT '版本',
  `create_dept` bigint DEFAULT NULL COMMENT '创建部门',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `create_by` bigint DEFAULT NULL COMMENT '创建人',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `update_by` bigint DEFAULT NULL COMMENT '更新人',
  `del_flag` int DEFAULT '0' COMMENT '删除标志',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='测试单表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `test_demo`
--

LOCK TABLES `test_demo` WRITE;
/*!40000 ALTER TABLE `test_demo` DISABLE KEYS */;
INSERT INTO `test_demo` VALUES (1,'000000',102,4,1,'测试数据权限','测试',0,103,'2024-12-04 17:33:34',1,NULL,NULL,0),(2,'000000',102,3,2,'子节点1','111',0,103,'2024-12-04 17:33:34',1,NULL,NULL,0),(3,'000000',102,3,3,'子节点2','222',0,103,'2024-12-04 17:33:34',1,NULL,NULL,0),(4,'000000',108,4,4,'测试数据','demo',0,103,'2024-12-04 17:33:34',1,NULL,NULL,0),(5,'000000',108,3,13,'子节点11','1111',0,103,'2024-12-04 17:33:34',1,NULL,NULL,0),(6,'000000',108,3,12,'子节点22','2222',0,103,'2024-12-04 17:33:34',1,NULL,NULL,0),(7,'000000',108,3,11,'子节点33','3333',0,103,'2024-12-04 17:33:34',1,NULL,NULL,0),(8,'000000',108,3,10,'子节点44','4444',0,103,'2024-12-04 17:33:34',1,NULL,NULL,0),(9,'000000',108,3,9,'子节点55','5555',0,103,'2024-12-04 17:33:34',1,NULL,NULL,0),(10,'000000',108,3,8,'子节点66','6666',0,103,'2024-12-04 17:33:34',1,NULL,NULL,0),(11,'000000',108,3,7,'子节点77','7777',0,103,'2024-12-04 17:33:34',1,NULL,NULL,0),(12,'000000',108,3,6,'子节点88','8888',0,103,'2024-12-04 17:33:34',1,NULL,NULL,0),(13,'000000',108,3,5,'子节点99','9999',0,103,'2024-12-04 17:33:34',1,NULL,NULL,0);
/*!40000 ALTER TABLE `test_demo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `test_tree`
--

DROP TABLE IF EXISTS `test_tree`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `test_tree` (
  `id` bigint NOT NULL COMMENT '主键',
  `tenant_id` varchar(20) COLLATE utf8mb4_general_ci DEFAULT '000000' COMMENT '租户编号',
  `parent_id` bigint DEFAULT '0' COMMENT '父id',
  `dept_id` bigint DEFAULT NULL COMMENT '部门id',
  `user_id` bigint DEFAULT NULL COMMENT '用户id',
  `tree_name` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '值',
  `version` int DEFAULT '0' COMMENT '版本',
  `create_dept` bigint DEFAULT NULL COMMENT '创建部门',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `create_by` bigint DEFAULT NULL COMMENT '创建人',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `update_by` bigint DEFAULT NULL COMMENT '更新人',
  `del_flag` int DEFAULT '0' COMMENT '删除标志',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='测试树表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `test_tree`
--

LOCK TABLES `test_tree` WRITE;
/*!40000 ALTER TABLE `test_tree` DISABLE KEYS */;
INSERT INTO `test_tree` VALUES (1,'000000',0,102,4,'测试数据权限',0,103,'2024-12-04 17:33:34',1,NULL,NULL,0),(2,'000000',1,102,3,'子节点1',0,103,'2024-12-04 17:33:34',1,NULL,NULL,0),(3,'000000',2,102,3,'子节点2',0,103,'2024-12-04 17:33:34',1,NULL,NULL,0),(4,'000000',0,108,4,'测试树1',0,103,'2024-12-04 17:33:34',1,NULL,NULL,0),(5,'000000',4,108,3,'子节点11',0,103,'2024-12-04 17:33:34',1,NULL,NULL,0),(6,'000000',4,108,3,'子节点22',0,103,'2024-12-04 17:33:34',1,NULL,NULL,0),(7,'000000',4,108,3,'子节点33',0,103,'2024-12-04 17:33:34',1,NULL,NULL,0),(8,'000000',5,108,3,'子节点44',0,103,'2024-12-04 17:33:34',1,NULL,NULL,0),(9,'000000',6,108,3,'子节点55',0,103,'2024-12-04 17:33:34',1,NULL,NULL,0),(10,'000000',7,108,3,'子节点66',0,103,'2024-12-04 17:33:34',1,NULL,NULL,0),(11,'000000',7,108,3,'子节点77',0,103,'2024-12-04 17:33:34',1,NULL,NULL,0),(12,'000000',10,108,3,'子节点88',0,103,'2024-12-04 17:33:34',1,NULL,NULL,0),(13,'000000',10,108,3,'子节点99',0,103,'2024-12-04 17:33:34',1,NULL,NULL,0);
/*!40000 ALTER TABLE `test_tree` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `undo_log`
--

DROP TABLE IF EXISTS `undo_log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `undo_log` (
  `branch_id` bigint NOT NULL COMMENT 'branch transaction id',
  `xid` varchar(100) COLLATE utf8mb4_general_ci NOT NULL COMMENT 'global transaction id',
  `context` varchar(128) COLLATE utf8mb4_general_ci NOT NULL COMMENT 'undo_log context,such as serialization',
  `rollback_info` longblob NOT NULL COMMENT 'rollback info',
  `log_status` int NOT NULL COMMENT '0:normal status,1:defense status',
  `log_created` datetime(6) NOT NULL COMMENT 'create datetime',
  `log_modified` datetime(6) NOT NULL COMMENT 'modify datetime',
  UNIQUE KEY `ux_undo_log` (`xid`,`branch_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='AT transaction mode undo table';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `undo_log`
--

LOCK TABLES `undo_log` WRITE;
/*!40000 ALTER TABLE `undo_log` DISABLE KEYS */;
/*!40000 ALTER TABLE `undo_log` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Current Database: `ry-config`
--

CREATE DATABASE /*!32312 IF NOT EXISTS*/ `ry-config` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;

USE `ry-config`;

--
-- Table structure for table `config_info`
--

DROP TABLE IF EXISTS `config_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `config_info` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
  `data_id` varchar(255) COLLATE utf8mb3_bin NOT NULL COMMENT 'data_id',
  `group_id` varchar(128) COLLATE utf8mb3_bin DEFAULT NULL COMMENT 'group_id',
  `content` longtext COLLATE utf8mb3_bin NOT NULL COMMENT 'content',
  `md5` varchar(32) COLLATE utf8mb3_bin DEFAULT NULL COMMENT 'md5',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `src_user` text COLLATE utf8mb3_bin COMMENT 'source user',
  `src_ip` varchar(50) COLLATE utf8mb3_bin DEFAULT NULL COMMENT 'source ip',
  `app_name` varchar(128) COLLATE utf8mb3_bin DEFAULT NULL COMMENT 'app_name',
  `tenant_id` varchar(128) COLLATE utf8mb3_bin DEFAULT '' COMMENT '租户字段',
  `c_desc` varchar(256) COLLATE utf8mb3_bin DEFAULT NULL COMMENT 'configuration description',
  `c_use` varchar(64) COLLATE utf8mb3_bin DEFAULT NULL COMMENT 'configuration usage',
  `effect` varchar(64) COLLATE utf8mb3_bin DEFAULT NULL COMMENT '配置生效的描述',
  `type` varchar(64) COLLATE utf8mb3_bin DEFAULT NULL COMMENT '配置的类型',
  `c_schema` text COLLATE utf8mb3_bin COMMENT '配置的模式',
  `encrypted_data_key` text COLLATE utf8mb3_bin NOT NULL COMMENT '密钥',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_configinfo_datagrouptenant` (`data_id`,`group_id`,`tenant_id`)
) ENGINE=InnoDB AUTO_INCREMENT=160 DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_bin COMMENT='config_info';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `config_info`
--

LOCK TABLES `config_info` WRITE;
/*!40000 ALTER TABLE `config_info` DISABLE KEYS */;
INSERT INTO `config_info` VALUES (1,'application-common.yml','DEFAULT_GROUP','server:\n  # undertow 配置\n  undertow:\n    # HTTP post内容的最大大小。当值为-1时，默认值为大小是无限的\n    max-http-post-size: -1\n    # 以下的配置会影响buffer,这些buffer会用于服务器连接的IO操作,有点类似netty的池化内存管理\n    # 每块buffer的空间大小,越小的空间被利用越充分\n    buffer-size: 512\n    # 是否分配的直接内存\n    direct-buffers: true\n    threads:\n      # 设置IO线程数, 它主要执行非阻塞的任务,它们会负责多个连接, 默认设置每个CPU核心一个线程\n      io: 8\n      # 阻塞任务线程池, 当执行类似servlet请求阻塞操作, undertow会从这个线程池中取得线程,它的值设置取决于系统的负载\n      worker: 256\n\ndubbo:\n  application:\n    # 关闭qos端口避免单机多生产者端口冲突 如需使用自行开启\n    qos-enable: false\n  protocol:\n    # 如需使用 Triple 3.0 新协议 可查看官方文档\n    # 使用 dubbo 协议通信\n    name: dubbo\n    # dubbo 协议端口(-1表示自增端口,从20880开始)\n    port: -1\n    # 指定dubbo协议注册ip\n    # host: 192.168.0.100\n  # 消费者相关配置\n  consumer:\n    # 超时时间\n    timeout: 3000\n  # 自定义配置\n  custom:\n    # 全局请求log\n    request-log: true\n    # info 基础信息 param 参数信息 full 全部\n    log-level: info\n\nspring:\n  threads:\n    # 开启虚拟线程 仅jdk21可用\n    virtual:\n      enabled: false\n  # 资源信息\n  messages:\n    # 国际化资源文件路径\n    basename: i18n/messages\n  servlet:\n    multipart:\n      # 整个请求大小限制\n      max-request-size: 20MB\n      # 上传单个文件大小限制\n      max-file-size: 10MB\n  mvc:\n    # 设置静态资源路径 防止所有请求都去查静态资源\n    static-path-pattern: /static/**\n    format:\n      date-time: yyyy-MM-dd HH:mm:ss\n  #jackson配置\n  jackson:\n    # 日期格式化\n    date-format: yyyy-MM-dd HH:mm:ss\n    serialization:\n      # 格式化输出\n      INDENT_OUTPUT: false\n      # 忽略无法转换的对象\n      fail_on_empty_beans: false\n    deserialization:\n      # 允许对象忽略json中不存在的属性\n      fail_on_unknown_properties: false\n  cloud:\n    nacos:\n      discovery:\n        metadata:\n          # admin 监控账号密码\n          username: ruoyi\n          userpassword: 123456\n    # sentinel 配置\n    sentinel:\n      # sentinel 开关\n      enabled: true\n      transport:\n        # dashboard控制台服务名 用于服务发现\n        # 如无此配置将默认使用下方 dashboard 配置直接注册\n        server-name: ruoyi-sentinel-dashboard\n        # 客户端指定注册的ip 用于多网卡ip不稳点使用\n        # client-ip:\n        # 控制台地址 从1.3.0开始使用 server-name 注册\n        # dashboard: localhost:8718\n\n    bus:\n      id: ${spring.application.name}\n      base-packages: com.cdzeroly.**.event\n  # 消息总线 也可以使用 kafka 参考 spring-cloud-bus 用法\n  rabbitmq:\n    host: localhost\n    port: 5672\n    username: ruoyi\n    password: ruoyi123\n\n  # redis通用配置 子服务可以自行配置进行覆盖\n  data:\n    redis:\n      host: localhost\n      port: 6379\n      # redis 密码必须配置\n      password: ruoyi123\n      database: 0\n      # 需要使用数字\n      timeout: 10000\n      ssl.enabled: false\n\n# redisson 配置\nredisson:\n  # redis key前缀\n  keyPrefix:\n  # 线程池数量\n  threads: 4\n  # Netty线程池数量\n  nettyThreads: 8\n  # 单节点配置\n  singleServerConfig:\n    # 客户端名称\n    clientName: ${spring.application.name}\n    # 最小空闲连接数\n    connectionMinimumIdleSize: 8\n    # 连接池大小\n    connectionPoolSize: 32\n    # 连接空闲超时，单位：毫秒\n    idleConnectionTimeout: 10000\n    # 命令等待超时，单位：毫秒\n    timeout: 3000\n    # 发布和订阅连接池大小\n    subscriptionConnectionPoolSize: 50\n\n# 分布式锁 lock4j 全局配置\nlock4j:\n  # 获取分布式锁超时时间，默认为 3000 毫秒\n  acquire-timeout: 3000\n  # 分布式锁的超时时间，默认为 30 秒\n  expire: 30000\n\n# 暴露监控端点\nmanagement:\n  endpoints:\n    web:\n      exposure:\n        include: \'*\'\n  endpoint:\n    health:\n      show-details: ALWAYS\n    logfile:\n      external-file: ./logs/${spring.application.name}/console.log\n\n# 日志配置\nlogging:\n  level:\n    org.springframework: warn\n    org.apache.dubbo: warn\n    com.alibaba.nacos: warn\n    com.alibaba.cloud.sentinel: warn\n    org.mybatis.spring.mapper: error\n    org.apache.dubbo.config: error\n    # 临时处理 spring 调整日志级别导致启动警告问题 不影响使用等待 alibaba 适配\n    org.springframework.context.support.PostProcessorRegistrationDelegate: error\n  config: classpath:logback-plus.xml\n\n# Sa-Token配置\nsa-token:\n  # token名称 (同时也是cookie名称)\n  token-name: Authorization\n  # 开启内网服务调用鉴权(不允许越过gateway访问内网服务 保障服务安全)\n  check-same-token: true\n  # 是否允许同一账号并发登录 (为true时允许一起登录, 为false时新登录挤掉旧登录)\n  is-concurrent: true\n  # 在多人登录同一账号时，是否共用一个token (为true时所有登录共用一个token, 为false时每次登录新建一个token)\n  is-share: false\n  # jwt秘钥\n  jwt-secret-key: abcdefghijklmnopqrstuvwxyz\n\n# MyBatisPlus配置\n# https://baomidou.com/config/\nmybatis-plus:\n  # 多包名使用 例如 com.cdzeroly.**.mapper,org.xxx.**.mapper\n  mapperPackage: com.cdzeroly.**.mapper\n  # 对应的 XML 文件位置\n  mapperLocations: classpath*:mapper/**/*Mapper.xml\n  # 实体扫描，多个package用逗号或者分号分隔\n  typeAliasesPackage: com.cdzeroly.**.domain\n  global-config:\n    dbConfig:\n      # 主键类型\n      # AUTO 自增 NONE 空 INPUT 用户输入 ASSIGN_ID 雪花 ASSIGN_UUID 唯一 UUID\n      # 如需改为自增 需要将数据库表全部设置为自增\n      idType: ASSIGN_ID\n\n# 数据加密\nmybatis-encryptor:\n  # 是否开启加密\n  enable: false\n  # 默认加密算法\n  algorithm: BASE64\n  # 编码方式 BASE64/HEX。默认BASE64\n  encode: BASE64\n  # 安全秘钥 对称算法的秘钥 如：AES，SM4\n  password:\n  # 公私钥 非对称算法的公私钥 如：SM2，RSA\n  publicKey:\n  privateKey:\n\n# api接口加密\napi-decrypt:\n  # 是否开启全局接口加密\n  enabled: true\n  # AES 加密头标识\n  headerFlag: encrypt-key\n  # 响应加密公钥 非对称算法的公私钥 如：SM2，RSA 使用者请自行更换\n  # 对应前端解密私钥 MIIBVAIBADANBgkqhkiG9w0BAQEFAASCAT4wggE6AgEAAkEAmc3CuPiGL/LcIIm7zryCEIbl1SPzBkr75E2VMtxegyZ1lYRD+7TZGAPkvIsBcaMs6Nsy0L78n2qh+lIZMpLH8wIDAQABAkEAk82Mhz0tlv6IVCyIcw/s3f0E+WLmtPFyR9/WtV3Y5aaejUkU60JpX4m5xNR2VaqOLTZAYjW8Wy0aXr3zYIhhQQIhAMfqR9oFdYw1J9SsNc+CrhugAvKTi0+BF6VoL6psWhvbAiEAxPPNTmrkmrXwdm/pQQu3UOQmc2vCZ5tiKpW10CgJi8kCIFGkL6utxw93Ncj4exE/gPLvKcT+1Emnoox+O9kRXss5AiAMtYLJDaLEzPrAWcZeeSgSIzbL+ecokmFKSDDcRske6QIgSMkHedwND1olF8vlKsJUGK3BcdtM8w4Xq7BpSBwsloE=\n  publicKey: MFwwDQYJKoZIhvcNAQEBBQADSwAwSAJBAJnNwrj4hi/y3CCJu868ghCG5dUj8wZK++RNlTLcXoMmdZWEQ/u02RgD5LyLAXGjLOjbMtC+/J9qofpSGTKSx/MCAwEAAQ==\n  # 请求解密私钥 非对称算法的公私钥 如：SM2，RSA 使用者请自行更换\n  # 对应前端加密公钥 MFwwDQYJKoZIhvcNAQEBBQADSwAwSAJBAKoR8mX0rGKLqzcWmOzbfj64K8ZIgOdHnzkXSOVOZbFu/TJhZ7rFAN+eaGkl3C4buccQd/EjEsj9ir7ijT7h96MCAwEAAQ==\n  privateKey: MIIBVAIBADANBgkqhkiG9w0BAQEFAASCAT4wggE6AgEAAkEAqhHyZfSsYourNxaY7Nt+PrgrxkiA50efORdI5U5lsW79MmFnusUA355oaSXcLhu5xxB38SMSyP2KvuKNPuH3owIDAQABAkAfoiLyL+Z4lf4Myxk6xUDgLaWGximj20CUf+5BKKnlrK+Ed8gAkM0HqoTt2UZwA5E2MzS4EI2gjfQhz5X28uqxAiEA3wNFxfrCZlSZHb0gn2zDpWowcSxQAgiCstxGUoOqlW8CIQDDOerGKH5OmCJ4Z21v+F25WaHYPxCFMvwxpcw99EcvDQIgIdhDTIqD2jfYjPTY8Jj3EDGPbH2HHuffvflECt3Ek60CIQCFRlCkHpi7hthhYhovyloRYsM+IS9h/0BzlEAuO0ktMQIgSPT3aFAgJYwKpqRYKlLDVcflZFCKY7u3UP8iWi1Qw0Y=\n\n# 防止XSS攻击\nxss:\n  enabled: true\n  excludeUrls:\n    - /system/notice\n    - /workflow/model/save\n    - /workflow/model/editModelXml\n\n# 接口文档配置\nspringdoc:\n  api-docs:\n    # 是否开启接口文档\n    enabled: true\n#  swagger-ui:\n#    # 持久化认证数据\n#    persistAuthorization: true\n  info:\n    # 标题\n    title: \'标题：RuoYi-Cloud-Plus微服务权限管理系统_接口文档\'\n    # 描述\n    description: \'描述：微服务权限管理系统, 具体包括XXX,XXX模块...\'\n    # 版本\n    version: \'版本号：系统版本...\'\n    # 作者信息\n    contact:\n      name: Lion Li\n      email: crazylionli@163.com\n      url: https://gitee.com/dromara/RuoYi-Cloud-Plus\n  components:\n    # 鉴权方式配置\n    security-schemes:\n      apiKey:\n        type: APIKEY\n        in: HEADER\n        name: ${sa-token.token-name}\n\n# seata配置\nseata:\n  # 是否启用\n  enabled: true\n  # Seata 应用编号，默认为应用名\n  application-id: ${spring.application.name}\n  # Seata 事务组编号，用于 TC 集群名\n  tx-service-group: ${spring.application.name}-group\n\n# 多租户配置\ntenant:\n  # 是否开启\n  enable: true\n  # 排除表\n  excludes:\n    - sys_menu\n    - sys_tenant\n    - sys_tenant_package\n    - sys_role_dept\n    - sys_role_menu\n    - sys_user_post\n    - sys_user_role\n    - sys_client\n    - sys_oss_config\n','9ce0cd9136de4b8f9c92fb37051f4bd4','2022-01-09 15:18:55','2024-12-05 04:15:06','nacos','192.168.1.19','','dev','通用配置基础配置','','','yaml','',''),(2,'datasource.yml','DEFAULT_GROUP','datasource:\n  system-master:\n    # jdbc 所有参数配置参考 https://lionli.blog.csdn.net/article/details/122018562\n    # rewriteBatchedStatements=true 批处理优化 大幅提升批量插入更新删除性能\n    url: jdbc:mysql://localhost:3306/ry-cloud?useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&useSSL=true&serverTimezone=GMT%2B8&rewriteBatchedStatements=true&allowPublicKeyRetrieval=true\n    username: root\n    password: root\n  gen:\n    url: jdbc:mysql://localhost:3306/ry-cloud?useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&useSSL=true&serverTimezone=GMT%2B8&rewriteBatchedStatements=true&allowPublicKeyRetrieval=true\n    username: root\n    password: root\n  job:\n    url: jdbc:mysql://localhost:3306/ry-job?useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&useSSL=true&serverTimezone=GMT%2B8&rewriteBatchedStatements=true&allowPublicKeyRetrieval=true\n    username: root\n    password: root\n  workflow:\n    url: jdbc:mysql://localhost:3306/ry-workflow?useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&useSSL=true&serverTimezone=GMT%2B8&rewriteBatchedStatements=true&allowPublicKeyRetrieval=true&nullCatalogMeansCurrent=true\n    username: root\n    password: root\n#  system-oracle:\n#    url: jdbc:oracle:thin:@//localhost:1521/XE\n#    username: ROOT\n#    password: password\n#  system-postgres:\n#    url: jdbc:postgresql://localhost:5432/postgres?useUnicode=true&characterEncoding=utf8&useSSL=true&autoReconnect=true&reWriteBatchedInserts=true\n#    username: root\n#    password: password\n\nspring:\n  datasource:\n    type: com.zaxxer.hikari.HikariDataSource\n    # 动态数据源文档 https://www.kancloud.cn/tracy5546/dynamic-datasource/content\n    dynamic:\n      # 性能分析插件(有性能损耗 不建议生产环境使用)\n      p6spy: true\n      # 开启seata代理，开启后默认每个数据源都代理，如果某个不需要代理可单独关闭\n      seata: ${seata.enabled}\n      # 严格模式 匹配不到数据源则报错\n      strict: true\n      hikari:\n        # 最大连接池数量\n        maxPoolSize: 20\n        # 最小空闲线程数量\n        minIdle: 10\n        # 配置获取连接等待超时的时间\n        connectionTimeout: 30000\n        # 校验超时时间\n        validationTimeout: 5000\n        # 空闲连接存活最大时间，默认10分钟\n        idleTimeout: 600000\n        # 此属性控制池中连接的最长生命周期，值0表示无限生命周期，默认30分钟\n        maxLifetime: 1800000\n        # 多久检查一次连接的活性\n        keepaliveTime: 30000\n','ceb67fe7f6e6a92955d0ac164c3f8805','2022-01-09 15:19:07','2024-12-05 04:18:57','nacos','192.168.1.19','','dev','数据源配置','','','yaml','',''),(3,'ruoyi-gateway.yml','DEFAULT_GROUP','# 安全配置\nsecurity:\n  # 不校验白名单\n  ignore:\n    whites:\n      - /auth/code\n      - /auth/logout\n      - /auth/login\n      - /auth/binding/*\n      - /auth/social/callback\n      - /auth/register\n      - /auth/tenant/list\n      - /resource/sms/code\n      - /resource/sse/close\n      - /*/v3/api-docs\n      - /*/error\n      - /csrf\n\nspring:\n  cloud:\n    # 网关配置\n    gateway:\n      # 打印请求日志(自定义)\n      requestLog: true\n      discovery:\n        locator:\n          lowerCaseServiceId: true\n          enabled: true\n      routes:\n        # 认证中心\n        - id: auth\n          uri: lb://auth\n          predicates:\n            - Path=/auth/**\n          filters:\n            - StripPrefix=1\n        # 代码生成\n        - id: gen\n          uri: lb://gen\n          predicates:\n            - Path=/tool/**\n          filters:\n            - StripPrefix=1\n        # 系统模块\n        - id: system\n          uri: lb://system\n          predicates:\n            - Path=/system/**,/monitor/**\n          filters:\n            - StripPrefix=1\n        # 资源服务\n        - id: resource\n          uri: lb://resource\n          predicates:\n            - Path=/resource/**\n          filters:\n            - StripPrefix=1\n        # workflow服务\n        - id: workflow\n          uri: lb://workflow\n          predicates:\n            - Path=/workflow/**\n          filters:\n            - StripPrefix=1\n        # 演示服务\n        - id: demo\n          uri: lb://demo\n          predicates:\n            - Path=/demo/**\n          filters:\n            - StripPrefix=1\n        # MQ演示服务\n        - id: test-mq\n          uri: lb://test-mq\n          predicates:\n            - Path=/test-mq/**\n          filters:\n            - StripPrefix=1\n\n    # sentinel 配置\n    sentinel:\n      filter:\n        enabled: false\n      # nacos配置持久化\n      datasource:\n        ds1:\n          nacos:\n            server-addr: ${spring.cloud.nacos.server-addr}\n            dataId: sentinel-${spring.application.name}.json\n            groupId: ${spring.cloud.nacos.config.group}\n            username: ${spring.cloud.nacos.username}\n            password: ${spring.cloud.nacos.password}\n            namespace: ${spring.profiles.active}\n            data-type: json\n            rule-type: gw-flow\n','f8943720504ecace1ff47cc4e9e31efc','2022-01-09 15:19:43','2024-12-05 06:40:25','nacos','192.168.1.19','','dev','网关模块','','','yaml','',''),(4,'ruoyi-auth.yml','DEFAULT_GROUP','# 安全配置\nsecurity:\n  # 验证码\n  captcha:\n    # 是否开启验证码\n    enabled: true\n    # 验证码类型 math 数组计算 char 字符验证\n    type: MATH\n    # line 线段干扰 circle 圆圈干扰 shear 扭曲干扰\n    category: CIRCLE\n    # 数字验证码位数\n    numberLength: 1\n    # 字符验证码长度\n    charLength: 4\n\n# 用户配置\nuser:\n  password:\n    # 密码最大错误次数\n    maxRetryCount: 5\n    # 密码锁定时间（默认10分钟）\n    lockTime: 10\n\n# 三方授权\njustauth:\n  # 前端外网访问地址\n  address: http://localhost:80\n  type:\n    maxkey:\n      # maxkey 服务器地址\n      # 注意 如下均配置均不需要修改 maxkey 已经内置好了数据\n      server-url: http://sso.maxkey.top\n      client-id: 876892492581044224\n      client-secret: x1Y5MTMwNzIwMjMxNTM4NDc3Mzche8\n      redirect-uri: ${justauth.address}/social-callback?source=maxkey\n    topiam:\n      # topiam 服务器地址\n      server-url: http://127.0.0.1:1989/api/v1/authorize/y0q************spq***********8ol\n      client-id: 449c4*********937************759\n      client-secret: ac7***********1e0************28d\n      redirect-uri: ${justauth.address}/social-callback?source=topiam\n      scopes: [ openid, email, phone, profile ]\n    qq:\n      client-id: 10**********6\n      client-secret: 1f7d08**********5b7**********29e\n      redirect-uri: ${justauth.address}/social-callback?source=qq\n      union-id: false\n    weibo:\n      client-id: 10**********6\n      client-secret: 1f7d08**********5b7**********29e\n      redirect-uri: ${justauth.address}/social-callback?source=weibo\n    gitee:\n      client-id: 91436b7940090d09c72c7daf85b959cfd5f215d67eea73acbf61b6b590751a98\n      client-secret: 02c6fcfd70342980cd8dd2f2c06c1a350645d76c754d7a264c4e125f9ba915ac\n      redirect-uri: ${justauth.address}/social-callback?source=gitee\n    dingtalk:\n      client-id: 10**********6\n      client-secret: 1f7d08**********5b7**********29e\n      redirect-uri: ${justauth.address}/social-callback?source=dingtalk\n    baidu:\n      client-id: 10**********6\n      client-secret: 1f7d08**********5b7**********29e\n      redirect-uri: ${justauth.address}/social-callback?source=baidu\n    csdn:\n      client-id: 10**********6\n      client-secret: 1f7d08**********5b7**********29e\n      redirect-uri: ${justauth.address}/social-callback?source=csdn\n    coding:\n      client-id: 10**********6\n      client-secret: 1f7d08**********5b7**********29e\n      redirect-uri: ${justauth.address}/social-callback?source=coding\n      coding-group-name: xx\n    oschina:\n      client-id: 10**********6\n      client-secret: 1f7d08**********5b7**********29e\n      redirect-uri: ${justauth.address}/social-callback?source=oschina\n    alipay_wallet:\n      client-id: 10**********6\n      client-secret: 1f7d08**********5b7**********29e\n      redirect-uri: ${justauth.address}/social-callback?source=alipay_wallet\n      alipay-public-key: MIIB**************DAQAB\n    wechat_open:\n      client-id: 10**********6\n      client-secret: 1f7d08**********5b7**********29e\n      redirect-uri: ${justauth.address}/social-callback?source=wechat_open\n    wechat_mp:\n      client-id: 10**********6\n      client-secret: 1f7d08**********5b7**********29e\n      redirect-uri: ${justauth.address}/social-callback?source=wechat_mp\n    wechat_enterprise:\n      client-id: 10**********6\n      client-secret: 1f7d08**********5b7**********29e\n      redirect-uri: ${justauth.address}/social-callback?source=wechat_enterprise\n      agent-id: 1000002\n    gitlab:\n      client-id: 10**********6\n      client-secret: 1f7d08**********5b7**********29e\n      redirect-uri: ${justauth.address}/social-callback?source=gitlab\n','362c28d8864626b864d682b5f1c4a73a','2022-01-09 15:19:43','2024-12-04 10:02:30','nacos','192.168.1.19','','dev','认证中心','','','yaml','',''),(5,'ruoyi-monitor.yml','DEFAULT_GROUP','# 监控中心配置\nspring:\n  security:\n    user:\n      name: ruoyi\n      password: 123456\n  boot:\n    admin:\n      ui:\n        title: RuoYi-Cloud-Plus服务监控中心\n      discovery:\n        # seata 不具有健康检查的能力 防止报错排除掉\n        ignored-services: ruoyi-seata-server\n','ba7fa6a6bfde3dcb3b566fd8864834af','2022-01-09 15:20:18','2024-12-04 10:03:12','nacos','192.168.1.19','','dev','监控中心','','','yaml','',''),(6,'ruoyi-system.yml','DEFAULT_GROUP','spring:\n  datasource:\n    dynamic:\n      # 设置默认的数据源或者数据源组,默认值即为 master\n      primary: master\n      datasource:\n        # 主库数据源\n        master:\n          type: ${spring.datasource.type}\n          driver-class-name: com.mysql.cj.jdbc.Driver\n          url: ${datasource.system-master.url}\n          username: ${datasource.system-master.username}\n          password: ${datasource.system-master.password}\n#        oracle:\n#          type: ${spring.datasource.type}\n#          driverClassName: oracle.jdbc.OracleDriver\n#          url: ${datasource.system-oracle.url}\n#          username: ${datasource.system-oracle.username}\n#          password: ${datasource.system-oracle.password}\n#        postgres:\n#          type: ${spring.datasource.type}\n#          driverClassName: org.postgresql.Driver\n#          url: ${datasource.system-postgres.url}\n#          username: ${datasource.system-postgres.username}\n#          password: ${datasource.system-postgres.password}\n','f59259409ff41d30e5276bf4afbcaa3d','2022-01-09 15:20:18','2024-12-04 10:04:13','nacos','192.168.1.19','','dev','系统模块','','','yaml','',''),(7,'ruoyi-gen.yml','DEFAULT_GROUP','spring:\n  datasource:\n    dynamic:\n      # 设置默认的数据源或者数据源组,默认值即为 master\n      primary: master\n      seata: false\n      datasource:\n        # 主库数据源\n        master:\n          type: ${spring.datasource.type}\n          driver-class-name: com.mysql.cj.jdbc.Driver\n          url: ${datasource.system-master.url}\n          username: ${datasource.system-master.username}\n          password: ${datasource.system-master.password}\n#        oracle:\n#          type: ${spring.datasource.type}\n#          driverClassName: oracle.jdbc.OracleDriver\n#          url: ${datasource.system-oracle.url}\n#          username: ${datasource.system-oracle.username}\n#          password: ${datasource.system-oracle.password}\n#        postgres:\n#          type: ${spring.datasource.type}\n#          driverClassName: org.postgresql.Driver\n#          url: ${datasource.system-postgres.url}\n#          username: ${datasource.system-postgres.username}\n#          password: ${datasource.system-postgres.password}\n\n# 代码生成\ngen:\n  # 作者\n  author: MGARY\n  # 默认生成包路径 system 需改成自己的模块名称 如 system monitor tool\n  packageName: com.cdzeroly.system\n  # 自动去除表前缀，默认是false\n  autoRemovePre: false\n  # 表前缀（生成类名不会包含表前缀，多个用逗号分隔）\n  tablePrefix: sys_\n','f02376e215be7e735427461290ca100e','2022-01-09 15:20:18','2024-12-05 06:40:55','nacos','192.168.1.19','','dev','代码生成','','','yaml','',''),(8,'ruoyi-job.yml','DEFAULT_GROUP','spring:\n  datasource:\n    dynamic:\n      # 设置默认的数据源或者数据源组,默认值即为 master\n      primary: master\n      seata: false\n      datasource:\n        # 主库数据源\n        master:\n          type: ${spring.datasource.type}\n          driver-class-name: com.mysql.cj.jdbc.Driver\n          url: ${datasource.job.url}\n          username: ${datasource.job.username}\n          password: ${datasource.job.password}\n\nsnail-job:\n  enabled: true\n  # 需要在 SnailJob 后台组管理创建对应名称的组,然后创建任务的时候选择对应的组,才能正确分派任务\n  group: \"base_platform_group\"\n  #  SnailJob 接入验证令牌\n  token: \"SJ_cKqBTPzCsWA3VyuCfFoccmuIEGXjr5KT\"\n  server:\n    # 从 nacos 获取服务\n    server-name: snailjob-server\n    # 服务名优先 ip垫底\n    host: 127.0.0.1\n    port: 17888\n  # 详见 script/sql/ry_job.sql `sj_namespace` 表\n  namespace: ${spring.profiles.active}\n  # 随主应用端口飘逸\n  port: 2${server.port}\n  # 客户端ip指定\n  host:\n','58b664a7d062e5983172cdc562e37f00','2022-01-09 15:20:18','2024-12-05 06:41:14','nacos','192.168.1.19','','dev','定时任务','','','yaml','',''),(9,'ruoyi-resource.yml','DEFAULT_GROUP','spring:\n  datasource:\n    dynamic:\n      # 设置默认的数据源或者数据源组,默认值即为 master\n      primary: master\n      datasource:\n        # 主库数据源\n        master:\n          type: ${spring.datasource.type}\n          driver-class-name: com.mysql.cj.jdbc.Driver\n          url: ${datasource.system-master.url}\n          username: ${datasource.system-master.username}\n          password: ${datasource.system-master.password}\n#        oracle:\n#          type: ${spring.datasource.type}\n#          driverClassName: oracle.jdbc.OracleDriver\n#          url: ${datasource.system-oracle.url}\n#          username: ${datasource.system-oracle.username}\n#          password: ${datasource.system-oracle.password}\n#        postgres:\n#          type: ${spring.datasource.type}\n#          driverClassName: org.postgresql.Driver\n#          url: ${datasource.system-postgres.url}\n#          username: ${datasource.system-postgres.username}\n#          password: ${datasource.system-postgres.password}\n\n# 默认/推荐使用sse推送\nsse:\n  enabled: true\n  path: /sse\n\nwebsocket:\n  # 如果关闭 需要和前端开关一起关闭\n  enabled: false\n  # 路径\n  path: /websocket\n  # 设置访问源地址\n  allowedOrigins: \'*\'\n\nmail:\n  enabled: false\n  host: smtp.163.com\n  port: 465\n  # 是否需要用户名密码验证\n  auth: true\n  # 发送方，遵循RFC-822标准\n  from: xxx@163.com\n  # 用户名（注意：如果使用foxmail邮箱，此处user为qq号）\n  user: xxx@163.com\n  # 密码（注意，某些邮箱需要为SMTP服务单独设置密码，详情查看相关帮助）\n  pass: xxxxxxxxxx\n  # 使用 STARTTLS安全连接，STARTTLS是对纯文本通信协议的扩展。\n  starttlsEnable: true\n  # 使用SSL安全连接\n  sslEnable: true\n  # SMTP超时时长，单位毫秒，缺省值不超时\n  timeout: 0\n  # Socket连接超时值，单位毫秒，缺省值不超时\n  connectionTimeout: 0\n\n# sms 短信 支持 阿里云 腾讯云 云片 等等各式各样的短信服务商\n# https://sms4j.com/doc3/ 差异配置文档地址 支持单厂商多配置，可以配置多个同时使用\nsms:\n  # 配置源类型用于标定配置来源(interface,yaml)\n  config-type: yaml\n  # 用于标定yml中的配置是否开启短信拦截，接口配置不受此限制\n  restricted: true\n  # 短信拦截限制单手机号每分钟最大发送，只对开启了拦截的配置有效\n  minute-max: 1\n  # 短信拦截限制单手机号每日最大发送量，只对开启了拦截的配置有效\n  account-max: 30\n  # 以下配置来自于 org.dromara.sms4j.provider.config.BaseConfig类中\n  blends:\n    # 唯一ID 用于发送短信寻找具体配置 随便定义别用中文即可\n    # 可以同时存在两个相同厂商 例如: ali1 ali2 两个不同的阿里短信账号 也可用于区分租户\n    config1:\n      # 框架定义的厂商名称标识，标定此配置是哪个厂商，详细请看厂商标识介绍部分\n      supplier: alibaba\n      # 有些称为accessKey有些称之为apiKey，也有称为sdkKey或者appId。\n      access-key-id: 您的accessKey\n      # 称为accessSecret有些称之为apiSecret\n      access-key-secret: 您的accessKeySecret\n      signature: 您的短信签名\n      sdk-app-id: 您的sdkAppId\n    config2:\n      # 厂商标识，标定此配置是哪个厂商，详细请看厂商标识介绍部分\n      supplier: tencent\n      access-key-id: 您的accessKey\n      access-key-secret: 您的accessKeySecret\n      signature: 您的短信签名\n      sdk-app-id: 您的sdkAppId\n','efb16000eb230328fa2128ea56558fd2','2022-01-09 15:20:35','2024-12-04 10:03:25','nacos','192.168.1.19','','dev','文件服务','','','yaml','',''),(10,'ruoyi-workflow.yml','DEFAULT_GROUP','spring:\n  datasource:\n    dynamic:\n      # 设置默认的数据源或者数据源组,默认值即为 master\n      primary: master\n      datasource:\n        # 主库数据源\n        master:\n          type: ${spring.datasource.type}\n          driver-class-name: com.mysql.cj.jdbc.Driver\n          url: ${datasource.workflow.url}\n          username: ${datasource.workflow.username}\n          password: ${datasource.workflow.password}\n#        oracle:\n#          type: ${spring.datasource.type}\n#          driverClassName: oracle.jdbc.OracleDriver\n#          url: ${datasource.system-oracle.url}\n#          username: ${datasource.system-oracle.username}\n#          password: ${datasource.system-oracle.password}\n#        postgres:\n#          type: ${spring.datasource.type}\n#          driverClassName: org.postgresql.Driver\n#          url: ${datasource.system-postgres.url}\n#          username: ${datasource.system-postgres.username}\n#          password: ${datasource.system-postgres.password}\n\n# flowable配置\nflowable:\n  # 开关 用于启动/停用工作流\n  enabled: true\n  process.enabled: ${flowable.enabled}\n  eventregistry.enabled: ${flowable.enabled}\n  # 关闭定时任务JOB\n  async-executor-activate: false\n  # 将databaseSchemaUpdate设置为true。当Flowable发现库与数据库表结构不一致时，会自动将数据库表结构升级至新版本。\n  database-schema-update: true\n  activity-font-name: 宋体\n  label-font-name: 宋体\n  annotation-font-name: 宋体\n  # 关闭各个模块生成表，目前只使用工作流基础表\n  idm:\n    enabled: false\n  cmmn:\n    enabled: false\n  dmn:\n    enabled: false\n  app:\n    enabled: false\n','95b566892b838da618fbfdaf1ecffd0a','2022-01-09 15:20:35','2024-12-04 10:04:32','nacos','192.168.1.19','','dev','工作流服务','','','yaml','',''),(11,'sentinel-ruoyi-gateway.json','DEFAULT_GROUP','[\n  {\n    \"resource\": \"ruoyi-auth\",\n    \"count\": 500,\n    \"grade\": 1,\n    \"limitApp\": \"default\",\n    \"strategy\": 0,\n    \"controlBehavior\": 0\n  },\n  {\n    \"resource\": \"ruoyi-system\",\n    \"count\": 1000,\n    \"grade\": 1,\n    \"limitApp\": \"default\",\n    \"strategy\": 0,\n    \"controlBehavior\": 0\n  },\n  {\n    \"resource\": \"ruoyi-resource\",\n    \"count\": 500,\n    \"grade\": 1,\n    \"limitApp\": \"default\",\n    \"strategy\": 0,\n    \"controlBehavior\": 0\n  }\n]\n','2efad3702e99adef46c2ee818b3925e5','2022-01-09 15:21:02','2024-12-04 10:05:05','nacos','192.168.1.19','','dev','限流策略','','','json','',''),(12,'seata-server.properties','DEFAULT_GROUP','service.vgroupMapping.ruoyi-auth-group=default\nservice.vgroupMapping.ruoyi-system-group=default\nservice.vgroupMapping.ruoyi-resource-group=default\nservice.vgroupMapping.ruoyi-workflow-group=default\n\nservice.enableDegrade=false\nservice.disableGlobalTransaction=false\n\n#Transaction storage configuration, only for the server. The file, DB, and redis configuration values are optional.\nstore.mode=db\nstore.lock.mode=db\nstore.session.mode=db\n#Used for password encryption\n#store.publicKey=\n\n#These configurations are required if the `store mode` is `db`. If `store.mode,store.lock.mode,store.session.mode` are not equal to `db`, you can remove the configuration block.\nstore.db.datasource=hikari\nstore.db.dbType=mysql\nstore.db.driverClassName=com.mysql.cj.jdbc.Driver\nstore.db.url=jdbc:mysql://127.0.0.1:3306/ry-seata?useUnicode=true&rewriteBatchedStatements=true&allowPublicKeyRetrieval=true\nstore.db.user=root\nstore.db.password=ruoyi123\nstore.db.minConn=5\nstore.db.maxConn=30\nstore.db.globalTable=global_table\nstore.db.branchTable=branch_table\nstore.db.distributedLockTable=distributed_lock\nstore.db.queryLimit=100\nstore.db.lockTable=lock_table\nstore.db.maxWait=5000\n\n# redis 模式 store.mode=redis 开启 (控制台查询功能有限,不影响实际执行功能)\n# store.redis.host=127.0.0.1\n# store.redis.port=6379\n# 最大连接数\n# store.redis.maxConn=10\n# 最小连接数\n# store.redis.minConn=1\n# store.redis.database=0\n# store.redis.password=\n# store.redis.queryLimit=100\n\n#Transaction rule configuration, only for the server\nserver.recovery.committingRetryPeriod=1000\nserver.recovery.asynCommittingRetryPeriod=1000\nserver.recovery.rollbackingRetryPeriod=1000\nserver.recovery.timeoutRetryPeriod=1000\nserver.maxCommitRetryTimeout=-1\nserver.maxRollbackRetryTimeout=-1\nserver.rollbackRetryTimeoutUnlockEnable=false\nserver.distributedLockExpireTime=10000\nserver.xaerNotaRetryTimeout=60000\nserver.session.branchAsyncQueueSize=5000\nserver.session.enableBranchAsyncRemove=false\n\n#Transaction rule configuration, only for the client\nclient.rm.asyncCommitBufferLimit=10000\nclient.rm.lock.retryInterval=10\nclient.rm.lock.retryTimes=30\nclient.rm.lock.retryPolicyBranchRollbackOnConflict=true\nclient.rm.reportRetryCount=5\nclient.rm.tableMetaCheckEnable=true\nclient.rm.tableMetaCheckerInterval=60000\nclient.rm.sqlParserType=druid\nclient.rm.reportSuccessEnable=false\nclient.rm.sagaBranchRegisterEnable=false\nclient.rm.sagaJsonParser=fastjson\nclient.rm.tccActionInterceptorOrder=-2147482648\nclient.tm.commitRetryCount=5\nclient.tm.rollbackRetryCount=5\nclient.tm.defaultGlobalTransactionTimeout=60000\nclient.tm.degradeCheck=false\nclient.tm.degradeCheckAllowTimes=10\nclient.tm.degradeCheckPeriod=2000\nclient.tm.interceptorOrder=-2147482648\nclient.undo.dataValidation=true\nclient.undo.logSerialization=jackson\nclient.undo.onlyCareUpdateColumns=true\nserver.undo.logSaveDays=7\nserver.undo.logDeletePeriod=86400000\nclient.undo.logTable=undo_log\nclient.undo.compress.enable=true\nclient.undo.compress.type=zip\nclient.undo.compress.threshold=64k\n\n#For TCC transaction mode\ntcc.fence.logTableName=tcc_fence_log\ntcc.fence.cleanPeriod=1h\n\n#Log rule configuration, for client and server\nlog.exceptionRate=100\n\n#Metrics configuration, only for the server\nmetrics.enabled=false\nmetrics.registryType=compact\nmetrics.exporterList=prometheus\nmetrics.exporterPrometheusPort=9898\n\n#For details about configuration items, see https://seata.io/zh-cn/docs/user/configurations.html\n#Transport configuration, for client and server\ntransport.type=TCP\ntransport.server=NIO\ntransport.heartbeat=true\ntransport.enableTmClientBatchSendRequest=false\ntransport.enableRmClientBatchSendRequest=true\ntransport.enableTcServerBatchSendResponse=false\ntransport.rpcRmRequestTimeout=30000\ntransport.rpcTmRequestTimeout=30000\ntransport.rpcTcRequestTimeout=30000\ntransport.threadFactory.bossThreadPrefix=NettyBoss\ntransport.threadFactory.workerThreadPrefix=NettyServerNIOWorker\ntransport.threadFactory.serverExecutorThreadPrefix=NettyServerBizHandler\ntransport.threadFactory.shareBossWorker=false\ntransport.threadFactory.clientSelectorThreadPrefix=NettyClientSelector\ntransport.threadFactory.clientSelectorThreadSize=1\ntransport.threadFactory.clientWorkerThreadPrefix=NettyClientWorkerThread\ntransport.threadFactory.bossThreadSize=1\ntransport.threadFactory.workerThreadSize=default\ntransport.shutdown.wait=3\ntransport.serialization=seata\ntransport.compressor=none\n','12f47aed8a463f862da4944fee7f5ad2','2022-01-09 15:21:02','2024-12-04 10:08:58','nacos','192.168.1.19','','dev','seata配置文件','','','properties','',''),(13,'ruoyi-sentinel-dashboard.yml','DEFAULT_GROUP','spring:\n  mvc:\n    pathmatch:\n      # 修复 sentinel 控制台未适配 springboot 2.6 新路由方式\n      matching-strategy: ANT_PATH_MATCHER\n\nserver:\n  servlet:\n    encoding:\n      force: true\n      charset: UTF-8\n      enabled: true\n    session:\n      cookie:\n        name: sentinel_dashboard_cookie\n\nlogging:\n  level:\n    org.springframework.web: INFO\n\nauth:\n  enabled: true\n  filter:\n    exclude-urls: /,/auth/login,/auth/logout,/registry/machine,/version,/actuator,/actuator/**\n    exclude-url-suffixes: htm,html,js,css,map,ico,ttf,woff,png\n  username: sentinel\n  password: sentinel\n','59055747c62f08cd2c38a6016d4b9227','2022-01-09 15:21:02','2024-12-04 10:03:47','nacos','192.168.1.19','','dev','sentinel控制台配置文件','','','yaml','',''),(14,'ruoyi-snailjob-server.yml','DEFAULT_GROUP','spring:\n  datasource:\n    type: com.zaxxer.hikari.HikariDataSource\n    driver-class-name: com.mysql.cj.jdbc.Driver\n    url: ${datasource.job.url}\n    username: ${datasource.job.username}\n    password: ${datasource.job.password}\n    hikari:\n      connection-timeout: 30000\n      validation-timeout: 5000\n      minimum-idle: 10\n      maximum-pool-size: 20\n      idle-timeout: 600000\n      max-lifetime: 900000\n      keepaliveTime: 30000\n  cloud:\n    nacos:\n      discovery:\n        metadata:\n          # 解决 er 服务有 context-path 无法监控问题\n          management.context-path: ${server.servlet.context-path}/actuator\n          # 监控账号密码\n          username: ruoyi\n          userpassword: 123456\n\n# snail-job 服务端配置\nsnail-job:\n  # 拉取重试数据的每批次的大小\n  retry-pull-page-size: 1000\n  # 拉取重试数据的每批次的大小\n  job-pull-page-size: 1000\n  # 服务端 netty 端口\n  netty-port: 17888\n  # 重试和死信表的分区总数\n  total-partition: 2\n  # 一个客户端每秒最多接收的重试数量指令\n  limiter: 1000\n  # 号段模式下步长配置\n  step: 100\n  # 日志保存时间(单位: day)\n  log-storage: 90\n  # 回调配置\n  callback:\n    #回调最大执行次数\n    max-count: 288\n    #间隔时间\n    trigger-interval: 900\n  retry-max-pull-count: 10\n','ab06186a47974fd09806e48484f37a1a','2022-01-09 15:21:02','2024-12-04 10:04:01','nacos','192.168.1.19','','dev','SJ定时任务控制台','','','yaml','',''),(133,'application-common.yml','DEFAULT_GROUP','server:\n  # undertow 配置\n  undertow:\n    # HTTP post内容的最大大小。当值为-1时，默认值为大小是无限的\n    max-http-post-size: -1\n    # 以下的配置会影响buffer,这些buffer会用于服务器连接的IO操作,有点类似netty的池化内存管理\n    # 每块buffer的空间大小,越小的空间被利用越充分\n    buffer-size: 512\n    # 是否分配的直接内存\n    direct-buffers: true\n    threads:\n      # 设置IO线程数, 它主要执行非阻塞的任务,它们会负责多个连接, 默认设置每个CPU核心一个线程\n      io: 8\n      # 阻塞任务线程池, 当执行类似servlet请求阻塞操作, undertow会从这个线程池中取得线程,它的值设置取决于系统的负载\n      worker: 256\n\ndubbo:\n  application:\n    # 关闭qos端口避免单机多生产者端口冲突 如需使用自行开启\n    qos-enable: false\n  protocol:\n    # 如需使用 Triple 3.0 新协议 可查看官方文档\n    # 使用 dubbo 协议通信\n    name: dubbo\n    # dubbo 协议端口(-1表示自增端口,从20880开始)\n    port: -1\n    # 指定dubbo协议注册ip\n    # host: 192.168.0.100\n  # 消费者相关配置\n  consumer:\n    # 超时时间\n    timeout: 3000\n  # 自定义配置\n  custom:\n    # 全局请求log\n    request-log: true\n    # info 基础信息 param 参数信息 full 全部\n    log-level: info\n\nspring:\n  threads:\n    # 开启虚拟线程 仅jdk21可用\n    virtual:\n      enabled: false\n  # 资源信息\n  messages:\n    # 国际化资源文件路径\n    basename: i18n/messages\n  servlet:\n    multipart:\n      # 整个请求大小限制\n      max-request-size: 20MB\n      # 上传单个文件大小限制\n      max-file-size: 10MB\n  mvc:\n    # 设置静态资源路径 防止所有请求都去查静态资源\n    static-path-pattern: /static/**\n    format:\n      date-time: yyyy-MM-dd HH:mm:ss\n  #jackson配置\n  jackson:\n    # 日期格式化\n    date-format: yyyy-MM-dd HH:mm:ss\n    serialization:\n      # 格式化输出\n      INDENT_OUTPUT: false\n      # 忽略无法转换的对象\n      fail_on_empty_beans: false\n    deserialization:\n      # 允许对象忽略json中不存在的属性\n      fail_on_unknown_properties: false\n  cloud:\n    nacos:\n      discovery:\n        metadata:\n          # admin 监控账号密码\n          username: admin\n          userpassword: 123456\n    # sentinel 配置\n    sentinel:\n      # sentinel 开关\n      enabled: true\n      transport:\n        # dashboard控制台服务名 用于服务发现\n        # 如无此配置将默认使用下方 dashboard 配置直接注册\n        server-name: sentinel-dashboard\n        # 客户端指定注册的ip 用于多网卡ip不稳点使用\n        # client-ip:\n        # 控制台地址 从1.3.0开始使用 server-name 注册\n        # dashboard: localhost:8718\n\n    bus:\n      id: ${spring.application.name}\n      base-packages: com.cdzeroly.**.event\n  # 消息总线 也可以使用 kafka 参考 spring-cloud-bus 用法\n  rabbitmq:\n    host: localhost\n    port: 5672\n    username: admin\n    password: 123456\n\n  # redis通用配置 子服务可以自行配置进行覆盖\n  data:\n    redis:\n      host: localhost\n      port: 6379\n      # redis 密码必须配置\n      password: 123456\n      database: 0\n      # 需要使用数字\n      timeout: 10000\n      ssl.enabled: false\n\n# redisson 配置\nredisson:\n  # redis key前缀\n  keyPrefix:\n  # 线程池数量\n  threads: 4\n  # Netty线程池数量\n  nettyThreads: 8\n  # 单节点配置\n  singleServerConfig:\n    # 客户端名称\n    clientName: ${spring.application.name}\n    # 最小空闲连接数\n    connectionMinimumIdleSize: 8\n    # 连接池大小\n    connectionPoolSize: 32\n    # 连接空闲超时，单位：毫秒\n    idleConnectionTimeout: 10000\n    # 命令等待超时，单位：毫秒\n    timeout: 3000\n    # 发布和订阅连接池大小\n    subscriptionConnectionPoolSize: 50\n\n# 分布式锁 lock4j 全局配置\nlock4j:\n  # 获取分布式锁超时时间，默认为 3000 毫秒\n  acquire-timeout: 3000\n  # 分布式锁的超时时间，默认为 30 秒\n  expire: 30000\n\n# 暴露监控端点\nmanagement:\n  endpoints:\n    web:\n      exposure:\n        include: \'*\'\n  endpoint:\n    health:\n      show-details: ALWAYS\n    logfile:\n      external-file: ./logs/${spring.application.name}/console.log\n\n# 日志配置\nlogging:\n  level:\n    org.springframework: warn\n    org.apache.dubbo: warn\n    com.alibaba.nacos: warn\n    com.alibaba.cloud.sentinel: warn\n    org.mybatis.spring.mapper: error\n    org.apache.dubbo.config: error\n    # 临时处理 spring 调整日志级别导致启动警告问题 不影响使用等待 alibaba 适配\n    org.springframework.context.support.PostProcessorRegistrationDelegate: error\n  config: classpath:logback-plus.xml\n\n# Sa-Token配置\nsa-token:\n  # token名称 (同时也是cookie名称)\n  token-name: Authorization\n  # 开启内网服务调用鉴权(不允许越过gateway访问内网服务 保障服务安全)\n  check-same-token: true\n  # 是否允许同一账号并发登录 (为true时允许一起登录, 为false时新登录挤掉旧登录)\n  is-concurrent: true\n  # 在多人登录同一账号时，是否共用一个token (为true时所有登录共用一个token, 为false时每次登录新建一个token)\n  is-share: false\n  # jwt秘钥\n  jwt-secret-key: abcdefghijklmnopqrstuvwxyz\n\n# MyBatisPlus配置\n# https://baomidou.com/config/\nmybatis-plus:\n  # 多包名使用 例如 com.cdzeroly.**.mapper,org.xxx.**.mapper\n  mapperPackage: com.cdzeroly.**.mapper\n  # 对应的 XML 文件位置\n  mapperLocations: classpath*:mapper/**/*Mapper.xml\n  # 实体扫描，多个package用逗号或者分号分隔\n  typeAliasesPackage: com.cdzeroly.**.domain\n  global-config:\n    dbConfig:\n      # 主键类型\n      # AUTO 自增 NONE 空 INPUT 用户输入 ASSIGN_ID 雪花 ASSIGN_UUID 唯一 UUID\n      # 如需改为自增 需要将数据库表全部设置为自增\n      idType: ASSIGN_ID\n\n# 数据加密\nmybatis-encryptor:\n  # 是否开启加密\n  enable: false\n  # 默认加密算法\n  algorithm: BASE64\n  # 编码方式 BASE64/HEX。默认BASE64\n  encode: BASE64\n  # 安全秘钥 对称算法的秘钥 如：AES，SM4\n  password:\n  # 公私钥 非对称算法的公私钥 如：SM2，RSA\n  publicKey:\n  privateKey:\n\n# api接口加密\napi-decrypt:\n  # 是否开启全局接口加密\n  enabled: true\n  # AES 加密头标识\n  headerFlag: encrypt-key\n  # 响应加密公钥 非对称算法的公私钥 如：SM2，RSA 使用者请自行更换\n  # 对应前端解密私钥 MIIBVAIBADANBgkqhkiG9w0BAQEFAASCAT4wggE6AgEAAkEAmc3CuPiGL/LcIIm7zryCEIbl1SPzBkr75E2VMtxegyZ1lYRD+7TZGAPkvIsBcaMs6Nsy0L78n2qh+lIZMpLH8wIDAQABAkEAk82Mhz0tlv6IVCyIcw/s3f0E+WLmtPFyR9/WtV3Y5aaejUkU60JpX4m5xNR2VaqOLTZAYjW8Wy0aXr3zYIhhQQIhAMfqR9oFdYw1J9SsNc+CrhugAvKTi0+BF6VoL6psWhvbAiEAxPPNTmrkmrXwdm/pQQu3UOQmc2vCZ5tiKpW10CgJi8kCIFGkL6utxw93Ncj4exE/gPLvKcT+1Emnoox+O9kRXss5AiAMtYLJDaLEzPrAWcZeeSgSIzbL+ecokmFKSDDcRske6QIgSMkHedwND1olF8vlKsJUGK3BcdtM8w4Xq7BpSBwsloE=\n  publicKey: MFwwDQYJKoZIhvcNAQEBBQADSwAwSAJBAJnNwrj4hi/y3CCJu868ghCG5dUj8wZK++RNlTLcXoMmdZWEQ/u02RgD5LyLAXGjLOjbMtC+/J9qofpSGTKSx/MCAwEAAQ==\n  # 请求解密私钥 非对称算法的公私钥 如：SM2，RSA 使用者请自行更换\n  # 对应前端加密公钥 MFwwDQYJKoZIhvcNAQEBBQADSwAwSAJBAKoR8mX0rGKLqzcWmOzbfj64K8ZIgOdHnzkXSOVOZbFu/TJhZ7rFAN+eaGkl3C4buccQd/EjEsj9ir7ijT7h96MCAwEAAQ==\n  privateKey: MIIBVAIBADANBgkqhkiG9w0BAQEFAASCAT4wggE6AgEAAkEAqhHyZfSsYourNxaY7Nt+PrgrxkiA50efORdI5U5lsW79MmFnusUA355oaSXcLhu5xxB38SMSyP2KvuKNPuH3owIDAQABAkAfoiLyL+Z4lf4Myxk6xUDgLaWGximj20CUf+5BKKnlrK+Ed8gAkM0HqoTt2UZwA5E2MzS4EI2gjfQhz5X28uqxAiEA3wNFxfrCZlSZHb0gn2zDpWowcSxQAgiCstxGUoOqlW8CIQDDOerGKH5OmCJ4Z21v+F25WaHYPxCFMvwxpcw99EcvDQIgIdhDTIqD2jfYjPTY8Jj3EDGPbH2HHuffvflECt3Ek60CIQCFRlCkHpi7hthhYhovyloRYsM+IS9h/0BzlEAuO0ktMQIgSPT3aFAgJYwKpqRYKlLDVcflZFCKY7u3UP8iWi1Qw0Y=\n\n# 防止XSS攻击\nxss:\n  enabled: true\n  excludeUrls:\n    - /system/notice\n    - /workflow/model/save\n    - /workflow/model/editModelXml\n\n# 接口文档配置\nspringdoc:\n  api-docs:\n    # 是否开启接口文档\n    enabled: true\n#  swagger-ui:\n#    # 持久化认证数据\n#    persistAuthorization: true\n  info:\n    # 标题\n    title: \'微服务权限管理系统_接口文档\'\n    # 描述\n    description: \'微服务权限管理系统, 具体包括全部模块\'\n    # 版本\n    version: \'version 1.0.0\'\n    # 作者信息\n    contact:\n      name: cdzeroly\n      email: xxx@cdzeroly.com\n      url: https://gitee.com/chengdu-zero-degree-layer/base-platform_cloud\n  components:\n    # 鉴权方式配置\n    security-schemes:\n      apiKey:\n        type: APIKEY\n        in: HEADER\n        name: ${sa-token.token-name}\n\n# seata配置\nseata:\n  # 是否启用\n  enabled: true\n  # Seata 应用编号，默认为应用名\n  application-id: ${spring.application.name}\n  # Seata 事务组编号，用于 TC 集群名\n  tx-service-group: ${spring.application.name}-group\n\n# 多租户配置\ntenant:\n  # 是否开启\n  enable: true\n  # 排除表\n  excludes:\n    - sys_menu\n    - sys_tenant\n    - sys_tenant_package\n    - sys_role_dept\n    - sys_role_menu\n    - sys_user_post\n    - sys_user_role\n    - sys_client\n    - sys_oss_config\n','906ed32ce515152d3ea8a6827617f2fd','2024-12-05 07:16:50','2024-12-05 08:41:44','nacos','192.168.1.19','','prod','通用配置基础配置','','','yaml','',''),(134,'datasource.yml','DEFAULT_GROUP','datasource:\n  system-master:\n    # jdbc 所有参数配置参考 https://lionli.blog.csdn.net/article/details/122018562\n    # rewriteBatchedStatements=true 批处理优化 大幅提升批量插入更新删除性能\n    url: jdbc:mysql://localhost:3306/ry-cloud?useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&useSSL=true&serverTimezone=GMT%2B8&rewriteBatchedStatements=true&allowPublicKeyRetrieval=true\n    username: root\n    password: root\n  gen:\n    url: jdbc:mysql://localhost:3306/ry-cloud?useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&useSSL=true&serverTimezone=GMT%2B8&rewriteBatchedStatements=true&allowPublicKeyRetrieval=true\n    username: root\n    password: root\n  job:\n    url: jdbc:mysql://localhost:3306/ry-job?useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&useSSL=true&serverTimezone=GMT%2B8&rewriteBatchedStatements=true&allowPublicKeyRetrieval=true\n    username: root\n    password: root\n  workflow:\n    url: jdbc:mysql://localhost:3306/ry-workflow?useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&useSSL=true&serverTimezone=GMT%2B8&rewriteBatchedStatements=true&allowPublicKeyRetrieval=true&nullCatalogMeansCurrent=true\n    username: root\n    password: root\n#  system-oracle:\n#    url: jdbc:oracle:thin:@//localhost:1521/XE\n#    username: ROOT\n#    password: password\n#  system-postgres:\n#    url: jdbc:postgresql://localhost:5432/postgres?useUnicode=true&characterEncoding=utf8&useSSL=true&autoReconnect=true&reWriteBatchedInserts=true\n#    username: root\n#    password: password\n\nspring:\n  datasource:\n    type: com.zaxxer.hikari.HikariDataSource\n    # 动态数据源文档 https://www.kancloud.cn/tracy5546/dynamic-datasource/content\n    dynamic:\n      # 性能分析插件(有性能损耗 不建议生产环境使用)\n      p6spy: true\n      # 开启seata代理，开启后默认每个数据源都代理，如果某个不需要代理可单独关闭\n      seata: ${seata.enabled}\n      # 严格模式 匹配不到数据源则报错\n      strict: true\n      hikari:\n        # 最大连接池数量\n        maxPoolSize: 20\n        # 最小空闲线程数量\n        minIdle: 10\n        # 配置获取连接等待超时的时间\n        connectionTimeout: 30000\n        # 校验超时时间\n        validationTimeout: 5000\n        # 空闲连接存活最大时间，默认10分钟\n        idleTimeout: 600000\n        # 此属性控制池中连接的最长生命周期，值0表示无限生命周期，默认30分钟\n        maxLifetime: 1800000\n        # 多久检查一次连接的活性\n        keepaliveTime: 30000\n','ceb67fe7f6e6a92955d0ac164c3f8805','2024-12-05 07:16:50','2024-12-05 07:16:50',NULL,'192.168.1.19','','prod','数据源配置',NULL,NULL,'yaml',NULL,''),(135,'gateway.yml','DEFAULT_GROUP','# 安全配置\nsecurity:\n  # 不校验白名单\n  ignore:\n    whites:\n      - /auth/code\n      - /auth/logout\n      - /auth/login\n      - /auth/binding/*\n      - /auth/social/callback\n      - /auth/register\n      - /auth/tenant/list\n      - /resource/sms/code\n      - /resource/sse/close\n      - /*/v3/api-docs\n      - /*/error\n      - /csrf\n\nspring:\n  cloud:\n    # 网关配置\n    gateway:\n      # 打印请求日志(自定义)\n      requestLog: true\n      discovery:\n        locator:\n          lowerCaseServiceId: true\n          enabled: false\n      routes:\n        # 认证中心\n        - id: auth\n          uri: lb://auth\n          predicates:\n            - Path=/auth/**\n          filters:\n            - StripPrefix=1\n        # 代码生成\n        - id: gen\n          uri: lb://gen\n          predicates:\n            - Path=/tool/**\n          filters:\n            - StripPrefix=1\n        # 系统模块\n        - id: system\n          uri: lb://system\n          predicates:\n            - Path=/system/**,/monitor/**\n          filters:\n            - StripPrefix=1\n        # 资源服务\n        - id: resource\n          uri: lb://resource\n          predicates:\n            - Path=/resource/**\n          filters:\n            - StripPrefix=1\n        # workflow服务\n        - id: workflow\n          uri: lb://workflow\n          predicates:\n            - Path=/workflow/**\n          filters:\n            - StripPrefix=1\n        # 演示服务\n        - id: demo\n          uri: lb://demo\n          predicates:\n            - Path=/demo/**\n          filters:\n            - StripPrefix=1\n        # MQ演示服务\n        - id: test-mq\n          uri: lb://test-mq\n          predicates:\n            - Path=/test-mq/**\n          filters:\n            - StripPrefix=1\n\n    # sentinel 配置\n    sentinel:\n      filter:\n        enabled: false\n      # nacos配置持久化\n      datasource:\n        ds1:\n          nacos:\n            server-addr: ${spring.cloud.nacos.server-addr}\n            dataId: sentinel-${spring.application.name}.json\n            groupId: ${spring.cloud.nacos.config.group}\n            username: ${spring.cloud.nacos.username}\n            password: ${spring.cloud.nacos.password}\n            namespace: ${spring.profiles.active}\n            data-type: json\n            rule-type: gw-flow\n','1c55f5eb4c091d37a2a2264f75f108ae','2024-12-05 07:16:50','2024-12-06 02:57:39','nacos','192.168.1.19','','prod','网关模块','','','yaml','',''),(136,'auth.yml','DEFAULT_GROUP','# 安全配置\nsecurity:\n  # 验证码\n  captcha:\n    # 是否开启验证码\n    enabled: true\n    # 验证码类型 math 数组计算 char 字符验证\n    type: MATH\n    # line 线段干扰 circle 圆圈干扰 shear 扭曲干扰\n    category: CIRCLE\n    # 数字验证码位数\n    numberLength: 1\n    # 字符验证码长度\n    charLength: 4\n\n# 用户配置\nuser:\n  password:\n    # 密码最大错误次数\n    maxRetryCount: 5\n    # 密码锁定时间（默认10分钟）\n    lockTime: 10\n\n# 三方授权\njustauth:\n  # 前端外网访问地址\n  address: http://localhost:80\n  type:\n    maxkey:\n      # maxkey 服务器地址\n      # 注意 如下均配置均不需要修改 maxkey 已经内置好了数据\n      server-url: http://sso.maxkey.top\n      client-id: 876892492581044224\n      client-secret: x1Y5MTMwNzIwMjMxNTM4NDc3Mzche8\n      redirect-uri: ${justauth.address}/social-callback?source=maxkey\n    topiam:\n      # topiam 服务器地址\n      server-url: http://127.0.0.1:1989/api/v1/authorize/y0q************spq***********8ol\n      client-id: 449c4*********937************759\n      client-secret: ac7***********1e0************28d\n      redirect-uri: ${justauth.address}/social-callback?source=topiam\n      scopes: [ openid, email, phone, profile ]\n    qq:\n      client-id: 10**********6\n      client-secret: 1f7d08**********5b7**********29e\n      redirect-uri: ${justauth.address}/social-callback?source=qq\n      union-id: false\n    weibo:\n      client-id: 10**********6\n      client-secret: 1f7d08**********5b7**********29e\n      redirect-uri: ${justauth.address}/social-callback?source=weibo\n    gitee:\n      client-id: 91436b7940090d09c72c7daf85b959cfd5f215d67eea73acbf61b6b590751a98\n      client-secret: 02c6fcfd70342980cd8dd2f2c06c1a350645d76c754d7a264c4e125f9ba915ac\n      redirect-uri: ${justauth.address}/social-callback?source=gitee\n    dingtalk:\n      client-id: 10**********6\n      client-secret: 1f7d08**********5b7**********29e\n      redirect-uri: ${justauth.address}/social-callback?source=dingtalk\n    baidu:\n      client-id: 10**********6\n      client-secret: 1f7d08**********5b7**********29e\n      redirect-uri: ${justauth.address}/social-callback?source=baidu\n    csdn:\n      client-id: 10**********6\n      client-secret: 1f7d08**********5b7**********29e\n      redirect-uri: ${justauth.address}/social-callback?source=csdn\n    coding:\n      client-id: 10**********6\n      client-secret: 1f7d08**********5b7**********29e\n      redirect-uri: ${justauth.address}/social-callback?source=coding\n      coding-group-name: xx\n    oschina:\n      client-id: 10**********6\n      client-secret: 1f7d08**********5b7**********29e\n      redirect-uri: ${justauth.address}/social-callback?source=oschina\n    alipay_wallet:\n      client-id: 10**********6\n      client-secret: 1f7d08**********5b7**********29e\n      redirect-uri: ${justauth.address}/social-callback?source=alipay_wallet\n      alipay-public-key: MIIB**************DAQAB\n    wechat_open:\n      client-id: 10**********6\n      client-secret: 1f7d08**********5b7**********29e\n      redirect-uri: ${justauth.address}/social-callback?source=wechat_open\n    wechat_mp:\n      client-id: 10**********6\n      client-secret: 1f7d08**********5b7**********29e\n      redirect-uri: ${justauth.address}/social-callback?source=wechat_mp\n    wechat_enterprise:\n      client-id: 10**********6\n      client-secret: 1f7d08**********5b7**********29e\n      redirect-uri: ${justauth.address}/social-callback?source=wechat_enterprise\n      agent-id: 1000002\n    gitlab:\n      client-id: 10**********6\n      client-secret: 1f7d08**********5b7**********29e\n      redirect-uri: ${justauth.address}/social-callback?source=gitlab\n','362c28d8864626b864d682b5f1c4a73a','2024-12-05 07:16:50','2024-12-05 07:16:50',NULL,'192.168.1.19','','prod','认证中心',NULL,NULL,'yaml',NULL,''),(137,'monitor.yml','DEFAULT_GROUP','# 监控中心配置\nspring:\n  security:\n    user:\n      name: admin\n      password: 123456\n  boot:\n    admin:\n      ui:\n        title: 基础平台服务监控中心\n      discovery:\n        # seata 不具有健康检查的能力 防止报错排除掉\n        ignored-services: seata-server\n','a342a0c14803d7d2337f91f7804fe057','2024-12-05 07:16:50','2024-12-05 08:19:35','nacos','192.168.1.19','','prod','监控中心','','','yaml','',''),(138,'system.yml','DEFAULT_GROUP','spring:\n  datasource:\n    dynamic:\n      # 设置默认的数据源或者数据源组,默认值即为 master\n      primary: master\n      datasource:\n        # 主库数据源\n        master:\n          type: ${spring.datasource.type}\n          driver-class-name: com.mysql.cj.jdbc.Driver\n          url: ${datasource.system-master.url}\n          username: ${datasource.system-master.username}\n          password: ${datasource.system-master.password}\n#        oracle:\n#          type: ${spring.datasource.type}\n#          driverClassName: oracle.jdbc.OracleDriver\n#          url: ${datasource.system-oracle.url}\n#          username: ${datasource.system-oracle.username}\n#          password: ${datasource.system-oracle.password}\n#        postgres:\n#          type: ${spring.datasource.type}\n#          driverClassName: org.postgresql.Driver\n#          url: ${datasource.system-postgres.url}\n#          username: ${datasource.system-postgres.username}\n#          password: ${datasource.system-postgres.password}\n','f59259409ff41d30e5276bf4afbcaa3d','2024-12-05 07:16:51','2024-12-05 07:16:51',NULL,'192.168.1.19','','prod','系统模块',NULL,NULL,'yaml',NULL,''),(139,'gen.yml','DEFAULT_GROUP','spring:\n  datasource:\n    dynamic:\n      # 设置默认的数据源或者数据源组,默认值即为 master\n      primary: master\n      seata: false\n      datasource:\n        # 主库数据源\n        master:\n          type: ${spring.datasource.type}\n          driver-class-name: com.mysql.cj.jdbc.Driver\n          url: ${datasource.system-master.url}\n          username: ${datasource.system-master.username}\n          password: ${datasource.system-master.password}\n#        oracle:\n#          type: ${spring.datasource.type}\n#          driverClassName: oracle.jdbc.OracleDriver\n#          url: ${datasource.system-oracle.url}\n#          username: ${datasource.system-oracle.username}\n#          password: ${datasource.system-oracle.password}\n#        postgres:\n#          type: ${spring.datasource.type}\n#          driverClassName: org.postgresql.Driver\n#          url: ${datasource.system-postgres.url}\n#          username: ${datasource.system-postgres.username}\n#          password: ${datasource.system-postgres.password}\n\n# 代码生成\ngen:\n  # 作者\n  author: MGARY\n  # 默认生成包路径 system 需改成自己的模块名称 如 system monitor tool\n  packageName: com.cdzeroly.system\n  # 自动去除表前缀，默认是false\n  autoRemovePre: false\n  # 表前缀（生成类名不会包含表前缀，多个用逗号分隔）\n  tablePrefix: sys_\n','f02376e215be7e735427461290ca100e','2024-12-05 07:16:51','2024-12-05 07:16:51',NULL,'192.168.1.19','','prod','代码生成',NULL,NULL,'yaml',NULL,''),(140,'job.yml','DEFAULT_GROUP','spring:\n  datasource:\n    dynamic:\n      # 设置默认的数据源或者数据源组,默认值即为 master\n      primary: master\n      seata: false\n      datasource:\n        # 主库数据源\n        master:\n          type: ${spring.datasource.type}\n          driver-class-name: com.mysql.cj.jdbc.Driver\n          url: ${datasource.job.url}\n          username: ${datasource.job.username}\n          password: ${datasource.job.password}\n\nsnail-job:\n  enabled: true\n  # 需要在 SnailJob 后台组管理创建对应名称的组,然后创建任务的时候选择对应的组,才能正确分派任务\n  group: \"ruoyi_group\"\n  #  SnailJob 接入验证令牌\n  token: \"SJ_cKqBTPzCsWA3VyuCfFoccmuIEGXjr5KT\"\n  server:\n    # 从 nacos 获取服务\n    server-name: snailjob-server\n    # 服务名优先 ip垫底\n    host: 127.0.0.1\n    port: 17888\n  # 详见 script/sql/ry_job.sql `sj_namespace` 表\n  namespace: ${spring.profiles.active}\n  # 随主应用端口飘逸\n  port: 2${server.port}\n  # 客户端ip指定\n  host:\n','eac1f2165a346ed9ce9ced98705aacc3','2024-12-05 07:16:51','2024-12-05 08:05:04','nacos','192.168.1.19','','prod','定时任务','','','yaml','',''),(141,'resource.yml','DEFAULT_GROUP','spring:\n  datasource:\n    dynamic:\n      # 设置默认的数据源或者数据源组,默认值即为 master\n      primary: master\n      datasource:\n        # 主库数据源\n        master:\n          type: ${spring.datasource.type}\n          driver-class-name: com.mysql.cj.jdbc.Driver\n          url: ${datasource.system-master.url}\n          username: ${datasource.system-master.username}\n          password: ${datasource.system-master.password}\n#        oracle:\n#          type: ${spring.datasource.type}\n#          driverClassName: oracle.jdbc.OracleDriver\n#          url: ${datasource.system-oracle.url}\n#          username: ${datasource.system-oracle.username}\n#          password: ${datasource.system-oracle.password}\n#        postgres:\n#          type: ${spring.datasource.type}\n#          driverClassName: org.postgresql.Driver\n#          url: ${datasource.system-postgres.url}\n#          username: ${datasource.system-postgres.username}\n#          password: ${datasource.system-postgres.password}\n\n# 默认/推荐使用sse推送\nsse:\n  enabled: true\n  path: /sse\n\nwebsocket:\n  # 如果关闭 需要和前端开关一起关闭\n  enabled: false\n  # 路径\n  path: /websocket\n  # 设置访问源地址\n  allowedOrigins: \'*\'\n\nmail:\n  enabled: false\n  host: smtp.163.com\n  port: 465\n  # 是否需要用户名密码验证\n  auth: true\n  # 发送方，遵循RFC-822标准\n  from: xxx@163.com\n  # 用户名（注意：如果使用foxmail邮箱，此处user为qq号）\n  user: xxx@163.com\n  # 密码（注意，某些邮箱需要为SMTP服务单独设置密码，详情查看相关帮助）\n  pass: xxxxxxxxxx\n  # 使用 STARTTLS安全连接，STARTTLS是对纯文本通信协议的扩展。\n  starttlsEnable: true\n  # 使用SSL安全连接\n  sslEnable: true\n  # SMTP超时时长，单位毫秒，缺省值不超时\n  timeout: 0\n  # Socket连接超时值，单位毫秒，缺省值不超时\n  connectionTimeout: 0\n\n# sms 短信 支持 阿里云 腾讯云 云片 等等各式各样的短信服务商\n# https://sms4j.com/doc3/ 差异配置文档地址 支持单厂商多配置，可以配置多个同时使用\nsms:\n  # 配置源类型用于标定配置来源(interface,yaml)\n  config-type: yaml\n  # 用于标定yml中的配置是否开启短信拦截，接口配置不受此限制\n  restricted: true\n  # 短信拦截限制单手机号每分钟最大发送，只对开启了拦截的配置有效\n  minute-max: 1\n  # 短信拦截限制单手机号每日最大发送量，只对开启了拦截的配置有效\n  account-max: 30\n  # 以下配置来自于 org.dromara.sms4j.provider.config.BaseConfig类中\n  blends:\n    # 唯一ID 用于发送短信寻找具体配置 随便定义别用中文即可\n    # 可以同时存在两个相同厂商 例如: ali1 ali2 两个不同的阿里短信账号 也可用于区分租户\n    config1:\n      # 框架定义的厂商名称标识，标定此配置是哪个厂商，详细请看厂商标识介绍部分\n      supplier: alibaba\n      # 有些称为accessKey有些称之为apiKey，也有称为sdkKey或者appId。\n      access-key-id: 您的accessKey\n      # 称为accessSecret有些称之为apiSecret\n      access-key-secret: 您的accessKeySecret\n      signature: 您的短信签名\n      sdk-app-id: 您的sdkAppId\n    config2:\n      # 厂商标识，标定此配置是哪个厂商，详细请看厂商标识介绍部分\n      supplier: tencent\n      access-key-id: 您的accessKey\n      access-key-secret: 您的accessKeySecret\n      signature: 您的短信签名\n      sdk-app-id: 您的sdkAppId\n','efb16000eb230328fa2128ea56558fd2','2024-12-05 07:16:51','2024-12-05 07:16:51',NULL,'192.168.1.19','','prod','文件服务',NULL,NULL,'yaml',NULL,''),(142,'workflow.yml','DEFAULT_GROUP','spring:\n  datasource:\n    dynamic:\n      # 设置默认的数据源或者数据源组,默认值即为 master\n      primary: master\n      datasource:\n        # 主库数据源\n        master:\n          type: ${spring.datasource.type}\n          driver-class-name: com.mysql.cj.jdbc.Driver\n          url: ${datasource.workflow.url}\n          username: ${datasource.workflow.username}\n          password: ${datasource.workflow.password}\n#        oracle:\n#          type: ${spring.datasource.type}\n#          driverClassName: oracle.jdbc.OracleDriver\n#          url: ${datasource.system-oracle.url}\n#          username: ${datasource.system-oracle.username}\n#          password: ${datasource.system-oracle.password}\n#        postgres:\n#          type: ${spring.datasource.type}\n#          driverClassName: org.postgresql.Driver\n#          url: ${datasource.system-postgres.url}\n#          username: ${datasource.system-postgres.username}\n#          password: ${datasource.system-postgres.password}\n\n# flowable配置\nflowable:\n  # 开关 用于启动/停用工作流\n  enabled: true\n  process.enabled: ${flowable.enabled}\n  eventregistry.enabled: ${flowable.enabled}\n  # 关闭定时任务JOB\n  async-executor-activate: false\n  # 将databaseSchemaUpdate设置为true。当Flowable发现库与数据库表结构不一致时，会自动将数据库表结构升级至新版本。\n  database-schema-update: true\n  activity-font-name: 宋体\n  label-font-name: 宋体\n  annotation-font-name: 宋体\n  # 关闭各个模块生成表，目前只使用工作流基础表\n  idm:\n    enabled: false\n  cmmn:\n    enabled: false\n  dmn:\n    enabled: false\n  app:\n    enabled: false\n','95b566892b838da618fbfdaf1ecffd0a','2024-12-05 07:16:51','2024-12-05 07:16:51',NULL,'192.168.1.19','','prod','工作流服务',NULL,NULL,'yaml',NULL,''),(143,'seata-server.properties','DEFAULT_GROUP','service.vgroupMapping.auth-group=default\nservice.vgroupMapping.system-group=default\nservice.vgroupMapping.resource-group=default\nservice.vgroupMapping.workflow-group=default\n\nservice.enableDegrade=false\nservice.disableGlobalTransaction=false\n\n#Transaction storage configuration, only for the server. The file, DB, and redis configuration values are optional.\nstore.mode=db\nstore.lock.mode=db\nstore.session.mode=db\n#Used for password encryption\n#store.publicKey=\n\n#These configurations are required if the `store mode` is `db`. If `store.mode,store.lock.mode,store.session.mode` are not equal to `db`, you can remove the configuration block.\nstore.db.datasource=hikari\nstore.db.dbType=mysql\nstore.db.driverClassName=com.mysql.cj.jdbc.Driver\nstore.db.url=jdbc:mysql://127.0.0.1:3306/ry-seata?useUnicode=true&rewriteBatchedStatements=true&allowPublicKeyRetrieval=true\nstore.db.user=root\nstore.db.password=root\nstore.db.minConn=5\nstore.db.maxConn=30\nstore.db.globalTable=global_table\nstore.db.branchTable=branch_table\nstore.db.distributedLockTable=distributed_lock\nstore.db.queryLimit=100\nstore.db.lockTable=lock_table\nstore.db.maxWait=5000\n\n# redis 模式 store.mode=redis 开启 (控制台查询功能有限,不影响实际执行功能)\n# store.redis.host=127.0.0.1\n# store.redis.port=6379\n# 最大连接数\n# store.redis.maxConn=10\n# 最小连接数\n# store.redis.minConn=1\n# store.redis.database=0\n# store.redis.password=\n# store.redis.queryLimit=100\n\n#Transaction rule configuration, only for the server\nserver.recovery.committingRetryPeriod=1000\nserver.recovery.asynCommittingRetryPeriod=1000\nserver.recovery.rollbackingRetryPeriod=1000\nserver.recovery.timeoutRetryPeriod=1000\nserver.maxCommitRetryTimeout=-1\nserver.maxRollbackRetryTimeout=-1\nserver.rollbackRetryTimeoutUnlockEnable=false\nserver.distributedLockExpireTime=10000\nserver.xaerNotaRetryTimeout=60000\nserver.session.branchAsyncQueueSize=5000\nserver.session.enableBranchAsyncRemove=false\n\n#Transaction rule configuration, only for the client\nclient.rm.asyncCommitBufferLimit=10000\nclient.rm.lock.retryInterval=10\nclient.rm.lock.retryTimes=30\nclient.rm.lock.retryPolicyBranchRollbackOnConflict=true\nclient.rm.reportRetryCount=5\nclient.rm.tableMetaCheckEnable=true\nclient.rm.tableMetaCheckerInterval=60000\nclient.rm.sqlParserType=druid\nclient.rm.reportSuccessEnable=false\nclient.rm.sagaBranchRegisterEnable=false\nclient.rm.sagaJsonParser=fastjson\nclient.rm.tccActionInterceptorOrder=-2147482648\nclient.tm.commitRetryCount=5\nclient.tm.rollbackRetryCount=5\nclient.tm.defaultGlobalTransactionTimeout=60000\nclient.tm.degradeCheck=false\nclient.tm.degradeCheckAllowTimes=10\nclient.tm.degradeCheckPeriod=2000\nclient.tm.interceptorOrder=-2147482648\nclient.undo.dataValidation=true\nclient.undo.logSerialization=jackson\nclient.undo.onlyCareUpdateColumns=true\nserver.undo.logSaveDays=7\nserver.undo.logDeletePeriod=86400000\nclient.undo.logTable=undo_log\nclient.undo.compress.enable=true\nclient.undo.compress.type=zip\nclient.undo.compress.threshold=64k\n\n#For TCC transaction mode\ntcc.fence.logTableName=tcc_fence_log\ntcc.fence.cleanPeriod=1h\n\n#Log rule configuration, for client and server\nlog.exceptionRate=100\n\n#Metrics configuration, only for the server\nmetrics.enabled=false\nmetrics.registryType=compact\nmetrics.exporterList=prometheus\nmetrics.exporterPrometheusPort=9898\n\n#For details about configuration items, see https://seata.io/zh-cn/docs/user/configurations.html\n#Transport configuration, for client and server\ntransport.type=TCP\ntransport.server=NIO\ntransport.heartbeat=true\ntransport.enableTmClientBatchSendRequest=false\ntransport.enableRmClientBatchSendRequest=true\ntransport.enableTcServerBatchSendResponse=false\ntransport.rpcRmRequestTimeout=30000\ntransport.rpcTmRequestTimeout=30000\ntransport.rpcTcRequestTimeout=30000\ntransport.threadFactory.bossThreadPrefix=NettyBoss\ntransport.threadFactory.workerThreadPrefix=NettyServerNIOWorker\ntransport.threadFactory.serverExecutorThreadPrefix=NettyServerBizHandler\ntransport.threadFactory.shareBossWorker=false\ntransport.threadFactory.clientSelectorThreadPrefix=NettyClientSelector\ntransport.threadFactory.clientSelectorThreadSize=1\ntransport.threadFactory.clientWorkerThreadPrefix=NettyClientWorkerThread\ntransport.threadFactory.bossThreadSize=1\ntransport.threadFactory.workerThreadSize=default\ntransport.shutdown.wait=3\ntransport.serialization=seata\ntransport.compressor=none\n','2f3eb3aa0bf7252b4a88def5a368d03a','2024-12-05 07:16:51','2024-12-05 07:54:12','nacos','192.168.1.19','','prod','seata配置文件','','','properties','',''),(144,'sentinel-dashboard.yml','DEFAULT_GROUP','spring:\n  mvc:\n    pathmatch:\n      # 修复 sentinel 控制台未适配 springboot 2.6 新路由方式\n      matching-strategy: ANT_PATH_MATCHER\n\nserver:\n  servlet:\n    encoding:\n      force: true\n      charset: UTF-8\n      enabled: true\n    session:\n      cookie:\n        name: sentinel_dashboard_cookie\n\nlogging:\n  level:\n    org.springframework.web: INFO\n\nauth:\n  enabled: true\n  filter:\n    exclude-urls: /,/auth/login,/auth/logout,/registry/machine,/version,/actuator,/actuator/**\n    exclude-url-suffixes: htm,html,js,css,map,ico,ttf,woff,png\n  username: sentinel\n  password: sentinel\n','59055747c62f08cd2c38a6016d4b9227','2024-12-05 07:16:51','2024-12-05 07:16:51',NULL,'192.168.1.19','','prod','sentinel控制台配置文件',NULL,NULL,'yaml',NULL,''),(145,'snailjob-server.yml','DEFAULT_GROUP','spring:\n  datasource:\n    type: com.zaxxer.hikari.HikariDataSource\n    driver-class-name: com.mysql.cj.jdbc.Driver\n    url: ${datasource.job.url}\n    username: ${datasource.job.username}\n    password: ${datasource.job.password}\n    hikari:\n      connection-timeout: 30000\n      validation-timeout: 5000\n      minimum-idle: 10\n      maximum-pool-size: 20\n      idle-timeout: 600000\n      max-lifetime: 900000\n      keepaliveTime: 30000\n  cloud:\n    nacos:\n      discovery:\n        metadata:\n          # 解决 er 服务有 context-path 无法监控问题\n          management.context-path: ${server.servlet.context-path}/actuator\n          # 监控账号密码\n          username: ruoyi\n          userpassword: 123456\n\n# snail-job 服务端配置\nsnail-job:\n  # 拉取重试数据的每批次的大小\n  retry-pull-page-size: 1000\n  # 拉取重试数据的每批次的大小\n  job-pull-page-size: 1000\n  # 服务端 netty 端口\n  netty-port: 17888\n  # 重试和死信表的分区总数\n  total-partition: 2\n  # 一个客户端每秒最多接收的重试数量指令\n  limiter: 1000\n  # 号段模式下步长配置\n  step: 100\n  # 日志保存时间(单位: day)\n  log-storage: 90\n  # 回调配置\n  callback:\n    #回调最大执行次数\n    max-count: 288\n    #间隔时间\n    trigger-interval: 900\n  retry-max-pull-count: 10\n','ab06186a47974fd09806e48484f37a1a','2024-12-05 07:16:51','2024-12-05 07:16:51',NULL,'192.168.1.19','','prod','SJ定时任务控制台',NULL,NULL,'yaml',NULL,''),(156,'sentinel-gateway.json','DEFAULT_GROUP','[\n  {\n    \"resource\": \"ruoyi-auth\",\n    \"count\": 500,\n    \"grade\": 1,\n    \"limitApp\": \"default\",\n    \"strategy\": 0,\n    \"controlBehavior\": 0\n  },\n  {\n    \"resource\": \"ruoyi-system\",\n    \"count\": 1000,\n    \"grade\": 1,\n    \"limitApp\": \"default\",\n    \"strategy\": 0,\n    \"controlBehavior\": 0\n  },\n  {\n    \"resource\": \"ruoyi-resource\",\n    \"count\": 500,\n    \"grade\": 1,\n    \"limitApp\": \"default\",\n    \"strategy\": 0,\n    \"controlBehavior\": 0\n  }\n]\n','2efad3702e99adef46c2ee818b3925e5','2024-12-05 07:19:21','2024-12-05 07:19:21',NULL,'192.168.1.19','','prod','限流策略',NULL,NULL,'json',NULL,'');
/*!40000 ALTER TABLE `config_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `config_info_aggr`
--

DROP TABLE IF EXISTS `config_info_aggr`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `config_info_aggr` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
  `data_id` varchar(255) COLLATE utf8mb3_bin NOT NULL COMMENT 'data_id',
  `group_id` varchar(128) COLLATE utf8mb3_bin NOT NULL COMMENT 'group_id',
  `datum_id` varchar(255) COLLATE utf8mb3_bin NOT NULL COMMENT 'datum_id',
  `content` longtext COLLATE utf8mb3_bin NOT NULL COMMENT '内容',
  `gmt_modified` datetime NOT NULL COMMENT '修改时间',
  `app_name` varchar(128) COLLATE utf8mb3_bin DEFAULT NULL COMMENT 'app_name',
  `tenant_id` varchar(128) COLLATE utf8mb3_bin DEFAULT '' COMMENT '租户字段',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_configinfoaggr_datagrouptenantdatum` (`data_id`,`group_id`,`tenant_id`,`datum_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_bin COMMENT='增加租户字段';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `config_info_aggr`
--

LOCK TABLES `config_info_aggr` WRITE;
/*!40000 ALTER TABLE `config_info_aggr` DISABLE KEYS */;
/*!40000 ALTER TABLE `config_info_aggr` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `config_info_beta`
--

DROP TABLE IF EXISTS `config_info_beta`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `config_info_beta` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
  `data_id` varchar(255) COLLATE utf8mb3_bin NOT NULL COMMENT 'data_id',
  `group_id` varchar(128) COLLATE utf8mb3_bin NOT NULL COMMENT 'group_id',
  `app_name` varchar(128) COLLATE utf8mb3_bin DEFAULT NULL COMMENT 'app_name',
  `content` longtext COLLATE utf8mb3_bin NOT NULL COMMENT 'content',
  `beta_ips` varchar(1024) COLLATE utf8mb3_bin DEFAULT NULL COMMENT 'betaIps',
  `md5` varchar(32) COLLATE utf8mb3_bin DEFAULT NULL COMMENT 'md5',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `src_user` text COLLATE utf8mb3_bin COMMENT 'source user',
  `src_ip` varchar(50) COLLATE utf8mb3_bin DEFAULT NULL COMMENT 'source ip',
  `tenant_id` varchar(128) COLLATE utf8mb3_bin DEFAULT '' COMMENT '租户字段',
  `encrypted_data_key` text COLLATE utf8mb3_bin NOT NULL COMMENT '密钥',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_configinfobeta_datagrouptenant` (`data_id`,`group_id`,`tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_bin COMMENT='config_info_beta';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `config_info_beta`
--

LOCK TABLES `config_info_beta` WRITE;
/*!40000 ALTER TABLE `config_info_beta` DISABLE KEYS */;
/*!40000 ALTER TABLE `config_info_beta` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `config_info_tag`
--

DROP TABLE IF EXISTS `config_info_tag`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `config_info_tag` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
  `data_id` varchar(255) COLLATE utf8mb3_bin NOT NULL COMMENT 'data_id',
  `group_id` varchar(128) COLLATE utf8mb3_bin NOT NULL COMMENT 'group_id',
  `tenant_id` varchar(128) COLLATE utf8mb3_bin DEFAULT '' COMMENT 'tenant_id',
  `tag_id` varchar(128) COLLATE utf8mb3_bin NOT NULL COMMENT 'tag_id',
  `app_name` varchar(128) COLLATE utf8mb3_bin DEFAULT NULL COMMENT 'app_name',
  `content` longtext COLLATE utf8mb3_bin NOT NULL COMMENT 'content',
  `md5` varchar(32) COLLATE utf8mb3_bin DEFAULT NULL COMMENT 'md5',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `src_user` text COLLATE utf8mb3_bin COMMENT 'source user',
  `src_ip` varchar(50) COLLATE utf8mb3_bin DEFAULT NULL COMMENT 'source ip',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_configinfotag_datagrouptenanttag` (`data_id`,`group_id`,`tenant_id`,`tag_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_bin COMMENT='config_info_tag';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `config_info_tag`
--

LOCK TABLES `config_info_tag` WRITE;
/*!40000 ALTER TABLE `config_info_tag` DISABLE KEYS */;
/*!40000 ALTER TABLE `config_info_tag` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `config_tags_relation`
--

DROP TABLE IF EXISTS `config_tags_relation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `config_tags_relation` (
  `id` bigint NOT NULL COMMENT 'id',
  `tag_name` varchar(128) COLLATE utf8mb3_bin NOT NULL COMMENT 'tag_name',
  `tag_type` varchar(64) COLLATE utf8mb3_bin DEFAULT NULL COMMENT 'tag_type',
  `data_id` varchar(255) COLLATE utf8mb3_bin NOT NULL COMMENT 'data_id',
  `group_id` varchar(128) COLLATE utf8mb3_bin NOT NULL COMMENT 'group_id',
  `tenant_id` varchar(128) COLLATE utf8mb3_bin DEFAULT '' COMMENT 'tenant_id',
  `nid` bigint NOT NULL AUTO_INCREMENT COMMENT 'nid, 自增长标识',
  PRIMARY KEY (`nid`),
  UNIQUE KEY `uk_configtagrelation_configidtag` (`id`,`tag_name`,`tag_type`),
  KEY `idx_tenant_id` (`tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_bin COMMENT='config_tag_relation';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `config_tags_relation`
--

LOCK TABLES `config_tags_relation` WRITE;
/*!40000 ALTER TABLE `config_tags_relation` DISABLE KEYS */;
/*!40000 ALTER TABLE `config_tags_relation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `group_capacity`
--

DROP TABLE IF EXISTS `group_capacity`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `group_capacity` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `group_id` varchar(128) COLLATE utf8mb3_bin NOT NULL DEFAULT '' COMMENT 'Group ID，空字符表示整个集群',
  `quota` int unsigned NOT NULL DEFAULT '0' COMMENT '配额，0表示使用默认值',
  `usage` int unsigned NOT NULL DEFAULT '0' COMMENT '使用量',
  `max_size` int unsigned NOT NULL DEFAULT '0' COMMENT '单个配置大小上限，单位为字节，0表示使用默认值',
  `max_aggr_count` int unsigned NOT NULL DEFAULT '0' COMMENT '聚合子配置最大个数，，0表示使用默认值',
  `max_aggr_size` int unsigned NOT NULL DEFAULT '0' COMMENT '单个聚合数据的子配置大小上限，单位为字节，0表示使用默认值',
  `max_history_count` int unsigned NOT NULL DEFAULT '0' COMMENT '最大变更历史数量',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_group_id` (`group_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_bin COMMENT='集群、各Group容量信息表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `group_capacity`
--

LOCK TABLES `group_capacity` WRITE;
/*!40000 ALTER TABLE `group_capacity` DISABLE KEYS */;
/*!40000 ALTER TABLE `group_capacity` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `his_config_info`
--

DROP TABLE IF EXISTS `his_config_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `his_config_info` (
  `id` bigint unsigned NOT NULL COMMENT 'id',
  `nid` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT 'nid, 自增标识',
  `data_id` varchar(255) COLLATE utf8mb3_bin NOT NULL COMMENT 'data_id',
  `group_id` varchar(128) COLLATE utf8mb3_bin NOT NULL COMMENT 'group_id',
  `app_name` varchar(128) COLLATE utf8mb3_bin DEFAULT NULL COMMENT 'app_name',
  `content` longtext COLLATE utf8mb3_bin NOT NULL COMMENT 'content',
  `md5` varchar(32) COLLATE utf8mb3_bin DEFAULT NULL COMMENT 'md5',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `src_user` text COLLATE utf8mb3_bin COMMENT 'source user',
  `src_ip` varchar(50) COLLATE utf8mb3_bin DEFAULT NULL COMMENT 'source ip',
  `op_type` char(10) COLLATE utf8mb3_bin DEFAULT NULL COMMENT 'operation type',
  `tenant_id` varchar(128) COLLATE utf8mb3_bin DEFAULT '' COMMENT '租户字段',
  `encrypted_data_key` text COLLATE utf8mb3_bin NOT NULL COMMENT '密钥',
  PRIMARY KEY (`nid`),
  KEY `idx_gmt_create` (`gmt_create`),
  KEY `idx_gmt_modified` (`gmt_modified`),
  KEY `idx_did` (`data_id`)
) ENGINE=InnoDB AUTO_INCREMENT=88 DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_bin COMMENT='多租户改造';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `his_config_info`
--

LOCK TABLES `his_config_info` WRITE;
/*!40000 ALTER TABLE `his_config_info` DISABLE KEYS */;
INSERT INTO `his_config_info` VALUES (1,1,'application-common.yml','DEFAULT_GROUP','','# 将项目路径：config/下对应文件中内容复制到此处','2944a25cb97926efcaa43b3ad7a64cf0','2024-12-04 17:47:03','2024-12-04 09:47:03','nacos','192.168.1.19','U','dev',''),(2,2,'datasource.yml','DEFAULT_GROUP','','# 将项目路径：config/下对应文件中内容复制到此处','2944a25cb97926efcaa43b3ad7a64cf0','2024-12-04 17:47:17','2024-12-04 09:47:18','nacos','192.168.1.19','U','dev',''),(3,3,'ruoyi-gateway.yml','DEFAULT_GROUP','','# 将项目路径：config/下对应文件中内容复制到此处','2944a25cb97926efcaa43b3ad7a64cf0','2024-12-04 18:02:15','2024-12-04 10:02:17','nacos','192.168.1.19','U','dev',''),(4,4,'ruoyi-auth.yml','DEFAULT_GROUP','','# 将项目路径：config/下对应文件中内容复制到此处','2944a25cb97926efcaa43b3ad7a64cf0','2024-12-04 18:02:29','2024-12-04 10:02:30','nacos','192.168.1.19','U','dev',''),(7,5,'ruoyi-gen.yml','DEFAULT_GROUP','','# 将项目路径：config/下对应文件中内容复制到此处','2944a25cb97926efcaa43b3ad7a64cf0','2024-12-04 18:02:46','2024-12-04 10:02:47','nacos','192.168.1.19','U','dev',''),(8,6,'ruoyi-job.yml','DEFAULT_GROUP','','# 将项目路径：config/下对应文件中内容复制到此处','2944a25cb97926efcaa43b3ad7a64cf0','2024-12-04 18:03:00','2024-12-04 10:03:00','nacos','192.168.1.19','U','dev',''),(5,7,'ruoyi-monitor.yml','DEFAULT_GROUP','','# 将项目路径：config/下对应文件中内容复制到此处','2944a25cb97926efcaa43b3ad7a64cf0','2024-12-04 18:03:11','2024-12-04 10:03:12','nacos','192.168.1.19','U','dev',''),(9,8,'ruoyi-resource.yml','DEFAULT_GROUP','','# 将项目路径：config/下对应文件中内容复制到此处','2944a25cb97926efcaa43b3ad7a64cf0','2024-12-04 18:03:25','2024-12-04 10:03:25','nacos','192.168.1.19','U','dev',''),(13,9,'ruoyi-sentinel-dashboard.yml','DEFAULT_GROUP','','# 将项目路径：config/下对应文件中内容复制到此处','2944a25cb97926efcaa43b3ad7a64cf0','2024-12-04 18:03:46','2024-12-04 10:03:47','nacos','192.168.1.19','U','dev',''),(14,10,'ruoyi-snailjob-server.yml','DEFAULT_GROUP','','# 将项目路径：config/下对应文件中内容复制到此处','2944a25cb97926efcaa43b3ad7a64cf0','2024-12-04 18:04:01','2024-12-04 10:04:01','nacos','192.168.1.19','U','dev',''),(6,11,'ruoyi-system.yml','DEFAULT_GROUP','','# 将项目路径：config/下对应文件中内容复制到此处','2944a25cb97926efcaa43b3ad7a64cf0','2024-12-04 18:04:12','2024-12-04 10:04:13','nacos','192.168.1.19','U','dev',''),(10,12,'ruoyi-workflow.yml','DEFAULT_GROUP','','# 将项目路径：config/下对应文件中内容复制到此处','2944a25cb97926efcaa43b3ad7a64cf0','2024-12-04 18:04:32','2024-12-04 10:04:32','nacos','192.168.1.19','U','dev',''),(12,13,'seata-server.properties','DEFAULT_GROUP','','# 将项目路径：config/下对应文件中内容复制到此处','2944a25cb97926efcaa43b3ad7a64cf0','2024-12-04 18:04:46','2024-12-04 10:04:47','nacos','192.168.1.19','U','dev',''),(11,14,'sentinel-ruoyi-gateway.json','DEFAULT_GROUP','','# 将项目路径：config/下对应文件中内容复制到此处','2944a25cb97926efcaa43b3ad7a64cf0','2024-12-04 18:05:04','2024-12-04 10:05:05','nacos','192.168.1.19','U','dev',''),(2,15,'datasource.yml','DEFAULT_GROUP','','datasource:\n  system-master:\n    # jdbc 所有参数配置参考 https://lionli.blog.csdn.net/article/details/122018562\n    # rewriteBatchedStatements=true 批处理优化 大幅提升批量插入更新删除性能\n    url: jdbc:mysql://localhost:3306/ry-cloud?useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&useSSL=true&serverTimezone=GMT%2B8&rewriteBatchedStatements=true&allowPublicKeyRetrieval=true\n    username: root\n    password: password\n  gen:\n    url: jdbc:mysql://localhost:3306/ry-cloud?useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&useSSL=true&serverTimezone=GMT%2B8&rewriteBatchedStatements=true&allowPublicKeyRetrieval=true\n    username: root\n    password: password\n  job:\n    url: jdbc:mysql://localhost:3306/ry-job?useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&useSSL=true&serverTimezone=GMT%2B8&rewriteBatchedStatements=true&allowPublicKeyRetrieval=true\n    username: root\n    password: password\n  workflow:\n    url: jdbc:mysql://localhost:3306/ry-workflow?useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&useSSL=true&serverTimezone=GMT%2B8&rewriteBatchedStatements=true&allowPublicKeyRetrieval=true&nullCatalogMeansCurrent=true\n    username: root\n    password: password\n#  system-oracle:\n#    url: jdbc:oracle:thin:@//localhost:1521/XE\n#    username: ROOT\n#    password: password\n#  system-postgres:\n#    url: jdbc:postgresql://localhost:5432/postgres?useUnicode=true&characterEncoding=utf8&useSSL=true&autoReconnect=true&reWriteBatchedInserts=true\n#    username: root\n#    password: password\n\nspring:\n  datasource:\n    type: com.zaxxer.hikari.HikariDataSource\n    # 动态数据源文档 https://www.kancloud.cn/tracy5546/dynamic-datasource/content\n    dynamic:\n      # 性能分析插件(有性能损耗 不建议生产环境使用)\n      p6spy: true\n      # 开启seata代理，开启后默认每个数据源都代理，如果某个不需要代理可单独关闭\n      seata: ${seata.enabled}\n      # 严格模式 匹配不到数据源则报错\n      strict: true\n      hikari:\n        # 最大连接池数量\n        maxPoolSize: 20\n        # 最小空闲线程数量\n        minIdle: 10\n        # 配置获取连接等待超时的时间\n        connectionTimeout: 30000\n        # 校验超时时间\n        validationTimeout: 5000\n        # 空闲连接存活最大时间，默认10分钟\n        idleTimeout: 600000\n        # 此属性控制池中连接的最长生命周期，值0表示无限生命周期，默认30分钟\n        maxLifetime: 1800000\n        # 多久检查一次连接的活性\n        keepaliveTime: 30000\n','0a54f3eeff600980be9bc099a266438e','2024-12-04 18:08:13','2024-12-04 10:08:14','nacos','192.168.1.19','U','dev',''),(12,16,'seata-server.properties','DEFAULT_GROUP','','service.vgroupMapping.ruoyi-auth-group=default\nservice.vgroupMapping.ruoyi-system-group=default\nservice.vgroupMapping.ruoyi-resource-group=default\nservice.vgroupMapping.ruoyi-workflow-group=default\n\nservice.enableDegrade=false\nservice.disableGlobalTransaction=false\n\n#Transaction storage configuration, only for the server. The file, DB, and redis configuration values are optional.\nstore.mode=db\nstore.lock.mode=db\nstore.session.mode=db\n#Used for password encryption\n#store.publicKey=\n\n#These configurations are required if the `store mode` is `db`. If `store.mode,store.lock.mode,store.session.mode` are not equal to `db`, you can remove the configuration block.\nstore.db.datasource=hikari\nstore.db.dbType=mysql\nstore.db.driverClassName=com.mysql.cj.jdbc.Driver\nstore.db.url=jdbc:mysql://127.0.0.1:3306/ry-seata?useUnicode=true&rewriteBatchedStatements=true&allowPublicKeyRetrieval=true\nstore.db.user=root\nstore.db.password=root\nstore.db.minConn=5\nstore.db.maxConn=30\nstore.db.globalTable=global_table\nstore.db.branchTable=branch_table\nstore.db.distributedLockTable=distributed_lock\nstore.db.queryLimit=100\nstore.db.lockTable=lock_table\nstore.db.maxWait=5000\n\n# redis 模式 store.mode=redis 开启 (控制台查询功能有限,不影响实际执行功能)\n# store.redis.host=127.0.0.1\n# store.redis.port=6379\n# 最大连接数\n# store.redis.maxConn=10\n# 最小连接数\n# store.redis.minConn=1\n# store.redis.database=0\n# store.redis.password=\n# store.redis.queryLimit=100\n\n#Transaction rule configuration, only for the server\nserver.recovery.committingRetryPeriod=1000\nserver.recovery.asynCommittingRetryPeriod=1000\nserver.recovery.rollbackingRetryPeriod=1000\nserver.recovery.timeoutRetryPeriod=1000\nserver.maxCommitRetryTimeout=-1\nserver.maxRollbackRetryTimeout=-1\nserver.rollbackRetryTimeoutUnlockEnable=false\nserver.distributedLockExpireTime=10000\nserver.xaerNotaRetryTimeout=60000\nserver.session.branchAsyncQueueSize=5000\nserver.session.enableBranchAsyncRemove=false\n\n#Transaction rule configuration, only for the client\nclient.rm.asyncCommitBufferLimit=10000\nclient.rm.lock.retryInterval=10\nclient.rm.lock.retryTimes=30\nclient.rm.lock.retryPolicyBranchRollbackOnConflict=true\nclient.rm.reportRetryCount=5\nclient.rm.tableMetaCheckEnable=true\nclient.rm.tableMetaCheckerInterval=60000\nclient.rm.sqlParserType=druid\nclient.rm.reportSuccessEnable=false\nclient.rm.sagaBranchRegisterEnable=false\nclient.rm.sagaJsonParser=fastjson\nclient.rm.tccActionInterceptorOrder=-2147482648\nclient.tm.commitRetryCount=5\nclient.tm.rollbackRetryCount=5\nclient.tm.defaultGlobalTransactionTimeout=60000\nclient.tm.degradeCheck=false\nclient.tm.degradeCheckAllowTimes=10\nclient.tm.degradeCheckPeriod=2000\nclient.tm.interceptorOrder=-2147482648\nclient.undo.dataValidation=true\nclient.undo.logSerialization=jackson\nclient.undo.onlyCareUpdateColumns=true\nserver.undo.logSaveDays=7\nserver.undo.logDeletePeriod=86400000\nclient.undo.logTable=undo_log\nclient.undo.compress.enable=true\nclient.undo.compress.type=zip\nclient.undo.compress.threshold=64k\n\n#For TCC transaction mode\ntcc.fence.logTableName=tcc_fence_log\ntcc.fence.cleanPeriod=1h\n\n#Log rule configuration, for client and server\nlog.exceptionRate=100\n\n#Metrics configuration, only for the server\nmetrics.enabled=false\nmetrics.registryType=compact\nmetrics.exporterList=prometheus\nmetrics.exporterPrometheusPort=9898\n\n#For details about configuration items, see https://seata.io/zh-cn/docs/user/configurations.html\n#Transport configuration, for client and server\ntransport.type=TCP\ntransport.server=NIO\ntransport.heartbeat=true\ntransport.enableTmClientBatchSendRequest=false\ntransport.enableRmClientBatchSendRequest=true\ntransport.enableTcServerBatchSendResponse=false\ntransport.rpcRmRequestTimeout=30000\ntransport.rpcTmRequestTimeout=30000\ntransport.rpcTcRequestTimeout=30000\ntransport.threadFactory.bossThreadPrefix=NettyBoss\ntransport.threadFactory.workerThreadPrefix=NettyServerNIOWorker\ntransport.threadFactory.serverExecutorThreadPrefix=NettyServerBizHandler\ntransport.threadFactory.shareBossWorker=false\ntransport.threadFactory.clientSelectorThreadPrefix=NettyClientSelector\ntransport.threadFactory.clientSelectorThreadSize=1\ntransport.threadFactory.clientWorkerThreadPrefix=NettyClientWorkerThread\ntransport.threadFactory.bossThreadSize=1\ntransport.threadFactory.workerThreadSize=default\ntransport.shutdown.wait=3\ntransport.serialization=seata\ntransport.compressor=none\n','444318b29f52bedc05766af6c154b76e','2024-12-04 18:08:57','2024-12-04 10:08:58','nacos','192.168.1.19','U','dev',''),(1,17,'application-common.yml','DEFAULT_GROUP','','server:\n  # undertow 配置\n  undertow:\n    # HTTP post内容的最大大小。当值为-1时，默认值为大小是无限的\n    max-http-post-size: -1\n    # 以下的配置会影响buffer,这些buffer会用于服务器连接的IO操作,有点类似netty的池化内存管理\n    # 每块buffer的空间大小,越小的空间被利用越充分\n    buffer-size: 512\n    # 是否分配的直接内存\n    direct-buffers: true\n    threads:\n      # 设置IO线程数, 它主要执行非阻塞的任务,它们会负责多个连接, 默认设置每个CPU核心一个线程\n      io: 8\n      # 阻塞任务线程池, 当执行类似servlet请求阻塞操作, undertow会从这个线程池中取得线程,它的值设置取决于系统的负载\n      worker: 256\n\ndubbo:\n  application:\n    # 关闭qos端口避免单机多生产者端口冲突 如需使用自行开启\n    qos-enable: false\n  protocol:\n    # 如需使用 Triple 3.0 新协议 可查看官方文档\n    # 使用 dubbo 协议通信\n    name: dubbo\n    # dubbo 协议端口(-1表示自增端口,从20880开始)\n    port: -1\n    # 指定dubbo协议注册ip\n    # host: 192.168.0.100\n  # 消费者相关配置\n  consumer:\n    # 超时时间\n    timeout: 3000\n  # 自定义配置\n  custom:\n    # 全局请求log\n    request-log: true\n    # info 基础信息 param 参数信息 full 全部\n    log-level: info\n\nspring:\n  threads:\n    # 开启虚拟线程 仅jdk21可用\n    virtual:\n      enabled: false\n  # 资源信息\n  messages:\n    # 国际化资源文件路径\n    basename: i18n/messages\n  servlet:\n    multipart:\n      # 整个请求大小限制\n      max-request-size: 20MB\n      # 上传单个文件大小限制\n      max-file-size: 10MB\n  mvc:\n    # 设置静态资源路径 防止所有请求都去查静态资源\n    static-path-pattern: /static/**\n    format:\n      date-time: yyyy-MM-dd HH:mm:ss\n  #jackson配置\n  jackson:\n    # 日期格式化\n    date-format: yyyy-MM-dd HH:mm:ss\n    serialization:\n      # 格式化输出\n      INDENT_OUTPUT: false\n      # 忽略无法转换的对象\n      fail_on_empty_beans: false\n    deserialization:\n      # 允许对象忽略json中不存在的属性\n      fail_on_unknown_properties: false\n  cloud:\n    nacos:\n      discovery:\n        metadata:\n          # admin 监控账号密码\n          username: ruoyi\n          userpassword: 123456\n    # sentinel 配置\n    sentinel:\n      # sentinel 开关\n      enabled: true\n      transport:\n        # dashboard控制台服务名 用于服务发现\n        # 如无此配置将默认使用下方 dashboard 配置直接注册\n        server-name: ruoyi-sentinel-dashboard\n        # 客户端指定注册的ip 用于多网卡ip不稳点使用\n        # client-ip:\n        # 控制台地址 从1.3.0开始使用 server-name 注册\n        # dashboard: localhost:8718\n\n    bus:\n      id: ${spring.application.name}\n      base-packages: org.dromara.**.event\n  # 消息总线 也可以使用 kafka 参考 spring-cloud-bus 用法\n  rabbitmq:\n    host: localhost\n    port: 5672\n    username: ruoyi\n    password: ruoyi123\n\n  # redis通用配置 子服务可以自行配置进行覆盖\n  data:\n    redis:\n      host: localhost\n      port: 6379\n      # redis 密码必须配置\n      password: ruoyi123\n      database: 0\n      # 需要使用数字\n      timeout: 10000\n      ssl.enabled: false\n\n# redisson 配置\nredisson:\n  # redis key前缀\n  keyPrefix:\n  # 线程池数量\n  threads: 4\n  # Netty线程池数量\n  nettyThreads: 8\n  # 单节点配置\n  singleServerConfig:\n    # 客户端名称\n    clientName: ${spring.application.name}\n    # 最小空闲连接数\n    connectionMinimumIdleSize: 8\n    # 连接池大小\n    connectionPoolSize: 32\n    # 连接空闲超时，单位：毫秒\n    idleConnectionTimeout: 10000\n    # 命令等待超时，单位：毫秒\n    timeout: 3000\n    # 发布和订阅连接池大小\n    subscriptionConnectionPoolSize: 50\n\n# 分布式锁 lock4j 全局配置\nlock4j:\n  # 获取分布式锁超时时间，默认为 3000 毫秒\n  acquire-timeout: 3000\n  # 分布式锁的超时时间，默认为 30 秒\n  expire: 30000\n\n# 暴露监控端点\nmanagement:\n  endpoints:\n    web:\n      exposure:\n        include: \'*\'\n  endpoint:\n    health:\n      show-details: ALWAYS\n    logfile:\n      external-file: ./logs/${spring.application.name}/console.log\n\n# 日志配置\nlogging:\n  level:\n    org.springframework: warn\n    org.apache.dubbo: warn\n    com.alibaba.nacos: warn\n    com.alibaba.cloud.sentinel: warn\n    org.mybatis.spring.mapper: error\n    org.apache.dubbo.config: error\n    # 临时处理 spring 调整日志级别导致启动警告问题 不影响使用等待 alibaba 适配\n    org.springframework.context.support.PostProcessorRegistrationDelegate: error\n  config: classpath:logback-plus.xml\n\n# Sa-Token配置\nsa-token:\n  # token名称 (同时也是cookie名称)\n  token-name: Authorization\n  # 开启内网服务调用鉴权(不允许越过gateway访问内网服务 保障服务安全)\n  check-same-token: true\n  # 是否允许同一账号并发登录 (为true时允许一起登录, 为false时新登录挤掉旧登录)\n  is-concurrent: true\n  # 在多人登录同一账号时，是否共用一个token (为true时所有登录共用一个token, 为false时每次登录新建一个token)\n  is-share: false\n  # jwt秘钥\n  jwt-secret-key: abcdefghijklmnopqrstuvwxyz\n\n# MyBatisPlus配置\n# https://baomidou.com/config/\nmybatis-plus:\n  # 多包名使用 例如 org.dromara.**.mapper,org.xxx.**.mapper\n  mapperPackage: org.dromara.**.mapper\n  # 对应的 XML 文件位置\n  mapperLocations: classpath*:mapper/**/*Mapper.xml\n  # 实体扫描，多个package用逗号或者分号分隔\n  typeAliasesPackage: org.dromara.**.domain\n  global-config:\n    dbConfig:\n      # 主键类型\n      # AUTO 自增 NONE 空 INPUT 用户输入 ASSIGN_ID 雪花 ASSIGN_UUID 唯一 UUID\n      # 如需改为自增 需要将数据库表全部设置为自增\n      idType: ASSIGN_ID\n\n# 数据加密\nmybatis-encryptor:\n  # 是否开启加密\n  enable: false\n  # 默认加密算法\n  algorithm: BASE64\n  # 编码方式 BASE64/HEX。默认BASE64\n  encode: BASE64\n  # 安全秘钥 对称算法的秘钥 如：AES，SM4\n  password:\n  # 公私钥 非对称算法的公私钥 如：SM2，RSA\n  publicKey:\n  privateKey:\n\n# api接口加密\napi-decrypt:\n  # 是否开启全局接口加密\n  enabled: true\n  # AES 加密头标识\n  headerFlag: encrypt-key\n  # 响应加密公钥 非对称算法的公私钥 如：SM2，RSA 使用者请自行更换\n  # 对应前端解密私钥 MIIBVAIBADANBgkqhkiG9w0BAQEFAASCAT4wggE6AgEAAkEAmc3CuPiGL/LcIIm7zryCEIbl1SPzBkr75E2VMtxegyZ1lYRD+7TZGAPkvIsBcaMs6Nsy0L78n2qh+lIZMpLH8wIDAQABAkEAk82Mhz0tlv6IVCyIcw/s3f0E+WLmtPFyR9/WtV3Y5aaejUkU60JpX4m5xNR2VaqOLTZAYjW8Wy0aXr3zYIhhQQIhAMfqR9oFdYw1J9SsNc+CrhugAvKTi0+BF6VoL6psWhvbAiEAxPPNTmrkmrXwdm/pQQu3UOQmc2vCZ5tiKpW10CgJi8kCIFGkL6utxw93Ncj4exE/gPLvKcT+1Emnoox+O9kRXss5AiAMtYLJDaLEzPrAWcZeeSgSIzbL+ecokmFKSDDcRske6QIgSMkHedwND1olF8vlKsJUGK3BcdtM8w4Xq7BpSBwsloE=\n  publicKey: MFwwDQYJKoZIhvcNAQEBBQADSwAwSAJBAJnNwrj4hi/y3CCJu868ghCG5dUj8wZK++RNlTLcXoMmdZWEQ/u02RgD5LyLAXGjLOjbMtC+/J9qofpSGTKSx/MCAwEAAQ==\n  # 请求解密私钥 非对称算法的公私钥 如：SM2，RSA 使用者请自行更换\n  # 对应前端加密公钥 MFwwDQYJKoZIhvcNAQEBBQADSwAwSAJBAKoR8mX0rGKLqzcWmOzbfj64K8ZIgOdHnzkXSOVOZbFu/TJhZ7rFAN+eaGkl3C4buccQd/EjEsj9ir7ijT7h96MCAwEAAQ==\n  privateKey: MIIBVAIBADANBgkqhkiG9w0BAQEFAASCAT4wggE6AgEAAkEAqhHyZfSsYourNxaY7Nt+PrgrxkiA50efORdI5U5lsW79MmFnusUA355oaSXcLhu5xxB38SMSyP2KvuKNPuH3owIDAQABAkAfoiLyL+Z4lf4Myxk6xUDgLaWGximj20CUf+5BKKnlrK+Ed8gAkM0HqoTt2UZwA5E2MzS4EI2gjfQhz5X28uqxAiEA3wNFxfrCZlSZHb0gn2zDpWowcSxQAgiCstxGUoOqlW8CIQDDOerGKH5OmCJ4Z21v+F25WaHYPxCFMvwxpcw99EcvDQIgIdhDTIqD2jfYjPTY8Jj3EDGPbH2HHuffvflECt3Ek60CIQCFRlCkHpi7hthhYhovyloRYsM+IS9h/0BzlEAuO0ktMQIgSPT3aFAgJYwKpqRYKlLDVcflZFCKY7u3UP8iWi1Qw0Y=\n\n# 防止XSS攻击\nxss:\n  enabled: true\n  excludeUrls:\n    - /system/notice\n    - /workflow/model/save\n    - /workflow/model/editModelXml\n\n# 接口文档配置\nspringdoc:\n  api-docs:\n    # 是否开启接口文档\n    enabled: true\n#  swagger-ui:\n#    # 持久化认证数据\n#    persistAuthorization: true\n  info:\n    # 标题\n    title: \'标题：RuoYi-Cloud-Plus微服务权限管理系统_接口文档\'\n    # 描述\n    description: \'描述：微服务权限管理系统, 具体包括XXX,XXX模块...\'\n    # 版本\n    version: \'版本号：系统版本...\'\n    # 作者信息\n    contact:\n      name: Lion Li\n      email: crazylionli@163.com\n      url: https://gitee.com/dromara/RuoYi-Cloud-Plus\n  components:\n    # 鉴权方式配置\n    security-schemes:\n      apiKey:\n        type: APIKEY\n        in: HEADER\n        name: ${sa-token.token-name}\n\n# seata配置\nseata:\n  # 是否启用\n  enabled: true\n  # Seata 应用编号，默认为应用名\n  application-id: ${spring.application.name}\n  # Seata 事务组编号，用于 TC 集群名\n  tx-service-group: ${spring.application.name}-group\n\n# 多租户配置\ntenant:\n  # 是否开启\n  enable: true\n  # 排除表\n  excludes:\n    - sys_menu\n    - sys_tenant\n    - sys_tenant_package\n    - sys_role_dept\n    - sys_role_menu\n    - sys_user_post\n    - sys_user_role\n    - sys_client\n    - sys_oss_config\n','d7acbd0d6eb76c7ec59bce6216882c18','2024-12-04 18:10:15','2024-12-04 10:10:15','nacos','192.168.1.19','U','dev',''),(1,18,'application-common.yml','DEFAULT_GROUP','','server:\n  # undertow 配置\n  undertow:\n    # HTTP post内容的最大大小。当值为-1时，默认值为大小是无限的\n    max-http-post-size: -1\n    # 以下的配置会影响buffer,这些buffer会用于服务器连接的IO操作,有点类似netty的池化内存管理\n    # 每块buffer的空间大小,越小的空间被利用越充分\n    buffer-size: 512\n    # 是否分配的直接内存\n    direct-buffers: true\n    threads:\n      # 设置IO线程数, 它主要执行非阻塞的任务,它们会负责多个连接, 默认设置每个CPU核心一个线程\n      io: 8\n      # 阻塞任务线程池, 当执行类似servlet请求阻塞操作, undertow会从这个线程池中取得线程,它的值设置取决于系统的负载\n      worker: 256\n\ndubbo:\n  application:\n    # 关闭qos端口避免单机多生产者端口冲突 如需使用自行开启\n    qos-enable: false\n  protocol:\n    # 如需使用 Triple 3.0 新协议 可查看官方文档\n    # 使用 dubbo 协议通信\n    name: dubbo\n    # dubbo 协议端口(-1表示自增端口,从20880开始)\n    port: -1\n    # 指定dubbo协议注册ip\n    # host: 192.168.0.100\n  # 消费者相关配置\n  consumer:\n    # 超时时间\n    timeout: 3000\n  # 自定义配置\n  custom:\n    # 全局请求log\n    request-log: true\n    # info 基础信息 param 参数信息 full 全部\n    log-level: info\n\nspring:\n  threads:\n    # 开启虚拟线程 仅jdk21可用\n    virtual:\n      enabled: false\n  # 资源信息\n  messages:\n    # 国际化资源文件路径\n    basename: i18n/messages\n  servlet:\n    multipart:\n      # 整个请求大小限制\n      max-request-size: 20MB\n      # 上传单个文件大小限制\n      max-file-size: 10MB\n  mvc:\n    # 设置静态资源路径 防止所有请求都去查静态资源\n    static-path-pattern: /static/**\n    format:\n      date-time: yyyy-MM-dd HH:mm:ss\n  #jackson配置\n  jackson:\n    # 日期格式化\n    date-format: yyyy-MM-dd HH:mm:ss\n    serialization:\n      # 格式化输出\n      INDENT_OUTPUT: false\n      # 忽略无法转换的对象\n      fail_on_empty_beans: false\n    deserialization:\n      # 允许对象忽略json中不存在的属性\n      fail_on_unknown_properties: false\n  cloud:\n    nacos:\n      discovery:\n        metadata:\n          # admin 监控账号密码\n          username: ruoyi\n          userpassword: 123456\n    # sentinel 配置\n    sentinel:\n      # sentinel 开关\n      enabled: true\n      transport:\n        # dashboard控制台服务名 用于服务发现\n        # 如无此配置将默认使用下方 dashboard 配置直接注册\n        server-name: ruoyi-sentinel-dashboard\n        # 客户端指定注册的ip 用于多网卡ip不稳点使用\n        # client-ip:\n        # 控制台地址 从1.3.0开始使用 server-name 注册\n        # dashboard: localhost:8718\n\n    bus:\n      id: ${spring.application.name}\n      base-packages: org.dromara.**.event\n  # 消息总线 也可以使用 kafka 参考 spring-cloud-bus 用法\n  rabbitmq:\n    host: localhost\n    port: 5672\n    username: ruoyi\n    password: ruoyi123\n\n  # redis通用配置 子服务可以自行配置进行覆盖\n  data:\n    redis:\n      host: localhost\n      port: 6379\n      # redis 密码必须配置\n      password: ruoyi123\n      database: 0\n      # 需要使用数字\n      timeout: 10000\n      ssl.enabled: false\n\n# redisson 配置\nredisson:\n  # redis key前缀\n  keyPrefix:\n  # 线程池数量\n  threads: 4\n  # Netty线程池数量\n  nettyThreads: 8\n  # 单节点配置\n  singleServerConfig:\n    # 客户端名称\n    clientName: ${spring.application.name}\n    # 最小空闲连接数\n    connectionMinimumIdleSize: 8\n    # 连接池大小\n    connectionPoolSize: 32\n    # 连接空闲超时，单位：毫秒\n    idleConnectionTimeout: 10000\n    # 命令等待超时，单位：毫秒\n    timeout: 3000\n    # 发布和订阅连接池大小\n    subscriptionConnectionPoolSize: 50\n\n# 分布式锁 lock4j 全局配置\nlock4j:\n  # 获取分布式锁超时时间，默认为 3000 毫秒\n  acquire-timeout: 3000\n  # 分布式锁的超时时间，默认为 30 秒\n  expire: 30000\n\n# 暴露监控端点\nmanagement:\n  endpoints:\n    web:\n      exposure:\n        include: \'*\'\n  endpoint:\n    health:\n      show-details: ALWAYS\n    logfile:\n      external-file: ./logs/${spring.application.name}/console.log\n\n# 日志配置\nlogging:\n  level:\n    org.springframework: warn\n    org.apache.dubbo: warn\n    com.alibaba.nacos: warn\n    com.alibaba.cloud.sentinel: warn\n    org.mybatis.spring.mapper: error\n    org.apache.dubbo.config: error\n    # 临时处理 spring 调整日志级别导致启动警告问题 不影响使用等待 alibaba 适配\n    org.springframework.context.support.PostProcessorRegistrationDelegate: error\n  config: classpath:logback-plus.xml\n\n# Sa-Token配置\nsa-token:\n  # token名称 (同时也是cookie名称)\n  token-name: Authorization\n  # 开启内网服务调用鉴权(不允许越过gateway访问内网服务 保障服务安全)\n  check-same-token: true\n  # 是否允许同一账号并发登录 (为true时允许一起登录, 为false时新登录挤掉旧登录)\n  is-concurrent: true\n  # 在多人登录同一账号时，是否共用一个token (为true时所有登录共用一个token, 为false时每次登录新建一个token)\n  is-share: false\n  # jwt秘钥\n  jwt-secret-key: abcdefghijklmnopqrstuvwxyz\n\n# MyBatisPlus配置\n# https://baomidou.com/config/\nmybatis-plus:\n  # 多包名使用 例如 org.dromara.**.mapper,org.xxx.**.mapper\n  mapperPackage: org.dromara.**.mapper\n  # 对应的 XML 文件位置\n  mapperLocations: classpath*:mapper/**/*Mapper.xml\n  # 实体扫描，多个package用逗号或者分号分隔\n  typeAliasesPackage: org.dromara.**.domain\n  global-config:\n    dbConfig:\n      # 主键类型\n      # AUTO 自增 NONE 空 INPUT 用户输入 ASSIGN_ID 雪花 ASSIGN_UUID 唯一 UUID\n      # 如需改为自增 需要将数据库表全部设置为自增\n      idType: ASSIGN_ID\n\n# 数据加密\nmybatis-encryptor:\n  # 是否开启加密\n  enable: false\n  # 默认加密算法\n  algorithm: BASE64\n  # 编码方式 BASE64/HEX。默认BASE64\n  encode: BASE64\n  # 安全秘钥 对称算法的秘钥 如：AES，SM4\n  password:\n  # 公私钥 非对称算法的公私钥 如：SM2，RSA\n  publicKey:\n  privateKey:\n\n# api接口加密\napi-decrypt:\n  # 是否开启全局接口加密\n  enabled: true\n  # AES 加密头标识\n  headerFlag: encrypt-key\n  # 响应加密公钥 非对称算法的公私钥 如：SM2，RSA 使用者请自行更换\n  # 对应前端解密私钥 MIIBVAIBADANBgkqhkiG9w0BAQEFAASCAT4wggE6AgEAAkEAmc3CuPiGL/LcIIm7zryCEIbl1SPzBkr75E2VMtxegyZ1lYRD+7TZGAPkvIsBcaMs6Nsy0L78n2qh+lIZMpLH8wIDAQABAkEAk82Mhz0tlv6IVCyIcw/s3f0E+WLmtPFyR9/WtV3Y5aaejUkU60JpX4m5xNR2VaqOLTZAYjW8Wy0aXr3zYIhhQQIhAMfqR9oFdYw1J9SsNc+CrhugAvKTi0+BF6VoL6psWhvbAiEAxPPNTmrkmrXwdm/pQQu3UOQmc2vCZ5tiKpW10CgJi8kCIFGkL6utxw93Ncj4exE/gPLvKcT+1Emnoox+O9kRXss5AiAMtYLJDaLEzPrAWcZeeSgSIzbL+ecokmFKSDDcRske6QIgSMkHedwND1olF8vlKsJUGK3BcdtM8w4Xq7BpSBwsloE=\n  publicKey: MFwwDQYJKoZIhvcNAQEBBQADSwAwSAJBAJnNwrj4hi/y3CCJu868ghCG5dUj8wZK++RNlTLcXoMmdZWEQ/u02RgD5LyLAXGjLOjbMtC+/J9qofpSGTKSx/MCAwEAAQ==\n  # 请求解密私钥 非对称算法的公私钥 如：SM2，RSA 使用者请自行更换\n  # 对应前端加密公钥 MFwwDQYJKoZIhvcNAQEBBQADSwAwSAJBAKoR8mX0rGKLqzcWmOzbfj64K8ZIgOdHnzkXSOVOZbFu/TJhZ7rFAN+eaGkl3C4buccQd/EjEsj9ir7ijT7h96MCAwEAAQ==\n  privateKey: MIIBVAIBADANBgkqhkiG9w0BAQEFAASCAT4wggE6AgEAAkEAqhHyZfSsYourNxaY7Nt+PrgrxkiA50efORdI5U5lsW79MmFnusUA355oaSXcLhu5xxB38SMSyP2KvuKNPuH3owIDAQABAkAfoiLyL+Z4lf4Myxk6xUDgLaWGximj20CUf+5BKKnlrK+Ed8gAkM0HqoTt2UZwA5E2MzS4EI2gjfQhz5X28uqxAiEA3wNFxfrCZlSZHb0gn2zDpWowcSxQAgiCstxGUoOqlW8CIQDDOerGKH5OmCJ4Z21v+F25WaHYPxCFMvwxpcw99EcvDQIgIdhDTIqD2jfYjPTY8Jj3EDGPbH2HHuffvflECt3Ek60CIQCFRlCkHpi7hthhYhovyloRYsM+IS9h/0BzlEAuO0ktMQIgSPT3aFAgJYwKpqRYKlLDVcflZFCKY7u3UP8iWi1Qw0Y=\n\n# 防止XSS攻击\nxss:\n  enabled: true\n  excludeUrls:\n    - /system/notice\n    - /workflow/model/save\n    - /workflow/model/editModelXml\n\n# 接口文档配置\nspringdoc:\n  api-docs:\n    # 是否开启接口文档\n    enabled: true\n#  swagger-ui:\n#    # 持久化认证数据\n#    persistAuthorization: true\n  info:\n    # 标题\n    title: \'标题：RuoYi-Cloud-Plus微服务权限管理系统_接口文档\'\n    # 描述\n    description: \'描述：微服务权限管理系统, 具体包括XXX,XXX模块...\'\n    # 版本\n    version: \'版本号：系统版本...\'\n    # 作者信息\n    contact:\n      name: Lion Li\n      email: crazylionli@163.com\n      url: https://gitee.com/dromara/RuoYi-Cloud-Plus\n  components:\n    # 鉴权方式配置\n    security-schemes:\n      apiKey:\n        type: APIKEY\n        in: HEADER\n        name: ${sa-token.token-name}\n\n# seata配置\nseata:\n  # 是否启用\n  enabled: false\n  # Seata 应用编号，默认为应用名\n  application-id: ${spring.application.name}\n  # Seata 事务组编号，用于 TC 集群名\n  tx-service-group: ${spring.application.name}-group\n\n# 多租户配置\ntenant:\n  # 是否开启\n  enable: true\n  # 排除表\n  excludes:\n    - sys_menu\n    - sys_tenant\n    - sys_tenant_package\n    - sys_role_dept\n    - sys_role_menu\n    - sys_user_post\n    - sys_user_role\n    - sys_client\n    - sys_oss_config\n','cfd7100fcd375165ac78fb2c736b381c','2024-12-05 12:15:04','2024-12-05 04:15:06','nacos','192.168.1.19','U','dev',''),(2,19,'datasource.yml','DEFAULT_GROUP','','datasource:\n  system-master:\n    # jdbc 所有参数配置参考 https://lionli.blog.csdn.net/article/details/122018562\n    # rewriteBatchedStatements=true 批处理优化 大幅提升批量插入更新删除性能\n    url: jdbc:mysql://localhost:3306/ry-cloud?useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&useSSL=true&serverTimezone=GMT%2B8&rewriteBatchedStatements=true&allowPublicKeyRetrieval=true\n    username: root\n    password: ruoyi123\n  gen:\n    url: jdbc:mysql://localhost:3306/ry-cloud?useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&useSSL=true&serverTimezone=GMT%2B8&rewriteBatchedStatements=true&allowPublicKeyRetrieval=true\n    username: root\n    password: ruoyi123\n  job:\n    url: jdbc:mysql://localhost:3306/ry-job?useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&useSSL=true&serverTimezone=GMT%2B8&rewriteBatchedStatements=true&allowPublicKeyRetrieval=true\n    username: root\n    password: ruoyi123\n  workflow:\n    url: jdbc:mysql://localhost:3306/ry-workflow?useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&useSSL=true&serverTimezone=GMT%2B8&rewriteBatchedStatements=true&allowPublicKeyRetrieval=true&nullCatalogMeansCurrent=true\n    username: root\n    password: ruoyi123\n#  system-oracle:\n#    url: jdbc:oracle:thin:@//localhost:1521/XE\n#    username: ROOT\n#    password: password\n#  system-postgres:\n#    url: jdbc:postgresql://localhost:5432/postgres?useUnicode=true&characterEncoding=utf8&useSSL=true&autoReconnect=true&reWriteBatchedInserts=true\n#    username: root\n#    password: password\n\nspring:\n  datasource:\n    type: com.zaxxer.hikari.HikariDataSource\n    # 动态数据源文档 https://www.kancloud.cn/tracy5546/dynamic-datasource/content\n    dynamic:\n      # 性能分析插件(有性能损耗 不建议生产环境使用)\n      p6spy: true\n      # 开启seata代理，开启后默认每个数据源都代理，如果某个不需要代理可单独关闭\n      seata: ${seata.enabled}\n      # 严格模式 匹配不到数据源则报错\n      strict: true\n      hikari:\n        # 最大连接池数量\n        maxPoolSize: 20\n        # 最小空闲线程数量\n        minIdle: 10\n        # 配置获取连接等待超时的时间\n        connectionTimeout: 30000\n        # 校验超时时间\n        validationTimeout: 5000\n        # 空闲连接存活最大时间，默认10分钟\n        idleTimeout: 600000\n        # 此属性控制池中连接的最长生命周期，值0表示无限生命周期，默认30分钟\n        maxLifetime: 1800000\n        # 多久检查一次连接的活性\n        keepaliveTime: 30000\n','0d5c7e0ca73f01ecbdd52708a805169d','2024-12-05 12:18:57','2024-12-05 04:18:57','nacos','192.168.1.19','U','dev',''),(3,20,'ruoyi-gateway.yml','DEFAULT_GROUP','','# 安全配置\nsecurity:\n  # 不校验白名单\n  ignore:\n    whites:\n      - /auth/code\n      - /auth/logout\n      - /auth/login\n      - /auth/binding/*\n      - /auth/social/callback\n      - /auth/register\n      - /auth/tenant/list\n      - /resource/sms/code\n      - /resource/sse/close\n      - /*/v3/api-docs\n      - /*/error\n      - /csrf\n\nspring:\n  cloud:\n    # 网关配置\n    gateway:\n      # 打印请求日志(自定义)\n      requestLog: true\n      discovery:\n        locator:\n          lowerCaseServiceId: true\n          enabled: true\n      routes:\n        # 认证中心\n        - id: ruoyi-auth\n          uri: lb://ruoyi-auth\n          predicates:\n            - Path=/auth/**\n          filters:\n            - StripPrefix=1\n        # 代码生成\n        - id: ruoyi-gen\n          uri: lb://ruoyi-gen\n          predicates:\n            - Path=/tool/**\n          filters:\n            - StripPrefix=1\n        # 系统模块\n        - id: ruoyi-system\n          uri: lb://ruoyi-system\n          predicates:\n            - Path=/system/**,/monitor/**\n          filters:\n            - StripPrefix=1\n        # 资源服务\n        - id: ruoyi-resource\n          uri: lb://ruoyi-resource\n          predicates:\n            - Path=/resource/**\n          filters:\n            - StripPrefix=1\n        # workflow服务\n        - id: ruoyi-workflow\n          uri: lb://ruoyi-workflow\n          predicates:\n            - Path=/workflow/**\n          filters:\n            - StripPrefix=1\n        # 演示服务\n        - id: ruoyi-demo\n          uri: lb://ruoyi-demo\n          predicates:\n            - Path=/demo/**\n          filters:\n            - StripPrefix=1\n        # MQ演示服务\n        - id: ruoyi-test-mq\n          uri: lb://ruoyi-test-mq\n          predicates:\n            - Path=/test-mq/**\n          filters:\n            - StripPrefix=1\n\n    # sentinel 配置\n    sentinel:\n      filter:\n        enabled: false\n      # nacos配置持久化\n      datasource:\n        ds1:\n          nacos:\n            server-addr: ${spring.cloud.nacos.server-addr}\n            dataId: sentinel-${spring.application.name}.json\n            groupId: ${spring.cloud.nacos.config.group}\n            username: ${spring.cloud.nacos.username}\n            password: ${spring.cloud.nacos.password}\n            namespace: ${spring.profiles.active}\n            data-type: json\n            rule-type: gw-flow\n','304d094bc91db51befa7783b1976ff63','2024-12-05 14:40:24','2024-12-05 06:40:25','nacos','192.168.1.19','U','dev',''),(7,21,'ruoyi-gen.yml','DEFAULT_GROUP','','spring:\n  datasource:\n    dynamic:\n      # 设置默认的数据源或者数据源组,默认值即为 master\n      primary: master\n      seata: false\n      datasource:\n        # 主库数据源\n        master:\n          type: ${spring.datasource.type}\n          driver-class-name: com.mysql.cj.jdbc.Driver\n          url: ${datasource.system-master.url}\n          username: ${datasource.system-master.username}\n          password: ${datasource.system-master.password}\n#        oracle:\n#          type: ${spring.datasource.type}\n#          driverClassName: oracle.jdbc.OracleDriver\n#          url: ${datasource.system-oracle.url}\n#          username: ${datasource.system-oracle.username}\n#          password: ${datasource.system-oracle.password}\n#        postgres:\n#          type: ${spring.datasource.type}\n#          driverClassName: org.postgresql.Driver\n#          url: ${datasource.system-postgres.url}\n#          username: ${datasource.system-postgres.username}\n#          password: ${datasource.system-postgres.password}\n\n# 代码生成\ngen:\n  # 作者\n  author: LionLi\n  # 默认生成包路径 system 需改成自己的模块名称 如 system monitor tool\n  packageName: org.dromara.system\n  # 自动去除表前缀，默认是false\n  autoRemovePre: false\n  # 表前缀（生成类名不会包含表前缀，多个用逗号分隔）\n  tablePrefix: sys_\n','2d00f0362d71287c0033b665a48337af','2024-12-05 14:40:54','2024-12-05 06:40:55','nacos','192.168.1.19','U','dev',''),(8,22,'ruoyi-job.yml','DEFAULT_GROUP','','spring:\n  datasource:\n    dynamic:\n      # 设置默认的数据源或者数据源组,默认值即为 master\n      primary: master\n      seata: false\n      datasource:\n        # 主库数据源\n        master:\n          type: ${spring.datasource.type}\n          driver-class-name: com.mysql.cj.jdbc.Driver\n          url: ${datasource.job.url}\n          username: ${datasource.job.username}\n          password: ${datasource.job.password}\n\nsnail-job:\n  enabled: true\n  # 需要在 SnailJob 后台组管理创建对应名称的组,然后创建任务的时候选择对应的组,才能正确分派任务\n  group: \"ruoyi_group\"\n  #  SnailJob 接入验证令牌\n  token: \"SJ_cKqBTPzCsWA3VyuCfFoccmuIEGXjr5KT\"\n  server:\n    # 从 nacos 获取服务\n    server-name: ruoyi-snailjob-server\n    # 服务名优先 ip垫底\n    host: 127.0.0.1\n    port: 17888\n  # 详见 script/sql/ry_job.sql `sj_namespace` 表\n  namespace: ${spring.profiles.active}\n  # 随主应用端口飘逸\n  port: 2${server.port}\n  # 客户端ip指定\n  host:\n','08f5e690ce18e8497d69f7a1018efea3','2024-12-05 14:41:14','2024-12-05 06:41:14','nacos','192.168.1.19','U','dev',''),(101,23,'application-common.yml','DEFAULT_GROUP','','# 将项目路径：config/下对应文件中内容复制到此处','2944a25cb97926efcaa43b3ad7a64cf0','2024-12-05 15:05:37','2024-12-05 07:05:37','nacos','192.168.1.19','D','prod',''),(102,24,'datasource.yml','DEFAULT_GROUP','','# 将项目路径：config/下对应文件中内容复制到此处','2944a25cb97926efcaa43b3ad7a64cf0','2024-12-05 15:05:37','2024-12-05 07:05:37','nacos','192.168.1.19','D','prod',''),(103,25,'ruoyi-gateway.yml','DEFAULT_GROUP','','# 将项目路径：config/下对应文件中内容复制到此处','2944a25cb97926efcaa43b3ad7a64cf0','2024-12-05 15:05:37','2024-12-05 07:05:37','nacos','192.168.1.19','D','prod',''),(104,26,'ruoyi-auth.yml','DEFAULT_GROUP','','# 将项目路径：config/下对应文件中内容复制到此处','2944a25cb97926efcaa43b3ad7a64cf0','2024-12-05 15:05:37','2024-12-05 07:05:37','nacos','192.168.1.19','D','prod',''),(105,27,'ruoyi-monitor.yml','DEFAULT_GROUP','','# 将项目路径：config/下对应文件中内容复制到此处','2944a25cb97926efcaa43b3ad7a64cf0','2024-12-05 15:05:37','2024-12-05 07:05:37','nacos','192.168.1.19','D','prod',''),(106,28,'ruoyi-system.yml','DEFAULT_GROUP','','# 将项目路径：config/下对应文件中内容复制到此处','2944a25cb97926efcaa43b3ad7a64cf0','2024-12-05 15:05:37','2024-12-05 07:05:37','nacos','192.168.1.19','D','prod',''),(107,29,'ruoyi-gen.yml','DEFAULT_GROUP','','# 将项目路径：config/下对应文件中内容复制到此处','2944a25cb97926efcaa43b3ad7a64cf0','2024-12-05 15:05:37','2024-12-05 07:05:37','nacos','192.168.1.19','D','prod',''),(108,30,'ruoyi-job.yml','DEFAULT_GROUP','','# 将项目路径：config/下对应文件中内容复制到此处','2944a25cb97926efcaa43b3ad7a64cf0','2024-12-05 15:05:37','2024-12-05 07:05:37','nacos','192.168.1.19','D','prod',''),(109,31,'ruoyi-resource.yml','DEFAULT_GROUP','','# 将项目路径：config/下对应文件中内容复制到此处','2944a25cb97926efcaa43b3ad7a64cf0','2024-12-05 15:05:37','2024-12-05 07:05:37','nacos','192.168.1.19','D','prod',''),(110,32,'ruoyi-workflow.yml','DEFAULT_GROUP','','# 将项目路径：config/下对应文件中内容复制到此处','2944a25cb97926efcaa43b3ad7a64cf0','2024-12-05 15:05:37','2024-12-05 07:05:37','nacos','192.168.1.19','D','prod',''),(111,33,'sentinel-ruoyi-gateway.json','DEFAULT_GROUP','','# 将项目路径：config/下对应文件中内容复制到此处','2944a25cb97926efcaa43b3ad7a64cf0','2024-12-05 15:05:40','2024-12-05 07:05:40','nacos','192.168.1.19','D','prod',''),(112,34,'seata-server.properties','DEFAULT_GROUP','','# 将项目路径：config/下对应文件中内容复制到此处','2944a25cb97926efcaa43b3ad7a64cf0','2024-12-05 15:05:40','2024-12-05 07:05:40','nacos','192.168.1.19','D','prod',''),(113,35,'ruoyi-sentinel-dashboard.yml','DEFAULT_GROUP','','# 将项目路径：config/下对应文件中内容复制到此处','2944a25cb97926efcaa43b3ad7a64cf0','2024-12-05 15:05:40','2024-12-05 07:05:40','nacos','192.168.1.19','D','prod',''),(114,36,'ruoyi-snailjob-server.yml','DEFAULT_GROUP','','# 将项目路径：config/下对应文件中内容复制到此处','2944a25cb97926efcaa43b3ad7a64cf0','2024-12-05 15:05:40','2024-12-05 07:05:40','nacos','192.168.1.19','D','prod',''),(0,37,'application-common.yml','DEFAULT_GROUP','','server:\n  # undertow 配置\n  undertow:\n    # HTTP post内容的最大大小。当值为-1时，默认值为大小是无限的\n    max-http-post-size: -1\n    # 以下的配置会影响buffer,这些buffer会用于服务器连接的IO操作,有点类似netty的池化内存管理\n    # 每块buffer的空间大小,越小的空间被利用越充分\n    buffer-size: 512\n    # 是否分配的直接内存\n    direct-buffers: true\n    threads:\n      # 设置IO线程数, 它主要执行非阻塞的任务,它们会负责多个连接, 默认设置每个CPU核心一个线程\n      io: 8\n      # 阻塞任务线程池, 当执行类似servlet请求阻塞操作, undertow会从这个线程池中取得线程,它的值设置取决于系统的负载\n      worker: 256\n\ndubbo:\n  application:\n    # 关闭qos端口避免单机多生产者端口冲突 如需使用自行开启\n    qos-enable: false\n  protocol:\n    # 如需使用 Triple 3.0 新协议 可查看官方文档\n    # 使用 dubbo 协议通信\n    name: dubbo\n    # dubbo 协议端口(-1表示自增端口,从20880开始)\n    port: -1\n    # 指定dubbo协议注册ip\n    # host: 192.168.0.100\n  # 消费者相关配置\n  consumer:\n    # 超时时间\n    timeout: 3000\n  # 自定义配置\n  custom:\n    # 全局请求log\n    request-log: true\n    # info 基础信息 param 参数信息 full 全部\n    log-level: info\n\nspring:\n  threads:\n    # 开启虚拟线程 仅jdk21可用\n    virtual:\n      enabled: false\n  # 资源信息\n  messages:\n    # 国际化资源文件路径\n    basename: i18n/messages\n  servlet:\n    multipart:\n      # 整个请求大小限制\n      max-request-size: 20MB\n      # 上传单个文件大小限制\n      max-file-size: 10MB\n  mvc:\n    # 设置静态资源路径 防止所有请求都去查静态资源\n    static-path-pattern: /static/**\n    format:\n      date-time: yyyy-MM-dd HH:mm:ss\n  #jackson配置\n  jackson:\n    # 日期格式化\n    date-format: yyyy-MM-dd HH:mm:ss\n    serialization:\n      # 格式化输出\n      INDENT_OUTPUT: false\n      # 忽略无法转换的对象\n      fail_on_empty_beans: false\n    deserialization:\n      # 允许对象忽略json中不存在的属性\n      fail_on_unknown_properties: false\n  cloud:\n    nacos:\n      discovery:\n        metadata:\n          # admin 监控账号密码\n          username: ruoyi\n          userpassword: 123456\n    # sentinel 配置\n    sentinel:\n      # sentinel 开关\n      enabled: true\n      transport:\n        # dashboard控制台服务名 用于服务发现\n        # 如无此配置将默认使用下方 dashboard 配置直接注册\n        server-name: ruoyi-sentinel-dashboard\n        # 客户端指定注册的ip 用于多网卡ip不稳点使用\n        # client-ip:\n        # 控制台地址 从1.3.0开始使用 server-name 注册\n        # dashboard: localhost:8718\n\n    bus:\n      id: ${spring.application.name}\n      base-packages: com.cdzeroly.**.event\n  # 消息总线 也可以使用 kafka 参考 spring-cloud-bus 用法\n  rabbitmq:\n    host: localhost\n    port: 5672\n    username: ruoyi\n    password: ruoyi123\n\n  # redis通用配置 子服务可以自行配置进行覆盖\n  data:\n    redis:\n      host: localhost\n      port: 6379\n      # redis 密码必须配置\n      password: ruoyi123\n      database: 0\n      # 需要使用数字\n      timeout: 10000\n      ssl.enabled: false\n\n# redisson 配置\nredisson:\n  # redis key前缀\n  keyPrefix:\n  # 线程池数量\n  threads: 4\n  # Netty线程池数量\n  nettyThreads: 8\n  # 单节点配置\n  singleServerConfig:\n    # 客户端名称\n    clientName: ${spring.application.name}\n    # 最小空闲连接数\n    connectionMinimumIdleSize: 8\n    # 连接池大小\n    connectionPoolSize: 32\n    # 连接空闲超时，单位：毫秒\n    idleConnectionTimeout: 10000\n    # 命令等待超时，单位：毫秒\n    timeout: 3000\n    # 发布和订阅连接池大小\n    subscriptionConnectionPoolSize: 50\n\n# 分布式锁 lock4j 全局配置\nlock4j:\n  # 获取分布式锁超时时间，默认为 3000 毫秒\n  acquire-timeout: 3000\n  # 分布式锁的超时时间，默认为 30 秒\n  expire: 30000\n\n# 暴露监控端点\nmanagement:\n  endpoints:\n    web:\n      exposure:\n        include: \'*\'\n  endpoint:\n    health:\n      show-details: ALWAYS\n    logfile:\n      external-file: ./logs/${spring.application.name}/console.log\n\n# 日志配置\nlogging:\n  level:\n    org.springframework: warn\n    org.apache.dubbo: warn\n    com.alibaba.nacos: warn\n    com.alibaba.cloud.sentinel: warn\n    org.mybatis.spring.mapper: error\n    org.apache.dubbo.config: error\n    # 临时处理 spring 调整日志级别导致启动警告问题 不影响使用等待 alibaba 适配\n    org.springframework.context.support.PostProcessorRegistrationDelegate: error\n  config: classpath:logback-plus.xml\n\n# Sa-Token配置\nsa-token:\n  # token名称 (同时也是cookie名称)\n  token-name: Authorization\n  # 开启内网服务调用鉴权(不允许越过gateway访问内网服务 保障服务安全)\n  check-same-token: true\n  # 是否允许同一账号并发登录 (为true时允许一起登录, 为false时新登录挤掉旧登录)\n  is-concurrent: true\n  # 在多人登录同一账号时，是否共用一个token (为true时所有登录共用一个token, 为false时每次登录新建一个token)\n  is-share: false\n  # jwt秘钥\n  jwt-secret-key: abcdefghijklmnopqrstuvwxyz\n\n# MyBatisPlus配置\n# https://baomidou.com/config/\nmybatis-plus:\n  # 多包名使用 例如 com.cdzeroly.**.mapper,org.xxx.**.mapper\n  mapperPackage: com.cdzeroly.**.mapper\n  # 对应的 XML 文件位置\n  mapperLocations: classpath*:mapper/**/*Mapper.xml\n  # 实体扫描，多个package用逗号或者分号分隔\n  typeAliasesPackage: com.cdzeroly.**.domain\n  global-config:\n    dbConfig:\n      # 主键类型\n      # AUTO 自增 NONE 空 INPUT 用户输入 ASSIGN_ID 雪花 ASSIGN_UUID 唯一 UUID\n      # 如需改为自增 需要将数据库表全部设置为自增\n      idType: ASSIGN_ID\n\n# 数据加密\nmybatis-encryptor:\n  # 是否开启加密\n  enable: false\n  # 默认加密算法\n  algorithm: BASE64\n  # 编码方式 BASE64/HEX。默认BASE64\n  encode: BASE64\n  # 安全秘钥 对称算法的秘钥 如：AES，SM4\n  password:\n  # 公私钥 非对称算法的公私钥 如：SM2，RSA\n  publicKey:\n  privateKey:\n\n# api接口加密\napi-decrypt:\n  # 是否开启全局接口加密\n  enabled: true\n  # AES 加密头标识\n  headerFlag: encrypt-key\n  # 响应加密公钥 非对称算法的公私钥 如：SM2，RSA 使用者请自行更换\n  # 对应前端解密私钥 MIIBVAIBADANBgkqhkiG9w0BAQEFAASCAT4wggE6AgEAAkEAmc3CuPiGL/LcIIm7zryCEIbl1SPzBkr75E2VMtxegyZ1lYRD+7TZGAPkvIsBcaMs6Nsy0L78n2qh+lIZMpLH8wIDAQABAkEAk82Mhz0tlv6IVCyIcw/s3f0E+WLmtPFyR9/WtV3Y5aaejUkU60JpX4m5xNR2VaqOLTZAYjW8Wy0aXr3zYIhhQQIhAMfqR9oFdYw1J9SsNc+CrhugAvKTi0+BF6VoL6psWhvbAiEAxPPNTmrkmrXwdm/pQQu3UOQmc2vCZ5tiKpW10CgJi8kCIFGkL6utxw93Ncj4exE/gPLvKcT+1Emnoox+O9kRXss5AiAMtYLJDaLEzPrAWcZeeSgSIzbL+ecokmFKSDDcRske6QIgSMkHedwND1olF8vlKsJUGK3BcdtM8w4Xq7BpSBwsloE=\n  publicKey: MFwwDQYJKoZIhvcNAQEBBQADSwAwSAJBAJnNwrj4hi/y3CCJu868ghCG5dUj8wZK++RNlTLcXoMmdZWEQ/u02RgD5LyLAXGjLOjbMtC+/J9qofpSGTKSx/MCAwEAAQ==\n  # 请求解密私钥 非对称算法的公私钥 如：SM2，RSA 使用者请自行更换\n  # 对应前端加密公钥 MFwwDQYJKoZIhvcNAQEBBQADSwAwSAJBAKoR8mX0rGKLqzcWmOzbfj64K8ZIgOdHnzkXSOVOZbFu/TJhZ7rFAN+eaGkl3C4buccQd/EjEsj9ir7ijT7h96MCAwEAAQ==\n  privateKey: MIIBVAIBADANBgkqhkiG9w0BAQEFAASCAT4wggE6AgEAAkEAqhHyZfSsYourNxaY7Nt+PrgrxkiA50efORdI5U5lsW79MmFnusUA355oaSXcLhu5xxB38SMSyP2KvuKNPuH3owIDAQABAkAfoiLyL+Z4lf4Myxk6xUDgLaWGximj20CUf+5BKKnlrK+Ed8gAkM0HqoTt2UZwA5E2MzS4EI2gjfQhz5X28uqxAiEA3wNFxfrCZlSZHb0gn2zDpWowcSxQAgiCstxGUoOqlW8CIQDDOerGKH5OmCJ4Z21v+F25WaHYPxCFMvwxpcw99EcvDQIgIdhDTIqD2jfYjPTY8Jj3EDGPbH2HHuffvflECt3Ek60CIQCFRlCkHpi7hthhYhovyloRYsM+IS9h/0BzlEAuO0ktMQIgSPT3aFAgJYwKpqRYKlLDVcflZFCKY7u3UP8iWi1Qw0Y=\n\n# 防止XSS攻击\nxss:\n  enabled: true\n  excludeUrls:\n    - /system/notice\n    - /workflow/model/save\n    - /workflow/model/editModelXml\n\n# 接口文档配置\nspringdoc:\n  api-docs:\n    # 是否开启接口文档\n    enabled: true\n#  swagger-ui:\n#    # 持久化认证数据\n#    persistAuthorization: true\n  info:\n    # 标题\n    title: \'标题：RuoYi-Cloud-Plus微服务权限管理系统_接口文档\'\n    # 描述\n    description: \'描述：微服务权限管理系统, 具体包括XXX,XXX模块...\'\n    # 版本\n    version: \'版本号：系统版本...\'\n    # 作者信息\n    contact:\n      name: Lion Li\n      email: crazylionli@163.com\n      url: https://gitee.com/dromara/RuoYi-Cloud-Plus\n  components:\n    # 鉴权方式配置\n    security-schemes:\n      apiKey:\n        type: APIKEY\n        in: HEADER\n        name: ${sa-token.token-name}\n\n# seata配置\nseata:\n  # 是否启用\n  enabled: true\n  # Seata 应用编号，默认为应用名\n  application-id: ${spring.application.name}\n  # Seata 事务组编号，用于 TC 集群名\n  tx-service-group: ${spring.application.name}-group\n\n# 多租户配置\ntenant:\n  # 是否开启\n  enable: true\n  # 排除表\n  excludes:\n    - sys_menu\n    - sys_tenant\n    - sys_tenant_package\n    - sys_role_dept\n    - sys_role_menu\n    - sys_user_post\n    - sys_user_role\n    - sys_client\n    - sys_oss_config\n','9ce0cd9136de4b8f9c92fb37051f4bd4','2024-12-05 15:05:53','2024-12-05 07:05:53',NULL,'192.168.1.19','I','prod',''),(0,38,'datasource.yml','DEFAULT_GROUP','','datasource:\n  system-master:\n    # jdbc 所有参数配置参考 https://lionli.blog.csdn.net/article/details/122018562\n    # rewriteBatchedStatements=true 批处理优化 大幅提升批量插入更新删除性能\n    url: jdbc:mysql://localhost:3306/ry-cloud?useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&useSSL=true&serverTimezone=GMT%2B8&rewriteBatchedStatements=true&allowPublicKeyRetrieval=true\n    username: root\n    password: root\n  gen:\n    url: jdbc:mysql://localhost:3306/ry-cloud?useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&useSSL=true&serverTimezone=GMT%2B8&rewriteBatchedStatements=true&allowPublicKeyRetrieval=true\n    username: root\n    password: root\n  job:\n    url: jdbc:mysql://localhost:3306/ry-job?useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&useSSL=true&serverTimezone=GMT%2B8&rewriteBatchedStatements=true&allowPublicKeyRetrieval=true\n    username: root\n    password: root\n  workflow:\n    url: jdbc:mysql://localhost:3306/ry-workflow?useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&useSSL=true&serverTimezone=GMT%2B8&rewriteBatchedStatements=true&allowPublicKeyRetrieval=true&nullCatalogMeansCurrent=true\n    username: root\n    password: root\n#  system-oracle:\n#    url: jdbc:oracle:thin:@//localhost:1521/XE\n#    username: ROOT\n#    password: password\n#  system-postgres:\n#    url: jdbc:postgresql://localhost:5432/postgres?useUnicode=true&characterEncoding=utf8&useSSL=true&autoReconnect=true&reWriteBatchedInserts=true\n#    username: root\n#    password: password\n\nspring:\n  datasource:\n    type: com.zaxxer.hikari.HikariDataSource\n    # 动态数据源文档 https://www.kancloud.cn/tracy5546/dynamic-datasource/content\n    dynamic:\n      # 性能分析插件(有性能损耗 不建议生产环境使用)\n      p6spy: true\n      # 开启seata代理，开启后默认每个数据源都代理，如果某个不需要代理可单独关闭\n      seata: ${seata.enabled}\n      # 严格模式 匹配不到数据源则报错\n      strict: true\n      hikari:\n        # 最大连接池数量\n        maxPoolSize: 20\n        # 最小空闲线程数量\n        minIdle: 10\n        # 配置获取连接等待超时的时间\n        connectionTimeout: 30000\n        # 校验超时时间\n        validationTimeout: 5000\n        # 空闲连接存活最大时间，默认10分钟\n        idleTimeout: 600000\n        # 此属性控制池中连接的最长生命周期，值0表示无限生命周期，默认30分钟\n        maxLifetime: 1800000\n        # 多久检查一次连接的活性\n        keepaliveTime: 30000\n','ceb67fe7f6e6a92955d0ac164c3f8805','2024-12-05 15:05:53','2024-12-05 07:05:53',NULL,'192.168.1.19','I','prod',''),(0,39,'sentinel-ruoyi-gateway.json','DEFAULT_GROUP','','[\n  {\n    \"resource\": \"ruoyi-auth\",\n    \"count\": 500,\n    \"grade\": 1,\n    \"limitApp\": \"default\",\n    \"strategy\": 0,\n    \"controlBehavior\": 0\n  },\n  {\n    \"resource\": \"ruoyi-system\",\n    \"count\": 1000,\n    \"grade\": 1,\n    \"limitApp\": \"default\",\n    \"strategy\": 0,\n    \"controlBehavior\": 0\n  },\n  {\n    \"resource\": \"ruoyi-resource\",\n    \"count\": 500,\n    \"grade\": 1,\n    \"limitApp\": \"default\",\n    \"strategy\": 0,\n    \"controlBehavior\": 0\n  }\n]\n','2efad3702e99adef46c2ee818b3925e5','2024-12-05 15:05:53','2024-12-05 07:05:53',NULL,'192.168.1.19','I','prod',''),(0,40,'seata-server.properties','DEFAULT_GROUP','','service.vgroupMapping.ruoyi-auth-group=default\nservice.vgroupMapping.ruoyi-system-group=default\nservice.vgroupMapping.ruoyi-resource-group=default\nservice.vgroupMapping.ruoyi-workflow-group=default\n\nservice.enableDegrade=false\nservice.disableGlobalTransaction=false\n\n#Transaction storage configuration, only for the server. The file, DB, and redis configuration values are optional.\nstore.mode=db\nstore.lock.mode=db\nstore.session.mode=db\n#Used for password encryption\n#store.publicKey=\n\n#These configurations are required if the `store mode` is `db`. If `store.mode,store.lock.mode,store.session.mode` are not equal to `db`, you can remove the configuration block.\nstore.db.datasource=hikari\nstore.db.dbType=mysql\nstore.db.driverClassName=com.mysql.cj.jdbc.Driver\nstore.db.url=jdbc:mysql://127.0.0.1:3306/ry-seata?useUnicode=true&rewriteBatchedStatements=true&allowPublicKeyRetrieval=true\nstore.db.user=root\nstore.db.password=ruoyi123\nstore.db.minConn=5\nstore.db.maxConn=30\nstore.db.globalTable=global_table\nstore.db.branchTable=branch_table\nstore.db.distributedLockTable=distributed_lock\nstore.db.queryLimit=100\nstore.db.lockTable=lock_table\nstore.db.maxWait=5000\n\n# redis 模式 store.mode=redis 开启 (控制台查询功能有限,不影响实际执行功能)\n# store.redis.host=127.0.0.1\n# store.redis.port=6379\n# 最大连接数\n# store.redis.maxConn=10\n# 最小连接数\n# store.redis.minConn=1\n# store.redis.database=0\n# store.redis.password=\n# store.redis.queryLimit=100\n\n#Transaction rule configuration, only for the server\nserver.recovery.committingRetryPeriod=1000\nserver.recovery.asynCommittingRetryPeriod=1000\nserver.recovery.rollbackingRetryPeriod=1000\nserver.recovery.timeoutRetryPeriod=1000\nserver.maxCommitRetryTimeout=-1\nserver.maxRollbackRetryTimeout=-1\nserver.rollbackRetryTimeoutUnlockEnable=false\nserver.distributedLockExpireTime=10000\nserver.xaerNotaRetryTimeout=60000\nserver.session.branchAsyncQueueSize=5000\nserver.session.enableBranchAsyncRemove=false\n\n#Transaction rule configuration, only for the client\nclient.rm.asyncCommitBufferLimit=10000\nclient.rm.lock.retryInterval=10\nclient.rm.lock.retryTimes=30\nclient.rm.lock.retryPolicyBranchRollbackOnConflict=true\nclient.rm.reportRetryCount=5\nclient.rm.tableMetaCheckEnable=true\nclient.rm.tableMetaCheckerInterval=60000\nclient.rm.sqlParserType=druid\nclient.rm.reportSuccessEnable=false\nclient.rm.sagaBranchRegisterEnable=false\nclient.rm.sagaJsonParser=fastjson\nclient.rm.tccActionInterceptorOrder=-2147482648\nclient.tm.commitRetryCount=5\nclient.tm.rollbackRetryCount=5\nclient.tm.defaultGlobalTransactionTimeout=60000\nclient.tm.degradeCheck=false\nclient.tm.degradeCheckAllowTimes=10\nclient.tm.degradeCheckPeriod=2000\nclient.tm.interceptorOrder=-2147482648\nclient.undo.dataValidation=true\nclient.undo.logSerialization=jackson\nclient.undo.onlyCareUpdateColumns=true\nserver.undo.logSaveDays=7\nserver.undo.logDeletePeriod=86400000\nclient.undo.logTable=undo_log\nclient.undo.compress.enable=true\nclient.undo.compress.type=zip\nclient.undo.compress.threshold=64k\n\n#For TCC transaction mode\ntcc.fence.logTableName=tcc_fence_log\ntcc.fence.cleanPeriod=1h\n\n#Log rule configuration, for client and server\nlog.exceptionRate=100\n\n#Metrics configuration, only for the server\nmetrics.enabled=false\nmetrics.registryType=compact\nmetrics.exporterList=prometheus\nmetrics.exporterPrometheusPort=9898\n\n#For details about configuration items, see https://seata.io/zh-cn/docs/user/configurations.html\n#Transport configuration, for client and server\ntransport.type=TCP\ntransport.server=NIO\ntransport.heartbeat=true\ntransport.enableTmClientBatchSendRequest=false\ntransport.enableRmClientBatchSendRequest=true\ntransport.enableTcServerBatchSendResponse=false\ntransport.rpcRmRequestTimeout=30000\ntransport.rpcTmRequestTimeout=30000\ntransport.rpcTcRequestTimeout=30000\ntransport.threadFactory.bossThreadPrefix=NettyBoss\ntransport.threadFactory.workerThreadPrefix=NettyServerNIOWorker\ntransport.threadFactory.serverExecutorThreadPrefix=NettyServerBizHandler\ntransport.threadFactory.shareBossWorker=false\ntransport.threadFactory.clientSelectorThreadPrefix=NettyClientSelector\ntransport.threadFactory.clientSelectorThreadSize=1\ntransport.threadFactory.clientWorkerThreadPrefix=NettyClientWorkerThread\ntransport.threadFactory.bossThreadSize=1\ntransport.threadFactory.workerThreadSize=default\ntransport.shutdown.wait=3\ntransport.serialization=seata\ntransport.compressor=none\n','12f47aed8a463f862da4944fee7f5ad2','2024-12-05 15:05:53','2024-12-05 07:05:53',NULL,'192.168.1.19','I','prod',''),(115,41,'application-common.yml','DEFAULT_GROUP','','server:\n  # undertow 配置\n  undertow:\n    # HTTP post内容的最大大小。当值为-1时，默认值为大小是无限的\n    max-http-post-size: -1\n    # 以下的配置会影响buffer,这些buffer会用于服务器连接的IO操作,有点类似netty的池化内存管理\n    # 每块buffer的空间大小,越小的空间被利用越充分\n    buffer-size: 512\n    # 是否分配的直接内存\n    direct-buffers: true\n    threads:\n      # 设置IO线程数, 它主要执行非阻塞的任务,它们会负责多个连接, 默认设置每个CPU核心一个线程\n      io: 8\n      # 阻塞任务线程池, 当执行类似servlet请求阻塞操作, undertow会从这个线程池中取得线程,它的值设置取决于系统的负载\n      worker: 256\n\ndubbo:\n  application:\n    # 关闭qos端口避免单机多生产者端口冲突 如需使用自行开启\n    qos-enable: false\n  protocol:\n    # 如需使用 Triple 3.0 新协议 可查看官方文档\n    # 使用 dubbo 协议通信\n    name: dubbo\n    # dubbo 协议端口(-1表示自增端口,从20880开始)\n    port: -1\n    # 指定dubbo协议注册ip\n    # host: 192.168.0.100\n  # 消费者相关配置\n  consumer:\n    # 超时时间\n    timeout: 3000\n  # 自定义配置\n  custom:\n    # 全局请求log\n    request-log: true\n    # info 基础信息 param 参数信息 full 全部\n    log-level: info\n\nspring:\n  threads:\n    # 开启虚拟线程 仅jdk21可用\n    virtual:\n      enabled: false\n  # 资源信息\n  messages:\n    # 国际化资源文件路径\n    basename: i18n/messages\n  servlet:\n    multipart:\n      # 整个请求大小限制\n      max-request-size: 20MB\n      # 上传单个文件大小限制\n      max-file-size: 10MB\n  mvc:\n    # 设置静态资源路径 防止所有请求都去查静态资源\n    static-path-pattern: /static/**\n    format:\n      date-time: yyyy-MM-dd HH:mm:ss\n  #jackson配置\n  jackson:\n    # 日期格式化\n    date-format: yyyy-MM-dd HH:mm:ss\n    serialization:\n      # 格式化输出\n      INDENT_OUTPUT: false\n      # 忽略无法转换的对象\n      fail_on_empty_beans: false\n    deserialization:\n      # 允许对象忽略json中不存在的属性\n      fail_on_unknown_properties: false\n  cloud:\n    nacos:\n      discovery:\n        metadata:\n          # admin 监控账号密码\n          username: ruoyi\n          userpassword: 123456\n    # sentinel 配置\n    sentinel:\n      # sentinel 开关\n      enabled: true\n      transport:\n        # dashboard控制台服务名 用于服务发现\n        # 如无此配置将默认使用下方 dashboard 配置直接注册\n        server-name: ruoyi-sentinel-dashboard\n        # 客户端指定注册的ip 用于多网卡ip不稳点使用\n        # client-ip:\n        # 控制台地址 从1.3.0开始使用 server-name 注册\n        # dashboard: localhost:8718\n\n    bus:\n      id: ${spring.application.name}\n      base-packages: com.cdzeroly.**.event\n  # 消息总线 也可以使用 kafka 参考 spring-cloud-bus 用法\n  rabbitmq:\n    host: localhost\n    port: 5672\n    username: ruoyi\n    password: ruoyi123\n\n  # redis通用配置 子服务可以自行配置进行覆盖\n  data:\n    redis:\n      host: localhost\n      port: 6379\n      # redis 密码必须配置\n      password: ruoyi123\n      database: 0\n      # 需要使用数字\n      timeout: 10000\n      ssl.enabled: false\n\n# redisson 配置\nredisson:\n  # redis key前缀\n  keyPrefix:\n  # 线程池数量\n  threads: 4\n  # Netty线程池数量\n  nettyThreads: 8\n  # 单节点配置\n  singleServerConfig:\n    # 客户端名称\n    clientName: ${spring.application.name}\n    # 最小空闲连接数\n    connectionMinimumIdleSize: 8\n    # 连接池大小\n    connectionPoolSize: 32\n    # 连接空闲超时，单位：毫秒\n    idleConnectionTimeout: 10000\n    # 命令等待超时，单位：毫秒\n    timeout: 3000\n    # 发布和订阅连接池大小\n    subscriptionConnectionPoolSize: 50\n\n# 分布式锁 lock4j 全局配置\nlock4j:\n  # 获取分布式锁超时时间，默认为 3000 毫秒\n  acquire-timeout: 3000\n  # 分布式锁的超时时间，默认为 30 秒\n  expire: 30000\n\n# 暴露监控端点\nmanagement:\n  endpoints:\n    web:\n      exposure:\n        include: \'*\'\n  endpoint:\n    health:\n      show-details: ALWAYS\n    logfile:\n      external-file: ./logs/${spring.application.name}/console.log\n\n# 日志配置\nlogging:\n  level:\n    org.springframework: warn\n    org.apache.dubbo: warn\n    com.alibaba.nacos: warn\n    com.alibaba.cloud.sentinel: warn\n    org.mybatis.spring.mapper: error\n    org.apache.dubbo.config: error\n    # 临时处理 spring 调整日志级别导致启动警告问题 不影响使用等待 alibaba 适配\n    org.springframework.context.support.PostProcessorRegistrationDelegate: error\n  config: classpath:logback-plus.xml\n\n# Sa-Token配置\nsa-token:\n  # token名称 (同时也是cookie名称)\n  token-name: Authorization\n  # 开启内网服务调用鉴权(不允许越过gateway访问内网服务 保障服务安全)\n  check-same-token: true\n  # 是否允许同一账号并发登录 (为true时允许一起登录, 为false时新登录挤掉旧登录)\n  is-concurrent: true\n  # 在多人登录同一账号时，是否共用一个token (为true时所有登录共用一个token, 为false时每次登录新建一个token)\n  is-share: false\n  # jwt秘钥\n  jwt-secret-key: abcdefghijklmnopqrstuvwxyz\n\n# MyBatisPlus配置\n# https://baomidou.com/config/\nmybatis-plus:\n  # 多包名使用 例如 com.cdzeroly.**.mapper,org.xxx.**.mapper\n  mapperPackage: com.cdzeroly.**.mapper\n  # 对应的 XML 文件位置\n  mapperLocations: classpath*:mapper/**/*Mapper.xml\n  # 实体扫描，多个package用逗号或者分号分隔\n  typeAliasesPackage: com.cdzeroly.**.domain\n  global-config:\n    dbConfig:\n      # 主键类型\n      # AUTO 自增 NONE 空 INPUT 用户输入 ASSIGN_ID 雪花 ASSIGN_UUID 唯一 UUID\n      # 如需改为自增 需要将数据库表全部设置为自增\n      idType: ASSIGN_ID\n\n# 数据加密\nmybatis-encryptor:\n  # 是否开启加密\n  enable: false\n  # 默认加密算法\n  algorithm: BASE64\n  # 编码方式 BASE64/HEX。默认BASE64\n  encode: BASE64\n  # 安全秘钥 对称算法的秘钥 如：AES，SM4\n  password:\n  # 公私钥 非对称算法的公私钥 如：SM2，RSA\n  publicKey:\n  privateKey:\n\n# api接口加密\napi-decrypt:\n  # 是否开启全局接口加密\n  enabled: true\n  # AES 加密头标识\n  headerFlag: encrypt-key\n  # 响应加密公钥 非对称算法的公私钥 如：SM2，RSA 使用者请自行更换\n  # 对应前端解密私钥 MIIBVAIBADANBgkqhkiG9w0BAQEFAASCAT4wggE6AgEAAkEAmc3CuPiGL/LcIIm7zryCEIbl1SPzBkr75E2VMtxegyZ1lYRD+7TZGAPkvIsBcaMs6Nsy0L78n2qh+lIZMpLH8wIDAQABAkEAk82Mhz0tlv6IVCyIcw/s3f0E+WLmtPFyR9/WtV3Y5aaejUkU60JpX4m5xNR2VaqOLTZAYjW8Wy0aXr3zYIhhQQIhAMfqR9oFdYw1J9SsNc+CrhugAvKTi0+BF6VoL6psWhvbAiEAxPPNTmrkmrXwdm/pQQu3UOQmc2vCZ5tiKpW10CgJi8kCIFGkL6utxw93Ncj4exE/gPLvKcT+1Emnoox+O9kRXss5AiAMtYLJDaLEzPrAWcZeeSgSIzbL+ecokmFKSDDcRske6QIgSMkHedwND1olF8vlKsJUGK3BcdtM8w4Xq7BpSBwsloE=\n  publicKey: MFwwDQYJKoZIhvcNAQEBBQADSwAwSAJBAJnNwrj4hi/y3CCJu868ghCG5dUj8wZK++RNlTLcXoMmdZWEQ/u02RgD5LyLAXGjLOjbMtC+/J9qofpSGTKSx/MCAwEAAQ==\n  # 请求解密私钥 非对称算法的公私钥 如：SM2，RSA 使用者请自行更换\n  # 对应前端加密公钥 MFwwDQYJKoZIhvcNAQEBBQADSwAwSAJBAKoR8mX0rGKLqzcWmOzbfj64K8ZIgOdHnzkXSOVOZbFu/TJhZ7rFAN+eaGkl3C4buccQd/EjEsj9ir7ijT7h96MCAwEAAQ==\n  privateKey: MIIBVAIBADANBgkqhkiG9w0BAQEFAASCAT4wggE6AgEAAkEAqhHyZfSsYourNxaY7Nt+PrgrxkiA50efORdI5U5lsW79MmFnusUA355oaSXcLhu5xxB38SMSyP2KvuKNPuH3owIDAQABAkAfoiLyL+Z4lf4Myxk6xUDgLaWGximj20CUf+5BKKnlrK+Ed8gAkM0HqoTt2UZwA5E2MzS4EI2gjfQhz5X28uqxAiEA3wNFxfrCZlSZHb0gn2zDpWowcSxQAgiCstxGUoOqlW8CIQDDOerGKH5OmCJ4Z21v+F25WaHYPxCFMvwxpcw99EcvDQIgIdhDTIqD2jfYjPTY8Jj3EDGPbH2HHuffvflECt3Ek60CIQCFRlCkHpi7hthhYhovyloRYsM+IS9h/0BzlEAuO0ktMQIgSPT3aFAgJYwKpqRYKlLDVcflZFCKY7u3UP8iWi1Qw0Y=\n\n# 防止XSS攻击\nxss:\n  enabled: true\n  excludeUrls:\n    - /system/notice\n    - /workflow/model/save\n    - /workflow/model/editModelXml\n\n# 接口文档配置\nspringdoc:\n  api-docs:\n    # 是否开启接口文档\n    enabled: true\n#  swagger-ui:\n#    # 持久化认证数据\n#    persistAuthorization: true\n  info:\n    # 标题\n    title: \'标题：RuoYi-Cloud-Plus微服务权限管理系统_接口文档\'\n    # 描述\n    description: \'描述：微服务权限管理系统, 具体包括XXX,XXX模块...\'\n    # 版本\n    version: \'版本号：系统版本...\'\n    # 作者信息\n    contact:\n      name: Lion Li\n      email: crazylionli@163.com\n      url: https://gitee.com/dromara/RuoYi-Cloud-Plus\n  components:\n    # 鉴权方式配置\n    security-schemes:\n      apiKey:\n        type: APIKEY\n        in: HEADER\n        name: ${sa-token.token-name}\n\n# seata配置\nseata:\n  # 是否启用\n  enabled: true\n  # Seata 应用编号，默认为应用名\n  application-id: ${spring.application.name}\n  # Seata 事务组编号，用于 TC 集群名\n  tx-service-group: ${spring.application.name}-group\n\n# 多租户配置\ntenant:\n  # 是否开启\n  enable: true\n  # 排除表\n  excludes:\n    - sys_menu\n    - sys_tenant\n    - sys_tenant_package\n    - sys_role_dept\n    - sys_role_menu\n    - sys_user_post\n    - sys_user_role\n    - sys_client\n    - sys_oss_config\n','9ce0cd9136de4b8f9c92fb37051f4bd4','2024-12-05 15:07:07','2024-12-05 07:07:08',NULL,'192.168.1.19','U','prod',''),(116,42,'datasource.yml','DEFAULT_GROUP','','datasource:\n  system-master:\n    # jdbc 所有参数配置参考 https://lionli.blog.csdn.net/article/details/122018562\n    # rewriteBatchedStatements=true 批处理优化 大幅提升批量插入更新删除性能\n    url: jdbc:mysql://localhost:3306/ry-cloud?useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&useSSL=true&serverTimezone=GMT%2B8&rewriteBatchedStatements=true&allowPublicKeyRetrieval=true\n    username: root\n    password: root\n  gen:\n    url: jdbc:mysql://localhost:3306/ry-cloud?useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&useSSL=true&serverTimezone=GMT%2B8&rewriteBatchedStatements=true&allowPublicKeyRetrieval=true\n    username: root\n    password: root\n  job:\n    url: jdbc:mysql://localhost:3306/ry-job?useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&useSSL=true&serverTimezone=GMT%2B8&rewriteBatchedStatements=true&allowPublicKeyRetrieval=true\n    username: root\n    password: root\n  workflow:\n    url: jdbc:mysql://localhost:3306/ry-workflow?useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&useSSL=true&serverTimezone=GMT%2B8&rewriteBatchedStatements=true&allowPublicKeyRetrieval=true&nullCatalogMeansCurrent=true\n    username: root\n    password: root\n#  system-oracle:\n#    url: jdbc:oracle:thin:@//localhost:1521/XE\n#    username: ROOT\n#    password: password\n#  system-postgres:\n#    url: jdbc:postgresql://localhost:5432/postgres?useUnicode=true&characterEncoding=utf8&useSSL=true&autoReconnect=true&reWriteBatchedInserts=true\n#    username: root\n#    password: password\n\nspring:\n  datasource:\n    type: com.zaxxer.hikari.HikariDataSource\n    # 动态数据源文档 https://www.kancloud.cn/tracy5546/dynamic-datasource/content\n    dynamic:\n      # 性能分析插件(有性能损耗 不建议生产环境使用)\n      p6spy: true\n      # 开启seata代理，开启后默认每个数据源都代理，如果某个不需要代理可单独关闭\n      seata: ${seata.enabled}\n      # 严格模式 匹配不到数据源则报错\n      strict: true\n      hikari:\n        # 最大连接池数量\n        maxPoolSize: 20\n        # 最小空闲线程数量\n        minIdle: 10\n        # 配置获取连接等待超时的时间\n        connectionTimeout: 30000\n        # 校验超时时间\n        validationTimeout: 5000\n        # 空闲连接存活最大时间，默认10分钟\n        idleTimeout: 600000\n        # 此属性控制池中连接的最长生命周期，值0表示无限生命周期，默认30分钟\n        maxLifetime: 1800000\n        # 多久检查一次连接的活性\n        keepaliveTime: 30000\n','ceb67fe7f6e6a92955d0ac164c3f8805','2024-12-05 15:07:07','2024-12-05 07:07:08',NULL,'192.168.1.19','U','prod',''),(0,43,'ruoyi-gateway.yml','DEFAULT_GROUP','','# 安全配置\nsecurity:\n  # 不校验白名单\n  ignore:\n    whites:\n      - /auth/code\n      - /auth/logout\n      - /auth/login\n      - /auth/binding/*\n      - /auth/social/callback\n      - /auth/register\n      - /auth/tenant/list\n      - /resource/sms/code\n      - /resource/sse/close\n      - /*/v3/api-docs\n      - /*/error\n      - /csrf\n\nspring:\n  cloud:\n    # 网关配置\n    gateway:\n      # 打印请求日志(自定义)\n      requestLog: true\n      discovery:\n        locator:\n          lowerCaseServiceId: true\n          enabled: true\n      routes:\n        # 认证中心\n        - id: auth\n          uri: lb://auth\n          predicates:\n            - Path=/auth/**\n          filters:\n            - StripPrefix=1\n        # 代码生成\n        - id: gen\n          uri: lb://gen\n          predicates:\n            - Path=/tool/**\n          filters:\n            - StripPrefix=1\n        # 系统模块\n        - id: system\n          uri: lb://system\n          predicates:\n            - Path=/system/**,/monitor/**\n          filters:\n            - StripPrefix=1\n        # 资源服务\n        - id: resource\n          uri: lb://resource\n          predicates:\n            - Path=/resource/**\n          filters:\n            - StripPrefix=1\n        # workflow服务\n        - id: workflow\n          uri: lb://workflow\n          predicates:\n            - Path=/workflow/**\n          filters:\n            - StripPrefix=1\n        # 演示服务\n        - id: demo\n          uri: lb://demo\n          predicates:\n            - Path=/demo/**\n          filters:\n            - StripPrefix=1\n        # MQ演示服务\n        - id: test-mq\n          uri: lb://test-mq\n          predicates:\n            - Path=/test-mq/**\n          filters:\n            - StripPrefix=1\n\n    # sentinel 配置\n    sentinel:\n      filter:\n        enabled: false\n      # nacos配置持久化\n      datasource:\n        ds1:\n          nacos:\n            server-addr: ${spring.cloud.nacos.server-addr}\n            dataId: sentinel-${spring.application.name}.json\n            groupId: ${spring.cloud.nacos.config.group}\n            username: ${spring.cloud.nacos.username}\n            password: ${spring.cloud.nacos.password}\n            namespace: ${spring.profiles.active}\n            data-type: json\n            rule-type: gw-flow\n','f8943720504ecace1ff47cc4e9e31efc','2024-12-05 15:07:07','2024-12-05 07:07:08',NULL,'192.168.1.19','I','prod',''),(0,44,'ruoyi-auth.yml','DEFAULT_GROUP','','# 安全配置\nsecurity:\n  # 验证码\n  captcha:\n    # 是否开启验证码\n    enabled: true\n    # 验证码类型 math 数组计算 char 字符验证\n    type: MATH\n    # line 线段干扰 circle 圆圈干扰 shear 扭曲干扰\n    category: CIRCLE\n    # 数字验证码位数\n    numberLength: 1\n    # 字符验证码长度\n    charLength: 4\n\n# 用户配置\nuser:\n  password:\n    # 密码最大错误次数\n    maxRetryCount: 5\n    # 密码锁定时间（默认10分钟）\n    lockTime: 10\n\n# 三方授权\njustauth:\n  # 前端外网访问地址\n  address: http://localhost:80\n  type:\n    maxkey:\n      # maxkey 服务器地址\n      # 注意 如下均配置均不需要修改 maxkey 已经内置好了数据\n      server-url: http://sso.maxkey.top\n      client-id: 876892492581044224\n      client-secret: x1Y5MTMwNzIwMjMxNTM4NDc3Mzche8\n      redirect-uri: ${justauth.address}/social-callback?source=maxkey\n    topiam:\n      # topiam 服务器地址\n      server-url: http://127.0.0.1:1989/api/v1/authorize/y0q************spq***********8ol\n      client-id: 449c4*********937************759\n      client-secret: ac7***********1e0************28d\n      redirect-uri: ${justauth.address}/social-callback?source=topiam\n      scopes: [ openid, email, phone, profile ]\n    qq:\n      client-id: 10**********6\n      client-secret: 1f7d08**********5b7**********29e\n      redirect-uri: ${justauth.address}/social-callback?source=qq\n      union-id: false\n    weibo:\n      client-id: 10**********6\n      client-secret: 1f7d08**********5b7**********29e\n      redirect-uri: ${justauth.address}/social-callback?source=weibo\n    gitee:\n      client-id: 91436b7940090d09c72c7daf85b959cfd5f215d67eea73acbf61b6b590751a98\n      client-secret: 02c6fcfd70342980cd8dd2f2c06c1a350645d76c754d7a264c4e125f9ba915ac\n      redirect-uri: ${justauth.address}/social-callback?source=gitee\n    dingtalk:\n      client-id: 10**********6\n      client-secret: 1f7d08**********5b7**********29e\n      redirect-uri: ${justauth.address}/social-callback?source=dingtalk\n    baidu:\n      client-id: 10**********6\n      client-secret: 1f7d08**********5b7**********29e\n      redirect-uri: ${justauth.address}/social-callback?source=baidu\n    csdn:\n      client-id: 10**********6\n      client-secret: 1f7d08**********5b7**********29e\n      redirect-uri: ${justauth.address}/social-callback?source=csdn\n    coding:\n      client-id: 10**********6\n      client-secret: 1f7d08**********5b7**********29e\n      redirect-uri: ${justauth.address}/social-callback?source=coding\n      coding-group-name: xx\n    oschina:\n      client-id: 10**********6\n      client-secret: 1f7d08**********5b7**********29e\n      redirect-uri: ${justauth.address}/social-callback?source=oschina\n    alipay_wallet:\n      client-id: 10**********6\n      client-secret: 1f7d08**********5b7**********29e\n      redirect-uri: ${justauth.address}/social-callback?source=alipay_wallet\n      alipay-public-key: MIIB**************DAQAB\n    wechat_open:\n      client-id: 10**********6\n      client-secret: 1f7d08**********5b7**********29e\n      redirect-uri: ${justauth.address}/social-callback?source=wechat_open\n    wechat_mp:\n      client-id: 10**********6\n      client-secret: 1f7d08**********5b7**********29e\n      redirect-uri: ${justauth.address}/social-callback?source=wechat_mp\n    wechat_enterprise:\n      client-id: 10**********6\n      client-secret: 1f7d08**********5b7**********29e\n      redirect-uri: ${justauth.address}/social-callback?source=wechat_enterprise\n      agent-id: 1000002\n    gitlab:\n      client-id: 10**********6\n      client-secret: 1f7d08**********5b7**********29e\n      redirect-uri: ${justauth.address}/social-callback?source=gitlab\n','362c28d8864626b864d682b5f1c4a73a','2024-12-05 15:07:08','2024-12-05 07:07:08',NULL,'192.168.1.19','I','prod',''),(0,45,'ruoyi-monitor.yml','DEFAULT_GROUP','','# 监控中心配置\nspring:\n  security:\n    user:\n      name: ruoyi\n      password: 123456\n  boot:\n    admin:\n      ui:\n        title: RuoYi-Cloud-Plus服务监控中心\n      discovery:\n        # seata 不具有健康检查的能力 防止报错排除掉\n        ignored-services: ruoyi-seata-server\n','ba7fa6a6bfde3dcb3b566fd8864834af','2024-12-05 15:07:08','2024-12-05 07:07:08',NULL,'192.168.1.19','I','prod',''),(0,46,'ruoyi-system.yml','DEFAULT_GROUP','','spring:\n  datasource:\n    dynamic:\n      # 设置默认的数据源或者数据源组,默认值即为 master\n      primary: master\n      datasource:\n        # 主库数据源\n        master:\n          type: ${spring.datasource.type}\n          driver-class-name: com.mysql.cj.jdbc.Driver\n          url: ${datasource.system-master.url}\n          username: ${datasource.system-master.username}\n          password: ${datasource.system-master.password}\n#        oracle:\n#          type: ${spring.datasource.type}\n#          driverClassName: oracle.jdbc.OracleDriver\n#          url: ${datasource.system-oracle.url}\n#          username: ${datasource.system-oracle.username}\n#          password: ${datasource.system-oracle.password}\n#        postgres:\n#          type: ${spring.datasource.type}\n#          driverClassName: org.postgresql.Driver\n#          url: ${datasource.system-postgres.url}\n#          username: ${datasource.system-postgres.username}\n#          password: ${datasource.system-postgres.password}\n','f59259409ff41d30e5276bf4afbcaa3d','2024-12-05 15:07:08','2024-12-05 07:07:09',NULL,'192.168.1.19','I','prod',''),(0,47,'ruoyi-gen.yml','DEFAULT_GROUP','','spring:\n  datasource:\n    dynamic:\n      # 设置默认的数据源或者数据源组,默认值即为 master\n      primary: master\n      seata: false\n      datasource:\n        # 主库数据源\n        master:\n          type: ${spring.datasource.type}\n          driver-class-name: com.mysql.cj.jdbc.Driver\n          url: ${datasource.system-master.url}\n          username: ${datasource.system-master.username}\n          password: ${datasource.system-master.password}\n#        oracle:\n#          type: ${spring.datasource.type}\n#          driverClassName: oracle.jdbc.OracleDriver\n#          url: ${datasource.system-oracle.url}\n#          username: ${datasource.system-oracle.username}\n#          password: ${datasource.system-oracle.password}\n#        postgres:\n#          type: ${spring.datasource.type}\n#          driverClassName: org.postgresql.Driver\n#          url: ${datasource.system-postgres.url}\n#          username: ${datasource.system-postgres.username}\n#          password: ${datasource.system-postgres.password}\n\n# 代码生成\ngen:\n  # 作者\n  author: MGARY\n  # 默认生成包路径 system 需改成自己的模块名称 如 system monitor tool\n  packageName: com.cdzeroly.system\n  # 自动去除表前缀，默认是false\n  autoRemovePre: false\n  # 表前缀（生成类名不会包含表前缀，多个用逗号分隔）\n  tablePrefix: sys_\n','f02376e215be7e735427461290ca100e','2024-12-05 15:07:08','2024-12-05 07:07:09',NULL,'192.168.1.19','I','prod',''),(0,48,'ruoyi-job.yml','DEFAULT_GROUP','','spring:\n  datasource:\n    dynamic:\n      # 设置默认的数据源或者数据源组,默认值即为 master\n      primary: master\n      seata: false\n      datasource:\n        # 主库数据源\n        master:\n          type: ${spring.datasource.type}\n          driver-class-name: com.mysql.cj.jdbc.Driver\n          url: ${datasource.job.url}\n          username: ${datasource.job.username}\n          password: ${datasource.job.password}\n\nsnail-job:\n  enabled: true\n  # 需要在 SnailJob 后台组管理创建对应名称的组,然后创建任务的时候选择对应的组,才能正确分派任务\n  group: \"base_platform_group\"\n  #  SnailJob 接入验证令牌\n  token: \"SJ_cKqBTPzCsWA3VyuCfFoccmuIEGXjr5KT\"\n  server:\n    # 从 nacos 获取服务\n    server-name: snailjob-server\n    # 服务名优先 ip垫底\n    host: 127.0.0.1\n    port: 17888\n  # 详见 script/sql/ry_job.sql `sj_namespace` 表\n  namespace: ${spring.profiles.active}\n  # 随主应用端口飘逸\n  port: 2${server.port}\n  # 客户端ip指定\n  host:\n','58b664a7d062e5983172cdc562e37f00','2024-12-05 15:07:09','2024-12-05 07:07:09',NULL,'192.168.1.19','I','prod',''),(0,49,'ruoyi-resource.yml','DEFAULT_GROUP','','spring:\n  datasource:\n    dynamic:\n      # 设置默认的数据源或者数据源组,默认值即为 master\n      primary: master\n      datasource:\n        # 主库数据源\n        master:\n          type: ${spring.datasource.type}\n          driver-class-name: com.mysql.cj.jdbc.Driver\n          url: ${datasource.system-master.url}\n          username: ${datasource.system-master.username}\n          password: ${datasource.system-master.password}\n#        oracle:\n#          type: ${spring.datasource.type}\n#          driverClassName: oracle.jdbc.OracleDriver\n#          url: ${datasource.system-oracle.url}\n#          username: ${datasource.system-oracle.username}\n#          password: ${datasource.system-oracle.password}\n#        postgres:\n#          type: ${spring.datasource.type}\n#          driverClassName: org.postgresql.Driver\n#          url: ${datasource.system-postgres.url}\n#          username: ${datasource.system-postgres.username}\n#          password: ${datasource.system-postgres.password}\n\n# 默认/推荐使用sse推送\nsse:\n  enabled: true\n  path: /sse\n\nwebsocket:\n  # 如果关闭 需要和前端开关一起关闭\n  enabled: false\n  # 路径\n  path: /websocket\n  # 设置访问源地址\n  allowedOrigins: \'*\'\n\nmail:\n  enabled: false\n  host: smtp.163.com\n  port: 465\n  # 是否需要用户名密码验证\n  auth: true\n  # 发送方，遵循RFC-822标准\n  from: xxx@163.com\n  # 用户名（注意：如果使用foxmail邮箱，此处user为qq号）\n  user: xxx@163.com\n  # 密码（注意，某些邮箱需要为SMTP服务单独设置密码，详情查看相关帮助）\n  pass: xxxxxxxxxx\n  # 使用 STARTTLS安全连接，STARTTLS是对纯文本通信协议的扩展。\n  starttlsEnable: true\n  # 使用SSL安全连接\n  sslEnable: true\n  # SMTP超时时长，单位毫秒，缺省值不超时\n  timeout: 0\n  # Socket连接超时值，单位毫秒，缺省值不超时\n  connectionTimeout: 0\n\n# sms 短信 支持 阿里云 腾讯云 云片 等等各式各样的短信服务商\n# https://sms4j.com/doc3/ 差异配置文档地址 支持单厂商多配置，可以配置多个同时使用\nsms:\n  # 配置源类型用于标定配置来源(interface,yaml)\n  config-type: yaml\n  # 用于标定yml中的配置是否开启短信拦截，接口配置不受此限制\n  restricted: true\n  # 短信拦截限制单手机号每分钟最大发送，只对开启了拦截的配置有效\n  minute-max: 1\n  # 短信拦截限制单手机号每日最大发送量，只对开启了拦截的配置有效\n  account-max: 30\n  # 以下配置来自于 org.dromara.sms4j.provider.config.BaseConfig类中\n  blends:\n    # 唯一ID 用于发送短信寻找具体配置 随便定义别用中文即可\n    # 可以同时存在两个相同厂商 例如: ali1 ali2 两个不同的阿里短信账号 也可用于区分租户\n    config1:\n      # 框架定义的厂商名称标识，标定此配置是哪个厂商，详细请看厂商标识介绍部分\n      supplier: alibaba\n      # 有些称为accessKey有些称之为apiKey，也有称为sdkKey或者appId。\n      access-key-id: 您的accessKey\n      # 称为accessSecret有些称之为apiSecret\n      access-key-secret: 您的accessKeySecret\n      signature: 您的短信签名\n      sdk-app-id: 您的sdkAppId\n    config2:\n      # 厂商标识，标定此配置是哪个厂商，详细请看厂商标识介绍部分\n      supplier: tencent\n      access-key-id: 您的accessKey\n      access-key-secret: 您的accessKeySecret\n      signature: 您的短信签名\n      sdk-app-id: 您的sdkAppId\n','efb16000eb230328fa2128ea56558fd2','2024-12-05 15:07:09','2024-12-05 07:07:09',NULL,'192.168.1.19','I','prod',''),(0,50,'ruoyi-workflow.yml','DEFAULT_GROUP','','spring:\n  datasource:\n    dynamic:\n      # 设置默认的数据源或者数据源组,默认值即为 master\n      primary: master\n      datasource:\n        # 主库数据源\n        master:\n          type: ${spring.datasource.type}\n          driver-class-name: com.mysql.cj.jdbc.Driver\n          url: ${datasource.workflow.url}\n          username: ${datasource.workflow.username}\n          password: ${datasource.workflow.password}\n#        oracle:\n#          type: ${spring.datasource.type}\n#          driverClassName: oracle.jdbc.OracleDriver\n#          url: ${datasource.system-oracle.url}\n#          username: ${datasource.system-oracle.username}\n#          password: ${datasource.system-oracle.password}\n#        postgres:\n#          type: ${spring.datasource.type}\n#          driverClassName: org.postgresql.Driver\n#          url: ${datasource.system-postgres.url}\n#          username: ${datasource.system-postgres.username}\n#          password: ${datasource.system-postgres.password}\n\n# flowable配置\nflowable:\n  # 开关 用于启动/停用工作流\n  enabled: true\n  process.enabled: ${flowable.enabled}\n  eventregistry.enabled: ${flowable.enabled}\n  # 关闭定时任务JOB\n  async-executor-activate: false\n  # 将databaseSchemaUpdate设置为true。当Flowable发现库与数据库表结构不一致时，会自动将数据库表结构升级至新版本。\n  database-schema-update: true\n  activity-font-name: 宋体\n  label-font-name: 宋体\n  annotation-font-name: 宋体\n  # 关闭各个模块生成表，目前只使用工作流基础表\n  idm:\n    enabled: false\n  cmmn:\n    enabled: false\n  dmn:\n    enabled: false\n  app:\n    enabled: false\n','95b566892b838da618fbfdaf1ecffd0a','2024-12-05 15:07:09','2024-12-05 07:07:10',NULL,'192.168.1.19','I','prod',''),(117,51,'sentinel-ruoyi-gateway.json','DEFAULT_GROUP','','[\n  {\n    \"resource\": \"ruoyi-auth\",\n    \"count\": 500,\n    \"grade\": 1,\n    \"limitApp\": \"default\",\n    \"strategy\": 0,\n    \"controlBehavior\": 0\n  },\n  {\n    \"resource\": \"ruoyi-system\",\n    \"count\": 1000,\n    \"grade\": 1,\n    \"limitApp\": \"default\",\n    \"strategy\": 0,\n    \"controlBehavior\": 0\n  },\n  {\n    \"resource\": \"ruoyi-resource\",\n    \"count\": 500,\n    \"grade\": 1,\n    \"limitApp\": \"default\",\n    \"strategy\": 0,\n    \"controlBehavior\": 0\n  }\n]\n','2efad3702e99adef46c2ee818b3925e5','2024-12-05 15:07:09','2024-12-05 07:07:10',NULL,'192.168.1.19','U','prod',''),(118,52,'seata-server.properties','DEFAULT_GROUP','','service.vgroupMapping.ruoyi-auth-group=default\nservice.vgroupMapping.ruoyi-system-group=default\nservice.vgroupMapping.ruoyi-resource-group=default\nservice.vgroupMapping.ruoyi-workflow-group=default\n\nservice.enableDegrade=false\nservice.disableGlobalTransaction=false\n\n#Transaction storage configuration, only for the server. The file, DB, and redis configuration values are optional.\nstore.mode=db\nstore.lock.mode=db\nstore.session.mode=db\n#Used for password encryption\n#store.publicKey=\n\n#These configurations are required if the `store mode` is `db`. If `store.mode,store.lock.mode,store.session.mode` are not equal to `db`, you can remove the configuration block.\nstore.db.datasource=hikari\nstore.db.dbType=mysql\nstore.db.driverClassName=com.mysql.cj.jdbc.Driver\nstore.db.url=jdbc:mysql://127.0.0.1:3306/ry-seata?useUnicode=true&rewriteBatchedStatements=true&allowPublicKeyRetrieval=true\nstore.db.user=root\nstore.db.password=ruoyi123\nstore.db.minConn=5\nstore.db.maxConn=30\nstore.db.globalTable=global_table\nstore.db.branchTable=branch_table\nstore.db.distributedLockTable=distributed_lock\nstore.db.queryLimit=100\nstore.db.lockTable=lock_table\nstore.db.maxWait=5000\n\n# redis 模式 store.mode=redis 开启 (控制台查询功能有限,不影响实际执行功能)\n# store.redis.host=127.0.0.1\n# store.redis.port=6379\n# 最大连接数\n# store.redis.maxConn=10\n# 最小连接数\n# store.redis.minConn=1\n# store.redis.database=0\n# store.redis.password=\n# store.redis.queryLimit=100\n\n#Transaction rule configuration, only for the server\nserver.recovery.committingRetryPeriod=1000\nserver.recovery.asynCommittingRetryPeriod=1000\nserver.recovery.rollbackingRetryPeriod=1000\nserver.recovery.timeoutRetryPeriod=1000\nserver.maxCommitRetryTimeout=-1\nserver.maxRollbackRetryTimeout=-1\nserver.rollbackRetryTimeoutUnlockEnable=false\nserver.distributedLockExpireTime=10000\nserver.xaerNotaRetryTimeout=60000\nserver.session.branchAsyncQueueSize=5000\nserver.session.enableBranchAsyncRemove=false\n\n#Transaction rule configuration, only for the client\nclient.rm.asyncCommitBufferLimit=10000\nclient.rm.lock.retryInterval=10\nclient.rm.lock.retryTimes=30\nclient.rm.lock.retryPolicyBranchRollbackOnConflict=true\nclient.rm.reportRetryCount=5\nclient.rm.tableMetaCheckEnable=true\nclient.rm.tableMetaCheckerInterval=60000\nclient.rm.sqlParserType=druid\nclient.rm.reportSuccessEnable=false\nclient.rm.sagaBranchRegisterEnable=false\nclient.rm.sagaJsonParser=fastjson\nclient.rm.tccActionInterceptorOrder=-2147482648\nclient.tm.commitRetryCount=5\nclient.tm.rollbackRetryCount=5\nclient.tm.defaultGlobalTransactionTimeout=60000\nclient.tm.degradeCheck=false\nclient.tm.degradeCheckAllowTimes=10\nclient.tm.degradeCheckPeriod=2000\nclient.tm.interceptorOrder=-2147482648\nclient.undo.dataValidation=true\nclient.undo.logSerialization=jackson\nclient.undo.onlyCareUpdateColumns=true\nserver.undo.logSaveDays=7\nserver.undo.logDeletePeriod=86400000\nclient.undo.logTable=undo_log\nclient.undo.compress.enable=true\nclient.undo.compress.type=zip\nclient.undo.compress.threshold=64k\n\n#For TCC transaction mode\ntcc.fence.logTableName=tcc_fence_log\ntcc.fence.cleanPeriod=1h\n\n#Log rule configuration, for client and server\nlog.exceptionRate=100\n\n#Metrics configuration, only for the server\nmetrics.enabled=false\nmetrics.registryType=compact\nmetrics.exporterList=prometheus\nmetrics.exporterPrometheusPort=9898\n\n#For details about configuration items, see https://seata.io/zh-cn/docs/user/configurations.html\n#Transport configuration, for client and server\ntransport.type=TCP\ntransport.server=NIO\ntransport.heartbeat=true\ntransport.enableTmClientBatchSendRequest=false\ntransport.enableRmClientBatchSendRequest=true\ntransport.enableTcServerBatchSendResponse=false\ntransport.rpcRmRequestTimeout=30000\ntransport.rpcTmRequestTimeout=30000\ntransport.rpcTcRequestTimeout=30000\ntransport.threadFactory.bossThreadPrefix=NettyBoss\ntransport.threadFactory.workerThreadPrefix=NettyServerNIOWorker\ntransport.threadFactory.serverExecutorThreadPrefix=NettyServerBizHandler\ntransport.threadFactory.shareBossWorker=false\ntransport.threadFactory.clientSelectorThreadPrefix=NettyClientSelector\ntransport.threadFactory.clientSelectorThreadSize=1\ntransport.threadFactory.clientWorkerThreadPrefix=NettyClientWorkerThread\ntransport.threadFactory.bossThreadSize=1\ntransport.threadFactory.workerThreadSize=default\ntransport.shutdown.wait=3\ntransport.serialization=seata\ntransport.compressor=none\n','12f47aed8a463f862da4944fee7f5ad2','2024-12-05 15:07:09','2024-12-05 07:07:10',NULL,'192.168.1.19','U','prod',''),(0,53,'ruoyi-sentinel-dashboard.yml','DEFAULT_GROUP','','spring:\n  mvc:\n    pathmatch:\n      # 修复 sentinel 控制台未适配 springboot 2.6 新路由方式\n      matching-strategy: ANT_PATH_MATCHER\n\nserver:\n  servlet:\n    encoding:\n      force: true\n      charset: UTF-8\n      enabled: true\n    session:\n      cookie:\n        name: sentinel_dashboard_cookie\n\nlogging:\n  level:\n    org.springframework.web: INFO\n\nauth:\n  enabled: true\n  filter:\n    exclude-urls: /,/auth/login,/auth/logout,/registry/machine,/version,/actuator,/actuator/**\n    exclude-url-suffixes: htm,html,js,css,map,ico,ttf,woff,png\n  username: sentinel\n  password: sentinel\n','59055747c62f08cd2c38a6016d4b9227','2024-12-05 15:07:09','2024-12-05 07:07:10',NULL,'192.168.1.19','I','prod',''),(0,54,'ruoyi-snailjob-server.yml','DEFAULT_GROUP','','spring:\n  datasource:\n    type: com.zaxxer.hikari.HikariDataSource\n    driver-class-name: com.mysql.cj.jdbc.Driver\n    url: ${datasource.job.url}\n    username: ${datasource.job.username}\n    password: ${datasource.job.password}\n    hikari:\n      connection-timeout: 30000\n      validation-timeout: 5000\n      minimum-idle: 10\n      maximum-pool-size: 20\n      idle-timeout: 600000\n      max-lifetime: 900000\n      keepaliveTime: 30000\n  cloud:\n    nacos:\n      discovery:\n        metadata:\n          # 解决 er 服务有 context-path 无法监控问题\n          management.context-path: ${server.servlet.context-path}/actuator\n          # 监控账号密码\n          username: ruoyi\n          userpassword: 123456\n\n# snail-job 服务端配置\nsnail-job:\n  # 拉取重试数据的每批次的大小\n  retry-pull-page-size: 1000\n  # 拉取重试数据的每批次的大小\n  job-pull-page-size: 1000\n  # 服务端 netty 端口\n  netty-port: 17888\n  # 重试和死信表的分区总数\n  total-partition: 2\n  # 一个客户端每秒最多接收的重试数量指令\n  limiter: 1000\n  # 号段模式下步长配置\n  step: 100\n  # 日志保存时间(单位: day)\n  log-storage: 90\n  # 回调配置\n  callback:\n    #回调最大执行次数\n    max-count: 288\n    #间隔时间\n    trigger-interval: 900\n  retry-max-pull-count: 10\n','ab06186a47974fd09806e48484f37a1a','2024-12-05 15:07:09','2024-12-05 07:07:10',NULL,'192.168.1.19','I','prod',''),(115,55,'application-common.yml','DEFAULT_GROUP','','server:\n  # undertow 配置\n  undertow:\n    # HTTP post内容的最大大小。当值为-1时，默认值为大小是无限的\n    max-http-post-size: -1\n    # 以下的配置会影响buffer,这些buffer会用于服务器连接的IO操作,有点类似netty的池化内存管理\n    # 每块buffer的空间大小,越小的空间被利用越充分\n    buffer-size: 512\n    # 是否分配的直接内存\n    direct-buffers: true\n    threads:\n      # 设置IO线程数, 它主要执行非阻塞的任务,它们会负责多个连接, 默认设置每个CPU核心一个线程\n      io: 8\n      # 阻塞任务线程池, 当执行类似servlet请求阻塞操作, undertow会从这个线程池中取得线程,它的值设置取决于系统的负载\n      worker: 256\n\ndubbo:\n  application:\n    # 关闭qos端口避免单机多生产者端口冲突 如需使用自行开启\n    qos-enable: false\n  protocol:\n    # 如需使用 Triple 3.0 新协议 可查看官方文档\n    # 使用 dubbo 协议通信\n    name: dubbo\n    # dubbo 协议端口(-1表示自增端口,从20880开始)\n    port: -1\n    # 指定dubbo协议注册ip\n    # host: 192.168.0.100\n  # 消费者相关配置\n  consumer:\n    # 超时时间\n    timeout: 3000\n  # 自定义配置\n  custom:\n    # 全局请求log\n    request-log: true\n    # info 基础信息 param 参数信息 full 全部\n    log-level: info\n\nspring:\n  threads:\n    # 开启虚拟线程 仅jdk21可用\n    virtual:\n      enabled: false\n  # 资源信息\n  messages:\n    # 国际化资源文件路径\n    basename: i18n/messages\n  servlet:\n    multipart:\n      # 整个请求大小限制\n      max-request-size: 20MB\n      # 上传单个文件大小限制\n      max-file-size: 10MB\n  mvc:\n    # 设置静态资源路径 防止所有请求都去查静态资源\n    static-path-pattern: /static/**\n    format:\n      date-time: yyyy-MM-dd HH:mm:ss\n  #jackson配置\n  jackson:\n    # 日期格式化\n    date-format: yyyy-MM-dd HH:mm:ss\n    serialization:\n      # 格式化输出\n      INDENT_OUTPUT: false\n      # 忽略无法转换的对象\n      fail_on_empty_beans: false\n    deserialization:\n      # 允许对象忽略json中不存在的属性\n      fail_on_unknown_properties: false\n  cloud:\n    nacos:\n      discovery:\n        metadata:\n          # admin 监控账号密码\n          username: ruoyi\n          userpassword: 123456\n    # sentinel 配置\n    sentinel:\n      # sentinel 开关\n      enabled: true\n      transport:\n        # dashboard控制台服务名 用于服务发现\n        # 如无此配置将默认使用下方 dashboard 配置直接注册\n        server-name: ruoyi-sentinel-dashboard\n        # 客户端指定注册的ip 用于多网卡ip不稳点使用\n        # client-ip:\n        # 控制台地址 从1.3.0开始使用 server-name 注册\n        # dashboard: localhost:8718\n\n    bus:\n      id: ${spring.application.name}\n      base-packages: com.cdzeroly.**.event\n  # 消息总线 也可以使用 kafka 参考 spring-cloud-bus 用法\n  rabbitmq:\n    host: localhost\n    port: 5672\n    username: ruoyi\n    password: ruoyi123\n\n  # redis通用配置 子服务可以自行配置进行覆盖\n  data:\n    redis:\n      host: localhost\n      port: 6379\n      # redis 密码必须配置\n      password: ruoyi123\n      database: 0\n      # 需要使用数字\n      timeout: 10000\n      ssl.enabled: false\n\n# redisson 配置\nredisson:\n  # redis key前缀\n  keyPrefix:\n  # 线程池数量\n  threads: 4\n  # Netty线程池数量\n  nettyThreads: 8\n  # 单节点配置\n  singleServerConfig:\n    # 客户端名称\n    clientName: ${spring.application.name}\n    # 最小空闲连接数\n    connectionMinimumIdleSize: 8\n    # 连接池大小\n    connectionPoolSize: 32\n    # 连接空闲超时，单位：毫秒\n    idleConnectionTimeout: 10000\n    # 命令等待超时，单位：毫秒\n    timeout: 3000\n    # 发布和订阅连接池大小\n    subscriptionConnectionPoolSize: 50\n\n# 分布式锁 lock4j 全局配置\nlock4j:\n  # 获取分布式锁超时时间，默认为 3000 毫秒\n  acquire-timeout: 3000\n  # 分布式锁的超时时间，默认为 30 秒\n  expire: 30000\n\n# 暴露监控端点\nmanagement:\n  endpoints:\n    web:\n      exposure:\n        include: \'*\'\n  endpoint:\n    health:\n      show-details: ALWAYS\n    logfile:\n      external-file: ./logs/${spring.application.name}/console.log\n\n# 日志配置\nlogging:\n  level:\n    org.springframework: warn\n    org.apache.dubbo: warn\n    com.alibaba.nacos: warn\n    com.alibaba.cloud.sentinel: warn\n    org.mybatis.spring.mapper: error\n    org.apache.dubbo.config: error\n    # 临时处理 spring 调整日志级别导致启动警告问题 不影响使用等待 alibaba 适配\n    org.springframework.context.support.PostProcessorRegistrationDelegate: error\n  config: classpath:logback-plus.xml\n\n# Sa-Token配置\nsa-token:\n  # token名称 (同时也是cookie名称)\n  token-name: Authorization\n  # 开启内网服务调用鉴权(不允许越过gateway访问内网服务 保障服务安全)\n  check-same-token: true\n  # 是否允许同一账号并发登录 (为true时允许一起登录, 为false时新登录挤掉旧登录)\n  is-concurrent: true\n  # 在多人登录同一账号时，是否共用一个token (为true时所有登录共用一个token, 为false时每次登录新建一个token)\n  is-share: false\n  # jwt秘钥\n  jwt-secret-key: abcdefghijklmnopqrstuvwxyz\n\n# MyBatisPlus配置\n# https://baomidou.com/config/\nmybatis-plus:\n  # 多包名使用 例如 com.cdzeroly.**.mapper,org.xxx.**.mapper\n  mapperPackage: com.cdzeroly.**.mapper\n  # 对应的 XML 文件位置\n  mapperLocations: classpath*:mapper/**/*Mapper.xml\n  # 实体扫描，多个package用逗号或者分号分隔\n  typeAliasesPackage: com.cdzeroly.**.domain\n  global-config:\n    dbConfig:\n      # 主键类型\n      # AUTO 自增 NONE 空 INPUT 用户输入 ASSIGN_ID 雪花 ASSIGN_UUID 唯一 UUID\n      # 如需改为自增 需要将数据库表全部设置为自增\n      idType: ASSIGN_ID\n\n# 数据加密\nmybatis-encryptor:\n  # 是否开启加密\n  enable: false\n  # 默认加密算法\n  algorithm: BASE64\n  # 编码方式 BASE64/HEX。默认BASE64\n  encode: BASE64\n  # 安全秘钥 对称算法的秘钥 如：AES，SM4\n  password:\n  # 公私钥 非对称算法的公私钥 如：SM2，RSA\n  publicKey:\n  privateKey:\n\n# api接口加密\napi-decrypt:\n  # 是否开启全局接口加密\n  enabled: true\n  # AES 加密头标识\n  headerFlag: encrypt-key\n  # 响应加密公钥 非对称算法的公私钥 如：SM2，RSA 使用者请自行更换\n  # 对应前端解密私钥 MIIBVAIBADANBgkqhkiG9w0BAQEFAASCAT4wggE6AgEAAkEAmc3CuPiGL/LcIIm7zryCEIbl1SPzBkr75E2VMtxegyZ1lYRD+7TZGAPkvIsBcaMs6Nsy0L78n2qh+lIZMpLH8wIDAQABAkEAk82Mhz0tlv6IVCyIcw/s3f0E+WLmtPFyR9/WtV3Y5aaejUkU60JpX4m5xNR2VaqOLTZAYjW8Wy0aXr3zYIhhQQIhAMfqR9oFdYw1J9SsNc+CrhugAvKTi0+BF6VoL6psWhvbAiEAxPPNTmrkmrXwdm/pQQu3UOQmc2vCZ5tiKpW10CgJi8kCIFGkL6utxw93Ncj4exE/gPLvKcT+1Emnoox+O9kRXss5AiAMtYLJDaLEzPrAWcZeeSgSIzbL+ecokmFKSDDcRske6QIgSMkHedwND1olF8vlKsJUGK3BcdtM8w4Xq7BpSBwsloE=\n  publicKey: MFwwDQYJKoZIhvcNAQEBBQADSwAwSAJBAJnNwrj4hi/y3CCJu868ghCG5dUj8wZK++RNlTLcXoMmdZWEQ/u02RgD5LyLAXGjLOjbMtC+/J9qofpSGTKSx/MCAwEAAQ==\n  # 请求解密私钥 非对称算法的公私钥 如：SM2，RSA 使用者请自行更换\n  # 对应前端加密公钥 MFwwDQYJKoZIhvcNAQEBBQADSwAwSAJBAKoR8mX0rGKLqzcWmOzbfj64K8ZIgOdHnzkXSOVOZbFu/TJhZ7rFAN+eaGkl3C4buccQd/EjEsj9ir7ijT7h96MCAwEAAQ==\n  privateKey: MIIBVAIBADANBgkqhkiG9w0BAQEFAASCAT4wggE6AgEAAkEAqhHyZfSsYourNxaY7Nt+PrgrxkiA50efORdI5U5lsW79MmFnusUA355oaSXcLhu5xxB38SMSyP2KvuKNPuH3owIDAQABAkAfoiLyL+Z4lf4Myxk6xUDgLaWGximj20CUf+5BKKnlrK+Ed8gAkM0HqoTt2UZwA5E2MzS4EI2gjfQhz5X28uqxAiEA3wNFxfrCZlSZHb0gn2zDpWowcSxQAgiCstxGUoOqlW8CIQDDOerGKH5OmCJ4Z21v+F25WaHYPxCFMvwxpcw99EcvDQIgIdhDTIqD2jfYjPTY8Jj3EDGPbH2HHuffvflECt3Ek60CIQCFRlCkHpi7hthhYhovyloRYsM+IS9h/0BzlEAuO0ktMQIgSPT3aFAgJYwKpqRYKlLDVcflZFCKY7u3UP8iWi1Qw0Y=\n\n# 防止XSS攻击\nxss:\n  enabled: true\n  excludeUrls:\n    - /system/notice\n    - /workflow/model/save\n    - /workflow/model/editModelXml\n\n# 接口文档配置\nspringdoc:\n  api-docs:\n    # 是否开启接口文档\n    enabled: true\n#  swagger-ui:\n#    # 持久化认证数据\n#    persistAuthorization: true\n  info:\n    # 标题\n    title: \'标题：RuoYi-Cloud-Plus微服务权限管理系统_接口文档\'\n    # 描述\n    description: \'描述：微服务权限管理系统, 具体包括XXX,XXX模块...\'\n    # 版本\n    version: \'版本号：系统版本...\'\n    # 作者信息\n    contact:\n      name: Lion Li\n      email: crazylionli@163.com\n      url: https://gitee.com/dromara/RuoYi-Cloud-Plus\n  components:\n    # 鉴权方式配置\n    security-schemes:\n      apiKey:\n        type: APIKEY\n        in: HEADER\n        name: ${sa-token.token-name}\n\n# seata配置\nseata:\n  # 是否启用\n  enabled: true\n  # Seata 应用编号，默认为应用名\n  application-id: ${spring.application.name}\n  # Seata 事务组编号，用于 TC 集群名\n  tx-service-group: ${spring.application.name}-group\n\n# 多租户配置\ntenant:\n  # 是否开启\n  enable: true\n  # 排除表\n  excludes:\n    - sys_menu\n    - sys_tenant\n    - sys_tenant_package\n    - sys_role_dept\n    - sys_role_menu\n    - sys_user_post\n    - sys_user_role\n    - sys_client\n    - sys_oss_config\n','9ce0cd9136de4b8f9c92fb37051f4bd4','2024-12-05 15:14:22','2024-12-05 07:14:23','nacos','192.168.1.19','D','prod',''),(116,56,'datasource.yml','DEFAULT_GROUP','','datasource:\n  system-master:\n    # jdbc 所有参数配置参考 https://lionli.blog.csdn.net/article/details/122018562\n    # rewriteBatchedStatements=true 批处理优化 大幅提升批量插入更新删除性能\n    url: jdbc:mysql://localhost:3306/ry-cloud?useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&useSSL=true&serverTimezone=GMT%2B8&rewriteBatchedStatements=true&allowPublicKeyRetrieval=true\n    username: root\n    password: root\n  gen:\n    url: jdbc:mysql://localhost:3306/ry-cloud?useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&useSSL=true&serverTimezone=GMT%2B8&rewriteBatchedStatements=true&allowPublicKeyRetrieval=true\n    username: root\n    password: root\n  job:\n    url: jdbc:mysql://localhost:3306/ry-job?useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&useSSL=true&serverTimezone=GMT%2B8&rewriteBatchedStatements=true&allowPublicKeyRetrieval=true\n    username: root\n    password: root\n  workflow:\n    url: jdbc:mysql://localhost:3306/ry-workflow?useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&useSSL=true&serverTimezone=GMT%2B8&rewriteBatchedStatements=true&allowPublicKeyRetrieval=true&nullCatalogMeansCurrent=true\n    username: root\n    password: root\n#  system-oracle:\n#    url: jdbc:oracle:thin:@//localhost:1521/XE\n#    username: ROOT\n#    password: password\n#  system-postgres:\n#    url: jdbc:postgresql://localhost:5432/postgres?useUnicode=true&characterEncoding=utf8&useSSL=true&autoReconnect=true&reWriteBatchedInserts=true\n#    username: root\n#    password: password\n\nspring:\n  datasource:\n    type: com.zaxxer.hikari.HikariDataSource\n    # 动态数据源文档 https://www.kancloud.cn/tracy5546/dynamic-datasource/content\n    dynamic:\n      # 性能分析插件(有性能损耗 不建议生产环境使用)\n      p6spy: true\n      # 开启seata代理，开启后默认每个数据源都代理，如果某个不需要代理可单独关闭\n      seata: ${seata.enabled}\n      # 严格模式 匹配不到数据源则报错\n      strict: true\n      hikari:\n        # 最大连接池数量\n        maxPoolSize: 20\n        # 最小空闲线程数量\n        minIdle: 10\n        # 配置获取连接等待超时的时间\n        connectionTimeout: 30000\n        # 校验超时时间\n        validationTimeout: 5000\n        # 空闲连接存活最大时间，默认10分钟\n        idleTimeout: 600000\n        # 此属性控制池中连接的最长生命周期，值0表示无限生命周期，默认30分钟\n        maxLifetime: 1800000\n        # 多久检查一次连接的活性\n        keepaliveTime: 30000\n','ceb67fe7f6e6a92955d0ac164c3f8805','2024-12-05 15:14:22','2024-12-05 07:14:23','nacos','192.168.1.19','D','prod',''),(117,57,'sentinel-ruoyi-gateway.json','DEFAULT_GROUP','','[\n  {\n    \"resource\": \"ruoyi-auth\",\n    \"count\": 500,\n    \"grade\": 1,\n    \"limitApp\": \"default\",\n    \"strategy\": 0,\n    \"controlBehavior\": 0\n  },\n  {\n    \"resource\": \"ruoyi-system\",\n    \"count\": 1000,\n    \"grade\": 1,\n    \"limitApp\": \"default\",\n    \"strategy\": 0,\n    \"controlBehavior\": 0\n  },\n  {\n    \"resource\": \"ruoyi-resource\",\n    \"count\": 500,\n    \"grade\": 1,\n    \"limitApp\": \"default\",\n    \"strategy\": 0,\n    \"controlBehavior\": 0\n  }\n]\n','2efad3702e99adef46c2ee818b3925e5','2024-12-05 15:14:22','2024-12-05 07:14:23','nacos','192.168.1.19','D','prod',''),(118,58,'seata-server.properties','DEFAULT_GROUP','','service.vgroupMapping.ruoyi-auth-group=default\nservice.vgroupMapping.ruoyi-system-group=default\nservice.vgroupMapping.ruoyi-resource-group=default\nservice.vgroupMapping.ruoyi-workflow-group=default\n\nservice.enableDegrade=false\nservice.disableGlobalTransaction=false\n\n#Transaction storage configuration, only for the server. The file, DB, and redis configuration values are optional.\nstore.mode=db\nstore.lock.mode=db\nstore.session.mode=db\n#Used for password encryption\n#store.publicKey=\n\n#These configurations are required if the `store mode` is `db`. If `store.mode,store.lock.mode,store.session.mode` are not equal to `db`, you can remove the configuration block.\nstore.db.datasource=hikari\nstore.db.dbType=mysql\nstore.db.driverClassName=com.mysql.cj.jdbc.Driver\nstore.db.url=jdbc:mysql://127.0.0.1:3306/ry-seata?useUnicode=true&rewriteBatchedStatements=true&allowPublicKeyRetrieval=true\nstore.db.user=root\nstore.db.password=ruoyi123\nstore.db.minConn=5\nstore.db.maxConn=30\nstore.db.globalTable=global_table\nstore.db.branchTable=branch_table\nstore.db.distributedLockTable=distributed_lock\nstore.db.queryLimit=100\nstore.db.lockTable=lock_table\nstore.db.maxWait=5000\n\n# redis 模式 store.mode=redis 开启 (控制台查询功能有限,不影响实际执行功能)\n# store.redis.host=127.0.0.1\n# store.redis.port=6379\n# 最大连接数\n# store.redis.maxConn=10\n# 最小连接数\n# store.redis.minConn=1\n# store.redis.database=0\n# store.redis.password=\n# store.redis.queryLimit=100\n\n#Transaction rule configuration, only for the server\nserver.recovery.committingRetryPeriod=1000\nserver.recovery.asynCommittingRetryPeriod=1000\nserver.recovery.rollbackingRetryPeriod=1000\nserver.recovery.timeoutRetryPeriod=1000\nserver.maxCommitRetryTimeout=-1\nserver.maxRollbackRetryTimeout=-1\nserver.rollbackRetryTimeoutUnlockEnable=false\nserver.distributedLockExpireTime=10000\nserver.xaerNotaRetryTimeout=60000\nserver.session.branchAsyncQueueSize=5000\nserver.session.enableBranchAsyncRemove=false\n\n#Transaction rule configuration, only for the client\nclient.rm.asyncCommitBufferLimit=10000\nclient.rm.lock.retryInterval=10\nclient.rm.lock.retryTimes=30\nclient.rm.lock.retryPolicyBranchRollbackOnConflict=true\nclient.rm.reportRetryCount=5\nclient.rm.tableMetaCheckEnable=true\nclient.rm.tableMetaCheckerInterval=60000\nclient.rm.sqlParserType=druid\nclient.rm.reportSuccessEnable=false\nclient.rm.sagaBranchRegisterEnable=false\nclient.rm.sagaJsonParser=fastjson\nclient.rm.tccActionInterceptorOrder=-2147482648\nclient.tm.commitRetryCount=5\nclient.tm.rollbackRetryCount=5\nclient.tm.defaultGlobalTransactionTimeout=60000\nclient.tm.degradeCheck=false\nclient.tm.degradeCheckAllowTimes=10\nclient.tm.degradeCheckPeriod=2000\nclient.tm.interceptorOrder=-2147482648\nclient.undo.dataValidation=true\nclient.undo.logSerialization=jackson\nclient.undo.onlyCareUpdateColumns=true\nserver.undo.logSaveDays=7\nserver.undo.logDeletePeriod=86400000\nclient.undo.logTable=undo_log\nclient.undo.compress.enable=true\nclient.undo.compress.type=zip\nclient.undo.compress.threshold=64k\n\n#For TCC transaction mode\ntcc.fence.logTableName=tcc_fence_log\ntcc.fence.cleanPeriod=1h\n\n#Log rule configuration, for client and server\nlog.exceptionRate=100\n\n#Metrics configuration, only for the server\nmetrics.enabled=false\nmetrics.registryType=compact\nmetrics.exporterList=prometheus\nmetrics.exporterPrometheusPort=9898\n\n#For details about configuration items, see https://seata.io/zh-cn/docs/user/configurations.html\n#Transport configuration, for client and server\ntransport.type=TCP\ntransport.server=NIO\ntransport.heartbeat=true\ntransport.enableTmClientBatchSendRequest=false\ntransport.enableRmClientBatchSendRequest=true\ntransport.enableTcServerBatchSendResponse=false\ntransport.rpcRmRequestTimeout=30000\ntransport.rpcTmRequestTimeout=30000\ntransport.rpcTcRequestTimeout=30000\ntransport.threadFactory.bossThreadPrefix=NettyBoss\ntransport.threadFactory.workerThreadPrefix=NettyServerNIOWorker\ntransport.threadFactory.serverExecutorThreadPrefix=NettyServerBizHandler\ntransport.threadFactory.shareBossWorker=false\ntransport.threadFactory.clientSelectorThreadPrefix=NettyClientSelector\ntransport.threadFactory.clientSelectorThreadSize=1\ntransport.threadFactory.clientWorkerThreadPrefix=NettyClientWorkerThread\ntransport.threadFactory.bossThreadSize=1\ntransport.threadFactory.workerThreadSize=default\ntransport.shutdown.wait=3\ntransport.serialization=seata\ntransport.compressor=none\n','12f47aed8a463f862da4944fee7f5ad2','2024-12-05 15:14:22','2024-12-05 07:14:23','nacos','192.168.1.19','D','prod',''),(121,59,'ruoyi-gateway.yml','DEFAULT_GROUP','','# 安全配置\nsecurity:\n  # 不校验白名单\n  ignore:\n    whites:\n      - /auth/code\n      - /auth/logout\n      - /auth/login\n      - /auth/binding/*\n      - /auth/social/callback\n      - /auth/register\n      - /auth/tenant/list\n      - /resource/sms/code\n      - /resource/sse/close\n      - /*/v3/api-docs\n      - /*/error\n      - /csrf\n\nspring:\n  cloud:\n    # 网关配置\n    gateway:\n      # 打印请求日志(自定义)\n      requestLog: true\n      discovery:\n        locator:\n          lowerCaseServiceId: true\n          enabled: true\n      routes:\n        # 认证中心\n        - id: auth\n          uri: lb://auth\n          predicates:\n            - Path=/auth/**\n          filters:\n            - StripPrefix=1\n        # 代码生成\n        - id: gen\n          uri: lb://gen\n          predicates:\n            - Path=/tool/**\n          filters:\n            - StripPrefix=1\n        # 系统模块\n        - id: system\n          uri: lb://system\n          predicates:\n            - Path=/system/**,/monitor/**\n          filters:\n            - StripPrefix=1\n        # 资源服务\n        - id: resource\n          uri: lb://resource\n          predicates:\n            - Path=/resource/**\n          filters:\n            - StripPrefix=1\n        # workflow服务\n        - id: workflow\n          uri: lb://workflow\n          predicates:\n            - Path=/workflow/**\n          filters:\n            - StripPrefix=1\n        # 演示服务\n        - id: demo\n          uri: lb://demo\n          predicates:\n            - Path=/demo/**\n          filters:\n            - StripPrefix=1\n        # MQ演示服务\n        - id: test-mq\n          uri: lb://test-mq\n          predicates:\n            - Path=/test-mq/**\n          filters:\n            - StripPrefix=1\n\n    # sentinel 配置\n    sentinel:\n      filter:\n        enabled: false\n      # nacos配置持久化\n      datasource:\n        ds1:\n          nacos:\n            server-addr: ${spring.cloud.nacos.server-addr}\n            dataId: sentinel-${spring.application.name}.json\n            groupId: ${spring.cloud.nacos.config.group}\n            username: ${spring.cloud.nacos.username}\n            password: ${spring.cloud.nacos.password}\n            namespace: ${spring.profiles.active}\n            data-type: json\n            rule-type: gw-flow\n','f8943720504ecace1ff47cc4e9e31efc','2024-12-05 15:14:22','2024-12-05 07:14:23','nacos','192.168.1.19','D','prod',''),(122,60,'ruoyi-auth.yml','DEFAULT_GROUP','','# 安全配置\nsecurity:\n  # 验证码\n  captcha:\n    # 是否开启验证码\n    enabled: true\n    # 验证码类型 math 数组计算 char 字符验证\n    type: MATH\n    # line 线段干扰 circle 圆圈干扰 shear 扭曲干扰\n    category: CIRCLE\n    # 数字验证码位数\n    numberLength: 1\n    # 字符验证码长度\n    charLength: 4\n\n# 用户配置\nuser:\n  password:\n    # 密码最大错误次数\n    maxRetryCount: 5\n    # 密码锁定时间（默认10分钟）\n    lockTime: 10\n\n# 三方授权\njustauth:\n  # 前端外网访问地址\n  address: http://localhost:80\n  type:\n    maxkey:\n      # maxkey 服务器地址\n      # 注意 如下均配置均不需要修改 maxkey 已经内置好了数据\n      server-url: http://sso.maxkey.top\n      client-id: 876892492581044224\n      client-secret: x1Y5MTMwNzIwMjMxNTM4NDc3Mzche8\n      redirect-uri: ${justauth.address}/social-callback?source=maxkey\n    topiam:\n      # topiam 服务器地址\n      server-url: http://127.0.0.1:1989/api/v1/authorize/y0q************spq***********8ol\n      client-id: 449c4*********937************759\n      client-secret: ac7***********1e0************28d\n      redirect-uri: ${justauth.address}/social-callback?source=topiam\n      scopes: [ openid, email, phone, profile ]\n    qq:\n      client-id: 10**********6\n      client-secret: 1f7d08**********5b7**********29e\n      redirect-uri: ${justauth.address}/social-callback?source=qq\n      union-id: false\n    weibo:\n      client-id: 10**********6\n      client-secret: 1f7d08**********5b7**********29e\n      redirect-uri: ${justauth.address}/social-callback?source=weibo\n    gitee:\n      client-id: 91436b7940090d09c72c7daf85b959cfd5f215d67eea73acbf61b6b590751a98\n      client-secret: 02c6fcfd70342980cd8dd2f2c06c1a350645d76c754d7a264c4e125f9ba915ac\n      redirect-uri: ${justauth.address}/social-callback?source=gitee\n    dingtalk:\n      client-id: 10**********6\n      client-secret: 1f7d08**********5b7**********29e\n      redirect-uri: ${justauth.address}/social-callback?source=dingtalk\n    baidu:\n      client-id: 10**********6\n      client-secret: 1f7d08**********5b7**********29e\n      redirect-uri: ${justauth.address}/social-callback?source=baidu\n    csdn:\n      client-id: 10**********6\n      client-secret: 1f7d08**********5b7**********29e\n      redirect-uri: ${justauth.address}/social-callback?source=csdn\n    coding:\n      client-id: 10**********6\n      client-secret: 1f7d08**********5b7**********29e\n      redirect-uri: ${justauth.address}/social-callback?source=coding\n      coding-group-name: xx\n    oschina:\n      client-id: 10**********6\n      client-secret: 1f7d08**********5b7**********29e\n      redirect-uri: ${justauth.address}/social-callback?source=oschina\n    alipay_wallet:\n      client-id: 10**********6\n      client-secret: 1f7d08**********5b7**********29e\n      redirect-uri: ${justauth.address}/social-callback?source=alipay_wallet\n      alipay-public-key: MIIB**************DAQAB\n    wechat_open:\n      client-id: 10**********6\n      client-secret: 1f7d08**********5b7**********29e\n      redirect-uri: ${justauth.address}/social-callback?source=wechat_open\n    wechat_mp:\n      client-id: 10**********6\n      client-secret: 1f7d08**********5b7**********29e\n      redirect-uri: ${justauth.address}/social-callback?source=wechat_mp\n    wechat_enterprise:\n      client-id: 10**********6\n      client-secret: 1f7d08**********5b7**********29e\n      redirect-uri: ${justauth.address}/social-callback?source=wechat_enterprise\n      agent-id: 1000002\n    gitlab:\n      client-id: 10**********6\n      client-secret: 1f7d08**********5b7**********29e\n      redirect-uri: ${justauth.address}/social-callback?source=gitlab\n','362c28d8864626b864d682b5f1c4a73a','2024-12-05 15:14:22','2024-12-05 07:14:23','nacos','192.168.1.19','D','prod',''),(123,61,'ruoyi-monitor.yml','DEFAULT_GROUP','','# 监控中心配置\nspring:\n  security:\n    user:\n      name: ruoyi\n      password: 123456\n  boot:\n    admin:\n      ui:\n        title: RuoYi-Cloud-Plus服务监控中心\n      discovery:\n        # seata 不具有健康检查的能力 防止报错排除掉\n        ignored-services: ruoyi-seata-server\n','ba7fa6a6bfde3dcb3b566fd8864834af','2024-12-05 15:14:22','2024-12-05 07:14:23','nacos','192.168.1.19','D','prod',''),(124,62,'ruoyi-system.yml','DEFAULT_GROUP','','spring:\n  datasource:\n    dynamic:\n      # 设置默认的数据源或者数据源组,默认值即为 master\n      primary: master\n      datasource:\n        # 主库数据源\n        master:\n          type: ${spring.datasource.type}\n          driver-class-name: com.mysql.cj.jdbc.Driver\n          url: ${datasource.system-master.url}\n          username: ${datasource.system-master.username}\n          password: ${datasource.system-master.password}\n#        oracle:\n#          type: ${spring.datasource.type}\n#          driverClassName: oracle.jdbc.OracleDriver\n#          url: ${datasource.system-oracle.url}\n#          username: ${datasource.system-oracle.username}\n#          password: ${datasource.system-oracle.password}\n#        postgres:\n#          type: ${spring.datasource.type}\n#          driverClassName: org.postgresql.Driver\n#          url: ${datasource.system-postgres.url}\n#          username: ${datasource.system-postgres.username}\n#          password: ${datasource.system-postgres.password}\n','f59259409ff41d30e5276bf4afbcaa3d','2024-12-05 15:14:22','2024-12-05 07:14:23','nacos','192.168.1.19','D','prod',''),(125,63,'ruoyi-gen.yml','DEFAULT_GROUP','','spring:\n  datasource:\n    dynamic:\n      # 设置默认的数据源或者数据源组,默认值即为 master\n      primary: master\n      seata: false\n      datasource:\n        # 主库数据源\n        master:\n          type: ${spring.datasource.type}\n          driver-class-name: com.mysql.cj.jdbc.Driver\n          url: ${datasource.system-master.url}\n          username: ${datasource.system-master.username}\n          password: ${datasource.system-master.password}\n#        oracle:\n#          type: ${spring.datasource.type}\n#          driverClassName: oracle.jdbc.OracleDriver\n#          url: ${datasource.system-oracle.url}\n#          username: ${datasource.system-oracle.username}\n#          password: ${datasource.system-oracle.password}\n#        postgres:\n#          type: ${spring.datasource.type}\n#          driverClassName: org.postgresql.Driver\n#          url: ${datasource.system-postgres.url}\n#          username: ${datasource.system-postgres.username}\n#          password: ${datasource.system-postgres.password}\n\n# 代码生成\ngen:\n  # 作者\n  author: MGARY\n  # 默认生成包路径 system 需改成自己的模块名称 如 system monitor tool\n  packageName: com.cdzeroly.system\n  # 自动去除表前缀，默认是false\n  autoRemovePre: false\n  # 表前缀（生成类名不会包含表前缀，多个用逗号分隔）\n  tablePrefix: sys_\n','f02376e215be7e735427461290ca100e','2024-12-05 15:14:22','2024-12-05 07:14:23','nacos','192.168.1.19','D','prod',''),(126,64,'ruoyi-job.yml','DEFAULT_GROUP','','spring:\n  datasource:\n    dynamic:\n      # 设置默认的数据源或者数据源组,默认值即为 master\n      primary: master\n      seata: false\n      datasource:\n        # 主库数据源\n        master:\n          type: ${spring.datasource.type}\n          driver-class-name: com.mysql.cj.jdbc.Driver\n          url: ${datasource.job.url}\n          username: ${datasource.job.username}\n          password: ${datasource.job.password}\n\nsnail-job:\n  enabled: true\n  # 需要在 SnailJob 后台组管理创建对应名称的组,然后创建任务的时候选择对应的组,才能正确分派任务\n  group: \"base_platform_group\"\n  #  SnailJob 接入验证令牌\n  token: \"SJ_cKqBTPzCsWA3VyuCfFoccmuIEGXjr5KT\"\n  server:\n    # 从 nacos 获取服务\n    server-name: snailjob-server\n    # 服务名优先 ip垫底\n    host: 127.0.0.1\n    port: 17888\n  # 详见 script/sql/ry_job.sql `sj_namespace` 表\n  namespace: ${spring.profiles.active}\n  # 随主应用端口飘逸\n  port: 2${server.port}\n  # 客户端ip指定\n  host:\n','58b664a7d062e5983172cdc562e37f00','2024-12-05 15:14:22','2024-12-05 07:14:23','nacos','192.168.1.19','D','prod',''),(127,65,'ruoyi-resource.yml','DEFAULT_GROUP','','spring:\n  datasource:\n    dynamic:\n      # 设置默认的数据源或者数据源组,默认值即为 master\n      primary: master\n      datasource:\n        # 主库数据源\n        master:\n          type: ${spring.datasource.type}\n          driver-class-name: com.mysql.cj.jdbc.Driver\n          url: ${datasource.system-master.url}\n          username: ${datasource.system-master.username}\n          password: ${datasource.system-master.password}\n#        oracle:\n#          type: ${spring.datasource.type}\n#          driverClassName: oracle.jdbc.OracleDriver\n#          url: ${datasource.system-oracle.url}\n#          username: ${datasource.system-oracle.username}\n#          password: ${datasource.system-oracle.password}\n#        postgres:\n#          type: ${spring.datasource.type}\n#          driverClassName: org.postgresql.Driver\n#          url: ${datasource.system-postgres.url}\n#          username: ${datasource.system-postgres.username}\n#          password: ${datasource.system-postgres.password}\n\n# 默认/推荐使用sse推送\nsse:\n  enabled: true\n  path: /sse\n\nwebsocket:\n  # 如果关闭 需要和前端开关一起关闭\n  enabled: false\n  # 路径\n  path: /websocket\n  # 设置访问源地址\n  allowedOrigins: \'*\'\n\nmail:\n  enabled: false\n  host: smtp.163.com\n  port: 465\n  # 是否需要用户名密码验证\n  auth: true\n  # 发送方，遵循RFC-822标准\n  from: xxx@163.com\n  # 用户名（注意：如果使用foxmail邮箱，此处user为qq号）\n  user: xxx@163.com\n  # 密码（注意，某些邮箱需要为SMTP服务单独设置密码，详情查看相关帮助）\n  pass: xxxxxxxxxx\n  # 使用 STARTTLS安全连接，STARTTLS是对纯文本通信协议的扩展。\n  starttlsEnable: true\n  # 使用SSL安全连接\n  sslEnable: true\n  # SMTP超时时长，单位毫秒，缺省值不超时\n  timeout: 0\n  # Socket连接超时值，单位毫秒，缺省值不超时\n  connectionTimeout: 0\n\n# sms 短信 支持 阿里云 腾讯云 云片 等等各式各样的短信服务商\n# https://sms4j.com/doc3/ 差异配置文档地址 支持单厂商多配置，可以配置多个同时使用\nsms:\n  # 配置源类型用于标定配置来源(interface,yaml)\n  config-type: yaml\n  # 用于标定yml中的配置是否开启短信拦截，接口配置不受此限制\n  restricted: true\n  # 短信拦截限制单手机号每分钟最大发送，只对开启了拦截的配置有效\n  minute-max: 1\n  # 短信拦截限制单手机号每日最大发送量，只对开启了拦截的配置有效\n  account-max: 30\n  # 以下配置来自于 org.dromara.sms4j.provider.config.BaseConfig类中\n  blends:\n    # 唯一ID 用于发送短信寻找具体配置 随便定义别用中文即可\n    # 可以同时存在两个相同厂商 例如: ali1 ali2 两个不同的阿里短信账号 也可用于区分租户\n    config1:\n      # 框架定义的厂商名称标识，标定此配置是哪个厂商，详细请看厂商标识介绍部分\n      supplier: alibaba\n      # 有些称为accessKey有些称之为apiKey，也有称为sdkKey或者appId。\n      access-key-id: 您的accessKey\n      # 称为accessSecret有些称之为apiSecret\n      access-key-secret: 您的accessKeySecret\n      signature: 您的短信签名\n      sdk-app-id: 您的sdkAppId\n    config2:\n      # 厂商标识，标定此配置是哪个厂商，详细请看厂商标识介绍部分\n      supplier: tencent\n      access-key-id: 您的accessKey\n      access-key-secret: 您的accessKeySecret\n      signature: 您的短信签名\n      sdk-app-id: 您的sdkAppId\n','efb16000eb230328fa2128ea56558fd2','2024-12-05 15:14:25','2024-12-05 07:14:25','nacos','192.168.1.19','D','prod',''),(128,66,'ruoyi-workflow.yml','DEFAULT_GROUP','','spring:\n  datasource:\n    dynamic:\n      # 设置默认的数据源或者数据源组,默认值即为 master\n      primary: master\n      datasource:\n        # 主库数据源\n        master:\n          type: ${spring.datasource.type}\n          driver-class-name: com.mysql.cj.jdbc.Driver\n          url: ${datasource.workflow.url}\n          username: ${datasource.workflow.username}\n          password: ${datasource.workflow.password}\n#        oracle:\n#          type: ${spring.datasource.type}\n#          driverClassName: oracle.jdbc.OracleDriver\n#          url: ${datasource.system-oracle.url}\n#          username: ${datasource.system-oracle.username}\n#          password: ${datasource.system-oracle.password}\n#        postgres:\n#          type: ${spring.datasource.type}\n#          driverClassName: org.postgresql.Driver\n#          url: ${datasource.system-postgres.url}\n#          username: ${datasource.system-postgres.username}\n#          password: ${datasource.system-postgres.password}\n\n# flowable配置\nflowable:\n  # 开关 用于启动/停用工作流\n  enabled: true\n  process.enabled: ${flowable.enabled}\n  eventregistry.enabled: ${flowable.enabled}\n  # 关闭定时任务JOB\n  async-executor-activate: false\n  # 将databaseSchemaUpdate设置为true。当Flowable发现库与数据库表结构不一致时，会自动将数据库表结构升级至新版本。\n  database-schema-update: true\n  activity-font-name: 宋体\n  label-font-name: 宋体\n  annotation-font-name: 宋体\n  # 关闭各个模块生成表，目前只使用工作流基础表\n  idm:\n    enabled: false\n  cmmn:\n    enabled: false\n  dmn:\n    enabled: false\n  app:\n    enabled: false\n','95b566892b838da618fbfdaf1ecffd0a','2024-12-05 15:14:25','2024-12-05 07:14:25','nacos','192.168.1.19','D','prod',''),(131,67,'ruoyi-sentinel-dashboard.yml','DEFAULT_GROUP','','spring:\n  mvc:\n    pathmatch:\n      # 修复 sentinel 控制台未适配 springboot 2.6 新路由方式\n      matching-strategy: ANT_PATH_MATCHER\n\nserver:\n  servlet:\n    encoding:\n      force: true\n      charset: UTF-8\n      enabled: true\n    session:\n      cookie:\n        name: sentinel_dashboard_cookie\n\nlogging:\n  level:\n    org.springframework.web: INFO\n\nauth:\n  enabled: true\n  filter:\n    exclude-urls: /,/auth/login,/auth/logout,/registry/machine,/version,/actuator,/actuator/**\n    exclude-url-suffixes: htm,html,js,css,map,ico,ttf,woff,png\n  username: sentinel\n  password: sentinel\n','59055747c62f08cd2c38a6016d4b9227','2024-12-05 15:14:25','2024-12-05 07:14:25','nacos','192.168.1.19','D','prod',''),(132,68,'ruoyi-snailjob-server.yml','DEFAULT_GROUP','','spring:\n  datasource:\n    type: com.zaxxer.hikari.HikariDataSource\n    driver-class-name: com.mysql.cj.jdbc.Driver\n    url: ${datasource.job.url}\n    username: ${datasource.job.username}\n    password: ${datasource.job.password}\n    hikari:\n      connection-timeout: 30000\n      validation-timeout: 5000\n      minimum-idle: 10\n      maximum-pool-size: 20\n      idle-timeout: 600000\n      max-lifetime: 900000\n      keepaliveTime: 30000\n  cloud:\n    nacos:\n      discovery:\n        metadata:\n          # 解决 er 服务有 context-path 无法监控问题\n          management.context-path: ${server.servlet.context-path}/actuator\n          # 监控账号密码\n          username: ruoyi\n          userpassword: 123456\n\n# snail-job 服务端配置\nsnail-job:\n  # 拉取重试数据的每批次的大小\n  retry-pull-page-size: 1000\n  # 拉取重试数据的每批次的大小\n  job-pull-page-size: 1000\n  # 服务端 netty 端口\n  netty-port: 17888\n  # 重试和死信表的分区总数\n  total-partition: 2\n  # 一个客户端每秒最多接收的重试数量指令\n  limiter: 1000\n  # 号段模式下步长配置\n  step: 100\n  # 日志保存时间(单位: day)\n  log-storage: 90\n  # 回调配置\n  callback:\n    #回调最大执行次数\n    max-count: 288\n    #间隔时间\n    trigger-interval: 900\n  retry-max-pull-count: 10\n','ab06186a47974fd09806e48484f37a1a','2024-12-05 15:14:25','2024-12-05 07:14:25','nacos','192.168.1.19','D','prod',''),(0,69,'application-common.yml','DEFAULT_GROUP','','server:\n  # undertow 配置\n  undertow:\n    # HTTP post内容的最大大小。当值为-1时，默认值为大小是无限的\n    max-http-post-size: -1\n    # 以下的配置会影响buffer,这些buffer会用于服务器连接的IO操作,有点类似netty的池化内存管理\n    # 每块buffer的空间大小,越小的空间被利用越充分\n    buffer-size: 512\n    # 是否分配的直接内存\n    direct-buffers: true\n    threads:\n      # 设置IO线程数, 它主要执行非阻塞的任务,它们会负责多个连接, 默认设置每个CPU核心一个线程\n      io: 8\n      # 阻塞任务线程池, 当执行类似servlet请求阻塞操作, undertow会从这个线程池中取得线程,它的值设置取决于系统的负载\n      worker: 256\n\ndubbo:\n  application:\n    # 关闭qos端口避免单机多生产者端口冲突 如需使用自行开启\n    qos-enable: false\n  protocol:\n    # 如需使用 Triple 3.0 新协议 可查看官方文档\n    # 使用 dubbo 协议通信\n    name: dubbo\n    # dubbo 协议端口(-1表示自增端口,从20880开始)\n    port: -1\n    # 指定dubbo协议注册ip\n    # host: 192.168.0.100\n  # 消费者相关配置\n  consumer:\n    # 超时时间\n    timeout: 3000\n  # 自定义配置\n  custom:\n    # 全局请求log\n    request-log: true\n    # info 基础信息 param 参数信息 full 全部\n    log-level: info\n\nspring:\n  threads:\n    # 开启虚拟线程 仅jdk21可用\n    virtual:\n      enabled: false\n  # 资源信息\n  messages:\n    # 国际化资源文件路径\n    basename: i18n/messages\n  servlet:\n    multipart:\n      # 整个请求大小限制\n      max-request-size: 20MB\n      # 上传单个文件大小限制\n      max-file-size: 10MB\n  mvc:\n    # 设置静态资源路径 防止所有请求都去查静态资源\n    static-path-pattern: /static/**\n    format:\n      date-time: yyyy-MM-dd HH:mm:ss\n  #jackson配置\n  jackson:\n    # 日期格式化\n    date-format: yyyy-MM-dd HH:mm:ss\n    serialization:\n      # 格式化输出\n      INDENT_OUTPUT: false\n      # 忽略无法转换的对象\n      fail_on_empty_beans: false\n    deserialization:\n      # 允许对象忽略json中不存在的属性\n      fail_on_unknown_properties: false\n  cloud:\n    nacos:\n      discovery:\n        metadata:\n          # admin 监控账号密码\n          username: ruoyi\n          userpassword: 123456\n    # sentinel 配置\n    sentinel:\n      # sentinel 开关\n      enabled: true\n      transport:\n        # dashboard控制台服务名 用于服务发现\n        # 如无此配置将默认使用下方 dashboard 配置直接注册\n        server-name: ruoyi-sentinel-dashboard\n        # 客户端指定注册的ip 用于多网卡ip不稳点使用\n        # client-ip:\n        # 控制台地址 从1.3.0开始使用 server-name 注册\n        # dashboard: localhost:8718\n\n    bus:\n      id: ${spring.application.name}\n      base-packages: com.cdzeroly.**.event\n  # 消息总线 也可以使用 kafka 参考 spring-cloud-bus 用法\n  rabbitmq:\n    host: localhost\n    port: 5672\n    username: ruoyi\n    password: ruoyi123\n\n  # redis通用配置 子服务可以自行配置进行覆盖\n  data:\n    redis:\n      host: localhost\n      port: 6379\n      # redis 密码必须配置\n      password: ruoyi123\n      database: 0\n      # 需要使用数字\n      timeout: 10000\n      ssl.enabled: false\n\n# redisson 配置\nredisson:\n  # redis key前缀\n  keyPrefix:\n  # 线程池数量\n  threads: 4\n  # Netty线程池数量\n  nettyThreads: 8\n  # 单节点配置\n  singleServerConfig:\n    # 客户端名称\n    clientName: ${spring.application.name}\n    # 最小空闲连接数\n    connectionMinimumIdleSize: 8\n    # 连接池大小\n    connectionPoolSize: 32\n    # 连接空闲超时，单位：毫秒\n    idleConnectionTimeout: 10000\n    # 命令等待超时，单位：毫秒\n    timeout: 3000\n    # 发布和订阅连接池大小\n    subscriptionConnectionPoolSize: 50\n\n# 分布式锁 lock4j 全局配置\nlock4j:\n  # 获取分布式锁超时时间，默认为 3000 毫秒\n  acquire-timeout: 3000\n  # 分布式锁的超时时间，默认为 30 秒\n  expire: 30000\n\n# 暴露监控端点\nmanagement:\n  endpoints:\n    web:\n      exposure:\n        include: \'*\'\n  endpoint:\n    health:\n      show-details: ALWAYS\n    logfile:\n      external-file: ./logs/${spring.application.name}/console.log\n\n# 日志配置\nlogging:\n  level:\n    org.springframework: warn\n    org.apache.dubbo: warn\n    com.alibaba.nacos: warn\n    com.alibaba.cloud.sentinel: warn\n    org.mybatis.spring.mapper: error\n    org.apache.dubbo.config: error\n    # 临时处理 spring 调整日志级别导致启动警告问题 不影响使用等待 alibaba 适配\n    org.springframework.context.support.PostProcessorRegistrationDelegate: error\n  config: classpath:logback-plus.xml\n\n# Sa-Token配置\nsa-token:\n  # token名称 (同时也是cookie名称)\n  token-name: Authorization\n  # 开启内网服务调用鉴权(不允许越过gateway访问内网服务 保障服务安全)\n  check-same-token: true\n  # 是否允许同一账号并发登录 (为true时允许一起登录, 为false时新登录挤掉旧登录)\n  is-concurrent: true\n  # 在多人登录同一账号时，是否共用一个token (为true时所有登录共用一个token, 为false时每次登录新建一个token)\n  is-share: false\n  # jwt秘钥\n  jwt-secret-key: abcdefghijklmnopqrstuvwxyz\n\n# MyBatisPlus配置\n# https://baomidou.com/config/\nmybatis-plus:\n  # 多包名使用 例如 com.cdzeroly.**.mapper,org.xxx.**.mapper\n  mapperPackage: com.cdzeroly.**.mapper\n  # 对应的 XML 文件位置\n  mapperLocations: classpath*:mapper/**/*Mapper.xml\n  # 实体扫描，多个package用逗号或者分号分隔\n  typeAliasesPackage: com.cdzeroly.**.domain\n  global-config:\n    dbConfig:\n      # 主键类型\n      # AUTO 自增 NONE 空 INPUT 用户输入 ASSIGN_ID 雪花 ASSIGN_UUID 唯一 UUID\n      # 如需改为自增 需要将数据库表全部设置为自增\n      idType: ASSIGN_ID\n\n# 数据加密\nmybatis-encryptor:\n  # 是否开启加密\n  enable: false\n  # 默认加密算法\n  algorithm: BASE64\n  # 编码方式 BASE64/HEX。默认BASE64\n  encode: BASE64\n  # 安全秘钥 对称算法的秘钥 如：AES，SM4\n  password:\n  # 公私钥 非对称算法的公私钥 如：SM2，RSA\n  publicKey:\n  privateKey:\n\n# api接口加密\napi-decrypt:\n  # 是否开启全局接口加密\n  enabled: true\n  # AES 加密头标识\n  headerFlag: encrypt-key\n  # 响应加密公钥 非对称算法的公私钥 如：SM2，RSA 使用者请自行更换\n  # 对应前端解密私钥 MIIBVAIBADANBgkqhkiG9w0BAQEFAASCAT4wggE6AgEAAkEAmc3CuPiGL/LcIIm7zryCEIbl1SPzBkr75E2VMtxegyZ1lYRD+7TZGAPkvIsBcaMs6Nsy0L78n2qh+lIZMpLH8wIDAQABAkEAk82Mhz0tlv6IVCyIcw/s3f0E+WLmtPFyR9/WtV3Y5aaejUkU60JpX4m5xNR2VaqOLTZAYjW8Wy0aXr3zYIhhQQIhAMfqR9oFdYw1J9SsNc+CrhugAvKTi0+BF6VoL6psWhvbAiEAxPPNTmrkmrXwdm/pQQu3UOQmc2vCZ5tiKpW10CgJi8kCIFGkL6utxw93Ncj4exE/gPLvKcT+1Emnoox+O9kRXss5AiAMtYLJDaLEzPrAWcZeeSgSIzbL+ecokmFKSDDcRske6QIgSMkHedwND1olF8vlKsJUGK3BcdtM8w4Xq7BpSBwsloE=\n  publicKey: MFwwDQYJKoZIhvcNAQEBBQADSwAwSAJBAJnNwrj4hi/y3CCJu868ghCG5dUj8wZK++RNlTLcXoMmdZWEQ/u02RgD5LyLAXGjLOjbMtC+/J9qofpSGTKSx/MCAwEAAQ==\n  # 请求解密私钥 非对称算法的公私钥 如：SM2，RSA 使用者请自行更换\n  # 对应前端加密公钥 MFwwDQYJKoZIhvcNAQEBBQADSwAwSAJBAKoR8mX0rGKLqzcWmOzbfj64K8ZIgOdHnzkXSOVOZbFu/TJhZ7rFAN+eaGkl3C4buccQd/EjEsj9ir7ijT7h96MCAwEAAQ==\n  privateKey: MIIBVAIBADANBgkqhkiG9w0BAQEFAASCAT4wggE6AgEAAkEAqhHyZfSsYourNxaY7Nt+PrgrxkiA50efORdI5U5lsW79MmFnusUA355oaSXcLhu5xxB38SMSyP2KvuKNPuH3owIDAQABAkAfoiLyL+Z4lf4Myxk6xUDgLaWGximj20CUf+5BKKnlrK+Ed8gAkM0HqoTt2UZwA5E2MzS4EI2gjfQhz5X28uqxAiEA3wNFxfrCZlSZHb0gn2zDpWowcSxQAgiCstxGUoOqlW8CIQDDOerGKH5OmCJ4Z21v+F25WaHYPxCFMvwxpcw99EcvDQIgIdhDTIqD2jfYjPTY8Jj3EDGPbH2HHuffvflECt3Ek60CIQCFRlCkHpi7hthhYhovyloRYsM+IS9h/0BzlEAuO0ktMQIgSPT3aFAgJYwKpqRYKlLDVcflZFCKY7u3UP8iWi1Qw0Y=\n\n# 防止XSS攻击\nxss:\n  enabled: true\n  excludeUrls:\n    - /system/notice\n    - /workflow/model/save\n    - /workflow/model/editModelXml\n\n# 接口文档配置\nspringdoc:\n  api-docs:\n    # 是否开启接口文档\n    enabled: true\n#  swagger-ui:\n#    # 持久化认证数据\n#    persistAuthorization: true\n  info:\n    # 标题\n    title: \'标题：RuoYi-Cloud-Plus微服务权限管理系统_接口文档\'\n    # 描述\n    description: \'描述：微服务权限管理系统, 具体包括XXX,XXX模块...\'\n    # 版本\n    version: \'版本号：系统版本...\'\n    # 作者信息\n    contact:\n      name: Lion Li\n      email: crazylionli@163.com\n      url: https://gitee.com/dromara/RuoYi-Cloud-Plus\n  components:\n    # 鉴权方式配置\n    security-schemes:\n      apiKey:\n        type: APIKEY\n        in: HEADER\n        name: ${sa-token.token-name}\n\n# seata配置\nseata:\n  # 是否启用\n  enabled: true\n  # Seata 应用编号，默认为应用名\n  application-id: ${spring.application.name}\n  # Seata 事务组编号，用于 TC 集群名\n  tx-service-group: ${spring.application.name}-group\n\n# 多租户配置\ntenant:\n  # 是否开启\n  enable: true\n  # 排除表\n  excludes:\n    - sys_menu\n    - sys_tenant\n    - sys_tenant_package\n    - sys_role_dept\n    - sys_role_menu\n    - sys_user_post\n    - sys_user_role\n    - sys_client\n    - sys_oss_config\n','9ce0cd9136de4b8f9c92fb37051f4bd4','2024-12-05 15:16:49','2024-12-05 07:16:50',NULL,'192.168.1.19','I','prod',''),(0,70,'datasource.yml','DEFAULT_GROUP','','datasource:\n  system-master:\n    # jdbc 所有参数配置参考 https://lionli.blog.csdn.net/article/details/122018562\n    # rewriteBatchedStatements=true 批处理优化 大幅提升批量插入更新删除性能\n    url: jdbc:mysql://localhost:3306/ry-cloud?useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&useSSL=true&serverTimezone=GMT%2B8&rewriteBatchedStatements=true&allowPublicKeyRetrieval=true\n    username: root\n    password: root\n  gen:\n    url: jdbc:mysql://localhost:3306/ry-cloud?useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&useSSL=true&serverTimezone=GMT%2B8&rewriteBatchedStatements=true&allowPublicKeyRetrieval=true\n    username: root\n    password: root\n  job:\n    url: jdbc:mysql://localhost:3306/ry-job?useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&useSSL=true&serverTimezone=GMT%2B8&rewriteBatchedStatements=true&allowPublicKeyRetrieval=true\n    username: root\n    password: root\n  workflow:\n    url: jdbc:mysql://localhost:3306/ry-workflow?useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&useSSL=true&serverTimezone=GMT%2B8&rewriteBatchedStatements=true&allowPublicKeyRetrieval=true&nullCatalogMeansCurrent=true\n    username: root\n    password: root\n#  system-oracle:\n#    url: jdbc:oracle:thin:@//localhost:1521/XE\n#    username: ROOT\n#    password: password\n#  system-postgres:\n#    url: jdbc:postgresql://localhost:5432/postgres?useUnicode=true&characterEncoding=utf8&useSSL=true&autoReconnect=true&reWriteBatchedInserts=true\n#    username: root\n#    password: password\n\nspring:\n  datasource:\n    type: com.zaxxer.hikari.HikariDataSource\n    # 动态数据源文档 https://www.kancloud.cn/tracy5546/dynamic-datasource/content\n    dynamic:\n      # 性能分析插件(有性能损耗 不建议生产环境使用)\n      p6spy: true\n      # 开启seata代理，开启后默认每个数据源都代理，如果某个不需要代理可单独关闭\n      seata: ${seata.enabled}\n      # 严格模式 匹配不到数据源则报错\n      strict: true\n      hikari:\n        # 最大连接池数量\n        maxPoolSize: 20\n        # 最小空闲线程数量\n        minIdle: 10\n        # 配置获取连接等待超时的时间\n        connectionTimeout: 30000\n        # 校验超时时间\n        validationTimeout: 5000\n        # 空闲连接存活最大时间，默认10分钟\n        idleTimeout: 600000\n        # 此属性控制池中连接的最长生命周期，值0表示无限生命周期，默认30分钟\n        maxLifetime: 1800000\n        # 多久检查一次连接的活性\n        keepaliveTime: 30000\n','ceb67fe7f6e6a92955d0ac164c3f8805','2024-12-05 15:16:49','2024-12-05 07:16:50',NULL,'192.168.1.19','I','prod',''),(0,71,'gateway.yml','DEFAULT_GROUP','','# 安全配置\nsecurity:\n  # 不校验白名单\n  ignore:\n    whites:\n      - /auth/code\n      - /auth/logout\n      - /auth/login\n      - /auth/binding/*\n      - /auth/social/callback\n      - /auth/register\n      - /auth/tenant/list\n      - /resource/sms/code\n      - /resource/sse/close\n      - /*/v3/api-docs\n      - /*/error\n      - /csrf\n\nspring:\n  cloud:\n    # 网关配置\n    gateway:\n      # 打印请求日志(自定义)\n      requestLog: true\n      discovery:\n        locator:\n          lowerCaseServiceId: true\n          enabled: true\n      routes:\n        # 认证中心\n        - id: auth\n          uri: lb://auth\n          predicates:\n            - Path=/auth/**\n          filters:\n            - StripPrefix=1\n        # 代码生成\n        - id: gen\n          uri: lb://gen\n          predicates:\n            - Path=/tool/**\n          filters:\n            - StripPrefix=1\n        # 系统模块\n        - id: system\n          uri: lb://system\n          predicates:\n            - Path=/system/**,/monitor/**\n          filters:\n            - StripPrefix=1\n        # 资源服务\n        - id: resource\n          uri: lb://resource\n          predicates:\n            - Path=/resource/**\n          filters:\n            - StripPrefix=1\n        # workflow服务\n        - id: workflow\n          uri: lb://workflow\n          predicates:\n            - Path=/workflow/**\n          filters:\n            - StripPrefix=1\n        # 演示服务\n        - id: demo\n          uri: lb://demo\n          predicates:\n            - Path=/demo/**\n          filters:\n            - StripPrefix=1\n        # MQ演示服务\n        - id: test-mq\n          uri: lb://test-mq\n          predicates:\n            - Path=/test-mq/**\n          filters:\n            - StripPrefix=1\n\n    # sentinel 配置\n    sentinel:\n      filter:\n        enabled: false\n      # nacos配置持久化\n      datasource:\n        ds1:\n          nacos:\n            server-addr: ${spring.cloud.nacos.server-addr}\n            dataId: sentinel-${spring.application.name}.json\n            groupId: ${spring.cloud.nacos.config.group}\n            username: ${spring.cloud.nacos.username}\n            password: ${spring.cloud.nacos.password}\n            namespace: ${spring.profiles.active}\n            data-type: json\n            rule-type: gw-flow\n','f8943720504ecace1ff47cc4e9e31efc','2024-12-05 15:16:50','2024-12-05 07:16:50',NULL,'192.168.1.19','I','prod',''),(0,72,'auth.yml','DEFAULT_GROUP','','# 安全配置\nsecurity:\n  # 验证码\n  captcha:\n    # 是否开启验证码\n    enabled: true\n    # 验证码类型 math 数组计算 char 字符验证\n    type: MATH\n    # line 线段干扰 circle 圆圈干扰 shear 扭曲干扰\n    category: CIRCLE\n    # 数字验证码位数\n    numberLength: 1\n    # 字符验证码长度\n    charLength: 4\n\n# 用户配置\nuser:\n  password:\n    # 密码最大错误次数\n    maxRetryCount: 5\n    # 密码锁定时间（默认10分钟）\n    lockTime: 10\n\n# 三方授权\njustauth:\n  # 前端外网访问地址\n  address: http://localhost:80\n  type:\n    maxkey:\n      # maxkey 服务器地址\n      # 注意 如下均配置均不需要修改 maxkey 已经内置好了数据\n      server-url: http://sso.maxkey.top\n      client-id: 876892492581044224\n      client-secret: x1Y5MTMwNzIwMjMxNTM4NDc3Mzche8\n      redirect-uri: ${justauth.address}/social-callback?source=maxkey\n    topiam:\n      # topiam 服务器地址\n      server-url: http://127.0.0.1:1989/api/v1/authorize/y0q************spq***********8ol\n      client-id: 449c4*********937************759\n      client-secret: ac7***********1e0************28d\n      redirect-uri: ${justauth.address}/social-callback?source=topiam\n      scopes: [ openid, email, phone, profile ]\n    qq:\n      client-id: 10**********6\n      client-secret: 1f7d08**********5b7**********29e\n      redirect-uri: ${justauth.address}/social-callback?source=qq\n      union-id: false\n    weibo:\n      client-id: 10**********6\n      client-secret: 1f7d08**********5b7**********29e\n      redirect-uri: ${justauth.address}/social-callback?source=weibo\n    gitee:\n      client-id: 91436b7940090d09c72c7daf85b959cfd5f215d67eea73acbf61b6b590751a98\n      client-secret: 02c6fcfd70342980cd8dd2f2c06c1a350645d76c754d7a264c4e125f9ba915ac\n      redirect-uri: ${justauth.address}/social-callback?source=gitee\n    dingtalk:\n      client-id: 10**********6\n      client-secret: 1f7d08**********5b7**********29e\n      redirect-uri: ${justauth.address}/social-callback?source=dingtalk\n    baidu:\n      client-id: 10**********6\n      client-secret: 1f7d08**********5b7**********29e\n      redirect-uri: ${justauth.address}/social-callback?source=baidu\n    csdn:\n      client-id: 10**********6\n      client-secret: 1f7d08**********5b7**********29e\n      redirect-uri: ${justauth.address}/social-callback?source=csdn\n    coding:\n      client-id: 10**********6\n      client-secret: 1f7d08**********5b7**********29e\n      redirect-uri: ${justauth.address}/social-callback?source=coding\n      coding-group-name: xx\n    oschina:\n      client-id: 10**********6\n      client-secret: 1f7d08**********5b7**********29e\n      redirect-uri: ${justauth.address}/social-callback?source=oschina\n    alipay_wallet:\n      client-id: 10**********6\n      client-secret: 1f7d08**********5b7**********29e\n      redirect-uri: ${justauth.address}/social-callback?source=alipay_wallet\n      alipay-public-key: MIIB**************DAQAB\n    wechat_open:\n      client-id: 10**********6\n      client-secret: 1f7d08**********5b7**********29e\n      redirect-uri: ${justauth.address}/social-callback?source=wechat_open\n    wechat_mp:\n      client-id: 10**********6\n      client-secret: 1f7d08**********5b7**********29e\n      redirect-uri: ${justauth.address}/social-callback?source=wechat_mp\n    wechat_enterprise:\n      client-id: 10**********6\n      client-secret: 1f7d08**********5b7**********29e\n      redirect-uri: ${justauth.address}/social-callback?source=wechat_enterprise\n      agent-id: 1000002\n    gitlab:\n      client-id: 10**********6\n      client-secret: 1f7d08**********5b7**********29e\n      redirect-uri: ${justauth.address}/social-callback?source=gitlab\n','362c28d8864626b864d682b5f1c4a73a','2024-12-05 15:16:50','2024-12-05 07:16:50',NULL,'192.168.1.19','I','prod',''),(0,73,'monitor.yml','DEFAULT_GROUP','','# 监控中心配置\nspring:\n  security:\n    user:\n      name: ruoyi\n      password: 123456\n  boot:\n    admin:\n      ui:\n        title: RuoYi-Cloud-Plus服务监控中心\n      discovery:\n        # seata 不具有健康检查的能力 防止报错排除掉\n        ignored-services: ruoyi-seata-server\n','ba7fa6a6bfde3dcb3b566fd8864834af','2024-12-05 15:16:50','2024-12-05 07:16:50',NULL,'192.168.1.19','I','prod',''),(0,74,'system.yml','DEFAULT_GROUP','','spring:\n  datasource:\n    dynamic:\n      # 设置默认的数据源或者数据源组,默认值即为 master\n      primary: master\n      datasource:\n        # 主库数据源\n        master:\n          type: ${spring.datasource.type}\n          driver-class-name: com.mysql.cj.jdbc.Driver\n          url: ${datasource.system-master.url}\n          username: ${datasource.system-master.username}\n          password: ${datasource.system-master.password}\n#        oracle:\n#          type: ${spring.datasource.type}\n#          driverClassName: oracle.jdbc.OracleDriver\n#          url: ${datasource.system-oracle.url}\n#          username: ${datasource.system-oracle.username}\n#          password: ${datasource.system-oracle.password}\n#        postgres:\n#          type: ${spring.datasource.type}\n#          driverClassName: org.postgresql.Driver\n#          url: ${datasource.system-postgres.url}\n#          username: ${datasource.system-postgres.username}\n#          password: ${datasource.system-postgres.password}\n','f59259409ff41d30e5276bf4afbcaa3d','2024-12-05 15:16:50','2024-12-05 07:16:51',NULL,'192.168.1.19','I','prod',''),(0,75,'gen.yml','DEFAULT_GROUP','','spring:\n  datasource:\n    dynamic:\n      # 设置默认的数据源或者数据源组,默认值即为 master\n      primary: master\n      seata: false\n      datasource:\n        # 主库数据源\n        master:\n          type: ${spring.datasource.type}\n          driver-class-name: com.mysql.cj.jdbc.Driver\n          url: ${datasource.system-master.url}\n          username: ${datasource.system-master.username}\n          password: ${datasource.system-master.password}\n#        oracle:\n#          type: ${spring.datasource.type}\n#          driverClassName: oracle.jdbc.OracleDriver\n#          url: ${datasource.system-oracle.url}\n#          username: ${datasource.system-oracle.username}\n#          password: ${datasource.system-oracle.password}\n#        postgres:\n#          type: ${spring.datasource.type}\n#          driverClassName: org.postgresql.Driver\n#          url: ${datasource.system-postgres.url}\n#          username: ${datasource.system-postgres.username}\n#          password: ${datasource.system-postgres.password}\n\n# 代码生成\ngen:\n  # 作者\n  author: MGARY\n  # 默认生成包路径 system 需改成自己的模块名称 如 system monitor tool\n  packageName: com.cdzeroly.system\n  # 自动去除表前缀，默认是false\n  autoRemovePre: false\n  # 表前缀（生成类名不会包含表前缀，多个用逗号分隔）\n  tablePrefix: sys_\n','f02376e215be7e735427461290ca100e','2024-12-05 15:16:50','2024-12-05 07:16:51',NULL,'192.168.1.19','I','prod',''),(0,76,'job.yml','DEFAULT_GROUP','','spring:\n  datasource:\n    dynamic:\n      # 设置默认的数据源或者数据源组,默认值即为 master\n      primary: master\n      seata: false\n      datasource:\n        # 主库数据源\n        master:\n          type: ${spring.datasource.type}\n          driver-class-name: com.mysql.cj.jdbc.Driver\n          url: ${datasource.job.url}\n          username: ${datasource.job.username}\n          password: ${datasource.job.password}\n\nsnail-job:\n  enabled: true\n  # 需要在 SnailJob 后台组管理创建对应名称的组,然后创建任务的时候选择对应的组,才能正确分派任务\n  group: \"base_platform_group\"\n  #  SnailJob 接入验证令牌\n  token: \"SJ_cKqBTPzCsWA3VyuCfFoccmuIEGXjr5KT\"\n  server:\n    # 从 nacos 获取服务\n    server-name: snailjob-server\n    # 服务名优先 ip垫底\n    host: 127.0.0.1\n    port: 17888\n  # 详见 script/sql/ry_job.sql `sj_namespace` 表\n  namespace: ${spring.profiles.active}\n  # 随主应用端口飘逸\n  port: 2${server.port}\n  # 客户端ip指定\n  host:\n','58b664a7d062e5983172cdc562e37f00','2024-12-05 15:16:51','2024-12-05 07:16:51',NULL,'192.168.1.19','I','prod',''),(0,77,'resource.yml','DEFAULT_GROUP','','spring:\n  datasource:\n    dynamic:\n      # 设置默认的数据源或者数据源组,默认值即为 master\n      primary: master\n      datasource:\n        # 主库数据源\n        master:\n          type: ${spring.datasource.type}\n          driver-class-name: com.mysql.cj.jdbc.Driver\n          url: ${datasource.system-master.url}\n          username: ${datasource.system-master.username}\n          password: ${datasource.system-master.password}\n#        oracle:\n#          type: ${spring.datasource.type}\n#          driverClassName: oracle.jdbc.OracleDriver\n#          url: ${datasource.system-oracle.url}\n#          username: ${datasource.system-oracle.username}\n#          password: ${datasource.system-oracle.password}\n#        postgres:\n#          type: ${spring.datasource.type}\n#          driverClassName: org.postgresql.Driver\n#          url: ${datasource.system-postgres.url}\n#          username: ${datasource.system-postgres.username}\n#          password: ${datasource.system-postgres.password}\n\n# 默认/推荐使用sse推送\nsse:\n  enabled: true\n  path: /sse\n\nwebsocket:\n  # 如果关闭 需要和前端开关一起关闭\n  enabled: false\n  # 路径\n  path: /websocket\n  # 设置访问源地址\n  allowedOrigins: \'*\'\n\nmail:\n  enabled: false\n  host: smtp.163.com\n  port: 465\n  # 是否需要用户名密码验证\n  auth: true\n  # 发送方，遵循RFC-822标准\n  from: xxx@163.com\n  # 用户名（注意：如果使用foxmail邮箱，此处user为qq号）\n  user: xxx@163.com\n  # 密码（注意，某些邮箱需要为SMTP服务单独设置密码，详情查看相关帮助）\n  pass: xxxxxxxxxx\n  # 使用 STARTTLS安全连接，STARTTLS是对纯文本通信协议的扩展。\n  starttlsEnable: true\n  # 使用SSL安全连接\n  sslEnable: true\n  # SMTP超时时长，单位毫秒，缺省值不超时\n  timeout: 0\n  # Socket连接超时值，单位毫秒，缺省值不超时\n  connectionTimeout: 0\n\n# sms 短信 支持 阿里云 腾讯云 云片 等等各式各样的短信服务商\n# https://sms4j.com/doc3/ 差异配置文档地址 支持单厂商多配置，可以配置多个同时使用\nsms:\n  # 配置源类型用于标定配置来源(interface,yaml)\n  config-type: yaml\n  # 用于标定yml中的配置是否开启短信拦截，接口配置不受此限制\n  restricted: true\n  # 短信拦截限制单手机号每分钟最大发送，只对开启了拦截的配置有效\n  minute-max: 1\n  # 短信拦截限制单手机号每日最大发送量，只对开启了拦截的配置有效\n  account-max: 30\n  # 以下配置来自于 org.dromara.sms4j.provider.config.BaseConfig类中\n  blends:\n    # 唯一ID 用于发送短信寻找具体配置 随便定义别用中文即可\n    # 可以同时存在两个相同厂商 例如: ali1 ali2 两个不同的阿里短信账号 也可用于区分租户\n    config1:\n      # 框架定义的厂商名称标识，标定此配置是哪个厂商，详细请看厂商标识介绍部分\n      supplier: alibaba\n      # 有些称为accessKey有些称之为apiKey，也有称为sdkKey或者appId。\n      access-key-id: 您的accessKey\n      # 称为accessSecret有些称之为apiSecret\n      access-key-secret: 您的accessKeySecret\n      signature: 您的短信签名\n      sdk-app-id: 您的sdkAppId\n    config2:\n      # 厂商标识，标定此配置是哪个厂商，详细请看厂商标识介绍部分\n      supplier: tencent\n      access-key-id: 您的accessKey\n      access-key-secret: 您的accessKeySecret\n      signature: 您的短信签名\n      sdk-app-id: 您的sdkAppId\n','efb16000eb230328fa2128ea56558fd2','2024-12-05 15:16:51','2024-12-05 07:16:51',NULL,'192.168.1.19','I','prod',''),(0,78,'workflow.yml','DEFAULT_GROUP','','spring:\n  datasource:\n    dynamic:\n      # 设置默认的数据源或者数据源组,默认值即为 master\n      primary: master\n      datasource:\n        # 主库数据源\n        master:\n          type: ${spring.datasource.type}\n          driver-class-name: com.mysql.cj.jdbc.Driver\n          url: ${datasource.workflow.url}\n          username: ${datasource.workflow.username}\n          password: ${datasource.workflow.password}\n#        oracle:\n#          type: ${spring.datasource.type}\n#          driverClassName: oracle.jdbc.OracleDriver\n#          url: ${datasource.system-oracle.url}\n#          username: ${datasource.system-oracle.username}\n#          password: ${datasource.system-oracle.password}\n#        postgres:\n#          type: ${spring.datasource.type}\n#          driverClassName: org.postgresql.Driver\n#          url: ${datasource.system-postgres.url}\n#          username: ${datasource.system-postgres.username}\n#          password: ${datasource.system-postgres.password}\n\n# flowable配置\nflowable:\n  # 开关 用于启动/停用工作流\n  enabled: true\n  process.enabled: ${flowable.enabled}\n  eventregistry.enabled: ${flowable.enabled}\n  # 关闭定时任务JOB\n  async-executor-activate: false\n  # 将databaseSchemaUpdate设置为true。当Flowable发现库与数据库表结构不一致时，会自动将数据库表结构升级至新版本。\n  database-schema-update: true\n  activity-font-name: 宋体\n  label-font-name: 宋体\n  annotation-font-name: 宋体\n  # 关闭各个模块生成表，目前只使用工作流基础表\n  idm:\n    enabled: false\n  cmmn:\n    enabled: false\n  dmn:\n    enabled: false\n  app:\n    enabled: false\n','95b566892b838da618fbfdaf1ecffd0a','2024-12-05 15:16:51','2024-12-05 07:16:51',NULL,'192.168.1.19','I','prod',''),(0,79,'seata-server.properties','DEFAULT_GROUP','','service.vgroupMapping.ruoyi-auth-group=default\nservice.vgroupMapping.ruoyi-system-group=default\nservice.vgroupMapping.ruoyi-resource-group=default\nservice.vgroupMapping.ruoyi-workflow-group=default\n\nservice.enableDegrade=false\nservice.disableGlobalTransaction=false\n\n#Transaction storage configuration, only for the server. The file, DB, and redis configuration values are optional.\nstore.mode=db\nstore.lock.mode=db\nstore.session.mode=db\n#Used for password encryption\n#store.publicKey=\n\n#These configurations are required if the `store mode` is `db`. If `store.mode,store.lock.mode,store.session.mode` are not equal to `db`, you can remove the configuration block.\nstore.db.datasource=hikari\nstore.db.dbType=mysql\nstore.db.driverClassName=com.mysql.cj.jdbc.Driver\nstore.db.url=jdbc:mysql://127.0.0.1:3306/ry-seata?useUnicode=true&rewriteBatchedStatements=true&allowPublicKeyRetrieval=true\nstore.db.user=root\nstore.db.password=ruoyi123\nstore.db.minConn=5\nstore.db.maxConn=30\nstore.db.globalTable=global_table\nstore.db.branchTable=branch_table\nstore.db.distributedLockTable=distributed_lock\nstore.db.queryLimit=100\nstore.db.lockTable=lock_table\nstore.db.maxWait=5000\n\n# redis 模式 store.mode=redis 开启 (控制台查询功能有限,不影响实际执行功能)\n# store.redis.host=127.0.0.1\n# store.redis.port=6379\n# 最大连接数\n# store.redis.maxConn=10\n# 最小连接数\n# store.redis.minConn=1\n# store.redis.database=0\n# store.redis.password=\n# store.redis.queryLimit=100\n\n#Transaction rule configuration, only for the server\nserver.recovery.committingRetryPeriod=1000\nserver.recovery.asynCommittingRetryPeriod=1000\nserver.recovery.rollbackingRetryPeriod=1000\nserver.recovery.timeoutRetryPeriod=1000\nserver.maxCommitRetryTimeout=-1\nserver.maxRollbackRetryTimeout=-1\nserver.rollbackRetryTimeoutUnlockEnable=false\nserver.distributedLockExpireTime=10000\nserver.xaerNotaRetryTimeout=60000\nserver.session.branchAsyncQueueSize=5000\nserver.session.enableBranchAsyncRemove=false\n\n#Transaction rule configuration, only for the client\nclient.rm.asyncCommitBufferLimit=10000\nclient.rm.lock.retryInterval=10\nclient.rm.lock.retryTimes=30\nclient.rm.lock.retryPolicyBranchRollbackOnConflict=true\nclient.rm.reportRetryCount=5\nclient.rm.tableMetaCheckEnable=true\nclient.rm.tableMetaCheckerInterval=60000\nclient.rm.sqlParserType=druid\nclient.rm.reportSuccessEnable=false\nclient.rm.sagaBranchRegisterEnable=false\nclient.rm.sagaJsonParser=fastjson\nclient.rm.tccActionInterceptorOrder=-2147482648\nclient.tm.commitRetryCount=5\nclient.tm.rollbackRetryCount=5\nclient.tm.defaultGlobalTransactionTimeout=60000\nclient.tm.degradeCheck=false\nclient.tm.degradeCheckAllowTimes=10\nclient.tm.degradeCheckPeriod=2000\nclient.tm.interceptorOrder=-2147482648\nclient.undo.dataValidation=true\nclient.undo.logSerialization=jackson\nclient.undo.onlyCareUpdateColumns=true\nserver.undo.logSaveDays=7\nserver.undo.logDeletePeriod=86400000\nclient.undo.logTable=undo_log\nclient.undo.compress.enable=true\nclient.undo.compress.type=zip\nclient.undo.compress.threshold=64k\n\n#For TCC transaction mode\ntcc.fence.logTableName=tcc_fence_log\ntcc.fence.cleanPeriod=1h\n\n#Log rule configuration, for client and server\nlog.exceptionRate=100\n\n#Metrics configuration, only for the server\nmetrics.enabled=false\nmetrics.registryType=compact\nmetrics.exporterList=prometheus\nmetrics.exporterPrometheusPort=9898\n\n#For details about configuration items, see https://seata.io/zh-cn/docs/user/configurations.html\n#Transport configuration, for client and server\ntransport.type=TCP\ntransport.server=NIO\ntransport.heartbeat=true\ntransport.enableTmClientBatchSendRequest=false\ntransport.enableRmClientBatchSendRequest=true\ntransport.enableTcServerBatchSendResponse=false\ntransport.rpcRmRequestTimeout=30000\ntransport.rpcTmRequestTimeout=30000\ntransport.rpcTcRequestTimeout=30000\ntransport.threadFactory.bossThreadPrefix=NettyBoss\ntransport.threadFactory.workerThreadPrefix=NettyServerNIOWorker\ntransport.threadFactory.serverExecutorThreadPrefix=NettyServerBizHandler\ntransport.threadFactory.shareBossWorker=false\ntransport.threadFactory.clientSelectorThreadPrefix=NettyClientSelector\ntransport.threadFactory.clientSelectorThreadSize=1\ntransport.threadFactory.clientWorkerThreadPrefix=NettyClientWorkerThread\ntransport.threadFactory.bossThreadSize=1\ntransport.threadFactory.workerThreadSize=default\ntransport.shutdown.wait=3\ntransport.serialization=seata\ntransport.compressor=none\n','12f47aed8a463f862da4944fee7f5ad2','2024-12-05 15:16:51','2024-12-05 07:16:51',NULL,'192.168.1.19','I','prod',''),(0,80,'sentinel-dashboard.yml','DEFAULT_GROUP','','spring:\n  mvc:\n    pathmatch:\n      # 修复 sentinel 控制台未适配 springboot 2.6 新路由方式\n      matching-strategy: ANT_PATH_MATCHER\n\nserver:\n  servlet:\n    encoding:\n      force: true\n      charset: UTF-8\n      enabled: true\n    session:\n      cookie:\n        name: sentinel_dashboard_cookie\n\nlogging:\n  level:\n    org.springframework.web: INFO\n\nauth:\n  enabled: true\n  filter:\n    exclude-urls: /,/auth/login,/auth/logout,/registry/machine,/version,/actuator,/actuator/**\n    exclude-url-suffixes: htm,html,js,css,map,ico,ttf,woff,png\n  username: sentinel\n  password: sentinel\n','59055747c62f08cd2c38a6016d4b9227','2024-12-05 15:16:51','2024-12-05 07:16:51',NULL,'192.168.1.19','I','prod',''),(0,81,'snailjob-server.yml','DEFAULT_GROUP','','spring:\n  datasource:\n    type: com.zaxxer.hikari.HikariDataSource\n    driver-class-name: com.mysql.cj.jdbc.Driver\n    url: ${datasource.job.url}\n    username: ${datasource.job.username}\n    password: ${datasource.job.password}\n    hikari:\n      connection-timeout: 30000\n      validation-timeout: 5000\n      minimum-idle: 10\n      maximum-pool-size: 20\n      idle-timeout: 600000\n      max-lifetime: 900000\n      keepaliveTime: 30000\n  cloud:\n    nacos:\n      discovery:\n        metadata:\n          # 解决 er 服务有 context-path 无法监控问题\n          management.context-path: ${server.servlet.context-path}/actuator\n          # 监控账号密码\n          username: ruoyi\n          userpassword: 123456\n\n# snail-job 服务端配置\nsnail-job:\n  # 拉取重试数据的每批次的大小\n  retry-pull-page-size: 1000\n  # 拉取重试数据的每批次的大小\n  job-pull-page-size: 1000\n  # 服务端 netty 端口\n  netty-port: 17888\n  # 重试和死信表的分区总数\n  total-partition: 2\n  # 一个客户端每秒最多接收的重试数量指令\n  limiter: 1000\n  # 号段模式下步长配置\n  step: 100\n  # 日志保存时间(单位: day)\n  log-storage: 90\n  # 回调配置\n  callback:\n    #回调最大执行次数\n    max-count: 288\n    #间隔时间\n    trigger-interval: 900\n  retry-max-pull-count: 10\n','ab06186a47974fd09806e48484f37a1a','2024-12-05 15:16:51','2024-12-05 07:16:51',NULL,'192.168.1.19','I','prod',''),(0,82,'sentinel-gateway.json','DEFAULT_GROUP','','[\n  {\n    \"resource\": \"ruoyi-auth\",\n    \"count\": 500,\n    \"grade\": 1,\n    \"limitApp\": \"default\",\n    \"strategy\": 0,\n    \"controlBehavior\": 0\n  },\n  {\n    \"resource\": \"ruoyi-system\",\n    \"count\": 1000,\n    \"grade\": 1,\n    \"limitApp\": \"default\",\n    \"strategy\": 0,\n    \"controlBehavior\": 0\n  },\n  {\n    \"resource\": \"ruoyi-resource\",\n    \"count\": 500,\n    \"grade\": 1,\n    \"limitApp\": \"default\",\n    \"strategy\": 0,\n    \"controlBehavior\": 0\n  }\n]\n','2efad3702e99adef46c2ee818b3925e5','2024-12-05 15:19:21','2024-12-05 07:19:21',NULL,'192.168.1.19','I','prod',''),(143,83,'seata-server.properties','DEFAULT_GROUP','','service.vgroupMapping.ruoyi-auth-group=default\nservice.vgroupMapping.ruoyi-system-group=default\nservice.vgroupMapping.ruoyi-resource-group=default\nservice.vgroupMapping.ruoyi-workflow-group=default\n\nservice.enableDegrade=false\nservice.disableGlobalTransaction=false\n\n#Transaction storage configuration, only for the server. The file, DB, and redis configuration values are optional.\nstore.mode=db\nstore.lock.mode=db\nstore.session.mode=db\n#Used for password encryption\n#store.publicKey=\n\n#These configurations are required if the `store mode` is `db`. If `store.mode,store.lock.mode,store.session.mode` are not equal to `db`, you can remove the configuration block.\nstore.db.datasource=hikari\nstore.db.dbType=mysql\nstore.db.driverClassName=com.mysql.cj.jdbc.Driver\nstore.db.url=jdbc:mysql://127.0.0.1:3306/ry-seata?useUnicode=true&rewriteBatchedStatements=true&allowPublicKeyRetrieval=true\nstore.db.user=root\nstore.db.password=ruoyi123\nstore.db.minConn=5\nstore.db.maxConn=30\nstore.db.globalTable=global_table\nstore.db.branchTable=branch_table\nstore.db.distributedLockTable=distributed_lock\nstore.db.queryLimit=100\nstore.db.lockTable=lock_table\nstore.db.maxWait=5000\n\n# redis 模式 store.mode=redis 开启 (控制台查询功能有限,不影响实际执行功能)\n# store.redis.host=127.0.0.1\n# store.redis.port=6379\n# 最大连接数\n# store.redis.maxConn=10\n# 最小连接数\n# store.redis.minConn=1\n# store.redis.database=0\n# store.redis.password=\n# store.redis.queryLimit=100\n\n#Transaction rule configuration, only for the server\nserver.recovery.committingRetryPeriod=1000\nserver.recovery.asynCommittingRetryPeriod=1000\nserver.recovery.rollbackingRetryPeriod=1000\nserver.recovery.timeoutRetryPeriod=1000\nserver.maxCommitRetryTimeout=-1\nserver.maxRollbackRetryTimeout=-1\nserver.rollbackRetryTimeoutUnlockEnable=false\nserver.distributedLockExpireTime=10000\nserver.xaerNotaRetryTimeout=60000\nserver.session.branchAsyncQueueSize=5000\nserver.session.enableBranchAsyncRemove=false\n\n#Transaction rule configuration, only for the client\nclient.rm.asyncCommitBufferLimit=10000\nclient.rm.lock.retryInterval=10\nclient.rm.lock.retryTimes=30\nclient.rm.lock.retryPolicyBranchRollbackOnConflict=true\nclient.rm.reportRetryCount=5\nclient.rm.tableMetaCheckEnable=true\nclient.rm.tableMetaCheckerInterval=60000\nclient.rm.sqlParserType=druid\nclient.rm.reportSuccessEnable=false\nclient.rm.sagaBranchRegisterEnable=false\nclient.rm.sagaJsonParser=fastjson\nclient.rm.tccActionInterceptorOrder=-2147482648\nclient.tm.commitRetryCount=5\nclient.tm.rollbackRetryCount=5\nclient.tm.defaultGlobalTransactionTimeout=60000\nclient.tm.degradeCheck=false\nclient.tm.degradeCheckAllowTimes=10\nclient.tm.degradeCheckPeriod=2000\nclient.tm.interceptorOrder=-2147482648\nclient.undo.dataValidation=true\nclient.undo.logSerialization=jackson\nclient.undo.onlyCareUpdateColumns=true\nserver.undo.logSaveDays=7\nserver.undo.logDeletePeriod=86400000\nclient.undo.logTable=undo_log\nclient.undo.compress.enable=true\nclient.undo.compress.type=zip\nclient.undo.compress.threshold=64k\n\n#For TCC transaction mode\ntcc.fence.logTableName=tcc_fence_log\ntcc.fence.cleanPeriod=1h\n\n#Log rule configuration, for client and server\nlog.exceptionRate=100\n\n#Metrics configuration, only for the server\nmetrics.enabled=false\nmetrics.registryType=compact\nmetrics.exporterList=prometheus\nmetrics.exporterPrometheusPort=9898\n\n#For details about configuration items, see https://seata.io/zh-cn/docs/user/configurations.html\n#Transport configuration, for client and server\ntransport.type=TCP\ntransport.server=NIO\ntransport.heartbeat=true\ntransport.enableTmClientBatchSendRequest=false\ntransport.enableRmClientBatchSendRequest=true\ntransport.enableTcServerBatchSendResponse=false\ntransport.rpcRmRequestTimeout=30000\ntransport.rpcTmRequestTimeout=30000\ntransport.rpcTcRequestTimeout=30000\ntransport.threadFactory.bossThreadPrefix=NettyBoss\ntransport.threadFactory.workerThreadPrefix=NettyServerNIOWorker\ntransport.threadFactory.serverExecutorThreadPrefix=NettyServerBizHandler\ntransport.threadFactory.shareBossWorker=false\ntransport.threadFactory.clientSelectorThreadPrefix=NettyClientSelector\ntransport.threadFactory.clientSelectorThreadSize=1\ntransport.threadFactory.clientWorkerThreadPrefix=NettyClientWorkerThread\ntransport.threadFactory.bossThreadSize=1\ntransport.threadFactory.workerThreadSize=default\ntransport.shutdown.wait=3\ntransport.serialization=seata\ntransport.compressor=none\n','12f47aed8a463f862da4944fee7f5ad2','2024-12-05 15:54:11','2024-12-05 07:54:12','nacos','192.168.1.19','U','prod',''),(140,84,'job.yml','DEFAULT_GROUP','','spring:\n  datasource:\n    dynamic:\n      # 设置默认的数据源或者数据源组,默认值即为 master\n      primary: master\n      seata: false\n      datasource:\n        # 主库数据源\n        master:\n          type: ${spring.datasource.type}\n          driver-class-name: com.mysql.cj.jdbc.Driver\n          url: ${datasource.job.url}\n          username: ${datasource.job.username}\n          password: ${datasource.job.password}\n\nsnail-job:\n  enabled: true\n  # 需要在 SnailJob 后台组管理创建对应名称的组,然后创建任务的时候选择对应的组,才能正确分派任务\n  group: \"base_platform_group\"\n  #  SnailJob 接入验证令牌\n  token: \"SJ_cKqBTPzCsWA3VyuCfFoccmuIEGXjr5KT\"\n  server:\n    # 从 nacos 获取服务\n    server-name: snailjob-server\n    # 服务名优先 ip垫底\n    host: 127.0.0.1\n    port: 17888\n  # 详见 script/sql/ry_job.sql `sj_namespace` 表\n  namespace: ${spring.profiles.active}\n  # 随主应用端口飘逸\n  port: 2${server.port}\n  # 客户端ip指定\n  host:\n','58b664a7d062e5983172cdc562e37f00','2024-12-05 16:05:04','2024-12-05 08:05:04','nacos','192.168.1.19','U','prod',''),(137,85,'monitor.yml','DEFAULT_GROUP','','# 监控中心配置\nspring:\n  security:\n    user:\n      name: ruoyi\n      password: 123456\n  boot:\n    admin:\n      ui:\n        title: RuoYi-Cloud-Plus服务监控中心\n      discovery:\n        # seata 不具有健康检查的能力 防止报错排除掉\n        ignored-services: ruoyi-seata-server\n','ba7fa6a6bfde3dcb3b566fd8864834af','2024-12-05 16:19:35','2024-12-05 08:19:35','nacos','192.168.1.19','U','prod',''),(133,86,'application-common.yml','DEFAULT_GROUP','','server:\n  # undertow 配置\n  undertow:\n    # HTTP post内容的最大大小。当值为-1时，默认值为大小是无限的\n    max-http-post-size: -1\n    # 以下的配置会影响buffer,这些buffer会用于服务器连接的IO操作,有点类似netty的池化内存管理\n    # 每块buffer的空间大小,越小的空间被利用越充分\n    buffer-size: 512\n    # 是否分配的直接内存\n    direct-buffers: true\n    threads:\n      # 设置IO线程数, 它主要执行非阻塞的任务,它们会负责多个连接, 默认设置每个CPU核心一个线程\n      io: 8\n      # 阻塞任务线程池, 当执行类似servlet请求阻塞操作, undertow会从这个线程池中取得线程,它的值设置取决于系统的负载\n      worker: 256\n\ndubbo:\n  application:\n    # 关闭qos端口避免单机多生产者端口冲突 如需使用自行开启\n    qos-enable: false\n  protocol:\n    # 如需使用 Triple 3.0 新协议 可查看官方文档\n    # 使用 dubbo 协议通信\n    name: dubbo\n    # dubbo 协议端口(-1表示自增端口,从20880开始)\n    port: -1\n    # 指定dubbo协议注册ip\n    # host: 192.168.0.100\n  # 消费者相关配置\n  consumer:\n    # 超时时间\n    timeout: 3000\n  # 自定义配置\n  custom:\n    # 全局请求log\n    request-log: true\n    # info 基础信息 param 参数信息 full 全部\n    log-level: info\n\nspring:\n  threads:\n    # 开启虚拟线程 仅jdk21可用\n    virtual:\n      enabled: false\n  # 资源信息\n  messages:\n    # 国际化资源文件路径\n    basename: i18n/messages\n  servlet:\n    multipart:\n      # 整个请求大小限制\n      max-request-size: 20MB\n      # 上传单个文件大小限制\n      max-file-size: 10MB\n  mvc:\n    # 设置静态资源路径 防止所有请求都去查静态资源\n    static-path-pattern: /static/**\n    format:\n      date-time: yyyy-MM-dd HH:mm:ss\n  #jackson配置\n  jackson:\n    # 日期格式化\n    date-format: yyyy-MM-dd HH:mm:ss\n    serialization:\n      # 格式化输出\n      INDENT_OUTPUT: false\n      # 忽略无法转换的对象\n      fail_on_empty_beans: false\n    deserialization:\n      # 允许对象忽略json中不存在的属性\n      fail_on_unknown_properties: false\n  cloud:\n    nacos:\n      discovery:\n        metadata:\n          # admin 监控账号密码\n          username: ruoyi\n          userpassword: 123456\n    # sentinel 配置\n    sentinel:\n      # sentinel 开关\n      enabled: true\n      transport:\n        # dashboard控制台服务名 用于服务发现\n        # 如无此配置将默认使用下方 dashboard 配置直接注册\n        server-name: ruoyi-sentinel-dashboard\n        # 客户端指定注册的ip 用于多网卡ip不稳点使用\n        # client-ip:\n        # 控制台地址 从1.3.0开始使用 server-name 注册\n        # dashboard: localhost:8718\n\n    bus:\n      id: ${spring.application.name}\n      base-packages: com.cdzeroly.**.event\n  # 消息总线 也可以使用 kafka 参考 spring-cloud-bus 用法\n  rabbitmq:\n    host: localhost\n    port: 5672\n    username: ruoyi\n    password: ruoyi123\n\n  # redis通用配置 子服务可以自行配置进行覆盖\n  data:\n    redis:\n      host: localhost\n      port: 6379\n      # redis 密码必须配置\n      password: ruoyi123\n      database: 0\n      # 需要使用数字\n      timeout: 10000\n      ssl.enabled: false\n\n# redisson 配置\nredisson:\n  # redis key前缀\n  keyPrefix:\n  # 线程池数量\n  threads: 4\n  # Netty线程池数量\n  nettyThreads: 8\n  # 单节点配置\n  singleServerConfig:\n    # 客户端名称\n    clientName: ${spring.application.name}\n    # 最小空闲连接数\n    connectionMinimumIdleSize: 8\n    # 连接池大小\n    connectionPoolSize: 32\n    # 连接空闲超时，单位：毫秒\n    idleConnectionTimeout: 10000\n    # 命令等待超时，单位：毫秒\n    timeout: 3000\n    # 发布和订阅连接池大小\n    subscriptionConnectionPoolSize: 50\n\n# 分布式锁 lock4j 全局配置\nlock4j:\n  # 获取分布式锁超时时间，默认为 3000 毫秒\n  acquire-timeout: 3000\n  # 分布式锁的超时时间，默认为 30 秒\n  expire: 30000\n\n# 暴露监控端点\nmanagement:\n  endpoints:\n    web:\n      exposure:\n        include: \'*\'\n  endpoint:\n    health:\n      show-details: ALWAYS\n    logfile:\n      external-file: ./logs/${spring.application.name}/console.log\n\n# 日志配置\nlogging:\n  level:\n    org.springframework: warn\n    org.apache.dubbo: warn\n    com.alibaba.nacos: warn\n    com.alibaba.cloud.sentinel: warn\n    org.mybatis.spring.mapper: error\n    org.apache.dubbo.config: error\n    # 临时处理 spring 调整日志级别导致启动警告问题 不影响使用等待 alibaba 适配\n    org.springframework.context.support.PostProcessorRegistrationDelegate: error\n  config: classpath:logback-plus.xml\n\n# Sa-Token配置\nsa-token:\n  # token名称 (同时也是cookie名称)\n  token-name: Authorization\n  # 开启内网服务调用鉴权(不允许越过gateway访问内网服务 保障服务安全)\n  check-same-token: true\n  # 是否允许同一账号并发登录 (为true时允许一起登录, 为false时新登录挤掉旧登录)\n  is-concurrent: true\n  # 在多人登录同一账号时，是否共用一个token (为true时所有登录共用一个token, 为false时每次登录新建一个token)\n  is-share: false\n  # jwt秘钥\n  jwt-secret-key: abcdefghijklmnopqrstuvwxyz\n\n# MyBatisPlus配置\n# https://baomidou.com/config/\nmybatis-plus:\n  # 多包名使用 例如 com.cdzeroly.**.mapper,org.xxx.**.mapper\n  mapperPackage: com.cdzeroly.**.mapper\n  # 对应的 XML 文件位置\n  mapperLocations: classpath*:mapper/**/*Mapper.xml\n  # 实体扫描，多个package用逗号或者分号分隔\n  typeAliasesPackage: com.cdzeroly.**.domain\n  global-config:\n    dbConfig:\n      # 主键类型\n      # AUTO 自增 NONE 空 INPUT 用户输入 ASSIGN_ID 雪花 ASSIGN_UUID 唯一 UUID\n      # 如需改为自增 需要将数据库表全部设置为自增\n      idType: ASSIGN_ID\n\n# 数据加密\nmybatis-encryptor:\n  # 是否开启加密\n  enable: false\n  # 默认加密算法\n  algorithm: BASE64\n  # 编码方式 BASE64/HEX。默认BASE64\n  encode: BASE64\n  # 安全秘钥 对称算法的秘钥 如：AES，SM4\n  password:\n  # 公私钥 非对称算法的公私钥 如：SM2，RSA\n  publicKey:\n  privateKey:\n\n# api接口加密\napi-decrypt:\n  # 是否开启全局接口加密\n  enabled: true\n  # AES 加密头标识\n  headerFlag: encrypt-key\n  # 响应加密公钥 非对称算法的公私钥 如：SM2，RSA 使用者请自行更换\n  # 对应前端解密私钥 MIIBVAIBADANBgkqhkiG9w0BAQEFAASCAT4wggE6AgEAAkEAmc3CuPiGL/LcIIm7zryCEIbl1SPzBkr75E2VMtxegyZ1lYRD+7TZGAPkvIsBcaMs6Nsy0L78n2qh+lIZMpLH8wIDAQABAkEAk82Mhz0tlv6IVCyIcw/s3f0E+WLmtPFyR9/WtV3Y5aaejUkU60JpX4m5xNR2VaqOLTZAYjW8Wy0aXr3zYIhhQQIhAMfqR9oFdYw1J9SsNc+CrhugAvKTi0+BF6VoL6psWhvbAiEAxPPNTmrkmrXwdm/pQQu3UOQmc2vCZ5tiKpW10CgJi8kCIFGkL6utxw93Ncj4exE/gPLvKcT+1Emnoox+O9kRXss5AiAMtYLJDaLEzPrAWcZeeSgSIzbL+ecokmFKSDDcRske6QIgSMkHedwND1olF8vlKsJUGK3BcdtM8w4Xq7BpSBwsloE=\n  publicKey: MFwwDQYJKoZIhvcNAQEBBQADSwAwSAJBAJnNwrj4hi/y3CCJu868ghCG5dUj8wZK++RNlTLcXoMmdZWEQ/u02RgD5LyLAXGjLOjbMtC+/J9qofpSGTKSx/MCAwEAAQ==\n  # 请求解密私钥 非对称算法的公私钥 如：SM2，RSA 使用者请自行更换\n  # 对应前端加密公钥 MFwwDQYJKoZIhvcNAQEBBQADSwAwSAJBAKoR8mX0rGKLqzcWmOzbfj64K8ZIgOdHnzkXSOVOZbFu/TJhZ7rFAN+eaGkl3C4buccQd/EjEsj9ir7ijT7h96MCAwEAAQ==\n  privateKey: MIIBVAIBADANBgkqhkiG9w0BAQEFAASCAT4wggE6AgEAAkEAqhHyZfSsYourNxaY7Nt+PrgrxkiA50efORdI5U5lsW79MmFnusUA355oaSXcLhu5xxB38SMSyP2KvuKNPuH3owIDAQABAkAfoiLyL+Z4lf4Myxk6xUDgLaWGximj20CUf+5BKKnlrK+Ed8gAkM0HqoTt2UZwA5E2MzS4EI2gjfQhz5X28uqxAiEA3wNFxfrCZlSZHb0gn2zDpWowcSxQAgiCstxGUoOqlW8CIQDDOerGKH5OmCJ4Z21v+F25WaHYPxCFMvwxpcw99EcvDQIgIdhDTIqD2jfYjPTY8Jj3EDGPbH2HHuffvflECt3Ek60CIQCFRlCkHpi7hthhYhovyloRYsM+IS9h/0BzlEAuO0ktMQIgSPT3aFAgJYwKpqRYKlLDVcflZFCKY7u3UP8iWi1Qw0Y=\n\n# 防止XSS攻击\nxss:\n  enabled: true\n  excludeUrls:\n    - /system/notice\n    - /workflow/model/save\n    - /workflow/model/editModelXml\n\n# 接口文档配置\nspringdoc:\n  api-docs:\n    # 是否开启接口文档\n    enabled: true\n#  swagger-ui:\n#    # 持久化认证数据\n#    persistAuthorization: true\n  info:\n    # 标题\n    title: \'标题：RuoYi-Cloud-Plus微服务权限管理系统_接口文档\'\n    # 描述\n    description: \'描述：微服务权限管理系统, 具体包括XXX,XXX模块...\'\n    # 版本\n    version: \'版本号：系统版本...\'\n    # 作者信息\n    contact:\n      name: Lion Li\n      email: crazylionli@163.com\n      url: https://gitee.com/dromara/RuoYi-Cloud-Plus\n  components:\n    # 鉴权方式配置\n    security-schemes:\n      apiKey:\n        type: APIKEY\n        in: HEADER\n        name: ${sa-token.token-name}\n\n# seata配置\nseata:\n  # 是否启用\n  enabled: true\n  # Seata 应用编号，默认为应用名\n  application-id: ${spring.application.name}\n  # Seata 事务组编号，用于 TC 集群名\n  tx-service-group: ${spring.application.name}-group\n\n# 多租户配置\ntenant:\n  # 是否开启\n  enable: true\n  # 排除表\n  excludes:\n    - sys_menu\n    - sys_tenant\n    - sys_tenant_package\n    - sys_role_dept\n    - sys_role_menu\n    - sys_user_post\n    - sys_user_role\n    - sys_client\n    - sys_oss_config\n','9ce0cd9136de4b8f9c92fb37051f4bd4','2024-12-05 16:41:43','2024-12-05 08:41:44','nacos','192.168.1.19','U','prod',''),(135,87,'gateway.yml','DEFAULT_GROUP','','# 安全配置\nsecurity:\n  # 不校验白名单\n  ignore:\n    whites:\n      - /auth/code\n      - /auth/logout\n      - /auth/login\n      - /auth/binding/*\n      - /auth/social/callback\n      - /auth/register\n      - /auth/tenant/list\n      - /resource/sms/code\n      - /resource/sse/close\n      - /*/v3/api-docs\n      - /*/error\n      - /csrf\n\nspring:\n  cloud:\n    # 网关配置\n    gateway:\n      # 打印请求日志(自定义)\n      requestLog: true\n      discovery:\n        locator:\n          lowerCaseServiceId: true\n          enabled: true\n      routes:\n        # 认证中心\n        - id: auth\n          uri: lb://auth\n          predicates:\n            - Path=/auth/**\n          filters:\n            - StripPrefix=1\n        # 代码生成\n        - id: gen\n          uri: lb://gen\n          predicates:\n            - Path=/tool/**\n          filters:\n            - StripPrefix=1\n        # 系统模块\n        - id: system\n          uri: lb://system\n          predicates:\n            - Path=/system/**,/monitor/**\n          filters:\n            - StripPrefix=1\n        # 资源服务\n        - id: resource\n          uri: lb://resource\n          predicates:\n            - Path=/resource/**\n          filters:\n            - StripPrefix=1\n        # workflow服务\n        - id: workflow\n          uri: lb://workflow\n          predicates:\n            - Path=/workflow/**\n          filters:\n            - StripPrefix=1\n        # 演示服务\n        - id: demo\n          uri: lb://demo\n          predicates:\n            - Path=/demo/**\n          filters:\n            - StripPrefix=1\n        # MQ演示服务\n        - id: test-mq\n          uri: lb://test-mq\n          predicates:\n            - Path=/test-mq/**\n          filters:\n            - StripPrefix=1\n\n    # sentinel 配置\n    sentinel:\n      filter:\n        enabled: false\n      # nacos配置持久化\n      datasource:\n        ds1:\n          nacos:\n            server-addr: ${spring.cloud.nacos.server-addr}\n            dataId: sentinel-${spring.application.name}.json\n            groupId: ${spring.cloud.nacos.config.group}\n            username: ${spring.cloud.nacos.username}\n            password: ${spring.cloud.nacos.password}\n            namespace: ${spring.profiles.active}\n            data-type: json\n            rule-type: gw-flow\n','f8943720504ecace1ff47cc4e9e31efc','2024-12-06 10:57:38','2024-12-06 02:57:39','nacos','192.168.1.19','U','prod','');
/*!40000 ALTER TABLE `his_config_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `permissions`
--

DROP TABLE IF EXISTS `permissions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `permissions` (
  `role` varchar(50) COLLATE utf8mb4_general_ci NOT NULL COMMENT 'role',
  `resource` varchar(128) COLLATE utf8mb4_general_ci NOT NULL COMMENT 'resource',
  `action` varchar(8) COLLATE utf8mb4_general_ci NOT NULL COMMENT 'action',
  UNIQUE KEY `uk_role_permission` (`role`,`resource`,`action`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `permissions`
--

LOCK TABLES `permissions` WRITE;
/*!40000 ALTER TABLE `permissions` DISABLE KEYS */;
/*!40000 ALTER TABLE `permissions` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `roles`
--

DROP TABLE IF EXISTS `roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `roles` (
  `username` varchar(50) COLLATE utf8mb4_general_ci NOT NULL COMMENT 'username',
  `role` varchar(50) COLLATE utf8mb4_general_ci NOT NULL COMMENT 'role',
  UNIQUE KEY `idx_user_role` (`username`,`role`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `roles`
--

LOCK TABLES `roles` WRITE;
/*!40000 ALTER TABLE `roles` DISABLE KEYS */;
INSERT INTO `roles` VALUES ('nacos','ROLE_ADMIN');
/*!40000 ALTER TABLE `roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tenant_capacity`
--

DROP TABLE IF EXISTS `tenant_capacity`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tenant_capacity` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `tenant_id` varchar(128) COLLATE utf8mb3_bin NOT NULL DEFAULT '' COMMENT 'Tenant ID',
  `quota` int unsigned NOT NULL DEFAULT '0' COMMENT '配额，0表示使用默认值',
  `usage` int unsigned NOT NULL DEFAULT '0' COMMENT '使用量',
  `max_size` int unsigned NOT NULL DEFAULT '0' COMMENT '单个配置大小上限，单位为字节，0表示使用默认值',
  `max_aggr_count` int unsigned NOT NULL DEFAULT '0' COMMENT '聚合子配置最大个数',
  `max_aggr_size` int unsigned NOT NULL DEFAULT '0' COMMENT '单个聚合数据的子配置大小上限，单位为字节，0表示使用默认值',
  `max_history_count` int unsigned NOT NULL DEFAULT '0' COMMENT '最大变更历史数量',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_tenant_id` (`tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_bin COMMENT='租户容量信息表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tenant_capacity`
--

LOCK TABLES `tenant_capacity` WRITE;
/*!40000 ALTER TABLE `tenant_capacity` DISABLE KEYS */;
/*!40000 ALTER TABLE `tenant_capacity` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tenant_info`
--

DROP TABLE IF EXISTS `tenant_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tenant_info` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
  `kp` varchar(128) COLLATE utf8mb3_bin NOT NULL COMMENT 'kp',
  `tenant_id` varchar(128) COLLATE utf8mb3_bin DEFAULT '' COMMENT 'tenant_id',
  `tenant_name` varchar(128) COLLATE utf8mb3_bin DEFAULT '' COMMENT 'tenant_name',
  `tenant_desc` varchar(256) COLLATE utf8mb3_bin DEFAULT NULL COMMENT 'tenant_desc',
  `create_source` varchar(32) COLLATE utf8mb3_bin DEFAULT NULL COMMENT 'create_source',
  `gmt_create` bigint NOT NULL COMMENT '创建时间',
  `gmt_modified` bigint NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_tenant_info_kptenantid` (`kp`,`tenant_id`),
  KEY `idx_tenant_id` (`tenant_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_bin COMMENT='tenant_info';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tenant_info`
--

LOCK TABLES `tenant_info` WRITE;
/*!40000 ALTER TABLE `tenant_info` DISABLE KEYS */;
INSERT INTO `tenant_info` VALUES (1,'1','dev','dev','开发环境',NULL,1641741261189,1641741261189),(2,'1','prod','prod','生产环境',NULL,1641741270448,1641741287236);
/*!40000 ALTER TABLE `tenant_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `username` varchar(50) COLLATE utf8mb4_general_ci NOT NULL COMMENT 'username',
  `password` varchar(500) COLLATE utf8mb4_general_ci NOT NULL COMMENT 'password',
  `enabled` tinyint(1) NOT NULL COMMENT 'enabled',
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES ('nacos','$2a$10$EuWPZHzz32dJN7jexM34MOeYirDdFAZm2kuWj7VEOJhhZkDrxfvUu',1);
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Current Database: `ry-job`
--

CREATE DATABASE /*!32312 IF NOT EXISTS*/ `ry-job` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;

USE `ry-job`;

--
-- Table structure for table `sj_distributed_lock`
--

DROP TABLE IF EXISTS `sj_distributed_lock`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sj_distributed_lock` (
  `name` varchar(64) NOT NULL COMMENT '锁名称',
  `lock_until` timestamp(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) ON UPDATE CURRENT_TIMESTAMP(3) COMMENT '锁定时长',
  `locked_at` timestamp(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) COMMENT '锁定时间',
  `locked_by` varchar(255) NOT NULL COMMENT '锁定者',
  `create_dt` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_dt` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='锁定表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sj_distributed_lock`
--

LOCK TABLES `sj_distributed_lock` WRITE;
/*!40000 ALTER TABLE `sj_distributed_lock` DISABLE KEYS */;
INSERT INTO `sj_distributed_lock` VALUES ('clearFinishAndMoveDeadLetterRetryTask','2024-12-06 06:42:28.967','2024-12-06 06:41:28.967','1864605947645636608','2024-12-05 17:41:24','2024-12-06 14:41:29'),('clearLog','2024-12-06 06:42:23.520','2024-12-06 06:41:23.520','1864605947645636608','2024-12-05 17:41:24','2024-12-06 14:41:24'),('clearOfflineNode','2024-12-06 06:55:04.927','2024-12-06 06:54:59.927','1864605947645636608','2024-12-05 17:41:22','2024-12-06 14:55:00'),('jobClearLog','2024-12-06 06:42:23.223','2024-12-06 06:41:23.223','1864605947645636608','2024-12-05 17:41:24','2024-12-06 14:41:23'),('jobLogMerge','2024-12-06 06:42:23.207','2024-12-06 06:41:23.207','1864605947645636608','2024-12-05 17:41:23','2024-12-06 14:41:23'),('jobSummaryDashboard','2024-12-06 06:54:43.214','2024-12-06 06:54:23.214','1864605947645636608','2024-12-05 17:41:23','2024-12-06 14:54:23'),('retryErrorMoreThreshold','2024-12-06 06:52:47.635','2024-12-06 06:51:47.635','1864605947645636608','2024-12-05 17:41:24','2024-12-06 14:51:48'),('retryLogMerge','2024-12-06 06:42:23.493','2024-12-06 06:41:23.493','1864605947645636608','2024-12-05 17:41:24','2024-12-06 14:41:24'),('retrySummaryDashboard','2024-12-06 06:54:43.214','2024-12-06 06:54:23.214','1864605947645636608','2024-12-05 17:41:24','2024-12-06 14:54:23'),('retryTaskMoreThreshold','2024-12-06 06:52:48.245','2024-12-06 06:51:48.245','1864605947645636608','2024-12-05 17:41:24','2024-12-06 14:51:48'),('workflowJobSummarySchedule','2024-12-06 06:54:43.214','2024-12-06 06:54:23.214','1864605947645636608','2024-12-05 17:41:23','2024-12-06 14:54:23');
/*!40000 ALTER TABLE `sj_distributed_lock` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sj_group_config`
--

DROP TABLE IF EXISTS `sj_group_config`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sj_group_config` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `namespace_id` varchar(64) NOT NULL DEFAULT '764d604ec6fc45f68cd92514c40e9e1a' COMMENT '命名空间id',
  `group_name` varchar(64) NOT NULL DEFAULT '' COMMENT '组名称',
  `description` varchar(256) NOT NULL DEFAULT '' COMMENT '组描述',
  `token` varchar(64) NOT NULL DEFAULT 'SJ_cKqBTPzCsWA3VyuCfFoccmuIEGXjr5KT' COMMENT 'token',
  `group_status` tinyint NOT NULL DEFAULT '0' COMMENT '组状态 0、未启用 1、启用',
  `version` int NOT NULL COMMENT '版本号',
  `group_partition` int NOT NULL COMMENT '分区',
  `id_generator_mode` tinyint NOT NULL DEFAULT '1' COMMENT '唯一id生成模式 默认号段模式',
  `init_scene` tinyint NOT NULL DEFAULT '0' COMMENT '是否初始化场景 0:否 1:是',
  `bucket_index` int NOT NULL DEFAULT '0' COMMENT 'bucket',
  `create_dt` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_dt` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_namespace_id_group_name` (`namespace_id`,`group_name`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='组配置';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sj_group_config`
--

LOCK TABLES `sj_group_config` WRITE;
/*!40000 ALTER TABLE `sj_group_config` DISABLE KEYS */;
INSERT INTO `sj_group_config` VALUES (1,'dev','ruoyi_group','','SJ_cKqBTPzCsWA3VyuCfFoccmuIEGXjr5KT',1,1,0,1,1,4,'2024-12-05 15:51:53','2024-12-05 15:51:53'),(2,'prod','ruoyi_group','','SJ_cKqBTPzCsWA3VyuCfFoccmuIEGXjr5KT',1,1,0,1,1,4,'2024-12-05 15:51:53','2024-12-05 15:51:53'),(3,'dev','base_platform_group','','SJ_Wyz3dmsdbDOkDujOTSSoBjGQP1BMsVnj',1,1,0,1,1,93,'2024-12-05 16:07:38','2024-12-05 16:07:38');
/*!40000 ALTER TABLE `sj_group_config` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sj_job`
--

DROP TABLE IF EXISTS `sj_job`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sj_job` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `namespace_id` varchar(64) NOT NULL DEFAULT '764d604ec6fc45f68cd92514c40e9e1a' COMMENT '命名空间id',
  `group_name` varchar(64) NOT NULL COMMENT '组名称',
  `job_name` varchar(64) NOT NULL COMMENT '名称',
  `args_str` text COMMENT '执行方法参数',
  `args_type` tinyint NOT NULL DEFAULT '1' COMMENT '参数类型 ',
  `next_trigger_at` bigint NOT NULL COMMENT '下次触发时间',
  `job_status` tinyint NOT NULL DEFAULT '1' COMMENT '任务状态 0、关闭、1、开启',
  `task_type` tinyint NOT NULL DEFAULT '1' COMMENT '任务类型 1、集群 2、广播 3、切片',
  `route_key` tinyint NOT NULL DEFAULT '4' COMMENT '路由策略',
  `executor_type` tinyint NOT NULL DEFAULT '1' COMMENT '执行器类型',
  `executor_info` varchar(255) DEFAULT NULL COMMENT '执行器名称',
  `trigger_type` tinyint NOT NULL COMMENT '触发类型 1.CRON 表达式 2. 固定时间',
  `trigger_interval` varchar(255) NOT NULL COMMENT '间隔时长',
  `block_strategy` tinyint NOT NULL DEFAULT '1' COMMENT '阻塞策略 1、丢弃 2、覆盖 3、并行',
  `executor_timeout` int NOT NULL DEFAULT '0' COMMENT '任务执行超时时间，单位秒',
  `max_retry_times` int NOT NULL DEFAULT '0' COMMENT '最大重试次数',
  `parallel_num` int NOT NULL DEFAULT '1' COMMENT '并行数',
  `retry_interval` int NOT NULL DEFAULT '0' COMMENT '重试间隔(s)',
  `bucket_index` int NOT NULL DEFAULT '0' COMMENT 'bucket',
  `resident` tinyint NOT NULL DEFAULT '0' COMMENT '是否是常驻任务',
  `description` varchar(256) NOT NULL DEFAULT '' COMMENT '描述',
  `ext_attrs` varchar(256) DEFAULT '' COMMENT '扩展字段',
  `deleted` tinyint NOT NULL DEFAULT '0' COMMENT '逻辑删除 1、删除',
  `create_dt` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_dt` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`),
  KEY `idx_namespace_id_group_name` (`namespace_id`,`group_name`),
  KEY `idx_job_status_bucket_index` (`job_status`,`bucket_index`),
  KEY `idx_create_dt` (`create_dt`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='任务信息';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sj_job`
--

LOCK TABLES `sj_job` WRITE;
/*!40000 ALTER TABLE `sj_job` DISABLE KEYS */;
INSERT INTO `sj_job` VALUES (1,'dev','ruoyi_group','demo-job',NULL,1,1733468152335,1,1,4,1,'testJobExecutor',2,'60',1,60,3,1,1,116,0,'','',0,'2024-12-05 15:52:02','2024-12-06 14:54:51');
/*!40000 ALTER TABLE `sj_job` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sj_job_log_message`
--

DROP TABLE IF EXISTS `sj_job_log_message`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sj_job_log_message` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `namespace_id` varchar(64) NOT NULL DEFAULT '764d604ec6fc45f68cd92514c40e9e1a' COMMENT '命名空间id',
  `group_name` varchar(64) NOT NULL COMMENT '组名称',
  `job_id` bigint NOT NULL COMMENT '任务信息id',
  `task_batch_id` bigint NOT NULL COMMENT '任务批次id',
  `task_id` bigint NOT NULL COMMENT '调度任务id',
  `message` longtext NOT NULL COMMENT '调度信息',
  `log_num` int NOT NULL DEFAULT '1' COMMENT '日志数量',
  `real_time` bigint NOT NULL DEFAULT '0' COMMENT '上报时间',
  `ext_attrs` varchar(256) DEFAULT '' COMMENT '扩展字段',
  `create_dt` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`),
  KEY `idx_task_batch_id_task_id` (`task_batch_id`,`task_id`),
  KEY `idx_create_dt` (`create_dt`),
  KEY `idx_namespace_id_group_name` (`namespace_id`,`group_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='调度日志';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sj_job_log_message`
--

LOCK TABLES `sj_job_log_message` WRITE;
/*!40000 ALTER TABLE `sj_job_log_message` DISABLE KEYS */;
/*!40000 ALTER TABLE `sj_job_log_message` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sj_job_summary`
--

DROP TABLE IF EXISTS `sj_job_summary`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sj_job_summary` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `namespace_id` varchar(64) NOT NULL DEFAULT '764d604ec6fc45f68cd92514c40e9e1a' COMMENT '命名空间id',
  `group_name` varchar(64) NOT NULL DEFAULT '' COMMENT '组名称',
  `business_id` bigint NOT NULL COMMENT '业务id (job_id或workflow_id)',
  `system_task_type` tinyint NOT NULL DEFAULT '3' COMMENT '任务类型 3、JOB任务 4、WORKFLOW任务',
  `trigger_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '统计时间',
  `success_num` int NOT NULL DEFAULT '0' COMMENT '执行成功-日志数量',
  `fail_num` int NOT NULL DEFAULT '0' COMMENT '执行失败-日志数量',
  `fail_reason` varchar(512) NOT NULL DEFAULT '' COMMENT '失败原因',
  `stop_num` int NOT NULL DEFAULT '0' COMMENT '执行失败-日志数量',
  `stop_reason` varchar(512) NOT NULL DEFAULT '' COMMENT '失败原因',
  `cancel_num` int NOT NULL DEFAULT '0' COMMENT '执行失败-日志数量',
  `cancel_reason` varchar(512) NOT NULL DEFAULT '' COMMENT '失败原因',
  `create_dt` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_dt` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_trigger_at_system_task_type_business_id` (`trigger_at`,`system_task_type`,`business_id`) USING BTREE,
  KEY `idx_namespace_id_group_name_business_id` (`namespace_id`,`group_name`,`business_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='DashBoard_Job';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sj_job_summary`
--

LOCK TABLES `sj_job_summary` WRITE;
/*!40000 ALTER TABLE `sj_job_summary` DISABLE KEYS */;
INSERT INTO `sj_job_summary` VALUES (1,'dev','ruoyi_group',1,3,'2024-12-05 00:00:00',0,0,'[]',0,'[]',475,'[{\"reason\":2,\"total\":475}]','2024-12-05 16:02:21','2024-12-06 00:00:23'),(2,'dev','ruoyi_group',1,3,'2024-12-06 00:00:00',0,0,'[]',0,'[]',894,'[{\"reason\":2,\"total\":894}]','2024-12-06 00:01:23','2024-12-06 14:54:22');
/*!40000 ALTER TABLE `sj_job_summary` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sj_job_task`
--

DROP TABLE IF EXISTS `sj_job_task`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sj_job_task` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `namespace_id` varchar(64) NOT NULL DEFAULT '764d604ec6fc45f68cd92514c40e9e1a' COMMENT '命名空间id',
  `group_name` varchar(64) NOT NULL COMMENT '组名称',
  `job_id` bigint NOT NULL COMMENT '任务信息id',
  `task_batch_id` bigint NOT NULL COMMENT '调度任务id',
  `parent_id` bigint NOT NULL DEFAULT '0' COMMENT '父执行器id',
  `task_status` tinyint NOT NULL DEFAULT '0' COMMENT '执行的状态 0、失败 1、成功',
  `retry_count` int NOT NULL DEFAULT '0' COMMENT '重试次数',
  `mr_stage` tinyint DEFAULT NULL COMMENT '动态分片所处阶段 1:map 2:reduce 3:mergeReduce',
  `leaf` tinyint NOT NULL DEFAULT '1' COMMENT '叶子节点',
  `task_name` varchar(255) NOT NULL DEFAULT '' COMMENT '任务名称',
  `client_info` varchar(128) DEFAULT NULL COMMENT '客户端地址 clientId#ip:port',
  `wf_context` text COMMENT '工作流全局上下文',
  `result_message` text NOT NULL COMMENT '执行结果',
  `args_str` text COMMENT '执行方法参数',
  `args_type` tinyint NOT NULL DEFAULT '1' COMMENT '参数类型 ',
  `ext_attrs` varchar(256) DEFAULT '' COMMENT '扩展字段',
  `create_dt` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_dt` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`),
  KEY `idx_task_batch_id_task_status` (`task_batch_id`,`task_status`),
  KEY `idx_create_dt` (`create_dt`),
  KEY `idx_namespace_id_group_name` (`namespace_id`,`group_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='任务实例';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sj_job_task`
--

LOCK TABLES `sj_job_task` WRITE;
/*!40000 ALTER TABLE `sj_job_task` DISABLE KEYS */;
/*!40000 ALTER TABLE `sj_job_task` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sj_job_task_batch`
--

DROP TABLE IF EXISTS `sj_job_task_batch`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sj_job_task_batch` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `namespace_id` varchar(64) NOT NULL DEFAULT '764d604ec6fc45f68cd92514c40e9e1a' COMMENT '命名空间id',
  `group_name` varchar(64) NOT NULL COMMENT '组名称',
  `job_id` bigint NOT NULL COMMENT '任务id',
  `workflow_node_id` bigint NOT NULL DEFAULT '0' COMMENT '工作流节点id',
  `parent_workflow_node_id` bigint NOT NULL DEFAULT '0' COMMENT '工作流任务父批次id',
  `workflow_task_batch_id` bigint NOT NULL DEFAULT '0' COMMENT '工作流任务批次id',
  `task_batch_status` tinyint NOT NULL DEFAULT '0' COMMENT '任务批次状态 0、失败 1、成功',
  `operation_reason` tinyint NOT NULL DEFAULT '0' COMMENT '操作原因',
  `execution_at` bigint NOT NULL DEFAULT '0' COMMENT '任务执行时间',
  `system_task_type` tinyint NOT NULL DEFAULT '3' COMMENT '任务类型 3、JOB任务 4、WORKFLOW任务',
  `parent_id` varchar(64) NOT NULL DEFAULT '' COMMENT '父节点',
  `ext_attrs` varchar(256) DEFAULT '' COMMENT '扩展字段',
  `deleted` tinyint NOT NULL DEFAULT '0' COMMENT '逻辑删除 1、删除',
  `create_dt` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_dt` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`),
  KEY `idx_job_id_task_batch_status` (`job_id`,`task_batch_status`),
  KEY `idx_create_dt` (`create_dt`),
  KEY `idx_namespace_id_group_name` (`namespace_id`,`group_name`),
  KEY `idx_workflow_task_batch_id_workflow_node_id` (`workflow_task_batch_id`,`workflow_node_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1371 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='任务批次';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sj_job_task_batch`
--

LOCK TABLES `sj_job_task_batch` WRITE;
/*!40000 ALTER TABLE `sj_job_task_batch` DISABLE KEYS */;
INSERT INTO `sj_job_task_batch` VALUES (1,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 16:01:51','2024-12-05 16:01:51'),(2,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 16:02:51','2024-12-05 16:02:51'),(3,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 16:03:51','2024-12-05 16:03:51'),(4,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 16:04:51','2024-12-05 16:04:51'),(5,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 16:05:51','2024-12-05 16:05:51'),(6,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 16:06:51','2024-12-05 16:06:51'),(7,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 16:07:51','2024-12-05 16:07:51'),(8,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 16:09:40','2024-12-05 16:09:40'),(9,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 16:10:40','2024-12-05 16:10:40'),(10,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 16:11:40','2024-12-05 16:11:40'),(11,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 16:12:40','2024-12-05 16:12:40'),(12,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 16:13:40','2024-12-05 16:13:40'),(13,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 16:14:40','2024-12-05 16:14:40'),(14,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 16:15:40','2024-12-05 16:15:40'),(15,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 16:16:40','2024-12-05 16:16:40'),(16,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 16:17:40','2024-12-05 16:17:40'),(17,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 16:18:40','2024-12-05 16:18:40'),(18,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 16:19:40','2024-12-05 16:19:40'),(19,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 16:20:40','2024-12-05 16:20:40'),(20,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 16:21:40','2024-12-05 16:21:40'),(21,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 16:22:40','2024-12-05 16:22:40'),(22,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 16:23:40','2024-12-05 16:23:40'),(23,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 16:24:40','2024-12-05 16:24:40'),(24,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 16:25:40','2024-12-05 16:25:40'),(25,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 16:26:40','2024-12-05 16:26:40'),(26,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 16:27:40','2024-12-05 16:27:40'),(27,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 16:28:40','2024-12-05 16:28:40'),(28,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 16:29:40','2024-12-05 16:29:40'),(29,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 16:30:40','2024-12-05 16:30:40'),(30,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 16:31:40','2024-12-05 16:31:40'),(31,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 16:32:40','2024-12-05 16:32:40'),(32,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 16:33:40','2024-12-05 16:33:40'),(33,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 16:34:40','2024-12-05 16:34:40'),(34,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 16:35:40','2024-12-05 16:35:40'),(35,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 16:36:40','2024-12-05 16:36:40'),(36,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 16:37:40','2024-12-05 16:37:40'),(37,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 16:38:40','2024-12-05 16:38:40'),(38,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 16:39:40','2024-12-05 16:39:40'),(39,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 16:40:40','2024-12-05 16:40:40'),(40,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 16:41:40','2024-12-05 16:41:40'),(41,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 16:42:40','2024-12-05 16:42:40'),(42,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 16:43:40','2024-12-05 16:43:40'),(43,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 16:44:40','2024-12-05 16:44:40'),(44,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 16:45:40','2024-12-05 16:45:40'),(45,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 16:46:40','2024-12-05 16:46:40'),(46,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 16:47:40','2024-12-05 16:47:40'),(47,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 16:48:40','2024-12-05 16:48:40'),(48,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 16:49:40','2024-12-05 16:49:40'),(49,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 16:50:40','2024-12-05 16:50:40'),(50,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 16:51:40','2024-12-05 16:51:40'),(51,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 16:52:40','2024-12-05 16:52:40'),(52,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 16:53:40','2024-12-05 16:53:40'),(53,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 16:54:40','2024-12-05 16:54:40'),(54,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 16:55:40','2024-12-05 16:55:40'),(55,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 16:56:40','2024-12-05 16:56:40'),(56,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 16:57:40','2024-12-05 16:57:40'),(57,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 16:58:40','2024-12-05 16:58:40'),(58,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 16:59:40','2024-12-05 16:59:40'),(59,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 17:00:40','2024-12-05 17:00:40'),(60,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 17:01:40','2024-12-05 17:01:40'),(61,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 17:02:40','2024-12-05 17:02:40'),(62,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 17:03:40','2024-12-05 17:03:40'),(63,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 17:04:40','2024-12-05 17:04:40'),(64,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 17:05:40','2024-12-05 17:05:40'),(65,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 17:06:40','2024-12-05 17:06:40'),(66,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 17:07:40','2024-12-05 17:07:40'),(67,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 17:08:40','2024-12-05 17:08:40'),(68,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 17:09:40','2024-12-05 17:09:40'),(69,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 17:10:40','2024-12-05 17:10:40'),(70,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 17:11:40','2024-12-05 17:11:40'),(71,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 17:12:40','2024-12-05 17:12:40'),(72,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 17:13:40','2024-12-05 17:13:40'),(73,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 17:14:40','2024-12-05 17:14:40'),(74,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 17:15:40','2024-12-05 17:15:40'),(75,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 17:16:40','2024-12-05 17:16:40'),(76,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 17:17:40','2024-12-05 17:17:40'),(77,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 17:18:40','2024-12-05 17:18:40'),(78,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 17:19:40','2024-12-05 17:19:40'),(79,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 17:20:40','2024-12-05 17:20:40'),(80,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 17:21:40','2024-12-05 17:21:40'),(81,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 17:22:40','2024-12-05 17:22:40'),(82,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 17:23:40','2024-12-05 17:23:40'),(83,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 17:24:40','2024-12-05 17:24:40'),(84,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 17:25:40','2024-12-05 17:25:40'),(85,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 17:26:40','2024-12-05 17:26:40'),(86,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 17:27:40','2024-12-05 17:27:40'),(87,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 17:28:40','2024-12-05 17:28:40'),(88,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 17:29:40','2024-12-05 17:29:40'),(89,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 17:30:40','2024-12-05 17:30:40'),(90,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 17:31:40','2024-12-05 17:31:40'),(91,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 17:32:40','2024-12-05 17:32:40'),(92,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 17:33:40','2024-12-05 17:33:40'),(93,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 17:34:40','2024-12-05 17:34:40'),(94,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 17:35:40','2024-12-05 17:35:40'),(95,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 17:36:40','2024-12-05 17:36:40'),(96,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 17:37:40','2024-12-05 17:37:40'),(97,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 17:41:52','2024-12-05 17:41:52'),(98,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 17:42:52','2024-12-05 17:42:52'),(99,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 17:43:52','2024-12-05 17:43:52'),(100,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 17:44:52','2024-12-05 17:44:52'),(101,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 17:45:52','2024-12-05 17:45:52'),(102,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 17:46:52','2024-12-05 17:46:52'),(103,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 17:47:52','2024-12-05 17:47:52'),(104,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 17:48:52','2024-12-05 17:48:52'),(105,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 17:49:52','2024-12-05 17:49:52'),(106,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 17:50:52','2024-12-05 17:50:52'),(107,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 17:51:52','2024-12-05 17:51:52'),(108,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 17:52:52','2024-12-05 17:52:52'),(109,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 17:53:52','2024-12-05 17:53:52'),(110,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 17:54:52','2024-12-05 17:54:52'),(111,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 17:55:53','2024-12-05 17:55:53'),(112,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 17:56:52','2024-12-05 17:56:52'),(113,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 17:57:52','2024-12-05 17:57:52'),(114,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 17:58:52','2024-12-05 17:58:52'),(115,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 17:59:52','2024-12-05 17:59:52'),(116,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 18:00:52','2024-12-05 18:00:52'),(117,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 18:01:52','2024-12-05 18:01:52'),(118,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 18:02:52','2024-12-05 18:02:52'),(119,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 18:03:52','2024-12-05 18:03:52'),(120,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 18:04:53','2024-12-05 18:04:53'),(121,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 18:05:52','2024-12-05 18:05:52'),(122,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 18:06:52','2024-12-05 18:06:52'),(123,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 18:07:52','2024-12-05 18:07:52'),(124,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 18:08:52','2024-12-05 18:08:52'),(125,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 18:09:52','2024-12-05 18:09:52'),(126,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 18:10:52','2024-12-05 18:10:52'),(127,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 18:11:52','2024-12-05 18:11:52'),(128,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 18:12:52','2024-12-05 18:12:52'),(129,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 18:13:52','2024-12-05 18:13:52'),(130,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 18:14:52','2024-12-05 18:14:52'),(131,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 18:15:52','2024-12-05 18:15:52'),(132,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 18:16:52','2024-12-05 18:16:52'),(133,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 18:17:52','2024-12-05 18:17:52'),(134,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 18:18:52','2024-12-05 18:18:52'),(135,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 18:19:52','2024-12-05 18:19:52'),(136,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 18:20:52','2024-12-05 18:20:52'),(137,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 18:21:52','2024-12-05 18:21:52'),(138,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 18:22:52','2024-12-05 18:22:52'),(139,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 18:23:52','2024-12-05 18:23:52'),(140,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 18:24:53','2024-12-05 18:24:53'),(141,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 18:25:52','2024-12-05 18:25:52'),(142,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 18:26:52','2024-12-05 18:26:52'),(143,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 18:27:52','2024-12-05 18:27:52'),(144,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 18:28:52','2024-12-05 18:28:52'),(145,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 18:29:52','2024-12-05 18:29:52'),(146,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 18:30:52','2024-12-05 18:30:52'),(147,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 18:31:52','2024-12-05 18:31:52'),(148,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 18:32:52','2024-12-05 18:32:52'),(149,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 18:33:52','2024-12-05 18:33:52'),(150,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 18:34:52','2024-12-05 18:34:52'),(151,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 18:35:52','2024-12-05 18:35:52'),(152,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 18:36:52','2024-12-05 18:36:52'),(153,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 18:37:52','2024-12-05 18:37:52'),(154,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 18:38:52','2024-12-05 18:38:52'),(155,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 18:39:52','2024-12-05 18:39:52'),(156,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 18:40:52','2024-12-05 18:40:52'),(157,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 18:41:52','2024-12-05 18:41:52'),(158,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 18:42:52','2024-12-05 18:42:52'),(159,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 18:43:52','2024-12-05 18:43:52'),(160,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 18:44:52','2024-12-05 18:44:52'),(161,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 18:45:52','2024-12-05 18:45:52'),(162,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 18:46:52','2024-12-05 18:46:52'),(163,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 18:47:53','2024-12-05 18:47:53'),(164,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 18:48:52','2024-12-05 18:48:52'),(165,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 18:49:52','2024-12-05 18:49:52'),(166,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 18:50:52','2024-12-05 18:50:52'),(167,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 18:51:52','2024-12-05 18:51:52'),(168,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 18:52:52','2024-12-05 18:52:52'),(169,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 18:53:52','2024-12-05 18:53:52'),(170,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 18:54:52','2024-12-05 18:54:52'),(171,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 18:55:52','2024-12-05 18:55:52'),(172,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 18:56:52','2024-12-05 18:56:52'),(173,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 18:57:52','2024-12-05 18:57:52'),(174,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 18:58:52','2024-12-05 18:58:52'),(175,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 18:59:52','2024-12-05 18:59:52'),(176,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 19:00:52','2024-12-05 19:00:52'),(177,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 19:01:52','2024-12-05 19:01:52'),(178,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 19:02:52','2024-12-05 19:02:52'),(179,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 19:03:52','2024-12-05 19:03:52'),(180,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 19:04:52','2024-12-05 19:04:52'),(181,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 19:05:52','2024-12-05 19:05:52'),(182,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 19:06:52','2024-12-05 19:06:52'),(183,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 19:07:52','2024-12-05 19:07:52'),(184,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 19:08:52','2024-12-05 19:08:52'),(185,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 19:09:52','2024-12-05 19:09:52'),(186,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 19:10:52','2024-12-05 19:10:52'),(187,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 19:11:52','2024-12-05 19:11:52'),(188,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 19:12:52','2024-12-05 19:12:52'),(189,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 19:13:52','2024-12-05 19:13:52'),(190,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 19:14:52','2024-12-05 19:14:52'),(191,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 19:15:52','2024-12-05 19:15:52'),(192,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 19:16:52','2024-12-05 19:16:52'),(193,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 19:17:52','2024-12-05 19:17:52'),(194,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 19:18:52','2024-12-05 19:18:52'),(195,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 19:19:52','2024-12-05 19:19:52'),(196,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 19:20:52','2024-12-05 19:20:52'),(197,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 19:21:52','2024-12-05 19:21:52'),(198,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 19:22:52','2024-12-05 19:22:52'),(199,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 19:23:52','2024-12-05 19:23:52'),(200,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 19:24:52','2024-12-05 19:24:52'),(201,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 19:25:52','2024-12-05 19:25:52'),(202,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 19:26:52','2024-12-05 19:26:52'),(203,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 19:27:52','2024-12-05 19:27:52'),(204,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 19:28:52','2024-12-05 19:28:52'),(205,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 19:29:52','2024-12-05 19:29:52'),(206,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 19:30:52','2024-12-05 19:30:52'),(207,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 19:31:52','2024-12-05 19:31:52'),(208,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 19:32:52','2024-12-05 19:32:52'),(209,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 19:33:52','2024-12-05 19:33:52'),(210,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 19:34:52','2024-12-05 19:34:52'),(211,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 19:35:52','2024-12-05 19:35:52'),(212,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 19:36:52','2024-12-05 19:36:52'),(213,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 19:37:52','2024-12-05 19:37:52'),(214,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 19:38:52','2024-12-05 19:38:52'),(215,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 19:39:52','2024-12-05 19:39:52'),(216,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 19:40:52','2024-12-05 19:40:52'),(217,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 19:41:52','2024-12-05 19:41:52'),(218,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 19:42:52','2024-12-05 19:42:52'),(219,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 19:43:52','2024-12-05 19:43:52'),(220,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 19:44:52','2024-12-05 19:44:52'),(221,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 19:45:52','2024-12-05 19:45:52'),(222,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 19:46:52','2024-12-05 19:46:52'),(223,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 19:47:52','2024-12-05 19:47:52'),(224,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 19:48:52','2024-12-05 19:48:52'),(225,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 19:49:52','2024-12-05 19:49:52'),(226,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 19:50:52','2024-12-05 19:50:52'),(227,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 19:51:52','2024-12-05 19:51:52'),(228,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 19:52:52','2024-12-05 19:52:52'),(229,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 19:53:52','2024-12-05 19:53:52'),(230,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 19:54:52','2024-12-05 19:54:52'),(231,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 19:55:52','2024-12-05 19:55:52'),(232,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 19:56:52','2024-12-05 19:56:52'),(233,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 19:57:52','2024-12-05 19:57:52'),(234,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 19:58:52','2024-12-05 19:58:52'),(235,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 19:59:52','2024-12-05 19:59:52'),(236,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 20:00:52','2024-12-05 20:00:52'),(237,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 20:01:52','2024-12-05 20:01:52'),(238,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 20:02:52','2024-12-05 20:02:52'),(239,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 20:03:52','2024-12-05 20:03:52'),(240,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 20:04:52','2024-12-05 20:04:52'),(241,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 20:05:52','2024-12-05 20:05:52'),(242,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 20:06:52','2024-12-05 20:06:52'),(243,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 20:07:52','2024-12-05 20:07:52'),(244,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 20:08:52','2024-12-05 20:08:52'),(245,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 20:09:52','2024-12-05 20:09:52'),(246,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 20:10:52','2024-12-05 20:10:52'),(247,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 20:11:52','2024-12-05 20:11:52'),(248,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 20:12:52','2024-12-05 20:12:52'),(249,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 20:13:52','2024-12-05 20:13:52'),(250,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 20:14:52','2024-12-05 20:14:52'),(251,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 20:15:52','2024-12-05 20:15:52'),(252,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 20:16:52','2024-12-05 20:16:52'),(253,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 20:17:52','2024-12-05 20:17:52'),(254,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 20:18:52','2024-12-05 20:18:52'),(255,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 20:19:52','2024-12-05 20:19:52'),(256,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 20:20:52','2024-12-05 20:20:52'),(257,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 20:21:52','2024-12-05 20:21:52'),(258,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 20:22:52','2024-12-05 20:22:52'),(259,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 20:23:52','2024-12-05 20:23:52'),(260,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 20:24:52','2024-12-05 20:24:52'),(261,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 20:25:52','2024-12-05 20:25:52'),(262,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 20:26:52','2024-12-05 20:26:52'),(263,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 20:27:52','2024-12-05 20:27:52'),(264,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 20:28:52','2024-12-05 20:28:52'),(265,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 20:29:52','2024-12-05 20:29:52'),(266,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 20:30:52','2024-12-05 20:30:52'),(267,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 20:31:52','2024-12-05 20:31:52'),(268,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 20:32:52','2024-12-05 20:32:52'),(269,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 20:33:52','2024-12-05 20:33:52'),(270,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 20:34:52','2024-12-05 20:34:52'),(271,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 20:35:52','2024-12-05 20:35:52'),(272,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 20:36:52','2024-12-05 20:36:52'),(273,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 20:37:52','2024-12-05 20:37:52'),(274,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 20:38:52','2024-12-05 20:38:52'),(275,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 20:39:52','2024-12-05 20:39:52'),(276,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 20:40:52','2024-12-05 20:40:52'),(277,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 20:41:52','2024-12-05 20:41:52'),(278,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 20:42:52','2024-12-05 20:42:52'),(279,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 20:43:52','2024-12-05 20:43:52'),(280,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 20:44:52','2024-12-05 20:44:52'),(281,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 20:45:52','2024-12-05 20:45:52'),(282,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 20:46:52','2024-12-05 20:46:52'),(283,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 20:47:52','2024-12-05 20:47:52'),(284,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 20:48:52','2024-12-05 20:48:52'),(285,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 20:49:52','2024-12-05 20:49:52'),(286,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 20:50:52','2024-12-05 20:50:52'),(287,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 20:51:52','2024-12-05 20:51:52'),(288,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 20:52:52','2024-12-05 20:52:52'),(289,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 20:53:52','2024-12-05 20:53:52'),(290,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 20:54:52','2024-12-05 20:54:52'),(291,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 20:55:52','2024-12-05 20:55:52'),(292,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 20:56:52','2024-12-05 20:56:52'),(293,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 20:57:52','2024-12-05 20:57:52'),(294,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 20:58:52','2024-12-05 20:58:52'),(295,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 20:59:52','2024-12-05 20:59:52'),(296,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 21:00:52','2024-12-05 21:00:52'),(297,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 21:01:52','2024-12-05 21:01:52'),(298,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 21:02:52','2024-12-05 21:02:52'),(299,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 21:03:52','2024-12-05 21:03:52'),(300,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 21:04:52','2024-12-05 21:04:52'),(301,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 21:05:52','2024-12-05 21:05:52'),(302,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 21:06:52','2024-12-05 21:06:52'),(303,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 21:07:52','2024-12-05 21:07:52'),(304,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 21:08:52','2024-12-05 21:08:52'),(305,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 21:09:52','2024-12-05 21:09:52'),(306,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 21:10:52','2024-12-05 21:10:52'),(307,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 21:11:52','2024-12-05 21:11:52'),(308,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 21:12:52','2024-12-05 21:12:52'),(309,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 21:13:52','2024-12-05 21:13:52'),(310,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 21:14:52','2024-12-05 21:14:52'),(311,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 21:15:52','2024-12-05 21:15:52'),(312,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 21:16:52','2024-12-05 21:16:52'),(313,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 21:17:52','2024-12-05 21:17:52'),(314,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 21:18:52','2024-12-05 21:18:52'),(315,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 21:19:52','2024-12-05 21:19:52'),(316,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 21:20:52','2024-12-05 21:20:52'),(317,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 21:21:52','2024-12-05 21:21:52'),(318,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 21:22:52','2024-12-05 21:22:52'),(319,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 21:23:52','2024-12-05 21:23:52'),(320,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 21:24:52','2024-12-05 21:24:52'),(321,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 21:25:52','2024-12-05 21:25:52'),(322,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 21:26:52','2024-12-05 21:26:52'),(323,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 21:27:52','2024-12-05 21:27:52'),(324,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 21:28:52','2024-12-05 21:28:52'),(325,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 21:29:52','2024-12-05 21:29:52'),(326,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 21:30:52','2024-12-05 21:30:52'),(327,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 21:31:52','2024-12-05 21:31:52'),(328,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 21:32:52','2024-12-05 21:32:52'),(329,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 21:33:52','2024-12-05 21:33:52'),(330,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 21:34:52','2024-12-05 21:34:52'),(331,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 21:35:52','2024-12-05 21:35:52'),(332,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 21:36:52','2024-12-05 21:36:52'),(333,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 21:37:52','2024-12-05 21:37:52'),(334,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 21:38:52','2024-12-05 21:38:52'),(335,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 21:39:52','2024-12-05 21:39:52'),(336,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 21:40:52','2024-12-05 21:40:52'),(337,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 21:41:52','2024-12-05 21:41:52'),(338,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 21:42:52','2024-12-05 21:42:52'),(339,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 21:43:52','2024-12-05 21:43:52'),(340,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 21:44:52','2024-12-05 21:44:52'),(341,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 21:45:52','2024-12-05 21:45:52'),(342,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 21:46:52','2024-12-05 21:46:52'),(343,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 21:47:52','2024-12-05 21:47:52'),(344,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 21:48:52','2024-12-05 21:48:52'),(345,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 21:49:52','2024-12-05 21:49:52'),(346,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 21:50:52','2024-12-05 21:50:52'),(347,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 21:51:52','2024-12-05 21:51:52'),(348,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 21:52:52','2024-12-05 21:52:52'),(349,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 21:53:52','2024-12-05 21:53:52'),(350,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 21:54:52','2024-12-05 21:54:52'),(351,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 21:55:52','2024-12-05 21:55:52'),(352,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 21:56:52','2024-12-05 21:56:52'),(353,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 21:57:52','2024-12-05 21:57:52'),(354,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 21:58:52','2024-12-05 21:58:52'),(355,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 21:59:52','2024-12-05 21:59:52'),(356,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 22:00:52','2024-12-05 22:00:52'),(357,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 22:01:52','2024-12-05 22:01:52'),(358,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 22:02:52','2024-12-05 22:02:52'),(359,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 22:03:52','2024-12-05 22:03:52'),(360,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 22:04:52','2024-12-05 22:04:52'),(361,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 22:05:52','2024-12-05 22:05:52'),(362,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 22:06:52','2024-12-05 22:06:52'),(363,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 22:07:52','2024-12-05 22:07:52'),(364,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 22:08:52','2024-12-05 22:08:52'),(365,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 22:09:52','2024-12-05 22:09:52'),(366,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 22:10:52','2024-12-05 22:10:52'),(367,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 22:11:52','2024-12-05 22:11:52'),(368,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 22:12:52','2024-12-05 22:12:52'),(369,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 22:13:52','2024-12-05 22:13:52'),(370,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 22:14:52','2024-12-05 22:14:52'),(371,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 22:15:52','2024-12-05 22:15:52'),(372,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 22:16:52','2024-12-05 22:16:52'),(373,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 22:17:52','2024-12-05 22:17:52'),(374,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 22:18:52','2024-12-05 22:18:52'),(375,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 22:19:52','2024-12-05 22:19:52'),(376,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 22:20:52','2024-12-05 22:20:52'),(377,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 22:21:52','2024-12-05 22:21:52'),(378,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 22:22:52','2024-12-05 22:22:52'),(379,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 22:23:52','2024-12-05 22:23:52'),(380,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 22:24:52','2024-12-05 22:24:52'),(381,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 22:25:52','2024-12-05 22:25:52'),(382,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 22:26:52','2024-12-05 22:26:52'),(383,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 22:27:52','2024-12-05 22:27:52'),(384,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 22:28:52','2024-12-05 22:28:52'),(385,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 22:29:53','2024-12-05 22:29:53'),(386,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 22:30:52','2024-12-05 22:30:52'),(387,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 22:31:52','2024-12-05 22:31:52'),(388,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 22:32:52','2024-12-05 22:32:52'),(389,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 22:33:52','2024-12-05 22:33:52'),(390,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 22:34:52','2024-12-05 22:34:52'),(391,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 22:35:52','2024-12-05 22:35:52'),(392,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 22:36:52','2024-12-05 22:36:52'),(393,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 22:37:52','2024-12-05 22:37:52'),(394,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 22:38:52','2024-12-05 22:38:52'),(395,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 22:39:52','2024-12-05 22:39:52'),(396,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 22:40:52','2024-12-05 22:40:52'),(397,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 22:41:52','2024-12-05 22:41:52'),(398,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 22:42:52','2024-12-05 22:42:52'),(399,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 22:43:52','2024-12-05 22:43:52'),(400,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 22:44:52','2024-12-05 22:44:52'),(401,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 22:45:52','2024-12-05 22:45:52'),(402,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 22:46:52','2024-12-05 22:46:52'),(403,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 22:47:52','2024-12-05 22:47:52'),(404,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 22:48:52','2024-12-05 22:48:52'),(405,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 22:49:52','2024-12-05 22:49:52'),(406,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 22:50:52','2024-12-05 22:50:52'),(407,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 22:51:52','2024-12-05 22:51:52'),(408,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 22:52:52','2024-12-05 22:52:52'),(409,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 22:53:52','2024-12-05 22:53:52'),(410,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 22:54:52','2024-12-05 22:54:52'),(411,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 22:55:52','2024-12-05 22:55:52'),(412,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 22:56:52','2024-12-05 22:56:52'),(413,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 22:57:52','2024-12-05 22:57:52'),(414,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 22:58:52','2024-12-05 22:58:52'),(415,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 22:59:52','2024-12-05 22:59:52'),(416,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 23:00:52','2024-12-05 23:00:52'),(417,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 23:01:52','2024-12-05 23:01:52'),(418,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 23:02:52','2024-12-05 23:02:52'),(419,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 23:03:52','2024-12-05 23:03:52'),(420,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 23:04:52','2024-12-05 23:04:52'),(421,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 23:05:52','2024-12-05 23:05:52'),(422,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 23:06:52','2024-12-05 23:06:52'),(423,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 23:07:52','2024-12-05 23:07:52'),(424,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 23:08:52','2024-12-05 23:08:52'),(425,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 23:09:52','2024-12-05 23:09:52'),(426,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 23:10:52','2024-12-05 23:10:52'),(427,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 23:11:52','2024-12-05 23:11:52'),(428,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 23:12:52','2024-12-05 23:12:52'),(429,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 23:13:52','2024-12-05 23:13:52'),(430,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 23:14:52','2024-12-05 23:14:52'),(431,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 23:15:52','2024-12-05 23:15:52'),(432,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 23:16:52','2024-12-05 23:16:52'),(433,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 23:17:52','2024-12-05 23:17:52'),(434,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 23:18:52','2024-12-05 23:18:52'),(435,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 23:19:52','2024-12-05 23:19:52'),(436,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 23:20:52','2024-12-05 23:20:52'),(437,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 23:21:52','2024-12-05 23:21:52'),(438,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 23:22:53','2024-12-05 23:22:53'),(439,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 23:23:52','2024-12-05 23:23:52'),(440,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 23:24:52','2024-12-05 23:24:52'),(441,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 23:25:52','2024-12-05 23:25:52'),(442,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 23:26:52','2024-12-05 23:26:52'),(443,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 23:27:52','2024-12-05 23:27:52'),(444,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 23:28:52','2024-12-05 23:28:52'),(445,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 23:29:52','2024-12-05 23:29:52'),(446,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 23:30:52','2024-12-05 23:30:52'),(447,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 23:31:52','2024-12-05 23:31:52'),(448,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 23:32:52','2024-12-05 23:32:52'),(449,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 23:33:52','2024-12-05 23:33:52'),(450,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 23:34:52','2024-12-05 23:34:52'),(451,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 23:35:52','2024-12-05 23:35:52'),(452,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 23:36:52','2024-12-05 23:36:52'),(453,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 23:37:52','2024-12-05 23:37:52'),(454,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 23:38:52','2024-12-05 23:38:52'),(455,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 23:39:52','2024-12-05 23:39:52'),(456,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 23:40:52','2024-12-05 23:40:52'),(457,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 23:41:52','2024-12-05 23:41:52'),(458,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 23:42:52','2024-12-05 23:42:52'),(459,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 23:43:52','2024-12-05 23:43:52'),(460,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 23:44:52','2024-12-05 23:44:52'),(461,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 23:45:52','2024-12-05 23:45:52'),(462,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 23:46:52','2024-12-05 23:46:52'),(463,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 23:47:52','2024-12-05 23:47:52'),(464,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 23:48:52','2024-12-05 23:48:52'),(465,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 23:49:52','2024-12-05 23:49:52'),(466,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 23:50:52','2024-12-05 23:50:52'),(467,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 23:51:52','2024-12-05 23:51:52'),(468,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 23:52:52','2024-12-05 23:52:52'),(469,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 23:53:52','2024-12-05 23:53:52'),(470,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 23:54:52','2024-12-05 23:54:52'),(471,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 23:55:52','2024-12-05 23:55:52'),(472,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 23:56:52','2024-12-05 23:56:52'),(473,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 23:57:52','2024-12-05 23:57:52'),(474,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 23:58:52','2024-12-05 23:58:52'),(475,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-05 23:59:52','2024-12-05 23:59:52'),(476,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 00:00:52','2024-12-06 00:00:52'),(477,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 00:01:52','2024-12-06 00:01:52'),(478,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 00:02:52','2024-12-06 00:02:52'),(479,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 00:03:52','2024-12-06 00:03:52'),(480,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 00:04:52','2024-12-06 00:04:52'),(481,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 00:05:52','2024-12-06 00:05:52'),(482,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 00:06:52','2024-12-06 00:06:52'),(483,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 00:07:52','2024-12-06 00:07:52'),(484,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 00:08:52','2024-12-06 00:08:52'),(485,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 00:09:52','2024-12-06 00:09:52'),(486,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 00:10:52','2024-12-06 00:10:52'),(487,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 00:11:52','2024-12-06 00:11:52'),(488,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 00:12:52','2024-12-06 00:12:52'),(489,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 00:13:52','2024-12-06 00:13:52'),(490,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 00:14:52','2024-12-06 00:14:52'),(491,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 00:15:52','2024-12-06 00:15:52'),(492,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 00:16:52','2024-12-06 00:16:52'),(493,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 00:17:52','2024-12-06 00:17:52'),(494,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 00:18:52','2024-12-06 00:18:52'),(495,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 00:19:52','2024-12-06 00:19:52'),(496,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 00:20:52','2024-12-06 00:20:52'),(497,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 00:21:52','2024-12-06 00:21:52'),(498,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 00:22:52','2024-12-06 00:22:52'),(499,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 00:23:52','2024-12-06 00:23:52'),(500,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 00:24:52','2024-12-06 00:24:52'),(501,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 00:25:52','2024-12-06 00:25:52'),(502,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 00:26:52','2024-12-06 00:26:52'),(503,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 00:27:52','2024-12-06 00:27:52'),(504,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 00:28:52','2024-12-06 00:28:52'),(505,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 00:29:52','2024-12-06 00:29:52'),(506,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 00:30:52','2024-12-06 00:30:52'),(507,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 00:31:52','2024-12-06 00:31:52'),(508,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 00:32:52','2024-12-06 00:32:52'),(509,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 00:33:52','2024-12-06 00:33:52'),(510,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 00:34:52','2024-12-06 00:34:52'),(511,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 00:35:52','2024-12-06 00:35:52'),(512,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 00:36:52','2024-12-06 00:36:52'),(513,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 00:37:52','2024-12-06 00:37:52'),(514,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 00:38:52','2024-12-06 00:38:52'),(515,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 00:39:52','2024-12-06 00:39:52'),(516,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 00:40:52','2024-12-06 00:40:52'),(517,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 00:41:52','2024-12-06 00:41:52'),(518,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 00:42:52','2024-12-06 00:42:52'),(519,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 00:43:52','2024-12-06 00:43:52'),(520,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 00:44:52','2024-12-06 00:44:52'),(521,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 00:45:52','2024-12-06 00:45:52'),(522,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 00:46:52','2024-12-06 00:46:52'),(523,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 00:47:52','2024-12-06 00:47:52'),(524,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 00:48:52','2024-12-06 00:48:52'),(525,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 00:49:52','2024-12-06 00:49:52'),(526,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 00:50:52','2024-12-06 00:50:52'),(527,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 00:51:52','2024-12-06 00:51:52'),(528,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 00:52:52','2024-12-06 00:52:52'),(529,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 00:53:52','2024-12-06 00:53:52'),(530,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 00:54:52','2024-12-06 00:54:52'),(531,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 00:55:52','2024-12-06 00:55:52'),(532,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 00:56:52','2024-12-06 00:56:52'),(533,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 00:57:52','2024-12-06 00:57:52'),(534,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 00:58:52','2024-12-06 00:58:52'),(535,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 00:59:52','2024-12-06 00:59:52'),(536,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 01:00:52','2024-12-06 01:00:52'),(537,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 01:01:52','2024-12-06 01:01:52'),(538,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 01:02:52','2024-12-06 01:02:52'),(539,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 01:03:52','2024-12-06 01:03:52'),(540,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 01:04:52','2024-12-06 01:04:52'),(541,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 01:05:52','2024-12-06 01:05:52'),(542,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 01:06:52','2024-12-06 01:06:52'),(543,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 01:07:52','2024-12-06 01:07:52'),(544,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 01:08:52','2024-12-06 01:08:52'),(545,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 01:09:52','2024-12-06 01:09:52'),(546,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 01:10:52','2024-12-06 01:10:52'),(547,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 01:11:52','2024-12-06 01:11:52'),(548,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 01:12:52','2024-12-06 01:12:52'),(549,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 01:13:52','2024-12-06 01:13:52'),(550,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 01:14:52','2024-12-06 01:14:52'),(551,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 01:15:52','2024-12-06 01:15:52'),(552,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 01:16:53','2024-12-06 01:16:53'),(553,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 01:17:52','2024-12-06 01:17:52'),(554,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 01:18:52','2024-12-06 01:18:52'),(555,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 01:19:52','2024-12-06 01:19:52'),(556,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 01:20:52','2024-12-06 01:20:52'),(557,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 01:21:52','2024-12-06 01:21:52'),(558,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 01:22:52','2024-12-06 01:22:52'),(559,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 01:23:52','2024-12-06 01:23:52'),(560,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 01:24:52','2024-12-06 01:24:52'),(561,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 01:25:52','2024-12-06 01:25:52'),(562,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 01:26:52','2024-12-06 01:26:52'),(563,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 01:27:52','2024-12-06 01:27:52'),(564,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 01:28:52','2024-12-06 01:28:52'),(565,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 01:29:52','2024-12-06 01:29:52'),(566,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 01:30:52','2024-12-06 01:30:52'),(567,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 01:31:52','2024-12-06 01:31:52'),(568,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 01:32:52','2024-12-06 01:32:52'),(569,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 01:33:52','2024-12-06 01:33:52'),(570,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 01:34:52','2024-12-06 01:34:52'),(571,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 01:35:52','2024-12-06 01:35:52'),(572,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 01:36:52','2024-12-06 01:36:52'),(573,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 01:37:52','2024-12-06 01:37:52'),(574,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 01:38:52','2024-12-06 01:38:52'),(575,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 01:39:52','2024-12-06 01:39:52'),(576,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 01:40:52','2024-12-06 01:40:52'),(577,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 01:41:52','2024-12-06 01:41:52'),(578,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 01:42:52','2024-12-06 01:42:52'),(579,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 01:43:52','2024-12-06 01:43:52'),(580,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 01:44:52','2024-12-06 01:44:52'),(581,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 01:45:52','2024-12-06 01:45:52'),(582,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 01:46:52','2024-12-06 01:46:52'),(583,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 01:47:52','2024-12-06 01:47:52'),(584,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 01:48:52','2024-12-06 01:48:52'),(585,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 01:49:52','2024-12-06 01:49:52'),(586,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 01:50:52','2024-12-06 01:50:52'),(587,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 01:51:52','2024-12-06 01:51:52'),(588,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 01:52:52','2024-12-06 01:52:52'),(589,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 01:53:52','2024-12-06 01:53:52'),(590,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 01:54:52','2024-12-06 01:54:52'),(591,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 01:55:52','2024-12-06 01:55:52'),(592,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 01:56:52','2024-12-06 01:56:52'),(593,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 01:57:52','2024-12-06 01:57:52'),(594,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 01:58:52','2024-12-06 01:58:52'),(595,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 01:59:52','2024-12-06 01:59:52'),(596,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 02:00:52','2024-12-06 02:00:52'),(597,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 02:01:52','2024-12-06 02:01:52'),(598,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 02:02:52','2024-12-06 02:02:52'),(599,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 02:03:52','2024-12-06 02:03:52'),(600,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 02:04:52','2024-12-06 02:04:52'),(601,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 02:05:52','2024-12-06 02:05:52'),(602,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 02:06:52','2024-12-06 02:06:52'),(603,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 02:07:52','2024-12-06 02:07:52'),(604,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 02:08:52','2024-12-06 02:08:52'),(605,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 02:09:52','2024-12-06 02:09:52'),(606,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 02:10:52','2024-12-06 02:10:52'),(607,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 02:11:52','2024-12-06 02:11:52'),(608,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 02:12:52','2024-12-06 02:12:52'),(609,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 02:13:52','2024-12-06 02:13:52'),(610,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 02:14:52','2024-12-06 02:14:52'),(611,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 02:15:52','2024-12-06 02:15:52'),(612,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 02:16:52','2024-12-06 02:16:52'),(613,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 02:17:52','2024-12-06 02:17:52'),(614,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 02:18:52','2024-12-06 02:18:52'),(615,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 02:19:52','2024-12-06 02:19:52'),(616,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 02:20:52','2024-12-06 02:20:52'),(617,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 02:21:52','2024-12-06 02:21:52'),(618,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 02:22:52','2024-12-06 02:22:52'),(619,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 02:23:52','2024-12-06 02:23:52'),(620,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 02:24:52','2024-12-06 02:24:52'),(621,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 02:25:52','2024-12-06 02:25:52'),(622,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 02:26:52','2024-12-06 02:26:52'),(623,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 02:27:52','2024-12-06 02:27:52'),(624,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 02:28:52','2024-12-06 02:28:52'),(625,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 02:29:52','2024-12-06 02:29:52'),(626,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 02:30:52','2024-12-06 02:30:52'),(627,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 02:31:52','2024-12-06 02:31:52'),(628,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 02:32:52','2024-12-06 02:32:52'),(629,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 02:33:52','2024-12-06 02:33:52'),(630,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 02:34:52','2024-12-06 02:34:52'),(631,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 02:35:52','2024-12-06 02:35:52'),(632,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 02:36:52','2024-12-06 02:36:52'),(633,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 02:37:52','2024-12-06 02:37:52'),(634,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 02:38:52','2024-12-06 02:38:52'),(635,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 02:39:52','2024-12-06 02:39:52'),(636,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 02:40:52','2024-12-06 02:40:52'),(637,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 02:41:52','2024-12-06 02:41:52'),(638,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 02:42:52','2024-12-06 02:42:52'),(639,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 02:43:52','2024-12-06 02:43:52'),(640,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 02:44:52','2024-12-06 02:44:52'),(641,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 02:45:52','2024-12-06 02:45:52'),(642,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 02:46:52','2024-12-06 02:46:52'),(643,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 02:47:52','2024-12-06 02:47:52'),(644,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 02:48:52','2024-12-06 02:48:52'),(645,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 02:49:52','2024-12-06 02:49:52'),(646,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 02:50:52','2024-12-06 02:50:52'),(647,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 02:51:52','2024-12-06 02:51:52'),(648,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 02:52:52','2024-12-06 02:52:52'),(649,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 02:53:52','2024-12-06 02:53:52'),(650,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 02:54:52','2024-12-06 02:54:52'),(651,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 02:55:52','2024-12-06 02:55:52'),(652,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 02:56:52','2024-12-06 02:56:52'),(653,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 02:57:52','2024-12-06 02:57:52'),(654,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 02:58:52','2024-12-06 02:58:52'),(655,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 02:59:52','2024-12-06 02:59:52'),(656,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 03:00:52','2024-12-06 03:00:52'),(657,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 03:01:52','2024-12-06 03:01:52'),(658,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 03:02:52','2024-12-06 03:02:52'),(659,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 03:03:52','2024-12-06 03:03:52'),(660,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 03:04:52','2024-12-06 03:04:52'),(661,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 03:05:52','2024-12-06 03:05:52'),(662,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 03:06:52','2024-12-06 03:06:52'),(663,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 03:07:52','2024-12-06 03:07:52'),(664,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 03:08:53','2024-12-06 03:08:53'),(665,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 03:09:52','2024-12-06 03:09:52'),(666,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 03:10:52','2024-12-06 03:10:52'),(667,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 03:11:52','2024-12-06 03:11:52'),(668,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 03:12:52','2024-12-06 03:12:52'),(669,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 03:13:52','2024-12-06 03:13:52'),(670,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 03:14:52','2024-12-06 03:14:52'),(671,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 03:15:52','2024-12-06 03:15:52'),(672,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 03:16:52','2024-12-06 03:16:52'),(673,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 03:17:52','2024-12-06 03:17:52'),(674,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 03:18:52','2024-12-06 03:18:52'),(675,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 03:19:52','2024-12-06 03:19:52'),(676,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 03:20:52','2024-12-06 03:20:52'),(677,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 03:21:52','2024-12-06 03:21:52'),(678,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 03:22:52','2024-12-06 03:22:52'),(679,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 03:23:52','2024-12-06 03:23:52'),(680,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 03:24:52','2024-12-06 03:24:52'),(681,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 03:25:52','2024-12-06 03:25:52'),(682,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 03:26:52','2024-12-06 03:26:52'),(683,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 03:27:52','2024-12-06 03:27:52'),(684,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 03:28:52','2024-12-06 03:28:52'),(685,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 03:29:52','2024-12-06 03:29:52'),(686,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 03:30:52','2024-12-06 03:30:52'),(687,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 03:31:52','2024-12-06 03:31:52'),(688,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 03:32:52','2024-12-06 03:32:52'),(689,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 03:33:52','2024-12-06 03:33:52'),(690,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 03:34:52','2024-12-06 03:34:52'),(691,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 03:35:52','2024-12-06 03:35:52'),(692,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 03:36:52','2024-12-06 03:36:52'),(693,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 03:37:52','2024-12-06 03:37:52'),(694,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 03:38:52','2024-12-06 03:38:52'),(695,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 03:39:52','2024-12-06 03:39:52'),(696,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 03:40:52','2024-12-06 03:40:52'),(697,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 03:41:52','2024-12-06 03:41:52'),(698,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 03:42:52','2024-12-06 03:42:52'),(699,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 03:43:52','2024-12-06 03:43:52'),(700,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 03:44:52','2024-12-06 03:44:52'),(701,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 03:45:52','2024-12-06 03:45:52'),(702,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 03:46:52','2024-12-06 03:46:52'),(703,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 03:47:52','2024-12-06 03:47:52'),(704,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 03:48:52','2024-12-06 03:48:52'),(705,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 03:49:52','2024-12-06 03:49:52'),(706,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 03:50:52','2024-12-06 03:50:52'),(707,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 03:51:52','2024-12-06 03:51:52'),(708,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 03:52:52','2024-12-06 03:52:52'),(709,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 03:53:52','2024-12-06 03:53:52'),(710,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 03:54:52','2024-12-06 03:54:52'),(711,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 03:55:52','2024-12-06 03:55:52'),(712,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 03:56:52','2024-12-06 03:56:52'),(713,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 03:57:52','2024-12-06 03:57:52'),(714,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 03:58:52','2024-12-06 03:58:52'),(715,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 03:59:52','2024-12-06 03:59:52'),(716,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 04:00:52','2024-12-06 04:00:52'),(717,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 04:01:52','2024-12-06 04:01:52'),(718,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 04:02:52','2024-12-06 04:02:52'),(719,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 04:03:52','2024-12-06 04:03:52'),(720,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 04:04:52','2024-12-06 04:04:52'),(721,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 04:05:52','2024-12-06 04:05:52'),(722,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 04:06:52','2024-12-06 04:06:52'),(723,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 04:07:52','2024-12-06 04:07:52'),(724,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 04:08:52','2024-12-06 04:08:52'),(725,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 04:09:52','2024-12-06 04:09:52'),(726,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 04:10:52','2024-12-06 04:10:52'),(727,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 04:11:52','2024-12-06 04:11:52'),(728,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 04:12:52','2024-12-06 04:12:52'),(729,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 04:13:52','2024-12-06 04:13:52'),(730,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 04:14:52','2024-12-06 04:14:52'),(731,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 04:15:52','2024-12-06 04:15:52'),(732,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 04:16:52','2024-12-06 04:16:52'),(733,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 04:17:52','2024-12-06 04:17:52'),(734,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 04:18:52','2024-12-06 04:18:52'),(735,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 04:19:52','2024-12-06 04:19:52'),(736,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 04:20:52','2024-12-06 04:20:52'),(737,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 04:21:52','2024-12-06 04:21:52'),(738,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 04:22:52','2024-12-06 04:22:52'),(739,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 04:23:52','2024-12-06 04:23:52'),(740,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 04:24:52','2024-12-06 04:24:52'),(741,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 04:25:52','2024-12-06 04:25:52'),(742,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 04:26:52','2024-12-06 04:26:52'),(743,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 04:27:52','2024-12-06 04:27:52'),(744,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 04:28:52','2024-12-06 04:28:52'),(745,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 04:29:52','2024-12-06 04:29:52'),(746,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 04:30:52','2024-12-06 04:30:52'),(747,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 04:31:52','2024-12-06 04:31:52'),(748,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 04:32:52','2024-12-06 04:32:52'),(749,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 04:33:52','2024-12-06 04:33:52'),(750,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 04:34:52','2024-12-06 04:34:52'),(751,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 04:35:52','2024-12-06 04:35:52'),(752,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 04:36:52','2024-12-06 04:36:52'),(753,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 04:37:52','2024-12-06 04:37:52'),(754,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 04:38:52','2024-12-06 04:38:52'),(755,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 04:39:52','2024-12-06 04:39:52'),(756,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 04:40:52','2024-12-06 04:40:52'),(757,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 04:41:52','2024-12-06 04:41:52'),(758,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 04:42:52','2024-12-06 04:42:52'),(759,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 04:43:52','2024-12-06 04:43:52'),(760,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 04:44:52','2024-12-06 04:44:52'),(761,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 04:45:52','2024-12-06 04:45:52'),(762,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 04:46:52','2024-12-06 04:46:52'),(763,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 04:47:52','2024-12-06 04:47:52'),(764,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 04:48:52','2024-12-06 04:48:52'),(765,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 04:49:52','2024-12-06 04:49:52'),(766,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 04:50:52','2024-12-06 04:50:52'),(767,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 04:51:52','2024-12-06 04:51:52'),(768,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 04:52:53','2024-12-06 04:52:53'),(769,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 04:53:52','2024-12-06 04:53:52'),(770,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 04:54:52','2024-12-06 04:54:52'),(771,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 04:55:52','2024-12-06 04:55:52'),(772,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 04:56:52','2024-12-06 04:56:52'),(773,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 04:57:52','2024-12-06 04:57:52'),(774,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 04:58:52','2024-12-06 04:58:52'),(775,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 04:59:52','2024-12-06 04:59:52'),(776,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 05:00:52','2024-12-06 05:00:52'),(777,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 05:01:52','2024-12-06 05:01:52'),(778,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 05:02:52','2024-12-06 05:02:52'),(779,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 05:03:52','2024-12-06 05:03:52'),(780,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 05:04:52','2024-12-06 05:04:52'),(781,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 05:05:52','2024-12-06 05:05:52'),(782,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 05:06:52','2024-12-06 05:06:52'),(783,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 05:07:52','2024-12-06 05:07:52'),(784,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 05:08:52','2024-12-06 05:08:52'),(785,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 05:09:52','2024-12-06 05:09:52'),(786,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 05:10:52','2024-12-06 05:10:52'),(787,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 05:11:52','2024-12-06 05:11:52'),(788,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 05:12:52','2024-12-06 05:12:52'),(789,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 05:13:52','2024-12-06 05:13:52'),(790,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 05:14:52','2024-12-06 05:14:52'),(791,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 05:15:52','2024-12-06 05:15:52'),(792,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 05:16:52','2024-12-06 05:16:52'),(793,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 05:17:52','2024-12-06 05:17:52'),(794,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 05:18:52','2024-12-06 05:18:52'),(795,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 05:19:52','2024-12-06 05:19:52'),(796,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 05:20:52','2024-12-06 05:20:52'),(797,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 05:21:52','2024-12-06 05:21:52'),(798,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 05:22:52','2024-12-06 05:22:52'),(799,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 05:23:52','2024-12-06 05:23:52'),(800,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 05:24:52','2024-12-06 05:24:52'),(801,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 05:25:52','2024-12-06 05:25:52'),(802,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 05:26:52','2024-12-06 05:26:52'),(803,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 05:27:52','2024-12-06 05:27:52'),(804,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 05:28:52','2024-12-06 05:28:52'),(805,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 05:29:52','2024-12-06 05:29:52'),(806,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 05:30:52','2024-12-06 05:30:52'),(807,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 05:31:52','2024-12-06 05:31:52'),(808,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 05:32:52','2024-12-06 05:32:52'),(809,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 05:33:52','2024-12-06 05:33:52'),(810,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 05:34:52','2024-12-06 05:34:52'),(811,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 05:35:52','2024-12-06 05:35:52'),(812,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 05:36:52','2024-12-06 05:36:52'),(813,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 05:37:52','2024-12-06 05:37:52'),(814,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 05:38:52','2024-12-06 05:38:52'),(815,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 05:39:52','2024-12-06 05:39:52'),(816,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 05:40:52','2024-12-06 05:40:52'),(817,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 05:41:52','2024-12-06 05:41:52'),(818,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 05:42:52','2024-12-06 05:42:52'),(819,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 05:43:52','2024-12-06 05:43:52'),(820,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 05:44:52','2024-12-06 05:44:52'),(821,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 05:45:52','2024-12-06 05:45:52'),(822,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 05:46:52','2024-12-06 05:46:52'),(823,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 05:47:52','2024-12-06 05:47:52'),(824,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 05:48:52','2024-12-06 05:48:52'),(825,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 05:49:52','2024-12-06 05:49:52'),(826,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 05:50:52','2024-12-06 05:50:52'),(827,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 05:51:52','2024-12-06 05:51:52'),(828,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 05:52:52','2024-12-06 05:52:52'),(829,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 05:53:52','2024-12-06 05:53:52'),(830,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 05:54:52','2024-12-06 05:54:52'),(831,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 05:55:52','2024-12-06 05:55:52'),(832,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 05:56:52','2024-12-06 05:56:52'),(833,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 05:57:52','2024-12-06 05:57:52'),(834,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 05:58:52','2024-12-06 05:58:52'),(835,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 05:59:52','2024-12-06 05:59:52'),(836,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 06:00:52','2024-12-06 06:00:52'),(837,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 06:01:52','2024-12-06 06:01:52'),(838,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 06:02:52','2024-12-06 06:02:52'),(839,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 06:03:52','2024-12-06 06:03:52'),(840,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 06:04:52','2024-12-06 06:04:52'),(841,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 06:05:52','2024-12-06 06:05:52'),(842,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 06:06:52','2024-12-06 06:06:52'),(843,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 06:07:52','2024-12-06 06:07:52'),(844,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 06:08:52','2024-12-06 06:08:52'),(845,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 06:09:52','2024-12-06 06:09:52'),(846,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 06:10:52','2024-12-06 06:10:52'),(847,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 06:11:52','2024-12-06 06:11:52'),(848,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 06:12:52','2024-12-06 06:12:52'),(849,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 06:13:52','2024-12-06 06:13:52'),(850,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 06:14:52','2024-12-06 06:14:52'),(851,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 06:15:52','2024-12-06 06:15:52'),(852,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 06:16:52','2024-12-06 06:16:52'),(853,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 06:17:52','2024-12-06 06:17:52'),(854,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 06:18:52','2024-12-06 06:18:52'),(855,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 06:19:52','2024-12-06 06:19:52'),(856,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 06:20:52','2024-12-06 06:20:52'),(857,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 06:21:52','2024-12-06 06:21:52'),(858,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 06:22:52','2024-12-06 06:22:52'),(859,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 06:23:52','2024-12-06 06:23:52'),(860,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 06:24:52','2024-12-06 06:24:52'),(861,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 06:25:52','2024-12-06 06:25:52'),(862,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 06:26:52','2024-12-06 06:26:52'),(863,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 06:27:52','2024-12-06 06:27:52'),(864,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 06:28:52','2024-12-06 06:28:52'),(865,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 06:29:52','2024-12-06 06:29:52'),(866,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 06:30:52','2024-12-06 06:30:52'),(867,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 06:31:52','2024-12-06 06:31:52'),(868,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 06:32:52','2024-12-06 06:32:52'),(869,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 06:33:52','2024-12-06 06:33:52'),(870,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 06:34:52','2024-12-06 06:34:52'),(871,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 06:35:52','2024-12-06 06:35:52'),(872,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 06:36:52','2024-12-06 06:36:52'),(873,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 06:37:52','2024-12-06 06:37:52'),(874,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 06:38:52','2024-12-06 06:38:52'),(875,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 06:39:52','2024-12-06 06:39:52'),(876,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 06:40:52','2024-12-06 06:40:52'),(877,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 06:41:52','2024-12-06 06:41:52'),(878,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 06:42:52','2024-12-06 06:42:52'),(879,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 06:43:52','2024-12-06 06:43:52'),(880,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 06:44:52','2024-12-06 06:44:52'),(881,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 06:45:52','2024-12-06 06:45:52'),(882,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 06:46:52','2024-12-06 06:46:52'),(883,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 06:47:52','2024-12-06 06:47:52'),(884,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 06:48:52','2024-12-06 06:48:52'),(885,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 06:49:52','2024-12-06 06:49:52'),(886,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 06:50:52','2024-12-06 06:50:52'),(887,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 06:51:52','2024-12-06 06:51:52'),(888,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 06:52:52','2024-12-06 06:52:52'),(889,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 06:53:52','2024-12-06 06:53:52'),(890,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 06:54:52','2024-12-06 06:54:52'),(891,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 06:55:52','2024-12-06 06:55:52'),(892,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 06:56:52','2024-12-06 06:56:52'),(893,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 06:57:52','2024-12-06 06:57:52'),(894,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 06:58:52','2024-12-06 06:58:52'),(895,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 06:59:52','2024-12-06 06:59:52'),(896,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 07:00:52','2024-12-06 07:00:52'),(897,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 07:01:52','2024-12-06 07:01:52'),(898,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 07:02:52','2024-12-06 07:02:52'),(899,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 07:03:52','2024-12-06 07:03:52'),(900,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 07:04:52','2024-12-06 07:04:52'),(901,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 07:05:52','2024-12-06 07:05:52'),(902,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 07:06:52','2024-12-06 07:06:52'),(903,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 07:07:52','2024-12-06 07:07:52'),(904,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 07:08:52','2024-12-06 07:08:52'),(905,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 07:09:52','2024-12-06 07:09:52'),(906,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 07:10:52','2024-12-06 07:10:52'),(907,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 07:11:52','2024-12-06 07:11:52'),(908,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 07:12:52','2024-12-06 07:12:52'),(909,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 07:13:52','2024-12-06 07:13:52'),(910,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 07:14:52','2024-12-06 07:14:52'),(911,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 07:15:52','2024-12-06 07:15:52'),(912,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 07:16:52','2024-12-06 07:16:52'),(913,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 07:17:52','2024-12-06 07:17:52'),(914,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 07:18:52','2024-12-06 07:18:52'),(915,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 07:19:52','2024-12-06 07:19:52'),(916,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 07:20:52','2024-12-06 07:20:52'),(917,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 07:21:52','2024-12-06 07:21:52'),(918,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 07:22:52','2024-12-06 07:22:52'),(919,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 07:23:52','2024-12-06 07:23:52'),(920,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 07:24:52','2024-12-06 07:24:52'),(921,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 07:25:52','2024-12-06 07:25:52'),(922,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 07:26:52','2024-12-06 07:26:52'),(923,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 07:27:52','2024-12-06 07:27:52'),(924,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 07:28:52','2024-12-06 07:28:52'),(925,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 07:29:52','2024-12-06 07:29:52'),(926,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 07:30:52','2024-12-06 07:30:52'),(927,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 07:31:52','2024-12-06 07:31:52'),(928,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 07:32:52','2024-12-06 07:32:52'),(929,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 07:33:52','2024-12-06 07:33:52'),(930,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 07:34:52','2024-12-06 07:34:52'),(931,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 07:35:52','2024-12-06 07:35:52'),(932,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 07:36:52','2024-12-06 07:36:52'),(933,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 07:37:52','2024-12-06 07:37:52'),(934,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 07:38:52','2024-12-06 07:38:52'),(935,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 07:39:52','2024-12-06 07:39:52'),(936,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 07:40:52','2024-12-06 07:40:52'),(937,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 07:41:52','2024-12-06 07:41:52'),(938,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 07:42:52','2024-12-06 07:42:52'),(939,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 07:43:52','2024-12-06 07:43:52'),(940,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 07:44:52','2024-12-06 07:44:52'),(941,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 07:45:52','2024-12-06 07:45:52'),(942,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 07:46:52','2024-12-06 07:46:52'),(943,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 07:47:52','2024-12-06 07:47:52'),(944,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 07:48:52','2024-12-06 07:48:52'),(945,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 07:49:52','2024-12-06 07:49:52'),(946,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 07:50:52','2024-12-06 07:50:52'),(947,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 07:51:52','2024-12-06 07:51:52'),(948,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 07:52:52','2024-12-06 07:52:52'),(949,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 07:53:52','2024-12-06 07:53:52'),(950,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 07:54:52','2024-12-06 07:54:52'),(951,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 07:55:52','2024-12-06 07:55:52'),(952,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 07:56:52','2024-12-06 07:56:52'),(953,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 07:57:52','2024-12-06 07:57:52'),(954,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 07:58:52','2024-12-06 07:58:52'),(955,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 07:59:52','2024-12-06 07:59:52'),(956,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 08:00:52','2024-12-06 08:00:52'),(957,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 08:01:52','2024-12-06 08:01:52'),(958,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 08:02:52','2024-12-06 08:02:52'),(959,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 08:03:52','2024-12-06 08:03:52'),(960,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 08:04:52','2024-12-06 08:04:52'),(961,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 08:05:52','2024-12-06 08:05:52'),(962,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 08:06:52','2024-12-06 08:06:52'),(963,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 08:07:52','2024-12-06 08:07:52'),(964,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 08:08:52','2024-12-06 08:08:52'),(965,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 08:09:52','2024-12-06 08:09:52'),(966,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 08:10:52','2024-12-06 08:10:52'),(967,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 08:11:52','2024-12-06 08:11:52'),(968,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 08:12:52','2024-12-06 08:12:52'),(969,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 08:13:52','2024-12-06 08:13:52'),(970,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 08:14:52','2024-12-06 08:14:52'),(971,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 08:15:52','2024-12-06 08:15:52'),(972,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 08:16:52','2024-12-06 08:16:52'),(973,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 08:17:52','2024-12-06 08:17:52'),(974,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 08:18:52','2024-12-06 08:18:52'),(975,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 08:19:52','2024-12-06 08:19:52'),(976,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 08:20:52','2024-12-06 08:20:52'),(977,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 08:21:52','2024-12-06 08:21:52'),(978,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 08:22:52','2024-12-06 08:22:52'),(979,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 08:23:52','2024-12-06 08:23:52'),(980,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 08:24:52','2024-12-06 08:24:52'),(981,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 08:25:52','2024-12-06 08:25:52'),(982,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 08:26:52','2024-12-06 08:26:52'),(983,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 08:27:52','2024-12-06 08:27:52'),(984,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 08:28:52','2024-12-06 08:28:52'),(985,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 08:29:52','2024-12-06 08:29:52'),(986,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 08:30:52','2024-12-06 08:30:52'),(987,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 08:31:52','2024-12-06 08:31:52'),(988,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 08:32:52','2024-12-06 08:32:52'),(989,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 08:33:52','2024-12-06 08:33:52'),(990,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 08:34:52','2024-12-06 08:34:52'),(991,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 08:35:52','2024-12-06 08:35:52'),(992,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 08:36:52','2024-12-06 08:36:52'),(993,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 08:37:52','2024-12-06 08:37:52'),(994,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 08:38:52','2024-12-06 08:38:52'),(995,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 08:39:52','2024-12-06 08:39:52'),(996,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 08:40:52','2024-12-06 08:40:52'),(997,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 08:41:52','2024-12-06 08:41:52'),(998,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 08:42:52','2024-12-06 08:42:52'),(999,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 08:43:52','2024-12-06 08:43:52'),(1000,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 08:44:52','2024-12-06 08:44:52'),(1001,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 08:45:52','2024-12-06 08:45:52'),(1002,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 08:46:52','2024-12-06 08:46:52'),(1003,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 08:47:52','2024-12-06 08:47:52'),(1004,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 08:48:52','2024-12-06 08:48:52'),(1005,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 08:49:52','2024-12-06 08:49:52'),(1006,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 08:50:52','2024-12-06 08:50:52'),(1007,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 08:51:52','2024-12-06 08:51:52'),(1008,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 08:52:52','2024-12-06 08:52:52'),(1009,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 08:53:52','2024-12-06 08:53:52'),(1010,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 08:54:52','2024-12-06 08:54:52'),(1011,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 08:55:52','2024-12-06 08:55:52'),(1012,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 08:56:52','2024-12-06 08:56:52'),(1013,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 08:57:52','2024-12-06 08:57:52'),(1014,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 08:58:52','2024-12-06 08:58:52'),(1015,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 08:59:52','2024-12-06 08:59:52'),(1016,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 09:00:52','2024-12-06 09:00:52'),(1017,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 09:01:52','2024-12-06 09:01:52'),(1018,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 09:02:52','2024-12-06 09:02:52'),(1019,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 09:03:52','2024-12-06 09:03:52'),(1020,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 09:04:52','2024-12-06 09:04:52'),(1021,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 09:05:52','2024-12-06 09:05:52'),(1022,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 09:06:52','2024-12-06 09:06:52'),(1023,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 09:07:52','2024-12-06 09:07:52'),(1024,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 09:08:52','2024-12-06 09:08:52'),(1025,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 09:09:52','2024-12-06 09:09:52'),(1026,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 09:10:52','2024-12-06 09:10:52'),(1027,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 09:11:52','2024-12-06 09:11:52'),(1028,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 09:12:52','2024-12-06 09:12:52'),(1029,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 09:13:52','2024-12-06 09:13:52'),(1030,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 09:14:52','2024-12-06 09:14:52'),(1031,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 09:15:52','2024-12-06 09:15:52'),(1032,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 09:16:52','2024-12-06 09:16:52'),(1033,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 09:17:52','2024-12-06 09:17:52'),(1034,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 09:18:52','2024-12-06 09:18:52'),(1035,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 09:19:52','2024-12-06 09:19:52'),(1036,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 09:20:52','2024-12-06 09:20:52'),(1037,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 09:21:52','2024-12-06 09:21:52'),(1038,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 09:22:52','2024-12-06 09:22:52'),(1039,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 09:23:52','2024-12-06 09:23:52'),(1040,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 09:24:52','2024-12-06 09:24:52'),(1041,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 09:25:52','2024-12-06 09:25:52'),(1042,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 09:26:52','2024-12-06 09:26:52'),(1043,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 09:27:52','2024-12-06 09:27:52'),(1044,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 09:28:52','2024-12-06 09:28:52'),(1045,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 09:29:52','2024-12-06 09:29:52'),(1046,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 09:30:52','2024-12-06 09:30:52'),(1047,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 09:31:52','2024-12-06 09:31:52'),(1048,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 09:32:52','2024-12-06 09:32:52'),(1049,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 09:33:52','2024-12-06 09:33:52'),(1050,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 09:34:52','2024-12-06 09:34:52'),(1051,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 09:35:52','2024-12-06 09:35:52'),(1052,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 09:36:52','2024-12-06 09:36:52'),(1053,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 09:37:52','2024-12-06 09:37:52'),(1054,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 09:38:52','2024-12-06 09:38:52'),(1055,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 09:39:52','2024-12-06 09:39:52'),(1056,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 09:40:52','2024-12-06 09:40:52'),(1057,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 09:41:52','2024-12-06 09:41:52'),(1058,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 09:42:52','2024-12-06 09:42:52'),(1059,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 09:43:52','2024-12-06 09:43:52'),(1060,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 09:44:52','2024-12-06 09:44:52'),(1061,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 09:45:52','2024-12-06 09:45:52'),(1062,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 09:46:52','2024-12-06 09:46:52'),(1063,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 09:47:52','2024-12-06 09:47:52'),(1064,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 09:48:52','2024-12-06 09:48:52'),(1065,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 09:49:52','2024-12-06 09:49:52'),(1066,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 09:50:52','2024-12-06 09:50:52'),(1067,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 09:51:52','2024-12-06 09:51:52'),(1068,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 09:52:52','2024-12-06 09:52:52'),(1069,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 09:53:52','2024-12-06 09:53:52'),(1070,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 09:54:52','2024-12-06 09:54:52'),(1071,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 09:55:52','2024-12-06 09:55:52'),(1072,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 09:56:52','2024-12-06 09:56:52'),(1073,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 09:57:52','2024-12-06 09:57:52'),(1074,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 09:58:52','2024-12-06 09:58:52'),(1075,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 09:59:52','2024-12-06 09:59:52'),(1076,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 10:00:52','2024-12-06 10:00:52'),(1077,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 10:01:52','2024-12-06 10:01:52'),(1078,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 10:02:52','2024-12-06 10:02:52'),(1079,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 10:03:52','2024-12-06 10:03:52'),(1080,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 10:04:52','2024-12-06 10:04:52'),(1081,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 10:05:52','2024-12-06 10:05:52'),(1082,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 10:06:52','2024-12-06 10:06:52'),(1083,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 10:07:52','2024-12-06 10:07:52'),(1084,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 10:08:52','2024-12-06 10:08:52'),(1085,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 10:09:52','2024-12-06 10:09:52'),(1086,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 10:10:52','2024-12-06 10:10:52'),(1087,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 10:11:52','2024-12-06 10:11:52'),(1088,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 10:12:52','2024-12-06 10:12:52'),(1089,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 10:13:52','2024-12-06 10:13:52'),(1090,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 10:14:52','2024-12-06 10:14:52'),(1091,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 10:15:52','2024-12-06 10:15:52'),(1092,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 10:16:52','2024-12-06 10:16:52'),(1093,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 10:17:52','2024-12-06 10:17:52'),(1094,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 10:18:52','2024-12-06 10:18:52'),(1095,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 10:19:52','2024-12-06 10:19:52'),(1096,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 10:20:52','2024-12-06 10:20:52'),(1097,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 10:21:52','2024-12-06 10:21:52'),(1098,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 10:22:52','2024-12-06 10:22:52'),(1099,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 10:23:52','2024-12-06 10:23:52'),(1100,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 10:24:52','2024-12-06 10:24:52'),(1101,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 10:25:52','2024-12-06 10:25:52'),(1102,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 10:26:52','2024-12-06 10:26:52'),(1103,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 10:27:52','2024-12-06 10:27:52'),(1104,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 10:28:52','2024-12-06 10:28:52'),(1105,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 10:29:52','2024-12-06 10:29:52'),(1106,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 10:30:52','2024-12-06 10:30:52'),(1107,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 10:31:52','2024-12-06 10:31:52'),(1108,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 10:32:52','2024-12-06 10:32:52'),(1109,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 10:33:52','2024-12-06 10:33:52'),(1110,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 10:34:52','2024-12-06 10:34:52'),(1111,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 10:35:52','2024-12-06 10:35:52'),(1112,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 10:36:52','2024-12-06 10:36:52'),(1113,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 10:37:52','2024-12-06 10:37:52'),(1114,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 10:38:52','2024-12-06 10:38:52'),(1115,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 10:39:52','2024-12-06 10:39:52'),(1116,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 10:40:52','2024-12-06 10:40:52'),(1117,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 10:41:52','2024-12-06 10:41:52'),(1118,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 10:42:52','2024-12-06 10:42:52'),(1119,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 10:43:52','2024-12-06 10:43:52'),(1120,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 10:44:52','2024-12-06 10:44:52'),(1121,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 10:45:52','2024-12-06 10:45:52'),(1122,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 10:46:52','2024-12-06 10:46:52'),(1123,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 10:47:52','2024-12-06 10:47:52'),(1124,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 10:48:52','2024-12-06 10:48:52'),(1125,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 10:49:52','2024-12-06 10:49:52'),(1126,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 10:50:52','2024-12-06 10:50:52'),(1127,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 10:51:52','2024-12-06 10:51:52'),(1128,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 10:52:52','2024-12-06 10:52:52'),(1129,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 10:53:52','2024-12-06 10:53:52'),(1130,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 10:54:52','2024-12-06 10:54:52'),(1131,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 10:55:52','2024-12-06 10:55:52'),(1132,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 10:56:52','2024-12-06 10:56:52'),(1133,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 10:57:52','2024-12-06 10:57:52'),(1134,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 10:58:52','2024-12-06 10:58:52'),(1135,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 10:59:52','2024-12-06 10:59:52'),(1136,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 11:00:52','2024-12-06 11:00:52'),(1137,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 11:01:52','2024-12-06 11:01:52'),(1138,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 11:02:52','2024-12-06 11:02:52'),(1139,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 11:03:52','2024-12-06 11:03:52'),(1140,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 11:04:52','2024-12-06 11:04:52'),(1141,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 11:05:52','2024-12-06 11:05:52'),(1142,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 11:06:52','2024-12-06 11:06:52'),(1143,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 11:07:52','2024-12-06 11:07:52'),(1144,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 11:08:52','2024-12-06 11:08:52'),(1145,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 11:09:52','2024-12-06 11:09:52'),(1146,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 11:10:52','2024-12-06 11:10:52'),(1147,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 11:11:52','2024-12-06 11:11:52'),(1148,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 11:12:52','2024-12-06 11:12:52'),(1149,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 11:13:52','2024-12-06 11:13:52'),(1150,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 11:14:52','2024-12-06 11:14:52'),(1151,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 11:15:52','2024-12-06 11:15:52'),(1152,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 11:16:52','2024-12-06 11:16:52'),(1153,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 11:17:52','2024-12-06 11:17:52'),(1154,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 11:18:52','2024-12-06 11:18:52'),(1155,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 11:19:52','2024-12-06 11:19:52'),(1156,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 11:20:52','2024-12-06 11:20:52'),(1157,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 11:21:52','2024-12-06 11:21:52'),(1158,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 11:22:52','2024-12-06 11:22:52'),(1159,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 11:23:52','2024-12-06 11:23:52'),(1160,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 11:24:52','2024-12-06 11:24:52'),(1161,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 11:25:52','2024-12-06 11:25:52'),(1162,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 11:26:52','2024-12-06 11:26:52'),(1163,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 11:27:53','2024-12-06 11:27:53'),(1164,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 11:28:52','2024-12-06 11:28:52'),(1165,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 11:29:52','2024-12-06 11:29:52'),(1166,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 11:30:52','2024-12-06 11:30:52'),(1167,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 11:31:52','2024-12-06 11:31:52'),(1168,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 11:32:52','2024-12-06 11:32:52'),(1169,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 11:33:52','2024-12-06 11:33:52'),(1170,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 11:34:52','2024-12-06 11:34:52'),(1171,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 11:35:52','2024-12-06 11:35:52'),(1172,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 11:36:52','2024-12-06 11:36:52'),(1173,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 11:37:52','2024-12-06 11:37:52'),(1174,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 11:38:52','2024-12-06 11:38:52'),(1175,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 11:39:52','2024-12-06 11:39:52'),(1176,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 11:40:52','2024-12-06 11:40:52'),(1177,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 11:41:52','2024-12-06 11:41:52'),(1178,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 11:42:52','2024-12-06 11:42:52'),(1179,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 11:43:52','2024-12-06 11:43:52'),(1180,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 11:44:52','2024-12-06 11:44:52'),(1181,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 11:45:52','2024-12-06 11:45:52'),(1182,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 11:46:52','2024-12-06 11:46:52'),(1183,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 11:47:52','2024-12-06 11:47:52'),(1184,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 11:48:52','2024-12-06 11:48:52'),(1185,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 11:49:52','2024-12-06 11:49:52'),(1186,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 11:50:52','2024-12-06 11:50:52'),(1187,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 11:51:52','2024-12-06 11:51:52'),(1188,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 11:52:52','2024-12-06 11:52:52'),(1189,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 11:53:52','2024-12-06 11:53:52'),(1190,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 11:54:52','2024-12-06 11:54:52'),(1191,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 11:55:52','2024-12-06 11:55:52'),(1192,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 11:56:52','2024-12-06 11:56:52'),(1193,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 11:57:52','2024-12-06 11:57:52'),(1194,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 11:58:52','2024-12-06 11:58:52'),(1195,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 11:59:52','2024-12-06 11:59:52'),(1196,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 12:00:52','2024-12-06 12:00:52'),(1197,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 12:01:52','2024-12-06 12:01:52'),(1198,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 12:02:52','2024-12-06 12:02:52'),(1199,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 12:03:52','2024-12-06 12:03:52'),(1200,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 12:04:52','2024-12-06 12:04:52'),(1201,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 12:05:52','2024-12-06 12:05:52'),(1202,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 12:06:52','2024-12-06 12:06:52'),(1203,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 12:07:52','2024-12-06 12:07:52'),(1204,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 12:08:52','2024-12-06 12:08:52'),(1205,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 12:09:52','2024-12-06 12:09:52'),(1206,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 12:10:52','2024-12-06 12:10:52'),(1207,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 12:11:52','2024-12-06 12:11:52'),(1208,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 12:12:52','2024-12-06 12:12:52'),(1209,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 12:13:52','2024-12-06 12:13:52'),(1210,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 12:14:52','2024-12-06 12:14:52'),(1211,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 12:15:52','2024-12-06 12:15:52'),(1212,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 12:16:52','2024-12-06 12:16:52'),(1213,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 12:17:52','2024-12-06 12:17:52'),(1214,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 12:18:52','2024-12-06 12:18:52'),(1215,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 12:19:52','2024-12-06 12:19:52'),(1216,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 12:20:52','2024-12-06 12:20:52'),(1217,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 12:21:52','2024-12-06 12:21:52'),(1218,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 12:22:52','2024-12-06 12:22:52'),(1219,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 12:23:52','2024-12-06 12:23:52'),(1220,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 12:24:52','2024-12-06 12:24:52'),(1221,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 12:25:52','2024-12-06 12:25:52'),(1222,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 12:26:52','2024-12-06 12:26:52'),(1223,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 12:27:52','2024-12-06 12:27:52'),(1224,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 12:28:52','2024-12-06 12:28:52'),(1225,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 12:29:52','2024-12-06 12:29:52'),(1226,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 12:30:52','2024-12-06 12:30:52'),(1227,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 12:31:52','2024-12-06 12:31:52'),(1228,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 12:32:52','2024-12-06 12:32:52'),(1229,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 12:33:52','2024-12-06 12:33:52'),(1230,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 12:34:52','2024-12-06 12:34:52'),(1231,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 12:35:52','2024-12-06 12:35:52'),(1232,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 12:36:52','2024-12-06 12:36:52'),(1233,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 12:37:52','2024-12-06 12:37:52'),(1234,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 12:38:52','2024-12-06 12:38:52'),(1235,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 12:39:52','2024-12-06 12:39:52'),(1236,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 12:40:52','2024-12-06 12:40:52'),(1237,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 12:41:52','2024-12-06 12:41:52'),(1238,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 12:42:52','2024-12-06 12:42:52'),(1239,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 12:43:52','2024-12-06 12:43:52'),(1240,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 12:44:52','2024-12-06 12:44:52'),(1241,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 12:45:52','2024-12-06 12:45:52'),(1242,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 12:46:52','2024-12-06 12:46:52'),(1243,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 12:47:52','2024-12-06 12:47:52'),(1244,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 12:48:52','2024-12-06 12:48:52'),(1245,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 12:49:52','2024-12-06 12:49:52'),(1246,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 12:50:52','2024-12-06 12:50:52'),(1247,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 12:51:52','2024-12-06 12:51:52'),(1248,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 12:52:52','2024-12-06 12:52:52'),(1249,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 12:53:52','2024-12-06 12:53:52'),(1250,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 12:54:52','2024-12-06 12:54:52'),(1251,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 12:55:52','2024-12-06 12:55:52'),(1252,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 12:56:52','2024-12-06 12:56:52'),(1253,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 12:57:52','2024-12-06 12:57:52'),(1254,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 12:58:52','2024-12-06 12:58:52'),(1255,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 12:59:52','2024-12-06 12:59:52'),(1256,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 13:00:52','2024-12-06 13:00:52'),(1257,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 13:01:52','2024-12-06 13:01:52'),(1258,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 13:02:52','2024-12-06 13:02:52'),(1259,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 13:03:52','2024-12-06 13:03:52'),(1260,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 13:04:52','2024-12-06 13:04:52'),(1261,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 13:05:52','2024-12-06 13:05:52'),(1262,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 13:06:52','2024-12-06 13:06:52'),(1263,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 13:07:52','2024-12-06 13:07:52'),(1264,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 13:08:52','2024-12-06 13:08:52'),(1265,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 13:09:52','2024-12-06 13:09:52'),(1266,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 13:10:52','2024-12-06 13:10:52'),(1267,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 13:11:52','2024-12-06 13:11:52'),(1268,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 13:12:52','2024-12-06 13:12:52'),(1269,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 13:13:52','2024-12-06 13:13:52'),(1270,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 13:14:52','2024-12-06 13:14:52'),(1271,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 13:15:52','2024-12-06 13:15:52'),(1272,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 13:16:52','2024-12-06 13:16:52'),(1273,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 13:17:52','2024-12-06 13:17:52'),(1274,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 13:18:52','2024-12-06 13:18:52'),(1275,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 13:19:52','2024-12-06 13:19:52'),(1276,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 13:20:52','2024-12-06 13:20:52'),(1277,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 13:21:52','2024-12-06 13:21:52'),(1278,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 13:22:52','2024-12-06 13:22:52'),(1279,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 13:23:52','2024-12-06 13:23:52'),(1280,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 13:24:52','2024-12-06 13:24:52'),(1281,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 13:25:52','2024-12-06 13:25:52'),(1282,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 13:26:52','2024-12-06 13:26:52'),(1283,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 13:27:52','2024-12-06 13:27:52'),(1284,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 13:28:52','2024-12-06 13:28:52'),(1285,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 13:29:52','2024-12-06 13:29:52'),(1286,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 13:30:52','2024-12-06 13:30:52'),(1287,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 13:31:52','2024-12-06 13:31:52'),(1288,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 13:32:52','2024-12-06 13:32:52'),(1289,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 13:33:52','2024-12-06 13:33:52'),(1290,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 13:34:52','2024-12-06 13:34:52'),(1291,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 13:35:52','2024-12-06 13:35:52'),(1292,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 13:36:52','2024-12-06 13:36:52'),(1293,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 13:37:52','2024-12-06 13:37:52'),(1294,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 13:38:52','2024-12-06 13:38:52'),(1295,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 13:39:52','2024-12-06 13:39:52'),(1296,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 13:40:52','2024-12-06 13:40:52'),(1297,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 13:41:52','2024-12-06 13:41:52'),(1298,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 13:42:52','2024-12-06 13:42:52'),(1299,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 13:43:52','2024-12-06 13:43:52'),(1300,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 13:44:52','2024-12-06 13:44:52'),(1301,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 13:45:52','2024-12-06 13:45:52'),(1302,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 13:46:52','2024-12-06 13:46:52'),(1303,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 13:47:52','2024-12-06 13:47:52'),(1304,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 13:48:52','2024-12-06 13:48:52'),(1305,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 13:49:52','2024-12-06 13:49:52'),(1306,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 13:50:52','2024-12-06 13:50:52'),(1307,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 13:51:52','2024-12-06 13:51:52'),(1308,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 13:52:52','2024-12-06 13:52:52'),(1309,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 13:53:52','2024-12-06 13:53:52'),(1310,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 13:54:52','2024-12-06 13:54:52'),(1311,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 13:55:53','2024-12-06 13:55:53'),(1312,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 13:56:52','2024-12-06 13:56:52'),(1313,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 13:57:52','2024-12-06 13:57:52'),(1314,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 13:58:52','2024-12-06 13:58:52'),(1315,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 13:59:52','2024-12-06 13:59:52'),(1316,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 14:00:52','2024-12-06 14:00:52'),(1317,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 14:01:52','2024-12-06 14:01:52'),(1318,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 14:02:52','2024-12-06 14:02:52'),(1319,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 14:03:52','2024-12-06 14:03:52'),(1320,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 14:04:52','2024-12-06 14:04:52'),(1321,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 14:05:52','2024-12-06 14:05:52'),(1322,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 14:06:52','2024-12-06 14:06:52'),(1323,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 14:07:52','2024-12-06 14:07:52'),(1324,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 14:08:52','2024-12-06 14:08:52'),(1325,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 14:09:52','2024-12-06 14:09:52'),(1326,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 14:10:52','2024-12-06 14:10:52'),(1327,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 14:11:52','2024-12-06 14:11:52'),(1328,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 14:12:52','2024-12-06 14:12:52'),(1329,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 14:13:52','2024-12-06 14:13:52'),(1330,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 14:14:52','2024-12-06 14:14:52'),(1331,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 14:15:52','2024-12-06 14:15:52'),(1332,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 14:16:52','2024-12-06 14:16:52'),(1333,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 14:17:52','2024-12-06 14:17:52'),(1334,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 14:18:52','2024-12-06 14:18:52'),(1335,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 14:19:52','2024-12-06 14:19:52'),(1336,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 14:20:52','2024-12-06 14:20:52'),(1337,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 14:21:52','2024-12-06 14:21:52'),(1338,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 14:22:52','2024-12-06 14:22:52'),(1339,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 14:23:52','2024-12-06 14:23:52'),(1340,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 14:24:52','2024-12-06 14:24:52'),(1341,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 14:25:52','2024-12-06 14:25:52'),(1342,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 14:26:52','2024-12-06 14:26:52'),(1343,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 14:27:52','2024-12-06 14:27:52'),(1344,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 14:28:52','2024-12-06 14:28:52'),(1345,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 14:29:52','2024-12-06 14:29:52'),(1346,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 14:30:52','2024-12-06 14:30:52'),(1347,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 14:31:52','2024-12-06 14:31:52'),(1348,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 14:32:52','2024-12-06 14:32:52'),(1349,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 14:33:52','2024-12-06 14:33:52'),(1350,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 14:34:52','2024-12-06 14:34:52'),(1351,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 14:35:52','2024-12-06 14:35:52'),(1352,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 14:36:52','2024-12-06 14:36:52'),(1353,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 14:37:52','2024-12-06 14:37:52'),(1354,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 14:38:52','2024-12-06 14:38:52'),(1355,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 14:39:52','2024-12-06 14:39:52'),(1356,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 14:40:52','2024-12-06 14:40:52'),(1357,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 14:41:52','2024-12-06 14:41:52'),(1358,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 14:42:52','2024-12-06 14:42:52'),(1359,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 14:43:52','2024-12-06 14:43:52'),(1360,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 14:44:52','2024-12-06 14:44:52'),(1361,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 14:45:52','2024-12-06 14:45:52'),(1362,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 14:46:52','2024-12-06 14:46:52'),(1363,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 14:47:52','2024-12-06 14:47:52'),(1364,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 14:48:52','2024-12-06 14:48:52'),(1365,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 14:49:52','2024-12-06 14:49:52'),(1366,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 14:50:52','2024-12-06 14:50:52'),(1367,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 14:51:52','2024-12-06 14:51:52'),(1368,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 14:52:52','2024-12-06 14:52:52'),(1369,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 14:53:52','2024-12-06 14:53:52'),(1370,'dev','ruoyi_group',1,0,0,0,6,2,0,3,'','',0,'2024-12-06 14:54:52','2024-12-06 14:54:52');
/*!40000 ALTER TABLE `sj_job_task_batch` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sj_namespace`
--

DROP TABLE IF EXISTS `sj_namespace`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sj_namespace` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(64) NOT NULL COMMENT '名称',
  `unique_id` varchar(64) NOT NULL COMMENT '唯一id',
  `description` varchar(256) NOT NULL DEFAULT '' COMMENT '描述',
  `deleted` tinyint NOT NULL DEFAULT '0' COMMENT '逻辑删除 1、删除',
  `create_dt` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_dt` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_unique_id` (`unique_id`),
  KEY `idx_name` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='命名空间';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sj_namespace`
--

LOCK TABLES `sj_namespace` WRITE;
/*!40000 ALTER TABLE `sj_namespace` DISABLE KEYS */;
INSERT INTO `sj_namespace` VALUES (1,'Development','dev','',0,'2024-12-05 15:51:52','2024-12-05 15:51:52'),(2,'Production','prod','',0,'2024-12-05 15:51:52','2024-12-05 15:51:52');
/*!40000 ALTER TABLE `sj_namespace` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sj_notify_config`
--

DROP TABLE IF EXISTS `sj_notify_config`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sj_notify_config` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `namespace_id` varchar(64) NOT NULL DEFAULT '764d604ec6fc45f68cd92514c40e9e1a' COMMENT '命名空间id',
  `group_name` varchar(64) NOT NULL COMMENT '组名称',
  `business_id` varchar(64) NOT NULL COMMENT '业务id (job_id或workflow_id或scene_name)',
  `system_task_type` tinyint NOT NULL DEFAULT '3' COMMENT '任务类型 1. 重试任务 2. 重试回调 3、JOB任务 4、WORKFLOW任务',
  `notify_status` tinyint NOT NULL DEFAULT '0' COMMENT '通知状态 0、未启用 1、启用',
  `recipient_ids` varchar(128) NOT NULL COMMENT '接收人id列表',
  `notify_threshold` int NOT NULL DEFAULT '0' COMMENT '通知阈值',
  `notify_scene` tinyint NOT NULL DEFAULT '0' COMMENT '通知场景',
  `rate_limiter_status` tinyint NOT NULL DEFAULT '0' COMMENT '限流状态 0、未启用 1、启用',
  `rate_limiter_threshold` int NOT NULL DEFAULT '0' COMMENT '每秒限流阈值',
  `description` varchar(256) NOT NULL DEFAULT '' COMMENT '描述',
  `create_dt` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_dt` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`),
  KEY `idx_namespace_id_group_name_scene_name` (`namespace_id`,`group_name`,`business_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='通知配置';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sj_notify_config`
--

LOCK TABLES `sj_notify_config` WRITE;
/*!40000 ALTER TABLE `sj_notify_config` DISABLE KEYS */;
/*!40000 ALTER TABLE `sj_notify_config` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sj_notify_recipient`
--

DROP TABLE IF EXISTS `sj_notify_recipient`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sj_notify_recipient` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `namespace_id` varchar(64) NOT NULL DEFAULT '764d604ec6fc45f68cd92514c40e9e1a' COMMENT '命名空间id',
  `recipient_name` varchar(64) NOT NULL COMMENT '接收人名称',
  `notify_type` tinyint NOT NULL DEFAULT '0' COMMENT '通知类型 1、钉钉 2、邮件 3、企业微信 4 飞书 5 webhook',
  `notify_attribute` varchar(512) NOT NULL COMMENT '配置属性',
  `description` varchar(256) NOT NULL DEFAULT '' COMMENT '描述',
  `create_dt` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_dt` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`),
  KEY `idx_namespace_id` (`namespace_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='告警通知接收人';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sj_notify_recipient`
--

LOCK TABLES `sj_notify_recipient` WRITE;
/*!40000 ALTER TABLE `sj_notify_recipient` DISABLE KEYS */;
/*!40000 ALTER TABLE `sj_notify_recipient` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sj_retry_dead_letter_0`
--

DROP TABLE IF EXISTS `sj_retry_dead_letter_0`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sj_retry_dead_letter_0` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `namespace_id` varchar(64) NOT NULL DEFAULT '764d604ec6fc45f68cd92514c40e9e1a' COMMENT '命名空间id',
  `unique_id` varchar(64) NOT NULL COMMENT '同组下id唯一',
  `group_name` varchar(64) NOT NULL COMMENT '组名称',
  `scene_name` varchar(64) NOT NULL COMMENT '场景名称',
  `idempotent_id` varchar(64) NOT NULL COMMENT '幂等id',
  `biz_no` varchar(64) NOT NULL DEFAULT '' COMMENT '业务编号',
  `executor_name` varchar(512) NOT NULL DEFAULT '' COMMENT '执行器名称',
  `args_str` text NOT NULL COMMENT '执行方法参数',
  `ext_attrs` text NOT NULL COMMENT '扩展字段',
  `task_type` tinyint NOT NULL DEFAULT '1' COMMENT '任务类型 1、重试数据 2、回调数据',
  `create_dt` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_namespace_id_group_name_unique_id` (`namespace_id`,`group_name`,`unique_id`),
  KEY `idx_namespace_id_group_name_scene_name` (`namespace_id`,`group_name`,`scene_name`),
  KEY `idx_idempotent_id` (`idempotent_id`),
  KEY `idx_biz_no` (`biz_no`),
  KEY `idx_create_dt` (`create_dt`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='死信队列表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sj_retry_dead_letter_0`
--

LOCK TABLES `sj_retry_dead_letter_0` WRITE;
/*!40000 ALTER TABLE `sj_retry_dead_letter_0` DISABLE KEYS */;
/*!40000 ALTER TABLE `sj_retry_dead_letter_0` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sj_retry_scene_config`
--

DROP TABLE IF EXISTS `sj_retry_scene_config`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sj_retry_scene_config` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `namespace_id` varchar(64) NOT NULL DEFAULT '764d604ec6fc45f68cd92514c40e9e1a' COMMENT '命名空间id',
  `scene_name` varchar(64) NOT NULL COMMENT '场景名称',
  `group_name` varchar(64) NOT NULL COMMENT '组名称',
  `scene_status` tinyint NOT NULL DEFAULT '0' COMMENT '组状态 0、未启用 1、启用',
  `max_retry_count` int NOT NULL DEFAULT '5' COMMENT '最大重试次数',
  `back_off` tinyint NOT NULL DEFAULT '1' COMMENT '1、默认等级 2、固定间隔时间 3、CRON 表达式',
  `trigger_interval` varchar(16) NOT NULL DEFAULT '' COMMENT '间隔时长',
  `deadline_request` bigint unsigned NOT NULL DEFAULT '60000' COMMENT 'Deadline Request 调用链超时 单位毫秒',
  `executor_timeout` int unsigned NOT NULL DEFAULT '5' COMMENT '任务执行超时时间，单位秒',
  `route_key` tinyint NOT NULL DEFAULT '4' COMMENT '路由策略',
  `description` varchar(256) NOT NULL DEFAULT '' COMMENT '描述',
  `create_dt` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_dt` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_namespace_id_group_name_scene_name` (`namespace_id`,`group_name`,`scene_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='场景配置';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sj_retry_scene_config`
--

LOCK TABLES `sj_retry_scene_config` WRITE;
/*!40000 ALTER TABLE `sj_retry_scene_config` DISABLE KEYS */;
/*!40000 ALTER TABLE `sj_retry_scene_config` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sj_retry_summary`
--

DROP TABLE IF EXISTS `sj_retry_summary`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sj_retry_summary` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `namespace_id` varchar(64) NOT NULL DEFAULT '764d604ec6fc45f68cd92514c40e9e1a' COMMENT '命名空间id',
  `group_name` varchar(64) NOT NULL DEFAULT '' COMMENT '组名称',
  `scene_name` varchar(50) NOT NULL DEFAULT '' COMMENT '场景名称',
  `trigger_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '统计时间',
  `running_num` int NOT NULL DEFAULT '0' COMMENT '重试中-日志数量',
  `finish_num` int NOT NULL DEFAULT '0' COMMENT '重试完成-日志数量',
  `max_count_num` int NOT NULL DEFAULT '0' COMMENT '重试到达最大次数-日志数量',
  `suspend_num` int NOT NULL DEFAULT '0' COMMENT '暂停重试-日志数量',
  `create_dt` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_dt` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_scene_name_trigger_at` (`namespace_id`,`group_name`,`scene_name`,`trigger_at`) USING BTREE,
  KEY `idx_trigger_at` (`trigger_at`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='DashBoard_Retry';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sj_retry_summary`
--

LOCK TABLES `sj_retry_summary` WRITE;
/*!40000 ALTER TABLE `sj_retry_summary` DISABLE KEYS */;
/*!40000 ALTER TABLE `sj_retry_summary` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sj_retry_task_0`
--

DROP TABLE IF EXISTS `sj_retry_task_0`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sj_retry_task_0` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `namespace_id` varchar(64) NOT NULL DEFAULT '764d604ec6fc45f68cd92514c40e9e1a' COMMENT '命名空间id',
  `unique_id` varchar(64) NOT NULL COMMENT '同组下id唯一',
  `group_name` varchar(64) NOT NULL COMMENT '组名称',
  `scene_name` varchar(64) NOT NULL COMMENT '场景名称',
  `idempotent_id` varchar(64) NOT NULL COMMENT '幂等id',
  `biz_no` varchar(64) NOT NULL DEFAULT '' COMMENT '业务编号',
  `executor_name` varchar(512) NOT NULL DEFAULT '' COMMENT '执行器名称',
  `args_str` text NOT NULL COMMENT '执行方法参数',
  `ext_attrs` text NOT NULL COMMENT '扩展字段',
  `next_trigger_at` datetime NOT NULL COMMENT '下次触发时间',
  `retry_count` int NOT NULL DEFAULT '0' COMMENT '重试次数',
  `retry_status` tinyint NOT NULL DEFAULT '0' COMMENT '重试状态 0、重试中 1、成功 2、最大重试次数',
  `task_type` tinyint NOT NULL DEFAULT '1' COMMENT '任务类型 1、重试数据 2、回调数据',
  `create_dt` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_dt` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_name_unique_id` (`namespace_id`,`group_name`,`unique_id`),
  KEY `idx_namespace_id_group_name_scene_name` (`namespace_id`,`group_name`,`scene_name`),
  KEY `idx_namespace_id_group_name_task_type` (`namespace_id`,`group_name`,`task_type`),
  KEY `idx_namespace_id_group_name_retry_status` (`namespace_id`,`group_name`,`retry_status`),
  KEY `idx_idempotent_id` (`idempotent_id`),
  KEY `idx_biz_no` (`biz_no`),
  KEY `idx_create_dt` (`create_dt`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='任务表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sj_retry_task_0`
--

LOCK TABLES `sj_retry_task_0` WRITE;
/*!40000 ALTER TABLE `sj_retry_task_0` DISABLE KEYS */;
/*!40000 ALTER TABLE `sj_retry_task_0` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sj_retry_task_log`
--

DROP TABLE IF EXISTS `sj_retry_task_log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sj_retry_task_log` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `namespace_id` varchar(64) NOT NULL DEFAULT '764d604ec6fc45f68cd92514c40e9e1a' COMMENT '命名空间id',
  `unique_id` varchar(64) NOT NULL COMMENT '同组下id唯一',
  `group_name` varchar(64) NOT NULL COMMENT '组名称',
  `scene_name` varchar(64) NOT NULL COMMENT '场景名称',
  `idempotent_id` varchar(64) NOT NULL COMMENT '幂等id',
  `biz_no` varchar(64) NOT NULL DEFAULT '' COMMENT '业务编号',
  `executor_name` varchar(512) NOT NULL DEFAULT '' COMMENT '执行器名称',
  `args_str` text NOT NULL COMMENT '执行方法参数',
  `ext_attrs` text NOT NULL COMMENT '扩展字段',
  `retry_status` tinyint NOT NULL DEFAULT '0' COMMENT '重试状态 0、重试中 1、成功 2、最大次数',
  `task_type` tinyint NOT NULL DEFAULT '1' COMMENT '任务类型 1、重试数据 2、回调数据',
  `create_dt` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_dt` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`),
  KEY `idx_group_name_scene_name` (`namespace_id`,`group_name`,`scene_name`),
  KEY `idx_retry_status` (`retry_status`),
  KEY `idx_idempotent_id` (`idempotent_id`),
  KEY `idx_unique_id` (`unique_id`),
  KEY `idx_biz_no` (`biz_no`),
  KEY `idx_create_dt` (`create_dt`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='任务日志基础信息表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sj_retry_task_log`
--

LOCK TABLES `sj_retry_task_log` WRITE;
/*!40000 ALTER TABLE `sj_retry_task_log` DISABLE KEYS */;
/*!40000 ALTER TABLE `sj_retry_task_log` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sj_retry_task_log_message`
--

DROP TABLE IF EXISTS `sj_retry_task_log_message`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sj_retry_task_log_message` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `namespace_id` varchar(64) NOT NULL DEFAULT '764d604ec6fc45f68cd92514c40e9e1a' COMMENT '命名空间id',
  `group_name` varchar(64) NOT NULL COMMENT '组名称',
  `unique_id` varchar(64) NOT NULL COMMENT '同组下id唯一',
  `message` longtext NOT NULL COMMENT '异常信息',
  `log_num` int NOT NULL DEFAULT '1' COMMENT '日志数量',
  `real_time` bigint NOT NULL DEFAULT '0' COMMENT '上报时间',
  `create_dt` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`),
  KEY `idx_namespace_id_group_name_scene_name` (`namespace_id`,`group_name`,`unique_id`),
  KEY `idx_create_dt` (`create_dt`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='任务调度日志信息记录表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sj_retry_task_log_message`
--

LOCK TABLES `sj_retry_task_log_message` WRITE;
/*!40000 ALTER TABLE `sj_retry_task_log_message` DISABLE KEYS */;
/*!40000 ALTER TABLE `sj_retry_task_log_message` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sj_sequence_alloc`
--

DROP TABLE IF EXISTS `sj_sequence_alloc`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sj_sequence_alloc` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `namespace_id` varchar(64) NOT NULL DEFAULT '764d604ec6fc45f68cd92514c40e9e1a' COMMENT '命名空间id',
  `group_name` varchar(64) NOT NULL DEFAULT '' COMMENT '组名称',
  `max_id` bigint NOT NULL DEFAULT '1' COMMENT '最大id',
  `step` int NOT NULL DEFAULT '100' COMMENT '步长',
  `update_dt` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_namespace_id_group_name` (`namespace_id`,`group_name`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='号段模式序号ID分配表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sj_sequence_alloc`
--

LOCK TABLES `sj_sequence_alloc` WRITE;
/*!40000 ALTER TABLE `sj_sequence_alloc` DISABLE KEYS */;
INSERT INTO `sj_sequence_alloc` VALUES (1,'dev','base_platform_group',1,100,'2024-12-05 16:07:38');
/*!40000 ALTER TABLE `sj_sequence_alloc` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sj_server_node`
--

DROP TABLE IF EXISTS `sj_server_node`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sj_server_node` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `namespace_id` varchar(64) NOT NULL DEFAULT '764d604ec6fc45f68cd92514c40e9e1a' COMMENT '命名空间id',
  `group_name` varchar(64) NOT NULL COMMENT '组名称',
  `host_id` varchar(64) NOT NULL COMMENT '主机id',
  `host_ip` varchar(64) NOT NULL COMMENT '机器ip',
  `host_port` int NOT NULL COMMENT '机器端口',
  `expire_at` datetime NOT NULL COMMENT '过期时间',
  `node_type` tinyint NOT NULL COMMENT '节点类型 1、客户端 2、是服务端',
  `ext_attrs` varchar(256) DEFAULT '' COMMENT '扩展字段',
  `create_dt` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_dt` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_host_id_host_ip` (`host_id`,`host_ip`),
  KEY `idx_namespace_id_group_name` (`namespace_id`,`group_name`),
  KEY `idx_expire_at_node_type` (`expire_at`,`node_type`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='服务器节点';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sj_server_node`
--

LOCK TABLES `sj_server_node` WRITE;
/*!40000 ALTER TABLE `sj_server_node` DISABLE KEYS */;
INSERT INTO `sj_server_node` VALUES (5,'DEFAULT_SERVER_NAMESPACE_ID','DEFAULT_SERVER','1864605947645636608','10.26.0.3',17888,'2024-12-06 14:55:32',2,'{\"webPort\":8800}','2024-12-05 17:41:22','2024-12-06 14:55:02'),(6,'prod','ruoyi_group','1864605961767870464','10.26.0.3',29203,'2024-12-06 14:55:29',1,NULL,'2024-12-05 17:41:29','2024-12-06 14:54:58');
/*!40000 ALTER TABLE `sj_server_node` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sj_system_user`
--

DROP TABLE IF EXISTS `sj_system_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sj_system_user` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `username` varchar(64) NOT NULL COMMENT '账号',
  `password` varchar(128) NOT NULL COMMENT '密码',
  `role` tinyint NOT NULL DEFAULT '0' COMMENT '角色：1-普通用户、2-管理员',
  `create_dt` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_dt` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_username` (`username`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='系统用户表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sj_system_user`
--

LOCK TABLES `sj_system_user` WRITE;
/*!40000 ALTER TABLE `sj_system_user` DISABLE KEYS */;
INSERT INTO `sj_system_user` VALUES (1,'admin','465c194afb65670f38322df087f0a9bb225cc257e43eb4ac5a0c98ef5b3173ac',2,'2024-12-05 15:52:01','2024-12-05 15:52:01');
/*!40000 ALTER TABLE `sj_system_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sj_system_user_permission`
--

DROP TABLE IF EXISTS `sj_system_user_permission`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sj_system_user_permission` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `group_name` varchar(64) NOT NULL COMMENT '组名称',
  `namespace_id` varchar(64) NOT NULL DEFAULT '764d604ec6fc45f68cd92514c40e9e1a' COMMENT '命名空间id',
  `system_user_id` bigint NOT NULL COMMENT '系统用户id',
  `create_dt` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_dt` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_namespace_id_group_name_system_user_id` (`namespace_id`,`group_name`,`system_user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='系统用户权限表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sj_system_user_permission`
--

LOCK TABLES `sj_system_user_permission` WRITE;
/*!40000 ALTER TABLE `sj_system_user_permission` DISABLE KEYS */;
/*!40000 ALTER TABLE `sj_system_user_permission` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sj_workflow`
--

DROP TABLE IF EXISTS `sj_workflow`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sj_workflow` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `workflow_name` varchar(64) NOT NULL COMMENT '工作流名称',
  `namespace_id` varchar(64) NOT NULL DEFAULT '764d604ec6fc45f68cd92514c40e9e1a' COMMENT '命名空间id',
  `group_name` varchar(64) NOT NULL COMMENT '组名称',
  `workflow_status` tinyint NOT NULL DEFAULT '1' COMMENT '工作流状态 0、关闭、1、开启',
  `trigger_type` tinyint NOT NULL COMMENT '触发类型 1.CRON 表达式 2. 固定时间',
  `trigger_interval` varchar(255) NOT NULL COMMENT '间隔时长',
  `next_trigger_at` bigint NOT NULL COMMENT '下次触发时间',
  `block_strategy` tinyint NOT NULL DEFAULT '1' COMMENT '阻塞策略 1、丢弃 2、覆盖 3、并行',
  `executor_timeout` int NOT NULL DEFAULT '0' COMMENT '任务执行超时时间，单位秒',
  `description` varchar(256) NOT NULL DEFAULT '' COMMENT '描述',
  `flow_info` text COMMENT '流程信息',
  `wf_context` text COMMENT '上下文',
  `bucket_index` int NOT NULL DEFAULT '0' COMMENT 'bucket',
  `version` int NOT NULL COMMENT '版本号',
  `ext_attrs` varchar(256) DEFAULT '' COMMENT '扩展字段',
  `deleted` tinyint NOT NULL DEFAULT '0' COMMENT '逻辑删除 1、删除',
  `create_dt` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_dt` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`),
  KEY `idx_create_dt` (`create_dt`),
  KEY `idx_namespace_id_group_name` (`namespace_id`,`group_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='工作流';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sj_workflow`
--

LOCK TABLES `sj_workflow` WRITE;
/*!40000 ALTER TABLE `sj_workflow` DISABLE KEYS */;
/*!40000 ALTER TABLE `sj_workflow` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sj_workflow_node`
--

DROP TABLE IF EXISTS `sj_workflow_node`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sj_workflow_node` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `namespace_id` varchar(64) NOT NULL DEFAULT '764d604ec6fc45f68cd92514c40e9e1a' COMMENT '命名空间id',
  `node_name` varchar(64) NOT NULL COMMENT '节点名称',
  `group_name` varchar(64) NOT NULL COMMENT '组名称',
  `job_id` bigint NOT NULL COMMENT '任务信息id',
  `workflow_id` bigint NOT NULL COMMENT '工作流ID',
  `node_type` tinyint NOT NULL DEFAULT '1' COMMENT '1、任务节点 2、条件节点',
  `expression_type` tinyint NOT NULL DEFAULT '0' COMMENT '1、SpEl、2、Aviator 3、QL',
  `fail_strategy` tinyint NOT NULL DEFAULT '1' COMMENT '失败策略 1、跳过 2、阻塞',
  `workflow_node_status` tinyint NOT NULL DEFAULT '1' COMMENT '工作流节点状态 0、关闭、1、开启',
  `priority_level` int NOT NULL DEFAULT '1' COMMENT '优先级',
  `node_info` text COMMENT '节点信息 ',
  `version` int NOT NULL COMMENT '版本号',
  `ext_attrs` varchar(256) DEFAULT '' COMMENT '扩展字段',
  `deleted` tinyint NOT NULL DEFAULT '0' COMMENT '逻辑删除 1、删除',
  `create_dt` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_dt` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`),
  KEY `idx_create_dt` (`create_dt`),
  KEY `idx_namespace_id_group_name` (`namespace_id`,`group_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='工作流节点';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sj_workflow_node`
--

LOCK TABLES `sj_workflow_node` WRITE;
/*!40000 ALTER TABLE `sj_workflow_node` DISABLE KEYS */;
/*!40000 ALTER TABLE `sj_workflow_node` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sj_workflow_task_batch`
--

DROP TABLE IF EXISTS `sj_workflow_task_batch`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sj_workflow_task_batch` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `namespace_id` varchar(64) NOT NULL DEFAULT '764d604ec6fc45f68cd92514c40e9e1a' COMMENT '命名空间id',
  `group_name` varchar(64) NOT NULL COMMENT '组名称',
  `workflow_id` bigint NOT NULL COMMENT '工作流任务id',
  `task_batch_status` tinyint NOT NULL DEFAULT '0' COMMENT '任务批次状态 0、失败 1、成功',
  `operation_reason` tinyint NOT NULL DEFAULT '0' COMMENT '操作原因',
  `flow_info` text COMMENT '流程信息',
  `wf_context` text COMMENT '全局上下文',
  `execution_at` bigint NOT NULL DEFAULT '0' COMMENT '任务执行时间',
  `ext_attrs` varchar(256) DEFAULT '' COMMENT '扩展字段',
  `version` int NOT NULL DEFAULT '1' COMMENT '版本号',
  `deleted` tinyint NOT NULL DEFAULT '0' COMMENT '逻辑删除 1、删除',
  `create_dt` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_dt` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`),
  KEY `idx_job_id_task_batch_status` (`workflow_id`,`task_batch_status`),
  KEY `idx_create_dt` (`create_dt`),
  KEY `idx_namespace_id_group_name` (`namespace_id`,`group_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='工作流批次';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sj_workflow_task_batch`
--

LOCK TABLES `sj_workflow_task_batch` WRITE;
/*!40000 ALTER TABLE `sj_workflow_task_batch` DISABLE KEYS */;
/*!40000 ALTER TABLE `sj_workflow_task_batch` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Current Database: `ry-seata`
--

CREATE DATABASE /*!32312 IF NOT EXISTS*/ `ry-seata` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;

USE `ry-seata`;

--
-- Table structure for table `branch_table`
--

DROP TABLE IF EXISTS `branch_table`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `branch_table` (
  `branch_id` bigint NOT NULL,
  `xid` varchar(128) NOT NULL,
  `transaction_id` bigint DEFAULT NULL,
  `resource_group_id` varchar(32) DEFAULT NULL,
  `resource_id` varchar(256) DEFAULT NULL,
  `branch_type` varchar(8) DEFAULT NULL,
  `status` tinyint DEFAULT NULL,
  `client_id` varchar(64) DEFAULT NULL,
  `application_data` varchar(2000) DEFAULT NULL,
  `gmt_create` datetime(6) DEFAULT NULL,
  `gmt_modified` datetime(6) DEFAULT NULL,
  PRIMARY KEY (`branch_id`),
  KEY `idx_xid` (`xid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `branch_table`
--

LOCK TABLES `branch_table` WRITE;
/*!40000 ALTER TABLE `branch_table` DISABLE KEYS */;
/*!40000 ALTER TABLE `branch_table` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `distributed_lock`
--

DROP TABLE IF EXISTS `distributed_lock`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `distributed_lock` (
  `lock_key` char(20) NOT NULL,
  `lock_value` varchar(20) NOT NULL,
  `expire` bigint DEFAULT NULL,
  PRIMARY KEY (`lock_key`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `distributed_lock`
--

LOCK TABLES `distributed_lock` WRITE;
/*!40000 ALTER TABLE `distributed_lock` DISABLE KEYS */;
INSERT INTO `distributed_lock` VALUES ('AsyncCommitting',' ',0),('RetryCommitting',' ',0),('RetryRollbacking',' ',0),('TxTimeoutCheck',' ',0),('UndologDelete',' ',0);
/*!40000 ALTER TABLE `distributed_lock` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `global_table`
--

DROP TABLE IF EXISTS `global_table`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `global_table` (
  `xid` varchar(128) NOT NULL,
  `transaction_id` bigint DEFAULT NULL,
  `status` tinyint NOT NULL,
  `application_id` varchar(32) DEFAULT NULL,
  `transaction_service_group` varchar(32) DEFAULT NULL,
  `transaction_name` varchar(128) DEFAULT NULL,
  `timeout` int DEFAULT NULL,
  `begin_time` bigint DEFAULT NULL,
  `application_data` varchar(2000) DEFAULT NULL,
  `gmt_create` datetime DEFAULT NULL,
  `gmt_modified` datetime DEFAULT NULL,
  PRIMARY KEY (`xid`),
  KEY `idx_status_gmt_modified` (`status`,`gmt_modified`),
  KEY `idx_transaction_id` (`transaction_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `global_table`
--

LOCK TABLES `global_table` WRITE;
/*!40000 ALTER TABLE `global_table` DISABLE KEYS */;
/*!40000 ALTER TABLE `global_table` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `lock_table`
--

DROP TABLE IF EXISTS `lock_table`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `lock_table` (
  `row_key` varchar(128) NOT NULL,
  `xid` varchar(128) DEFAULT NULL,
  `transaction_id` bigint DEFAULT NULL,
  `branch_id` bigint NOT NULL,
  `resource_id` varchar(256) DEFAULT NULL,
  `table_name` varchar(32) DEFAULT NULL,
  `pk` varchar(36) DEFAULT NULL,
  `status` tinyint NOT NULL DEFAULT '0' COMMENT '0:locked ,1:rollbacking',
  `gmt_create` datetime DEFAULT NULL,
  `gmt_modified` datetime DEFAULT NULL,
  PRIMARY KEY (`row_key`),
  KEY `idx_status` (`status`),
  KEY `idx_branch_id` (`branch_id`),
  KEY `idx_xid_and_branch_id` (`xid`,`branch_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lock_table`
--

LOCK TABLES `lock_table` WRITE;
/*!40000 ALTER TABLE `lock_table` DISABLE KEYS */;
/*!40000 ALTER TABLE `lock_table` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Current Database: `ry-workflow`
--

CREATE DATABASE /*!32312 IF NOT EXISTS*/ `ry-workflow` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;

USE `ry-workflow`;

--
-- Table structure for table `act_evt_log`
--

DROP TABLE IF EXISTS `act_evt_log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `act_evt_log` (
  `LOG_NR_` bigint NOT NULL AUTO_INCREMENT,
  `TYPE_` varchar(64) COLLATE utf8mb3_bin DEFAULT NULL,
  `PROC_DEF_ID_` varchar(64) COLLATE utf8mb3_bin DEFAULT NULL,
  `PROC_INST_ID_` varchar(64) COLLATE utf8mb3_bin DEFAULT NULL,
  `EXECUTION_ID_` varchar(64) COLLATE utf8mb3_bin DEFAULT NULL,
  `TASK_ID_` varchar(64) COLLATE utf8mb3_bin DEFAULT NULL,
  `TIME_STAMP_` timestamp(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3),
  `USER_ID_` varchar(255) COLLATE utf8mb3_bin DEFAULT NULL,
  `DATA_` longblob,
  `LOCK_OWNER_` varchar(255) COLLATE utf8mb3_bin DEFAULT NULL,
  `LOCK_TIME_` timestamp(3) NULL DEFAULT NULL,
  `IS_PROCESSED_` tinyint DEFAULT '0',
  PRIMARY KEY (`LOG_NR_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `act_evt_log`
--

LOCK TABLES `act_evt_log` WRITE;
/*!40000 ALTER TABLE `act_evt_log` DISABLE KEYS */;
/*!40000 ALTER TABLE `act_evt_log` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `act_ge_bytearray`
--

DROP TABLE IF EXISTS `act_ge_bytearray`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `act_ge_bytearray` (
  `ID_` varchar(64) COLLATE utf8mb3_bin NOT NULL,
  `REV_` int DEFAULT NULL,
  `NAME_` varchar(255) COLLATE utf8mb3_bin DEFAULT NULL,
  `DEPLOYMENT_ID_` varchar(64) COLLATE utf8mb3_bin DEFAULT NULL,
  `BYTES_` longblob,
  `GENERATED_` tinyint DEFAULT NULL,
  PRIMARY KEY (`ID_`),
  KEY `ACT_FK_BYTEARR_DEPL` (`DEPLOYMENT_ID_`),
  CONSTRAINT `ACT_FK_BYTEARR_DEPL` FOREIGN KEY (`DEPLOYMENT_ID_`) REFERENCES `act_re_deployment` (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `act_ge_bytearray`
--

LOCK TABLES `act_ge_bytearray` WRITE;
/*!40000 ALTER TABLE `act_ge_bytearray` DISABLE KEYS */;
INSERT INTO `act_ge_bytearray` VALUES ('1864607711493074947',1,'请假流程（包容网关）-leave4.bpmn20.xml','1864607711493074946',_binary '<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<definitions xmlns=\"http://www.omg.org/spec/BPMN/20100524/MODEL\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:bpmndi=\"http://www.omg.org/spec/BPMN/20100524/DI\" xmlns:omgdc=\"http://www.omg.org/spec/DD/20100524/DC\" xmlns:bioc=\"http://bpmn.io/schema/bpmn/biocolor/1.0\" xmlns:flowable=\"http://flowable.org/bpmn\" xmlns:di=\"http://www.omg.org/spec/DD/20100524/DI\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" targetNamespace=\"http://www.flowable.org/processdef\">\n  <process id=\"leave4\" name=\"请假流程（包容网关）\">\n    <startEvent id=\"startNode1\" name=\"开始\">\n      <outgoing>Flow_14qet78</outgoing>\n    </startEvent>\n    <userTask id=\"Activity_0uscrk3\" name=\"申请人\" flowable:formKey=\"static:1\">\n      <incoming>Flow_14qet78</incoming>\n      <outgoing>Flow_171lpw3</outgoing>\n    </userTask>\n    <sequenceFlow id=\"Flow_14qet78\" sourceRef=\"startNode1\" targetRef=\"Activity_0uscrk3\" />\n    <userTask id=\"Activity_0ped7fd\" name=\"科研部门\" flowable:candidateUsers=\"1,3\">\n      <extensionElements />\n      <incoming>Flow_16qxdzv</incoming>\n      <outgoing>Flow_01rdmuq</outgoing>\n    </userTask>\n    <userTask id=\"Activity_1e8dxc6\" name=\"总经理\" flowable:candidateUsers=\"1\">\n      <extensionElements />\n      <incoming>Flow_0rt1gbx</incoming>\n      <outgoing>Flow_01maojf</outgoing>\n    </userTask>\n    <userTask id=\"Activity_0xun74h\" name=\"综合部门\" flowable:assignee=\"1\">\n      <extensionElements />\n      <incoming>Flow_15anuo0</incoming>\n      <outgoing>Flow_1j0t4se</outgoing>\n    </userTask>\n    <sequenceFlow id=\"Flow_171lpw3\" sourceRef=\"Activity_0uscrk3\" targetRef=\"Gateway_0qj0eur\" />\n    <sequenceFlow id=\"Flow_16qxdzv\" sourceRef=\"Gateway_0qj0eur\" targetRef=\"Activity_0ped7fd\" />\n    <sequenceFlow id=\"Flow_15anuo0\" sourceRef=\"Gateway_0qj0eur\" targetRef=\"Activity_0xun74h\">\n      <conditionExpression xsi:type=\"tFormalExpression\">${entity.leaveDays &gt; 2}</conditionExpression>\n    </sequenceFlow>\n    <endEvent id=\"Event_0k2b5e5\">\n      <incoming>Flow_01maojf</incoming>\n    </endEvent>\n    <sequenceFlow id=\"Flow_01maojf\" sourceRef=\"Activity_1e8dxc6\" targetRef=\"Event_0k2b5e5\" />\n    <sequenceFlow id=\"Flow_01rdmuq\" sourceRef=\"Activity_0ped7fd\" targetRef=\"Gateway_05y03rn\" />\n    <sequenceFlow id=\"Flow_1j0t4se\" sourceRef=\"Activity_0xun74h\" targetRef=\"Gateway_05y03rn\" />\n    <sequenceFlow id=\"Flow_0rt1gbx\" sourceRef=\"Gateway_05y03rn\" targetRef=\"Activity_1e8dxc6\" />\n    <inclusiveGateway id=\"Gateway_0qj0eur\">\n      <incoming>Flow_171lpw3</incoming>\n      <outgoing>Flow_16qxdzv</outgoing>\n      <outgoing>Flow_15anuo0</outgoing>\n    </inclusiveGateway>\n    <inclusiveGateway id=\"Gateway_05y03rn\">\n      <incoming>Flow_01rdmuq</incoming>\n      <incoming>Flow_1j0t4se</incoming>\n      <outgoing>Flow_0rt1gbx</outgoing>\n    </inclusiveGateway>\n  </process>\n  <bpmndi:BPMNDiagram id=\"BPMNDiagram_flow\">\n    <bpmndi:BPMNPlane id=\"BPMNPlane_flow\" bpmnElement=\"leave4\">\n      <bpmndi:BPMNShape id=\"BPMNShape_startNode1\" bpmnElement=\"startNode1\" bioc:stroke=\"\">\n        <omgdc:Bounds x=\"235\" y=\"205\" width=\"30\" height=\"30\" />\n        <bpmndi:BPMNLabel>\n          <omgdc:Bounds x=\"238\" y=\"242\" width=\"22\" height=\"14\" />\n        </bpmndi:BPMNLabel>\n      </bpmndi:BPMNShape>\n      <bpmndi:BPMNShape id=\"Activity_0uscrk3_di\" bpmnElement=\"Activity_0uscrk3\">\n        <omgdc:Bounds x=\"320\" y=\"180\" width=\"100\" height=\"80\" />\n        <bpmndi:BPMNLabel />\n      </bpmndi:BPMNShape>\n      <bpmndi:BPMNShape id=\"Activity_0ped7fd_di\" bpmnElement=\"Activity_0ped7fd\">\n        <omgdc:Bounds x=\"590\" y=\"120\" width=\"100\" height=\"80\" />\n        <bpmndi:BPMNLabel />\n      </bpmndi:BPMNShape>\n      <bpmndi:BPMNShape id=\"Activity_1e8dxc6_di\" bpmnElement=\"Activity_1e8dxc6\">\n        <omgdc:Bounds x=\"850\" y=\"180\" width=\"100\" height=\"80\" />\n        <bpmndi:BPMNLabel />\n      </bpmndi:BPMNShape>\n      <bpmndi:BPMNShape id=\"Activity_0xun74h_di\" bpmnElement=\"Activity_0xun74h\">\n        <omgdc:Bounds x=\"590\" y=\"240\" width=\"100\" height=\"80\" />\n        <bpmndi:BPMNLabel />\n      </bpmndi:BPMNShape>\n      <bpmndi:BPMNShape id=\"Event_0k2b5e5_di\" bpmnElement=\"Event_0k2b5e5\">\n        <omgdc:Bounds x=\"1022\" y=\"202\" width=\"36\" height=\"36\" />\n      </bpmndi:BPMNShape>\n      <bpmndi:BPMNShape id=\"Gateway_041zo9p_di\" bpmnElement=\"Gateway_0qj0eur\">\n        <omgdc:Bounds x=\"475\" y=\"195\" width=\"50\" height=\"50\" />\n      </bpmndi:BPMNShape>\n      <bpmndi:BPMNShape id=\"Gateway_1cyx391_di\" bpmnElement=\"Gateway_05y03rn\">\n        <omgdc:Bounds x=\"745\" y=\"195\" width=\"50\" height=\"50\" />\n      </bpmndi:BPMNShape>\n      <bpmndi:BPMNEdge id=\"Flow_14qet78_di\" bpmnElement=\"Flow_14qet78\">\n        <di:waypoint x=\"265\" y=\"220\" />\n        <di:waypoint x=\"320\" y=\"220\" />\n      </bpmndi:BPMNEdge>\n      <bpmndi:BPMNEdge id=\"Flow_171lpw3_di\" bpmnElement=\"Flow_171lpw3\">\n        <di:waypoint x=\"420\" y=\"220\" />\n        <di:waypoint x=\"475\" y=\"220\" />\n      </bpmndi:BPMNEdge>\n      <bpmndi:BPMNEdge id=\"Flow_16qxdzv_di\" bpmnElement=\"Flow_16qxdzv\">\n        <di:waypoint x=\"500\" y=\"195\" />\n        <di:waypoint x=\"500\" y=\"160\" />\n        <di:waypoint x=\"590\" y=\"160\" />\n      </bpmndi:BPMNEdge>\n      <bpmndi:BPMNEdge id=\"Flow_15anuo0_di\" bpmnElement=\"Flow_15anuo0\">\n        <di:waypoint x=\"500\" y=\"245\" />\n        <di:waypoint x=\"500\" y=\"280\" />\n        <di:waypoint x=\"590\" y=\"280\" />\n      </bpmndi:BPMNEdge>\n      <bpmndi:BPMNEdge id=\"Flow_01maojf_di\" bpmnElement=\"Flow_01maojf\">\n        <di:waypoint x=\"950\" y=\"220\" />\n        <di:waypoint x=\"1022\" y=\"220\" />\n      </bpmndi:BPMNEdge>\n      <bpmndi:BPMNEdge id=\"Flow_01rdmuq_di\" bpmnElement=\"Flow_01rdmuq\">\n        <di:waypoint x=\"690\" y=\"160\" />\n        <di:waypoint x=\"770\" y=\"160\" />\n        <di:waypoint x=\"770\" y=\"195\" />\n      </bpmndi:BPMNEdge>\n      <bpmndi:BPMNEdge id=\"Flow_1j0t4se_di\" bpmnElement=\"Flow_1j0t4se\">\n        <di:waypoint x=\"690\" y=\"280\" />\n        <di:waypoint x=\"770\" y=\"280\" />\n        <di:waypoint x=\"770\" y=\"245\" />\n      </bpmndi:BPMNEdge>\n      <bpmndi:BPMNEdge id=\"Flow_0rt1gbx_di\" bpmnElement=\"Flow_0rt1gbx\">\n        <di:waypoint x=\"795\" y=\"220\" />\n        <di:waypoint x=\"850\" y=\"220\" />\n      </bpmndi:BPMNEdge>\n    </bpmndi:BPMNPlane>\n  </bpmndi:BPMNDiagram>\n</definitions>\n',0),('1864607716060672001',1,'请假流程（包容网关）-leave4.leave4.png','1864607711493074946',_binary '�PNG\r\n\Z\n\0\0\0\rIHDR\0\0,\0\0J\0\0\0և\�p\0\01\�IDATx^\�\��\\u}7�i}l\�R��R�\�>�y-E��Z9\�OIv@��\��V��\�*�z��bk��B)����!!D y@A!! ,!�\0T�\��\\3�\�;�ٻ;;w\�ξ^\�\�n\�sg��\�\�\��\�\�ީ\�\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0**I�\�[�t\�\�,�՜9s�ٳgK\�I\�͟?�ɹs\�\�~\0\0\0`BZ�t\�\�\�`9Y�re�nݺ\�W^��?\���ϛ7\��ٳg:�\0\0��3+\�`9-姯�\�ٳgߕ�\0\0\0`\�@̬�F\�y�={\���s\0\0\0N\\C!\�,c�x>\�\�\0\0\0L8E/>ח,��\�d\�-_\�\�ǲ�v\�^4,\0\0\0�V�a�v͊d\�Mg&?�\�s\�b]~{y4,\0\0\0�V�a\��\�k��\�<��ڦ\�e\�Ѱ\0\0\0�Z��Ń��\�Ԩ�\'\�巗�G\�\0\0\0j\�\Z�f�\�Ԩ�\'\�巗�G\�\0\0\0j\ZU��\0\0\0Ԋ5,\�SA\�zb]~{y4,\0\0\0�V�a�\�o75*\�u�\�e\�Ѱ\0\0\0�Z��\�\�\�%�n�RS�\"�ź�\�2\�hX\0\0\0@�X\�\"\�\�O.njXĲ�v\�^4,\0\0\0�V�a�~}�\�\�\�65,bY�k\�^F\r\0\0\0�\rݰX�fE\�\�\�55+\�u�M�v2�hX\0\0\0@m\r�\�듾%s��o�BS�\"�\�&�5ۢ�hX\0\0\0@�u\�b�Y�\�l�\��a\0\0\0�\�\r�\"�*K\�6�?)\r\0\0\0��nX\�\�M~R<\Z\0\0\0Pkݰ����\0\0\0\�4,�\r\0\0\0�iXT-\Z\0\0\0PӰ�Z4,\0\0\0��aQ�hX\0\0\0@MâjѰ\0\0\0���Eբa\0\0\05\r��E\�\0\0\0j\ZU��\0\0\0\�4,�\r\0\0\0�iXT-\Z\0\0\0PӰ�Z4,\0\0\0��aQ�hX\0\0\0@MâjѰ\0\0\0��\�\�\�ޚfR~y��E��a\0\0@W\�\�\�M6d��\r�jE\�\0\0��\�а\�d\�BâZѰ\0\0\0���hX�l\\hXT+\Z\0\0\0t���|�\�\�X7,�}\�\���\�\�;�\�\�\�7my\���-Z��i\�ڵk,�۽\�\�K\�\��}\�w\�}ɚ5k�\�y�嗛�u:\Z\0\0\0\�\�\0[:��As�M�{\�7�\����\�MoJn��\��u�\�G�/�0�~޼y\�QG�\�CI^��\�\'�\�O2}�\����\�oO\�;\���F\�N;\�\�|\�\�\�\�\�?�,]�4�wl�\�\�{\'g�yf�\�X�pa�\��\�&w\�}wrꩧ\�\�\���\�\�\�-�$�L���\�\'?�\0\0\0�*\�\�\�5\�\�\�1<%\�\�\'�L�=\�\�\�c�I\��\�\�&w\�qG�`���Yͅ]v\�%�\�P\���\�K\�\�\�\�\�X�jU�\�V[e\�\�Cʚ\�m\�\���vZ\�p��>��\�q\���N:)\�n�\��mb�q\��̙3�&Ɣ)S�k��6\�\�\�\�fgĺ�s\�&�\�{\�\�\�t̰h�q\0\0\�8\��QQWvâ��~8�˿�ˬ\�3b\�W��\�\�c�؀�Ń>�-�\�׭[�}�a�G4 ^|\�\�d֬YY�b�\�\�Y\�\"�~\�S�\�\�\��+�\�fl\�OiL4,\�\�c��坎�E{�\0\0@\�9\�h\�P����7\�tS6�a\�\�\�\�̉�q\�\�\'\'˖-Kx\��lVCc\�\�[\��V\��\��?9蠃�\�ĲƆŒ%K�\�D\�}D\�\��\�\�O�<\�\�\�k45�a��\�p\�\r�\�]u\�U\Z�Ѱ��\0\0@\�9\�h\�P����1�bŊ\�7�\�\�\�\�p�\���\�͎��E\\ߢ��?����\�\��0y\�{ޓ�\�\�7�!�\Z\r�hZL�6-\�\�m�ݖq\�\�;\�|\�\�\Z\�{�\�[f\�\����M�\�5\�\\�a\�e�\0\0@\�9\�(G\�\r�\�ʕ+��en�\�6\�\�[o�5.�\�\�{\�\�N\�s\�=�C=4���\�\�vۭ�a׾�G4.b?1;#ff4\���K.I\�\�w߬�1cƌd�\��\�nS��E,�SE\���n��Ew1n\0\0\0\���cѰ�_�\�ײ\�\�\�>\��\�t��q�\�\�gM�h(\�\�/��\�\�B\�\r�X�(Ro2D\�\"�KѸ\�[n�%9\���\�\��]w\�5�\����z\�q\�%_�җ�\�fXt\�\0\0\�q<\�Qv\�\�\�/N~\��ë́�\��\�իW\��\�Jȝwޙ�\�\�L>�\�g3,;\�\�\�K/\�\��\�_�E\�裏\���9s�SMb&EOOO\�я~4kTl��\�ɤI��-�\�\"�\�\�Vâ�7\0\0��s\�Q��\�,Z�(��D~y41\�Ԑ��\�1\�\�oN<\��\�\�O�\�[ħ�4\�>.�9u\�\�\��h�D\�\"\ZqJ\�s\�=�<\�\�S�\�^y\�ٵ.\Zo��0\�Z{\��W\�\�\�t4,\�c\�\0\0\0:΁G9Ʋaש\�/�\�\'>1�	�/\�S@.�\�\�\������_~�\�~�\�\�\���O?�t���\�Ѱ�\�Z\�\�\�\�\�/O�>�\�\�O<\�D�\�N;%3g\�lZ\�\�hX�Ǹ\0\0t��r�U\�b�ԛ��\�g|�H�\��e\��ھ�hX�Ǹ\0\0t��rT�a1ѣa\�\�\0\0\�q<ʡaQ�hX�Ǹ\0\0t��rhXT+\Z\�1n\0\0\0\���\ZՊ�E{�\0\0@\�9\�(��E��a\�\�\0\0\�q<ʡaQ�hX�Ǹ\0\0t��rhXT+\Z\�1n\0\0\0\���\ZՊ�E{�\0\0@\�9\�(��E��a\�\�\0@E%I\�{K�.�|����3gNV�J�I\�͟?�ɹs\�\�~\�\�\��A��]\��\�?Gg\�h�:b죎\0�k�E\�\�\�\\�r\�\�dݺuM��t>\�s����y\�O�O\�#�s\�Q�(�\�\�2v�\�#�Q�q�=ꈱ�:��\�\���?)?}}}Ϥ�\�]�\�\�x�CâZѰh�q�=\�\�D@׉\�\��F\�yH�\��\�\�x�CâZѰh�q�=\�\�D@\�q\�Q�8\�h��r7�\�F{�\�1T+\�\0�J\�B\�\�\����w_�,�\�\�Y\��X�\�NڋB�=<\�Qtܐrb\�(.#n�qb�ܚ��+:�#ʉ\�\0��R�\�X�fE�\�3��_\���e�.���<\n�\�hX��ȸ!\�ŸQ\\:FLjѠ\�gR�v�\�x��(/\�\0�J�B\�\�\�\�4\�<��ڦ\�e\�Qh�GâE\�\r)/ƍ\�-�fW�P�\�@Q^�\0t�\"�ƃ��\�T`\�\�\�\�\�ȣ\�h��E9��R^�\�3(Z4*̮�\"\�:��\0\�*E\n�E�\�j*0\�u�\�e\�Qh�GâE\�\r)/ƍዙ-�fW�@�\�@Q^�\0t�F��\�h��E9��R^�\�7\�,�I�\�Z�\�@Q^�\0t�\"�F\\\�;_`\�\�\�\�\�ȣ\�h��E9��R^�#��eav\�\�\�\�x\0@W)Rh,�\�\�MF=�.���<\n�\�hX��ȸ!\�Ÿ12�Y�\�\�)�\�x��(/\�\0�J�Bc\����E7��Ȉe�.���<\n�\�hX��ȸ!\�Ÿ1r\�Y�\�Wd<PG�\�\0]�H�y\�\'7\Z�,����F{x��\�!\�ĸ1r1�b\�\�\�~~m(:�#ʉ\�\0��R�\�X�>Yr\�w�\n�X뚶�G�і�ohX|<��\�Uhܐ\�b\�h\�\�7\�l3\�F\n�\�\�b<\0��Uh�]�\"y��󚊌zb]l����,\n�����iN\�\�\��G\r5nH�1n��qc5�#ʍ\�\0��2h��~}ҷdnr��_h*.\�mb[�ƈ\�:\��3\�\�̙\�ںu\�~���\�Ó鸱>�1��8��7�#�u\0]�U�1Ի!�Ż$\�G�1l�d��Q0��\'W�\\\�\��+\�g�\�嗥\�\�]�\�M\Zl|l9����V\�\0t�V�F�wCK\�6�?)�ưup1\�zFh\�ܹ�͛7\�����g̴��?\��e˖]�����t�9bPC�C�\'GQ��#\0\�*�\n�|\�0\�\�\�\'ţ\�(�\�AE\�\��8H�w\�Ӽ��Rz\�\�?͊⊎E�����Z\��\�?G\00n�*4d\�\�(d�\�\�\�>\��������V\��\�\�\�Soo\�A===�\'�:\'ͣi^�O%\�\�5�=g\���b��>\0h�B�ZQhi�#�0��\�\�?\�\�M(\�jE1:�N��Ooo\�4�lhNMl?+n�\�\'\0#�ШV\Z�\�\��C��Ɵv_\�\�޾\�#�uD{�N��Koo\�\����da\�/\0�B�ZQhj�\ZFk?@\��\�\�}�\�ӕ\�Պ:bd?�\�\�zzz�\�\�\��Z�\�p\�)�$�\\rI\�ӟ�4y\�G�իW\'!�ƿcy��\�\��\�~c���&�t��\�x\�\�\�|\�\�g?\�\��\�\�=\�\�l\�9\�c~}\�i���.�\�9\�\�\�\�ӧo���D�ШV\Z-�\��\�h\���\�~��\����:�ZQG_z��\�ԩS\�\�h�6mZr�$O<\�D֜(*��\�\�\�s���\��\�\��@\�1b�o�\��g�y\�f̘����\�[vBcy�?\�\�#_=\�\��u\�Q�;���B�Q�(4�v�\�\�4\�\�\�Lso�\'\�\�\�nE����\"\�\�i�ٰ}\�`�ƿ�^\�ƍPGT+\�\�:u\��J����p\�\�&�?���N\�0\�\�c?��\�\�q�\�\0L\0.<��s\�YS�,X���\��q��\�.�?餓~}\�q\�\�\�\�\�\�?\��\�v\n�jE�1@���x��(ͫ�\�h\�-i&՚�\�?0��z]7:HQ��#�\�0���Y�\"��\��\�B[b�\���iH:�\�M7\�t\�\�G�\\�\�\�o~\��8QH\�.n�ᇿ8Ѯ\�ШV\Z�\���\�\���\�|`Q47\�~�k���\0\�W�\�l\�(�:�ZQGהh<\rd�\�\�ɽ\�ޛ?D�\�\�C\�\�g�i@����Y�f�\�c�=6�\�/~�F$\�3cƌ��M�vd����B�ZQhd\�oL\��ך$\�Г\��y��o\�6�ƿcZ\�Ui^\�\�\�4�ذm]���\�\'�:6n�DQ��#�\�p�\����jV\�\��gZ\��\�\�ebf\�1\���\\�2?&�%\�7}�\��S�L\�\']I�Q�(4�\�\��Aǭivڰ~(\�Ls^�_\�6\�~}\��t��\�׸Q\"uD���چ�.}�\�<�\�\�k\�\�\�0\�\�Uy\n]\�\�;\�<>��5�\"/\�\�k��\�g\�L�<�\���\�6\n�je�\Z����\�\�x\�qn�\�5�/j�4�\�6\�gm�آ\��\�\�\�uk\�(�:�Z�\�uD!���덃�0f�r\�\\�l@H_\�}\�+_Yל褫��zʹi\�n\�\��QhT+�\�hU\�ǅ\�\Z\�=���u#Ӿ�m\�ߢ4��E\�\�TS�׫q�d\�je\��\�\�\�SA\��4�\��k<5d�\�\�	\�n� >\rd�\�,*\�?cƌU\�@�g�1t�F�2A����KjnM\�W�\�{\�<_۸\�\�\r\\�\�\�\0\�1\�\�ԸQ2uD�2A\�\�z{{g՛\\pA��/E\�o\�,�Y�\��c\�k�w\�8\�_\�G��\�\�\�o.H~�\�D�Q�L�Bc�\"?�\�_�\�\�j\�\�=/\".�W?\�x�\��ni\�qco�קqc�#��	XG6mڴ7�u�+\�fAٳ+\�\�~\Z�\�\�\�?V`��\�\�;8ꨣ�W_}5�\���>��ɓ\'�#�X�.\0oM3)�<O�Q��\�B�\�\�Z�*\�\�6\�U�G\���\r�\��\�W\�\�\�\�\�Ʀ^�ƍQTtlWGT+㱎\rE~_\�\�\�1[{,\��74-\�?V`��袋f����\�|G�}\�ًzzz�\�?��k79�+4��\�Xh�]\������\�� \�\�m_�m\��\�\�5\Z\�q\�\�\�h\�EE\�vuD�2\�\�P\�\�5]~^}��\�G���Tq�\r�9>�\�\�sΪ�N��\�[\��\�\�\�\Z�M\�\n�je<\ZE\�\Z)\�\�m<0\�1�n4\�\��\��_�[�W\�\��U\�uh\�EE\�vuD�2\�\�P\�\�u\�ԩs\�\�\�\�K�R\��\�K<�\�\�	�c��~���K�\�_\�\�\�C-K��\�\�Z\�-p�F�2��c-\�6(Z\�?Y\�x`\�u�᝵��<�����}E_ƍQ\�bLo9��#��\�XG����M��\�\�\�\�\�\�>�ȋ�ox�\�\�\�@78\�c^{\�\�g\���Z�z\�\�t Y�,U\�b�\�\'��\\h\�v\�m\�/�д|�\�s\�=\�\�\�?ߴ<��\�\�\�?ܴ�\n��F�߭|�ߵ\�\��\�WjZ]ܮ]�_۸�\��u�\�\�F\�p^wƍQ\�b,\�\'۫VG4\�/�\�R\�\���i�z�X�jU\�\�u\�\�5-/�u\�hh\���O���T�w\�\�yq�\�\�2u\�\�\��`�:\�\�C�_��\��\�|G�\�\�B�A�kR�B\�\�_\�\�]v\�%�\�\�K�\�_|\�\�\�\��\�%\��\�7,;\�3�϶�\�y晦�\�s\�\�7\'�{\�뒅6�\�䟟n\�\�ɓ�\�6\�,��\�\�\�A\�ժ\�Ni]<�8�xc~0\�\�u��q?n\�\�\�nK�o\�X\�\�3\�L��\��g\�_w\�uɶ\�n�<\�\�M\�E�=\�\�\��Ln�\�d�\�\��y\��ޗ,Z��i�h�D����o�\��\��MێU\�ύ�N\�\�yq�\r�\�7�A��>�\�_�\�]�j\�/{�k�ŭ��\�\�\�\�I|L\�o}kVH\�\�{\�w2cƌ�m\��\���\�O\�&��\�g�m>\�$;\��S�\�[l�\\t\�EM\��&��g3-\�\�\�:\�\��c-\�j\�{�\�g��;\�֍�]j\��0�n0\�y��\�\�\�θ1�Z�\�-\�\�*\��+V$ox\������̝;�\�\r�\�SO=�l�\�\�\�\�\�˳7G\�v�<\�N8���3\�uI\�(�\��wܱY\�O�\�O*SS�\�:b4��=m�}��\�\�eO\�a]\�\�SO}�\�s\�x\���\�v\�5,uU*4/^��\��\�\��ww\�uW\��׾\��\�_�\�\�����;������\�;\"Ѭ�Ї>�57v\�m�AO\'�\�\��-�\�2�i�_7\���F\�ߵ\r�\�W\�6�[7\Z���q��\��U-}\��\�+��3n���c{�ꈘyy\��\'\����#�<2y\�;ޑ����\�\�7ͼ<\�#��/�8���m�\�&�Q\�\��0�\�?�\�\�g?�ـmc�\��̟??\�.\���\�|&y\�ߜ\�\�g\�\�X\�*㱎\rE~_{]\�\�/|\���)!W^y\�5�\��SB�\�FU*4b�\��\�\�O\��\�?�\�d��\�˾�\�q\�Gl3\'\��hn�u\�YљN�L�\�߰�\���\��<\�&���\Z��*e<\ZE\�\Z)\�\�`�~`pUn\�h���q�\�\�\�y�@gy\Z7FQѱ�JuD��q\�i�\rX\��d5A㲨#\�4\�O|\�\�V[m�}b\�y睗L�4)�\�;�\�w\�=9\�C�5k\�\�\�&\Z1[#f�\�\�>ٵ2�\�n�lݻ\�\���\�2V�u\�h(\��\�SB��;�쳿~��\�\�_\�u\�	\'\\\�\�\�sl��Tݦ\�FU*4y\�Mΰ�7b\�\�\�O?��\��q\�}�C\�H�O͌\�s\�i\�=Qt\���\�M\�\�:\�\�(���3T1�Mm\�A\\H/\�-�\�6\��\�4o�z��\'P��^�\�Ԍ��\�\�^�:\�\��fˆE�\�Ѹ,j�9s\�dopD�\�\�ˮ{Q?u$���\�^{%7\�xcr��\�g��Ƶ0\�\�\���SVgX��\�ooz,c�\�XG��\"��\��\�\�M��Y3�\��\�%W��\�T�Ga\�3f��ʿ\�;\"���\�AdUz0���c\�U*4b�Ŧ\Z���ą�ⴎ��\�+�L\��\�w\�\�̈\��}~�\�\�Ź�w\�}wv\�H����	VhU\�\�R\�x�0Z\�/̓����\��\�\���\�\r\��4n��JG��կ&;\�C\�\�ӓ�\nob\�\�q�H\�v1;\�\�SO\�N!�Y���rJ�\��\"q��xS�~\ZI\\�b��\�\�N	y衇�����0\�\"\�L\�?��\��#�%�\��7	\�9K\'�|\�k\r\r��\�\�N<\�\�G\�:-\�\�.�Q:��8��I�\n�|p����~�\��\�\�q�i�\�� S_͊�\Z\��\�w��}\�c\�\�3g\�\�\�))\�Q�ee\Z�*\�\'\�6 �:\�n֎̿\�6\�su�?��ߦ0�6\���T3n��*u\��\�\��\�\�\�?��\�oi�&\Z�\��\�����o���nE\\\�\"�\�1�є��Qs\�\��_\�\".\�M��F}�\�;\��\�l&F�6c�	XG6mڴ7�u�+\�FA<\�c!Y\�J<��cƱ\�\��>餓~��\��&��U\��O�%S�L\�3��IU\n�H4,\Z?	�q�\�W\\1\�\"�1]3\�?\�u\�]���\��,4\�\".pS:?�}\�=\�̊�\�\�T\�2�\�\�T�cm\�B\\�\�\�W\�����=pu�M=�\Z6\�:5n��*uD\�r�ַ��͞�\�ŬY���\�ꫯ\�fS\�l�%K�d\�F\ro~\�v\'�|rr�\�\�gM��\�f�a�v\�\��SD�n�\�\">n�+_�J\�\�\"�3,\�\�Ԫ��1A\�\�\�\�~V�Yp�\�\��R\��64,\�\�u\�N8\�{����\�\��(z\�_�\�_.L�\�\�\�\�m�Rh�J4,.\\ش<\�\�\�o�qJH}�E�\�\��\�\�[�\�\�\'�D����ƕ�\�]��`V�6c�	\\hV\�5\�S����yπ-�\��4?�\r<\����-6\Z\�q\0\�3\�\�ոQ��\�ձH\\+��\�q\�̘YQ_��\�c�\�E\�oXĿ\�Դ֋ٴ���\�u+\�:\�}�!׶�\����\�mY3\�{\��^��#�װ\�y睓}\�\�7���leg\��L�2\�\�f��i\�J�e\�7u\�\��\�A\�\�\�#\�&O��\�\�\�\�_�\��q`T�\�.J�����}w�*\Z�哟�dr\�\�6-�\'\�#��qj\�?�\�?d\�\�;\�\�.�٪o�\�\�;.+h\���D\�#�h\�տ\�\�\�\�/4+�\���\�ƃ�\�Ҝ�\�4n4��P\�/j:n��\�`$o���k�׭q�$U�#\�\�}�R\Z\r�H�>\�\�\Z���ě�\����t\�טI�jժ�\�U\��#\nIk��\����瞛?訸��\�\�\r\�\"S�N\�\�#�xy\�ʕ���-˖-�6@��(Ϫ\Z��\��e�Rt�\��Ơ\��\�\�@<�\�\�\�\�\�6l�u�]\��cm\�G\�\�o5\�m{�\Z7JP\�:b\"F1�\�b��\��\�8�\�\�k\�\�\�Ьx5G��]fڴiGN�>��њi�afŊ4G\�\�[)4��Ff����Y\\k>�(��P�sϡ{\r\�:6nt�:�ZQG\�\�\�\�z\� f\�ƌ\�N��7�\n\��L@����z蚫��z\�H/\�\�\�p͊�&\�̊:�F��\�\�7\��@�\���4\�Ԛ.Z\�\�\�o?�p�+pv?��3\�\�ٸ\�A\�jEQ\�\��YZ\���\�@�k�t�i�M\�FG\�o\��1]���\�N�6\��3f���\�۟{\�\�W\�cEK\�v+/�\�KҁcI�b?�}w;�F��\�`Sq\0\�4��3\�\�\�o4\�\�4?Ls@�7\�o\�¦\��O�z]7:@Q��#�Kk��\�ħ\�ϴ�\�k\�m��5ά\�pq\Z\Z0\�G������>���\�:\��9s\�,z衇{\�g^Lǌ�O?�\�\�ŋ�䪫���\�O�!\�\��ؾ\�?�tS\ZՊB�I�:�_`\�u\�\�ݩ��k\�jE1<S�N�_�M�H\\�\�O�\�\�.��5+\��\���&O���tP8���\�\�\�]\rQ|�k\�\�cb��m\'\Z�F��\�hi�F{@\��\�\�|�\�\�5\�Պ:b�z;Ӣ�\��H̶�\���O��\�>n��U��\���o\0��ШV\Z�\Z���\�\�P}�\�z��t%uD������Ćq\�\r\�)���\\r\�%\�O�\�\�GIV�^�5\'\�k�;�\���O>�鶱�دkV\0��B�ZQhlR�\r\�\�\�}ݷ{�����V\�\�\�\�[4F��>��M\n�jE�1��<�\�v��7\�\��Ho7��#�u\�\�:u\�>����ҼҢ��\�\��\�\��}0\n�jE�Q\�p\"��=\�}�;w�	KQ��#F\�\���\�\�ރzzz�O�uN�GӼ��9_\�\�s6�?(�\�\��6(4��FaE&�n\�0$I\�{K�.�|����3gN\�{+\�&���6��\'\�Ν�_��aPEǃ�\�QSGT-\�|\�#\0�\ZՊBcX�:�j=#�t\�ҙ\��r�r\�\�dݺuM�\�\��\�\�=~�\�\�\�{>7>��\�P\�\�P\�\�QGT+\�\0��B�ZQh\�`�-g\�̊8X\���J�\�\�\�{&7\�\�?Gl\�`\�\�`\�\�uD���\0��(4��ƈ\�2\��f�\�i fVT#\�<�\�\���sĐ\�\�D�\����V\�\0t�F��\����\�i�:\�\� \�F�b\�1\�\�(0T+\�\0��B�ZQh�\�\�q\�\���_�\�*:n��\\_�\�\��ŷ|9K|\�\�\�I{1n�\��\�\���7\�l3\�F��RN�\0t�F��\�hφ�Vd\�X�fE�\�3��_\���e�.���<ƍ�Kǌ[\'O�\�\�׆\"ご\�\0]E�Q�(4ڣaQ�\"\�\�㋯ijV\�\�\�\�k����Ǹ12\�x1)ƌ\r��_O1E\�)/\�\0��B�ZQh�GâEƍo=��QQO�\�o/#�qcdbvEC\�\�\��z�)2Hy1\0\�U\ZՊB�=\Z\�(2n,�}VS���X�\�^F\�\�\�\�fW�eц\"ご\�\0]E�Q�(4ڣaQ�\"ㆆEy1n_nv�Ym(2Hy1\0\�U\ZՊB�=\Z\�(2nħ�\�\�ĺ�\�2\�7�g�\�fY�P�\�@ʋ\�\0���ШV\Z\�Ѱ(G�qc\�\�njT\�\�\�\�\�\�c\��AfW�e1BE\�)/\�\0��B�ZQh�GâEƍ\�+\�K\����fE,�u�\�e\�1n3(Z4)򙔿�+2Hy1\0\�U\ZՊB�=\Z\�(:n<����\Z�,���\�Fq1��E�\"�,��\�x \�\�x\0@WQhT+\n�\�hX��и�~}�\�\�\�65,bY�k\�^F\�F{�\�)4Hi1\0\�U\ZՊB�=<\�1Ը�v͊\�\�;\�kjV\�\�b��\�dd1n�Ǹў�\�)7\�\0�ʜ9s^[�n]\�<)?\�\�\�dZh�\�?G\������_�\�-��\�\����\�6��\�\�\�J{�\�QGT\'\�\0�\���\�\\�re\�=)?˗/�,-4\�\�?G\����\ZCͪ,f[�\r�\�7ڣ��N\�\0t��s\�\�7o޼\�����\�\�\�$��\�-[�\�Ҵ\�x,ͧ\�\�\�9\�(G��E�Y�%n�ߟ��E{�\�QG�}\�\0t��\��4�D\�+�\'~\�\�\�Wd�ɁG9\�\�6_0\�\�M~R<\�|\�#�3n�/�~m�;����#\0\0�΁G9�@\�4\�\�%���sDq\�\r\0\0�\�x�CâZѰh�q\0\0\�8\�а�V4,\�c\�\0\0\0:΁G94,�\r�\�7\0\0��s\�Q\r�jEâ=\�\r\0\0�\�x�CâZѰh�q\0\0\�8\�а�V4,\�c\�\0\0\0:΁G94,�\r�\�7\0\0��s\�Q\r�jEâ=\�\r\0\0�\�x�CâZѰh�q\0\0\�8\�а�V4,\�c\�\0\0\0:΁G94,�\r�\�7\0\0��s\�Q\r�jEâ=\�\r\0\0�\�x�CâZѰh�q\0\0\�8\�I~����_��aQ�hX�Ǹ\0\0t��\�\�\�oC6ٸа�V4,\�c\�\0\0\0:΁G{\Z\Z�l\\hXT+\Z\�1n\0\0\0\���=-\Z-\ZՊ�E{�\0\0@\�9\�hO�FE>Y\�b�\Z\�>�\�?\�\�#\�SO=մ]>w\�uWӲ|֮]�,Z���\�/�\�B\�\�K/e߯_�~��\�\�w_�f͚�}D^~�\�e���E{�\0\0@ǵ8��%\�\\F\�\��\�\�_�r\��s\�=��\�L�\�f�\�\�\'��Q\��$�{\�\�\�.��i]c\�\�;\��Sr\�\�7\'\�?�|�t\�\�\�\�ӧOO\�\�{\�\�\�3\�̶�\�\��m�\�6��SO=59ꨣ�|\��\�\�;\�5;\��\�d\�ύ?��\0\0�\n\�\�5\�\�\�1>%dٲeɻ\�\��lVE4v\�a��I�>�d\�ԩ\�^{한~�\�M�������\�\�\�O~2kr\�\�Gs\�\�N\�\�\��| �Ї>�\�\�\�I\'��l�\�v\�6�\�K�`��\���3gf��2eJr\��\�&}}}\�\�X7w\�\�d\�\�wo��N\�\0\0\0�\�P����hX{\�\��\�e���p�\���FĤI��\�n�m�m\�:\�\��ꯒu\�\�e�\�m�ݲ�}?\�\�cY\�\�_Lf͚�5+�/_�\�O|�ԧ>տ\�W\\�\�\�>���4&\Z{\�G\�\�NG\�\0\0��6T���\�E4	�\�� 9\�\�#��\�Dh<#fED���}̚8�裳�K}\�C\�\��n�\�[fM�k��f�}r\�!\��矟\�G|�SB�aq\�y\�%7\�pC�vW]uՀFc4,\0\0\0��jTԕݰX�zu\�\���\�\�iw\�qG�\�駟p�G̎�SE\�̙�;8\�3�\Z\�l�\�V\�\��\�\�%��\�����\�n�2�8∬��\�6\�\�\�\�[\�\�\���\�O&O�\�?\�\�а\0\0\0�\n*�a�t�}\�\�\�\�\��\�t\�MY\�!�#ј�@\�a���Z�*�\�-�ܒͼh\�&\Z\'�pB\��/�\�d\�}\�͚\Z3f\�H�\�~�dɒ%�װ�\�q�H|׭а\0\0\0�\n*�a\�\�\�\'o|\��kG\\p�Y3\�?�Av퉘q�\�\�7\�\�\�?�q�\�{X״�䷍\�F��q��]w\�5Y�bE�\�\�[g_�;\�\�K_�R�\�\0\0\0�����nE|}衇�[o�5kTĬ����\�?;]��M$Niհ8\�s�\���\�ǔ\�L����\��h֨\�|\�ͳ�yn�\�\�G�ƶ\Z\0\0\0PQe7,/^��\�_�ur\��&���\�\��\��\���\�7�)y\��S=\Z�53,\�\���\r8%$N�ϔhL\\T3>\Z5��Ѵ��E�\�\�xꩧ���\�\�+�SK\Zo��0\���q�\���a\0\0\0�\��{\�\'��\�o;�I\�\��&�\��_��q\�\�SC\�1~\�����o~\�\�\�*\���x\�E;\���^F|��E\\\�\"�\�\�_�}I~�O<�]Gc\�̙M\�:\r\0\0\0��M\�b��O\�/N�q�~���\�\�\�\�i�}Ѱ\0\0\0�\��hXL�hX\0\0\0@MâjѰ\0\0\0���Eբa\0\0\05\r��E\�\0\0\0j\ZU��\0\0\0\�4,�\r\0\0\0�iXT-\Z\0\0\0PӰ�Z4,\0\0\0��aQ�hX\0\0\0@MâjѰ\0\0\0���Eբa\0\0\05\r��E\�\0\0\0j\ZU��\0\0\0\�4,�\r\0\0\0�iXT-\Z\0\0\0PӰ�Z4,\0\0\0��aQ�hX\0\0\0@MâjѰ\0\0\0���Eբa\0\0\05\r��E\�\0\0\0j\ZU��\0\0\0\�4,�\r\0\0\0�iXT-\Z\0\0\0PӰ�Z4,\0\0\0��aQ�hX\0\0\0@MâjѰ\0\0\0�Ԝ9s^[�n]Ӂ���\�yxr\�\�\�\�\�\�\0\0\0L8\�\�\�r\�ʕM\�R~�/_~\�\�ٳ\�\�?G\0\0\00\�̝;w�y\�\�=\�\�\�\���c�\�\�޷lٲKgϞ�X�O\�#\0\0\0��\� 9\�\�O\�J\\CAJO�\�\�\�Y\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0�\�\��\0�\�M޶�}\r\0\0\0\0IEND�B`�',1),('1864607717163773955',1,'请假流程（并行网关）-leave3.bpmn20.xml','1864607717163773954',_binary '<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<definitions xmlns=\"http://www.omg.org/spec/BPMN/20100524/MODEL\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:bpmndi=\"http://www.omg.org/spec/BPMN/20100524/DI\" xmlns:omgdc=\"http://www.omg.org/spec/DD/20100524/DC\" xmlns:bioc=\"http://bpmn.io/schema/bpmn/biocolor/1.0\" xmlns:flowable=\"http://flowable.org/bpmn\" xmlns:di=\"http://www.omg.org/spec/DD/20100524/DI\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" targetNamespace=\"http://www.flowable.org/processdef\">\n  <process id=\"leave3\" name=\"请假流程（并行网关）\">\n    <startEvent id=\"startNode1\" name=\"开始\">\n      <outgoing>Flow_14qet78</outgoing>\n    </startEvent>\n    <userTask id=\"Activity_0uscrk3\" name=\"申请人\" flowable:formKey=\"static:1\">\n      <incoming>Flow_14qet78</incoming>\n      <outgoing>Flow_171lpw3</outgoing>\n    </userTask>\n    <sequenceFlow id=\"Flow_14qet78\" sourceRef=\"startNode1\" targetRef=\"Activity_0uscrk3\" />\n    <userTask id=\"Activity_0ped7fd\" name=\"科研部门\" flowable:candidateUsers=\"1,3\">\n      <extensionElements />\n      <incoming>Flow_16qxdzv</incoming>\n      <outgoing>Flow_01rdmuq</outgoing>\n    </userTask>\n    <userTask id=\"Activity_1e8dxc6\" name=\"总经理\" flowable:candidateUsers=\"1\">\n      <extensionElements />\n      <incoming>Flow_0rt1gbx</incoming>\n      <outgoing>Flow_01maojf</outgoing>\n    </userTask>\n    <userTask id=\"Activity_0xun74h\" name=\"综合部门\" flowable:assignee=\"1\">\n      <extensionElements />\n      <incoming>Flow_15anuo0</incoming>\n      <outgoing>Flow_1j0t4se</outgoing>\n    </userTask>\n    <sequenceFlow id=\"Flow_171lpw3\" sourceRef=\"Activity_0uscrk3\" targetRef=\"Gateway_0qj0eur\" />\n    <sequenceFlow id=\"Flow_16qxdzv\" sourceRef=\"Gateway_0qj0eur\" targetRef=\"Activity_0ped7fd\" />\n    <sequenceFlow id=\"Flow_15anuo0\" sourceRef=\"Gateway_0qj0eur\" targetRef=\"Activity_0xun74h\" />\n    <parallelGateway id=\"Gateway_0qj0eur\">\n      <incoming>Flow_171lpw3</incoming>\n      <outgoing>Flow_16qxdzv</outgoing>\n      <outgoing>Flow_15anuo0</outgoing>\n    </parallelGateway>\n    <endEvent id=\"Event_0k2b5e5\">\n      <incoming>Flow_01maojf</incoming>\n    </endEvent>\n    <sequenceFlow id=\"Flow_01maojf\" sourceRef=\"Activity_1e8dxc6\" targetRef=\"Event_0k2b5e5\" />\n    <sequenceFlow id=\"Flow_01rdmuq\" sourceRef=\"Activity_0ped7fd\" targetRef=\"Gateway_05y03rn\" />\n    <parallelGateway id=\"Gateway_05y03rn\">\n      <incoming>Flow_01rdmuq</incoming>\n      <incoming>Flow_1j0t4se</incoming>\n      <outgoing>Flow_0rt1gbx</outgoing>\n    </parallelGateway>\n    <sequenceFlow id=\"Flow_1j0t4se\" sourceRef=\"Activity_0xun74h\" targetRef=\"Gateway_05y03rn\" />\n    <sequenceFlow id=\"Flow_0rt1gbx\" sourceRef=\"Gateway_05y03rn\" targetRef=\"Activity_1e8dxc6\" />\n  </process>\n  <bpmndi:BPMNDiagram id=\"BPMNDiagram_flow\">\n    <bpmndi:BPMNPlane id=\"BPMNPlane_flow\" bpmnElement=\"leave3\">\n      <bpmndi:BPMNShape id=\"BPMNShape_startNode1\" bpmnElement=\"startNode1\" bioc:stroke=\"\">\n        <omgdc:Bounds x=\"235\" y=\"205\" width=\"30\" height=\"30\" />\n        <bpmndi:BPMNLabel>\n          <omgdc:Bounds x=\"238\" y=\"242\" width=\"22\" height=\"14\" />\n        </bpmndi:BPMNLabel>\n      </bpmndi:BPMNShape>\n      <bpmndi:BPMNShape id=\"Activity_0uscrk3_di\" bpmnElement=\"Activity_0uscrk3\">\n        <omgdc:Bounds x=\"320\" y=\"180\" width=\"100\" height=\"80\" />\n        <bpmndi:BPMNLabel />\n      </bpmndi:BPMNShape>\n      <bpmndi:BPMNShape id=\"Activity_0ped7fd_di\" bpmnElement=\"Activity_0ped7fd\">\n        <omgdc:Bounds x=\"590\" y=\"120\" width=\"100\" height=\"80\" />\n        <bpmndi:BPMNLabel />\n      </bpmndi:BPMNShape>\n      <bpmndi:BPMNShape id=\"Activity_1e8dxc6_di\" bpmnElement=\"Activity_1e8dxc6\">\n        <omgdc:Bounds x=\"850\" y=\"180\" width=\"100\" height=\"80\" />\n        <bpmndi:BPMNLabel />\n      </bpmndi:BPMNShape>\n      <bpmndi:BPMNShape id=\"Activity_0xun74h_di\" bpmnElement=\"Activity_0xun74h\">\n        <omgdc:Bounds x=\"590\" y=\"240\" width=\"100\" height=\"80\" />\n        <bpmndi:BPMNLabel />\n      </bpmndi:BPMNShape>\n      <bpmndi:BPMNShape id=\"Gateway_0ydkpig_di\" bpmnElement=\"Gateway_0qj0eur\">\n        <omgdc:Bounds x=\"475\" y=\"195\" width=\"50\" height=\"50\" />\n      </bpmndi:BPMNShape>\n      <bpmndi:BPMNShape id=\"Event_0k2b5e5_di\" bpmnElement=\"Event_0k2b5e5\">\n        <omgdc:Bounds x=\"1022\" y=\"202\" width=\"36\" height=\"36\" />\n      </bpmndi:BPMNShape>\n      <bpmndi:BPMNShape id=\"Gateway_0f4e1bw_di\" bpmnElement=\"Gateway_05y03rn\">\n        <omgdc:Bounds x=\"745\" y=\"195\" width=\"50\" height=\"50\" />\n      </bpmndi:BPMNShape>\n      <bpmndi:BPMNEdge id=\"Flow_14qet78_di\" bpmnElement=\"Flow_14qet78\">\n        <di:waypoint x=\"265\" y=\"220\" />\n        <di:waypoint x=\"320\" y=\"220\" />\n      </bpmndi:BPMNEdge>\n      <bpmndi:BPMNEdge id=\"Flow_171lpw3_di\" bpmnElement=\"Flow_171lpw3\">\n        <di:waypoint x=\"420\" y=\"220\" />\n        <di:waypoint x=\"475\" y=\"220\" />\n      </bpmndi:BPMNEdge>\n      <bpmndi:BPMNEdge id=\"Flow_16qxdzv_di\" bpmnElement=\"Flow_16qxdzv\">\n        <di:waypoint x=\"500\" y=\"195\" />\n        <di:waypoint x=\"500\" y=\"160\" />\n        <di:waypoint x=\"590\" y=\"160\" />\n      </bpmndi:BPMNEdge>\n      <bpmndi:BPMNEdge id=\"Flow_15anuo0_di\" bpmnElement=\"Flow_15anuo0\">\n        <di:waypoint x=\"500\" y=\"245\" />\n        <di:waypoint x=\"500\" y=\"280\" />\n        <di:waypoint x=\"590\" y=\"280\" />\n      </bpmndi:BPMNEdge>\n      <bpmndi:BPMNEdge id=\"Flow_01maojf_di\" bpmnElement=\"Flow_01maojf\">\n        <di:waypoint x=\"950\" y=\"220\" />\n        <di:waypoint x=\"1022\" y=\"220\" />\n      </bpmndi:BPMNEdge>\n      <bpmndi:BPMNEdge id=\"Flow_01rdmuq_di\" bpmnElement=\"Flow_01rdmuq\">\n        <di:waypoint x=\"690\" y=\"160\" />\n        <di:waypoint x=\"770\" y=\"160\" />\n        <di:waypoint x=\"770\" y=\"195\" />\n      </bpmndi:BPMNEdge>\n      <bpmndi:BPMNEdge id=\"Flow_1j0t4se_di\" bpmnElement=\"Flow_1j0t4se\">\n        <di:waypoint x=\"690\" y=\"280\" />\n        <di:waypoint x=\"770\" y=\"280\" />\n        <di:waypoint x=\"770\" y=\"245\" />\n      </bpmndi:BPMNEdge>\n      <bpmndi:BPMNEdge id=\"Flow_0rt1gbx_di\" bpmnElement=\"Flow_0rt1gbx\">\n        <di:waypoint x=\"795\" y=\"220\" />\n        <di:waypoint x=\"850\" y=\"220\" />\n      </bpmndi:BPMNEdge>\n    </bpmndi:BPMNPlane>\n  </bpmndi:BPMNDiagram>\n</definitions>\n',0),('1864607717742587906',1,'请假流程（并行网关）-leave3.leave3.png','1864607717163773954',_binary '�PNG\r\n\Z\n\0\0\0\rIHDR\0\0,\0\0J\0\0\0և\�p\0\0/\�IDATx^\�\��\�y.\�=Ǖ\'�\�!&\�8>;���\\N�p	\�\�\n0��_*`\�`(�8�IlW�`�I\�P`C*�p+�H\� �AFH�X�	��\r}\�\�\�\Z\�\�j{�wz{g��\�]\�\�̴vf���o�\�\�\�\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0��$I~sɒ%\�ϟ?��}}}\�\�ٳ�\�\�~\�\�Λ7\�\�9s\��}~\0\0\0`BZ�dɌ\�d9Y�bE�nݺ\�\��ߖ�?\���ϝ;\�\�ٳg�}�\0\0\0`�1Y\�N���\�\�\��2{\�\��\�\0\0\0L8q��\�H<�g\�^�}�\0\0\0`s(d\'\�2v�\�#�\0\0����a\�\�k�ɒ��L\��\�4\�}\\�\�N�E\�\0\0\0�\�5,֮^�,�\��\�g�~i�\�eq]v{i=\Z\0\0\0Е�a\���YM͊z^XtK\�\�\�z4,\0\0\0�+_\�\�\�{.ijT\�\�e��֣a\0\0\0]�\Zg_\�Ԩ�\'�\�n/�G\�\0\0\0�4,�\r\0\0\0\�\�װ�O\�6*\�\�\�K\�Ѱ\0\0\0��|\r�\�\���QQO\\�\�^Z��\0\0\0t\�kX�Z�X�\���qY\\�\�^Z��\0\0\0t\�kXD��\�\�M\r��,����\0\0\0t\�lX�_�,~\�\�E\\\�5m/-G\�\0\0\0��oX�]�<y��K��\�\�u�M\�v\�Z4,\0\0\0�k\r�\�\��\�s�\�\�8��I�Ml\�ZmQ<\Z\0\0\0\�5x\�b�UC\�j�\�Ѱ\0\0\0���yVU��m\��$4,\0\0\0�k\��E�	1\�d\�O\�G\�\0\0\0�oX\�\�E\�\0\0\0�4,�\r\0\0\0\�Ұ�Z4,\0\0\0�KâjѰ\0\0\0�.\r��E\�\0\0\0�4,�\r\0\0\0\�Ұ�Z4,\0\0\0�KâjѰ\0\0\0�.\r��E\�\0\0\0�4,�\r\0\0\0\�Ұ�Z4,\0\0\0�KâjѰ\0\0\0�.\r��E\�\0\0\0�4,�\r\0\0\0:ZOO\�=�L\�^��aQ�hX\0\0\0\�\�zzz�\r\�d\�BâZѰ\0\0\0��54,6ٸа�V4,\0\0\0\�h�4,m\\hXT+\Z\0\0\0t�A\Z٤���nX��\����H֯_ߴ]d͚5M�-\\��鲵k\�nty\�\�\�7\�L�\�\�\�c�=��^��\�>\"o�\�V\�e펆\0\0@\�\r2��6%;inw�i\�裏&_�\�W�\��\�}\�}\�\�7pݧ?�\�\�\�+�L��;wnr�\�ǧ9\�\�#�\���\�H\�\��dڴi�Ї>�\\z\�\�4\Zv\�e�䮻\�J^�\�dɒ%\�c��\�\�/9��\�\�c��ɶ\�n�<\�\�C\�Yg�5\�xGqD�뮻\�$ig�ύ�<ٱ\0\0`T�x��\�5䞞1<$\�\�_LN:\�\�\�OL�\�n�\���\�O\�ϟ�6���\�n���\�\�?\�\�s\�>\����+W�L�\�j�\���w\�9mFԷ�۟}\�\�i\�ᓟ�dz�N;픜~�\�\�\�\�o�n\��\�Ϙ1#mbL�2%�\�[����tuF\\7gΜd\�=\�l\��v\�\n�b�\0\0@ۙx3\\���\�E=O?�t\���i�!V:\�\n���\�\�g?�ٍ\ZO>�dzY|�nݺ\�\�P\r�H48�\�\�o$3g\�L�˖-K\�\�\�����7\�pC�b�~�Hc�a�\�^{5]\�\�hXc\�\0\0\0\�\�ģ�\�\Zue7,\�\�\�tU\�\�ɓӕ�\�3\�H�.]�<\�\�骆Ɔ\�w�\�\���Dr�ᇧ��\�\Z�/N���͏\�.�,9\�\�ү\�Ԉ�E:r�\��lw\�M7m\��h��\��d\�\0\0\0\�\�ģ�\�\Zue7,b5\�\�\�˓o}\�[\�#�\�\r�C9$]\r�8�E}�O}\�SiS\�?�a\���\�\�\�}\�{ӯ\�\���\�ԩS\���\�\�{�c�=6\�q\��8 ml\�\�h\�-�L�}�\�\���Y�fiXt\�\0\0\�v&\�(�aY�bEz�\�m�\�&\�z\�\�F\�UW]�<\�\�#\�a\"�\�Or\�QG%�\�v[�\�{4,\�\�\�\��\�E\�O�Έ��\�}\�5\�$tP\�И>}z�\�;����\�\".�CE\��8o��Eg1n\0\0\0mg\�Q��hXĊ�o|\�is\�K_�Rz�G��8\�S\�&F4\�䗷\�zk�I!\��E\\�(Ro2D\�\"\�K\�x\�w\�}wr衇�\�\�!&�\�{��#\Z#\�\�\�ON.�\��\�z+,:�q\0\0h;�r�ݰ��꫓�\�Gi3!>\�4>�cժU\�g?%\�H>\�${\�w�\�\�裏N��\�\�\��?�\�?O�}\�ٍ�/=\�$VRtww\'��\�g\�F\�\�o�L�4)\�b�-ҏ0�m5,:�q\0\0h;�r�ݰ�g\�\��\'��G#\r��;\�K+0\�\�\�\�K\�IQ���8�E|�H\�\�㤚���\�\�\���E4,␐\�^{-y饗��\�\�\�s]4\�>>1$VaĹ.\�\�wߦ�kw4,�1n\0\0\0mg\�Q��lX\�y*��\�s�ۨ	�/\�S@��\�\�\��韒�\�zk຃>�\�cM_~�\�O\r�76�a\�\�>\�\�\�_��p\�	M��\�\�\�.�\�̘1�\�vGâ\�\0\0\�v&\���\�P�7F3q�\�)\"�]������/#\Z\�7\0\0��3\�(G\�\Z=\Z\�7\0\0��3\�(��E��aQ�q\0\0h;�rhXT+\Z\�7\0\0��3\�(��E��aQ�q\0\0h;�rhXT+\Z\�7\0\0��3\�(��E��aQ�q\0\0h;�rhXT+\Z\�7\0\0��3\�(��E��aQ�q\0\0h;�rhXT+\Z\�7\0\0**I�\�\\�d\�\�\�\�\��e___Z�J���\�ߝ7oދs\�\�98��02&\�\�mv\�,c�x>�\��7�QG�}\�\0t�Z�1�\�K.Y�bE�nݺ�BXڟ��\�\�\�ܹ�\�\n��\���x�#\n\�\�\�X\�.\�|d�#\�3n����#\0\�X\���%��\�\'姿���Z�\�`\�9\"?�rhXT+\Z\�7�QGT\'\�\0:N,\�\��j$��Z��>����G94,�\r�b�Ũ#�u\0\�ģZ1\�(\�ģƍjŸQ�q�\�A�b<\0��\�-4\�x�?Y\�Еɢ���&��˲\�I�(4�1\�(G\�qCʉq#�\�qO�\�\�\�\�Z\�\�@QN�\0t�<�\�\�\�˓�w���\�\�/m��,�\�n/�G�Q��E9\�R^��\�ƈI�4(����C\�3�#ʋ\�\0����\�x~Ѭ�\"��\�Ҵ���F1\Z\�\�3nHy1n�L���IauE�\�\�\�b<\0��\�)4��璦���.����F1\Z\�\�3nHy1n�L���QauE�\�\�\�b<\0��\�)4ξ����\'�\�n/�G�Q��E9\�R^�#+)iVX]т<\�:��\0\�(\n�jE�Q��E9\�R^�#7\�*�I\�\�^�\�@Q^�\0t�<�F�\�;[`\�\�e��֣\�(Fây\�\r)/ƍ\�dVYX]Ѣ<\�:��\0\�(y\n�\�\�����\'�\�n/�G�Q��E9\�R^��ɬ�����|\�\�\�b<\0��\�)4V-,Yx\�MEF\\\�e��֣\�(Fây\�\r)/ƍ\�\�WYd/\'�<\�:��\0\�(y\n�ȳ?���Ј˲\�I�(4�1\�(G\�qCʉq�u��b\�\�\�~~\�\�\�\�x\0@G\�Uh�_�,~\�\�B#.�뚶���\�(d\�\r\r���W0�r�RZ��\�\�f�7\n\�5�#J�\�\0��2\\��v\�\�\�\��/m*2\�\�b�\�\���(4Z�����\�\�G\r7nH�1n�̸1\n�\�\�\�x\0@G�\�X�>\�_<\'y��s���lb�\�\�_I�G�ђ���>\�\���Q\�\�\�\�\�u\�^�R~j\�Ë�qc}\�9bX\�q\"�orRGT+\�\0:\�`�\�p\r*�JR<\n�j�1\�匂y\�潸bŊ�ׯ��e˖]W7\�>Gl\�P\�\�P��	\�jE@G�\�\�\�א��\�ޟ\�BcD��\\w=-�3g\��s\�\�}����+-\�&��{�ҥK���\�\�r`\�9bHÍ\�]O�:�ZQG\0\�Q+4�\�\�H��?\��Fny\'y�c�b�ٯ\�\�x\�J鉟{��5+\�\�;\�ݎ.uD\�\�G\�9�qk�BC\�.\n�\\F:�\�\�@\�\�80\�\�\',uD���]\�s\��zzz\�\�\��?��}�<[\�[\�d�ƿ�6\\xl��\0\nPhT+\n�a�:�h\�v��\�\���\�\�M(\�jE1:z{{\�\�\�\�Y\�\��y\�ό\�g\��(4��\�&�<�=0�}\��}\�SGT+\�bz{{w\�\�\�Y0H#��,��\�>\0#�ШV\ZC\�3i��]=C\�s?@g\�\�~7n���V\��9\�c6\�\�\��^OOϻ\�\�Ùg��\\s\�5\�\�?�<\�\�3ɪU��_\�\�qy\\\�eo\�\�\��}L`����s����]}\�\�w]t\�E���\�����N:)|N<\�\�_�}\�\�kj��\�\�/�\�iӶ\�\�~�QhT+\n�A\�,\�x��\��_y\�\�ƍ�\�Պ:b\�js��{{{\�\�h�:ujr\�W$/�\�Bڜ\�+��\�\�\�3���\�\�d�@jc\�fw\�qǿ�����>}zr\�e�%,�\Z�\�\�\�w\�;��vڒ\�?�g\�o�PhT+\n�&#�$\�x��\�/0��\��m\�(HQ��#F���\�ղ���p\�%�$\�?��Ɲ��\�\��d�\�\�\�e\��\0,Xp\�\�_�.�b͟??y\�w�\�Ơb�\��\�\�O�\�\�\'��Hww\��d\�\�)4��\�FF:9\�\�#�\���\�\��ڸQ�:�ZQG\�ae\�@�\"VE̚5+;]($\�/�\�\�y+-`���\�\�;\�\�NHn�\��\�׿�uv�\�%n�?\�cޘhg\�UhT+\n��L\nF:\��<PM�����#�uD>qN�\�\�@�M��<�\�\�)¨����ohZ�\�9-`����\�̙3�\�I\'����\�?ώ\r-���>}�[S�N=.�x�J�Q�(4R�NZ�x�V��V\�\�ƍ�\�Պ:\"�\r\'\�XYѮfE]\�\�J�x�\�>&VV�x\�Ɋ+�cB!qӦM[;eʔ\�e�)4��F�I@��P\�q��U\��k\�(HQ��#��\�Kߩ7n�\�\�t�-\�q\ZVY�\�#O��=\���D�b�VVd\�\�\�\�/|\��\'O���\�cw\Z�F�2���\���G(��@���o��#��	^G\�\�\�ӳ�\�8�c�)s\"\�\�}:@\���\�׾\��uqΉv��\�WO�:\��\�\�w\Z�F�2��\�(��N<\�h\�P�\�x�\Z7\nRGT+��\�%VP7\nR\�\�@F*�\�А���&�\�o����4�VO��W\��\�\�\�W\��}��\�I\Z\�\�-4F�\���G��\�g�ާƍ�\�\�\��#r\�\�\�Yo\\q\�\�\�\�\r�,ff\�\�j\�\��y\�y\��2>��\�\�w\�k��\�\�\��\�$\n�je\Z�Y\�\�\�#�\�~�k4ߟƍ�\�\�\��#r�:u\��ku�\�\�fA٫+\�\�q\Zo\�~e\��x\��C�?��\�w\�ɾ\�\�\"\�#�xm\�\�\�\�\�K\�\��{j���<K�Q��\�B#\�km�]܏\�\�#�\��ō\��Ҹ1��c�:�Z�u\�h\�\�z�]x�Q��\�R<~C\�\�\�\�\�\�UW]5��\�.˾\�\�ꢋ.Z\�\�\�}Rv_��a\�\�\0�ШV\�c��\���ю�~�\'��	��\�G\�\�\�\�\�je<\��!\�\�v��\�\�~\��eK�R\�\�7\�\�\�}Ʃ�/�xeY��\�\�}\�݋z\�\�\�e\r�\�&p�F�2����\�*\�\�1\�\�\�_ �v��C\�;��#��\�XG��<�\�\�\�\�9\�\�\�dK�R\�\�\�\�%\��q?�q\�s\�Y�dɒ\�{���zꩥ��\�\�T\� \���B�Z��\� ��A_k���o\�\�#�s��Mk\��ϸ1�A\�\�A\�vuD�2\�\�0\�\�\�\�Z��\\�\�\�Y\��\r��\\\��W\'�x⻯��j\�=\�V�V�Z[H�g\��\���I\�*\Z\�\�{o�f͚�ˇ\�#�<����\�M�g\';z�駛.�B\�c�1\�k+�\��֕�xo�<�U��w\�\����<\�\�{x,2����\��J\Zd,\�&۫VG4\�o��f\���i�z�X�re\�\�\�֭k�l�d<\��a�\�g6\�z}��\�\�Y\��\�}\�\�\�]���\0\�\�QG��\�W�ʾ\�۪\�xk\�:&U*4\�z뭍��\�n�%\�^{m��o��\�u\���\��o}\�[]v\�y祟m߿\�\�+M\�_\�]wݕ�\�=\�I,X\�t\�X\'��tJ&O��l�\�fQD��}_gd\'c�M���\�\�og�\0F]�\�\��6\�Ǎ\�\�i\��.˜��\�7��\�\��[o�5\�v\�m�^x�i�\�I\'��v\�aɝwޙL�6m ��Ǔ�6m\r�hr\�\�\�ot�\�?ܴ\�X%�\�\�\�){>��߰?�\�\Z�8u\�	\'��\�\�ʕ+\�\�Y+,\�\�\�R\��+\�^{\�\�\'�D�\��(-$\���\�\�/�>}���\��\���\�J\�&_�\�\�m>�\�O\'�\�K�\�[l�\\u\�UM���\�_OWZd�\�ǿ�\�\Z\��֕\�/�\�I�Xd({w\r���\�\�\�˾�\�\"Cٻk����A\�\�A\�\�*\��\�˗\'\�}\�{\�����̙3�\�\"��^z)\�z뭓e˖��\�\�\�QO�z\�\�E�Q�K�F�ԧ>�\�\�N�E�\���25\�x�#F\� �Ӧ\�k�d�_^\�|\"\�\n\�Pg�u֚��9{\�\'~\�\�\�ب���R��hѢd�\���w>�`��7�\�\�+_�\�F\��\��w�\�_�\�_ҿ�D�b\�wN�{\�ǐ��\\y\�ɖ[n���\�^7\���F\�\�\�\�,\�\�LZ\�\��6��\�?\�\�\�\�U�#b\�\�G�|\��H�;\�\�\��p�G�vءi\�\�\��\\}\�\�\�5l�\�6銊�\��\�\��\�\�&?�\�O7\�6Vhz\�ɼy\�\�\�\�~�\��/$\���\��\�����\�e�2\�ѐ\�\�\�\�@��{\�O��)!7\�x㬞\��)!Mv�*\Z�\�\�\�~\�\��d�\��ar\���\�\�\�8\�#���\��hn\\x\�љN�L�2а�\��\�\��,\�&���\�\�\����\�Xh\�}�5hW߮�G�\�ȯ]\�C\�\�\�\�U�#\�g�}\�F�=\�\�iM\�xY\�q�\�\�>\��d���J?�\�\�K/M&M��\���ɞ{\�y\�\�\�իn\r�X��A\�\��\�\\\�o�}z\�G?�Ѧ}��\�:b4\�y����\�.�\�o^v\�e\�\�|[�z꩷uww��ݗ�\�ԀݨJ�\�3\�<�\�\�C��x�嗓\���\�O����\�9\�K3�\0��⋛(:\������h�n�3�����v\�\�x�c?�ִ\��h\�Bޱ�Juķ��\�AqG\�eQC\�\�\��\��F\�\�G��\��~\�H�7k\�}\�M\�\�\�\�\�O=�saĹ-����6���Ї>Դ/c�\�XG��<�\�\�\��֛��f,\�\�\�\�%\�+���8U���\�\�\�\�ߩɾ\�ۢ\�8/\����\�\����\�)�Th\�\n�M5,\Z	�_\�a\���7ޘ|\�cX��\'b�\��\�oǚ>\�\�C\�a#U��G&X�1\�E�hO<F{��\�F�}i\�(�J�G��\��\';\�c\�\�ݝ\nĈ\�\��\�\�bu\�Yg��B\Z�.\�<\�́\�aE\��Y\�G��a$q~�C9$=$䩧�J�\�?�c�\�Ǵ쾌U&X1\"���\�z� �\�t\�g�\�а8<���8v\�i�=[\�a!\�]wݏj�ȏ��\�I�Th<�䓛<$\�s\�ضް�\�L\�\�	2\�\�Y1T\�\�\�\�/O>�\�Ϧ\�Ϙ1#�KI��B-+�\�\�\�~4\'��_�\�\Z\�\��q���\�\�ׯO~\��$_�\�\�ߥq�h|<z�L\�\�s\�M��o��8oE�\�\"�\�1�є��Qsd\�;R?�E��3�\Zq��\n��|\�#\�J�\�m\�\"��\�m\�ԩ\�\�\�o\�񜏅x܆f\�۱_\�}Ʊ\�/��}�\��\�׿�u\��?�j\��|mY<eʔ}��\�I�RhD�a\��I �+,n�ᆍN�\�5\��\�\�w\�=�\���\�\\+,\�G���\�3\�\�\�g��i<Nu,3A�\�*\�Gk\�1Z��\�h�O�U���U\��\�w\�ՓѸ�9sfZ\�|\�\�\�j�X\r�x\�\�tۨ!\�_�\�g���r\�)i\�\"N�YoX�]�v\�������[�\�׾�~�H\�\n�8<�*���uDn�\�~f�Yp\�Wd\��R\�\�64,\�\�y\�N=\�\�Gn�\��w��(z\�������\r\"�e�\�T�\�,ѰX�`A\�\�(6\�|\�\��CB\�+,\�P�X\�\'\�j\�>>�$��\��7\��e�feo3���\�h��1\���\0\�1\Z\�W\�FA\�߫c�8V��?|ĉ3ceE��8�4\�1���\"���\�j�XM;p(H��\"\�S\�\�D\�\��\�~{\��~0m�|��\��\�H\�9,v\�u\�䠃JWf\��\�L\�:\"�)S�|�\�,�:uj\�,\�\�z{{��\�\�#\�&O���\�/��?��ϳ\��������6�\�\�\�d�\�T�\�*p@\�裏6]^OG\Z_\�Ў��H��\�\����\\+N�U\�.��\'�|rZ\�d\�#͏��g�\�^Wv&x�Q�\�/:\�(��@���o�U���\�\�qHi40\"q�\�`\�h<D$�x���1q�\��\Z+)V�\\\�t}�2�\�\\j5��z\�\��K.\�N\�*�auł\����w�c�=\��+VdǂB�.]zKm\0yq�t<�Vh4&�gf/,y�Qh*��L<�<.0����U����QG�6�حV\�So\�r\�-\�\�@[\�\�44+މ�\�\�\�a�N�zܴi\�֌\�J�\r++�\�rl\��:�B�ZQh�Z��:\�h\�\�\�h\�}l\�(HQ��#\�\�\�\��^�y�lcEo;\��7\n��\�\'�C\�J���:j\�\�7߼�\�q\�	67��⥉���N�Q�(4�2he\�\�\�\�\0\�\�\��ٸQ�:�ZQG\�s\�1\�lV��Zo ĹH\�մ���=\����x\�x�\�>����O�N�z\�\�\�\�W\�w\�}��\�\�;ٱbP�\�V\\w\�u\�\��ŵ\�\���\�N�ШV\Z\�`���\�?P}#}_7\nRGT+\��j��ֵħ���5kVv�PH\�_\�ʊ\r��uv_�	\">��6\�;\�#^�\�\�\�\�\�[�\�SO=\�\�+��Q3־�\�\�K-Z\���n�\�\�\�N;\�\�ڶ�\�\���ѥ��ШV\ZMF29\�\�c$\��/#y7\nRGT+ꈑ\�\�\��_�M�H��觇\�\�3\'\�L�\�x\�}\0&�ɓ\'�6(�\�\�\�=�\�\����(�>�\�\�c�\�m\'\Z�F��\�T\�IBމG\��Ư�\�s\�FA\�jE1r=��\��\�!�Xmq\�W�� 3�}\�.��\"=$\'�\�\0C�Q�(4��g��g\�\�~�ΐ\��n\�(HQ��#Z\�\�p\"\�l�!9\�\�3�k��&y�ᇓg�y&Y�jUڜ��\�\�<�?\�3�n\�\�\�\0-RhT+\n�M*:i(z{`�)��/z�����V\�\�l�\�\��4Z\�]\nP�B�ZQh�\�\�C��ƿV\���\�nBQGT+\�\�\�\�ۻOO\�\�Z\����\�\�3\�\�\���\n�jE��\�H\'#\�\�<#F������V\��\�c�y_OO\�\�\�\�\���\����gkykCs\"�ƿ�6\\xl��\0\nPhT+\n�\�\�N&\�n\�$I\�K�,�~����\�\�\�K_�Rnj?\�w\�͛\�\�9s\�>?)\�x�w;�\�UK<\�\�\0\�-�F��\��\�&\�]O��,Y2�6YNV�X��[��\�u,\�O�\�\�\�?w\�\�\�k\�Ɓ\�\�!\r7.w=\�jE@GQhT+\n�jr1\�匂XY�\�\�\�W\�O�+�q\��\�s\�&\r5>u9����V\�\0t�F��\�hIv���7�,���\Z�\�6n�\�>G+;Nd�MN\�jE@GQhT+\n��\�\'go�j\�\�FƍjŸ\�2\�\�(0T+\�\0:�B�ZQh�w��;�f�`t\�7\�x�?Y\�Еɢ���&��˲\�I�7Z\�\���\�6\�̸Q@\�\�@ʉ\�\0���ШV\Z\�lhX\�fyƍ���\'\�<?�٭_\�(qY\\�\�^Z�q�u�1\�ɓ\'���g<�\�b<\0��(4��F1\Z\�\�3n<�hVS���\�Ҵ��\�Fkj\�Ť36dR\�z\�\�3Hy1\0\�Q\ZՊB�\r�r\�7��璦FE=q]v{i=ƍ\�\�ꊆ�\�=\�\�\�\'\�x \�\�x\0@GQhT+\n�b4,ʑg\�X8�¦FE=q]v{i=ƍ�ˬ��ʢ�<ご\�\0E�Q�(4�Ѱ(G�qCâ�7F.��\�*�\�R^�\0t�F��\�(Fâyƍ�T�l����.���\�\�\���\�*�\���\0\�(\n�jE�Q��E9\��\��nS����.���\�\�\���\�*�\���\0\�(\n�jE�Q��E9\���?�,�낦fE\\\�e��\�c\�\�/VPҤ\�fR\�v-\�x \�\�x\0@GQhT+\n�b4,ʑw\�x\�\'W75,\�\�vR,ƍ�b\� \r�l����ご\�\0E�Q�(4�Ѱ(G�qc��d\��75,Ⲹ�i{i9ƍb�\�\�\Z��\0\�(\n�jE�Q��G9�7֮^�<}��M͊z\�\�&{;i-ƍb�\�7H�1\0\�Q���\�]�n]\�/<)?�\�\�\�Z��>����G9����_�\�/��<~ǹMM�lb�\�\�j�\�1A)ƸQ�:�:QG\0\�q\�͛\�\�+�~\�I�Y�l\�u�B\��\�sD~&\��a1ܪ��b�E\�hXc\�(FQ��#\0\�8s\�\�9x\�ܹ�\�\�\��\�/$c�\�Ͻ\�ҥ\�֊�\�j90����G9kX\�YU1T\�\����Ѱ(ƸQ�:b죎\0��\�/�\�\�\�\�v�Rz\�\�?EFA&\�\�m�`\�6!F�\��I�\�\�}�\�ϸQ\\��\�\�{L16QG\0\0T��G9�@\�N�e\�\�G\�9\"?\�\0\0\�v&\�а�V4,�1n\0\0\0mg\�Q\r�jEâ\�\0\0\�v&\�а�V4,�1n\0\0\0mg\�Q\r�jEâ\�\0\0\�v&\�а�V4,�1n\0\0\0mg\�Q\r�jEâ\�\0\0\�v&\�а�V4,�1n\0\0\0mg\�Q\r�jEâ\�\0\0\�v&\�а�V4,�1n\0\0\0mg\�Q\r�jEâ\�\0\0\�v&\�а�V4,�1n\0\0\0mg\�Q\r�jEâ\�\0\0\�v&\�а�V4,�1n\0\0\0mg\�QL\�\�wO-���giXT+\Z\�7\0\0��3\�(&~~�\�ƅ�E��aQ�q\0\0h;�b\Z\Z�l\\hXT+\Z\�7\0\0��3\�(f��Š�\r�jEâ\�\0\0\�v&\�Ҩ\�&m\\�U\�\�\�W_\�\�\�\�<\�L\�\�K/5m�̓>\�tY6k׮M.\\8\�\�5k\�$o��f���\�\�7\�\��\�KV�^\�t��\�z�\�vGâ\�\0\0\�v�L��M\�N�\�\�!��|\��_M�\��ג_�\�\�6\�l���\�C6\n�x\�\�=\�yOr\�u\�5]ט��.�\�\�u\�]\�믿�,Y�$�\��iӒ�\�\�/9��\�\�\��y�`��d\�m�Mz\�䬳\�J�?��4GqD�뮻�͎\���3\�\�FF�\�X\0\0@�d\'q\r��g�	Y�ti\�я~4]Uͅw\�1m\�v\'\�\�\�M\�\�w\�\�s\�i�\�\��\�ߤM�8 mrd��\�\�\�g��\�\�\'?�\�d\�wNv\�i�\�\�\�OO�\�~�t�\�v\�-�?~���3\���2eJr\�-�$���\�ꌸnΜ9ɞ{\�\�\�\�\0\0\0t�\�\ZucѰ8餓�����JW1D#\�\�C�.\Z�&MJ\�\�ލns\�&\�W��[�.�\�{쑮�\�\�\�s\�=�6 \�x\�d\�̙i�bٲe\�\�\�\�\��\�\�\�p\�\r\���\�?p�Hc�a�\�^{5]\�\�hX\0\0\0\�цkTԕݰ�&�\��\�\�$\�w\\��\"���bĪ�h2Է�U\'�pB���~�\\~�\�ɖ[n�6=f͚�\�cy\�\�e�]�>F|�CB�aq饗&�\�~��v7\�t\�F\r�\�hX\0\0\0@ר�+�a�jժ\�\���\�\�\�>\���\���_~y�C<buD*\�\�ח��8\�\�\Z\Z\�l�\�V\�\��\�\�%x`\�o�\�o\�\�bUƱ\��6BⰑ\�&\Z\�\���>�<��\�\�\�ɓ\'\Z\Z\0\0\0PAe7,␎�.�(9\�S�;\�3m:\�y$\Z\'\�<�裓�+W����\�ӕ�\�DC\�\�SOm��k��&9蠃Ҧ\�\�\�ӓv\�!Y�x\��9,\�\�8T$��\�VhX\0\0\0@�ݰx�駓\��\�\�N\�q\�W�̈́�\�\�\'bE\�m�\�\�t���\�\�>�\�\�eqN�Hv\�hn\�ω\�\�}\�ݓ\�˗\'[o�u�\�\�ON.�\��\�z+,\0\0\0��\�nXD\�\�\����J\�瞴Q�\�\��뿦��Է�ġ#�5,.��\���\�H\�cJc%Ewww\�\�|&mTl��\�\�\�<�\�b�\�#Lc[\r\0\0\0����-J����:9\�Ò/�\�\�v\�m�����\�{\���5k֤�z4�k\"VX|\��\�萐8D��R�1qR\��h\��>VXD\�\"\ZqHH�\'㥗^\Z\�\�\�oL-i�}|bH�Ǐ\�I\��\�\r\0\0\0\�*�ay\�Gү\��\�wӕO>�dr\��\�\�h<4$\�\�_�\�F\�\�\�o;=_E\��\��q\�\��>Η_�a\�\�n{�\�ק�@����^Hϣ1cƌ�\�\�\r\0\0\0\�\Z��\�H���l$�\�\��\�\��<{Y=�m_F4,\0\0\0�k|4,&R4,\0\0\0�KâjѰ\0\0\0�.\r��E\�\0\0\0�4,�\r\0\0\0\�Ұ�Z4,\0\0\0�KâjѰ\0\0\0�.\r��E\�\0\0\0�4,�\r\0\0\0\�Ұ�Z4,\0\0\0�KâjѰ\0\0\0�.\r��E\�\0\0\0�4,�\r\0\0\0\�Ұ�Z4,\0\0\0�KâjѰ\0\0\0�.\r��E\�\0\0\0�4,�\r\0\0\0\�Ұ�Z4,\0\0\0�KâjѰ\0\0\0�.\r��E\�\0\0\0�4,�\r\0\0\0\�Ұ�Z4,\0\0\0�KâjѰ\0\0\0�.\r��E\�\0\0\0�4,�\r\0\0\0\�Ұ�Z4,\0\0\0�KâjѰ\0\0\0�����w׭[\�4q�\�S{^�={\��\�s\0\0\0μy\�^\\�bE\�\�Y\�ϲeˮ�={\��\�\�\0\0\0&�9s\�<w\�\�\����_�\�blR��\�/]�\�\�ٳg?Wˁ\�\�\0\0\0&��$\�_\�ky;Ρ �\'~\�\�\�׬\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0`b�i�\�\�t*	\0\0\0\0IEND�B`�',1),('1864607718426259458',1,'请假流程（会签）-leave5.bpmn20.xml','1864607718426259457',_binary '<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<definitions xmlns=\"http://www.omg.org/spec/BPMN/20100524/MODEL\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:bpmndi=\"http://www.omg.org/spec/BPMN/20100524/DI\" xmlns:omgdc=\"http://www.omg.org/spec/DD/20100524/DC\" xmlns:bioc=\"http://bpmn.io/schema/bpmn/biocolor/1.0\" xmlns:flowable=\"http://flowable.org/bpmn\" xmlns:di=\"http://www.omg.org/spec/DD/20100524/DI\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" targetNamespace=\"http://www.flowable.org/processdef\">\n  <process id=\"leave5\" name=\"请假流程（会签）\">\n    <startEvent id=\"startNode1\" name=\"开始\">\n      <outgoing>Flow_1a01nzj</outgoing>\n    </startEvent>\n    <userTask id=\"Activity_0x6b71j\" name=\"申请人\" flowable:formKey=\"static:1\">\n      <incoming>Flow_1a01nzj</incoming>\n      <outgoing>Flow_1wdq20a</outgoing>\n    </userTask>\n    <sequenceFlow id=\"Flow_1a01nzj\" sourceRef=\"startNode1\" targetRef=\"Activity_0x6b71j\" />\n    <userTask id=\"Activity_0dvsmdc\" name=\"串行会签\" flowable:assignee=\"${user}\">\n      <incoming>Flow_1wdq20a</incoming>\n      <outgoing>Flow_1gbyvno</outgoing>\n      <multiInstanceLoopCharacteristics isSequential=\"true\" flowable:collection=\"userList\" flowable:elementVariable=\"user\" />\n    </userTask>\n    <sequenceFlow id=\"Flow_1wdq20a\" sourceRef=\"Activity_0x6b71j\" targetRef=\"Activity_0dvsmdc\" />\n    <userTask id=\"Activity_194idnn\" name=\"并行会签\" flowable:assignee=\"${user}\">\n      <incoming>Flow_1gbyvno</incoming>\n      <outgoing>Flow_06trc15</outgoing>\n      <multiInstanceLoopCharacteristics flowable:collection=\"userList2\" flowable:elementVariable=\"user\" />\n    </userTask>\n    <sequenceFlow id=\"Flow_1gbyvno\" sourceRef=\"Activity_0dvsmdc\" targetRef=\"Activity_194idnn\" />\n    <userTask id=\"Activity_1o4clkg\" name=\"总经理\" flowable:assignee=\"1\">\n      <extensionElements />\n      <incoming>Flow_06trc15</incoming>\n      <outgoing>Flow_1jhko1g</outgoing>\n    </userTask>\n    <sequenceFlow id=\"Flow_06trc15\" sourceRef=\"Activity_194idnn\" targetRef=\"Activity_1o4clkg\" />\n    <endEvent id=\"Event_1it04q9\">\n      <incoming>Flow_1jhko1g</incoming>\n    </endEvent>\n    <sequenceFlow id=\"Flow_1jhko1g\" sourceRef=\"Activity_1o4clkg\" targetRef=\"Event_1it04q9\" />\n  </process>\n  <bpmndi:BPMNDiagram id=\"BPMNDiagram_flow\">\n    <bpmndi:BPMNPlane id=\"BPMNPlane_flow\" bpmnElement=\"leave5\">\n      <bpmndi:BPMNShape id=\"BPMNShape_startNode1\" bpmnElement=\"startNode1\" bioc:stroke=\"\">\n        <omgdc:Bounds x=\"240\" y=\"200\" width=\"30\" height=\"30\" />\n        <bpmndi:BPMNLabel>\n          <omgdc:Bounds x=\"242\" y=\"237\" width=\"23\" height=\"14\" />\n        </bpmndi:BPMNLabel>\n      </bpmndi:BPMNShape>\n      <bpmndi:BPMNShape id=\"Activity_0x6b71j_di\" bpmnElement=\"Activity_0x6b71j\">\n        <omgdc:Bounds x=\"320\" y=\"175\" width=\"100\" height=\"80\" />\n        <bpmndi:BPMNLabel />\n      </bpmndi:BPMNShape>\n      <bpmndi:BPMNShape id=\"Activity_0dvsmdc_di\" bpmnElement=\"Activity_0dvsmdc\">\n        <omgdc:Bounds x=\"470\" y=\"175\" width=\"100\" height=\"80\" />\n        <bpmndi:BPMNLabel />\n      </bpmndi:BPMNShape>\n      <bpmndi:BPMNShape id=\"Activity_194idnn_di\" bpmnElement=\"Activity_194idnn\">\n        <omgdc:Bounds x=\"620\" y=\"175\" width=\"100\" height=\"80\" />\n        <bpmndi:BPMNLabel />\n      </bpmndi:BPMNShape>\n      <bpmndi:BPMNShape id=\"Activity_1o4clkg_di\" bpmnElement=\"Activity_1o4clkg\">\n        <omgdc:Bounds x=\"770\" y=\"175\" width=\"100\" height=\"80\" />\n        <bpmndi:BPMNLabel />\n      </bpmndi:BPMNShape>\n      <bpmndi:BPMNShape id=\"Event_1it04q9_di\" bpmnElement=\"Event_1it04q9\">\n        <omgdc:Bounds x=\"922\" y=\"197\" width=\"36\" height=\"36\" />\n      </bpmndi:BPMNShape>\n      <bpmndi:BPMNEdge id=\"Flow_1a01nzj_di\" bpmnElement=\"Flow_1a01nzj\">\n        <di:waypoint x=\"270\" y=\"215\" />\n        <di:waypoint x=\"320\" y=\"215\" />\n      </bpmndi:BPMNEdge>\n      <bpmndi:BPMNEdge id=\"Flow_1wdq20a_di\" bpmnElement=\"Flow_1wdq20a\">\n        <di:waypoint x=\"420\" y=\"215\" />\n        <di:waypoint x=\"470\" y=\"215\" />\n      </bpmndi:BPMNEdge>\n      <bpmndi:BPMNEdge id=\"Flow_1gbyvno_di\" bpmnElement=\"Flow_1gbyvno\">\n        <di:waypoint x=\"570\" y=\"215\" />\n        <di:waypoint x=\"620\" y=\"215\" />\n      </bpmndi:BPMNEdge>\n      <bpmndi:BPMNEdge id=\"Flow_06trc15_di\" bpmnElement=\"Flow_06trc15\">\n        <di:waypoint x=\"720\" y=\"215\" />\n        <di:waypoint x=\"770\" y=\"215\" />\n      </bpmndi:BPMNEdge>\n      <bpmndi:BPMNEdge id=\"Flow_1jhko1g_di\" bpmnElement=\"Flow_1jhko1g\">\n        <di:waypoint x=\"870\" y=\"215\" />\n        <di:waypoint x=\"922\" y=\"215\" />\n      </bpmndi:BPMNEdge>\n    </bpmndi:BPMNPlane>\n  </bpmndi:BPMNDiagram>\n</definitions>\n',0),('1864607719726493698',1,'请假流程（会签）-leave5.leave5.png','1864607718426259457',_binary '�PNG\r\n\Z\n\0\0\0\rIHDR\0\0\�\0\0	\0\0\0Hy:\0\0�IDATx^\�\�	�u�\�\���by\�by���w\�}\�Qޚ�\�\"�@���@P��<\�\��(ޠQ|j��D\�qDA\r\�!\�\�!�\�\�׿~\�\�v\�\�\�w>��oe3\�;3d�\���\�==�\�\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0���(��\\t\�E�s\�)��{�\�\�\�\�~\�;p\�\��O8ᄫV�X\�\�\�\�Vz5�\�+i\"z%M�����ʡ\�\�\�/�b͚5\�ڵk�\�o�]��w��\�?�\�rPx[�3j#���\�4��&\�\�^\�R\�y�RJY\�\�z\�\�k\��\�\�\�g\�Fz�O\�J��^I\�R�\0h�8�\�+\�y$~\�`p[�3j#�\�\'z%MD���t�W\0�T�\�\'�%ӗ�y�?�6ҫ��W\�D\�J�HWz@K�w0�\�\�\�E���8��OU��\�t;�Z�2\�U^\�+�j\"z�WM�+���\�3\�|��Ūc\�+�������,�K��ɧ+��^\��ҫ&�Wz\�D�\�+\0Zj<��\�g\�7Թ\�\�}\�\�\�ӕ�@�\�^\�U\�+�j\"]\�\0-5��\�o�?�o �ץ\�\�\�ӕ�@�\�^\�U\�+�j\"]\�\0-5��`\�\�\�\�\�\�u\�\�2�te0Ы��Wz\�D\�J��HWz@K\�JW�\�+z�WMD�\���t�W\0�\�x�8[g:ԉ\�\�\�e\�\�\�`�WyE�\���\�^5��\�\n��\Z\�`p�I_\�\�\�u\�\�2�te0Ы��Wz\�D\�J��HWz@K�g0�\�ʿ�~�ɾ� .�\�\�\�e\�\�\�`�WyE�\���\�^5��\�\n��\Z\�`��\�\����,\�N���z�W\�J���^\�U\�J�\0h�q\r�\�V\\p\�7���,�\�\�^&��z�W\�J���^\�U\�J�\0h�{\Zn��\�\���\�\n\�\�u�M�}2�te0Ы��Wz\�D\�J��HWz@K�9\�v[���\�_�٧oH\�Ķ^E�z�2\�U^\�+�j\"z�WM�+���F\�\�\�\�\�U\���+��^\��\�^M=z\���z�\�+\0Zj��`<�����\�\�\�d�\�\�`�WyE�F�^M-z5z\�jj\�J�\0h�\��\���D�ޞ�?]\�*�\�\�\�IoO\��\Z;\�\�\��ӕ^\�R�\r2}\�\�`�WyE���\�4��\�j�\�̙\�Y�f\�\Z\Z�\�\�\�\�e..sk�bݟ\�\�\�뮟ۧ��\�JW�\�+z%MD���t�W5{\�\�\�\�2��[�7�����\�6��A^\�\�`�WyE���\�4��\�j�ʅ\�\�w\�(\�\�de\�^z\0L�� �te0Ы��W\�D\�J�HWzuO\�̙�\�\�\�\�7\�E\�\�\�Bw\�\�w//^\\�q\�Ņ^X\\w\�uE�?\�\�qy\\ۥ\�����\�\'��\�9\�N9\�\�v\�a�]�p\�{\�\�ڝw޹zB�?�{\�\�M\�e_�h\�\�Ν�E��]f0\�+]\�*�\�4��&ҕ^mH9n1{\�\�?\�.l����C9��\�+�\�\�x\�\�\�}\��\�B����\���M\\���\�1\�\�\��\�\�\�\�\�\�\�+>�\�b\�ʕ��\Z�\�\�;\�\�],�h\�w|sz{]d0\�+]\�*�\�4��&ҕ^��\\?�̕��\�<���\�\�\�_�NP|\�N�H�<\�/}�&�\\\�~hѢEk\�\�SN9��뮻\�\�Q\�v����\�z\�.�\�򧡡����\�%��ҕ�@�\�^I\�+i\"]\�\�h\�\�9Y\�^ߣ�:*E�$n/ٛ|�=ɰ�+��\�\�c�]�\�N;˖-+\�\�\�\��c\\\��\��\�̙sK�\�h0\�+]\�*�\�4��&ҕ^�\�=���Uϝ;�8\�\�3\�\�s��ۍ\�\�Y$�\�=ɰ�*��sɒ%\�y睋s\�=7}�����y\�\�\�:<<�Cz]`0\�+]\�*�\�4��&ҕ^�֝�kd\�qS�\�Z\�~\�\��\�1��\�s<��b͚5\�\�Ĕ\�\�͝;\�歷\���\�}��� �te0Ы��W\�D\�J�HWz\�k\�G9\�U/V�.]������\�ً|����M\�\�\'���Xo�=ǩ\�v\�\�n�\���9s\�S\��n3�A^\�\�`�WyE���\�4��\��׬�\�9�i\rRr⮕\�c:����8`m�g�IGy\�\�\�\�\�Ǥ\�\�f��ҕ�@�\�^I\�+i\"]\�U-�>\�=�z�g�����\�C��x4$0���>��8[\�dO\�5^q�\�\�ͻ�|ry]�\�\�`�W�2\�U^\�+i\"z%M�+����\�%\�\�4>�x:\��\�\�E^�>F�c\��\��k\�}\��w|4\� �x\�7�O.��>��2䕮z�W\�J��^I\�J�\�\�\�\�C˙\�\�zq:\�ǵ�ߞ\�\�\�\�\�\nt\�\�\'���w\�qܟs<Uq?\�l�\�\r3g\�\�<},9)�\0_\�\�\�)�A^\�}0ЫvF���\�4�\�{UO�\�\�g\�\�8\�q:\��\�,�g��\�C=\�w|p�<Ш��\Z\Z\Z\�9},9\�y\�\��� �\�>\�U;�W\�D\�J�H��_\�\�\�\���\�?N\�Ɂ��\�y\���\�E�]3�ëk\�w\�ٳ2Gϓ\���\ry%\��@�\���&�W\�Dr\�Um<��={\����\�O?=\'*\�~,\�z\'\�1{\��\�ڋ.�(}h\�y\�wI�sZ�Xr2\�\��O\�����`0J�\��\�+i\"z%M$\�^\�F\�U_�\�?/�/\�����\�y��%�9@�̟?�\��\�\�\�@���\�\'�+\�ǒ�Q��\�TO\�9�\���n��\�\�\�?���\�\��.O\'�8��\��.\�!��\�(M��ޜy\�}���3\�8�\�ȵ\�^\�w\�=}O�Dz�x\�\�W\\pA\�v��^M=\�{\�z��1\�6u\�\�\�T:ѫ��Gi��W��\�g�����\��ܷM��]v\�5\�\�]�v\�ھ\�ڒ\�{U�Oi�_���>\�Y5\�_?�ٳgߔ�\�\0�\��\�w\�qG�<Ш\��n\Z剰�\�i0�\�\�[\����[nY�\�\'?���\�[ֻ\�[\��V\�/}i�\�\�\�w\�\��\�\�\r\r���\�o�{\�\�^\�ʕ+���\�?��f:z\�;ƀy\�{߻�\��\�w]oV�XQ<\��-\�m\Z\�\�\�/\�Ν[\�=\�yO\�\�\'?�z1%��_�\�\�m�}\�\�}\�\�Y�`A\�\�w���:ˡ�Z<\�)O��\ru���?��f:zUg\�=\�,>�яV_\�0�Ë\�˗\�m\�T�\"z\�L��W�e��\�+��\�/V_�\�7�)�\��\'W\\qE\�v��w޹x\�{\�[{\�#]�<\�\�(V�Zշ},�cQ�z\�\�\�.\�4�J�\�\�J=��\��{ϝ\�<\rt\�N;\�tǠ_��\�k�>��{�?+\�C\�\�U\�W��\�E��<\�\�<��\�_�\�\ro�Ϡ\�6^1�\�}\�[\r��\��m^\�/~\�\�\��U\�cz�8\��\�Q\�\�N�ҧlzu\�\�W?�\�̙S\�\�>\���\�gV]\�\�>ė�\�e\�ҥK�\�n���{�\"M�X�<\�Y\�*v\�a�j��^��J,�\�\��7CCC\�\���\�5�yM\�җ��\�\�\���⠃\���&�WSO쭽��\�_�\�-\�b�wa\�T�\��ѫ��O\�\�j�\\y\�\��\�w�ꅘ\��\�}Ջ.�\�\���G�\�[�^zi\�br|_\\}�\��?<�]<\�տW\�w\�s��\�\�/x�\�e�G<\�\��N̽W�Qz\�ׯ\�S[_>\�Y5e2lB\�\�c�����s\�9\�\�Y\�{\�zA-�� \��<�\�O�{�\"?\�\�S��?\��\���\�\'\�\��׿�u\�\��\��j���\��\�b���|嘇g�\�?�\��Ğ\�\��\�N\�Aν�ꪫ��k|{I�\��\\�\��\�f,2b\�\�\�\��\�3��L\�\��Y��o(�A�~�擟�d\�ַ��o\�\�G>\�\���\�\�}\�;ߩn;�t�Ƿ�\���\��\�\�\�\�\�\�_�bdQ/Fc�\�>\��*��8\�\�\�TD�&.\�^��82j�m�y\�$~�я\�<\�9}G	l��\�\�a�V����O|b�\��G?�Q\��>�8묳\�\�6:G1�p\�	\�vq�\�m�]\��K��|\��\�\�X�+�\��6�~\�\�d`:\�\�>\��,\�Gq\�Q�\�s\�\'\�^9\rq\�W,j\�~�~\���w�\�]\�ױw8��\�b\�M���\���\�\�b뭷Y \�\�\�{\�\�m\���|�ӟ^\�\�rJ\�Aν���m�\�Ȃ#\� \���:^=\�\�>�B8\�\�#�g?�\�\�\�{\�\�ቑ�=\�i\�텉=9\�Z�(�\�\�\�!���X�dIu\�m\�\�\�\�\�o|cu�\�~\��\�\�\�%�\\\�\�؛�^M>\�\��X�\�9^�\�W��|\�\�\�\�^\���m�\�Tl�W��s�\�J�\��\�\�^v\�9\�T�\�z/�߃\�|]xԣU�\'>� x\�k_[�t\�Iū^\��\��\�q�\�׏|O,�cot��\�V[U/\ZFW\�8�?},ӕ\�{UO��\�\Z�.�\�?�p��\�s�\�|\�\�\�`p\�npr���=\�q�\�\�\��\�\�x}\�̙#��-Z\�w�ubH�\�_\��\�]7\�\�}0ȹW�Ї�EC,4\�C�\�\�x?h\�\�qҥG>\�Uwb\�L��9\�ޓ�\�a��\'\�+_�Juc:\�{;�\�0�\�ŋ\�\�G\�\�{j�\�\�˖-�\�(�u\�� �WS\�X\�\�Q݉&:׫\�iC�\�|�\�_u��E\�^�ㅓx~��q�\�\�[��P�v^��\�\�sL\�׿��z�%\�\�\�M�\�v���w\�\�\���\�2]ɽW�\�\�k�\�A�C�ľży\�\�*�\��(\�\�\�\�5\�\0�y�X\�(�� \�\r-�{��Ch\�\�i�8\�\�O}\�\�b(\�\�\�\�\�\�\��D�v\�i\�a\�9�\�G\�2ܓ\��w�CP\�{z�u�_�\�\�F�\�\r�=-�\�\�z{\�\"\�b樣��\�to`�w�\�\�\��\�_F�\�\��F�(�Crc�8\�\\�=�\�x,�~x\�\�o2z5�\�\�\"c��a\�{\�\�\�w�\�\�\"5��F� {��\�TD��1\�^m(��\�g�\��\�\��q$L�\�_\�!׽\�ŋ/{\�G\���8*j\�\�w9G$\�\�/ׇe\�6\�\�x�%^p��\���\�\�\�ѐ>�\�JWz\�yqF�(��\�t\�m�\�\�\�Y \�H+\�1,�xP�Y�ȏ\�\'��MC[\�4�\�o\�\�!\�{\��\�ȶ\�9\�g�f���.Ic-���\�o�x\�+���\�W���磡��\�ѫX GO�/\�\�\��{f\�m\��\�\�\�\�\��\�=.f\"q\�l�p\�\r7T\�?\�g�}����\��㏯\�؃�\�\�\�\��\�w�\�⅜\�\�\'Y�/T\�j\�\�\�\�ϲ~��CX\�C��\�TD��5�\Z-ѳ\�}\�{\�\�>\��\�\�6\�6w��~\�i\�x\�q��\�8\� �qDU<祷�߃\'\�Et��S\�A���\�B\�\�LG�ҫ0<<�\�rf��^�\�\�h:\��\�,�o�Ǖ>V�c\�\'�7\�\�w\�y\�\�s\�FU\��\�\�\�[o�\�\�\�\�\�V��{\�Tݻ9>�\��Zq�X��*y�\�O:�=\�q��8Ĭ\�3%cȍ��\�}Zә�\�ѫ8qQݗ�o�y�=\�qB�\�>9>.l<��:q\�N;\�T}\'Ép.�첑\�c�!6���\�ę�ㅜ8ANt5�\�\�6��z\��\��W�\��:Nʕ.�\�l\�NE\��Y\�٫\�\�^\�8\�%�n��r�=~�ŋ\"��8zT\�u���\�n�\�V�\�$\�ѡz�σ\�!\�\�{/~7\�Qp@u\�\�\�=\�\�bM./w�W�rn\\R/N9\�t���ߞr\�\�\�6�r �Ӳe\�\�N�6��?��\���|bY�\�w�\�2��X �\�Y\�\�\��x@5 \�\�Z\�ىC�\�0�8\�I\�\��w\'��\��7\�x>NP�~\�t�+�A�\�<\�Ġ\��^�&\�z1�i\�y\�CR\��w�4\�Dpq\�ׯ~\��E�\\P|\�S��a�Cm\�M$\��1\�\�1ľ\�-o�.w2�ɛ�^\�\�4�^�ϛ�/��z\���ni�S�\����Wi\�\�{(^(�C\��c\�u\�\��x�q�u�o�=ޒ��C\�\�C�\�}\�q\�}|/�\�0G}tթ\�c��S��\��\��x�x(�\�J۠ӕ^\�\��\�\�X/N����9\�o\�\�\�#�W\�\�I#\�Q3g\�|j�$s�$�	\�/�C\�\'�\�q?\�}�Y��X�CG\�\�W\'\�Gơ\���ǫ�\�=zq8m�Ԥ\�.�w\�e�j\0Io#�k�Ҋ�{�\�\r:]��Wq���\�\�\�4_��\�G\�{g���Mt%\�.g�\�b\�\��\�\�㎫�\�\�\�g�mb���\�p\Z{ff̘Q��\�o���*\�\�Y�\�j~z�MF�&�xΈCX\�\�\�\�\�g�\�\�G6v�\"zլA\�\�}��ł9G�\�\�\�C�G�h�\�\�sb�{�\�\��\���ҕ^\�*�?_Y/P\�\�\�)\�g\�\�\�\��7{\�쭶\�~�[\�&7�K.�di��rU_u\�m0\�M}\�\�{\�x�kC�2Lw�bpL\�<Z�\�[\'kJ?\�v��W�K��\�Us\�ЫM)]\�U�r>ݲ�#\��K�.MG\�F\��\�,�\�Ǒ>6`0<<�\�ܹso\�X{�\�\�9��\�\�\�}u�� �te0Ы��W\�D\�J�HWz�\Z\Z\Z�F�X��\�\��&\�\�\�Z\��>&`{z�\�v\�\�<\�\�\�\'{\�8!׺\����{�k��ҕ�@�\�^I\�+i\"]\�UjΜ9��3\�Y\��5\��\�\�\"9n����=\�q�q�\�c61CCCO>v޼yלx\�7�\�s�\�\�\�~�\�\�\'�\���\�v�\�JW�\�+z%MD���t�W�)\�\�-\�\�\'��\�I�\�Eߘ\�\�z\����-\�\�l\�\�#�\�\'��\�f�\�\���._�|\�y\�wٵ\�^{K�<r\�\�W_}\�\�g�}��~\��#,Xpt�\���}�>\�iCy�+��^\���&�W\�D�ҫ���\�g\�.�#q\"���\�:�?9!W�8��K@e\�̙��O󇆆�*�<�\�\�)�<u\�\�\�c�\�{�\�`�W�2\�U^\�+i\"z%M�+�ڐY��\'y\�p\�H\�M�3\�\�\�\'\"��\�K\�\ZW�U\���\�\r�=0䕮z�W\�J��^I\�J�\�I�\'x݉�҅m��\��/.\�8\�\�\�殻\�j1\�\�\�\�~�\�v\��޸��]\�9�$�A^\�\�`�WyE���\�4��\�j�\�}\�\�\�$O1+}��\�JW�\�+z%MD���t�WU.l�*�K\�\�>\�\�wC�\�\�\���	�$\�JW�\�+z%MD���t�W�5gΜ��\�CCC�]���\�\�en]��?\�\�\�\�]?#�Oo�)0䕮z�W\�J��^I\�J�\0h)�A^\�\�`�WyE���\�4��\�\n��2䕮z�W\�J��^I\�J�\0h)�A^\�\�`�WyE���\�4��\�\n��2䕮m\�\�-�\�R�Z�j\�Ko+\�\�\�\�S\�*\�\�D/\�9z%M�+���y�+�A�z��\�!O8\�}\�m\�\Z�\Z|\�^�=�\�\�9G���t�W\0��� �te0hS�\�An�6\�j�\�D/\�9z%M�+���y�+��^\��\Z|\�Z\�N\�\�W\�D�\�+\0Z\�`�W�2\�U^ѫ�g�\�D/\�9z%M�+���y�+��^\��\Z|\�Z\�N\�\�W\�D�\�+\0Z\�`�W�2��Wރ\�m\�\�Xމ^�s\�J�HWz@K\�JW�6\��^�L42�\�\�^�=�\�\�9G���t�W\0��� �te0hS�\�An�6\�j�\�D/\�9z%M�+���y�+��^\��\Z|\�Z\�N\�\�W\�D�\�+\0Z\�`�W�2\�U^ѫ�g�\�D/\�9z%M�+���y�+��^\��\Z|\�Z\�N\�\�W\�D�\�+\0Z\�`�W�2��Wރ\�m\�\�Xމ^�s\�J�HWz@K-_��\�k\�\�������9\\U��?�6jS�\�\�DӖ��^MO\�Z\�N\�\�\\�W\�D�\�+\0Z\�N�j͚5}��d\�\�\�K/�SӟQ��W]߃�Wӓ�WiO&zy�\�+i\"]\�\0-�bŊw�\�7�^��Z��OO\�\�՗\\r\�Oʡ\�2oKFm�W\���&�W\�D�\�+\0Z,~\�+�en/S��G��w��N\r\�߳\�K��\'z%MD���t�W\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0@G�����\�\�\0\0\0\0IEND�B`�',1),('1864607720812818434',1,'请假流程（普通流程）-leave1.bpmn20.xml','1864607720812818433',_binary '<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<definitions xmlns=\"http://www.omg.org/spec/BPMN/20100524/MODEL\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:bpmndi=\"http://www.omg.org/spec/BPMN/20100524/DI\" xmlns:omgdc=\"http://www.omg.org/spec/DD/20100524/DC\" xmlns:bioc=\"http://bpmn.io/schema/bpmn/biocolor/1.0\" xmlns:flowable=\"http://flowable.org/bpmn\" xmlns:di=\"http://www.omg.org/spec/DD/20100524/DI\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" targetNamespace=\"http://www.flowable.org/processdef\">\n  <process id=\"leave1\" name=\"请假流程（普通流程）\">\n    <startEvent id=\"startNode1\" name=\"开始\">\n      <outgoing>Flow_1f4xioj</outgoing>\n    </startEvent>\n    <userTask id=\"Activity_14633hx\" name=\"申请人\" flowable:formKey=\"static:1\">\n      <incoming>Flow_1f4xioj</incoming>\n      <outgoing>Flow_0cy98fl</outgoing>\n    </userTask>\n    <sequenceFlow id=\"Flow_1f4xioj\" sourceRef=\"startNode1\" targetRef=\"Activity_14633hx\" />\n    <userTask id=\"Activity_0lym9dc\" name=\"组长\" flowable:candidateUsers=\"1,3\">\n      <extensionElements />\n      <incoming>Flow_0cy98fl</incoming>\n      <outgoing>Flow_1o16t5v</outgoing>\n    </userTask>\n    <sequenceFlow id=\"Flow_0cy98fl\" sourceRef=\"Activity_14633hx\" targetRef=\"Activity_0lym9dc\" />\n    <userTask id=\"Activity_1j25s1c\" name=\"部门领导\" flowable:assignee=\"1\">\n      <extensionElements />\n      <incoming>Flow_1o16t5v</incoming>\n      <outgoing>Flow_0s1t2f2</outgoing>\n    </userTask>\n    <sequenceFlow id=\"Flow_1o16t5v\" sourceRef=\"Activity_0lym9dc\" targetRef=\"Activity_1j25s1c\" />\n    <endEvent id=\"Event_1jib7oq\">\n      <incoming>Flow_0s1t2f2</incoming>\n    </endEvent>\n    <sequenceFlow id=\"Flow_0s1t2f2\" sourceRef=\"Activity_1j25s1c\" targetRef=\"Event_1jib7oq\" />\n  </process>\n  <bpmndi:BPMNDiagram id=\"BPMNDiagram_flow\">\n    <bpmndi:BPMNPlane id=\"BPMNPlane_flow\" bpmnElement=\"leave1\">\n      <bpmndi:BPMNShape id=\"BPMNShape_startNode1\" bpmnElement=\"startNode1\" bioc:stroke=\"\">\n        <omgdc:Bounds x=\"240\" y=\"200\" width=\"30\" height=\"30\" />\n        <bpmndi:BPMNLabel>\n          <omgdc:Bounds x=\"242\" y=\"237\" width=\"23\" height=\"14\" />\n        </bpmndi:BPMNLabel>\n      </bpmndi:BPMNShape>\n      <bpmndi:BPMNShape id=\"Activity_14633hx_di\" bpmnElement=\"Activity_14633hx\">\n        <omgdc:Bounds x=\"320\" y=\"175\" width=\"100\" height=\"80\" />\n        <bpmndi:BPMNLabel />\n      </bpmndi:BPMNShape>\n      <bpmndi:BPMNShape id=\"Activity_0lym9dc_di\" bpmnElement=\"Activity_0lym9dc\">\n        <omgdc:Bounds x=\"470\" y=\"175\" width=\"100\" height=\"80\" />\n        <bpmndi:BPMNLabel />\n      </bpmndi:BPMNShape>\n      <bpmndi:BPMNShape id=\"Activity_1j25s1c_di\" bpmnElement=\"Activity_1j25s1c\">\n        <omgdc:Bounds x=\"620\" y=\"175\" width=\"100\" height=\"80\" />\n        <bpmndi:BPMNLabel />\n      </bpmndi:BPMNShape>\n      <bpmndi:BPMNShape id=\"Event_1jib7oq_di\" bpmnElement=\"Event_1jib7oq\">\n        <omgdc:Bounds x=\"772\" y=\"197\" width=\"36\" height=\"36\" />\n      </bpmndi:BPMNShape>\n      <bpmndi:BPMNEdge id=\"Flow_1f4xioj_di\" bpmnElement=\"Flow_1f4xioj\">\n        <di:waypoint x=\"270\" y=\"215\" />\n        <di:waypoint x=\"320\" y=\"215\" />\n      </bpmndi:BPMNEdge>\n      <bpmndi:BPMNEdge id=\"Flow_0cy98fl_di\" bpmnElement=\"Flow_0cy98fl\">\n        <di:waypoint x=\"420\" y=\"215\" />\n        <di:waypoint x=\"470\" y=\"215\" />\n      </bpmndi:BPMNEdge>\n      <bpmndi:BPMNEdge id=\"Flow_1o16t5v_di\" bpmnElement=\"Flow_1o16t5v\">\n        <di:waypoint x=\"570\" y=\"215\" />\n        <di:waypoint x=\"620\" y=\"215\" />\n      </bpmndi:BPMNEdge>\n      <bpmndi:BPMNEdge id=\"Flow_0s1t2f2_di\" bpmnElement=\"Flow_0s1t2f2\">\n        <di:waypoint x=\"720\" y=\"215\" />\n        <di:waypoint x=\"772\" y=\"215\" />\n      </bpmndi:BPMNEdge>\n    </bpmndi:BPMNPlane>\n  </bpmndi:BPMNDiagram>\n</definitions>\n',0),('1864607721265803266',1,'请假流程（普通流程）-leave1.leave1.png','1864607720812818433',_binary '�PNG\r\n\Z\n\0\0\0\rIHDR\0\02\0\0	\0\0\0Oj\0\0\Z�IDATx^\�\�	�e�7\�{�\�\'V��{\�z\�}W\�Q�RT\\H&�J$H\�eS7(\�V	^��\�--�\�-P\�ܐ�!ȾC$�J�,�\�\�7=u�g�\�\�\�o~��\�\�9}�;3O�<oo\�_�\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0�P\���\�\���\��s��\�g�!\�\�\��ĢE�\�\\�p\�ͿOW������6�c]\�QeS\��\�M�X�|y�jժb\�\�\�2Ή\�{��\�>�\��\�Fa�\�ߨ�\�\�\�G]Iɱ�\0\�سoJ\�7+�,[�lE\�\\\��u��J\'\�J\�HNu@G\�\�\�l���;���#ͿQ��t����\�TW\0tT�\�\�|���K�=��.RWiE]Iɥ�\0\�~��\�[V\�x\�1\�\�3�o��>kN\'\�\\\Zu�Vԕ�j#�\�\0\�Oc\�\�wW�vPq�_�6,\�X<ל^ƞ\\\Zu�Vԕ�j#�\�\0\�Ocp\�\�G4un_z҈\�e\�ɥ1PWiE]��6�K]\�Q�4;\�\rA�x�9��=�4\�*��+u\�Fr�+\0:��\�\�\�3\�ԉ\�\�\�ؓKc��Ҋ�RWm$����4i%�\�@]�u��\�H.u@G\�\�\�]�\rA�x�9��=�4\�*��+u\�Fr�+\0:��\�\���Ɉ��N<ל^ƞ\\\Zu�Vԕ�j#�\�\0\�Oc�\�W���MA<\�5���\'�\�@]�u��\�H.u@G\�\�Dn�\��A<֜N\�/�4\�*��+u\�Fr�+\0:��\�\��G�\��tDc�\�s#��1\'�\�@]�u��\�H.u@G=Yc��}\��1�)�\���\�\r�\\\Zu�Vԕ�j#�\�\0�\�\�\��G�e\�-,�8\�[#��fb��\�\�\�\�O.���J+\�J]��\\\�\n��\Z�1x���k���\�\\\Zu�V\�\�Ȩ�\�O.u@G�\�\��Wsm�\�6\�\'�\'�\�@]�u5z\�\��%����Fk�o\�O5\��I�ɥ1PWiE]�=\��I�ɥ�\0\�\�\Z��\�\��������\�RWc5mڴM�L��\�����_���\�\�e�5_\�\�k�\�1�o\��\��1H+�4\�*��+i#�\�\�S5u\�\�mˁɼ2�\�Z�ML?/^ߜ\'\0c�1H+�4\�*��+i#�\�U�\�\�V\�@d\�(��dI̯�\0��AZɥ1PWiE]Iɥ��̴i\�68�|<\��\�\�Ŝ9s��/���\���+W!�\�\�\�x<\�5_\��\���\�RnS�\��\�\���q\�w�!��\�\�_���Y�fU��3g>����?P>v�\�ٳ�s�\�\�[4_�3�AZɥ1PWiE]Iɥ�֥\���:u\�\�����\�裏.n��\�j\�ү�>^�oh.�\�4�\rl\�\�\�\�&��z\�t\�A��1cFq\�QGK�,u�I<\�\�뮏\�\�^7\�\�n\�5\�#�AZɥ1PWiE]Iɥ�֦��{�;z�vXq\�m�\r�<E\���Oc0s[,���F���\�\�ٳW\�\�\�\�\�?�x�\�ǛےQ\�t1�\�{\��\�{\�q\������\�Ή\� �\�\��������\�RW�Ys$fhGQN<\�\�f˰^b~��3�92�r\�𠣞vڜ\�w߽8�䓋\�{��\�\�K�.^?mڴ�r�È\� �\�\��������\�RWMq\�J\�\�dӧO/.�\�\�f��A\�|c�=��\�\\3�r�\�\�\�\���f\�*���\�\�\�bLb>3f\�xxppp\�\�\�r�1H+�4\�*��+i#�\�UӚ���Ĵ5��\��{�\�\�\�\�l\�H\�̙3�\�˗7�\�%\�7}�\�\'O��Ms�]�1H+�4\�*��+i#�\�U�5�X~�T�t\�I͖�����2��53ld/^�1�\�PGb�\��>�\�.�\�;iҤ-�\�\�2�AZɥ1PWiE]Iɥ�zM\����� <5n\0���n@�\����z衫⚖6͟?�\����S�\�\�2�AZɥ1PWiE]Iɥ�jq\�E\�)e\�{w��*�\�{�Y�g�\0�8\�S�����\�\��~\��g̘qO�qٺ�]�1H+�4\�*��+i#�\�U�<̫\�y/!�\�sTf^s�̔�\��\���g\�2y<�w\�y\���s�\�\�U\Z���Kc��Ҋ��6�K]����\�\�\�\�\�\� b��\�\�b�=�ձ^\�u2�x\�\�\�\��\�n}N\���\�\�\�N\�M�4i\�溤�\�\0�U\�#\�Ǜ4i%\�\�@]u3\�J\�H\�uU맾\�\�w�q�\�D�\�\�fvl�+��c�=\����:��h\�!�r\�����溤�g#�\�\r�\� ��\���nF]II��j�\�W��\�t��\���o�\�*�߳\�G4\�\�\�\�ٳ\��\�\�jg�y\�\�)���ڳ\\\�\\c�VRo\�U7�����^W�~\�k\�ԩ\�\�/�\�\�\����\�\�\�\�ջ�@f8\��U7\�xcs;Ъk��\�\�rsas]R2ʆ{\�\r�\� ��\��RO\�QW\�FR��\�(u5��ʯ�֏�wO\�\�\�Y\�[� \'3g\�|\�\��{s;Ъ�+W>Xn\\\�h�KJF\�`7Sm�Sn\�9\�\�\��\�r\�%�\�\���Ǜ��)��\�\����\��Qꨙ\�\�\�\�_^<�\��\�\��V�\Zz���ǈi\�\�\�o�۰\�\�\�\�a�K9\�j�ur\�W�ۥ�Ny\�a\��\��-\�\�\�\�<�\�\�\�~�mQ\�Vs�Ȳeˊ\�o�}���s\�W�xl<�z]\�F��f���Q�<\�=ES,�^��S�>\��\�\0\�y睋G}��hU��F\�v2\���\�\�V[mU�\�7���衇�=\�������~0\�<��|�bŊ\�s�\�O{\�ӊ%K��xn�\���t5)\�\�\�\�\�O��\�7�Q\rF\�\�wqӐȻ\�\��\�?�\�i��\�\�\�\�~\�Pc\�\�\�>\��������\�ߧ�����m\�;\��\�j\�;Kb�y�<}�\��:\�j���خ�\�,.�\�\�b��\����vکx\�{\�3l\�\�\�^���\�/����\">й�6\�\�����\�g>3l=f͚UL�6m\�t�&M*^\��\�MȠ��\�\�%\�\�S4\�\�{\�\�f\�dd\�\�wt�\��\�s\�=\�O\�\����$z�F\�!�Ї>4\��������\�?_}\�l\Z˿A\�g<��룾�\�/5�\�\\\�t\�y\�s�c�=v\�2\"�\�_b/b�7��\�D\'\�=��\�S\�u5Zb@\�׼���⋫=\�/x����\�\�N\Z6}�{\�\�^6\�\�W�\�)�\�\�[G\�;Ũ��%-�\��}y\�\�X\r.\�\���{\�w\��׿��&v�\�v(��\��P\rn&O�\\\�PU���-\\���\�?8l�\�~\����L<5��W��\�\�Q�掝H\���c�\�\�)��R\rʛӎGR��\�(u5��\�\�G��x\�M�\��Fd��\�{`�\�g�ꪫ.�ҽkd�5�\�n֕�K��}\�k�~�A\�\\P}��\�}��\���=l�?��\�\�\�\���\�|b�шA\�>\�������\���j\�j\�N\�A\�j�|\��_-��\�?VM\��\��\�E/z\�\�sO6��\�׾V�fm��u5\�\�`5&qTx޼y\� \�[n�2\�\���\�д�ӟ�m�\�v\�\�c�۴�\�\�\�;��\���n�vڔ\rkU{�o�y�={\�\�P�}\�\�\�\�;ib\�|�\��ۮ�\�\'?Y�\��?�̶�z]\����)��&·�\��k\���e\���S�sײ\�^\��-\�,>\��\�z�~\�_\\\�\�\�\�q�%N�\�\�HK\�!�A\��\\5\0\�h\����\�o~s5Ms�\�~\��\�\�RRo�XW\�\�\�x�M6�\ZЗ��\�\�w\�Q����\�U�\Zu \����\�G?\Z\�\\�ʨ�\�\���\�\"n\�뮻V_�ь�\�GQ��;\��\rlz\�;��S\�b\�G\�~�\�?�\�\�Y���\�o��\�/�mY���>\�\�Ǯ�\�\�\�s\�->�\��Xf\�I��j�ԗ��\�C�\�\���=\�\�\�\�|�\�Z7ؽ&�1-7\�p\�:�\�\��8\�r\�\�w\�{\�\�\�;\�jOf݈n�\�6\�\�ٳG̿\�\�>\��\���.��\�D\'\�Ơ�u\�L4�\�pFs\Z�\�ck;\"_����x\�3�Y5��\�\'Ġ9��;\�\�u5�\�\rG�\��/oy\�[���\�U�D-\�\��\��\�E\�Q/\�\�\'�xb_�8*G��/_^m\�\�\����\�\'\�@&j*�i�\rdb�Pǎ�H\\\�W�Z�u\�l�ͪSٚ\�m3�\�U����\�sd��Pn\���1c\�\�涠\�r\�*7,\��o^�7ץ�ƻ1XWb�\��2���ũ\�L\�\�\�|�\�[ɉ�-1}s�\�\�\�z��7N?K\�\�\�Jc\�dR��6{��1��q��\���~\�B\�z \�)ĵ���\��\�՝��\�L9\�j\�3gN\�O}�\Z�Ʃ]q\�\�u\�]7t�L<��\�\�Q7�d��\�%Qo\�}\�{�A\���@5����M\�\�D�\���\�ղ>�\�\�\r�c\�M7ݴ:R�\��?}\�њ��K]�\�}}\�z\�\��6\��\�\�>�\�\�D\�@f\�\��\�k��n\Z�\�\�\�Ν�\�r\�rns�j\"\Z��%no��S\�8\���i\�\�e�]V\�\�.\�y�\\b\�6��\�\�\�\�\��>\�\�G\�\�-�\�+�4)\�U3q*Y\���&0\Z\�ث��#2uz2Q�q�w\�i�9\�T��ƞ3\�<�:�\�\�\�\�\�\��\�U5�\�[T_c`��\�|�z�\�#2���b䬳\�*>\�Twʋ�L\�< v\�\�@��\�bY1\0��O\�%���T�\\\�*nV����q3��\�\�Ĭ�\�j�+��\�v\�m��\�\��\�\�{��Mؠ\��\�VnX��<y\�\�\�u誉h֖h{\�L\�{D&.�\��8?.��\����\�o\�\��8�=\��\�M@d뭷���T.\�Υ1H����;C\��ŭ�\�\�£�>z\�@&8+W�z�w {\�\�fq�v\�Y\�M%R��\Z{\�]l7\�# 1��Aų�\��j\0G\�\�oL�T2�/�\�ʸ�\�\�WG�\�Ν[]/���ӥ\�&&�\�֡�:t[f�\r�|�W\"b�0b�=���\�6�=\�\�\�O>��\�Faz\�\�\�?�ܰ�\�\\v�MDc\�o\�Mm�\�w�F\"�\�\�i\��h(ⴀhz��;�\�\0��\�\��xEu�w\�5�\\\Z�\�\�9�̟?��� \�>\�\�_��jP\�:��t�����\��\�ݠ\�[\�^z\�\�5q�\�����+��1��u|Hs9)E]�=q1\�P$��#21���L \�\�ahۢ8e�\�\�q\�8�\�\�\0���\�\�~\��\�UGo��\�jP�g�\�\�@:�Kq3�\��\��8�\�\�D\��ַ��f��K]\�&O��M=��\�$\�2���ֆN+�\�i�#��\�Mb\�\�ӧ?x\�\�W7�\r\�9\�sl�aY\�i.�\�&�1\�7\�D\�|�Nܭ\'�\�)bq����\�#�,\�\��7U\rG=]����\�9�H�\�M+\��6�\�\�\��\\W�E�U_�\�\�\�騧��f�\�E\�ь�\��)E]�=qc��y\����L\\7Ӝ\�\���}u-K\�\�\�awU�o.ћz@ۨ\�\�R?�\�\�\�\�\�ub�\��կ~5\�\�J.uի|�_R$;\�f+ЪX^\�ј%\�u27u\�\�m\�7��\�N0\�\�7\�|R�Q�3ǽ#\�\��~?��\�麐\\\Z��\�j����5\�J\�ՆMlsF��um�F�~}\�\�<�Jr��^e�U�~�x=��\�\�\�C,�g\�x�Gs݀��\�\�\�ӧO`C�Ys$\�2_j.+�4\�ɥ1PWiE]Iɥ����q�>\�hS̿\���X~s���H9\�y\�\�?��c�@\\ؿ暘�r<S\���\\\Zu�Vԕ��\\\�iڴi��\���\��8ů��L̷\\\�\��Xn,��N�Ff``\�Ճ���͘1\�\�\�;\�~?g��n�ܹs\��\�ʜ\�i\�;\'\Z���Kc��Ҋ��6�K]��|�ߢLܩt\�\�LܕnC��\��Y��-�\�l\�\�V\�\�a\�N;\�t��|ł��\�kn]�b\�C\�v\���\��ƥK�^t\�	\'\�\�k��N)�]\�\�t�\�u\���\\\Zu�Vԕ��\\\�jm\�Aƿ\�f\"qA��\�\�,^߸��\Z\�\�\�\�\0P�4i\�\�\�b\�����\�\�z6N\�\��5�ό隯͙\� �\�\��������\�RW\�2\��\��f��3\�y/q7��\"��\�5�\�T��\�r�\�\�Ih\�J.���J+\�J\�H.u\�d⚕57\0h@�\�-�3gNq\�\�W���\�\r\�5~�\�\��}\�\�g\�kc~1_\�\�\0��\� �\�\��������\�RW�Zsk\�ϙY\�,q�e�\��1H+�4\�*��+i#�\�\�S�YWD\�Y=\�\0e]�\�\�\�\�\�`4i%�\�@]�u%m$��\Z�iӦmZLv��\�\�27�yx͠%�\�\�\�<�cLߜ\0\�Ac�Vri\�UZQW\�Fr�+\0:Jc�Vri\�UZQW\�Fr�+\0:Jc�Vri\�UZQW\�Fr�+\0:Jc�Vri\�UZQW\�Fr�+\0:Jc�Vri\�UZQW\�Fr�+\0:Jc�Vri\�UZQW\�Fr�+\0:Jc�Vri\�UZQW\�Fr�+\0:Jc�Vri\�UZQW\�Fr�+\0:Jc�Vri\�UZQW\�Fr�+\0:Jc�Vri\�UZQW\�Fr�+\0:Jc�Vri\�UZQW\�Fr�+\0:Jc�Vri\�UZQW\�Fr�+\0:Jc�Vri\�UZQW\�Fr�+\0:Jc�Vri\�UZQW\�Fr�+\0:Jc�Vri\�UZQW\�Fr�+\0:Jc�Vri\�UZQW\�Fr�+\0:Jc�Vri\�UZQW\�Fr�+\0:j��O�Z�j\���ʿÝec\�H\�o\�E\�*��+i#9\�\0�hѢ;�/_>\�MJ\�?�\�r\�ܲ1���7\�\"u�Nԕ���\�\n��Z�p\�g�}\��˖-[aO\�Ĥ��/��\�S6��پ�7\�\"u5\�QW\�Fr�+\0:,ތb\�Z�\�qγ�{\�\�������\���w������6�e]\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0�F\���0f�rh�y\0\0\0\0IEND�B`�',1),('1864607722045943811',1,'请假流程（排他网关）-leave2.bpmn20.xml','1864607722045943810',_binary '<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<definitions xmlns=\"http://www.omg.org/spec/BPMN/20100524/MODEL\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:bpmndi=\"http://www.omg.org/spec/BPMN/20100524/DI\" xmlns:omgdc=\"http://www.omg.org/spec/DD/20100524/DC\" xmlns:bioc=\"http://bpmn.io/schema/bpmn/biocolor/1.0\" xmlns:flowable=\"http://flowable.org/bpmn\" xmlns:di=\"http://www.omg.org/spec/DD/20100524/DI\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" targetNamespace=\"http://www.flowable.org/processdef\">\n  <process id=\"leave2\" name=\"请假流程（排他网关）\">\n    <startEvent id=\"startNode1\" name=\"开始\">\n      <outgoing>Flow_0q78air</outgoing>\n    </startEvent>\n    <userTask id=\"Activity_19b1i4j\" name=\"申请人\" flowable:formKey=\"static:1\">\n      <incoming>Flow_0q78air</incoming>\n      <outgoing>Flow_129vtbe</outgoing>\n    </userTask>\n    <sequenceFlow id=\"Flow_0q78air\" sourceRef=\"startNode1\" targetRef=\"Activity_19b1i4j\" />\n    <userTask id=\"Activity_0r8rs5v\" name=\"组长\" default=\"Flow_1z12r58\" flowable:assignee=\"1\">\n      <extensionElements />\n      <incoming>Flow_129vtbe</incoming>\n      <outgoing>Flow_1z12r58</outgoing>\n      <outgoing>Flow_0bt4srq</outgoing>\n    </userTask>\n    <sequenceFlow id=\"Flow_129vtbe\" sourceRef=\"Activity_19b1i4j\" targetRef=\"Activity_0r8rs5v\" />\n    <userTask id=\"Activity_0iw78d3\" name=\"部门领导\" flowable:candidateGroups=\"1,2,3,4\">\n      <incoming>Flow_1z12r58</incoming>\n      <outgoing>Flow_0nj4k00</outgoing>\n    </userTask>\n    <userTask id=\"Activity_1ex621m\" name=\"总经理\" flowable:assignee=\"1\">\n      <extensionElements />\n      <incoming>Flow_0bt4srq</incoming>\n      <outgoing>Flow_0fo3v6j</outgoing>\n    </userTask>\n    <endEvent id=\"Event_1shlpsv\">\n      <incoming>Flow_0nj4k00</incoming>\n      <incoming>Flow_0fo3v6j</incoming>\n    </endEvent>\n    <sequenceFlow id=\"Flow_0nj4k00\" sourceRef=\"Activity_0iw78d3\" targetRef=\"Event_1shlpsv\" />\n    <sequenceFlow id=\"Flow_0fo3v6j\" sourceRef=\"Activity_1ex621m\" targetRef=\"Event_1shlpsv\" />\n    <sequenceFlow id=\"Flow_1z12r58\" sourceRef=\"Activity_0r8rs5v\" targetRef=\"Activity_0iw78d3\" />\n    <sequenceFlow id=\"Flow_0bt4srq\" sourceRef=\"Activity_0r8rs5v\" targetRef=\"Activity_1ex621m\">\n      <conditionExpression xsi:type=\"tFormalExpression\">${entity.leaveDays &gt; 2}</conditionExpression>\n    </sequenceFlow>\n  </process>\n  <bpmndi:BPMNDiagram id=\"BPMNDiagram_flow\">\n    <bpmndi:BPMNPlane id=\"BPMNPlane_flow\" bpmnElement=\"leave2\">\n      <bpmndi:BPMNShape id=\"BPMNShape_startNode1\" bpmnElement=\"startNode1\" bioc:stroke=\"\">\n        <omgdc:Bounds x=\"240\" y=\"200\" width=\"30\" height=\"30\" />\n        <bpmndi:BPMNLabel>\n          <omgdc:Bounds x=\"242\" y=\"237\" width=\"23\" height=\"14\" />\n        </bpmndi:BPMNLabel>\n      </bpmndi:BPMNShape>\n      <bpmndi:BPMNShape id=\"Activity_19b1i4j_di\" bpmnElement=\"Activity_19b1i4j\">\n        <omgdc:Bounds x=\"320\" y=\"175\" width=\"100\" height=\"80\" />\n        <bpmndi:BPMNLabel />\n      </bpmndi:BPMNShape>\n      <bpmndi:BPMNShape id=\"Activity_0r8rs5v_di\" bpmnElement=\"Activity_0r8rs5v\">\n        <omgdc:Bounds x=\"470\" y=\"175\" width=\"100\" height=\"80\" />\n        <bpmndi:BPMNLabel />\n      </bpmndi:BPMNShape>\n      <bpmndi:BPMNShape id=\"Activity_0iw78d3_di\" bpmnElement=\"Activity_0iw78d3\">\n        <omgdc:Bounds x=\"640\" y=\"100\" width=\"100\" height=\"80\" />\n        <bpmndi:BPMNLabel />\n      </bpmndi:BPMNShape>\n      <bpmndi:BPMNShape id=\"Activity_1ex621m_di\" bpmnElement=\"Activity_1ex621m\">\n        <omgdc:Bounds x=\"640\" y=\"250\" width=\"100\" height=\"80\" />\n        <bpmndi:BPMNLabel />\n      </bpmndi:BPMNShape>\n      <bpmndi:BPMNShape id=\"Event_1shlpsv_di\" bpmnElement=\"Event_1shlpsv\">\n        <omgdc:Bounds x=\"802\" y=\"197\" width=\"36\" height=\"36\" />\n      </bpmndi:BPMNShape>\n      <bpmndi:BPMNEdge id=\"Flow_0q78air_di\" bpmnElement=\"Flow_0q78air\">\n        <di:waypoint x=\"270\" y=\"215\" />\n        <di:waypoint x=\"320\" y=\"215\" />\n      </bpmndi:BPMNEdge>\n      <bpmndi:BPMNEdge id=\"Flow_129vtbe_di\" bpmnElement=\"Flow_129vtbe\">\n        <di:waypoint x=\"420\" y=\"215\" />\n        <di:waypoint x=\"470\" y=\"215\" />\n      </bpmndi:BPMNEdge>\n      <bpmndi:BPMNEdge id=\"Flow_0nj4k00_di\" bpmnElement=\"Flow_0nj4k00\">\n        <di:waypoint x=\"740\" y=\"140\" />\n        <di:waypoint x=\"771\" y=\"140\" />\n        <di:waypoint x=\"771\" y=\"215\" />\n        <di:waypoint x=\"802\" y=\"215\" />\n      </bpmndi:BPMNEdge>\n      <bpmndi:BPMNEdge id=\"Flow_0fo3v6j_di\" bpmnElement=\"Flow_0fo3v6j\">\n        <di:waypoint x=\"740\" y=\"290\" />\n        <di:waypoint x=\"771\" y=\"290\" />\n        <di:waypoint x=\"771\" y=\"215\" />\n        <di:waypoint x=\"802\" y=\"215\" />\n      </bpmndi:BPMNEdge>\n      <bpmndi:BPMNEdge id=\"Flow_1z12r58_di\" bpmnElement=\"Flow_1z12r58\">\n        <di:waypoint x=\"570\" y=\"215\" />\n        <di:waypoint x=\"605\" y=\"215\" />\n        <di:waypoint x=\"605\" y=\"140\" />\n        <di:waypoint x=\"640\" y=\"140\" />\n      </bpmndi:BPMNEdge>\n      <bpmndi:BPMNEdge id=\"Flow_0bt4srq_di\" bpmnElement=\"Flow_0bt4srq\">\n        <di:waypoint x=\"570\" y=\"215\" />\n        <di:waypoint x=\"605\" y=\"215\" />\n        <di:waypoint x=\"605\" y=\"290\" />\n        <di:waypoint x=\"640\" y=\"290\" />\n      </bpmndi:BPMNEdge>\n    </bpmndi:BPMNPlane>\n  </bpmndi:BPMNDiagram>\n</definitions>\n',0),('1864607722507317250',1,'请假流程（排他网关）-leave2.leave2.png','1864607722045943810',_binary '�PNG\r\n\Z\n\0\0\0\rIHDR\0\0P\0\0T\0\0\0*\�\0\0*:IDATx^\�\���u}7\�\��WTD���[/X��b�c\�*H�$\\L H@��VQ*|[.\�CQ��Z\n\n�\�V��\"^nrI@�K  @��\��?\�\�;��M2\�dw~�|>\�|\�\�>\�\�<��_&�_�\����\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0@��\�ϝ;\�\�3g\��\�\�\�\�\�/�K\�T�\�Wf̘\�Xoo\�\�\�\0\0�\Z�ί�\��p\�´t\�\�\�\�/\�\'�\�\�\��\�k�}�\Z�>[�F\0\0@y\�Sn\�˦^F>}}}��\�\�\�5\0\0�ȇ\�\�\�#�u��e\�k\0\0�\��)y\�^\�\�Q�F\0\0@\�P\�=ݗ\�\�rf�}\�?\�\�\�\�e\�z\�YP\0\0X;Ԓ\�Ҭ+�Jw^�\�\�\�\�m\��2\��\0\0 �v�Gf_\�2<5\�\�\�KZ֗�\�\0\0\0��3@\�{\�\�-�S#��r}zP\0\0X;Ԭ_\�285�o+ח�\�\0\0\0��b\�\0\0\0��3@\�S#��r}zP\0\0X;Ԝ~\�285�o+ח�\�\0\0\0��3@=�\�4\�\�OyY��\\_�\0\0\�\�\0�\�Эg�PyY��t\0\0\�\�\0�lY�s\�\�-T^�okY_�\0\0��j\�\�\��Nm�\Zɷ\�u\��\�\�b�\0��V8@-[��\�\����8�eh*�\�\�\�\�\�yP\0\0\�`Ԫ\�:�(\�Fu\0\06\�\0\�\�^�%߷|<i?(\0\0l���^m\�Ǔ\�c�\0����{1@\0@`�X1@\0@`�X1@\0@`�X1@\0@`�X1@\0@`�X1@\0@`�X1@\0@`�X1@\0@`�X1@\0@`�X1@\0@`�X1@\0@`�X1@\0@`�X1@\0@`�X1@\0@\�\�n\�TS./�b\�\0\0\0]P\rOiyV:H�b\�\0\0\0]\�4@�t�2@Ŋ\n\0\0�`�j\�A\�\0+(\0\0\�A�2\� խjɒ%i֬Y�??�\�\��矯�_�lـu\�뮴x\�\�\�\�y\���\�#��\��e�����\�裏�\���\�}\�\�-\�F\"(\0\0(2\�t-\�\Z�\�\�\�V[����*=\�\�3i\�ܹ\�ϓ&MJ\�o�}:ꨣ\�\�\�0u\�M7�\�6\�,\�r\�-\�\�\�O�\�o�\�w\�=m�\�\�\�\�\�x\�N8!}\��M<\�@�2eJ��9�\�\�\��\�\�\rx�\��8q\�\�Ǝ�\��\�w��K��<\�\�N���t\�Z\0���F�l��r\�n]<�/KGqD=��\�}難�-�\�2|\��\�=\�yO�\�6\�l�fΜY��\�\�Cոq\�\�%�\\R\�E\�{�\�m���\�\����\������z�ʷ\�Myz\�;\�Y\�\ZloS~\�3\�<s��\�/�<}�\�oYw$\�\�=P#Q�\0\0\�D�����a����~���{\�4mڴzx�?~=@察�\�g�\��\�/~�v\�q\��\������\��ۮ�\�믿�^\���Jm�Q\Z?~|�7i\�7��\��\��\�\�k�\��\'O�#\�\�\�N;\��\�o�6�~�\�-\�\��\0\0�0�\���nP9{\�G:\��\�\�>�\�S͇\�\�\�\�SO�\�\05ֻ\�\�T\�i�\�~y\�9\�\�ҿ����SN9%m��\�\�ޮo}\�[\�\�?�q��+o͏�\��\�ۚ�\�w\�}\�W��U�\�\'>Ѳ\�\�\n\0\0\n#Ѩ�jpj\�\�\�K{\�w��>�>�\�O�\r6ؠ\�\�_�\�ַ���xC\�k\�X�\�/nk�ʇ\�\�\�.\\�\��\�7ׇ��\�?���W\�\�\�t�\�\�l�\�i�-��\�\�׾6�6\�sYo�\�\�C\�\�gP\0\0P�Ԩ�\�\0�s\�9\�\�}\�s\�ޟ|]>�nΜ9�\�@\�\��о�}>艹*�\�t\�g\�\�\�G>\�z\�\�\���\��.0@\���i��\���\�\�/~��y\�7�\�Mi\�=\�L\�GԲwj�c�\0�B�F�\�\�W_�v\�y\���{\�\'m�\��i��i�M6��p�\�\��n}{�{�r-ZT\�k�IcƌI\'�|r=@\�R\�|\�\�\�\0��\�\�X?o+^y\�\�\�;\�ey�jr݈\n\0\0\n�\Z\�nPӧO�/K\�\�\��<̼�\����7�\�\r\�%\�\�f��\�\�\�.�\�>�\�\�\�W\��:uj}>Ժ\�[�gu\�\'�\�y#\�w\\�\�\�\rP\0\0L�F�T�HD�B^�>\�\�CT�\�\�\�\�\�O� \�|\�]>W�����|y/U>W\�S��Ԁ\�\�;\����y\�\�\�\�P�\�y\�^>$p\�M7�/RQ>�|�|Ž|nV\�_�\�_��3R1@\0@!R�ڍ\�\'��/a��o|Xm�\�yQ\�?��\�\�s�\�\�yS�\�\��9Q\�m�A(Y�g\�\�_~\�w\�\�H�\�7����\�\'?�I\�m#\0\0\"5�\��K�\�\�[^.kd�\�;\�p<櫉\n\0\0\n�\Z\�(��OP\0\0P�Ԩ\Z�b\�\0\0\0�H��*VP\0\0P�Ԩ\Z�b\�\0\0\0�H��*VP\0\0P�Ԩ\Z�b\�\0\0\0�H��*VP\0\0P�Ԩ\Z�b\�\0\0\0�H��*VP\0\0P�Ԩ\Z�b\�\0\0\0�H��*VP\0\0P�Ԩ\Z�b\�\0\0\0�H��*VP\0\0P�Ԩ\Z�b\�\0\0\0�H��*VP\0\0P�Ԩ\Z�b\�\0\0\0�H��*VP\0\0P�Ԩ\Z�b\�\0\0\0�H��*VP\0\0P�Ԩ\Z�b\�\0\0\0�H��*VP\0\0P�Ԩ\Z�b\�\0\0\0�H��*VP\0\0P�Ԩ\Z�b\�\0���R�\�s\��|\�̙��>}z���l�\��+3f\�x���\�\�\�3Z��\�\'j]EjT\�\�l\�{ɯG�\Z��Hu	@pU�{~\�d����K����&ß�{Ͽ�k��\�����l�\Z�F\���ZW�\ZUT��\0\��\�d���\�ȧ��oQ\�@\�\\�F�����hu�Q\�{\��1R��Uu��|�FJ�� �|x�\"F\�\�\�\�buRWq��\"5��G�~�̟?j7�Hu	@pa��n²:��X�TW�\Z\�|~X>\�1\�3\�w\'\�\�o޼y\�V5�p75�T�\0\�n��\�\�}i\�-g�\�W�c��}^V�\'�%R�\�	u+�\�*Z���\��\�ʋ�\�$#��{Ͽ��\rOY�� ��X\�l�Y�xA�u\�Q\�\�K�> yY��\\_���z��\�h��b%R]iT�H]жv\Z\�Gf_\�\�\�6\�\�\�KZ֗�\'R�\�	u+�\�J�JD\����\�\�\�{\�\�-\rn#��r}z\"5��PW���4�D�.h[;�\�_\�\�\�6�o+ח�\'R�\�	u+�\�J�JD\���itc%R�\�	u+�\�J�JD\����\�\�櫣�\rn#��r}z\"5��PW���4�D�.h[;�\�~\�\�\�6�o+ח�\'R�\�	u+�\�J�JD\����\�\�>�\�4\�\�4�yY��\\_��H�n\'\�U�D�+�*�K\0\�\�N��\�Эg�4�yY��t�H�n\'\�U�D�+�*�K\0\�\�V��lY�s\�\�-�n^�okY_��H�n\'\�U�D�+�*�K\0ڶ�Fw\�\�\��Nmirɷ\�u\��\�\��\�턺��Hu�Q%\"u	@\�V\�\�.[��\�\����8���-�\�\�\�\�k\�y\"5��PW���4�D�.h\�`�\�\��(\�\Zt�H�n\'\�U�D�+�*�K\0\�6X�\�\�ށ%߷|<i?�\Z\�N��X�TW\ZU\"R�\0�m�F�l^_m\�Ǔ\��\�턺��Hu�Q%\"u	@\�kt�{�\�\�vB]\�J��Ҩ\�h�ˉ\'��z��\�\�\��k\�uz������˿柧/�}׼~�\0t@�+�\Z\�N��X�TW��Qe\�1\Z\�r��\�;V\�sZ��K\�&�?-߿|L\0�@�+�\Z\�N��X�TW��Qe\��.��g�\��\�4\�`4�ܔ�\�\0��F7V\"5��PW���rW.�n�X�\'N\\���\�\�s{��=\�\�t\�9\�\�n�-=�\��驧�JY��\�\�\�\�y�\��\�\�\�\�\�/�	ЯzOy\�̙3w9�쳯:\�c��\���\��\�߿~C�2e\�\�8\�g�e{\�\'M�4i�\��k2�n�Djt;��b%R]ElT!Z]V\�g�\�\�\�\�\�<�L�0!�\�G?J�>�h=,�+��\�\�_Rw\�\�\�\�r\��\�:W\\qſu\�Q��<yr:\��\�\�M7\�4\��\�\�\��\�}\�\�\�\�:h\��\�S�xk\"�n�Djt;��b%R]EkT!�T�\�\�\��U4;\�|z\�GNF�R�~�b�z$o�|�Z�\Z��z\�\�.ͻ�gΜ�^~�\�\�dPy������8\�\����w���&\�\�\�J�F�\�*V\"\�U�F\Z�\�e\�#\�<<\�F_|q٪t$?^�7ꑼ\�\�\0k�\�\���\�\�s\�\�o�t\�e���^z�|\�hK�_��ĉ�[��\\�э�H�n\'\�U�D��(�*4�P����\�\�\�&M��~�\�_�\�\�j�7?~\�u�s�`-U�\'���i\��\���\�~\�ߔ\�C�g\�\�\�/L�0a�r{k�n�Djt;��b%R]EhT��.�_0�\�\�p\rO\r�\�\�D\�\�\�	X\�=OS�LI.,\�\':�oҤIKƍ�C�\�\�N�+�\Z\�N��X�TW\ZU(u�.�_��\�\�0s\�%���Ȱ\�\�i\�\��K�\�Z\�\�o�j�Vמ�R\���\�\�^�ǎ��rۣ�F7V\"5��PW����ݨ\�`�]��5}\�S�\�\�H*.,qS�܀5T\�\��\�;ni>gi8]t\�E�\'L�pE��\�L�+�\Z\�N��X�TW\�nTa0ݬ\�|tKc�ɇ\�uz��W+o��P�5\�h`�_~��\�\�\��z��v\�ǟ<y\�՛\�\'\�\�0Zitc%R�\�	u+�ꪛ�*�H7\�\�\��\�\�?��\�v�\�BM+�#����޿\�;\��\�\�\�\�G\�\�\�_�t\�\�\�\�y�V\Z\�X�\�\�vB]\�ʪ\�\�裏~M�l�t�Q�\�V]N�0a�j\�/6���\��Ԑ�\�4@���W�\\�5ȍ7޸\��\�\�\�\�<u*og\�\�wz\�ر�\�%�\�\r\�*c\�\�%�n���\�\�6u5:S\�՞{\���\�\�\�|��~x\�\��\��\�?�\�?��1c\��a\�zá[�*�\�p\�\��\�\�W9�\\ެ\��%I\�My�MCԮ\�s\� g�u\�/O;\��\�}`Xs\�1�zzz\�/�K$Mo�+mx5��R6�Ѩ�љ\�\�\�ӄ	�\�~�lٲ��y�\���y睷`ҤI\�6���\�p4�Щ\�\�\��\�\��\�\�S\��\��?mn5F\\\�~\�{��\�s\� \�{\�#u�^\�\�W_={�\�\�7�	��\�\�\�\�\�(\Z�\�\�(Js]=\�\�C�x\�\��\�k\r\�\�\��\�\�\��رc\�\����.�n\�e\�m\�Q�\��\�`�T\�so\�\�[o���\�8�\�\���ko\�\�\�0\��\�7�Ν;�|V\�\�w߼\�\r\�\�DR�q7e@ëэ�Q8@��Q��zT\�}\��.m\�p\�W*\�}�v\�a_nz�W�\\/\�2\�\�\�A\�\'\�4R\�\�7��t/S\�\�oz��`\r2eʔW~�\�ߖ\�\�ꩧ�ZR��,(�K$\�\� ��ʘȍ\�u\�]��}\�ٖ\�+\�\��ߞ�y晖\�e\�ɲ\�\�\�\�\�Ԩ��F\�\�δdɒ���K�\�/��\�[\�}�\�\�ӽ\�\�;`\�c�=6\�~��\�\�W7��\�e��x\�\�ߝq\���;v���*\�I��m8\�r�\�\�ɃT\�uY\�\�\�eJy�M\�\�\�\�\��A\�\�s\�\��\���|V\�\��-\�Gk\"5�/�\���\�f�t\�\�\�\�?\�\�sn���\��\���\�|\�;\�gY\�\�-Z\�\���\\u\�U鵯}m�馛Zn\�v\�\�g�&R]�(���\�\�7��\�z�Ї>�\�\�hr>�\���O>y��\�\�sOZw\�uӣ�>Z��lٲ��\�\�\�5hy\�hɃ\�I\'�T��x�\�}\�}\�׷Ӕ\�\�\�me�v##\�˔\�\���\�K\�\�X�\�\�~�\���y\�\�\'\�m\�f���Z\�=\�m�]#�\�F�]v٥��lV�\� �ɟ�I�?\�_�\�W�߭�ڪ^\�\roxC:묳Z��s饗\��\�U\�\�\�\�=Pa\�j�\�A\�/�\�/\�m�ݖf͚�\�\�\�\�6v\�\�t\�%�X?\�|�\�\�\��\�a���\�z\�\�\�\��<v\�L�6-������\�\�W^ye\�\�\�\�R__\�Խ\�\��\�\�u�\�(\�t\�Q���O\�i>�/\�\�i,\�^�d�E?�\�gG��\�{\�\�\�\�F\�9P\Z܆H�\�\�ٳ\�\�o\��s�n��\����\�N\��\����\���\�\�\�O<1M�4��>��\�\�\�\�>\���w\�g�\�\�_�\�U\�\�\�\�*|]\r��}\�k\��\�\�G��\��/�\r6ؠ��U\rP_��\�\��,^��\�q��QWUS4�ZXy�|_kX�dɜ|�\�u}4�_���\�uS.�n��,\�/��\�n΁�\�\�#����\�w�\\�\�\�\nߠ\rnC�F77�o|\�\�a(g\�\r7L_�\�\�\�\�ޥ|�^^/\�Y\�{�\�u\�\�G\��Ҹq\�����[lQ�Sn#\�{\��ހǋ�Q4@���*��\�u\�Y\'}\�3�I�\��,XP\�l\��|\�;\�1\�\0�\�12�/�\�/n\r{��\�*_]o\�\�w?�\�s\�}���^z�\�\�\�\�\�\�\�\'L�\�R\�\����\�6��l�_685\�\�5\�s>`\�s\�1\'�\�\�@x\�����ρZi�\��\�}\��W��1\�\�=KO<\�Dz\�\�\\\�\�g\�{\r\Z\r\�;쐎=\�ؖ\�od\�w�o\�罔�u;�`�\ZuuU&��t\��=\�\�#���\��\�\�_sM\�\�u�{]=t5\�,\'\�yyޛUn\'R��\�=\�#�r\�=�\��\�\��\�k_�Zn\�\�\�u\�]ǔ\�N\�ѨB���.W685\�\�s��n�\Z�M&O��r;�\�]�\�<^��<Y5Y�\�e4�\�\�\��\�_\�\0\�|_�RZ>/��w�\�]�{�\�ޥ�~����\�\�]n�\��0�H���\Z\�\�(\�\�\�)���>�\�O\�\�\�\�\�\�\�=P��\�l�\���|a�|�T\�!|p@�뮻Z3rVTWcƌ�ê��F~O\�_\�\�\�:�\�p4�Щn\�e�\�]Cˡ�Z�#\�Cy�i�ڵ|��\Z栃zh�\�:u\�O�7�_�\�a��\�\�\�\�D�\��o|\��\�6�;\�#\��\�^\��U�\�\�\�\�\�\�OO�\�G\�\�\�އ|�_;�>���\�m\"\�U�|\�\�[\��\�\�\�\�S_\\\�\�\�^\��F��\\�[n�ez\��Z;jVUWG}\�k\�eå[�*�L�\�r	\�U\�~�1�\�uC\�n\�\�\�b~^\�s\�0�\�\�N|\�\�_z\�\�=a����\�eθq\�>Y>�\�*R��\�\�+\�5\��\�/~1\���\��<�m�\�6�\�g?kkT>A6\�]\�W�k,�\�\'?Y\�\�\0�jtG�HuU&_�<?�|	\�|\�~\���*VO=\�T��\�jΜ9\�E(\�U\"\�]�$R\"\�U�\ZUX�n\�e�\�i�\�%�uC\�n\�\0�o`5:\��o�\�\�^)\�V�WN8\�3�7�\�\�m�f�\�<@�賚\�ϟ�^��\�ח1χ\�5\�@\�C\�\�a�\�|\�\�\�+�\����\�\�ó6\�t\��\�\�}��H�n\'\�\�s�\\t\�E\�\�|\��\�?�\�?\�s�\�yL\��x\�\�ޖv\�i��\�&�\���\�[o����&N�X�����y�ʃ��)SZ�)�ꪛ�*�H7\�rܸq;4���Y�#�*oo��\���\�\�\�S>G`\r5v\�\�wM�4i\�o~\�\�a��\�\�Ϊ\�X�\�v\�m�f�\�|�Jn\\\�\�\�w\�}\�\�|(޷�\�����\��\��\�����\�\�1����\�\�1r\�0��\�\�\�\�m�m��\�\�D\�ʙ1cF�5\n\�\�\�\�r{�\\O\�{\��Zjdɒ%\�\�\�k�\�h�TW\�lTaE�]�\�\�oj0\�|ق�����O7�\�\rXÍ?~ǽ\�\�����\��7o\�%՛\�ck\��\�Dnt\�V\��\�\�z�!�\Z\�ND���&_��\\6Z���ݨ\�`�]�U��M\�^n2�<̑��\�4<���G�܀���	\��4iҳ�kO\�\�=O�\�]nkM�&6��9�\Z\�N��X�TW\�nTa0겧�燍a&���N�\�\�\�\�/��\�{�\�\�s\�\�]t\�\�^X\"_0b�9O���{�\Z4���\�턺��Hu�Q�R���8q\�:\�\�1\�\�\"\r\���\�^���ݼ�\�9k����wN�0\�\�ɓ\'?y�\�\�?\�\�\�DU\�-�:u\�9՛ɜ*�\�\�){M�э�H�n\'\�U�D���*��\�e\�<6����ۿ\'\�\�/.[��\�\�k\�\�|{��\�X�\�K�Wo3v\�}\�g�>�軧O�>\��\�{xѢE\�U\�#K�x≹�gϾ\�\�/�蠃��ZwQ^M�T�\�htc%R�\�	u+�\�*J�\n\�\"\�e5\��e\���/\�\�\�\��\���F\�\�S\�^�\0jcǎݸz��\�\�\�sq\�\�\�7��\�\�\�˧\�\�\���\�4���\�턺��Hu�Q��hu�\��\�\�?�/\'\�ʟה?~\�\�\�\�\��{�\�\�\�\�v\�m�\n\Z\�X�\�\�vB]\�J���֨B�.\�9I\�/,Q>\�\��s\�9\�\�n�?�#\�w��\�\�\�|�!�\�r\��x�q�\�0D\Z\�X�\�\�vB]\�J���بB\�\\~�\��ω\�07�T9@�4���\�턺��Hu���rt\�h�\��Y�\�\�V\�\�A��%�?-߿|L\0�@�+�\Z\�N��X�TW��Qe\�3�\�r\�ĉo��\�===�Z}�^\�*/,�\�\��\�\�\�\�\�/�htc%R�\�	u+�\�j45��=\�%\0m\�\�\�J�F�\�*V\"ՕF��\�%\0m\�\�\�J�F�\�*V\"ՕF��\�%\0m\�\�\�J�F�\�*V\"ՕF��\�%\0m\�\�\�J�F�\�*V\"ՕF��\�%\0m\�\�\�J�F�\�*V\"ՕF��\�%\0m\�\�\�J�F�\�*V\"ՕF��\�%\0m\�\�\�J�F�\�*V\"ՕF��\�%\0m\�\�\�J�F�\�*V\"ՕF��\�%\0m\�\�\�J�F�\�*V\"ՕF��\�%\0m\�\�\�J�F�\�*V\"ՕF��\�%\0m\�\�\�J�F�\�*V\"ՕF��\�%\0m\�\�\�J�F�\�*V\"ՕF��\�%\0m\�\�\�J�F�\�*V\"ՕF��\�%\0m\�\�\�J�F�\�*V\"ՕF��\�%\0m\�\�\�J�F�\�*V\"\�U�F5�\�\�s\�\���̙37}�\��\�$#�\�\��ʌ3\�\�\��B����Hu	@p���ْ\�%�\�k4\Z��X�TW�\Z\�jx:�j\�\�\�ҥK[~o2�ɿ\����\�\�k��\�\��\�k4R\"\�%\0�\���\�8\�H\�:<V5\�\�\�h4RWq��\"5�y\�Sn\�\�ߙ�|���Uuzs�\Z��Hu	@p�\�	\rD�̟?j7�\�I]\�I���Ԩ\�\�\��1�_�n��\��\�\�q\��Љ��\Z�\���\�͛7\�ܪyx�����N\���ZW�\ZU��\�J75�T�\0���\��C]\�\����x\�\�=��\�4��C�\�,�s��\�$d]EjT\�\�l\�\�sO\������f_��u\�\�yY��t��z��\�H�T�\0\0\�/R�\�\�\0�d\�4\�ʣҝ�~}@\�|[��=(\0\0(DjT\���}q\�\�\�ȣ�/iY_�\0\0\"5�\�P\�^s|\�\�\�H��\\_�\0\0\"5�\�P�~yt\�\�\�H��\\_�\0\0\"5��X1@\0@!R�\�\�\0���WN�\�\�\�\�e\�1@\0@!R�\�\�\05\��N�\�\�\�\�e\�1@\0@!R�\�\�\0\�Ԃ�Ҭ��\�2<\�e��r}zP\0\0P�Ԩ�3@\�<t\�\�-T^V�\'�\�\0\0\0�H�j[Բei΍��PyY��e}rP\0\0P�Ԩ�j�Z�xA���S[��F\�my�\�~2��\0\0��Q]\�\0�lY\�ӛ\�\�Ȗ��L^\'�koT\�1@\0@!R�:\�\0���N+��Q�\�\0\0\0�H�\�`T;{�V�|\�\�\���\0\0��Ql�*��W�\�\���\0\0��Ql��\�\�\0\0\0�H��*VP\0\0P�Ԩ\Z�b\�\0\0\0�H��*VP\0\0P�Ԩ\Z�b\�\0\0\0�H��*VP\0\0P�Ԩ\Z�b\�\0\0\0�H��*VP\0\0P�Ԩ\Z�b\�\0\0\0�H��*VP\0\0P�Ԩ\Z�b\�\0\0\0�H��*VP\0\0P�Ԩ\Z�b\�\0\0\0�H��*VP\0\0P�F�\�\�5UƔ\�K�X1@\0@a$\Zռ�\�Y\� e��\0\0F�Qm\Z�V:H�b\�\0\0\0��hT��P�b�\0�\�H4��Ne\�A*\�\0\�\�3\�\���\�ϧ;Ｓe�F\���\�\�O�,_�ti˲\�\0\0f��h\�QG�N:\���K/�4m�\�f\�\�GmY/g��\�O�\�K�\�\�+ӤI��\�\�w�;͚5�e�<�填��o�\�\�n��e\�n�|}F:e�\0�Z�l��r\�n�\�[�`Az\�\�^��}\�\�\��/})\�\�\��^x�e�\�<m�\�&i���\�\�sϭ\�\�\��\�_o\�̙\���\��\�.���\�O[n�e�����\�-\�Gi\�F7\�\�=P\0\0�\�Z\�\�\�i���\���v\�}\�\�W\�Wi�}\�Io�q�<yrz\�{ߛ-Z4`ݽ\�\�;�}\�\�\�?�i\�t\�M\�=N?�\�OҺ뮛\�\�\�\�=X;\�s�1cF�^~ܽ\�\�+��\�z\�����\�-ϥ[1@\0@�jpj�4@�x\�\�#��\�{\�I[l�ŀeg�uVZg�u\�;\�6\�`�t뭷�SO=5�3&\�p\�\r\�\��x\�c�=\�\�ŋ�\���7+�S�\�;\�\�Z�\�=\�o{\�;\�\�\�\\�\0\0t����H\�\�\'�<\�\0��k^\�\�O�\�ӧ\�{�\�\�\�\�/�>o�q�\�s\�=�>\��O�+��\"\�}\�\�\�+_�J}.U>7*cm�р=P\�g\�\�\\�\0\0i�����\�| \�\�\�ԇ\�\�\�\�\����\�\�\�ޣ\�?<m��\�\�{\��^:\�\�C\�!�qNS��ć>\���\��\��M_�\�\�C�\�\�\��\�\�@�v\�i-ϥ[1@\0@`Q�e˖���\�\�\��f=Dl�\�V\�뮫/q\�Gַ7\�̓Q>\�)�\'���|\�\�<$\�s�\��\�s\Z\�@\�N\�!+�C\�\�\�����\�SUާ1@\0@`Q��\�SNI�\�\�\�Ajڴi\�g?�Y�袋\�Myoќ9s\�u\�!z�\�zy�C9$}\��_���|�\�\0�dɒ�C�\�yO�\��\�6\�l��;\��\�}\�{�\���?��1@\0@`�|���ý\�\�[_\"\�yj\�\�\�o�*\�\�}�\�\�?o��\�\�NHS�N\�?t/�\��\�s\�\�o�\��\��Q�_~y�\�?�\�z8;\�3�?+�����\�:}\�s��?\'�|n#\0\0a�j\��6\�p\�z�\�Y�\�=G����}\�n#���k\�\�\�\�O�\�)(\0\0,\�\0��\�\0\0\0��b\�\0\0\0��b\�\0\0\0��b\�\0\0\0��b\�\0\0\0��b\�\0\0\0��b\�\0\0\0��b\�\0\0\0��b\�\0\0\0��b\�\0\0\0��b\�\0\0\0��b\�\0\0\0��b\�\0\0\0��b\�\0\0\0��b\�\0\0\0��b\�\0\0\0��b\�\0\0\0��b\�\0\0\0��b\�\0\0\0��b\�\0\0\0��b\�\0\0\0��b\�\0\0\0��b\�\0\0\0��b\�\0\0\0��b\�\0\0\0��b\�\0\0\0��b\�\0\0\0�M�>���K��4\�2\�^�ǪjY�\Z\0\0A̘1㱅�4\�2\�?�\�j���|�\0\0� z{{�p\��\�>\�\�׷Ȟ�\���\�͛7\�\�jxz�\�g\�\�\0\0$7\�y\�G�\�982\�ɿ\���7<\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0�\��j��.�\�!�\0\0\0\0IEND�B`�',1),('1864607723115491331',1,'请假流程（子流程）-leave6.bpmn20.xml','1864607723115491330',_binary '<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<definitions xmlns=\"http://www.omg.org/spec/BPMN/20100524/MODEL\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:bpmndi=\"http://www.omg.org/spec/BPMN/20100524/DI\" xmlns:omgdc=\"http://www.omg.org/spec/DD/20100524/DC\" xmlns:bioc=\"http://bpmn.io/schema/bpmn/biocolor/1.0\" xmlns:flowable=\"http://flowable.org/bpmn\" xmlns:di=\"http://www.omg.org/spec/DD/20100524/DI\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" targetNamespace=\"http://www.flowable.org/processdef\">\n  <process id=\"leave6\" name=\"请假流程（子流程）\">\n    <startEvent id=\"startNode1\" name=\"开始\">\n      <outgoing>Flow_1mu0fz5</outgoing>\n    </startEvent>\n    <userTask id=\"Activity_0zy3g6j\" name=\"申请人\" flowable:formKey=\"static:1\">\n      <incoming>Flow_1mu0fz5</incoming>\n      <outgoing>Flow_03931vt</outgoing>\n    </userTask>\n    <sequenceFlow id=\"Flow_1mu0fz5\" sourceRef=\"startNode1\" targetRef=\"Activity_0zy3g6j\" />\n    <sequenceFlow id=\"Flow_03931vt\" sourceRef=\"Activity_0zy3g6j\" targetRef=\"Activity_15to8yb\" />\n    <subProcess id=\"Activity_15to8yb\">\n      <incoming>Flow_03931vt</incoming>\n      <outgoing>Flow_0e4ru1l</outgoing>\n      <startEvent id=\"Event_14f0d54\">\n        <outgoing>Flow_0y1q06j</outgoing>\n      </startEvent>\n      <userTask id=\"Activity_0x88dc2\" name=\"组长\" flowable:assignee=\"1\">\n        <extensionElements />\n        <incoming>Flow_0y1q06j</incoming>\n        <outgoing>Flow_1kj44n7</outgoing>\n      </userTask>\n      <sequenceFlow id=\"Flow_0y1q06j\" sourceRef=\"Event_14f0d54\" targetRef=\"Activity_0x88dc2\" />\n      <userTask id=\"Activity_15z7u3k\" name=\"部门副经理\" flowable:assignee=\"1\">\n        <extensionElements />\n        <incoming>Flow_1kj44n7</incoming>\n        <outgoing>Flow_1lrkvhq</outgoing>\n      </userTask>\n      <sequenceFlow id=\"Flow_1kj44n7\" sourceRef=\"Activity_0x88dc2\" targetRef=\"Activity_15z7u3k\" />\n      <userTask id=\"Activity_1p3e8iu\" name=\"部门经理\" flowable:assignee=\"1\">\n        <extensionElements />\n        <incoming>Flow_1lrkvhq</incoming>\n        <outgoing>Flow_161ozbd</outgoing>\n      </userTask>\n      <sequenceFlow id=\"Flow_1lrkvhq\" sourceRef=\"Activity_15z7u3k\" targetRef=\"Activity_1p3e8iu\" />\n      <endEvent id=\"Event_0ategpj\">\n        <incoming>Flow_161ozbd</incoming>\n      </endEvent>\n      <sequenceFlow id=\"Flow_161ozbd\" sourceRef=\"Activity_1p3e8iu\" targetRef=\"Event_0ategpj\" />\n    </subProcess>\n    <userTask id=\"Activity_03nnma3\" name=\"总经理\" flowable:assignee=\"1\">\n      <extensionElements />\n      <incoming>Flow_0e4ru1l</incoming>\n      <outgoing>Flow_19akbau</outgoing>\n    </userTask>\n    <sequenceFlow id=\"Flow_0e4ru1l\" sourceRef=\"Activity_15to8yb\" targetRef=\"Activity_03nnma3\" />\n    <endEvent id=\"Event_1ixvcma\">\n      <incoming>Flow_19akbau</incoming>\n    </endEvent>\n    <sequenceFlow id=\"Flow_19akbau\" sourceRef=\"Activity_03nnma3\" targetRef=\"Event_1ixvcma\" />\n  </process>\n  <bpmndi:BPMNDiagram id=\"BPMNDiagram_flow\">\n    <bpmndi:BPMNPlane id=\"BPMNPlane_flow\" bpmnElement=\"leave6\">\n      <bpmndi:BPMNShape id=\"BPMNShape_startNode1\" bpmnElement=\"startNode1\" bioc:stroke=\"\">\n        <omgdc:Bounds x=\"240\" y=\"200\" width=\"30\" height=\"30\" />\n        <bpmndi:BPMNLabel>\n          <omgdc:Bounds x=\"242\" y=\"237\" width=\"23\" height=\"14\" />\n        </bpmndi:BPMNLabel>\n      </bpmndi:BPMNShape>\n      <bpmndi:BPMNShape id=\"Activity_0zy3g6j_di\" bpmnElement=\"Activity_0zy3g6j\">\n        <omgdc:Bounds x=\"320\" y=\"175\" width=\"100\" height=\"80\" />\n        <bpmndi:BPMNLabel />\n      </bpmndi:BPMNShape>\n      <bpmndi:BPMNShape id=\"Activity_0b5ys91_di\" bpmnElement=\"Activity_15to8yb\" isExpanded=\"true\">\n        <omgdc:Bounds x=\"460\" y=\"115\" width=\"750\" height=\"200\" />\n      </bpmndi:BPMNShape>\n      <bpmndi:BPMNShape id=\"Event_14f0d54_di\" bpmnElement=\"Event_14f0d54\">\n        <omgdc:Bounds x=\"500.33333333333326\" y=\"197\" width=\"36\" height=\"36\" />\n      </bpmndi:BPMNShape>\n      <bpmndi:BPMNShape id=\"Activity_0x88dc2_di\" bpmnElement=\"Activity_0x88dc2\">\n        <omgdc:Bounds x=\"590\" y=\"175\" width=\"100\" height=\"80\" />\n        <bpmndi:BPMNLabel />\n      </bpmndi:BPMNShape>\n      <bpmndi:BPMNShape id=\"Activity_15z7u3k_di\" bpmnElement=\"Activity_15z7u3k\">\n        <omgdc:Bounds x=\"750\" y=\"175\" width=\"100\" height=\"80\" />\n        <bpmndi:BPMNLabel />\n      </bpmndi:BPMNShape>\n      <bpmndi:BPMNShape id=\"Activity_1p3e8iu_di\" bpmnElement=\"Activity_1p3e8iu\">\n        <omgdc:Bounds x=\"910\" y=\"175\" width=\"100\" height=\"80\" />\n        <bpmndi:BPMNLabel />\n      </bpmndi:BPMNShape>\n      <bpmndi:BPMNShape id=\"Event_0ategpj_di\" bpmnElement=\"Event_0ategpj\">\n        <omgdc:Bounds x=\"1072\" y=\"197\" width=\"36\" height=\"36\" />\n      </bpmndi:BPMNShape>\n      <bpmndi:BPMNEdge id=\"Flow_0y1q06j_di\" bpmnElement=\"Flow_0y1q06j\">\n        <di:waypoint x=\"536\" y=\"215\" />\n        <di:waypoint x=\"590\" y=\"215\" />\n      </bpmndi:BPMNEdge>\n      <bpmndi:BPMNEdge id=\"Flow_1kj44n7_di\" bpmnElement=\"Flow_1kj44n7\">\n        <di:waypoint x=\"690\" y=\"215\" />\n        <di:waypoint x=\"750\" y=\"215\" />\n      </bpmndi:BPMNEdge>\n      <bpmndi:BPMNEdge id=\"Flow_1lrkvhq_di\" bpmnElement=\"Flow_1lrkvhq\">\n        <di:waypoint x=\"850\" y=\"215\" />\n        <di:waypoint x=\"910\" y=\"215\" />\n      </bpmndi:BPMNEdge>\n      <bpmndi:BPMNEdge id=\"Flow_161ozbd_di\" bpmnElement=\"Flow_161ozbd\">\n        <di:waypoint x=\"1010\" y=\"215\" />\n        <di:waypoint x=\"1072\" y=\"215\" />\n      </bpmndi:BPMNEdge>\n      <bpmndi:BPMNShape id=\"Activity_03nnma3_di\" bpmnElement=\"Activity_03nnma3\">\n        <omgdc:Bounds x=\"1260\" y=\"175\" width=\"100\" height=\"80\" />\n        <bpmndi:BPMNLabel />\n      </bpmndi:BPMNShape>\n      <bpmndi:BPMNShape id=\"Event_1ixvcma_di\" bpmnElement=\"Event_1ixvcma\">\n        <omgdc:Bounds x=\"1412\" y=\"197\" width=\"36\" height=\"36\" />\n      </bpmndi:BPMNShape>\n      <bpmndi:BPMNEdge id=\"Flow_1mu0fz5_di\" bpmnElement=\"Flow_1mu0fz5\">\n        <di:waypoint x=\"270\" y=\"215\" />\n        <di:waypoint x=\"320\" y=\"215\" />\n      </bpmndi:BPMNEdge>\n      <bpmndi:BPMNEdge id=\"Flow_03931vt_di\" bpmnElement=\"Flow_03931vt\">\n        <di:waypoint x=\"420\" y=\"215\" />\n        <di:waypoint x=\"460\" y=\"215\" />\n      </bpmndi:BPMNEdge>\n      <bpmndi:BPMNEdge id=\"Flow_0e4ru1l_di\" bpmnElement=\"Flow_0e4ru1l\">\n        <di:waypoint x=\"1210\" y=\"215\" />\n        <di:waypoint x=\"1260\" y=\"215\" />\n      </bpmndi:BPMNEdge>\n      <bpmndi:BPMNEdge id=\"Flow_19akbau_di\" bpmnElement=\"Flow_19akbau\">\n        <di:waypoint x=\"1360\" y=\"215\" />\n        <di:waypoint x=\"1412\" y=\"215\" />\n      </bpmndi:BPMNEdge>\n    </bpmndi:BPMNPlane>\n  </bpmndi:BPMNDiagram>\n</definitions>\n',0),('1864607723623002113',1,'请假流程（子流程）-leave6.leave6.png','1864607723115491330',_binary '�PNG\r\n\Z\n\0\0\0\rIHDR\0\0�\0\0E\0\0\09=?\0\04\�IDATx^\�\��\\ey7p���Rz\�\�[Koj�\�\���]\�ji�vY-NP ���E�\�[Z�~ �jk�D�\�M6 \�/@D.*�$%j\�7\�Ȝo\�s$\�\�w\����\���\�\�3{\'\�9\��\�w\�\�\�i\'\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\�ɬY�\�\�>�蛟�\�\�~k�]viz7)��RJ)�Z�\��\�\�}��\�|\�Wv\�}\�7\���\�N\0\0P�X\�~\�\�\����\�%\�\r7\�\�l޼�\0\0\��<\��_�\�_|k\�]w�n\'�\�\0\0\�쨣���\�/~q\�\�\0\0@!\�\�c��{\�}Y\�\�\0\0P�\���\��V�\�\0\0�\�\�{�\�o\�Y\0\0�\�<\�\�D\0\0�`�\�{\�}S\�\�\0\0P���\0�\�Dn\�A\0\0j�32\0\0P�\�\�9\�\0@MrF\0\0\n�=y\0\0�I\�\�\0\0@a\"�\� \0\05\�\0\0(L\�\�\�\0�&9#\0\0��ܞ�<\0\0\�$gd\0\0�0�\�s�\0��\�\0\0&r{\�\0\0P���\0�\�Dn\�A\0\0j�32\0\0P�\�\�9\�\0@MrF\0\0\n�=y\0\0�I\�\�\0\0@a\"�\� \0\05\�\0\0(L\�\�\�\0�&9#\0\0��ܞ�<\0\0\�$gd\0\0�0�\�s�\0��\�\0\0&r{\�\0\0P���\0�\�Dn\�A\0\0j�32\0\0P�\�\�9\�\0@MrF\0\0\n�=y\0\0�I\�\�\0\0@a\"�\� \0\05\�\0\0(L\�\�\�\0�&9#\0\0��ܞ�<\0\0\�$gd\0\0�0�\�s�\0��\�\0\0&r{\�\0\0P���\0�\�Dn\�A\0\0j�32\0\0P�\�\�9\�\0@MrF\0\0\n�=y\0\0�I\�\�\0\0@a\"�\� \0\05\�\0\0(L\�\�\�\0�&9#\0\0��ܞ�<\0\0\�$gd\0\0�0�\�s�\0��\�\0\0&r{\�\0\0P���\0�\�Dn\�A\0\0j�32\0\0P�\�\�9\�\0@MrF\0\0\n�=y\0\0�I\�\�\0\0@a\"�\� \0\05\�\0\0(L\�\�\�\0�&9#\0\0��ܞ�<\0\0\�$gd\0\0�0�\�s�\0��\�\0\0&r{\�\0\0P���\0�\�Dn\�A\0\0j�32\0\0P�\�\�9\�\0@MrF\0\0\n�=y\0\0�I\�\�\0\0@a\"�\� \0\05\�\0\0(L\�\�\�\0�&9#\0\0��ܞ�<\0\0\�$gd\0\0�0�\�s�\0��\�\0\0&r{\�\0\0P���\0�\�Dn\�A\0\0j�32\0\0P�\�\�9\�\0@MrF\0\0\n�=y\0\0�I\�\�\0\0@a\"�\� \0\05\�\0\0(L\�\�\�\0�&9#\0\0��ܞ�<\0\0\�$gd\0\0�0�\�s�\0\�^\�\�\�\�\��\�\�\�_��+V4�\�\�\�<W\���\�+W��ꪫ^�>\��J\�s\�=Mo܈��\�\�j\�+�߯�\�殻\�\�?�*\�vK�\�6�\���يܞ�|5櫭W\�\�\0��^(8�\��\�<\��\�ƍ�͛7�y�������\�_\��\�QarF�B��c\�h�\�\�\�W_]\�b��k�\���k�j\�?E\�\�\�\�|��\�\�|�q�lǓR~�R\�_k׮}�>�F�\��\nř�ƍ2jݺu\�UW]�DcM��S�O�Y5\��\"r{\�]d�ZNud�\n�8��gye����C/l\�?�\�\�L��\�\�\�F��5�WN\�?\�f\�\�0�\�\�9\�w�q��\�\�|�q���ګ�y\�QarF�Bƍ�����WV\�?\�f\�\�0�\�\�9\�w�q��\��|�q6\�`\�\���m\�\�=ͭW�\�~\�\�q[\�Nm_u \�L��eUm9���\�ӗ������`�\�s�\�\"\�nYՁ�*\0\�l6�`\�#\�7�\\��\�3=~��\�⾼�\�\�\�@0\��\n7ʪ\�r\�_Y����������`�\�s�\�\"\�nYՁ�*\0\�l6�\�\�[/�\nu߭O\�^m{u \�L��eUm9���\�SK�\�_\�\�0�\�\�9\�w�q��\��|�q6�`��\�N	���\�\�j۫� gd*d\�(�j[\�\�e���Z�o�����QDn\�A����eU\�\0��\��[>vҔ@0��/o���:rF�Bƍ�����WV鿩��\�j\�?E\�\�\�ȸ[Vu`�\n�8ʪ�����q���m!G��U�oj\���\��F�=�.2\�U��0\�f\�S�s Tܗ�W\�^9#S!\�FYU\�B��+�\�\�\�\�\�W�\��\"r{\�]d\�-�:0_`�\�&\�\��3%*\�\�۫m������q���m!G��U�oj\���\��F�=�.2\�U��0\�f���\�-W�qJ(�\�⾼�\�\�\�@0\��\n7ʪ\�r\�_Y����������`�\�s�\�\"\�nYՁ�*\0\�l6� ꋟzߔ`�\�\�\�\�U�A\�\�TȸQVն���\�*�7}\���\��F�=�.2\�U��0\�f6mj\�\�S�A\�\�M\�^msu \�L��eUm9���\�Ӕ������`�\�s�\�\"\�nYՁ�*\0\�\�ɂ��G\�o�\��wL	���b��}j۪� gd*d\�(�j[\�\�e��۲\�\��Vm���ܞ�|w˪\�Wg3�M���w\\\�|\����\\�Ml\�\�\�\�������q���m!G��U�\�\��Tm���ܞ�|w˪\�Wg\��\'{U{�\�j\�\�W�A\�\�TȸQVն���\�*���ڬ\��F�=�.2\�U��0Φ�yU{��\�͏�f_9#S!\�FYU\�B��+�\���k�j\�?E\�\�\�ȸ[Vu`�\n�8�.\�\'�Q+?��}u \�L��eUm9���\���ͪ��`�\�s�\�\"\�nYՁ�*\0\�l�`�ګ�����q���m!G��U�O�Y�\�߶z衇�s\�=�Y�lYs\��7�\��\�~�MV�=n��c�؞\�ܞ�|w˪\�W\�ԢE��\�7\�^�`��\��\\ѫ/\�\�O��\�g�}\�\�\�\�\�\�\0`;eU�A\�ȝ\�\�c�5+W�l\�8\�椓N\�O\��.]ڟ\�-Y��9��\�7�\�\�\�\��\�\�ꫯ\�o���Ҫ���WV\�?\�f\�\���\�\�\'sͨ\�\�OwEn\�A����eU\�sbbb\�E��qy�6\�\�\�I*�_ߟ�m �U9#w\�\��\�ޜv\�i͡�ڜp\�	\��\�\�\�x\�\�]w\�\�<�\�\��m\�\��{\�\�\�v�rH󖷼�Y�zuzĺ7ʪ\�r\�_Y��T�U[�\�֊+�E�\�Ŕm�x�x<�\'r{\�]d\�-�:0_ݡ&&&\�썅�\�ظ��*/\��eU�A\�ȝ�v\�\�\�mo{[��.h֯_�7٪\�>�/�O=\�\�f͚5y�*7ʪ\�r\�_Y��T�U[�=�o|\�\�q\��N�u\�1\�4\�w\�V_���c���Q\�\��tG\�\�\�ȸ[Vu`��C,Z�\�,8�7�=�\�\�\�O�Ǎ\�\���\�S�\�\�\�_�\�\��\�}W�|\�\��p\�	�.]\���,Y\�\�^0{�w۝��r\�ix\�\���Ǚ`PVu l��;\�\�/\�/@�s\�9͆\r\�\�#�\�ǉ\�I\�\�vm�eUm9���\��ͪ���\�;\�h8\��-L.\\؜}\�\�\�}\�ݗ7ߪ\�>�/��\�\�\�c?tC\�\�\�ȸ[Vu`��\�z\�\�\���\�\�W���\�}�\�O�\�\�\�\��7�\�[�/n\�<\�\�fժUӾj�\��|\�cGy\�݇r\�^�\�Ƒ`PVu l�\�\�\�z׻�\�?���\�\�]\�%/�!y�\�\�ƚq���m!G��U�O�Y�\�\�Ln��\�)\��ַ���\�\�{\�#�\�\�ɋ9�?\��=�.2\�U��n����_\�\��\�1�\�\�\�\�_>�R�V�:��SN\�o\���\�g�!m�]l\�QG}�կ~\�M,xf~\�q\"�U�W�xq	�׿�\�\�#�<�\�\�!\�q_\��\�\�\�Q\�FYU\�B��+�\��j�j\�\�\�\�Ëر\�|\�E\�ͶK<\�\�ل\�3�\��=�.2\�U��n���{&\�\�\"\�|�����\�\�T�76|\�\�_~\�a�\�\\r\�%\�w�\�<v\�J|_|��E��>Ο0+�U�W�h\�|\�;�\�\�;�\�\�O<\�\��H\���QVն���\�*��ڬ\��/�kV_N\������\�q\�\�����fv\�\"�\� \�E\�ݲ�\�\�m׬��\�|�����kfC�zc\�\�,_���\�[�?��\�\�\�b�\�\�,^���.<8\�oeU�A�)V�\�}\�G\�ٙ\�Y\�\'ƞ\�\�??\�5v�eUm9���\��ͪ���\�v��K~\�\��I��\\�\�s�\�\"\�nYՁ�\�6y\�[Oc����\nę\�K�,ix\��<Nl�x�<pþ�\��¼Ϯʪ��\�Q��P��.���&\����-Z�h\�\�\Z\�FYU\�B��+�\��j�j\�a+V�>����\�\�!\�3�\�8\��=�.2\�U���lbbb\�\�x\�X\�\�\�cq�؀1v\�u\��\�;\�L\�\���p��\�\�>\�\��\�2���\�@0ȿ\ZE�\�8\�s\�\�\�\�\�}\�\�_/۸QVն���\�*��ڬ\��oX�p>X\0�\�3��?�,��2En\�A����eU\�#\�e�JO\�8\�c�\���\�7�\�M\�\�s\�\�/|d\��\��w�`PVu \�_�\�^��9\�C�G}4\�5/b�tPs\�m�\�Ɔq���m!G��U�O�Y�\�\�@oN2�\�oM�\�\�{\�&s*\�7���8\��=�.2\�U���$\�u_\�x:�W\0�q饗�}\�1\�l\�;\�V<�\�ŋ\�\��\�c\�*���\�@0ȿ\�9\�S�\�˗\�\�\�\\Мp\�	�\�a\�(�j[\�\�e��SmVm�7�\�1X\�8�\�\�\�\�\"\�;8�8\��=�.2\�U����7�-/i<�\�\�\���\�\���z�\�_��믿>�s\�\�k��jop�&GW	eU�A��(\�c�=ֿ6\��>�\�W��W�\�U�\�Gƍ�����WV\�?\�f\�\�!2\�ЂǼ�=8�>���SEn\�A����eU櫳�p\�§\�Ưͅ���\�\�c\�\�{Y\\F`��b?�\��W\�\�g�g\�c)Io\0��W\�Ϸg�AY\�V0�m�\�T�Bv|\�вe\�\�ͭ8�裛+��2\�\\��>��\�%��eո,\�\�n��Smָ\�\��l�\�\�sϝ\\\��w��)\�?8�8.\��=��\�v�a\�-�ښ��j6�ջ\�\�\�8�|��9\�s>v\�g\�q`N�|\�ɷ,X�`i>��\r�[�����`0\�~٩\��\����\�\�??\�܊}\�C��\�\�A<\�DڸQV�\�B��\�f\�?\�f�K�\r̦\���v\���\�\�\�*\�?8�RN$\���ܞ�|If;�0\�Um\�WG5��\�\���\�\�8�|��9\�S\�\�\�eE��\�\�[\�+�\�EC�\�Vp���j+̶_v*|!��\�on�\�\�|s+\�8^�\�W盋��`���q��\Z���\�\�\��\�\Z���M\�m��?\��Omq\�|��+e�ܞ�|Ir�\�7\��ø[V�5_\�l�kbb\�\�\�8�\�\�\�̲e\�6\�}\�\�y�S�\�~��z\�\r�XJ2\��=\�\0.�Um�i�d\�~٩\��\�#�<��\�\�`&qq�\�.�\�\�߯<�6n�U㲐��N�u�\��j�ƥ�r�Mׇ.���\�\��Ke�\�>�ዓ�|��\�\�|�\�jk�:�i�jJ\���gp{I\�iW�\�\0\�dɒ%�\�+_\�\���z�\�7\���\�d�;W\0/9�\���O\�裏N�}��馛��}\�kSn\��\��/|a\�\�%T[�`��\�\�\n_Ȏ�\�|�3�\�8\��\�\�E�\�\�E\r&\�%���\�g>\�lذ��\�ƍ\'o�\�7�1e\�u\�\�5�W�\�\�5k\�l\�}%׸,\�\�~\�5_�}s\�-�L�=��}�iӦ-��\�g?\�<\�\�#S#\�\��\�ۆ+���{\�\�\�\��x�Z�~��\�+�/\�\��\�\\\�eW�l�\Z��\�}�+�pbbb\�\�mg�\��\�X\�(K\�\�����{��z�\�\�\�\��#s\�m\�\�1v�jk�:�i�)W\�\�7/l<}4�{�1\�\�W���\����ǁ9\�\�ߣ\������A�\\\��\�\�>\���\�_��׷�דּ\�j����y�\�\�z\�q6H|�\�CMy�A]q\�\�\�;\�ܬZ�j\�}mW���V;������\��x0�8���7.UҸ1S�\�_�U\�\�׾�?Q�\�\��\�&>8\�\��\����\�b\�\�n������\��\�\��=��\�\���F}~\�+�|ƽ\��\�\�\�~\�\��\�1Y�3d\�\�x`�/\�\��7\���>�\�_��_hn�\�\�\�c��\��\�\�c�\�c\����ኾ\\�h\�\�dv\�\�wo��\�\�\�X�ti\�򗿼��\�˷��_�\�_\�bAsP1��	\�ڵk��=.s��ݑ�>\�^s\�3\�\\\�eԨ}\�V�MW�\�S[��yr֡,�_�Zm��3U����vZ�\�~\���\�v�!s�:Ɩ4�\�T�\�3.U\�x���\���\�;\�\�\��\�\��\�\�\�ܯ�gd_�_�oՊW��\�O�dr\�\��?���\'��:/*\�+\�Oy\�S��>���:�@��\�\�\��s\�9g\�>�\"p\��\��3�\�}mW[�pO\�\'\�\�\�N�/dt\�A���>\�\�W�\"\�\\�i~��\�\�[\�c2\�\�g?��c�\�?���\�\��\�>\�\�_�\�\�1a�ٟ�\�ɿ�\�5�\�/\�\�s\�=S�\�\Z�3s\�\�w�\�\�\�q\�\�>y\�s�\��\�\��N\�ۿ�\�\�QG\��گ�Z�x�5�w\�\��`٘�\�\����I\�\�]\0W]uU\�\��\�[<��\��\�\�/}\�/�<�\�\��\�x���}\���\�ƻba�\�_�r�\��￿{|\�G1��q,�ǋ\���\����1n����\�\�;�7�5�}9\�>�*�צ�q鿁\�\�\��3���\�\�C�8�χ\�\��\n��F\�X�뮻\�_������ؚOȊ�\�[\��:S�5_\�4}5��\�\�\��텍�\�Ȇqv\�\�>:\�\�3�\��\�>�_\����E ()\�z\�\�/�\�/M�=�\�?�\�O\��>\�\�S�׽\�u[l�\���\��?�\���?i�E\�\�Ţ\�\�\��/K\�\��������YM�����`0\�~٩\��\��p\��f\�}\�%�\�\�@I\�\�t\����\��\�\�b�\�\�?�\�\�=\�Bv|`h|\�Lo\�/�\�e!����/b0\��|�\��baL@c�0��˿�\�\�m/�\��\�E/zѴ���Im<�\r\�\�������u,X?\��\�\��C?\�C�	p<�\r�\�E�\��\�}�O�������2\�{n����\���x\�x\�&w\�ʕ�\�\�g\�/k\�\��?�?\r�H3��\�\�/G鳨�{m�\Z���M�F6��=��\�~\�o��G\�\�L\��w��\��j�\�3\��8�\�\��ק�#x�1�\�\�u�jk�:�\�\�\�~��\r�\�\�O�B��9�>�\�_\�\\�\�c)\�\�\�a%�XL�	�\�-W?\�?\�?{-��3\�\�\�!�]�i�LǤ���N\���Bvl�M\�Gԛ\��\�-��j+̶_v*|!;Τ��pK�<}\�a�囋6\�\�&\�\�J\Z7rŋ_O}\�S�;?\�s?\�?�%ޡ18�\�\��]Ȏ\�&.Uu�\�oq_\�\����R�/\�Iq\�g\��#���E,�\�\�h.�\�\�\�\�>\�l��8\\y�0*^<���稘\�ٵq�\���\�x��~\�_\�&ƒ\�\�\���\���D��\�8\�\�[b\���3�c3\�\���=��oG����\�\�}�-}\�f�MW\�\��\�øo�]�\�\��>f\��=��\�v�\�ָ;]\�	Tq�\�\�mq����Ӄږ1��\�u�jk�:�\�\�\�\�\�\�U%��q\\�X�1r\�\�\'��~>q\��,X�`i>��lm�VR0�뮻�zF\�`\�9δ~\����\�\��t\�Y������rʔ\�T���?�O�\�k�\�\n�헝\n_\�~\�\�\�|\�C\�7�\�\���\��=\�%[�8+i\�\�ׇ����\�[\�㶙\�Ȏ?c̈�o�ş\�qƂc\�gw\���T㲐\�v�\�\�\�S����\�����y�o�7\�<?\�c?\�|\�s�\�\�\�`��.�hV�Qq&m\\k�]v\�\�U��_�x\��ǎm\�\���bŊ�v1\�}ի^տ\�\�m\�\�X��\�\\v\�e�c�\�k\�\�9\�z�1��\�r\r�\�\�\�?�\�S�oG���ۚ���\�\�\�%\�\�t5.�70�>\\�l\�\�bG�\�٦\��\�X\�(K\�\�\�K2\��\�|��[����\��\�r ÷�2Ɩ:�\�Tm\�WG5��\�\���\�\�8�|��\�\r\�/^����<̉\�~\�\��\��I\�3\�tQI� Ί\�\�B\�\�E\�-�1���?�\�7\�zֳ&\�䎳�c����\�\��\�\�#%���:\�DQ��\�\�\�c�\�7�\"\�Ǝ\�G��\�\�z�\�\�\�_\���bgdƇ�$�c2o\���H\\�泟�\�\�,�\�m!\�\�\�e��w\�y\�K^\����\n\�q\��gD\�\�qi��:�h6��z\�Ӟ\��3>�\���n\�?OE\rn,d\�X\�}q��xQ�i�ς��ώ�boa�kf�\�_�u�\�ȷ\�~{�E�᳸\�\��||;�\�\�\�\�\\\�\�}\�v�MW�\�_�K;\�\�<����\�qQ�\�\�9\�wQ\�\�L\��\��,X\��c���\�R#\�ۍ:Ɩ8�\�T��\�Zo\�ڻ�\�\�裏~|h!{\�|���9\�\�#�8_��\�?��\��rM>��*)�^�z���3=\��\��b\�ju\\`p\�`q ?~\�;\��\�\��\��_\�ٚ\�w���][Ձ`�%�/h-Z��D�b�q\��z�m��4n\�K�\�Y�1����8\�e�3�5��\�P|�Νw\�9\�K�\�r\��\�ŧ�u�\��?�\�?\�\�T|XS�/t\�;?\��ٞ�:�\���\�\�O^\�\�\"{\�w�E\�j�\�v\�?�\r�cR\�Ҍ33cB�q\�\��7^��\�ü���u5\�äbq\�\�,�����\��\�\�\�Q��\�\�/��Ϣ\�\�骶��1�;�?�6\�~���\�\�\�T�\�s�\�6\�\�\�*\�\�\��\�׾�?\�\�e-\�]31\�;�\����\�2Ɩ6�\�T��\�\��\��66�n�\�\�\�\n��\�@�\�QG\�\�\�|\�;yLءz�oo`�c\�}\�}A>��*%D\�R��=��\�\�\�\�E\�\�oǊ\�\�d\�?�\�?fuFv\\O2\�ƌ\�\rn{�^\�\r�\Z�mU�A��(N|X_�HѦ\��\����0\�UI\�F��(\�\�\�$#\�t9�쳧,d\�\�\�?<y\�\�Bv�\�o\�3�g�\�ؒ�����\�8�?�\'gX���A���\����\�Z\�\��\�\�v���\Z\\B$���\�}��\�o\�\�|L��>�\�\�\�\�\�\�u�\�8��nذa\�\�\�\�\�{{\�g\�7���\�\�gqE\�\���o\�k.�r\�>�j�צ�\��o`\�ҥ��\�<׆\�\�\�\�x(O\�\�仨�qw����#ƻzc\�<\�\�/����c,�ێ:Ɩ8�\�T����7�-/i<�\�\�\���#�8\�K.�\�\�<(\�@��\�?�\�{z\�%y\�]VJ0��b��jժ)�Gœ{L\�\" ĥEgd\�D.\�\�1����\�\��\���W\�\�4\�\��Q�\��8q�Z\\�._b�q6vǸʿG%UL&v\�y\�樣�꿠?q�\��[9\�w=\�j\�k��&\'7\�|s���X`�3�\�z�\�o�\�\�x�lɒ%S\�SRն�3��� _\�\�p,\ZƂaL$��կ6\�֭�\�6�{\�6\�\�gV\�ٱ\�I\\�r���\����\�\�\�\�oQ�\��3�~\�~��K��bQ��9\�]`���\�Ό�\�E\�\�\�x\��bb}\�YgM��;|]\�8\�;.Kǖ��;�\�\�\�\�\\\�\�(}g\�u\\\�\�骶��\���G\\s�\�\"��\�~\�\�Cy\"�\� \�Em���\"F���\�\���\�Qq�\�8\�xN�05\�[\�s�(Ձ�\�H\�\�w\��=�\��\�\'/+Ǔ�S�p��\� �\�\��|v�ޓ\�9��em\�\'\�\�J3UL\�b�)\�>��~X�g\��\�\��]�\�3\�8��a1\�l�Ƙ�E\0ɏ�\�\�g*\��\�:\�F�\�l\���\�7ϋ\�O?�9\�\�\�c�\�q#*ޖF\�_�v\��s\�x\�\�<g\�g\��\��\�g$�X�-\�\�e�\�[\�\�,��:Μ�?c�0&�y\��`ٸ~�=^ ��>l4*3\�\�\Z\�.z\�\�k�\�\�sp\��}\�oO�\�\������3^D[�~���\�\�\�\�\�\\\�\�(}\�v�MW�\�߰x\�u�\�\�ַ�5\�=�b�}\�qP�\�\�9\�wQ\�\�\�*\�q	\�X؎�\�]Nw\r\�Q\�\�\�י�\�Ց\�ƲU%��q�؀1711\�:\�\�\�\�\�K_�\�ŽAe\�8�:VZ0��d\�\�j�\�u�:\�G�\",\�Y�\�\�\�|�KTċ\"��qV򸱭5<\�\�Zն�3\��\�1\�\�\�֞{�\�~\\K��S�\�U[�\r�K\�-~\�?�a>\�~�\�\�A�\"�\� \�E���\�Ձ�\�\�&&&\�\�g��<�>Ǒ�\r��>\��\�Qgf?q&\���:(\�keU�A�)V�\��\�?�_�\�W��\�\�qg\�(�j[\�\�e��SmVm��w\�q�� \�Bz��q.\�\�\�en���S�\�\�9\�w�q��\��|u�,X�\��\�\�\�K�\��\�1�3�_�\�W>r\�>��\0\��\�5�׍\�\��AYՁ`�U�\�����\�\����K\��\�V\�\�_\r�eUm9���\��ͪ�����V\\Gw�\�ҝ�ŗx\�\�˙\�~c��+r{\�]d\�-�:0_\�&�\�\����\�\�\�8�N����\�c*�`��g.\\�\�\�ŋ��\�\�k��\�c�\�\�cZ�\��\�?x^o0��W�\�\�\�\�\'�AYՁ`�e�\��׼�9\�\�#\�\�\�\��p�C=���Z7ʪ\�r\�_Y��T�U[�M\�;\�\�\�\�\�.�(o�]\�\�\�Ď}\�~)[\�\�\�ȸ[Vu`��\�z\�\�w\�\�\�3��\�\�\��X��\��\�/\�\r+\�\����t\�I�[�b\�-�\�~�==\�\�\�{\�Ȇ|\�\�[o�\�S�\�G.<\�\�#/\�m�Plߗk	eU�A~.\�8S:>�\�;\�\�wm���\�+^\�j\�\�0n�U�-\�迲J��6����I�\�7�� v\�\�\�MGߟ>�l^\�rώ�=�.2\�U��n����_^̞\�\�4\��\�\�\0з\�>�<�7P,Y�`�E�??948ş�|\�\�%�]�\�q&�U�9�3\�\�\��\�󶷽�ٰaC�{$\�⧞zj2W\�5�3\�FYU\�B��+�\��j�j뿭�3��/39\�\�\��C�c����8�\�L\�\�ܞ�|w˪\�W�\�~\�=3{\�2#;b<MgaG\�\�;`T�AYՁ`���;e͚5\�	\'�\�_\�~\��\�\�<�\��y��Z�n]s\�Yg\��L<N<^��eUm9���\��ͪ���L\\�z� �+>3\�\�\�kn�\�\�殻\�\�_\Z-ğ\�\��=\�\�\�\�F\�\�&v�Dn\�A����eU\�;D\\����\�гO�>�\�)\���\�\�\0\�H0(�:RD\�\�n��9\�\�c�\�{\\�xq�U\�k�����\�\�\�\���?W�^ݬ\\��9\�3��\�\�\�\�\�\�̸QVն���\�*��ڬ\��o�V�X�\�3nO\�\�\�\�\�=�\�s�\�\"\�nYՁ�\�՛�\�\�W\�qkU<^\�\0#ʪ���;->\��+�h��\���9\�\�����\�h\�\��{\�\�\�v��\�\�qg\�(�j[\�\�e��SmVm�7�/��Y�ti^H�U\�\�\�\�\�]�\�s�\�\"\�nYՁ�ꜘ��xQol\\ޫ\�y�|��\�\�\�\�\�`eU�A\�\�TȸQVն���\�*��ڬ\��o[=\�\�C͹\�\�,[��9�\����X^h��\�\�ql\�\�}�\�s�\�\"\�nYՁ�\�Z�h\�\�z\�\�\�,�\�ޟ+z\�\�^}\�\�4����x\���c��\0l���\�@0\��\n7ʪ\�r\�_Y��T�U[��(\"�\� \�E\�ݲ�\�U\0ƙ`PVu \�L��eUm9���\��ͪ��`�\�s�\�\"\�nYՁ�*\0\�L0(�:rF�Bƍ�����WV\�?\�f\�\�0�\�\�9\�w�q��\��|�q&�U9#S!\�FYU\�B��+�\��j�j\�?E\�\�\�ȸ[Vu`�\n�8ʪ�����q���m!G��U�O�Y�\��\"r{\�]d\�-�:0_`�	eU�A\�\�TȸQVն���\�*��ڬ\��F�=�.2\�U��0\����� gd*d\�(�j[\�\�e��SmVm���ܞ�|w˪\�Wg�AYՁ`�322n�U�-\�迲J��6����QDn\�A����eU\�\0�3���\�@0\��\n7ʪ\�r\�_Y��T�U[��(\"�\� \�E\�ݲ�\�U\0ƙ`PVu \�L��eUm9���\��ͪ��`�\�s�\�\"\�nYՁ�*\0\�L0(�:rF�Bƍ�����WV\�?\�f\�\�0�\�\�9\�w�q��\��|�q&�U9#S!\�FYU\�B��+�\��j�j\�?E\�\�\�ȸ[Vu`�\n�8ʪ�����q���m!G��U�O�Y�\��\"r{\�]d\�-�:0_`�	eU�A\�\�TȸQVն���\�*��ڬ\��F�=�.2\�U��0\����� gd*d\�(�j[\�\�e��SmVm���ܞ�|w˪\�Wg�AYՁ`�322n�U�-\�迲J��6����QDn\�A����eU\�\0�3���\�@0\��\n7ʪ\�r\�_Y��T�U[��(\"�\� \�E\�ݲ�\�U\0\�ي+߸q\�\'(5�\��9�\��M�gT����Po܈~�\�\�j�+~�-\�\�rJ��6�\���QDn\�A��\�W˩�\�Wg+W�\\\��Ly�R\�__�\�?\��\�?�\�\�L���\�ƸQF\�w\�}\�UW]�DcM��S�O�Y5\��\"r{\�]d�ZNud�\n�8\�M\0^�\��kk׮}\�+\�\�T\��}헾\��\�B�=�zq�&gd*t\�]w5W_}u�n\�:g&�T\���8W^ye�\�Q�\�~\�?�\�f\�\�0�\�\�9\�w��j�ձ�*\0\�.��\�\�^m��h�y�����.�����T,ęp\�\�������Zq\�_���\�_�Us��lEn\�A��>f�\�vui�\n\00)gd\0\0�0�\�s�\0��\�\0\0&r{\�\0\0P���\0�\�Dn\�A\0\0j�32\0\0P�\�\�9\�\0@MrF\0\0\n�=y\0\0�I\�\�\0\0@a\"�\� \0\05\�\0\0(L\�\�\�\0�&9#\0\0��ܞ�<\0\0\�$gd\0\0�0�\�s�\0��\�\0\0&r{\�\0\0P���\0�\�Dn\�A\0\0j�32\0\0P�\�\�9\�\0@MrF\0\0\n�=y\0\0�I\�\�\0\0@a\"�\� \0\05\�\0\0(L\�\�\�\0�&9#\0\0��ܞ�<\0\0\�$gd\0\0�0�\�s�\0��\�\0\0&r{\�\0\0P���\0�\�Dn\�A\0\0j�32\0\0P�\�\�9\�\0@MrF\0\0\n�=y\0\0�I\�\�\0\0@a\"�\� \0\05\�\0\0(L\�\�\�\0�&9#\0\0��ܞ�<\0\0\�$gd\0\0�0�\�s�\0��\�\0\0&r{\�\0\0P���\0�\�Dn\�A\0\0j�32\0\0P�\�\�9\�\0@MrF\0\0\n�=y\0\0�I\�\�\0\0@a\"�\� \0\05\�\0\0(L\�\�\�\0�&9#\0\0��ܞ�<\0\0\�$gd\0\0�0�\�s�\0��\�\0\0&r{\�\0\0P���\0�\�Dn\�A\0\0j�32\0\0P�\�\�9\�\0@MrF\0\0\n�=y\0\0�I\�\�\0\0@a\"�\� \0\05\�\0\0(L\�\�\�\0�&9#\0\0��ܞ�<\0\0\�$gd\0\0�0�\�s�\0��\�\0\0&r{\�\0\0P���\0�\�Dn\�A\0\0j�32\0\0P�\�\�9\�\0@MrF\0\0\n�=y\0\0�I\�\�\0\0@a\"�\� \0\05\�\0\0(L\�\�\�\0�&9#\0\0��ܞ�<\0\0\�$gd\0\0�0�\�s�\0��\�\0\0&r{\�\0\0P���\0�\�Dn\�A\0\0j�32\0\0P�\�\�9\�\0@MrF\0\0\n�=y\0\0�I\�\�\0\0@a\"�\� \0\05\�\0\0(L\�\�\�\0�\Z�\�\�\�7o\�9\0\0(ĦM�\�\�����\�\0�\Z\�~\���\�rV\0\0\nq饗^֋\�7\�,\0\0\�\�}\�\�߼\�^{}+�e\0\0�\�\�nW\�����\�\0�&;\�\�\�\��\�\�]�\�\�]f\0\0ڷiӦ�q&\�n�\�\��^f�:r{\�\0\0P�\�\�\�\�\�^m\�\�$��RJ)��j�\"�G>��n\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\�\��}��*\�3�j\0\0\0\0IEND�B`�',1),('1864607918230319105',1,'var-entity',NULL,_binary '�\�\0sr\0java.util.LinkedHashMap4�N\\l��\0Z\0accessOrderxr\0java.util.HashMap\��\�`\�\0F\0\nloadFactorI\0	thresholdxp?@\0\0\0\0\0w\0\0\0\0\0\0t\0idt\01864607914954567681t\0	leaveTypet\01t\0	startDatet\02024-12-05 00:00:00t\0endDatet\02025-01-15 00:00:00t\0	leaveDayssr\0java.lang.Integer⠤\���8\0I\0valuexr\0java.lang.Number����\��\0\0xp\0\0\0*t\0remarkt\0玩t\0statust\0draftx\0',NULL),('1864607918247096321',1,'hist.var-entity',NULL,_binary '�\�\0sr\0java.util.LinkedHashMap4�N\\l��\0Z\0accessOrderxr\0java.util.HashMap\��\�`\�\0F\0\nloadFactorI\0	thresholdxp?@\0\0\0\0\0w\0\0\0\0\0\0t\0idt\01864607914954567681t\0	leaveTypet\01t\0	startDatet\02024-12-05 00:00:00t\0endDatet\02025-01-15 00:00:00t\0	leaveDayssr\0java.lang.Integer⠤\���8\0I\0valuexr\0java.lang.Number����\��\0\0xp\0\0\0*t\0remarkt\0玩t\0statust\0draftx\0',NULL),('1864607918251290625',1,'var-userList',NULL,_binary '�\�\0sr\0java.util.ArrayListx�\��\�a�\0I\0sizexp\0\0\0w\0\0\0t\01t\03x',NULL),('1864607918251290627',1,'hist.var-userList',NULL,_binary '�\�\0sr\0java.util.ArrayListx�\��\�a�\0I\0sizexp\0\0\0w\0\0\0t\01t\03x',NULL),('1864607918251290628',1,'var-userList2',NULL,_binary '�\�\0sr\0java.util.ArrayListx�\��\�a�\0I\0sizexp\0\0\0w\0\0\0t\01t\03x',NULL),('1864607918251290630',1,'hist.var-userList2',NULL,_binary '�\�\0sr\0java.util.ArrayListx�\��\�a�\0I\0sizexp\0\0\0w\0\0\0t\01t\03x',NULL),('1864608563805007874',1,'source',NULL,_binary '<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<definitions xmlns=\"http://www.omg.org/spec/BPMN/20100524/MODEL\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:bpmndi=\"http://www.omg.org/spec/BPMN/20100524/DI\" xmlns:omgdc=\"http://www.omg.org/spec/DD/20100524/DC\" xmlns:bioc=\"http://bpmn.io/schema/bpmn/biocolor/1.0\" xmlns:flowable=\"http://flowable.org/bpmn\" xmlns:di=\"http://www.omg.org/spec/DD/20100524/DI\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" targetNamespace=\"http://www.flowable.org/processdef\">\n  <process id=\"leave6\" name=\"请假流程（子流程）\">\n    <startEvent id=\"startNode1\" name=\"开始\">\n      <outgoing>Flow_1mu0fz5</outgoing>\n    </startEvent>\n    <userTask id=\"Activity_0zy3g6j\" name=\"申请人\" flowable:formKey=\"static:1\">\n      <incoming>Flow_1mu0fz5</incoming>\n      <outgoing>Flow_03931vt</outgoing>\n    </userTask>\n    <sequenceFlow id=\"Flow_1mu0fz5\" sourceRef=\"startNode1\" targetRef=\"Activity_0zy3g6j\" />\n    <sequenceFlow id=\"Flow_03931vt\" sourceRef=\"Activity_0zy3g6j\" targetRef=\"Activity_15to8yb\" />\n    <subProcess id=\"Activity_15to8yb\">\n      <incoming>Flow_03931vt</incoming>\n      <outgoing>Flow_0e4ru1l</outgoing>\n      <startEvent id=\"Event_14f0d54\">\n        <outgoing>Flow_0y1q06j</outgoing>\n      </startEvent>\n      <userTask id=\"Activity_0x88dc2\" name=\"组长\" flowable:assignee=\"1\">\n        <extensionElements />\n        <incoming>Flow_0y1q06j</incoming>\n        <outgoing>Flow_1kj44n7</outgoing>\n      </userTask>\n      <sequenceFlow id=\"Flow_0y1q06j\" sourceRef=\"Event_14f0d54\" targetRef=\"Activity_0x88dc2\" />\n      <userTask id=\"Activity_15z7u3k\" name=\"部门副经理\" flowable:assignee=\"1\">\n        <extensionElements />\n        <incoming>Flow_1kj44n7</incoming>\n        <outgoing>Flow_1lrkvhq</outgoing>\n      </userTask>\n      <sequenceFlow id=\"Flow_1kj44n7\" sourceRef=\"Activity_0x88dc2\" targetRef=\"Activity_15z7u3k\" />\n      <userTask id=\"Activity_1p3e8iu\" name=\"部门经理\" flowable:assignee=\"1\">\n        <extensionElements />\n        <incoming>Flow_1lrkvhq</incoming>\n        <outgoing>Flow_161ozbd</outgoing>\n      </userTask>\n      <sequenceFlow id=\"Flow_1lrkvhq\" sourceRef=\"Activity_15z7u3k\" targetRef=\"Activity_1p3e8iu\" />\n      <endEvent id=\"Event_0ategpj\">\n        <incoming>Flow_161ozbd</incoming>\n      </endEvent>\n      <sequenceFlow id=\"Flow_161ozbd\" sourceRef=\"Activity_1p3e8iu\" targetRef=\"Event_0ategpj\" />\n    </subProcess>\n    <userTask id=\"Activity_03nnma3\" name=\"总经理\" flowable:assignee=\"1\">\n      <extensionElements />\n      <incoming>Flow_0e4ru1l</incoming>\n      <outgoing>Flow_19akbau</outgoing>\n    </userTask>\n    <sequenceFlow id=\"Flow_0e4ru1l\" sourceRef=\"Activity_15to8yb\" targetRef=\"Activity_03nnma3\" />\n    <endEvent id=\"Event_1ixvcma\">\n      <incoming>Flow_19akbau</incoming>\n    </endEvent>\n    <sequenceFlow id=\"Flow_19akbau\" sourceRef=\"Activity_03nnma3\" targetRef=\"Event_1ixvcma\" />\n  </process>\n  <bpmndi:BPMNDiagram id=\"BPMNDiagram_flow\">\n    <bpmndi:BPMNPlane id=\"BPMNPlane_flow\" bpmnElement=\"leave6\">\n      <bpmndi:BPMNShape id=\"BPMNShape_startNode1\" bpmnElement=\"startNode1\" bioc:stroke=\"\">\n        <omgdc:Bounds x=\"240\" y=\"200\" width=\"30\" height=\"30\" />\n        <bpmndi:BPMNLabel>\n          <omgdc:Bounds x=\"242\" y=\"237\" width=\"23\" height=\"14\" />\n        </bpmndi:BPMNLabel>\n      </bpmndi:BPMNShape>\n      <bpmndi:BPMNShape id=\"Activity_0zy3g6j_di\" bpmnElement=\"Activity_0zy3g6j\">\n        <omgdc:Bounds x=\"320\" y=\"175\" width=\"100\" height=\"80\" />\n        <bpmndi:BPMNLabel />\n      </bpmndi:BPMNShape>\n      <bpmndi:BPMNShape id=\"Activity_0b5ys91_di\" bpmnElement=\"Activity_15to8yb\" isExpanded=\"true\">\n        <omgdc:Bounds x=\"460\" y=\"115\" width=\"750\" height=\"200\" />\n      </bpmndi:BPMNShape>\n      <bpmndi:BPMNShape id=\"Event_14f0d54_di\" bpmnElement=\"Event_14f0d54\">\n        <omgdc:Bounds x=\"500.33333333333326\" y=\"197\" width=\"36\" height=\"36\" />\n      </bpmndi:BPMNShape>\n      <bpmndi:BPMNShape id=\"Activity_0x88dc2_di\" bpmnElement=\"Activity_0x88dc2\">\n        <omgdc:Bounds x=\"590\" y=\"175\" width=\"100\" height=\"80\" />\n        <bpmndi:BPMNLabel />\n      </bpmndi:BPMNShape>\n      <bpmndi:BPMNShape id=\"Activity_15z7u3k_di\" bpmnElement=\"Activity_15z7u3k\">\n        <omgdc:Bounds x=\"750\" y=\"175\" width=\"100\" height=\"80\" />\n        <bpmndi:BPMNLabel />\n      </bpmndi:BPMNShape>\n      <bpmndi:BPMNShape id=\"Activity_1p3e8iu_di\" bpmnElement=\"Activity_1p3e8iu\">\n        <omgdc:Bounds x=\"910\" y=\"175\" width=\"100\" height=\"80\" />\n        <bpmndi:BPMNLabel />\n      </bpmndi:BPMNShape>\n      <bpmndi:BPMNShape id=\"Event_0ategpj_di\" bpmnElement=\"Event_0ategpj\">\n        <omgdc:Bounds x=\"1072\" y=\"197\" width=\"36\" height=\"36\" />\n      </bpmndi:BPMNShape>\n      <bpmndi:BPMNEdge id=\"Flow_0y1q06j_di\" bpmnElement=\"Flow_0y1q06j\">\n        <di:waypoint x=\"536\" y=\"215\" />\n        <di:waypoint x=\"590\" y=\"215\" />\n      </bpmndi:BPMNEdge>\n      <bpmndi:BPMNEdge id=\"Flow_1kj44n7_di\" bpmnElement=\"Flow_1kj44n7\">\n        <di:waypoint x=\"690\" y=\"215\" />\n        <di:waypoint x=\"750\" y=\"215\" />\n      </bpmndi:BPMNEdge>\n      <bpmndi:BPMNEdge id=\"Flow_1lrkvhq_di\" bpmnElement=\"Flow_1lrkvhq\">\n        <di:waypoint x=\"850\" y=\"215\" />\n        <di:waypoint x=\"910\" y=\"215\" />\n      </bpmndi:BPMNEdge>\n      <bpmndi:BPMNEdge id=\"Flow_161ozbd_di\" bpmnElement=\"Flow_161ozbd\">\n        <di:waypoint x=\"1010\" y=\"215\" />\n        <di:waypoint x=\"1072\" y=\"215\" />\n      </bpmndi:BPMNEdge>\n      <bpmndi:BPMNShape id=\"Activity_03nnma3_di\" bpmnElement=\"Activity_03nnma3\">\n        <omgdc:Bounds x=\"1260\" y=\"175\" width=\"100\" height=\"80\" />\n        <bpmndi:BPMNLabel />\n      </bpmndi:BPMNShape>\n      <bpmndi:BPMNShape id=\"Event_1ixvcma_di\" bpmnElement=\"Event_1ixvcma\">\n        <omgdc:Bounds x=\"1412\" y=\"197\" width=\"36\" height=\"36\" />\n      </bpmndi:BPMNShape>\n      <bpmndi:BPMNEdge id=\"Flow_1mu0fz5_di\" bpmnElement=\"Flow_1mu0fz5\">\n        <di:waypoint x=\"270\" y=\"215\" />\n        <di:waypoint x=\"320\" y=\"215\" />\n      </bpmndi:BPMNEdge>\n      <bpmndi:BPMNEdge id=\"Flow_03931vt_di\" bpmnElement=\"Flow_03931vt\">\n        <di:waypoint x=\"420\" y=\"215\" />\n        <di:waypoint x=\"460\" y=\"215\" />\n      </bpmndi:BPMNEdge>\n      <bpmndi:BPMNEdge id=\"Flow_0e4ru1l_di\" bpmnElement=\"Flow_0e4ru1l\">\n        <di:waypoint x=\"1210\" y=\"215\" />\n        <di:waypoint x=\"1260\" y=\"215\" />\n      </bpmndi:BPMNEdge>\n      <bpmndi:BPMNEdge id=\"Flow_19akbau_di\" bpmnElement=\"Flow_19akbau\">\n        <di:waypoint x=\"1360\" y=\"215\" />\n        <di:waypoint x=\"1412\" y=\"215\" />\n      </bpmndi:BPMNEdge>\n    </bpmndi:BPMNPlane>\n  </bpmndi:BPMNDiagram>\n</definitions>\n',NULL),('1864609413235453953',1,'source',NULL,_binary '<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<definitions xmlns=\"http://www.omg.org/spec/BPMN/20100524/MODEL\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:bpmndi=\"http://www.omg.org/spec/BPMN/20100524/DI\" xmlns:omgdc=\"http://www.omg.org/spec/DD/20100524/DC\" xmlns:bioc=\"http://bpmn.io/schema/bpmn/biocolor/1.0\" xmlns:flowable=\"http://flowable.org/bpmn\" xmlns:di=\"http://www.omg.org/spec/DD/20100524/DI\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" targetNamespace=\"http://www.flowable.org/processdef\">\n  <process id=\"leave2\" name=\"请假流程（排他网关）\">\n    <startEvent id=\"startNode1\" name=\"开始\">\n      <outgoing>Flow_0q78air</outgoing>\n    </startEvent>\n    <userTask id=\"Activity_19b1i4j\" name=\"申请人\" flowable:formKey=\"static:1\">\n      <incoming>Flow_0q78air</incoming>\n      <outgoing>Flow_129vtbe</outgoing>\n    </userTask>\n    <sequenceFlow id=\"Flow_0q78air\" sourceRef=\"startNode1\" targetRef=\"Activity_19b1i4j\" />\n    <userTask id=\"Activity_0r8rs5v\" name=\"组长\" default=\"Flow_1z12r58\" flowable:assignee=\"1\">\n      <extensionElements />\n      <incoming>Flow_129vtbe</incoming>\n      <outgoing>Flow_1z12r58</outgoing>\n      <outgoing>Flow_0bt4srq</outgoing>\n    </userTask>\n    <sequenceFlow id=\"Flow_129vtbe\" sourceRef=\"Activity_19b1i4j\" targetRef=\"Activity_0r8rs5v\" />\n    <userTask id=\"Activity_0iw78d3\" name=\"部门领导\" flowable:candidateGroups=\"1,2,3,4\">\n      <incoming>Flow_1z12r58</incoming>\n      <outgoing>Flow_0nj4k00</outgoing>\n    </userTask>\n    <userTask id=\"Activity_1ex621m\" name=\"总经理\" flowable:assignee=\"1\">\n      <extensionElements />\n      <incoming>Flow_0bt4srq</incoming>\n      <outgoing>Flow_0fo3v6j</outgoing>\n    </userTask>\n    <endEvent id=\"Event_1shlpsv\">\n      <incoming>Flow_0nj4k00</incoming>\n      <incoming>Flow_0fo3v6j</incoming>\n    </endEvent>\n    <sequenceFlow id=\"Flow_0nj4k00\" sourceRef=\"Activity_0iw78d3\" targetRef=\"Event_1shlpsv\" />\n    <sequenceFlow id=\"Flow_0fo3v6j\" sourceRef=\"Activity_1ex621m\" targetRef=\"Event_1shlpsv\" />\n    <sequenceFlow id=\"Flow_1z12r58\" sourceRef=\"Activity_0r8rs5v\" targetRef=\"Activity_0iw78d3\" />\n    <sequenceFlow id=\"Flow_0bt4srq\" sourceRef=\"Activity_0r8rs5v\" targetRef=\"Activity_1ex621m\">\n      <conditionExpression xsi:type=\"tFormalExpression\">${entity.leaveDays &gt; 2}</conditionExpression>\n    </sequenceFlow>\n  </process>\n  <bpmndi:BPMNDiagram id=\"BPMNDiagram_flow\">\n    <bpmndi:BPMNPlane id=\"BPMNPlane_flow\" bpmnElement=\"leave2\">\n      <bpmndi:BPMNShape id=\"BPMNShape_startNode1\" bpmnElement=\"startNode1\" bioc:stroke=\"\">\n        <omgdc:Bounds x=\"240\" y=\"200\" width=\"30\" height=\"30\" />\n        <bpmndi:BPMNLabel>\n          <omgdc:Bounds x=\"242\" y=\"237\" width=\"23\" height=\"14\" />\n        </bpmndi:BPMNLabel>\n      </bpmndi:BPMNShape>\n      <bpmndi:BPMNShape id=\"Activity_19b1i4j_di\" bpmnElement=\"Activity_19b1i4j\">\n        <omgdc:Bounds x=\"320\" y=\"175\" width=\"100\" height=\"80\" />\n        <bpmndi:BPMNLabel />\n      </bpmndi:BPMNShape>\n      <bpmndi:BPMNShape id=\"Activity_0r8rs5v_di\" bpmnElement=\"Activity_0r8rs5v\">\n        <omgdc:Bounds x=\"470\" y=\"175\" width=\"100\" height=\"80\" />\n        <bpmndi:BPMNLabel />\n      </bpmndi:BPMNShape>\n      <bpmndi:BPMNShape id=\"Activity_0iw78d3_di\" bpmnElement=\"Activity_0iw78d3\">\n        <omgdc:Bounds x=\"640\" y=\"100\" width=\"100\" height=\"80\" />\n        <bpmndi:BPMNLabel />\n      </bpmndi:BPMNShape>\n      <bpmndi:BPMNShape id=\"Activity_1ex621m_di\" bpmnElement=\"Activity_1ex621m\">\n        <omgdc:Bounds x=\"640\" y=\"250\" width=\"100\" height=\"80\" />\n        <bpmndi:BPMNLabel />\n      </bpmndi:BPMNShape>\n      <bpmndi:BPMNShape id=\"Event_1shlpsv_di\" bpmnElement=\"Event_1shlpsv\">\n        <omgdc:Bounds x=\"802\" y=\"197\" width=\"36\" height=\"36\" />\n      </bpmndi:BPMNShape>\n      <bpmndi:BPMNEdge id=\"Flow_0q78air_di\" bpmnElement=\"Flow_0q78air\">\n        <di:waypoint x=\"270\" y=\"215\" />\n        <di:waypoint x=\"320\" y=\"215\" />\n      </bpmndi:BPMNEdge>\n      <bpmndi:BPMNEdge id=\"Flow_129vtbe_di\" bpmnElement=\"Flow_129vtbe\">\n        <di:waypoint x=\"420\" y=\"215\" />\n        <di:waypoint x=\"470\" y=\"215\" />\n      </bpmndi:BPMNEdge>\n      <bpmndi:BPMNEdge id=\"Flow_0nj4k00_di\" bpmnElement=\"Flow_0nj4k00\">\n        <di:waypoint x=\"740\" y=\"140\" />\n        <di:waypoint x=\"771\" y=\"140\" />\n        <di:waypoint x=\"771\" y=\"215\" />\n        <di:waypoint x=\"802\" y=\"215\" />\n      </bpmndi:BPMNEdge>\n      <bpmndi:BPMNEdge id=\"Flow_0fo3v6j_di\" bpmnElement=\"Flow_0fo3v6j\">\n        <di:waypoint x=\"740\" y=\"290\" />\n        <di:waypoint x=\"771\" y=\"290\" />\n        <di:waypoint x=\"771\" y=\"215\" />\n        <di:waypoint x=\"802\" y=\"215\" />\n      </bpmndi:BPMNEdge>\n      <bpmndi:BPMNEdge id=\"Flow_1z12r58_di\" bpmnElement=\"Flow_1z12r58\">\n        <di:waypoint x=\"570\" y=\"215\" />\n        <di:waypoint x=\"605\" y=\"215\" />\n        <di:waypoint x=\"605\" y=\"140\" />\n        <di:waypoint x=\"640\" y=\"140\" />\n      </bpmndi:BPMNEdge>\n      <bpmndi:BPMNEdge id=\"Flow_0bt4srq_di\" bpmnElement=\"Flow_0bt4srq\">\n        <di:waypoint x=\"570\" y=\"215\" />\n        <di:waypoint x=\"605\" y=\"215\" />\n        <di:waypoint x=\"605\" y=\"290\" />\n        <di:waypoint x=\"640\" y=\"290\" />\n      </bpmndi:BPMNEdge>\n    </bpmndi:BPMNPlane>\n  </bpmndi:BPMNDiagram>\n</definitions>\n',NULL);
/*!40000 ALTER TABLE `act_ge_bytearray` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `act_ge_property`
--

DROP TABLE IF EXISTS `act_ge_property`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `act_ge_property` (
  `NAME_` varchar(64) COLLATE utf8mb3_bin NOT NULL,
  `VALUE_` varchar(300) COLLATE utf8mb3_bin DEFAULT NULL,
  `REV_` int DEFAULT NULL,
  PRIMARY KEY (`NAME_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `act_ge_property`
--

LOCK TABLES `act_ge_property` WRITE;
/*!40000 ALTER TABLE `act_ge_property` DISABLE KEYS */;
INSERT INTO `act_ge_property` VALUES ('batch.schema.version','7.0.1.1',1),('cfg.execution-related-entities-count','true',1),('cfg.task-related-entities-count','true',1),('common.schema.version','7.0.1.1',1),('entitylink.schema.version','7.0.1.1',1),('eventsubscription.schema.version','7.0.1.1',1),('identitylink.schema.version','7.0.1.1',1),('job.schema.version','7.0.1.1',1),('next.dbid','1',1),('schema.history','create(7.0.1.1)',1),('schema.version','7.0.1.1',1),('task.schema.version','7.0.1.1',1),('variable.schema.version','7.0.1.1',1);
/*!40000 ALTER TABLE `act_ge_property` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `act_hi_actinst`
--

DROP TABLE IF EXISTS `act_hi_actinst`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `act_hi_actinst` (
  `ID_` varchar(64) COLLATE utf8mb3_bin NOT NULL,
  `REV_` int DEFAULT '1',
  `PROC_DEF_ID_` varchar(64) COLLATE utf8mb3_bin NOT NULL,
  `PROC_INST_ID_` varchar(64) COLLATE utf8mb3_bin NOT NULL,
  `EXECUTION_ID_` varchar(64) COLLATE utf8mb3_bin NOT NULL,
  `ACT_ID_` varchar(255) COLLATE utf8mb3_bin NOT NULL,
  `TASK_ID_` varchar(64) COLLATE utf8mb3_bin DEFAULT NULL,
  `CALL_PROC_INST_ID_` varchar(64) COLLATE utf8mb3_bin DEFAULT NULL,
  `ACT_NAME_` varchar(255) COLLATE utf8mb3_bin DEFAULT NULL,
  `ACT_TYPE_` varchar(255) COLLATE utf8mb3_bin NOT NULL,
  `ASSIGNEE_` varchar(255) COLLATE utf8mb3_bin DEFAULT NULL,
  `START_TIME_` datetime(3) NOT NULL,
  `END_TIME_` datetime(3) DEFAULT NULL,
  `TRANSACTION_ORDER_` int DEFAULT NULL,
  `DURATION_` bigint DEFAULT NULL,
  `DELETE_REASON_` varchar(4000) COLLATE utf8mb3_bin DEFAULT NULL,
  `TENANT_ID_` varchar(255) COLLATE utf8mb3_bin DEFAULT '',
  PRIMARY KEY (`ID_`),
  KEY `ACT_IDX_HI_ACT_INST_START` (`START_TIME_`),
  KEY `ACT_IDX_HI_ACT_INST_END` (`END_TIME_`),
  KEY `ACT_IDX_HI_ACT_INST_PROCINST` (`PROC_INST_ID_`,`ACT_ID_`),
  KEY `ACT_IDX_HI_ACT_INST_EXEC` (`EXECUTION_ID_`,`ACT_ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `act_hi_actinst`
--

LOCK TABLES `act_hi_actinst` WRITE;
/*!40000 ALTER TABLE `act_hi_actinst` DISABLE KEYS */;
INSERT INTO `act_hi_actinst` VALUES ('1864607918255484932',1,'leave1:1:1864607721269997569','1864607918167404545','1864607918255484931','startNode1',NULL,NULL,'开始','startEvent',NULL,'2024-12-05 17:49:05.109','2024-12-05 17:49:05.113',1,4,NULL,'000000'),('1864607918284845057',1,'leave1:1:1864607721269997569','1864607918167404545','1864607918255484931','Flow_1f4xioj',NULL,NULL,NULL,'sequenceFlow',NULL,'2024-12-05 17:49:05.115','2024-12-05 17:49:05.115',2,0,NULL,'000000'),('1864607918284845058',3,'leave1:1:1864607721269997569','1864607918167404545','1864607918255484931','Activity_14633hx','1864607918322593794',NULL,'申请人','userTask','1','2024-12-05 17:49:05.115','2024-12-05 17:49:32.285',3,27170,NULL,'000000'),('1864608032252473346',1,'leave1:1:1864607721269997569','1864607918167404545','1864607918255484931','Flow_0cy98fl',NULL,NULL,NULL,'sequenceFlow',NULL,'2024-12-05 17:49:32.287','2024-12-05 17:49:32.287',1,0,NULL,'000000'),('1864608032256667650',1,'leave1:1:1864607721269997569','1864607918167404545','1864607918255484931','Activity_0lym9dc','1864608032256667651',NULL,'组长','userTask',NULL,'2024-12-05 17:49:32.288',NULL,2,NULL,NULL,'000000');
/*!40000 ALTER TABLE `act_hi_actinst` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `act_hi_attachment`
--

DROP TABLE IF EXISTS `act_hi_attachment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `act_hi_attachment` (
  `ID_` varchar(64) COLLATE utf8mb3_bin NOT NULL,
  `REV_` int DEFAULT NULL,
  `USER_ID_` varchar(255) COLLATE utf8mb3_bin DEFAULT NULL,
  `NAME_` varchar(255) COLLATE utf8mb3_bin DEFAULT NULL,
  `DESCRIPTION_` varchar(4000) COLLATE utf8mb3_bin DEFAULT NULL,
  `TYPE_` varchar(255) COLLATE utf8mb3_bin DEFAULT NULL,
  `TASK_ID_` varchar(64) COLLATE utf8mb3_bin DEFAULT NULL,
  `PROC_INST_ID_` varchar(64) COLLATE utf8mb3_bin DEFAULT NULL,
  `URL_` varchar(4000) COLLATE utf8mb3_bin DEFAULT NULL,
  `CONTENT_ID_` varchar(64) COLLATE utf8mb3_bin DEFAULT NULL,
  `TIME_` datetime(3) DEFAULT NULL,
  PRIMARY KEY (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `act_hi_attachment`
--

LOCK TABLES `act_hi_attachment` WRITE;
/*!40000 ALTER TABLE `act_hi_attachment` DISABLE KEYS */;
/*!40000 ALTER TABLE `act_hi_attachment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `act_hi_comment`
--

DROP TABLE IF EXISTS `act_hi_comment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `act_hi_comment` (
  `ID_` varchar(64) COLLATE utf8mb3_bin NOT NULL,
  `TYPE_` varchar(255) COLLATE utf8mb3_bin DEFAULT NULL,
  `TIME_` datetime(3) NOT NULL,
  `USER_ID_` varchar(255) COLLATE utf8mb3_bin DEFAULT NULL,
  `TASK_ID_` varchar(64) COLLATE utf8mb3_bin DEFAULT NULL,
  `PROC_INST_ID_` varchar(64) COLLATE utf8mb3_bin DEFAULT NULL,
  `ACTION_` varchar(255) COLLATE utf8mb3_bin DEFAULT NULL,
  `MESSAGE_` varchar(4000) COLLATE utf8mb3_bin DEFAULT NULL,
  `FULL_MSG_` longblob,
  PRIMARY KEY (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `act_hi_comment`
--

LOCK TABLES `act_hi_comment` WRITE;
/*!40000 ALTER TABLE `act_hi_comment` DISABLE KEYS */;
INSERT INTO `act_hi_comment` VALUES ('1864607919148871682','event','2024-12-05 17:49:05.322','1','1864607918322593794',NULL,'AddUserLink','1_|_assignee',NULL),('1864608032139227137','pass','2024-12-05 17:49:32.261','1','1864607918322593794','1864607918167404545','AddComment','同意',_binary '同意'),('1864608033154248706','copy','2024-12-05 17:49:32.503','1','1864608033057779713','1864607918167404545','AddComment','疯狂的狮子Li【抄送】给仅本人 密码666666',_binary '疯狂的狮子Li【抄送】给仅本人 密码666666'),('1864608033624010754','copy','2024-12-05 17:49:32.614','1','1864608033418489858','1864607918167404545','AddComment','','');
/*!40000 ALTER TABLE `act_hi_comment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `act_hi_detail`
--

DROP TABLE IF EXISTS `act_hi_detail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `act_hi_detail` (
  `ID_` varchar(64) COLLATE utf8mb3_bin NOT NULL,
  `TYPE_` varchar(255) COLLATE utf8mb3_bin NOT NULL,
  `PROC_INST_ID_` varchar(64) COLLATE utf8mb3_bin DEFAULT NULL,
  `EXECUTION_ID_` varchar(64) COLLATE utf8mb3_bin DEFAULT NULL,
  `TASK_ID_` varchar(64) COLLATE utf8mb3_bin DEFAULT NULL,
  `ACT_INST_ID_` varchar(64) COLLATE utf8mb3_bin DEFAULT NULL,
  `NAME_` varchar(255) COLLATE utf8mb3_bin NOT NULL,
  `VAR_TYPE_` varchar(255) COLLATE utf8mb3_bin DEFAULT NULL,
  `REV_` int DEFAULT NULL,
  `TIME_` datetime(3) NOT NULL,
  `BYTEARRAY_ID_` varchar(64) COLLATE utf8mb3_bin DEFAULT NULL,
  `DOUBLE_` double DEFAULT NULL,
  `LONG_` bigint DEFAULT NULL,
  `TEXT_` varchar(4000) COLLATE utf8mb3_bin DEFAULT NULL,
  `TEXT2_` varchar(4000) COLLATE utf8mb3_bin DEFAULT NULL,
  PRIMARY KEY (`ID_`),
  KEY `ACT_IDX_HI_DETAIL_PROC_INST` (`PROC_INST_ID_`),
  KEY `ACT_IDX_HI_DETAIL_ACT_INST` (`ACT_INST_ID_`),
  KEY `ACT_IDX_HI_DETAIL_TIME` (`TIME_`),
  KEY `ACT_IDX_HI_DETAIL_NAME` (`NAME_`),
  KEY `ACT_IDX_HI_DETAIL_TASK_ID` (`TASK_ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `act_hi_detail`
--

LOCK TABLES `act_hi_detail` WRITE;
/*!40000 ALTER TABLE `act_hi_detail` DISABLE KEYS */;
/*!40000 ALTER TABLE `act_hi_detail` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `act_hi_entitylink`
--

DROP TABLE IF EXISTS `act_hi_entitylink`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `act_hi_entitylink` (
  `ID_` varchar(64) COLLATE utf8mb3_bin NOT NULL,
  `LINK_TYPE_` varchar(255) COLLATE utf8mb3_bin DEFAULT NULL,
  `CREATE_TIME_` datetime(3) DEFAULT NULL,
  `SCOPE_ID_` varchar(255) COLLATE utf8mb3_bin DEFAULT NULL,
  `SUB_SCOPE_ID_` varchar(255) COLLATE utf8mb3_bin DEFAULT NULL,
  `SCOPE_TYPE_` varchar(255) COLLATE utf8mb3_bin DEFAULT NULL,
  `SCOPE_DEFINITION_ID_` varchar(255) COLLATE utf8mb3_bin DEFAULT NULL,
  `PARENT_ELEMENT_ID_` varchar(255) COLLATE utf8mb3_bin DEFAULT NULL,
  `REF_SCOPE_ID_` varchar(255) COLLATE utf8mb3_bin DEFAULT NULL,
  `REF_SCOPE_TYPE_` varchar(255) COLLATE utf8mb3_bin DEFAULT NULL,
  `REF_SCOPE_DEFINITION_ID_` varchar(255) COLLATE utf8mb3_bin DEFAULT NULL,
  `ROOT_SCOPE_ID_` varchar(255) COLLATE utf8mb3_bin DEFAULT NULL,
  `ROOT_SCOPE_TYPE_` varchar(255) COLLATE utf8mb3_bin DEFAULT NULL,
  `HIERARCHY_TYPE_` varchar(255) COLLATE utf8mb3_bin DEFAULT NULL,
  PRIMARY KEY (`ID_`),
  KEY `ACT_IDX_HI_ENT_LNK_SCOPE` (`SCOPE_ID_`,`SCOPE_TYPE_`,`LINK_TYPE_`),
  KEY `ACT_IDX_HI_ENT_LNK_REF_SCOPE` (`REF_SCOPE_ID_`,`REF_SCOPE_TYPE_`,`LINK_TYPE_`),
  KEY `ACT_IDX_HI_ENT_LNK_ROOT_SCOPE` (`ROOT_SCOPE_ID_`,`ROOT_SCOPE_TYPE_`,`LINK_TYPE_`),
  KEY `ACT_IDX_HI_ENT_LNK_SCOPE_DEF` (`SCOPE_DEFINITION_ID_`,`SCOPE_TYPE_`,`LINK_TYPE_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `act_hi_entitylink`
--

LOCK TABLES `act_hi_entitylink` WRITE;
/*!40000 ALTER TABLE `act_hi_entitylink` DISABLE KEYS */;
/*!40000 ALTER TABLE `act_hi_entitylink` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `act_hi_identitylink`
--

DROP TABLE IF EXISTS `act_hi_identitylink`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `act_hi_identitylink` (
  `ID_` varchar(64) COLLATE utf8mb3_bin NOT NULL,
  `GROUP_ID_` varchar(255) COLLATE utf8mb3_bin DEFAULT NULL,
  `TYPE_` varchar(255) COLLATE utf8mb3_bin DEFAULT NULL,
  `USER_ID_` varchar(255) COLLATE utf8mb3_bin DEFAULT NULL,
  `TASK_ID_` varchar(64) COLLATE utf8mb3_bin DEFAULT NULL,
  `CREATE_TIME_` datetime(3) DEFAULT NULL,
  `PROC_INST_ID_` varchar(64) COLLATE utf8mb3_bin DEFAULT NULL,
  `SCOPE_ID_` varchar(255) COLLATE utf8mb3_bin DEFAULT NULL,
  `SUB_SCOPE_ID_` varchar(255) COLLATE utf8mb3_bin DEFAULT NULL,
  `SCOPE_TYPE_` varchar(255) COLLATE utf8mb3_bin DEFAULT NULL,
  `SCOPE_DEFINITION_ID_` varchar(255) COLLATE utf8mb3_bin DEFAULT NULL,
  PRIMARY KEY (`ID_`),
  KEY `ACT_IDX_HI_IDENT_LNK_USER` (`USER_ID_`),
  KEY `ACT_IDX_HI_IDENT_LNK_SCOPE` (`SCOPE_ID_`,`SCOPE_TYPE_`),
  KEY `ACT_IDX_HI_IDENT_LNK_SUB_SCOPE` (`SUB_SCOPE_ID_`,`SCOPE_TYPE_`),
  KEY `ACT_IDX_HI_IDENT_LNK_SCOPE_DEF` (`SCOPE_DEFINITION_ID_`,`SCOPE_TYPE_`),
  KEY `ACT_IDX_HI_IDENT_LNK_TASK` (`TASK_ID_`),
  KEY `ACT_IDX_HI_IDENT_LNK_PROCINST` (`PROC_INST_ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `act_hi_identitylink`
--

LOCK TABLES `act_hi_identitylink` WRITE;
/*!40000 ALTER TABLE `act_hi_identitylink` DISABLE KEYS */;
INSERT INTO `act_hi_identitylink` VALUES ('1864607918171598849',NULL,'starter','1',NULL,'2024-12-05 17:49:05.089','1864607918167404545',NULL,NULL,NULL,NULL),('1864607919132094465',NULL,'assignee','1','1864607918322593794','2024-12-05 17:49:05.317',NULL,NULL,NULL,NULL,NULL),('1864607919148871681',NULL,'participant','1',NULL,'2024-12-05 17:49:05.322','1864607918167404545',NULL,NULL,NULL,NULL),('1864608032193753090',NULL,'participant','1',NULL,'2024-12-05 17:49:32.273','1864607918167404545',NULL,NULL,NULL,NULL),('1864608032256667652',NULL,'candidate','1','1864608032256667651','2024-12-05 17:49:32.289',NULL,NULL,NULL,NULL,NULL),('1864608032260861953',NULL,'candidate','3','1864608032256667651','2024-12-05 17:49:32.289',NULL,NULL,NULL,NULL,NULL),('1864608032260861954',NULL,'participant','3',NULL,'2024-12-05 17:49:32.290','1864607918167404545',NULL,NULL,NULL,NULL),('1864608033183608834',NULL,'participant','1',NULL,'2024-12-05 17:49:32.510','1864607918167404545',NULL,NULL,NULL,NULL),('1864608033410101249',NULL,'participant','4',NULL,'2024-12-05 17:49:32.565','1864607918167404545',NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `act_hi_identitylink` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `act_hi_procinst`
--

DROP TABLE IF EXISTS `act_hi_procinst`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `act_hi_procinst` (
  `ID_` varchar(64) COLLATE utf8mb3_bin NOT NULL,
  `REV_` int DEFAULT '1',
  `PROC_INST_ID_` varchar(64) COLLATE utf8mb3_bin NOT NULL,
  `BUSINESS_KEY_` varchar(255) COLLATE utf8mb3_bin DEFAULT NULL,
  `PROC_DEF_ID_` varchar(64) COLLATE utf8mb3_bin NOT NULL,
  `START_TIME_` datetime(3) NOT NULL,
  `END_TIME_` datetime(3) DEFAULT NULL,
  `DURATION_` bigint DEFAULT NULL,
  `START_USER_ID_` varchar(255) COLLATE utf8mb3_bin DEFAULT NULL,
  `START_ACT_ID_` varchar(255) COLLATE utf8mb3_bin DEFAULT NULL,
  `END_ACT_ID_` varchar(255) COLLATE utf8mb3_bin DEFAULT NULL,
  `SUPER_PROCESS_INSTANCE_ID_` varchar(64) COLLATE utf8mb3_bin DEFAULT NULL,
  `DELETE_REASON_` varchar(4000) COLLATE utf8mb3_bin DEFAULT NULL,
  `TENANT_ID_` varchar(255) COLLATE utf8mb3_bin DEFAULT '',
  `NAME_` varchar(255) COLLATE utf8mb3_bin DEFAULT NULL,
  `CALLBACK_ID_` varchar(255) COLLATE utf8mb3_bin DEFAULT NULL,
  `CALLBACK_TYPE_` varchar(255) COLLATE utf8mb3_bin DEFAULT NULL,
  `REFERENCE_ID_` varchar(255) COLLATE utf8mb3_bin DEFAULT NULL,
  `REFERENCE_TYPE_` varchar(255) COLLATE utf8mb3_bin DEFAULT NULL,
  `PROPAGATED_STAGE_INST_ID_` varchar(255) COLLATE utf8mb3_bin DEFAULT NULL,
  `BUSINESS_STATUS_` varchar(255) COLLATE utf8mb3_bin DEFAULT NULL,
  PRIMARY KEY (`ID_`),
  UNIQUE KEY `PROC_INST_ID_` (`PROC_INST_ID_`),
  KEY `ACT_IDX_HI_PRO_INST_END` (`END_TIME_`),
  KEY `ACT_IDX_HI_PRO_I_BUSKEY` (`BUSINESS_KEY_`),
  KEY `ACT_IDX_HI_PRO_SUPER_PROCINST` (`SUPER_PROCESS_INSTANCE_ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `act_hi_procinst`
--

LOCK TABLES `act_hi_procinst` WRITE;
/*!40000 ALTER TABLE `act_hi_procinst` DISABLE KEYS */;
INSERT INTO `act_hi_procinst` VALUES ('1864607918167404545',4,'1864607918167404545','1864607914954567681','leave1:1:1864607721269997569','2024-12-05 17:49:05.088',NULL,NULL,'1','startNode1',NULL,NULL,NULL,'000000','请假流程（普通流程）',NULL,NULL,NULL,NULL,NULL,'waiting');
/*!40000 ALTER TABLE `act_hi_procinst` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `act_hi_taskinst`
--

DROP TABLE IF EXISTS `act_hi_taskinst`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `act_hi_taskinst` (
  `ID_` varchar(64) COLLATE utf8mb3_bin NOT NULL,
  `REV_` int DEFAULT '1',
  `PROC_DEF_ID_` varchar(64) COLLATE utf8mb3_bin DEFAULT NULL,
  `TASK_DEF_ID_` varchar(64) COLLATE utf8mb3_bin DEFAULT NULL,
  `TASK_DEF_KEY_` varchar(255) COLLATE utf8mb3_bin DEFAULT NULL,
  `PROC_INST_ID_` varchar(64) COLLATE utf8mb3_bin DEFAULT NULL,
  `EXECUTION_ID_` varchar(64) COLLATE utf8mb3_bin DEFAULT NULL,
  `SCOPE_ID_` varchar(255) COLLATE utf8mb3_bin DEFAULT NULL,
  `SUB_SCOPE_ID_` varchar(255) COLLATE utf8mb3_bin DEFAULT NULL,
  `SCOPE_TYPE_` varchar(255) COLLATE utf8mb3_bin DEFAULT NULL,
  `SCOPE_DEFINITION_ID_` varchar(255) COLLATE utf8mb3_bin DEFAULT NULL,
  `PROPAGATED_STAGE_INST_ID_` varchar(255) COLLATE utf8mb3_bin DEFAULT NULL,
  `STATE_` varchar(255) COLLATE utf8mb3_bin DEFAULT NULL,
  `NAME_` varchar(255) COLLATE utf8mb3_bin DEFAULT NULL,
  `PARENT_TASK_ID_` varchar(64) COLLATE utf8mb3_bin DEFAULT NULL,
  `DESCRIPTION_` varchar(4000) COLLATE utf8mb3_bin DEFAULT NULL,
  `OWNER_` varchar(255) COLLATE utf8mb3_bin DEFAULT NULL,
  `ASSIGNEE_` varchar(255) COLLATE utf8mb3_bin DEFAULT NULL,
  `START_TIME_` datetime(3) NOT NULL,
  `IN_PROGRESS_TIME_` datetime(3) DEFAULT NULL,
  `IN_PROGRESS_STARTED_BY_` varchar(255) COLLATE utf8mb3_bin DEFAULT NULL,
  `CLAIM_TIME_` datetime(3) DEFAULT NULL,
  `CLAIMED_BY_` varchar(255) COLLATE utf8mb3_bin DEFAULT NULL,
  `SUSPENDED_TIME_` datetime(3) DEFAULT NULL,
  `SUSPENDED_BY_` varchar(255) COLLATE utf8mb3_bin DEFAULT NULL,
  `END_TIME_` datetime(3) DEFAULT NULL,
  `COMPLETED_BY_` varchar(255) COLLATE utf8mb3_bin DEFAULT NULL,
  `DURATION_` bigint DEFAULT NULL,
  `DELETE_REASON_` varchar(4000) COLLATE utf8mb3_bin DEFAULT NULL,
  `PRIORITY_` int DEFAULT NULL,
  `IN_PROGRESS_DUE_DATE_` datetime(3) DEFAULT NULL,
  `DUE_DATE_` datetime(3) DEFAULT NULL,
  `FORM_KEY_` varchar(255) COLLATE utf8mb3_bin DEFAULT NULL,
  `CATEGORY_` varchar(255) COLLATE utf8mb3_bin DEFAULT NULL,
  `TENANT_ID_` varchar(255) COLLATE utf8mb3_bin DEFAULT '',
  `LAST_UPDATED_TIME_` datetime(3) DEFAULT NULL,
  PRIMARY KEY (`ID_`),
  KEY `ACT_IDX_HI_TASK_SCOPE` (`SCOPE_ID_`,`SCOPE_TYPE_`),
  KEY `ACT_IDX_HI_TASK_SUB_SCOPE` (`SUB_SCOPE_ID_`,`SCOPE_TYPE_`),
  KEY `ACT_IDX_HI_TASK_SCOPE_DEF` (`SCOPE_DEFINITION_ID_`,`SCOPE_TYPE_`),
  KEY `ACT_IDX_HI_TASK_INST_PROCINST` (`PROC_INST_ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `act_hi_taskinst`
--

LOCK TABLES `act_hi_taskinst` WRITE;
/*!40000 ALTER TABLE `act_hi_taskinst` DISABLE KEYS */;
INSERT INTO `act_hi_taskinst` VALUES ('1864607918322593794',3,'leave1:1:1864607721269997569',NULL,'Activity_14633hx','1864607918167404545','1864607918255484931',NULL,NULL,NULL,NULL,NULL,'completed','申请人',NULL,NULL,NULL,'1','2024-12-05 17:49:05.115',NULL,NULL,NULL,NULL,NULL,NULL,'2024-12-05 17:49:32.277',NULL,27162,NULL,50,NULL,NULL,'static:1',NULL,'000000','2024-12-05 17:49:32.277'),('1864608032256667651',1,'leave1:1:1864607721269997569',NULL,'Activity_0lym9dc','1864607918167404545','1864607918255484931',NULL,NULL,NULL,NULL,NULL,'created','组长',NULL,NULL,NULL,NULL,'2024-12-05 17:49:32.288',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,50,NULL,NULL,NULL,NULL,'000000','2024-12-05 17:49:32.288'),('1864608033057779713',3,'leave1:1:1864607721269997569',NULL,'Activity_14633hx','1864607918167404545',NULL,NULL,NULL,NULL,NULL,NULL,'completed','申请人',NULL,NULL,NULL,'1','2024-12-05 17:49:32.450',NULL,NULL,NULL,NULL,NULL,NULL,'2024-12-05 17:49:32.512',NULL,62,NULL,50,NULL,NULL,NULL,NULL,'000000','2024-12-05 17:49:32.512'),('1864608033418489858',1,'leave1:1:1864607721269997569',NULL,'Activity_0lym9dc','1864607918167404545',NULL,NULL,NULL,'copy',NULL,NULL,'created','【抄送】-组长','1864608032256667651',NULL,NULL,'4','2024-12-05 17:49:32.541',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,50,NULL,NULL,NULL,NULL,'000000','2024-12-05 17:49:32.565');
/*!40000 ALTER TABLE `act_hi_taskinst` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `act_hi_tsk_log`
--

DROP TABLE IF EXISTS `act_hi_tsk_log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `act_hi_tsk_log` (
  `ID_` bigint NOT NULL AUTO_INCREMENT,
  `TYPE_` varchar(64) COLLATE utf8mb3_bin DEFAULT NULL,
  `TASK_ID_` varchar(64) COLLATE utf8mb3_bin NOT NULL,
  `TIME_STAMP_` timestamp(3) NOT NULL,
  `USER_ID_` varchar(255) COLLATE utf8mb3_bin DEFAULT NULL,
  `DATA_` varchar(4000) COLLATE utf8mb3_bin DEFAULT NULL,
  `EXECUTION_ID_` varchar(64) COLLATE utf8mb3_bin DEFAULT NULL,
  `PROC_INST_ID_` varchar(64) COLLATE utf8mb3_bin DEFAULT NULL,
  `PROC_DEF_ID_` varchar(64) COLLATE utf8mb3_bin DEFAULT NULL,
  `SCOPE_ID_` varchar(255) COLLATE utf8mb3_bin DEFAULT NULL,
  `SCOPE_DEFINITION_ID_` varchar(255) COLLATE utf8mb3_bin DEFAULT NULL,
  `SUB_SCOPE_ID_` varchar(255) COLLATE utf8mb3_bin DEFAULT NULL,
  `SCOPE_TYPE_` varchar(255) COLLATE utf8mb3_bin DEFAULT NULL,
  `TENANT_ID_` varchar(255) COLLATE utf8mb3_bin DEFAULT '',
  PRIMARY KEY (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `act_hi_tsk_log`
--

LOCK TABLES `act_hi_tsk_log` WRITE;
/*!40000 ALTER TABLE `act_hi_tsk_log` DISABLE KEYS */;
/*!40000 ALTER TABLE `act_hi_tsk_log` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `act_hi_varinst`
--

DROP TABLE IF EXISTS `act_hi_varinst`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `act_hi_varinst` (
  `ID_` varchar(64) COLLATE utf8mb3_bin NOT NULL,
  `REV_` int DEFAULT '1',
  `PROC_INST_ID_` varchar(64) COLLATE utf8mb3_bin DEFAULT NULL,
  `EXECUTION_ID_` varchar(64) COLLATE utf8mb3_bin DEFAULT NULL,
  `TASK_ID_` varchar(64) COLLATE utf8mb3_bin DEFAULT NULL,
  `NAME_` varchar(255) COLLATE utf8mb3_bin NOT NULL,
  `VAR_TYPE_` varchar(100) COLLATE utf8mb3_bin DEFAULT NULL,
  `SCOPE_ID_` varchar(255) COLLATE utf8mb3_bin DEFAULT NULL,
  `SUB_SCOPE_ID_` varchar(255) COLLATE utf8mb3_bin DEFAULT NULL,
  `SCOPE_TYPE_` varchar(255) COLLATE utf8mb3_bin DEFAULT NULL,
  `BYTEARRAY_ID_` varchar(64) COLLATE utf8mb3_bin DEFAULT NULL,
  `DOUBLE_` double DEFAULT NULL,
  `LONG_` bigint DEFAULT NULL,
  `TEXT_` varchar(4000) COLLATE utf8mb3_bin DEFAULT NULL,
  `TEXT2_` varchar(4000) COLLATE utf8mb3_bin DEFAULT NULL,
  `META_INFO_` varchar(4000) COLLATE utf8mb3_bin DEFAULT NULL,
  `CREATE_TIME_` datetime(3) DEFAULT NULL,
  `LAST_UPDATED_TIME_` datetime(3) DEFAULT NULL,
  PRIMARY KEY (`ID_`),
  KEY `ACT_IDX_HI_PROCVAR_NAME_TYPE` (`NAME_`,`VAR_TYPE_`),
  KEY `ACT_IDX_HI_VAR_SCOPE_ID_TYPE` (`SCOPE_ID_`,`SCOPE_TYPE_`),
  KEY `ACT_IDX_HI_VAR_SUB_ID_TYPE` (`SUB_SCOPE_ID_`,`SCOPE_TYPE_`),
  KEY `ACT_IDX_HI_PROCVAR_PROC_INST` (`PROC_INST_ID_`),
  KEY `ACT_IDX_HI_PROCVAR_TASK_ID` (`TASK_ID_`),
  KEY `ACT_IDX_HI_PROCVAR_EXE` (`EXECUTION_ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `act_hi_varinst`
--

LOCK TABLES `act_hi_varinst` WRITE;
/*!40000 ALTER TABLE `act_hi_varinst` DISABLE KEYS */;
INSERT INTO `act_hi_varinst` VALUES ('1864607918234513409',0,'1864607918167404545','1864607918167404545',NULL,'entity','serializable',NULL,NULL,NULL,'1864607918247096321',NULL,NULL,NULL,NULL,NULL,'2024-12-05 17:49:05.105','2024-12-05 17:49:05.105'),('1864607918247096322',0,'1864607918167404545','1864607918167404545',NULL,'leaveDays','integer',NULL,NULL,NULL,NULL,NULL,42,'42',NULL,NULL,'2024-12-05 17:49:05.106','2024-12-05 17:49:05.106'),('1864607918251290626',0,'1864607918167404545','1864607918167404545',NULL,'userList','serializable',NULL,NULL,NULL,'1864607918251290627',NULL,NULL,NULL,NULL,NULL,'2024-12-05 17:49:05.107','2024-12-05 17:49:05.107'),('1864607918251290629',0,'1864607918167404545','1864607918167404545',NULL,'userList2','serializable',NULL,NULL,NULL,'1864607918251290630',NULL,NULL,NULL,NULL,NULL,'2024-12-05 17:49:05.107','2024-12-05 17:49:05.107'),('1864607918251290631',0,'1864607918167404545','1864607918167404545',NULL,'_FLOWABLE_SKIP_EXPRESSION_ENABLED','boolean',NULL,NULL,NULL,NULL,NULL,1,NULL,NULL,NULL,'2024-12-05 17:49:05.107','2024-12-05 17:49:05.107'),('1864607918255484930',0,'1864607918167404545','1864607918167404545',NULL,'initiator','string',NULL,NULL,NULL,NULL,NULL,NULL,'1',NULL,NULL,'2024-12-05 17:49:05.108','2024-12-05 17:49:05.108'),('1864607919576690690',0,'1864607918167404545','1864607918167404545',NULL,'processInstanceId','string',NULL,NULL,NULL,NULL,NULL,NULL,'1864607918167404545',NULL,NULL,'2024-12-05 17:49:05.423','2024-12-05 17:49:05.423'),('1864607919673159681',0,'1864607918167404545','1864607918167404545',NULL,'businessKey','string',NULL,NULL,NULL,NULL,NULL,NULL,'1864607914954567681',NULL,NULL,'2024-12-05 17:49:05.446','2024-12-05 17:49:05.446');
/*!40000 ALTER TABLE `act_hi_varinst` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `act_procdef_info`
--

DROP TABLE IF EXISTS `act_procdef_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `act_procdef_info` (
  `ID_` varchar(64) COLLATE utf8mb3_bin NOT NULL,
  `PROC_DEF_ID_` varchar(64) COLLATE utf8mb3_bin NOT NULL,
  `REV_` int DEFAULT NULL,
  `INFO_JSON_ID_` varchar(64) COLLATE utf8mb3_bin DEFAULT NULL,
  PRIMARY KEY (`ID_`),
  UNIQUE KEY `ACT_UNIQ_INFO_PROCDEF` (`PROC_DEF_ID_`),
  KEY `ACT_IDX_INFO_PROCDEF` (`PROC_DEF_ID_`),
  KEY `ACT_FK_INFO_JSON_BA` (`INFO_JSON_ID_`),
  CONSTRAINT `ACT_FK_INFO_JSON_BA` FOREIGN KEY (`INFO_JSON_ID_`) REFERENCES `act_ge_bytearray` (`ID_`),
  CONSTRAINT `ACT_FK_INFO_PROCDEF` FOREIGN KEY (`PROC_DEF_ID_`) REFERENCES `act_re_procdef` (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `act_procdef_info`
--

LOCK TABLES `act_procdef_info` WRITE;
/*!40000 ALTER TABLE `act_procdef_info` DISABLE KEYS */;
/*!40000 ALTER TABLE `act_procdef_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `act_re_deployment`
--

DROP TABLE IF EXISTS `act_re_deployment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `act_re_deployment` (
  `ID_` varchar(64) COLLATE utf8mb3_bin NOT NULL,
  `NAME_` varchar(255) COLLATE utf8mb3_bin DEFAULT NULL,
  `CATEGORY_` varchar(255) COLLATE utf8mb3_bin DEFAULT NULL,
  `KEY_` varchar(255) COLLATE utf8mb3_bin DEFAULT NULL,
  `TENANT_ID_` varchar(255) COLLATE utf8mb3_bin DEFAULT '',
  `DEPLOY_TIME_` timestamp(3) NULL DEFAULT NULL,
  `DERIVED_FROM_` varchar(64) COLLATE utf8mb3_bin DEFAULT NULL,
  `DERIVED_FROM_ROOT_` varchar(64) COLLATE utf8mb3_bin DEFAULT NULL,
  `PARENT_DEPLOYMENT_ID_` varchar(255) COLLATE utf8mb3_bin DEFAULT NULL,
  `ENGINE_VERSION_` varchar(255) COLLATE utf8mb3_bin DEFAULT NULL,
  PRIMARY KEY (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `act_re_deployment`
--

LOCK TABLES `act_re_deployment` WRITE;
/*!40000 ALTER TABLE `act_re_deployment` DISABLE KEYS */;
INSERT INTO `act_re_deployment` VALUES ('1864607711493074946','请假流程（包容网关）','OA','leave4.bpmn20','000000','2024-12-05 09:48:15.809',NULL,NULL,'1864607711493074946',NULL),('1864607717163773954','请假流程（并行网关）','OA','leave3.bpmn20','000000','2024-12-05 09:48:17.164',NULL,NULL,'1864607717163773954',NULL),('1864607718426259457','请假流程（会签）','OA','leave5.bpmn20','000000','2024-12-05 09:48:17.466',NULL,NULL,'1864607718426259457',NULL),('1864607720812818433','请假流程（普通流程）','OA','leave1.bpmn20','000000','2024-12-05 09:48:18.035',NULL,NULL,'1864607720812818433',NULL),('1864607722045943810','请假流程（排他网关）','OA','leave2.bpmn20','000000','2024-12-05 09:48:18.328',NULL,NULL,'1864607722045943810',NULL),('1864607723115491330','请假流程（子流程）','OA','leave6.bpmn20','000000','2024-12-05 09:48:18.583',NULL,NULL,'1864607723115491330',NULL);
/*!40000 ALTER TABLE `act_re_deployment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `act_re_model`
--

DROP TABLE IF EXISTS `act_re_model`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `act_re_model` (
  `ID_` varchar(64) COLLATE utf8mb3_bin NOT NULL,
  `REV_` int DEFAULT NULL,
  `NAME_` varchar(255) COLLATE utf8mb3_bin DEFAULT NULL,
  `KEY_` varchar(255) COLLATE utf8mb3_bin DEFAULT NULL,
  `CATEGORY_` varchar(255) COLLATE utf8mb3_bin DEFAULT NULL,
  `CREATE_TIME_` timestamp(3) NULL DEFAULT NULL,
  `LAST_UPDATE_TIME_` timestamp(3) NULL DEFAULT NULL,
  `VERSION_` int DEFAULT NULL,
  `META_INFO_` varchar(4000) COLLATE utf8mb3_bin DEFAULT NULL,
  `DEPLOYMENT_ID_` varchar(64) COLLATE utf8mb3_bin DEFAULT NULL,
  `EDITOR_SOURCE_VALUE_ID_` varchar(64) COLLATE utf8mb3_bin DEFAULT NULL,
  `EDITOR_SOURCE_EXTRA_VALUE_ID_` varchar(64) COLLATE utf8mb3_bin DEFAULT NULL,
  `TENANT_ID_` varchar(255) COLLATE utf8mb3_bin DEFAULT '',
  PRIMARY KEY (`ID_`),
  KEY `ACT_FK_MODEL_SOURCE` (`EDITOR_SOURCE_VALUE_ID_`),
  KEY `ACT_FK_MODEL_SOURCE_EXTRA` (`EDITOR_SOURCE_EXTRA_VALUE_ID_`),
  KEY `ACT_FK_MODEL_DEPLOYMENT` (`DEPLOYMENT_ID_`),
  CONSTRAINT `ACT_FK_MODEL_DEPLOYMENT` FOREIGN KEY (`DEPLOYMENT_ID_`) REFERENCES `act_re_deployment` (`ID_`),
  CONSTRAINT `ACT_FK_MODEL_SOURCE` FOREIGN KEY (`EDITOR_SOURCE_VALUE_ID_`) REFERENCES `act_ge_bytearray` (`ID_`),
  CONSTRAINT `ACT_FK_MODEL_SOURCE_EXTRA` FOREIGN KEY (`EDITOR_SOURCE_EXTRA_VALUE_ID_`) REFERENCES `act_ge_bytearray` (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `act_re_model`
--

LOCK TABLES `act_re_model` WRITE;
/*!40000 ALTER TABLE `act_re_model` DISABLE KEYS */;
INSERT INTO `act_re_model` VALUES ('1864608563582709761',2,'请假流程（子流程）','leave6','OA','2024-12-05 09:51:38.966','2024-12-05 09:51:39.019',1,NULL,NULL,'1864608563805007874',NULL,'000000'),('1864609412967018497',2,'请假流程（排他网关）','leave2','OA','2024-12-05 09:55:01.476','2024-12-05 09:55:01.539',1,NULL,NULL,'1864609413235453953',NULL,'000000');
/*!40000 ALTER TABLE `act_re_model` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `act_re_procdef`
--

DROP TABLE IF EXISTS `act_re_procdef`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `act_re_procdef` (
  `ID_` varchar(64) COLLATE utf8mb3_bin NOT NULL,
  `REV_` int DEFAULT NULL,
  `CATEGORY_` varchar(255) COLLATE utf8mb3_bin DEFAULT NULL,
  `NAME_` varchar(255) COLLATE utf8mb3_bin DEFAULT NULL,
  `KEY_` varchar(255) COLLATE utf8mb3_bin NOT NULL,
  `VERSION_` int NOT NULL,
  `DEPLOYMENT_ID_` varchar(64) COLLATE utf8mb3_bin DEFAULT NULL,
  `RESOURCE_NAME_` varchar(4000) COLLATE utf8mb3_bin DEFAULT NULL,
  `DGRM_RESOURCE_NAME_` varchar(4000) COLLATE utf8mb3_bin DEFAULT NULL,
  `DESCRIPTION_` varchar(4000) COLLATE utf8mb3_bin DEFAULT NULL,
  `HAS_START_FORM_KEY_` tinyint DEFAULT NULL,
  `HAS_GRAPHICAL_NOTATION_` tinyint DEFAULT NULL,
  `SUSPENSION_STATE_` int DEFAULT NULL,
  `TENANT_ID_` varchar(255) COLLATE utf8mb3_bin DEFAULT '',
  `ENGINE_VERSION_` varchar(255) COLLATE utf8mb3_bin DEFAULT NULL,
  `DERIVED_FROM_` varchar(64) COLLATE utf8mb3_bin DEFAULT NULL,
  `DERIVED_FROM_ROOT_` varchar(64) COLLATE utf8mb3_bin DEFAULT NULL,
  `DERIVED_VERSION_` int NOT NULL DEFAULT '0',
  PRIMARY KEY (`ID_`),
  UNIQUE KEY `ACT_UNIQ_PROCDEF` (`KEY_`,`VERSION_`,`DERIVED_VERSION_`,`TENANT_ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `act_re_procdef`
--

LOCK TABLES `act_re_procdef` WRITE;
/*!40000 ALTER TABLE `act_re_procdef` DISABLE KEYS */;
INSERT INTO `act_re_procdef` VALUES ('leave1:1:1864607721269997569',2,'OA','请假流程（普通流程）','leave1',1,'1864607720812818433','请假流程（普通流程）-leave1.bpmn20.xml','请假流程（普通流程）-leave1.leave1.png',NULL,0,1,1,'000000',NULL,NULL,NULL,0),('leave2:1:1864607722519900161',2,'OA','请假流程（排他网关）','leave2',1,'1864607722045943810','请假流程（排他网关）-leave2.bpmn20.xml','请假流程（排他网关）-leave2.leave2.png',NULL,0,1,1,'000000',NULL,NULL,NULL,0),('leave3:1:1864607717742587907',2,'OA','请假流程（并行网关）','leave3',1,'1864607717163773954','请假流程（并行网关）-leave3.bpmn20.xml','请假流程（并行网关）-leave3.leave3.png',NULL,0,1,1,'000000',NULL,NULL,NULL,0),('leave4:1:1864607716060672002',2,'OA','请假流程（包容网关）','leave4',1,'1864607711493074946','请假流程（包容网关）-leave4.bpmn20.xml','请假流程（包容网关）-leave4.leave4.png',NULL,0,1,1,'000000',NULL,NULL,NULL,0),('leave5:1:1864607719789408258',2,'OA','请假流程（会签）','leave5',1,'1864607718426259457','请假流程（会签）-leave5.bpmn20.xml','请假流程（会签）-leave5.leave5.png',NULL,0,1,1,'000000',NULL,NULL,NULL,0),('leave6:1:1864607723623002114',2,'OA','请假流程（子流程）','leave6',1,'1864607723115491330','请假流程（子流程）-leave6.bpmn20.xml','请假流程（子流程）-leave6.leave6.png',NULL,0,1,1,'000000',NULL,NULL,NULL,0);
/*!40000 ALTER TABLE `act_re_procdef` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `act_ru_actinst`
--

DROP TABLE IF EXISTS `act_ru_actinst`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `act_ru_actinst` (
  `ID_` varchar(64) COLLATE utf8mb3_bin NOT NULL,
  `REV_` int DEFAULT '1',
  `PROC_DEF_ID_` varchar(64) COLLATE utf8mb3_bin NOT NULL,
  `PROC_INST_ID_` varchar(64) COLLATE utf8mb3_bin NOT NULL,
  `EXECUTION_ID_` varchar(64) COLLATE utf8mb3_bin NOT NULL,
  `ACT_ID_` varchar(255) COLLATE utf8mb3_bin NOT NULL,
  `TASK_ID_` varchar(64) COLLATE utf8mb3_bin DEFAULT NULL,
  `CALL_PROC_INST_ID_` varchar(64) COLLATE utf8mb3_bin DEFAULT NULL,
  `ACT_NAME_` varchar(255) COLLATE utf8mb3_bin DEFAULT NULL,
  `ACT_TYPE_` varchar(255) COLLATE utf8mb3_bin NOT NULL,
  `ASSIGNEE_` varchar(255) COLLATE utf8mb3_bin DEFAULT NULL,
  `START_TIME_` datetime(3) NOT NULL,
  `END_TIME_` datetime(3) DEFAULT NULL,
  `DURATION_` bigint DEFAULT NULL,
  `TRANSACTION_ORDER_` int DEFAULT NULL,
  `DELETE_REASON_` varchar(4000) COLLATE utf8mb3_bin DEFAULT NULL,
  `TENANT_ID_` varchar(255) COLLATE utf8mb3_bin DEFAULT '',
  PRIMARY KEY (`ID_`),
  KEY `ACT_IDX_RU_ACTI_START` (`START_TIME_`),
  KEY `ACT_IDX_RU_ACTI_END` (`END_TIME_`),
  KEY `ACT_IDX_RU_ACTI_PROC` (`PROC_INST_ID_`),
  KEY `ACT_IDX_RU_ACTI_PROC_ACT` (`PROC_INST_ID_`,`ACT_ID_`),
  KEY `ACT_IDX_RU_ACTI_EXEC` (`EXECUTION_ID_`),
  KEY `ACT_IDX_RU_ACTI_EXEC_ACT` (`EXECUTION_ID_`,`ACT_ID_`),
  KEY `ACT_IDX_RU_ACTI_TASK` (`TASK_ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `act_ru_actinst`
--

LOCK TABLES `act_ru_actinst` WRITE;
/*!40000 ALTER TABLE `act_ru_actinst` DISABLE KEYS */;
INSERT INTO `act_ru_actinst` VALUES ('1864607918255484932',1,'leave1:1:1864607721269997569','1864607918167404545','1864607918255484931','startNode1',NULL,NULL,'开始','startEvent',NULL,'2024-12-05 17:49:05.109','2024-12-05 17:49:05.113',4,1,NULL,'000000'),('1864607918284845057',1,'leave1:1:1864607721269997569','1864607918167404545','1864607918255484931','Flow_1f4xioj',NULL,NULL,NULL,'sequenceFlow',NULL,'2024-12-05 17:49:05.115','2024-12-05 17:49:05.115',0,2,NULL,'000000'),('1864607918284845058',3,'leave1:1:1864607721269997569','1864607918167404545','1864607918255484931','Activity_14633hx','1864607918322593794',NULL,'申请人','userTask','1','2024-12-05 17:49:05.115','2024-12-05 17:49:32.285',27170,3,NULL,'000000'),('1864608032252473346',1,'leave1:1:1864607721269997569','1864607918167404545','1864607918255484931','Flow_0cy98fl',NULL,NULL,NULL,'sequenceFlow',NULL,'2024-12-05 17:49:32.287','2024-12-05 17:49:32.287',0,1,NULL,'000000'),('1864608032256667650',1,'leave1:1:1864607721269997569','1864607918167404545','1864607918255484931','Activity_0lym9dc','1864608032256667651',NULL,'组长','userTask',NULL,'2024-12-05 17:49:32.288',NULL,NULL,2,NULL,'000000');
/*!40000 ALTER TABLE `act_ru_actinst` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `act_ru_deadletter_job`
--

DROP TABLE IF EXISTS `act_ru_deadletter_job`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `act_ru_deadletter_job` (
  `ID_` varchar(64) COLLATE utf8mb3_bin NOT NULL,
  `REV_` int DEFAULT NULL,
  `CATEGORY_` varchar(255) COLLATE utf8mb3_bin DEFAULT NULL,
  `TYPE_` varchar(255) COLLATE utf8mb3_bin NOT NULL,
  `EXCLUSIVE_` tinyint(1) DEFAULT NULL,
  `EXECUTION_ID_` varchar(64) COLLATE utf8mb3_bin DEFAULT NULL,
  `PROCESS_INSTANCE_ID_` varchar(64) COLLATE utf8mb3_bin DEFAULT NULL,
  `PROC_DEF_ID_` varchar(64) COLLATE utf8mb3_bin DEFAULT NULL,
  `ELEMENT_ID_` varchar(255) COLLATE utf8mb3_bin DEFAULT NULL,
  `ELEMENT_NAME_` varchar(255) COLLATE utf8mb3_bin DEFAULT NULL,
  `SCOPE_ID_` varchar(255) COLLATE utf8mb3_bin DEFAULT NULL,
  `SUB_SCOPE_ID_` varchar(255) COLLATE utf8mb3_bin DEFAULT NULL,
  `SCOPE_TYPE_` varchar(255) COLLATE utf8mb3_bin DEFAULT NULL,
  `SCOPE_DEFINITION_ID_` varchar(255) COLLATE utf8mb3_bin DEFAULT NULL,
  `CORRELATION_ID_` varchar(255) COLLATE utf8mb3_bin DEFAULT NULL,
  `EXCEPTION_STACK_ID_` varchar(64) COLLATE utf8mb3_bin DEFAULT NULL,
  `EXCEPTION_MSG_` varchar(4000) COLLATE utf8mb3_bin DEFAULT NULL,
  `DUEDATE_` timestamp(3) NULL DEFAULT NULL,
  `REPEAT_` varchar(255) COLLATE utf8mb3_bin DEFAULT NULL,
  `HANDLER_TYPE_` varchar(255) COLLATE utf8mb3_bin DEFAULT NULL,
  `HANDLER_CFG_` varchar(4000) COLLATE utf8mb3_bin DEFAULT NULL,
  `CUSTOM_VALUES_ID_` varchar(64) COLLATE utf8mb3_bin DEFAULT NULL,
  `CREATE_TIME_` timestamp(3) NULL DEFAULT NULL,
  `TENANT_ID_` varchar(255) COLLATE utf8mb3_bin DEFAULT '',
  PRIMARY KEY (`ID_`),
  KEY `ACT_IDX_DEADLETTER_JOB_EXCEPTION_STACK_ID` (`EXCEPTION_STACK_ID_`),
  KEY `ACT_IDX_DEADLETTER_JOB_CUSTOM_VALUES_ID` (`CUSTOM_VALUES_ID_`),
  KEY `ACT_IDX_DEADLETTER_JOB_CORRELATION_ID` (`CORRELATION_ID_`),
  KEY `ACT_IDX_DJOB_SCOPE` (`SCOPE_ID_`,`SCOPE_TYPE_`),
  KEY `ACT_IDX_DJOB_SUB_SCOPE` (`SUB_SCOPE_ID_`,`SCOPE_TYPE_`),
  KEY `ACT_IDX_DJOB_SCOPE_DEF` (`SCOPE_DEFINITION_ID_`,`SCOPE_TYPE_`),
  KEY `ACT_FK_DEADLETTER_JOB_EXECUTION` (`EXECUTION_ID_`),
  KEY `ACT_FK_DEADLETTER_JOB_PROCESS_INSTANCE` (`PROCESS_INSTANCE_ID_`),
  KEY `ACT_FK_DEADLETTER_JOB_PROC_DEF` (`PROC_DEF_ID_`),
  CONSTRAINT `ACT_FK_DEADLETTER_JOB_CUSTOM_VALUES` FOREIGN KEY (`CUSTOM_VALUES_ID_`) REFERENCES `act_ge_bytearray` (`ID_`),
  CONSTRAINT `ACT_FK_DEADLETTER_JOB_EXCEPTION` FOREIGN KEY (`EXCEPTION_STACK_ID_`) REFERENCES `act_ge_bytearray` (`ID_`),
  CONSTRAINT `ACT_FK_DEADLETTER_JOB_EXECUTION` FOREIGN KEY (`EXECUTION_ID_`) REFERENCES `act_ru_execution` (`ID_`),
  CONSTRAINT `ACT_FK_DEADLETTER_JOB_PROC_DEF` FOREIGN KEY (`PROC_DEF_ID_`) REFERENCES `act_re_procdef` (`ID_`),
  CONSTRAINT `ACT_FK_DEADLETTER_JOB_PROCESS_INSTANCE` FOREIGN KEY (`PROCESS_INSTANCE_ID_`) REFERENCES `act_ru_execution` (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `act_ru_deadletter_job`
--

LOCK TABLES `act_ru_deadletter_job` WRITE;
/*!40000 ALTER TABLE `act_ru_deadletter_job` DISABLE KEYS */;
/*!40000 ALTER TABLE `act_ru_deadletter_job` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `act_ru_entitylink`
--

DROP TABLE IF EXISTS `act_ru_entitylink`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `act_ru_entitylink` (
  `ID_` varchar(64) COLLATE utf8mb3_bin NOT NULL,
  `REV_` int DEFAULT NULL,
  `CREATE_TIME_` datetime(3) DEFAULT NULL,
  `LINK_TYPE_` varchar(255) COLLATE utf8mb3_bin DEFAULT NULL,
  `SCOPE_ID_` varchar(255) COLLATE utf8mb3_bin DEFAULT NULL,
  `SUB_SCOPE_ID_` varchar(255) COLLATE utf8mb3_bin DEFAULT NULL,
  `SCOPE_TYPE_` varchar(255) COLLATE utf8mb3_bin DEFAULT NULL,
  `SCOPE_DEFINITION_ID_` varchar(255) COLLATE utf8mb3_bin DEFAULT NULL,
  `PARENT_ELEMENT_ID_` varchar(255) COLLATE utf8mb3_bin DEFAULT NULL,
  `REF_SCOPE_ID_` varchar(255) COLLATE utf8mb3_bin DEFAULT NULL,
  `REF_SCOPE_TYPE_` varchar(255) COLLATE utf8mb3_bin DEFAULT NULL,
  `REF_SCOPE_DEFINITION_ID_` varchar(255) COLLATE utf8mb3_bin DEFAULT NULL,
  `ROOT_SCOPE_ID_` varchar(255) COLLATE utf8mb3_bin DEFAULT NULL,
  `ROOT_SCOPE_TYPE_` varchar(255) COLLATE utf8mb3_bin DEFAULT NULL,
  `HIERARCHY_TYPE_` varchar(255) COLLATE utf8mb3_bin DEFAULT NULL,
  PRIMARY KEY (`ID_`),
  KEY `ACT_IDX_ENT_LNK_SCOPE` (`SCOPE_ID_`,`SCOPE_TYPE_`,`LINK_TYPE_`),
  KEY `ACT_IDX_ENT_LNK_REF_SCOPE` (`REF_SCOPE_ID_`,`REF_SCOPE_TYPE_`,`LINK_TYPE_`),
  KEY `ACT_IDX_ENT_LNK_ROOT_SCOPE` (`ROOT_SCOPE_ID_`,`ROOT_SCOPE_TYPE_`,`LINK_TYPE_`),
  KEY `ACT_IDX_ENT_LNK_SCOPE_DEF` (`SCOPE_DEFINITION_ID_`,`SCOPE_TYPE_`,`LINK_TYPE_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `act_ru_entitylink`
--

LOCK TABLES `act_ru_entitylink` WRITE;
/*!40000 ALTER TABLE `act_ru_entitylink` DISABLE KEYS */;
/*!40000 ALTER TABLE `act_ru_entitylink` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `act_ru_event_subscr`
--

DROP TABLE IF EXISTS `act_ru_event_subscr`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `act_ru_event_subscr` (
  `ID_` varchar(64) COLLATE utf8mb3_bin NOT NULL,
  `REV_` int DEFAULT NULL,
  `EVENT_TYPE_` varchar(255) COLLATE utf8mb3_bin NOT NULL,
  `EVENT_NAME_` varchar(255) COLLATE utf8mb3_bin DEFAULT NULL,
  `EXECUTION_ID_` varchar(64) COLLATE utf8mb3_bin DEFAULT NULL,
  `PROC_INST_ID_` varchar(64) COLLATE utf8mb3_bin DEFAULT NULL,
  `ACTIVITY_ID_` varchar(64) COLLATE utf8mb3_bin DEFAULT NULL,
  `CONFIGURATION_` varchar(255) COLLATE utf8mb3_bin DEFAULT NULL,
  `CREATED_` timestamp(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3),
  `PROC_DEF_ID_` varchar(64) COLLATE utf8mb3_bin DEFAULT NULL,
  `SUB_SCOPE_ID_` varchar(64) COLLATE utf8mb3_bin DEFAULT NULL,
  `SCOPE_ID_` varchar(64) COLLATE utf8mb3_bin DEFAULT NULL,
  `SCOPE_DEFINITION_ID_` varchar(64) COLLATE utf8mb3_bin DEFAULT NULL,
  `SCOPE_DEFINITION_KEY_` varchar(255) COLLATE utf8mb3_bin DEFAULT NULL,
  `SCOPE_TYPE_` varchar(64) COLLATE utf8mb3_bin DEFAULT NULL,
  `LOCK_TIME_` timestamp(3) NULL DEFAULT NULL,
  `LOCK_OWNER_` varchar(255) COLLATE utf8mb3_bin DEFAULT NULL,
  `TENANT_ID_` varchar(255) COLLATE utf8mb3_bin DEFAULT '',
  PRIMARY KEY (`ID_`),
  KEY `ACT_IDX_EVENT_SUBSCR_CONFIG_` (`CONFIGURATION_`),
  KEY `ACT_IDX_EVENT_SUBSCR_SCOPEREF_` (`SCOPE_ID_`,`SCOPE_TYPE_`),
  KEY `ACT_FK_EVENT_EXEC` (`EXECUTION_ID_`),
  CONSTRAINT `ACT_FK_EVENT_EXEC` FOREIGN KEY (`EXECUTION_ID_`) REFERENCES `act_ru_execution` (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `act_ru_event_subscr`
--

LOCK TABLES `act_ru_event_subscr` WRITE;
/*!40000 ALTER TABLE `act_ru_event_subscr` DISABLE KEYS */;
/*!40000 ALTER TABLE `act_ru_event_subscr` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `act_ru_execution`
--

DROP TABLE IF EXISTS `act_ru_execution`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `act_ru_execution` (
  `ID_` varchar(64) COLLATE utf8mb3_bin NOT NULL,
  `REV_` int DEFAULT NULL,
  `PROC_INST_ID_` varchar(64) COLLATE utf8mb3_bin DEFAULT NULL,
  `BUSINESS_KEY_` varchar(255) COLLATE utf8mb3_bin DEFAULT NULL,
  `PARENT_ID_` varchar(64) COLLATE utf8mb3_bin DEFAULT NULL,
  `PROC_DEF_ID_` varchar(64) COLLATE utf8mb3_bin DEFAULT NULL,
  `SUPER_EXEC_` varchar(64) COLLATE utf8mb3_bin DEFAULT NULL,
  `ROOT_PROC_INST_ID_` varchar(64) COLLATE utf8mb3_bin DEFAULT NULL,
  `ACT_ID_` varchar(255) COLLATE utf8mb3_bin DEFAULT NULL,
  `IS_ACTIVE_` tinyint DEFAULT NULL,
  `IS_CONCURRENT_` tinyint DEFAULT NULL,
  `IS_SCOPE_` tinyint DEFAULT NULL,
  `IS_EVENT_SCOPE_` tinyint DEFAULT NULL,
  `IS_MI_ROOT_` tinyint DEFAULT NULL,
  `SUSPENSION_STATE_` int DEFAULT NULL,
  `CACHED_ENT_STATE_` int DEFAULT NULL,
  `TENANT_ID_` varchar(255) COLLATE utf8mb3_bin DEFAULT '',
  `NAME_` varchar(255) COLLATE utf8mb3_bin DEFAULT NULL,
  `START_ACT_ID_` varchar(255) COLLATE utf8mb3_bin DEFAULT NULL,
  `START_TIME_` datetime(3) DEFAULT NULL,
  `START_USER_ID_` varchar(255) COLLATE utf8mb3_bin DEFAULT NULL,
  `LOCK_TIME_` timestamp(3) NULL DEFAULT NULL,
  `LOCK_OWNER_` varchar(255) COLLATE utf8mb3_bin DEFAULT NULL,
  `IS_COUNT_ENABLED_` tinyint DEFAULT NULL,
  `EVT_SUBSCR_COUNT_` int DEFAULT NULL,
  `TASK_COUNT_` int DEFAULT NULL,
  `JOB_COUNT_` int DEFAULT NULL,
  `TIMER_JOB_COUNT_` int DEFAULT NULL,
  `SUSP_JOB_COUNT_` int DEFAULT NULL,
  `DEADLETTER_JOB_COUNT_` int DEFAULT NULL,
  `EXTERNAL_WORKER_JOB_COUNT_` int DEFAULT NULL,
  `VAR_COUNT_` int DEFAULT NULL,
  `ID_LINK_COUNT_` int DEFAULT NULL,
  `CALLBACK_ID_` varchar(255) COLLATE utf8mb3_bin DEFAULT NULL,
  `CALLBACK_TYPE_` varchar(255) COLLATE utf8mb3_bin DEFAULT NULL,
  `REFERENCE_ID_` varchar(255) COLLATE utf8mb3_bin DEFAULT NULL,
  `REFERENCE_TYPE_` varchar(255) COLLATE utf8mb3_bin DEFAULT NULL,
  `PROPAGATED_STAGE_INST_ID_` varchar(255) COLLATE utf8mb3_bin DEFAULT NULL,
  `BUSINESS_STATUS_` varchar(255) COLLATE utf8mb3_bin DEFAULT NULL,
  PRIMARY KEY (`ID_`),
  KEY `ACT_IDX_EXEC_BUSKEY` (`BUSINESS_KEY_`),
  KEY `ACT_IDC_EXEC_ROOT` (`ROOT_PROC_INST_ID_`),
  KEY `ACT_IDX_EXEC_REF_ID_` (`REFERENCE_ID_`),
  KEY `ACT_FK_EXE_PROCINST` (`PROC_INST_ID_`),
  KEY `ACT_FK_EXE_PARENT` (`PARENT_ID_`),
  KEY `ACT_FK_EXE_SUPER` (`SUPER_EXEC_`),
  KEY `ACT_FK_EXE_PROCDEF` (`PROC_DEF_ID_`),
  CONSTRAINT `ACT_FK_EXE_PARENT` FOREIGN KEY (`PARENT_ID_`) REFERENCES `act_ru_execution` (`ID_`) ON DELETE CASCADE,
  CONSTRAINT `ACT_FK_EXE_PROCDEF` FOREIGN KEY (`PROC_DEF_ID_`) REFERENCES `act_re_procdef` (`ID_`),
  CONSTRAINT `ACT_FK_EXE_PROCINST` FOREIGN KEY (`PROC_INST_ID_`) REFERENCES `act_ru_execution` (`ID_`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `ACT_FK_EXE_SUPER` FOREIGN KEY (`SUPER_EXEC_`) REFERENCES `act_ru_execution` (`ID_`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `act_ru_execution`
--

LOCK TABLES `act_ru_execution` WRITE;
/*!40000 ALTER TABLE `act_ru_execution` DISABLE KEYS */;
INSERT INTO `act_ru_execution` VALUES ('1864607918167404545',4,'1864607918167404545','1864607914954567681',NULL,'leave1:1:1864607721269997569',NULL,'1864607918167404545',NULL,1,0,1,0,0,1,NULL,'000000','请假流程（普通流程）','startNode1','2024-12-05 17:49:05.088','1',NULL,NULL,1,0,0,0,0,0,0,0,0,0,NULL,NULL,NULL,NULL,NULL,'waiting'),('1864607918255484931',2,'1864607918167404545',NULL,'1864607918167404545','leave1:1:1864607721269997569',NULL,'1864607918167404545','Activity_0lym9dc',1,0,0,0,0,1,NULL,'000000',NULL,NULL,'2024-12-05 17:49:05.108',NULL,NULL,NULL,1,0,1,0,0,0,0,0,0,0,NULL,NULL,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `act_ru_execution` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `act_ru_external_job`
--

DROP TABLE IF EXISTS `act_ru_external_job`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `act_ru_external_job` (
  `ID_` varchar(64) COLLATE utf8mb3_bin NOT NULL,
  `REV_` int DEFAULT NULL,
  `CATEGORY_` varchar(255) COLLATE utf8mb3_bin DEFAULT NULL,
  `TYPE_` varchar(255) COLLATE utf8mb3_bin NOT NULL,
  `LOCK_EXP_TIME_` timestamp(3) NULL DEFAULT NULL,
  `LOCK_OWNER_` varchar(255) COLLATE utf8mb3_bin DEFAULT NULL,
  `EXCLUSIVE_` tinyint(1) DEFAULT NULL,
  `EXECUTION_ID_` varchar(64) COLLATE utf8mb3_bin DEFAULT NULL,
  `PROCESS_INSTANCE_ID_` varchar(64) COLLATE utf8mb3_bin DEFAULT NULL,
  `PROC_DEF_ID_` varchar(64) COLLATE utf8mb3_bin DEFAULT NULL,
  `ELEMENT_ID_` varchar(255) COLLATE utf8mb3_bin DEFAULT NULL,
  `ELEMENT_NAME_` varchar(255) COLLATE utf8mb3_bin DEFAULT NULL,
  `SCOPE_ID_` varchar(255) COLLATE utf8mb3_bin DEFAULT NULL,
  `SUB_SCOPE_ID_` varchar(255) COLLATE utf8mb3_bin DEFAULT NULL,
  `SCOPE_TYPE_` varchar(255) COLLATE utf8mb3_bin DEFAULT NULL,
  `SCOPE_DEFINITION_ID_` varchar(255) COLLATE utf8mb3_bin DEFAULT NULL,
  `CORRELATION_ID_` varchar(255) COLLATE utf8mb3_bin DEFAULT NULL,
  `RETRIES_` int DEFAULT NULL,
  `EXCEPTION_STACK_ID_` varchar(64) COLLATE utf8mb3_bin DEFAULT NULL,
  `EXCEPTION_MSG_` varchar(4000) COLLATE utf8mb3_bin DEFAULT NULL,
  `DUEDATE_` timestamp(3) NULL DEFAULT NULL,
  `REPEAT_` varchar(255) COLLATE utf8mb3_bin DEFAULT NULL,
  `HANDLER_TYPE_` varchar(255) COLLATE utf8mb3_bin DEFAULT NULL,
  `HANDLER_CFG_` varchar(4000) COLLATE utf8mb3_bin DEFAULT NULL,
  `CUSTOM_VALUES_ID_` varchar(64) COLLATE utf8mb3_bin DEFAULT NULL,
  `CREATE_TIME_` timestamp(3) NULL DEFAULT NULL,
  `TENANT_ID_` varchar(255) COLLATE utf8mb3_bin DEFAULT '',
  PRIMARY KEY (`ID_`),
  KEY `ACT_IDX_EXTERNAL_JOB_EXCEPTION_STACK_ID` (`EXCEPTION_STACK_ID_`),
  KEY `ACT_IDX_EXTERNAL_JOB_CUSTOM_VALUES_ID` (`CUSTOM_VALUES_ID_`),
  KEY `ACT_IDX_EXTERNAL_JOB_CORRELATION_ID` (`CORRELATION_ID_`),
  KEY `ACT_IDX_EJOB_SCOPE` (`SCOPE_ID_`,`SCOPE_TYPE_`),
  KEY `ACT_IDX_EJOB_SUB_SCOPE` (`SUB_SCOPE_ID_`,`SCOPE_TYPE_`),
  KEY `ACT_IDX_EJOB_SCOPE_DEF` (`SCOPE_DEFINITION_ID_`,`SCOPE_TYPE_`),
  CONSTRAINT `ACT_FK_EXTERNAL_JOB_CUSTOM_VALUES` FOREIGN KEY (`CUSTOM_VALUES_ID_`) REFERENCES `act_ge_bytearray` (`ID_`),
  CONSTRAINT `ACT_FK_EXTERNAL_JOB_EXCEPTION` FOREIGN KEY (`EXCEPTION_STACK_ID_`) REFERENCES `act_ge_bytearray` (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `act_ru_external_job`
--

LOCK TABLES `act_ru_external_job` WRITE;
/*!40000 ALTER TABLE `act_ru_external_job` DISABLE KEYS */;
/*!40000 ALTER TABLE `act_ru_external_job` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `act_ru_history_job`
--

DROP TABLE IF EXISTS `act_ru_history_job`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `act_ru_history_job` (
  `ID_` varchar(64) COLLATE utf8mb3_bin NOT NULL,
  `REV_` int DEFAULT NULL,
  `LOCK_EXP_TIME_` timestamp(3) NULL DEFAULT NULL,
  `LOCK_OWNER_` varchar(255) COLLATE utf8mb3_bin DEFAULT NULL,
  `RETRIES_` int DEFAULT NULL,
  `EXCEPTION_STACK_ID_` varchar(64) COLLATE utf8mb3_bin DEFAULT NULL,
  `EXCEPTION_MSG_` varchar(4000) COLLATE utf8mb3_bin DEFAULT NULL,
  `HANDLER_TYPE_` varchar(255) COLLATE utf8mb3_bin DEFAULT NULL,
  `HANDLER_CFG_` varchar(4000) COLLATE utf8mb3_bin DEFAULT NULL,
  `CUSTOM_VALUES_ID_` varchar(64) COLLATE utf8mb3_bin DEFAULT NULL,
  `ADV_HANDLER_CFG_ID_` varchar(64) COLLATE utf8mb3_bin DEFAULT NULL,
  `CREATE_TIME_` timestamp(3) NULL DEFAULT NULL,
  `SCOPE_TYPE_` varchar(255) COLLATE utf8mb3_bin DEFAULT NULL,
  `TENANT_ID_` varchar(255) COLLATE utf8mb3_bin DEFAULT '',
  PRIMARY KEY (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `act_ru_history_job`
--

LOCK TABLES `act_ru_history_job` WRITE;
/*!40000 ALTER TABLE `act_ru_history_job` DISABLE KEYS */;
/*!40000 ALTER TABLE `act_ru_history_job` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `act_ru_identitylink`
--

DROP TABLE IF EXISTS `act_ru_identitylink`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `act_ru_identitylink` (
  `ID_` varchar(64) COLLATE utf8mb3_bin NOT NULL,
  `REV_` int DEFAULT NULL,
  `GROUP_ID_` varchar(255) COLLATE utf8mb3_bin DEFAULT NULL,
  `TYPE_` varchar(255) COLLATE utf8mb3_bin DEFAULT NULL,
  `USER_ID_` varchar(255) COLLATE utf8mb3_bin DEFAULT NULL,
  `TASK_ID_` varchar(64) COLLATE utf8mb3_bin DEFAULT NULL,
  `PROC_INST_ID_` varchar(64) COLLATE utf8mb3_bin DEFAULT NULL,
  `PROC_DEF_ID_` varchar(64) COLLATE utf8mb3_bin DEFAULT NULL,
  `SCOPE_ID_` varchar(255) COLLATE utf8mb3_bin DEFAULT NULL,
  `SUB_SCOPE_ID_` varchar(255) COLLATE utf8mb3_bin DEFAULT NULL,
  `SCOPE_TYPE_` varchar(255) COLLATE utf8mb3_bin DEFAULT NULL,
  `SCOPE_DEFINITION_ID_` varchar(255) COLLATE utf8mb3_bin DEFAULT NULL,
  PRIMARY KEY (`ID_`),
  KEY `ACT_IDX_IDENT_LNK_USER` (`USER_ID_`),
  KEY `ACT_IDX_IDENT_LNK_GROUP` (`GROUP_ID_`),
  KEY `ACT_IDX_IDENT_LNK_SCOPE` (`SCOPE_ID_`,`SCOPE_TYPE_`),
  KEY `ACT_IDX_IDENT_LNK_SUB_SCOPE` (`SUB_SCOPE_ID_`,`SCOPE_TYPE_`),
  KEY `ACT_IDX_IDENT_LNK_SCOPE_DEF` (`SCOPE_DEFINITION_ID_`,`SCOPE_TYPE_`),
  KEY `ACT_IDX_ATHRZ_PROCEDEF` (`PROC_DEF_ID_`),
  KEY `ACT_FK_TSKASS_TASK` (`TASK_ID_`),
  KEY `ACT_FK_IDL_PROCINST` (`PROC_INST_ID_`),
  CONSTRAINT `ACT_FK_ATHRZ_PROCEDEF` FOREIGN KEY (`PROC_DEF_ID_`) REFERENCES `act_re_procdef` (`ID_`),
  CONSTRAINT `ACT_FK_IDL_PROCINST` FOREIGN KEY (`PROC_INST_ID_`) REFERENCES `act_ru_execution` (`ID_`),
  CONSTRAINT `ACT_FK_TSKASS_TASK` FOREIGN KEY (`TASK_ID_`) REFERENCES `act_ru_task` (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `act_ru_identitylink`
--

LOCK TABLES `act_ru_identitylink` WRITE;
/*!40000 ALTER TABLE `act_ru_identitylink` DISABLE KEYS */;
INSERT INTO `act_ru_identitylink` VALUES ('1864607918171598849',1,NULL,'starter','1',NULL,'1864607918167404545',NULL,NULL,NULL,NULL,NULL),('1864607919148871681',1,NULL,'participant','1',NULL,'1864607918167404545',NULL,NULL,NULL,NULL,NULL),('1864608032193753090',1,NULL,'participant','1',NULL,'1864607918167404545',NULL,NULL,NULL,NULL,NULL),('1864608032256667652',1,NULL,'candidate','1','1864608032256667651',NULL,NULL,NULL,NULL,NULL,NULL),('1864608032260861953',1,NULL,'candidate','3','1864608032256667651',NULL,NULL,NULL,NULL,NULL,NULL),('1864608032260861954',1,NULL,'participant','3',NULL,'1864607918167404545',NULL,NULL,NULL,NULL,NULL),('1864608033183608834',1,NULL,'participant','1',NULL,'1864607918167404545',NULL,NULL,NULL,NULL,NULL),('1864608033410101249',1,NULL,'participant','4',NULL,'1864607918167404545',NULL,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `act_ru_identitylink` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `act_ru_job`
--

DROP TABLE IF EXISTS `act_ru_job`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `act_ru_job` (
  `ID_` varchar(64) COLLATE utf8mb3_bin NOT NULL,
  `REV_` int DEFAULT NULL,
  `CATEGORY_` varchar(255) COLLATE utf8mb3_bin DEFAULT NULL,
  `TYPE_` varchar(255) COLLATE utf8mb3_bin NOT NULL,
  `LOCK_EXP_TIME_` timestamp(3) NULL DEFAULT NULL,
  `LOCK_OWNER_` varchar(255) COLLATE utf8mb3_bin DEFAULT NULL,
  `EXCLUSIVE_` tinyint(1) DEFAULT NULL,
  `EXECUTION_ID_` varchar(64) COLLATE utf8mb3_bin DEFAULT NULL,
  `PROCESS_INSTANCE_ID_` varchar(64) COLLATE utf8mb3_bin DEFAULT NULL,
  `PROC_DEF_ID_` varchar(64) COLLATE utf8mb3_bin DEFAULT NULL,
  `ELEMENT_ID_` varchar(255) COLLATE utf8mb3_bin DEFAULT NULL,
  `ELEMENT_NAME_` varchar(255) COLLATE utf8mb3_bin DEFAULT NULL,
  `SCOPE_ID_` varchar(255) COLLATE utf8mb3_bin DEFAULT NULL,
  `SUB_SCOPE_ID_` varchar(255) COLLATE utf8mb3_bin DEFAULT NULL,
  `SCOPE_TYPE_` varchar(255) COLLATE utf8mb3_bin DEFAULT NULL,
  `SCOPE_DEFINITION_ID_` varchar(255) COLLATE utf8mb3_bin DEFAULT NULL,
  `CORRELATION_ID_` varchar(255) COLLATE utf8mb3_bin DEFAULT NULL,
  `RETRIES_` int DEFAULT NULL,
  `EXCEPTION_STACK_ID_` varchar(64) COLLATE utf8mb3_bin DEFAULT NULL,
  `EXCEPTION_MSG_` varchar(4000) COLLATE utf8mb3_bin DEFAULT NULL,
  `DUEDATE_` timestamp(3) NULL DEFAULT NULL,
  `REPEAT_` varchar(255) COLLATE utf8mb3_bin DEFAULT NULL,
  `HANDLER_TYPE_` varchar(255) COLLATE utf8mb3_bin DEFAULT NULL,
  `HANDLER_CFG_` varchar(4000) COLLATE utf8mb3_bin DEFAULT NULL,
  `CUSTOM_VALUES_ID_` varchar(64) COLLATE utf8mb3_bin DEFAULT NULL,
  `CREATE_TIME_` timestamp(3) NULL DEFAULT NULL,
  `TENANT_ID_` varchar(255) COLLATE utf8mb3_bin DEFAULT '',
  PRIMARY KEY (`ID_`),
  KEY `ACT_IDX_JOB_EXCEPTION_STACK_ID` (`EXCEPTION_STACK_ID_`),
  KEY `ACT_IDX_JOB_CUSTOM_VALUES_ID` (`CUSTOM_VALUES_ID_`),
  KEY `ACT_IDX_JOB_CORRELATION_ID` (`CORRELATION_ID_`),
  KEY `ACT_IDX_JOB_SCOPE` (`SCOPE_ID_`,`SCOPE_TYPE_`),
  KEY `ACT_IDX_JOB_SUB_SCOPE` (`SUB_SCOPE_ID_`,`SCOPE_TYPE_`),
  KEY `ACT_IDX_JOB_SCOPE_DEF` (`SCOPE_DEFINITION_ID_`,`SCOPE_TYPE_`),
  KEY `ACT_FK_JOB_EXECUTION` (`EXECUTION_ID_`),
  KEY `ACT_FK_JOB_PROCESS_INSTANCE` (`PROCESS_INSTANCE_ID_`),
  KEY `ACT_FK_JOB_PROC_DEF` (`PROC_DEF_ID_`),
  CONSTRAINT `ACT_FK_JOB_CUSTOM_VALUES` FOREIGN KEY (`CUSTOM_VALUES_ID_`) REFERENCES `act_ge_bytearray` (`ID_`),
  CONSTRAINT `ACT_FK_JOB_EXCEPTION` FOREIGN KEY (`EXCEPTION_STACK_ID_`) REFERENCES `act_ge_bytearray` (`ID_`),
  CONSTRAINT `ACT_FK_JOB_EXECUTION` FOREIGN KEY (`EXECUTION_ID_`) REFERENCES `act_ru_execution` (`ID_`),
  CONSTRAINT `ACT_FK_JOB_PROC_DEF` FOREIGN KEY (`PROC_DEF_ID_`) REFERENCES `act_re_procdef` (`ID_`),
  CONSTRAINT `ACT_FK_JOB_PROCESS_INSTANCE` FOREIGN KEY (`PROCESS_INSTANCE_ID_`) REFERENCES `act_ru_execution` (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `act_ru_job`
--

LOCK TABLES `act_ru_job` WRITE;
/*!40000 ALTER TABLE `act_ru_job` DISABLE KEYS */;
/*!40000 ALTER TABLE `act_ru_job` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `act_ru_suspended_job`
--

DROP TABLE IF EXISTS `act_ru_suspended_job`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `act_ru_suspended_job` (
  `ID_` varchar(64) COLLATE utf8mb3_bin NOT NULL,
  `REV_` int DEFAULT NULL,
  `CATEGORY_` varchar(255) COLLATE utf8mb3_bin DEFAULT NULL,
  `TYPE_` varchar(255) COLLATE utf8mb3_bin NOT NULL,
  `EXCLUSIVE_` tinyint(1) DEFAULT NULL,
  `EXECUTION_ID_` varchar(64) COLLATE utf8mb3_bin DEFAULT NULL,
  `PROCESS_INSTANCE_ID_` varchar(64) COLLATE utf8mb3_bin DEFAULT NULL,
  `PROC_DEF_ID_` varchar(64) COLLATE utf8mb3_bin DEFAULT NULL,
  `ELEMENT_ID_` varchar(255) COLLATE utf8mb3_bin DEFAULT NULL,
  `ELEMENT_NAME_` varchar(255) COLLATE utf8mb3_bin DEFAULT NULL,
  `SCOPE_ID_` varchar(255) COLLATE utf8mb3_bin DEFAULT NULL,
  `SUB_SCOPE_ID_` varchar(255) COLLATE utf8mb3_bin DEFAULT NULL,
  `SCOPE_TYPE_` varchar(255) COLLATE utf8mb3_bin DEFAULT NULL,
  `SCOPE_DEFINITION_ID_` varchar(255) COLLATE utf8mb3_bin DEFAULT NULL,
  `CORRELATION_ID_` varchar(255) COLLATE utf8mb3_bin DEFAULT NULL,
  `RETRIES_` int DEFAULT NULL,
  `EXCEPTION_STACK_ID_` varchar(64) COLLATE utf8mb3_bin DEFAULT NULL,
  `EXCEPTION_MSG_` varchar(4000) COLLATE utf8mb3_bin DEFAULT NULL,
  `DUEDATE_` timestamp(3) NULL DEFAULT NULL,
  `REPEAT_` varchar(255) COLLATE utf8mb3_bin DEFAULT NULL,
  `HANDLER_TYPE_` varchar(255) COLLATE utf8mb3_bin DEFAULT NULL,
  `HANDLER_CFG_` varchar(4000) COLLATE utf8mb3_bin DEFAULT NULL,
  `CUSTOM_VALUES_ID_` varchar(64) COLLATE utf8mb3_bin DEFAULT NULL,
  `CREATE_TIME_` timestamp(3) NULL DEFAULT NULL,
  `TENANT_ID_` varchar(255) COLLATE utf8mb3_bin DEFAULT '',
  PRIMARY KEY (`ID_`),
  KEY `ACT_IDX_SUSPENDED_JOB_EXCEPTION_STACK_ID` (`EXCEPTION_STACK_ID_`),
  KEY `ACT_IDX_SUSPENDED_JOB_CUSTOM_VALUES_ID` (`CUSTOM_VALUES_ID_`),
  KEY `ACT_IDX_SUSPENDED_JOB_CORRELATION_ID` (`CORRELATION_ID_`),
  KEY `ACT_IDX_SJOB_SCOPE` (`SCOPE_ID_`,`SCOPE_TYPE_`),
  KEY `ACT_IDX_SJOB_SUB_SCOPE` (`SUB_SCOPE_ID_`,`SCOPE_TYPE_`),
  KEY `ACT_IDX_SJOB_SCOPE_DEF` (`SCOPE_DEFINITION_ID_`,`SCOPE_TYPE_`),
  KEY `ACT_FK_SUSPENDED_JOB_EXECUTION` (`EXECUTION_ID_`),
  KEY `ACT_FK_SUSPENDED_JOB_PROCESS_INSTANCE` (`PROCESS_INSTANCE_ID_`),
  KEY `ACT_FK_SUSPENDED_JOB_PROC_DEF` (`PROC_DEF_ID_`),
  CONSTRAINT `ACT_FK_SUSPENDED_JOB_CUSTOM_VALUES` FOREIGN KEY (`CUSTOM_VALUES_ID_`) REFERENCES `act_ge_bytearray` (`ID_`),
  CONSTRAINT `ACT_FK_SUSPENDED_JOB_EXCEPTION` FOREIGN KEY (`EXCEPTION_STACK_ID_`) REFERENCES `act_ge_bytearray` (`ID_`),
  CONSTRAINT `ACT_FK_SUSPENDED_JOB_EXECUTION` FOREIGN KEY (`EXECUTION_ID_`) REFERENCES `act_ru_execution` (`ID_`),
  CONSTRAINT `ACT_FK_SUSPENDED_JOB_PROC_DEF` FOREIGN KEY (`PROC_DEF_ID_`) REFERENCES `act_re_procdef` (`ID_`),
  CONSTRAINT `ACT_FK_SUSPENDED_JOB_PROCESS_INSTANCE` FOREIGN KEY (`PROCESS_INSTANCE_ID_`) REFERENCES `act_ru_execution` (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `act_ru_suspended_job`
--

LOCK TABLES `act_ru_suspended_job` WRITE;
/*!40000 ALTER TABLE `act_ru_suspended_job` DISABLE KEYS */;
/*!40000 ALTER TABLE `act_ru_suspended_job` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `act_ru_task`
--

DROP TABLE IF EXISTS `act_ru_task`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `act_ru_task` (
  `ID_` varchar(64) COLLATE utf8mb3_bin NOT NULL,
  `REV_` int DEFAULT NULL,
  `EXECUTION_ID_` varchar(64) COLLATE utf8mb3_bin DEFAULT NULL,
  `PROC_INST_ID_` varchar(64) COLLATE utf8mb3_bin DEFAULT NULL,
  `PROC_DEF_ID_` varchar(64) COLLATE utf8mb3_bin DEFAULT NULL,
  `TASK_DEF_ID_` varchar(64) COLLATE utf8mb3_bin DEFAULT NULL,
  `SCOPE_ID_` varchar(255) COLLATE utf8mb3_bin DEFAULT NULL,
  `SUB_SCOPE_ID_` varchar(255) COLLATE utf8mb3_bin DEFAULT NULL,
  `SCOPE_TYPE_` varchar(255) COLLATE utf8mb3_bin DEFAULT NULL,
  `SCOPE_DEFINITION_ID_` varchar(255) COLLATE utf8mb3_bin DEFAULT NULL,
  `PROPAGATED_STAGE_INST_ID_` varchar(255) COLLATE utf8mb3_bin DEFAULT NULL,
  `STATE_` varchar(255) COLLATE utf8mb3_bin DEFAULT NULL,
  `NAME_` varchar(255) COLLATE utf8mb3_bin DEFAULT NULL,
  `PARENT_TASK_ID_` varchar(64) COLLATE utf8mb3_bin DEFAULT NULL,
  `DESCRIPTION_` varchar(4000) COLLATE utf8mb3_bin DEFAULT NULL,
  `TASK_DEF_KEY_` varchar(255) COLLATE utf8mb3_bin DEFAULT NULL,
  `OWNER_` varchar(255) COLLATE utf8mb3_bin DEFAULT NULL,
  `ASSIGNEE_` varchar(255) COLLATE utf8mb3_bin DEFAULT NULL,
  `DELEGATION_` varchar(64) COLLATE utf8mb3_bin DEFAULT NULL,
  `PRIORITY_` int DEFAULT NULL,
  `CREATE_TIME_` timestamp(3) NULL DEFAULT NULL,
  `IN_PROGRESS_TIME_` datetime(3) DEFAULT NULL,
  `IN_PROGRESS_STARTED_BY_` varchar(255) COLLATE utf8mb3_bin DEFAULT NULL,
  `CLAIM_TIME_` datetime(3) DEFAULT NULL,
  `CLAIMED_BY_` varchar(255) COLLATE utf8mb3_bin DEFAULT NULL,
  `SUSPENDED_TIME_` datetime(3) DEFAULT NULL,
  `SUSPENDED_BY_` varchar(255) COLLATE utf8mb3_bin DEFAULT NULL,
  `IN_PROGRESS_DUE_DATE_` datetime(3) DEFAULT NULL,
  `DUE_DATE_` datetime(3) DEFAULT NULL,
  `CATEGORY_` varchar(255) COLLATE utf8mb3_bin DEFAULT NULL,
  `SUSPENSION_STATE_` int DEFAULT NULL,
  `TENANT_ID_` varchar(255) COLLATE utf8mb3_bin DEFAULT '',
  `FORM_KEY_` varchar(255) COLLATE utf8mb3_bin DEFAULT NULL,
  `IS_COUNT_ENABLED_` tinyint DEFAULT NULL,
  `VAR_COUNT_` int DEFAULT NULL,
  `ID_LINK_COUNT_` int DEFAULT NULL,
  `SUB_TASK_COUNT_` int DEFAULT NULL,
  PRIMARY KEY (`ID_`),
  KEY `ACT_IDX_TASK_CREATE` (`CREATE_TIME_`),
  KEY `ACT_IDX_TASK_SCOPE` (`SCOPE_ID_`,`SCOPE_TYPE_`),
  KEY `ACT_IDX_TASK_SUB_SCOPE` (`SUB_SCOPE_ID_`,`SCOPE_TYPE_`),
  KEY `ACT_IDX_TASK_SCOPE_DEF` (`SCOPE_DEFINITION_ID_`,`SCOPE_TYPE_`),
  KEY `ACT_FK_TASK_EXE` (`EXECUTION_ID_`),
  KEY `ACT_FK_TASK_PROCINST` (`PROC_INST_ID_`),
  KEY `ACT_FK_TASK_PROCDEF` (`PROC_DEF_ID_`),
  CONSTRAINT `ACT_FK_TASK_EXE` FOREIGN KEY (`EXECUTION_ID_`) REFERENCES `act_ru_execution` (`ID_`),
  CONSTRAINT `ACT_FK_TASK_PROCDEF` FOREIGN KEY (`PROC_DEF_ID_`) REFERENCES `act_re_procdef` (`ID_`),
  CONSTRAINT `ACT_FK_TASK_PROCINST` FOREIGN KEY (`PROC_INST_ID_`) REFERENCES `act_ru_execution` (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `act_ru_task`
--

LOCK TABLES `act_ru_task` WRITE;
/*!40000 ALTER TABLE `act_ru_task` DISABLE KEYS */;
INSERT INTO `act_ru_task` VALUES ('1864608032256667651',2,'1864607918255484931','1864607918167404545','leave1:1:1864607721269997569',NULL,NULL,NULL,NULL,NULL,NULL,'created','组长',NULL,NULL,'Activity_0lym9dc',NULL,NULL,NULL,50,'2024-12-05 09:49:32.288',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,1,'000000',NULL,1,0,2,1),('1864608033418489858',1,NULL,'1864607918167404545','leave1:1:1864607721269997569',NULL,NULL,NULL,NULL,NULL,NULL,'created','【抄送】-组长','1864608032256667651',NULL,'Activity_0lym9dc',NULL,'4',NULL,50,'2024-12-05 09:49:32.541',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,1,'000000',NULL,1,0,0,0);
/*!40000 ALTER TABLE `act_ru_task` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `act_ru_timer_job`
--

DROP TABLE IF EXISTS `act_ru_timer_job`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `act_ru_timer_job` (
  `ID_` varchar(64) COLLATE utf8mb3_bin NOT NULL,
  `REV_` int DEFAULT NULL,
  `CATEGORY_` varchar(255) COLLATE utf8mb3_bin DEFAULT NULL,
  `TYPE_` varchar(255) COLLATE utf8mb3_bin NOT NULL,
  `LOCK_EXP_TIME_` timestamp(3) NULL DEFAULT NULL,
  `LOCK_OWNER_` varchar(255) COLLATE utf8mb3_bin DEFAULT NULL,
  `EXCLUSIVE_` tinyint(1) DEFAULT NULL,
  `EXECUTION_ID_` varchar(64) COLLATE utf8mb3_bin DEFAULT NULL,
  `PROCESS_INSTANCE_ID_` varchar(64) COLLATE utf8mb3_bin DEFAULT NULL,
  `PROC_DEF_ID_` varchar(64) COLLATE utf8mb3_bin DEFAULT NULL,
  `ELEMENT_ID_` varchar(255) COLLATE utf8mb3_bin DEFAULT NULL,
  `ELEMENT_NAME_` varchar(255) COLLATE utf8mb3_bin DEFAULT NULL,
  `SCOPE_ID_` varchar(255) COLLATE utf8mb3_bin DEFAULT NULL,
  `SUB_SCOPE_ID_` varchar(255) COLLATE utf8mb3_bin DEFAULT NULL,
  `SCOPE_TYPE_` varchar(255) COLLATE utf8mb3_bin DEFAULT NULL,
  `SCOPE_DEFINITION_ID_` varchar(255) COLLATE utf8mb3_bin DEFAULT NULL,
  `CORRELATION_ID_` varchar(255) COLLATE utf8mb3_bin DEFAULT NULL,
  `RETRIES_` int DEFAULT NULL,
  `EXCEPTION_STACK_ID_` varchar(64) COLLATE utf8mb3_bin DEFAULT NULL,
  `EXCEPTION_MSG_` varchar(4000) COLLATE utf8mb3_bin DEFAULT NULL,
  `DUEDATE_` timestamp(3) NULL DEFAULT NULL,
  `REPEAT_` varchar(255) COLLATE utf8mb3_bin DEFAULT NULL,
  `HANDLER_TYPE_` varchar(255) COLLATE utf8mb3_bin DEFAULT NULL,
  `HANDLER_CFG_` varchar(4000) COLLATE utf8mb3_bin DEFAULT NULL,
  `CUSTOM_VALUES_ID_` varchar(64) COLLATE utf8mb3_bin DEFAULT NULL,
  `CREATE_TIME_` timestamp(3) NULL DEFAULT NULL,
  `TENANT_ID_` varchar(255) COLLATE utf8mb3_bin DEFAULT '',
  PRIMARY KEY (`ID_`),
  KEY `ACT_IDX_TIMER_JOB_EXCEPTION_STACK_ID` (`EXCEPTION_STACK_ID_`),
  KEY `ACT_IDX_TIMER_JOB_CUSTOM_VALUES_ID` (`CUSTOM_VALUES_ID_`),
  KEY `ACT_IDX_TIMER_JOB_CORRELATION_ID` (`CORRELATION_ID_`),
  KEY `ACT_IDX_TIMER_JOB_DUEDATE` (`DUEDATE_`),
  KEY `ACT_IDX_TJOB_SCOPE` (`SCOPE_ID_`,`SCOPE_TYPE_`),
  KEY `ACT_IDX_TJOB_SUB_SCOPE` (`SUB_SCOPE_ID_`,`SCOPE_TYPE_`),
  KEY `ACT_IDX_TJOB_SCOPE_DEF` (`SCOPE_DEFINITION_ID_`,`SCOPE_TYPE_`),
  KEY `ACT_FK_TIMER_JOB_EXECUTION` (`EXECUTION_ID_`),
  KEY `ACT_FK_TIMER_JOB_PROCESS_INSTANCE` (`PROCESS_INSTANCE_ID_`),
  KEY `ACT_FK_TIMER_JOB_PROC_DEF` (`PROC_DEF_ID_`),
  CONSTRAINT `ACT_FK_TIMER_JOB_CUSTOM_VALUES` FOREIGN KEY (`CUSTOM_VALUES_ID_`) REFERENCES `act_ge_bytearray` (`ID_`),
  CONSTRAINT `ACT_FK_TIMER_JOB_EXCEPTION` FOREIGN KEY (`EXCEPTION_STACK_ID_`) REFERENCES `act_ge_bytearray` (`ID_`),
  CONSTRAINT `ACT_FK_TIMER_JOB_EXECUTION` FOREIGN KEY (`EXECUTION_ID_`) REFERENCES `act_ru_execution` (`ID_`),
  CONSTRAINT `ACT_FK_TIMER_JOB_PROC_DEF` FOREIGN KEY (`PROC_DEF_ID_`) REFERENCES `act_re_procdef` (`ID_`),
  CONSTRAINT `ACT_FK_TIMER_JOB_PROCESS_INSTANCE` FOREIGN KEY (`PROCESS_INSTANCE_ID_`) REFERENCES `act_ru_execution` (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `act_ru_timer_job`
--

LOCK TABLES `act_ru_timer_job` WRITE;
/*!40000 ALTER TABLE `act_ru_timer_job` DISABLE KEYS */;
/*!40000 ALTER TABLE `act_ru_timer_job` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `act_ru_variable`
--

DROP TABLE IF EXISTS `act_ru_variable`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `act_ru_variable` (
  `ID_` varchar(64) COLLATE utf8mb3_bin NOT NULL,
  `REV_` int DEFAULT NULL,
  `TYPE_` varchar(255) COLLATE utf8mb3_bin NOT NULL,
  `NAME_` varchar(255) COLLATE utf8mb3_bin NOT NULL,
  `EXECUTION_ID_` varchar(64) COLLATE utf8mb3_bin DEFAULT NULL,
  `PROC_INST_ID_` varchar(64) COLLATE utf8mb3_bin DEFAULT NULL,
  `TASK_ID_` varchar(64) COLLATE utf8mb3_bin DEFAULT NULL,
  `SCOPE_ID_` varchar(255) COLLATE utf8mb3_bin DEFAULT NULL,
  `SUB_SCOPE_ID_` varchar(255) COLLATE utf8mb3_bin DEFAULT NULL,
  `SCOPE_TYPE_` varchar(255) COLLATE utf8mb3_bin DEFAULT NULL,
  `BYTEARRAY_ID_` varchar(64) COLLATE utf8mb3_bin DEFAULT NULL,
  `DOUBLE_` double DEFAULT NULL,
  `LONG_` bigint DEFAULT NULL,
  `TEXT_` varchar(4000) COLLATE utf8mb3_bin DEFAULT NULL,
  `TEXT2_` varchar(4000) COLLATE utf8mb3_bin DEFAULT NULL,
  `META_INFO_` varchar(4000) COLLATE utf8mb3_bin DEFAULT NULL,
  PRIMARY KEY (`ID_`),
  KEY `ACT_IDX_RU_VAR_SCOPE_ID_TYPE` (`SCOPE_ID_`,`SCOPE_TYPE_`),
  KEY `ACT_IDX_RU_VAR_SUB_ID_TYPE` (`SUB_SCOPE_ID_`,`SCOPE_TYPE_`),
  KEY `ACT_FK_VAR_BYTEARRAY` (`BYTEARRAY_ID_`),
  KEY `ACT_IDX_VARIABLE_TASK_ID` (`TASK_ID_`),
  KEY `ACT_FK_VAR_EXE` (`EXECUTION_ID_`),
  KEY `ACT_FK_VAR_PROCINST` (`PROC_INST_ID_`),
  CONSTRAINT `ACT_FK_VAR_BYTEARRAY` FOREIGN KEY (`BYTEARRAY_ID_`) REFERENCES `act_ge_bytearray` (`ID_`),
  CONSTRAINT `ACT_FK_VAR_EXE` FOREIGN KEY (`EXECUTION_ID_`) REFERENCES `act_ru_execution` (`ID_`),
  CONSTRAINT `ACT_FK_VAR_PROCINST` FOREIGN KEY (`PROC_INST_ID_`) REFERENCES `act_ru_execution` (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `act_ru_variable`
--

LOCK TABLES `act_ru_variable` WRITE;
/*!40000 ALTER TABLE `act_ru_variable` DISABLE KEYS */;
INSERT INTO `act_ru_variable` VALUES ('1864607918234513409',1,'serializable','entity','1864607918167404545','1864607918167404545',NULL,NULL,NULL,NULL,'1864607918230319105',NULL,NULL,NULL,NULL,NULL),('1864607918247096322',1,'integer','leaveDays','1864607918167404545','1864607918167404545',NULL,NULL,NULL,NULL,NULL,NULL,42,'42',NULL,NULL),('1864607918251290626',1,'serializable','userList','1864607918167404545','1864607918167404545',NULL,NULL,NULL,NULL,'1864607918251290625',NULL,NULL,NULL,NULL,NULL),('1864607918251290629',1,'serializable','userList2','1864607918167404545','1864607918167404545',NULL,NULL,NULL,NULL,'1864607918251290628',NULL,NULL,NULL,NULL,NULL),('1864607918251290631',1,'boolean','_FLOWABLE_SKIP_EXPRESSION_ENABLED','1864607918167404545','1864607918167404545',NULL,NULL,NULL,NULL,NULL,NULL,1,NULL,NULL,NULL),('1864607918255484930',1,'string','initiator','1864607918167404545','1864607918167404545',NULL,NULL,NULL,NULL,NULL,NULL,NULL,'1',NULL,NULL),('1864607919576690690',1,'string','processInstanceId','1864607918167404545','1864607918167404545',NULL,NULL,NULL,NULL,NULL,NULL,NULL,'1864607918167404545',NULL,NULL),('1864607919673159681',1,'string','businessKey','1864607918167404545','1864607918167404545',NULL,NULL,NULL,NULL,NULL,NULL,NULL,'1864607914954567681',NULL,NULL);
/*!40000 ALTER TABLE `act_ru_variable` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `flw_channel_definition`
--

DROP TABLE IF EXISTS `flw_channel_definition`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `flw_channel_definition` (
  `ID_` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `NAME_` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `VERSION_` int DEFAULT NULL,
  `KEY_` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `CATEGORY_` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `DEPLOYMENT_ID_` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `CREATE_TIME_` datetime(3) DEFAULT NULL,
  `TENANT_ID_` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `RESOURCE_NAME_` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `DESCRIPTION_` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `TYPE_` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `IMPLEMENTATION_` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  PRIMARY KEY (`ID_`),
  UNIQUE KEY `ACT_IDX_CHANNEL_DEF_UNIQ` (`KEY_`,`VERSION_`,`TENANT_ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `flw_channel_definition`
--

LOCK TABLES `flw_channel_definition` WRITE;
/*!40000 ALTER TABLE `flw_channel_definition` DISABLE KEYS */;
/*!40000 ALTER TABLE `flw_channel_definition` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `flw_ev_databasechangelog`
--

DROP TABLE IF EXISTS `flw_ev_databasechangelog`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `flw_ev_databasechangelog` (
  `ID` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `AUTHOR` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `FILENAME` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `DATEEXECUTED` datetime NOT NULL,
  `ORDEREXECUTED` int NOT NULL,
  `EXECTYPE` varchar(10) COLLATE utf8mb4_general_ci NOT NULL,
  `MD5SUM` varchar(35) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `DESCRIPTION` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `COMMENTS` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `TAG` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `LIQUIBASE` varchar(20) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `CONTEXTS` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `LABELS` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `DEPLOYMENT_ID` varchar(10) COLLATE utf8mb4_general_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `flw_ev_databasechangelog`
--

LOCK TABLES `flw_ev_databasechangelog` WRITE;
/*!40000 ALTER TABLE `flw_ev_databasechangelog` DISABLE KEYS */;
INSERT INTO `flw_ev_databasechangelog` VALUES ('1','flowable','org/flowable/eventregistry/db/liquibase/flowable-eventregistry-db-changelog.xml','2024-12-05 16:28:29',1,'EXECUTED','9:63268f536c469325acef35970312551b','createTable tableName=FLW_EVENT_DEPLOYMENT; createTable tableName=FLW_EVENT_RESOURCE; createTable tableName=FLW_EVENT_DEFINITION; createIndex indexName=ACT_IDX_EVENT_DEF_UNIQ, tableName=FLW_EVENT_DEFINITION; createTable tableName=FLW_CHANNEL_DEFIN...','',NULL,'4.24.0',NULL,NULL,'3387305942'),('2','flowable','org/flowable/eventregistry/db/liquibase/flowable-eventregistry-db-changelog.xml','2024-12-05 16:28:30',2,'EXECUTED','9:dcb58b7dfd6dbda66939123a96985536','addColumn tableName=FLW_CHANNEL_DEFINITION; addColumn tableName=FLW_CHANNEL_DEFINITION','',NULL,'4.24.0',NULL,NULL,'3387305942'),('3','flowable','org/flowable/eventregistry/db/liquibase/flowable-eventregistry-db-changelog.xml','2024-12-05 16:28:30',3,'EXECUTED','9:d0c05678d57af23ad93699991e3bf4f6','customChange','',NULL,'4.24.0',NULL,NULL,'3387305942');
/*!40000 ALTER TABLE `flw_ev_databasechangelog` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `flw_ev_databasechangeloglock`
--

DROP TABLE IF EXISTS `flw_ev_databasechangeloglock`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `flw_ev_databasechangeloglock` (
  `ID` int NOT NULL,
  `LOCKED` tinyint(1) NOT NULL,
  `LOCKGRANTED` datetime DEFAULT NULL,
  `LOCKEDBY` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `flw_ev_databasechangeloglock`
--

LOCK TABLES `flw_ev_databasechangeloglock` WRITE;
/*!40000 ALTER TABLE `flw_ev_databasechangeloglock` DISABLE KEYS */;
INSERT INTO `flw_ev_databasechangeloglock` VALUES (1,0,NULL,NULL);
/*!40000 ALTER TABLE `flw_ev_databasechangeloglock` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `flw_event_definition`
--

DROP TABLE IF EXISTS `flw_event_definition`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `flw_event_definition` (
  `ID_` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `NAME_` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `VERSION_` int DEFAULT NULL,
  `KEY_` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `CATEGORY_` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `DEPLOYMENT_ID_` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `TENANT_ID_` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `RESOURCE_NAME_` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `DESCRIPTION_` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  PRIMARY KEY (`ID_`),
  UNIQUE KEY `ACT_IDX_EVENT_DEF_UNIQ` (`KEY_`,`VERSION_`,`TENANT_ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `flw_event_definition`
--

LOCK TABLES `flw_event_definition` WRITE;
/*!40000 ALTER TABLE `flw_event_definition` DISABLE KEYS */;
/*!40000 ALTER TABLE `flw_event_definition` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `flw_event_deployment`
--

DROP TABLE IF EXISTS `flw_event_deployment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `flw_event_deployment` (
  `ID_` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `NAME_` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `CATEGORY_` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `DEPLOY_TIME_` datetime(3) DEFAULT NULL,
  `TENANT_ID_` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `PARENT_DEPLOYMENT_ID_` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  PRIMARY KEY (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `flw_event_deployment`
--

LOCK TABLES `flw_event_deployment` WRITE;
/*!40000 ALTER TABLE `flw_event_deployment` DISABLE KEYS */;
/*!40000 ALTER TABLE `flw_event_deployment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `flw_event_resource`
--

DROP TABLE IF EXISTS `flw_event_resource`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `flw_event_resource` (
  `ID_` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `NAME_` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `DEPLOYMENT_ID_` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `RESOURCE_BYTES_` longblob,
  PRIMARY KEY (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `flw_event_resource`
--

LOCK TABLES `flw_event_resource` WRITE;
/*!40000 ALTER TABLE `flw_event_resource` DISABLE KEYS */;
/*!40000 ALTER TABLE `flw_event_resource` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `flw_ru_batch`
--

DROP TABLE IF EXISTS `flw_ru_batch`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `flw_ru_batch` (
  `ID_` varchar(64) COLLATE utf8mb3_bin NOT NULL,
  `REV_` int DEFAULT NULL,
  `TYPE_` varchar(64) COLLATE utf8mb3_bin NOT NULL,
  `SEARCH_KEY_` varchar(255) COLLATE utf8mb3_bin DEFAULT NULL,
  `SEARCH_KEY2_` varchar(255) COLLATE utf8mb3_bin DEFAULT NULL,
  `CREATE_TIME_` datetime(3) NOT NULL,
  `COMPLETE_TIME_` datetime(3) DEFAULT NULL,
  `STATUS_` varchar(255) COLLATE utf8mb3_bin DEFAULT NULL,
  `BATCH_DOC_ID_` varchar(64) COLLATE utf8mb3_bin DEFAULT NULL,
  `TENANT_ID_` varchar(255) COLLATE utf8mb3_bin DEFAULT '',
  PRIMARY KEY (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `flw_ru_batch`
--

LOCK TABLES `flw_ru_batch` WRITE;
/*!40000 ALTER TABLE `flw_ru_batch` DISABLE KEYS */;
/*!40000 ALTER TABLE `flw_ru_batch` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `flw_ru_batch_part`
--

DROP TABLE IF EXISTS `flw_ru_batch_part`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `flw_ru_batch_part` (
  `ID_` varchar(64) COLLATE utf8mb3_bin NOT NULL,
  `REV_` int DEFAULT NULL,
  `BATCH_ID_` varchar(64) COLLATE utf8mb3_bin DEFAULT NULL,
  `TYPE_` varchar(64) COLLATE utf8mb3_bin NOT NULL,
  `SCOPE_ID_` varchar(64) COLLATE utf8mb3_bin DEFAULT NULL,
  `SUB_SCOPE_ID_` varchar(64) COLLATE utf8mb3_bin DEFAULT NULL,
  `SCOPE_TYPE_` varchar(64) COLLATE utf8mb3_bin DEFAULT NULL,
  `SEARCH_KEY_` varchar(255) COLLATE utf8mb3_bin DEFAULT NULL,
  `SEARCH_KEY2_` varchar(255) COLLATE utf8mb3_bin DEFAULT NULL,
  `CREATE_TIME_` datetime(3) NOT NULL,
  `COMPLETE_TIME_` datetime(3) DEFAULT NULL,
  `STATUS_` varchar(255) COLLATE utf8mb3_bin DEFAULT NULL,
  `RESULT_DOC_ID_` varchar(64) COLLATE utf8mb3_bin DEFAULT NULL,
  `TENANT_ID_` varchar(255) COLLATE utf8mb3_bin DEFAULT '',
  PRIMARY KEY (`ID_`),
  KEY `FLW_IDX_BATCH_PART` (`BATCH_ID_`),
  CONSTRAINT `FLW_FK_BATCH_PART_PARENT` FOREIGN KEY (`BATCH_ID_`) REFERENCES `flw_ru_batch` (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `flw_ru_batch_part`
--

LOCK TABLES `flw_ru_batch_part` WRITE;
/*!40000 ALTER TABLE `flw_ru_batch_part` DISABLE KEYS */;
/*!40000 ALTER TABLE `flw_ru_batch_part` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `test_leave`
--

DROP TABLE IF EXISTS `test_leave`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `test_leave` (
  `id` bigint NOT NULL COMMENT '主键',
  `leave_type` varchar(255) COLLATE utf8mb4_general_ci NOT NULL COMMENT '请假类型',
  `start_date` datetime NOT NULL COMMENT '开始时间',
  `end_date` datetime NOT NULL COMMENT '结束时间',
  `leave_days` int NOT NULL COMMENT '请假天数',
  `remark` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '请假原因',
  `status` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '状态',
  `create_dept` bigint DEFAULT NULL COMMENT '创建部门',
  `create_by` bigint DEFAULT NULL COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` bigint DEFAULT NULL COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `tenant_id` varchar(20) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '租户编号',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='请假申请表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `test_leave`
--

LOCK TABLES `test_leave` WRITE;
/*!40000 ALTER TABLE `test_leave` DISABLE KEYS */;
INSERT INTO `test_leave` VALUES (1864607914954567681,'1','2024-12-05 00:00:00','2025-01-15 00:00:00',42,'玩','waiting',103,1,'2024-12-05 17:49:04',1,'2024-12-05 17:49:32','000000');
/*!40000 ALTER TABLE `test_leave` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `wf_category`
--

DROP TABLE IF EXISTS `wf_category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `wf_category` (
  `id` bigint NOT NULL COMMENT '主键',
  `category_name` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '分类名称',
  `category_code` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '分类编码',
  `parent_id` bigint DEFAULT NULL COMMENT '父级id',
  `sort_num` int DEFAULT NULL COMMENT '排序',
  `tenant_id` varchar(20) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '租户编号',
  `create_dept` bigint DEFAULT NULL COMMENT '创建部门',
  `create_by` bigint DEFAULT NULL COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` bigint DEFAULT NULL COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uni_category_code` (`category_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='流程分类';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `wf_category`
--

LOCK TABLES `wf_category` WRITE;
/*!40000 ALTER TABLE `wf_category` DISABLE KEYS */;
INSERT INTO `wf_category` VALUES (1,'OA','OA',0,0,'000000',103,1,'2024-12-05 16:27:45',1,'2024-12-05 16:27:45');
/*!40000 ALTER TABLE `wf_category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `wf_definition_config`
--

DROP TABLE IF EXISTS `wf_definition_config`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `wf_definition_config` (
  `id` bigint NOT NULL COMMENT '主键',
  `table_name` varchar(255) COLLATE utf8mb4_general_ci NOT NULL COMMENT '表名',
  `definition_id` varchar(255) COLLATE utf8mb4_general_ci NOT NULL COMMENT '流程定义ID',
  `process_key` varchar(255) COLLATE utf8mb4_general_ci NOT NULL COMMENT '流程KEY',
  `version` int NOT NULL COMMENT '流程版本',
  `create_dept` bigint DEFAULT NULL COMMENT '创建部门',
  `create_by` bigint DEFAULT NULL COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` bigint DEFAULT NULL COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '备注',
  `tenant_id` varchar(20) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '租户编号',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uni_definition_id` (`definition_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='流程定义配置';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `wf_definition_config`
--

LOCK TABLES `wf_definition_config` WRITE;
/*!40000 ALTER TABLE `wf_definition_config` DISABLE KEYS */;
INSERT INTO `wf_definition_config` VALUES (1864607724738686978,'test_leave','leave1:1:1864607721269997569','leave1',1,103,1,'2024-12-05 17:48:19',1,'2024-12-05 17:48:19','','000000');
/*!40000 ALTER TABLE `wf_definition_config` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `wf_form_manage`
--

DROP TABLE IF EXISTS `wf_form_manage`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `wf_form_manage` (
  `id` bigint NOT NULL COMMENT '主键',
  `form_name` varchar(255) COLLATE utf8mb4_general_ci NOT NULL COMMENT '表单名称',
  `form_type` varchar(255) COLLATE utf8mb4_general_ci NOT NULL COMMENT '表单类型',
  `router` varchar(255) COLLATE utf8mb4_general_ci NOT NULL COMMENT '路由地址/表单ID',
  `remark` varchar(500) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '备注',
  `tenant_id` varchar(20) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '租户编号',
  `create_dept` bigint DEFAULT NULL COMMENT '创建部门',
  `create_by` bigint DEFAULT NULL COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` bigint DEFAULT NULL COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='表单管理';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `wf_form_manage`
--

LOCK TABLES `wf_form_manage` WRITE;
/*!40000 ALTER TABLE `wf_form_manage` DISABLE KEYS */;
INSERT INTO `wf_form_manage` VALUES (1,'请假申请','static','/workflow/leaveEdit/index',NULL,'000000',103,1,'2024-12-05 16:27:47',1,'2024-12-05 16:27:47');
/*!40000 ALTER TABLE `wf_form_manage` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `wf_node_config`
--

DROP TABLE IF EXISTS `wf_node_config`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `wf_node_config` (
  `id` bigint NOT NULL COMMENT '主键',
  `form_id` bigint DEFAULT NULL COMMENT '表单id',
  `form_type` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '表单类型',
  `node_name` varchar(255) COLLATE utf8mb4_general_ci NOT NULL COMMENT '节点名称',
  `node_id` varchar(255) COLLATE utf8mb4_general_ci NOT NULL COMMENT '节点id',
  `definition_id` varchar(255) COLLATE utf8mb4_general_ci NOT NULL COMMENT '流程定义id',
  `apply_user_task` char(1) COLLATE utf8mb4_general_ci DEFAULT '0' COMMENT '是否为申请人节点 （0是 1否）',
  `create_dept` bigint DEFAULT NULL COMMENT '创建部门',
  `create_by` bigint DEFAULT NULL COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` bigint DEFAULT NULL COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `tenant_id` varchar(20) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '租户编号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='节点配置';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `wf_node_config`
--

LOCK TABLES `wf_node_config` WRITE;
/*!40000 ALTER TABLE `wf_node_config` DISABLE KEYS */;
INSERT INTO `wf_node_config` VALUES (1864607716907921409,1,'static','申请人','Activity_0uscrk3','leave4:1:1864607716060672002','0',103,1,'2024-12-05 17:48:17',1,'2024-12-05 17:48:17','000000'),(1864607718220738561,1,'static','申请人','Activity_0uscrk3','leave3:1:1864607717742587907','0',103,1,'2024-12-05 17:48:17',1,'2024-12-05 17:48:17','000000'),(1864607720590520322,1,'static','申请人','Activity_0x6b71j','leave5:1:1864607719789408258','0',103,1,'2024-12-05 17:48:18',1,'2024-12-05 17:48:18','000000'),(1864607721848811521,1,'static','申请人','Activity_14633hx','leave1:1:1864607721269997569','0',103,1,'2024-12-05 17:48:18',1,'2024-12-05 17:48:18','000000'),(1864607722914164737,1,'static','申请人','Activity_19b1i4j','leave2:1:1864607722519900161','0',103,1,'2024-12-05 17:48:19',1,'2024-12-05 17:48:19','000000'),(1864607724273119234,1,'static','申请人','Activity_0zy3g6j','leave6:1:1864607723623002114','0',103,1,'2024-12-05 17:48:19',1,'2024-12-05 17:48:19','000000');
/*!40000 ALTER TABLE `wf_node_config` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `wf_task_back_node`
--

DROP TABLE IF EXISTS `wf_task_back_node`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `wf_task_back_node` (
  `id` bigint NOT NULL,
  `node_id` varchar(255) COLLATE utf8mb4_general_ci NOT NULL COMMENT '节点id',
  `node_name` varchar(255) COLLATE utf8mb4_general_ci NOT NULL COMMENT '节点名称',
  `order_no` int NOT NULL COMMENT '排序',
  `instance_id` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '流程实例id',
  `task_type` varchar(255) COLLATE utf8mb4_general_ci NOT NULL COMMENT '节点类型',
  `assignee` varchar(2000) COLLATE utf8mb4_general_ci NOT NULL COMMENT '审批人',
  `tenant_id` varchar(20) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '租户编号',
  `create_dept` bigint DEFAULT NULL COMMENT '创建部门',
  `create_by` bigint DEFAULT NULL COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` bigint DEFAULT NULL COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='节点审批记录';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `wf_task_back_node`
--

LOCK TABLES `wf_task_back_node` WRITE;
/*!40000 ALTER TABLE `wf_task_back_node` DISABLE KEYS */;
INSERT INTO `wf_task_back_node` VALUES (1864608032592211969,'Activity_14633hx','申请人',0,'1864607918167404545','userTask','1','000000',103,1,'2024-12-05 17:49:32',1,'2024-12-05 17:49:32');
/*!40000 ALTER TABLE `wf_task_back_node` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-12-06 14:55:03
