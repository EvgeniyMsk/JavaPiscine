DROP SCHEMA IF EXISTS chat CASCADE;

CREATE SCHEMA chat;

CREATE TABLE chat.jc_user
(
    user_id serial NOT NULL,
    login character varying NOT NULL,
    pass character varying NOT NULL,
    PRIMARY KEY (user_id)
);

CREATE TABLE chat.jc_chatroom
(
    chatroom_id serial NOT NULL,
    chatroom_name character varying NOT NULL UNIQUE,
    chatroom_owner serial NOT NULL,
    PRIMARY KEY (chatroom_id),
    FOREIGN KEY (chatroom_owner)
        REFERENCES chat.jc_user (user_id) MATCH SIMPLE
        ON UPDATE CASCADE
        ON DELETE CASCADE
);

CREATE TABLE chat.jc_message
(
    message_id serial NOT NULL,
    message_author serial NOT NULL,
    message_room serial NOT NULL,
    message_text text,
    message_timestamp timestamp with time zone NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (message_id),
    CONSTRAINT fc_author FOREIGN KEY (message_author)
        REFERENCES chat.jc_user (user_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT fc_room FOREIGN KEY (message_room)
        REFERENCES chat.jc_chatroom (chatroom_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);

CREATE TABLE chat.js_chats(
    chats_id serial,
    user_id serial NOT NULL,
    room_id serial NOT NULL,
    PRIMARY KEY (chats_id),
    FOREIGN KEY (user_id) REFERENCES chat.jc_user(user_id),
    FOREIGN KEY (room_id) REFERENCES chat.jc_chatroom(chatroom_id)
);