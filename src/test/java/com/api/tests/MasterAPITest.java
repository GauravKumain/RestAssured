package com.api.tests;

import static com.api.utils.SpecUtil.*;
import static io.restassured.module.jsv.JsonSchemaValidator.*;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import static com.api.constant.Role.*;
import static io.restassured.RestAssured.given;

public class MasterAPITest {

    @Test(description = "Validating if correct status codes are given in response", groups = {"api","negative"})

    public void masterAPITest() {
        given()
                .spec(requestSpecWithAuth(FD))
                .when()
                .post("master")
                .then()
                .spec(responseSpec_OK())
                .body("message", Matchers.equalTo("Success"))
                .body("data", Matchers.notNullValue())
                .body("data", Matchers.hasKey("mst_oem"))
                .body("data", Matchers.hasKey("mst_model"))
                .body("$", Matchers.hasKey("message"))
                .body("$", Matchers.hasKey("data"))
                .body("data.mst_oem.size()", Matchers.greaterThanOrEqualTo(0))
                .body("data.mst_model.size()", Matchers.greaterThan(0))
                .body("data.mst_oem.id", Matchers.everyItem(Matchers.notNullValue()))
                .body("data.mst_oem.name", Matchers.everyItem(Matchers.notNullValue()))
                .body(matchesJsonSchemaInClasspath("responseSchema/masterAPIResponseSchema"));

    }


    @Test
    public void invalidTokenMasterAPITest() {
        given()
                .spec(requestSpec())
                .log().all()
                .when()
                .post("master")
                .then()
                .spec(responseSpec_TEXT(401));
    }
}
