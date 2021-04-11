package com.test;// Page URL: http://localhost:8084
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class DonationPage extends PageObject{




    @FindBy(css = "body > div > table > tbody > tr:nth-child(1) > td:nth-child(7) > a.btn.btn-primary")
    private WebElement updateFirst;

    @FindBy(css = "body > div > table > tbody > tr:nth-child(1) > td:nth-child(7) > a.btn.btn-danger")
    private WebElement deleteFirst;



    public DonationPage(WebDriver driver) {
        super(driver);
    }

    private JavascriptExecutor jse = (JavascriptExecutor)driver;


    public void clickUpdateFirst(){
        jse.executeScript("arguments[0].click()", updateFirst);
    }

    public void clickDeleteFirst(){
        jse.executeScript("arguments[0].click()", deleteFirst);
    }


}
