import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import page_object_model.ForgotPasswordPage;
import page_object_model.HomePage;
import page_object_model.LoginPage;
import page_object_model.RegisterPage;
import pojo.User;

import static io.restassured.RestAssured.given;

public class LoginTest {

    private WebDriver driver;

    private User user;

    @Before
    public void initDriver() {
        driver = new ChromeDriver();
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
    @Step("Вход по кнопке «Войти в аккаунт» на главной")
    public void checkLoginFromEnterAccountButton() {
        driver.get("https://stellarburgers.nomoreparties.site/");
        HomePage homePage = new HomePage(driver);
        LoginPage loginPage = new LoginPage(driver);
        homePage.clickEnterAccountButton();
        loginPage.waitForLoadLoginPage();
        loginPage.setLoginField(user.getEmail(), user.getPassword());
        loginPage.clickLoginButton();
        homePage.waitForLoadHomePage();
    }

    @Test
    @Step("Вход через кнопку «Личный кабинет»")
    public void checkLoginFromOwnerCabinetLabel() {
        driver.get("https://stellarburgers.nomoreparties.site/");
        HomePage homePage = new HomePage(driver);
        LoginPage loginPage = new LoginPage(driver);
        homePage.clickOwnerCabinetLabel();
        loginPage.waitForLoadLoginPage();
        loginPage.setLoginField(user.getEmail(), user.getPassword());
        loginPage.clickLoginButton();
        homePage.waitForLoadHomePage();
    }

    @Test
    @Step("Вход через кнопку в форме регистрации")
    public void checkLoginFromEnterInRegistrationPage() {
        driver.get("https://stellarburgers.nomoreparties.site/register");
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
    @Step("Вход через кнопку в форме восстановления пароля")
    public void checkLoginFromEnterInForgotPasswordPage() {
        driver.get("https://stellarburgers.nomoreparties.site/forgot-password");
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
