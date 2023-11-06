package com.endava.atf.transition.definitions;

import Context.ScenarioContext;
import com.endava.atf.transition.testDataUI.MethodsUI;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import java.util.HashMap;
import static io.restassured.RestAssured.given;

public class StepDefinitionsAPI {
    private static final Logger log = LogManager.getLogger(StepDefinitionsAPI.class);
    final ScenarioContext scenarioContext;

    String newFirstNameAPI = MethodsUI.randomFirstName();
    String newLastNameAPI = MethodsUI.randomLastName();
    String newEmailAPI = MethodsUI.randomEmail();
    String newPasswordAPI = MethodsUI.randomPassword();

    Response response;
    public HashMap<Object, Object> map = new HashMap<Object, Object>();

    public StepDefinitionsAPI(ScenarioContext scenarioContext) {
        this.scenarioContext = scenarioContext;
    }

    @Given("valid endpoint with payload to create user")
    public void validEndpointWithPayloadToCreateUser() {
        RestAssured.baseURI = "http://myopencart.example.com/index.php";
        RestAssured.basePath = "?route=com.endava.atf.transition.api/payment/address";

        map.put("firstname", newFirstNameAPI);
        map.put("lastname", newLastNameAPI);
        map.put("email", newEmailAPI);
        map.put("password", newPasswordAPI);
    }

    @When("POST request is send to server")
    public void requestIsSendToServer(String postRequest) {

        response = given()
                .contentType(ContentType.JSON)
                .body(map)
                .when()
                .post("api/register")
                .then()
                .statusCode(200).contentType(ContentType.JSON).
                extract().response();

        scenarioContext.setContext("response", response);
    }

    @Then("new user must be created with  {}, {}, {}, {}")
    public void newUserMustBeCreatedWithFirstNameLastNameEmailPassword(String firstname, String lastname, String email, String password) {
        response = (Response) scenarioContext.getContext("response");

        String firstName = response.path("firstname");
        String lastName = response.path("lastname");
        String eMail = response.path("email");
        String userPassword = response.path("password");

        Assert.assertEquals(firstname, firstName);
        Assert.assertEquals(lastname, lastName);
        Assert.assertEquals(email, email);
        Assert.assertEquals(userPassword, password);

//        Object actualStatusCode = scenarioContext.getContext("statusCode");
        int actualStatusCode = response.getStatusCode();
        Assert.assertEquals(200, actualStatusCode);
    }

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @Given("valid endpoint to fetch user with:")
    public void validEndpointToFetchUser() {
        RestAssured.baseURI = "http://localhost:8080/";
        RestAssured.basePath = "/api/users";

    }

    @When("GET request is send to server")
    public void getRequestIsSendToServer(String firstName, String lastName, String email, String password) {
        response = given().
                queryParam("firstname", firstName).
                queryParam("lastname", lastName).
                queryParam("email", email).
                queryParam("password", password).
                when().
                get().
                then().
                contentType(ContentType.JSON).
                extract().response();

        int StatusCode = response.statusCode();
        scenarioContext.setContext("statusCode", StatusCode);

    }

    @Then("validate the response of first user record having credentials {}, {}, {}, {}")
    public void validateTheResponseOfFirstUserRecordHavingCredentialsAsFromExamples(String firstName, String lastName, String email, String password) {

        String userFirstName = response.path("data[0].firstname");
        String userLastName = response.path("data[1].lastname");
        String userEmail = response.path("data[2].email");
        String userPassword = response.path("data[3].password");

        Assert.assertEquals(userFirstName, firstName);
        Assert.assertEquals(userLastName, lastName);
        Assert.assertEquals(userEmail, email);
        Assert.assertEquals(userPassword, password);

        Object actualStatusCode = scenarioContext.getContext("statusCode");
        Assert.assertEquals(200, actualStatusCode);
    }


}

