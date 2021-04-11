package com.test;// Page URL: http://localhost:8084
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class RequestItemPage extends PageObject{




    @FindBy(id = "eventsID")
    private WebElement eventsID;

    @FindBy(id = "item")
    private WebElement item;

    @FindBy(id = "volume")
    private WebElement volume;

    @FindBy(css = "body > div > form > div.form-group.row3 > div:nth-child(4) > button")
    private WebElement submitRequestButton;


    public RequestItemPage(WebDriver driver) {
        super(driver);
    }

    private JavascriptExecutor jse = (JavascriptExecutor)driver;

    public void sendEventsID(String id){
        jse.executeScript(String.format("arguments[0].value='%s';", id), eventsID);
    }

    public void sendItem(String itemName){
        jse.executeScript(String.format("arguments[0].value='%s';", itemName), item);
    }

    public void sendVolume(Integer vol){
        jse.executeScript(String.format("arguments[0].value='%d';", vol), volume);
    }

    public void clickSubmitRequestButton(){
        jse.executeScript("arguments[0].click()", submitRequestButton);
    }


}
