package edu.school21.chat.models;

import java.util.List;
import java.util.Objects;

public class User {
    private final long id;

    private final String login;

    private final String password;

    private final List<Chatroom> createdRooms;

    private final List<Chatroom> chatRooms;

    public User(long id, String login, String password, List<Chatroom> createdRooms, List<Chatroom> chatRooms) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.createdRooms = createdRooms;
        this.chatRooms = chatRooms;
    }

    public long getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public List<Chatroom> getCreatedRooms() {
        return createdRooms;
    }

    public List<Chatroom> getChatRooms() {
        return chatRooms;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id && Objects.equals(login, user.login) && Objects.equals(password, user.password) && Objects.equals(createdRooms, user.createdRooms) && Objects.equals(chatRooms, user.chatRooms);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, login, password, createdRooms, chatRooms);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", createdRooms=" + createdRooms +
                ", chatRooms=" + chatRooms +
                '}';
    }
}
