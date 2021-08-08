package com.school21.day01.ex04;

public class UsersArrayList implements UsersList {
    private User[] usersList = new User[10];

    public void addUser(User user) {
        int i;
        for (i = 0; i < usersList.length; i++)
            if (usersList[i] == null)
            {
                usersList[i] = user;
                return;
            }
        if (i == usersList.length) {
            User[] tmp = new User[(int) (usersList.length * 1.5)];
            int j;
            for (j = 0; j < usersList.length; j++)
                tmp[j] = usersList[j];
            tmp[j] = user;
            usersList = tmp;
        }
    }

    public User getUserById(int id) throws UserNotFoundException {
        for (User user : usersList)
        {
            if (user.getId() == id)
                return user;
        }
        throw new UserNotFoundException("Нет такого пользователя");
    }

    public User getUserByIndex(int index) throws UserNotFoundException {
        if (index < usersList.length)
        {
            if (usersList[index] != null)
                return usersList[index];
        }
        throw new UserNotFoundException("Индекс некорректен");
    }

    public int getUsersCount() {
        int result = 0;
        for (User user : usersList) {
            if (user != null)
                result++;
        }
        return result;
    }
}
