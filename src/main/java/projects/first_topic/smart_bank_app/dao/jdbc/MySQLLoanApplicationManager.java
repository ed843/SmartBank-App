package projects.first_topic.smart_bank_app.dao.jdbc;
import projects.first_topic.smart_bank_app.connection.DBConnection;
import projects.first_topic.smart_bank_app.constant.ProjectConstant;
import projects.first_topic.smart_bank_app.dao.*;
import projects.first_topic.smart_bank_app.exception.DAOException;
import java.sql.*;
import static projects.first_topic.smart_bank_app.constant.ProjectConstant.*;
import static projects.first_topic.smart_bank_app.util.DAOUtil.preparedStatement;
import projects.first_topic.smart_bank_app.model.*;


public class MySQLLoanApplicationManager implements ILoanApplicationManagement {
    private static IUserManagement iUserManagement;

    @Override
    public void create(LoanApplication loanApplication) throws SQLException {
        if (loanApplication.getApplication_id() != null) {
            throw new IllegalArgumentException("Loan Application is already created, the application_id is not null.");
        }
        Object[] values = {loanApplication.getUser_id(), loanApplication.getLoan_type(), loanApplication.getAmount(),
                loanApplication.getStart_date(), loanApplication.getEnd_date(), loanApplication.getApplication_status(),
                loanApplication.getApplication_date()};
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement
                     = preparedStatement(connection, SQL_INSERT_LOAN_APPLICATION, true, values)) {
            statement.executeUpdate();
            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    loanApplication.setApplication_id(generatedKeys.getInt(1));
                    System.out.println("Successfully Inserted application_id " + generatedKeys.getInt(1));
                } else {
                    throw new DAOException("Creating loan application failed, no generated key obtained.");
                }
            }
        } catch (SQLException e) {
            throw new SQLException(e);
        }
    }

    @Override
    public LoanApplication findById(Integer id) throws SQLException {
        return find(id);
    }

    private LoanApplication find(Object... values) throws SQLException {
        LoanApplication loanApplication = null;
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = preparedStatement(connection,
                     ProjectConstant.SQL_FIND_BY_LOAN_APPLICATION_ID, false, values);
             ResultSet resultSet = statement.executeQuery()) {
            if (resultSet.next()) {
                loanApplication = getLoanApplicationFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            throw new SQLException(e);
        }
        return loanApplication;
    }

    /**
     * Mapped LoanApplication from the current row of the given ResultSet
     */
    private LoanApplication getLoanApplicationFromResultSet(ResultSet resultSet) throws SQLException {
        LoanApplication loanApplication = new LoanApplication();
        loanApplication.setApplication_id(resultSet.getInt("application_id"));
        loanApplication.setUser_id(resultSet.getInt("user_id"));
        loanApplication.setLoan_type(resultSet.getString("loan_type"));
        loanApplication.setAmount(resultSet.getDouble("amount"));
        loanApplication.setStart_date(resultSet.getString("start_date"));
        loanApplication.setEnd_date(resultSet.getString("end_date"));
        loanApplication.setApplication_status(resultSet.getString("application_status"));
        loanApplication.setApplication_date(resultSet.getString("application_date"));
        return loanApplication;
    }

    @Override
    public void resetAutoIncrement() throws SQLException {
        Integer start = 1;
        Object[] values = {start};
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = preparedStatement(connection, SQL_RESET_AUTO_INCREMENT_LOAN_APPLICATION,
                     false, values)) {
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException(e);
        }
    }

    @Override
    public void updateLoanApplicationStatus(LoanApplication loanApplication) throws SQLException {
        if (loanApplication.getApplication_id() == null) {
            throw new IllegalArgumentException("Loan Application does not exist.");
        }

        String status = null;
        Integer user_id = loanApplication.getUser_id();
        User user = null;
        Object[] values = {user_id};
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = preparedStatement(connection, ProjectConstant.SQL_FIND_BY_USER_ID,
                     false, values);
             ResultSet resultSet = statement.executeQuery()) {
            if (resultSet.next()) {
                user = getUserFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            throw new SQLException(e);
        }

        // Implement a simple algorithm to calculate loan eligibility based on user input
        if (user != null) {
            double annual_salary = user.getAnnual_income();
            double loan_amount = user.getLoan_amount();
            double current_amount = loanApplication.getAmount();
            double balance = totalUserBalance(user_id);
            int credit_score = user.getCredit_score();
            if (loan_amount + current_amount <= annual_salary * 3 && credit_score >= 670) {
                status = "approved";
            } else if (loan_amount + current_amount <= annual_salary * 4 && credit_score >= 730) {
                status = "approved";
            } else if (loan_amount + current_amount <= (annual_salary * 3 + balance) && credit_score >= 650) {
                status = "approved";
            } else {
                status = "declined";
            }
        }

        if (status != null) {
            values = new Object[]{status, loanApplication.getApplication_id()};
            try (Connection connection = DBConnection.getConnection();
                 PreparedStatement statement = preparedStatement(connection, SQL_UPDATE_LOAN_APPLICATION_STATUS,
                         false, values)) {
                statement.executeUpdate();
            } catch(SQLException e) {
                throw new SQLException(e);
            }
        } else {
            System.out.println("Failed to update loan application status.");
        }
    }

    private User getUserFromResultSet(ResultSet resultSet) throws SQLException {
        User user = new User();
        user.setUser_id(resultSet.getInt("user_id"));
        user.setUser_name(resultSet.getString("user_name"));
        user.setUser_type(resultSet.getString("user_type"));
        user.setPassword(resultSet.getString("password"));
        user.setFirst_name(resultSet.getString("first_name"));
        user.setLast_name(resultSet.getString("last_name"));
        user.setPhone(resultSet.getString("phone"));
        user.setEmail(resultSet.getString("email"));
        user.setCredit_score(resultSet.getInt("credit_score"));
        user.setAnnual_income(resultSet.getDouble("annual_income"));
        user.setLoan_amount(resultSet.getDouble("loan_amount"));
        user.setRegistration_date(resultSet.getString("registration_date"));
        return user;
    }

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

    private Account getAccountFromResultSet(ResultSet resultSet) throws SQLException {
        Account account = new Account();
        account.setAccount_id(resultSet.getInt("account_id"));
        account.setUser_id(resultSet.getInt("user_id"));
        account.setAccount_type(resultSet.getString("account_type"));
        account.setBalance(resultSet.getDouble("balance"));
        return account;
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
    public void deleteAllLoanApplications() throws SQLException {
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = preparedStatement(connection, SQL_DELETE_ALL_LOAN_APPLICATON,
                     false)) {
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException(e);
        }
    }

    @Override
    public void deleteLoanApplication(LoanApplication loanApplication) throws SQLException {
        Object[] values = {loanApplication.getApplication_id()};
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = preparedStatement(connection, SQL_DELETE_LOAN_APPLICATION,
                     false, values)) {
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException(e);
        }
    }
}
