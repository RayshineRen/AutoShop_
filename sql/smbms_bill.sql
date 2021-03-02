/*
SQLyog Ultimate v12.08 (64 bit)
MySQL - 5.7.31-log : Database - smbms
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`smbms` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `smbms`;

/*Table structure for table `smbms_bill` */

DROP TABLE IF EXISTS `smbms_bill`;

CREATE TABLE `smbms_bill` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `billCode` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '账单编码',
  `productName` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '商品名称',
  `productDesc` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '商品描述',
  `productCount` decimal(20,2) DEFAULT NULL COMMENT '商品数量',
  `totalPrice` decimal(20,2) DEFAULT NULL COMMENT '商品总额',
  `creationDate` datetime DEFAULT NULL COMMENT '创建时间',
  `providerId` bigint(20) DEFAULT NULL COMMENT '供应商ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `smbms_bill` */

insert  into `smbms_bill`(`id`,`billCode`,`productName`,`productDesc`,`productCount`,`totalPrice`,`creationDate`,`providerId`) values (2,'BILL002','香皂、肥皂','日用品-皂类','1000.00','10000.00','2016-03-23 04:20:40',9),(3,'BILL003','大豆油','食品-食用油','300.00','5890.00','2014-12-14 13:02:03',6),(5,'BILL005','洗衣粉','日用品-清洁','500.00','7000.00','2014-12-14 13:02:03',9),(6,'BILL006','杏仁','食品-坚果','300.00','5000.00','2016-04-14 06:08:09',4),(7,'BILL001','沐浴液、洗衣粉','日用品','50.00','2300.00','2020-07-22 10:10:22',6),(9,'BILL008','纸杯、水杯','日用品-杯子','350.00','1750.00','2016-02-04 11:40:20',14),(11,'BILL010','百岁山','水中贵族','50.00','10000.00','2016-04-14 16:16:00',1),(12,'BILL011','各类方便面','食品-方便面','20.00','6000.00','2016-09-09 17:00:00',1),(16,'BILL015','可口可乐','饮料','2000.00','6000.00','2012-03-27 13:03:01',2),(17,'BILL016','脉动','饮料','1500.00','4500.00','2016-05-10 12:00:00',2),(18,'BILL017','哇哈哈','饮料','2000.00','4000.00','2015-11-24 15:12:03',2),(19,'BILL018','百事可乐','饮料','15.00','1500.00','2021-01-21 04:00:36',1),(20,'BILL019','农夫山泉','好喝 矿泉水 解渴','15.00','1500.00','2021-01-21 04:06:05',1);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
