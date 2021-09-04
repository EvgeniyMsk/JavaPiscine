package com.school21.day00.ex04;
import java.util.Scanner;

public class Program {
    private static final int MAX_N = 65535;
    private static final int MAX_REP = 10;


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        char[] charArr = scanner.nextLine().toCharArray();
        scanner.close();
        if (charArr.length > 0) {
            int[] values = new int[MAX_N];
            int[] chars = new int[MAX_REP];
            int[] counts = new int[MAX_REP];
            int[] procents = new int[MAX_REP];

            for (char ch : charArr)
                values[ch]++;


            for (int i = 0; i < MAX_REP; i++) {
                int currentChar = 0;
                int currentCount = 0;

                for (int j = 0; j < MAX_N; j++) {
                    if (values[j] > currentCount) {
                        currentCount = values[j];
                        currentChar = j;
                    }
                }
                chars[i] = currentChar;
                counts[i] = currentCount;
                values[currentChar] = 0;
            }

            double step = (double) counts[0] / MAX_REP;
            for (int i = 0; i < MAX_REP; i++)
                procents[i] = (int) (counts[i] / step);


            for (int i = 0; i < MAX_REP + 1; i++) {
                for (int j = 0; j < MAX_REP; j++) {
                    if (counts[j] > 0) {
                        if (procents[j] == (MAX_REP - i))
                            System.out.printf("%d\t", counts[j]);
                        if (procents[j] > (MAX_REP - i))
                            System.out.print("#\t");
                    }
                }
                System.out.println();
            }
            for (int i = 0; i < 10; i++)
                System.out.print((char) chars[i] + "\t");
        }
    }
}
