package edu.school21.sockets.repositories;

import edu.school21.sockets.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Component
public class UsersRepositoryImpl implements UsersRepository {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public UsersRepositoryImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    final String getUserName =  "SELECT * FROM public.user WHERE name=";
    @Override
    public Optional<User> findByName(String name) throws SQLException {
        if (jdbcTemplate.query(getUserName + "'" + name + "'", new BeanPropertyRowMapper<>(User.class)).isEmpty())
            return Optional.empty();
        else
            return Optional.of(jdbcTemplate.queryForObject(getUserName + "'" + name + "'", new BeanPropertyRowMapper<>(User.class)));
    }

    final String getUserId =  "SELECT * FROM public.user WHERE id=";
    @Override
    public Optional<User> findById(Long id) throws SQLException {
        if (jdbcTemplate.query(getUserId + id, new BeanPropertyRowMapper<>(User.class)).isEmpty())
            return Optional.empty();
        else
            return Optional.of(jdbcTemplate.queryForObject(getUserId + id, new BeanPropertyRowMapper<>(User.class)));
    }

    @Override
    public List<User> findAll() throws SQLException {
        return jdbcTemplate.query("select * from public.user;", new BeanPropertyRowMapper<User>(User.class));
    }

    @Override
    public void save(Object entity) throws SQLException {
        User user = (User) entity;
        try {
            jdbcTemplate.execute("insert into public.user(name, password) " +
                    "values ('" + user.getName() + "', '" + user.getPassword() + "')");
        } catch (DuplicateKeyException e)
        {
            System.out.println("Пользователь с таким name уже существует, либо ввод некорректен!");
        }
    }

    @Override
    public void update(Object entity) throws SQLException {
        User user = (User) entity;
        jdbcTemplate.execute("update public.user set name='" + user.getName()+ "', " +
                "password='" + user.getPassword() + "' where id=" + user.getId());
    }

    @Override
    public void delete(Long id) throws SQLException {
        jdbcTemplate.execute("delete from public.user where id="+ id);
    }

    @Override
    public void saveMessage(String text) {
        jdbcTemplate.execute("insert into public.messages(message) values ('"+ text + "')");
    }
}
