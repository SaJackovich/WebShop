drop table if exists `users`;

create table `users`
(
    id int not null primary key
    unique auto_increment,
    email varchar(30) not null,
    first_name varchar(50) not null,
    last_name varchar(50) not null,
    login varchar(50) not null unique,
    password varchar(50) not null,
    avatar varchar(200) not null,
    id_role int not null
);

insert into `users` values (default, 'phenomenon13@mail.ua', 'Vlad', 'Perevertailo', 'SaJackovich', 'Fkzcrffkzcrf98', 0, 1);

