package projects.first_topic.smart_bank_app.commandline;

import java.util.Scanner;

import static projects.first_topic.smart_bank_app.commandline.userutil.AccountManager.*;
import static projects.first_topic.smart_bank_app.commandline.userutil.TransactionManager.logTransactions;
import static projects.first_topic.smart_bank_app.commandline.userutil.UserManager.*;

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
                    logTransactions();
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

}
