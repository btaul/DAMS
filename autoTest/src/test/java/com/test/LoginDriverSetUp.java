package com.test;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;


public class LoginDriverSetUp {

    public static void loginSetup(WebDriver driver, String username, String password){
        HomePageDriverSetUp.loginButton(driver);
//        WebHomePage webHomePage = new WebHomePage(driver);
//        driver.get(BaseUrl.BASE_URL);
//        webHomePage.clickLogin();
//        assert driver.getCurrentUrl().equals("http://localhost:8084/login");

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        LoginPage loginPage = new LoginPage(driver);
        loginPage.sendUsername(username);
        loginPage.sendPassword(password);
        loginPage.clickSignIn();
//        assert driver.getCurrentUrl().equals("http://localhost:8084/list_events");

    }
}
