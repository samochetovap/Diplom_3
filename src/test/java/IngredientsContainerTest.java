import builder.DriverBuilder;
import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import pom.HomePage;
import pojo.User;

import java.util.List;

public class IngredientsContainerTest {

    private WebDriver driver;

    private User user;

    @Before
    public void initDriver() {
        driver = DriverBuilder.buildFromProperty();
    }

    @Test
    @DisplayName("Тест переходов к разделу булки")
    public void ingredientsContainerBuns() {
        HomePage homePage = new HomePage(driver);
        WebElement webElement = homePage.clickBurgerIngredientsSectionsBuns();
        checkContainerElement(webElement);
    }

    @Test
    @DisplayName("Тест переходов к разделу соусы")
    public void ingredientsContainerSauces() {
        HomePage homePage = new HomePage(driver);
        WebElement webElement = homePage.clickBurgerIngredientsSectionsSauces();
        checkContainerElement(webElement);
    }

    @Test
    @DisplayName("Тест переходов к разделу начинки")
    public void ingredientsContainerFillings() {
        HomePage homePage = new HomePage(driver);
        WebElement webElement = homePage.clickBurgerIngredientsSectionsFillings();
        checkContainerElement(webElement);
    }

    private void checkContainerElement(WebElement webElement){
        String checkSelectedClassName = "tab_tab__1SPyG tab_tab_type_current__2BEPc pt-4 pr-10 pb-4 pl-10 noselect";
        Assert.assertEquals(checkSelectedClassName, webElement.getAttribute("class"));
    }

    @After
    public void quitBrowser() {
        driver.quit();
    }
}
