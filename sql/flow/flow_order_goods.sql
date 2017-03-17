/*
Navicat MySQL Data Transfer

Source Server         : db_kwm
Source Server Version : 50717
Source Host           : localhost:3306
Source Database       : db_kwm

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2017-03-14 16:31:31
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for flow_order_goods
-- ----------------------------
DROP TABLE IF EXISTS `flow_order_goods`;
CREATE TABLE `flow_order_goods` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `ORDER_NO` bigint(20) NOT NULL COMMENT '订单号',
  `GOODS_ID` bigint(20) NOT NULL COMMENT '订单商品的ID',
  `AMOUNT` int(10) NOT NULL COMMENT '数量',
  `DISCOUT` decimal(3,2) NOT NULL COMMENT '折扣率 0.01-1.00之间 1.00表示没有折扣。做折扣活动需要创建活动表，每种商品同时只有一种折扣',
  `GMT_CREATE` datetime NOT NULL,
  `GMT_UPDATE` datetime NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='订单商品表 同一订单可能有多个商品';
