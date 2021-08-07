package com.school21.ex00;

public class Program {

    public static int getSum(int number) {
        int result = 0;
        if (number < 0)
            number *= (-1);
        while (number > 0)
        {
            result += number % 10;
            number = number / 10;
        }
        return result;
    }

    public static void main(String[] args) {
        int number = 479598;

        System.out.println(getSum(number));
    }
}
