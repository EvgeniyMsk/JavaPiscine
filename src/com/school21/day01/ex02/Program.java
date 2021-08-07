package com.school21.day01.ex02;

public class Program {
    public static void main(String[] args) throws UserNotFoundException {
        UsersArrayList usersArrayList = new UsersArrayList();
        usersArrayList.addUser(new User());
        usersArrayList.addUser(new User());
        usersArrayList.addUser(new User());
        usersArrayList.addUser(new User());
        usersArrayList.addUser(new User());
        usersArrayList.addUser(new User());
        usersArrayList.addUser(new User());

//        System.out.printf("Всего пользователей: %d\n",usersArrayList.getCountOfUsers());
//        System.out.println("Пользователь по ID: " + usersArrayList.getUserById(1));
//        System.out.println("Пользователь по индексу: " + usersArrayList.getUserByIndex(1));

//        С исключениями
        System.out.println("Пользователь по ID: " + usersArrayList.getUserById(10));
        System.out.println("Пользователь по индексу: " + usersArrayList.getUserByIndex(6));

    }

}
