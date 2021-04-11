package com.test;

import com.test.BaseUrl;
import com.test.WebHomePage;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;


public class TestRegisterPage {


    private static final WebDriver driver = new ChromeDriver();

    @BeforeSuite
    public static void startup() {
        // ChromeDriver location set up in Utils class
        System.setProperty("webdriver.chrome.driver", BaseUrl.CHROME_DRIVER_LOCATION);

    }

    public static void setupRegisterProfile(WebDriver driver, String username, String role, Integer zipCode,
            String password, String q1, String a1, String q2, String a2){
        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.sendUsername(username);
        registerPage.sendRole(role);
        registerPage.sendZipcode(zipCode);
        registerPage.sendPassword(password);
        registerPage.sendQuestion1(q1);
        registerPage.sendAnswer1(a1);
        registerPage.sendQuestion2(q2);
        registerPage.sendAnswer2(a2);
        registerPage.clickSignUp();

    }




    @Test(testName = "Test Register for a receipient")
    public static void testRegisterReceipient() throws InterruptedException {

        HomePageDriverSetUp.registerButton(driver);

        setupRegisterProfile(driver, "testReceipient1", "recipient", 52246,
                "Abc123!", "What is the brand of your first car?","civic",
                "What is your hometown?", "Zhengzhou");

        assert driver.getCurrentUrl().equals("http://localhost:8084/register");

//        Thread.sleep(3000);

    }

    @Test(testName = "Test Register for a donor")
    public static void testRegisterDonor() throws InterruptedException {

        HomePageDriverSetUp.registerButton(driver);

        setupRegisterProfile(driver, "testDonor1", "donor", 52246,
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
