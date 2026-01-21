package com.api.tests;

import static com.api.constant.Role.*;
import static com.api.utils.DateTimeUtil.getTimeWithDaysAgo;
import static io.restassured.RestAssured.*;

import com.api.constant.*;
import com.api.request.model.*;
import com.api.utils.SpecUtil;
import io.restassured.module.jsv.JsonSchemaValidator;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class CreateJobAPITest {


    @Test
    public void createJobAPITest()
    {

        Customer customer = new Customer("Gaurav","Kumain","8787474487","","qwssww@gmail.com","");
        CustomerAddress customerAddress = new CustomerAddress("101","raja","aaawaj","rana","badrish","12212","India","uttarakhand");
        CustomerProduct customerProduct = new CustomerProduct(getTimeWithDaysAgo(10),"16572885284323","16572885284323","16572885284323",getTimeWithDaysAgo(10), Product.NEXUS_2.getCode(), Model.NEXUS_2_BLUE.getCode());
        Problems problems = new Problems(Problem.SMARTPHONE_IS_RUNNING_SLOW.getCode(), "Battery Issue");
        List<Problems> problemList  = new ArrayList<Problems>();
        problemList.add(problems);

        CreateJobPayload createJobPayload = new CreateJobPayload(Service_Location.SERVICE_LOCATION_A.getCode(), Platform.Front_Desk.getCode(), Warranty_Status.IN_WARRANTY.getCode(), OEM.GOOGLE.getCode(),customer,customerAddress,customerProduct,problemList);


        given()
                .spec(SpecUtil.requestSpecWithAuth(FD,createJobPayload))
                .when()
                .post("/job/create")
                .then()
                .spec(SpecUtil.responseSpec_OK())
                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("responseSchema/createJobAPIResponseSchema"))
                .body("message",Matchers.equalTo("Job created successfully. "))
                .body("data.mst_service_location_id",Matchers.equalTo(1))
                .body("data.job_number",Matchers.startsWith("JOB_"));
    }
}
