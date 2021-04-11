package com.test;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;


public class LoginDriverSetUp {

    public static void loginSetup(WebDriver driver){
        HomePageDriverSetUp.loginButton(driver);
//        WebHomePage webHomePage = new WebHomePage(driver);
//        driver.get(BaseUrl.BASE_URL);
//        webHomePage.clickLogin();
//        assert driver.getCurrentUrl().equals("http://localhost:8084/login");

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        LoginPage loginPage = new LoginPage(driver);
        loginPage.sendUsername("testReceipient1");
        loginPage.sendPassword("Abc123!");
        loginPage.clickSignIn();
//        assert driver.getCurrentUrl().equals("http://localhost:8084/list_events");

    }
}
