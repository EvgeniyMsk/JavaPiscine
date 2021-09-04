package edu.school21.sockets.server;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.LinkedList;

public class Story {
    private LinkedList<String> history = new LinkedList<>();

    public void addStory(String message) {
        if (history.size() >= 10) {
            history.removeFirst();
            history.add(message);
        } else {
            history.add(message);
        }
    }

    public void printStory(BufferedWriter writer) {
        if(history.size() > 0) {
            try {
                writer.write("История сообщений" + "\n");
                for (String i : history) {
                    writer.write(i + "\n");
                }
                writer.write("...." + "\n");
                writer.flush();
            } catch (IOException ignored) {}
        }
    }
}
