package com.test;

import com.test.BaseUrl;
import com.test.WebHomePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;


public class TestEventPage {

    private static final WebDriver driver = new ChromeDriver();

    @BeforeSuite
    public static void startup() {
        // ChromeDriver location set up in Utils class
        System.setProperty("webdriver.chrome.driver", BaseUrl.CHROME_DRIVER_LOCATION);
    }

    @Test(testName = "Test requestItem Button")
    public static void testRequestItem() throws InterruptedException {
        LoginDriverSetUp.loginSetup(driver);
        EventPage eventPage = new EventPage(driver);
        eventPage.clickRequestItem();
        assert driver.getCurrentUrl().equals("http://localhost:8084/list_users");

//        Thread.sleep(3000);

    }



    @AfterSuite
    public static void cleanUp(){
        driver.manage().deleteAllCookies();
        driver.close();
    }

}
