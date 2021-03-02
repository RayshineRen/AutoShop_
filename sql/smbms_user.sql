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

/*Table structure for table `smbms_user` */

DROP TABLE IF EXISTS `smbms_user`;

CREATE TABLE `smbms_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `userCode` varchar(15) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '用户编码',
  `userName` varchar(15) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '用户名称',
  `userPassword` varchar(15) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '用户密码',
  `gender` int(10) DEFAULT NULL COMMENT '性别（1:女、 2:男）',
  `birthday` date DEFAULT NULL COMMENT '出生日期',
  `phone` varchar(15) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '手机',
  `address` varchar(30) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '地址',
  `userRole` bigint(20) DEFAULT NULL COMMENT '用户角色（取自角色表-角色id）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=89 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `smbms_user` */

insert  into `smbms_user`(`id`,`userCode`,`userName`,`userPassword`,`gender`,`birthday`,`phone`,`address`,`userRole`) values (1,'admin','系统管理员','123',1,'1983-10-10','13688889999','nudt',1),(2,'liming','李明','0000000',2,'1983-12-10','13688884457','长沙市开福区1号',2),(5,'lumingfei','路明非','0000000',1,'1984-06-03','18567542323','长沙市开福区12号',2),(6,'Amine','Amine','123',1,'1983-06-15','13544561111','长沙市开福区61号',3),(7,'xiaolongnv','小龙女','0000000',2,'1982-12-31','13444561124','长沙市开福区3号',3),(8,'zhaofeiyan','赵飞燕','0000000',1,'1986-03-07','18098764545','长沙市开福区10号',3),(11,'maliang','马良','0000000',1,'1978-03-11','13367890985','长沙市开福区11号',3),(12,'lvchen','吕辰','0000000',1,'1986-03-28','18098765434','长沙市开福区13号',3),(13,'dengye','邓叶','0000000',2,'1981-11-04','13689674534','长沙市开福区111号',3),(14,'yangguo','杨过','123',2,'1980-01-01','13388886623','福元路一号',3),(16,'Ray','任睿轩','rrx123456',1,'2000-09-06','13862110985','sgfd',1),(78,'asd','asd','1234567',1,'2021-01-05','13854128025','福元路1号F2',2);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
