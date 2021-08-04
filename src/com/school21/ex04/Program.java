package com.school21.ex04;

import java.util.Arrays;
import java.util.Scanner;

public class Program {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        char[] charArr = scanner.nextLine().toCharArray();
        int[] values = new int[65536];
        for (char ch : charArr)
            values[ch]++;
        Arrays.sort(values);
        int sum = 0;
        for (int i = 65535; i > 65525; i--)
            sum += values[i];
        double mnoj = (double) values[65535] / 10;
        for (int i = 65535; i > 65525; i--) {
            System.out.print(values[i] + " ");
            for (int j = 0; j < (int) (values[i] / mnoj); j++)
                System.out.print("#");
            System.out.println((char) values[i]);
        }
    }
}
