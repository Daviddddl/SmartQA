

CREATE TABLE `outline` (
  `id` int(8) unsigned NOT NULL AUTO_INCREMENT,
  `courseid` bigint(20) NOT NULL,
  `chapters` int(8) DEFAULT '0',
  `content` text,
  `uknown` int(8) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `fk_outline` (`courseid`),
  CONSTRAINT `fk_outline` FOREIGN KEY (`courseid`) REFERENCES `course` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;


CREATE TABLE `course` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(64) NOT NULL DEFAULT '',
  `password` varchar(64) NOT NULL DEFAULT '',
  `teacher` bigint(20) unsigned NOT NULL,
  `capacity` int(8) DEFAULT '2000',
  `stunum` int(16) DEFAULT '0',
  `startdate` datetime DEFAULT '2018-01-01 00:00:00' ON UPDATE CURRENT_TIMESTAMP,
  `enddate` datetime DEFAULT '2099-12-31 00:00:00' ON UPDATE CURRENT_TIMESTAMP,
  `isactive` int(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `coursename` (`name`),
  KEY `teacher` (`teacher`),
  CONSTRAINT `course_ibfk_1` FOREIGN KEY (`teacher`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;


CREATE TABLE `question` (
  `id` int(8) unsigned NOT NULL AUTO_INCREMENT,
  `courseid` bigint(20) NOT NULL,
  `chapters` int(8) DEFAULT '0',
  `ques` text,
  `ans` text,
  PRIMARY KEY (`id`),
  KEY `fk_ques` (`courseid`),
  CONSTRAINT `fk_ques` FOREIGN KEY (`courseid`) REFERENCES `course` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE `user` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `nickname` varchar(64) NOT NULL DEFAULT '',
  `remark` varchar(64) NOT NULL DEFAULT '',
  `gender` int(1) DEFAULT '1',
  `lang` varchar(64) DEFAULT '',
  `city` varchar(64) DEFAULT '',
  `province` varchar(64) DEFAULT '',
  `country` varchar(64) DEFAULT '',
  `avatarUrl` varchar(128) DEFAULT '',
  `joinCourse` varchar(255) DEFAULT '',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;