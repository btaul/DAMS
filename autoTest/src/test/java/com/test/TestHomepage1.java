package com.test;

import com.test.BaseUrl;
import com.test.WebHomePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;


public class TestHomepage1 {

    private static final WebDriver driver = new ChromeDriver();

    @BeforeSuite
    public static void startup() {
        // ChromeDriver location set up in Utils class
        System.setProperty("webdriver.chrome.driver", BaseUrl.CHROME_DRIVER_LOCATION);
    }

    @Test(testName = "Test Login Button")
    public static void testLoginButton(){
        HomePageDriverSetUp.loginButton(driver);
        assert driver.getCurrentUrl().equals("http://localhost:8084/login");
    }

    @Test(testName = "Test Register Button")
    public static void testRegisterButton(){
        HomePageDriverSetUp.registerButton(driver);
        assert driver.getCurrentUrl().equals("http://localhost:8084/register");
    }

    @Test(testName = "Test ListAllEvents Button")
    public static void testListAllUsersButton(){
        HomePageDriverSetUp.listAllEventsButton(driver);
        assert driver.getCurrentUrl().equals("http://localhost:8084/list_events");
    }



    @AfterSuite
    public static void cleanUp(){
        driver.manage().deleteAllCookies();
        driver.close();
    }

}
