package com.endava.atf.transition.context;

import java.util.Map;

public class TestContext {
    private static Map<String, Object> properties;

    public static Map<String, Object> getProperties() {
        return properties;
    }

    public static void setProperties(Map<String, Object> properties) {
        TestContext.properties = properties;
    }
}
