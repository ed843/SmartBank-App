package projects.first_topic.smart_bank_app.dao;
import org.apache.ibatis.jdbc.SQL;
import projects.first_topic.smart_bank_app.model.*;
import java.sql.SQLException;
import java.util.List;

public interface IAccountManagement extends IGenericManagement<Account, Integer> {
    void create(Account element) throws SQLException;
    Account findById(Integer id) throws SQLException;
    Account findByUserId(Integer user_id) throws SQLException;
    List<Account> findAccountsByUserId(Integer userId) throws SQLException;
    void resetAutoIncrement() throws SQLException;
    void updateAccountType(Account account, String accountType) throws SQLException;
    void updateAccountBalance(Account account, Double balance) throws SQLException;
    void setSafeUpdates(Integer n) throws SQLException;
    void deleteAllAccounts() throws SQLException;
    void deleteAccount(Account account) throws SQLException;
}
