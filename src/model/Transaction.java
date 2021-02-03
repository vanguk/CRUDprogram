package model;

import repository.FileModel;
import repository.FileStorageManager;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;

public class Transaction implements FileModel, Serializable {
    int transactionId;
    BigDecimal amount;
    Account account;
    Date created;
    TransactionStatus transactionStatus;

    public Transaction(String str) {
        String[] token = str.split(";");
        this.transactionId = Integer.parseInt(token[0]);
        this.amount = new BigDecimal(token[1]);
        this.account = new FileStorageManager<>("src/resources/accounts.txt", Account.class).readBy(Integer.parseInt(token[2]));
        try {
            this.created = DateFormat.getDateInstance().parse(token[3]);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        this.transactionStatus = TransactionStatus.valueOf(token[4]);
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public TransactionStatus getTransactionStatus() {
        return transactionStatus;
    }

    public void setTransactionStatus(TransactionStatus transactionStatus) {
        this.transactionStatus = transactionStatus;
    }

    public int getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(int transactionId) {
        this.transactionId = transactionId;
    }

    @Override
    public int getId() {
        return transactionId;
    }

}
