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
