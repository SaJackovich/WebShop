drop table if exists `order`;

CREATE TABLE `order` (
	id INT(9) PRIMARY KEY,
	`status` int NOT NULL,
	`paymentKind` varchar(100) NOT NULL,
	`date` Date NOT NULL,
	`delivery` varchar(20) not null,
	`bankCard` long not null,
	id_user INT(255) NOT NULL
);