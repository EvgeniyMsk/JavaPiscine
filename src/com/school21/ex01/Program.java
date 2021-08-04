package com.school21.ex01;

import java.util.Scanner;

public class Program {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int number = scanner.nextInt();

        boolean result = true;
        if (number <= 1)
        {
            System.err.println("IllegalArgument");
            scanner.close();
            System.exit(-1);
        } else
        {
            int step = 1;
            for (int i = 2; i * i <= number; i++)
            {
                if (number % i == 0)
                {
                    result = false;
                    break;
                }
                step++;
            }
            System.out.println(result + " " + step);
            scanner.close();
        }

    }

}
