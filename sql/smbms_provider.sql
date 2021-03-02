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

/*Table structure for table `smbms_provider` */

DROP TABLE IF EXISTS `smbms_provider`;

CREATE TABLE `smbms_provider` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `proCode` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '供应商编码',
  `proName` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '供应商名称',
  `proDesc` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '供应商详细描述',
  `proContact` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '供应商联系人',
  `proPhone` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '联系电话',
  `proAddress` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '地址',
  `proFax` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '传真',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `smbms_provider` */

insert  into `smbms_provider`(`id`,`proCode`,`proName`,`proDesc`,`proContact`,`proPhone`,`proAddress`,`proFax`) values (1,'HB_GYS001','石家庄食品公司','长期合作伙伴，主营产品: 饮料、水饮料、休闲食品等','王一','13309094212','河北省石家庄市高新区','0311-67738876'),(2,'GZ_GYS001','广州方便面厂','初次合作伙伴，主营产品：各种方便面等','李一','13402013312','广东省广州市高新区','0755-67776212'),(3,'GZ_GYS002','广州食品加工厂','长期合作伙伴，主营产品：坚果炒货.果脯蜜饯','林一','18599897645','广东省广州市高新区','0755-67772341'),(4,'JS_GYS001','江苏食品加工厂','长期合作伙伴，主营产品：鸡精、调味料','张一','13754444221','江苏省南京市高新区','0523-21299098'),(5,'ZJ_GYS001','浙江食品厂','长期合作伙伴，主营产品：甜面酱、豆瓣酱','李二','18099953223','浙江省杭州市高新区','0574-34449090'),(6,'GX_GYS001','广西百货加工厂','长期合作伙伴，主营产品：日化产品','李三','13323566543','广西省南宁市高新区','0771-98861134'),(7,'GZ_GYS003','广州日用品厂','长期合作伙伴，主营产品：坐垫、靠垫、枕头等','王二','13562276775','广东省广州市高新区','020-85542231'),(8,'SD_GYS001','山东日用品大厂','长期合作伙伴，主营产品：洗衣粉、洗衣液等','刘义','13245468787','山东省济南市章丘区','0531-53362445'),(9,'ZJ_GYS002','浙江日用品厂','长期合作伙伴，主营产品：塑料杯、保鲜杯','刘二','13212331567','浙江省杭州市高新区','0579-34452321');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
