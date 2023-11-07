package com.endava.atf.transition.definitions;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class ApiSpecifications {

    public static RequestSpecification getRequestSpecification() {
        return new RequestSpecBuilder()
//                .setBaseUri("https://reqres.in/")
                .setContentType(ContentType.JSON)
                .build();
    }

    public static void setupRestAssureRequest() {
//        RequestSpecification requestSpecXml = RestAssured.given();
//        requestSpecXml.contentType(ContentType.JSON);
    }

//
//    public static ResponseSpecification getResponseSpecification() {
//        return new ResponseSpecBuilder()
////                .expectStatusCode(200)
//                .expectContentType("application/json")
//                .build();
//    }
}
