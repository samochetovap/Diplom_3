package builder;

import enums.DriverType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import service.ConfigService;

public class DriverBuilder {

    public static WebDriver buildFromProperty() {
        if (ConfigService.getDriverType().equals(DriverType.CHROME.name())) {
            WebDriver webDriver = new ChromeDriver();
            webDriver.get(ConfigService.getMainUrl());
            return webDriver;
        } else if (ConfigService.getDriverType().equals(DriverType.FIREFOX.name())) {
            WebDriver webDriver = new FirefoxDriver();
            webDriver.get(ConfigService.getMainUrl());
            return webDriver;
        }else{
            throw new RuntimeException("Cannot bouild driver");
        }
    }

}
