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

    public static void createAccountAndLogin(String username, String password, String role){
        HomePageDriverSetUp.registerButton(driver);



        UserProfileSetting.setupRegisterProfile(driver, username, role, 52246,
                password, "What is the brand of your first car?","civic",
                "What is your hometown?", "Zhengzhou");

        LoginDriverSetUp.loginSetup(driver, username, password);

    }



    @Test(testName = "Test requestItem Link")
    public static void testRequestItemLink() throws InterruptedException {


        String username = "testRecipient1";
        String password = "Abc123!";
        String role = "recipient";
        createAccountAndLogin(username, password, role);
        EventPageDriverSetUp.clickRequestLink(driver);
        assert driver.getCurrentUrl().equals("http://localhost:8084/list_users");
//        Thread.sleep(3000);
    }

    @Test(testName = "Test pledge link")
    public static void testPledgeLink() throws InterruptedException {
        String username = "testDonor1";
        String password = "Abc123!";
        String role = "donor";
        createAccountAndLogin(username, password, role);

        EventPageDriverSetUp.clickPledgeLink(driver);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
//        Thread.sleep(3000);
        assert driver.getCurrentUrl().equals("http://localhost:8084/showNewDonationForm");
//        Thread.sleep(3000);
    }

    @Test(testName = "Test response link")
    public static void testResponseLink() throws InterruptedException {


        String username = "testDonor1";
        String password = "Abc123!";
        String role = "donor";
        createAccountAndLogin(username, password, role);

        EventPageDriverSetUp.clickResponseLink(driver);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
//        Thread.sleep(3000);
        assert driver.getCurrentUrl().equals("http://localhost:8084/responseToRequest");
//        Thread.sleep(3000);
    }

    @Test(testName = "Test donation link")
    public static void testDonationLink() throws InterruptedException {

        String username = "testDonor1";
        String password = "Abc123!";
        String role = "donor";
        createAccountAndLogin(username, password, role);

        EventPageDriverSetUp.clickDonationLink(driver);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
//        Thread.sleep(3000);
        assert driver.getCurrentUrl().equals("http://localhost:8084/donation");
//        Thread.sleep(3000);
    }





    @AfterSuite
    public static void cleanUp(){
        driver.manage().deleteAllCookies();
        driver.close();
    }

}
