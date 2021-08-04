package com.school21.ex03;

import java.util.Scanner;

public class Program {
    public static void writeGraph(int number) {
        for (int i = 0; i < number; i++)
            System.out.print('=');
        System.out.println('>');
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String line;
        int weekNumber = 0;
        int[] marks = new int[18];
        int maxWeekNumber = 0;
        while (!(line = scanner.nextLine()).equals("42")) {
            weekNumber = Integer.parseInt(line.replaceAll("\\s{2,}", " ").trim().split( " ")[1]);
            if (weekNumber == 19 || (maxWeekNumber + 1 != weekNumber)) {
                System.err.println("IllegalArgument");
                System.exit(-1);
            }
            maxWeekNumber = weekNumber;
            marks[weekNumber - 1] = Integer.MAX_VALUE;
            String[] values = scanner.nextLine().replaceAll("\\s{2,}", " ").trim().split( " ");

            for (String val : values) {
                int currentMark = Integer.parseInt(val);
                marks[weekNumber - 1] = currentMark < marks[weekNumber - 1] ? currentMark : marks[weekNumber - 1];
            }
        }
        scanner.close();
        for (int i = 0; i < maxWeekNumber; i++)
            writeGraph(marks[i]);
    }
}
