package com.endava.atf.transition.testDataAPI;

public class ResponseSuccessRegistration {
    private Integer expectedId = 4;
    private String expectedToken = "QpwL5tke4Pnpja7X4";

    public ResponseSuccessRegistration() {
    }

    public ResponseSuccessRegistration(Integer expectedId, String expectedToken) {
        this.expectedId = expectedId;
        this.expectedToken = expectedToken;
    }

    public Integer getExpectedId() {
        return expectedId;
    }

    public void setExpectedId(Integer expectedId) {
        this.expectedId = expectedId;
    }

    public String getExpectedToken() {
        return expectedToken;
    }

    public void setExpectedToken(String expectedToken) {
        this.expectedToken = expectedToken;
    }
}
