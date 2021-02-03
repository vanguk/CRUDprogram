package model;

import repository.FileModel;
import repository.FileStorageManager;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;
import java.util.Random;

public class Account implements FileModel, Serializable {
    private int accountId;
    private BigDecimal balance;
    private AccountStatus accountStatus;

    public static Account getAccountById(Integer id) {
        FileStorageManager<Account> fileStorageManager = new FileStorageManager<>("src/resources/accounts.txt", Account.class);
        return fileStorageManager.readBy(id);
    }

    public Account(int accountNumber, String balance, AccountStatus accountStatus) {
        this.accountId = accountNumber;
        this.balance = new BigDecimal(balance);
        this.accountStatus = accountStatus;
    }

    public Account() {
        this.accountId = new Random().nextInt(10000);
        this.balance = new BigDecimal(0L);
        this.accountStatus = AccountStatus.ACTIVE;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public AccountStatus getAccountStatus() {
        return this.accountStatus;
    }

    public void setAccountStatus(model.AccountStatus accountStatus) {
        this.accountStatus = accountStatus;
    }


    public int getId() {
        return accountId;
    }

    @Override
    public String toString() {
        return "AccountId= " + accountId + " balance= " + balance + " accountStatus= " + accountStatus + "\n";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return accountId == account.accountId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountId);
    }

}

