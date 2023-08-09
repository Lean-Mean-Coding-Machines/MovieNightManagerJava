create table community
(
    community_id    serial  primary key,
    community_name  varchar(40)  not null,
    community_user_id  integer not null    references community_user (community_id)
);