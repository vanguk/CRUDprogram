package model;

import repository.FileModel;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;
import java.util.Random;

public class Transaction implements FileModel, Serializable {
    int transactionId;
    BigDecimal amount;
    Account account;
    Date created;
    TransactionStatus transactionStatus;

    public Transaction(int amount , Account account) {
        this.transactionId = new Random().nextInt(10000);
        this.amount = new BigDecimal(amount);
        this.account = account;
        this.created = new Date();
        this.transactionStatus = TransactionStatus.REVIEW;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public void setTransactionStatus(TransactionStatus transactionStatus) {
        this.transactionStatus = transactionStatus;
    }

    @Override
    public int getId() {
        return transactionId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transaction that = (Transaction) o;
        return transactionId == that.transactionId && Objects.equals(amount, that.amount) && Objects.equals(account, that.account) && Objects.equals(created, that.created) && transactionStatus == that.transactionStatus;
    }

    @Override
    public int hashCode() {
        return Objects.hash(transactionId, amount, account, created, transactionStatus);
    }
}
