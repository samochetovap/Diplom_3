import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import page_object_model.AccountProfilePage;
import page_object_model.HeaderPage;
import page_object_model.HomePage;
import page_object_model.LoginPage;
import pojo.User;

import static io.restassured.RestAssured.given;

public class CabinetTest {

    private WebDriver driver;

    private User user;

    @Before
    public void initDriver() {
        driver = new ChromeDriver();
        driver.get("https://stellarburgers.nomoreparties.site/register");
    }

    @Before
    @Step("Создать и зарегестрировать тестового пользователя")
    public void initAndRegistrationUser() {
        RestAssured.baseURI = "https://stellarburgers.nomoreparties.site/";
        user = new User("some12332112332111@gmail.com", "password12345", "some12332112332111@gmail.com");
        given().contentType(ContentType.JSON)
                .and().body(user).and()
                .post("/api/auth/register");
    }

    @Test
    @Step("Переход в личный кабинет по клику на «Личный кабинет»")
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
    @Step("Переход из личного кабинета в конструктор по клику на «Конструктор»")
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
    @Step("Выход по кнопке «Выйти» в личном кабинете.")
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
    @Step("Переход из личного кабинета в конструктор по клику на логотип Stellar Burgers")
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

    @Step("Удалить тестового пользователя если зарегестрирован")
    private void deleteUserIfExists(User user) {
        Response responseLogin = given()
                .contentType(ContentType.JSON)
                .and().body(user)
                .and().post("/api/auth/login");
        if (responseLogin.statusCode() == 200) {
            String accessToken = responseLogin.then().extract().body().path("accessToken").toString();
            given().header("authorization", accessToken).contentType(ContentType.JSON)
                    .and().delete("/api/auth/user");
        }
    }
}
