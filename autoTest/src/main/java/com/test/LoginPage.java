package com.test;// Page URL: http://localhost:8084
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class LoginPage extends PageObject{


    @FindBy(id = "username")
    private WebElement username;

    @FindBy(id = "password")
    private WebElement password;

    @FindBy(css = "body > div > form > button")
    private WebElement signInButton;


    public LoginPage(WebDriver driver) {
        super(driver);
    }

    private JavascriptExecutor jse = (JavascriptExecutor)driver;

    public void sendUsername(String user){
        jse.executeScript(String.format("arguments[0].value='%s';", user), username);
    }

    public void sendPassword(String pw){
        jse.executeScript(String.format("arguments[0].value='%s';", pw), password);
    }

    public void clickSignIn(){
        jse.executeScript("arguments[0].click()", signInButton);
    }

//    public String getLogin(){
//        return this.login.getText();
//    }
//    public void clickLogin(){
//        this.login.click();
//    }
//
//    public String getRegister(){
//        return this.register.getText();
//    }
//    public void clickRegister(){
//        this.register.click();
//    }
//
//    public String getListAllEvents(){
//        return this.listAllEvents.getText();
//    }
//    public void clickListAllEvents(){
//        this.listAllEvents.click();
//    }




}
