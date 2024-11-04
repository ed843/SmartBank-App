package projects.first_topic.smart_bank_app.factory;
import projects.first_topic.smart_bank_app.dao.IAccountManagement;
import projects.first_topic.smart_bank_app.dao.ILoanApplicationManagement;
import projects.first_topic.smart_bank_app.dao.ITransactionManagement;
import projects.first_topic.smart_bank_app.dao.IUserManagement;
import projects.first_topic.smart_bank_app.dao.jdbc.MySQLAccountManager;
import projects.first_topic.smart_bank_app.dao.jdbc.MySQLLoanApplicationManager;
import projects.first_topic.smart_bank_app.dao.jdbc.MySQLTransactionManager;
import projects.first_topic.smart_bank_app.dao.jdbc.MySQLUserManager;
import projects.first_topic.smart_bank_app.exception.DAOException;

public class MySQLDAOFactory extends DAOFactory {
    @Override
    public IUserManagement getUserManagement() throws DAOException {
        return new MySQLUserManager();
    }

    @Override
    public IAccountManagement getAccountManagement() throws DAOException {
        return new MySQLAccountManager();
    }

    @Override
    public ILoanApplicationManagement getLoanApplicationManagement() throws DAOException {
        return new MySQLLoanApplicationManager();
    }

    @Override
    public ITransactionManagement getTransactionManagement() throws DAOException {
        return new MySQLTransactionManager();
    }
}
