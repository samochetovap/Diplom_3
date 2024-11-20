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
import pom.AccountProfilePage;
import pom.HeaderPage;
import pom.HomePage;
import pom.LoginPage;
import pojo.User;
import service.ConfigService;

import static io.restassured.RestAssured.given;

public class CabinetTest {

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
    @DisplayName("Переход в личный кабинет по клику на «Личный кабинет»")
    public void routeToOwnerCabinet() {
        //логин
        HomePage homePage = new HomePage(driver);
        LoginPage loginPage = new LoginPage(driver);
        AccountProfilePage accountProfilePage = new AccountProfilePage(driver);
        homePage.clickOwnerCabinetLabel();
        loginPage.waitForLoadLoginPage();
        loginPage.setLoginField(user.getEmail(), user.getPassword());
        loginPage.clickLoginButton();
        homePage.waitForLoadHomePage();
        //переход в личный кабинет
        homePage.clickOwnerCabinetLabel();
        accountProfilePage.waitForLoadAccountProfileData();
    }

    @Test
    @DisplayName("Переход из личного кабинета в конструктор по клику на «Конструктор»")
    public void routeToMainPageFromCabinetOnConstructorClick() {
        //логин
        HomePage homePage = new HomePage(driver);
        LoginPage loginPage = new LoginPage(driver);
        HeaderPage headerPage = new HeaderPage(driver);
        AccountProfilePage accountProfilePage = new AccountProfilePage(driver);
        homePage.clickOwnerCabinetLabel();
        loginPage.waitForLoadLoginPage();
        loginPage.setLoginField(user.getEmail(), user.getPassword());
        loginPage.clickLoginButton();
        homePage.waitForLoadHomePage();
        //переход в личный кабинет
        homePage.clickOwnerCabinetLabel();
        accountProfilePage.waitForLoadAccountProfileData();
        //кликаем на конструктор в хедере
        headerPage.clickConstructorLabel();
        homePage.waitForLoadHomePage();
    }

    @Test
    @DisplayName("Выход по кнопке «Выйти» в личном кабинете.")
    public void exitFromCabinet() {
        //логин
        HomePage homePage = new HomePage(driver);
        LoginPage loginPage = new LoginPage(driver);
        AccountProfilePage accountProfilePage = new AccountProfilePage(driver);
        homePage.clickOwnerCabinetLabel();
        loginPage.waitForLoadLoginPage();
        loginPage.setLoginField(user.getEmail(), user.getPassword());
        loginPage.clickLoginButton();
        homePage.waitForLoadHomePage();
        //переход в личный кабинет
        homePage.clickOwnerCabinetLabel();
        accountProfilePage.waitForLoadAccountProfileData();
        accountProfilePage.clickExitButton();
        //выкинуло в окно логина
        loginPage.waitForLoadLoginPage();
    }

    @Test
    @DisplayName("Переход из личного кабинета в конструктор по клику на логотип Stellar Burgers")
    public void routeToMainPageFromCabinetOnStellarBurgersClick() {
        //логин
        HomePage homePage = new HomePage(driver);
        LoginPage loginPage = new LoginPage(driver);
        HeaderPage headerPage = new HeaderPage(driver);
        AccountProfilePage accountProfilePage = new AccountProfilePage(driver);
        homePage.clickOwnerCabinetLabel();
        loginPage.waitForLoadLoginPage();
        loginPage.setLoginField(user.getEmail(), user.getPassword());
        loginPage.clickLoginButton();
        homePage.waitForLoadHomePage();
        //переход в личный кабинет
        homePage.clickOwnerCabinetLabel();
        accountProfilePage.waitForLoadAccountProfileData();
        //кликаем на Stellar Burger в хедере
        headerPage.clickStellarBurgersLogotype();
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
