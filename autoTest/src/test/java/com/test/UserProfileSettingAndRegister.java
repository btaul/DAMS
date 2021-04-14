package com.test;

import org.openqa.selenium.WebDriver;

public class UserProfileSettingAndRegister {
    //this is to sign up a user with the following info
    public static void setupRegisterProfile(WebDriver driver, String username, String role, Integer zipCode,
                                            String password, String q1, String a1, String q2, String a2){
        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.sendUsername(username);
        registerPage.sendRole(role);
        registerPage.sendZipcode(zipCode);
        registerPage.sendPassword(password);
        registerPage.sendQuestion1(q1);
        registerPage.sendAnswer1(a1);
        registerPage.sendQuestion2(q2);
        registerPage.sendAnswer2(a2);
        registerPage.clickSignUp();

    }
}
