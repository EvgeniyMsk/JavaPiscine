package edu.school21.sockets.services;

import java.sql.SQLException;

public interface UsersService {
    boolean register(String name, String password) throws SQLException;
}
