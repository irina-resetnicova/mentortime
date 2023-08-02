package org.example.StreamIO;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Reader {

    public Properties read(String path) {

        try (InputStream input = new FileInputStream(path)) {
            Properties properties = new Properties();
            properties.load(input);
            return properties;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public void updateProperties(String property, String value) {
    }

    public void write(Properties properties){

    }

    public void print(Properties properties) {
        properties.forEach((k, v) -> {
            System.out.println(k + ":" + v);
        });

    }
}
