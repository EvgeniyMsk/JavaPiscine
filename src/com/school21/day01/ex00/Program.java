package com.school21.day01.ex00;

public class Program {
    public static void main(String[] args) {
        User user = new User("Ivan", 3900);
        User user1 = new User("Petr", 1200);
        Transaction transaction = new Transaction(user, user1, TransactionType.OUTCOME, 300);
        System.out.println(user);
        System.out.println(user1);
        System.out.println(transaction);
        if (transaction.getCategory().equals(TransactionType.INCOME))
        {
            transaction.getReceiver().setBalance(transaction.getReceiver().getBalance() + transaction.getAmount());
            transaction.getSender().setBalance(transaction.getSender().getBalance() - transaction.getAmount());
            System.out.println(transaction.getReceiver() + " получил от " + transaction.getSender() + " сумму " + transaction.getAmount());
        } else
        {
            transaction.getReceiver().setBalance(transaction.getReceiver().getBalance() - transaction.getAmount());
            transaction.getSender().setBalance(transaction.getSender().getBalance() + transaction.getAmount());
            System.out.println(transaction.getReceiver() + " перевел пользователю " + transaction.getSender() + " сумму " + transaction.getAmount());
        }
    }
}

enum TransactionType {
    INCOME,
    OUTCOME
}
