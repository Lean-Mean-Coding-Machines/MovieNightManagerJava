create table nomination
(
    nomination_id          serial primary key,
    movie_id               integer        not null,
    movie_title            varchar(256)   not null,
    chosen                 bool          not null default false,
    poster_path            varchar(256)  null,
    movie_overview         varchar(1000) null,
    release_date           varchar(25)   null,
    movie_night_segment_id integer       not null references movie_night_segment (movie_night_segment_id),
    genres                 varchar(256)  null,
    runtime                integer       null,
    app_user_id            integer       not null references app_user (user_id)
);