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

/*Table structure for table `auto_goods` */

DROP TABLE IF EXISTS `auto_goods`;

CREATE TABLE `auto_goods` (
  `id` int(4) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) NOT NULL,
  `type` varchar(10) NOT NULL,
  `classCode` int(2) NOT NULL,
  `cost` varchar(10) NOT NULL,
  `number` int(4) NOT NULL,
  `description` varchar(100) DEFAULT NULL,
  `providerCode` varchar(20) NOT NULL,
  `img` varchar(50) DEFAULT NULL,
  `likeit` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8;

/*Data for the table `auto_goods` */

insert  into `auto_goods`(`id`,`name`,`type`,`classCode`,`cost`,`number`,`description`,`providerCode`,`img`,`likeit`) values (1,'农夫山泉','饮品',1,'2.5元',20,'矿泉水 解渴 不贵','HB_GYS001','img/nongfu.jpeg',3),(2,'百岁山','饮品',1,'25元',20,'矿泉水 解渴 贵 水中贵族 身份的象征','HB_GYS001','img/baisui.jpg',4),(3,'冰露','饮品',1,'1元',30,'矿泉水 解渴 便宜','HB_GYS001','img/binglu.jpg',5),(4,'哇哈哈','饮品',1,'3元',10,'矿泉水 解渴 ','HB_GYS001','img/wahaha.jpeg',6),(5,'百事可乐','饮品',1,'4元',15,'可乐 好喝 不贵','HB_GYS001','img/baishi.jpg',2),(6,'可口可乐','饮品',1,'4元',15,'可乐 好喝 不贵','HB_GYS001','img/kekou.jpg',1),(7,'雪碧','饮品',1,'3元',15,'雪碧 好喝 便宜','HB_GYS001','img/xuebi.jpeg',3),(8,'七喜','饮品',1,'4元',15,'七喜 好喝 不贵','HB_GYS001','img/qixi.jpeg',4),(9,'芬达','饮品',1,'4元',15,'芬达 好喝 不贵','HB_GYS001','img/fenda.jpeg',6),(10,'大瓶百事','饮品',1,'25元',5,'可乐 好喝 贵 量大','HB_GYS001','img/BigBaishi.jpg',11),(11,'牙刷','日用品',2,'10元',20,'刷牙 好用 不贵','GX_GYS001','img/yashua.jpg',1),(12,'牙膏','日用品',2,'7元',20,'刷牙 好用 不贵','GX_GYS001','img/yagao.jpg',2),(13,'毛巾','日用品',2,'15元',20,'擦脸 擦汗 好用 ','GX_GYS001','img/xiyifen.jpeg',3),(14,'牙签','日用品',2,'3元',20,'剔牙 好用 方便','GX_GYS001','img/yaqian.jpeg',4),(15,'纸杯','日用品',2,'5元',20,'方便 好用 喝水 杯','ZJ_GYS002','img/zhibei.jpg',3),(16,'水杯','日用品',2,'10元',20,'方便 好用 喝水 杯','ZJ_GYS002','img/zhibei.jpg',2),(17,'洗衣粉','日用品',2,'11元',20,'方便 好用 量小 洗衣','SD_GYS001','img/xiyifen.jpeg',4),(18,'小瓶洗衣液','日用品',2,'7元',20,'方便 好用 量小 洗衣','SD_GYS001','img/xiyiye.jpeg',3),(19,'每日坚果','食品',3,'5元',30,'好吃 量小 多样 坚果','GZ_GYS002','img/bigenguo.jpeg',9),(20,'碧根果','食品',3,'35元',20,'特别好吃 量大 坚果','GZ_GYS002','img/bigenguo.jpeg',15),(21,'巴旦木','食品',3,'25元',20,'好吃 量大 坚果','GZ_GYS002','img/xiaweiyi.jpeg',7),(22,'夏威夷果','食品',3,'30元',20,'好吃 量大 坚果','GZ_GYS002','img/xiaweiyi.jpeg',6),(23,'芒果干','食品',3,'15元',20,'好吃 芒果 甜 ','GZ_GYS002','img/mangguo.jpeg',6),(24,'鱿鱼丝','食品',3,'15元',20,'好吃 鱿鱼 丝','GZ_GYS002','img/youyusi.jpg',8),(25,'康师傅牛肉面','食品',3,'5元',20,'方便面 好吃 牛肉','GZ_GYS002','img/hongshao.jpg',9),(26,'牛肉板面','食品',3,'5元',20,'方便面 好吃 牛肉','GZ_GYS002','img/banmian.jpg',11),(27,'统一牛肉面','食品',3,'5元',20,'方便面 好吃 牛肉','GZ_GYS002','img/hongshao.jpg',13),(28,'康师傅老坛酸菜','食品',3,'5元',20,'方便面 酸菜 ','GZ_GYS002','img/hongshao.jpg',6),(29,'日式豚骨拉面','食品',3,'8元',20,'汤面 方便面 日式 好吃','GZ_GYS002','img/hongshao.jpg',8),(30,'小鸡炖蘑菇面','食品',3,'5元',20,'方便面 好吃 鸡 蘑菇','GZ_GYS002','img/xiaojimogu.jpeg',7),(31,'座垫','日用品',2,'29元',5,'日用品 好用 垫子','GZ_GYS003','img/xiyifen.jpeg',1),(32,'枕头','日用品',2,'16元',5,'日用品 好用 枕','GZ_GYS003','img/xiyifen.jpeg',0),(33,'沐浴液','日用品',2,'8元',15,'日用品 沐浴 好用','GX_GYS001','img/xiyifen.jpeg',2);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
