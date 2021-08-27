package com.school21.day02.ex01;

import java.io.*;
import java.util.*;

public class Program {
    private static TreeMap<String, Integer> similar1 = new TreeMap<String,Integer>();
    private static TreeMap<String, Integer> similar2 = new TreeMap<String,Integer>();

    private static SortedSet<String> initDictionary(String fileName1, String fileName2) throws IOException {
        SortedSet<String> result = new TreeSet<>();
        Scanner scanner = new Scanner(new File(fileName1));
        while (scanner.hasNextLine())
        {
            String line = scanner.nextLine();
            String[] words = line.split(" ");
            result.addAll(Arrays.asList(words.clone()));
            for (String i : words)
                similar1.merge(i, 1, Integer::sum);
        }
        scanner.close();
        scanner = new Scanner(new File(fileName2));
        while (scanner.hasNextLine())
        {
            String line = scanner.nextLine();
            String[] words = line.split(" ");
            result.addAll(Arrays.asList(words.clone()));
            for (String i : words)
                similar2.merge(i, 1, Integer::sum);
        }
        Writer writer = new OutputStreamWriter(new FileOutputStream("dictionary.txt", false));
        for (String i : result)
            writer.write(i + "\n");
        writer.close();
        scanner.close();
        return (result);
    }

    private static void doExercize(String arg1, String arg2) throws IOException {
        double numerator = 0;
        double denominator;
        double similarity;
        double sumA = 0;
        double sumB = 0;
        for (String i : initDictionary(arg1, arg2))
        {
            similar1.putIfAbsent(i, 0);
            similar2.putIfAbsent(i, 0);
            numerator += similar1.get(i) * similar2.get(i);
            sumA += similar1.get(i) * similar1.get(i);
            sumB += similar2.get(i) * similar2.get(i);
        }
        denominator = Math.sqrt(sumA * sumB);
        if (numerator == 0)
            similarity = 0;
        else
            similarity = numerator / denominator;
        System.out.printf("Similarity = %f", similarity);
    }

    public static void main(String[] argv) throws IOException {
        if (argv.length != 2) {
            System.err.println("пример использования: java Program fileName1.txt fileName2.txt");
            System.exit(-1);
        } else {
            doExercize(argv[0], argv[1]);
        }
    }
}
