package edu.school21.chat.repositories.impl;

import edu.school21.chat.exceptions.NotSavedSubEntityException;
import edu.school21.chat.models.Chatroom;
import edu.school21.chat.models.Message;
import edu.school21.chat.models.User;
import edu.school21.chat.repositories.MessagesRepository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class MessagesRepositoryJdbcImpl implements MessagesRepository {
    private static final String SELECT_MESSAGE_BY_ID =
            "select m.id as m_id, m.text as m_text, m.time as m_time,\n" +
                    "       u.id as u_id, u.login as u_login, u.password as u_password,\n" +
                    "       c.id as c_id, c.owner as c_owner, c.name as c_name\n" +
                    "from messages m\n" +
                    "         inner join chatrooms c on c.id = m.room\n" +
                    "         inner join users u on u.id = m.author\n" +
                    "where m.id = ?";
    private static final String INSERT_MESSAGE = "insert into messages (author, room, text)" +
            "VALUES (?, ?, ?) returning id";
    private static final String UPDATE_MESSAGE = "update messages\n" +
            "set author = ?,\n" +
            "    room   = ?,\n" +
            "    text   = ?,\n" +
            "    time   = ?\n" +
            "where id = ?";

    private final DataSource dataSource;

    public MessagesRepositoryJdbcImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Optional<Message> findById(long id) throws SQLException {
        Connection connection = dataSource.getConnection();
        PreparedStatement statement = connection.prepareStatement(SELECT_MESSAGE_BY_ID);
        statement.setLong(1, id);
        ResultSet rs = statement.executeQuery();
        List<Message> messages = new LinkedList<>();
        while (rs.next()) {
            messages.add(new Message(
                    rs.getLong("m_id"),
                    new User(
                            rs.getInt("u_id"),
                            rs.getString("u_login"),
                            rs.getString("u_password"),
                            null,
                            null
                    ),
                    new Chatroom(
                            rs.getLong("c_id"),
                            rs.getString("c_name"),
                            null,
                            null
                    ),
                    rs.getString("m_text"),
                    rs.getTimestamp("m_time")));
        }

        return messages.stream().findFirst();
    }

    @Override
    public void save(Message message) throws SQLException {
        Connection connection = dataSource.getConnection();
        PreparedStatement statement = connection.prepareStatement(INSERT_MESSAGE, Statement.RETURN_GENERATED_KEYS);
        statement.setLong(1, message.getAuthor().getId());
        statement.setLong(2, message.getRoom().getId());
        statement.setString(3, message.getText());
        try {
            if (statement.executeUpdate() == 0) {
                throw new NotSavedSubEntityException();
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        try (ResultSet rs = statement.getGeneratedKeys()) {
            if (rs.next()) {
                message.setId(rs.getLong(1));
            } else {
                throw new NotSavedSubEntityException();
            }
        }
    }

    @Override
    public void update(Message message) throws SQLException {
        Connection connection = dataSource.getConnection();
        PreparedStatement statement = connection.prepareStatement(UPDATE_MESSAGE);
        statement.setLong(1, message.getAuthor().getId());
        statement.setLong(2, message.getRoom().getId());
        if (message.getText() == null) {
            statement.setNull(3, Types.VARCHAR);
        } else {
            statement.setString(3, message.getText());
        }
        if (message.getTime() == null) {
            statement.setNull(4, Types.TIMESTAMP);
        } else {
            statement.setTimestamp(4, message.getTime());
        }
        statement.setLong(5, message.getId());

        statement.executeUpdate();
    }
}
