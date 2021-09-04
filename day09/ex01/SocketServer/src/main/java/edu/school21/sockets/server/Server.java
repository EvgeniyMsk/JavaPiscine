package edu.school21.sockets.server;

import edu.school21.sockets.config.SocketsApplicationConfig;
import edu.school21.sockets.services.UsersService;
import edu.school21.sockets.services.UsersServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.*;
import java.net.Socket;
import java.sql.SQLException;

import static edu.school21.sockets.app.Main.story;
import static edu.school21.sockets.app.Main.serverList;

public class Server extends Thread {

    private Socket socket;
    private BufferedReader in;
    private BufferedWriter out;
    private UsersServiceImpl usersService;

    public Server(Socket socket) throws IOException {
        this.socket = socket;
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        introduce();
        story.printStory(out);
        ApplicationContext context = new AnnotationConfigApplicationContext(SocketsApplicationConfig.class);
        usersService = context.getBean("usersServiceImpl",UsersServiceImpl.class);
        start();
    }

    @Override
    public void run() {
        String message;
        try {
            message = in.readLine();
            try {
                out.write(message + "\n");
                out.flush();
            } catch (IOException ignored) {}
            try {
                while (true) {
                    message = in.readLine();
                    if(message.equals("stop")) {
                        this.closeConnections();
                        break;
                    }
                    System.out.println(message);
                    story.addStory(message);
                    for (Server server : serverList) {
                        server.sendMessage(message);
                    }
                    if (!message.equals(""))
                        usersService.saveMessage(message);
                }
            } catch (NullPointerException ignored) {}
        } catch (IOException e) {
            this.closeConnections();
        }
    }

    private void sendMessage(String message) {
        try {
            out.write(message + "\n");
            out.flush();
        } catch (IOException ignored) {}
    }

    private void introduce() {
        String cmd;
        try {
            cmd = in.readLine();
            System.out.println(cmd);
            if (cmd.equals("signUp"))
                registerUser();
            else if (cmd.equals("signIn"))
                loginUser();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loginUser() {
        try {
            out.write("Enter username:\n");
            out.flush();
            String userName = in.readLine();
            out.write("Enter password:\n");
            out.flush();
            String password = in.readLine();
            ApplicationContext context = new AnnotationConfigApplicationContext(SocketsApplicationConfig.class);
            UsersService usersService = context.getBean("usersServiceImpl", UsersService.class);
            System.out.println(userName + " " + password);
            if (usersService.signIn(userName, password)) {
                out.write("0\n");
            } else {
                out.write("2\n");
            }
            out.flush();
        } catch (IOException | SQLException e) {
            e.printStackTrace();
            this.closeConnections();
        }
    }

    private void registerUser() {
        try {
            out.write("Enter username:\n");
            out.flush();
            String userName = in.readLine();
            out.write("Enter password:\n");
            out.flush();
            String password = in.readLine();
            ApplicationContext context = new AnnotationConfigApplicationContext(SocketsApplicationConfig.class);
            UsersService usersService = context.getBean(UsersService.class);
            System.out.println(userName + " " + password);
            if (usersService.signUp(userName, password)) {
                out.write("0\n");
            } else {
                out.write("1\n");
            }
            out.flush();
        } catch (IOException | SQLException e) {
            e.printStackTrace();
            this.closeConnections();
        }
    }

    private void closeConnections() {
        try {
            if(!socket.isClosed()) {
                socket.close();
                in.close();
                out.close();
                for (Server server : serverList) {
                    if(server.equals(this)) server.interrupt();
                    serverList.remove(this);
                }
            }
        } catch (IOException ignored) {}
    }
}
