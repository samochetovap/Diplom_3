package service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ConfigService {
    private static final Properties properties = new Properties();;

    public ConfigService() {
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

    public String getDriverType() {
        return properties.getProperty("browser");
    }

    public String getMainUrl() {
        return properties.getProperty("mainUrl");
    }

}
