package projects.first_topic.smart_bank_app.loanManager;

import projects.first_topic.smart_bank_app.model.User;
import projects.first_topic.smart_bank_app.services.LoanApplicationService;
import projects.first_topic.smart_bank_app.services.UserService;

import java.sql.SQLException;
import java.util.Scanner;

import static projects.first_topic.smart_bank_app.commandline.Main.scanner;

public class LoanHandler {

    public static void applyForLoanMenu() {
        try {
            System.out.println("\n=== Loan Application Form ===");

            Integer userId = getUserId();
            if (userId == null) return;

            User user = validateUser(userId);
            if (user == null) return;

            String loanType = getLoanType();
            if (loanType == null) return;

            double amount = getLoanAmount();
            if (amount <= 0) return;

            String startDate = getDate("Enter start date (YYYY-MM-DD): ");
            String endDate = getDate("Enter end date (YYYY-MM-DD): ");

            submitApplication(userId, loanType, amount, startDate, endDate);

        } catch (NumberFormatException e) {
            System.out.println("Error: Please enter valid numbers");
        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static Integer getUserId() {
        System.out.print("Enter user ID: ");
        try {
            return Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("Error: Invalid user ID");
            return null;
        }
    }

    private static User validateUser(Integer userId) throws SQLException {
        User user = UserService.getUser(userId);
        if (user == null) {
            System.out.println("Error: User not found");
        }
        return user;
    }

    private static String getLoanType() {
        System.out.println("\nAvailable loan types:");
        System.out.println("1. Personal Loan");
        System.out.println("2. Home Loan");
        System.out.println("3. Auto Loan");
        System.out.println("4. Business Loan");
        System.out.print("Select loan type (1-4): ");

        switch (scanner.nextLine().trim()) {
            case "1" -> {
                return "PERSONAL";
            }
            case "2" -> {
                return "HOME";
            }
            case "3" -> {
                return "AUTO";
            }
            case "4" -> {
                return "BUSINESS";
            }
            default -> {
                System.out.println("Invalid loan type selected");
                return null;
            }
        }
    }

    private static double getLoanAmount() {
        System.out.print("Enter loan amount: ");
        try {
            double amount = Double.parseDouble(scanner.nextLine().trim());
            if (amount <= 0) {
                System.out.println("Error: Loan amount must be positive");
            }
            return amount;
        } catch (NumberFormatException e) {
            System.out.println("Error: Invalid loan amount");
            return -1;
        }
    }

    private static String getDate(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }

    private static void submitApplication(Integer userId, String loanType, double amount, String startDate, String endDate) throws SQLException {
        LoanApplicationService.applyForLoan(userId, loanType, amount, startDate, endDate);
        System.out.println("Loan application processed successfully!");
    }

}
