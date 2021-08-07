package com.school21.day01.ex03;

public class TransactionsLinkedList implements TransactionsList {
    private Node first;
    private Node last;
    private int size;

    public TransactionsLinkedList() {
        first = null;
        last = null;
        size = 0;
    }

    @Override
    public void addTransaction(Transaction transaction) {
        if (first == null)
        {
            Node node = new Node(transaction, null, null);
            first = node;
            last = node;
        } else
        {
            Node node = new Node(transaction, last, null);
            last.next = node;
            last = node;
        }
        size++;
    }

    @Override
    public void deleteTransationById(String id) throws TransactionNotFoundException {
        if (first != null) {
            Node tmp = first;
            if (tmp.isFind(id)) {
                if (tmp.next == null) {
                    tmp.transaction = null;
                    first = null;
                    last = null;
                    size = 0;
                    return;
                } else {
                    tmp.transaction = null;
                    first = tmp.next;
                    first.prev = null;
                    size--;
                    return;
                }
            }
            while (tmp.next != null) {
                tmp = tmp.next;
                if (tmp.isFind(id)) {
                    tmp.remove();
                    size--;
                    return;
                }
            }
        }
        throw new TransactionNotFoundException("Нет такой транзакции!");
    }

    @Override
    public Transaction[] toArray() {
        Transaction[] array = new Transaction[size];

        if (first != null) {
            Node tmp = first;
            for (int i = 0 ; i < size ; i++) {
                array[i] = tmp.transaction;
                tmp = tmp.next;
            }
        }
        return array;
    }

    public int getSize() {
        return size;
    }

    private class Node {
        Transaction transaction;
        Node prev;
        Node next;

        public Node(Transaction transaction, Node prev, Node next) {
            this.transaction = transaction;
            this.prev = prev;
            this.next = next;
        }

        boolean isFind(String id) {
            return transaction.getId().equals(id);
        }

        public void remove() {
            transaction = null;
            if (prev != null)
                prev.next = next;
            if (next != null)
                next.prev = prev;
        }
    }
}
