package com.pickcomplete.main.properties;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesClass {

    private static final Properties properties = new Properties();

    static {
        try (InputStream input = PropertiesClass.class.getClassLoader().getResourceAsStream("config.properties")) {
            if (input == null) {
                System.out.println("Sorry, config was not found");
            }

            properties.load(input);

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static String getUrlServerEvents() {
        return properties.getProperty("URL_SERVER_EVENTS");
    }

}
