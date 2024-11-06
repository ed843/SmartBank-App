package projects.first_topic.smart_bank_app.dao;
import projects.first_topic.smart_bank_app.model.LoanApplication;
import java.sql.SQLException;

public interface ILoanApplicationManagement extends IGenericManagement<LoanApplication, Integer> {
    void create(LoanApplication element) throws SQLException;
    LoanApplication findById(Integer id) throws SQLException;
    void resetAutoIncrement() throws SQLException;
    void updateLoanApplicationStatus(LoanApplication application) throws SQLException;
    void setSafeUpdates(Integer n) throws SQLException;
    void deleteAllLoanApplications() throws SQLException;
    void deleteLoanApplication(LoanApplication loanApplication) throws SQLException;
}
