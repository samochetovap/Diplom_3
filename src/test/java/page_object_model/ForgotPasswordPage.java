package page_object_model;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ForgotPasswordPage {

    private final WebDriver driver;

    private final By enterButton = By.xpath(".//a[@class = 'Auth_link__1fOlj']");

    public ForgotPasswordPage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickEnterButton() {
        driver.findElement(enterButton).click();
    }
}
