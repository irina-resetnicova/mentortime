package com.endava.atf.transition.definitions;

import com.endava.atf.transition.config.DataBase.DbManager;
import io.cucumber.java.en.Given;

public class StepDefinitions {
    private DbManager dbManager;

    public StepDefinitions (DbManager dbManager) {
        this.dbManager = dbManager;
    }

    @Given("Smoke step")
    public void smokeStep() {
        System.out.println(dbManager.executeQuery("SELECT * FROM oc_api"));

        System.out.println("Hello running");
    }

}
