/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50556
Source Host           : localhost:3306
Source Database       : meite_integral

Target Server Type    : MYSQL
Target Server Version : 50556
File Encoding         : 65001

Date: 2019-04-10 14:08:45
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for meite_integral
-- ----------------------------
DROP TABLE IF EXISTS `meite_integral`;
CREATE TABLE `meite_integral` (
  `ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `USER_ID` int(11) DEFAULT NULL COMMENT '用户ID',
  `PAYMENT_ID` varchar(1024) DEFAULT NULL COMMENT '支付ID',
  `INTEGRAL` varchar(32) DEFAULT NULL COMMENT '积分',
  `AVAILABILITY` int(11) DEFAULT NULL COMMENT '是否可用',
  `REVISION` int(11) DEFAULT NULL COMMENT '乐观锁',
  `CREATED_BY` varchar(32) DEFAULT NULL COMMENT '创建人',
  `CREATED_TIME` datetime DEFAULT NULL COMMENT '创建时间',
  `UPDATED_BY` varchar(32) DEFAULT NULL COMMENT '更新人',
  `UPDATED_TIME` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8 COMMENT=' ';

-- ----------------------------
-- Records of meite_integral
-- ----------------------------
INSERT INTO `meite_integral` VALUES ('21', '644064', '275597844126240768', '100', '1', '0', null, '2019-04-09 22:15:53', null, '2019-04-09 22:15:53');
