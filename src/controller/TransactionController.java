package controller;

import model.Customer;
import model.Transaction;
import repository.FileStorageManager;

public class TransactionController {
    final FileStorageManager<Transaction> transactionFileStorageManager;

    public TransactionController() {
        this.transactionFileStorageManager = new FileStorageManager<Transaction>("src/resources/transactions.txt", Transaction.class);
    }

}
