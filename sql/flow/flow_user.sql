/*
Navicat MySQL Data Transfer

Source Server         : db_kwm-localhost
Source Server Version : 50717
Source Host           : localhost:3306
Source Database       : db_kwm

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2017-03-24 17:36:55
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for flow_user
-- ----------------------------
DROP TABLE IF EXISTS `flow_user`;
CREATE TABLE `flow_user` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `OPEN_ID` varchar(28) NOT NULL,
  `NICK_NAME` varchar(100) NOT NULL,
  `SEX` varchar(1) NOT NULL,
  `PROVINCE` varchar(128) DEFAULT NULL,
  `CITY` varchar(128) DEFAULT NULL,
  `COUNTRY` varchar(128) DEFAULT NULL,
  `HEAD_IMG_URL` varchar(255) DEFAULT NULL,
  `PRIVILEGE` varchar(255) DEFAULT NULL COMMENT '用户特权信息，json 数组，如微信沃卡用户为（chinaunicom）',
  `SUBSCRIBE_TIME` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '最后关注时间',
  `GMT_CREATE` datetime NOT NULL,
  `GMT_UPDATE` datetime NOT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `OPEN_ID` (`OPEN_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='微信用户表 flow_user\r\n1.用户关注公众号拿到的信息\r\n2.不支持多公众号接入';

-- ----------------------------
-- Records of flow_user
-- ----------------------------
INSERT INTO `flow_user` VALUES ('1', '1234567890AABBCCDD1234567890', '月下独酌', '1', null, null, null, null, null, null, '2017-03-15 15:00:07', '2017-03-15 15:00:07');
