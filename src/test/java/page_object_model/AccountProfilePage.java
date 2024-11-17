package page_object_model;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AccountProfilePage {


    private final WebDriver driver;

    private final By accountProfileData = By.xpath(".//div[@class = 'Account_account__vgk_w']");
    private final By exitButton = By.xpath(".//button[@class = 'Account_button__14Yp3 text text_type_main-medium text_color_inactive']");

    public AccountProfilePage(WebDriver driver) {
        this.driver = driver;
    }

    public void waitForLoadAccountProfileData() {
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(
                ExpectedConditions.visibilityOf(driver.findElement(accountProfileData)));
    }

    public void clickExitButton() {
        driver.findElement(exitButton).click();
    }
}
