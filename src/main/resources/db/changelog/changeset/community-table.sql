create table community
(
    community_id serial primary key,
    name         varchar(50) not null,
    timezone     varchar(50) not null,
    active       boolean     not null,
    created_on   DATE        not null,
    modified_on  DATE        not null,
    app_user_id  integer     not null references app_user (user_id)
);