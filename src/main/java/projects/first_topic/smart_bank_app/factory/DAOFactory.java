package projects.first_topic.smart_bank_app.factory;
import projects.first_topic.smart_bank_app.dao.*;
import projects.first_topic.smart_bank_app.exception.*;
import static projects.first_topic.smart_bank_app.constant.ProjectConstant.*;


public abstract class DAOFactory {
    public abstract IUserManagement getUserManagement() throws DAOException;
    public abstract IAccountManagement getAccountManagement() throws DAOException;
    public abstract ILoanApplicationManagement getLoanApplicationManagement() throws DAOException;
    public abstract ITransactionManagement getTransactionManagement() throws DAOException;

    public static DAOFactory getDAOFactory(int whichFactory) {
        switch (whichFactory) {
            case ORACLE:
                return new OracleDAOFactory();
            case MYSQL:
                return new MySQLDAOFactory();
            case POSTGRESQL:
                return new PostgreSQLDAOFactory();
            default:
                return null;
        }
    }
}
