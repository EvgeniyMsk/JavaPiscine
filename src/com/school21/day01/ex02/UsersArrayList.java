package com.school21.day01.ex02;

public class UsersArrayList implements UserList {
    private User[] userList = new User[10];

    @Override
    public void addUser(User user) {
        int i;
        for (i = 0; i < userList.length; i++)
            if (userList[i] == null) {
                userList[i] = user;
                break;
            }
        if (i == userList.length)
        {
            User[] tmp = new User[(int) (userList.length * 1.5)];
            for (int j = 0; j < userList.length; j++)
                tmp[j] = userList[j];
            tmp[userList.length + 1] = user;
            userList = tmp;
        }
    }

    @Override
    public User getUserById(int id) throws UserNotFoundException {
        for (User user : userList)
            if (user != null)
                if (user.getId() == id)
                return user;
            throw new UserNotFoundException("Нет такого пользователя");
    }

    @Override
    public User getUserByIndex(int index) throws UserNotFoundException {
        if (index < userList.length)
        if (userList[index] != null)
            return userList[index];
        throw new UserNotFoundException("Нет такого пользователя");
    }

    @Override
    public int getCountOfUsers() {
        int result = 0;
        for (User user : userList)
            if (user != null)
                result++;
            return result;
    }
}
