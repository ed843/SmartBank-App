package projects.first_topic.smart_bank_app.dao;
import projects.first_topic.smart_bank_app.model.Transaction;
import java.sql.SQLException;

public interface ITransactionManagement extends IGenericManagement<Transaction, Integer> {
    void create(Transaction element) throws SQLException;
    Transaction findById(Integer id) throws SQLException;
    void resetAutoIncrement() throws SQLException;
    void setSafeUpdates(Integer n) throws SQLException;
    void deleteAllTransactions() throws SQLException;
    void deleteTransaction(Transaction transaction) throws SQLException;
}
