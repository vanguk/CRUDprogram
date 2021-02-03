package model;

import java.io.Serializable;

public enum TransactionStatus implements Serializable {
    FINISHED,
    REVIEW,
    BLOCKED,
    RETURNED;

}
