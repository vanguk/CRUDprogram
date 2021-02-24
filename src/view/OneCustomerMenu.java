package view;

import controller.CustomerController;
import utilClass.Validator;
import java.util.Scanner;

public class OneCustomerMenu {

    public void menuOneCustomer(int customerId) {

        System.out.println("Please select: \n" +
                " 1 - Change FirstName customer \n" +
                " 2 - Change LastName customer \n" +
                " 3 - Delete account \n" +
                " 4 - Create new account \n" +
                " 5 - Show all accounts \n" +
                " 6 - Transfer money \n" +
                " 9 - Back \n" +
                " 0 - Exit");
        Scanner scanner = new Scanner(System.in);
        int number = Validator.validateInt();
        if (1 == number) {
            System.out.println("Enter new FirstName");
            CustomerController.getInstance().changeFirstNameCustomer(customerId, Validator.validateString());
            menuOneCustomer(customerId);
        } else if (2 == number) {
            System.out.println("Enter new LastName");
            CustomerController.getInstance().changeLastNameCustomer(customerId, Validator.validateString());
            menuOneCustomer(customerId);
        } else if (3 == number) {
            System.out.println("Which account id should delete?");
            CustomerController.getInstance().deleteAccount(customerId, Validator.validateInt());
            menuOneCustomer(customerId);
        } else if (4 == number) {
            CustomerController.getInstance().addAccountForCustomer(customerId);
            menuOneCustomer(customerId);
        } else if (5 == number) {
            System.out.println("ALL ACCOUNTS");
            System.out.println(CustomerController.getInstance().getAllAccounts(customerId));
            menuOneCustomer(customerId);
        } else if (6 == number) {
            System.out.println("1 - Transfer to account \n 2 - Debit to account \n");
            int number1 = Validator.validateInt();
            if (1 == number1) {
                CustomerController.getInstance().transferMoney(customerId, true);
                menuOneCustomer(customerId);
            } else if (2 == number1) {
                CustomerController.getInstance().transferMoney(customerId, false);
                menuOneCustomer(customerId);
            }

        } else if (9 == number) {
            new CustomerMenu().menu();
        } else if (0 == number) {
            scanner.close();
            System.exit(0);
        } else {
            System.out.println("Menu item selected incorrectly");
            menuOneCustomer(customerId);
        }
    }
}
