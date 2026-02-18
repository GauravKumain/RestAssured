package com.api.utils;

import com.api.request.model.*;
import com.github.javafaker.Faker;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;

public class FakerDemoPractice {
    public static void main(String[] args) {
        Faker faker = new Faker(new Locale("en-IND"));


        String first_name = faker.name().firstName();
        String last_name = faker.name().lastName();
        String mobile_number = faker.numerify("74########");
        String mobile_number_alt = faker.numerify("74########");
        String email = faker.internet().emailAddress();
        String email_alt = faker.internet().emailAddress();

        Customer customer = new Customer(first_name,last_name,mobile_number,mobile_number_alt,email,email_alt);

        System.out.println(customer);

        String flat_number = faker.numerify("###");
        String apartment_name = faker.address().streetName();
        String landmark = faker.address().streetName();
        String streetName = faker.address().streetName();
        String area = faker.address().streetName();
        String pincode = faker.numerify("#####");
        String country = faker.address().country();
        String state = faker.address().state();


        CustomerAddress customerAddress = new CustomerAddress(flat_number,apartment_name,streetName,landmark,area,pincode,country,state);
        System.out.println(customerAddress);


        String dop = DateTimeUtil.getTimeWithDaysAgo(10);
        String serial_number =faker.numerify("###############");
        String popUrl = faker.internet().url();
        CustomerProduct customerProduct = new CustomerProduct(dop,serial_number,serial_number,serial_number,popUrl,1,1);
        System.out.println(customerProduct);

        String fakerRemark = faker.lorem().sentence(10);

        Random random = new Random();
        int id = random.nextInt(26)+1;
        Problems problems = new Problems(id,fakerRemark);
        System.out.println(problems);

        List<Problems> problemsList = new ArrayList<>();
        problemsList.add(problems);

        CreateJobPayload createJobPayload = new CreateJobPayload(0,2,1,1,customer,customerAddress,customerProduct,problemsList);


    }
}
