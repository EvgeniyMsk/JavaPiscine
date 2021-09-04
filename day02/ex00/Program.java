package com.school21.day02.ex00;

import java.io.*;
import java.util.*;

public class Program {
    private static Map<String, String> signatures;
    private static final String SIGFILE = "signatures.txt";
    private static final String RESULTFILE = "result.txt";

    private static String byteToHex(byte number) {
        char[] digits = new char[2];
        digits[0] = Character.forDigit((number >> 4) & 0xF, 16);
        digits[1] = Character.forDigit(number & 0xF, 16);
        return new String(digits);
    }

    private static Map<String, String> initSignatures() throws FileNotFoundException {
        File file = new File(SIGFILE);
        Scanner scanner = new Scanner(file);
        Map<String, String> result = new HashMap<>();
        while (scanner.hasNextLine())
        {
            String line = scanner.nextLine();
            String key = line.substring(0, line.indexOf(" ") - 1);
            String value = line.substring(line.indexOf(" ") + 1);
            result.put(key, value);
        }
        scanner.close();
        return result;
    }

    private static String isInSignatures(String sign) {
        for (Map.Entry<String, String> pair : signatures.entrySet())
            if (pair.getValue().equalsIgnoreCase(sign.substring(0, sign.length() - 1)))
                return pair.getKey();
        return null;
    }

    private static void doExercize() throws IOException {
        Scanner scanner = new Scanner(System.in);
        String fileName;
        while (true) {
            fileName = scanner.nextLine();
            if (fileName.equals("42"))
            {
                scanner.close();
                return;
            }
            try {
                String sign = "";
                FileInputStream fileInputStream = new FileInputStream(fileName);
                for (int i = 0; i < 8; i++)
                {
                    sign = sign + byteToHex((byte) fileInputStream.read()) + " ";
                    if (isInSignatures(sign) != null)
                        break;
                }
                fileInputStream.close();
                if (isInSignatures(sign) != null)
                {
                    System.out.println("PROCESSED");
                    Writer writer = new OutputStreamWriter(new FileOutputStream(RESULTFILE, true));
                    writer.write(isInSignatures(sign) + "\n");
                    writer.close();
                }
                else
                    System.out.println("UNDEFINED");
            } catch (FileNotFoundException e)
            {
                System.out.println("Некорректное имя файла!");
            }
        }
    }

    public static void main(String[] args) throws IOException {
        signatures = initSignatures();
        doExercize();
    }
}