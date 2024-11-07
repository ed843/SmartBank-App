package projects.first_topic.smart_bank_app.frontend;

import projects.first_topic.smart_bank_app.constant.ProjectConstant;
import projects.first_topic.smart_bank_app.factory.DAOFactory;
import projects.first_topic.smart_bank_app.model.User;
import projects.first_topic.smart_bank_app.services.UserService;

import java.sql.SQLException;
import java.util.Scanner;
import java.util.regex.Pattern;

import static projects.first_topic.smart_bank_app.frontend.userutil.AccountManager.*;
import static projects.first_topic.smart_bank_app.frontend.userutil.UserManager.*;

public class Main {
    public static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        String userInput;

        do {
            // Display menu options
            System.out.println("\nPlease select an option:");
            System.out.println("1. Create user");
            System.out.println("2. Manage user");
            System.out.println("3. Create account");
            System.out.println("4. View account");
            System.out.println("5. Manage account");
            System.out.println("6. Apply for loan");
            System.out.println("7. Transaction log");
            System.out.println("Q. Quit");
            System.out.print("Enter your choice: ");

            // Get user input
            userInput = scanner.nextLine().trim().toLowerCase();

            // Process user input
            switch (userInput) {
                case "1":
                    createUser();
                    break;
                case "2":
                    manageUser();
                    break;
                case "3":
                    createAccount();
                    break;
                case "4":
                    viewAccount();
                    break;
                case "5":
                    manageAccount();
                    break;
                case "6":
                    System.out.println("You selected 'Apply for loan'");
                    // Add code for Option 3
                    break;
                case "7":
                    System.out.println("You selected 'Transaction log'");
                    // Add code for Option 3
                    break;
                case "q":
                    System.out.println("\nExiting the program. Goodbye!");
                    break;
                default:
                    System.out.println("\nInvalid option. Please try again.");
            }
        } while (!userInput.equals("q"));

        scanner.close();
    }

    // Helper functions

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

    /**
     * Authenticates a user and returns their user ID.
     *
     * @return The user ID if authentication is successful, -1 if the user chooses to quit, or -2 if authentication fails.
     */
    public static int manageUserAuthentication() {
        UserService userService;
        try {
            userService = new UserService(DAOFactory.getDAOFactory(ProjectConstant.MYSQL));
        } catch (SQLException e) {
            System.out.println("Error initializing UserService: " + e.getMessage());
            return -2;
        }

        System.out.println("Please login: (enter 'q' at any prompt to go back)");

        while (true) {
            String username = getValidInputOrQ("Username", "^[a-zA-Z0-9_]{3,20}$", "Username must be 3-20 alphanumeric characters or underscores");
            if (username.equalsIgnoreCase("q")) return -1;

            String password = getValidInputOrQ("Password", ".{8,}", "Password must be at least 8 characters long");
            if (password.equalsIgnoreCase("q")) return -1;

            try {
                User user = userService.getUserByLogin(username, password);
                if (user != null) {
                    System.out.println("Login successful!");
                    return user.getUser_id();
                } else {
                    System.out.println("Invalid username or password. Please try again.");
                }
            } catch (SQLException e) {
                System.out.println("Error during authentication: " + e.getMessage());
            }
        }
    }
}
