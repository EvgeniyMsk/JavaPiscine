package com.school21.day01.ex00;

public class Program {
    public static void main(String[] args) {
        User user1 = new User("Adam", 700);
        User user2 = new User("Helen", 1100);
        System.out.println(user1);
        System.out.println(user2);
        Transaction transaction = new Transaction(user1, user2, TransactionType.INCOME, 800);
        System.out.println(transaction);
        System.out.println(user1);
        System.out.println(user2);
        Transaction transaction1 = new Transaction(user1, user2, TransactionType.OUTCOME, 100);
        System.out.println(transaction1);
        System.out.println(user1);
        System.out.println(user2);
    }
}
