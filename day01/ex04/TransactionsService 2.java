package com.school21.day01.ex04;

import java.util.UUID;

public class TransactionsService {
    private UsersList usersList;

    public TransactionsService() {
        this.usersList = new UsersArrayList();
    }

    void addUser(String name, int balance) {
        usersList.addUser(new User(name, balance));
    }

    int getUserBalance(int id) throws UserNotFoundException {
        return usersList.getUserById(id).getBalance();
    }

    void doTransaction(int senderId, int receiverId, int amount) throws UserNotFoundException, IllegalTransactionException {
        if (amount <= 0)
            throw new IllegalTransactionException("Сумма перевода некорректна");
        User sender = usersList.getUserById(senderId);
        User receiver = usersList.getUserById(receiverId);

        if (sender.getBalance() - amount < 0)
            throw new IllegalTransactionException("Недостаточно средств для перевода");

        Transaction out = new Transaction(
                usersList.getUserById(receiverId),
                usersList.getUserById(senderId),
                TransactionType.OUTCOME, amount);

        sender.getTransactions().addTransaction(out);
        sender.setBalance(sender.getBalance() - amount);

        Transaction in = new Transaction(
                usersList.getUserById(receiverId),
                usersList.getUserById(senderId),
                TransactionType.INCOME, -amount);
        in.setId(out.getId());
        receiver.getTransactions().addTransaction(in);
        receiver.setBalance(receiver.getBalance() + amount);
    }

    Transaction[] getTransactionsByUserId(int id) throws UserNotFoundException {
        return usersList.getUserById(id).getTransactions().toArray();
    }

    void deleteTransaction(UUID id, int userId) throws UserNotFoundException, TransactionNotFoundException {
        usersList.getUserById(userId).getTransactions().deleteTransactionById(id);
    }

    public Transaction[] getUnpairedTransactions() throws UserNotFoundException, TransactionNotFoundException {
        boolean flag;
        TransactionsList unpairedList = new TransactionsLinkedList();
        TransactionsList inc_list = new TransactionsLinkedList();
        TransactionsList out_list = new TransactionsLinkedList();
        int inc_count = 0;
        int out_count = 0;

        for (int i = 0; i < usersList.getUsersCount(); i++) {
            User user = usersList.getUserById(i);
            Transaction[] transaction = user.getTransactions().toArray();

            for (int j = 0; j < transaction.length; j++) {
                if (transaction[j].getCategory() == TransactionType.INCOME) {
                    inc_list.addTransaction(transaction[j]);
                    inc_count++;
                } else {
                    out_list.addTransaction(transaction[j]);
                    out_count++;
                }
            }
        }

        for (int i = 0; i < inc_count; i++) {
            flag = false;
            for (int j = 0; j < out_count; j++) {
                if (inc_list.toArray()[i].getId().equals(out_list.toArray()[j].getId())) {
                    flag = true;
                    break;
                }
            }
            if (!flag) {
                unpairedList.addTransaction(inc_list.toArray()[i]);
            }
        }
        for (int i = 0; i < out_count; i++) {
            flag = false;
            for (int j = 0; j < inc_count; j++) {
                if (out_list.toArray()[i].getId().equals(inc_list.toArray()[j].getId())) {
                    flag = true;
                    break;
                }
            }
            if (!flag) {
                unpairedList.addTransaction(out_list.toArray()[i]);
            }
        }
        return unpairedList.toArray();
    }
}
