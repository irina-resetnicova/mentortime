package com.endava.atf.transition.definitions;

import Context.ScenarioContext;
import com.endava.atf.transition.testDataAPI.*;
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

    private final ScenarioContext scenarioContext;
    private SuccessReg successReg;
    private UnSuccessReg unSuccessReg;
    Response response;
    ResponseSuccessRegistration responseSucReg = new ResponseSuccessRegistration();

    private static final Logger log = LogManager.getLogger(StepDefinitionsAPI.class);

    public StepDefApi2(ScenarioContext scenarioContext) {
        this.scenarioContext = scenarioContext;
    }

    HashMap<Object, Object> map = new HashMap<Object, Object>();

    @Given("the base URI is set to  {string}")
    public void theBaseURIIsSetToHttps(String baseURI) {
        RestAssured.baseURI = baseURI;
        log.info("The base URI is set");
    }

    @When("a GET request is sent to the server with the endpoint {string}")
    public void getRequestIsSentToServer(String endpoint) {
        log.info("GET request is sent to server");
        response = given().
                header("Accept", "application/json").
                contentType(ContentType.JSON).
                when().
                get(endpoint);
        scenarioContext.setContext("response", response);

    }

    @Then("the response code should be {}")
    public void responseCodeIs(int expectedStatusCode) {
        log.info("Response code should be 200");
        Response response = (Response) scenarioContext.getContext("response");
        response.then()
                .log().all()
                .assertThat()
                .statusCode(expectedStatusCode);

        scenarioContext.setContext("response", response);

    }

    @Then("the response is retrieved and displayed on the screen")
    public void listOfUsersAppearsOnTheScreen() {
//        Response response = (Response) scenarioContext.getContext("response");
        log.info("Get response in ContentType JSON");
        response.then().
                log().all().
                contentType(ContentType.JSON).
                extract().response();

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
//////////////////////////////////////////////////////////////////////////////////////////////////
    @Then("the List of Resources can be sorted by years")
    public void listRESOURCECanBeSortedByYears() {
        log.info("List<RESOURCE> can be sorted by years");

        List<ColorsData> colors = response.getBody().jsonPath().getList("data", ColorsData.class);

        List<Integer> years = colors.stream().map(ColorsData::getYear).collect(Collectors.toList());
        List<Integer> sortedYears = years.stream().sorted().collect(Collectors.toList());

        Assert.assertEquals(sortedYears, years);
        System.out.println(years);
        System.out.println(sortedYears);

    }

////////////////////////////////////////////////////////////////////////////////////////////////////////

    @When("a POST request is sent to the Server with the endpoint {string}")
    public void postRequestIsSentToTheServer(String endpoint) {
        log.info("POST request is sent to the Server");
//         HashMap<Object, Object> map = new HashMap<Object, Object>();

        map.put("email", "eve.holt@reqres.in");
        map.put("password", "pistol");

        response = given().
                contentType(ContentType.JSON).
                body(map).
                when().
                post(endpoint);

        //consumer
        map.forEach((key, value) -> System.out.println("Key: " + key + ", Value: " + value));

    }

    @Then("the Post response is obtained")
    public void getPostResponseReg() {
        log.info("Get the Post response");

        response.then().
                contentType(ContentType.JSON).
                extract().response();

        successReg = response.as(SuccessReg.class);

        Integer expectedId = 4;
        String expectedToken = "QpwL5tke4Pnpja7X4";

        Assert.assertEquals(successReg.getId(), expectedId);
        Assert.assertEquals(successReg.getToken(), expectedToken);
    }


    @Then("the result is not null")
    public void resultIsNoNull() {
        Assert.assertNotNull(successReg.getId());
        Assert.assertNotNull(successReg.getToken());

        log.info("Check that result is not null");
    }
//////////////////////////////////////////////////////////////////////////////////

    @When("a POST request with an empty password is sent to the server with the endpoint {}")
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

    @Then("the Post response is obtained and displayed")
    public void getPostResponseContentMissingPassword() {
        String expectedErrorMessage = "Missing password";
        response.then().
                contentType(ContentType.JSON).
                log().all().
                extract().response();

        unSuccessReg = response.as(UnSuccessReg.class);
        Assert.assertEquals(expectedErrorMessage, unSuccessReg.getError());
    }

    @Then("the expected status code should be {}")
    public void expectedStatusCodeIs(int expectedStatusCode) {
        Object actualStatusCode = response.getStatusCode();

        System.out.println("actual status code is: " + actualStatusCode);
        System.out.println("expected status code is: " + expectedStatusCode);
        Assert.assertEquals(expectedStatusCode, actualStatusCode);

        log.info("status code is 400");
    }


    @When("POST request is sent to Server: {string}")
    public void postRequestIsSentToTheServers(String endpoint) {

        Register user = new Register("eve.holt@reqres.in", "pistol");

        response = given().
                body(user).
//                contentType(ContentType.JSON).
        when().
                post(endpoint);
    }

    @Then("the Post the response is retrieved")
    public void getThePostResponse() {
        log.info("Get the POST response");

        response.then().
                log().all().
                extract().response();
        successReg = response.as(SuccessReg.class);

        if (responseSucReg == null) {
            throw new NullPointerException("responseSucReg is null");
        }

        Assert.assertEquals(successReg.getId(), responseSucReg.getExpectedId());
        Assert.assertEquals(successReg.getToken(), responseSucReg.getExpectedToken());


    }


}
















