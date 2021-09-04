package edu.school21.sockets.app;

import edu.school21.sockets.client.Client;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        Client client = new Client(Integer.parseInt(args[0].split("=")[1]));
        client.connectToServer();
    }
}
