package com.api.tests;

import com.api.utils.SpecUtil;
import io.restassured.module.jsv.JsonSchemaValidator;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import static com.api.constant.Role.FD;
import static io.restassured.RestAssured.given;

public class MasterAPITest {

    @Test

    public void masterAPITest() {
        given()
                .spec(SpecUtil.requestSpecWithAuth(FD))
                .when()
                .post("master")
                .then()
                .spec(SpecUtil.responseSpec_OK())
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
                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("responseSchema/masterAPIResponseSchema"));

    }


    @Test
    public void invalidTokenMasterAPITest() {
        given()
                .spec(SpecUtil.requestSpec())
                .log().all()
                .when()
                .post("master")
                .then()
                .spec(SpecUtil.responseSpec_TEXT(401));
    }
}
