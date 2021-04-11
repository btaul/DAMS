package com.test;

import org.openqa.selenium.WebDriver;

public class EventPageDriverSetUp {

    public static void clickRequestLink(WebDriver driver){

        EventPage eventPage = new EventPage(driver);
        eventPage.clickRequestItemLink();
//        assert driver.getCurrentUrl().equals("http://localhost:8084/list_users");

    }


    public static void clickPledgeLink(WebDriver driver){

        EventPage eventPage = new EventPage(driver);
        eventPage.clickPledgeLink();

    }


}
