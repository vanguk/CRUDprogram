package view;

import controller.CustomerController;

import java.util.Scanner;

public class OneCustomerMenu {
    public static void menuOneCustomer(int customerId) {

        System.out.println("Please select: \n" +
                " 1 - Change FirstName customer \n" +
                " 2 - Change LastName customer \n" +
                " 3 - Delete account \n" +
                " 4 - Create new account \n" +
                " 9 - Back \n" +
                " 0 - Exit");
        Scanner scanner = new Scanner(System.in);
        if (scanner.hasNextInt()) {
            int number = scanner.nextInt();
            if (1 == number) {
                System.out.println("Enter new FirstName");
                CustomerController.changeFirstNameCustomer(customerId);
                menuOneCustomer(customerId);
            } else if (2 == number) {
                System.out.println("Enter new LastName");
                CustomerController.changeLastNameCustomer(customerId);
                menuOneCustomer(customerId);
            } else if (3 == number) {
                CustomerController.deleteAccount(customerId);
                menuOneCustomer(customerId);
            } else if (4 == number) {
                System.out.println("Enter customer id");
                CustomerController.addAccountForCustomer(customerId);
                menuOneCustomer(customerId);
            } else if (9 == number) {
                CustomerMenu.menu();
            } else if (0 == number) {
                scanner.close();
                System.exit(0);
            }
        }
    }
}
