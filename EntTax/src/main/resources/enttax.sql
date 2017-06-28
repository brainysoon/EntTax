SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table bill
-- ----------------------------
DROP TABLE IF EXISTS `bill`;

CREATE TABLE `bill` (
  `BId`         CHAR(30)    NOT NULL,
  `BType`       VARCHAR(10) NOT NULL,
  `BName`       VARCHAR(20) NOT NULL,
  `BMonth`      SMALLINT    NOT NULL,
  `BPrice`      DOUBLE      NOT NULL,
  `BUpdateTime` DATETIME DEFAULT NULL,
  `BMark`       SMALLINT DEFAULT NULL,
  PRIMARY KEY (`BId`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

-- ----------------------------
-- Table loginfo
-- ----------------------------
DROP TABLE IF EXISTS `loginfo`;

CREATE TABLE `loginfo` (
  `LId`      CHAR(20) NOT NULL,
  `LTime`    DATETIME NOT NULL,
  `LMessage` VARCHAR(255) DEFAULT NULL,
  PRIMARY KEY (`LId`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

-- ----------------------------
-- Table perms
-- ----------------------------
DROP TABLE IF EXISTS `perms`;

CREATE TABLE `perms` (
  `PId`    CHAR(4)     NOT NULL,
  `PName`  VARCHAR(10) NOT NULL,
  `PLabel` VARCHAR(10) DEFAULT NULL,
  `PType`  INT(11)     NOT NULL,
  `PMark`  SMALLINT(6) DEFAULT NULL,
  PRIMARY KEY (`PId`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

-- ----------------------------
-- Table perm_role
-- ----------------------------
DROP TABLE IF EXISTS `role_perms`;

CREATE TABLE `role_perms` (
  `PId` CHAR(4) NOT NULL,
  `RId` CHAR(4) NOT NULL,
  PRIMARY KEY (`RId`),
  KEY `PId` (`PId`),
  CONSTRAINT `role_perms_ibfk_1` FOREIGN KEY (`PId`) REFERENCES `perms` (`PId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `role_perms_ibfk_2` FOREIGN KEY (`RId`) REFERENCES `role` (`RId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

-- ----------------------------
-- Table role
-- ----------------------------
DROP TABLE IF EXISTS `role`;

CREATE TABLE `role` (
  `RId`         CHAR(4)     NOT NULL,
  `RName`       VARCHAR(10) NOT NULL,
  `RLabel`      VARCHAR(10) DEFAULT NULL,
  `RUpdateTime` DATETIME    DEFAULT NULL,
  `RMark`       SMALLINT(6) DEFAULT NULL,
  PRIMARY KEY (`RId`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

-- ----------------------------
-- Table staff
-- ----------------------------
DROP TABLE IF EXISTS `staff`;

CREATE TABLE `staff` (
  `SId`       CHAR(8)      NOT NULL,
  `SName`     VARCHAR(10)  NOT NULL,
  `SPassword` VARCHAR(255) NOT NULL,
  `SSalt`     VARCHAR(50)  NOT NULL,
  `SEmail`    VARCHAR(255) DEFAULT NULL UNIQUE,
  `SPhone`    CHAR(11)     DEFAULT NULL UNIQUE,
  `SSex`      TINYINT(1)   DEFAULT NULL,
  `SBirthday` DATE         DEFAULT NULL,
  `SEnter`    DATETIME     DEFAULT NULL,
  `SMark`     SMALLINT(6)  DEFAULT NULL,
  `SAddress`  VARCHAR(30)  DEFAULT NULL,
  `SAvatar`   VARCHAR(255) DEFAULT NULL,
  PRIMARY KEY (`SId`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

-- ----------------------------
-- Table staff_role
-- ----------------------------
DROP TABLE IF EXISTS `staff_role`;

CREATE TABLE `staff_role` (
  `SId` CHAR(8) NOT NULL,
  `RId` CHAR(4) NOT NULL,
  KEY `SId` (`SId`) USING BTREE,
  KEY `RId` (`RId`),
  CONSTRAINT `staff_role_ibfk_2` FOREIGN KEY (`RId`) REFERENCES `role` (`RId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `staff_role_ibfk_1` FOREIGN KEY (`SId`) REFERENCES `staff` (`SId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

-- ----------------------------
-- Table Msg
-- ----------------------------

DROP TABLE IF EXISTS `msg`;

CREATE TABLE `msg` (
  `MId`      CHAR(20)     NOT NULL,
  `ToSId`    CHAR(8)      NOT NULL,
  `FromSId`  CHAR(8)      NOT NULL,
  `MRead`    INT          NOT NULL,
  `MContent` VARCHAR(255) NOT NULL,
  `MDate`    DATETIME     NOT NULL,
  `MMark`    INT          NOT NULL,
  PRIMARY KEY (`MId`),
  KEY (`ToSId`),
  KEY (`FromSId`),
  CONSTRAINT `msg_staff_ibfk_1` FOREIGN KEY (`ToSId`) REFERENCES `staff` (`SId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `msg_staff_idfk_2` FOREIGN KEY (`FromSId`) REFERENCES `staff` (`SId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

-- ------------------
-- 趋势预测测试数据
-- ------------------
INSERT INTO enttax.bill (BId, BType, BName, BMonth, BPrice, BUpdateTime, BMark)
VALUES ('20170517201923123216', '销项数据', '铁皮', 2, 1.4, '2017-06-20 16:07:15', 1);
INSERT INTO enttax.bill (BId, BType, BName, BMonth, BPrice, BUpdateTime, BMark)
VALUES ('20170517201923123217', '销项数据', '罐头', 2, 5.6, '2017-06-20 16:07:15', 1);
INSERT INTO enttax.bill (BId, BType, BName, BMonth, BPrice, BUpdateTime, BMark)
VALUES ('20170517201923123218', '销项数据', '猪肉', 2, 0.3, '2017-06-20 16:07:15', 1);
INSERT INTO enttax.bill (BId, BType, BName, BMonth, BPrice, BUpdateTime, BMark)
VALUES ('20170517201923123226', '销项数据', '铁皮', 3, 1.9, '2017-06-20 16:06:29', 1);
INSERT INTO enttax.bill (BId, BType, BName, BMonth, BPrice, BUpdateTime, BMark)
VALUES ('20170517201923123227', '销项数据', '罐头', 3, 5.2, '2017-06-20 16:06:29', 1);
INSERT INTO enttax.bill (BId, BType, BName, BMonth, BPrice, BUpdateTime, BMark)
VALUES ('20170517201923123228', '销项数据', '猪肉', 3, 0.5, '2017-06-20 16:06:29', 1);
INSERT INTO enttax.bill (BId, BType, BName, BMonth, BPrice, BUpdateTime, BMark)
VALUES ('20170517201923123236', '销项数据', '铁皮', 4, 2.2, '2017-06-20 16:05:44', 1);
INSERT INTO enttax.bill (BId, BType, BName, BMonth, BPrice, BUpdateTime, BMark)
VALUES ('20170517201923123237', '销项数据', '罐头', 4, 5.1, '2017-06-20 16:05:44', 1);
INSERT INTO enttax.bill (BId, BType, BName, BMonth, BPrice, BUpdateTime, BMark)
VALUES ('20170517201923123238', '销项数据', '猪肉', 4, 0.6, '2017-06-20 16:05:44', 1);
INSERT INTO enttax.bill (BId, BType, BName, BMonth, BPrice, BUpdateTime, BMark)
VALUES ('20170517201923123246', '销项数据', '铁皮', 1, 0.9, '2017-06-20 16:07:56', 1);
INSERT INTO enttax.bill (BId, BType, BName, BMonth, BPrice, BUpdateTime, BMark)
VALUES ('20170517201923123247', '销项数据', '罐头', 1, 6.1, '2017-06-20 16:07:56', 1);
INSERT INTO enttax.bill (BId, BType, BName, BMonth, BPrice, BUpdateTime, BMark)
VALUES ('20170517201923123248', '销项数据', '猪肉', 1, 0.1, '2017-06-20 16:07:56', 1);
INSERT INTO enttax.bill (BId, BType, BName, BMonth, BPrice, BUpdateTime, BMark)
VALUES ('20170517201923123256', '销项数据', '铁皮', 5, 2.7, '2017-06-20 16:04:40', 1);
INSERT INTO enttax.bill (BId, BType, BName, BMonth, BPrice, BUpdateTime, BMark)
VALUES ('20170517201923123257', '销项数据', '罐头', 5, 4.4, '2017-06-20 16:04:40', 1);
INSERT INTO enttax.bill (BId, BType, BName, BMonth, BPrice, BUpdateTime, BMark)
VALUES ('20170517201923123258', '销项数据', '猪肉', 5, 1.3, '2017-06-20 16:04:40', 1);
INSERT INTO enttax.bill (BId, BType, BName, BMonth, BPrice, BUpdateTime, BMark)
VALUES ('20170517201923123266', '销项数据', '铁皮', 6, 3.1, '2017-06-20 16:03:15', 1);
INSERT INTO enttax.bill (BId, BType, BName, BMonth, BPrice, BUpdateTime, BMark)
VALUES ('20170517201923123267', '销项数据', '罐头', 6, 4.1, '2017-06-20 16:03:15', 1);
INSERT INTO enttax.bill (BId, BType, BName, BMonth, BPrice, BUpdateTime, BMark)
VALUES ('20170517201923123268', '销项数据', '猪肉', 6, 1.6, '2017-06-20 16:03:15', 1);

-- ---------------
--  李常的数据
-- ---------------
INSERT INTO enttax.bill (BId, BType, BName, BMonth, BPrice, BUpdateTime, BMark) VALUES ('20140117201923123100', '进项数据', '鸡肉', 1, 2.5, '2017-06-19 15:56:26', 0);
INSERT INTO enttax.bill (BId, BType, BName, BMonth, BPrice, BUpdateTime, BMark) VALUES ('20140117201923123101', '进项数据', '花生油', 2, 6, '2017-06-19 15:56:46', 0);
INSERT INTO enttax.bill (BId, BType, BName, BMonth, BPrice, BUpdateTime, BMark) VALUES ('20140117201923123102', '进项数据', '牛肉', 3, 7.8, '2017-06-19 16:10:48', 0);
INSERT INTO enttax.bill (BId, BType, BName, BMonth, BPrice, BUpdateTime, BMark) VALUES ('20140117201923123103', '销项数据', '鱼', 4, 14.1, '2017-06-19 16:14:10', 0);
INSERT INTO enttax.bill (BId, BType, BName, BMonth, BPrice, BUpdateTime, BMark) VALUES ('20140117201923123104', '进项数据', '鸡肉', 3, 29.7, '2017-06-19 15:52:06', 0);
INSERT INTO enttax.bill (BId, BType, BName, BMonth, BPrice, BUpdateTime, BMark) VALUES ('20140117201923123105', '进项数据', '花生油', 6, 8.6, '2017-06-19 09:33:57', 0);
INSERT INTO enttax.bill (BId, BType, BName, BMonth, BPrice, BUpdateTime, BMark) VALUES ('20140117201923123106', '进项数据', '鸡肉', 7, 5.6, '2014-06-14 21:31:27', 0);
INSERT INTO enttax.bill (BId, BType, BName, BMonth, BPrice, BUpdateTime, BMark) VALUES ('20140117201923123107', '进项数据', '牛奶', 8, 11.7, '2017-06-19 16:15:29', 0);
INSERT INTO enttax.bill (BId, BType, BName, BMonth, BPrice, BUpdateTime, BMark) VALUES ('20140117201923123108', '进项数据', '花生油', 9, 5.3, '2017-06-19 10:13:26', 0);
INSERT INTO enttax.bill (BId, BType, BName, BMonth, BPrice, BUpdateTime, BMark) VALUES ('20140117201923123109', '进项数据', '鸡肉', 10, 10.1, '2014-06-14 21:31:27', 0);
INSERT INTO enttax.bill (BId, BType, BName, BMonth, BPrice, BUpdateTime, BMark) VALUES ('20140117201923123110', '进项数据', '鸡肉', 11, 7.4, '2014-06-14 21:31:27', -1);
INSERT INTO enttax.bill (BId, BType, BName, BMonth, BPrice, BUpdateTime, BMark) VALUES ('20140117201923123111', '进项数据', '花生油', 12, 2.3, '2014-06-14 21:31:27', 0);
INSERT INTO enttax.bill (BId, BType, BName, BMonth, BPrice, BUpdateTime, BMark) VALUES ('20140117201923123112', '进项数据', '鸡肉', 1, 6.5, '2014-06-14 21:31:27', 0);
INSERT INTO enttax.bill (BId, BType, BName, BMonth, BPrice, BUpdateTime, BMark) VALUES ('20140117201923123113', '进项数据', '鸡肉', 2, 5.8, '2014-06-14 21:31:27', 0);
INSERT INTO enttax.bill (BId, BType, BName, BMonth, BPrice, BUpdateTime, BMark) VALUES ('20140117201923123114', '进项数据', '花生油', 3, 2.3, '2014-06-14 21:31:27', 0);
INSERT INTO enttax.bill (BId, BType, BName, BMonth, BPrice, BUpdateTime, BMark) VALUES ('20140117201923123115', '进项数据', '鸡肉', 4, 6.5, '2014-06-14 21:31:27', 0);
INSERT INTO enttax.bill (BId, BType, BName, BMonth, BPrice, BUpdateTime, BMark) VALUES ('20140117201923123116', '进项数据', '鸡肉', 5, 2.5, '2014-06-14 21:31:27', 0);
INSERT INTO enttax.bill (BId, BType, BName, BMonth, BPrice, BUpdateTime, BMark) VALUES ('20140117201923123117', '进项数据', '花生油', 6, 6.4, '2014-06-14 21:31:27', 0);
INSERT INTO enttax.bill (BId, BType, BName, BMonth, BPrice, BUpdateTime, BMark) VALUES ('20140117201923123118', '进项数据', '鸡肉', 7, 7.8, '2014-06-14 21:31:27', 0);
INSERT INTO enttax.bill (BId, BType, BName, BMonth, BPrice, BUpdateTime, BMark) VALUES ('20140117201923123119', '进项数据', '鸡肉', 8, 11.4, '2014-06-14 21:31:27', -1);
INSERT INTO enttax.bill (BId, BType, BName, BMonth, BPrice, BUpdateTime, BMark) VALUES ('20140117201923123120', '进项数据', '花生油', 9, 29.7, '2014-06-14 21:31:27', 0);
INSERT INTO enttax.bill (BId, BType, BName, BMonth, BPrice, BUpdateTime, BMark) VALUES ('20140117201923123121', '进项数据', '鸡肉', 10, 3.4, '2014-06-14 21:31:27', 0);
INSERT INTO enttax.bill (BId, BType, BName, BMonth, BPrice, BUpdateTime, BMark) VALUES ('20140117201923123122', '进项数据', '鸡肉', 11, 5.6, '2014-06-14 21:31:27', 0);
INSERT INTO enttax.bill (BId, BType, BName, BMonth, BPrice, BUpdateTime, BMark) VALUES ('20140117201923123123', '进项数据', '花生油', 12, 11.7, '2014-06-14 21:31:27', 0);
INSERT INTO enttax.bill (BId, BType, BName, BMonth, BPrice, BUpdateTime, BMark) VALUES ('20140117201923123124', '进项数据', '花生油', 1, 3.9, '2014-06-14 21:31:27', 0);
INSERT INTO enttax.bill (BId, BType, BName, BMonth, BPrice, BUpdateTime, BMark) VALUES ('20140117201923123125', '进项数据', '鸡肉', 2, 10.1, '2014-06-14 21:31:27', 0);
INSERT INTO enttax.bill (BId, BType, BName, BMonth, BPrice, BUpdateTime, BMark) VALUES ('20140117201923123126', '进项数据', '花生油', 3, 7.4, '2014-06-14 21:31:27', 0);
INSERT INTO enttax.bill (BId, BType, BName, BMonth, BPrice, BUpdateTime, BMark) VALUES ('20140117201923123127', '进项数据', '鸡肉', 4, 2.3, '2014-06-14 21:31:27', 0);
INSERT INTO enttax.bill (BId, BType, BName, BMonth, BPrice, BUpdateTime, BMark) VALUES ('20140117201923123128', '进项数据', '鸡肉', 5, 9.8, '2014-06-14 21:31:27', 0);
INSERT INTO enttax.bill (BId, BType, BName, BMonth, BPrice, BUpdateTime, BMark) VALUES ('20140117201923123129', '进项数据', '花生油', 6, 2.3, '2014-06-14 21:31:27', 0);
INSERT INTO enttax.bill (BId, BType, BName, BMonth, BPrice, BUpdateTime, BMark) VALUES ('20140117201923123130', '进项数据', '鸡肉', 7, 2.3, '2014-06-14 21:31:27', 0);
INSERT INTO enttax.bill (BId, BType, BName, BMonth, BPrice, BUpdateTime, BMark) VALUES ('20140117201923123131', '进项数据', '鸡肉', 4, 11.4, '2014-06-20 16:17:10', 0);
INSERT INTO enttax.bill (BId, BType, BName, BMonth, BPrice, BUpdateTime, BMark) VALUES ('20140117201923123132', '进项数据', '鸡肉', 5, 29.7, '2014-06-20 16:17:10', 0);
INSERT INTO enttax.bill (BId, BType, BName, BMonth, BPrice, BUpdateTime, BMark) VALUES ('20140117201923123133', '进项数据', '花生油', 6, 3.4, '2014-06-20 16:17:10', 0);
INSERT INTO enttax.bill (BId, BType, BName, BMonth, BPrice, BUpdateTime, BMark) VALUES ('20140117201923123134', '进项数据', '鸡肉', 7, 5.6, '2014-06-20 16:17:10', 0);
INSERT INTO enttax.bill (BId, BType, BName, BMonth, BPrice, BUpdateTime, BMark) VALUES ('20140117201923123135', '进项数据', '鸡肉', 8, 11.7, '2014-06-20 16:17:10', 0);
INSERT INTO enttax.bill (BId, BType, BName, BMonth, BPrice, BUpdateTime, BMark) VALUES ('20140117201923123136', '进项数据', '花生油', 9, 3.9, '2014-06-20 16:17:10', 0);
INSERT INTO enttax.bill (BId, BType, BName, BMonth, BPrice, BUpdateTime, BMark) VALUES ('20140117201923123137', '进项数据', '鸡肉', 10, 10.1, '2014-06-20 16:17:10', 0);
INSERT INTO enttax.bill (BId, BType, BName, BMonth, BPrice, BUpdateTime, BMark) VALUES ('20140117201923123138', '进项数据', '鸡肉', 11, 7.4, '2014-06-20 16:17:10', 0);
INSERT INTO enttax.bill (BId, BType, BName, BMonth, BPrice, BUpdateTime, BMark) VALUES ('20140117201923123139', '进项数据', '花生油', 12, 2.3, '2014-06-20 16:17:10', 0);
INSERT INTO enttax.bill (BId, BType, BName, BMonth, BPrice, BUpdateTime, BMark) VALUES ('20140117201923123140', '进项数据', '鸡肉', 1, 6.5, '2014-06-20 16:17:10', 0);
INSERT INTO enttax.bill (BId, BType, BName, BMonth, BPrice, BUpdateTime, BMark) VALUES ('20140117201923123141', '进项数据', '鸡肉', 2, 5.8, '2014-06-20 16:17:10', 0);
INSERT INTO enttax.bill (BId, BType, BName, BMonth, BPrice, BUpdateTime, BMark) VALUES ('20140117201923123142', '进项数据', '花生油', 3, 2.3, '2014-06-20 16:17:10', 0);
INSERT INTO enttax.bill (BId, BType, BName, BMonth, BPrice, BUpdateTime, BMark) VALUES ('20140117201923123143', '进项数据', '鸡肉', 4, 11.4, '2014-06-20 16:19:32', 0);
INSERT INTO enttax.bill (BId, BType, BName, BMonth, BPrice, BUpdateTime, BMark) VALUES ('20140117201923123144', '进项数据', '鸡肉', 5, 29.7, '2014-06-20 16:19:32', 0);
INSERT INTO enttax.bill (BId, BType, BName, BMonth, BPrice, BUpdateTime, BMark) VALUES ('20140117201923123145', '进项数据', '花生油', 6, 3.4, '2014-06-20 16:19:32', 0);
INSERT INTO enttax.bill (BId, BType, BName, BMonth, BPrice, BUpdateTime, BMark) VALUES ('20140117201923123146', '进项数据', '鸡肉', 7, 5.6, '2014-06-20 16:19:32', 0);
INSERT INTO enttax.bill (BId, BType, BName, BMonth, BPrice, BUpdateTime, BMark) VALUES ('20140117201923123147', '进项数据', '鸡肉', 8, 11.7, '2014-06-20 16:19:32', 0);
INSERT INTO enttax.bill (BId, BType, BName, BMonth, BPrice, BUpdateTime, BMark) VALUES ('20140117201923123148', '进项数据', '花生油', 9, 3.9, '2014-06-20 16:19:32', 0);
INSERT INTO enttax.bill (BId, BType, BName, BMonth, BPrice, BUpdateTime, BMark) VALUES ('20140117201923123149', '进项数据', '鸡肉', 10, 10.1, '2014-06-20 16:19:32', 0);
INSERT INTO enttax.bill (BId, BType, BName, BMonth, BPrice, BUpdateTime, BMark) VALUES ('20140117201923123150', '进项数据', '鸡肉', 11, 7.4, '2014-06-20 16:19:32', 0);
INSERT INTO enttax.bill (BId, BType, BName, BMonth, BPrice, BUpdateTime, BMark) VALUES ('20140117201923123151', '进项数据', '花生油', 12, 2.3, '2014-06-20 16:19:32', 0);
INSERT INTO enttax.bill (BId, BType, BName, BMonth, BPrice, BUpdateTime, BMark) VALUES ('20140117201923123152', '进项数据', '鸡肉', 1, 6.5, '2014-06-20 16:19:32', 0);
INSERT INTO enttax.bill (BId, BType, BName, BMonth, BPrice, BUpdateTime, BMark) VALUES ('20140117201923123153', '进项数据', '鸡肉', 2, 5.8, '2014-06-20 16:19:32', 0);
INSERT INTO enttax.bill (BId, BType, BName, BMonth, BPrice, BUpdateTime, BMark) VALUES ('20140117201923123154', '进项数据', '花生油', 3, 2.3, '2014-06-20 16:19:32', 0);
INSERT INTO enttax.bill (BId, BType, BName, BMonth, BPrice, BUpdateTime, BMark) VALUES ('20140117201923123155', '进项数据', '鸡肉', 4, 11.4, '2014-06-20 16:29:24', 0);
INSERT INTO enttax.bill (BId, BType, BName, BMonth, BPrice, BUpdateTime, BMark) VALUES ('20140117201923123156', '进项数据', '鸡肉', 5, 29.7, '2014-06-20 16:29:24', 0);
INSERT INTO enttax.bill (BId, BType, BName, BMonth, BPrice, BUpdateTime, BMark) VALUES ('20140117201923123157', '进项数据', '花生油', 6, 3.4, '2014-06-20 16:29:24', 0);
INSERT INTO enttax.bill (BId, BType, BName, BMonth, BPrice, BUpdateTime, BMark) VALUES ('20140117201923123158', '进项数据', '鸡肉', 7, 5.6, '2014-06-20 16:29:24', 0);
INSERT INTO enttax.bill (BId, BType, BName, BMonth, BPrice, BUpdateTime, BMark) VALUES ('20140117201923123159', '进项数据', '鸡肉', 8, 11.7, '2014-06-20 16:29:24', 0);
INSERT INTO enttax.bill (BId, BType, BName, BMonth, BPrice, BUpdateTime, BMark) VALUES ('20140117201923123160', '进项数据', '花生油', 9, 3.9, '2014-06-20 16:29:24', 0);
INSERT INTO enttax.bill (BId, BType, BName, BMonth, BPrice, BUpdateTime, BMark) VALUES ('20140117201923123161', '进项数据', '鸡肉', 10, 10.1, '2014-06-20 16:29:24', 0);
INSERT INTO enttax.bill (BId, BType, BName, BMonth, BPrice, BUpdateTime, BMark) VALUES ('20140117201923123162', '进项数据', '鸡肉', 11, 7.4, '2014-06-20 16:29:24', 0);
INSERT INTO enttax.bill (BId, BType, BName, BMonth, BPrice, BUpdateTime, BMark) VALUES ('20140117201923123163', '进项数据', '花生油', 12, 2.3, '2014-06-20 16:29:24', 0);
INSERT INTO enttax.bill (BId, BType, BName, BMonth, BPrice, BUpdateTime, BMark) VALUES ('20140117201923123164', '进项数据', '鸡肉', 1, 6.5, '2014-06-20 16:29:24', 0);
INSERT INTO enttax.bill (BId, BType, BName, BMonth, BPrice, BUpdateTime, BMark) VALUES ('20140117201923123165', '进项数据', '鸡肉', 2, 5.8, '2014-06-20 16:29:24', 0);
INSERT INTO enttax.bill (BId, BType, BName, BMonth, BPrice, BUpdateTime, BMark) VALUES ('20140117201923123166', '进项数据', '花生油', 3, 2.3, '2014-06-20 16:29:24', 0);
INSERT INTO enttax.bill (BId, BType, BName, BMonth, BPrice, BUpdateTime, BMark) VALUES ('20140117201923123167', '进项数据', '鸡肉', 4, 11.4, '2014-06-20 16:38:15', 0);
INSERT INTO enttax.bill (BId, BType, BName, BMonth, BPrice, BUpdateTime, BMark) VALUES ('20140122019231231001', '进项数据', '鸡肉', 4, 11.4, '2014-06-21 20:13:10', 0);
INSERT INTO enttax.bill (BId, BType, BName, BMonth, BPrice, BUpdateTime, BMark) VALUES ('20140217201923123100', '销项数据', '铁皮', 1, 6.5, '2014-06-14 21:31:27', 1);
INSERT INTO enttax.bill (BId, BType, BName, BMonth, BPrice, BUpdateTime, BMark) VALUES ('20140217201923123101', '销项数据', '罐头', 2, 0.8, '2014-06-14 21:31:27', 1);
INSERT INTO enttax.bill (BId, BType, BName, BMonth, BPrice, BUpdateTime, BMark) VALUES ('20140217201923123102', '销项数据', '猪肉', 3, 2.3, '2014-06-14 21:31:27', 1);
INSERT INTO enttax.bill (BId, BType, BName, BMonth, BPrice, BUpdateTime, BMark) VALUES ('20140217201923123103', '销项数据', '铁皮', 4, 6.5, '2014-06-14 21:31:27', 1);
INSERT INTO enttax.bill (BId, BType, BName, BMonth, BPrice, BUpdateTime, BMark) VALUES ('20140217201923123104', '销项数据', '罐头', 5, 0.8, '2014-06-14 21:31:27', 1);
INSERT INTO enttax.bill (BId, BType, BName, BMonth, BPrice, BUpdateTime, BMark) VALUES ('20140217201923123105', '销项数据', '猪肉', 6, 2.3, '2014-06-14 21:31:27', 1);
INSERT INTO enttax.bill (BId, BType, BName, BMonth, BPrice, BUpdateTime, BMark) VALUES ('20140217201923123106', '销项数据', '铁皮', 7, 6.5, '2014-06-14 21:31:27', 1);
INSERT INTO enttax.bill (BId, BType, BName, BMonth, BPrice, BUpdateTime, BMark) VALUES ('20140217201923123107', '销项数据', '罐头', 8, 0.8, '2014-06-14 21:31:27', 1);
INSERT INTO enttax.bill (BId, BType, BName, BMonth, BPrice, BUpdateTime, BMark) VALUES ('20140217201923123108', '销项数据', '猪肉', 9, 2.3, '2014-06-14 21:31:27', 1);
INSERT INTO enttax.bill (BId, BType, BName, BMonth, BPrice, BUpdateTime, BMark) VALUES ('20140217201923123109', '销项数据', '铁皮', 10, 6.5, '2014-06-14 21:31:27', 1);
INSERT INTO enttax.bill (BId, BType, BName, BMonth, BPrice, BUpdateTime, BMark) VALUES ('20140217201923123110', '销项数据', '罐头', 11, 7.8, '2014-06-14 21:31:27', 1);
INSERT INTO enttax.bill (BId, BType, BName, BMonth, BPrice, BUpdateTime, BMark) VALUES ('20140217201923123111', '销项数据', '猪肉', 12, 11.4, '2014-06-14 21:31:27', 1);
INSERT INTO enttax.bill (BId, BType, BName, BMonth, BPrice, BUpdateTime, BMark) VALUES ('20140217201923123112', '销项数据', '铁皮', 1, 29.7, '2014-06-14 21:31:27', -1);
INSERT INTO enttax.bill (BId, BType, BName, BMonth, BPrice, BUpdateTime, BMark) VALUES ('20140217201923123113', '销项数据', '罐头', 2, 3.4, '2014-06-14 21:31:27', 1);
INSERT INTO enttax.bill (BId, BType, BName, BMonth, BPrice, BUpdateTime, BMark) VALUES ('20140217201923123114', '销项数据', '猪肉', 3, 5.6, '2014-06-14 21:31:27', 1);
INSERT INTO enttax.bill (BId, BType, BName, BMonth, BPrice, BUpdateTime, BMark) VALUES ('20140217201923123115', '销项数据', '铁皮', 4, 11.7, '2014-06-14 21:31:27', 1);
INSERT INTO enttax.bill (BId, BType, BName, BMonth, BPrice, BUpdateTime, BMark) VALUES ('20140217201923123116', '销项数据', '罐头', 5, 3.9, '2014-06-14 21:31:27', 1);
INSERT INTO enttax.bill (BId, BType, BName, BMonth, BPrice, BUpdateTime, BMark) VALUES ('20140217201923123117', '销项数据', '猪肉', 6, 10.1, '2014-06-14 21:31:27', 1);
INSERT INTO enttax.bill (BId, BType, BName, BMonth, BPrice, BUpdateTime, BMark) VALUES ('20140217201923123118', '销项数据', '铁皮', 7, 7.4, '2014-06-14 21:31:27', 1);
INSERT INTO enttax.bill (BId, BType, BName, BMonth, BPrice, BUpdateTime, BMark) VALUES ('20140217201923123119', '销项数据', '罐头', 8, 0.8, '2014-06-14 21:31:27', 1);
INSERT INTO enttax.bill (BId, BType, BName, BMonth, BPrice, BUpdateTime, BMark) VALUES ('20140217201923123120', '销项数据', '猪肉', 9, 2.3, '2014-06-14 21:31:27', 1);
INSERT INTO enttax.bill (BId, BType, BName, BMonth, BPrice, BUpdateTime, BMark) VALUES ('20140217201923123121', '销项数据', '铁皮', 10, 6.5, '2014-06-14 21:31:27', 1);
INSERT INTO enttax.bill (BId, BType, BName, BMonth, BPrice, BUpdateTime, BMark) VALUES ('20140217201923123122', '销项数据', '罐头', 11, 0.8, '2014-06-14 21:31:27', 1);
INSERT INTO enttax.bill (BId, BType, BName, BMonth, BPrice, BUpdateTime, BMark) VALUES ('20140217201923123123', '销项数据', '猪肉', 12, 7.8, '2014-06-14 21:31:27', 1);
INSERT INTO enttax.bill (BId, BType, BName, BMonth, BPrice, BUpdateTime, BMark) VALUES ('20140217201923123124', '销项数据', '铁皮', 1, 11.4, '2014-06-14 21:31:27', 1);
INSERT INTO enttax.bill (BId, BType, BName, BMonth, BPrice, BUpdateTime, BMark) VALUES ('20140217201923123125', '销项数据', '罐头', 2, 29.7, '2014-06-14 21:31:27', 1);
INSERT INTO enttax.bill (BId, BType, BName, BMonth, BPrice, BUpdateTime, BMark) VALUES ('20140217201923123126', '销项数据', '猪肉', 3, 3.4, '2014-06-14 21:31:27', 1);
INSERT INTO enttax.bill (BId, BType, BName, BMonth, BPrice, BUpdateTime, BMark) VALUES ('20140217201923123127', '销项数据', '铁皮', 4, 5.6, '2014-06-14 21:31:27', 1);
INSERT INTO enttax.bill (BId, BType, BName, BMonth, BPrice, BUpdateTime, BMark) VALUES ('20140217201923123128', '销项数据', '罐头', 5, 11.7, '2014-06-14 21:31:27', 1);
INSERT INTO enttax.bill (BId, BType, BName, BMonth, BPrice, BUpdateTime, BMark) VALUES ('20140217201923123129', '销项数据', '猪肉', 6, 3.9, '2014-06-14 21:31:27', 1);
INSERT INTO enttax.bill (BId, BType, BName, BMonth, BPrice, BUpdateTime, BMark) VALUES ('20140217201923123130', '销项数据', '铁皮', 7, 10.1, '2014-06-14 21:31:27', 1);
INSERT INTO enttax.bill (BId, BType, BName, BMonth, BPrice, BUpdateTime, BMark) VALUES ('20140217201923123131', '销项数据', '猪肉', 3, 2.3, '2014-06-20 16:17:10', 1);
INSERT INTO enttax.bill (BId, BType, BName, BMonth, BPrice, BUpdateTime, BMark) VALUES ('20140217201923123132', '销项数据', '铁皮', 4, 6.5, '2014-06-20 16:17:10', 1);
INSERT INTO enttax.bill (BId, BType, BName, BMonth, BPrice, BUpdateTime, BMark) VALUES ('20140217201923123133', '销项数据', '罐头', 5, 0.8, '2014-06-20 16:17:10', 1);
INSERT INTO enttax.bill (BId, BType, BName, BMonth, BPrice, BUpdateTime, BMark) VALUES ('20140217201923123134', '销项数据', '猪肉', 6, 2.3, '2014-06-20 16:17:10', 1);
INSERT INTO enttax.bill (BId, BType, BName, BMonth, BPrice, BUpdateTime, BMark) VALUES ('20140217201923123135', '销项数据', '铁皮', 7, 6.5, '2014-06-20 16:17:10', 1);
INSERT INTO enttax.bill (BId, BType, BName, BMonth, BPrice, BUpdateTime, BMark) VALUES ('20140217201923123136', '销项数据', '罐头', 8, 0.8, '2014-06-20 16:17:10', 1);
INSERT INTO enttax.bill (BId, BType, BName, BMonth, BPrice, BUpdateTime, BMark) VALUES ('20140217201923123137', '销项数据', '猪肉', 9, 2.3, '2014-06-20 16:17:10', 1);
INSERT INTO enttax.bill (BId, BType, BName, BMonth, BPrice, BUpdateTime, BMark) VALUES ('20140217201923123138', '销项数据', '铁皮', 10, 6.5, '2014-06-20 16:17:10', 1);
INSERT INTO enttax.bill (BId, BType, BName, BMonth, BPrice, BUpdateTime, BMark) VALUES ('20140217201923123139', '销项数据', '罐头', 11, 7.8, '2014-06-20 16:17:10', 1);
INSERT INTO enttax.bill (BId, BType, BName, BMonth, BPrice, BUpdateTime, BMark) VALUES ('20140217201923123140', '销项数据', '猪肉', 12, 11.4, '2014-06-20 16:17:10', 1);
INSERT INTO enttax.bill (BId, BType, BName, BMonth, BPrice, BUpdateTime, BMark) VALUES ('20140217201923123141', '销项数据', '铁皮', 1, 29.7, '2014-06-20 16:17:10', 1);
INSERT INTO enttax.bill (BId, BType, BName, BMonth, BPrice, BUpdateTime, BMark) VALUES ('20140217201923123142', '销项数据', '罐头', 2, 3.4, '2014-06-20 16:17:10', 1);
INSERT INTO enttax.bill (BId, BType, BName, BMonth, BPrice, BUpdateTime, BMark) VALUES ('20140217201923123143', '销项数据', '猪肉', 3, 2.3, '2014-06-20 16:20:35', 1);
INSERT INTO enttax.bill (BId, BType, BName, BMonth, BPrice, BUpdateTime, BMark) VALUES ('20140217201923123144', '销项数据', '铁皮', 4, 6.5, '2014-06-20 16:20:35', 1);
INSERT INTO enttax.bill (BId, BType, BName, BMonth, BPrice, BUpdateTime, BMark) VALUES ('20140217201923123145', '销项数据', '罐头', 5, 0.8, '2014-06-20 16:20:35', 1);
INSERT INTO enttax.bill (BId, BType, BName, BMonth, BPrice, BUpdateTime, BMark) VALUES ('20140217201923123146', '销项数据', '猪肉', 6, 2.3, '2014-06-20 16:20:35', 1);
INSERT INTO enttax.bill (BId, BType, BName, BMonth, BPrice, BUpdateTime, BMark) VALUES ('20140217201923123147', '销项数据', '铁皮', 7, 6.5, '2014-06-20 16:20:35', 1);
INSERT INTO enttax.bill (BId, BType, BName, BMonth, BPrice, BUpdateTime, BMark) VALUES ('20140217201923123148', '销项数据', '罐头', 8, 0.8, '2014-06-20 16:20:35', 1);
INSERT INTO enttax.bill (BId, BType, BName, BMonth, BPrice, BUpdateTime, BMark) VALUES ('20140217201923123149', '销项数据', '猪肉', 9, 2.3, '2014-06-20 16:20:35', 1);
INSERT INTO enttax.bill (BId, BType, BName, BMonth, BPrice, BUpdateTime, BMark) VALUES ('20140217201923123150', '销项数据', '铁皮', 10, 6.5, '2014-06-20 16:20:35', 1);
INSERT INTO enttax.bill (BId, BType, BName, BMonth, BPrice, BUpdateTime, BMark) VALUES ('20140217201923123151', '销项数据', '罐头', 11, 7.8, '2014-06-20 16:20:35', 1);
INSERT INTO enttax.bill (BId, BType, BName, BMonth, BPrice, BUpdateTime, BMark) VALUES ('20140217201923123152', '销项数据', '猪肉', 12, 11.4, '2014-06-20 16:20:35', 1);
INSERT INTO enttax.bill (BId, BType, BName, BMonth, BPrice, BUpdateTime, BMark) VALUES ('20140217201923123153', '销项数据', '铁皮', 1, 29.7, '2014-06-20 16:20:35', 1);
INSERT INTO enttax.bill (BId, BType, BName, BMonth, BPrice, BUpdateTime, BMark) VALUES ('20140217201923123154', '销项数据', '罐头', 2, 3.4, '2014-06-20 16:20:35', 1);
INSERT INTO enttax.bill (BId, BType, BName, BMonth, BPrice, BUpdateTime, BMark) VALUES ('20140217201923123155', '销项数据', '铁皮', 4, 6.5, '2014-06-20 16:29:44', 1);
INSERT INTO enttax.bill (BId, BType, BName, BMonth, BPrice, BUpdateTime, BMark) VALUES ('20140217201923123156', '销项数据', '罐头', 5, 0.8, '2014-06-20 16:29:44', 1);
INSERT INTO enttax.bill (BId, BType, BName, BMonth, BPrice, BUpdateTime, BMark) VALUES ('20140217201923123157', '销项数据', '猪肉', 6, 2.3, '2014-06-20 16:29:44', 1);
INSERT INTO enttax.bill (BId, BType, BName, BMonth, BPrice, BUpdateTime, BMark) VALUES ('20140217201923123158', '销项数据', '铁皮', 7, 6.5, '2014-06-20 16:29:44', 1);
INSERT INTO enttax.bill (BId, BType, BName, BMonth, BPrice, BUpdateTime, BMark) VALUES ('20140217201923123159', '销项数据', '罐头', 8, 0.8, '2014-06-20 16:29:44', 1);
INSERT INTO enttax.bill (BId, BType, BName, BMonth, BPrice, BUpdateTime, BMark) VALUES ('20140217201923123160', '销项数据', '猪肉', 9, 2.3, '2014-06-20 16:29:44', 1);
INSERT INTO enttax.bill (BId, BType, BName, BMonth, BPrice, BUpdateTime, BMark) VALUES ('20140217201923123161', '销项数据', '铁皮', 10, 6.5, '2014-06-20 16:29:44', 1);
INSERT INTO enttax.bill (BId, BType, BName, BMonth, BPrice, BUpdateTime, BMark) VALUES ('20140217201923123162', '销项数据', '罐头', 11, 7.8, '2014-06-20 16:29:44', 1);
INSERT INTO enttax.bill (BId, BType, BName, BMonth, BPrice, BUpdateTime, BMark) VALUES ('20140217201923123163', '销项数据', '铁皮', 4, 6.5, '2014-06-20 16:38:29', 1);
INSERT INTO enttax.bill (BId, BType, BName, BMonth, BPrice, BUpdateTime, BMark) VALUES ('20140217201923123164', '销项数据', '罐头', 5, 0.8, '2014-06-20 16:38:29', 1);
INSERT INTO enttax.bill (BId, BType, BName, BMonth, BPrice, BUpdateTime, BMark) VALUES ('20140217201923123165', '销项数据', '猪肉', 6, 2.3, '2014-06-20 16:38:29', 1);
INSERT INTO enttax.bill (BId, BType, BName, BMonth, BPrice, BUpdateTime, BMark) VALUES ('20140272019231231001', '销项数据', '铁皮', 4, 6.5, '2014-06-21 20:13:44', 1);
INSERT INTO enttax.bill (BId, BType, BName, BMonth, BPrice, BUpdateTime, BMark) VALUES ('20140272019231231002', '销项数据', '罐头', 5, 0.8, '2014-06-21 20:13:44', 1);
INSERT INTO enttax.bill (BId, BType, BName, BMonth, BPrice, BUpdateTime, BMark) VALUES ('20140272019231231003', '销项数据', '猪肉', 6, 2.3, '2014-06-21 20:13:44', 1);
INSERT INTO enttax.bill (BId, BType, BName, BMonth, BPrice, BUpdateTime, BMark) VALUES ('20140272019231231004', '销项数据', '铁皮', 4, 6.5, '2014-06-21 20:19:40', 1);
INSERT INTO enttax.bill (BId, BType, BName, BMonth, BPrice, BUpdateTime, BMark) VALUES ('20140272019231231005', '销项数据', '罐头', 5, 0.8, '2014-06-21 20:19:40', 1);
INSERT INTO enttax.bill (BId, BType, BName, BMonth, BPrice, BUpdateTime, BMark) VALUES ('20140272019231231006', '销项数据', '猪肉', 6, 2.3, '2014-06-21 20:19:40', 1);
INSERT INTO enttax.bill (BId, BType, BName, BMonth, BPrice, BUpdateTime, BMark) VALUES ('20150117201923123100', '进项数据', '花生油', 1, 6.5, '2015-06-14 21:25:20', 0);
INSERT INTO enttax.bill (BId, BType, BName, BMonth, BPrice, BUpdateTime, BMark) VALUES ('20150117201923123101', '进项数据', '鸡肉', 2, 2.3, '2015-06-14 21:25:20', 0);
INSERT INTO enttax.bill (BId, BType, BName, BMonth, BPrice, BUpdateTime, BMark) VALUES ('20150117201923123102', '进项数据', '花生油', 3, 6.5, '2015-06-14 21:25:20', 0);
INSERT INTO enttax.bill (BId, BType, BName, BMonth, BPrice, BUpdateTime, BMark) VALUES ('20150117201923123103', '进项数据', '鸡肉', 4, 0.8, '2015-06-14 21:25:20', 0);
INSERT INTO enttax.bill (BId, BType, BName, BMonth, BPrice, BUpdateTime, BMark) VALUES ('20150117201923123104', '进项数据', '鸡肉', 5, 2.3, '2015-06-14 21:25:20', 0);
INSERT INTO enttax.bill (BId, BType, BName, BMonth, BPrice, BUpdateTime, BMark) VALUES ('20150117201923123105', '进项数据', '花生油', 6, 6.5, '2015-06-14 21:25:20', 0);
INSERT INTO enttax.bill (BId, BType, BName, BMonth, BPrice, BUpdateTime, BMark) VALUES ('20150117201923123106', '进项数据', '鸡肉', 7, 0.8, '2015-06-14 21:25:20', 0);
INSERT INTO enttax.bill (BId, BType, BName, BMonth, BPrice, BUpdateTime, BMark) VALUES ('20150117201923123107', '进项数据', '鸡肉', 8, 2.3, '2015-06-14 21:25:20', 0);
INSERT INTO enttax.bill (BId, BType, BName, BMonth, BPrice, BUpdateTime, BMark) VALUES ('20150117201923123108', '进项数据', '花生油', 9, 2.3, '2015-06-14 21:25:20', 0);
INSERT INTO enttax.bill (BId, BType, BName, BMonth, BPrice, BUpdateTime, BMark) VALUES ('20150117201923123109', '进项数据', '鸡肉', 10, 6.5, '2015-06-14 21:25:20', 0);
INSERT INTO enttax.bill (BId, BType, BName, BMonth, BPrice, BUpdateTime, BMark) VALUES ('20150117201923123110', '进项数据', '鸡肉', 11, 0.8, '2015-06-14 21:25:20', 0);
INSERT INTO enttax.bill (BId, BType, BName, BMonth, BPrice, BUpdateTime, BMark) VALUES ('20150117201923123111', '进项数据', '花生油', 12, 2.3, '2015-06-14 21:25:20', 0);
INSERT INTO enttax.bill (BId, BType, BName, BMonth, BPrice, BUpdateTime, BMark) VALUES ('20150117201923123112', '进项数据', '鸡肉', 1, 6.5, '2015-06-14 21:25:20', 0);
INSERT INTO enttax.bill (BId, BType, BName, BMonth, BPrice, BUpdateTime, BMark) VALUES ('20150117201923123113', '进项数据', '鸡肉', 2, 0.8, '2015-06-14 21:25:20', 0);
INSERT INTO enttax.bill (BId, BType, BName, BMonth, BPrice, BUpdateTime, BMark) VALUES ('20150117201923123114', '进项数据', '花生油', 3, 2.3, '2015-06-14 21:25:20', 0);
INSERT INTO enttax.bill (BId, BType, BName, BMonth, BPrice, BUpdateTime, BMark) VALUES ('20150117201923123115', '进项数据', '鸡肉', 4, 6.5, '2015-06-14 21:25:20', 0);
INSERT INTO enttax.bill (BId, BType, BName, BMonth, BPrice, BUpdateTime, BMark) VALUES ('20150117201923123116', '进项数据', '鸡肉', 5, 0.8, '2015-06-14 21:25:20', 0);
INSERT INTO enttax.bill (BId, BType, BName, BMonth, BPrice, BUpdateTime, BMark) VALUES ('20150117201923123117', '进项数据', '花生油', 6, 2.3, '2015-06-14 21:25:20', 0);
INSERT INTO enttax.bill (BId, BType, BName, BMonth, BPrice, BUpdateTime, BMark) VALUES ('20150117201923123118', '进项数据', '鸡肉', 7, 2.3, '2015-06-14 21:25:20', 0);
INSERT INTO enttax.bill (BId, BType, BName, BMonth, BPrice, BUpdateTime, BMark) VALUES ('20150117201923123119', '进项数据', '鸡肉', 8, 6.5, '2015-06-14 21:25:20', 0);
INSERT INTO enttax.bill (BId, BType, BName, BMonth, BPrice, BUpdateTime, BMark) VALUES ('20150117201923123120', '进项数据', '花生油', 9, 0.8, '2015-06-14 21:25:20', 0);
INSERT INTO enttax.bill (BId, BType, BName, BMonth, BPrice, BUpdateTime, BMark) VALUES ('20150117201923123121', '进项数据', '鸡肉', 10, 2.3, '2015-06-14 21:25:20', 0);
INSERT INTO enttax.bill (BId, BType, BName, BMonth, BPrice, BUpdateTime, BMark) VALUES ('20150117201923123122', '进项数据', '鸡肉', 11, 6.5, '2015-06-14 21:25:20', 0);
INSERT INTO enttax.bill (BId, BType, BName, BMonth, BPrice, BUpdateTime, BMark) VALUES ('20150117201923123123', '进项数据', '花生油', 12, 0.8, '2015-06-14 21:25:20', 0);
INSERT INTO enttax.bill (BId, BType, BName, BMonth, BPrice, BUpdateTime, BMark) VALUES ('20150117201923123124', '进项数据', '花生油', 5, 6.5, '2015-06-14 21:25:20', 0);
INSERT INTO enttax.bill (BId, BType, BName, BMonth, BPrice, BUpdateTime, BMark) VALUES ('20150117201923123125', '进项数据', '鸡肉', 6, 0.8, '2015-06-14 21:25:20', 0);
INSERT INTO enttax.bill (BId, BType, BName, BMonth, BPrice, BUpdateTime, BMark) VALUES ('20150117201923123126', '进项数据', '花生油', 7, 2.3, '2015-06-14 21:25:20', 0);
INSERT INTO enttax.bill (BId, BType, BName, BMonth, BPrice, BUpdateTime, BMark) VALUES ('20150117201923123127', '进项数据', '鸡肉', 8, 6.5, '2015-06-14 21:25:20', 0);
INSERT INTO enttax.bill (BId, BType, BName, BMonth, BPrice, BUpdateTime, BMark) VALUES ('20150117201923123128', '进项数据', '鸡肉', 1, 0.8, '2015-06-14 21:25:20', 0);
INSERT INTO enttax.bill (BId, BType, BName, BMonth, BPrice, BUpdateTime, BMark) VALUES ('20150117201923123129', '进项数据', '花生油', 2, 2.3, '2015-06-14 21:25:20', 0);
INSERT INTO enttax.bill (BId, BType, BName, BMonth, BPrice, BUpdateTime, BMark) VALUES ('20150117201923123130', '进项数据', '鸡肉', 3, 2.3, '2015-06-14 21:25:20', 0);
INSERT INTO enttax.bill (BId, BType, BName, BMonth, BPrice, BUpdateTime, BMark) VALUES ('20150217201923123100', '销项数据', '铁皮', 1, 6.5, '2015-06-14 21:25:20', 1);
INSERT INTO enttax.bill (BId, BType, BName, BMonth, BPrice, BUpdateTime, BMark) VALUES ('20150217201923123101', '销项数据', '罐头', 2, 0.8, '2015-06-14 21:25:20', 1);
INSERT INTO enttax.bill (BId, BType, BName, BMonth, BPrice, BUpdateTime, BMark) VALUES ('20150217201923123102', '销项数据', '猪肉', 3, 2.3, '2015-06-14 21:25:20', 1);
INSERT INTO enttax.bill (BId, BType, BName, BMonth, BPrice, BUpdateTime, BMark) VALUES ('20150217201923123103', '销项数据', '铁皮', 4, 6.5, '2015-06-14 21:25:20', 1);
INSERT INTO enttax.bill (BId, BType, BName, BMonth, BPrice, BUpdateTime, BMark) VALUES ('20150217201923123104', '销项数据', '罐头', 5, 0.8, '2015-06-14 21:25:20', 1);
INSERT INTO enttax.bill (BId, BType, BName, BMonth, BPrice, BUpdateTime, BMark) VALUES ('20150217201923123105', '销项数据', '猪肉', 6, 2.3, '2015-06-14 21:25:20', 1);
INSERT INTO enttax.bill (BId, BType, BName, BMonth, BPrice, BUpdateTime, BMark) VALUES ('20150217201923123106', '销项数据', '铁皮', 7, 6.5, '2015-06-14 21:25:20', 1);
INSERT INTO enttax.bill (BId, BType, BName, BMonth, BPrice, BUpdateTime, BMark) VALUES ('20150217201923123107', '销项数据', '罐头', 8, 0.8, '2015-06-14 21:25:20', 1);
INSERT INTO enttax.bill (BId, BType, BName, BMonth, BPrice, BUpdateTime, BMark) VALUES ('20150217201923123108', '销项数据', '猪肉', 9, 2.3, '2015-06-14 21:25:20', 1);
INSERT INTO enttax.bill (BId, BType, BName, BMonth, BPrice, BUpdateTime, BMark) VALUES ('20150217201923123109', '销项数据', '铁皮', 10, 6.5, '2015-06-14 21:25:20', 1);
INSERT INTO enttax.bill (BId, BType, BName, BMonth, BPrice, BUpdateTime, BMark) VALUES ('20150217201923123110', '销项数据', '罐头', 11, 0.8, '2015-06-14 21:25:20', 1);
INSERT INTO enttax.bill (BId, BType, BName, BMonth, BPrice, BUpdateTime, BMark) VALUES ('20150217201923123111', '销项数据', '猪肉', 12, 2.3, '2015-06-14 21:25:20', 1);
INSERT INTO enttax.bill (BId, BType, BName, BMonth, BPrice, BUpdateTime, BMark) VALUES ('20150217201923123112', '销项数据', '铁皮', 1, 6.5, '2015-06-14 21:25:20', 1);
INSERT INTO enttax.bill (BId, BType, BName, BMonth, BPrice, BUpdateTime, BMark) VALUES ('20150217201923123113', '销项数据', '罐头', 2, 0.8, '2015-06-14 21:25:20', 1);
INSERT INTO enttax.bill (BId, BType, BName, BMonth, BPrice, BUpdateTime, BMark) VALUES ('20150217201923123114', '销项数据', '猪肉', 3, 2.3, '2015-06-14 21:25:20', 1);
INSERT INTO enttax.bill (BId, BType, BName, BMonth, BPrice, BUpdateTime, BMark) VALUES ('20150217201923123115', '销项数据', '铁皮', 4, 6.5, '2015-06-14 21:25:20', 1);
INSERT INTO enttax.bill (BId, BType, BName, BMonth, BPrice, BUpdateTime, BMark) VALUES ('20150217201923123116', '销项数据', '罐头', 5, 0.8, '2015-06-14 21:25:20', 1);
INSERT INTO enttax.bill (BId, BType, BName, BMonth, BPrice, BUpdateTime, BMark) VALUES ('20150217201923123117', '销项数据', '猪肉', 6, 2.3, '2015-06-14 21:25:20', 1);
INSERT INTO enttax.bill (BId, BType, BName, BMonth, BPrice, BUpdateTime, BMark) VALUES ('20150217201923123118', '销项数据', '铁皮', 7, 6.5, '2015-06-14 21:25:20', 1);
INSERT INTO enttax.bill (BId, BType, BName, BMonth, BPrice, BUpdateTime, BMark) VALUES ('20150217201923123119', '销项数据', '罐头', 8, 0.8, '2015-06-14 21:25:20', 1);
INSERT INTO enttax.bill (BId, BType, BName, BMonth, BPrice, BUpdateTime, BMark) VALUES ('20150217201923123120', '销项数据', '猪肉', 9, 2.3, '2015-06-14 21:25:20', 1);
INSERT INTO enttax.bill (BId, BType, BName, BMonth, BPrice, BUpdateTime, BMark) VALUES ('20150217201923123121', '销项数据', '铁皮', 10, 6.5, '2015-06-14 21:25:20', 1);
INSERT INTO enttax.bill (BId, BType, BName, BMonth, BPrice, BUpdateTime, BMark) VALUES ('20150217201923123122', '销项数据', '罐头', 11, 0.8, '2015-06-14 21:25:20', 1);
INSERT INTO enttax.bill (BId, BType, BName, BMonth, BPrice, BUpdateTime, BMark) VALUES ('20150217201923123123', '销项数据', '猪肉', 12, 2.3, '2015-06-14 21:25:20', 1);
INSERT INTO enttax.bill (BId, BType, BName, BMonth, BPrice, BUpdateTime, BMark) VALUES ('20150217201923123124', '销项数据', '铁皮', 5, 6.5, '2015-06-14 21:25:20', 1);
INSERT INTO enttax.bill (BId, BType, BName, BMonth, BPrice, BUpdateTime, BMark) VALUES ('20150217201923123125', '销项数据', '罐头', 6, 0.8, '2015-06-14 21:25:20', 1);
INSERT INTO enttax.bill (BId, BType, BName, BMonth, BPrice, BUpdateTime, BMark) VALUES ('20150217201923123126', '销项数据', '猪肉', 7, 2.3, '2015-06-14 21:25:20', 1);
INSERT INTO enttax.bill (BId, BType, BName, BMonth, BPrice, BUpdateTime, BMark) VALUES ('20150217201923123127', '销项数据', '铁皮', 8, 6.5, '2015-06-14 21:25:20', 1);
INSERT INTO enttax.bill (BId, BType, BName, BMonth, BPrice, BUpdateTime, BMark) VALUES ('20150217201923123128', '销项数据', '罐头', 1, 0.8, '2015-06-14 21:25:20', 1);
INSERT INTO enttax.bill (BId, BType, BName, BMonth, BPrice, BUpdateTime, BMark) VALUES ('20150217201923123129', '销项数据', '猪肉', 2, 2.3, '2015-06-14 21:25:20', 1);
INSERT INTO enttax.bill (BId, BType, BName, BMonth, BPrice, BUpdateTime, BMark) VALUES ('20150217201923123130', '销项数据', '铁皮', 3, 6.5, '2015-06-14 21:25:20', 1);
INSERT INTO enttax.bill (BId, BType, BName, BMonth, BPrice, BUpdateTime, BMark) VALUES ('20160517201923123100', '进项数据', '花生油', 12, 6.5, '2016-06-14 18:53:36', 0);
INSERT INTO enttax.bill (BId, BType, BName, BMonth, BPrice, BUpdateTime, BMark) VALUES ('20160517201923123101', '进项数据', '鸡肉', 3, 0.8, '2016-06-14 18:53:36', 0);
INSERT INTO enttax.bill (BId, BType, BName, BMonth, BPrice, BUpdateTime, BMark) VALUES ('20160517201923123102', '进项数据', '鸡肉', 1, 2.3, '2016-06-14 18:53:36', 0);
INSERT INTO enttax.bill (BId, BType, BName, BMonth, BPrice, BUpdateTime, BMark) VALUES ('20160517201923123188', '进项数据', '花生油', 9, 6.5, '2016-06-14 18:53:36', 0);
INSERT INTO enttax.bill (BId, BType, BName, BMonth, BPrice, BUpdateTime, BMark) VALUES ('20160517201923123189', '进项数据', '鸡肉', 8, 0.8, '2016-06-14 18:53:36', 0);
INSERT INTO enttax.bill (BId, BType, BName, BMonth, BPrice, BUpdateTime, BMark) VALUES ('20160517201923123190', '进项数据', '鸡肉', 1, 2.3, '2016-06-14 18:53:36', 0);
INSERT INTO enttax.bill (BId, BType, BName, BMonth, BPrice, BUpdateTime, BMark) VALUES ('20160517201923123191', '进项数据', '花生油', 2, 6.5, '2016-06-14 18:53:36', 0);
INSERT INTO enttax.bill (BId, BType, BName, BMonth, BPrice, BUpdateTime, BMark) VALUES ('20160517201923123192', '进项数据', '鸡肉', 3, 0.8, '2016-06-14 18:53:36', 0);
INSERT INTO enttax.bill (BId, BType, BName, BMonth, BPrice, BUpdateTime, BMark) VALUES ('20160517201923123193', '进项数据', '鸡肉', 4, 2.3, '2016-06-14 18:53:36', 0);
INSERT INTO enttax.bill (BId, BType, BName, BMonth, BPrice, BUpdateTime, BMark) VALUES ('20160517201923123194', '进项数据', '花生油', 3, 6.5, '2016-06-14 18:53:36', 0);
INSERT INTO enttax.bill (BId, BType, BName, BMonth, BPrice, BUpdateTime, BMark) VALUES ('20160517201923123195', '进项数据', '鸡肉', 3, 0.8, '2016-06-14 18:53:36', 0);
INSERT INTO enttax.bill (BId, BType, BName, BMonth, BPrice, BUpdateTime, BMark) VALUES ('20160517201923123196', '进项数据', '鸡肉', 4, 2.3, '2016-06-14 18:53:36', 0);
INSERT INTO enttax.bill (BId, BType, BName, BMonth, BPrice, BUpdateTime, BMark) VALUES ('20160517201923123197', '进项数据', '花生油', 8, 6.5, '2016-06-14 18:53:36', 0);
INSERT INTO enttax.bill (BId, BType, BName, BMonth, BPrice, BUpdateTime, BMark) VALUES ('20160517201923123198', '进项数据', '鸡肉', 9, 0.8, '2016-06-14 18:53:36', 0);
INSERT INTO enttax.bill (BId, BType, BName, BMonth, BPrice, BUpdateTime, BMark) VALUES ('20160517201923123199', '进项数据', '鸡肉', 11, 2.3, '2016-06-14 18:53:36', 0);
INSERT INTO enttax.bill (BId, BType, BName, BMonth, BPrice, BUpdateTime, BMark) VALUES ('20160517201923123270', '销项数据', '铁皮', 1, 6.5, '2016-06-14 18:53:36', 1);
INSERT INTO enttax.bill (BId, BType, BName, BMonth, BPrice, BUpdateTime, BMark) VALUES ('20160517201923123271', '销项数据', '罐头', 3, 0.8, '2016-06-14 18:53:36', 1);
INSERT INTO enttax.bill (BId, BType, BName, BMonth, BPrice, BUpdateTime, BMark) VALUES ('20160517201923123272', '销项数据', '猪肉', 2, 2.3, '2016-06-14 18:53:36', 1);
INSERT INTO enttax.bill (BId, BType, BName, BMonth, BPrice, BUpdateTime, BMark) VALUES ('20160517201923123273', '销项数据', '铁皮', 4, 6.5, '2016-06-14 18:53:36', 1);
INSERT INTO enttax.bill (BId, BType, BName, BMonth, BPrice, BUpdateTime, BMark) VALUES ('20160517201923123274', '销项数据', '罐头', 8, 0.8, '2016-06-14 18:53:36', 1);
INSERT INTO enttax.bill (BId, BType, BName, BMonth, BPrice, BUpdateTime, BMark) VALUES ('20160517201923123275', '销项数据', '猪肉', 9, 2.3, '2016-06-14 18:53:36', 1);
INSERT INTO enttax.bill (BId, BType, BName, BMonth, BPrice, BUpdateTime, BMark) VALUES ('20160517201923123276', '销项数据', '铁皮', 3, 6.5, '2016-06-14 18:53:36', 1);
INSERT INTO enttax.bill (BId, BType, BName, BMonth, BPrice, BUpdateTime, BMark) VALUES ('20160517201923123277', '销项数据', '罐头', 2, 0.8, '2016-06-14 18:53:36', 1);
INSERT INTO enttax.bill (BId, BType, BName, BMonth, BPrice, BUpdateTime, BMark) VALUES ('20160517201923123278', '销项数据', '猪肉', 5, 2.3, '2016-06-14 18:53:36', 1);
INSERT INTO enttax.bill (BId, BType, BName, BMonth, BPrice, BUpdateTime, BMark) VALUES ('20160517201923123279', '销项数据', '铁皮', 11, 6.5, '2016-06-14 18:53:36', 1);
INSERT INTO enttax.bill (BId, BType, BName, BMonth, BPrice, BUpdateTime, BMark) VALUES ('20160517201923123280', '销项数据', '罐头', 5, 0.8, '2016-06-14 18:53:36', 1);
INSERT INTO enttax.bill (BId, BType, BName, BMonth, BPrice, BUpdateTime, BMark) VALUES ('20160517201923123281', '销项数据', '猪肉', 3, 2.3, '2016-06-14 18:53:36', 1);
INSERT INTO enttax.bill (BId, BType, BName, BMonth, BPrice, BUpdateTime, BMark) VALUES ('20160517201923123282', '销项数据', '铁皮', 4, 6.5, '2016-06-14 18:53:36', 1);
INSERT INTO enttax.bill (BId, BType, BName, BMonth, BPrice, BUpdateTime, BMark) VALUES ('20160517201923123283', '销项数据', '罐头', 12, 0.8, '2016-06-14 18:53:36', 1);
INSERT INTO enttax.bill (BId, BType, BName, BMonth, BPrice, BUpdateTime, BMark) VALUES ('20160517201923123284', '销项数据', '猪肉', 9, 2.3, '2016-06-14 18:53:36', 1);
INSERT INTO enttax.bill (BId, BType, BName, BMonth, BPrice, BUpdateTime, BMark) VALUES ('20160517201923123285', '销项数据', '铁皮', 6, 6.5, '2016-06-14 18:53:36', 1);
INSERT INTO enttax.bill (BId, BType, BName, BMonth, BPrice, BUpdateTime, BMark) VALUES ('20160517201923123286', '销项数据', '罐头', 6, 0.8, '2016-06-14 18:53:36', 1);
INSERT INTO enttax.bill (BId, BType, BName, BMonth, BPrice, BUpdateTime, BMark) VALUES ('20160517201923123287', '销项数据', '猪肉', 7, 2.3, '2016-06-14 18:53:36', 1);
INSERT INTO enttax.bill (BId, BType, BName, BMonth, BPrice, BUpdateTime, BMark) VALUES ('20170517201923123100', '进项数据', '花生油', 4, 6.5, '2017-06-12 20:29:12', 0);
INSERT INTO enttax.bill (BId, BType, BName, BMonth, BPrice, BUpdateTime, BMark) VALUES ('20170517201923123101', '进项数据', '鸡肉', 5, 0.8, '2017-06-12 20:29:12', 0);
INSERT INTO enttax.bill (BId, BType, BName, BMonth, BPrice, BUpdateTime, BMark) VALUES ('20170517201923123102', '进项数据', '鸡肉', 6, 2.3, '2017-06-12 20:29:12', 0);
INSERT INTO enttax.bill (BId, BType, BName, BMonth, BPrice, BUpdateTime, BMark) VALUES ('20170517201923123188', '进项数据', '花生油', 1, 6.5, '2017-06-12 20:29:12', 0);
INSERT INTO enttax.bill (BId, BType, BName, BMonth, BPrice, BUpdateTime, BMark) VALUES ('20170517201923123189', '进项数据', '鸡肉', 2, 0.8, '2017-06-12 20:29:12', 0);
INSERT INTO enttax.bill (BId, BType, BName, BMonth, BPrice, BUpdateTime, BMark) VALUES ('20170517201923123190', '进项数据', '鸡肉', 3, 2.3, '2017-06-12 20:29:12', 0);
INSERT INTO enttax.bill (BId, BType, BName, BMonth, BPrice, BUpdateTime, BMark) VALUES ('20170517201923123191', '进项数据', '花生油', 4, 6.5, '2017-06-12 20:29:12', 0);
INSERT INTO enttax.bill (BId, BType, BName, BMonth, BPrice, BUpdateTime, BMark) VALUES ('20170517201923123192', '进项数据', '鸡肉', 5, 0.8, '2017-06-12 20:29:12', 0);
INSERT INTO enttax.bill (BId, BType, BName, BMonth, BPrice, BUpdateTime, BMark) VALUES ('20170517201923123193', '进项数据', '鸡肉', 6, 2.3, '2017-06-12 20:29:12', 0);
INSERT INTO enttax.bill (BId, BType, BName, BMonth, BPrice, BUpdateTime, BMark) VALUES ('20170517201923123194', '进项数据', '花生油', 5, 6.5, '2017-06-12 20:29:12', 0);
INSERT INTO enttax.bill (BId, BType, BName, BMonth, BPrice, BUpdateTime, BMark) VALUES ('20170517201923123195', '进项数据', '鸡肉', 5, 0.8, '2017-06-12 20:29:12', 0);
INSERT INTO enttax.bill (BId, BType, BName, BMonth, BPrice, BUpdateTime, BMark) VALUES ('20170517201923123196', '进项数据', '鸡肉', 6, 2.3, '2017-06-12 20:29:12', 0);
INSERT INTO enttax.bill (BId, BType, BName, BMonth, BPrice, BUpdateTime, BMark) VALUES ('20170517201923123197', '进项数据', '花生油', 1, 6.5, '2017-06-12 20:29:12', 0);
INSERT INTO enttax.bill (BId, BType, BName, BMonth, BPrice, BUpdateTime, BMark) VALUES ('20170517201923123198', '进项数据', '鸡肉', 2, 0.8, '2017-06-12 20:29:12', 0);
INSERT INTO enttax.bill (BId, BType, BName, BMonth, BPrice, BUpdateTime, BMark) VALUES ('20170517201923123199', '进项数据', '鸡肉', 3, 2.3, '2017-06-12 20:29:12', 0);
INSERT INTO enttax.bill (BId, BType, BName, BMonth, BPrice, BUpdateTime, BMark) VALUES ('20170517201923123246', '销项数据', '铁皮', 1, 6.5, '2017-06-07 19:55:31', 0);
INSERT INTO enttax.bill (BId, BType, BName, BMonth, BPrice, BUpdateTime, BMark) VALUES ('20170517201923123247', '进项数据', '罐头', 2, 0.8, '2017-06-07 18:36:34', 1);
INSERT INTO enttax.bill (BId, BType, BName, BMonth, BPrice, BUpdateTime, BMark) VALUES ('20170517201923123248', '进项数据', '猪肉', 3, 2.3, '2017-06-07 18:36:34', 1);
INSERT INTO enttax.bill (BId, BType, BName, BMonth, BPrice, BUpdateTime, BMark) VALUES ('20170517201923123249', '进项数据', '铁皮', 1, 6.5, '2017-06-07 18:36:34', 1);
INSERT INTO enttax.bill (BId, BType, BName, BMonth, BPrice, BUpdateTime, BMark) VALUES ('20170517201923123250', '销项数据', '罐头', 2, 0.8, '2017-06-07 19:55:41', 0);
INSERT INTO enttax.bill (BId, BType, BName, BMonth, BPrice, BUpdateTime, BMark) VALUES ('20170517201923123251', '进项数据', '猪肉', 3, 2.3, '2017-06-07 18:36:34', 1);
INSERT INTO enttax.bill (BId, BType, BName, BMonth, BPrice, BUpdateTime, BMark) VALUES ('20170517201923123252', '销项数据', '铁皮', 1, 6.5, '2017-06-07 19:56:01', 0);
INSERT INTO enttax.bill (BId, BType, BName, BMonth, BPrice, BUpdateTime, BMark) VALUES ('20170517201923123253', '进项数据', '罐头', 2, 0.8, '2017-06-07 18:36:34', 1);
INSERT INTO enttax.bill (BId, BType, BName, BMonth, BPrice, BUpdateTime, BMark) VALUES ('20170517201923123254', '进项数据', '猪肉', 3, 2.3, '2017-06-07 18:36:34', 1);
INSERT INTO enttax.bill (BId, BType, BName, BMonth, BPrice, BUpdateTime, BMark) VALUES ('20170517201923123255', '进项数据', '铁皮', 1, 6.5, '2017-06-07 18:36:34', 1);
INSERT INTO enttax.bill (BId, BType, BName, BMonth, BPrice, BUpdateTime, BMark) VALUES ('20170517201923123256', '进项数据', '罐头', 2, 0.8, '2017-06-07 18:36:34', 1);
INSERT INTO enttax.bill (BId, BType, BName, BMonth, BPrice, BUpdateTime, BMark) VALUES ('20170517201923123257', '进项数据', '猪肉', 3, 2.3, '2017-06-07 18:36:34', 1);
INSERT INTO enttax.bill (BId, BType, BName, BMonth, BPrice, BUpdateTime, BMark) VALUES ('20170517201923123258', '进项数据', '铁皮', 1, 6.5, '2017-06-07 18:36:34', 1);
INSERT INTO enttax.bill (BId, BType, BName, BMonth, BPrice, BUpdateTime, BMark) VALUES ('20170517201923123259', '进项数据', '罐头', 2, 0.8, '2017-06-07 18:36:34', 1);
INSERT INTO enttax.bill (BId, BType, BName, BMonth, BPrice, BUpdateTime, BMark) VALUES ('20170517201923123260', '进项数据', '猪肉', 3, 2.3, '2017-06-07 18:36:34', 1);
INSERT INTO enttax.bill (BId, BType, BName, BMonth, BPrice, BUpdateTime, BMark) VALUES ('20170517201923123261', '进项数据', '铁皮', 1, 6.5, '2017-06-07 18:36:34', 1);
INSERT INTO enttax.bill (BId, BType, BName, BMonth, BPrice, BUpdateTime, BMark) VALUES ('20170517201923123262', '进项数据', '罐头', 2, 0.8, '2017-06-07 18:36:34', 1);
INSERT INTO enttax.bill (BId, BType, BName, BMonth, BPrice, BUpdateTime, BMark) VALUES ('20170517201923123263', '进项数据', '猪肉', 3, 2.3, '2017-06-07 18:36:34', 1);
INSERT INTO enttax.bill (BId, BType, BName, BMonth, BPrice, BUpdateTime, BMark) VALUES ('20170517201923123264', '进项数据', '铁皮', 1, 6.5, '2017-06-07 18:36:34', 1);
INSERT INTO enttax.bill (BId, BType, BName, BMonth, BPrice, BUpdateTime, BMark) VALUES ('20170517201923123265', '进项数据', '罐头', 2, 0.8, '2017-06-07 18:36:34', 1);
INSERT INTO enttax.bill (BId, BType, BName, BMonth, BPrice, BUpdateTime, BMark) VALUES ('20170517201923123266', '进项数据', '猪肉', 3, 2.3, '2017-06-07 18:36:34', 1);
INSERT INTO enttax.bill (BId, BType, BName, BMonth, BPrice, BUpdateTime, BMark) VALUES ('20170517201923123270', '销项数据', '铁皮', 5, 6.5, '2017-06-12 20:29:12', 1);
INSERT INTO enttax.bill (BId, BType, BName, BMonth, BPrice, BUpdateTime, BMark) VALUES ('20170517201923123271', '销项数据', '罐头', 5, 0.8, '2017-06-12 20:29:12', 1);
INSERT INTO enttax.bill (BId, BType, BName, BMonth, BPrice, BUpdateTime, BMark) VALUES ('20170517201923123272', '销项数据', '猪肉', 4, 2.3, '2017-06-12 20:29:12', 1);
INSERT INTO enttax.bill (BId, BType, BName, BMonth, BPrice, BUpdateTime, BMark) VALUES ('20170517201923123273', '销项数据', '铁皮', 6, 6.5, '2017-06-12 20:29:12', 1);
INSERT INTO enttax.bill (BId, BType, BName, BMonth, BPrice, BUpdateTime, BMark) VALUES ('20170517201923123274', '销项数据', '罐头', 6, 0.8, '2017-06-12 20:29:12', 1);
INSERT INTO enttax.bill (BId, BType, BName, BMonth, BPrice, BUpdateTime, BMark) VALUES ('20170517201923123275', '销项数据', '猪肉', 6, 2.3, '2017-06-12 20:29:12', 1);
INSERT INTO enttax.bill (BId, BType, BName, BMonth, BPrice, BUpdateTime, BMark) VALUES ('20170517201923123276', '销项数据', '铁皮', 5, 6.5, '2017-06-12 20:29:12', 1);
INSERT INTO enttax.bill (BId, BType, BName, BMonth, BPrice, BUpdateTime, BMark) VALUES ('20170517201923123277', '销项数据', '罐头', 4, 0.8, '2017-06-12 20:29:12', 1);
INSERT INTO enttax.bill (BId, BType, BName, BMonth, BPrice, BUpdateTime, BMark) VALUES ('20170517201923123278', '销项数据', '猪肉', 7, 2.3, '2017-06-12 20:29:12', 1);
INSERT INTO enttax.bill (BId, BType, BName, BMonth, BPrice, BUpdateTime, BMark) VALUES ('20170517201923123279', '销项数据', '铁皮', 7, 6.5, '2017-06-12 20:29:12', 1);
INSERT INTO enttax.bill (BId, BType, BName, BMonth, BPrice, BUpdateTime, BMark) VALUES ('20170517201923123280', '销项数据', '罐头', 7, 0.8, '2017-06-12 20:29:12', 1);
INSERT INTO enttax.bill (BId, BType, BName, BMonth, BPrice, BUpdateTime, BMark) VALUES ('20170517201923123281', '销项数据', '猪肉', 5, 2.3, '2017-06-12 20:29:12', 1);
INSERT INTO enttax.bill (BId, BType, BName, BMonth, BPrice, BUpdateTime, BMark) VALUES ('20170517201923123282', '销项数据', '铁皮', 6, 6.5, '2017-06-12 20:29:12', 1);
INSERT INTO enttax.bill (BId, BType, BName, BMonth, BPrice, BUpdateTime, BMark) VALUES ('20170517201923123283', '销项数据', '罐头', 8, 0.8, '2017-06-12 20:29:12', 1);
INSERT INTO enttax.bill (BId, BType, BName, BMonth, BPrice, BUpdateTime, BMark) VALUES ('20170517201923123284', '销项数据', '猪肉', 8, 2.3, '2017-06-12 20:29:12', 1);
INSERT INTO enttax.bill (BId, BType, BName, BMonth, BPrice, BUpdateTime, BMark) VALUES ('20170517201923123285', '销项数据', '铁皮', 8, 6.5, '2017-06-12 20:29:12', 1);
INSERT INTO enttax.bill (BId, BType, BName, BMonth, BPrice, BUpdateTime, BMark) VALUES ('20170517201923123286', '销项数据', '罐头', 8, 0.8, '2017-06-12 20:29:12', 1);
INSERT INTO enttax.bill (BId, BType, BName, BMonth, BPrice, BUpdateTime, BMark) VALUES ('20170517201923123287', '销项数据', '猪肉', 9, 2.3, '2017-06-12 20:29:12', 1);