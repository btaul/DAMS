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

    public static void navigateToLogin(){
        //Navigate from Homepage to Loginpage
        driver.get(BaseUrl.BASE_URL);
        WebHomePage webHomePage = new WebHomePage(driver);

        webHomePage.clickRegister();
    }


    @Test(testName = "Test Login Button")
    public static void testRegister() throws InterruptedException {
        navigateToLogin();
        assert driver.getCurrentUrl().equals("http://localhost:8084/register");

        JavascriptExecutor jse = (JavascriptExecutor)driver;
        WebElement wb;
        wb = driver.findElement(By.id("email"));
        jse.executeScript("arguments[0].value='Wangjunnan2@gmail.com';", wb);
        wb = driver.findElement(By.id("password"));
        jse.executeScript("arguments[0].value='1234567';", wb);
        wb = driver.findElement(By.id("firstName"));
        jse.executeScript("arguments[0].value='Junnan';", wb);
        wb = driver.findElement(By.id("lastName"));
        jse.executeScript("arguments[0].value='Wang';", wb);
        wb = driver.findElement(By.cssSelector("body > div > form > div > div:nth-child(5) > button"));
        jse.executeScript("arguments[0].click()", wb);
        assert driver.getCurrentUrl().equals("http://localhost:8084/process_register");
        //Thread.sleep(5000);

    }











    @AfterSuite
    public static void cleanUp(){
        driver.manage().deleteAllCookies();
        driver.close();
    }
}
