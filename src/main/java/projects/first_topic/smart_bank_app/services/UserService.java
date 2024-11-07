package projects.first_topic.smart_bank_app.services;
import java.sql.*;
import projects.first_topic.smart_bank_app.dao.IUserManagement;
import projects.first_topic.smart_bank_app.exception.DAOException;
import projects.first_topic.smart_bank_app.factory.DAOFactory;
import projects.first_topic.smart_bank_app.model.User;


public class UserService {
    private static IUserManagement iUserManagement = null;

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

    public void updateUserPassword(User user, String password) throws SQLException{
        iUserManagement.updateUserPassword(user, password);
    }

    public void deleteAllUsers() throws SQLException {
        iUserManagement.deleteAllUsers();
    }

    public void deleteUser(User user) throws SQLException {
        iUserManagement.deleteUser(user);
    }

    public static User getUser(Integer id) throws SQLException {
        return iUserManagement.findById(id);
    }
}
