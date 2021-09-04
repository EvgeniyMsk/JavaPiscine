package edu.school21.chat.repositories;

import edu.school21.chat.models.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.*;
import java.util.Optional;

public class MessagesRepositoryJdbcImpl implements MessagesRepository {

    private DataSource dataSource;

    private void closeConnections(ResultSet rs, PreparedStatement ps, Connection conn) throws SQLException {
        dataSource.close();
        rs.close();
        ps.close();
        conn.close();
    }

    public MessagesRepositoryJdbcImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public Optional<Message> findById(Long id) throws SQLException {

        try {
            Connection connection = dataSource.getConnection();
            String selectSQL = "SELECT * FROM chat.jc_message WHERE message_id = " + id;
            PreparedStatement prepStmt = connection.prepareStatement(selectSQL);
            ResultSet rs = prepStmt.executeQuery();

            if (rs.next() == false) {
                closeConnections(rs, prepStmt, connection);
                return null;
            }

            Message message = new Message();
            message.setMessageId(rs.getLong(1));
            message.setText(rs.getString(4));
            message.setLocalDateTime(rs.getTimestamp(5).toLocalDateTime());


            Long userId = rs.getLong(2);

            Long chatRoomId = rs.getLong(3);

            selectSQL = "SELECT * FROM chat.jc_user WHERE user_id = " + userId;
            prepStmt = connection.prepareStatement(selectSQL);
            rs = prepStmt.executeQuery();

            if (rs.next()) {
                User user = new User();
                user.setUserId(userId);
                user.setLogin(rs.getString(2));
                user.setPassword(rs.getString(3));
                message.setAuthor(user);
            }

            selectSQL = "SELECT * FROM chat.jc_chatroom WHERE chatroom_id = " + chatRoomId;
            prepStmt = connection.prepareStatement(selectSQL);
            rs = prepStmt.executeQuery();

            if (rs.next()) {
                Chatroom chatRoom = new Chatroom();
                chatRoom.setChatId(chatRoomId);
                chatRoom.setChatName(rs.getString(2));
                message.setChatroom(chatRoom);
            }

            closeConnections(rs, prepStmt, connection);
            return Optional.of(message);
        } catch (SQLException e) {
            e.getMessage();
        }
        return Optional.empty();
    }
}
