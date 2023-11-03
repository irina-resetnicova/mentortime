package com.endava.atf.transition.definitions;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.config.MultiPartConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import java.nio.charset.StandardCharsets;

public class ApiSpecifications {

    public static RequestSpecification getRequestSpecification() {
        return new RequestSpecBuilder()
//                .setBaseUri("https://reqres.in/")
                .setContentType(ContentType.JSON)
                .build();
    }

    public static void setupRestAssureRequest(){
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
