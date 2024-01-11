create table feedback
(
    feedback_id     serial          primary key,
    user_id         integer         not null references app_user (user_id),
    submit_date     DATE            not null,
    feedback_type   varchar(60)     not null,
    content         varchar(500)    not null
);