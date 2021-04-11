package com.test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;


public class TestEventPage4 {

    private static final WebDriver driver = new ChromeDriver();

    @BeforeSuite
    public static void startup() {
        // ChromeDriver location set up in Utils class
        System.setProperty("webdriver.chrome.driver", BaseUrl.CHROME_DRIVER_LOCATION);
    }


    @Test(testName = "Test requestItem Link")
    public static void testRequestItemLink() throws InterruptedException {

        HomePageDriverSetUp.registerButton(driver);

        String username = "testRecipient1";
        String password = "Abc123!";

        UserProfileSetting.setupRegisterProfile(driver, username, "recipient", 52246,
                password, "What is the brand of your first car?","civic",
                "What is your hometown?", "Zhengzhou");

        LoginDriverSetUp.loginSetup(driver, username, password);

        EventPageDriverSetUp.clickRequestLink(driver);
        assert driver.getCurrentUrl().equals("http://localhost:8084/list_users");
//        Thread.sleep(3000);
    }

    @Test(testName = "Test pledge link")
    public static void testPledgeLink() throws InterruptedException {

        HomePageDriverSetUp.registerButton(driver);

        String username = "testDonor1";
        String password = "Abc123!";

        UserProfileSetting.setupRegisterProfile(driver, username, "donor", 52246,
                password, "What is your favorite vacation spot?","beach",
                "What is the name of your first pet?", "pet");

        LoginDriverSetUp.loginSetup(driver, username, password);

        EventPageDriverSetUp.clickPledgeLink(driver);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
//        Thread.sleep(3000);
        assert driver.getCurrentUrl().equals("http://localhost:8084/showNewDonationForm");
//        Thread.sleep(3000);
    }




    @AfterSuite
    public static void cleanUp(){
        driver.manage().deleteAllCookies();
        driver.close();
    }

}
