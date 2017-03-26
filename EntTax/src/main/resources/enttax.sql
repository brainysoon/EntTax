
SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table bill
-- ----------------------------
DROP TABLE IF EXISTS `bill`;

CREATE TABLE `bill` (
  `BId` char(30) NOT NULL,
  `BType` varchar(10) NOT NULL,
  `BNum` int(11) NOT NULL,
  `BPrice` double NOT NULL,
  `BTax` double NOT NULL,
  PRIMARY KEY (`BId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table log
-- ----------------------------
DROP TABLE IF EXISTS `log`;

CREATE TABLE `log` (
  `LId` char(20) NOT NULL,
  `SId` char(8) NOT NULL,
  `BId` char(30) NOT NULL,
  `LTime` datetime NOT NULL,
  `LMessage` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`LId`),
  KEY `SId` (`SId`),
  KEY `BId` (`BId`),
  CONSTRAINT `log_ibfk_1` FOREIGN KEY (`SId`) REFERENCES `staff` (`SId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `log_ibfk_2` FOREIGN KEY (`BId`) REFERENCES `bill` (`BId`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table permission
-- ----------------------------
DROP TABLE IF EXISTS `permission`;

CREATE TABLE `permission` (
  `PId` char(4) NOT NULL,
  `PName` varchar(10) NOT NULL,
  `PType` int(11) NOT NULL,
  PRIMARY KEY (`PId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table perm_role
-- ----------------------------
DROP TABLE IF EXISTS `perm_role`;

CREATE TABLE `perm_role` (
  `PId` char(4) NOT NULL,
  `RId` char(4) NOT NULL,
  PRIMARY KEY (`RId`),
  KEY `PId` (`PId`),
  CONSTRAINT `perm_role_ibfk_1` FOREIGN KEY (`PId`) REFERENCES `permission` (`PId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `perm_role_ibfk_2` FOREIGN KEY (`RId`) REFERENCES `role` (`RId`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table role
-- ----------------------------
DROP TABLE IF EXISTS `role`;

CREATE TABLE `role` (
  `RId` char(4) NOT NULL,
  `RName` varchar(10) NOT NULL,
  `RUpdateTime` datetime DEFAULT NULL,
  `RMark` smallint(6) DEFAULT NULL,
  PRIMARY KEY (`RId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table staff
-- ----------------------------
DROP TABLE IF EXISTS `staff`;

CREATE TABLE `staff` (
  `SId` char(8) NOT NULL,
  `SName` varchar(10) NOT NULL,
  `SPassword` varchar(16) NOT NULL,
  `SEmail` varchar(20) DEFAULT NULL,
  `SPhone` char(11) DEFAULT NULL,
  `SSex` tinyint(1) DEFAULT NULL,
  `SBirthday` date DEFAULT NULL,
  `SEnter` datetime DEFAULT NULL,
  `SMark` smallint(6) DEFAULT NULL,
  `SAddress` varchar(30) DEFAULT NULL,
  `SAvator` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`SId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table staff_role
-- ----------------------------
DROP TABLE IF EXISTS `staff_role`;

CREATE TABLE `staff_role` (
  `SId` char(8) NOT NULL,
  `RId` char(4) NOT NULL,
  KEY `SId` (`SId`) USING BTREE,
  KEY `RId` (`RId`),
  CONSTRAINT `staff_role_ibfk_2` FOREIGN KEY (`RId`) REFERENCES `role` (`RId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `staff_role_ibfk_1` FOREIGN KEY (`SId`) REFERENCES `staff` (`SId`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
