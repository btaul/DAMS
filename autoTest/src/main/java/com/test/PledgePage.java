package com.test;
//This page is called: http://localhost:8084/showNewDonationForm

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PledgePage extends PageObject{

    @FindBy(id = "item")
    private WebElement item;

    @FindBy(id = "donationVolume")
    private WebElement donationVolume;

    @FindBy(id = "zip")
    private WebElement zip;

    @FindBy(css = "body > div > form > button")
    private WebElement saveDonation;


    public PledgePage(WebDriver driver) {
        super(driver);
    }

    private JavascriptExecutor jse = (JavascriptExecutor)driver;

    public void sendItem(String itemName){
        jse.executeScript(String.format("arguments[0].value='%s';", itemName), item);
    }

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
