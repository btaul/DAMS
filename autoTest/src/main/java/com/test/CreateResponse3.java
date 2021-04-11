package com.test;// Page URL: http://localhost:8084
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class CreateResponse3 extends PageObject{


    @FindBy(id = "donationVolume")
    private WebElement donationVolume;

    @FindBy(id = "zip")
    private WebElement zip;

    @FindBy(css = "body > div > form > button")
    private WebElement saveDonation;


    public CreateResponse3(WebDriver driver) {
        super(driver);
    }

    private JavascriptExecutor jse = (JavascriptExecutor)driver;

    public void sendDonationVolume(Integer vol){
        jse.executeScript(String.format("arguments[0].value='%d';", vol), donationVolume);
    }

    public void sendZip(Integer zipCode){
        jse.executeScript(String.format("arguments[0].value='%d';", zipCode), zip);
    }

    public void clickSaveDonation(){
        jse.executeScript("arguments[0].click()", saveDonation);
    }


}
