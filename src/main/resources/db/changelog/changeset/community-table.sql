create table community
(
    community_id serial primary key,
    name         varchar(50) not null,
    timezone     varchar(50) not null,
    created_on   DATE        not null,
    created_by   varchar(50) not null
);