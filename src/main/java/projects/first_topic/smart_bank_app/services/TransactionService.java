package projects.first_topic.smart_bank_app.services;
import java.sql.*;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import projects.first_topic.smart_bank_app.dao.ITransactionManagement;
import projects.first_topic.smart_bank_app.exception.DAOException;
import projects.first_topic.smart_bank_app.factory.DAOFactory;
import projects.first_topic.smart_bank_app.model.Transaction;


public class TransactionService {
    private final ITransactionManagement iTransactionManagement;
    static Logger logger = LogManager.getLogger(TransactionService.class.getName());

    public TransactionService(DAOFactory daoFactory) throws DAOException {
        this.iTransactionManagement = daoFactory.getTransactionManagement();
    }

    public void createTransaction(Transaction transaction) throws SQLException {
        iTransactionManagement.create(transaction);
    }

    public void resetAutoIncrement() throws SQLException {
        iTransactionManagement.resetAutoIncrement();
    }

    public void setSafeUpdates(Integer n) throws SQLException {
        iTransactionManagement.setSafeUpdates(n);
    }

    public List<Transaction> selectAllTransactionsByAccountId(Integer account_id) throws SQLException {
        List<Transaction> accountTransactions = iTransactionManagement.selectAllTransactionsByAccountId(account_id);
        accountTransactions.forEach(
                (transaction) -> {
                    String res = "Account_id: " + transaction.getAccount_id()
                            + " Transaction_id: " + transaction.getTransaction_id()
                            + " Transaction_date: " + transaction.getTransaction_date()
                            + " Transaction_type: " + transaction.getTransaction_type()
                            + " Transaction_amount: " + transaction.getTransaction_amount()
                            + " balance_before: $" + transaction.getAccount_balance_before()
                            + " balance_after: $" + transaction.getAccount_balance_after();
                    logger.info(res);
                    System.out.println(res);});
        return accountTransactions;
    }

    public void deleteAllTransactions() throws SQLException {
        iTransactionManagement.deleteAllTransactions();
    }

    public void deleteTransaction(Transaction transaction) throws SQLException {
        iTransactionManagement.deleteTransaction(transaction);
    }

    public Transaction getTransaction(Integer id) throws SQLException {
        return iTransactionManagement.findById(id);
    }
}
