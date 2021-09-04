package school21.spring.service.application;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import school21.spring.service.models.User;
import school21.spring.service.repositories.UsersRepository;
import school21.spring.service.repositories.UsersRepositoryJdbcImpl;
import school21.spring.service.repositories.UsersRepositoryJdbcTemplateImpl;

import javax.sql.DataSource;
import java.sql.*;

public class Main {
    public static void main(String[] args) throws SQLException {
        doDemo();
    }

    public static void doDemo() throws SQLException {
        ApplicationContext context = new ClassPathXmlApplicationContext("context.xml");
        UsersRepository usersRepository1 = context.getBean("usersRepositoryJdbcImplHikariDataSource", UsersRepository.class);
        System.out.println(usersRepository1.findAll());

        UsersRepository usersRepository2 = context.getBean("usersRepositoryJdbcImplDriverManagerDataSource", UsersRepository.class);
        System.out.println(usersRepository2.findAll());

        UsersRepository usersRepository3 = context.getBean("usersRepositoryJdbcTemplateImplHikariDataSource", UsersRepository.class);
        System.out.println(usersRepository3.findAll());

        UsersRepository usersRepository4 = context.getBean("usersRepositoryJdbcTemplateImplDriverManagerDataSource", UsersRepository.class);
        System.out.println(usersRepository4.findAll());
    }
}
