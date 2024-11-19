package service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ConfigService {
    private static final Properties properties = new Properties();;

    public static String getDriverType() {
        readProp();
        return properties.getProperty("browser");
    }

    public static String getMainUrl() {
        readProp();
        return properties.getProperty("mainUrl");
    }

    private static void readProp(){
        BufferedReader bufferedReader;
        try {
            String propPath = "src/test/resources/application.properties";
            bufferedReader = new BufferedReader(new FileReader(propPath));
            properties.load(bufferedReader);
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
