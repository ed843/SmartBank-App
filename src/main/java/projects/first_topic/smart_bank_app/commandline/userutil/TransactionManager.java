package projects.first_topic.smart_bank_app.commandline.userutil;

import projects.first_topic.smart_bank_app.constant.ProjectConstant;
import projects.first_topic.smart_bank_app.factory.DAOFactory;
import projects.first_topic.smart_bank_app.model.Account;
import projects.first_topic.smart_bank_app.model.Transaction;
import projects.first_topic.smart_bank_app.services.AccountService;
import projects.first_topic.smart_bank_app.services.TransactionService;

import java.sql.SQLException;
import java.util.List;

import static projects.first_topic.smart_bank_app.commandline.userutil.UserManager.manageUserAuthentication;
import static projects.first_topic.smart_bank_app.util.InputSanitation.getValidInputOrQ;

public class TransactionManager {

    public static void logTransactions() {
        int userId = manageUserAuthentication();
        if (userId < 0) return;
        AccountService accountService;
        TransactionService transactionService;
        List<Account> accounts;
        List<Transaction> transactions;
        try {
            accountService = new AccountService(DAOFactory.getDAOFactory((ProjectConstant.MYSQL)));
        } catch (SQLException e) {
            System.out.println("\nError initializing AccountService: " + e.getMessage());
            return;
        }
        try {
            accounts = accountService.getAccountsByUserId(userId);
        } catch (SQLException e) {
            System.out.println("\nError getting accounts: " + e.getMessage());
            return;
        }
        if (accounts.isEmpty()) {
            System.out.println("\nNo accounts found");
            return;
        }
        System.out.println("\nPlease choose from the following options:");
        System.out.println("\t\tid\tbalance\ttype");
        for(int i = 0; i < accounts.size(); i++) {
            Account account = accounts.get(i);
            System.out.println((i + 1) + ":\t\t" + account.getAccount_id() + "\t$" + account.getBalance() + "\t" + account.getAccount_type());
        }
        String userInput;
        userInput = getValidInputOrQ("Which account (type 'q' to cancel)", "^[1-" + (accounts.size()) + "]$", "Please choose one of the following options");
        if (userInput.equals("q")) {
            return;
        }
        Account account = accounts.get(Integer.parseInt(userInput) - 1);
        try {
            transactionService = new TransactionService(DAOFactory.getDAOFactory((ProjectConstant.MYSQL)));
        } catch (SQLException e) {
            System.out.println("\nError initializing TransactionService: " + e.getMessage());
            return;
        }
        try {
            transactions = transactionService.getTransactionByAccountId(account.getAccount_id());
        } catch (SQLException e) {
            System.out.println("\nError getting transactions: " + e.getMessage());
            return;
        }
        if (transactions.isEmpty()) {
            System.out.println("\nNo transactions found");
            return;
        }
        System.out.println("\nid\t\tamount\t\ttype\t\tdate");
        for (int i = 0; i < transactions.size(); i++) {
            Transaction transaction = transactions.get(i);
            System.out.println(transaction.getTransaction_id()+ "\t\t" + transaction.getTransaction_amount() + "\t\t" + transaction.getTransaction_type() + "\t\t" + transaction.getTransaction_date());
        }
    }

}
