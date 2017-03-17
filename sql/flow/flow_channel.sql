/*
Navicat MySQL Data Transfer

Source Server         : 本机mysql5.7
Source Server Version : 50717
Source Host           : localhost:3306
Source Database       : db_kwm

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2017-03-04 11:21:31
*/

use db_kwm;

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for flow_channel
-- ----------------------------
DROP TABLE IF EXISTS `flow_channel`;
CREATE TABLE `flow_channel` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `CHANNEL_NAME` varchar(128) NOT NULL COMMENT '渠道名称',
  `CHANNEL_DESC` varchar(512) DEFAULT NULL,
  `GMT_CREATE` datetime NOT NULL,
  `GMT_UPDATE` datetime NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of flow_channel
-- ----------------------------
INSERT INTO `flow_channel` VALUES ('1', '测试渠道1', '测试渠道1的描述', NOW(), NOW());
INSERT INTO `flow_channel` VALUES ('2', '测试渠道2', '测试渠道2的描述', NOW(), NOW());
INSERT INTO `flow_channel` VALUES ('3', '测试渠道3', '测试渠道3的描述', NOW(), NOW());
