package com.endava.atf.transition.testDataAPI;

import org.json.JSONObject;

public class ResponseBody {

    private final JSONObject responseJson;

    public ResponseBody() {
        responseJson = new JSONObject();
        responseJson.put("id", 4);
        responseJson.put("token", "QpwL5tke4Pnpja7X4");
    }

    public int getId() {
        return responseJson.getInt("id");
    }

    public String getToken() {
        return responseJson.getString("token");
    }
}
