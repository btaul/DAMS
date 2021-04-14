package com.test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;


public class TestRegisterPage2 {


    private static final WebDriver driver = new ChromeDriver();

    @BeforeSuite
    public static void startup() {
        // ChromeDriver location set up in Utils class
        System.setProperty("webdriver.chrome.driver", BaseUrl.CHROME_DRIVER_LOCATION);

    }


    @Test(testName = "Test Register for a recipient")
    public static void testRegisterRecipient() throws InterruptedException {

        HomePageDriverSetUp.registerButton(driver);

        UserProfileSettingAndRegister.setupRegisterProfile(driver, "testRecipient1", "recipient", 52246,
                "Abc123!", "What is the brand of your first car?","civic",
                "What is your hometown?", "Zhengzhou");

        assert driver.getCurrentUrl().equals("http://localhost:8084/register");

//        Thread.sleep(3000);

    }

    @Test(testName = "Test Register for a donor")
    public static void testRegisterDonor() throws InterruptedException {

        HomePageDriverSetUp.registerButton(driver);

        UserProfileSettingAndRegister.setupRegisterProfile(driver, "testDonor1", "donor", 52246,
                "Abc123!", "What is your favorite vacation spot?","beach",
                "What is the name of your first pet?", "pet");

        assert driver.getCurrentUrl().equals("http://localhost:8084/register");

//        Thread.sleep(3000);

    }


    @AfterSuite
    public static void cleanUp(){
        driver.manage().deleteAllCookies();
        driver.close();
    }
}
