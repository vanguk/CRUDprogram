package model;

import repository.FileModel;

import java.math.BigDecimal;
import java.util.Random;

public class Account implements FileModel {
    private int accountId;
    private BigDecimal balance;
    private AccountStatus accountStatus;

    public Account(int accountNumber, String balance, AccountStatus accountStatus) {
        this.accountId = accountNumber;
        this.balance = new BigDecimal(balance);
        this.accountStatus = accountStatus;
    }

    public Account(String str) {
        String[] token = str.split(";");
        this.accountId = Integer.parseInt(token[0]);
        this.balance = new BigDecimal(token[1]);
        this.accountStatus = AccountStatus.valueOf(token[2]);
    }

    public Account() {
        this.accountId = new Random().nextInt(10000);
        this.balance = new BigDecimal(0L);
        this.accountStatus = AccountStatus.FINISHED;
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

    @Override
    public String toString() {
        return "Account{" +
                "accountId=" + accountId +
                ", balance=" + balance +
                ", accountStatus=" + accountStatus +
                '}';
    }

    public int getId() {
        return accountId;
    }


    public String getStringForFile() {
        return getAccountId() + ";" + getBalance() + ";" + AccountStatus.ACTIVE;
    }
}
