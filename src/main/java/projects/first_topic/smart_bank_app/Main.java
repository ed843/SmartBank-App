package projects.first_topic.smart_bank_app;

import projects.first_topic.smart_bank_app.constant.ProjectConstant;
import projects.first_topic.smart_bank_app.factory.DAOFactory;
import projects.first_topic.smart_bank_app.model.User;
import projects.first_topic.smart_bank_app.services.AccountService;
import projects.first_topic.smart_bank_app.services.UserService;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);

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

        scanner.close();
    }

    public static void createUser() {
        try {
            System.out.println("You selected 'Create user'");
            System.out.println("Create User Form");

            String firstName = getValidInput("First name", "[a-zA-Z]{2,30}",
                    "First name must be 2-30 alphabetic characters");

            String lastName = getValidInput("Last name", "[a-zA-Z]{2,30}",
                    "Last name must be 2-30 alphabetic characters");

            String email = getValidInput("Email", "^[A-Za-z0-9+_.-]+@(.+)$",
                    "Invalid email format");

            String username = getValidInput("Username", "^[a-zA-Z0-9_]{3,20}$",
                    "Username must be 3-20 alphanumeric characters or underscores");

            String password = getValidInput("Password", ".{8,}",
                    "Password must be at least 8 characters long");

            String phoneNumber = getValidInput("Phone number", "^\\d{10}$",
                    "Phone number must be 10 digits");

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

    public static void manageUser() {
        System.out.println("You selected 'Manage user'");
        int userId = manageUserAuthentication();
        if (userId != -1) {

            String manageUserInput;

            do {
                System.out.println("Please pick one of the following options: ");
                System.out.println("1. Edit name");
                System.out.println("2. Edit username");
                System.out.println("3. Edit password");
                System.out.println("4. Edit membership");
                System.out.println("5. Edit phone number");
                System.out.println("6. Edit email");
                System.out.println("Q. Quit");
                System.out.print("Enter your choice: ");

                manageUserInput = scanner.nextLine().trim().toLowerCase();

                switch (manageUserInput) {
                    case "1":
                        System.out.println("You selected 'Edit name'");
                        break;
                    case "2":
                        System.out.println("You selected 'Edit username'");
                        break;
                    case "3":
                        editPassword(userId);
                        break;
                    case "4":
                        editUserType(userId);
                        break;
                    case "5":
                        System.out.println("You selected 'Edit phone number'");
                        break;
                    case "6":
                        System.out.println("You selected 'Edit email'");
                        break;

                }
            } while (!manageUserInput.equals("q"));
        }
    }

    public static void editPassword(int userId) {
        System.out.println("You selected 'Edit password'");
        String password = getValidInputOrQ("Enter your new password (type 'q' to cancel)", ".{8,}",
                "Password must be at least 8 characters long");
        if (password.equals("q")) return;
        try {
            UserService userService = new UserService(DAOFactory.getDAOFactory(ProjectConstant.MYSQL));
            User user = userService.getUser(userId);
            userService.updateUserPassword(user, password);
            System.out.println("Password successfully updated!");
        } catch (SQLException e) {
            System.out.println("Error updating password: " + e.getMessage());
        }
    }
    // 'NEW_USER', 'REWARD_USER', 'PLATINUM_USER', 'VIP'
    public static void editUserType(int userId) {
        System.out.println("You selected 'Edit membership'");
        System.out.println("Please select from the following options:");
        try {
            UserService userService = new UserService(DAOFactory.getDAOFactory(ProjectConstant.MYSQL));
            User user = userService.getUser(userId);
            switch(user.getUser_type()) {
                case "NEW_USER":

                    System.out.println("1. Rewards User (a $25 charge will be made)");
                    System.out.println("2. Platinum User (a $50 charge will be made)");
                    System.out.println("3. VIP User (a $75 charge will be made)");
                    System.out.println("Q. Quit");

                    String userInput;
                    // TODO: Charges are currently unimplemented
                    do {
                        userInput = scanner.nextLine().trim().toLowerCase();
                        switch (userInput) {
                            case "1":
                                userService.updateUserType(user, "REWARD_USER");
                                System.out.println("Successfully updated to 'Rewards User'");
                                return;
                            case "2":
                                userService.updateUserType(user, "PLATINUM_USER");
                                System.out.println("Successfully updated to 'Platinum User'");
                                return;
                            case "3":
                                userService.updateUserType(user, "VIP_USER");
                                System.out.println("Successfully updated to 'VIP User'");
                                return;
                        }
                    } while (!userInput.equals("q"));
                    break;
                case "REWARD_USER":
                    System.out.println("1. Downgrade to Regular User");
                    System.out.println("2. Platinum User (a $25 charge will be made)");
                    System.out.println("3. VIP User (a $50 charge will be made)");
                    System.out.println("Q. Quit");

                    String userInput1;

                    do {
                        userInput1 = scanner.nextLine().trim().toLowerCase();
                        switch (userInput1) {
                            case "1":
                                userService.updateUserType(user, "NEW_USER");
                                System.out.println("Successfully downgraded to 'Regular User'");
                                return;
                            case "2":
                                userService.updateUserType(user, "PLATINUM_USER");
                                System.out.println("Successfully upgraded to 'Platinum User'");
                                return;
                            case "3":
                                userService.updateUserType(user, "VIP_USER");
                                System.out.println("Successfully upgraded to 'VIP User'");
                                return;
                        }
                    } while (!userInput1.equals("q"));
                    break;
                case "PLATINUM_USER":
                    System.out.println("1. Downgrade to Regular User");
                    System.out.println("2. Downgrade to Rewards User");
                    System.out.println("3. VIP User (a $25 charge will be made)");
                    System.out.println("Q. Quit");

                    String userInput2;

                    do {
                        userInput2 = scanner.nextLine().trim().toLowerCase();
                        switch (userInput2) {
                            case "1":
                                userService.updateUserType(user, "NEW_USER");
                                System.out.println("Successfully downgraded to 'Regular User'");
                                return;
                            case "2":
                                userService.updateUserType(user, "REWARD_USER");
                                System.out.println("Successfully downgraded to 'Rewards User'");
                                return;
                            case "3":
                                userService.updateUserType(user, "VIP_USER");
                                System.out.println("Successfully upgraded to 'VIP User'");
                                return;
                        }
                    } while (!userInput2.equals("q"));
                    return;
                case "VIP":
                    System.out.println("1. Downgrade to Regular User");
                    System.out.println("2. Downgrade to Rewards User");
                    System.out.println("3. Downgrade to Platinum User");
                    System.out.println("Q. Quit");

                    String userInput3;

                    do {
                        userInput3 = scanner.nextLine().trim().toLowerCase();
                        switch (userInput3) {
                            case "1":
                                userService.updateUserType(user, "NEW_USER");
                                System.out.println("Successfully downgraded to 'Regular User'");
                                return;
                            case "2":
                                userService.updateUserType(user, "REWARD_USER");
                                System.out.println("Successfully downgraded to 'Rewards User'");
                                return;
                            case "3":
                                userService.updateUserType(user, "PLATINUM_USER");
                                System.out.println("Successfully downgraded to 'Platinum User'");
                                return;
                        }
                    } while (!userInput3.equals("q"));
                    break;

            }
        } catch (SQLException e) {
            System.out.println("Error updating password: " + e.getMessage());
        }
    }

    // Helper functions

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

    private static String getValidInputOrQ(String prompt, String regex, String errorMessage) {
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
