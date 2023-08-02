package org.example.StreamIO;

import java.io.FileOutputStream;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {

        Config config1 = new Config();

        if (args != null) {
            String employeeArgs = args[0];
            String[] array1 = employeeArgs.split("=");
            String key = array1[0];
            String value = array1[1];

            if (config1.getProperty().getProperty(key) != null ) {
                config1.getProperty().replace(key, value);
                try(FileOutputStream file = new FileOutputStream(config1.getFileName())) {
                    config1.getProperty().store(file, null);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
          }
        }

//        config1.getReader().print(config1.getProperty());


        for (String args1 : args) {
            System.out.println("args:  " + args1);
        }


    }
}
