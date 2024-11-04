package projects.first_topic.smart_bank_app.factory;

import projects.first_topic.smart_bank_app.dao.IAccountManagement;
import projects.first_topic.smart_bank_app.dao.ILoanApplicationManagement;
import projects.first_topic.smart_bank_app.dao.ITransactionManagement;
import projects.first_topic.smart_bank_app.dao.IUserManagement;
import projects.first_topic.smart_bank_app.exception.DAOException;

public class OracleDAOFactory extends DAOFactory {
    @Override
    public IUserManagement getUserManagement() throws DAOException {
        return null;
    }

    @Override
    public IAccountManagement getAccountManagement() throws DAOException {
        return null;
    }

    @Override
    public ILoanApplicationManagement getLoanApplicationManagement() throws DAOException {
        return null;
    }

    @Override
    public ITransactionManagement getTransactionManagement() throws DAOException {
        return null;
    }
}
