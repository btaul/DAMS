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

    public static void navigateToLogin(WebHomePage webHomePage){
        //Navigate from Homepage to Loginpage
        driver.get(BaseUrl.BASE_URL);
//        WebHomePage webHomePage = new WebHomePage(driver);
        webHomePage.clickRegister();
    }


    @Test(testName = "Test Login Button")
    public static void testRegister() throws InterruptedException {
        WebHomePage webHomePage = new WebHomePage(driver);
        navigateToLogin(webHomePage);
        assert driver.getCurrentUrl().equals("http://localhost:8084/register");

        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.sendUsername("testReceipient1");
        registerPage.sendRole();
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



    @AfterSuite
    public static void cleanUp(){
        driver.manage().deleteAllCookies();
        driver.close();
    }
}
