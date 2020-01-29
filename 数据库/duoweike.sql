/*
SQLyog Ultimate v12.14 (64 bit)
MySQL - 8.0.18 : Database - duoweike
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`duoweike` /*!40100 DEFAULT CHARACTER SET utf8 */ /*!80016 DEFAULT ENCRYPTION='N' */;

USE `duoweike`;

/*Table structure for table `buyer` */

DROP TABLE IF EXISTS `buyer`;

CREATE TABLE `buyer` (
  `userId` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户id',
  `membershipCard` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '会员卡号',
  `membershipPassword` varchar(32) DEFAULT NULL COMMENT '会员卡密码',
  `integral` int(11) DEFAULT NULL COMMENT '积分数额',
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `cumoney` double DEFAULT NULL COMMENT '本次消费金额',
  `sunmoney` double DEFAULT NULL COMMENT '消费总金额',
  PRIMARY KEY (`id`),
  KEY `id` (`id`),
  KEY `FK_ID` (`userId`),
  CONSTRAINT `FK_ID` FOREIGN KEY (`userId`) REFERENCES `user` (`userId`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=43 DEFAULT CHARSET=utf8;

/*Data for the table `buyer` */

insert  into `buyer`(`userId`,`membershipCard`,`membershipPassword`,`integral`,`id`,`cumoney`,`sunmoney`) values 
('16935616666','415576','1',183,35,123,183),
('17712869014','377756','123',2103,37,1234,2103),
('12345678','304761','123',123456,38,123456,123456),
('1','395001','1',124,39,124,124),
('2','409556','2',22,40,22,22),
('3','421507','3',0,41,NULL,NULL),
('6','470644','1',12,42,12,12);

/*Table structure for table `rechagerecord` */

DROP TABLE IF EXISTS `rechagerecord`;

CREATE TABLE `rechagerecord` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `userid` varchar(20) NOT NULL COMMENT '用户id',
  `recharge_date` datetime DEFAULT NULL COMMENT '充值日期',
  `money` double DEFAULT NULL COMMENT '充值金额',
  PRIMARY KEY (`id`),
  KEY `re_user` (`userid`),
  CONSTRAINT `re_cord` FOREIGN KEY (`userid`) REFERENCES `recharge` (`userid`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `re_user` FOREIGN KEY (`userid`) REFERENCES `user` (`userId`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=utf8;

/*Data for the table `rechagerecord` */

insert  into `rechagerecord`(`id`,`userid`,`recharge_date`,`money`) values 
(33,'16935616666','2020-01-12 02:47:13',100),
(35,'17712869014','2020-01-28 14:21:23',500),
(36,'16935616666','2020-01-28 22:21:47',1000),
(37,'17712869014','2020-01-28 22:22:18',1000);

/*Table structure for table `recharge` */

DROP TABLE IF EXISTS `recharge`;

CREATE TABLE `recharge` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `userid` varchar(20) NOT NULL COMMENT '用户id',
  `restmoney` double DEFAULT NULL COMMENT '当前账户金额',
  PRIMARY KEY (`id`),
  KEY `recharge_ID` (`userid`),
  CONSTRAINT `recharge_ID` FOREIGN KEY (`userid`) REFERENCES `user` (`userId`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;

/*Data for the table `recharge` */

insert  into `recharge`(`id`,`userid`,`restmoney`) values 
(13,'16935616666',977),
(15,'17712869014',266);

/*Table structure for table `redemption` */

DROP TABLE IF EXISTS `redemption`;

CREATE TABLE `redemption` (
  `number` double DEFAULT NULL COMMENT '积分数量',
  `award` varchar(200) DEFAULT NULL COMMENT '奖品',
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

/*Data for the table `redemption` */

insert  into `redemption`(`number`,`award`,`id`) values 
(10,'土豆丝',5),
(200,'小鸡炖蘑菇',7),
(20,'红烧鲤鱼',8);

/*Table structure for table `spend` */

DROP TABLE IF EXISTS `spend`;

CREATE TABLE `spend` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `userid` varchar(20) NOT NULL COMMENT '用户id',
  PRIMARY KEY (`id`),
  KEY `userid` (`userid`),
  CONSTRAINT `spend_ibfk_1` FOREIGN KEY (`userid`) REFERENCES `user` (`userId`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

/*Data for the table `spend` */

/*Table structure for table `spenddetail` */

DROP TABLE IF EXISTS `spenddetail`;

CREATE TABLE `spenddetail` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `userId` varchar(16) NOT NULL COMMENT '用户id',
  `spendtime` datetime DEFAULT NULL COMMENT '消费时间',
  `spendnumber` double DEFAULT NULL COMMENT '消费金额',
  PRIMARY KEY (`id`),
  KEY `userId` (`userId`),
  CONSTRAINT `spenddetail_ibfk_1` FOREIGN KEY (`userId`) REFERENCES `user` (`userId`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

/*Data for the table `spenddetail` */

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `userId` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户id,用于标识唯一的用户',
  `userName` varchar(32) DEFAULT NULL COMMENT '用户名',
  `password` varchar(32) DEFAULT NULL COMMENT '密码（加密之后的结果）',
  `token` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '用于标识设备信息',
  `userType` int(11) NOT NULL COMMENT '0：超级管理员；1：普通管理员；2：用户',
  `passwordToken` varchar(32) NOT NULL COMMENT '密保，用于恢复密码时使用',
  PRIMARY KEY (`id`,`userId`),
  UNIQUE KEY `userId` (`userId`)
) ENGINE=InnoDB AUTO_INCREMENT=72 DEFAULT CHARSET=utf8;

/*Data for the table `user` */

insert  into `user`(`id`,`userId`,`userName`,`password`,`token`,`userType`,`passwordToken`) values 
(1,'16639413256','朱旭','123','F974619B53483884803DCB867A03442E',0,'123'),
(40,'16639413257','朱旭——admin','123','9614DDB7D899FA5ED9C893695CB4268C',1,'123'),
(57,'16639413258','a','1','1',1,'1'),
(63,'16935616666','saile','1',NULL,2,'1'),
(66,'17712869014','朱旭','123',NULL,2,'123'),
(67,'12345678','朱旭旭小','123',NULL,2,'123'),
(68,'1','1','1',NULL,2,'1'),
(69,'2','2','2',NULL,2,'2'),
(70,'3','3','3',NULL,2,'3'),
(71,'6','9','1',NULL,2,'1');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
