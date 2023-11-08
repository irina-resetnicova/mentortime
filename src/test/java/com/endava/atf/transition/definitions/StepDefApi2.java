package com.endava.atf.transition.definitions;

import com.endava.atf.transition.context.ScenarioContext;
import com.endava.atf.transition.testDataAPI.*;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import static io.restassured.RestAssured.given;

public class StepDefApi2 {
    private static final Logger log = LogManager.getLogger(StepDefApi2.class);
    private final ScenarioContext scenarioContext;
    private SuccessReg successReg;
    private UnSuccessReg unSuccessReg;
    Response response;
    ResponseSuccessRegistration responseSucReg = new ResponseSuccessRegistration();
    HashMap<Object, Object> map = new HashMap<>();

    public StepDefApi2() {
        this.scenarioContext = ScenarioContext.getInstance();
    }

    @Given("the base URI is set to  {string}")
    public void theBaseURIIsSetToHttps(String baseURI) {
        RestAssured.baseURI = baseURI;
        log.info("The base URI is set:  " + baseURI);
    }

    @When("a GET request is sent to the server with the endpoint {string}")
    public void getRequestIsSentToServer(String endpoint) {
        log.info("GET request is sent to server: " + endpoint);
        response = given().
                log().all().
                header("Accept", "application/json").
                contentType(ContentType.JSON).
                when().
                get(endpoint);

        scenarioContext.setContext("response", response);
    }

    @Then("the response code should be {}")
    public void responseCodeIs(int expectedStatusCode) {
        log.info("Response code should be: " + expectedStatusCode);
        Response response = (Response) scenarioContext.getContext("response");
        response.then()
                .log().status()
                .assertThat()
                .statusCode(expectedStatusCode);

        scenarioContext.setContext("response", response);
    }

    @Then("the response is retrieved and displayed on the screen")
    public void listOfUsersAppearsOnTheScreen() {
        log.info("Get response in: " + ContentType.JSON);
        Response response = (Response) scenarioContext.getContext("response");
        response.then().
                log().body().
                contentType(ContentType.JSON).
                extract().response();

        scenarioContext.setContext("response", response);
    }

    @Then("the response confirm requirements")
    public void theResponseConfirmRequirements() throws IOException {
        log.info("The actual JSON response match expected JSON response");
        Response response = (Response) scenarioContext.getContext("response");

        String jsonResponse = response.asString();

        String filePath = "src/test/java/com/endava/atf/transition/jsonCollection/user_response.json";
        String jsonFromFile = new String(Files.readAllBytes(Paths.get(filePath)));

        JsonObject expectedObj = JsonParser.parseString(jsonFromFile).getAsJsonObject();
        JsonObject actualObj = JsonParser.parseString(jsonResponse).getAsJsonObject();

        Assert.assertEquals(expectedObj, actualObj);
    }

    @Then("all users have emails end on reqres.in")
    public void allUsersHaveEmailsEnsdOnReqresIn() {
        log.info("All users have emails end on reqres.in");
        Response response = (Response) scenarioContext.getContext("response");
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
        scenarioContext.setContext("response", response);
    }

    @Then("all avatars are displaying on the screen from the list")
    public void displayAllAvatarsFromTheList() {
        log.info("All avatars are displayed");

        Response response = (Response) scenarioContext.getContext("response");

        List<UserData> users = response.getBody().jsonPath().getList("data", UserData.class);
        List<String> avatars = users.stream().map(UserData::getAvatar).collect(Collectors.toList());

        for (String avatar : avatars) {
            System.out.println(avatar);
        }
        scenarioContext.setContext("response", response);
    }

    @Then("the List of Resources can be sorted by years")
    public void listRESOURCECanBeSortedByYears() {
        log.info("List<RESOURCE> can be sorted by years");
        Response response = (Response) scenarioContext.getContext("response");

        List<ColorsData> colors = response.getBody().jsonPath().getList("data", ColorsData.class);

        List<Integer> years = colors.stream().map(ColorsData::getYear).collect(Collectors.toList());
        List<Integer> sortedYears = years.stream().sorted().collect(Collectors.toList());
        scenarioContext.setContext("response", response);

        Assert.assertEquals(sortedYears, years);
        System.out.println(years);
        System.out.println(sortedYears);
    }

    @When("a POST request is sent to the Server with the endpoint {string}")
    public void postRequestIsSentToTheServer(String endpoint) {
        log.info("POST request is sent to the Server");

        HashMap<Object, Object> map = new HashMap<>();
        map.put("email", "eve.holt@reqres.in");
        map.put("password", "pistol");

        response = given().
                log().all().
                contentType(ContentType.JSON).
                body(map).
                when().
                post(endpoint);

        map.forEach((key, value) -> System.out.println("Key: " + key + ", Value: " + value));
        scenarioContext.setContext("response", response);
    }

    @Then("the Post response is obtained")
    public void getPostResponseReg() {
        log.info("Get the POST response");
        Response response = (Response) scenarioContext.getContext("response");
        response.then().
                log().body().
                contentType(ContentType.JSON).
                extract().response();

        successReg = response.as(SuccessReg.class);

        Integer expectedId = 4;
        String expectedToken = "QpwL5tke4Pnpja7X4";

        Assert.assertEquals(successReg.getId(), expectedId);
        Assert.assertEquals(successReg.getToken(), expectedToken);
        scenarioContext.setContext("response", response);
    }

    @Then("the result is not null")
    public void resultIsNoNull() {
        log.info("Check that result is not null");
        Response response = (Response) scenarioContext.getContext("response");
        Assert.assertNotNull(successReg.getId());
        Assert.assertNotNull(successReg.getToken());
        scenarioContext.setContext("response", response);
    }

    @When("a POST request with an empty password is sent to the server with the endpoint {string}")
    public void postRequestWherePasswordIsEmptyIsSentToTheServer(String endpoint) {
        log.info("POST request where password is empty is sent to the Server");
        map.put("email", "sydney@fife");
        map.put("password", "");

        response = given().
                log().body().
                contentType(ContentType.JSON).
                body(map).
                when().
                post(endpoint);
        scenarioContext.setContext("response", response);
    }

    @Then("the Post response is obtained and displayed")
    public void getPostResponseContentMissingPassword() {
        log.info("The response is obtained and displayed");
        Response response = (Response) scenarioContext.getContext("response");
        String expectedErrorMessage = "Missing password";
        response.then().
                contentType(ContentType.JSON).
                log().all().
                extract().response();

        unSuccessReg = response.as(UnSuccessReg.class);
        Assert.assertEquals(expectedErrorMessage, unSuccessReg.getError());
        scenarioContext.setContext("response", response);
    }

    @Then("the expected status code should be {}")
    public void expectedStatusCodeIs(int expectedStatusCode) {
        log.info("The status code is 400");
        Response response = (Response) scenarioContext.getContext("response");
        Object actualStatusCode = response.getStatusCode();

        System.out.println("actual status code is: " + actualStatusCode);
        System.out.println("expected status code is: " + expectedStatusCode);
        Assert.assertEquals(expectedStatusCode, actualStatusCode);
        scenarioContext.setContext("response", response);
    }

    @When("a POST request is sent to the Server: {string}")
    public void aPOSTRequestIsSentToTheServer(String endpoint) {
        log.info("The POST the response is retrieved");
        Register user = new Register("eve.holt@reqres.in", "pistol");
        response = given().
                body(user).
                contentType(ContentType.JSON).
                when().
                post(endpoint);
        scenarioContext.setContext("response", response);
    }

    @Then("the Post the response is retrieved")
    public void getThePostResponse() {
        log.info("the POST the response is retrieved");
        Response response = (Response) scenarioContext.getContext("response");
        response.then().
                log().body().
                extract().response();
        successReg = response.as(SuccessReg.class);

        Assert.assertEquals(successReg.getId(), responseSucReg.getExpectedId());
        Assert.assertEquals(successReg.getToken(), responseSucReg.getExpectedToken());
        scenarioContext.setContext("response", response);
    }
}
















