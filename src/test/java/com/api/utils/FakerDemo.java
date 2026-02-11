package com.api.utils;

import com.github.javafaker.Faker;

import java.util.Locale;

public class FakerDemo {
    public static void main(String[] args) {
        Faker faker = new Faker(new Locale("en-IND"));
        System.out.println(faker.name().firstName());
        System.out.println(faker.address().city());
        System.out.println(faker.address().buildingNumber());
        System.out.println(faker.country().capital());
        System.out.println(faker.numerify("540####"));
        System.out.println(faker.numerify("540####"));
        System.out.println(faker.numerify("540####"));

    }
}
