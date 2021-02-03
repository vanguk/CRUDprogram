package view;

import java.util.Scanner;

public class StartMenu {
    public static void startMenu() {
        System.out.println("Please select: \n 1 - Customer menu \n 2 - Account menu \n 0 - Exit");
            Scanner scanner = new Scanner(System.in);
            if (scanner.hasNextInt()) {
                int number = scanner.nextInt();
                switch (number) {
                    case (1):
                        CustomerMenu.menu();
                        startMenu();
                        break;
                    case (2):
                        break;
                    case (0):
                        System.exit(0);
                }

            }
        }
}
