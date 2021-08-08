package com.school21.day01.ex04;

import java.util.UUID;

public class TransactionsLinkedList implements TransactionsList {
    private Node first;
    private Node last;
    private int size;

    public TransactionsLinkedList() {
        this.first = null;
        this.last = null;
        this.size = 0;
    }

    public void addTransaction(Transaction transaction) {
        if (first == null)
        {
            Node node = new Node(null, null, transaction);
            first = node;
            last = node;
        } else
        {
            Node node = new Node(last, null, transaction);
            last.next = node;
            last = node;
        }
        size++;
    }

    public void deleteTransactionById(UUID id) throws TransactionNotFoundException {
        if (first == null)
            throw new TransactionNotFoundException("Список транзакций пуст");
        if (first != null) {
            Node temp = first;
            if (temp.isFind(id)) {
                if (temp.next == null) {
                    temp.transaction = null;
                    first = null;
                    last = null;
                    size = 0;
                    return;
                } else {
                    temp.transaction = null;
                    first = temp.next;
                    first.prev = null;
                    size--;
                    return;
                }
            } else {
                while (temp.next != null) {
                    temp = temp.next;
                    if (temp.isFind(id)) {
                        temp.deleteNode();
                        size--;
                        return;
                    }
                }
            }
        }
        throw new TransactionNotFoundException("Транзакция не найдена");
    }

    public Transaction[] toArray() {
        Transaction[] result = new Transaction[size];
        Node tmp = first;
        if (first != null)
            for (int i = 0; i < result.length; i++)
            {
                result[i] = tmp.transaction;
                tmp = tmp.next;
            }
        return result;
    }

    private class Node {
        Node prev;
        Node next;
        Transaction transaction;

        public Node(Node prev, Node next, Transaction transaction) {
            this.prev = prev;
            this.next = next;
            this.transaction = transaction;
        }

        boolean isFind(UUID id) {
            return transaction.getId().equals(id);
        }

        public void deleteNode() {
            if (prev != null)
                prev.next = next;
            if (next != null)
                next.prev = prev;
            transaction = null;
        }
    }
}
