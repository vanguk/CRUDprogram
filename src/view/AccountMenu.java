package view;

import controller.AccountController;
import utilClass.Validator;

import java.util.Scanner;

public class AccountMenu {

    AccountController accountController = new AccountController();

    public void accountMenu() {
        System.out.println("Select: \n" +
                " 1 - Show All account \n" +
                " 2 - Remove account from file \n" +
                " 9 - Back \n" +
                " 0 - Exit");
            int number = Validator.validateInt();
            if (1 == number) {
                if (accountController.showAllAccounts().isEmpty()) {
                    System.out.println("Accounts not found");
                    accountMenu();
                } else {
                    System.out.println(accountController.showAllAccounts());
                    accountMenu();
                }

            } else if (2 == number) {
                System.out.println("Enter account id");
                accountController.removeAccountFromFile(Validator.validateInt());
                accountMenu();
            } else if (9 == number) {
                StartMenu.startMenu();
            } else if (0 == number) {
                System.exit(0);
            }
    }
}
