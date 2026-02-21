package com.api.tests.datadriven;

import com.api.request.model.CreateJobPayload;
import org.hamcrest.Matchers;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.api.constant.Role.FD;
import static com.api.utils.SpecUtil.requestSpecWithAuth;
import static com.api.utils.SpecUtil.responseSpec_OK;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class CreateJobAPIJsonDataDrivenTest {
    CreateJobPayload createJobPayload;

    @BeforeMethod(description = "creating create job api request payload")
    public void setup() {

     //   createJobPayload = FakerDataGenerator.generateFakeCreateJobData();

    }


    @Test(description = "verify if create job api is able to create Inwarranty jobs", groups = {"api", "regression", "smoke","JSON"},dataProviderClass = com.dataproviders.DataProviderUtils.class,
            dataProvider = "CreateJobAPIJsonDataProvider")
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
