package controller;

import model.*;
import repository.FileStorageManager;
import utilClass.Validator;

import javax.swing.text.html.HTMLDocument;
import java.util.*;

public class CustomerController {
    private static CustomerController instance;
    final AccountController accountController;
    final FileStorageManager<Customer> customerFileStorageManager;
    final TransactionController transactionController;

    private CustomerController() {
        this.customerFileStorageManager = new FileStorageManager<>("src/resources/customers.txt", Customer.class);
        this.accountController = new AccountController();
        this.transactionController = new TransactionController();
    }

    public static CustomerController getInstance() {
        if (instance == null) {
            return new CustomerController();
        }
        return instance;
    }

    public AccountController getAccountController() {
        return accountController;
    }

    public FileStorageManager<Customer> getCustomerFileStorageManager() {
        return customerFileStorageManager;
    }

    public TransactionController getTransactionController() {
        return transactionController;
    }

    /**
     * Create and write new customer
     *
     * @return
     */

    public Customer createNewCustomer(String firstName, String lastName) {
        Customer customer = new Customer();
        customer.setFirstName(firstName);
        customer.setLastName(lastName);
        customerFileStorageManager.saveObject(customer);
        return customer;
    }

    /**
     * Delete customer
     *
     * @param customerId
     * @return
     */
    public boolean deleteCustomer(int customerId) {
        if (customerFileStorageManager.checkObject(customerId)) {
            customerFileStorageManager.deleteBy(customerId);
            return true;
        } else {
            System.out.println("Customer not found");
            return false;
        }
    }

    /**
     * Create new account by the customer
     *
     * @param customerId
     */
    public void addAccountForCustomer(int customerId) {
        if (customerFileStorageManager.checkObject(customerId)) {
            Customer customer = customerFileStorageManager.readObject(customerId);
            Account account = new Account();
            accountController.accountFileStorageManager.saveObject(account);
            customer.getAccounts().add(account);
            customerFileStorageManager.saveObject(customer);
        } else {
            System.out.println("Customer not found");
        }
    }

    public List<Account> showAllAccounts(int customerId) {

        List<Account> list = new ArrayList<>();
        if (customerFileStorageManager.checkObject(customerId)) {
            Customer customer = customerFileStorageManager.readObject(customerId);
            if (customer.getAccounts() != null) {
                list.addAll(customer.getAccounts());
            }
            return list;
        } else {
            System.out.println("Customer not found");
            return list;
        }
    }


    public List showAllCustomer() {
        return customerFileStorageManager.showAllObjects();
    }

    public Customer withContains(int accountId) {
        Customer customer = null;
        for (Map.Entry<Integer, Customer> entry : customerFileStorageManager.readAll().entrySet()) {
            for (Account account : entry.getValue().getAccounts()) {
                if (account.getAccountId() == accountId) {
                    customer = entry.getValue();
                }
            }
        }
        return customer;
    }


    /**
     * Set new fistName for customer
     *
     * @param customerId
     */
    public void changeFirstNameCustomer(int customerId, String firstName) {
        if (customerFileStorageManager.checkObject(customerId)) {
            Customer customer = customerFileStorageManager.readObject(customerId);
            customer.setFirstName(firstName);
            customerFileStorageManager.saveObject(customer);
        } else {
            System.out.println("Customer not found");
        }
    }

    /**
     * Set new lastName for customer
     *
     * @param customerId
     */
    public void changeLastNameCustomer(int customerId, String lastName) {
        if (customerFileStorageManager.checkObject(customerId)) {
            Customer customer = customerFileStorageManager.readObject(customerId);
            customer.setLastName(lastName);
            customerFileStorageManager.saveObject(customer);
        } else {
            System.out.println("Customer not found");
        }
    }

    /**
     * This method dont remove account from the customer.
     *
     * @param customerId
     */
    public void deleteAccount(int customerId, int accountId) {
        if (customerFileStorageManager.checkObject(customerId) && accountController.accountFileStorageManager.checkObject(accountId)) {
            Customer customer = customerFileStorageManager.readObject(customerId);
            List<Account> list = customer.getAccounts();
            Iterator<Account> itr = list.iterator();
            while (itr.hasNext()) {
                Account account = itr.next();
                if (account.getId() == accountId) {
                    account.setAccountStatus(AccountStatus.DELETED);
                    accountController.accountFileStorageManager.saveObject(account);
                    itr.remove();
                }
            }

            customer.setAccounts(list);
            customerFileStorageManager.saveObject(customer);
        } else {
            System.out.println("Customer or account not found");
        }
    }

    public boolean transferMoney(int customerId, boolean debitOrСredit) {
        if (customerFileStorageManager.checkObject(customerId)) {
            Customer customer = customerFileStorageManager.readObject(customerId);
            Account account = null;
            Transaction transaction = null;
            int number, amount;
            Scanner scanner = new Scanner(System.in);
            System.out.println(customer.getAccounts());
            System.out.println("Enter id account from which to transfer\n");
            number = Validator.validateInt();
            account = accountController.accountFileStorageManager.readObject(number);
            System.out.println("Enter transfer amount");
            amount = Validator.validateInt();
            transaction = new Transaction(amount, account);
            transactionController.transactionFileStorageManager.saveObject(transaction);
            if (debitOrСredit) {
                return credit(account, transaction, customer);
            } else
                return debit(account, transaction, customer);
        }
        return true;
    }

    private boolean credit(Account account, Transaction transaction, Customer customer) {
        if (accountController.checkAccountStatus(account) == 1) {
            account.addMoney(transaction.getAmount());
            List<Account> list = customer.getAccounts();
            list.removeIf(account1 -> account1.getAccountId() == account.getAccountId());
            list.add(account);
            System.out.println(list);
            customer.setAccounts(list);
            accountController.accountFileStorageManager.saveObject(account);
            customerFileStorageManager.saveObject(customer);
            transaction.setTransactionStatus(TransactionStatus.FINISHED);
            transactionController.transactionFileStorageManager.saveObject(transaction);
            return true;
        } else if (accountController.checkAccountStatus(account) == 0) {
            transaction.setTransactionStatus(TransactionStatus.BLOCKED);
            transactionController.transactionFileStorageManager.saveObject(transaction);
            System.out.println("This account " + account.getAccountStatus());
            return false;
        }
        return false;
    }

    private boolean debit(Account account, Transaction transaction, Customer customer) {
        if (accountController.checkAccountStatus(account) == 1) {
            if (account.getBalance().subtract(transaction.getAmount()).doubleValue() >= 0) {
                account.minusMoney(transaction.getAmount());
                List<Account> list = customer.getAccounts();
                list.removeIf(account1 -> account1.getAccountId() == account.getAccountId());
                list.add(account);
                System.out.println(list);
                customer.setAccounts(list);
                accountController.accountFileStorageManager.saveObject(account);
                customerFileStorageManager.saveObject(customer);
                transaction.setTransactionStatus(TransactionStatus.FINISHED);
                transactionController.transactionFileStorageManager.saveObject(transaction);
                return true;
            } else {
                transaction.setTransactionStatus(TransactionStatus.RETURNED);
                transactionController.transactionFileStorageManager.saveObject(transaction);
                System.out.println("Not enough funds");
            }

        } else if (accountController.checkAccountStatus(account) == 0) {
            transaction.setTransactionStatus(TransactionStatus.BLOCKED);
            transactionController.transactionFileStorageManager.saveObject(transaction);
            System.out.println("This account " + account.getAccountStatus());
            return false;
        }
        return false;
    }
}
