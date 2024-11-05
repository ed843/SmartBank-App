package projects.first_topic.smart_bank_app.services;
import projects.first_topic.smart_bank_app.dao.IAccountManagement;
import projects.first_topic.smart_bank_app.exception.DAOException;
import projects.first_topic.smart_bank_app.factory.DAOFactory;
import projects.first_topic.smart_bank_app.model.Account;

import java.sql.SQLException;
import java.util.List;

public class AccountService {
    private final IAccountManagement iAccountManagement;

    public AccountService(DAOFactory daoFactory) throws DAOException {
        this.iAccountManagement = daoFactory.getAccountManagement();
    }

    public void createAccount(Account account) throws SQLException {
        iAccountManagement.create(account);
    }

    public void resetAutoIncrement() throws SQLException {
        iAccountManagement.resetAutoIncrement();
    }

    public void setSafeUpdates(Integer n) throws SQLException {
        iAccountManagement.setSafeUpdates(n);
    }

    public void updateAccountBalance(Account account, Double balance) throws SQLException {
        iAccountManagement.updateAccountBalance(account, balance);
    }

    public List<Account> selectAllAccountsByUserId(Integer user_id) throws SQLException {
        return iAccountManagement.selectAllAccountsByUserId(user_id);
    }

    public double totalUserBalance(Integer user_id) throws SQLException {
        return iAccountManagement.totalUserBalance(user_id);
    }

    public void deleteAllAccount() throws SQLException {
        iAccountManagement.deleteAllAccounts();
    }

    public void deleteAccount(Account account) throws SQLException {
        iAccountManagement.deleteAccount(account);
    }

    public Account getAccount(Integer id) throws SQLException {
        return iAccountManagement.findById(id);
    }
}
