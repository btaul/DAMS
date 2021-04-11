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




    @Test(testName = "Test Register for a receipient")
    public static void testRegisterReceipient() throws InterruptedException {

        HomePageDriverSetUp.registerButton(driver);

        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.sendUsername("testReceipient1");
        registerPage.sendRole("recipient");
        registerPage.sendZipcode(52246);
        registerPage.sendPassword("Abc123!");
        registerPage.sendQuestion1("What is the brand of your first car?");
        registerPage.sendAnswer1("civic");
        registerPage.sendQuestion2("What is your hometown?");
        registerPage.sendAnswer2("ZZ");
        registerPage.clickSignUp();
        assert driver.getCurrentUrl().equals("http://localhost:8084/register");

//        Thread.sleep(3000);

    }

    @Test(testName = "Test Register for a donor")
    public static void testRegisterDonor() throws InterruptedException {

        HomePageDriverSetUp.registerButton(driver);


        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.sendUsername("testDonor1");
        registerPage.sendRole("donor");
        registerPage.sendZipcode(52246);
        registerPage.sendPassword("Abc123!");
        registerPage.sendQuestion1("What is your favorite vacation spot?");
        registerPage.sendAnswer1("beach");
        registerPage.sendQuestion2("What is the name of your first pet?");
        registerPage.sendAnswer2("zhizhi");
        registerPage.clickSignUp();
        assert driver.getCurrentUrl().equals("http://localhost:8084/register");

//        Thread.sleep(3000);

    }


    @AfterSuite
    public static void cleanUp(){
        driver.manage().deleteAllCookies();
        driver.close();
    }
}
