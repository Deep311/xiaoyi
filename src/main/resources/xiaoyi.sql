/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 50540
 Source Host           : localhost:3306
 Source Schema         : xiaoyi

 Target Server Type    : MySQL
 Target Server Version : 50540
 File Encoding         : 65001

 Date: 07/01/2021 20:22:02
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for action
-- ----------------------------
DROP TABLE IF EXISTS `action`;
CREATE TABLE `action`  (
  `action_id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` bigint(20) NOT NULL COMMENT '用户id',
  `goods_want_id` bigint(20) NOT NULL COMMENT '商品/求购商品id',
  `action_sort` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '行为类别：g发布商品 w发布求购 b已购买',
  `buy_count` int(10) UNSIGNED ZEROFILL NOT NULL DEFAULT 0000000000 COMMENT 'b:已购买的数量',
  `buy_price` decimal(10, 2) UNSIGNED ZEROFILL NOT NULL DEFAULT 00000000.00 COMMENT 'b:购买时的商品价格',
  `gmt_create` datetime NOT NULL COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`action_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 16 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of action
-- ----------------------------
INSERT INTO `action` VALUES (1, 1, 1, 'g', 0000000000, 00000000.00, '2020-12-30 00:11:44', '2020-12-30 00:11:44');
INSERT INTO `action` VALUES (2, 1, 2, 'w', 0000000000, 00000000.00, '2020-12-30 00:13:44', '2020-12-30 00:13:44');
INSERT INTO `action` VALUES (3, 1, 3, 'g', 0000000000, 00000000.00, '2020-12-30 00:15:10', '2020-12-30 00:15:10');
INSERT INTO `action` VALUES (4, 2, 4, 'g', 0000000000, 00000000.00, '2020-12-30 00:19:50', '2020-12-30 00:19:50');
INSERT INTO `action` VALUES (5, 2, 5, 'w', 0000000000, 00000000.00, '2020-12-30 00:27:11', '2020-12-30 00:27:11');
INSERT INTO `action` VALUES (6, 3, 6, 'g', 0000000000, 00000000.00, '2020-12-30 00:30:19', '2020-12-30 00:30:19');
INSERT INTO `action` VALUES (7, 3, 7, 'g', 0000000000, 00000000.00, '2020-12-30 00:33:48', '2020-12-30 00:33:48');
INSERT INTO `action` VALUES (8, 4, 8, 'g', 0000000000, 00000000.00, '2020-12-30 00:38:39', '2020-12-30 00:38:39');
INSERT INTO `action` VALUES (9, 4, 9, 'w', 0000000000, 00000000.00, '2020-12-30 00:41:04', '2020-12-30 00:41:04');
INSERT INTO `action` VALUES (10, 4, 10, 'g', 0000000000, 00000000.00, '2020-12-30 00:43:43', '2020-12-30 00:43:43');
INSERT INTO `action` VALUES (11, 5, 11, 'g', 0000000000, 00000000.00, '2020-12-30 00:49:35', '2020-12-30 00:49:35');
INSERT INTO `action` VALUES (12, 1, 4, 'b', 0000000001, 00000030.00, '2020-12-30 00:51:25', '2020-12-30 00:51:25');
INSERT INTO `action` VALUES (13, 1, 10, 'b', 0000000001, 00000030.00, '2020-12-31 18:39:15', '2020-12-31 18:39:15');
INSERT INTO `action` VALUES (14, 1, 12, 'g', 0000000000, 00000000.00, '2020-12-31 18:41:29', '2020-12-31 18:41:29');
INSERT INTO `action` VALUES (15, 1, 13, 'w', 0000000000, 00000000.00, '2020-12-31 18:42:23', '2020-12-31 18:42:23');

-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin`  (
  `admin_id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
  `admin_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '管理员名称',
  `admin_password` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '管理员密码',
  `admin_phone` char(11) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '手机号码',
  `gmt_create` datetime NOT NULL COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`admin_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for favorite
-- ----------------------------
DROP TABLE IF EXISTS `favorite`;
CREATE TABLE `favorite`  (
  `favorite_id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` bigint(20) NOT NULL COMMENT '用户id',
  `goods_want_id` bigint(20) NOT NULL COMMENT '商品/求购商品id',
  `gmt_create` datetime NOT NULL COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`favorite_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of favorite
-- ----------------------------
INSERT INTO `favorite` VALUES (1, 2, 1, '2020-12-30 00:20:05', '2020-12-30 00:20:05');
INSERT INTO `favorite` VALUES (2, 3, 3, '2020-12-30 00:30:27', '2020-12-30 00:30:27');
INSERT INTO `favorite` VALUES (3, 3, 6, '2020-12-30 00:34:55', '2020-12-30 00:34:55');
INSERT INTO `favorite` VALUES (4, 4, 6, '2020-12-30 00:38:57', '2020-12-30 00:38:57');
INSERT INTO `favorite` VALUES (5, 4, 7, '2020-12-30 00:39:50', '2020-12-30 00:39:50');
INSERT INTO `favorite` VALUES (6, 4, 4, '2020-12-30 00:41:44', '2020-12-30 00:41:44');

-- ----------------------------
-- Table structure for goods_want
-- ----------------------------
DROP TABLE IF EXISTS `goods_want`;
CREATE TABLE `goods_want`  (
  `goods_want_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `goods_want` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '分类：g商品 w求购',
  `goods_want_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '商品名称/求购商品名称',
  `goods_want_level` int(2) NOT NULL COMMENT '商品成色/求购商品成色',
  `goods_want_info` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '商品信息/求购商品信息',
  `goods_want_price` decimal(10, 2) NOT NULL COMMENT '商品价格/求购商品价格',
  `sort_id` bigint(20) NOT NULL COMMENT '商品类别/求购商品类别id',
  `goods_want_count` int(10) NOT NULL COMMENT '商品数量/求购商品数量',
  `goods_want_image` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '/goodsWantImg/goodsWantDefault.jpg' COMMENT '商品图片存放路径',
  `goods_sales` int(10) UNSIGNED ZEROFILL NOT NULL DEFAULT 0000000000 COMMENT '商品销量',
  `user_id` bigint(20) NOT NULL COMMENT '发表的用户id',
  `browsed_count` int(10) UNSIGNED ZEROFILL NOT NULL DEFAULT 0000000000 COMMENT '浏览量',
  `collected_count` int(10) UNSIGNED ZEROFILL NOT NULL DEFAULT 0000000000 COMMENT '收藏量',
  `is_deleted` tinyint(1) UNSIGNED ZEROFILL NOT NULL DEFAULT 0 COMMENT '逻辑删除：1删除 0未删除',
  `gmt_create` datetime NOT NULL COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`goods_want_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 14 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of goods_want
-- ----------------------------
INSERT INTO `goods_want` VALUES (1, 'g', '小米手机', 9, '新买的小米手机，质量不错！', 1400.00, 1, 1, '/goodsWantImg/1.jpeg', 0000000000, 1, 0000000004, 0000000000, 0, '2020-12-30 00:11:44', '2020-12-30 00:31:36');
INSERT INTO `goods_want` VALUES (2, 'w', '蓝牙耳机', 8, '想要一个蓝牙耳机，谁有八成新的蓝牙耳机。', 50.00, 1, 1, '/goodsWantImg/goodsWantDefault.jpg', 0000000000, 1, 0000000004, 0000000000, 0, '2020-12-30 00:13:44', '2020-12-30 00:32:55');
INSERT INTO `goods_want` VALUES (3, 'g', '老人与海', 8, '老人与海，一本很好的书。', 23.00, 5, 1, '/goodsWantImg/3.jpeg', 0000000000, 1, 0000000005, 0000000000, 0, '2020-12-30 00:15:10', '2020-12-30 00:50:19');
INSERT INTO `goods_want` VALUES (4, 'g', 'C++Primer Plus', 8, 'C++学习资料，想学习C++编程的不要错过。', 30.00, 5, 1, '/goodsWantImg/4.jpeg', 0000000001, 2, 0000000006, 0000000000, 0, '2020-12-30 00:19:50', '2020-12-30 00:51:25');
INSERT INTO `goods_want` VALUES (5, 'w', '篮球', 8, '想要个篮球，希望磨损不要太严重。', 30.00, 6, 1, '/goodsWantImg/goodsWantDefault.jpg', 0000000000, 2, 0000000001, 0000000000, 0, '2020-12-30 00:27:11', '2020-12-30 00:27:12');
INSERT INTO `goods_want` VALUES (6, 'g', '小电动车', 8, '陪我走过了一段校园时光，质量不错。', 700.00, 4, 1, '/goodsWantImg/6.jpeg', 0000000000, 3, 0000000005, 0000000000, 0, '2020-12-30 00:30:19', '2020-12-31 18:39:58');
INSERT INTO `goods_want` VALUES (7, 'g', '蓝牙耳机', 8, '刚才看有人想要，价格好商量。', 50.00, 1, 1, '/goodsWantImg/7.jpeg', 0000000000, 3, 0000000003, 0000000000, 0, '2020-12-30 00:33:48', '2020-12-30 00:39:40');
INSERT INTO `goods_want` VALUES (8, 'g', '一对哑铃', 8, '适合刚开始锻炼的新人用。', 40.00, 6, 1, '/goodsWantImg/8.jpeg', 0000000000, 4, 0000000003, 0000000000, 0, '2020-12-30 00:38:39', '2020-12-30 00:56:13');
INSERT INTO `goods_want` VALUES (9, 'w', '书包', 9, '想要一个书包=-=', 30.00, 6, 1, '/goodsWantImg/goodsWantDefault.jpg', 0000000000, 4, 0000000001, 0000000000, 0, '2020-12-30 00:41:04', '2020-12-30 00:41:04');
INSERT INTO `goods_want` VALUES (10, 'g', '鼠标', 8, '出售鼠标，有意者速来。', 30.00, 1, 3, '/goodsWantImg/10.jpeg', 0000000001, 4, 0000000003, 0000000000, 0, '2020-12-30 00:43:42', '2020-12-31 18:39:15');
INSERT INTO `goods_want` VALUES (11, 'g', '书包', 8, '出一个书包。', 40.00, 6, 1, '/goodsWantImg/11.jpeg', 0000000000, 5, 0000000004, 0000000000, 0, '2020-12-30 00:49:35', '2020-12-31 19:07:39');
INSERT INTO `goods_want` VALUES (12, 'g', '123123', 10, 'sdfsdfsdf', 1000.00, 1, 1, '/goodsWantImg/12.png', 0000000000, 1, 0000000002, 0000000000, 0, '2020-12-31 18:41:29', '2020-12-31 18:41:48');
INSERT INTO `goods_want` VALUES (13, 'w', '11111', 10, 'cvbcvbgfhn', 400.00, 1, 3, '/goodsWantImg/goodsWantDefault.jpg', 0000000000, 1, 0000000001, 0000000000, 0, '2020-12-31 18:42:23', '2020-12-31 18:42:23');

-- ----------------------------
-- Table structure for message
-- ----------------------------
DROP TABLE IF EXISTS `message`;
CREATE TABLE `message`  (
  `message_id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
  `goods_want_id` bigint(20) NOT NULL COMMENT '商品/求购商品id',
  `content` varchar(122) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '留言内容',
  `message_floor` int(3) NOT NULL COMMENT '留言楼层',
  `user_id` bigint(20) NOT NULL COMMENT '评论的用户id',
  `gmt_create` datetime NOT NULL COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`message_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of message
-- ----------------------------
INSERT INTO `message` VALUES (1, 1, '等一个有缘人。', 1, 1, '2020-12-30 00:12:19', '2020-12-30 00:12:19');
INSERT INTO `message` VALUES (2, 3, '我有这本书，很不错的。', 1, 3, '2020-12-30 00:31:05', '2020-12-30 00:31:05');
INSERT INTO `message` VALUES (3, 1, '想要，希望价格可以降一点。', 2, 3, '2020-12-30 00:31:36', '2020-12-30 00:31:36');
INSERT INTO `message` VALUES (4, 2, '我有个，我马上发布一个。', 1, 3, '2020-12-30 00:32:55', '2020-12-30 00:32:55');
INSERT INTO `message` VALUES (5, 7, '考虑一下=。=', 1, 4, '2020-12-30 00:39:40', '2020-12-30 00:39:40');
INSERT INTO `message` VALUES (6, 4, '想要，最近打算学C++了。', 1, 4, '2020-12-30 00:42:17', '2020-12-30 00:42:17');

-- ----------------------------
-- Table structure for shoppingcart
-- ----------------------------
DROP TABLE IF EXISTS `shoppingcart`;
CREATE TABLE `shoppingcart`  (
  `shop_id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` bigint(20) NOT NULL COMMENT '用户id',
  `goods_want_id` bigint(20) NOT NULL COMMENT '商品/求购商品id',
  `need_count` int(10) UNSIGNED ZEROFILL NOT NULL COMMENT '需求商品数量',
  `gmt_create` datetime NOT NULL COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`shop_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of shoppingcart
-- ----------------------------
INSERT INTO `shoppingcart` VALUES (1, 3, 4, 0000000001, '2020-12-30 00:30:30', '2020-12-30 00:30:30');
INSERT INTO `shoppingcart` VALUES (2, 4, 1, 0000000001, '2020-12-30 00:38:59', '2020-12-30 00:38:59');
INSERT INTO `shoppingcart` VALUES (3, 4, 7, 0000000001, '2020-12-30 00:39:55', '2020-12-30 00:39:55');
INSERT INTO `shoppingcart` VALUES (4, 4, 3, 0000000001, '2020-12-30 00:41:34', '2020-12-30 00:41:34');
INSERT INTO `shoppingcart` VALUES (7, 1, 10, 0000000001, '2020-12-31 18:38:16', '2020-12-31 18:38:16');

-- ----------------------------
-- Table structure for sort
-- ----------------------------
DROP TABLE IF EXISTS `sort`;
CREATE TABLE `sort`  (
  `sort_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `goods_want_sort` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '商品类别/求购商品类别',
  `gmt_create` datetime NOT NULL COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`sort_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sort
-- ----------------------------
INSERT INTO `sort` VALUES (1, '电子产品', '2020-12-30 00:22:27', '2020-12-30 00:22:27');
INSERT INTO `sort` VALUES (2, '男装服饰', '2020-12-30 00:22:52', '2020-12-30 00:22:52');
INSERT INTO `sort` VALUES (3, '女装服饰', '2020-12-30 00:23:23', '2020-12-30 00:23:23');
INSERT INTO `sort` VALUES (4, '家电产品', '2020-12-30 00:23:56', '2020-12-30 00:23:56');
INSERT INTO `sort` VALUES (5, '图书音像', '2020-12-30 00:24:13', '2020-12-30 00:24:13');
INSERT INTO `sort` VALUES (7, '其他产品', '2020-12-30 00:24:45', '2020-12-30 00:24:45');
INSERT INTO `sort` VALUES (8, '美妆彩妆', '2020-12-30 00:25:08', '2020-12-30 00:25:08');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `user_id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
  `username` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户名',
  `password` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户密码',
  `phonenum` char(11) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '手机号码',
  `wallet_balance` decimal(10, 2) UNSIGNED ZEROFILL NOT NULL DEFAULT 00000000.00 COMMENT '钱包余额',
  `payment_password` char(6) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '用户支付密码',
  `real_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '用户真实姓名',
  `clazz` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '用户班级',
  `son` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '用户学号',
  `address` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '用户地址',
  `gender` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT 'n' COMMENT '性别：m男 f女 n未知',
  `user_img` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '/userImg/userDefault.jpg' COMMENT '用户头像图片存放路径',
  `gmt_create` datetime NOT NULL COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, '小明呀', 'xiaoming', '12345678901', 00009940.00, '111111', '明小明123123', '计算机172', '3170701201', '12#307', 'm', '/userImg/1.png', '2020-12-30 00:08:08', '2020-12-31 18:40:48');
INSERT INTO `user` VALUES (2, '网小易', '222222', '23456789012', 00020061.00, '222222', '', '', '', '', 'm', '/userImg/2.jpeg', '2020-12-30 00:16:25', '2020-12-30 00:51:25');
INSERT INTO `user` VALUES (3, '小红呀', '333333', '34567890123', 00000234.00, '', '', '', '', '', 'f', '/userImg/3.jpeg', '2020-12-30 00:28:07', '2020-12-30 00:28:43');
INSERT INTO `user` VALUES (4, '我是小军', '444444', '45678901234', 00000060.20, '', '王大锤', '', '', '', 'm', '/userImg/4.jpeg', '2020-12-30 00:35:34', '2020-12-31 18:39:15');
INSERT INTO `user` VALUES (5, '高小龙', '666666', '56789012345', 00000503.80, '123456', '高双龙', '计算机171', '3170701136', '12#205', 'm', '/userImg/5.jpeg', '2020-12-30 00:45:20', '2020-12-30 00:47:28');

SET FOREIGN_KEY_CHECKS = 1;
