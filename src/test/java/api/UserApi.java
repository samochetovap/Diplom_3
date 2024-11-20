package api;

import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import pojo.User;
import service.ConfigService;

import static config.ApiPath.*;
import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.responseSpecification;

public class UserApi {

    public UserApi() {
        ConfigService configService = new ConfigService();
        RestAssured.baseURI = configService.getMainUrl();
    }

    @Step("Логин")
    public static Response login(User user){
        return  given()
                .contentType(ContentType.JSON)
                .and().body(user)
                .and().post(LOGIN);
    }

    @Step("Регистрация пользователя через апи")
    public static Response register(User user){
        return  given().contentType(ContentType.JSON)
                .and().body(user).and()
                .post(REGISTER);
    }

    @Step("Удаление зарегистрированного пользователя")
    public static void deleteUser(Response loginResponse){
        if (loginResponse.statusCode() == 200) {
            String accessToken = loginResponse.then().extract().body().path("accessToken").toString();
            given().header("authorization", accessToken).contentType(ContentType.JSON)
                    .and().delete(AUTH);
        }
    }

    @Step("Попытка удаления пользователя если он есть в системе")
    public static void deleteUserIfExist(User user){
        Response deleteResponse = login(user);
        deleteUser(deleteResponse);
    }
}
