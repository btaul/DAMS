package com.test;// Page URL: http://localhost:8084
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class UserPage extends PageObject{

    @FindBy(css = "body > div > div:nth-child(2) > form > input[type=submit]")
    private WebElement requestItemButton;





    public UserPage(WebDriver driver) {
        super(driver);
    }

    private JavascriptExecutor jse = (JavascriptExecutor)driver;


    public void clickRequestItemButton(){
        jse.executeScript("arguments[0].click()", requestItemButton);
    }





}
