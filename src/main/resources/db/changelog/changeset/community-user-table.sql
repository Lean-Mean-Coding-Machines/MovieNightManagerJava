create table community_user
(
    community_id   integer     not null references community (community_id),
    user_id        integer     not null references app_user (user_id),
    community_role varchar(20) not null,
    PRIMARY KEY (community_id, user_id)
);