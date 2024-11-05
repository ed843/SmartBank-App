package projects.first_topic.smart_bank_app.userinterface.factory;

import projects.first_topic.smart_bank_app.exception.DAOException;
import projects.first_topic.smart_bank_app.factory.DAOFactory;
import projects.first_topic.smart_bank_app.model.Account;
import projects.first_topic.smart_bank_app.model.User;
import projects.first_topic.smart_bank_app.services.AccountService;
import projects.first_topic.smart_bank_app.services.UserService;
import projects.first_topic.smart_bank_app.userinterface.IPageBase;

import java.sql.SQLException;
import java.util.Scanner;

import static projects.first_topic.smart_bank_app.constant.ProjectConstant.MYSQL;

public class CreateAccount implements IPageBase {

    @Override
    public boolean newPage() {
        Scanner scanner = new Scanner(System.in);
        String userInput;

        do {
            // Display menu options
            System.out.println("\nPlease select an option:");
            System.out.println("1. Enter user ID:");
            System.out.println("2. Forgot user ID:");
            System.out.println("3. Go back:");
            System.out.println("Q. Quit");
            System.out.print("Enter your choice: ");

            // Get user input
            userInput = scanner.nextLine().trim().toLowerCase();

            switch (userInput) {
                case "1":
                    System.out.print("\nPlease enter your user ID: ");
                    userInput = scanner.nextLine().trim().toLowerCase();
                    Integer id = Integer.parseInt(userInput);
                    User user = null;
                    user = getUser(id);
                    if (user != null) {
                        accountInformation(scanner, user);
                    } else {
                        System.out.println("Error: Could no locate user with ID: " + userInput);
                    }
                    break;
                case "2":
                    System.out.print("\nPlease enter your user email: ");
                    userInput = scann
            }
        } while (!userInput.equals("q"));
    }

    private User getUser(Integer id) {
        User user = new User();
        try {
            DAOFactory factory = DAOFactory.getDAOFactory(MYSQL);
            UserService userService = new UserService(factory);
            user = userService.getUser(id);
        } catch (DAOException e) {
            //log error and print error message:
        } catch (SQLException e) {
            //log error
            System.out.println("Error: Unable to retrieve user information.");
            return null;
        }
        return user;
    }

    private void accountInformation(Scanner scanner, User user) {
        String userInput;
        Account account = null;
        System.out.print("\nPlease enter your user password: ");
        userInput = scanner.nextLine().trim().toLowerCase();
        if (!user.getPassword().equals(userInput)) {
            for (int i = 5; i > 0; i--) {
                System.out.println("\nIncorrect password! " + i + " attempts remaining.");
                System.out.print("Please enter your password: ");
                userInput = scanner.nextLine().trim().toLowerCase();
                if (user.getPassword().equals(userInput)) {
                    account = new Account();
                    break;
                }
            }
        }
        if (account != null) {
            account.setUser_id(user.getUser_id());
            double defaultBalance = 0;
            account.setBalance(defaultBalance);
            System.out.println("\nPlease choose account type:");
            System.out.println("1. Savings Account");
            System.out.println("2. Checking Account");
            userInput = scanner.nextLine().trim().toLowerCase();
            switch (userInput) {
                case "1":
                    account.setAccount_type("Savings");
                    break;
                case "2":
                    account.setAccount_type("Checking");
                    break;
                default:
                    break;
            }
            createAccount(account);
        }
        else {
            System.out.println("\nToo many incorrect attempts. Unable to create account.");
        }
    }

    private void createAccount(Account account) {
        try {
            DAOFactory factory = DAOFactory.getDAOFactory(MYSQL);
            AccountService service = new AccountService(factory);
            service.createAccount(account);
        } catch (DAOException e) {
            //log error
            System.out.println(e.getMessage());
        } catch (SQLException e) {
            //log error
        }
    }

}
