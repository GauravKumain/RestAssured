package com.api.tests;

import static com.api.utils.ConfigManager.*;
import static com.api.constant.Role.*;
import static com.api.utils.SpecUtil.*;
import static io.restassured.module.jsv.JsonSchemaValidator.*;

import org.testng.annotations.Test;

import java.io.IOException;

import static io.restassured.RestAssured.*;
import static io.restassured.http.ContentType.*;
import static org.hamcrest.Matchers.*;

public class UserDetailsAPITest {

    @Test(description = "verify if the userdetails are shown correctly", groups = {"api", "smoke", "regression"})
    public void UserDetailsAPITest() throws IOException {


        given()
                .spec(requestSpecWithAuth(FD))
                .when()
                .get("userdetails")
                .then()
                .spec(responseSpec_OK())
                .and()
                .body(matchesJsonSchemaInClasspath("responseSchema/userDetailsResponseSchema.json"));


    }
}
