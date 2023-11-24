package com.mindzone.service.worksheets;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class CommonUtil {
    public Properties readProperties(){
        Properties prop = new Properties();
        try (InputStream input =  getClass().getClassLoader().getResourceAsStream("config.properties")) {

            prop.load(input);
        } catch (IOException io) {
            io.printStackTrace();
        }
        return prop;
    }
}
