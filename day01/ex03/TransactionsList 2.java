package com.school21.day01.ex03;

import java.util.UUID;

public interface TransactionsList {
    void addTransaction(Transaction transaction);
    void deleteTransactionById(UUID id) throws TransactionNotFoundException;
    Transaction[] toArray();
}
