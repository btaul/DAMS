package com.test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;


public class TestMakePledge7 {

    private static final WebDriver driver = new ChromeDriver();

    @BeforeSuite
    public static void startup() {
        // ChromeDriver location set up in Utils class
        System.setProperty("webdriver.chrome.driver", BaseUrl.CHROME_DRIVER_LOCATION);
    }

    public static void createAccountAndLoginToPledge(String username, String password, String role){
        HomePageDriverSetUp.registerButton(driver);



        UserProfileSettingAndRegister.setupRegisterProfile(driver, username, role, 52246,
                password, "What is the brand of your first car?","civic",
                "What is your hometown?", "Zhengzhou");

        LoginDriverSetUp.loginSetup(driver, username, password);

        EventPageDriverSetUp.clickPledgeLink(driver);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

    }

    public static void setPledge(WebDriver driver, String item, Integer donationVolume, Integer zip){
        PledgePage pledgePage = new PledgePage(driver);
        pledgePage.sendItem(item);
        pledgePage.sendDonationVolume(donationVolume);
        pledgePage.sendZip(zip);
        pledgePage.clickSaveDonation();

    }




    @Test(testName = "Test making a pledge")
    public static void testPledgeLink() throws InterruptedException {
        String username = "testDonor1";
        String password = "Abc123!";
        String role = "donor";
        //create an account -- donor
        createAccountAndLoginToPledge(username, password, role);


        String item = "apple";
        Integer donationVolume = 100;
        Integer zip = 52246;
        //create a pledge
        setPledge(driver, item, donationVolume, zip);

        assert driver.getCurrentUrl().equals("http://localhost:8084/donation");

//        Thread.sleep(3000);
    }






    @AfterSuite
    public static void cleanUp(){
        driver.manage().deleteAllCookies();
        driver.close();
    }

}
