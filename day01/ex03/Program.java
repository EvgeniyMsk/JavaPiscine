package com.school21.day01.ex03;


public class Program {
    public static void main(String[] args) throws TransactionNotFoundException {
        User user1 = new User("Username1", 1100);
        User user2 = new User("Username2", 3400);
        Transaction transaction1 = new Transaction(user1, user2, TransactionType.INCOME, 700);
        Transaction transaction2 = new Transaction(user2, user1, TransactionType.OUTCOME, 1100);
        user1.getTransactions().addTransaction(transaction1);
        user1.getTransactions().addTransaction(transaction2);
        for (int i = 0; i < user1.getTransactions().toArray().length; i++) {
            System.out.println(user1.getTransactions().toArray()[i]);
        }
        user1.getTransactions().deleteTransactionById(transaction1.getId());
        System.out.println("После удаления");
        for (int i = 0; i < user1.getTransactions().toArray().length; i++) {
            System.out.println(user1.getTransactions().toArray()[i]);
        }
    }
}
