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

import java.util.concurrent.TimeUnit;


public class TestLoginPage {
    private static final WebDriver driver = new ChromeDriver();

    @BeforeSuite
    public static void startup() {
        // ChromeDriver location set up in Utils class
        System.setProperty("webdriver.chrome.driver", BaseUrl.CHROME_DRIVER_LOCATION);

    }




    @Test(testName = "Test Login Button")
    public static void testLogin() throws InterruptedException {



        WebHomePage webHomePage = new WebHomePage(driver);
        driver.get(BaseUrl.BASE_URL);
        webHomePage.clickLogin();
        assert driver.getCurrentUrl().equals("http://localhost:8084/login");

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        LoginPage loginPage = new LoginPage(driver);
        loginPage.sendUsername("testReceipient1");
        loginPage.sendPassword("Abc123!");
        loginPage.clickSignIn();
        assert driver.getCurrentUrl().equals("http://localhost:8084/list_events");


//        RegisterPage registerPage = new RegisterPage(driver);
//        registerPage.sendUsername("testReceipient1");
//        registerPage.sendRole();
//        registerPage.sendZipcode(52246);
//        registerPage.sendPassword("Abc123!");
//        registerPage.sendQuestion1("What is the brand of your first car?");
//        registerPage.sendAnswer1("civic");
//        registerPage.sendQuestion2("What is your hometown?");
//        registerPage.sendAnswer2("ZZ");
//        registerPage.clickSignUp();
//        assert driver.getCurrentUrl().equals("http://localhost:8084/register");


        Thread.sleep(3000);
//        navigateToLogin();
//        assert driver.getCurrentUrl().equals("http://localhost:8084/login");
//        JavascriptExecutor jse = (JavascriptExecutor)driver;
//        WebElement wb = driver.findElement(By.id("username"));
//        jse.executeScript("arguments[0].value='junnwang';", wb);
//        jse.executeScript("document.getElementById('password').value='1234567';");
//        wb = driver.findElement(By.cssSelector("body > div > form > button"));
//        jse.executeScript("arguments[0].click()", wb);
//        //Thread.sleep(5000);
//        assert driver.getCurrentUrl().equals("http://localhost:8084/list_users");

    }






    @AfterSuite
    public static void cleanUp(){
        driver.manage().deleteAllCookies();
        driver.close();
    }



}
