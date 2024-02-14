create table movie_night_segment
(
    movie_night_segment_id serial primary key,
    nomination_start_date  DATE        not null,
    nomination_lock_date   DATE        not null,
    chosen_watch_date      timestamp   null,
    community_id           integer     not null references community (community_id),
    app_user_id            integer     not null references app_user (user_id)
);