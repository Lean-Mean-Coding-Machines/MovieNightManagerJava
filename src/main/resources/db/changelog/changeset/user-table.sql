create table app_user
(
    user_id    serial primary key,
    first_name varchar(50)        not null,
    last_name  varchar(50)        not null,
    username   varchar(50) unique not null,
    password   varchar(50)        not null,
    email      varchar(50) unique not null,
    role       varchar(20)        not null
);