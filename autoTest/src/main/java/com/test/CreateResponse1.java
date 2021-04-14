package com.test;// Page URL: http://localhost:8084
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class CreateResponse1 extends PageObject{


    @FindBy(id = "eventId")
    private WebElement eventId;

    @FindBy(css = "body > div > form > button")
    private WebElement saveDonation;


    public CreateResponse1(WebDriver driver) {
        super(driver);
    }

    private JavascriptExecutor jse = (JavascriptExecutor)driver;

    public void sendEventId(String id){
        jse.executeScript(String.format("arguments[0].value='%s';", id), eventId);
    }

    public void clickSaveDonation(){
        jse.executeScript("arguments[0].click()", saveDonation);
    }


}
