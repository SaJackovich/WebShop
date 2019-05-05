drop table if exists `category`;

create table `category`
(
    id int not null unique
    primary key auto_increment,
    name varchar(50) not null
);

insert into `category` values (default, 'category1');
insert into `category` values (default, 'category2');
insert into `category` values (default, 'category3');
insert into `category` values (default, 'category4');
insert into `category` values (default, 'category5');
insert into `category` values (default, 'category6');
insert into `category` values (default, 'category7');
insert into `category` values (default, 'category8');
insert into `category` values (default, 'category9');
insert into `category` values (default, 'category10');
insert into `category` values (default, 'category11');