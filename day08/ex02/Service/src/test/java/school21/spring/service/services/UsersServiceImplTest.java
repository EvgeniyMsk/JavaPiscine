package school21.spring.service.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import school21.spring.service.config.TestApplicationConfig;
import school21.spring.service.repositories.UsersRepositoryJdbcImpl;

import javax.sql.DataSource;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

public class UsersServiceImplTest {
    private UsersService usersService;

    @BeforeEach
    public void init() throws SQLException {
        ApplicationContext context = new AnnotationConfigApplicationContext(TestApplicationConfig.class);
        DataSource dataSource = context.getBean("hsqldbDataSource", DataSource.class);
        usersService = new UsersServiceImpl(new UsersRepositoryJdbcImpl(dataSource));
    }

    @ParameterizedTest
    @ValueSource(strings = {"admin@yandex.ru", "user@mail.ru"})
    public void isReturnedPassword() throws SQLException, NoSuchAlgorithmException {
        Assertions.assertNotNull(usersService.signUp("admin@yandex.ru"));
    }
}
