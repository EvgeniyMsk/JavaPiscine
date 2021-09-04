insert into chat.jc_user(login, pass) VALUES	('user1','user1');
insert into chat.jc_user(login, pass) VALUES	('user2','user2');
insert into chat.jc_user(login, pass) VALUES	('user3','user3');
insert into chat.jc_user(login, pass) VALUES	('user4','user4');
insert into chat.jc_user(login, pass) VALUES	('user5','user5');

INSERT INTO chat.jc_chatroom(chatroom_name, chatroom_owner) VALUES
('Chat1', (SELECT user_id FROM chat.jc_user WHERE login = 'user1')),
('Chat2', (SELECT user_id FROM chat.jc_user WHERE login = 'user2')),
('Chat3', (SELECT user_id FROM chat.jc_user WHERE login = 'user3')),
('Chat4', (SELECT user_id FROM chat.jc_user WHERE login = 'user4')),
('Chat5', (SELECT user_id FROM chat.jc_user WHERE login = 'user5'));

INSERT INTO chat.jc_message (message_author, message_room, message_text) VALUES
((SELECT user_id FROM chat.jc_user WHERE login = 'user1'), (SELECT chatroom_id FROM chat.jc_chatroom WHERE chatroom_name = 'Chat1'), 'Message1'),
((SELECT user_id FROM chat.jc_user WHERE login = 'user2'), (SELECT chatroom_id FROM chat.jc_chatroom WHERE chatroom_name = 'Chat2'), 'Message2'),
((SELECT user_id FROM chat.jc_user WHERE login = 'user3'), (SELECT chatroom_id FROM chat.jc_chatroom WHERE chatroom_name = 'Chat3'), 'Message3'),
((SELECT user_id FROM chat.jc_user WHERE login = 'user4'), (SELECT chatroom_id FROM chat.jc_chatroom WHERE chatroom_name = 'Chat4'), 'Message4'),
((SELECT user_id FROM chat.jc_user WHERE login = 'user5'), (SELECT chatroom_id FROM chat.jc_chatroom WHERE chatroom_name = 'Chat5'), 'Message5');

INSERT INTO chat.js_chats (user_id, room_id) VALUES
((SELECT user_id FROM chat.jc_user WHERE login = 'user1'), (SELECT chatroom_id FROM chat.jc_chatroom WHERE chatroom_name = 'Chat1')),
((SELECT user_id FROM chat.jc_user WHERE login = 'user2'), (SELECT chatroom_id FROM chat.jc_chatroom WHERE chatroom_name = 'Chat2')),
((SELECT user_id FROM chat.jc_user WHERE login = 'user3'), (SELECT chatroom_id FROM chat.jc_chatroom WHERE chatroom_name = 'Chat3')),
((SELECT user_id FROM chat.jc_user WHERE login = 'user4'), (SELECT chatroom_id FROM chat.jc_chatroom WHERE chatroom_name = 'Chat4')),
((SELECT user_id FROM chat.jc_user WHERE login = 'user5'), (SELECT chatroom_id FROM chat.jc_chatroom WHERE chatroom_name = 'Chat5'));
