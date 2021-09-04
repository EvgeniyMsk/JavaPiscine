CREATE TABLE IF NOT EXISTS Users
(
    id       SERIAL PRIMARY KEY,
    login    VARCHAR NOT NULL,
    password VARCHAR NOT NULL
);

CREATE TABLE IF NOT EXISTS Chatrooms
(
    id    SERIAL PRIMARY KEY,
    name  VARCHAR NOT NULL,
    owner INTEGER NOT NULL,
    foreign key (owner) REFERENCES Users (id)
);

CREATE TABLE IF NOT EXISTS Messages
(
    id     SERIAL PRIMARY KEY,
    author INTEGER,
    room   INTEGER,
    text   VARCHAR,
    time   TIMESTAMP default current_timestamp,
    foreign key (author) REFERENCES Users (id),
    foreign key (room) REFERENCES Chatrooms (id)
);