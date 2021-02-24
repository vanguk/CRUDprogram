package model;

import repository.FileModel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Customer implements FileModel, Serializable {
    private int id;
    private String firstName;
    private String lastName;
    private List<Account> accounts;

    public Customer(int customerId) {
        this.id = customerId;
        this.accounts = new ArrayList<>();
    }


    public int getId() {
        return id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }

    @Override
    public String toString() {
        return " Id: " + id + " First name: " + firstName + " Last name: " + lastName + "\n"
                + " All accounts customer: " + "\n" + accounts + "\n";
    }

}
