package edu.school21.sockets.client;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;

public class Client {
    private Socket socket;
    private int port;
    private BufferedReader in;
    private BufferedWriter out;
    private BufferedReader reader;


    public Client(int port) {
        this.port = port;
    }

    public void connectToServer() throws IOException {
        socket = new Socket("localhost", port);
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        reader = new BufferedReader(new InputStreamReader(System.in));
        String helloMessage = in.readLine();
        System.out.println(helloMessage);
        String message = reader.readLine();
        out.write(message + "\n");
        out.flush();

        String askForName = in.readLine();
        System.out.println(askForName);
        String name = reader.readLine();
        out.write(name + "\n");
        out.flush();

        String askForPassword = in.readLine();
        System.out.println(askForPassword);
        String password = reader.readLine();
        out.write(password + "\n");
        out.flush();

        String feedBack = in.readLine();
        System.out.println(feedBack);
        try {
            socket.close();
            in.close();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
