package com.endava.atf.transition.definitions;
import com.endava.atf.transition.config.Browser;
import com.endava.atf.transition.config.WebDriverFactory;
import com.endava.atf.transition.config.DataBase.DbManager;
import com.endava.atf.transition.utils.Helper;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import com.endava.atf.transition.testData.RegistrationPage;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.*;

@Slf4j
public class StepDefinitions {
    private DbManager dbManager;
    private static WebDriver webdriver;
    private RegistrationPage registrationPage = new RegistrationPage();

    static {
        webdriver = WebDriverFactory.getDriver(Browser.CHROME);

    }

    public StepDefinitions(DbManager dbManager) {
        this.dbManager = dbManager;
    }
    
    //OpenCart.feature
    WebDriver driver = WebDriverFactory.getDriver(Browser.CHROME);

    @Given("User is on the Home page")
    public void userIsOnHomePage() {
        Helper.openPage();
//        webdriver.get("http://localhost:8080/en-gb?route=common/home");
    }

    @And("User is not registered")
    public void userIsNotRegistered() {
        
        //select
        //resultset is empty
    }

    @When("User goes on Register page")
    public void userGoesOnRegisterPage() {webdriver.get("http://localhost:8080/en-gb?route=account/register");
    }

    @And("User fills firstName {}")
    public void userFillsFirstName(String firstName) {
        driver.findElement(registrationPage.getInputFirstName()).sendKeys(firstName);

    }

    @And("User fills lastName {}")
    public void userFillsLastName(String lastName) {
        driver.findElement(registrationPage.getInputLastName()).sendKeys(lastName);

    }

    @And("User fills email {}")
    public void userFillsEmailEmail(String email) {
        driver.findElement(registrationPage.getInputPassword()).sendKeys(email);
    }


    @And("User fills password {}")
    public void userFillsPassword(String password) {
        driver.findElement(registrationPage.getInputPassword()).sendKeys(password);

    }

    @And("User clicks on newsletter")
    public void userClicksOnNewsletter() {
        driver.findElement(registrationPage.getBtnAgree()).click();

    }

    @And("User clicks on agreementOfThePrivacyPolicy")
    public void userClicksOnAgreementOfThePrivacyPolicy() {
        WebElement element = driver.findElement(registrationPage.getBtnAgree());
        Actions actions = new Actions(driver);
        actions.moveToElement(element);
        actions.perform();
//        driver.findElement(registrationPage.getBtnAgree()).click();

    }

    @And("User clicks on btn continue")
    public void userClicksBContinue() {
        driver.findElement(registrationPage.getBtnContinue()).click();

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
                String username=null;
                username = rs.getString("username");
                assertEquals("admin1", username);

                rs.close();
            }
        }
        catch(SQLException se)
            {
//                 log exception
                throw se;
            }
    }

    @Then("User is relocated on the page {string}")
    public void userIsRelocatedOnThePage() {
        webdriver.get("http://localhost:8080/en-gb?route=account/success&customer_token=742ebc0b54291c7a7443e8f401");
    }

//    @And("The inscription {string} is appeared on the screen")
//    public void theInscriptionIsAppearedOnTheScreen() {
//        driver.findElement(registrationPage.getInscriptionYourAccountHasBeenCreated());
//    }

    @And("The inscription {string} is appeared on the screen")
    public void theInscriptionIsAppearedOnTheScreen(String text) {
        driver.findElement(registrationPage.getInscriptionYourAccountHasBeenCreated()).getAttribute(text);
    }


    @And("A warning message {} is appeared on the screen")
    public void aWarningMessageWarningMessageIsAppearedOnTheScreen(String warningMessage) {
//        driver.findElement(registrationPage.getInscriptionFNBoundary()).getAttribute("First Name must be between 1 and 32 characters!");
        
        driver.findElement(registrationPage.getLocatorByName(warningMessage));
    }

    @And("User is already registered")
    public void userIsAlreadyRegistered() throws SQLException {

        String insertUserQuery = "INSERT INTO oc_user (firstname, lastname, email, password) " +
                "VALUES ('Ira', 'Fedorova', 'mail@mail.com', '123456');";

        try {
            int rsInsert = new DbManager().getPstmt().executeUpdate(insertUserQuery);
            assertTrue(rsInsert==1, "User is already existed");

        }catch(RuntimeException e){
            log.error("Exception: DB insert exception");
            e.printStackTrace();

        }


    }

    @And("User fills  credentials")
    public void userFillsCredentials() {
        
    }
       //https://www.baeldung.com/cucumber-data-tables
    @And("User registers")
    public void userRegisters(DataTable table) {
        List<Map<String, String>> rows = table.asMaps(String.class, String.class); // convert data table into List(Map)
        Map<String, String> row = rows.get(0); // use for use documentation

        driver.findElement(registrationPage.getInputFirstName()).sendKeys(row.get("firstName"));
        driver.findElement(registrationPage.getInputLastName()).sendKeys(row.get("lastName"));
        driver.findElement(registrationPage.getInputPassword()).sendKeys(row.get("email"));
        driver.findElement(registrationPage.getInputPassword()).sendKeys(row.get("password"));
        driver.findElement(registrationPage.getBtnAgree()).click();
        driver.findElement(registrationPage.getBtnContinue()).click();
        driver.findElement(registrationPage.getBtnAgree()).click();

    }
}
