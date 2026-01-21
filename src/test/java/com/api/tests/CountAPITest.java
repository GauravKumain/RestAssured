package com.api.tests;


import static com.api.utils.SpecUtil.*;
import static io.restassured.module.jsv.JsonSchemaValidator.*;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;
import static com.api.constant.Role.*;
import static io.restassured.RestAssured.given;

public class CountAPITest {

    @Test(description = "Validating if count api is giving correct response", groups = {"api","regression"})

    public void verifyCountAPIResponse() {
        given()
                .spec(requestSpecWithAuth(FD))
                .when()
                .get("/dashboard/count")
                .then()
                .spec(responseSpec_OK())
                .body("message", Matchers.equalTo("Success"))
                .body("data", Matchers.notNullValue())
                .body("data.size()", Matchers.equalTo(3))
                .body("data.count", Matchers.everyItem(Matchers.greaterThanOrEqualTo(0)))
                .body("data.label", Matchers.everyItem(Matchers.not(Matchers.blankOrNullString())))
                .body("data.key", Matchers.containsInAnyOrder("pending_for_delivery", "created_today", "pending_fst_assignment"))
                .body(matchesJsonSchemaInClasspath("responseSchema/countAPIResponseSchema.json"));

    }

    @Test(description = "Validating if count api giving correct stratus codes ", groups = {"api","negative"})
    public void countAPITest_MissingAuthToken() {
        given()
                .spec(requestSpec())
                .when()
                .get("/dashboard/count")
                .then()
                .spec(responseSpec_TEXT(401));
    }
}
