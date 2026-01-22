package com.demo.csv;

import com.opencsv.CSVReader;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.exceptions.CsvException;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

public class ReadCSVFile_MapToPojo {

    public static void main(String[] args) throws IOException, CsvException {

        //File file = new File("C:\\Users\\HellRaiser\\IdeaProjects\\PhoenixTestAutomationFramework\\src\\main\\resources\\testData\\LoginTest.csv");
        //FileReader reader = new FileReader(file);


        InputStream file = Thread.currentThread().getContextClassLoader().getResourceAsStream("testData/LoginTest.csv");
        InputStreamReader reader = new InputStreamReader(file);
        CSVReader csvReader = new CSVReader(reader);

        CsvToBean<UserPojo> csvToBean = new CsvToBeanBuilder(csvReader)
                .withType(UserPojo.class)
                .withIgnoreEmptyLine(true)
                .build();

        List<UserPojo> userList =csvToBean.parse();
        System.out.println(userList);


    }
}
