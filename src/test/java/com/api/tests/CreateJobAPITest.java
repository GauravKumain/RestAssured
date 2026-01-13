package com.api.tests;

import static com.api.constant.Role.*;
import static io.restassured.RestAssured.*;

import com.api.constant.Role;
import com.api.pojo.*;
import com.api.utils.AuthTokenProvider;
import com.api.utils.ConfigManager;
import com.api.utils.SpecUtil;
import io.restassured.http.ContentType;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;

public class CreateJobAPITest {


    @Test
    public void createJobAPITest()
    {

        Customer customer = new Customer("Gaurav","Kumain","8787474487","","qwssww@gmail.com","");
        CustomerAddress customerAddress = new CustomerAddress("101","raja","aaawaj","rana","badrish","12212","India","uttarakhand");
        CustomerProduct customerProduct = new CustomerProduct("2025-04-06T18:30:00.000Z","16572885284302","16572885284302","16572885284302","2025-04-06T18:30:00.000Z",1,1);
        Problems problems = new Problems(1,"Battery Issue");
        Problems[] problemsArray = new Problems[1];
        problemsArray[0] = problems;

        CreateJobPayload createJobPayload = new CreateJobPayload(0,2,1,1,customer,customerAddress,customerProduct,problemsArray);


        given()
                .spec(SpecUtil.requestSpecWithAuth(FD,createJobPayload))
                .when()
                .post("/job/create")
                .then()
                .spec(SpecUtil.responseSpec_OK());
    }
}
