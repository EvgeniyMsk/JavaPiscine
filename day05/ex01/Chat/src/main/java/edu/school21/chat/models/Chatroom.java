package edu.school21.chat.models;

import java.util.List;
import java.util.Objects;

public class Chatroom {
    private Long            chatId;
    private String          chatName;
    private User            chatOwner;
    private List<Message>   messages;

    public Chatroom() {
    }

    public Chatroom(Long chatId, String chatName, User chatOwner, List<Message> messages) {
        this.chatId = chatId;
        this.chatName = chatName;
        this.chatOwner = chatOwner;
        this.messages = messages;
    }

    public Long getChatId() {
        return chatId;
    }

    public void setChatId(Long chatId) {
        this.chatId = chatId;
    }

    public String getChatName() {
        return chatName;
    }

    public void setChatName(String chatName) {
        this.chatName = chatName;
    }

    public User getChatOwner() {
        return chatOwner;
    }

    public void setChatOwner(User chatOwner) {
        this.chatOwner = chatOwner;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Chatroom chatroom = (Chatroom) o;
        return chatId.equals(chatroom.chatId) && chatName.equals(chatroom.chatName) && chatOwner.equals(chatroom.chatOwner) && messages.equals(chatroom.messages);
    }

    @Override
    public int hashCode() {
        return Objects.hash(chatId, chatName, chatOwner, messages);
    }

    @Override
    public String toString() {
        return "Chatroom{" +
                "chatId=" + chatId +
                ", chatName='" + chatName + '\'' +
                ", chatOwner=" + chatOwner +
                ", messages=" + messages +
                '}';
    }
}
