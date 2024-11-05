package projects.first_topic.smart_bank_app;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
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
                    System.out.println("You selected 'Create user'");
                    // Add code for Option 1
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

}
