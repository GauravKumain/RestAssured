package com.api.tests;

import com.api.request.model.UserCredentials;
import com.api.utils.SpecUtil;
import io.restassured.module.jsv.JsonSchemaValidator;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class LoginAPITest {


    @Test
    public void loginAPITest() {


        UserCredentials userCredentials = new UserCredentials("iamfd", "password");

        given()
                .spec(SpecUtil.requestSpec(userCredentials))
                .when()
                .post("/login")
                .then()
                .spec(SpecUtil.responseSpec_OK())
                .body("message", equalTo("Success"))
                .body("data.token", notNullValue())
                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("responseSchema/loginAPIResponseScema.json"));


    }
}
