package edu.school21.sockets.app;


import edu.school21.sockets.client.Client;

public class Main {

    public static void main(String[] args) {
        Client client = new Client("localhost", Integer.parseInt(args[0].split("=")[1]));
        client.startMessaging();
    }
}
