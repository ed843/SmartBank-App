package projects.first_topic.smart_bank_app.services;
import java.sql.*;
import java.util.List;

import projects.first_topic.smart_bank_app.dao.ITransactionManagement;
import projects.first_topic.smart_bank_app.exception.DAOException;
import projects.first_topic.smart_bank_app.factory.DAOFactory;
import projects.first_topic.smart_bank_app.model.Transaction;

public class TransactionService {
    private final ITransactionManagement iTransactionManagement;

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
        return iTransactionManagement.selectAllTransactionsByAccountId(account_id);
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
