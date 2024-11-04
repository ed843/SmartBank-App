import database.db_fifth_topic.bank.mybatis.model.*;
import database.db_fifth_topic.bank.mybatis.service.*;
import org.testng.Assert;
import org.testng.annotations.Test;


public class MyBatisTest {
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

    // Create of CRUD
    @Test(priority = 0, description = "Create a Customer1")
    public void createCustomerTest01() {
        CustomerService customerService = new CustomerService();
        customerService.setSafeUpdates(0);
        customerService.deleteAllCustomers();
        customerService.resetAutoIncrement();
        customerService.setSafeUpdates(1);
        customerService.create(CUSTOMER_1);
        System.out.println("Customer successfully created: " + CUSTOMER_1.getUser_name());
        checkCustomer01(customerService.getCustomer(CUSTOMER_1.getCustomer_id()));
    }

    private void checkCustomer01(Customer customer) {
        Assert.assertEquals(customer.getUser_name(), CUSTOMER_1.getUser_name(), "UserName must match");
        Assert.assertEquals(customer.getPassword(), CUSTOMER_1.getPassword(), "Password must match");
    }

    // Create of CRUD
    @Test(priority = 1, description = "Create a Customer2")
    public void createCustomerTest02() {
        CustomerService customerService = new CustomerService();
        customerService.create(CUSTOMER_2);
        System.out.println("Customer successfully created: " + CUSTOMER_2.getUser_name());
        checkCustomer02(customerService.getCustomer(CUSTOMER_2.getCustomer_id()));
    }

    private void checkCustomer02(Customer customer) {
        Assert.assertEquals(customer.getUser_name(), CUSTOMER_2.getUser_name(), "UserName must match");
        Assert.assertEquals(customer.getPassword(), CUSTOMER_2.getPassword(), "Password must match");
    }

    @Test(priority = 2, description = "Create a Branch")
    public void createBranchTest03() {
        BranchService branchService = new BranchService();
        branchService.setSafeUpdates(0);
        branchService.deleteAllBranches();
        branchService.resetAutoIncrement();
        branchService.setSafeUpdates(1);
        branchService.create(BRANCH);
        System.out.println("Branch successfully created: " + BRANCH.getBranch_name());
        checkBranch(branchService.getBranch(BRANCH.getBranch_id()));
    }

    private void checkBranch(Branch branch) {
        Assert.assertEquals(branch.getBranch_name(), BRANCH.getBranch_name(), "BranchName must match");
        Assert.assertEquals(branch.getPhone(), BRANCH.getPhone(), "Phone must match");
    }

    @Test(priority = 3, description = "Create an Account Type")
    public void createAccountTypeTest04() {
        AccountTypeService accountTypeService = new AccountTypeService();
        accountTypeService.setSafeUpdates(0);
        accountTypeService.deleteAllAccountTypes();
        accountTypeService.resetAutoIncrement();
        accountTypeService.setSafeUpdates(1);
        accountTypeService.create(ACCOUNT_TYPE);
        System.out.println("Account Type successfully created: " + ACCOUNT_TYPE.getAccount_type_name());
        checkAccountType(accountTypeService.getAccountType(ACCOUNT_TYPE.getAccount_type_id()));
    }

    private void checkAccountType(AccountType accountType) {
        Assert.assertEquals(accountType.getAccount_type_name(), ACCOUNT_TYPE.getAccount_type_name(),
                "Account Type Name must match");
        Assert.assertEquals(accountType.getInterest_rate(), ACCOUNT_TYPE.getInterest_rate(),
                "Interest rate must match");
    }

    @Test(priority = 4, description = "Create an Account1")
    public void createAccountTest05() {
        AccountService accountService = new AccountService();
        accountService.setSafeUpdates(0);
        accountService.deleteAllAccounts();
        accountService.resetAutoIncrement();
        accountService.setSafeUpdates(1);
        accountService.create(ACCOUNT_1);
        System.out.println("Account successfully created: " + ACCOUNT_1.getAccount_id());
        checkAccount01(accountService.getAccount(ACCOUNT_1.getAccount_id()));
    }

    private void checkAccount01(Account account) {
        Assert.assertEquals(account.getAccount_opened(), ACCOUNT_1.getAccount_opened(),
                "Account opened date must match");
        Assert.assertEquals(account.getType_id(), ACCOUNT_1.getType_id(),
                "Account type must match");
        Assert.assertEquals(account.getBalance(), ACCOUNT_1.getBalance(),
                "Balance must match");
    }

    @Test(priority = 5, description = "Create an Account2")
    public void createAccountTest06() {
        AccountService accountService = new AccountService();
        accountService.create(ACCOUNT_2);
        System.out.println("Account successfully created: " + ACCOUNT_2.getAccount_id());
        checkAccount02(accountService.getAccount(ACCOUNT_2.getAccount_id()));
    }

    private void checkAccount02(Account account) {
        Assert.assertEquals(account.getAccount_opened(), ACCOUNT_2.getAccount_opened(),
                "Account opened date must match");
        Assert.assertEquals(account.getType_id(), ACCOUNT_2.getType_id(),
                "Account type must match");
        Assert.assertEquals(account.getBalance(), ACCOUNT_2.getBalance(),
                "Balance must match");
    }

    @Test(priority = 6, description = "Select the Customer1")
    public void selectCustomerTest07() {
        CustomerService customerService = new CustomerService();
        Customer customer = customerService.getCustomer(CUSTOMER_1.getCustomer_id());
        System.out.println("Customer successfully found, customer_id: " + customer.getCustomer_id()
                + " Customer name: " + customer.getUser_name());
    }

    @Test(priority = 7, description = "Select the Branch")
    public void selectBranchTest08() {
        BranchService branchService = new BranchService();
        Branch branch = branchService.getBranch(BRANCH.getBranch_id());
        System.out.println("Branch successfully found, branch_id: " + branch.getBranch_id()
                + " Branch name: " + branch.getBranch_name());
    }

    @Test(priority = 8, description = "Select the Account Type")
    public void selectAccountTypeTest09() {
        AccountTypeService accountTypeService = new AccountTypeService();
        AccountType accountType = accountTypeService.getAccountType(ACCOUNT_TYPE.getAccount_type_id());
        System.out.println("Account Type successfully found, account_type_id: " + accountType.getAccount_type_id()
                + " Account Type name: " + accountType.getAccount_type_name());
    }

    @Test(priority = 9, description = "Select the Account1")
    public void selectAccountTest10() {
        AccountService accountService = new AccountService();
        Account account = accountService.getAccount(ACCOUNT_1.getAccount_id());
        System.out.println("Account successfully found, account_id: " + account.getAccount_id()
                + " Account balance: $" + account.getBalance());
    }
    

    @Test(priority = 10, description = "Update the Customer1's password")
    public void updateCustomerPasswordTest11() {
        CustomerService customerService = new CustomerService();
        Customer customer = customerService.getCustomer(CUSTOMER_1.getCustomer_id());
        String newPassword = "NewPassword";
        customerService.updatePassword(newPassword, CUSTOMER_1.getCustomer_id());
        Customer updatedCustomer = customerService.getCustomer(CUSTOMER_1.getCustomer_id());
        Assert.assertEquals(updatedCustomer.getCustomer_id(), customer.getCustomer_id(),
                "Customer id must match");
        Assert.assertEquals(updatedCustomer.getUser_name(), customer.getUser_name(),
                "User name must match");
        Assert.assertEquals(updatedCustomer.getPassword(), newPassword,
                "Password must match");
    }

    @Test(priority = 11, description = "Update the Account2's balance")
    public void updateAccountBalanceTest12() {
        AccountService accountService = new AccountService();
        Account account = accountService.getAccount(ACCOUNT_2.getAccount_id());
        Double newBalance = 80000.00;
        accountService.updateBalance(newBalance, ACCOUNT_2.getAccount_id());
        Account updatedAccount = accountService.getAccount(ACCOUNT_2.getAccount_id());
        Assert.assertEquals(updatedAccount.getAccount_id(), account.getAccount_id(), "Account id must match");
        Assert.assertEquals(updatedAccount.getUser_id(), account.getUser_id(), "User id must match");
        Assert.assertEquals(updatedAccount.getBalance(), newBalance, "Balance must match");
    }

    @Test(priority = 12, description = "Update the Branch's phone")
    public void updateBranchPhoneTest13() {
        BranchService branchService = new BranchService();
        Branch branch = branchService.getBranch(BRANCH.getBranch_id());
        String newPhone = "3077018838";
        branchService.updatePhone(newPhone, BRANCH.getBranch_id());
        Branch updatedBranch = branchService.getBranch(BRANCH.getBranch_id());
        Assert.assertEquals(updatedBranch.getBranch_id(), branch.getBranch_id(), "Branch id must match");
        Assert.assertEquals(updatedBranch.getBranch_name(), branch.getBranch_name(), "Branch name must match");
        Assert.assertEquals(updatedBranch.getPhone(), newPhone, "Phone must match");
    }

    @Test(priority = 13, description = "Delete the Account1")
    public void deleteAccountTest14() {
        AccountService accountService = new AccountService();
        Account account = accountService.getAccount(ACCOUNT_1.getAccount_id());
        accountService.deleteAccount(account);
        Account deletedAccount = accountService.getAccount(ACCOUNT_1.getAccount_id());
        if (deletedAccount == null) {
            System.out.println("Successfully deleted account with account_id: " + account.getAccount_id());
        }
    }

    @Test(priority = 14, description = "Delete the Account2")
    public void deleteAccountTest15() {
        AccountService accountService = new AccountService();
        Account account = accountService.getAccount(ACCOUNT_2.getAccount_id());
        accountService.deleteAccount(account);
        Account deletedAccount = accountService.getAccount(ACCOUNT_2.getAccount_id());
        if (deletedAccount == null) {
            System.out.println("Successfully deleted account with account_id: " + account.getAccount_id());
        }
    }

    // Delete of CRUD
    @Test(priority = 15, description = "Delete the Customer1")
    public void deleteCustomerTest16() {
        CustomerService customerService = new CustomerService();
        Customer customer = customerService.getCustomer(CUSTOMER_1.getCustomer_id());
        customerService.deleteCustomer(customer);
        Customer deletedCustomer = customerService.getCustomer(CUSTOMER_1.getCustomer_id());
        if (deletedCustomer == null) {
            System.out.println("Successfully deleted customer with customer_id: " + customer.getCustomer_id());
        }
    }

    @Test(priority = 16, description = "Delete the Customer2")
    public void deleteCustomerTest17() {
        CustomerService customerService = new CustomerService();
        Customer customer = customerService.getCustomer(CUSTOMER_2.getCustomer_id());
        customerService.deleteCustomer(customer);
        Customer deletedCustomer = customerService.getCustomer(CUSTOMER_2.getCustomer_id());
        if (deletedCustomer == null) {
            System.out.println("Successfully deleted customer with customer_id: " + customer.getCustomer_id());
        }
    }

    @Test(priority = 17, description = "Delete the Account Type")
    public void deleteAccountTypeTest18() {
        AccountTypeService accountTypeService = new AccountTypeService();
        AccountType accountType = accountTypeService.getAccountType(ACCOUNT_TYPE.getAccount_type_id());
        accountTypeService.deleteAccountType(accountType);
        AccountType deletedAccountType = accountTypeService.getAccountType(ACCOUNT_TYPE.getAccount_type_id());
        if (deletedAccountType == null) {
            System.out.println("Successfully deleted account type with account_type_id: "
                    + accountType.getAccount_type_id());
        }
    }

    @Test(priority = 18, description = "Delete the Branch")
    public void deleteBranchTest19() {
        BranchService branchService = new BranchService();
        Branch branch = branchService.getBranch(BRANCH.getBranch_id());
        branchService.deleteBranch(branch);
        Branch deletedBranch = branchService.getBranch(BRANCH.getBranch_id());
        if (deletedBranch == null) {
            System.out.println("Successfully deleted branch with branch_id: " + branch.getBranch_id());
        }
    }
}
