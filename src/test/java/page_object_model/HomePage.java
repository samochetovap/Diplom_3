package page_object_model;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class HomePage {

    private final WebDriver driver;

    private final By ownerCabinetLabel = By.xpath(".//header/nav/a[@class = 'AppHeader_header__link__3D_hX']");

    private final By enterAccountButton = By.xpath(".//button[@class = 'button_button__33qZ0 button_button_type_primary__1O7Bx button_button_size_large__G21Vg']");

    private final By burgerIngredientsSection = By.xpath(".//section[@class = 'BurgerIngredients_ingredients__1N8v2']");

    private final By burgerIngredientsSectionsBuns = By.xpath(".//main/section[@class = 'BurgerIngredients_ingredients__1N8v2']/div/div");

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickOwnerCabinetLabel() {
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", driver.findElement(ownerCabinetLabel));
    }

    public void clickEnterAccountButton() {
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", driver.findElement(enterAccountButton));
    }

    public void waitForLoadHomePage() {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(
                ExpectedConditions.visibilityOf(driver.findElement(burgerIngredientsSection)));
    }

    public List<WebElement> getBurgerIngredientsSections(){
        return driver.findElements(burgerIngredientsSectionsBuns);
    }

}
