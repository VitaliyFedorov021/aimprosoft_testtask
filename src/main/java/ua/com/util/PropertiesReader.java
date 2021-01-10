package ua.com.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesReader {
    public static Properties readProperties(String name) {
        Properties properties = new Properties();
        try (InputStream inputStream = PropertiesReader.class.getClassLoader().getResourceAsStream(name)) {
            properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties;
    }
}
