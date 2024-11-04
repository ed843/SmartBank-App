package projects.first_topic.smart_bank_app.services;
import java.sql.*;
import projects.first_topic.smart_bank_app.dao.ILoanApplicationManagement;
import projects.first_topic.smart_bank_app.exception.DAOException;
import projects.first_topic.smart_bank_app.factory.DAOFactory;
import projects.first_topic.smart_bank_app.model.*;



public class LoanApplicationService {
    private final ILoanApplicationManagement iLoanApplicationManagement;

    public LoanApplicationService(DAOFactory daoFactory) throws DAOException {
        this.iLoanApplicationManagement = daoFactory.getLoanApplicationManagement();
    }

    public void createLoanApplication(LoanApplication loanApplication) throws SQLException {
        iLoanApplicationManagement.create(loanApplication);
    }

    public void resetAutoIncrement() throws SQLException {
        iLoanApplicationManagement.resetAutoIncrement();
    }

    public void setSafeUpdates(Integer n) throws SQLException {
        iLoanApplicationManagement.setSafeUpdates(n);
    }

    public void updateApplicationStatus(LoanApplication loanApplication) throws SQLException {
        iLoanApplicationManagement.updateLoanApplicationStatus(loanApplication);
    }

    public void deleteAllLoanApplications() throws SQLException {
        iLoanApplicationManagement.deleteAllLoanApplications();
    }

    public void deleteLoanApplication(LoanApplication loanApplication) throws SQLException {
        iLoanApplicationManagement.deleteLoanApplication(loanApplication);
    }

    public LoanApplication getLoanApplication(Integer id) throws SQLException {
        return iLoanApplicationManagement.findById(id);
    }
}
