/*
MySQL Data Transfer
Source Host: localhost
Source Database: tedu_store
Target Host: localhost
Target Database: tedu_store
Date: 2019/3/31 0:56:38
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for t_address
-- ----------------------------
DROP TABLE IF EXISTS `t_address`;
CREATE TABLE `t_address` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `uid` int(11) NOT NULL,
  `recv_name` varchar(20) NOT NULL,
  `recv_province` char(6) NOT NULL,
  `recv_city` char(6) NOT NULL,
  `recv_area` char(6) NOT NULL,
  `recv_address` varchar(50) NOT NULL,
  `recv_district` varchar(50) NOT NULL,
  `recv_phone` varchar(16) NOT NULL,
  `recv_tel` varchar(16) DEFAULT NULL,
  `recv_zip` char(6) DEFAULT NULL,
  `tag` varchar(8) DEFAULT NULL,
  `is_default` int(11) DEFAULT NULL,
  `created_user` varchar(20) DEFAULT NULL,
  `created_time` datetime DEFAULT NULL,
  `modified_user` varchar(20) DEFAULT NULL,
  `modified_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records 
-- ----------------------------
INSERT INTO `t_address` VALUES ('1', '8', '汪永赛', '340000', '340100', '340103', '长江中路98号银泰中心写字楼10楼达内教育', '安徽省合肥市庐阳区', '13579246800', null, null, null, '1', null, null, null, null);
INSERT INTO `t_address` VALUES ('2', '5', '杨帆', '340000', '340100', '340103', '长江中路98号银泰中心写字楼10楼达内教育', '安徽省合肥市庐阳区', '13500505445', '', '', '公司', '1', 'System', '2018-09-03 11:14:59', null, null);
INSERT INTO `t_address` VALUES ('5', '5', '杨帆', '340000', '341500', '341523', '城关镇舒中村南环路城南平价超市', '安徽省六安市舒城县', '13500505445', '', '', '家', '0', 'System', '2018-09-03 15:58:03', null, null);
INSERT INTO `t_address` VALUES ('6', '5', '杨帆', '340000', '340100', '340111', '皖江东路与当涂支路交叉口地矿家园4栋1003', '安徽省合肥市包河区', '13500505445', '', '', '宿舍', '0', 'System', '2018-09-03 16:04:57', null, null);
INSERT INTO `t_address` VALUES ('7', '5', '汪永赛', '340000', '340100', '340103', '长江中路98号银泰中心写字楼10楼达内教育', '安徽省合肥市庐阳区', '12345678900', '', '', '公司', '0', 'System', '2018-09-03 16:09:38', null, null);
INSERT INTO `t_address` VALUES ('8', '5', '舒志情', '340000', '340100', '340103', '长江中路98号银泰中心写字楼10楼达内教育', '安徽省合肥市庐阳区', '12345678900', '', '', '公司', '0', 'System', '2018-09-03 16:13:34', null, null);
INSERT INTO `t_address` VALUES ('9', '5', '韩某燕', '340000', '340100', '340103', '长江中路98号银泰中心写字楼10楼达内教育', '安徽省合肥市庐阳区', '12345678900', '', '', '公司', '0', 'System', '2018-09-03 16:13:49', null, null);
INSERT INTO `t_address` VALUES ('10', '5', '张鑫', '340000', '340100', '340103', '长江中路98号银泰中心写字楼10楼达内教育', '安徽省合肥市庐阳区', '12345678900', '', '', '公司', '0', 'System', '2018-09-03 16:14:15', '难道虚伪不是', '2018-09-05 09:01:13');
INSERT INTO `t_address` VALUES ('11', '5', '杨帆', '320000', '320100', '320114', '金蕊家园', '江苏省南京市雨花台区', '13500505445', '', '', '宿舍', '0', 'System', '2018-09-04 10:48:42', 'System', '2018-09-04 10:48:42');
