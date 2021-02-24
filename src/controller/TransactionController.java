package controller;

import model.Transaction;
import repository.FileStorageManager;

import java.util.List;

public class TransactionController {
    final FileStorageManager<Transaction> transactionFileStorageManager;

    public TransactionController() {
        this.transactionFileStorageManager = new FileStorageManager<Transaction>("src/resources/transactions.txt", Transaction.class);
    }
    public List getAllTransactions(){
        return transactionFileStorageManager.showAllObjects();
    }
}
