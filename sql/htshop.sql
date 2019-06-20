/*
 Navicat Premium Data Transfer

 Source Server         : localhost1
 Source Server Type    : MySQL
 Source Server Version : 50643
 Source Host           : localhost:3306
 Source Schema         : htshop

 Target Server Type    : MySQL
 Target Server Version : 50643
 File Encoding         : 65001

 Date: 20/06/2019 11:23:51
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for mp_about
-- ----------------------------
DROP TABLE IF EXISTS `mp_about`;
CREATE TABLE `mp_about`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `content` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '关于我们',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '关于我们' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for mp_card
-- ----------------------------
DROP TABLE IF EXISTS `mp_card`;
CREATE TABLE `mp_card`  (
  `card_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '我的卡券id',
  `card_no` int(12) NULL DEFAULT NULL COMMENT '卡券消费码',
  `user_id` int(11) NULL DEFAULT NULL COMMENT '用户id',
  `sku_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '商品id',
  `card_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '卡券名称',
  `total_frequency` int(11) NULL DEFAULT NULL COMMENT '总次数',
  `rest_frequency` int(11) NULL DEFAULT NULL COMMENT '剩余次数',
  `card_content` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '卡片详情',
  `valid_flag` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '1' COMMENT '有效标记（1有效0无效）',
  `order_id` int(11) NULL DEFAULT NULL COMMENT '订单id',
  `valide_time` datetime(0) NULL DEFAULT NULL COMMENT '有效期至',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  PRIMARY KEY (`card_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户卡券表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for mp_card_record
-- ----------------------------
DROP TABLE IF EXISTS `mp_card_record`;
CREATE TABLE `mp_card_record`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `card_id` int(11) NULL DEFAULT NULL COMMENT '卡券id',
  `card_no` int(12) NULL DEFAULT NULL COMMENT '卡券消费码',
  `user_id` int(11) NULL DEFAULT NULL COMMENT '用户id',
  `before_used_frequency` int(11) NULL DEFAULT NULL COMMENT '消费前次数',
  `used_frequency` int(11) NULL DEFAULT NULL COMMENT '本次消费次数',
  `after_used_frequency` int(11) NULL DEFAULT NULL COMMENT '消费后次数',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户卡券消费记录表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for mp_config
-- ----------------------------
DROP TABLE IF EXISTS `mp_config`;
CREATE TABLE `mp_config`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `k` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'k',
  `v` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'v',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '配置表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for mp_notice
-- ----------------------------
DROP TABLE IF EXISTS `mp_notice`;
CREATE TABLE `mp_notice`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `content` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '公告内容',
  `start_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP COMMENT '开始时间',
  `end_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP COMMENT '结束时间',
  `del_flag` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '删除标记',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '通知公告表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of mp_notice
-- ----------------------------
INSERT INTO `mp_notice` VALUES (3, '阿萨德是非得失', '2019-06-12 00:00:00', '2019-07-20 23:59:59', '0');

-- ----------------------------
-- Table structure for mp_order
-- ----------------------------
DROP TABLE IF EXISTS `mp_order`;
CREATE TABLE `mp_order`  (
  `order_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '订单id',
  `sku_id` int(11) NULL DEFAULT NULL COMMENT '商品id',
  `pay_price` decimal(10, 2) NULL DEFAULT NULL COMMENT '支付金额',
  `pay_status` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '支付状态(0:未支付;1:已支付)',
  `pay_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP COMMENT '支付时间',
  `user_id` int(8) NULL DEFAULT NULL COMMENT '用户id',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  PRIMARY KEY (`order_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '订单表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for mp_sku
-- ----------------------------
DROP TABLE IF EXISTS `mp_sku`;
CREATE TABLE `mp_sku`  (
  `sku_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '卡券id',
  `sku_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '卡券名称',
  `sku_price` decimal(10, 2) NULL DEFAULT NULL COMMENT '卡券售格',
  `frequency` int(11) NULL DEFAULT NULL COMMENT '次数',
  `sku_content` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '卡券详情',
  `sort` int(11) NULL DEFAULT 100 COMMENT '排序(越小越靠前)',
  `is_no_sale` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '禁售类卡券标记1禁售 0 非禁售',
  `sku_status` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '1' COMMENT '商品状态（1上架2下架）',
  `catagory` int(11) NULL DEFAULT NULL COMMENT '1:洗车2保养',
  `valid_month` int(11) NULL DEFAULT NULL COMMENT '用户购买后多少个月内使用有效',
  `del_flag` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '删除标记',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  PRIMARY KEY (`sku_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '卡券信息表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for mp_user
-- ----------------------------
DROP TABLE IF EXISTS `mp_user`;
CREATE TABLE `mp_user`  (
  `user_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `mobile` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '电话',
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '密码',
  `car_no` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '车牌号',
  `nickname` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '昵称',
  `avatar` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '头像',
  `openid` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'openid',
  `session_key` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'session_key',
  `last_login_time` datetime(0) NULL DEFAULT NULL COMMENT '最后登录时间',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户表' ROW_FORMAT = Compact;

SET FOREIGN_KEY_CHECKS = 1;
