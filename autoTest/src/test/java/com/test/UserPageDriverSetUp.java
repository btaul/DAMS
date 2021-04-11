package com.test;

import org.openqa.selenium.WebDriver;

public class UserPageDriverSetUp {


    //this is actually on userPage
    public static void clickRequestButton(WebDriver driver){
        UserPage userPage = new UserPage(driver);
        userPage.clickRequestItemButton();
    }
}
