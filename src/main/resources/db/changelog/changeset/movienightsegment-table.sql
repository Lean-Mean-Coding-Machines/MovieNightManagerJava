create table movie_night_segment
(
    movie_night_segment_id serial      primary key,
    nomination_start_date  DATE        not null,
    nomination_lock_date   DATE        not null,
    chosen_watch_date      DATE        not null,
    watch_type             varchar(20) null,
    segment_end_date       DATE        not null
);