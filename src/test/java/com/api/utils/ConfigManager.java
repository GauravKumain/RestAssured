package com.api.utils;

import java.io.*;
import java.util.Locale;
import java.util.Properties;

public class ConfigManager {

    public static Properties prop = new Properties();
    private static String path = "config/config.properties";
    private static String env;

    private ConfigManager()
    {

    }

    static{
        env = System.getProperty("env","qa");
        env = env.toLowerCase().trim();

        switch(env){

            case "dev" -> path ="config/config.dev.properties";


            case "qa" -> path ="config/config.qa.properties";

            case "uat " ->
                path ="config/config.uat.properties";

            default -> path ="config/config.qa.properties";



        }

        //static block execute only once during class loading time so in memory property file will be loaded once
        InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(path);

        if(inputStream == null)
        {
            throw new RuntimeException("File not present at specified path" + path);

        }

        try {

            prop.load(inputStream);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
         catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static String getProperty(String key){
        return prop.getProperty(key);
    }
}
