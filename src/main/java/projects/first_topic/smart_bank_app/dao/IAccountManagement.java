package projects.first_topic.smart_bank_app.dao;
import projects.first_topic.smart_bank_app.model.*;
import java.sql.SQLException;
import java.util.List;

public interface IAccountManagement extends IGenericManagement<Account, Integer> {
    void create(Account element) throws SQLException;
    Account findById(Integer id) throws SQLException;
    List<Account> selectAllAccountsByUserId(Integer user_id) throws SQLException;
    double totalUserBalance(Integer user_id) throws SQLException;
    void resetAutoIncrement() throws SQLException;
    void updateAccountType(Account account, String account_type) throws SQLException;
    void updateAccountBalance(Account account, Double balance) throws SQLException;
    void setSafeUpdates(Integer n) throws SQLException;
    void deleteAllAccounts() throws SQLException;
    void deleteAccount(Account account) throws SQLException;
}
