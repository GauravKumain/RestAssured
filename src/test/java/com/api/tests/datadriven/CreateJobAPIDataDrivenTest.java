package com.api.tests.datadriven;

import com.api.constant.*;
import com.api.request.model.*;
import org.hamcrest.Matchers;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static com.api.constant.Role.FD;
import static com.api.utils.DateTimeUtil.getTimeWithDaysAgo;
import static com.api.utils.SpecUtil.requestSpecWithAuth;
import static com.api.utils.SpecUtil.responseSpec_OK;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class CreateJobAPIDataDrivenTest {


    @Test(description = "verify if create job api is able to create Inwarranty jobs", groups = {"api", "regression", "smoke","csv", "dataDriven"}, dataProviderClass = com.dataproviders.DataProviderUtils.class,
            dataProvider = "CreateJobAPIDataProvider")
    public void createJobAPITest(CreateJobPayload createJobPayload) {


        given()
                .spec(requestSpecWithAuth(FD, createJobPayload))
                .when()
                .post("/job/create")
                .then()
                .spec(responseSpec_OK())
                .body(matchesJsonSchemaInClasspath("responseSchema/createJobAPIResponseSchema"))
                .body("message", Matchers.equalTo("Job created successfully. "))
                .body("data.mst_service_location_id", Matchers.equalTo(1))
                .body("data.job_number", Matchers.startsWith("JOB_"));
    }
}
