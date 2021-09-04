package com.school21.day01.ex02;

public class User {
    private final int id;
    private final String name;
    private int balance;

    public User(String name, int balance) {
        this.id = UserIdsGenerator.getInstance().generateId();
        this.name = name;
        setBalance(balance);
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

    @Override
    public String toString() {
        return "ID: " + id + " NAME: " + name + " BALANCE: " + balance;
    }
}
