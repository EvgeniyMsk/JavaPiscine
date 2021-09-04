package school21.spring.service.repositories;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import school21.spring.service.models.User;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class UsersRepositoryJdbcTemplateImpl implements UsersRepository {
    private JdbcTemplate jdbcTemplate;

    public UsersRepositoryJdbcTemplateImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    final String getUserId =  "SELECT * FROM public.user WHERE id=";
    @Override
    public Optional<User> findById(Long id) {
        if (jdbcTemplate.query(getUserId + id, new BeanPropertyRowMapper<>(User.class)).isEmpty())
            return Optional.empty();
        else
            return Optional.of(jdbcTemplate.queryForObject(getUserId + id, new BeanPropertyRowMapper<>(User.class)));
    }

    @Override
    public List<User> findAll() {
        return jdbcTemplate.query("select * from public.user;", new BeanPropertyRowMapper<User>(User.class));
    }

    @Override
    public void save(User entity) throws DuplicateKeyException {
        try {
            jdbcTemplate.execute("insert into public.user(email) values ('" + entity.getEmail() + "')");
        } catch (DuplicateKeyException e)
        {
            System.out.println("Пользователь с таким email уже существует!");
        }
    }

    @Override
    public void update(User entity) {
        jdbcTemplate.execute("update public.user set email='" + entity.getEmail()+ "' where id=" + entity.getId());
    }

    @Override
    public void delete(Long id) {
        jdbcTemplate.execute("delete from public.user where id="+ id);
    }

    final String getUserEmail =  "SELECT * FROM public.user WHERE email=";
    @Override
    public Optional<User> findByEmail(String email) {
        if (jdbcTemplate.query(getUserEmail + "'" + email + "'", new BeanPropertyRowMapper<>(User.class)).isEmpty())
            return Optional.empty();
        else
            return Optional.of(jdbcTemplate.queryForObject(getUserEmail + "'" + email + "'", new BeanPropertyRowMapper<>(User.class)));
    }
}
