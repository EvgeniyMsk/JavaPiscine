drop table if exists PRODUCT cascade;
create table if not exists PRODUCT
(
    IDENTIFIER INTEGER identity primary key,
    NAME VARCHAR(10) not null unique,
    PRICE INTEGER not null
);
