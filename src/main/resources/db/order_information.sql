drop table if exists `order_information`;

CREATE TABLE `order_information` (
	`id` INT(9) PRIMARY KEY AUTO_INCREMENT,
	`id_order` INT(9) NOT NULL,
	`id_product` INT(9) NOT NULL,
	`quantity_product` INT(9) NOT NULL,
	`price` DECIMAL(30) NOT NULL
);