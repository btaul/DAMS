package com.test;// Page URL: http://localhost:8084
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class CreateResponse2 extends PageObject{


    @FindBy(id = "item")
    private WebElement item;

    @FindBy(css = "body > div > form > button")
    private WebElement saveDonation;


    public CreateResponse2(WebDriver driver) {
        super(driver);
    }

    private JavascriptExecutor jse = (JavascriptExecutor)driver;

    public void sendItem(String itemName){
        jse.executeScript(String.format("arguments[0].value='%s';", itemName), item);
    }

    public void clickSaveDonation(){
        jse.executeScript("arguments[0].click()", saveDonation);
    }


}
