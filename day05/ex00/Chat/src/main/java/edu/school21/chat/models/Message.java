package edu.school21.chat.models;

import java.time.LocalDateTime;
import java.util.Objects;

public class Message {
    private Long            messageId;
    private User            author;
    private Chatroom        chatroom;
    private String          text;
    private LocalDateTime   localDateTime;

    public Message(Long messageId, User author, Chatroom chatroom, String text, LocalDateTime localDateTime) {
        this.messageId = messageId;
        this.author = author;
        this.chatroom = chatroom;
        this.text = text;
        this.localDateTime = localDateTime;
    }

    public Long getMessageId() {
        return messageId;
    }

    public void setMessageId(Long messageId) {
        this.messageId = messageId;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public Chatroom getChatroom() {
        return chatroom;
    }

    public void setChatroom(Chatroom chatroom) {
        this.chatroom = chatroom;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Message message = (Message) o;
        return messageId.equals(message.messageId) && author.equals(message.author) && chatroom.equals(message.chatroom) && text.equals(message.text) && localDateTime.equals(message.localDateTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(messageId, author, chatroom, text, localDateTime);
    }

    @Override
    public String toString() {
        return "Message{" +
                "messageId=" + messageId +
                ", author=" + author +
                ", chatroom=" + chatroom +
                ", text='" + text + '\'' +
                ", localDateTime=" + localDateTime +
                '}';
    }
}
