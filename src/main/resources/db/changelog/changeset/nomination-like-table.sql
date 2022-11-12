create table nomination_like
(
    nomination_like_id serial primary key,
    chosen             bool        not null default false,
    prefer_watch_type  varchar(20) null,
    prefer_watch_date  date        null,
    nomination_id      integer     not null references nomination (nomination_id),
    app_user_id        integer     not null references app_user (user_id)
);