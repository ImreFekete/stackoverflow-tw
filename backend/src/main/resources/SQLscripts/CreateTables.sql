create table if not exists questions
(
    question_id serial primary key,
    question_title varchar   not null,
    question_text varchar   not null,
    created_at timestamp not null
);

create table if not exists answers
(
    answer_id   serial primary key,
    answer_text varchar   not null,
    created_at  timestamp not null,
    question_id int       not null,
    foreign key (question_id)
        references questions (question_id)
);
create table if not exists users
(
    user_id       serial primary key,
    user_username varchar   not null,
    user_password varchar   not null,
    created_at    timestamp not null
);