package model;

import repository.FileModel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Customer implements FileModel, Serializable {
    private int id;
    private String firstName;
    private String lastName;
    private List<Account> accounts;

    public Customer() {
        this.id = new Random().nextInt(10000);
        this.accounts = new ArrayList<>();
    }

    public void addAccount(Account account) {
        this.accounts.add(account);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
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
