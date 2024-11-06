package projects.first_topic.smart_bank_app.dao.jdbc;
import projects.first_topic.smart_bank_app.connection.DBConnection;
import projects.first_topic.smart_bank_app.constant.ProjectConstant;
import projects.first_topic.smart_bank_app.dao.*;
import projects.first_topic.smart_bank_app.exception.DAOException;
import java.sql.*;
import static projects.first_topic.smart_bank_app.constant.ProjectConstant.*;
import static projects.first_topic.smart_bank_app.util.DAOUtil.preparedStatement;
import projects.first_topic.smart_bank_app.model.User;


public class MySQLUserManager implements IUserManagement {
    @Override
    public void create(User user) throws SQLException {
        if (user.getUser_id() != null) {
            throw new IllegalArgumentException("User is already created, the user_id is not null.");
        }
        Object[] values = {user.getUser_name(), user.getUser_type(), user.getPassword(), user.getFirst_name(),
                user.getLast_name(), user.getPhone(), user.getEmail(), user.getCredit_score(),
                user.getAnnual_income(), user.getLoan_amount(), user.getRegistration_date()};

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement
                     = preparedStatement(connection, SQL_INSERT_USER, true, values)) {
            statement.executeUpdate();
            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    user.setUser_id(generatedKeys.getInt(1));
                    System.out.println("Successfully Inserted user_id " + generatedKeys.getInt(1));
                } else {
                    throw new DAOException("Creating user failed, no generated key obtained.");
                }
            }
        } catch (SQLException e) {
            throw new SQLException(e);
        }
    }


    @Override
    public User findById(Integer id) throws SQLException {
        return find(id);
    }

    @Override
    public void updateUserName(User user, String user_name) throws SQLException {
        if (user.getUser_id() == null) {
            throw new IllegalArgumentException("User does not exist.");
        }
        Object[] values = {user_name, user.getUser_id()};
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement
                     = preparedStatement(connection, SQL_UPDATE_USER_NAME, false, values)) {
            statement.executeUpdate();
        } catch(SQLException e) {
            throw new SQLException(e);
        }
    }

    @Override
    public void updateUserType(User user, String user_type) throws SQLException {
        if (user.getUser_id() == null) {
            throw new IllegalArgumentException("User does not exist.");
        }
        Object[] values = {user_type, user.getUser_id()};
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement
                     = preparedStatement(connection, SQL_UPDATE_USER_TYPE, false, values)) {
            statement.executeUpdate();
        } catch(SQLException e) {
            throw new SQLException(e);
        }
    }

    @Override
    public void updateUserPassword(User user, String password) throws SQLException {
        if (user.getUser_id() == null) {
            throw new IllegalArgumentException("User does not exist.");
        }
        Object[] values = {password, user.getUser_id()};
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement
                     = preparedStatement(connection, SQL_UPDATE_USER_PASSWORD, false, values)) {
            statement.executeUpdate();
        } catch(SQLException e) {
            throw new SQLException(e);
        }
    }

    @Override
    public void updateFirstName(User user, String first_name) throws SQLException {
        if (user.getUser_id() == null) {
            throw new IllegalArgumentException("User does not exist.");
        }
        Object[] values = {first_name, user.getUser_id()};
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement
                     = preparedStatement(connection, SQL_UPDATE_USER_FIRST_NAME, false, values)) {
            statement.executeUpdate();
        } catch(SQLException e) {
            throw new SQLException(e);
        }
    }

    @Override
    public void updateLastName(User user, String last_name) throws SQLException {
        if (user.getUser_id() == null) {
            throw new IllegalArgumentException("User does not exist.");
        }
        Object[] values = {last_name, user.getUser_id()};
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement
                     = preparedStatement(connection, SQL_UPDATE_USER_LAST_NAME, false, values)) {
            statement.executeUpdate();
        } catch(SQLException e) {
            throw new SQLException(e);
        }
    }

    @Override
    public void updatePhone(User user, String phone) throws SQLException {
        if (user.getUser_id() == null) {
            throw new IllegalArgumentException("User does not exist.");
        }
        Object[] values = {phone, user.getUser_id()};
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement
                     = preparedStatement(connection, SQL_UPDATE_USER_PHONE, false, values)) {
            statement.executeUpdate();
        } catch(SQLException e) {
            throw new SQLException(e);
        }
    }

    @Override
    public void updateEmail(User user, String email) throws SQLException {
        if (user.getUser_id() == null) {
            throw new IllegalArgumentException("User does not exist.");
        }
        Object[] values = {email, user.getUser_id()};
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement
                     = preparedStatement(connection, SQL_UPDATE_USER_EMAIL, false, values)) {
            statement.executeUpdate();
        } catch(SQLException e) {
            throw new SQLException(e);
        }
    }

    @Override
    public void updateCreditScore(User user, Integer credit_score) throws SQLException {
        if (user.getUser_id() == null) {
            throw new IllegalArgumentException("User does not exist.");
        }
        Object[] values = {credit_score, user.getUser_id()};
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement
                     = preparedStatement(connection, SQL_UPDATE_USER_CREDIT_SCORE, false, values)) {
            statement.executeUpdate();
        } catch(SQLException e) {
            throw new SQLException(e);
        }
    }

    @Override
    public void updateAnnualIncome(User user, Double annual_income) throws SQLException {
        if (user.getUser_id() == null) {
            throw new IllegalArgumentException("User does not exist.");
        }
        Object[] values = {annual_income, user.getUser_id()};
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement
                     = preparedStatement(connection, SQL_UPDATE_USER_ANNUAL_INCOME, false, values)) {
            statement.executeUpdate();
        } catch(SQLException e) {
            throw new SQLException(e);
        }
    }

    @Override
    public void updateLoanAmount(User user, Double loan_amount) throws SQLException {
        if (user.getUser_id() == null) {
            throw new IllegalArgumentException("User does not exist.");
        }
        Object[] values = {loan_amount, user.getUser_id()};
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement
                     = preparedStatement(connection, SQL_UPDATE_USER_LOAN_AMOUNT, false, values)) {
            statement.executeUpdate();
        } catch(SQLException e) {
            throw new SQLException(e);
        }
    }

    @Override
    public void updateRegistrationDate(User user, String registration_date) throws SQLException {
        if (user.getUser_id() == null) {
            throw new IllegalArgumentException("User does not exist.");
        }
        Object[] values = {registration_date, user.getUser_id()};
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = preparedStatement(connection, SQL_UPDATE_USER_REGISTRATION_DATE,
                     false, values)) {
            statement.executeUpdate();
        } catch(SQLException e) {
            throw new SQLException(e);
        }
    }

    private User find(Object... values) throws SQLException {
        User user = null;
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
        return user;
    }

    /**
     * Mapped User from the current row of the given ResultSet
     */
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


    @Override
    public void resetAutoIncrement() throws SQLException {
        Integer start = 1;
        Object[] values = {start};
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = preparedStatement(connection, SQL_RESET_AUTO_INCREMENT_USER,
                     false, values)) {
            statement.executeUpdate();
        } catch (SQLException e) {
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
    public void deleteAllUsers() throws SQLException {
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = preparedStatement(connection, SQL_DELETE_ALL_USER,
                     false)) {
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException(e);
        }
    }

    @Override
    public void deleteUser(User user) throws SQLException {
        Object[] values = {user.getUser_id()};
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = preparedStatement(connection, SQL_DELETE_USER,
                     false, values)) {
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException(e);
        }
    }
}
