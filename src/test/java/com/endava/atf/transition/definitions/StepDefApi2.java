package com.endava.atf.transition.definitions;

import Context.ScenarioContext;
import com.endava.atf.transition.testDataAPI.SuccessReg;
import com.endava.atf.transition.testDataAPI.UnSuccessReg;
import com.endava.atf.transition.testDataAPI.UserData;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
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

    private List<UserData> users;

    private SuccessReg successReg;
    private UnSuccessReg unSuccessReg;
    Response response;
    public HashMap<Object, Object> map = new HashMap<Object, Object>();
    private static final Logger log = LogManager.getLogger(StepDefinitionsAPI.class);

    public StepDefApi2(ScenarioContext scenarioContext) {
        this.scenarioContext = scenarioContext;
    }


    @Given("the base URI is set to https: {string}")
    public void theBaseURIIsSetToHttps(String baseURI) {
        RestAssured.baseURI = baseURI;
        log.info("the base URI is set");
    }

    @When("GET request is sent to server: {string}")
    public void getRequestIsSentToServer(String endpoint) {
        response = given().
                when().
                get(endpoint).
                then().
                contentType(ContentType.JSON).
                statusCode(200).
                extract().response();

        int actualStatusCode = response.statusCode();

        scenarioContext.setContext("statusCode", actualStatusCode);
        log.info("GET request is sent to server: ");
    }

    @Then("List of users appears on the screen")
    public void listOfUsersAppearsOnTheScreen() {

        users = response.getBody().jsonPath().getList("data", UserData.class);

        scenarioContext.setContext("users", users);
        System.out.println("response.asString " + response.asString());

        String jsonString = response.asString();

        JsonParser parser = new JsonParser();

        JsonObject jsonObject = parser.parse(jsonString).getAsJsonObject();
        JsonArray dataArray = jsonObject.getAsJsonArray("data");

        System.out.println("dataArray: " + dataArray);

        scenarioContext.setContext("dataArrayNew", dataArray);

        for (int i = 0; i < dataArray.size(); i++) {
            JsonObject item = dataArray.get(i).getAsJsonObject();

            int id = item.get("id").getAsInt();
            String email = item.get("email").getAsString();
            String first_name = item.get("first_name").getAsString();
            String last_name = item.get("last_name").getAsString();
            String avatar = item.get("avatar").getAsString();

            System.out.println("id " + id);
            System.out.println("email " + email);
            System.out.println("first_name " + first_name);
            System.out.println("last_name " + last_name);
            System.out.println("avatar " + avatar);
        }
        log.info("List of users appeared on the screen");
    }

    @Then("Response code is {int}")
    public void responseCodeIs(int expectedStatusCode) {
        Object actualStatusCode = scenarioContext.getContext("statusCode");
        Assert.assertEquals(expectedStatusCode, actualStatusCode);

        System.out.println("actual status code is: " + actualStatusCode);
        log.info("Response code is 200");
    }


    @Then("All users have emails end on reqres.in")
    public void allUsersHaveEmailsEnsdOnReqresIn() {

        Object dataArrayNew = scenarioContext.getContext("dataArrayNew");

        // Создаем объект GSON
        Gson gson = new Gson();

// Определяем тип для списка объектов
        List<UserData> users = gson.fromJson(dataArrayNew.toString(), new TypeToken<List<UserData>>() {
        }.getType());

        for (UserData listUsers : users) {
            System.out.println("id: " + listUsers.getId());
            System.out.println("email: " + listUsers.getEmail());
            System.out.println("first_name: " + listUsers.getFirst_name());
            System.out.println("last_name: " + listUsers.getLast_name());
            System.out.println("avatar: " + listUsers.getAvatar());
            System.out.println();
        }


        Assert.assertTrue(users.stream().allMatch(x -> x.getEmail().endsWith("reqres.in")));
        scenarioContext.setContext("usersTypeList", users);
        log.info("All users have emails end on reqres.in");

    }

    @Then("Display all avatars from the list")
    public void displayAllAvatarsFromTheList() {

        scenarioContext.getContext("usersTypeList");

        List<String> avatars = users.stream().map(UserData::getAvatar).collect(Collectors.toList());

        for (String avatarsPrint : avatars) {
            System.out.println(avatarsPrint);
        }

        log.info("Response code is 200");
        log.info("All avatars are displayed");

    }

////////////////////////////////////////////////////////////////////////////////////////////////////////

    @When("POST request is sent to the Server: {string}")
    public void postRequestIsSentToTheServer(String endpoint) {
        map.put("email", "eve.holt@reqres.in");
        map.put("password", "pistol");

        response = given().
                contentType(ContentType.JSON).
                body(map).
                when().
                post(endpoint).
                then().
                contentType(ContentType.JSON).
                statusCode(200).
                extract().response();

        successReg = response.as(SuccessReg.class);


        int actualStatusCode = response.statusCode();
        scenarioContext.setContext("statusCodePost", actualStatusCode);
        System.out.println("actual status code is: " + actualStatusCode);

        //consumer
        map.forEach((key, value) -> System.out.println("Key: " + key + ", Value: " + value));
        log.info("POST request is sent to the Server");

    }

    @Then("status-code {int} appears on the screen")
    public void statusCodeAppearedOnTheScreen(int expectedStatusCode) {
        Integer expectedId = 4;
        String expectedToken = "QpwL5tke4Pnpja7X4";

        Object actualStatusCode = scenarioContext.getContext("statusCodePost");
        Assert.assertEquals(expectedStatusCode, actualStatusCode);

        Assert.assertEquals(successReg.getId(), expectedId);
        Assert.assertEquals(successReg.getToken(), expectedToken);

        System.out.println("actual status code is: " + actualStatusCode);
        System.out.println("expected status code is: " + expectedStatusCode);

        log.info("status code is 200");

    }

    @Then("result is not null")
    public void resultIsNoNull() {
        Assert.assertNotNull(successReg.getId());
        Assert.assertNotNull(successReg.getToken());

        log.info("the result is not null");
    }
//////////////////////////////////////////////////////////////////////////////////

    @When("POST request where password is empty is sent to the Server: {string}")
    public void postRequestWherePasswordIsEmptyIsSentToTheServer(String endpoint) {
        map.put("email", "sydney@fife");
        map.put("password", "");

        response = given().
                contentType(ContentType.JSON).
                body(map).
                when().
                post(endpoint).
                then().
                contentType(ContentType.JSON).
                statusCode(400).
                extract().response();

        unSuccessReg = response.as(UnSuccessReg.class);

        int actualStatusCode = response.statusCode();
        scenarioContext.setContext("statusCodePostNegative", actualStatusCode);

        log.info("POST request where password is empty is sent to the Server");
    }


    @Then("status code {int} appears on the screen")
    public void statusCode400AppearedOnTheScreen(int expectedStatusCode) {
        String expectedErrorMessage = "Missing password";
        Object actualStatusCode = scenarioContext.getContext("statusCodePostNegative");

        System.out.println("actual status code is: " + actualStatusCode);
        System.out.println("expected status code is: " + expectedStatusCode);

        Assert.assertEquals(expectedStatusCode, actualStatusCode);
        Assert.assertEquals(expectedErrorMessage, unSuccessReg.getError());

        log.info("status code 400 appears on the screen");

    }

}








