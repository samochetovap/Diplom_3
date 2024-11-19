import api.UserApi;
import builder.DriverBuilder;
import builder.UserBuilder;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import pojo.User;
import pom.LoginPage;
import pom.RegisterPage;
import service.ConfigService;

import static enums.RouteEnum.REGISTER;


public class RegistrationTest {

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
    }

    @Test
    @DisplayName("Успешная регистрация пользователя которого нет в системе")
    public void checkTextExpansion() {
        driver.get(driver.getCurrentUrl() + REGISTER.getValue());
        LoginPage loginPage = new LoginPage(driver);
        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.setNameInputField(user.getName());
        registerPage.setPasswordInputField(user.getPassword());
        registerPage.setEmailInputField(user.getEmail());
        registerPage.clickRegistrationButton();
        loginPage.waitForLoadLoginPage();
        UserApi.login(user).then().statusCode(200);
    }

    @Test
    @DisplayName("Проверка некорректного пароля. Минимальный пароль — шесть символов.")
    public void checkNotAvailablePassword() {
        driver.get(driver.getCurrentUrl() + REGISTER.getValue());
        RegisterPage registerPage = new RegisterPage(driver);
        user.setPassword("12345");
        registerPage.setNameInputField(user.getName());
        registerPage.setPasswordInputField(user.getPassword());
        registerPage.setEmailInputField(user.getEmail());
        registerPage.clickRegistrationButton();
        registerPage.waitForNotAvailablePasswordWarning();
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
