package com.test;// Page URL: http://localhost:8084
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class EventPage extends PageObject{

    @FindBy(linkText = "Request Items for an Event")
    private WebElement requestItemButton;





    public EventPage(WebDriver driver) {
        super(driver);
    }

    private JavascriptExecutor jse = (JavascriptExecutor)driver;

    public void clickRequestItem(){
        jse.executeScript("arguments[0].click()", requestItemButton);
    }





}
