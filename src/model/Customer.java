package model;

import repository.FileModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Customer implements FileModel {
    private int id;
    private String firstName;
    private String lastName;
    private List<Account> accounts;


    public Customer() {
        this(0, "", "", new ArrayList<Account>());
    }

    public Customer(String str) {
        String[] token = str.split(";");
        this.id = Integer.parseInt(token[0]);
        this.firstName = token[1];
        this.lastName = token[2];
        this.accounts = new ArrayList<>();
        String someString = token[3].replaceAll("[^0-9]", "");
        String[] masIdAccounts = someString.split("");
        for (int i = 0; i < masIdAccounts.length; i++) {
            this.addAccount(AccountsDAOImpl.getAccountById(Integer.parseInt(masIdAccounts[i])));
        }
    }

    public Customer(int id, String firstName, String lastName, List<Account> accounts) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.accounts = accounts;
    }

    public Customer(String firstName, String lastName) {
        this.id = new Random().nextInt(10000);
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Customer(int id, String firstName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public void addAccount(Account account) {
        this.accounts.add(account);
    }

    public int getId() {
        return id;
    }

    public String getStringForFile() {
        return getId() + ";"
                + getFirstName() + ";"
                + getLastName() + ";"
                + getAccounts();
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

    private String allAccountToString() {
        String resultString = "";
        for (Account account : accounts) {
            resultString += "Id account: " + account.getAccountId() + "\n"
                    + "Balance account: " + account.getBalance() + "\n"
                    + "Account status: " + account.getAccountStatus() + "\n";
        }
        return resultString;
    }

    @Override
    public String toString() {
        return "Id customer: " + id + "\n"
                + "First name customer: " + firstName + "\n"
                + "Last name customer: " + lastName + "\n"
                + "All accounts customer: " + "\n" + allAccountToString() + "\n";
    }

}
