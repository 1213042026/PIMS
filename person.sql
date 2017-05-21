/*
Navicat MySQL Data Transfer
Source Host     : localhost:3306
Source Database : person
Target Host     : localhost:3306
Target Database : person
Date: 2013-5-22 17:17:17
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for date
-- ----------------------------
DROP TABLE IF EXISTS `date`;
CREATE TABLE `date` (
  `userName` varchar(30) NOT NULL,
  `date` varchar(30) NOT NULL,
  `thing` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=gb2312;

-- ----------------------------
-- Records of date
-- ----------------------------
INSERT INTO `date` VALUES ('zzf', '2013-10-13', '去看海！');

-- ----------------------------
-- Table structure for file
-- ----------------------------
DROP TABLE IF EXISTS `file`;
CREATE TABLE `file` (
  `userName` varchar(255) default NULL,
  `title` varchar(255) default NULL,
  `name` varchar(255) default NULL,
  `contentType` varchar(255) default NULL,
  `size` varchar(255) default NULL,
  `filePath` varchar(255) default NULL
) ENGINE=InnoDB DEFAULT CHARSET=gb2312;

-- ----------------------------
-- Records of file
-- ----------------------------

-- ----------------------------
-- Table structure for friends
-- ----------------------------
DROP TABLE IF EXISTS `friends`;
CREATE TABLE `friends` (
  `userName` varchar(30) NOT NULL,
  `name` varchar(30) NOT NULL,
  `phone` varchar(30) NOT NULL,
  `email` varchar(30) NOT NULL,
  `workPlace` varchar(30) NOT NULL,
  `place` varchar(30) NOT NULL,
  `QQ` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=gb2312;

-- ----------------------------
-- Records of friends
-- ----------------------------
INSERT INTO `friends` VALUES ('zzf', '小明', '67677788', 'xiaoming@163.com', '北京', '北京', '100010');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `userName` varchar(30) NOT NULL,
  `password` varchar(30) NOT NULL,
  `name` varchar(30) NOT NULL,
  `sex` varchar(30) NOT NULL,
  `birth` varchar(30) NOT NULL,
  `nation` varchar(30) NOT NULL,
  `edu` varchar(30) NOT NULL,
  `work` varchar(30) NOT NULL,
  `phone` varchar(30) NOT NULL,
  `place` varchar(30) NOT NULL,
  `email` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=gb2312;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('zzf', '123', '张志锋', '男', '1978-01-01', '汉族', '硕士', '教师', '56565656', '北京', 'zzf@163.com');

DROP TABLE IF EXISTS `finance`;
CREATE TABLE `finance` (
  `userName` varchar(30) NOT NULL,
  `income` varchar(30) NOT NULL,
  `consumptionItem` varchar(30) NOT NULL,
  `consumptionSum` varchar(30) NOT NULL,
  `consumptionTime` varchar(30) NOT NULL,
  `remainingSum` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=gb2312;

-- ----------------------------
-- Records of finance
-- ----------------------------
INSERT INTO `finance` VALUES ('zzf', '10000', 'watch film', '200', '2017-01-01', '9800');