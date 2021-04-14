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

    public static void clickResponseLink(WebDriver driver){
        EventPage eventPage = new EventPage(driver);
        eventPage.clickResponseLink();
    }

    public static void clickDonationLink(WebDriver driver){
        EventPage eventPage = new EventPage(driver);
        eventPage.clickDonationLink();
    }

    public static void clickCreateEventLink(WebDriver driver){
        EventPage eventPage = new EventPage(driver);
        eventPage.clickCreateEventLink();
    }


}
