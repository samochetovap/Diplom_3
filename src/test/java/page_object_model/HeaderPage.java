package page_object_model;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HeaderPage {

    private final WebDriver driver;

    private final By constructorLabel = By.xpath(".//p[@class = 'AppHeader_header__linkText__3q_va ml-2']");

    private final By stellarBurgersLogotype = By.xpath(".//div[@class = 'AppHeader_header__logo__2D0X2']");


    public HeaderPage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickConstructorLabel() {
        driver.findElement(constructorLabel).click();
    }

    public void clickStellarBurgersLogotype() {
        driver.findElement(stellarBurgersLogotype).click();
    }

}
