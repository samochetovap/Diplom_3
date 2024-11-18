package builder;

import enums.DriverType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import service.ConfigService;

public class DriverBuilder {

    public static WebDriver buildFromProperty() {
        ConfigService configService = new ConfigService();
        if (configService.getDriverType().equals(DriverType.CHROME.name())) {
            WebDriver webDriver = new ChromeDriver();
            webDriver.get(configService.getMainUrl());
            return webDriver;
        } else if (configService.getDriverType().equals(DriverType.FIREFOX.name())) {
            WebDriver webDriver = new FirefoxDriver();
            webDriver.get(configService.getMainUrl());
            return webDriver;
        }
        return null;
    }

}
