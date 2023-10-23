package com.endava.atf.transition.testDataAPI;

import org.junit.Assert;
import org.junit.Test;
import java.util.List;
import java.util.stream.Collectors;

import static io.restassured.RestAssured.given;

public class ReqresTest {

    private final static String URL = "https://reqres.in/";

/// получить список пользователей Get

    @Test
    public void checkAvatarAndIdTest(){

//        MethodsAPI.installSpecification(MethodsAPI.requestSpec(URL), MethodsAPI.responseSpecOk200());

        List<UserData> users = given()
                .when()
                .get("api/users?page=2")
                .then()
                .log().all()
                .statusCode(200)
                .extract().body().jsonPath().getList("data", UserData.class);

        // перебираем список через stream().forEach(); x-> просто переменная счетчик
//        users.stream().forEach(x-> Assert.assertTrue(x.getEmail().contains(x.getId().toString())));

// проверяем, что email заканчивается на reqres.in
        Assert.assertTrue(users.stream().allMatch(x->x.getEmail().endsWith("reqres.in")));

        List<String> avatars = users.stream().map(UserData::getAvatar).collect(Collectors.toList());
        List<String> ids = users.stream().map(x->x.getId().toString()).collect(Collectors.toList());

        for(int i = 0; i< avatars.size(); i++){
            Assert.assertTrue(avatars.get(i).contains(ids.get(i)));
        }


    }

// проверяем регистрацию Post
    @Test
    public void successRegTet(){
//        MethodsAPI.installSpecification(MethodsAPI.requestSpec(URL), MethodsAPI.responseSpec400());

       Integer id = 4; // ответ
       String token = "QpwL5tke4Pnpja7X4"; // ответ

       Register user = new Register("eve.holt@reqres.in", "pistol"); // тело запроса

        SuccessReg successReg = given()
                .body(user)
                .when()
                .post("api/register")
                .then().log().all()
                .extract().as(SuccessReg.class);


        Assert.assertNotNull(successReg.getId());
        Assert.assertNotNull(successReg.getToken());

        Assert.assertEquals(id, successReg.getId());
        Assert.assertEquals(token, successReg.getToken());

    }


    @Test
    public void unSuccessRegTest(){
//        MethodsAPI.installSpecification(MethodsAPI.requestSpec(URL), MethodsAPI.responseSpecOk200());

        Register user = new Register("sydney@fife", ""); // тело запроса пароль пустой

        UnSuccessReg unSuccessReg= given()
                .body(user)
                .post("api/register")
                .then().log().all()
                .extract().as(UnSuccessReg.class);

        Assert.assertEquals("Missing password", unSuccessReg.getError());




    }

    @Test

    public static void sortedYearsTest(){



    }
}

