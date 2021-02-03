package controller;

import model.Account;
import model.Customer;
import repository.FileStorageManager;

import java.util.List;
import java.util.Scanner;

public class CustomerController {

    static FileStorageManager<Customer> customerFileStorageManager = new FileStorageManager<>("src/resources/customers.txt", Customer.class);
    static FileStorageManager<Account> accountFileStorageManager = new FileStorageManager<>("src/resources/accounts.txt", Account.class);

    public static Customer createNewCustomer() {

        Customer customer = new Customer();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter First name :");
        customer.setFirstName(scanner.nextLine());
        System.out.println("Enter Last name :");
        customer.setLastName(scanner.nextLine());
        customerFileStorageManager.saveObject(customer);
        return customer;
    }

    public static boolean deleteCustomer(int customerId) {
        customerFileStorageManager.deleteBy(customerId);
        return true;
    }

    public static void addAccountForCustomer(int customerId) {
        Customer customer = customerFileStorageManager.readBy(customerId);
        Account account = new Account();
        accountFileStorageManager.saveObject(account);
        customer.addAccount(account);
        customerFileStorageManager.saveObject(customer);
    }

    public static List showAllCustomer() {
        return customerFileStorageManager.showAllCustomer();
    }

    public static void changeFirstNameCustomer(int customerId) {
        Customer customer = customerFileStorageManager.deSerializationFromFile(customerId);
        Scanner scanner = new Scanner(System.in);
        customer.setFirstName(scanner.nextLine());
        customerFileStorageManager.saveObject(customer);
        scanner.close();
    }


    public static void changeLastNameCustomer(int customerId) {
        Customer customer = customerFileStorageManager.deSerializationFromFile(customerId);
        Scanner scanner = new Scanner(System.in);
        customer.setLastName(scanner.nextLine());
        customerFileStorageManager.saveObject(customer);
        scanner.close();
    }

    public static void deleteAccount(int customerId) {
        Customer customer = customerFileStorageManager.deSerializationFromFile(customerId);
        Scanner scanner = new Scanner(System.in);
        System.out.println(customer.getAccounts());
        System.out.println("Which account id should delete?");
        customer.getAccounts().remove(AccountController.deleteAccount(scanner.nextInt()));
        customerFileStorageManager.saveObject(customer);
        scanner.close();
    }
}
