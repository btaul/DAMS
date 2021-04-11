package com.test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;


public class TestLoginPage3 {
    private static final WebDriver driver = new ChromeDriver();

    @BeforeSuite
    public static void startup() {
        // ChromeDriver location set up in Utils class
        System.setProperty("webdriver.chrome.driver", BaseUrl.CHROME_DRIVER_LOCATION);

    }




    @Test(testName = "Test Login Process")
    public static void testLogin() throws InterruptedException {
        HomePageDriverSetUp.registerButton(driver);

        String username = "testRecipient1";
        String password = "Abc123!";

        UserProfileSetting.setupRegisterProfile(driver, username, "recipient", 52246,
                password, "What is the brand of your first car?","civic",
                "What is your hometown?", "Zhengzhou");


        LoginDriverSetUp.loginSetup(driver, username, password);
        assert driver.getCurrentUrl().equals("http://localhost:8084/list_events");
//        Thread.sleep(3000);
    }






    @AfterSuite
    public static void cleanUp(){
        driver.manage().deleteAllCookies();
        driver.close();
    }



}
