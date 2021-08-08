package com.school21.day01.ex04;


import java.util.UUID;

public class Program {
    public static void main(String[] args) throws TransactionNotFoundException, UserNotFoundException, IllegalTransactionException {
        TransactionsService transactionsService = new TransactionsService();
        transactionsService.addUser("Username#1", 1300);
        transactionsService.addUser("Username#2", 300);
        transactionsService.doTransaction(0, 1, 200);
        transactionsService.doTransaction(0, 1, 110);
        transactionsService.doTransaction(1, 0, 33);
        System.out.println("Транзакции пользователя");
        for (int i = 0; i < transactionsService.getTransactionsByUserId(0).length; i++) {
            System.out.println(transactionsService.getTransactionsByUserId(0)[i]);
        }
        System.out.println("Транзакции второго пользователя");
        for (int i = 0; i < transactionsService.getTransactionsByUserId(1).length; i++) {
            System.out.println(transactionsService.getTransactionsByUserId(1)[i]);
        }

        UUID uuid = transactionsService.getTransactionsByUserId(0)[0].getId();
        transactionsService.deleteTransaction(uuid, 0);
        System.out.println("Транзакции пользователя после удаления " + uuid);
        for (int i = 0; i < transactionsService.getTransactionsByUserId(0).length; i++) {
            System.out.println(transactionsService.getTransactionsByUserId(0)[i]);
        }
        System.out.println("Баланс первого пользователя: " + transactionsService.getUserBalance(0));
        System.out.println("Баланс второго пользователя: " + transactionsService.getUserBalance(1));
        for (int i = 0; i < transactionsService.getUnpairedTransactions().length; i++)
            System.out.println(transactionsService.getUnpairedTransactions()[i]);
    }
}
