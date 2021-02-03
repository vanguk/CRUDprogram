package view;

import controller.CustomerController;
import model.Customer;

import java.util.Scanner;

public class CustomerMenu {
    public static void menu() {
        System.out.println("Please select: \n" +
                " 1 - Create customer \n" +
                " 2 - Delete customer \n" +
                " 3 - Find customer \n" +
                " 4 - Show all customer \n" +
                " 9 - Back \n" +
                " 0 - Exit");
        Scanner scanner = new Scanner(System.in);
        if (scanner.hasNextInt()) {
            int number = scanner.nextInt();
            if (1 == number) {
                System.out.println("Account " + CustomerController.createNewCustomer() + " created");
                menu();
            } else if (2 == number) {
                System.out.println("Enter ID customer which should be removed");
                if (scanner.hasNextInt()) {
                    if (CustomerController.deleteCustomer(scanner.nextInt())) {
                        System.out.println("Customer removed");
                        menu();
                    }
                }
            } else if (3 == number) {
                System.out.println("Enter customer Id");
                OneCustomerMenu.menuOneCustomer(scanner.nextInt());
                menu();

            } else if (4 == number) {
                System.out.println("ALL CUSTOMERS: \n");
                System.out.println(CustomerController.showAllCustomer());
                menu();
            } else if (9 == number) {
                StartMenu.startMenu();
            } else if (0 == number) {
                System.exit(0);
            }
        }


    }
}

