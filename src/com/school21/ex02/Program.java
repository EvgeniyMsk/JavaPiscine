package com.school21.ex02;

import java.util.Scanner;

public class Program {
    public static boolean isPrime(int number) {
        if (number <= 1)
        {
            System.err.println("IllegalArgument");
            System.exit(-1);
        }
        for (int i = 2; i * i <= number; i++)
            if (number % i == 0)
                return false;
        return true;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int number = 0, primes = 0;
        String line;
        while (number != 42) {
            System.out.print("-> ");
            line = scanner.nextLine();
            if (!line.isEmpty())
            {
                String[] numbers = line
                        .replaceAll("\\s{2,}", " ").trim()
                        .split(" ");
                for (String s : numbers) {
                    number = Integer.parseInt(s);
                    if (number == 42)
                        break;
                    if (isPrime(number))
                        primes++;
                }
            }
        }
        System.out.println("Count of coffee-request - " + primes);
    }
}
