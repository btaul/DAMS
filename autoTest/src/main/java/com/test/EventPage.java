package com.test;// Page URL: http://localhost:8084
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class EventPage extends PageObject{

    @FindBy(linkText = "Request Items for an Event")
    private WebElement requestItemLink;

    @FindBy(linkText = "Make a Pledge")
    private WebElement pledgeLink;




    public EventPage(WebDriver driver) {
        super(driver);
    }

    private JavascriptExecutor jse = (JavascriptExecutor)driver;

    public void clickRequestItemLink(){
        jse.executeScript("arguments[0].click()", requestItemLink);
    }

    public void clickPledgeLink(){
        jse.executeScript("arguments[0].click()", pledgeLink);
    }




}
