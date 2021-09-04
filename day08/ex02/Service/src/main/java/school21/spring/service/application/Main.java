package school21.spring.service.application;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import school21.spring.service.config.ApplicationConfig;
import school21.spring.service.repositories.UsersRepositoryJdbcImpl;
import school21.spring.service.repositories.UsersRepositoryJdbcTemplateImpl;
import school21.spring.service.services.UsersService;
import school21.spring.service.services.UsersServiceImpl;


import javax.sql.DataSource;
import java.security.NoSuchAlgorithmException;
import java.sql.*;

public class Main {
    public static void main(String[] args) throws SQLException, NoSuchAlgorithmException {
        ApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfig.class);
        DataSource dataSource1 = context.getBean("hikariDataSource", DataSource.class);
        DataSource dataSource2 = context.getBean("driverManagerDataSource", DataSource.class);
        DataSource dataSource3 = context.getBean("hsqldbDataSource", DataSource.class);

        UsersRepositoryJdbcImpl usersRepositoryJdbc = new UsersRepositoryJdbcImpl(dataSource1);
        UsersRepositoryJdbcTemplateImpl usersRepositoryJdbcTemplate = new UsersRepositoryJdbcTemplateImpl(dataSource2);
        UsersRepositoryJdbcTemplateImpl usersRepositoryJdbc1 = new UsersRepositoryJdbcTemplateImpl(dataSource3);
        UsersService usersService = new UsersServiceImpl(usersRepositoryJdbc1);
        usersService.signUp("admin@yandex.ru");
        usersService.signUp("admin1@yandex.ru");
        usersService.signUp("admin1@yandex.ru");
        System.out.println(usersRepositoryJdbc1.findAll());
        System.out.println(usersRepositoryJdbc.findAll());
        System.out.println(usersRepositoryJdbcTemplate.findAll());
        System.out.println(usersRepositoryJdbc1.findAll());
    }
}
