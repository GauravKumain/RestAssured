package com.api.tests;

import com.api.request.model.UserCredentials;
import static com.api.utils.SpecUtil.*;
import static io.restassured.module.jsv.JsonSchemaValidator.*;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class LoginAPITest {

    UserCredentials userCredentials;

    @BeforeMethod(description = "create payload for login api")
    public void setup() {
        userCredentials = new UserCredentials("iamfd", "password");
    }

    @Test(description = "verifying if login api is working for FD user", groups = {"api", "smoke", "Regression"})
    public void loginAPITest() {


        given()
                .spec(requestSpec(userCredentials))
                .when()
                .post("/login")
                .then()
                .spec(responseSpec_OK())
                .body("message", equalTo("Success"))
                .body("data.token", notNullValue())
                .body(matchesJsonSchemaInClasspath("responseSchema/loginAPIResponseScema.json"));


    }
}
