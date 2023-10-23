package Context;

import java.util.HashMap;
import java.util.Map;

public class ScenarioContext2 {
    private static ScenarioContext instance;
    private Map<String, Object> scenarioContext;

    private void ScenarioContext() {
        scenarioContext = new HashMap<>();
    }

    public ScenarioContext2(Map<String, Object> scenarioContext) {
        this.scenarioContext = scenarioContext;
    }

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

//public class Main {
//    public static void main(String[] args) {
//        ScenarioContext context1 = ScenarioContext.getInstance();
//        context1.setContext("key1", "value1");
//
//        // В другой части приложения
//        ScenarioContext context2 = ScenarioContext.getInstance();
//        String value = (String) context2.getContext("key1");
//        System.out.println(value); // Выведет "value1"
//    }
//}
