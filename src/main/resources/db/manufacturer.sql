drop table if exists `manufacturer`;

create table `manufacturer`
(
    id int not null unique
    primary key auto_increment,
    name varchar(50) not null
);

insert into `manufacturer` values (default , 'manufacturer1');
insert into `manufacturer` values (default , 'manufacturer2');
insert into `manufacturer` values (default , 'manufacturer3');
insert into `manufacturer` values (default , 'manufacturer4');
insert into `manufacturer` values (default , 'manufacturer5');
insert into `manufacturer` values (default , 'manufacturer6');
insert into `manufacturer` values (default , 'manufacturer7');
insert into `manufacturer` values (default , 'manufacturer8');
insert into `manufacturer` values (default , 'manufacturer9');
insert into `manufacturer` values (default , 'manufacturer10');
insert into `manufacturer` values (default , 'manufacturer11');

