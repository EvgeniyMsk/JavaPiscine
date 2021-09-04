package edu.school21.chat.app;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import edu.school21.chat.models.Chatroom;
import edu.school21.chat.models.Message;
import edu.school21.chat.models.User;
import edu.school21.chat.repositories.MessagesRepository;
import edu.school21.chat.repositories.impl.MessagesRepositoryJdbcImpl;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.sql.Timestamp;

public class Program {
    public static void main(String[] args) throws SQLException {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:postgresql://localhost:5432/chat");
        config.setSchema("public");
        config.setUsername("qsymond");
        config.setPassword("password");
        config.setDriverClassName("org.postgresql.Driver");
        DataSource dataSource = new HikariDataSource(config);

        MessagesRepository messagesRepository = new MessagesRepositoryJdbcImpl(dataSource);

        User user = new User(5L, "user", "user", null, null);
        Chatroom room = new Chatroom(5L, "room", user, null);
        Message message = new Message(user, room, "Hello!", new Timestamp(System.currentTimeMillis()));
        messagesRepository.save(message);
        System.out.println(message.getId());

        message.setText(null);
        message.setTime(null);

        System.out.println("------------ Before update -----------");
        messagesRepository.findById(message.getId()).ifPresent(System.out::println);

        messagesRepository.update(message);

        System.out.println("------------- After update -----------");
        messagesRepository.findById(message.getId()).ifPresent(System.out::println);
    }
}
