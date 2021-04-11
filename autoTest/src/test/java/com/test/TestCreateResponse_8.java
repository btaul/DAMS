package com.test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;


public class TestCreateResponse_8 {

    private static final WebDriver driver = new ChromeDriver();

    @BeforeSuite
    public static void startup() {
        // ChromeDriver location set up in Utils class
        System.setProperty("webdriver.chrome.driver", BaseUrl.CHROME_DRIVER_LOCATION);
    }

    public static void createAccountAndLogin(String username, String password, String role){
        HomePageDriverSetUp.registerButton(driver);



        UserProfileSetting.setupRegisterProfile(driver, username, role, 52246,
                password, "What is the brand of your first car?","civic",
                "What is your hometown?", "Zhengzhou");

        LoginDriverSetUp.loginSetup(driver, username, password);


        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

    }



    public static void setUpRequest(WebDriver driver, String eventsID, String item, Integer volume){
        RequestItemPage requestItemPage = new RequestItemPage(driver);
        requestItemPage.sendEventsID(eventsID);
        requestItemPage.sendItem(item);
        requestItemPage.sendVolume(volume);
        requestItemPage.clickSubmitRequestButton();
    }

    public static void createRequest(WebDriver driver, String eventsID, String item, Integer volume){
        EventPageDriverSetUp.clickRequestLink(driver);

        UserPageDriverSetUp.clickRequestButton(driver);

        setUpRequest(driver, eventsID, item, volume);

    }


    @Test(testName = "Test creating a request and responding to it")
    public static void testRequestItem() throws InterruptedException {

        //This assumes that we have an event with ID as "1".

        //create a recipient account and create a request
        String usernameRecipient = "testRecipient1";
        String passwordRecipient = "Abc123!";
        String roleRecipient = "recipient";
        createAccountAndLogin(usernameRecipient, passwordRecipient, roleRecipient);

        String eventsID = "1";
        String item = "cucumber";
        Integer volume = 50;
        createRequest(driver, eventsID, item, volume);

        //create a donor account and respond to the request above
        String usernameDonor = "testDonor1";
        String passwordDonor = "Abc123!";
        String roleDonor = "donor";
        createAccountAndLogin(usernameDonor, passwordDonor, roleDonor);
        EventPageDriverSetUp.clickResponseLink(driver);

        CreateResponse1 createResponse1 = new CreateResponse1(driver);
        createResponse1.sendEventId(eventsID);  // in creating a request step
        createResponse1.clickSaveDonation();

        assert driver.getCurrentUrl().equals("http://localhost:8084/continueResponse");

        CreateResponse2 createResponse2 = new CreateResponse2(driver);
        createResponse2.sendItem(item); // in creating a request step
        createResponse2.clickSaveDonation();
        assert driver.getCurrentUrl().equals("http://localhost:8084/continueResponse2");

        CreateResponse3 createResponse3 = new CreateResponse3(driver);
        Integer donationVolume = 10;
        Integer zipDonation = 52246;
        createResponse3.sendDonationVolume(donationVolume);
        createResponse3.sendZip(zipDonation);
        createResponse3.clickSaveDonation();

        assert driver.getCurrentUrl().equals("http://localhost:8084/donation");

//      We should have a step to delete the request function later!

//        Thread.sleep(3000);

    }







    @AfterSuite
    public static void cleanUp(){
        driver.manage().deleteAllCookies();
        driver.close();
    }

}
