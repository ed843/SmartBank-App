package projects.first_topic.smart_bank_app.frontend.userutil;

import projects.first_topic.smart_bank_app.factory.DAOFactory;
import projects.first_topic.smart_bank_app.model.Account;
import projects.first_topic.smart_bank_app.services.AccountService;

import java.sql.SQLException;

import static projects.first_topic.smart_bank_app.constant.ProjectConstant.MYSQL;
import static projects.first_topic.smart_bank_app.frontend.Main.*;

public class AccountManager {

    public static void createAccount() {
        System.out.println("\nYou selected 'Create account'");
        int userId = manageUserAuthentication();
        AccountService accountService;
        Account account;
        try {
            accountService = new AccountService(DAOFactory.getDAOFactory(MYSQL));
            account = accountService.getAccountByUserId(userId);
            if (account != null) {
                System.out.println("\nThis user already has an account.");
                return;
            }
        } catch (SQLException e) {
            System.out.println("\nError initializing AccountService: " + e.getMessage());
            return;
        }
        if (userId != -1 && userId != -2) {

            String manageUserInput;


            System.out.println("\nPlease pick one of the following options: ");
            System.out.println("1. New Checking Account");
            System.out.println("2. New Savings Account");
            System.out.println("Q. Quit");
            System.out.print("Enter your choice: ");

            manageUserInput = scanner.nextLine().trim().toLowerCase();

            String accountType;

            switch (manageUserInput) {
                case "1":
                    accountType = "CHECKING_ACCOUNT";
                    try {
                        accountService.createAccount(new Account(userId, accountType));
                        System.out.println("\nAccount successfully created!");
                    } catch (SQLException e) {
                        System.out.println("\nError creating account: " + e.getMessage());
                    }
                    break;
                case "2":
                    accountType = "SAVINGS_ACCOUNT";
                    try {
                        accountService.createAccount(new Account(userId, accountType));
                        System.out.println("\nAccount successfully created!");
                    } catch (SQLException e) {
                        System.out.println("\nError creating account: " + e.getMessage());
                    }
                    break;
            }
        }
    }

    public static void viewAccount() {
        System.out.println("\nYou selected 'View account'");
        int userId = manageUserAuthentication();
        AccountService accountService;
        try {
            accountService = new AccountService(DAOFactory.getDAOFactory(MYSQL));
        } catch (SQLException e) {
            System.out.println("\nError initializing AccountService: " + e.getMessage());
            return;
        }
        if (userId != -1 && userId != -2) {

            String manageUserInput;
            Account account;

            do {
                try {
                    account = accountService.getAccountByUserId(userId);
                    if (account == null) {
                        System.out.println("\nNo account associated with current user. Please create an account.");
                        return;
                    } else {
                        System.out.println("\nAccount successfully retrieved!\n");
                        System.out.println("Account ID: " + account.getAccount_id());
                        System.out.println("Account Type: " + account.getAccount_type());
                        System.out.println("Account Balance: " + account.getBalance());
                    }
                } catch (SQLException e) {
                    System.out.println("Error retrieving account information: " + e.getMessage());
                }
                System.out.print("\nEnter 'Q' to go back: ");

                manageUserInput = scanner.nextLine().trim().toLowerCase();

            } while (!manageUserInput.equals("q"));
        }
    }

    public static void manageAccount() {
        System.out.println("\nYou selected 'Manage account'");
        int userId = manageUserAuthentication();
        AccountService accountService;
        boolean deleted = false;
        try {
            accountService = new AccountService(DAOFactory.getDAOFactory(MYSQL));
        } catch (SQLException e) {
            System.out.println("\nError initializing AccountService: " + e.getMessage());
            return;
        }
        if (userId != -1 && userId != -2) {

            String manageUserInput;
            Account account;

            try {
                account = accountService.getAccountByUserId(userId);
                if (account == null) {
                    System.out.println("\nNo account associated with current user. Please create an account.");
                    return;
                }
            } catch (SQLException e) {
                System.out.println("\nError retrieving account information: " + e.getMessage());
                return;
            }

            do {
                System.out.println("\nPlease pick one of the following options: ");
                System.out.println("1. Delete account");
                System.out.println("2. Change account type");
                System.out.println("Q. Quit");
                System.out.print("Enter your choice: ");

                manageUserInput = scanner.nextLine().trim().toLowerCase();

                switch (manageUserInput) {
                    case "1":
                        System.out.println("\nYou selected 'Delete account'");
                        System.out.println("This action cannot be undone, do you wish to continue?");
                        System.out.print("\nEnter '1' to continue or 'Q' to go back: ");

                        manageUserInput = scanner.nextLine().trim().toLowerCase();

                        if (manageUserInput.equals("1")) {
                            try {
                                accountService.deleteAccount(account);
                            } catch (SQLException e) {
                                System.out.println("\nError deleting account: " + e.getMessage());
                                return;
                            }
                            System.out.println("\nAccount deleted successfully!");
                            return;
                        }
                        break;
                    case "2":
                        if (account.getAccount_type().equals("CHECKING_ACCOUNT")) {
                            account.setAccount_type("SAVINGS_ACCOUNT");
                            try {
                                accountService.updateAccountType(account, account.getAccount_type());
                                System.out.println("\nAccount type changed successfully!");
                            } catch (SQLException e) {
                                System.out.println("\nError updating account type: " + e.getMessage());
                            }
                        } else {
                            account.setAccount_type("CHECKING_ACCOUNT");
                            try {
                                accountService.updateAccountType(account, account.getAccount_type());
                                System.out.println("\nAccount type changed successfully!");
                            } catch (SQLException e) {
                                System.out.println("\nError updating account type: " + e.getMessage());
                            }
                        }
                        break;
                }

            } while (!manageUserInput.equals("q"));
        }
    }


}
