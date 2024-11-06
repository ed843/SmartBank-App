package projects.first_topic.smart_bank_app.dao;
import java.sql.SQLException;
import projects.first_topic.smart_bank_app.model.*;

public interface IUserManagement extends IGenericManagement<User, Integer> {
    void create(User element) throws SQLException;
    User findById(Integer id) throws SQLException;
    void updateUserName(User user, String user_name) throws SQLException;
    void updateUserType(User user, String user_type) throws SQLException;
    void updateUserPassword(User user, String password) throws SQLException;
    void updateFirstName(User user, String first_name) throws SQLException;
    void updateLastName(User user, String last_name) throws SQLException;
    void updatePhone(User user, String phone) throws SQLException;
    void updateEmail(User user, String email) throws SQLException;
    void updateCreditScore(User user, Integer credit_score) throws SQLException;
    void updateAnnualIncome(User user, Double annual_income) throws SQLException;
    void updateLoanAmount(User user, Double loan_amount) throws SQLException;
    void updateRegistrationDate(User user, String registration_date) throws SQLException;
    void resetAutoIncrement() throws SQLException;
    void setSafeUpdates(Integer n) throws SQLException;
    void deleteAllUsers() throws SQLException;
    void deleteUser(User user) throws SQLException;
}
