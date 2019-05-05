drop table if exists `role`;

create table `role`
(
    id int not null primary key
    unique auto_increment,
    name varchar(50) not null unique
);

insert into `role` values (default, 'admin');
insert into `role` values (default, 'user');