create table PRODUCT
(
    IDENTIFIER INTEGER identity primary key,
    NAME VARCHAR(10) not null unique,
    PRICE INTEGER not null
);
