package edu.school21.sockets.client;

import java.io.*;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Client {
    private Socket socket;
    private BufferedReader in;
    private BufferedWriter out;
    private BufferedReader inputUser;
    private String nickname;
    private Date time;
    private String dtime;


    public Client(String address, int port) {
        try {
            this.socket = new Socket(address, port);
        } catch (IOException e) {
            System.err.println("Socket failed");
        }
    }

    public void startMessaging()
    {
        try {
            inputUser = new BufferedReader(new InputStreamReader(System.in));
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            this.registration();
            new ReadMsg().start();
            new WriteMsg().start();
        } catch (IOException e) {
            Client.this.closeConnections();
        }
    }
    private void registration() {
        try {
            String cmd = "";
            while (!cmd.equals("signIn") && !cmd.equals("signOut"))
            {
                System.out.println("Введите команду: signIn или signUp");
                cmd = inputUser.readLine();
            }
            out.write(cmd + "\n");
            out.flush();
            String serverMsg = in.readLine();
            System.out.println(serverMsg);
            nickname = inputUser.readLine();
            out.write(nickname + "\n");
            out.flush();
            serverMsg = in.readLine();
            System.out.println(serverMsg);
            String password = inputUser.readLine();
            out.write(password + "\n");
            out.flush();
            serverMsg = in.readLine();
            if (serverMsg.equals("0"))
                System.out.println("Start messaging");
            else if (serverMsg.equals("1")) {
                System.out.println("Такой ник уже существует. Используй signIn");
                this.closeConnections();
                System.exit(1);
            } else {
                System.out.println("Неверные логин или пароль.");
                this.closeConnections();
                System.exit(1);
            }
        } catch (IOException ignored) {
        }

    }

    private void closeConnections() {
        try {
            if (!socket.isClosed()) {
                socket.close();
                in.close();
                out.close();
            }
        } catch (IOException ignored) {}
    }

    private class ReadMsg extends Thread {
        @Override
        public void run() {
            String str;
            try {
                while (true) {
                    str = in.readLine();
                    if (str.equals("Exit")) {
                        Client.this.closeConnections();
                        break;
                    }
                    System.out.println(str);
                }
            } catch (IOException e) {
                Client.this.closeConnections();
            }
        }
    }

    public class WriteMsg extends Thread {
        @Override
        public void run() {
            while (true) {
                String userWord;
                try {
                    time = new Date();
                    dtime = new SimpleDateFormat("HH:mm:ss").format(time);
                    userWord = inputUser.readLine();
                    if (userWord.equals("Exit")) {
                        out.write("Exit" + "\n");
                        Client.this.closeConnections();
                        break;
                    } else {
                        out.write( "(" + dtime + ") " + nickname + ": " + userWord + "\n");
                    }
                    out.flush();
                } catch (IOException e) {
                    Client.this.closeConnections();
                }
            }
        }
    }

}
