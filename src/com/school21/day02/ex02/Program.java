package com.school21.day02.ex02;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Program {
    public static File isValidArgs(String args) {
        String[] split = args.split("=");
        if (split[0].equals("--current-folder")) {
            File folder = new File(split[1]);
            if (folder.exists())
                return (folder);
        }
        return (null);
    }

    public static void main(String[] Args) throws IOException {
        Scanner in = new Scanner(System.in);
        File folder = isValidArgs(Args[0]);
        if (folder == null) {
            System.out.println("Program --current-folder=DirectoryName");
            return;
        }
        else
            System.out.print(folder.getCanonicalPath() + " %");
        Menu menu = new Menu(folder);
        for (String input = in.nextLine(); !input.equals("exit"); input = in.nextLine()) {
            String[] args = input.split(" ");
            menu.menu(args);
        }
    }
}

