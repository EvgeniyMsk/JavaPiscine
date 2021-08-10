package com.school21.day02.ex00;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Program {
    private static final String SIG_FILE = "/Users/qsymond/Desktop/JavaPiscine/src/com/school21/day02/ex00/signatures.txt";
    private static final String RESULT_FILE = "result.txt";

    private static Map<String, String> getSignatures(String file) throws FileNotFoundException {
        Map<String, String> signaturesMap = new HashMap<>();
        Scanner scanner = new Scanner(new File(file));
        while (scanner.hasNextLine())
        {
            String line = scanner.nextLine();
            String extension = line.substring(0, line.indexOf(',')).toUpperCase();
            String signature = line.substring(line.indexOf(',') + 2);
            signaturesMap.put(extension, signature);
        }
        return signaturesMap;
    }

    public static String byteToHex(byte num) {
        char[] hexDigits = new char[2];
        hexDigits[0] = Character.forDigit((num >> 4) & 0xF, 16);
        hexDigits[1] = Character.forDigit((num & 0xF), 16);
        return new String(hexDigits);
    }

    public static void findSignature(String fileName) throws IOException {
        File file = new File(fileName);
        FileInputStream fileInputStream = new FileInputStream(file);
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < 12; i++)
            stringBuilder.append(byteToHex((byte) fileInputStream.read())).append(" ");
        fileInputStream.close();
        Writer writer = new OutputStreamWriter(new FileOutputStream(RESULT_FILE, true));
        Map<String, String> signatures = getSignatures(SIG_FILE);
        boolean isfound = false;
        for (Map.Entry entry : signatures.entrySet())
        {
            if (stringBuilder.toString().toUpperCase().startsWith(entry.getValue().toString()))
            {
                writer.write(entry.getKey().toString() + "\n");
                System.out.println("PROCESSED");
                isfound = true;
                break;
            }
        }
        if (!isfound)
            System.out.println("UNDEFINED");
        writer.close();
    }

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        String line = "";
        while (!line.equals("42")) {
            System.out.print("-> ");
            line = scanner.nextLine();
            try {
                findSignature(line);
            } catch (Exception e)
            {
                System.out.println("Некорректное имя файла!");
            }

        }
    }
}
