package model;

import java.math.BigDecimal;
import java.util.Date;

public class Transaction {
    BigDecimal amount;
    Account account;
    Date created;
    TransactionStatus transactionStatus;
}
