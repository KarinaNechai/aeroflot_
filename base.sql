CREATE TABLE `airport` (
  `airportid` int NOT NULL AUTO_INCREMENT,
  `airportname` varchar(255) NOT NULL COMMENT 'наименование аэропорта',
  `actfl` int NOT NULL DEFAULT '1',
  PRIMARY KEY (`airportid`),
  UNIQUE KEY `airportd` (`airportid`)
) ENGINE=InnoDB AUTO_INCREMENT=42 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
CREATE TABLE `plane` (
  `planeid` int NOT NULL AUTO_INCREMENT,
  `planename` varchar(255) NOT NULL COMMENT 'Наименование самолета',
  `capacity` int NOT NULL DEFAULT '0' COMMENT 'Вместимость самолета',
  `length` int NOT NULL DEFAULT '0',
  `actfl` int NOT NULL DEFAULT '1',
  PRIMARY KEY (`planeid`),
  UNIQUE KEY `planeid` (`planeid`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
CREATE TABLE `worker` (
  `workerid` int NOT NULL AUTO_INCREMENT,
  `workersurname` varchar(128) NOT NULL COMMENT 'Код классификатора',
  `workerfirstname` varchar(128) NOT NULL,
  `actfl` int NOT NULL DEFAULT '1' COMMENT 'Флаг актуальности',
  `workerpatronomic` varchar(45) DEFAULT NULL COMMENT 'Отчество',
  `workerprofession` int NOT NULL COMMENT 'Профессия, ссылка на классификатор',
  PRIMARY KEY (`workerid`),
  UNIQUE KEY `workerid` (`workerid`),
  KEY `FK1_idx` (`workerprofession`),
  CONSTRAINT `FK1` FOREIGN KEY (`workerprofession`) REFERENCES `classifier` (`clasid`)
) ENGINE=InnoDB AUTO_INCREMENT=69 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
CREATE TABLE `user` (
  `userid` int NOT NULL AUTO_INCREMENT,
  `userlogin` varchar(30) NOT NULL COMMENT 'учетная запись пользователя (логин)',
  `email` varchar(255) DEFAULT NULL COMMENT 'электронная почта пользователя',
  `userpassword` varchar(32) NOT NULL COMMENT 'пароль пользователя',
  `usersurname` varchar(128) NOT NULL COMMENT 'Фамилия пользователя',
  `userfirstname` varchar(128) NOT NULL COMMENT 'Имя пользователя',
  `userphone` varchar(32) DEFAULT NULL COMMENT 'Номер телефона пользователя',
  `actfl` int NOT NULL DEFAULT '1' COMMENT 'Признак актуальности',
  `modifydate` datetime DEFAULT NULL COMMENT 'Дата модификации',
  `userpatronomic` varchar(45) DEFAULT NULL COMMENT 'роль пользователя в системе',
  `userrole` int DEFAULT NULL,
  PRIMARY KEY (`userid`),
  UNIQUE KEY `userid` (`userid`),
  UNIQUE KEY `userid_2` (`userid`),
  UNIQUE KEY `U1_user` (`userlogin`),
  KEY `FK1_idx` (`userrole`),
  CONSTRAINT `FK_user_1` FOREIGN KEY (`userrole`) REFERENCES `classifier` (`clasid`) ON UPDATE SET NULL
) ENGINE=InnoDB AUTO_INCREMENT=174 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
CREATE TABLE `flight` (
  `flightid` int NOT NULL AUTO_INCREMENT,
  `airportfrom` int DEFAULT NULL COMMENT 'Пункт вылета',
  `airportto` int DEFAULT NULL COMMENT 'пункт назначения',
  `crewid` int DEFAULT NULL COMMENT 'идентификатор экипажа',
  `planeid` int DEFAULT NULL COMMENT 'Идентификатор самолета',
  `flightrange` int NOT NULL DEFAULT '0' COMMENT 'Дальность рейса',
  `amountpassengers` int NOT NULL DEFAULT '0',
  `flightdate` datetime NOT NULL COMMENT 'Дата и время рейса',
  `actfl` int NOT NULL DEFAULT '1',
  PRIMARY KEY (`flightid`),
  UNIQUE KEY `flightid` (`flightid`),
  KEY `FK1_idx` (`airportfrom`),
  KEY `FK_idx` (`airportto`),
  KEY `FK5_idx` (`planeid`),
  CONSTRAINT `FK_flight_1` FOREIGN KEY (`airportfrom`) REFERENCES `airport` (`airportid`),
  CONSTRAINT `FK_flight_2` FOREIGN KEY (`airportto`) REFERENCES `airport` (`airportid`),
  CONSTRAINT `FK_flight_4` FOREIGN KEY (`planeid`) REFERENCES `plane` (`planeid`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
CREATE TABLE `crew` (
  `actfl` int NOT NULL DEFAULT '1' COMMENT 'Флаг актуальности',
  `flightid` int NOT NULL,
  `workerid` int NOT NULL COMMENT 'Идентификатор члена экипажа ',
  PRIMARY KEY (`flightid`,`workerid`),
  KEY `FK_crew_1` (`workerid`),
  CONSTRAINT `FK_crew` FOREIGN KEY (`flightid`) REFERENCES `flight` (`flightid`),
  CONSTRAINT `FK_crew_1` FOREIGN KEY (`workerid`) REFERENCES `worker` (`workerid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
CREATE TABLE `flightstatus` (
  `flightstatusid` int NOT NULL AUTO_INCREMENT,
  `modifydate` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'Время изменения статуса рейса',
  `flightstatus` int NOT NULL COMMENT 'Статус рейса, ссылка на классификатор',
  `flightdesc` varchar(255) DEFAULT NULL COMMENT 'Описание события',
  `flightid` int NOT NULL COMMENT 'Идентификатор рейса',
  PRIMARY KEY (`flightstatusid`),
  UNIQUE KEY `flightstatusid` (`flightstatusid`),
  KEY `FK2_idx` (`flightid`),
  CONSTRAINT `FK_flightstatus_1` FOREIGN KEY (`flightstatusid`) REFERENCES `classifier` (`clasid`),
  CONSTRAINT `FK_flightstatus_2` FOREIGN KEY (`flightid`) REFERENCES `flight` (`flightid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
CREATE TABLE `classifier` (
  `clasid` int NOT NULL AUTO_INCREMENT COMMENT 'Идентификатор классификатора',
  `clascode` varchar(255) NOT NULL COMMENT 'Код классификатора',
  `clasval` varchar(45) NOT NULL COMMENT 'Значение классификатора',
  `actfl` int NOT NULL DEFAULT '1' COMMENT 'Флаг актуальности',
  `clasdesc` varchar(45) DEFAULT NULL COMMENT 'Описание поля',
  PRIMARY KEY (`clasid`),
  UNIQUE KEY `U1_user` (`clascode`,`clasval`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
INSERT INTO `myapp`.`classifier` (`clasid`, `clascode`, `clasval`, `actfl`, `clasdesc`) VALUES ('1', 'Role', 'ADMIN', '1', 'Администратор системы');
INSERT INTO `myapp`.`classifier` (`clasid`, `clascode`, `clasval`, `actfl`, `clasdesc`) VALUES ('2', 'Role', 'DISPATCHER', '1', 'Диспетчер');
INSERT INTO `myapp`.`classifier` (`clasid`, `clascode`, `clasval`, `actfl`, `clasdesc`) VALUES ('3', 'Profession', 'STEWARDESS', '1', 'Стюардесса');
INSERT INTO `myapp`.`classifier` (`clasid`, `clascode`, `clasval`, `actfl`, `clasdesc`) VALUES ('4', 'Profession', 'NAVIGATOR', '1', 'Штурман');
INSERT INTO `myapp`.`classifier` (`clasid`, `clascode`, `clasval`, `actfl`, `clasdesc`) VALUES ('5', 'Profession', 'PILOT', '1', 'Пилот');
INSERT INTO `myapp`.`classifier` (`clasid`, `clascode`, `clasval`, `actfl`, `clasdesc`) VALUES ('6', 'Profession', 'RADIOMAN', '1', 'Радист');
INSERT INTO `myapp`.`classifier` (`clasid`, `clascode`, `clasval`, `actfl`, `clasdesc`) VALUES ('7', 'flightstatus', 'new', '1', 'Новый');
INSERT INTO `myapp`.`classifier` (`clasid`, `clascode`, `clasval`, `actfl`, `clasdesc`) VALUES ('8', 'flightstatus', 'cancelled', '1', 'Отмененный');
INSERT INTO `myapp`.`classifier` (`clasid`, `clascode`, `clasval`, `actfl`, `clasdesc`) VALUES ('9', 'Role', 'USER', '1', 'Пассажир');
INSERT INTO `myapp`.`classifier` (`clasid`, `clascode`, `clasval`, `actfl`, `clasdesc`) VALUES ('10', 'reason', 'technicalfailure', '1', 'Техническая неисправность');
INSERT INTO `myapp`.`classifier` (`clasid`, `clascode`, `clasval`, `actfl`, `clasdesc`) VALUES ('11', 'reason', 'weither', '1', 'Погода');
INSERT INTO `myapp`.`USER` (`userid`, `userlogin`, `userpassword`, `usersurname`, `userfirstname`, `userphone`, `actfl`, `userrole`) VALUES ('78', 'dis1', '123', 'Иванова', 'Светлана', 'Петровна', '1', '2');
INSERT INTO `myapp`.`USER` (`userid`, `userlogin`, `email`, `userpassword`, `usersurname`, `userfirstname`, `userphone`, `actfl`, `userpatronomic`, `userrole`) VALUES ('13', 'admin', 'test@test.by', 'admin', 'Глушков', 'Иван', '+375297075019', '1', 'Иванович', '1');

