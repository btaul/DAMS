package com.test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;


public class TestRequestItemPage6 {

    private static final WebDriver driver = new ChromeDriver();

    @BeforeSuite
    public static void startup() {
        // ChromeDriver location set up in Utils class
        System.setProperty("webdriver.chrome.driver", BaseUrl.CHROME_DRIVER_LOCATION);
    }

    @Test(testName = "Test request item form")
    public static void testRequestItem() throws InterruptedException {
        HomePageDriverSetUp.registerButton(driver);

        String username = "testRecipient1";
        String password = "Abc123!";

        UserProfileSetting.setupRegisterProfile(driver, username, "recipient", 52246,
                password, "What is the brand of your first car?","civic",
                "What is your hometown?", "Zhengzhou");

        LoginDriverSetUp.loginSetup(driver, username, password);

        EventPageDriverSetUp.clickRequestLink(driver);

        UserPageDriverSetUp.clickRequestButton(driver);

        RequestItemPage requestItemPage = new RequestItemPage(driver);
        requestItemPage.sendEventsID("1");
        requestItemPage.sendItem("apple");
        requestItemPage.sendVolume(50);
        requestItemPage.clickSubmitRequestButton();
        assert driver.getCurrentUrl().equals("http://localhost:8084/request_items");



//        Thread.sleep(3000);

    }



    @AfterSuite
    public static void cleanUp(){
        driver.manage().deleteAllCookies();
        driver.close();
    }

}
