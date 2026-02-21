package com.api.utils;

import com.api.request.model.UserCredentials;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class JsonReaderUtil {

    public static <T> Iterator<T> loadJSON(String filename, Class<T[]> clazz) {

        InputStream inputStream= Thread.currentThread().getContextClassLoader().getResourceAsStream(filename);

        ObjectMapper objectMapper = new ObjectMapper();
       // UserCredentials userCredentials = objectMapper.readValue(inputStream, UserCredentials.class);
        //List userCredentials = objectMapper.readValue(inputStream, List.class);
        T[] userCredentialsArray;
        List<T> list= null;
        try {
            userCredentialsArray = objectMapper.readValue(inputStream, clazz);
            list = Arrays.asList(userCredentialsArray);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

       return list.iterator();


    }
}
