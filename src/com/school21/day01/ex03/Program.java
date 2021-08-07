package com.school21.day01.ex03;



public class Program {

    public static void main(String[] args) throws TransactionNotFoundException {

        User user1 = new User("Adam", 700);
        User user2 = new User("Helen", 1100);
        Transaction transaction1 = new Transaction(user1, user2, TransactionType.INCOME, 800);
        Transaction transaction2 = new Transaction(user1, user2, TransactionType.OUTCOME, 100);
        Transaction transaction3 = new Transaction(user1, user2, TransactionType.INCOME, 200);
        user1.getTransactions().addTransaction(transaction1);
        user1.getTransactions().addTransaction(transaction2);
        user1.getTransactions().addTransaction(transaction3);
        System.out.println("Количество транзакций у User1: " + user1.getTransactions().getSize());
        user1.getTransactions().deleteTransationById(transaction1.getId());
        System.out.println("Количество транзакций у User1 после удаления: " + user1.getTransactions().getSize());

        user2.getTransactions().addTransaction(transaction1);
        user2.getTransactions().addTransaction(transaction2);
        user2.getTransactions().addTransaction(transaction3);
        System.out.println("Количество транзакций у User2: " + user2.getTransactions().getSize());
        user2.getTransactions().deleteTransationById(transaction1.getId());
        System.out.println("Количество транзакций у User2 после удаления: " + user2.getTransactions().getSize());
        user2.getTransactions().deleteTransationById("12312");

    }
}
