package com.endava.atf.transition.definitions;

import Context.ScenarioContext;
import com.endava.atf.transition.testDataAPI.ColorsData;
import com.endava.atf.transition.testDataAPI.SuccessReg;
import com.endava.atf.transition.testDataAPI.UnSuccessReg;
import com.endava.atf.transition.testDataAPI.UserData;
import io.cucumber.java.en.And;
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
import java.util.List;
import java.util.stream.Collectors;
import static io.restassured.RestAssured.given;

public class StepDefApi2 {

    final ScenarioContext scenarioContext;
    private SuccessReg successReg;
    private UnSuccessReg unSuccessReg;
    Response response;
    public HashMap<Object, Object> map = new HashMap<Object, Object>();
    private static final Logger log = LogManager.getLogger(StepDefinitionsAPI.class);

    public StepDefApi2(ScenarioContext scenarioContext) {
        this.scenarioContext = scenarioContext;
    }


    @Given("The base URI is set to https: {string}")
    public void theBaseURIIsSetToHttps(String baseURI) {
        RestAssured.baseURI = baseURI;
        log.info("the base URI is set");
    }

    @When("GET request is sent to server: {string}")
    public void getRequestIsSentToServer(String endpoint) {
        response = given().
                when().
                get(endpoint);

        log.info("GET request is sent to server");
    }

    @Then("Get response ContentType JSON")
    public void listOfUsersAppearsOnTheScreen() {
        log.info("Get response in ContentType JSON");
        response.then().log().all().
                contentType(ContentType.JSON).
                extract().response();

        int actualStatusCode = response.getStatusCode();
        scenarioContext.setContext("statusCode", actualStatusCode);

    }

    @Then("Response code is {int}")
    public void responseCodeIs(int expectedStatusCode) {
        log.info("Response code should be 200");
        Assert.assertEquals(expectedStatusCode, response.getStatusCode());
        System.out.println("Actual response code is: " + response.getStatusCode());

    }

    @Then("All users have emails end on reqres.in")
    public void allUsersHaveEmailsEnsdOnReqresIn() {
        log.info("All users have emails end on reqres.in");

        List<UserData> users = response.getBody().jsonPath().getList("data", UserData.class);

        for (UserData user : users) {
            System.out.println("id: " + user.getId());
            System.out.println("email: " + user.getEmail());
            System.out.println("first_name: " + user.getFirst_name());
            System.out.println("last_name: " + user.getLast_name());
            System.out.println("avatar: " + user.getAvatar());
            System.out.println();

        }

        Assert.assertTrue(users.stream().allMatch(x -> x.getEmail().endsWith("reqres.in")));

    }

    @Then("Display all avatars from the list")
    public void displayAllAvatarsFromTheList() {
        log.info("All avatars are displayed");
        List<UserData> users = response.getBody().jsonPath().getList("data", UserData.class);
        List<String> avatars = users.stream().map(UserData::getAvatar).collect(Collectors.toList());

        for (String avatar : avatars) {
            System.out.println(avatar);
        }

        log.info("All avatars are displayed");

    }

    @Then("List<RESOURCE> can be sorted by years")
    public void listRESOURCECanBeSortedByYears() {

        log.info("List<RESOURCE> sorted by years");
        List<ColorsData> colors = response.getBody().jsonPath().getList("data", ColorsData.class);

        List<Integer> years = colors.stream().map(ColorsData::getYear).collect(Collectors.toList());
        List<Integer> sortedYears = years.stream().sorted().collect(Collectors.toList());

        Assert.assertEquals(sortedYears, years);
        System.out.println(years);
        System.out.println(sortedYears);

    }

////////////////////////////////////////////////////////////////////////////////////////////////////////

    @When("POST request is sent to the Server: {string}")
    public void postRequestIsSentToTheServer(String endpoint) {
        map.put("email", "eve.holt@reqres.in");
        map.put("password", "pistol");

        response = given().
                contentType(ContentType.JSON).
                body(map).
                when().log().all().
                post(endpoint);

        successReg = response.as(SuccessReg.class);

        //consumer
        map.forEach((key, value) -> System.out.println("Key: " + key + ", Value: " + value));
        log.info("POST request is sent to the Server");

    }

    @Then("Get Post response")
    public void getPostResponseReg() {
        log.info("Get the Post response");
        Integer expectedId = 4;
        String expectedToken = "QpwL5tke4Pnpja7X4";

        Assert.assertEquals(successReg.getId(), expectedId);
        Assert.assertEquals(successReg.getToken(), expectedToken);
    }


    @Then("result is not null")
    public void resultIsNoNull() {
        Assert.assertNotNull(successReg.getId());
        Assert.assertNotNull(successReg.getToken());

        log.info("Check that result is not null");
    }
//////////////////////////////////////////////////////////////////////////////////

    @When("POST request where password is empty is sent to the Server: {string}")
    public void postRequestWherePasswordIsEmptyIsSentToTheServer(String endpoint) {
        log.info("POST request where password is empty is sent to the Server");
        map.put("email", "sydney@fife");
        map.put("password", "");

        response = given().
                contentType(ContentType.JSON).
                body(map).
                when().
                post(endpoint);
    }

    @Then("get Post response")
    public void getPostResponse() {
        log.info("Get POST response");
        response.then().log().all().
                contentType(ContentType.JSON).
                extract().response();

        unSuccessReg = response.as(UnSuccessReg.class);

    }

    @Then("Get post-response ContentType JSON")
    public void getPostResponseContentTypeJSON() {
        String expectedErrorMessage = "Missing password";
        response.then().log().all().
                contentType(ContentType.JSON).
                extract().response();

        unSuccessReg = response.as(UnSuccessReg.class);
        Assert.assertEquals(expectedErrorMessage, unSuccessReg.getError());
    }

    @Then("expected status code is {int}")
    public void expectedStatusCodeIs(int expectedStatusCode) {
        Object actualStatusCode = response.getStatusCode();

        System.out.println("actual status code is: " + actualStatusCode);
        System.out.println("expected status code is: " + expectedStatusCode);
        Assert.assertEquals(expectedStatusCode, actualStatusCode);

        log.info("status code is 400");
    }


}
















