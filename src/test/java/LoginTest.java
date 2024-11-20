import api.UserApi;
import builder.DriverBuilder;
import builder.UserBuilder;
import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pom.ForgotPasswordPage;
import pom.HomePage;
import pom.LoginPage;
import pom.RegisterPage;
import pojo.User;
import service.ConfigService;

import static enums.RouteEnum.FORGOT_PASSWORD;
import static enums.RouteEnum.REGISTER;
import static io.restassured.RestAssured.given;

public class LoginTest {

    private WebDriver driver;

    private User user;

    @Before
    public void initDriver() {
        driver = DriverBuilder.buildFromProperty();
    }

    @Before
    public void initUser() {
        RestAssured.baseURI = ConfigService.getMainUrl();
        user = UserBuilder.generateUser();
        UserApi.register(user);
    }

    @Test
    @DisplayName("Вход по кнопке «Войти в аккаунт» на главной")
    public void checkLoginFromEnterAccountButton() {
        HomePage homePage = new HomePage(driver);
        LoginPage loginPage = new LoginPage(driver);
        homePage.clickEnterAccountButton();
        loginPage.waitForLoadLoginPage();
        loginPage.setLoginField(user.getEmail(), user.getPassword());
        loginPage.clickLoginButton();
        homePage.waitForLoadHomePage();
    }

    @Test
    @DisplayName("Вход через кнопку «Личный кабинет»")
    public void checkLoginFromOwnerCabinetLabel() {
        HomePage homePage = new HomePage(driver);
        LoginPage loginPage = new LoginPage(driver);
        homePage.clickOwnerCabinetLabel();
        loginPage.waitForLoadLoginPage();
        loginPage.setLoginField(user.getEmail(), user.getPassword());
        loginPage.clickLoginButton();
        homePage.waitForLoadHomePage();
    }

    @Test
    @DisplayName("Вход через кнопку в форме регистрации")
    public void checkLoginFromEnterInRegistrationPage() {
        driver.get(driver.getCurrentUrl() + REGISTER.getValue());
        RegisterPage registerPage = new RegisterPage(driver);
        LoginPage loginPage = new LoginPage(driver);
        HomePage homePage = new HomePage(driver);
        registerPage.clickEnterButton();
        loginPage.waitForLoadLoginPage();
        loginPage.setLoginField(user.getEmail(), user.getPassword());
        loginPage.clickLoginButton();
        homePage.waitForLoadHomePage();
    }

    @Test
    @DisplayName("Вход через кнопку в форме восстановления пароля")
    public void checkLoginFromEnterInForgotPasswordPage() {
        driver.get(driver.getCurrentUrl() + FORGOT_PASSWORD.getValue());
        ForgotPasswordPage forgotPasswordPage = new ForgotPasswordPage(driver);
        LoginPage loginPage = new LoginPage(driver);
        HomePage homePage = new HomePage(driver);
        forgotPasswordPage.clickEnterButton();
        loginPage.waitForLoadLoginPage();
        loginPage.setLoginField(user.getEmail(), user.getPassword());
        loginPage.clickLoginButton();
        homePage.waitForLoadHomePage();
    }

    @After
    public void quitBrowser() {
        driver.quit();
    }

    @After
    public void deleteUserIfExists() {
        deleteUserIfExists(user);
    }

    private void deleteUserIfExists(User user) {
        UserApi.deleteUserIfExist(user);
    }

}
