/*
Navicat MySQL Data Transfer

Source Server         : db_kwm
Source Server Version : 50717
Source Host           : localhost:3306
Source Database       : db_kwm

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2017-03-15 18:24:16
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for flow_order_trans
-- ----------------------------
DROP TABLE IF EXISTS `flow_order_trans`;
CREATE TABLE `flow_order_trans` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `ORDER_NO` bigint(20) NOT NULL COMMENT '订单号',
  `MERCH_ID` bigint(20) NOT NULL COMMENT '商户ID',
  `SHOP_ID` bigint(20) NOT NULL COMMENT '店铺ID',
  `TRANS_CODE` varchar(20) NOT NULL COMMENT '交易代码-目前只有公众号支付',
  `TRANS_NAME` varchar(100) NOT NULL COMMENT '交易名称-如公众号支付',
  `STATE` varchar(20) NOT NULL COMMENT '订单状态代码',
  `STATE_DESC` varchar(100) NOT NULL COMMENT '订单状态描述',
  `TOTAL_PRICE` decimal(10,2) NOT NULL COMMENT '总价',
  `ACTUALLY_PAID` decimal(10,2) NOT NULL COMMENT '实付金额',
  `NICK_NAME` varchar(100) DEFAULT NULL COMMENT '微信用户昵称',
  `OPEN_ID` varchar(28) NOT NULL,
  `PREPAY_ID` varchar(50) DEFAULT NULL COMMENT '微信预支付交易会话标识',
  `BANK_TYPE` varchar(50) DEFAULT NULL COMMENT '付款银行',
  `GMT_CREATE` datetime NOT NULL,
  `GMT_UPDATE` datetime NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='订单流水表';
