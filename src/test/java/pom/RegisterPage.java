package pom;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class RegisterPage {

    private final WebDriver driver;
    private final JavascriptExecutor executor;

    private final By nameInputField = By.xpath(".//fieldset[@class = 'Auth_fieldset__1QzWN mb-6'][1]/div/div/input");
    private final By emailInputField = By.xpath(".//fieldset[@class = 'Auth_fieldset__1QzWN mb-6'][2]/div/div/input");
    private final By passwordInputField = By.xpath(".//fieldset[@class = 'Auth_fieldset__1QzWN mb-6'][3]/div/div/input");
    private final By registrationButton = By.xpath(".//form[@class = 'Auth_form__3qKeq mb-20']/button");
    private final By notAvailablePasswordWarning = By.xpath(".//fieldset[@class = 'Auth_fieldset__1QzWN mb-6'][3]/div/p");
    private final By enterButton = By.xpath(".//a[@class = 'Auth_link__1fOlj']");

    public RegisterPage(WebDriver driver) {
        this.driver = driver;
        this.executor = (JavascriptExecutor) driver;
    }
    @Step("Клик на кнопку войти")
    public void clickEnterButton() {
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", driver.findElement(enterButton));
    }
    @Step("Ввод в поле имя")
    public void setNameInputField(String name) {
        driver.findElement(nameInputField).sendKeys(name);
    }
    @Step("Ввод в поле email")
    public void setEmailInputField(String email) {
        driver.findElement(emailInputField).sendKeys(email);
    }
    @Step("Ввод в поле пароль")
    public void setPasswordInputField(String password) {
        driver.findElement(passwordInputField).sendKeys(password);
    }
    @Step("Зарегистрироваться")
    public void clickRegistrationButton() {
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", driver.findElement(registrationButton));
    }

    @Step("Ожидание предупреждения о невалидном пароле")
    public void waitForNotAvailablePasswordWarning() {
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(
                ExpectedConditions.visibilityOf(driver.findElement(notAvailablePasswordWarning)));
    }

}
