package edu.school21.sockets.server;

import edu.school21.sockets.config.SocketsApplicationConfig;
import edu.school21.sockets.services.UsersService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;

public class Server {
    private Socket socket;
    private ServerSocket serverSocket;
    private int port;
    private BufferedReader in;
    private BufferedWriter out;
    private ApplicationContext context;
    private UsersService usersService;

    public Server(int port) {
        this.port = port;
        this.context = new AnnotationConfigApplicationContext(SocketsApplicationConfig.class);
        usersService = this.context.getBean("usersServiceImpl", UsersService.class);
    }

    public void startServer() throws IOException, SQLException {
        serverSocket = new ServerSocket(port);
        Socket client = serverSocket.accept();
        in = new BufferedReader(new InputStreamReader(client.getInputStream()));
        out = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
        out.write("Hello from Server!\n");
        out.flush();
        String message = in.readLine();
        System.out.println(message);
        out.write("Enter username:\n");
        out.flush();
        String name = in.readLine();
        out.write("Enter password:\n");
        out.flush();
        String password = in.readLine();
        if (!usersService.register(name, password))
        {
            out.write("Пользователь с таким name уже существует, либо ввод некорректен!");
        } else
            out.write("Successful!\n");
        out.flush();
        out.close();
        in.close();
        client.close();
        serverSocket.close();
    }
}
