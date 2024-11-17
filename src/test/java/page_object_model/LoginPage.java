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

    private final By passwordInputField = By.xpath(".//div[@class = 'Auth_login__3hAey']/form/fieldset[2]/div/div/input");

    private final By loginButton = By.xpath(".//div[@class = 'Auth_login__3hAey']/form/button[@class = 'button_button__33qZ0 button_button_type_primary__1O7Bx button_button_size_medium__3zxIa']");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.executor = (JavascriptExecutor) driver;
    }

    public void waitForLoadLoginPage() {
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(
                ExpectedConditions.visibilityOf(driver.findElement(emailInputField)));
    }

    public void setLoginField(String email, String password) {
        driver.findElement(emailInputField).sendKeys(email);
        driver.findElement(passwordInputField).sendKeys(password);
    }

    public void clickLoginButton(){
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(
            ExpectedConditions.visibilityOfAllElementsLocatedBy(loginButton)).get(0).click();
    }

}
