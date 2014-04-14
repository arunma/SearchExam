SELECT * FROM evaluation.USER_RATINGS;

drop index UserRatingsIndex on evaluation.USER_RATINGS;




CREATE TABLE `USER_RATINGS` (
  `ID` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `USERNAME` varchar(50) NOT NULL DEFAULT '',
  `URL` varchar(200) NOT NULL DEFAULT '',
  `COMMENTS` varchar(300) DEFAULT NULL,
  `RATING` varchar(50) NOT NULL DEFAULT '',
  `QUERY` varchar(200) NOT NULL DEFAULT '',
  PRIMARY KEY (`ID`),
  KEY `UserRatingsIndex` (`USERNAME`,`URL`,`QUERY`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=latin1;


CREATE TABLE `USERS` (
  `ID` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `USERNAME` varchar(50) NOT NULL DEFAULT '',
  `PASSWORD` varchar(300) NOT NULL DEFAULT '',
  `EMAIL` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;





explain select * from evaluation.USER_RATINGS where USERNAME='dummyuser' and 
and QUERY='omega' and 
URL in ('http://www.patientslikeme.com/all/patients/treatment/17786-triple-omega-3-side-effects-and-efficacy?brand=t',
'http://www.fda.gov/ICECI/EnforcementActions/WarningLetters/2005/ucm075535.htm')
 
