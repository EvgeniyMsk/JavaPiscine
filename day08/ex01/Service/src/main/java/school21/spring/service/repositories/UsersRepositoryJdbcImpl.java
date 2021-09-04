package school21.spring.service.repositories;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import school21.spring.service.models.User;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class UsersRepositoryJdbcImpl implements UsersRepository {
    private Connection connection;

    public UsersRepositoryJdbcImpl(DataSource dataSource) throws SQLException {
        this.connection = dataSource.getConnection();
    }

    final String getUserId =  "SELECT * FROM public.user WHERE id=";
    @Override
    public Optional<User> findById(Long id) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(getUserId + id);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next())
            return Optional.of(new User(resultSet.getLong(1), resultSet.getString(2)));
        else
            return Optional.empty();
    }

    @Override
    public List<User> findAll() throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("select * from public.user;");
        ResultSet resultSet = preparedStatement.executeQuery();
        List<User> result = new LinkedList<>();
        while (resultSet.next())
            result.add(new User(resultSet.getLong(1), resultSet.getString(2)));
        preparedStatement.close();
        resultSet.close();
        return (result);
    }

    @Override
    public void save(User entity) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("insert into public.user(email) values ('" + entity.getEmail() + "')");
            preparedStatement.execute();
            preparedStatement.close();

        } catch (DuplicateKeyException | SQLException e)
        {
            System.out.println("Пользователь с таким email уже существует!");
        }
    }

    @Override
    public void update(User entity) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("update public.user set email='" + entity.getEmail()+ "' where id=" + entity.getId());
        preparedStatement.execute();
        preparedStatement.close();
    }

    @Override
    public void delete(Long id) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("delete from public.user where id="+ id);
        preparedStatement.execute();
        preparedStatement.close();
    }

    final String getUserEmail =  "SELECT * FROM public.user WHERE email=";
    @Override
    public Optional<User> findByEmail(String email) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(getUserEmail + "'" + email + "'");
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next())
            return Optional.of(new User(resultSet.getLong(1), resultSet.getString(2)));
        else
            return Optional.empty();
    }
}
