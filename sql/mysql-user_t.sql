CREATE DATABASE IF NOT EXISTS db_kwm default charset utf8 COLLATE utf8_general_ci;

USE db_kwm;

DROP TABLE IF EXISTS `user_t`;  
  
CREATE TABLE `user_t` (  
  `id` int(11) NOT NULL AUTO_INCREMENT,  
  `user_name` varchar(40) NOT NULL,  
  `password` varchar(255) NOT NULL,  
  `age` int(4) NOT NULL,  
  PRIMARY KEY (`id`)  
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;  
  
/*Data for the table `user_t` */  
  
insert  into `user_t`(`id`,`user_name`,`password`,`age`) values (1,'测试人员1','123456',11); 
insert  into `user_t`(`id`,`user_name`,`password`,`age`) values (2,'测试人员2','123456',22); 
insert  into `user_t`(`id`,`user_name`,`password`,`age`) values (3,'测试人员3','123456',33); 
insert  into `user_t`(`id`,`user_name`,`password`,`age`) values (4,'测试人员4','123456',44); 
insert  into `user_t`(`id`,`user_name`,`password`,`age`) values (5,'测试人员5','123456',55);
insert  into `user_t`(`id`,`user_name`,`password`,`age`) values (6,'kwm','123456',36);  