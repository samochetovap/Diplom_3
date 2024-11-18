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
import page_object_model.LoginPage;
import page_object_model.RegisterPage;
import pojo.User;

import static io.restassured.RestAssured.given;


public class RegistrationTest {

    private WebDriver driver;

    private User user;

    @Before
    public void initDriver() {
        driver = new ChromeDriver();
        driver.get("https://stellarburgers.nomoreparties.site/register");
    }

    @Before
    public void initUser() {
        RestAssured.baseURI = "https://stellarburgers.nomoreparties.site/";
        user = new User("some12332112332111@gmail.com", "somepassword12345", "someUserName");

    }

    @Test
    @Step("Успешная регистрация пользователя которого нет в системе")
    public void checkTextExpansion() {
        LoginPage loginPage = new LoginPage(driver);
        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.setNameInputField(user.getName());
        registerPage.setPasswordInputField(user.getPassword());
        registerPage.setEmailInputField(user.getEmail());
        registerPage.clickRegistrationButton();
        loginPage.waitForLoadLoginPage();
        given().contentType(ContentType.JSON)
                .and().body(user).and()
                .post("/api/auth/login").then().statusCode(200);
    }

    @Test
    @Step("Проверка некорректного пароля. Минимальный пароль — шесть символов.")
    public void checkNotAvailablePassword() {
        RegisterPage registerPage = new RegisterPage(driver);
        user.setPassword("12345");
        registerPage.setNameInputField(user.getName());
        registerPage.setPasswordInputField(user.getPassword());
        registerPage.setEmailInputField(user.getEmail());
        registerPage.clickRegistrationButton();
        registerPage.waitForNotAvailablePasswordWarning();
        given().contentType(ContentType.JSON)
                .and().body(user).and()
                .post("/api/auth/login").then().statusCode(401);
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
