drop table if exists "user";
create table "user"
(
    id serial not null primary key,
    name varchar not null,
    password varchar not null
);
drop table if exists "messages";
create table messages
(
    message   varchar
);