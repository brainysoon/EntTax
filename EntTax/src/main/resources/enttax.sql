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
  `BUpdateTime` DATETIME    NOT NULL,
  `BMark`       SMALLINT DEFAULT NULL,
  PRIMARY KEY (`BId`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

-- ----------------------------
-- Table log
-- ----------------------------
DROP TABLE IF EXISTS `log`;

CREATE TABLE `log` (
  `LId`      CHAR(20) NOT NULL,
  `SId`      CHAR(8)  NOT NULL,
  `BId`      CHAR(30) NOT NULL,
  `LTime`    DATETIME NOT NULL,
  `LMessage` VARCHAR(255) DEFAULT NULL,
  PRIMARY KEY (`LId`),
  KEY `SId` (`SId`),
  KEY `BId` (`BId`),
  CONSTRAINT `log_ibfk_1` FOREIGN KEY (`SId`) REFERENCES `staff` (`SId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `log_ibfk_2` FOREIGN KEY (`BId`) REFERENCES `bill` (`BId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
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