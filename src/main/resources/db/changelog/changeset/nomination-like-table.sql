create table nomination_like
(
    nomination_like_id     serial primary key,
    enabled                bool        not null default false,
    prefer_watch_date_time timestamp   null,
    nomination_id          integer     not null references nomination (nomination_id),
    app_user_id            integer     not null references app_user (user_id)
);