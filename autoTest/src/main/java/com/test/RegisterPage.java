package com.test;// Page URL: http://localhost:8084
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class RegisterPage extends PageObject{

    @FindBy(id = "username")
    private WebElement username;

    @FindBy(id = "role")
    private WebElement role;

    @FindBy(id = "zipcode")
    private WebElement zipcode;

    @FindBy(id = "password")
    private WebElement password;

    @FindBy(id = "question1")
    private WebElement question1;

    @FindBy(id = "answer1")
    private WebElement answer1;

    @FindBy(id = "question2")
    private WebElement question2;

    @FindBy(id = "answer2")
    private WebElement answer2;

    @FindBy(css = "body > div > form > div > div:nth-child(9) > button")
    private WebElement signUpButton;


    public RegisterPage(WebDriver driver) {
        super(driver);
    }
//    WebElement wb;
//    wb = driver.findElement(By.id("username"));
//        jse.executeScript("arguments[0].value='Wangjunnan@gmail1.com';", wb);
    private JavascriptExecutor jse = (JavascriptExecutor)driver;

    public void sendUsername(String user){
        jse.executeScript(String.format("arguments[0].value='%s';", user), username);
    }

    public void sendRole(String selectedRole){
        jse.executeScript(String.format("arguments[0].value='%s';", selectedRole), role);
    }

    public void sendZipcode(Integer zip){
        jse.executeScript(String.format("arguments[0].value='%d';", zip), zipcode);
    }

    public void sendPassword(String pw){
        jse.executeScript(String.format("arguments[0].value='%s';", pw), password);
    }

    public void sendQuestion1(String q1){
        jse.executeScript(String.format("arguments[0].value='%s';", q1), question1);
    }

    public void sendAnswer1(String a1){
        jse.executeScript(String.format("arguments[0].value='%s';", a1), answer1);
    }

    public void sendQuestion2(String q2){
        jse.executeScript(String.format("arguments[0].value='%s';", q2), question2);
    }

    public void sendAnswer2(String a2){
        jse.executeScript(String.format("arguments[0].value='%s';", a2), answer2);
    }

    public void clickSignUp(){
        jse.executeScript("arguments[0].click()", signUpButton);
    }






}
