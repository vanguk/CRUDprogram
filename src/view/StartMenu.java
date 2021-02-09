package view;

import utilClass.Validator;

public class StartMenu {
    public static void startMenu() {
        System.out.println("Please select: \n 1 - Customer menu \n 2 - Account menu \n 0 - Exit");
        int number = Validator.validateInt();
        if (1 == number) {
            new CustomerMenu().menu();
            startMenu();
        } else if (2 == number) {
            new AccountMenu().accountMenu();
            startMenu();
        } else if (0 == number) {
            System.exit(0);
        } else {
            System.out.println("Menu item selected incorrectly");
            startMenu();
        }

    }
}
