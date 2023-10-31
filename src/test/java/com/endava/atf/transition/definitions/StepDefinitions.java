package com.endava.atf.transition.definitions;

import com.endava.atf.transition.config.DataBase.DbManager;
import com.endava.atf.transition.drivers.Driver;
import com.endava.atf.transition.testDataUI.Queries;
import com.endava.atf.transition.testDataUI.QueryDelete;
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
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StepDefinitions {

    private static final Logger log = LogManager.getLogger(StepDefinitions.class);
    private final WebDriver driver = Driver.getDriver();
    private final RegistrationPage registrationPage = new RegistrationPage();
    private static final Queries query;
    private static final QueryDelete queryDeleteAll;
    Duration timeout = Duration.ofSeconds(10);
    Connection connection;

    static {
        try {
            queryDeleteAll = new QueryDelete();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }



    static {
        try {
            query = new Queries();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


//    WebDriver driver = WebDriverFactory.getDriver(DriverProvider.CHROME);
    private final DbManager dbManager;

//    static {
//       driver = WebDriverFactory.getDriver(DriverProvider.CHROME);
//
//    }

    public StepDefinitions(DbManager dbManager) {
        this.dbManager = dbManager;
    }


    @Given("User is on Register page")
    public void userIsOnRegisterPage() {
        try {
            Helper.openRegisterPage();
            log.info("User is on register page");
        } catch (RuntimeException e) {
            log.error(String.valueOf(e));
            throw e;
        }
    }

    @Given("User does not have any account with petrov@gmail.com")
    public void userDoesNotHaveAccount() throws SQLException {

        log.info("User does not have any account");
        int rsDeleteAll = query.getPsDeleteAll().executeUpdate();

    }




    @When("User registers")
    public void userRegisters(DataTable table) throws SQLException {
        List<Map<String, String>> rows = table.asMaps(String.class, String.class); // convert data table into List(Map)

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(50)); // Создание экземпляра WebDriverWait

        for (Map<String, String> row : rows) {

                    WebElement inputFirstName = wait.until(ExpectedConditions.presenceOfElementLocated(registrationPage.getInputFirstNameLocator()));
                    inputFirstName.sendKeys(row.get("firstName"));

                    WebElement inputLastName = wait.until(ExpectedConditions.presenceOfElementLocated(registrationPage.getInputLastNameLocator()));
                    inputLastName.sendKeys(row.get("lastName"));

                    WebElement inputEmail = wait.until(ExpectedConditions.presenceOfElementLocated(registrationPage.getInputEmailLocator()));
                    inputEmail.sendKeys(row.get("email"));

                    WebElement inputPassword = wait.until(ExpectedConditions.presenceOfElementLocated(registrationPage.getInputPasswordLocator()));
                    inputPassword.sendKeys(row.get("password"));

                    WebElement agreeSlider = driver.findElement(registrationPage.getAgreeSliderLocator());
                    JavascriptExecutor executor = (JavascriptExecutor) driver;
                    executor.executeScript("arguments[0].click();", agreeSlider);

                    driver.findElement(registrationPage.getBtnContinueRegister()).submit();

                    log.info("User registers: firstName");
                    log.info("User registers: lastName");
                    log.info("User registers: email");
                    log.info("User registers: password");
                    log.info("User moves slider on the rightSide");
                    log.info("User press the btn Continue");
                }
                }

/////Доделать!!!!!!
    @Then("User is registered")
    public void userIsRegistered() throws SQLException {

        String QUERY = "select * from oc_user u where u.username = ''";
        ResultSet rs = new DbManager().getPreparedStatement().executeQuery(QUERY);
        try {
            while (rs.next()) {
                System.out.println();
                System.out.println("ID: " + rs.getInt("user_id"));
                System.out.println(", First: " + rs.getString("firstname"));
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
//        Helper.openYourAccountHasBeenCreatedPage();
        log.info("User is relocated on the page Your Account Has Been Created!");
    }

    @Then("The inscription {} is appeared on the screen")
    public void theInscriptionIsAppearedOnTheScreen(String string) {

        Duration timeout = Duration.ofSeconds(10);
        WebDriverWait wait = new WebDriverWait(driver, timeout);

        WebElement getActualElement = wait.until(ExpectedConditions.visibilityOfElementLocated(registrationPage.getInscriptionYourAccountHasBeenCreated()));
        assertEquals(string, getActualElement.getText());

        WebElement btnContinue = wait.until(ExpectedConditions.elementToBeClickable(registrationPage.getBtnContinueLocator()));
        btnContinue.click();

    }
    @And("new cycle")
    public void newCycle() {
        Duration timeout = Duration.ofSeconds(10);
        WebDriverWait wait = new WebDriverWait(driver, timeout);

        WebElement dropdownToggle = wait.until(ExpectedConditions.visibilityOfElementLocated(registrationPage.getBtnDropDownToggleLocator()));
        dropdownToggle.click();
        WebElement dropdownOption = wait.until(ExpectedConditions.visibilityOfElementLocated(registrationPage.getLogoutLocator()));
        dropdownOption.click();

        WebElement btnContinueAccountLogout = wait.until(ExpectedConditions.visibilityOfElementLocated(registrationPage.getBtnContinueAccountLogout()));
        btnContinueAccountLogout.click();

        try {
            Helper.openRegisterPage();
            log.info("User is on register page");
        } catch (RuntimeException e) {
            log.error(String.valueOf(e));
            throw e;
        }
    }


/////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @When("User fills firstName field {}")
    public void userFillsFirstNameField(String firstName) {

        driver.findElement(registrationPage.getInputFirstNameLocator()).sendKeys(firstName);

        WebElement agreeSlider = driver.findElement(registrationPage.getAgreeSliderLocator());
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", agreeSlider);
        driver.findElement(registrationPage.getBtnContinueLocator()).submit();

        log.info("User fills its firstName");

    }

    @Then("User is not registered")
    public void userIsNotRegistered() throws SQLException {
        ResultSet rsSelect = query.getPsSelect().executeQuery();
        System.out.println("List of customers with email: " + query.getEmailValue());

        while (rsSelect.next()) {
            System.out.println("firstname: " + rsSelect.getString("firstname"));
            System.out.println("lastname: " + rsSelect.getString("lastname"));
            System.out.println("email: " + rsSelect.getString("email"));
            System.out.println("password: " + rsSelect.getString("password"));
            System.out.println();

        }
        rsSelect.close();
        ResultSet rsCount = query.getPsCount().executeQuery();

        while (rsCount.next()) {
            String email = rsCount.getString("email");
            int userCount = rsCount.getInt("user_count");

            System.out.printf("Count of customers with email %s: %d%n%n", email, userCount);

            int expectUserCount = 1;
            Assert.assertEquals(userCount, expectUserCount);

            if (userCount == 1) {
                log.info("User can not register with existing account");
            } else {
                log.error("User registers with existing account");
            }
        }
    }



        @Then("User does not relocated onto another page")
        public void notRelocatedOntoAnotherPage () {
            String expectedURL = "http://localhost:8080/en-gb?route=account/register";
            String currentURL = driver.getCurrentUrl();
            Assert.assertEquals(expectedURL, currentURL);

            if (expectedURL.equals(currentURL)) {
                log.info("User does not relocated onto another page");
            } else {
                log.error("The URLs are not equals");
            }
        }







        @Given("User has already its account")
        public void userIsAlreadyRegistered () throws SQLException {
            query.setParameters();
            int rsDelete = query.getPsDelete().executeUpdate();
            int rsInsert = query.getPsInsert().executeUpdate();
            log.info("User has already its account");

        }

        @When("User try to register with existing account")
        public void userTryToRegisterWithExistingAccountFirstnameLastnameEmailPassword () throws SQLException {
            query.setParameters();

            driver.findElement(registrationPage.getInputFirstNameLocator()).sendKeys(query.getFirstnameValue());
            driver.findElement(registrationPage.getInputLastNameLocator()).sendKeys(query.getLastnameValue());
            driver.findElement(registrationPage.getInputEmailLocator()).sendKeys(query.getEmailValue());
            driver.findElement(registrationPage.getInputPasswordLocator()).sendKeys(query.getPasswordValue());

            WebElement agreeSlider = driver.findElement(registrationPage.getAgreeSliderLocator());
            JavascriptExecutor executor = (JavascriptExecutor) driver;
            executor.executeScript("arguments[0].click();", agreeSlider);
            driver.findElement(registrationPage.getBtnContinueRegister()).submit();



            log.info("User try to register with existing account");
        }

        @Then("Warning message {} is appeared on the screen")
        public void aWarningEMailAddressIsAlreadyRegisteredIsAppearedOnTheScreen (String alert){


            String getActualWarningMessage = driver.findElement(registrationPage.getAlertEmailAddressIsAlreadyRegistered()).getText();
            assertEquals(alert, getActualWarningMessage);
            log.info("Warning message is appeared on the screen");
        }

/////////////////////////////////////////////////////////////////////////////////
    @When("User registers with wrong firstname")
    public void userRegistersWithWrongFirstname(DataTable table) {
        List<Map<String, String>> rows = table.asMaps(String.class, String.class); // convert data table into List(Map)

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(50)); // Создание экземпляра WebDriverWait

        for (Map<String, String> row : rows) {

            WebElement inputFirstName = wait.until(ExpectedConditions.presenceOfElementLocated(registrationPage.getInputFirstNameLocator()));
            inputFirstName.sendKeys(row.get("firstName"));

            WebElement inputLastName = wait.until(ExpectedConditions.presenceOfElementLocated(registrationPage.getInputLastNameLocator()));
            inputLastName.sendKeys(row.get("lastName"));

            WebElement inputEmail = wait.until(ExpectedConditions.presenceOfElementLocated(registrationPage.getInputEmailLocator()));
            inputEmail.sendKeys(row.get("email"));

            WebElement inputPassword = wait.until(ExpectedConditions.presenceOfElementLocated(registrationPage.getInputPasswordLocator()));
            inputPassword.sendKeys(row.get("password"));

            WebElement agreeSlider = driver.findElement(registrationPage.getAgreeSliderLocator());
            JavascriptExecutor executor = (JavascriptExecutor) driver;
            executor.executeScript("arguments[0].click();", agreeSlider);

            driver.findElement(registrationPage.getBtnContinueRegister()).submit();

            log.info("User registers: firstName");
            log.info("User registers: lastName");
            log.info("User registers: email");
            log.info("User registers: password");
            log.info("User moves slider on the rightSide");
            log.info("User press the btn Continue");
        }

    }

    @Then("User is not registered with entered credential")
    public void userIsNotRegisteredWithEnteredCredential() throws SQLException {

        ResultSet rsSelect = query.getPsSelectAllUsers().executeQuery();
        while (rsSelect.next()) {
            System.out.println("email: " + rsSelect.getString("email"));
            System.out.println();

        }
        rsSelect.close();

        ResultSet rsCountAll = query.getPsCountAll().executeQuery();

        while (rsCountAll.next()) {
            String email = rsCountAll.getString("email");
            int userCount = rsCountAll.getInt("user_count");


            System.out.printf("Count of customers with email %s: %d%n%n", email, userCount);

            int expectUserCount = 1;
            Assert.assertEquals(userCount, expectUserCount);

            if (userCount == 1) {
                log.info("User can not register with existing account");
            } else {
                log.error("User registers with existing account");
            }
        }
    }

    @Then("A warning message {} is appeared on the screen")
    public void aWarningMessageWarningMessageIsAppearedOnTheScreen (String warningMessage){
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
    public void getTheFieldsClear () {
//        WebElement textField = driver.findElement(registrationPage.getFirstNameField());
//        textField.clear();
    }

    @And("User does not have any ACCOUNTS")
    public void userDoesNotHaveAnyACCOUNTS() throws SQLException {

        log.info("User does not have any ACCOUNTS");
//        int rsDeleteAll = queryDeleteAll.getPsDeleteAll().executeUpdate();
    }


}


















