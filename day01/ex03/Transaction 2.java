package com.school21.day01.ex03;


import java.util.UUID;

public class Transaction {
    private UUID id;
    private User receiver;
    private User sender;
    private TransactionType category;
    private int amount;

    public Transaction(User receiver, User sender, TransactionType category, int amount) {
        this.receiver = receiver;
        this.sender = sender;
        this.category = category;
        this.amount = amount;
        this.id = UUID.randomUUID();
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public User getReceiver() {
        return receiver;
    }

    public void setReceiver(User receiver) {
        this.receiver = receiver;
    }

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public TransactionType getCategory() {
        return category;
    }

    public void setCategory(TransactionType category) {
        this.category = category;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        if (category.equals(TransactionType.INCOME))
        {
            if (amount < 0)
                this.amount = 0;
            else
                this.amount = amount;
        } else
        {
            if (amount > 0)
                this.amount = 0;
            else
                this.amount = amount;
        }
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Transaction: ID: " + id +
                " Получатель: [" + receiver +
                "] Отправитель: [" + sender +
                "] Сумма: " + amount;
    }
}
