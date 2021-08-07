package com.school21.day01.ex01;

public class User {

    private final int id;

    public int getId() {
        return id;
    }

    public User() {
        this.id = UserIdsGenerator.getInstance().generateId();
    }
}
