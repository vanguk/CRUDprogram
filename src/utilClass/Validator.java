package utilClass;

import java.util.Scanner;
import java.util.regex.Pattern;

public class Validator {
    static Scanner scanner = new Scanner(System.in);

    public static String validateString() {
        String str = scanner.nextLine();
        if (Pattern.matches("[a-zA-Z]+", str)) {
            return str;
        } else {
            System.out.println("Incorrect input \n Re-Enter");
            return validateString();
        }
    }

    public static int validateInt() {
        String str = scanner.nextLine();
        if (Pattern.matches("[0-9]+", str)) {
            return Integer.parseInt(str);
        } else {
            System.out.println("Incorrect input \n Re-Enter");
            return validateInt();
        }
    }
}
