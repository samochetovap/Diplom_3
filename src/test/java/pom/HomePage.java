package pom;

import io.qameta.allure.Step;
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

    private final By burgerIngredientsSectionsBuns = By.xpath(".//main/section[@class = 'BurgerIngredients_ingredients__1N8v2']/div/div[1]");
    private final By burgerIngredientsSectionsSauces = By.xpath(".//main/section[@class = 'BurgerIngredients_ingredients__1N8v2']/div/div[2]");
    private final By burgerIngredientsSectionsFillings = By.xpath(".//main/section[@class = 'BurgerIngredients_ingredients__1N8v2']/div/div[3]");

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Клик на личный кабинет")
    public void clickOwnerCabinetLabel() {
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", driver.findElement(ownerCabinetLabel));
    }

    @Step("Клик на кнопку вход в аккаунт")
    public void clickEnterAccountButton() {
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", driver.findElement(enterAccountButton));
    }

    @Step("Ожидания загрузки домашней страницы")
    public void waitForLoadHomePage() {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(
                ExpectedConditions.visibilityOf(driver.findElement(burgerIngredientsSection)));
    }

    @Step("Клик раздел булки")
    public WebElement clickBurgerIngredientsSectionsBuns(){
        WebElement webElement = driver.findElement(burgerIngredientsSectionsBuns);
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();",webElement);
        return webElement;
    }

    @Step("Клик раздел соусы")
    public WebElement clickBurgerIngredientsSectionsSauces(){
        WebElement webElement = driver.findElement(burgerIngredientsSectionsSauces);
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", driver.findElement(burgerIngredientsSectionsSauces));
        return webElement;
    }

    @Step("Клик раздел начинки")
    public WebElement clickBurgerIngredientsSectionsFillings(){
        WebElement webElement = driver.findElement(burgerIngredientsSectionsFillings);
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", driver.findElement(burgerIngredientsSectionsFillings));
        return webElement;
    }

}
