package com.endava.atf.transition.runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features",
        //path of step definition file
        glue = "com.endava.atf.transition.definitions",
        plugin = {"pretty",
                "json:target/cucumber-reports/report.json",
                "html:target/cucumber-reports/report.html",
        },
        tags = ("@API and @Registration")

)
public class TestRunner {
}