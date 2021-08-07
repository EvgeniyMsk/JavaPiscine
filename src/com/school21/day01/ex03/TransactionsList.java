package com.school21.day01.ex03;

public interface TransactionsList {
    void addTransaction(Transaction transaction);

    void deleteTransationById(String id) throws TransactionNotFoundException;

    Transaction[] toArray();
}
