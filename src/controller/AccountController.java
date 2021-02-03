package controller;

import model.Account;
import repository.FileStorageManager;

public class AccountController {
    static FileStorageManager<Account> accountFileStorageManager = new FileStorageManager<>("src/resources/accounts.txt", Account.class);

    public static Account deleteAccount(int accountId) {
        return accountFileStorageManager.deSerializationFromFile(accountId);
    }
}
