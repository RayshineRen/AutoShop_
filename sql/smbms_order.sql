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

/*Table structure for table `smbms_order` */

DROP TABLE IF EXISTS `smbms_order`;

CREATE TABLE `smbms_order` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `orderCode` varchar(20) NOT NULL,
  `productName` varchar(20) NOT NULL,
  `productDesc` varchar(50) NOT NULL,
  `totalPrice` decimal(20,2) NOT NULL,
  `creationDate` datetime NOT NULL,
  `userCode` varchar(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8;

/*Data for the table `smbms_order` */

insert  into `smbms_order`(`id`,`orderCode`,`productName`,`productDesc`,`totalPrice`,`creationDate`,`userCode`) values (1,'ORDER001','农夫山泉','好喝 解渴','2.50','2021-01-21 12:25:21','admin'),(2,'ORDER002','百岁山','好喝 贵族','25.00','2021-01-21 12:25:55','admin'),(3,'ORDER003','冰露','好喝 矿泉水 解渴','1.00','2021-01-21 06:52:38','admin'),(4,'ORDER004','哇哈哈','矿泉水 解渴 ','3.00','2021-01-21 06:54:36','admin'),(5,'ORDER005','大瓶百事','可乐 好喝 贵 量大','25.00','2021-01-21 06:55:04','admin'),(6,'ORDER006','小瓶洗衣液','方便 好用 量小','7.00','2021-01-21 06:55:22','admin'),(7,'ORDER007','牙刷','刷牙 好用 不贵','10.00','2021-01-21 07:16:52','admin'),(8,'ORDER008','沐浴液','日用品 沐浴 好用','8.00','2021-01-22 16:37:11','admin'),(9,'ORDER9','日式豚骨拉面','汤面 方便面','8.00','2021-01-22 16:45:36','admin'),(10,'ORDER10','牛肉板面','方便面 好吃 牛肉','5.00','2021-01-22 16:47:27','Amine'),(11,'ORDER11','鱿鱼丝','好吃','15.00','2021-01-23 02:45:12','admin'),(12,'ORDER12','大瓶百事','可乐 好喝 贵 量大','25.00','2021-01-24 14:57:39','Amine'),(13,'ORDER13','百事可乐','可乐 好喝 不贵','4.00','2021-01-24 15:01:26','admin'),(14,'ORDER14','雪碧','可乐 好喝 便宜','3.00','2021-01-25 07:46:49','admin'),(15,'ORDER15','大瓶百事','可乐 好喝 贵 量大','25.00','2021-01-25 07:48:27','Amine'),(16,'ORDER16','沐浴液','日用品 沐浴 好用','8.00','2021-01-25 08:28:04','Amine'),(17,'ORDER17','大瓶百事','可乐 好喝 贵 量大','25.00','2021-01-29 10:19:48','Amine'),(18,'ORDER18','大瓶百事','可乐 好喝 贵 量大','25.00','2021-01-30 11:04:18','admin'),(19,'ORDER19','大瓶百事','可乐 好喝 贵 量大','25.00','2021-01-30 11:10:18','admin'),(20,'ORDER20','芒果干','好吃 ','15.00','2021-01-30 12:02:34','admin'),(21,'ORDER21','每日坚果','好吃 量小 多样 坚果','5.00','2021-01-31 16:30:50','admin'),(22,'ORDER22','芬达','芬达 好喝 不贵','4.00','2021-01-31 16:38:45','Amine');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
