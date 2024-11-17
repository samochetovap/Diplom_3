import io.qameta.allure.Step;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import page_object_model.HomePage;
import pojo.User;

import java.util.List;

public class IngredientsContainerTest {

    private WebDriver driver;

    private User user;

    @Before
    public void initDriver() {
        driver = new ChromeDriver();
        driver.get("https://stellarburgers.nomoreparties.site/");
    }

    @Test
    @Step("Тест переходов к разделам: «Булки», «Соусы», «Начинки».")
    public void ingredientsContainerRouteTest() {
        HomePage homePage = new HomePage(driver);
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        String checkSelectedClassName = "tab_tab__1SPyG tab_tab_type_current__2BEPc pt-4 pr-10 pb-4 pl-10 noselect";

        List<WebElement> ingredientsLabels = homePage.getBurgerIngredientsSections();
        ingredientsLabels.forEach(webElement -> {
            executor.executeScript("arguments[0].click();", webElement);
            Assert.assertEquals(checkSelectedClassName, webElement.getAttribute("class"));
        });
    }

    @After
    public void quitBrowser() {
        driver.quit();
    }
}
