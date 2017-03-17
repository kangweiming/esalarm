/*
Navicat MySQL Data Transfer

Source Server         : db_kwm
Source Server Version : 50717
Source Host           : localhost:3306
Source Database       : db_kwm

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2017-03-14 16:24:46
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for flow_order
-- ----------------------------
DROP TABLE IF EXISTS `flow_order`;
CREATE TABLE `flow_order` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `ORDER_NO` bigint(20) NOT NULL COMMENT '订单号',
  `TYPE` varchar(10) NOT NULL COMMENT '订单类型 流量商品订单类型码为''01''',
  `STATE` varchar(20) NOT NULL COMMENT '订单状态代码',
  `STATE_DESC` varchar(100) NOT NULL COMMENT '订单状态描述',
  `EXPIRE_TIME` datetime NOT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '有效期截止时间',
  `USER_ID` bigint(20) NOT NULL COMMENT '下单人ID ，在这里对应 flow_user的ID字段',
  `MOBILE` varchar(11) NOT NULL COMMENT '收货人',
  `TOTAL_PRICE` decimal(10,2) NOT NULL COMMENT '总价',
  `ACTUALLY_PAID` decimal(10,2) NOT NULL COMMENT '实付金额',
  `GMT_CREATE` datetime NOT NULL,
  `GMT_UPDATE` datetime NOT NULL,
  PRIMARY KEY (`ID`,`ORDER_NO`),
  UNIQUE KEY `ORDER_NO` (`ORDER_NO`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='订单表';
