package com.endava.atf.transition.testDataAPI;

public class SuccessReg {

    private Integer id;
    private String token;
    private String email;
    private String password;

    public SuccessReg(Integer id, String token) {
        this.id = id;
        this.token = token;
    }

    public SuccessReg(String email, String password) {
        this.email = email;
        this.password = password;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
