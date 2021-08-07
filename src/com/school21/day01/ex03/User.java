package com.school21.day01.ex03;

public class User {
    private static int count;
    private final int id;
    private String name;
    private int balance;

    private final TransactionsLinkedList transactionsLinkedList;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public User(String name, int balance) {
        this.id = User.count++;
        this.name = name;
        this.balance = balance;
        this.transactionsLinkedList = new TransactionsLinkedList();
    }

    @Override
    public String toString() {
        return "ID: " + this.id + " " +
                "Name: " + this.name + " " +
                "Balance: " + this.balance;
    }

    public TransactionsLinkedList getTransactions() {
        return transactionsLinkedList;
    }
}