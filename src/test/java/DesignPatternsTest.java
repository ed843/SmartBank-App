import database.db_fifth_topic.bank.mybatis.model.*;
import database.db_fifth_topic.bank.mybatis.service.BranchService;
import database.db_fifth_topic.bank.mybatis.service.CustomerService;
import database.db_sixth_topic.design_patterns.abstract_factory_pattern.AbstractFactory;
import database.db_sixth_topic.design_patterns.abstract_factory_pattern.FactoryGenerator;
import database.db_sixth_topic.design_patterns.abstract_factory_pattern.*;
import database.db_sixth_topic.design_patterns.decorator_pattern.BasicInformationlayer;
import database.db_sixth_topic.design_patterns.decorator_pattern.ContactInformationDisplay;
import database.db_sixth_topic.design_patterns.decorator_pattern.InformationDisplay;
import database.db_sixth_topic.design_patterns.decorator_pattern.RegistrationInformationDisplay;
import database.db_sixth_topic.design_patterns.facade_pattern.FacadeObjectFactory;
import database.db_sixth_topic.design_patterns.factory_pattern.*;
import database.db_sixth_topic.design_patterns.listener_pattern.StringListener;
import database.db_sixth_topic.design_patterns.listener_pattern.Subject;
import database.db_sixth_topic.design_patterns.listener_pattern.UpperCaseListener;
import database.db_sixth_topic.design_patterns.mvc_pattern.CustomerController;
import database.db_sixth_topic.design_patterns.mvc_pattern.CustomerView;
import database.db_sixth_topic.design_patterns.proxy_pattern.OperationsPerformed;
import database.db_sixth_topic.design_patterns.proxy_pattern.ProxyOperationsPerformed;
import database.db_sixth_topic.design_patterns.strategy_pattern.Context;
import database.db_sixth_topic.design_patterns.strategy_pattern.StringComparisionByCompareTo;
import database.db_sixth_topic.design_patterns.strategy_pattern.StringComparisionByOperator;
import org.testng.Assert;
import org.testng.annotations.Test;


public class DesignPatternsTest {
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

    private static final Customer CUSTOMER_3 = new Customer() {
        {
            setUser_name("MichaelWilliams");
            setPassword("password789");
            setFirst_name("Michael");
            setLast_name("Williams");
            setPhone("3077018838");
            setEmail("michael.williams@gmail.com");
            setRegistration_date("2012-08-30");
        }
    };

    private static final Branch BRANCH_1 = new Branch() {
        {
            setBranch_name("Branch01");
            setAddress("108N Belair Rd, Evans, Georgia, 30809");
            setPhone("7068550483");
        }
    };

    private static final Branch BRANCH_2 = new Branch() {
        {
            setBranch_name("Branch02");
            setAddress("9 Hartford Ave, West Springfield, MA 01089");
            setPhone("2535112433");
        }
    };

    private static final AccountType ACCOUNT_TYPE_1 = new AccountType() {
        {
            setAccount_type_name("Checking");
            setInterest_rate(0.01);
        }
    };

    // Factory Pattern, CreateCustomer
    @Test(priority = 1, description = "Create a Customer1")
    public void createCustomerTest01() {
        CreateCustomer createCustomer = new CreateCustomer();
        CustomerService customerService = new CustomerService();
        customerService.setSafeUpdates(0);
        customerService.deleteAllCustomers();
        customerService.resetAutoIncrement();
        customerService.setSafeUpdates(1);
        createCustomer.createObject((Customer) CUSTOMER_1);
        Customer customer = customerService.getCustomer(CUSTOMER_1.getCustomer_id());
        System.out.println("Customer successfully created: " + customer.getUser_name());
        checkCustomer1(customer);
    }

    private void checkCustomer1(Customer customer) {
        Assert.assertEquals(customer.getUser_name(), CUSTOMER_1.getUser_name(), "UserName must match");
        Assert.assertEquals(customer.getPassword(), CUSTOMER_1.getPassword(), "Password must match");
    }

    // Abstract Factory Pattern, CreatePerson
    @Test(priority = 2, description = "Create a Customer2")
    public void createCustomerTest02() {
        AbstractFactory personFactory = FactoryGenerator.getFactory("Person");
        CustomerService customerService = new CustomerService();
        CreatePerson person = personFactory.getPerson("Customer");
        person.createPerson(CUSTOMER_2);
        Customer customer = customerService.getCustomer(CUSTOMER_2.getCustomer_id());
        System.out.println("Customer successfully created: " + customer.getUser_name());
        checkCustomer2(customer);
    }

    private void checkCustomer2(Customer customer) {
        Assert.assertEquals(customer.getUser_name(), CUSTOMER_2.getUser_name(), "UserName must match");
        Assert.assertEquals(customer.getPassword(), CUSTOMER_2.getPassword(), "Password must match");
    }

    // Factory Pattern, CreateBranch
    @Test(priority = 3, description = "Create a Branch1")
    public void createBranchTest03() {
        CreateBranch createBranch = new CreateBranch();
        BranchService branchService = new BranchService();
        branchService.setSafeUpdates(0);
        branchService.deleteAllBranches();
        branchService.resetAutoIncrement();
        branchService.setSafeUpdates(1);
        createBranch.createObject((Branch) BRANCH_1);
        Branch branch = branchService.getBranch(BRANCH_1.getBranch_id());
        System.out.println("Customer successfully created: " + branch.getBranch_name());
        checkBranch1(branch);
    }

    private void checkBranch1(Branch branch) {
        Assert.assertEquals(branch.getBranch_name(), BRANCH_1.getBranch_name(), "Branch name must match");
        Assert.assertEquals(branch.getPhone(), BRANCH_1.getPhone(), "Branch phone must match");
    }

    // Abstract Factory Pattern, CreateFacility
    @Test(priority = 4, description = "Create a Branch2")
    public void createBranchTest04() {
        AbstractFactory facilityFactory = FactoryGenerator.getFactory("Facility");
        BranchService branchService = new BranchService();
        CreateFacility facility = facilityFactory.getFacility("Branch");
        facility.createFacility(BRANCH_2);
        Branch branch = branchService.getBranch(BRANCH_2.getBranch_id());
        System.out.println("Customer successfully created: " + branch.getBranch_name());
        checkBranch2(branch);
    }

    private void checkBranch2(Branch branch) {
        Assert.assertEquals(branch.getBranch_name(), BRANCH_2.getBranch_name(), "Branch name must match");
        Assert.assertEquals(branch.getPhone(), BRANCH_2.getPhone(), "Branch phone must match");
    }

    // Decorator Pattern, InformationDisplay
    @Test(priority = 5, description = "Select the Customer1")
    public void selectCustomerTest05() {
        InformationDisplay registrationInformationDisplay = new RegistrationInformationDisplay(
                new BasicInformationlayer());
        registrationInformationDisplay.display(CUSTOMER_1.getCustomer_id());
        System.out.println("");
        InformationDisplay decoratedRegDisplay = new RegistrationInformationDisplay(
                new ContactInformationDisplay(new BasicInformationlayer()));
        decoratedRegDisplay.display(CUSTOMER_1.getCustomer_id());
    }

    // Facade Pattern, DisplayCustomer
    @Test(priority = 6, description = "Select the Customer2")
    public void selectCustomerTest06() {
        FacadeObjectFactory facadeObjectFactory = new FacadeObjectFactory();
        facadeObjectFactory.displayCustomer(CUSTOMER_2.getCustomer_id());
    }

    // Facade Pattern, DisplayBranch
    @Test(priority = 7, description = "Select the Branch1")
    public void selectBranchTest07() {
        FacadeObjectFactory facadeObjectFactory = new FacadeObjectFactory();
        facadeObjectFactory.displayBranch(BRANCH_1.getBranch_id());
    }

    // Strategy Pattern, Context
    @Test(priority = 8, description = "Select and compare the Customers")
    public void selectCustomerTest08() {
        CustomerService customerService = new CustomerService();
        Customer customer1 = customerService.getCustomer(CUSTOMER_1.getCustomer_id());
        Customer customer2 = customerService.getCustomer(CUSTOMER_1.getCustomer_id());
        Context context = new Context(new StringComparisionByOperator());
        System.out.println("String are equal: "
                + context.executeStrategy(customer1.getUser_name(), customer2.getUser_name()));
        context = new Context(new StringComparisionByOperator());
        System.out.println("String are equal: "
                + context.executeStrategy(customer1.getEmail(), customer2.getEmail()));
        context = new Context(new StringComparisionByCompareTo());
        System.out.println("String are equal: "
                + context.executeStrategy(customer1.getFirst_name(), customer2.getFirst_name()));
    }

    // Listener Pattern, StringListener, UpperCaseListener
    @Test(priority = 9, description = "Update and UpperCase the Customer1's password")
    public void updateCustomerPasswordTest9() {
        Subject subject = new Subject();
        new StringListener(subject);
        new UpperCaseListener(subject);
        CustomerService customerService = new CustomerService();
        Customer customer = customerService.getCustomer(CUSTOMER_1.getCustomer_id());

        String state1 = "NewPassword";
        subject.setState(state1, CUSTOMER_1.getCustomer_id());
        Customer updatedCustomer1 = customerService.getCustomer(CUSTOMER_1.getCustomer_id());
        Assert.assertEquals(updatedCustomer1.getCustomer_id(), customer.getCustomer_id(),
                "Customer id must match");
        Assert.assertEquals(updatedCustomer1.getUser_name(), customer.getUser_name(),
                "User name must match");
        Assert.assertEquals(updatedCustomer1.getPassword(), state1.toUpperCase(),
                "Password must match");

        String state2 = "NewPassword2";
        subject.setState(state2, CUSTOMER_1.getCustomer_id());
        Customer updatedCustomer2 = customerService.getCustomer(CUSTOMER_1.getCustomer_id());
        Assert.assertEquals(updatedCustomer2.getCustomer_id(), customer.getCustomer_id(),
                "Customer id must match");
        Assert.assertEquals(updatedCustomer2.getUser_name(), customer.getUser_name(),
                "User name must match");
        Assert.assertEquals(updatedCustomer2.getPassword(), state2.toUpperCase(),
                "Password must match");

    }

    // Listener Pattern, UpperCaseListener
    @Test(priority = 10, description = "Update and UpperCase the Customer2's password")
    public void updateCustomerPasswordTest10() {
        Subject subject = new Subject();
        new UpperCaseListener(subject);
        CustomerService customerService = new CustomerService();
        Customer customer = customerService.getCustomer(CUSTOMER_2.getCustomer_id());
        String state = "NewPassword2";
        subject.setState(state, CUSTOMER_2.getCustomer_id());
        Customer updatedCustomer = customerService.getCustomer(CUSTOMER_2.getCustomer_id());
        Assert.assertEquals(updatedCustomer.getCustomer_id(), customer.getCustomer_id(),
                "Customer id must match");
        Assert.assertEquals(updatedCustomer.getUser_name(), customer.getUser_name(),
                "User name must match");
        Assert.assertEquals(updatedCustomer.getPassword(), state.toUpperCase(),
                "Password must match");
    }

    // Proxy Pattern, OperationsPerformed
    @Test(priority = 11, description = "Select to view Customer2")
    public void selectCustomerTest11() {
        CustomerService customerService = new CustomerService();
        OperationsPerformed operationsPerformed = new ProxyOperationsPerformed("USER");
        operationsPerformed.view(CUSTOMER_2.getCustomer_id());
        Customer customer = customerService.getCustomer(CUSTOMER_2.getCustomer_id());
        String newPhone = "1111111111";
        operationsPerformed.edit(newPhone, CUSTOMER_2.getCustomer_id());
        Customer updatedCustomer = customerService.getCustomer(CUSTOMER_2.getCustomer_id());
        Assert.assertEquals(updatedCustomer.getCustomer_id(), customer.getCustomer_id(),
                "Customer id must match");
        Assert.assertEquals(updatedCustomer.getUser_name(), customer.getUser_name(),
                "User name must match");
        Assert.assertEquals(updatedCustomer.getPhone(), customer.getPhone(),
                "Phone must match");
    }

    // Proxy Pattern, OperationsPerformed
    @Test(priority = 12, description = "Update Customer2's Phone")
    public void updateCustomerPhoneTest12() {
        CustomerService customerService = new CustomerService();
        OperationsPerformed operationsPerformed = new ProxyOperationsPerformed("ADMIN");
        operationsPerformed.view(CUSTOMER_2.getCustomer_id());
        Customer customer = customerService.getCustomer(CUSTOMER_2.getCustomer_id());
        String newPhone = "1111111111";
        operationsPerformed.edit(newPhone, CUSTOMER_2.getCustomer_id());
        Customer updatedCustomer = customerService.getCustomer(CUSTOMER_2.getCustomer_id());
        Assert.assertEquals(updatedCustomer.getCustomer_id(), customer.getCustomer_id(),
                "Customer id must match");
        Assert.assertEquals(updatedCustomer.getUser_name(), customer.getUser_name(),
                "User name must match");
        Assert.assertEquals(updatedCustomer.getPhone(), newPhone,
                "Phone must match");
    }

    // MVC Pattern, CustomerView, CustomerController
    @Test(priority = 13, description = "Update Customer2")
    public void updateCustomerTest13() {
        CustomerService customerService = new CustomerService();
        Customer model = customerService.getCustomer(CUSTOMER_2.getCustomer_id());
        CustomerView view = new CustomerView();
        CustomerController controller = new CustomerController(model, view);
        controller.updateInformation();
        String newUserName = "NewUserName";
        String newPhone = "1234567890";
        String newEmail = "new.email@gmail.com";
        controller.setUserName(newUserName);
        controller.setPhone(newPhone);
        controller.setEmail(newEmail);
        controller.updateInformation();
        Customer updatedCustomer = customerService.getCustomer(CUSTOMER_2.getCustomer_id());
        Assert.assertEquals(updatedCustomer.getCustomer_id(), CUSTOMER_2.getCustomer_id(),
                "Customer id must match");
        Assert.assertEquals(updatedCustomer.getUser_name(), newUserName,
                "User name must match");
        Assert.assertEquals(updatedCustomer.getPhone(), newPhone,
                "Phone must match");
        Assert.assertEquals(updatedCustomer.getEmail(), newEmail,
                "Email must match");
    }
}
