package com.test;

import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;

public class RequestDriverSetUp {

    public static void requestSetUp(WebDriver driver){
        LoginDriverSetUp.loginSetup(driver);
        EventPage eventPage = new EventPage(driver);
        eventPage.clickRequestItemLink();
        assert driver.getCurrentUrl().equals("http://localhost:8084/list_users");

    }
}
