package view;

import java.util.Scanner;

public class UserInputUtils {
    private static final Scanner scanner = new Scanner(System.in);

    public static int getIntInput(String prompt) {
        System.out.print(prompt);
        return scanner.nextInt();
    }

    public static String getStringInput(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }

    public static void println(String prompt) {
        System.out.println(prompt);
    }


}
