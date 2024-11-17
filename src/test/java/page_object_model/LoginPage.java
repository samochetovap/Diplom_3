package page_object_model;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {

    private final WebDriver driver;
    private final JavascriptExecutor executor;

    private final By emailInputField = By.xpath(".//div[@class = 'Auth_login__3hAey']/form/fieldset[1]/div/div/input");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.executor = (JavascriptExecutor) driver;
    }

    public void waitForLoadLoginPage() {
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(
                ExpectedConditions.visibilityOf(driver.findElement(emailInputField)));
    }

}
