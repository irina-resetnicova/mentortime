package com.endava.atf.transition.definitions;

import com.endava.atf.transition.configs.DbConnection;
import com.endava.atf.transition.configs.YAMLConfigLoader;
import com.endava.atf.transition.context.ScenarioContext;
import com.endava.atf.transition.context.TestContext;
import com.endava.atf.transition.drivers.Driver;
import com.endava.atf.transition.testDataUI.UserDao;
import com.endava.atf.transition.utils.Helper;
import io.cucumber.java.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Map;

public class Hooks {
    private static final Logger log = LogManager.getLogger(Hooks.class);
    private static String currentScenarioName = null;
    private ScenarioContext scenarioContext = ScenarioContext.getInstance();
    private static boolean isUITest = false;
    private UserDao query;

    public Hooks(UserDao query) {
        this.query = query;
    }

    @BeforeAll
    public static void initApplication() throws IOException {
        YAMLConfigLoader configLoader = new YAMLConfigLoader();
        Map<String, Object> yamlData = configLoader.loadYAML("src/test/resources/application.yml");
        TestContext.setProperties(yamlData);
    }

    @Before("@API")
    public void setUpApi(Scenario scenario) {
        String scenarioName = scenario.getName();
        System.out.println("\u001B[32mSCENARIO: " + scenarioName + "\u001B[0m");
        log.info("Test started " + getClass());
        scenarioContext.clearContext();
    }

    @Before("@UI")
    public void setUp(Scenario scenario) {
        String scenarioName = scenario.getName();
        System.out.println("\u001B[32mSCENARIO: " + scenarioName + "\u001B[0m");
        Helper.setDriver(Driver.getDriver());
        scenarioContext.clearContext();
        log.info("Test started: " + getClass());

        if (scenario.getSourceTagNames().contains("@UI")) {
            isUITest = true;
        }
    }

    @Before("@DBClean")
    public void before_db_clean(Scenario scenario) throws SQLException {
        if (currentScenarioName == null) {
            currentScenarioName = scenario.getName();
            PreparedStatement psDeleteAll = query.deleteAllFromCustomers();
            psDeleteAll.executeUpdate();
        } else {
            if (!currentScenarioName.equals(scenario.getName())) {
                PreparedStatement psDeleteAll = query.deleteAllFromCustomers();
                psDeleteAll.executeUpdate();
                currentScenarioName = scenario.getName();
            }
        }
    }

    @After("@UI")
    public void closeWebdriver() {
        log.info("Scenario finished");
    }

    @AfterAll
    public static void tearDown() {
        log.info("Test finished");
        if (isUITest) {
            Driver.tearDown();
            isUITest = false;
        }
        DbConnection.closeConnection();
        ScenarioContext sc = ScenarioContext.getInstance();
        sc.clearContext();
    }
}














