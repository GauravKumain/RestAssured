package com.demo.csv;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

import java.io.*;
import java.util.List;

public class ReadCSVFile {

    public static void main(String[] args) throws IOException, CsvException {

        //File file = new File("C:\\Users\\HellRaiser\\IdeaProjects\\PhoenixTestAutomationFramework\\src\\main\\resources\\testData\\LoginTest.csv");
        //FileReader reader = new FileReader(file);


        InputStream file = Thread.currentThread().getContextClassLoader().getResourceAsStream("testData/LoginTest.csv");
        InputStreamReader reader = new InputStreamReader(file);
        CSVReader csvReader = new CSVReader(reader);
        List<String[]> dataList = csvReader.readAll();

        for (String[] dataArray : dataList) {

            System.out.print(dataArray[0]+" ");
            System.out.println(dataArray[1]);

        }
    }
}
