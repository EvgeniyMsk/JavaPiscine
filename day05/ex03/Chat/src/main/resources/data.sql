insert into users (login, password) values ('user1', 'user1');
insert into users (login, password) values ('user2', 'user2');
insert into users (login, password) values ('user3', 'user3');
insert into users (login, password) values ('user4', 'user4');
insert into users (login, password) values ('user5', 'user5');

insert into chatrooms (name, owner) values ('room1', 1);
insert into chatrooms (name, owner) values ('room2', 2);
insert into chatrooms (name, owner) values ('room3', 3);
insert into chatrooms (name, owner) values ('room4', 4);
insert into chatrooms (name, owner) values ('room5', 5);

insert into messages (author, room, text) VALUES (1, 1, 'message1');
insert into messages (author, room, text) VALUES (2, 1, 'message2');
insert into messages (author, room, text) VALUES (2, 2, 'message3');
insert into messages (author, room, text) VALUES (3, 1, 'message4');
insert into messages (author, room, text) VALUES (3, 2, 'message5');
insert into messages (author, room, text) VALUES (3, 3, 'message6');
insert into messages (author, room, text) VALUES (4, 4, 'message7');
insert into messages (author, room, text) VALUES (4, 5, 'message8');
insert into messages (author, room, text) VALUES (5, 4, 'message9');
insert into messages (author, room, text) VALUES (5, 5, 'message10');