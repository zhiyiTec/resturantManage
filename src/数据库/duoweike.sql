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
  `userId` varchar(32) NOT NULL COMMENT '用户id',
  `membershipCard` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '会员卡号',
  `membershipPassword` varchar(32) DEFAULT NULL COMMENT '会员卡密码',
  `integral` int(11) DEFAULT NULL COMMENT '积分数额',
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `cumoney` double DEFAULT NULL COMMENT '本次消费金额',
  `sunmoney` double DEFAULT NULL COMMENT '消费总金额',
  PRIMARY KEY (`userId`,`id`),
  KEY `id` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8;

/*Data for the table `buyer` */

insert  into `buyer`(`userId`,`membershipCard`,`membershipPassword`,`integral`,`id`,`cumoney`,`sunmoney`) values 
('16639413259','524941','1',0,24,NULL,NULL);

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

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `userId` varchar(32) NOT NULL COMMENT '用户id,用于标识唯一的用户',
  `userName` varchar(32) DEFAULT NULL COMMENT '用户名',
  `password` varchar(32) DEFAULT NULL COMMENT '密码（加密之后的结果）',
  `token` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '用于标识设备信息',
  `userType` int(11) NOT NULL COMMENT '0：超级管理员；1：普通管理员；2：用户',
  `passwordToken` varchar(32) NOT NULL COMMENT '密保，用于恢复密码时使用',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=54 DEFAULT CHARSET=utf8;

/*Data for the table `user` */

insert  into `user`(`id`,`userId`,`userName`,`password`,`token`,`userType`,`passwordToken`) values 
(1,'16639413256','朱旭','0117','123',0,'123'),
(40,'16639413257','朱旭——admin','123456',NULL,1,'123'),
(51,'16639413259','111','1',NULL,2,'11');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
