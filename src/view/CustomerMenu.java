package view;

import controller.CustomerController;
import utilClass.Validator;
import java.util.Scanner;

public class CustomerMenu {

    public void menu() {
        System.out.println("Please select: \n" +
                " 1 - Create customer \n" +
                " 2 - Delete customer \n" +
                " 3 - Find customer \n" +
                " 4 - Show all customer \n" +
                " 9 - Back \n" +
                " 0 - Exit");
        Scanner scanner = new Scanner(System.in);
            int number = Validator.validateInt();
            if (1 == number) {
                System.out.println("Enter first name");
                String firstName = Validator.validateString();
                System.out.println("Enter last name");
                String lastName = Validator.validateString();
                System.out.println("Enter customer ID");
                int customerID = Validator.validateInt();
                System.out.println("Account " + CustomerController.getInstance().createNewCustomer(firstName,lastName,customerID) + " created");
                menu();
            } else if (2 == number) {
                System.out.println("Enter ID customer which should be removed");
                if (scanner.hasNextInt()) {
                    if (CustomerController.getInstance().deleteCustomer(Validator.validateInt())) {
                        System.out.println("Customer removed");
                        menu();
                    }
                }
            } else if (3 == number) {
                System.out.println("Enter customer Id");
                int num = Validator.validateInt();
                if (CustomerController.getInstance().getCustomerFileStorageManager().checkObject(num)) {
                    new OneCustomerMenu().menuOneCustomer(num);
                    menu();
                }else {
                    System.out.println("Customer not found");
                    menu();
                }

            } else if (4 == number) {
                System.out.println("ALL CUSTOMERS: \n");
                System.out.println(CustomerController.getInstance().getAllCustomer());
                menu();
            } else if (9 == number) {
                StartMenu.startMenu();
            } else if (0 == number) {
                System.exit(0);
            }else {
                System.out.println("Menu item selected incorrectly");
                menu();
            }
    }
}

