/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50717
Source Host           : localhost:3306
Source Database       : htshop

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2019-06-21 20:26:16
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for mp_about
-- ----------------------------
DROP TABLE IF EXISTS `mp_about`;
CREATE TABLE `mp_about` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `content` longtext COMMENT '关于我们',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='关于我们';

-- ----------------------------
-- Records of mp_about
-- ----------------------------
INSERT INTO `mp_about` VALUES ('1', '我们地址在xxxxxx<img src=\"http://www.kulongtai.com/1.jpg\"/>来吧');

-- ----------------------------
-- Table structure for mp_card
-- ----------------------------
DROP TABLE IF EXISTS `mp_card`;
CREATE TABLE `mp_card` (
  `card_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '我的卡券id',
  `card_no` int(12) DEFAULT NULL COMMENT '卡券消费码',
  `user_id` int(11) DEFAULT NULL COMMENT '用户id',
  `sku_id` varchar(32) DEFAULT NULL COMMENT '商品id',
  `card_name` varchar(255) DEFAULT NULL COMMENT '卡券名称',
  `total_frequency` int(11) DEFAULT NULL COMMENT '总次数',
  `rest_frequency` int(11) DEFAULT NULL COMMENT '剩余次数',
  `card_content` longtext COMMENT '卡片详情',
  `valid_flag` char(1) DEFAULT '1' COMMENT '有效标记（1有效0无效）',
  `order_id` int(11) DEFAULT NULL COMMENT '订单id',
  `valide_time` datetime DEFAULT NULL COMMENT '有效期至',
  `catagory` char(1) DEFAULT NULL COMMENT '1:洗车2保养',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`card_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='用户卡券表';

-- ----------------------------
-- Records of mp_card
-- ----------------------------
INSERT INTO `mp_card` VALUES ('1', '1234567981', '1', '1', '洗车卡', '20', '18', '<img src=\"http://www.kulongtai.com/1.jpg\"/>我们洗车最实惠', '1', '1', '2019-07-06 00:37:44', '1', '2019-06-21 00:37:53', '2019-06-21 20:04:48');
INSERT INTO `mp_card` VALUES ('2', '654987123', '1', '2', '保养卡', '10', '8', '更换机油机滤<img src=\"http://www.kulongtai.com/1.jpg\"/>我们洗车最实惠', '1', '2', '2019-07-26 20:00:24', '2', '2019-06-21 19:59:37', '2019-06-21 20:05:04');
INSERT INTO `mp_card` VALUES ('3', '654987333', '1', '1', '洗车卡', '10', '0', '洗车机油机滤<img src=\"http://www.kulongtai.com/1.jpg\"/>我们洗车最实惠', '0', '3', '2019-07-26 20:00:24', '1', '2019-06-21 19:59:37', '2019-06-21 20:04:04');
INSERT INTO `mp_card` VALUES ('4', '654987333', '1', '1', '保养卡1', '10', '0', '更换机油机滤<img src=\"http://www.kulongtai.com/1.jpg\"/>我们洗车最实惠', '0', '3', '2019-07-26 20:00:24', '2', '2019-06-21 19:59:37', '2019-06-21 20:03:30');

-- ----------------------------
-- Table structure for mp_card_record
-- ----------------------------
DROP TABLE IF EXISTS `mp_card_record`;
CREATE TABLE `mp_card_record` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `card_id` int(11) DEFAULT NULL COMMENT '卡券id',
  `card_no` int(12) DEFAULT NULL COMMENT '卡券消费码',
  `user_id` int(11) DEFAULT NULL COMMENT '用户id',
  `before_used_frequency` int(11) DEFAULT NULL COMMENT '消费前次数',
  `used_frequency` int(11) DEFAULT NULL COMMENT '本次消费次数',
  `after_used_frequency` int(11) DEFAULT NULL COMMENT '消费后次数',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='用户卡券消费记录表';

-- ----------------------------
-- Records of mp_card_record
-- ----------------------------
INSERT INTO `mp_card_record` VALUES ('1', '1', '1234567981', '1', '20', '1', '19', '2019-06-21 20:05:19');
INSERT INTO `mp_card_record` VALUES ('2', '1', '1234567981', '1', '19', '1', '18', '2019-06-21 20:05:24');

-- ----------------------------
-- Table structure for mp_config
-- ----------------------------
DROP TABLE IF EXISTS `mp_config`;
CREATE TABLE `mp_config` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `k` varchar(255) DEFAULT NULL COMMENT 'k',
  `v` varchar(255) DEFAULT NULL COMMENT 'v',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='配置表';

-- ----------------------------
-- Records of mp_config
-- ----------------------------
INSERT INTO `mp_config` VALUES ('6', 'appid', 'wx712f0956a1ce6cf8');
INSERT INTO `mp_config` VALUES ('7', 'appsecret', '0903e881ac49adab1e6a32a6419b1990');
INSERT INTO `mp_config` VALUES ('8', 'mchid', null);
INSERT INTO `mp_config` VALUES ('9', 'paykey', null);
INSERT INTO `mp_config` VALUES ('10', 'server_phone_number', '13842823735');

-- ----------------------------
-- Table structure for mp_notice
-- ----------------------------
DROP TABLE IF EXISTS `mp_notice`;
CREATE TABLE `mp_notice` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `content` varchar(255) DEFAULT NULL COMMENT '公告内容',
  `start_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '开始时间',
  `end_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '结束时间',
  `del_flag` char(1) DEFAULT '0' COMMENT '删除标记',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='通知公告表';

-- ----------------------------
-- Records of mp_notice
-- ----------------------------
INSERT INTO `mp_notice` VALUES ('3', '阿萨德是非得失', '2019-06-12 00:00:00', '2019-07-20 23:59:59', '0');

-- ----------------------------
-- Table structure for mp_order
-- ----------------------------
DROP TABLE IF EXISTS `mp_order`;
CREATE TABLE `mp_order` (
  `order_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '订单id',
  `sku_id` int(11) DEFAULT NULL COMMENT '商品id',
  `pay_price` decimal(10,2) DEFAULT NULL COMMENT '支付金额',
  `pay_status` char(1) DEFAULT '0' COMMENT '支付状态(0:未支付;1:已支付)',
  `pay_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '支付时间',
  `user_id` int(8) DEFAULT NULL COMMENT '用户id',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`order_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='订单表';

-- ----------------------------
-- Records of mp_order
-- ----------------------------

-- ----------------------------
-- Table structure for mp_sku
-- ----------------------------
DROP TABLE IF EXISTS `mp_sku`;
CREATE TABLE `mp_sku` (
  `sku_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '卡券id',
  `sku_name` varchar(255) DEFAULT NULL COMMENT '卡券名称',
  `sku_price` decimal(10,2) DEFAULT NULL COMMENT '卡券售格',
  `frequency` int(11) DEFAULT NULL COMMENT '次数',
  `sku_content` longtext COMMENT '卡券详情',
  `sort` int(11) DEFAULT '100' COMMENT '排序(越小越靠前)',
  `is_no_sale` char(1) DEFAULT '0' COMMENT '禁售类卡券标记1禁售 0 非禁售',
  `sku_status` char(1) DEFAULT '1' COMMENT '商品状态（1上架2下架）',
  `catagory` char(1) DEFAULT NULL COMMENT '1:洗车2保养',
  `valid_month` int(11) DEFAULT NULL COMMENT '用户购买后多少个月内使用有效',
  `del_flag` char(1) DEFAULT '0' COMMENT '删除标记',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`sku_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='卡券信息表';

-- ----------------------------
-- Records of mp_sku
-- ----------------------------
INSERT INTO `mp_sku` VALUES ('1', '洗车券', '300.00', '30', '<img src=\"http://www.kulongtai.com/1.jpg\"/>我们洗车最实惠', '100', '0', '1', '1', '36', '0', '2019-06-21 19:57:16', '2019-06-21 19:57:16');
INSERT INTO `mp_sku` VALUES ('2', '小保养', '2500.00', '10', '更换机油机滤<img src=\"http://www.kulongtai.com/1.jpg\"/>我们洗车最实惠', '100', '0', '1', '2', '36', '0', '2019-06-21 19:58:05', '2019-06-21 20:00:12');

-- ----------------------------
-- Table structure for mp_user
-- ----------------------------
DROP TABLE IF EXISTS `mp_user`;
CREATE TABLE `mp_user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `mobile` varchar(20) DEFAULT NULL COMMENT '电话',
  `password` varchar(255) DEFAULT NULL COMMENT '密码',
  `car_no` varchar(20) DEFAULT NULL COMMENT '车牌号',
  `nickname` varchar(255) DEFAULT NULL COMMENT '昵称',
  `avatar` varchar(255) DEFAULT NULL COMMENT '头像',
  `openid` varchar(64) DEFAULT NULL COMMENT 'openid',
  `session_key` varchar(100) DEFAULT NULL COMMENT 'session_key',
  `last_login_time` datetime DEFAULT NULL COMMENT '最后登录时间',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='用户表';

-- ----------------------------
-- Records of mp_user
-- ----------------------------
INSERT INTO `mp_user` VALUES ('1', '13842823735', '123', '黑A88888', 'lijinliang', null, 'ovvRs1X0NvmAYI5EWRXcEEF2i07M', '/tW9YAo5vZud6bywYODm5g==', '2019-06-21 20:19:40', '2019-06-21 00:35:05', '2019-06-21 00:35:37');
