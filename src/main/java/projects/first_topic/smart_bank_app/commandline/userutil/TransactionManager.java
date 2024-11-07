package projects.first_topic.smart_bank_app.commandline.userutil;

import projects.first_topic.smart_bank_app.constant.ProjectConstant;
import projects.first_topic.smart_bank_app.factory.DAOFactory;
import projects.first_topic.smart_bank_app.model.Account;
import projects.first_topic.smart_bank_app.model.Transaction;
import projects.first_topic.smart_bank_app.services.AccountService;
import projects.first_topic.smart_bank_app.services.TransactionService;

import java.sql.SQLException;
import java.util.List;

import static projects.first_topic.smart_bank_app.commandline.userutil.AccountManager.getAccountChoice;
import static projects.first_topic.smart_bank_app.commandline.userutil.AccountManager.getAccountService;
import static projects.first_topic.smart_bank_app.commandline.userutil.UserManager.manageUserAuthentication;

public class TransactionManager {

    public static void logTransactions() {
        int userId = manageUserAuthentication();
        if (userId < 0) return;
        AccountService accountService = getAccountService();
        TransactionService transactionService;
        List<Account> accounts;
        Account account;
        List<Transaction> transactions;

        try {
            accounts = accountService.getAccountsByUserId(userId);
            if (accounts.isEmpty()) {
                System.out.println("\nNo accounts associated with current user. Please create an account.");
                return;
            }
        } catch (SQLException e) {
            System.out.println("\nError retrieving account(s) information: " + e.getMessage());
            return;
        }

        account = getAccountChoice(accounts);

        transactionService = getTransactionService();
        if (transactionService == null) return;

        try {
            transactions = transactionService.getTransactionByAccountId(account.getAccount_id());
            if (transactions.isEmpty()) {
                System.out.println("\nNo transactions found");
                return;
            }
        } catch (SQLException e) {
            System.out.println("\nError retrieving transaction(s) information: " + e.getMessage());
            return;
        }

        System.out.println("\nid\t\tamount\t\ttype\t\tdate");
        for (int i = 0; i < transactions.size(); i++) {
            Transaction transaction = transactions.get(i);
            System.out.println(transaction.getTransaction_id()+ "\t\t" + transaction.getTransaction_amount() + "\t\t" + transaction.getTransaction_type() + "\t\t" + transaction.getTransaction_date());
        }
    }

    public static TransactionService getTransactionService() {
        TransactionService transactionService;
        try {
            transactionService = new TransactionService(DAOFactory.getDAOFactory((ProjectConstant.MYSQL)));
        } catch (SQLException e) {
            System.out.println("\nError initializing TransactionService: " + e.getMessage());
            return null;
        }
        return transactionService;
    }

}
