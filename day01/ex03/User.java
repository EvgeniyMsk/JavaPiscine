package com.school21.day01.ex03;

public class User {
    private final int id;
    private final String name;
    private int balance;
    private TransactionsList transactions;

    public User(String name, int balance) {
        this.id = UserIdsGenerator.getInstance().generateId();
        this.name = name;
        setBalance(balance);
        this.transactions = new TransactionsLinkedList();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        if (balance < 0)
            this.balance = 0;
        else
            this.balance = balance;
    }

    public TransactionsList getTransactions() {
        return transactions;
    }

    @Override
    public String toString() {
        return "ID: " + id + " NAME: " + name + " BALANCE: " + balance;
    }
}
