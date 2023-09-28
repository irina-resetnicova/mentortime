package com.endava.atf.transition.runner;

import io.cucumber.junit.platform.engine.Constants;
import org.junit.platform.suite.api.*;

@Suite
@IncludeEngines("cucumber")
@SelectClasspathResource("features")
@IncludeTags("Registration")
@ConfigurationParameter(key = Constants.GLUE_PROPERTY_NAME, value = "com.endava.atf.transition.definitions")
public class CucumberRunnerTests {

}