/*
 Navicat Premium Dump SQL

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 50716 (5.7.16)
 Source Host           : localhost:3306
 Source Schema         : springbootzkgw

 Target Server Type    : MySQL
 Target Server Version : 50716 (5.7.16)
 File Encoding         : 65001

 Date: 16/01/2026 18:03:58
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for address
-- ----------------------------
DROP TABLE IF EXISTS `address`;
CREATE TABLE `address`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `userid` bigint(20) NOT NULL COMMENT '用户id',
  `address` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '地址',
  `name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '收货人',
  `phone` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '电话',
  `isdefault` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '是否默认地址[是/否]',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1768291369000 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '地址' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of address
-- ----------------------------
INSERT INTO `address` VALUES (7, '2026-01-13 09:15:23', 1, '北京市朝阳区建国门外大街1号国贸三期A座', '张三', '13800138000', '是');
INSERT INTO `address` VALUES (8, '2026-01-13 11:30:45', 2, '上海市浦东新区陆家嘴环路1000号恒生银行大厦', '李四', '13912345678', '是');
INSERT INTO `address` VALUES (9, '2026-01-13 14:22:18', 3, '广东省深圳市南山区深南大道10000号腾讯大厦', '王五', '15098765432', '否');
INSERT INTO `address` VALUES (10, '2026-01-13 16:05:37', 1, '北京市海淀区中关村软件园二期网易大厦', '张三', '13800138000', '否');
INSERT INTO `address` VALUES (11, '2026-01-13 20:48:09', 4, '浙江省杭州市余杭区文一西路969号阿里巴巴西溪园区', '赵六', '13755556666', '是');
INSERT INTO `address` VALUES (1768291368999, '2026-01-13 16:02:48', 1768272751054, '1', '1', '11111111111', '否');

-- ----------------------------
-- Table structure for cart
-- ----------------------------
DROP TABLE IF EXISTS `cart`;
CREATE TABLE `cart`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `tablename` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT 'ziliaoxinxi' COMMENT '商品表名',
  `userid` bigint(20) NOT NULL COMMENT '用户id',
  `goodid` bigint(20) NOT NULL COMMENT '商品id',
  `goodname` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '商品名称',
  `picture` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '图片',
  `buynumber` int(11) NOT NULL COMMENT '购买数量',
  `price` float NULL DEFAULT NULL COMMENT '单价',
  `discountprice` float NULL DEFAULT NULL COMMENT '会员价',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1625197454869 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '购物车表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of cart
-- ----------------------------
INSERT INTO `cart` VALUES (1625197454864, '2026-01-13 09:15:23', 'ziliaoxinxi', 1, 1, 'Apple iPhone 16 Pro Max 512GB', 'http://localhost:8080/springbootzkgw/upload/商品_6.png', 1, 11999, 11499);
INSERT INTO `cart` VALUES (1625197454865, '2026-01-13 14:30:45', 'ziliaoxinxi', 2, 2, '华为 Mate 70 Pro+ 1TB', 'http://localhost:8080/springbootzkgw/upload/商品_65.png', 2, 8999, 8699);
INSERT INTO `cart` VALUES (1625197454866, '2026-01-13 11:05:18', 'ziliaoxinxi', 3, 3, '小米 15 Ultra 陶瓷版', 'http://localhost:8080/springbootzkgw/upload/商品_53.png', 1, 6499, 6299);
INSERT INTO `cart` VALUES (1625197454867, '2026-01-13 16:48:32', 'ziliaoxinxi', 4, 4, '三星 Galaxy S26 Ultra', 'http://localhost:8080/springbootzkgw/upload/商品_15.png', 1, 10999, 10499);
INSERT INTO `cart` VALUES (1625197454868, '2026-01-13 20:22:57', 'ziliaoxinxi', 5, 5, 'OPPO Find X8 Pro 艺术家限定版', 'http://localhost:8080/springbootzkgw/upload/商品_62.png', 3, 5999, 5799);

-- ----------------------------
-- Table structure for config
-- ----------------------------
DROP TABLE IF EXISTS `config`;
CREATE TABLE `config`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '轮播图名称',
  `value` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '轮播图地址',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '轮播图' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of config
-- ----------------------------
INSERT INTO `config` VALUES (7, 'genImg/1768270304153.jpg', 'http://10.40.92.160:8080/springbootzkgw/upload/1768285495223.jpg');
INSERT INTO `config` VALUES (8, 'genImg/1768270307960.jpg', 'http://10.40.92.160:8080/springbootzkgw/upload/1768285267486.jpg');
INSERT INTO `config` VALUES (9, '首页主推活动', 'http://10.40.92.160:8080/springbootzkgw/upload/1768285272644.jpg');
INSERT INTO `config` VALUES (10, '新品上市', 'http://10.40.92.160:8080/springbootzkgw/upload/1768285277334.jpg');

-- ----------------------------
-- Table structure for consultation
-- ----------------------------
DROP TABLE IF EXISTS `consultation`;
CREATE TABLE `consultation`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `sender` bigint(20) NOT NULL COMMENT '发送者id',
  `sender_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '发送者昵称',
  `receiver` bigint(20) NOT NULL COMMENT '接收者id',
  `receiver_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '接收者昵称',
  `send_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '发送时间',
  `msg` varchar(4000) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '消息内容',
  `read_status` int(11) NOT NULL COMMENT '读取状态',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1637562662549 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '问诊表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of consultation
-- ----------------------------
INSERT INTO `consultation` VALUES (1637562662544, 1, '张伟', 2, '李娜', '2026-01-13 09:15:23', '李医生您好，我最近三天一直咳嗽，有少量黄痰，没有发烧，需要吃什么药吗？', 1);
INSERT INTO `consultation` VALUES (1637562662545, 2, '李娜', 1, '张伟', '2026-01-13 09:18:05', '张先生，建议先多喝温水，避免辛辣刺激食物。如果方便，可以拍一下喉咙的照片发给我看看。', 1);
INSERT INTO `consultation` VALUES (1637562662546, 1, '张伟', 2, '李娜', '2026-01-13 14:30:47', '李医生，照片发您了。另外今天下午感觉有点乏力，需要去医院检查吗？', 1);
INSERT INTO `consultation` VALUES (1637562662547, 3, '王芳', 2, '李娜', '2026-01-13 16:05:12', '李医生，我孩子5岁，昨晚开始发烧，38.5度，精神状态还可以，应该怎么处理？', 0);
INSERT INTO `consultation` VALUES (1637562662548, 4, '刘洋', 5, '赵敏', '2026-01-13 20:45:38', '赵医生，我胃疼的老毛病又犯了，主要是饭后胀痛，跟以前一样吃奥美拉唑可以吗？', 1);

-- ----------------------------
-- Table structure for crk
-- ----------------------------
DROP TABLE IF EXISTS `crk`;
CREATE TABLE `crk`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `refid` bigint(20) NOT NULL COMMENT '关联表id',
  `name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '出入库物品名称',
  `sl` bigint(20) NOT NULL COMMENT '出入库数量',
  `crkzt` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '出入库状态',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1768298048510 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '出入库表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of crk
-- ----------------------------
INSERT INTO `crk` VALUES (1637562662544, '2026-01-13 09:15:23', 1, '联想笔记本电脑', 50, '入库');
INSERT INTO `crk` VALUES (1637562662545, '2026-01-13 14:08:47', 2, '华为MatePad Pro', 30, '出库');
INSERT INTO `crk` VALUES (1637562662546, '2026-01-13 11:32:15', 3, '苹果iPhone 15', 100, '入库');
INSERT INTO `crk` VALUES (1637562662547, '2026-01-13 16:45:09', 4, '戴尔显示器', 25, '出库');
INSERT INTO `crk` VALUES (1637562662548, '2026-01-13 10:20:38', 5, '罗技无线鼠标', 200, '入库');
INSERT INTO `crk` VALUES (1768298044832, '2026-01-13 17:54:03', 1, '橡皮糖', 15, '出库');
INSERT INTO `crk` VALUES (1768298048509, '2026-01-13 17:54:07', 1, '橡皮糖', 20, '入库');

-- ----------------------------
-- Table structure for dianpuxinxi
-- ----------------------------
DROP TABLE IF EXISTS `dianpuxinxi`;
CREATE TABLE `dianpuxinxi`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `dianpumingcheng` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '店铺名称',
  `tupian` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '图片',
  `nicheng` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '昵称',
  `shangjiadianhua` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '商家电话',
  `dianpujianjie` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL COMMENT '店铺简介',
  `dianpudizhi` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '店铺地址',
  `userid` bigint(20) NULL DEFAULT NULL COMMENT '用户id',
  `lat` double(20, 6) NULL DEFAULT -1.000000 COMMENT 'lat',
  `lnt` double(20, 6) NULL DEFAULT -1.000000 COMMENT 'lnt',
  `conent` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '否' COMMENT '标记',
  `sfsh` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '否' COMMENT '是否审核',
  `shhf` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL COMMENT '审核回复',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1768285684025 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '店铺信息' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of dianpuxinxi
-- ----------------------------
INSERT INTO `dianpuxinxi` VALUES (1768273151200, '2026-01-13 10:59:10', '店铺1', 'http://10.40.92.160:8080/springbootzkgw/upload/1768273134278.jpg', '1', '1', '1', '1', 1768273103739, 34.819609, 113.478993, '1', '是', '11');
INSERT INTO `dianpuxinxi` VALUES (1768285637901, '2026-01-13 14:27:17', '店铺2', 'http://10.40.92.160:8080/springbootzkgw/upload/1768285621204.jpg', '2', '2', '2', '2', 1768273103739, 39.909159, 116.397433, '12', '是', NULL);
INSERT INTO `dianpuxinxi` VALUES (1768285657728, '2026-01-13 14:27:36', '店铺3', 'http://10.40.92.160:8080/springbootzkgw/upload/1768285645630.jpg', '3', '3', '3', '3', 1768273103739, 34.818829, 113.480981, '23', '是', NULL);
INSERT INTO `dianpuxinxi` VALUES (1768285684024, '2026-01-13 14:28:03', '店铺4', 'http://10.40.92.160:8080/springbootzkgw/upload/1768285677932.jpg', '4', '4', '4', '4', 1768273103739, 39.909232, 116.395528, '455', '是', NULL);

-- ----------------------------
-- Table structure for discussdianpuxinxi
-- ----------------------------
DROP TABLE IF EXISTS `discussdianpuxinxi`;
CREATE TABLE `discussdianpuxinxi`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `refid` bigint(20) NOT NULL COMMENT '关联表id',
  `userid` bigint(20) NOT NULL COMMENT '用户id',
  `nickname` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '用户名',
  `content` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL COMMENT '评论内容',
  `reply` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL COMMENT '回复内容',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1768298184752 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '店铺信息评论表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of discussdianpuxinxi
-- ----------------------------
INSERT INTO `discussdianpuxinxi` VALUES (1, '2026-01-13 09:15:23', 1, 1, '美食探索者小王', '这家店的招牌菜味道非常正宗，食材新鲜，分量也足，强烈推荐给大家！', '感谢您的认可！我们会继续坚持品质，期待您的再次光临。');
INSERT INTO `discussdianpuxinxi` VALUES (2, '2026-01-13 11:42:05', 2, 2, '周末探店达人', '环境优雅，服务周到，上菜速度很快。就是停车位稍微有点紧张。', '您好，感谢您的反馈。关于停车问题，我们已与隔壁商场协商，消费可享两小时免费停车，详情可咨询前台。');
INSERT INTO `discussdianpuxinxi` VALUES (3, '2026-01-13 14:08:37', 3, 3, '老顾客张先生', '已经是第五次来了，品质一直很稳定。希望以后能多推出一些季节限定菜品。', '张先生您好，非常感谢您长期以来的支持！您的建议已收到，我们的研发团队正在筹备春季新菜单，敬请期待。');
INSERT INTO `discussdianpuxinxi` VALUES (4, '2026-01-13 16:55:18', 4, 4, '吃货小刘', '和朋友聚餐选的这里，大家都说不错。人均消费大概80元左右，性价比很高。', '很高兴能为你们的聚会增添美味！我们致力于提供物超所值的用餐体验，欢迎下次再来。');
INSERT INTO `discussdianpuxinxi` VALUES (5, '2026-01-13 20:30:49', 5, 5, '安静的美食家', '菜品摆盘精致，适合拍照。味道偏清淡，适合我的口味。希望WiFi信号能再强一些。', '感谢您的评价。关于WiFi信号问题，我们已联系网络服务商，本周内会进行增强处理，给您带来不便深感抱歉。');
INSERT INTO `discussdianpuxinxi` VALUES (1768288361226, '2026-01-13 15:12:41', 1768273151200, 1768272751054, '1', '22', NULL);
INSERT INTO `discussdianpuxinxi` VALUES (1768298184751, '2026-01-13 17:56:23', 1768273151200, 1768272751054, '1', '111', NULL);

-- ----------------------------
-- Table structure for discussshangpinguanli
-- ----------------------------
DROP TABLE IF EXISTS `discussshangpinguanli`;
CREATE TABLE `discussshangpinguanli`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `refid` bigint(20) NOT NULL COMMENT '关联表id',
  `userid` bigint(20) NOT NULL COMMENT '用户id',
  `nickname` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '用户名',
  `content` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL COMMENT '评论内容',
  `reply` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL COMMENT '回复内容',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '商品管理评论表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of discussshangpinguanli
-- ----------------------------
INSERT INTO `discussshangpinguanli` VALUES (1, '2026-01-13 09:15:23', 1, 1, '数码爱好者小王', '这款手机的续航能力真的超乎想象，重度使用一天下来还有30%的电量，非常满意！', '感谢您的认可！我们采用了新一代的节能芯片和智能功耗管理系统，就是为了给用户带来更持久的续航体验。');
INSERT INTO `discussshangpinguanli` VALUES (2, '2026-01-13 11:42:05', 2, 2, '美食家老李', '咖啡豆烘焙程度刚刚好，酸苦平衡，油脂丰富，在家也能做出媲美咖啡馆的意式浓缩。', '很高兴您喜欢！我们的咖啡豆由资深烘焙师精心把控，确保每一批都保持稳定且高品质的风味。');
INSERT INTO `discussshangpinguanli` VALUES (3, '2026-01-13 14:28:37', 3, 3, '运动达人小美', '跑鞋的缓震和回弹效果太棒了，跑了十公里膝盖和脚踝没有任何不适，包裹性也很好。', '谢谢您的反馈！这款跑鞋的中底科技正是为了长距离跑步提供持续保护和推进力而设计的。');
INSERT INTO `discussshangpinguanli` VALUES (4, '2026-01-13 16:55:18', 4, 4, '居家宝妈乐乐', '儿童书桌设计很人性化，高度和角度都能调节，还有专门的收纳区域，孩子写作业更专注了。', '能帮助孩子养成良好的学习习惯我们也很开心！书桌的设计初衷就是兼顾健康、功能和整理。');
INSERT INTO `discussshangpinguanli` VALUES (5, '2026-01-13 20:10:49', 5, 5, '户外探险者峰哥', '冲锋衣的防水和透气性能经受住了暴雨的考验，内部依然干爽，是徒步旅行的可靠装备。', '您的实地测试是对我们产品最好的肯定！这款面料采用了高性能的防水透气膜，应对恶劣天气游刃有余。');

-- ----------------------------
-- Table structure for forum
-- ----------------------------
DROP TABLE IF EXISTS `forum`;
CREATE TABLE `forum`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `title` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '帖子标题',
  `content` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '帖子内容',
  `parentid` bigint(20) NULL DEFAULT 0 COMMENT '父节点id',
  `userid` bigint(20) NOT NULL COMMENT '用户id',
  `username` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '用户名',
  `isdone` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '状态',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1768284642720 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '论坛表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of forum
-- ----------------------------
INSERT INTO `forum` VALUES (1609987952877, '2026-01-13 09:15:22', '关于Java多线程编程的最佳实践讨论', '大家好，最近在项目中使用多线程处理数据时遇到了一些性能瓶颈和线程安全问题，想和大家探讨一下在实际开发中，有哪些经过验证的最佳实践可以分享？比如线程池的配置、锁的选择、并发工具类的使用等。', 0, 1, '程序员小王', '开放讨论');
INSERT INTO `forum` VALUES (1609987952878, '2026-01-13 14:30:45', '回复：关于Java多线程编程的最佳实践讨论', '@程序员小王 推荐优先使用`java.util.concurrent`包下的工具类，比如`ThreadPoolExecutor`创建线程池比`Executors`工厂方法更灵活可控。对于共享数据，根据场景选择`synchronized`、`ReentrantLock`或`StampedLock`。', 1, 2, '架构师老李', '已回复');
INSERT INTO `forum` VALUES (1609987952879, '2026-01-13 11:05:18', 'Spring Boot 3.0新特性体验分享', '刚刚将一个小项目升级到了Spring Boot 3.0，感觉GraalVM原生镜像支持非常棒，启动速度提升明显。另外，新的Problem Details API对错误处理也更规范了。大家升级过程中有遇到什么坑吗？', 0, 3, '技术爱好者小白', '开放讨论');
INSERT INTO `forum` VALUES (1609987952880, '2026-01-13 16:48:03', '数据库索引优化求助', '有一张用户订单表，查询经常用到`user_id`和`create_time`字段，目前分别建立了单列索引。请问是维持现状，还是建立一个`(user_id, create_time)`的联合索引效果更好？数据量大约在500万左右。', 0, 4, 'DBA小张', '等待解答');
INSERT INTO `forum` VALUES (1609987952881, '2026-01-13 20:12:37', '回复：数据库索引优化求助', '@DBA小张 根据你的查询场景，如果经常同时按`user_id`筛选并按`create_time`排序，或者同时查询这两个字段，那么`(user_id, create_time)`的联合索引通常比两个单列索引更高效，可以避免回表。建议在测试环境对比一下。', 4, 5, '资深开发老王', '已回复');
INSERT INTO `forum` VALUES (1768284642719, '2026-01-13 14:10:41', '22', '222', 0, 1768272751054, '1', '开放');

-- ----------------------------
-- Table structure for messagess
-- ----------------------------
DROP TABLE IF EXISTS `messagess`;
CREATE TABLE `messagess`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `userid` bigint(20) NOT NULL COMMENT '留言人id',
  `username` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '用户名',
  `content` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '留言内容',
  `cpicture` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '留言图片',
  `reply` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL COMMENT '回复内容',
  `rpicture` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '回复图片',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1629368316366 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '在线留言' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of messagess
-- ----------------------------
INSERT INTO `messagess` VALUES (1629368316361, '2026-01-13 09:15:23', 1, '张伟', '这款智能手表的续航能力真的超乎想象，充满电后用了整整一周还有余电，非常满意！', 'http://localhost:8080/springbootzkgw/upload/留言_4.png', '感谢您的认可！我们采用了最新的低功耗芯片和优化算法，就是为了给用户带来持久的使用体验。', NULL);
INSERT INTO `messagess` VALUES (1629368316362, '2026-01-13 14:08:47', 2, '李娜', '昨天刚收到新买的蓝牙耳机，音质非常纯净，降噪效果也很出色，在地铁上听歌完全不受干扰。', 'http://localhost:8080/springbootzkgw/upload/留言_2.png', '很高兴您喜欢我们的产品！优质的音频单元和主动降噪技术是我们产品的核心卖点，祝您使用愉快！', NULL);
INSERT INTO `messagess` VALUES (1629368316363, '2026-01-13 11:32:05', 3, '王明', '咨询一下，你们最新款的笔记本电脑支持内存扩展吗？我想买来用于视频剪辑。', 'http://localhost:8080/springbootzkgw/upload/留言_13.png', '您好，该型号提供两个SO-DIMM插槽，最高支持扩展至64GB DDR5内存，完全能满足专业视频剪辑的需求。', NULL);
INSERT INTO `messagess` VALUES (1629368316364, '2026-01-13 16:45:19', 4, '赵静', '订单号20260113001的快递显示已签收，但我并没有收到货，麻烦帮忙查一下物流情况，谢谢！', 'http://localhost:8080/springbootzkgw/upload/留言_9.png', '非常抱歉给您带来不便。我们已经联系快递公司核实，初步反馈可能是派送员操作失误。我们会立即跟进，并在1小时内给您回电说明情况。', NULL);
INSERT INTO `messagess` VALUES (1629368316365, '2026-01-13 20:20:38', 5, '刘洋', '你们家的扫地机器人规划路线很智能，扫得也很干净。就是尘盒再大一点就更完美了。', 'http://localhost:8080/springbootzkgw/upload/留言_17.png', '感谢您的宝贵建议！我们已经将大容量尘盒的需求反馈给产品部门，会在下一代产品中重点考虑优化。', NULL);

-- ----------------------------
-- Table structure for news
-- ----------------------------
DROP TABLE IF EXISTS `news`;
CREATE TABLE `news`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `title` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '标题',
  `introduction` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL COMMENT '简介',
  `picture` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '系统公告图片',
  `content` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '内容',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '系统公告' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of news
-- ----------------------------
INSERT INTO `news` VALUES (1, '2026-01-13 09:15:23', '关于2026年春节假期安排的通知', '根据国家法定节假日安排，结合公司实际情况，现将2026年春节假期安排通知如下。', 'http://localhost:8080/springbootzkgw/upload/公告_15.png', '全体员工：\n经公司研究决定，2026年春节假期安排为：2月15日（除夕）至2月21日（正月初六）放假调休，共7天。2月14日（星期六）、2月22日（星期日）正常上班。请各部门妥善安排好工作，做好假期值班和安全保卫工作。预祝大家新春愉快！');
INSERT INTO `news` VALUES (2, '2026-01-13 14:30:45', '系统维护升级公告', '为提升服务质量，我司计划于近期对核心业务系统进行例行维护升级。', 'http://localhost:8080/springbootzkgw/upload/公告_18.png', '尊敬的客户：\n为了给您提供更稳定、高效的服务，我们将于2026年1月20日（周二）凌晨00:00至06:00进行系统维护升级。在此期间，部分线上服务可能出现短暂中断或访问缓慢，请您提前做好安排。给您带来的不便，敬请谅解。');
INSERT INTO `news` VALUES (3, '2026-01-13 11:05:17', '2025年度优秀员工表彰大会圆满举行', '公司于2026年1月10日隆重举行了2025年度优秀员工表彰大会，表彰先进，树立榜样。', 'http://localhost:8080/springbootzkgw/upload/公告_19.png', '2026年1月10日下午，公司2025年度优秀员工表彰大会在总部报告厅隆重举行。大会对在过去一年中表现突出、为公司发展做出卓越贡献的50名优秀员工及10个优秀团队进行了表彰。公司领导出席大会并为获奖者颁奖，鼓励全体员工以他们为榜样，在新的一年里再创佳绩。');
INSERT INTO `news` VALUES (4, '2026-01-13 16:48:32', '新员工入职培训计划启动', '为帮助新同事快速融入公司，人力资源部将于本月启动新一期新员工入职培训。', 'http://localhost:8080/springbootzkgw/upload/公告_6.png', '各位新同事：\n欢迎加入我们的大家庭！为使大家更好地了解公司文化、规章制度及业务流程，人力资源部定于2026年1月18日至1月22日组织开展新员工入职培训。培训内容包括公司发展史、企业文化、规章制度、安全教育及岗位技能入门等。请各位新同事准时参加。');
INSERT INTO `news` VALUES (5, '2026-01-13 10:22:08', '关于办公区节能降耗的倡议书', '为践行绿色发展理念，建设节约型公司，现向全体员工发出节能降耗倡议。', 'http://localhost:8080/springbootzkgw/upload/公告_12.png', '全体员工：\n节约资源、保护环境是我们共同的责任。为此，公司倡议大家从身边小事做起：下班后及时关闭电脑、打印机等设备电源；合理设置空调温度，夏季不低于26℃，冬季不高于20℃；节约用水，随手关紧水龙头；推行无纸化办公，减少纸张消耗。让我们携手共建绿色、低碳的办公环境。');

-- ----------------------------
-- Table structure for orders
-- ----------------------------
DROP TABLE IF EXISTS `orders`;
CREATE TABLE `orders`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `orderid` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '订单编号',
  `tablename` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT 'ziliaoxinxi' COMMENT '商品表名',
  `userid` bigint(20) NOT NULL COMMENT '用户id',
  `goodid` bigint(20) NOT NULL COMMENT '商品id',
  `goodname` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '商品名称',
  `picture` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '商品图片',
  `buynumber` int(11) NOT NULL COMMENT '购买数量',
  `price` float NOT NULL DEFAULT 0 COMMENT '价格/积分',
  `discountprice` float NULL DEFAULT 0 COMMENT '折扣价格',
  `total` float NOT NULL DEFAULT 0 COMMENT '总价格/总积分',
  `discounttotal` float NULL DEFAULT 0 COMMENT '折扣总价格',
  `type` int(11) NULL DEFAULT 1 COMMENT '支付类型',
  `status` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '状态',
  `address` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '地址',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `orderid`(`orderid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1768298148380 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '订单' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of orders
-- ----------------------------
INSERT INTO `orders` VALUES (1635842906063, '2026-01-13 11:30:45', 'DD202601130002', 'ziliaoxinxi', 2, 2, '华为 Mate 70 Pro+ 1TB', 'http://localhost:8080/springbootzkgw/upload/商品_65.png', 1, 8999, 8999, 8999, 8999, 2, '待发货', '上海市浦东新区陆家嘴环路1000号');
INSERT INTO `orders` VALUES (1635842906066, '2026-01-13 20:20:07', 'DD202601130005', 'ziliaoxinxi', 5, 5, 'OPPO Find X8 Pro', 'http://localhost:8080/springbootzkgw/upload/商品_62.png', 1, 5999, 5799, 5999, 5799, 1, '待支付', '杭州市余杭区文一西路969号阿里巴巴西溪园区');
INSERT INTO `orders` VALUES (1768291380212, '2026-01-13 16:02:59', '20261131625776437853', 'shangpinguanli', 1768272751054, 1, '橡皮糖', 'http://10.40.92.160:8080/springbootzkgw/upload/1768272869644.jpg', 1, 15, 15, 15, 15, 1, '已支付', '1');
INSERT INTO `orders` VALUES (1768291464718, '2026-01-13 16:04:24', '20261131642245678061', 'shangpinguanli', 1768272751054, 1768291231359, '5', 'http://10.40.92.160:8080/springbootzkgw/upload/1768291216216.jpg', 1, 5, 5, 5, 5, 1, '已支付', '1');
INSERT INTO `orders` VALUES (1768292812258, '2026-01-13 16:26:51', '202611316265016567675', 'shangpinguanli', 1768272751054, 4, '元气森林', 'http://10.40.92.160:8080/springbootzkgw/upload/1768274605791.jpg', 1, 5, 5, 5, 5, 1, '已支付', '1');
INSERT INTO `orders` VALUES (1768292933855, '2026-01-13 16:28:53', '202611316285153890212', 'shangpinguanli', 1768272751054, 1768291231359, '5', 'http://10.40.92.160:8080/springbootzkgw/upload/1768291216216.jpg', 1, 5, 5, 5, 5, 1, '已发货', '1');
INSERT INTO `orders` VALUES (1768294115505, '2026-01-13 16:48:34', '202611316483293600596', 'shangpinguanli', 1768272751054, 1, '橡皮糖', 'http://10.40.92.160:8080/springbootzkgw/upload/1768272869644.jpg', 1, 15, 15, 15, 15, 1, '已退款', '1');
INSERT INTO `orders` VALUES (1768298137535, '2026-01-13 17:55:37', '202611317553544642999', 'shangpinguanli', 1768272751054, 1, '橡皮糖', 'http://10.40.92.160:8080/springbootzkgw/upload/1768272869644.jpg', 1, 15, 15, 15, 15, 1, '已支付', '1');
INSERT INTO `orders` VALUES (1768298148379, '2026-01-13 17:55:47', '202611317554633837352', 'shangpinguanli', 1768272751054, 1, '橡皮糖', 'http://10.40.92.160:8080/springbootzkgw/upload/1768272869644.jpg', 1, 15, 15, 15, 15, 1, '已支付', '1');

-- ----------------------------
-- Table structure for shangjia
-- ----------------------------
DROP TABLE IF EXISTS `shangjia`;
CREATE TABLE `shangjia`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `zhanghao` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '账号',
  `mima` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '密码',
  `shoujihao` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '手机号',
  `shangjiamingcheng` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '商家名称',
  `money` float NULL DEFAULT 0 COMMENT '余额',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1768273103740 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '商家' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of shangjia
-- ----------------------------
INSERT INTO `shangjia` VALUES (1, '2026-01-13 09:15:23', 'tech_gadgets', 'e10adc3949ba59abbe56e057f20f883e', '13800138001', '科技潮品数码店', 12500.5);
INSERT INTO `shangjia` VALUES (2, '2026-01-13 11:08:45', 'fresh_food', 'e10adc3949ba59abbe56e057f20f883e', '13900139002', '鲜果时光生鲜超市', 8760);
INSERT INTO `shangjia` VALUES (3, '2026-01-13 14:30:12', 'book_world', 'e10adc3949ba59abbe56e057f20f883e', '13600136003', '书香门第图书专营', 5320.75);
INSERT INTO `shangjia` VALUES (4, '2026-01-13 16:45:38', 'fashion_life', 'e10adc3949ba59abbe56e057f20f883e', '13700137004', '时尚生活服饰馆', 21000);
INSERT INTO `shangjia` VALUES (5, '2026-01-13 20:22:59', 'home_appliance', 'e10adc3949ba59abbe56e057f20f883e', '13500135005', '居家电器旗舰店', 15800.2);
INSERT INTO `shangjia` VALUES (1768273103739, '2026-01-13 10:58:23', '1', '1', '1', '1', 0);

-- ----------------------------
-- Table structure for shangpinfenlei
-- ----------------------------
DROP TABLE IF EXISTS `shangpinfenlei`;
CREATE TABLE `shangpinfenlei`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `fenleimingcheng` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '分类名称',
  `fenleijianjie` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL COMMENT '分类简介',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '商品分类' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of shangpinfenlei
-- ----------------------------
INSERT INTO `shangpinfenlei` VALUES (1, '2026-01-13 09:15:23', '新鲜水果', '汇集全球优质产地的时令鲜果，包括苹果、香蕉、橙子、葡萄等，保证新鲜直达。');
INSERT INTO `shangpinfenlei` VALUES (2, '2026-01-13 11:08:47', '休闲零食', '各类膨化食品、坚果炒货、肉脯蜜饯、糖果巧克力，满足您的休闲时光。');
INSERT INTO `shangpinfenlei` VALUES (3, '2026-01-13 14:30:05', '酒水饮料', '涵盖白酒、红酒、啤酒、果汁、碳酸饮料、功能饮料及乳制品等。');
INSERT INTO `shangpinfenlei` VALUES (4, '2026-01-13 16:45:38', '粮油调味', '大米、食用油、酱油、醋、盐、糖及各种复合调味料，厨房必备。');
INSERT INTO `shangpinfenlei` VALUES (5, '2026-01-13 20:20:12', '家居清洁', '洗衣液、洗洁精、卫生纸、垃圾袋等日常家居清洁与个人护理用品。');

-- ----------------------------
-- Table structure for shangpinguanli
-- ----------------------------
DROP TABLE IF EXISTS `shangpinguanli`;
CREATE TABLE `shangpinguanli`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `mingcheng` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '名称',
  `tupian` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '图片',
  `fenleimingcheng` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '分类名称',
  `jieshao` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL COMMENT '介绍',
  `xiangqing` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL COMMENT '详情',
  `dianpumingcheng` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '店铺名称',
  `userid` bigint(20) NULL DEFAULT NULL COMMENT '用户id',
  `price` float NOT NULL COMMENT '价格',
  `onelimittimes` int(11) NULL DEFAULT -1 COMMENT '单限',
  `alllimittimes` int(11) NULL DEFAULT -1 COMMENT '库存',
  `sfsh` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '否' COMMENT '是否审核',
  `shhf` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL COMMENT '审核回复',
  `thumbsupnum` int(11) NULL DEFAULT 0 COMMENT '赞',
  `crazilynum` int(11) NULL DEFAULT 0 COMMENT '踩',
  `sjid` bigint(20) NOT NULL COMMENT '商家id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1768297975722 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '商品管理' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of shangpinguanli
-- ----------------------------
INSERT INTO `shangpinguanli` VALUES (1, '2026-01-13 09:15:23', '橡皮糖', 'http://10.40.92.160:8080/springbootzkgw/upload/1768272869644.jpg', '手机数码', '新一代A18仿生芯片，6.9英寸超视网膜XDR显示屏，4800万像素主摄，支持卫星通信。', '<p>颜色：深空黑色、银色、金色、原色钛金属</p><p>存储容量：256GB/512GB/1TB</p><p>电池续航：视频播放最长可达29小时</p><p>操作系统：iOS 20</p><p>其他特性：灵动岛、车祸检测、IP68防水防尘</p>', '苹果官方旗舰店', 1, 15, 122, 1228, '是', '商品信息完整，审核通过。', 125, 3, 0);
INSERT INTO `shangpinguanli` VALUES (4, '2026-01-13 16:48:32', '元气森林', 'http://10.40.92.160:8080/springbootzkgw/upload/1768274605791.jpg', '酒水饮料', '次世代游戏主机，支持8K分辨率，光线追踪，超高速SSD，兼容PS5游戏。', 'CPU：定制AMD Zen 4\nGPU：定制RDNA 4\n内存：24GB GDDR6\n存储：2TB NVMe SSD\n光驱：4K UHD蓝光光驱（标准版）\n包含：DualSense 2手柄一只', '索尼官方授权店', 4, 5, 155, 3144, '是', '新品首发，货源充足。', 567, 12, 0);
INSERT INTO `shangpinguanli` VALUES (1768291231359, '2026-01-13 16:00:30', '5', 'http://10.40.92.160:8080/springbootzkgw/upload/1768291216216.jpg', '酒水饮料', '5', '<p>5</p>', '1', 1768273103739, 5, 122, 1222, '是', NULL, 0, 0, 1768273103739);
INSERT INTO `shangpinguanli` VALUES (1768296987191, '2026-01-13 17:36:26', '1', 'http://10.40.92.160:8080/springbootzkgw/upload/1768296979409.jpg', '休闲零食', '1', '<p>1</p>', '1', 1768273103739, 21, 23, 1332, '是', NULL, 0, 0, 1768273103739);
INSERT INTO `shangpinguanli` VALUES (1768297975721, '2026-01-13 17:52:55', '可乐', 'http://10.40.92.160:8080/springbootzkgw/upload/1768297962469.jpg', '酒水饮料', '111', '<p>111</p>', '1', 1768273103739, 15, 100, 200, '是', NULL, 0, 0, 1768273103739);

-- ----------------------------
-- Table structure for storeup
-- ----------------------------
DROP TABLE IF EXISTS `storeup`;
CREATE TABLE `storeup`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `userid` bigint(20) NOT NULL COMMENT '用户id',
  `refid` bigint(20) NULL DEFAULT NULL COMMENT '收藏id',
  `tablename` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '表名',
  `name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '收藏名称',
  `picture` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '收藏图片',
  `type` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '1' COMMENT '类型(1:收藏,21:赞,22:踩)',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1768298180530 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '收藏表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of storeup
-- ----------------------------
INSERT INTO `storeup` VALUES (1637562662544, '2026-01-13 09:15:23', 1, 101, 'goods', '高端智能手机', 'http://localhost:8080/springbootzkgw/upload/商品_6.png', '1');
INSERT INTO `storeup` VALUES (1637562662545, '2026-01-13 14:30:45', 2, 202, 'news', '系统维护公告', 'http://localhost:8080/springbootzkgw/upload/公告_15.png', '1');
INSERT INTO `storeup` VALUES (1637562662546, '2026-01-13 11:05:18', 3, 103, 'goods', '无线蓝牙耳机', 'http://localhost:8080/springbootzkgw/upload/商品_65.png', '21');
INSERT INTO `storeup` VALUES (1637562662547, '2026-01-13 16:42:37', 4, 204, 'news', '春节放假通知', 'http://localhost:8080/springbootzkgw/upload/公告_18.png', '22');
INSERT INTO `storeup` VALUES (1637562662548, '2026-01-13 20:18:09', 5, 105, 'goods', '轻薄笔记本电脑', 'http://localhost:8080/springbootzkgw/upload/商品_53.png', '1');
INSERT INTO `storeup` VALUES (1768290548624, '2026-01-13 15:49:08', 1768272751054, 1, 'shangpinguanli', 'Apple iPhone 16 Pro Max', 'http://10.40.92.160:8080/springbootzkgw/upload/1768272869644.jpg', '1');
INSERT INTO `storeup` VALUES (1768298180529, '2026-01-13 17:56:19', 1768272751054, 1768273151200, 'dianpuxinxi', '1', 'http://10.40.92.160:8080/springbootzkgw/upload/1768273134278.jpg', '1');

-- ----------------------------
-- Table structure for token
-- ----------------------------
DROP TABLE IF EXISTS `token`;
CREATE TABLE `token`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `userid` bigint(20) NOT NULL COMMENT '用户id',
  `username` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '用户名',
  `tablename` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '表名',
  `role` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '角色',
  `token` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '密码',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '新增时间',
  `expiratedtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '过期时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 19 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = 'token表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of token
-- ----------------------------
INSERT INTO `token` VALUES (11, 1, '张三', 'yonghu', '用户', 'a1b2c3d4e5f6g7h8i9j0', '2026-01-13 09:15:23', '2026-01-14 09:15:23');
INSERT INTO `token` VALUES (12, 2, '李四', 'yonghu', '用户', 'k1l2m3n4o5p6q7r8s9t0', '2026-01-13 14:08:47', '2026-01-14 14:08:47');
INSERT INTO `token` VALUES (13, 3, '王五', 'shangjia', '商家', 'u1v2w3x4y5z6a7b8c9d0', '2026-01-13 11:32:11', '2026-01-14 11:32:11');
INSERT INTO `token` VALUES (14, 4, '赵六', 'yonghu', '用户', 'e1f2g3h4i5j6k7l8m9n0', '2026-01-13 16:45:38', '2026-01-14 16:45:38');
INSERT INTO `token` VALUES (15, 5, '管理员', 'users', '管理员', 'o1p2q3r4s5t6u7v8w9x0', '2026-01-13 20:22:05', '2026-01-14 20:22:05');
INSERT INTO `token` VALUES (16, 1768272751054, '1', 'yonghu', '用户', '8071mn57ubra39g37et75cl446d7h3bv', '2026-01-13 10:52:40', '2026-01-13 10:51:47');
INSERT INTO `token` VALUES (17, 1, 'admin', 'users', '管理员', '1xw5edqg5jzskdsjvogqdlotae7odk3h', '2026-01-13 10:54:17', '2026-01-13 10:12:56');
INSERT INTO `token` VALUES (18, 1768273103739, '1', 'shangjia', '商家', '2kbjmdkpi2akd6sm1f965zsd0qco0ypg', '2026-01-13 10:58:28', '2026-01-13 10:09:33');

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `username` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '用户名',
  `password` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '密码',
  `role` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '管理员' COMMENT '角色',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '新增时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '用户表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of users
-- ----------------------------
INSERT INTO `users` VALUES (1, 'admin', 'admin', '管理员', '2025-03-20 10:41:27');
INSERT INTO `users` VALUES (2, 'admin', 'e10adc3949ba59abbe56e057f20f883e', '超级管理员', '2026-01-13 09:15:23');
INSERT INTO `users` VALUES (3, 'zhangsan', 'e10adc3949ba59abbe56e057f20f883e', '管理员', '2026-01-13 10:30:45');
INSERT INTO `users` VALUES (4, 'lisi', 'e10adc3949ba59abbe56e057f20f883e', '普通用户', '2026-01-13 14:22:18');
INSERT INTO `users` VALUES (5, 'wangwu', 'e10adc3949ba59abbe56e057f20f883e', '普通用户', '2026-01-13 16:05:37');
INSERT INTO `users` VALUES (6, 'zhaoliu', 'e10adc3949ba59abbe56e057f20f883e', '管理员', '2026-01-13 20:48:52');

-- ----------------------------
-- Table structure for yonghu
-- ----------------------------
DROP TABLE IF EXISTS `yonghu`;
CREATE TABLE `yonghu`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `zhanghao` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '账号',
  `mima` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '密码',
  `shoujihao` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '手机号',
  `dizhi` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '地址',
  `money` float NULL DEFAULT 0 COMMENT '余额',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1768272751055 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '用户' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of yonghu
-- ----------------------------
INSERT INTO `yonghu` VALUES (1, '2026-01-13 09:15:23', 'user_zhang', 'e10adc3949ba59abbe56e057f20f883e', '13800138001', '北京市朝阳区建国门外大街1号', 1250.5);
INSERT INTO `yonghu` VALUES (2, '2026-01-13 14:30:45', 'lisi_2026', '25d55ad283aa400af464c76d713c07ad', '13912345678', '上海市浦东新区陆家嘴环路100号', 780);
INSERT INTO `yonghu` VALUES (3, '2026-01-13 11:05:17', 'wangwu_tech', '96e79218965eb72c92a549dd5a330112', '15198765432', '广东省深圳市南山区科技园飞亚达大厦', 3200.75);
INSERT INTO `yonghu` VALUES (4, '2026-01-13 16:48:09', 'zhaoliu_shop', 'e3ceb5881a0a1fdaad01296d7554868d', '13611223344', '浙江省杭州市余杭区文一西路969号', 50.25);
INSERT INTO `yonghu` VALUES (5, '2026-01-13 20:22:38', 'sunqi_student', 'fcea920f7412b5da7be0cf42b8c93759', '18876543210', '江苏省南京市鼓楼区汉口路22号南京大学', 120);
INSERT INTO `yonghu` VALUES (1768272751054, '2026-01-13 10:52:31', '1', '1', '11111111111', '1', 104940);

SET FOREIGN_KEY_CHECKS = 1;
