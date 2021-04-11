package com.test;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;


public class HomePageDriverSetUp {



    public static void registerButton(WebDriver driver){
        WebHomePage webHomePage = new WebHomePage(driver);
        driver.get(BaseUrl.BASE_URL);
        webHomePage.clickRegister();
//        assert driver.getCurrentUrl().equals("http://localhost:8084/login");
    }

    public static void loginButton(WebDriver driver){
        WebHomePage webHomePage = new WebHomePage(driver);
        driver.get(BaseUrl.BASE_URL);
        webHomePage.clickLogin();
//        assert driver.getCurrentUrl().equals("http://localhost:8084/login");
    }

    public static void listAllEventsButton(WebDriver driver){
        WebHomePage webHomePage = new WebHomePage(driver);
        driver.get(BaseUrl.BASE_URL);
        webHomePage.clickListAllEvents();
//        assert driver.getCurrentUrl().equals("http://localhost:8084/login");
    }







}
