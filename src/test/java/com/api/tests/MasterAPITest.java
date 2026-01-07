package com.api.tests;

import com.api.constant.Role;
import com.api.utils.AuthTokenProvider;
import com.api.utils.ConfigManager;
import io.restassured.module.jsv.JsonSchemaValidator;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import java.util.regex.Matcher;

import static io.restassured.RestAssured.given;

public class MasterAPITest {

    @Test

    public void masterAPITest()
    {
        given()
                .baseUri(ConfigManager.getProperty("BASE_URI"))
                .and()
                .header("Authorization", AuthTokenProvider.getToken(Role.FD))
                .and()
                .contentType("")
                .log().all()
                .when()
                .post("master")
                .then()
                .log().all()
                .statusCode(200)
                .time(Matchers.lessThan(1000L))
                .body("message",Matchers.equalTo("Success"))
                .body("data",Matchers.notNullValue())
                .body("data",Matchers.hasKey("mst_oem"))
                .body("data",Matchers.hasKey("mst_model"))
                .body("$",Matchers.hasKey("message"))
                .body("$",Matchers.hasKey("data"))
                .body("data.mst_oem.size()",Matchers.greaterThanOrEqualTo(0))
                .body("data.mst_model.size()",Matchers.greaterThan(0))
                .body("data.mst_oem.id",Matchers.everyItem(Matchers.notNullValue()))
                .body("data.mst_oem.name",Matchers.everyItem(Matchers.notNullValue()))
                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("responseSchema/masterAPIResponseSchema"));

    }


    @Test
    public void invalidTokenMasterAPITest()
    {
        given()
                .baseUri(ConfigManager.getProperty("BASE_URI"))
                .and()
                .header("Authorization", "")
                .and()
                .contentType("")
                .log().all()
                .when()
                .post("master")
                .then()
                .log().all()
                .statusCode(401);
    }
}
