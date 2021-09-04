package edu.school21.chat.models;

import java.util.List;
import java.util.Objects;

public class User {
    private Long            userId;
    private String          login,
            password;
    private List<Chatroom>  createdRooms;
    private List<Chatroom>  activeChatroom;

    public User() {
    }

    public User(Long userId, String login, String password, List<Chatroom> createdRooms, List<Chatroom> activeChatroom) {
        this.userId = userId;
        this.login = login;
        this.password = password;
        this.createdRooms = createdRooms;
        this.activeChatroom = activeChatroom;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Chatroom> getCreatedRooms() {
        return createdRooms;
    }

    public void setCreatedRooms(List<Chatroom> createdRooms) {
        this.createdRooms = createdRooms;
    }

    public List<Chatroom> getActiveChatroom() {
        return activeChatroom;
    }

    public void setActiveChatroom(List<Chatroom> activeChatroom) {
        this.activeChatroom = activeChatroom;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return userId.equals(user.userId) && login.equals(user.login) && password.equals(user.password) && createdRooms.equals(user.createdRooms) && activeChatroom.equals(user.activeChatroom);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, login, password, createdRooms, activeChatroom);
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", createdRooms=" + createdRooms +
                ", activeChatroom=" + activeChatroom +
                '}';
    }
}
