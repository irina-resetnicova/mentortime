package com.endava.atf.transition.testDataUI;

import org.apache.commons.lang3.RandomStringUtils;

public class MethodsUI {

    public static String randomEmail() {
        String email = RandomStringUtils.randomAlphanumeric(10) + "@gmail.com";
        return email;
    }

    String email = "*/&^%" + RandomStringUtils.randomAlphanumeric(5) + "@gmail.com";

    public static String randomPassword() {
        String email = RandomStringUtils.randomAlphanumeric(10);
        return email;
    }

    public static String randomFirstName() {
        String email = RandomStringUtils.randomAlphanumeric(10);
        return email;
    }

    public static String randomLastName() {
        String email = RandomStringUtils.randomAlphanumeric(10);
        return email;
    }


}
