package com.test;// Page URL: http://localhost:8084
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class DonationUpdatePage extends PageObject{



    @FindBy(id = "donationVolume")
    private WebElement donationVolume;

    @FindBy(id = "zip")
    private WebElement donationZip;

    @FindBy(css = "body > div > form > button")
    private WebElement saveDonation;



    public DonationUpdatePage(WebDriver driver) {
        super(driver);
    }

    private JavascriptExecutor jse = (JavascriptExecutor)driver;



    public void sendDonationVolume(Integer vol){
        jse.executeScript(String.format("arguments[0].value='%d';", vol), donationVolume);
    }

    public void sendZip(Integer zipCode){
        jse.executeScript(String.format("arguments[0].value='%d';", zipCode), donationZip);
    }

    public void setSaveDonation(){
        jse.executeScript("arguments[0].click()", saveDonation);
    }



}
