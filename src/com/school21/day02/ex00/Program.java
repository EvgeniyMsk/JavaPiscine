package com.school21.day02.ex00;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
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
            String extension = line.substring(0, line.indexOf(','));
            String signature = line.substring(line.indexOf(',') + 2);
            signaturesMap.put(extension, signature);
        }
        return signaturesMap;
    }

    public static void main(String[] args) throws IOException {
        File file = new File("/Users/qsymond/Desktop/files/file1.pdf");
        FileInputStream fileInputStream = new FileInputStream(file);
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < 12; i++)
            stringBuilder.append(Integer.toHexString(fileInputStream.read())).append(" ");
        fileInputStream.close();

        Map<String, String> signatures = getSignatures(SIG_FILE);
        for (Map.Entry entry : signatures.entrySet())
        {

        }
    }
}
