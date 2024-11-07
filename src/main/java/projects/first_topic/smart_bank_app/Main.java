package projects.first_topic.smart_bank_app;

import projects.first_topic.smart_bank_app.constant.ProjectConstant;
import projects.first_topic.smart_bank_app.exception.DAOException;
import projects.first_topic.smart_bank_app.factory.DAOFactory;
import projects.first_topic.smart_bank_app.model.User;
import projects.first_topic.smart_bank_app.services.AccountService;
import projects.first_topic.smart_bank_app.services.LoanApplicationService;
import projects.first_topic.smart_bank_app.services.UserService;
import projects.first_topic.smart_bank_app.constant.ProjectConstant.*;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static UserService userService;
    private static LoanApplicationService loanService;
    private static AccountService accountService;
    public static void main(String[] args) {

        try {
            // Initialize services
            DAOFactory daoFactory = DAOFactory.getDAOFactory(ProjectConstant.MYSQL);
            userService = new UserService(daoFactory);
            loanService = new LoanApplicationService(daoFactory);
            accountService = new AccountService(daoFactory);

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
                        System.out.println("You selected 'Manage user'");
                        // Add code for Option 2
                        break;
                    case "3":
                        System.out.println("You selected 'Create account'");
                        // Add code for Option 3
                        break;
                    case "4":
                        System.out.println("You selected 'View account'");
                        // Add code for Option 3
                        break;
                    case "5":
                        System.out.println("You selected 'Manage account'");
                        // Add code for Option 3
                        break;
                    case "6":
                        System.out.println("You selected 'Apply for loan'");
                        // Add code for Option 3
                        applyForLoanMenu();
                        break;
                    case "7":
                        System.out.println("You selected 'Transaction log'");
                        // Add code for Option 3
                        break;
                    case "q":
                        System.out.println("Exiting the program. Goodbye!");
                        break;
                    default:
                        System.out.println("Invalid option. Please try again.");
                }
            } while (!userInput.equals("q"));
        } catch (DAOException e) {
            System.out.println("Error initializing services: " + e.getMessage());
            return;
        }
        scanner.close();
    }
    private static void applyForLoanMenu() {
        try {
            System.out.println("\n=== Loan Application Form ===");

            // Get user ID
            System.out.print("Enter user ID: ");
            Integer userId = Integer.parseInt(scanner.nextLine().trim());

            // Validate user exists
            User user = UserService.getUser(userId);
            if (user == null) {
                System.out.println("Error: User not found");
                return;
            }

            // Get loan type
            System.out.println("\nAvailable loan types:");
            System.out.println("1. Personal Loan");
            System.out.println("2. Home Loan");
            System.out.println("3. Auto Loan");
            System.out.println("4. Business Loan");
            System.out.print("Select loan type (1-4): ");

            String loanType;
            switch (scanner.nextLine().trim()) {
                case "1" -> loanType = "PERSONAL";
                case "2" -> loanType = "HOME";
                case "3" -> loanType = "AUTO";
                case "4" -> loanType = "BUSINESS";
                default -> {
                    System.out.println("Invalid loan type selected");
                    return;
                }
            }

            // Get loan amount
            System.out.print("Enter loan amount: ");
            double amount = Double.parseDouble(scanner.nextLine().trim());
            if (amount <= 0) {
                System.out.println("Error: Loan amount must be positive");
                return;
            }

            // Get dates
            System.out.print("Enter start date (YYYY-MM-DD): ");
            String startDate = scanner.nextLine().trim();
            System.out.print("Enter end date (YYYY-MM-DD): ");
            String endDate = scanner.nextLine().trim();

            // Submit application
            LoanApplicationService.applyForLoan(userId, loanType, amount, startDate, endDate);

            System.out.println("Loan application processed successfully!");

        } catch (NumberFormatException e) {
            System.out.println("Error: Please enter valid numbers");
        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    public static void createUser() {
        try {
            System.out.println("You selected 'Create user'");
            System.out.println("Create User Form");

            String firstName = getValidInput("First name", "[a-zA-Z]{2,30}", "First name must be 2-30 alphabetic characters");
            String lastName = getValidInput("Last name", "[a-zA-Z]{2,30}", "Last name must be 2-30 alphabetic characters");
            String email = getValidInput("Email", "^[A-Za-z0-9+_.-]+@(.+)$", "Invalid email format");
            String username = getValidInput("Username", "^[a-zA-Z0-9_]{3,20}$", "Username must be 3-20 alphanumeric characters or underscores");
            String password = getValidInput("Password", ".{8,}", "Password must be at least 8 characters long");
            String phoneNumber = getValidInput("Phone number", "^\\d{10}$", "Phone number must be 10 digits");
            int creditScore = getValidIntInput("Credit score", 300, 850);
            double annualIncome = getValidDoubleInput("Annual income", 0, Double.MAX_VALUE);

            String userType = "NEW_USER";
            LocalDateTime registrationDate = LocalDateTime.now();

            User user = new User(username, userType, password, firstName, lastName, phoneNumber, email, creditScore, annualIncome, registrationDate);
            UserService userService = new UserService(DAOFactory.getDAOFactory(ProjectConstant.MYSQL));
            userService.createUser(user);

            System.out.println("User created successfully!");
        } catch (SQLException e) {
            System.out.println("Error creating user: " + e.getMessage());
        }
    }

    private static String getValidInput(String prompt, String regex, String errorMessage) {
        while (true) {
            System.out.print(prompt + ": ");
            String input = scanner.nextLine().trim();
            if (Pattern.matches(regex, input)) {
                return input;
            }
            System.out.println(errorMessage);
        }
    }

    private static int getValidIntInput(String prompt, int min, int max) {
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

    private static double getValidDoubleInput(String prompt, double min, double max) {
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
