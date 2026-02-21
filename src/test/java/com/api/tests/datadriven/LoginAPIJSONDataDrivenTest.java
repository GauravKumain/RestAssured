package com.api.tests.datadriven;

import com.api.request.model.UserCredentials;
import com.dataproviders.api.bean.UserBean;
import org.testng.annotations.Test;

import static com.api.utils.SpecUtil.requestSpec;
import static com.api.utils.SpecUtil.responseSpec_OK;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

public class LoginAPIJSONDataDrivenTest {



    @Test(description = "verifying if login api is working for FD user",
            groups = {"api", "smoke", "Regression"},
            dataProviderClass = com.dataproviders.DataProviderUtils.class,
            dataProvider = "LoginAPIJsonDataProvider")
    public void loginAPIJsonTest(UserCredentials userCredentials) {


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
