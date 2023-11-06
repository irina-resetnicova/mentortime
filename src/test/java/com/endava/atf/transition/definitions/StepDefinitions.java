package com.endava.atf.transition.definitions;

import com.endava.atf.transition.utils.Helper;
import com.endava.atf.transition.drivers.Driver;
import com.endava.atf.transition.testDataUI.RegistrationPage;
import com.endava.atf.transition.testDataUI.UserDao;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StepDefinitions {
    private static final Logger log = LogManager.getLogger(StepDefinitions.class);
    private final UserDao query;
    private final RegistrationPage registrationPage;
    private final BasePage basePage;

    public StepDefinitions(UserDao query) {
        this.query = query;
        this.registrationPage = new RegistrationPage(Driver.getDriver());
        this.basePage = new BasePage(Driver.getDriver());
    }

    @Given("the User is on Register page")
    public void userIsOnRegisterPage() {
        Helper.openRegisterPage();
        log.info("User is on register page");

    }

//    Scenario Outline: A new User is successfully registered

    @Given("the User does not have any existing accounts")
    public void userDoesNotHaveAnyAccounts() {

        log.info("User does not have any ACCOUNTS");

    }

    @When("the User registers with the following details:")
    public void userRegisters(DataTable table) {
        List<Map<String, String>> rows = table.asMaps(String.class, String.class);

        for (Map<String, String> row : rows) {
            registrationPage.fillRegistrationForm(
                    row.get("firstName"),
                    row.get("lastName"),
                    row.get("email"),
                    row.get("password")
            );

            log.info("User registers: firstName, lastName, email, password");
            log.info("User moves slider on the rightSide");
            log.info("User presses the btn Continue");
        }
    }

    @Then("the User is redirected to the page Your Account Has Been Created!")
    public void userIsRelocatedOnThePage() {
        log.info("User is relocated on the page Your Account Has Been Created!");

        System.out.println("current page" + Driver.getDriver().getCurrentUrl());
        String expectedURL = "http://localhost:8080/en-gb?route=account/register";
        String currentURL = Driver.getDriver().getCurrentUrl();
        Assert.assertEquals("The URLs are not equals", expectedURL, currentURL);

        log.info("User relocated onto another page");

    }

    @Then("the inscription {} appears on the screen")
    public void theInscriptionIsAppearedOnTheScreen(String string) {
        log.info("The inscription Your Account Has Been Created! is appeared on the screen");

        String actualString = basePage.getTextOfString(registrationPage.getInscriptionYourAccountHasBeenCreated());

        assertEquals(string, actualString);
        registrationPage.yourAccountHasBeenCreated();

    }

    @Then("the User's registration is successful")
    public void userIsRegistered() {
        log.info("User is registered");

        try (ResultSet rsSelectAll = query.selectAllCustomers().executeQuery()) {
            while (rsSelectAll.next()) {
                System.out.println();
                System.out.println("firstname: " + rsSelectAll.getString("firstname"));
                System.out.println("lastname: " + rsSelectAll.getString("lastname"));
                System.out.println("email: " + rsSelectAll.getString("email"));
                System.out.println("password: " + rsSelectAll.getString("password"));
                System.out.println();
            }
        } catch (SQLException ex) {
            log.error(ex.getMessage(), ex);
        }
    }


//    Scenario Outline: User can not be registered if first name does not confirm requirements

    @Given("the User does not have any account with {}")
    public void userDoesNotHaveAccount(String email) throws SQLException {
        log.info("User does not have any account");
        int rsDeleteAll = query.deleteAllByEmail(email).executeUpdate();
    }


    @When("the User registers with the wrong firstname")
    public void userRegistersWithWrongFirstname(DataTable table) {

        List<Map<String, String>> rows = table.asMaps(String.class, String.class); // convert data table into List(Map)

        for (Map<String, String> row : rows) {
            registrationPage.fillRegistrationForm(
                    row.get("firstName"),
                    row.get("lastName"),
                    row.get("email"),
                    row.get("password")
            );

            log.info("User registers: firstName, lastName, email, password");
            log.info("User moves slider on the rightSide");
            log.info("User presses the btn Continue");
        }

    }

    @Then("the User is not registered with {}")
    public void userIsNotRegisteredWithEnteredCredential(String email) throws SQLException {
        log.info("the user is not registered with invalid data email");

        ResultSet rsSelect = query.selectAllUsersByEmail(email).executeQuery();

            while (rsSelect.next()) {
                log.error("email: " + rsSelect.getString("email"));
                System.out.println();


                ResultSet rsCountAll = query.countUsersByEmail(email).executeQuery();

                while (rsCountAll.next()) {
                    int userCount = rsCountAll.getInt("user_count");
                    int expectUserCount = 1;
                    Assert.assertEquals("User can register with an existing account", userCount, expectUserCount);
                }

            }

    }

    @Then("a warning message {} is displayed on the screen")
    public void aWarningMessageWarningMessageIsAppearedOnTheScreen(String expectedWarningMessage) {
        log.info("The warning message <First Name must be between 1 and 32 characters!> appears on the screen");


         String actualWarningMessage = basePage.getTextOfString(registrationPage.getFirstNameError());

        if (actualWarningMessage.equals(expectedWarningMessage)) {
            log.info("The warning messages are equals");
        } else {
            log.error("The warning messages are not equals");
        }

    }

// Scenario: Registration with Existing User
    @Given("the User already has an account")
    public void userIsAlreadyRegistered() throws SQLException {

        int rsDelete = query.deleteCustomerByEmail("john@gmail.com").executeUpdate();
        int rsInsert = query.insertCustomer("John", "Baiden", "john@gmail.com", "1234").executeUpdate();

        log.info("User has already its account");

    }

    @When("the User tries to register with an existing account {}, {}, {}, {}")
    public void userTryToRegisterWithExistingAccountFirstNameLastNameEmailPassword(String firstname, String lastname, String email, String password) {
        log.info("User tries to register with existing account:  ");
        registrationPage.fillRegistrationForm(firstname, lastname, email, password);

        log.info("User registers: firstName, lastName, email, password");
        log.info("User moves slider on the rightSide");
        log.info("User presses the btn Continue");

    }


    @Then("STOP! the User is not registered with the existing {}")
    public void userIsNotRegisteredWithExistingEmail(String email) throws SQLException {
        ResultSet rsSelect = query.getCustomerByEmail(email).executeQuery();
//        System.out.println("List of customers with email: " + query.getEmailValue());

        while (rsSelect.next()) {
            System.out.println("\u001B[33mfirstname: " + rsSelect.getString("firstname") + "\u001B[0m");
            System.out.println("\u001B[33mlastname: " + rsSelect.getString("lastname") + "\u001B[0m");
            System.out.println("\u001B[33memail: " + rsSelect.getString("email") + "\u001B[0m");
            System.out.println("\u001B[33mpassword: " + rsSelect.getString("password") + "\u001B[0m");
            System.out.println();

        }
        rsSelect.close();

        ResultSet rsCount = query.countUsersByEmail(email).executeQuery();

        while (rsCount.next()) {
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

    @Then("the User is not relocated to another page")
    public void notRelocatedOntoAnotherPage() {
        log.info("the User is not relocated to another page");

        String expectedURL = "http://localhost:8080/en-gb?route=account/register";
        String currentURL = Driver.getDriver().getCurrentUrl();
        Assert.assertEquals("The URLs are equals!", expectedURL, currentURL);

    }

//    @Then("an alert message {} is displayed on the screen")
//    public void anAlertMessageEMailAddressIsAlreadyRegistered(String alert) {
//
//        String actualAlert = registrationPage.getActualAlert();
//        assertEquals(alert, actualAlert);
//        log.info("Warning message <Warning: E-Mail Address is already registered!> is appeared on the screen");
//    }


}


















