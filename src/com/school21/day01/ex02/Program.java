package com.school21.day01.ex02;

public class Program {
    public static void main(String[] args) throws UserNotFoundException {
        UsersArrayList usersArrayList = new UsersArrayList();
        for (int i = 0; i < 10; i++)
            usersArrayList.addUser(new User("Name#" + i, i * i));
        for (int i = 0; i < 10; i++)
            System.out.println(usersArrayList.getUserByIndex(i));
        System.out.println("______________________");
        for (int i = 9; i > 0; i--)
            System.out.println(usersArrayList.getUserById(i));
//        System.out.println(usersArrayList.getUserByIndex(10));
//        System.out.println(usersArrayList.getUserById(10));
    }
}
