package edu.school21.sockets.services;

import edu.school21.sockets.models.User;
import edu.school21.sockets.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.sql.SQLException;
import java.util.Optional;

@Component
public class UsersServiceImpl implements UsersService {
    private UsersRepository usersRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UsersServiceImpl(UsersRepository usersRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.usersRepository = usersRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public boolean signIn(String username, String password) throws SQLException {
        Optional<User> optionalUser = usersRepository.findByName(username);
        if (!optionalUser.isPresent())
            return false;
        if (!bCryptPasswordEncoder.matches(password, optionalUser.get().getPassword()))
            return false;
        return true;
    }


    @Override
    public boolean signUp(String username, String password) throws SQLException {
        Optional<User> optionalUser = usersRepository.findByName(username);
        if (optionalUser.isPresent()) {
            return false;
        }
        usersRepository.save(new User(username, bCryptPasswordEncoder.encode(password)));
        return true;
    }

    @Override
    public void saveMessage(String text) {
        usersRepository.saveMessage(text);
    }
}
