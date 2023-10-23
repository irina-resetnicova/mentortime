package com.endava.atf.transition.testDataAPI;

public class SuccessReg {

    private Integer id;
    private String token;

    public SuccessReg(Integer id, String token) {
        this.id = id;
        this.token = token;
    }

    public SuccessReg() {
    }

    @Override
    public String toString() {
        return "SuccessReg{" +
                "id=" + id +
                ", token='" + token + '\'' +
                '}';
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Integer getId() {
        return id;
    }

    public String getToken() {
        return token;
    }
}
