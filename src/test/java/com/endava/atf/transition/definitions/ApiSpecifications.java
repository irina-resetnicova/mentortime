package com.endava.atf.transition.definitions;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class ApiSpecifications {

    public static RequestSpecification getRequestSpecification() {
        return new RequestSpecBuilder()
//                .setBaseUri("https://reqres.in/")
//                .setContentType("application/json")
                .build();
    }

    public static ResponseSpecification getResponseSpecification() {
        return new ResponseSpecBuilder()
//                .expectStatusCode(200)
//                .expectContentType("application/json")
                .build();
    }
}
