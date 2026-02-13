package com.api.utils;

import com.api.request.model.*;
import com.github.javafaker.Faker;

import java.util.*;

public class FakerDataGenerator {

    private static Faker faker = new Faker(new Locale("en-IND"));
    private static Random RANDOM = new Random();
    private static final int MST_SERVICE_LOCATION_ID = 0;
    private static final int MST_PLATFORM_ID = 2;
    private static final int MST_WARRANTY_STATUS_ID = 1;
    private static final int MST_OEM_ID = 1;

    private static final int validProblemsId[] ={1,2,3,4,5,6,7,8,9,10,11,12,15,16,17,19,20,22,24,26,27,28,29};


    private FakerDataGenerator() {
    }

    public static CreateJobPayload generateFakeCreateJobData() {
        Customer customer = generateFakeCustomerData();
        CustomerAddress customerAddress = generateFakeCustomerAddressData();
        CustomerProduct customerProduct = generateFakeCustomerProductData();
        List<Problems> problemsList = generateFakeProblemList();
        CreateJobPayload createJobPayload = new CreateJobPayload(MST_SERVICE_LOCATION_ID, MST_PLATFORM_ID, MST_WARRANTY_STATUS_ID, MST_OEM_ID, customer, customerAddress, customerProduct, problemsList);
        return createJobPayload;
    }

    public static Iterator<CreateJobPayload> generateFakeCreateJobData(int count) {
        List<CreateJobPayload> payloadList = new ArrayList<>();
        for (int i = 0; i <= count; i++) {

            Customer customer = generateFakeCustomerData();
            CustomerAddress customerAddress = generateFakeCustomerAddressData();
            CustomerProduct customerProduct = generateFakeCustomerProductData();
            List<Problems> problemsList = generateFakeProblemList();
            CreateJobPayload createJobPayload = new CreateJobPayload(MST_SERVICE_LOCATION_ID, MST_PLATFORM_ID, MST_WARRANTY_STATUS_ID, MST_OEM_ID, customer, customerAddress, customerProduct, problemsList);
            payloadList.add(createJobPayload);
        }
        return payloadList.iterator();
    }


    private static List<Problems> generateFakeProblemList() {

        String fakerRemark = faker.lorem().sentence(5);


        int randomIndex = RANDOM.nextInt(validProblemsId.length);
        Problems problems = new Problems(validProblemsId[randomIndex], fakerRemark);
        System.out.println(problems);

        List<Problems> problemsList = new ArrayList<>();
        problemsList.add(problems);
        return problemsList;


    }

    private static CustomerProduct generateFakeCustomerProductData() {

        String dop = DateTimeUtil.getTimeWithDaysAgo(10);
        String serial_number = faker.numerify("###############");
        String popUrl = faker.internet().url();
        CustomerProduct customerProduct = new CustomerProduct(dop, serial_number, serial_number, serial_number, popUrl, 1, 1);
        return customerProduct;

    }

    private static CustomerAddress generateFakeCustomerAddressData() {

        String flat_number = faker.numerify("###");
        String apartment_name = faker.address().streetName();
        String landmark = faker.address().streetName();
        String streetName = faker.address().streetName();
        String area = faker.address().streetName();
        String pincode = faker.numerify("#####");
        String country = faker.address().country();
        String state = faker.address().state();


        CustomerAddress customerAddress = new CustomerAddress(flat_number, apartment_name, streetName, landmark, area, pincode, country, state);
        return customerAddress;


    }

    private static Customer generateFakeCustomerData() {

        String first_name = faker.name().firstName();
        String last_name = faker.name().lastName();
        String mobile_number = faker.numerify("74########");
        String mobile_number_alt = faker.numerify("74########");
        String email = faker.internet().emailAddress();
        String email_alt = faker.internet().emailAddress();

        Customer customer = new Customer(first_name, last_name, mobile_number, mobile_number_alt, email, email_alt);
        return customer;
    }
}



