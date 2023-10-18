package com.endava.atf.transition.definitions;

import com.endava.atf.transition.config.DataBase.DbManager;
import com.endava.atf.transition.config.DriverProvider;
import com.endava.atf.transition.config.WebDriverFactory;
import com.endava.atf.transition.testDataUI.MethodsUI;


import com.endava.atf.transition.testDataUI.RegistrationPage;
import com.endava.atf.transition.utils.Helper;
import io.cucumber.datatable.DataTable;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.mindrot.jbcrypt.BCrypt;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.assertEquals;
import io.cucumber.java.Scenario;


public class StepDefinitions {

    private static final Logger log = LogManager.getLogger(StepDefinitions.class);
    private static final WebDriver webdriver;
    private final RegistrationPage registrationPage = new RegistrationPage();
    private Scenario scenario;


    WebDriver driver = WebDriverFactory.getDriver(DriverProvider.CHROME);
    private final DbManager dbManager;

    static {
        webdriver = WebDriverFactory.getDriver(DriverProvider.CHROME);

    }

    public StepDefinitions(DbManager dbManager) {
        this.dbManager = dbManager;
    }


    @Given("User is on Register page")
    public void userGoesOnRegisterPage() {
        try {
            Helper.openRegisterPage();
            log.info("User is on register page");
        } catch (RuntimeException e) {
            log.error(String.valueOf(e));
            throw e;
        }


    }

    @Given("User does not have account")
    public void userDoesNotHaveAccount() {
        log.info("User is not registered");

        //// check DB
        //resultset is empty
    }

    //https://www.baeldung.com/cucumber-data-tables
    @And("User registers")
    public void userRegisters(DataTable table) {
        List<Map<String, String>> rows = table.asMaps(String.class, String.class); // convert data table into List(Map)
        Map<String, String> row = rows.get(0); // use for use documentation
//        if (row.get("email") == "randomEmail") {
//            row.put("email", MethodsUI.randomEmail());
//        }
        driver.findElement(registrationPage.getInputFirstName()).sendKeys(row.get("firstName"));
        driver.findElement(registrationPage.getInputLastName()).sendKeys(row.get("lastName"));
        driver.findElement(registrationPage.getInputEmail()).sendKeys(row.get("email"));
        driver.findElement(registrationPage.getInputPassword()).sendKeys(row.get("password"));
        driver.findElement(registrationPage.getSliderAgree()).sendKeys(Keys.ARROW_RIGHT);
        driver.findElement(registrationPage.getBtnContinue()).submit();

        log.info("User registers: firstName");
        log.info("User registers: lastName");
        log.info("User registers: email");
        log.info("User registers: password");
        log.info("User moves slider on the rightSide");
        log.info("User press the btn Continue");

    }

    @Then("User is registered")
    public void userIsRegistered() throws SQLException {

        String QUERY = "select * from oc_user u where u.username = ''";
        ResultSet rs = new DbManager().getPstmt().executeQuery(QUERY);
        try {
            while (rs.next()) {

                System.out.print("ID: " + rs.getInt("user_id"));
                System.out.print(", First: " + rs.getString("firstname"));
                System.out.println(", Last: " + rs.getString("lastname"));
                System.out.println(", username: " + rs.getString("username"));
                String username = null;
                username = rs.getString("username");
                assertEquals("admin1", username);

                rs.close();
            }
        } catch (SQLException ex) {
            log.error(ex.getMessage(), ex);
            throw new SQLException(ex);
        }
    }

    @Then("User is relocated on the page Your Account Has Been Created!")
    public void userIsRelocatedOnThePage() {
        webdriver.get("http://localhost:8080/en-gb?route=account/success&customer_token");
    }

    @Then("The inscription {string} is appeared on the screen")
    public void theInscriptionIsAppearedOnTheScreen(String string) {
        driver.navigate().to("http://localhost:8080/en-gb?route=account/success&customer_token");
        String getActualElement = driver.findElement(registrationPage.getInscriptionYourAccountHasBeenCreated()).getText();
        assertEquals(string, getActualElement, "The inscriptions are equals");

    }
/////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @When("User fills firstName field {}")
    public void userFillsFirstNameField(String firstName) {

        driver.findElement(registrationPage.getInputFirstName()).sendKeys(firstName);
        driver.findElement(registrationPage.getSliderAgree()).sendKeys(Keys.ARROW_RIGHT);
        driver.findElement(registrationPage.getBtnContinue()).submit();

        log.info("User fills firstName");

    }

    @Then("User is not registered")
    public void userIsNotRegistered() {
        String expectedURL = "http://localhost:8080/en-gb?route=account/register";
        String currentURL = webdriver.getCurrentUrl();
        Assert.assertEquals(expectedURL, currentURL);

        if (expectedURL.equals(currentURL)) {
            System.out.println("The URLs are equals");
        } else {
            log.error("The URLs are not equals");
        }

        // check DB
    }

    @Then("A warning message {} is appeared on the screen")
    public void aWarningMessageWarningMessageIsAppearedOnTheScreen(String warningMessage) {
        WebElement element1 = driver.findElement(registrationPage.getLocatorByName(warningMessage));
        String warningAsText1 = element1.getText();
        String warningAsText2 = "First Name must be between 1 and 32 characters!";

        if (warningAsText1.equals(warningAsText2)) {
            log.info("The warning messages are equals");
        } else {
            log.error("The warning messages are not equals");
        }
        Assert.assertEquals(warningAsText1, warningAsText2);

    }

    @Then("Get the fields clear")
    public void getTheFieldsClear() {
        WebElement textField = driver.findElement(registrationPage.getFirstNameField());
        textField.clear();
    }


    @And("User is already registered")
    public void userIsAlreadyRegistered() throws SQLException {

        try {
            String newFirstName = MethodsUI.randomFirstName();
            String newLastName = MethodsUI.randomLastName();
            String newEmail = MethodsUI.randomEmail();
            String newPassword = MethodsUI.randomPassword();

            String insertUserQuery = "INSERT INTO oc_user (firstname, lastname, email, password) " +
                    "VALUES (newFirstName, newLastName, newEmail , newPassword)";

            int rsInsert = new DbManager().getPstmt().executeUpdate(insertUserQuery);

            // insert user data into ScenarioContext

        } catch (RuntimeException e) {
            log.error("Exception: DB insert exception");
            e.printStackTrace();
        }
    }

    @And("User presses btn Continue")
    public void userPressesBtnContinue() {
        log.info("User presses My Account btn");
    }


    @When("User try to register with existing account")
    public void userTryToRegisterWithExistingAccountFirstnameLastnameEmailPassword() throws SQLException {
//        String firstName, String lastName, String eMail, String passWord

        String initialPassword = "123456";
        String initialEmail = MethodsUI.randomEmail();

        String deleteCustomer = "DELETE FROM oc_customer WHERE email = 'john@gmail.com'";
        int rsDelete = new DbManager().getPstmt().executeUpdate(deleteCustomer);

        String insertCustomer = "INSERT INTO oc_customer (customer_group_id, language_id,  firstname, lastname, email,telephone, password, custom_field, ip, status, safe, token, code, date_added) " +
                "VALUES (55, 1, 'John', 'Doe', 'john@gmail.com', 37356987, 123456, '', '172.19.0.1', 1, 0, 123, 123, '2023-09-29 13:36:22')";
        int rsInsert = new DbManager().getPstmt().executeUpdate(insertCustomer);

        String selectCustomer = "SELECT * FROM oc_customer WHERE email = 'john@gmail.com'";
        ResultSet rsSelect = new DbManager().getPstmt().executeQuery(selectCustomer);

        System.out.println(" selectCustomer: " + selectCustomer);


        List<String> customers = new ArrayList<>();
        try {
            while (rsSelect.next()) {

                String firstname = rsSelect.getString("firstname");
                String lastname = rsSelect.getString("lastname");
                String email = rsSelect.getString("email");
                String password = rsSelect.getString("password");
                System.out.println("First name is: " + firstname);
                System.out.println("Last name is: " + lastname);
                System.out.println("Email is: " + email);
                System.out.println("Password is: " + password);
//                System.out.print("FirstName: " + rsSelect.getString("firstname"));
//                System.out.print(", LastName: " + rsSelect.getString("lastname"));
//                System.out.println(", Email: " + rsSelect.getString("email"));
//                System.out.println(", password: " + rsSelect.getString("password"));

                customers.add(firstname);
                customers.add(lastname);
                customers.add(email);
                customers.add(password);


//                 rsSelect.close();

            }
        } catch (SQLException ex) {
            log.error(ex.getMessage(), ex);

        }

        for (String element : customers) {
            System.out.println("List  " + element);
        }

        System.out.println("get element index 0 " + customers.get(0));



        // Генерируем хеш пароля
        String hashedPassword = BCrypt.hashpw(initialPassword, BCrypt.gensalt());
        System.out.println("password hash: " + hashedPassword);

        boolean condition = BCrypt.checkpw(customers.get(3), hashedPassword);

        System.out.println(condition);

//        if (condition == false) {
//            log.error("this step is failed: ");
//            scenario.fail

            driver.findElement(registrationPage.getInputFirstName()).sendKeys(customers.get(0));
            driver.findElement(registrationPage.getInputLastName()).sendKeys(customers.get(1));
            driver.findElement(registrationPage.getInputEmail()).sendKeys(customers.get(2));
            driver.findElement(registrationPage.getInputPassword()).sendKeys(initialPassword);
            driver.findElement(registrationPage.getSliderAgree()).sendKeys(Keys.ARROW_RIGHT);
            driver.findElement(registrationPage.getBtnContinue()).submit();
        }
    }

















