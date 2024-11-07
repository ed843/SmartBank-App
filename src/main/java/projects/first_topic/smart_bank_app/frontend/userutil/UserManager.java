package projects.first_topic.smart_bank_app.frontend.userutil;

import projects.first_topic.smart_bank_app.constant.ProjectConstant;
import projects.first_topic.smart_bank_app.factory.DAOFactory;
import projects.first_topic.smart_bank_app.model.Account;
import projects.first_topic.smart_bank_app.model.User;
import projects.first_topic.smart_bank_app.services.AccountService;
import projects.first_topic.smart_bank_app.services.UserService;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Objects;

import static projects.first_topic.smart_bank_app.frontend.Main.*;

public class UserManager {

    public static void createUser() {
        try {
            System.out.println("\nYou selected 'Create user'");
            System.out.println("--Create User Form--");

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

            System.out.println("\nUser created successfully!");
        } catch (SQLException e) {
            System.out.println("\nError creating user: " + e.getMessage());
        }
    }

    public static void manageUser() {
        System.out.println("\nYou selected 'Manage user'");
        int userId = manageUserAuthentication();
        if (userId != -1 && userId != -2) {

            String manageUserInput;

            do {
                System.out.println("\nPlease pick one of the following options: ");
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
                        editName(userId);
                        break;
                    case "2":
                        editUsername(userId);
                        break;
                    case "3":
                        editPassword(userId);
                        break;
                    case "4":
                        editUserType(userId);
                        break;
                    case "5":
                        editUserPhoneNumber(userId);
                        break;
                    case "6":
                        editUserEmail(userId);
                        break;
                    default:
                        System.out.println("\nInvalid option. Please try again.");

                }
            } while (!manageUserInput.equals("q"));
        }
    }

    private static void editName(int userId) {
        System.out.println("\nYou selected 'Edit name'");
        String newFirstName = getValidInputOrQ("Please enter your first name (type 'q' to cancel)", "[a-zA-Z]{2,30}",
                "First name must be 2-30 alphabetic characters");
        if (newFirstName.equals("q")) return;
        String newLastName = getValidInputOrQ("Please enter your last name (type 'q' to cancel)", "[a-zA-Z]{2,30}",
                "Last name must be 2-30 alphabetic characters");
        if (newLastName.equals("q")) return;
        try {
            UserService userService = new UserService(DAOFactory.getDAOFactory(ProjectConstant.MYSQL));
            User user = userService.getUser(userId);
            if (!Objects.equals(user.getFirst_name(), newFirstName)) {
                userService.updateFirstName(user, newFirstName);
            }
            if (!Objects.equals(user.getLast_name(), newLastName)) {
                userService.updateLastName(user, newLastName);
            }
            System.out.println("\nName successfully updated!");
        } catch (SQLException e) {
            System.out.println("\nError updating name: " + e.getMessage());
        }
    }

    private static void editUsername(int userId) {
        System.out.println("\nYou selected 'Edit username'");
        String newUsername = getValidInputOrQ("Enter your new username (type 'q' to cancel)", "^[a-zA-Z0-9_]{3,20}$",
                "Username must be 3-20 alphanumeric characters or underscores");
        if (newUsername.equals("q")) return;
        try {
            UserService userService = new UserService(DAOFactory.getDAOFactory(ProjectConstant.MYSQL));
            User user = userService.getUser(userId);
            userService.updateUserUsername(user, newUsername);
            System.out.println("\nUsername successfully updated!");
        } catch (SQLException e) {
            System.out.println("\nError updating username: " + e.getMessage());
        }
    }

    private static void editPassword(int userId) {
        System.out.println("\nYou selected 'Edit password'");
        String newPassword = getValidInputOrQ("Enter your new password (type 'q' to cancel)", ".{8,}",
                "Password must be at least 8 characters long");
        if (newPassword.equals("q")) return;
        try {
            UserService userService = new UserService(DAOFactory.getDAOFactory(ProjectConstant.MYSQL));
            User user = userService.getUser(userId);
            userService.updateUserPassword(user, newPassword);
            System.out.println("\nPassword successfully updated!");
        } catch (SQLException e) {
            System.out.println("\nError updating password: " + e.getMessage());
        }
    }

    // 'NEW_USER', 'REWARD_USER', 'PLATINUM_USER', 'VIP'
    private static void editUserType(int userId) {
        System.out.println("\nYou selected 'Edit membership'");
        System.out.println("Please select from the following options:");
        try {
            UserService userService = new UserService(DAOFactory.getDAOFactory(ProjectConstant.MYSQL));
            User user = userService.getUser(userId);
            Account account = null;
            AccountService accountService = null;
            String userInput;
            double rewards;
            double platinum;
            double vip;

            // TODO: transaction logs for editing user type

            switch(user.getUser_type()) {
                case "NEW_USER":
                    rewards = 25.0;
                    platinum = 50.0;
                    vip = 75.0;

                    do {
                        System.out.println("1. Rewards User (a $25 charge will be made)");
                        System.out.println("2. Platinum User (a $50 charge will be made)");
                        System.out.println("3. VIP User (a $75 charge will be made)");
                        System.out.println("Q. Quit");
                        System.out.print("Enter your choice: ");

                        userInput = scanner.nextLine().trim().toLowerCase();

                        if (!isValidOption(userInput) && !userInput.equals("q")) {
                            System.out.println("\nInvalid option. Please try again.\n");
                        }
                    } while (!isValidOption(userInput) && !userInput.equals("q"));

                    if (isValidOption(userInput)) {
                        try {
                            accountService = new AccountService(DAOFactory.getDAOFactory(ProjectConstant.MYSQL));
                            account = accountService.getAccountByUserId(userId);
                            if (account == null) {
                                System.out.println("\nNo account associated with current user. Please create an account.");
                                return;
                            }
                        } catch (SQLException e) {
                            System.out.println("\nError retrieving account information: " + e.getMessage());
                            return;
                        }
                    }

                    switch (userInput) {
                        case "1":
                            if (account.getBalance() < rewards) {
                                System.out.println("\nDeclined: Insufficient funds");
                                return;
                            }
                            account.setBalance(account.getBalance() - rewards);
                            accountService.updateAccountBalance(account, account.getBalance());
                            userService.updateUserType(user, "REWARD_USER");
                            System.out.println("\nSuccessfully updated to 'Rewards User'");
                            return;
                        case "2":
                            if (account.getBalance() < platinum) {
                                System.out.println("\nDeclined: Insufficient funds");
                                return;
                            }
                            account.setBalance(account.getBalance() - platinum);
                            accountService.updateAccountBalance(account, account.getBalance());
                            userService.updateUserType(user, "PLATINUM_USER");
                            System.out.println("\nSuccessfully updated to 'Platinum User'");
                            return;
                        case "3":
                            if (account.getBalance() < vip) {
                                System.out.println("\nDeclined: Insufficient funds");
                                return;
                            }
                            account.setBalance(account.getBalance() - vip);
                            accountService.updateAccountBalance(account, account.getBalance());
                            userService.updateUserType(user, "VIP_USER");
                            System.out.println("\nSuccessfully updated to 'VIP User'");
                            return;
                        case "q":
                            return;
                    }

                    break;
                case "REWARD_USER":
                    platinum = 25;
                    vip = 50;

                    do {
                        System.out.println("1. Downgrade to Regular User");
                        System.out.println("2. Platinum User (a $25 charge will be made)");
                        System.out.println("3. VIP User (a $50 charge will be made)");
                        System.out.println("Q. Quit");
                        System.out.print("Enter your choice: ");

                        userInput = scanner.nextLine().trim().toLowerCase();

                        if (!isValidOption(userInput) && !userInput.equals("q")) {
                            System.out.println("\nInvalid option. Please try again.\n");
                        }
                    } while (!isValidOption(userInput) && !userInput.equals("q"));

                    if (isValidOption(userInput)) {
                        try {
                            accountService = new AccountService(DAOFactory.getDAOFactory(ProjectConstant.MYSQL));
                            account = accountService.getAccountByUserId(userId);
                            if (account == null) {
                                System.out.println("\nNo account associated with current user. Please create an account.");
                                return;
                            }
                        } catch (SQLException e) {
                            System.out.println("\nError retrieving account information: " + e.getMessage());
                            return;
                        }
                    }

                    switch (userInput) {
                        case "1":
                            userService.updateUserType(user, "NEW_USER");
                            System.out.println("\nSuccessfully downgraded to 'Regular User'");
                            return;
                        case "2":
                            if (account.getBalance() < platinum) {
                                System.out.println("\nDeclined: Insufficient funds");
                                return;
                            }
                            account.setBalance(account.getBalance() - platinum);
                            accountService.updateAccountBalance(account, account.getBalance());
                            userService.updateUserType(user, "PLATINUM_USER");
                            System.out.println("\nSuccessfully upgraded to 'Platinum User'");
                            return;
                        case "3":
                            if (account.getBalance() < vip) {
                                System.out.println("\nDeclined: Insufficient funds");
                                return;
                            }
                            account.setBalance(account.getBalance() - vip);
                            accountService.updateAccountBalance(account, account.getBalance());
                            userService.updateUserType(user, "VIP_USER");
                            System.out.println("\nSuccessfully upgraded to 'VIP User'");
                            return;
                        case "q":
                            return;
                    }

                    break;
                case "PLATINUM_USER":
                    vip = 25;

                    do {
                        System.out.println("1. Downgrade to Regular User");
                        System.out.println("2. Downgrade to Rewards User");
                        System.out.println("3. VIP User (a $25 charge will be made)");
                        System.out.println("Q. Quit");
                        System.out.print("Enter your choice: ");

                        userInput = scanner.nextLine().trim().toLowerCase();

                        if (!isValidOption(userInput) && !userInput.equals("q")) {
                            System.out.println("\nInvalid option. Please try again.\n");
                        }
                    } while (!isValidOption(userInput) && !userInput.equals("q"));

                    if (isValidOption(userInput)) {
                        try {
                            accountService = new AccountService(DAOFactory.getDAOFactory(ProjectConstant.MYSQL));
                            account = accountService.getAccountByUserId(userId);
                            if (account == null) {
                                System.out.println("\nNo account associated with current user. Please create an account.");
                                return;
                            }
                        } catch (SQLException e) {
                            System.out.println("\nError retrieving account information: " + e.getMessage());
                            return;
                        }
                    }

                    switch (userInput) {
                        case "1":
                            userService.updateUserType(user, "NEW_USER");
                            System.out.println("\nSuccessfully downgraded to 'Regular User'");
                            return;
                        case "2":
                            userService.updateUserType(user, "REWARD_USER");
                            System.out.println("\nSuccessfully downgraded to 'Rewards User'");
                            return;
                        case "3":
                            if (account.getBalance() < vip) {
                                System.out.println("\nDeclined: Insufficient funds");
                                return;
                            }
                            account.setBalance(account.getBalance() - vip);
                            accountService.updateAccountBalance(account, account.getBalance());
                            userService.updateUserType(user, "VIP_USER");
                            System.out.println("\nSuccessfully upgraded to 'VIP User'");
                            return;
                        case "q":
                            return;
                    }

                    break;
                case "VIP":
                    do {
                        System.out.println("1. Downgrade to Regular User");
                        System.out.println("2. Downgrade to Rewards User");
                        System.out.println("3. Downgrade to Platinum User");
                        System.out.println("Q. Quit");
                        System.out.print("Enter your choice: ");

                        userInput = scanner.nextLine().trim().toLowerCase();

                        if (!isValidOption(userInput) && !userInput.equals("q")) {
                            System.out.println("\nInvalid option. Please try again.\n");
                        }
                    } while (!isValidOption(userInput) && !userInput.equals("q"));

                    switch (userInput) {
                        case "1":
                            userService.updateUserType(user, "NEW_USER");
                            System.out.println("\nSuccessfully downgraded to 'Regular User'");
                            return;
                        case "2":
                            userService.updateUserType(user, "REWARD_USER");
                            System.out.println("\nSuccessfully downgraded to 'Rewards User'");
                            return;
                        case "3":
                            userService.updateUserType(user, "PLATINUM_USER");
                            System.out.println("\nSuccessfully downgraded to 'Platinum User'");
                            return;
                    }

                    break;
            }
        } catch (SQLException e) {
            System.out.println("\nError updating membership: " + e.getMessage());
        }
    }

    private static boolean isValidOption(String userInput) {
        return userInput.equals("1") || userInput.equals("2") || userInput.equals("3");
    }

    private static void editUserPhoneNumber(int userId) {
        System.out.println("\nYou selected 'Edit phone number'");
        String newPhoneNumber = getValidInputOrQ("Enter your new phone number (type 'q' to cancel)", "^\\d{10}$",
                "Phone number must be 10 digits");
        if (newPhoneNumber.equals("q")) return;
        try {
            UserService userService = new UserService(DAOFactory.getDAOFactory(ProjectConstant.MYSQL));
            User user = userService.getUser(userId);
            userService.updateUserPhoneNumber(user, newPhoneNumber);
            System.out.println("\nPhone number successfully updated!");
        } catch (SQLException e) {
            System.out.println("\nError updating phone number: " + e.getMessage());
        }
    }

    private static void editUserEmail(int userId) {
        System.out.println("\nYou selected 'Edit email'");
        String newEmail = getValidInputOrQ("Enter your new email (type 'q' to cancel)", "^[A-Za-z0-9+_.-]+@(.+)$",
                "Invalid email format");
        if (newEmail.equals("q")) return;
        try {
            UserService userService = new UserService(DAOFactory.getDAOFactory(ProjectConstant.MYSQL));
            User user = userService.getUser(userId);
            userService.updateUserEmail(user, newEmail);
            System.out.println("\nEmail successfully updated!");
        } catch (SQLException e) {
            System.out.println("\nError updating email: " + e.getMessage());
        }
    }

}
