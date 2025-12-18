package com.api.tests;

import com.api.pojo.UserCredentials;
import io.restassured.module.jsv.JsonSchemaValidator;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.*;

public class LoginAPITest {


    @Test
    public void loginAPITest() {

        UserCredentials userCredentials = new UserCredentials("iamfd","password");

        given()
                .baseUri("http://64.227.160.186:9000/v1")
                .and()
                .contentType(JSON)
                .and()
                .accept(JSON)
                .and()
                .body(userCredentials)
                .log().method()
                .log().uri()
                .log().headers()
                .log().body()
                .when()
                .post("/login")
                .then()
                .log().all()
                .statusCode(200)
                .time(lessThan(1500L))
                .body("message", equalTo("Success"))
                .body("data.token",notNullValue())
                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("responseSchema/loginAPIResponseScema.json"));





    }
}
