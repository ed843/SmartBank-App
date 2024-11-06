package projects.first_topic.smart_bank_app.constant;

public class ProjectConstant {
    //Property Constants-----------------------
    public static final String PROPERTIES_FILE = "dao.properties";
    public static final String PROPERTY_URL = "url";
    public static final String PROPERTY_DRIVER = "driver";
    public static final String PROPERTY_USERNAME = "user";
    public static final String PROPERTY_PASSWORD = "password";
    public static final String PROPERTY_KEY_NAME = "mySQL";


    // CREATE of CRUD
    public static final String SQL_INSERT_USER = "INSERT INTO User "
            + "(user_name, user_type, password, first_name, last_name, phone, email, credit_score, "
            + "annual_income, loan_amount, registration_date) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
    public static final String SQL_INSERT_ACCOUNT = "INSERT INTO Account (user_id, account_type, balance) "
            + "VALUES (?, ?, ?);";
    public static final String SQL_INSERT_LOAN_APPLICATION = "INSERT INTO LoanApplication "
            + "(user_id, loan_type, amount, start_date, end_date, application_status, application_date) "
            + "VALUES (?, ?, ?, ?, ?, ?, ?);";
    public static final String SQL_INSERT_TRANSACTION = "INSERT INTO Transaction "
            + "(account_id, transaction_type, transaction_amount, transaction_date, account_balance_before, "
            + "account_balance_after) VALUES (?, ?, ?, ?, ?, ?);";


    // SELECT/READ of CRUD
    public static final String SQL_FIND_BY_USER_ID = "SELECT * FROM User WHERE user_id = ?;";
    public static final String SQL_FIND_BY_LOGIN = "SELECT * FROM User WHERE user_name = ? AND password = ?;";
    public static final String SQL_FIND_BY_ACCOUNT_ID = "SELECT * FROM Account WHERE account_id = ?;";
    public static final String SQL_FIND_ACCOUNT_BY_USER_ID = "SELECT * FROM Account WHERE user_id = ?;";
    public static final String SQL_FIND_BY_LOAN_APPLICATION_ID
            = "SELECT * FROM LoanApplication WHERE application_id = ?;";
    public static final String SQL_FIND_BY_TRANSACTION_ID = "SELECT * FROM Transaction WHERE transaction_id = ?;";


    // UPDATE of CRUD
    public static final String SQL_UPDATE_USER_PASSWORD = "UPDATE User SET password = ? WHERE user_id = ?;";
    public static final String SQL_UPDATE_USER_TYPE = "UPDATE User SET user_type = ? WHERE user_id = ?;";
    public static final String SQL_UPDATE_ACCOUNT_BALANCE = "UPDATE Account SET balance = ? WHERE account_id = ?;";
    public static final String SQL_UPDATE_LOAN_APPLICATION_STATUS
            = "UPDATE LoanApplication SET application_status = ? WHERE application_id = ?;";


    // DELETE of CRUD
    public static final String SQL_DELETE_USER = "DELETE FROM User WHERE user_id = ?;";
    public static final String SQL_DELETE_ACCOUNT = "DELETE FROM Account WHERE account_id = ?;";
    public static final String SQL_DELETE_LOAN_APPLICATION = "DELETE FROM LoanApplication WHERE application_id = ?;";
    public static final String SQL_DELETE_TRANSACTION = "DELETE FROM Transaction WHERE transaction_id = ?;";


    // Clear Table and reset AUTO_INCREMENT;
    public static final String SQL_SET_SAFE_UPDATES = "SET SQL_SAFE_UPDATES = ?;";
    public static final String SQL_DELETE_ALL_USER = "DELETE FROM User;";
    public static final String SQL_RESET_AUTO_INCREMENT_USER = "ALTER TABLE User AUTO_INCREMENT= ?;";
    public static final String SQL_DELETE_ALL_ACCOUNT = "DELETE FROM Account;";
    public static final String SQL_RESET_AUTO_INCREMENT_ACCOUNT = "ALTER TABLE Account AUTO_INCREMENT= ?;";
    public static final String SQL_DELETE_ALL_LOAN_APPLICATON = "DELETE FROM LoanApplication;";
    public static final String SQL_RESET_AUTO_INCREMENT_LOAN_APPLICATION
            = "ALTER TABLE LoanApplication AUTO_INCREMENT= ?;";
    public static final String SQL_DELETE_ALL_TRANSACTION = "DELETE FROM Transaction;";
    public static final String SQL_RESET_AUTO_INCREMENT_TRANSACTION = "ALTER TABLE Transaction AUTO_INCREMENT= ?;";


    // List of DAO type supported by the factory----------------------
    public static final int ORACLE = 1;
    public static final int MYSQL = 2;
    public static final int POSTGRESQL = 3;
}
