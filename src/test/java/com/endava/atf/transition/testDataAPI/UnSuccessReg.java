package com.endava.atf.transition.testDataAPI;

public class UnSuccessReg {

    private String error;

    public UnSuccessReg() {
    }

    @Override
    public String toString() {
        return "UnSuccessReg{" +
                "error='" + error + '\'' +
                '}';
    }

    public UnSuccessReg(String error) {
        this.error = error;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
