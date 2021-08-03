package com.school21.ex00;

public class Program {
    public static void main(String[] args) {
        int number = 479598;

        int result = 0;
        while (number > 0)
        {
            result += number % 10;
            number = number / 10;
        }
        System.out.println(result);
    }
}
