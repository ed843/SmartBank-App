package projects.first_topic.smart_bank_app.dao;
import java.sql.SQLException;
import projects.first_topic.smart_bank_app.model.*;

public interface IUserManagement extends IGenericManagement<User, Integer> {
    void create(User element) throws SQLException;
    User findById(Integer id) throws SQLException;
    User findByLogin(String username, String password) throws SQLException;
    void updateUserPassword(User user, String password) throws SQLException;
    void updateUserType(User user, String type) throws SQLException;
    void resetAutoIncrement() throws SQLException;
    void setSafeUpdates(Integer n) throws SQLException;
    void deleteAllUsers() throws SQLException;
    void deleteUser(User user) throws SQLException;
}
