package com.endava.atf.transition.context;

import java.util.HashMap;
import java.util.Map;

public class ScenarioContext {
    private static ScenarioContext instance;
    private Map<String, Object> scenarioContext;

    private   ScenarioContext() {
        scenarioContext = new HashMap<>();
    }

//    public ScenarioContext(Map<String, Object> scenarioContext) {
//        this.scenarioContext = scenarioContext;
//    }

    public static ScenarioContext getInstance() {
        if (instance == null) {
            instance = new ScenarioContext();
        }
        return instance;
    }

    public void setContext(String key, Object value) {
        scenarioContext.put(key, value);
    }

    public Object getContext(String key) {
        return scenarioContext.get(key);
    }

    public void clearContext() {
        scenarioContext.clear();
    }

}

