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
        wb = driver.findElement(By.id("username"));
        jse.executeScript("arguments[0].value='Wangjunnan@gmail1.com';", wb);
        wb = driver.findElement(By.id("role"));
        jse.executeScript("arguments[0].value='donor';", wb);
        wb = driver.findElement(By.id("password"));
        jse.executeScript("arguments[0].value='1234567';", wb);
        wb = driver.findElement(By.id("question1"));
        jse.executeScript("arguments[0].value='What is the brand of your first car?';", wb);
        wb = driver.findElement(By.id("answer1"));
        jse.executeScript("arguments[0].value='civic';", wb);
        wb = driver.findElement(By.id("question2"));
        jse.executeScript("arguments[0].value='What is your hometown?';", wb);
        wb = driver.findElement(By.id("answer2"));
        jse.executeScript("arguments[0].value='China';", wb);
        wb = driver.findElement(By.cssSelector("body > div > form > div > div:nth-child(8) > button"));
        jse.executeScript("arguments[0].click()", wb);
        assert driver.getCurrentUrl().equals("http://localhost:8084/register");

        //Thread.sleep(5000);

    }











    @AfterSuite
    public static void cleanUp(){
        driver.manage().deleteAllCookies();
        driver.close();
    }
}
