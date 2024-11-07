package projects.first_topic.smart_bank_app.dao.jdbc;
import projects.first_topic.smart_bank_app.connection.DBConnection;
import projects.first_topic.smart_bank_app.constant.ProjectConstant;
import projects.first_topic.smart_bank_app.dao.*;
import projects.first_topic.smart_bank_app.exception.DAOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static projects.first_topic.smart_bank_app.constant.ProjectConstant.*;
import static projects.first_topic.smart_bank_app.util.DAOUtil.preparedStatement;
import projects.first_topic.smart_bank_app.model.Account;
import projects.first_topic.smart_bank_app.model.User;


public class MySQLAccountManager implements IAccountManagement {
    @Override
    public void create(Account account) throws SQLException {
        if (account.getAccount_id() != null) {
            throw new IllegalArgumentException("Account is already created, the account_id is not null.");
        }
        Object[] values = {account.getUser_id(), account.getAccount_type(), account.getBalance()};
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement
                     = preparedStatement(connection, SQL_INSERT_ACCOUNT, true, values)) {
            statement.executeUpdate();
            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    account.setAccount_id(generatedKeys.getInt(1));
                } else {
                    throw new DAOException("Creating account failed, no generated key obtained.");
                }
            }
        } catch (SQLException e) {
            throw new SQLException(e);
        }
    }

    @Override
    public Account findById(Integer id) throws SQLException {
        return find(id);
    }

    @Override
    public Account findByUserId(Integer userId) throws SQLException {
        Account account = null;
        Object[] values = {userId};
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = preparedStatement(connection, ProjectConstant.SQL_FIND_ACCOUNT_BY_USER_ID,
                     false, values);
             ResultSet resultSet = statement.executeQuery()) {
            if (resultSet.next()) {
                account = getAccountFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            throw new SQLException(e);
        }
        return account;
    }

    @Override
    public double totalUserBalance(Integer user_id) throws SQLException {
        double balance = 0.0;
        Object[] values = {user_id};
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = preparedStatement(connection, ProjectConstant.SQL_FIND_ACCOUNT_BY_USER_ID,
                     false, values);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Account account = getAccountFromResultSet(resultSet);
                balance += account.getBalance();
            }
        } catch (SQLException e) {
            throw new SQLException(e);
        }
        return balance;
    }

    @Override
    public List<Account> findAccountsByUserId(Integer user_id) throws SQLException {
        Object[] values = {user_id};
        List<Account> accounts = new ArrayList<>();
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = preparedStatement(connection, ProjectConstant.SQL_FIND_ACCOUNT_BY_USER_ID,
                     false, values);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Account account = getAccountFromResultSet(resultSet);
                accounts.add(account);
            }
        } catch (SQLException e) {
            throw new SQLException(e);
        }
        return accounts;
    }

    private Account find(Object... values) throws SQLException {
        Account account = null;
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = preparedStatement(connection, ProjectConstant.SQL_FIND_BY_ACCOUNT_ID,
                     false, values);
             ResultSet resultSet = statement.executeQuery()) {
            if (resultSet.next()) {
                account = getAccountFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            throw new SQLException(e);
        }
        return account;
    }

    /**
     * Mapped Account from the current row of the given ResultSet
     */
    private Account getAccountFromResultSet(ResultSet resultSet) throws SQLException {
        Account account = new Account();
        account.setAccount_id(resultSet.getInt("account_id"));
        account.setUser_id(resultSet.getInt("user_id"));
        account.setAccount_type(resultSet.getString("account_type"));
        account.setBalance(resultSet.getDouble("balance"));
        return account;
    }

    @Override
    public void resetAutoIncrement() throws SQLException {
        Integer start = 1;
        Object[] values = {start};
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = preparedStatement(connection, SQL_RESET_AUTO_INCREMENT_ACCOUNT,
                     false, values)) {
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException(e);
        }
    }

    @Override
    public void updateAccountType(Account account, String accountType) throws SQLException {
        if (account.getAccount_id() == null) {
            throw new IllegalArgumentException("Account does not exist.");
        }
        Object[] values = {accountType, account.getAccount_id()};
        try (Connection connection = DBConnection.getConnection();
            PreparedStatement statement
                = preparedStatement(connection, SQL_UPDATE_ACCOUNT_TYPE, false, values)) {
            statement.executeUpdate();
        } catch(SQLException e) {
            throw new SQLException(e);
        }
    }

    @Override
    public void updateAccountBalance(Account account, Double balance) throws SQLException {
        if (account.getAccount_id() == null) {
            throw new IllegalArgumentException("Account does not exist.");
        }
        Object[] values = {balance, account.getAccount_id()};
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement
                     = preparedStatement(connection, SQL_UPDATE_ACCOUNT_BALANCE, false, values)) {
            statement.executeUpdate();
        } catch(SQLException e) {
            throw new SQLException(e);
        }
    }

    @Override
    public void setSafeUpdates(Integer n) throws SQLException {
        Object[] values = {n};
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = preparedStatement(connection, SQL_SET_SAFE_UPDATES,
                     false, values)) {
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException(e);
        }
    }

    @Override
    public void deleteAllAccounts() throws SQLException {
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = preparedStatement(connection, SQL_DELETE_ALL_ACCOUNT,
                     false)) {
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException(e);
        }
    }

    @Override
    public void deleteAccount(Account account) throws SQLException {
        Object[] values = {account.getAccount_id()};
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = preparedStatement(connection, SQL_DELETE_ACCOUNT, false, values)) {
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException(e);
        }
    }
}
