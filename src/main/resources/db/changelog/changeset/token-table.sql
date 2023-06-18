create table token
(
    token_id    serial primary key,
    token       varchar(256)       not null,
    token_type  varchar(25)        not null,
    revoked     varchar(50) unique not null,
    expired     varchar(256)       not null,
    app_user_id integer            not null references app_user (user_id)
);