/*
Navicat MySQL Data Transfer

Source Server         : 本机mysql5.7
Source Server Version : 50717
Source Host           : localhost:3306
Source Database       : db_kwm

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2017-03-04 15:48:21
*/
use db_kwm;
SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for flow_telecom_operator
-- ----------------------------
DROP TABLE IF EXISTS `flow_telecom_operator`;
CREATE TABLE `flow_telecom_operator` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `TP_NAME` varchar(45) NOT NULL COMMENT '运营商的名字，如"电信"、"联通","移动"',
  `GMT_CREATE` datetime NOT NULL,
  `GMT_UPDATE` datetime NOT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `TP_NAME_UNIQUE` (`TP_NAME`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of flow_telecom_operator
-- ----------------------------
INSERT INTO `flow_telecom_operator` VALUES ('1', '移动', NOW(), NOW());
INSERT INTO `flow_telecom_operator` VALUES ('2', '联通', NOW(), NOW());
INSERT INTO `flow_telecom_operator` VALUES ('3', '电信', NOW(), NOW());
