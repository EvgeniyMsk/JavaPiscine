package edu.school21.sockets.app;

import edu.school21.sockets.server.Server;
import java.io.*;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws IOException, SQLException {
        Server server = new Server(Integer.parseInt(args[0].split("=")[1]));
        server.startServer();
    }
}
