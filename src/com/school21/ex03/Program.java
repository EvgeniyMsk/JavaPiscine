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
//
//            marks[weekNumber] = Integer.MAX_VALUE;
            int i = 0;
            while (i < 5)
            {
                int value = scanner.nextInt();
                i++;
            }
//            if (maxWeekNumber < weekNumber)
//                maxWeekNumber = weekNumber;
//            if (weekNumber == 19) {
//                System.err.println("IllegalArgument");
//                System.exit(-1);
//            }
        }
//        for (int i = 0; i < maxWeekNumber; i++)
//        {
//            writeGraph(marks[i]);
//        }
    }
}
