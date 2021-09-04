package edu.school21.services;

import edu.school21.exceptions.AlreadyAuthenticatedException;
import edu.school21.exceptions.EntityNotFoundException;
import edu.school21.models.User;
import edu.school21.repositories.UsersRepository;

import java.util.Objects;

public class UsersServiceImpl {
    private final UsersRepository usersRepository;

    public UsersServiceImpl(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    public boolean authenticate(String login, String password) throws EntityNotFoundException, AlreadyAuthenticatedException {
        User user = findByLogin(login);
        if (user.isAuthentificated()) {
            throw new AlreadyAuthenticatedException("Аутентификация уже прошла");
        }
        boolean isAuthenticated = user.getPassword().equals(password);
        if (isAuthenticated) {
            user.setAuthentificated(true);
            usersRepository.update(user);
        }
        return isAuthenticated;
    }

    private User findByLogin(String login) throws EntityNotFoundException {
        User user = usersRepository.findByLogin(login);
        if (Objects.isNull(user)) {
            throw new EntityNotFoundException("Нет такого пользователя");
        }
        return user;
    }
}
