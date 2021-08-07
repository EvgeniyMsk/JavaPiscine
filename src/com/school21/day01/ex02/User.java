package com.school21.day01.ex02;

public class User {

    private final int id;

    public int getId() {
        return id;
    }

    public User() {
        this.id = UserIdsGenerator.getInstance().generateId();
    }

    @Override
    public String toString() {
        return "User#" + id;
    }
}
