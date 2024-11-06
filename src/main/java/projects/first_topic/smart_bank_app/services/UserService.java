package projects.first_topic.smart_bank_app.services;
import java.sql.*;
import projects.first_topic.smart_bank_app.dao.IUserManagement;
import projects.first_topic.smart_bank_app.exception.DAOException;
import projects.first_topic.smart_bank_app.factory.DAOFactory;
import projects.first_topic.smart_bank_app.model.User;


public class UserService {
    private final IUserManagement iUserManagement;

    public UserService(DAOFactory daoFactory) throws DAOException {
        this.iUserManagement = daoFactory.getUserManagement();
    }

    public void createUser(User user) throws SQLException {
        iUserManagement.create(user);
    }

    public void resetAutoIncrement() throws SQLException {
        iUserManagement.resetAutoIncrement();
    }

    public void setSafeUpdates(Integer n) throws SQLException {
        iUserManagement.setSafeUpdates(n);
    }

    public void updateUserName(User user, String user_name) throws SQLException{
        iUserManagement.updateUserName(user, user_name);
    }

    public void updateUserType(User user, String user_type) throws SQLException {
        iUserManagement.updateUserType(user, user_type);
    }

    public void updateUserPassword(User user, String password) throws SQLException {
        iUserManagement.updateUserPassword(user, password);
    }

    public void updateFirstName(User user, String first_name) throws SQLException {
        iUserManagement.updateFirstName(user, first_name);
    }

    public void updateLastName(User user, String last_name) throws SQLException {
        iUserManagement.updateLastName(user, last_name);
    }

    public void updatePhone(User user, String phone) throws SQLException {
        iUserManagement.updatePhone(user, phone);
    }

    public void updateEmail(User user, String email) throws SQLException {
        iUserManagement.updateEmail(user, email);
    }

    public void updateCreditScore(User user, Integer credit_score) throws SQLException {
        iUserManagement.updateCreditScore(user, credit_score);
    }

    public void updateAnnualIncome(User user, Double annual_income) throws SQLException {
        iUserManagement.updateAnnualIncome(user, annual_income);
    }

    public void updateLoanAmount(User user, Double loan_amount) throws SQLException {
        iUserManagement.updateLoanAmount(user, loan_amount);
    }

    public void updateRegistrationDate(User user, String registration_date) throws SQLException {
        iUserManagement.updateRegistrationDate(user, registration_date);
    }

    public void deleteAllUsers() throws SQLException {
        iUserManagement.deleteAllUsers();
    }

    public void deleteUser(User user) throws SQLException {
        iUserManagement.deleteUser(user);
    }

    public User getUser(Integer id) throws SQLException {
        return iUserManagement.findById(id);
    }
}
