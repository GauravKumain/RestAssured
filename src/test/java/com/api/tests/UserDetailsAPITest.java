package com.api.tests;

import static com.api.utils.ConfigManager.*;
import static com.api.constant.Role.*;
import com.api.utils.SpecUtil;
import io.restassured.module.jsv.JsonSchemaValidator;
import org.testng.annotations.Test;
import java.io.IOException;
import static io.restassured.RestAssured.*;
import static io.restassured.http.ContentType.*;
import static org.hamcrest.Matchers.*;

public class UserDetailsAPITest {

    @Test
    public void UserDetailsAPITest() throws IOException {


        String fname = given()
                .log().uri()
                .baseUri(getProperty("BASE_URI"))
                .and()
                .accept(JSON)
                .and()
                .spec(SpecUtil.requestSpecWithAuth(FD))
                .when()
                .get("userdetails")
                .then()
                .spec(SpecUtil.responseSpec_OK())
                .body("message", equalTo("Success"))
                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("responseSchema/userDetailsResponseSchema.json"))
                .extract()
                .jsonPath()
                .getString("data.first_name");

        System.out.println(fname);


    }
}
