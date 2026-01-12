package com.api.tests;


import com.api.utils.SpecUtil;
import io.restassured.module.jsv.JsonSchemaValidator;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;
import static com.api.constant.Role.FD;
import static io.restassured.RestAssured.given;

public class CountAPITest {

    @Test

    public void verifyCountAPIResponse() {
        given()
                .spec(SpecUtil.requestSpecWithAuth(FD))
                .when()
                .get("/dashboard/count")
                .then()
                .spec(SpecUtil.responseSpec_OK())
                .body("message", Matchers.equalTo("Success"))
                .body("data", Matchers.notNullValue())
                .body("data.size()", Matchers.equalTo(3))
                .body("data.count", Matchers.everyItem(Matchers.greaterThanOrEqualTo(0)))
                .body("data.label", Matchers.everyItem(Matchers.not(Matchers.blankOrNullString())))
                .body("data.key", Matchers.containsInAnyOrder("pending_for_delivery", "created_today", "pending_fst_assignment"))
                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("responseSchema/countAPIResponseSchema.json"));

    }

    @Test
    public void countAPITest_MissingAuthToken() {
        given()
                .spec(SpecUtil.requestSpec())
                .when()
                .get("/dashboard/count")
                .then()
                .spec(SpecUtil.responseSpec_TEXT(401));
    }
}
