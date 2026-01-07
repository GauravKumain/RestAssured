package com.api.tests;

import static com.api.utils.ConfigManager.*;

import static com.api.utils.AuthTokenProvider.*;

import static com.api.constant.Role.*;
import io.restassured.http.Header;
import io.restassured.module.jsv.JsonSchemaValidator;
import org.testng.annotations.Test;

import java.io.IOException;

import static io.restassured.RestAssured.*;
import static io.restassured.http.ContentType.*;
import static org.hamcrest.Matchers.*;

public class UserDetailsAPITest {

    @Test
    public void UserDetailsAPITest() throws IOException {

        Header headerOne = new Header("Authorization", getToken(FD));

        String fname = given()
                .log().uri()
                .baseUri(getProperty("BASE_URI"))
                .and()
                .accept(JSON)
                .and()
                .header(headerOne)
                .when()
                .get("userdetails")
                .then()
                .log().all()
                .statusCode(200)
                .time(lessThan(1500L))
                .body("message",equalTo("Success"))
                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("responseSchema/userDetailsResponseSchema.json"))
                .extract()
                .jsonPath()
                .getString("data.first_name");

        System.out.println(fname);


    }
}
