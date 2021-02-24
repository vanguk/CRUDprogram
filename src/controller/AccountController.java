package controller;

import model.Account;
import model.AccountStatus;
import model.Customer;
import repository.FileStorageManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AccountController {

    public final FileStorageManager<Account> accountFileStorageManager;

    public AccountController() {
        accountFileStorageManager = new FileStorageManager<>("src/resources/accounts.txt", Account.class);
    }

    public List<Account> showAllAccounts() {
        List<Account> list = new ArrayList<>();
        for (Map.Entry<Integer, Account> entry : accountFileStorageManager.readAll().entrySet()) {
            list.add(entry.getValue());
        }
        return list;
    }

    public int checkAccountStatus(Account account) {
        if (account.getAccountStatus() == AccountStatus.ACTIVE) return 1;
        else if (account.getAccountStatus() == AccountStatus.DELETED) return 0;
        else return -1;
    }

    /**
     * Need use only {@link view.AccountMenu}
     *
     * @param accountId
     */
    public void removeAccountFromFile(int accountId) {
        Customer customer = CustomerController.getInstance().withContains(accountId);
        if (customer != null) {
            CustomerController.getInstance().deleteAccount(customer.getId(), accountId);
        }
        accountFileStorageManager.deleteBy(accountId);
    }
}
