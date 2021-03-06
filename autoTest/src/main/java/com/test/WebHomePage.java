package com.test;// Page URL: http://localhost:8084
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class WebHomePage extends PageObject{


    @FindBy(linkText = "Login")
    private WebElement login;

    @FindBy(linkText = "Register")
    private WebElement register;

    @FindBy(linkText = "List All Events")
    private WebElement listAllEvents;


    public WebHomePage(WebDriver driver) {
        super(driver);
    }

    public String getLogin(){
        return this.login.getText();
    }
    public void clickLogin(){
        this.login.click();
    }

    public String getRegister(){
        return this.register.getText();
    }
    public void clickRegister(){
        this.register.click();
    }

    public String getListAllEvents(){
        return this.listAllEvents.getText();
    }
    public void clickListAllEvents(){
        this.listAllEvents.click();
    }




}
