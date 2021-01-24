package view;

import java.util.Scanner;

public class CommandLineInterface {
    public static void main(String[] args) {
//        Пользователь в
//        консоли должен иметь возможность
//        создания,
//        получения,
//        редактирования и
//        удаления данных.
        System.out.println("-------------------------------------------------");
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите команду:");
        String command = scanner.nextLine();
        System.out.println("Введите команду:" + command);
        String[] str = command.split(" ");

        System.out.println("-------------------------------------------------");
    }
}
