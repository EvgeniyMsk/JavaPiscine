drop table if exists user cascade;
create table if not exists user
(
    id INTEGER identity primary key,
    email VARCHAR(40) not null unique,
    password VARCHAR(40) not null unique
);
