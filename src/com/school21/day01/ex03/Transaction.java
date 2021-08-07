package com.school21.day01.ex03;

import java.util.UUID;

public class Transaction {
    private final String id;

    private User receiver;

    private User sender;

    private TransactionType category;

    private int sum;

    public String getId() {
        return id;
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

    public int getSum() {
        return sum;
    }

    public void setSum(int sum) {
        this.sum = sum;
    }

    public Transaction(User receiver, User sender, TransactionType category, int sum) {
        this.id = UUID.randomUUID().toString();
        if (receiver == null || sender == null)
            System.out.println("Транзакция не успешна. Причина: Ошибка получателя/отправителя");
        else {
            this.receiver = receiver;
            this.sender = sender;
            switch (category) {
                case INCOME:
                {
                    if (sender.getBalance() - sum >= 0)
                    {
                        sender.setBalance(sender.getBalance() - sum);
                        receiver.setBalance(receiver.getBalance() + sum);
                    } else System.out.println("Транзакция не успешна. Причина: не хватает средств!");
                    break;
                }
                case OUTCOME:
                {
                    if (receiver.getBalance() - sum >= 0)
                    {
                        sender.setBalance(sender.getBalance() + sum);
                        receiver.setBalance(receiver.getBalance() - sum);
                    } else System.out.println("Транзакция не успешна. Причина: не хватает средств!");
                    break;
                }
                default: break;
            }
            this.category = category;
            this.sum = sum;
        }
    }

    @Override
    public String toString() {
        if (this.category.equals(TransactionType.INCOME))
        {
            return "ID: " + this.id + " " +
                    "Category: " + this.category + " " +
                    "Amount: " + this.sum + " " +
                    "From: " + this.sender.getName() + " to: " + this.receiver.getName();
        }
        if (this.category.equals(TransactionType.OUTCOME))
        {
            return "ID: " + this.id + " " +
                    "Category: " + this.category + " " +
                    "Amount: " + this.sum + " " +
                    "From: " + this.receiver.getName() + " to: " + this.sender.getName();
        }
        else return "ошибка транзакции";
    }
}
