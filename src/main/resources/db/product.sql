drop table if exists `product`;

create table `product`
(
    id int not null unique
    primary key auto_increment,
    name varchar(50) not null,
    image varchar(50) not null,
    description varchar(50) not null,
    price decimal not null,
    quantity int not null,
    id_manufacturer int not null,
    id_category int not null
);

insert into `product` values (default, 'Stupid_Shark1', '/images/main/a.jpg', 'description1', 123.43, 2323, 1, 1);
insert into `product` values (default, 'Stupid shark2', '/images/main/a.jpg', 'description1', 123.43, 2323, 2, 2);
insert into `product` values (default, 'Stupid shark3', '/images/main/a.jpg', 'description1', 123.43, 2323, 3, 3);
insert into `product` values (default, 'Stupid shark4', '/images/main/a.jpg', 'description1', 123.43, 2323, 4, 4);
insert into `product` values (default, 'Stupid shark5', '/images/main/a.jpg', 'description1', 123.43, 2323, 1, 1);
insert into `product` values (default, 'Stupid shark6', '/images/main/a.jpg', 'description1', 123.43, 2323, 2, 2);
insert into `product` values (default, 'Stupid shark7', '/images/main/a.jpg', 'description1', 123.43, 2323, 3, 3);
insert into `product` values (default, 'Stupid shark8', '/images/main/a.jpg', 'description1', 123.43, 2323, 4, 4);
insert into `product` values (default, 'Stupid shark9', '/images/main/a.jpg', 'description1', 123.43, 2323, 1, 1);
insert into `product` values (default, 'Stupid shark10', '/images/main/a.jpg', 'description1', 123.43, 2323, 2, 2);
insert into `product` values (default, 'Stupid shark11', '/images/main/a.jpg', 'description1', 123.43, 2323, 3, 3);
insert into `product` values (default, 'Stupid shark12', '/images/main/a.jpg', 'description1', 123.43, 2323, 4, 4);
insert into `product` values (default, 'Stupid shark13', '/images/main/a.jpg', 'description1', 123.43, 2323, 1, 1);
insert into `product` values (default, 'Stupid shark14', '/images/main/a.jpg', 'description1', 123.43, 2323, 2, 2);
insert into `product` values (default, 'Stupid shark15', '/images/main/a.jpg', 'description1', 123.43, 2323, 3, 3);
insert into `product` values (default, 'Stupid shark16', '/images/main/a.jpg', 'description1', 123.43, 2323, 4, 4);
insert into `product` values (default, 'Stupid shark17', '/images/main/a.jpg', 'description1', 123.43, 2323, 1, 1);
insert into `product` values (default, 'Stupid shark18', '/images/main/a.jpg', 'description1', 123.43, 2323, 2, 2);
insert into `product` values (default, 'Stupid shark19', '/images/main/a.jpg', 'description1', 123.43, 2323, 3, 3);
insert into `product` values (default, 'Stupid shark20', '/images/main/a.jpg', 'description1', 123.43, 2323, 4, 4);
insert into `product` values (default, 'Stupid shark21', '/images/main/a.jpg', 'description1', 123.43, 2323, 1, 1);
insert into `product` values (default, 'Stupid shark22', '/images/main/a.jpg', 'description1', 123.43, 2323, 2, 2);
insert into `product` values (default, 'Stupid shark23', '/images/main/a.jpg', 'description1', 123.43, 2323, 3, 3);
insert into `product` values (default, 'Stupid shark24', '/images/main/a.jpg', 'description1', 123.43, 2323, 4, 4);
insert into `product` values (default, 'Stupid shark25', '/images/main/a.jpg', 'description1', 123.43, 2323, 1, 1);
insert into `product` values (default, 'Stupid shark26', '/images/main/a.jpg', 'description1', 123.43, 2323, 2, 2);
insert into `product` values (default, 'Stupid shark27', '/images/main/a.jpg', 'description1', 123.43, 2323, 3, 3);
insert into `product` values (default, 'Stupid shark28', '/images/main/a.jpg', 'description1', 123.43, 2323, 4, 4);
insert into `product` values (default, 'Stupid shark29', '/images/main/a.jpg', 'description1', 123.43, 2323, 1, 1);
insert into `product` values (default, 'Stupid shark30', '/images/main/a.jpg', 'description1', 123.43, 2323, 2, 2);
insert into `product` values (default, 'Stupid shark31', '/images/main/a.jpg', 'description1', 123.43, 2323, 3, 3);
insert into `product` values (default, 'Stupid shark32', '/images/main/a.jpg', 'description1', 123.43, 2323, 4, 4);
insert into `product` values (default, 'Stupid shark33', '/images/main/a.jpg', 'description1', 123.43, 2323, 1, 1);
insert into `product` values (default, 'Stupid shark34', '/images/main/a.jpg', 'description1', 123.43, 2323, 2, 2);
insert into `product` values (default, 'Stupid shark35', '/images/main/a.jpg', 'description1', 123.43, 2323, 3, 3);
insert into `product` values (default, 'Stupid shark36', '/images/main/a.jpg', 'description1', 123.43, 2323, 4, 4);
insert into `product` values (default, 'Stupid shark37', '/images/main/a.jpg', 'description1', 123.43, 2323, 1, 1);
insert into `product` values (default, 'Stupid shark38', '/images/main/a.jpg', 'description1', 123.43, 2323, 1, 1);
