package model;

import java.math.BigDecimal;

public class Account {
    private int accountNumber;
    private BigDecimal balance;
    private AccountStatus accountStatus;

    public Account(int accountNumber, BigDecimal balance, AccountStatus accountStatus) {
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.accountStatus = accountStatus;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(int accountNumber) {
        this.accountNumber = accountNumber;
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
}
