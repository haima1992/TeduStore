/*
MySQL Data Transfer
Source Host: localhost
Source Database: tedu_store
Target Host: localhost
Target Database: tedu_store
Date: 2019/3/31 0:57:07
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL,
  `password` char(32) NOT NULL,
  `gender` int(11) DEFAULT NULL,
  `phone` varchar(20) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `uuid` char(36) DEFAULT NULL,
  `created_user` varchar(50) DEFAULT NULL,
  `created_time` datetime DEFAULT NULL,
  `modified_user` varchar(50) DEFAULT NULL,
  `modified_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records 
-- ----------------------------
INSERT INTO `t_user` VALUES ('1', 'yaya', 'yaya520', '0', '13500505445', '13500505445@163.com', null, null, null, 'System', '2018-08-28 19:04:05');
INSERT INTO `t_user` VALUES ('2', '圆圆', '123456', '0', '13500505445', '13500505445@163.com', null, null, null, null, null);
INSERT INTO `t_user` VALUES ('3', 'ABC', 'BC10BDC50003EB79CF1764D364E692DE', null, '12345678900', 'abcdef@qq.com', '2DC34038-618F-4BF4-83DE-326216E6E993', 'System', '2018-08-28 20:30:29', null, null);
INSERT INTO `t_user` VALUES ('4', '二狗子', 'D4D6925454DEB5BB0478A3224122BD75', '1', 'ergou@qq.com', '12345678900', 'BAFF2B0C-CD8F-4DB0-957F-F0F7C39D523B', 'System', '2018-08-29 18:48:37', 'System', '2018-08-29 18:58:48');
INSERT INTO `t_user` VALUES ('5', '难道虚伪不是', 'BD51A438987BC977E4D0D62927D56C0E', '1', '943909831@qq.com', '13500505445', '39924EBB-C39E-42A8-837E-F02017E04C67', 'System', '2018-08-31 16:32:49', 'System', '2018-08-31 17:34:55');
INSERT INTO `t_user` VALUES ('6', 'yangfan', '98F5DDAD60C07C2799AFC150B17B11DE', '1', '943909831@qq.com', '13500505445', 'A7E2D1E8-7857-449A-9BAF-4E7CBF1C5DEF', 'System', '2019-03-26 15:09:34', 'System', '2019-03-26 15:09:54');
