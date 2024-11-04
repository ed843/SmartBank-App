import database.db_third_topic.bank.constant.ProjectConstant;
import database.db_third_topic.bank.factory.DAOFactory;
import database.db_third_topic.bank.model.*;
import database.db_third_topic.bank.services.AccountTypeService;
import database.db_third_topic.bank.services.AccountService;
import database.db_third_topic.bank.services.BranchService;
import database.db_third_topic.bank.services.CustomerService;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.sql.SQLException;

public class DAOTest {
    private static final Customer CUSTOMER_1 = new Customer() {
        {
            setUser_name("JamesSmith");
            setPassword("password123");
            setFirst_name("James");
            setLast_name("Smith");
            setPhone("4748995503");
            setEmail("james.smith@gmail.com");
            setRegistration_date("2010-02-01");
        }
    };

    private static final Customer CUSTOMER_2 = new Customer() {
        {
            setUser_name("MaryJohnson");
            setPassword("password456");
            setFirst_name("Mary");
            setLast_name("Johnson");
            setPhone("6234213337");
            setEmail("mary.johnson@yahoo.com");
            setRegistration_date("2011-06-10");
        }
    };

    private static final Branch BRANCH = new Branch() {
        {
            setBranch_name("Branch01");
            setAddress("108N Belair Rd, Evans, Georgia, 30809");
            setPhone("7068550483");
        }
    };

    private static final AccountType ACCOUNT_TYPE = new AccountType() {
        {
            setAccount_type_name("Checking");
            setInterest_rate(0.01);
        }
    };

    private static final Account ACCOUNT_1 = new Account() {
        {
            setUser_id(1);
            setAccount_opened("2010-02-01");
            setType_id(1);
            setBalance(50000.0);
            setBranch_id(1);
        }
    };

    private static final Account ACCOUNT_2 = new Account() {
        {
            setUser_id(2);
            setAccount_opened("2011-06-10");
            setType_id(1);
            setBalance(82000.0);
            setBranch_id(1);
        }
    };

    // Start Testing:
    // Create/Insert of CRUD
    @Test (priority = 0, description = "Create a customer1")
    public void createCustomerTest01() throws SQLException {
        //Create a DAOFactory instance for MySQL
        DAOFactory mySQLFactory = DAOFactory.getDAOFactory(ProjectConstant.MYSQL);
        CustomerService customerService = new CustomerService(mySQLFactory);
        System.out.println("DAOFactory successfully obtained: " + mySQLFactory);
        customerService.setSafeUpdates(0);
        customerService.deleteAllCustomers();
        customerService.setSafeUpdates(1);
        customerService.resetAutoIncrement();
        customerService.createCustomer(CUSTOMER_1);
        System.out.println("User successfully created: " + CUSTOMER_1.getUser_name());
        checkCustomer01(customerService.getCustomer(CUSTOMER_1.getCustomer_id()));
    }

    private void checkCustomer01(Customer customer) {
        Assert.assertEquals(customer.getUser_name(), CUSTOMER_1.getUser_name(), "User name must match");
        Assert.assertEquals(customer.getPassword(), CUSTOMER_1.getPassword(), "Password must match");
        Assert.assertEquals(customer.getEmail(), CUSTOMER_1.getEmail(), "Email must match");
    }

    @Test (priority = 1, description = "Create a customer2")
    public void createCustomerTest02() throws SQLException {
        //Create a DAOFactory instance for MySQL
        DAOFactory mySQLFactory = DAOFactory.getDAOFactory(ProjectConstant.MYSQL);
        CustomerService customerService = new CustomerService(mySQLFactory);
        System.out.println("DAOFactory successfully obtained: " + mySQLFactory);
        customerService.createCustomer(CUSTOMER_2);
        System.out.println("User successfully created: " + CUSTOMER_2.getUser_name());
        checkCustomer02(customerService.getCustomer(CUSTOMER_2.getCustomer_id()));
    }

    private void checkCustomer02(Customer customer) {
        Assert.assertEquals(customer.getUser_name(), CUSTOMER_2.getUser_name(), "User name must match");
        Assert.assertEquals(customer.getPassword(), CUSTOMER_2.getPassword(), "Password must match");
        Assert.assertEquals(customer.getEmail(), CUSTOMER_2.getEmail(), "Email must match");
    }

    @Test (priority = 2, description = "Create a branch")
    public void createBranchTest03() throws SQLException {
        //Create a DAOFactory instance for MySQL
        DAOFactory mySQLFactory = DAOFactory.getDAOFactory(ProjectConstant.MYSQL);
        BranchService branchService = new BranchService(mySQLFactory);
        System.out.println("DAOFactory successfully obtained: " + mySQLFactory);
        branchService.setSafeUpdates(0);
        branchService.deleteAllBranches();
        branchService.setSafeUpdates(1);
        branchService.resetAutoIncrement();
        branchService.createBranch(BRANCH);
        System.out.println("Branch successfully created: " + BRANCH.getBranch_name());
        checkBranch(branchService.getBranch(BRANCH.getBranch_id()));
    }

    private void checkBranch(Branch branch) {
        Assert.assertEquals(branch.getBranch_name(), BRANCH.getBranch_name(), "Branch name must match");
        Assert.assertEquals(branch.getAddress(), BRANCH.getAddress(), "Address must match");
        Assert.assertEquals(branch.getPhone(), BRANCH.getPhone(), "Phone must match");
    }

    @Test (priority = 3, description = "Create an Account Type")
    public void createAccountTypeTest04() throws SQLException {
        //Create a DAOFactory instance for MySQL
        DAOFactory mySQLFactory = DAOFactory.getDAOFactory(ProjectConstant.MYSQL);
        AccountTypeService accountTypeService = new AccountTypeService(mySQLFactory);
        System.out.println("DAOFactory successfully obtained: " + mySQLFactory);
        accountTypeService.setSafeUpdates(0);
        accountTypeService.deleteAllAccountTypes();
        accountTypeService.setSafeUpdates(1);
        accountTypeService.resetAutoIncrement();
        accountTypeService.createAccountType(ACCOUNT_TYPE);
        System.out.println("Account Type successfully created: " + ACCOUNT_TYPE.getAccount_type_name());
        checkAccountType(accountTypeService.getAccountType(ACCOUNT_TYPE.getAccount_type_id()));
    }

    private void checkAccountType(AccountType accountType) {
        Assert.assertEquals(accountType.getAccount_type_name(), ACCOUNT_TYPE.getAccount_type_name(),
                "Account name must match");
        Assert.assertEquals(accountType.getInterest_rate(), ACCOUNT_TYPE.getInterest_rate(),
                "Interest Rate must match");
    }

    @Test (priority = 4, description = "Create an Account1")
    public void createAccountTest05() throws SQLException {
        //Create a DAOFactory instance for MySQL
        DAOFactory mySQLFactory = DAOFactory.getDAOFactory(ProjectConstant.MYSQL);
        AccountService accountService = new AccountService(mySQLFactory);
        System.out.println("DAOFactory successfully obtained: " + mySQLFactory);
        accountService.setSafeUpdates(0);
        accountService.deleteAllAccounts();
        accountService.setSafeUpdates(1);
        accountService.resetAutoIncrement();
        accountService.createAccount(ACCOUNT_1);
        System.out.println("Account successfully created with account_id: " + ACCOUNT_1.getAccount_id());
        checkAccount01(accountService.getAccount(ACCOUNT_1.getAccount_id()));
    }

    private void checkAccount01(Account account) {
        Assert.assertEquals(account.getAccount_opened(), ACCOUNT_1.getAccount_opened(),
                "Account open date must match");
        Assert.assertEquals(account.getUser_id(), ACCOUNT_1.getUser_id(), "User id must match");
        Assert.assertEquals(account.getBalance(), ACCOUNT_1.getBalance(), "Balance must match");
    }

    @Test (priority = 5, description = "Create an Account2")
    public void createAccountTest06() throws SQLException {
        //Create a DAOFactory instance for MySQL
        DAOFactory mySQLFactory = DAOFactory.getDAOFactory(ProjectConstant.MYSQL);
        AccountService accountService = new AccountService(mySQLFactory);
        System.out.println("DAOFactory successfully obtained: " + mySQLFactory);
        accountService.createAccount(ACCOUNT_2);
        System.out.println("Account successfully created with account_id: " + ACCOUNT_2.getAccount_id());
        checkAccount02(accountService.getAccount(ACCOUNT_2.getAccount_id()));
    }

    private void checkAccount02(Account account) {
        Assert.assertEquals(account.getAccount_opened(), ACCOUNT_2.getAccount_opened(),
                "Account open date must match");
        Assert.assertEquals(account.getUser_id(), ACCOUNT_2.getUser_id(), "User id must match");
        Assert.assertEquals(account.getBalance(), ACCOUNT_2.getBalance(), "Balance must match");
    }

    // Read/Select of CRUD
    @Test(priority = 6, description = "Select a customer with customer_id=1")
    public void readCustomerTest07() throws SQLException {
        DAOFactory mySQLFactory = DAOFactory.getDAOFactory(ProjectConstant.MYSQL);
        CustomerService customerService = new CustomerService(mySQLFactory);
        System.out.println("DAOFactory successfully obtained: " + mySQLFactory);
        Customer customer = customerService.getCustomer(1);
        System.out.println("User successfully found, customer_id: " + customer.getCustomer_id()
                + " User name: " + customer.getUser_name());
    }

    @Test(priority = 7, description = "Select a branch with branch_id=1")
    public void readBranchTest08() throws SQLException {
        DAOFactory mySQLFactory = DAOFactory.getDAOFactory(ProjectConstant.MYSQL);
        BranchService branchService = new BranchService(mySQLFactory);
        System.out.println("DAOFactory successfully obtained: " + mySQLFactory);
        Branch branch = branchService.getBranch(1);
        System.out.println("Branch successfully found, customer_id: " + branch.getBranch_id()
                + " Branch name: " + branch.getBranch_name());
    }

    @Test(priority = 8, description = "Select an account type with account_type_id=1")
    public void readAccountTypeTest09() throws SQLException {
        DAOFactory mySQLFactory = DAOFactory.getDAOFactory(ProjectConstant.MYSQL);
        AccountTypeService accountTypeService = new AccountTypeService(mySQLFactory);
        System.out.println("DAOFactory successfully obtained: " + mySQLFactory);
        AccountType accountType = accountTypeService.getAccountType(1);
        System.out.println("Account Type successfully found, account_type_id: " + accountType.getAccount_type_id()
                + " Account Type name: " + accountType.getAccount_type_name());
    }

    @Test(priority = 9, description = "Select an account with account_id=1")
    public void readAccountTest10() throws SQLException {
        DAOFactory mySQLFactory = DAOFactory.getDAOFactory(ProjectConstant.MYSQL);
        AccountService accountService = new AccountService(mySQLFactory);
        System.out.println("DAOFactory successfully obtained: " + mySQLFactory);
        Account account = accountService.getAccount(1);
        System.out.println("Account successfully found, account_type_id: " + account.getAccount_id()
                + " Account balance: $" + account.getBalance());
    }

    // Update of CRUD
    @Test(priority = 10, description = "Update a customer's password with customer_id=1")
    public void updatePasswordCustomerTest11() throws SQLException {
        DAOFactory mySQLFactory = DAOFactory.getDAOFactory(ProjectConstant.MYSQL);
        CustomerService customerService = new CustomerService(mySQLFactory);
        System.out.println("DAOFactory successfully obtained: " + mySQLFactory);
        Customer customer = customerService.getCustomer(1);
        String newPassword = "NewPassword";
        customerService.updatePassword(customer, newPassword);
        Customer updatedCustomer = customerService.getCustomer(1);
        Assert.assertEquals(updatedCustomer.getCustomer_id(), customer.getCustomer_id(), "Customer id must match");
        Assert.assertEquals(updatedCustomer.getUser_name(), customer.getUser_name(), "User name must match");
        Assert.assertEquals(updatedCustomer.getPassword(), newPassword, "Password must match");
    }

    @Test(priority = 11, description = "Update an account's balance with account_id=2")
    public void updateAccountBalanceTest12() throws SQLException {
        DAOFactory mySQLFactory = DAOFactory.getDAOFactory(ProjectConstant.MYSQL);
        AccountService accountService = new AccountService(mySQLFactory);
        System.out.println("DAOFactory successfully obtained: " + mySQLFactory);
        Account account = accountService.getAccount(2);
        Double newBalance = 80000.00;
        accountService.updateAccountBalance(account, newBalance);
        Account updatedAccount = accountService.getAccount(2);
        System.out.println("Account successfully updated with account_id: " + ACCOUNT_2.getAccount_id()
                + " balance: $" + ACCOUNT_2.getBalance());
        Assert.assertEquals(updatedAccount.getAccount_id(), ACCOUNT_2.getAccount_id(), "Account id must match");
        Assert.assertEquals(updatedAccount.getUser_id(), ACCOUNT_2.getAccount_id(), "User id must match");
        Assert.assertEquals(updatedAccount.getBalance(), newBalance, "Balance must match");
    }

    @Test(priority = 12, description = "Update a branch's phone with branch_id=1")
    public void updateBranchPhoneTest13() throws SQLException {
        DAOFactory mySQLFactory = DAOFactory.getDAOFactory(ProjectConstant.MYSQL);
        BranchService branchService = new BranchService(mySQLFactory);
        System.out.println("DAOFactory successfully obtained: " + mySQLFactory);
        Branch branch = branchService.getBranch(1);
        String newPhone = "3077018838";
        branchService.updateBranchPhone(branch, newPhone);
        Branch updatedBranch = branchService.getBranch(1);
        System.out.println("Branch successfully updated with branch_id: " + BRANCH.getBranch_id()
                + " phone: " + BRANCH.getPhone());
        Assert.assertEquals(updatedBranch.getBranch_id(), BRANCH.getBranch_id(), "Branch id must match");
        Assert.assertEquals(updatedBranch.getBranch_name(), BRANCH.getBranch_name(), "Branch name must match");
        Assert.assertEquals(updatedBranch.getPhone(), newPhone, "Phone must match");
    }

    // Delete of CRUD
    @Test(priority = 13, description = "Delete an account with account_id=1")
    public void deleteAccountTest14() throws SQLException {
        DAOFactory mySQLFactory = DAOFactory.getDAOFactory(ProjectConstant.MYSQL);
        AccountService accountService = new AccountService(mySQLFactory);
        System.out.println("DAOFactory successfully obtained: " + mySQLFactory);
        Account account = accountService.getAccount(1);
        accountService.deleteAccount(account);
        Account deletedAccount = accountService.getAccount(1);
        if (deletedAccount == null) {
            System.out.println("Successfully deleted account with account_id: " + account.getAccount_id());
        }
    }

    @Test(priority = 14, description = "Delete an account with account_id=1")
    public void deleteAccountTest15() throws SQLException {
        DAOFactory mySQLFactory = DAOFactory.getDAOFactory(ProjectConstant.MYSQL);
        AccountService accountService = new AccountService(mySQLFactory);
        System.out.println("DAOFactory successfully obtained: " + mySQLFactory);
        Account account = accountService.getAccount(2);
        accountService.deleteAccount(account);
        Account deletedAccount = accountService.getAccount(2);
        if (deletedAccount == null) {
            System.out.println("Successfully deleted account with account_id: " + account.getAccount_id());
        }
    }

    @Test(priority = 15, description = "Delete a customer with customer_id=1")
    public void deleteCustomerTest16() throws SQLException {
        DAOFactory mySQLFactory = DAOFactory.getDAOFactory(ProjectConstant.MYSQL);
        CustomerService customerService = new CustomerService(mySQLFactory);
        System.out.println("DAOFactory successfully obtained: " + mySQLFactory);
        Customer customer = customerService.getCustomer(1);
        customerService.deleteCustomer(customer);
        Customer deletedCustomer = customerService.getCustomer(1);
        if (deletedCustomer == null) {
            System.out.println("Successfully deleted customer with customer_id: " + customer.getCustomer_id());
        }
    }

    @Test(priority = 16, description = "Delete a customer with customer_id=2")
    public void deleteCustomerTest17() throws SQLException {
        DAOFactory mySQLFactory = DAOFactory.getDAOFactory(ProjectConstant.MYSQL);
        CustomerService customerService = new CustomerService(mySQLFactory);
        System.out.println("DAOFactory successfully obtained: " + mySQLFactory);
        Customer customer = customerService.getCustomer(2);
        customerService.deleteCustomer(customer);
        Customer deletedCustomer = customerService.getCustomer(2);
        if (deletedCustomer == null) {
            System.out.println("Successfully deleted customer with customer_id: " + customer.getCustomer_id());
        }
    }

    @Test(priority = 17, description = "Delete an account with account_id=1")
    public void deleteAccountTypeTest18() throws SQLException {
        DAOFactory mySQLFactory = DAOFactory.getDAOFactory(ProjectConstant.MYSQL);
        AccountTypeService accountTypeService = new AccountTypeService(mySQLFactory);
        System.out.println("DAOFactory successfully obtained: " + mySQLFactory);
        AccountType accountType = accountTypeService.getAccountType(1);
        accountTypeService.deleteAccountType(accountType);
        AccountType deletedAccountType = accountTypeService.getAccountType(1);
        if (deletedAccountType == null) {
            System.out.println("Successfully deleted account type with account_type_id: "
                    + accountType.getAccount_type_id());
        }
    }

    @Test(priority = 18, description = "Delete a branch with branch_id=1")
    public void deleteBranchTest19() throws SQLException {
        DAOFactory mySQLFactory = DAOFactory.getDAOFactory(ProjectConstant.MYSQL);
        BranchService branchService = new BranchService(mySQLFactory);
        System.out.println("DAOFactory successfully obtained: " + mySQLFactory);
        Branch branch = branchService.getBranch(1);
        branchService.deleteBranch(branch);
        Branch deletedBranch = branchService.getBranch(1);
        if (deletedBranch == null) {
            System.out.println("Successfully deleted branch with branch_id: " + branch.getBranch_id());
        }
    }
}
