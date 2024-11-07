package projects.first_topic.smart_bank_app.util;

import java.util.Scanner;
import java.util.regex.Pattern;

import static projects.first_topic.smart_bank_app.commandline.Main.*;

public class InputSanitation {

    public static String getValidInput(String prompt, String regex, String errorMessage) {
        while (true) {
            System.out.print(prompt + ": ");
            String input = scanner.nextLine().trim();
            if (Pattern.matches(regex, input)) {
                return input;
            }
            System.out.println(errorMessage);
        }
    }

    public static String getValidInputOrQ(String prompt, String regex, String errorMessage) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print(prompt + ": ");
            String input = scanner.nextLine().trim();
            if (input.equalsIgnoreCase("q")) return "q";
            if (input.matches(regex)) {
                return input;
            }
            System.out.println(errorMessage);
        }
    }

    public static int getValidIntInput(String prompt, int min, int max) {
        while (true) {
            System.out.print(prompt + ": ");
            try {
                int input = Integer.parseInt(scanner.nextLine().trim());
                if (input >= min && input <= max) {
                    return input;
                }
                System.out.println("Please enter a number between " + min + " and " + max);
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number");
            }
        }
    }

    public static double getValidDoubleInput(String prompt, double min, double max) {
        while (true) {
            System.out.print(prompt + ": ");
            try {
                double input = Double.parseDouble(scanner.nextLine().trim());
                if (input >= min && input <= max) {
                    return input;
                }
                System.out.println("Please enter a number between " + min + " and " + max);
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number");
            }
        }
    }

}
